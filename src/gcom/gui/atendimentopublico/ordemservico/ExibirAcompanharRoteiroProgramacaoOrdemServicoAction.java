package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.ProgramacaoRoteiro;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.bean.OSProgramacaoHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterCargaTrabalhoEquipeHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAcompanharRoteiroProgramacaoOrdemServicoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("acompanharOrdemServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
			acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;

		// Data do Roteiro
		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());
		//Date dataRoteiro = Util.converteStringParaDate("12/09/2006");
		
		// Precisa pegar a unidade do usuario do login que esta na sessao
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();

		acompanharActionForm.setDataRoteiro(Util.formatarData(dataRoteiro));
		acompanharActionForm.setUnidadeLotacao(""+idUnidadeLotacao);
		
		String tipoAcao = httpServletRequest.getParameter("tipoAcao");
		
		// [SB0005] - Incluir Ordem de Serviço na Programação
		if(tipoAcao != null && tipoAcao.equals("I")){
			
			this.incluirOrdemServicoProgramacao(acompanharActionForm,usuario);

		// [SB0006] - Aloca Equipes para a Ordem de Serviço
		}else if (tipoAcao != null && tipoAcao.equals("A")){

			this.alocaEquipesParaOrdemServico(acompanharActionForm,usuario);
			
		// [SB0007] - Remaneja Ordem de Serviço
		}else if (tipoAcao != null && tipoAcao.equals("R")){
			
			this.remanejaOrdemServico(acompanharActionForm,usuario);

		// [SB0008] - Informa Situação da Ordem de Serviço		
		} else if (tipoAcao != null && tipoAcao.equals("S") ){

			this.informaSituacaoOrdemServico(acompanharActionForm,usuario,sessao);
			
		// [SB0013] - Reordena Sequencial na Programacao - Nova Ordem
		} else if (tipoAcao != null && tipoAcao.equals("P") ){
			
			this.reordenaSequencialNaProgramacaoNovaOrdem(acompanharActionForm,sessao);
			
		}

		String msg = httpServletRequest.getParameter("mensagemRetorno");
		if(msg != null && !msg.equals("")){
			acompanharActionForm.setMensagemRetorno(msg);	
		}
		
		
		
		// Monta o mapOsProgramacaoHelper com as ordens de serviço de programação
		this.montaOrdemServicoProgramacao(sessao,dataRoteiro,idUnidadeLotacao);
		
		// [SB0010]-Prepara Barra de Carga de Trabalho 
		this.preparaBarraCargaTrabalho(sessao,dataRoteiro);
		
		return retorno;
	}
	
	/**
	 * [SB0005] - Incluir Ordem de Serviço na Programação
	 * 
	 * @author Rafael Pinto
	 * @date 28/08/2006
	 * 
	 * @param AcompanharRoteiroProgramacaoOrdemServicoActionForm,usuario
	 */	
	private void incluirOrdemServicoProgramacao(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm,
		Usuario usuario){

		Fachada fachada = Fachada.getInstancia();
		
		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());
		
		
		// Colocado por Raphael Rossiter em 12/03/2007
		Integer idUnidadeLotacao = new Integer(acompanharActionForm.getUnidadeLotacao());
		
		OrdemServico os = 
		Fachada.getInstancia().recuperaOSPorId(new Integer(acompanharActionForm.getIdOrdemServico()));
		
		//[FS0008] - Verificar possibilidade da inclusão da ordem de serviço
		fachada.validarInclusaoOsNaProgramacao(os, idUnidadeLotacao);
		
		// [SB0012] - Reordena Sequencial de Programação - Inclusão de Ordem de Serviço
		short maiorSequencial = new Short(acompanharActionForm.getSequencialProgramacao());
		short sequencial = new Short(acompanharActionForm.getSequencialProgramacaoPrevisto());
		
		if(maiorSequencial != sequencial){
			fachada.reordenaSequencialProgramacaoNovaOrdem(dataRoteiro,sequencial,maiorSequencial,
					Util.converterStringParaInteger(acompanharActionForm.getIdEquipe()), false);
		}
		
		// [SB0011] - Incluir Programação
		OrdemServicoProgramacao ordemServicoProgramacao = new OrdemServicoProgramacao();
		
		ProgramacaoRoteiro programacaoRoteiro = fachada.pesquisarProgramacaoRoteiro(usuario.getUnidadeOrganizacional().getId(),dataRoteiro);
		
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setId(new Integer(acompanharActionForm.getIdOrdemServico()));
		
		ordemServicoProgramacao.setProgramacaoRoteiro(programacaoRoteiro);
		ordemServicoProgramacao.setOrdemServico(ordemServico);
		
		Equipe equipe = new Equipe();
		equipe.setId(new Integer(acompanharActionForm.getIdEquipe()));
		
		ordemServicoProgramacao.setEquipe(equipe);
		ordemServicoProgramacao.setUsuarioProgramacao(usuario);
		ordemServicoProgramacao.setUsuarioFechamento(null);
		ordemServicoProgramacao.setOsProgramNaoEncerMotivo(null);
		ordemServicoProgramacao.setNnSequencialProgramacao(sequencial);
		ordemServicoProgramacao.setIndicadorAtivo(ConstantesSistema.SIM);
		ordemServicoProgramacao.setIndicadorEquipePrincipal(ConstantesSistema.SIM);
		ordemServicoProgramacao.setUltimaAlteracao(new Date());
		ordemServicoProgramacao.setSituacaoFechamento(OrdemServicoProgramacao.SITUACAO_VAZIO);
		
		fachada.incluirOrdemServicoProgramacao(ordemServicoProgramacao, usuario);
		
	}
	
	/**
	 * [SB0006] - Aloca Equipes para a Ordem de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 28/08/2006
	 * 
	 * @param AcompanharRoteiroProgramacaoOrdemServicoActionForm,usuario
	 */
	private void alocaEquipesParaOrdemServico(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm,
		Usuario usuario){
		
		Fachada fachada = Fachada.getInstancia();
		
		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());
		
		Integer equipePrincipal = new Integer(acompanharActionForm.getIdEquipePrincipal());
		String[] equipeSelecionada = acompanharActionForm.getEquipeSelecionada();
		String[] equipeSelecionadaBanco = acompanharActionForm.getEquipeSelecionadaAtual();
		
		Collection equipeSelecionadaParaAlocar = 
			Util.separarCamposString(",",equipeSelecionada[0]) ;

		for (int i = 0; i < equipeSelecionadaBanco.length; i++) {
			
			String idEquipeBanco = equipeSelecionadaBanco[i];
			
			//Se nao tem mais a osProgramacao na lista de equipes para alocar,entao deve-se remover
			//essa programação
			if(!equipeSelecionadaParaAlocar.contains(idEquipeBanco)){
				
				fachada.alocaEquipeParaOs(new Integer(acompanharActionForm.getIdOrdemServico()),
					dataRoteiro,new Integer(idEquipeBanco), false, null);

			}else{
				equipeSelecionadaParaAlocar.remove(idEquipeBanco);					
			}
		}
		
		// Se sobrou alguma equipe, então de inserir a osProgramacao
		if(equipeSelecionadaParaAlocar != null && !equipeSelecionadaParaAlocar.isEmpty()){
			Iterator itera = equipeSelecionadaParaAlocar.iterator();
			while (itera.hasNext()) {
				
				String idEquipe = (String) itera.next();
				int id = new Integer(idEquipe).intValue();
				
				// [SB0011] - Incluir Programação
				OrdemServicoProgramacao ordemServicoProgramacao = new OrdemServicoProgramacao();
				
				ProgramacaoRoteiro programacaoRoteiro = fachada.pesquisarProgramacaoRoteiro(usuario.getUnidadeOrganizacional().getId(),dataRoteiro);
				
				OrdemServico ordemServico = new OrdemServico();
				ordemServico.setId(new Integer(acompanharActionForm.getIdOrdemServico()));
				
				ordemServicoProgramacao.setProgramacaoRoteiro(programacaoRoteiro);
				ordemServicoProgramacao.setOrdemServico(ordemServico);
				
				Equipe equipe = new Equipe();
				equipe.setId(new Integer(idEquipe));
				
				ordemServicoProgramacao.setEquipe(equipe);
				ordemServicoProgramacao.setUsuarioProgramacao(usuario);
				ordemServicoProgramacao.setUsuarioFechamento(null);
				ordemServicoProgramacao.setOsProgramNaoEncerMotivo(null);
				ordemServicoProgramacao.setNnSequencialProgramacao((short)ConstantesSistema.NUMERO_NAO_INFORMADO);
				ordemServicoProgramacao.setIndicadorAtivo(ConstantesSistema.SIM);

				if(equipePrincipal.intValue() == id){
					ordemServicoProgramacao.setIndicadorEquipePrincipal(ConstantesSistema.SIM);	
				}else{
					ordemServicoProgramacao.setIndicadorEquipePrincipal(ConstantesSistema.NAO);						
				}
				
				ordemServicoProgramacao.setUltimaAlteracao(new Date());
				ordemServicoProgramacao.setSituacaoFechamento(OrdemServicoProgramacao.SITUACAO_VAZIO);
				
				fachada.incluirOrdemServicoProgramacao(ordemServicoProgramacao, usuario);
			}
			
		}
	}
	
	/**
	 * [SB0007] - Remaneja Ordem de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 28/08/2006
	 * 
	 * @param AcompanharRoteiroProgramacaoOrdemServicoActionForm,usuario
	 */
	private void remanejaOrdemServico(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm,
		Usuario usuario){

		Fachada fachada = Fachada.getInstancia();
		
		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());
		
		Integer equipeRemanejada = new Integer(acompanharActionForm.getIdEquipePrincipal());
		Integer equipeAtual = new Integer(acompanharActionForm.getIdEquipeAtual());
		Integer idOrdemServico = new Integer(acompanharActionForm.getIdOrdemServico());

		// Usa o mesmo metodo de alocar equipe
		fachada.alocaEquipeParaOs(idOrdemServico,dataRoteiro,equipeAtual, false, null);
		
		// [SB0011] - Incluir Programação
		OrdemServicoProgramacao ordemServicoProgramacao = new OrdemServicoProgramacao();
		
		ProgramacaoRoteiro programacaoRoteiro = fachada.pesquisarProgramacaoRoteiro(usuario.getUnidadeOrganizacional().getId(),dataRoteiro);
		
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setId(new Integer(acompanharActionForm.getIdOrdemServico()));
		
		ordemServicoProgramacao.setProgramacaoRoteiro(programacaoRoteiro);
		ordemServicoProgramacao.setOrdemServico(ordemServico);
		
		Equipe equipe = new Equipe();
		equipe.setId(new Integer(equipeRemanejada));
		
		ordemServicoProgramacao.setEquipe(equipe);
		ordemServicoProgramacao.setUsuarioProgramacao(usuario);
		ordemServicoProgramacao.setUsuarioFechamento(null);
		ordemServicoProgramacao.setOsProgramNaoEncerMotivo(null);
		ordemServicoProgramacao.setNnSequencialProgramacao((short)ConstantesSistema.NUMERO_NAO_INFORMADO);
		ordemServicoProgramacao.setIndicadorAtivo(ConstantesSistema.SIM);
		ordemServicoProgramacao.setIndicadorEquipePrincipal(ConstantesSistema.SIM);	
		
		ordemServicoProgramacao.setUltimaAlteracao(new Date());
		ordemServicoProgramacao.setSituacaoFechamento(OrdemServicoProgramacao.SITUACAO_VAZIO);
		
		fachada.incluirOrdemServicoProgramacao(ordemServicoProgramacao, usuario);
		
	}
	
	/**
	 * [SB0008] - Informa Situação da Ordem de Serviço
	 * 
	 * @author Rafael Pinto
	 * @date 28/08/2006
	 * 
	 * @param AcompanharRoteiroProgramacaoOrdemServicoActionForm,usuario
	 */
	private void informaSituacaoOrdemServico(
		AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm,Usuario usuario,HttpSession sessao){
		
		Fachada fachada = Fachada.getInstancia();
		
		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());
		
		Short novaSituacaoOs = new Short(acompanharActionForm.getSituacaoOrdemServico());
		
		Integer motivoNaoEncerramentoOs = null;
		if(acompanharActionForm.getMotivoNaoEncerramento() != null && 
			!acompanharActionForm.getMotivoNaoEncerramento().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			motivoNaoEncerramentoOs = new Integer(acompanharActionForm.getMotivoNaoEncerramento());
		}

		Integer idOrdemServico = new Integer(acompanharActionForm.getIdOrdemServico());
		
		OrdemServico ordemServico = fachada.recuperaOSPorId(idOrdemServico);
		
		if(novaSituacaoOs.shortValue() == OrdemServico.SITUACAO_PENDENTE.shortValue() || 
			novaSituacaoOs.shortValue() == OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO.shortValue() ){
			
			ordemServico.setSituacao(novaSituacaoOs);
			
			if(novaSituacaoOs.shortValue() == OrdemServico.SITUACAO_PENDENTE.shortValue()){
				ordemServico.setIndicadorProgramada(OrdemServico.NAO_PROGRAMADA);	
			}
			
			
			fachada.atualizarOrdemServico(ordemServico,usuario);
			
			boolean naoInformaIndicadorAtivo = true;
			
			fachada.atualizarOrdemServicoProgramacaoSituacaoOs(idOrdemServico,
				dataRoteiro,novaSituacaoOs,motivoNaoEncerramentoOs, naoInformaIndicadorAtivo);
		
		}
		
		String chaveEquipe = acompanharActionForm.getChaveEquipe();
		
		HashMap mapEquipe = (HashMap) sessao.getAttribute("mapEquipe");

		Equipe equipe = (Equipe) mapEquipe.get(chaveEquipe);
		
