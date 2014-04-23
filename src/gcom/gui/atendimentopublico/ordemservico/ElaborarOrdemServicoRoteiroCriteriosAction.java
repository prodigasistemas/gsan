package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoProgramacao;
import gcom.atendimentopublico.ordemservico.bean.OSFiltroHelper;
import gcom.atendimentopublico.ordemservico.bean.OSProgramacaoHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterCargaTrabalhoEquipeHelper;
import gcom.atendimentopublico.ordemservico.bean.PesquisarOrdemServicoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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

public class ElaborarOrdemServicoRoteiroCriteriosAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = 
			actionMapping.findForward("elaborarOrdemServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Form
		ElaborarOrdemServicoRoteiroCriteriosActionForm 
			elaborarActionForm = 
				(ElaborarOrdemServicoRoteiroCriteriosActionForm) actionForm;
		
		// Data do Roteiro
		Date dataRoteiro = Util.converteStringParaDate(elaborarActionForm.getDataRoteiro());		

		// Precisa pegar a unidade do usuario do login que esta na sessao
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();


		// Montar o objeto que será usado na pesquisa
		PesquisarOrdemServicoHelper pesquisarOrdemServicoHelper = 
			this.montaPesquisarOrdemServicoHelper(elaborarActionForm);

		// Pesquisa a partir do filtro
		Collection<OrdemServico> colecaoOrdemServico = 
			Fachada.getInstancia().pesquisarOrdemServicoElaborarProgramacao(pesquisarOrdemServicoHelper);

		if (colecaoOrdemServico == null || colecaoOrdemServico.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhum_resultado.ordem_servico");
		}
		
		// Pesquisa as equipes associadas a unidade
		this.pesquisarEquipe(sessao,idUnidadeLotacao);
		
		// Monta a colecao de OSFiltroHelper
		Collection colecaoOSFiltroHelper = this.montaColecaoOSFiltroHelper(colecaoOrdemServico,dataRoteiro);
		sessao.setAttribute("colecaoOSFiltroHelper", colecaoOSFiltroHelper);
		
		elaborarActionForm.setSelecionadas(colecaoOSFiltroHelper.size());
		
		// Monta o mapOsProgramacaoHelper com as ordens de serviço de programação
		this.montaOrdemServicoProgramacao(sessao,dataRoteiro,idUnidadeLotacao);
		
		// [SB0010]-Prepara Barra de Carga de Trabalho 
		this.preparaBarraCargaTrabalho(sessao,dataRoteiro);
		
		return retorno;
	}
	
	/**
	 * Monta o Filtro de será usado na consulta
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 *
	 * @param ElaborarOrdemServicoRoteiroCriteriosActionForm 
	 * @return PesquisarOrdemServicoHelper
	 */
	private PesquisarOrdemServicoHelper montaPesquisarOrdemServicoHelper(
		ElaborarOrdemServicoRoteiroCriteriosActionForm elaborarActionForm){
		
		// Unidade Lotacao
		Integer idUnidadeLotacao = new Integer(elaborarActionForm.getUnidadeLotacao());
		
		// Origem Servico
		Integer origemServico = new Integer(elaborarActionForm.getOrigemServicos());

		// Criterio Seleção
		Integer criterio = new Integer(elaborarActionForm.getCriterioSelecao());
		
		// Serviço Diagnosticado
		short servicoDiagnosticado = new Short(elaborarActionForm.getServicoDiagnosticado());
		
		// Serviço Acompanhamento
		short servicoAcompanhamento = new Short(elaborarActionForm.getServicoAcompanhamento());

		//Tipo Servico
		Integer[] idsTipoServicoSelecionado = (Integer[]) elaborarActionForm.getTipoServicoSelecionados();
		
		if(idsTipoServicoSelecionado.length > 0){
			if(idsTipoServicoSelecionado[0].intValue() == ConstantesSistema.NUMERO_NAO_INFORMADO){
				idsTipoServicoSelecionado = null;
			}
		}
		// Data de Atraso
		Date dataAtrasoInicial = null;
		Date dataAtrasoFinal = null;
		
		String diaAtrasoInicial = elaborarActionForm.getDiaAtrasoInicial(); 
		String diaAtrasoFinal = elaborarActionForm.getDiaAtrasoFinal();
		
		//selecionou os dois dias
		if ((diaAtrasoFinal != null && !diaAtrasoFinal.equals("")) && 
			 (diaAtrasoInicial != null && !diaAtrasoInicial.equals("")) ) {
			
			Calendar calendar = Calendar.getInstance();
			
			int dia = new Integer(diaAtrasoInicial).intValue();

			calendar.add(Calendar.DAY_OF_MONTH, -dia);
			dataAtrasoInicial = calendar.getTime();
			
			dia = new Integer(diaAtrasoFinal).intValue();
			
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -dia);
			dataAtrasoFinal = calendar.getTime();

		//selecionou somente um dos dias
		}else if ((diaAtrasoFinal != null && !diaAtrasoFinal.equals("")) || 
			  (diaAtrasoInicial != null && !diaAtrasoInicial.equals("")) ) {

			Calendar calendar = Calendar.getInstance();
			
			int dia = ConstantesSistema.NUMERO_NAO_INFORMADO;
			if(diaAtrasoInicial != null && !diaAtrasoInicial.equals("")){
				dia = new Integer(diaAtrasoInicial).intValue();	
			}else {
				dia = new Integer(diaAtrasoFinal).intValue();
			}
			
			calendar.add(Calendar.DAY_OF_MONTH, -dia);
			dataAtrasoInicial = calendar.getTime();

		}
		
		// Data de Atendimento
		Date dataAtendimentoInicial = null;
		Date dataAtendimentoFinal = null;
		
		if (elaborarActionForm.getPeriodoAtendimentoInicial() != null &&
			!elaborarActionForm.getPeriodoAtendimentoInicial().equals("")) {
			
			dataAtendimentoInicial = 
				Util.converteStringParaDate(elaborarActionForm.getPeriodoAtendimentoInicial());
			
			dataAtendimentoFinal = null;
			if (elaborarActionForm.getPeriodoAtendimentoFinal() != null &&
				!elaborarActionForm.getPeriodoAtendimentoFinal().equals("")) {
				
				dataAtendimentoFinal = 
					Util.converteStringParaDate(elaborarActionForm.getPeriodoAtendimentoFinal());
			} else {
				dataAtendimentoFinal = new Date();
			}

		}

		// Data de Geração
		Date dataGeracaoInicial = null;
		Date dataGeracaoFinal = null;
		
		if (elaborarActionForm.getPeriodoGeracaoInicial() != null &&
			!elaborarActionForm.getPeriodoGeracaoInicial().equals("")){
			
			dataGeracaoInicial = 
				Util.converteStringParaDate(elaborarActionForm.getPeriodoGeracaoInicial());
			
			dataGeracaoFinal = null;
			
			if (elaborarActionForm.getPeriodoGeracaoFinal() != null &&
				!elaborarActionForm.getPeriodoGeracaoFinal().equals("") ) {
				
				dataGeracaoFinal = 
					Util.converteStringParaDate(elaborarActionForm.getPeriodoGeracaoFinal());
				
			} else {
				dataGeracaoFinal = new Date();
			}

		}

		// Data de Previsão para cliente
		Date dataClienteInicial = null;
		Date dataClienteFinal = null;
		
		if (elaborarActionForm.getPeriodoClienteInicial() != null &&
			!elaborarActionForm.getPeriodoClienteInicial().equals("")){
			
			dataClienteInicial = 
				Util.converteStringParaDate(elaborarActionForm.getPeriodoClienteInicial());
			
			dataClienteFinal = null;
			
			if (elaborarActionForm.getPeriodoClienteFinal() != null &&
				!elaborarActionForm.getPeriodoClienteFinal().equals("") ) {
				
				dataClienteFinal = 
					Util.converteStringParaDate(elaborarActionForm.getPeriodoClienteFinal());
				
			} else {
				dataClienteFinal = new Date();
			}

		}
		
		// Data de Previsão para Agência Reguladora
		Date dataAgenciaInicial = null;
		Date dataAgenciaFinal = null;
		
		if (elaborarActionForm.getPeriodoAgenciaInicial() != null &&
			!elaborarActionForm.getPeriodoAgenciaInicial().equals("")){
			
			dataAgenciaInicial = 
				Util.converteStringParaDate(elaborarActionForm.getPeriodoAgenciaInicial());
		
			dataAgenciaFinal = null;
			
			if (elaborarActionForm.getPeriodoAgenciaFinal() != null &&
				!elaborarActionForm.getPeriodoAgenciaFinal().equals("") ) {
				
				dataAgenciaFinal = 
					Util.converteStringParaDate(elaborarActionForm.getPeriodoAgenciaFinal());
				
			} else {
				dataAgenciaFinal = new Date();
			}

		}
		
		// Monta o filtro a partir das seleções feitas na tela
		PesquisarOrdemServicoHelper pesquisarOrdemServicoHelper = 
			new PesquisarOrdemServicoHelper();

		pesquisarOrdemServicoHelper.setOrigemServico(origemServico);
		pesquisarOrdemServicoHelper.setCriterioSelecao(criterio);
		pesquisarOrdemServicoHelper.setServicoDiagnosticado(servicoDiagnosticado);
		pesquisarOrdemServicoHelper.setServicoAcompanhamento(servicoAcompanhamento);
		pesquisarOrdemServicoHelper.setTipoServicos(idsTipoServicoSelecionado);
		
		pesquisarOrdemServicoHelper.setDataAtrasoInicial(dataAtrasoInicial);
		pesquisarOrdemServicoHelper.setDataAtrasoFinal(dataAtrasoFinal);
		pesquisarOrdemServicoHelper.setDataAtendimentoInicial(dataAtendimentoInicial);
		pesquisarOrdemServicoHelper.setDataAtendimentoFinal(dataAtendimentoFinal);
		pesquisarOrdemServicoHelper.setDataGeracaoInicial(dataGeracaoInicial);
		pesquisarOrdemServicoHelper.setDataGeracaoFinal(dataGeracaoFinal);

		pesquisarOrdemServicoHelper.setDataPrevisaoClienteInicial(dataClienteInicial);
		pesquisarOrdemServicoHelper.setDataPrevisaoClienteFinal(dataClienteFinal);
		pesquisarOrdemServicoHelper.setDataPrevisaoAgenciaInicial(dataAgenciaInicial);
		pesquisarOrdemServicoHelper.setDataPrevisaoAgenciaFinal(dataAgenciaFinal);
		pesquisarOrdemServicoHelper.setUnidadeLotacao(idUnidadeLotacao);
		
		
		return pesquisarOrdemServicoHelper;
	}
	
	
	/**
	 * Pesquisa todas as equipes que estão ligadas a unidade organizacional do usuario
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao,id da Unidade Organizacional 
	 * @return Collection de Equipe
	 */
	private void pesquisarEquipe(HttpSession sessao,Integer idUnidadeLotacao){

		// Filtro para Equipe
		Collection colecaoEquipes = (Collection) 
			sessao.getAttribute("colecaoEquipes");

		if(colecaoEquipes == null){

			FiltroEquipe filtroEquipe = new FiltroEquipe();

			filtroEquipe.adicionarParametro(new ParametroSimples(
				FiltroEquipe.ID_UNIDADE_ORGANIZACIONAL,idUnidadeLotacao));

			filtroEquipe.adicionarParametro(new ParametroSimples(
					FiltroEquipe.INDICADOR_USO,ConstantesSistema.SIM));

			filtroEquipe.setCampoOrderBy(FiltroEquipe.NOME);

			colecaoEquipes = Fachada.getInstancia().pesquisar(
					filtroEquipe, Equipe.class.getName());

			if (colecaoEquipes != null && !colecaoEquipes.isEmpty()) {
				sessao.setAttribute("colecaoEquipes",colecaoEquipes);
			} else {
				throw new ActionServletException("atencao.naocadastrado",null, "Equipe");
			}
		}	
		
		
	}
	
	/**
	 * Monta a colecao de OSFiltroHelper a partir da colecao de Ordem de Servico
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param colecao de ordem servico e data de roteiro
	 * @return Collection de OSFiltroHelper
	 */	
	private Collection montaColecaoOSFiltroHelper(Collection<OrdemServico> colecaoOrdemServico,Date dataRoteiro) {
		
		Collection colecaoOSFiltroHelper = new ArrayList();
		OSFiltroHelper helper = null;
		
		for (Iterator iter = colecaoOrdemServico.iterator(); iter.hasNext();) {
			
			OrdemServico ordemServico = (OrdemServico) iter.next();

			int qtdDiasCliente = ConstantesSistema.NUMERO_NAO_INFORMADO;
			int qtdDiasAgencia = ConstantesSistema.NUMERO_NAO_INFORMADO;
			
			
			if(ordemServico.getRegistroAtendimento() != null) {
				
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
			}
			
			helper = new OSFiltroHelper();
			helper.setOrdemServico(ordemServico);
			helper.setDataPrevisaoAtual(Util.formatarData(dataRoteiro));

			if(ordemServico.getSituacao() == OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO.shortValue()){
				helper.setSituacao(""+ordemServico.getSituacao());	
			}
			
			if(qtdDiasCliente > 0){
				helper.setDiasAtrasoCliente(qtdDiasCliente);
			}

			if(qtdDiasAgencia > 0){
				helper.setDiasAtrasoAgencia(qtdDiasAgencia);
			}

			String endereco = Fachada.getInstancia().obterEnderecoAbreviadoOS(ordemServico.getId());
			helper.setEndereco(endereco);
			
			colecaoOSFiltroHelper.add(helper);
		}
		
		return colecaoOSFiltroHelper;
	}
	
	/**
	 * Monta um HashMap(nomeEquipe,Colecao de OSProgramacaoHelper) a partir 
	 * da colecao de Ordem de Servico Programcao associado a data de roteiro
	 * 
	 * @author Rafael Pinto
	 * @date 17/08/2006
	 * 
	 * @param sessao,data do roteiro,ElaborarOrdemServicoRoteiroCriteriosActionForm
	 */
	private void montaOrdemServicoProgramacao(HttpSession sessao,Date dataRoteiro,Integer idUnidadeLotacao){
		
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
				String chave = equipe.getNome();
				
				OSProgramacaoHelper helper = new OSProgramacaoHelper();
				
				int qtdDiasCliente = ConstantesSistema.NUMERO_NAO_INFORMADO;
				int qtdDiasAgencia = ConstantesSistema.NUMERO_NAO_INFORMADO;
				
				
				if(ordemServico.getRegistroAtendimento() != null) {
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
				}
				
				helper.setPodeRemover(false);

				if(ordemServico.getSituacao() == OrdemServico.SITUACAO_EXECUCAO_EM_ANDAMENTO.shortValue()){
					helper.setSituacao(""+ordemServico.getSituacao());	
				}
				
				if(qtdDiasCliente > 0){
					helper.setDiasAtrasoCliente(qtdDiasCliente);
				}

				if(qtdDiasAgencia > 0){
					helper.setDiasAtrasoAgencia(qtdDiasAgencia);
				}
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
				String chaveNome = osProgramacao.getEquipe().getNome();
				
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
			
			String chaveSessao = key.replace(" ","");
			
			String keyPercentualPrevista = "percentualTrabalhoPrevista"+chaveSessao;
			sessao.setAttribute(keyPercentualPrevista,percentualPrevista);

			String keyPercentualRealizada = "percentualTrabalhoRealizada"+chaveSessao;
			sessao.setAttribute(keyPercentualRealizada,percentualRealizada);
		}
	}
	
	
}