//		Integer equipePrincipal = new Integer(acompanharActionForm.getIdEquipePrincipal());
		
		fachada.reordenarSequencialProgramacao(dataRoteiro, equipe.getId());
	}
	
	/**
	 * [SB0013] - Reordena Sequencial na Programacao - Nova Ordem
	 * 
	 * @author Rafael Pinto
	 * @date 28/08/2006
	 * 
	 * @param AcompanharRoteiroProgramacaoOrdemServicoActionForm,sessao
	 */
	private void reordenaSequencialNaProgramacaoNovaOrdem(
		AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm,HttpSession sessao){

		Date dataRoteiro = Util.converteStringParaDate(acompanharActionForm.getDataRoteiro());
		
		short sequencialAtual 		= new Short(acompanharActionForm.getSequencialProgramacao());
		int sequencialInformado 	= new Integer(acompanharActionForm.getSequencialProgramacaoPrevisto());
		String chaveEquipe = acompanharActionForm.getChaveEquipe();

		HashMap mapOsProgramacaoHelper = (HashMap) sessao.getAttribute("mapOsProgramacaoHelper");
		
		Collection colecaoHelper = (ArrayList) mapOsProgramacaoHelper.get(chaveEquipe);
		int valor = this.retornaUltimoSequencial(colecaoHelper);
		
		if(sequencialInformado > valor){
			throw new ActionServletException("atencao.sequencial_programacao_maior_limite",null,""+valor);
		}
		HashMap mapEquipe = (HashMap) sessao.getAttribute("mapEquipe");

		Equipe equipe = (Equipe) mapEquipe.get(chaveEquipe);
		
		Fachada.getInstancia().reordenaSequencialProgramacaoNovaOrdem(dataRoteiro,sequencialAtual,
				(short)sequencialInformado,equipe.getId(), false);
	}
	
	
	
	
	/**
	 * Monta um HashMap(nomeEquipe,Colecao de OSProgramacaoHelper) a partir 
	 * da colecao de Ordem de Servico Programcao associado a data de roteiro
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao,data do roteiro
	 */
	private void montaOrdemServicoProgramacao(HttpSession sessao,
		Date dataRoteiro,Integer idUnidadeLotacao){
		
		Collection<OrdemServicoProgramacao> colecaoOrdemServicoProgramacao = 
			Fachada.getInstancia().recuperaOSProgramacaoPorDataRoteiroUnidade(dataRoteiro,idUnidadeLotacao);
		
		HashMap mapOsProgramacaoHelper = new HashMap();
		HashMap mapEquipe = new HashMap();
		
		
		if(colecaoOrdemServicoProgramacao != null && !colecaoOrdemServicoProgramacao.isEmpty()){
			
			Iterator itera = colecaoOrdemServicoProgramacao.iterator();
			
			while (itera.hasNext()) {
				
				OrdemServicoProgramacao ordemServicoProgramacao = (OrdemServicoProgramacao) itera.next();
				OrdemServico ordemServico = ordemServicoProgramacao.getOrdemServico();
				
				Equipe equipe = ordemServicoProgramacao.getEquipe();
				String chave =  Util.removerSimbolosPontuacao(equipe.getNome().toString());
				
				OSProgramacaoHelper helper = new OSProgramacaoHelper();
				
				int qtdDiasCliente = ConstantesSistema.NUMERO_NAO_INFORMADO;
				int qtdDiasAgencia = ConstantesSistema.NUMERO_NAO_INFORMADO;
				
				if(ordemServico.getRegistroAtendimento() != null && !ordemServico.getRegistroAtendimento().equals("")) {
					Date dataPrevistaAtual = ordemServico.getRegistroAtendimento().getDataPrevistaAtual();
					
					if(dataPrevistaAtual != null){
						qtdDiasCliente = 
							Util.obterQuantidadeDiasEntreDuasDatas(dataPrevistaAtual,new Date());
					}
					
					Date dataAgenciaReguladoraPrevisaoAtual = 
						Fachada.getInstancia().obterDataAgenciaReguladoraPrevisaoAtual(
							ordemServico.getRegistroAtendimento().getId());
					
					if(dataAgenciaReguladoraPrevisaoAtual != null){

						qtdDiasAgencia = 
							Util.obterQuantidadeDiasEntreDuasDatas(dataAgenciaReguladoraPrevisaoAtual,new Date());
					}
					
					int logradouro = ConstantesSistema.NUMERO_NAO_INFORMADO;
					if(ordemServico.getRegistroAtendimento().getLogradouroBairro() != null){
						logradouro = ordemServico.getRegistroAtendimento().getLogradouroBairro().getId().intValue();
					}
					
					Collection colecaoAlertaLogradouro = pesquisaEquipePeloLogradouro(sessao,logradouro,chave);
					if(colecaoAlertaLogradouro != null && !colecaoAlertaLogradouro.isEmpty()){
						helper.setTemAlerta(true);
						helper.setColecaoAlertaEquipeDeLogradouro(colecaoAlertaLogradouro);
					}
				}
				
				helper.setPodeRemover(false);
				
				ServicoPerfilTipo servicoPerfilTipo = ordemServico.getServicoTipo().getServicoPerfilTipo();
				ServicoPerfilTipo servicoPerfilTipoEquipe = equipe.getServicoPerfilTipo();
				
				if(servicoPerfilTipo != null && servicoPerfilTipoEquipe != null &&
					servicoPerfilTipo.getId().intValue() != servicoPerfilTipoEquipe.getId().intValue()){
					
					helper.setTemAlerta(true);
					helper.setAlertaEquipeDeServicoPerfilTipo(chave);
					
				}else if((servicoPerfilTipo == null && equipe.getServicoPerfilTipo() != null) ||
						 (servicoPerfilTipo != null && equipe.getServicoPerfilTipo() == null) ){

					helper.setTemAlerta(true);
					helper.setAlertaEquipeDeServicoPerfilTipo(chave);
				}
				
				if(qtdDiasCliente > 0){
					helper.setDiasAtrasoCliente(qtdDiasCliente);
				}

				if(qtdDiasAgencia > 0){
					helper.setDiasAtrasoAgencia(qtdDiasAgencia);
				}

				ObterDescricaoSituacaoOSHelper obter =  
					Fachada.getInstancia().obterDescricaoSituacaoOS(ordemServico.getId());
				
				
				helper.setSituacao(obter.getDescricaoAbreviadaSituacao());
				String endereco = Fachada.getInstancia().obterEnderecoAbreviadoOS(ordemServico.getId());
				helper.setEndereco(endereco);
				helper.setOrdemServicoProgramacao(ordemServicoProgramacao);
				
				if(!mapOsProgramacaoHelper.containsKey(chave)){

					Collection colecaoHelper = new ArrayList();	
					colecaoHelper.add(helper);

					mapOsProgramacaoHelper.put(chave,colecaoHelper);
					
				}else{

					Collection colecaoHelper = (ArrayList) mapOsProgramacaoHelper.get(chave);
					colecaoHelper.add(helper);
					
					mapOsProgramacaoHelper.put(chave,colecaoHelper);
					
				}
				
				mapEquipe.put(chave,equipe);
			}
		} else {
			throw new ActionServletException("atencao.ordem_servico_programacao_inexistente",
					null,Util.formatarData(dataRoteiro));			
		}

		sessao.setAttribute("mapOsProgramacaoHelper",mapOsProgramacaoHelper);
		sessao.setAttribute("mapEquipe",mapEquipe);
	}
	
	/**
	 * [SB00010] - Prepara Barra da Carga de Trabalho 
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao,data do roteiro
	 */	
	private void preparaBarraCargaTrabalho(HttpSession sessao,Date dataRoteiro){

		HashMap mapEquipeIdsOsProgramadas = new HashMap();
		
		HashMap mapOsProgramacaoHelper = (HashMap) sessao.getAttribute("mapOsProgramacaoHelper");

		Collection colecaoHelper = mapOsProgramacaoHelper.values();
		Iterator itera = colecaoHelper.iterator();
		
		while (itera.hasNext()) {
			
			Collection colecaoOSProgramacaoHelper = (Collection) itera.next();

			Iterator iteraOsProgramacao = colecaoOSProgramacaoHelper.iterator();
			
			while (iteraOsProgramacao.hasNext()) {

				OSProgramacaoHelper osProgramacaoHelper = (OSProgramacaoHelper) iteraOsProgramacao.next();
				
				OrdemServicoProgramacao osProgramacao = osProgramacaoHelper.getOrdemServicoProgramacao();
				String chaveNome = Util.removerSimbolosPontuacao(osProgramacao.getEquipe().getNome());
				
				if(!mapEquipeIdsOsProgramadas.containsKey(chaveNome)){

					Set colecaoIds = new HashSet();	
					colecaoIds.add(osProgramacao.getOrdemServico().getId());

					mapEquipeIdsOsProgramadas.put(chaveNome,colecaoIds);
				}else{

					Set colecaoIds = (HashSet) mapEquipeIdsOsProgramadas.get(chaveNome);
					colecaoIds.add(osProgramacao.getOrdemServico().getId());
					
					mapEquipeIdsOsProgramadas.put(chaveNome,colecaoIds);
				}
				
			}
		}
		
		itera = mapEquipeIdsOsProgramadas.keySet().iterator();
		
		HashMap mapEquipe = 
			(HashMap) sessao.getAttribute("mapEquipe");
		
		while (itera.hasNext()) {
			
			String key = (String) itera.next();
			
			Collection colecaoIdsOSProgramadas = (HashSet) mapEquipeIdsOsProgramadas.get(key);
			
			Equipe equipe = (Equipe) mapEquipe.get(key);			
			
			
			ObterCargaTrabalhoEquipeHelper obterCargaTrabalhoEquipeHelper = 
				Fachada.getInstancia().obterCargaTrabalhoEquipe(
						equipe.getId(),colecaoIdsOSProgramadas,null,dataRoteiro);

			BigDecimal percentualPrevista = obterCargaTrabalhoEquipeHelper.getPercentualCargaTrabalhoPrevista();
			BigDecimal percentualRealizada = obterCargaTrabalhoEquipeHelper.getPercentualCargaRealizada();
			
			String chaveSessao = key.replace("-","");
			chaveSessao = chaveSessao.replace(" ","");
						
			String keyPercentualPrevista = "percentualTrabalhoPrevista"+chaveSessao;
			sessao.setAttribute(keyPercentualPrevista,percentualPrevista);

			String keyPercentualRealizada = "percentualTrabalhoRealizada"+chaveSessao;
			sessao.setAttribute(keyPercentualRealizada,percentualRealizada);
		}
	}
	
//	/**
//	 * Pesquisar a Programacao Roteiro (como a tabela eh pequena usa o filtro) 
//	 * 
//	 * @author Rafael Pinto
//	 * @date 21/08/2006
//	 * 
//	 * @param data do roteiro,idUnidade
//	 */		
//	private ProgramacaoRoteiro consultarProgramacaoRoteiro(Date dataRoterio,Integer unidade){
//		
//		FiltroProgramacaoRoteiro filtroProgramacaoRoteiro = new FiltroProgramacaoRoteiro();
//		filtroProgramacaoRoteiro.adicionarParametro(
//			new ParametroSimples(FiltroProgramacaoRoteiro.DATA_ROTEIRO, dataRoterio));
//
//		filtroProgramacaoRoteiro.adicionarParametro(
//				new ParametroSimples(FiltroProgramacaoRoteiro.UNIDADE_ORGANIZACIONAL_ID, unidade));
//		
//		Collection colecaoProgramacaoRoteiro = 
//			Fachada.getInstancia().pesquisar(filtroProgramacaoRoteiro, ProgramacaoRoteiro.class.getName());
//
//		ProgramacaoRoteiro programacaoRoteiro = 
//			(ProgramacaoRoteiro) Util.retonarObjetoDeColecao(colecaoProgramacaoRoteiro);
//		
//		
//		return programacaoRoteiro;
//		
//	}
	
	/**
	 * Retorna a colecao de chave da equipe que tenha o mesmo logradouro
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao,id do Logradouro e chave da equipe
	 * @return colecao de chaves da equipe
	 */	
	private Collection pesquisaEquipePeloLogradouro(HttpSession sessao,int idLogradouro,String chave){
		Set retorno = new HashSet();
		
		HashMap mapOsProgramacaoHelper = (HashMap) sessao.getAttribute("mapOsProgramacaoHelper");
		
		if(mapOsProgramacaoHelper != null && !mapOsProgramacaoHelper.isEmpty()){
		
			Collection colecao = (Collection) mapOsProgramacaoHelper.get(chave);
			
			if(colecao != null && !colecao.isEmpty()){
				Iterator iter = colecao.iterator();
				while (iter.hasNext()) {
					OSProgramacaoHelper helper = (OSProgramacaoHelper) iter.next();
					
					OrdemServicoProgramacao ordemServicoProgramacao = helper.getOrdemServicoProgramacao();
					OrdemServico ordemServico = ordemServicoProgramacao.getOrdemServico();
					int idLogra = ConstantesSistema.NUMERO_NAO_INFORMADO;
	
					if(ordemServico.getRegistroAtendimento() != null){
						
						if(ordemServico.getRegistroAtendimento().getLogradouroBairro() != null){
							idLogra = ordemServico.getRegistroAtendimento().getLogradouroBairro().getId().intValue();	
						}
						
						String key = ordemServicoProgramacao.getEquipe().getNome();
						
						if(idLogra == idLogradouro && !key.equals(chave)){
							retorno.add(key);
						}
					}
				}
			}
		}
		
		return retorno;
	}
	
	/**
	 * Retorna o ultimo sequencial das os´s programadas
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param colecao de OsProgramacaoHelper
	 * @return ultimoSequencial
	 */	
	private int retornaUltimoSequencial(Collection colecaoOsProgramacaoHelper){
		short valorSequencial = 0;
		Iterator iter = colecaoOsProgramacaoHelper.iterator();
		while (iter.hasNext()) {
			OSProgramacaoHelper helper = (OSProgramacaoHelper) iter.next();
			
			if(valorSequencial < helper.getOrdemServicoProgramacao().getNnSequencialProgramacao()){
				valorSequencial = helper.getOrdemServicoProgramacao().getNnSequencialProgramacao();
			}
		}
		
		return valorSequencial;
	}		
	
}
