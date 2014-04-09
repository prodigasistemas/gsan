package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroArquivoTextoAcompanhamentoServico;
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.bean.AcompanhamentoArquivosRoteiroHelper;
import gcom.atendimentopublico.ordemservico.bean.OSProgramacaoAcompanhamentoServicoHelper;
import gcom.atendimentopublico.ordemservico.bean.ObterDadosEquipe;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAcompanhamentoArquivosRoteiroIncluirOrdemServicoAction extends GcomAction {
	
	Fachada fachada = Fachada.getInstancia();
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
		HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirIncluirOrdemServicoAcompanhamentoArqRoteiro");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoAcompanhamentoArquivosRoteiro = 
				(Collection) sessao.getAttribute("colecaoAcompanhamentoArquivosRoteiro");
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");		
		
		// Form
		AcompanharRoteiroProgramacaoOrdemServicoActionForm 
			acompanharActionForm = (AcompanharRoteiroProgramacaoOrdemServicoActionForm) actionForm;
		
		String chave = httpServletRequest.getParameter("chave");
		ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico =  null;
		
		if (chave != null){
			FiltroArquivoTextoAcompanhamentoServico filtroArquivoTextoAcompanhamentoServico = new FiltroArquivoTextoAcompanhamentoServico();
			filtroArquivoTextoAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroArquivoTextoAcompanhamentoServico.ID, chave));
			
			
			Collection<?> colecaoArquivoTxtAcompanhamentoServico = fachada.pesquisar(
					filtroArquivoTextoAcompanhamentoServico,
				    ArquivoTextoAcompanhamentoServico.class.getName());
			
			arquivoTextoAcompanhamentoServico = (ArquivoTextoAcompanhamentoServico) colecaoArquivoTxtAcompanhamentoServico
				    .iterator().next();
			
			if (arquivoTextoAcompanhamentoServico.getSituacaoTransmissaoLeitura().getId().equals(SituacaoTransmissaoLeitura.TRANSMITIDO)){
				throw new ActionServletException("atencao.nao_possivel.incluir_situacao_finalizado");
			}
			
		}
		if (httpServletRequest.getParameter("tipoConsulta") != null && 
				!httpServletRequest.getParameter("tipoConsulta").equals("")) {
					
			String id = httpServletRequest.getParameter("idCampoEnviarDados");
			String descricao = httpServletRequest.getParameter("descricaoCampoEnviarDados");
				
			if (httpServletRequest.getParameter("tipoConsulta").equals("registroAtendimento")) {
				acompanharActionForm.setNumeroRA(id);
				acompanharActionForm.setDescricaoRA(descricao);
			}else if (httpServletRequest.getParameter("tipoConsulta").equals("ordemServico")) {
				
				Integer unidadeLotacao = new Integer(acompanharActionForm.getUnidadeLotacao());

				acompanharActionForm.setIdOrdemServico(id);
				acompanharActionForm.setDescricaoOrdemServico(descricao);
				
				// [FS0008] - Verificar possibilidade da inclusão da ordem de serviço
				this.pesquisarOrdemServico(acompanharActionForm,unidadeLotacao);
				
			}

			int valor = this.retornaUltimoSequencial(acompanharActionForm.getIdEquipe(),colecaoAcompanhamentoArquivosRoteiro)+1;
			
			acompanharActionForm.setSequencialProgramacao(""+valor);
				
		} else {
			// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
			String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
			if(objetoConsulta == null){

				objetoConsulta = (String) httpServletRequest.getAttribute("objetoConsulta");
				
				if(objetoConsulta != null){
					acompanharActionForm.setIdOrdemServico((String)httpServletRequest.getAttribute("idOrdemServico"));
					
					int valor = this.retornaUltimoSequencial(acompanharActionForm.getIdEquipe(),colecaoAcompanhamentoArquivosRoteiro)+1;
					
					acompanharActionForm.setSequencialProgramacao(""+valor);
				}
			}

			Integer numeroRA = (Integer) httpServletRequest.getAttribute("numeroRa");
			String osDescricao = (String) httpServletRequest.getAttribute("descOrdemServico");
			
			//[UC0443] - Pesquisar Registro Atendimento
			if ((objetoConsulta != null && !objetoConsulta.trim().equals("") && 
				objetoConsulta.trim().equals("1")) || (numeroRA != null && !numeroRA.equals(""))) {
				
				if (numeroRA != null && !numeroRA.equals("")){
					acompanharActionForm.setNumeroRA(Integer.toString(numeroRA));
				}
				
				if (osDescricao != null && !osDescricao.equals("")){
					acompanharActionForm.setDescricaoOrdemServico(osDescricao);
				}
				
				this.pesquisarRegistroAtendimento(acompanharActionForm);				
			}
			
			if (objetoConsulta != null && !objetoConsulta.trim().equals("") && 
					objetoConsulta.trim().equals("2")) {
				
				Integer idUnidadeLotacao = usuario.getUnidadeOrganizacional().getId();

				this.pesquisarOrdemServico(acompanharActionForm,idUnidadeLotacao);
				
			}else if(chave != null){
		
				FiltroEquipe filtroEquipe =  new FiltroEquipe();
				filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID, arquivoTextoAcompanhamentoServico.getEquipe().getId()));		
				
				Collection<?> colecaoEquipe = fachada.pesquisar(filtroEquipe, Equipe.class.getName());
				Equipe equipe = (Equipe) colecaoEquipe.iterator().next();
				
				//[UC0471]- Obter Dados da Equipe
				ObterDadosEquipe obterDadosEquipe = fachada.obterDadosEquipe(equipe.getId());
				
				acompanharActionForm.setIdEquipe(""+obterDadosEquipe.getEquipe().getId());
				acompanharActionForm.setNomeEquipe(obterDadosEquipe.getEquipe().getNome());
				acompanharActionForm.setPlacaVeiculo(obterDadosEquipe.getEquipe().getPlacaVeiculo());
				
				acompanharActionForm.setCargaTrabalhoDia(""+ (obterDadosEquipe.getEquipe().getCargaTrabalho()));
				acompanharActionForm.setIdUnidade(""+obterDadosEquipe.getEquipe().getUnidadeOrganizacional().getId());
				acompanharActionForm.setDescricaoUnidade(obterDadosEquipe.getEquipe().getUnidadeOrganizacional().getDescricao());
				acompanharActionForm.setIdTipoPerfilServico(""+obterDadosEquipe.getEquipe().getServicoPerfilTipo().getId());
				acompanharActionForm.setDescricaoTipoPerfilServico(obterDadosEquipe.getEquipe().getServicoPerfilTipo().getDescricao());
				acompanharActionForm.setEquipeComponentes(obterDadosEquipe.getColecaoEquipeComponentes());
				acompanharActionForm.setIdOrdemServico(null);
				acompanharActionForm.setIdAcompanhamentoArquivosRoteiro(chave);
				
				int valor = this.retornaUltimoSequencial(Integer.toString(obterDadosEquipe.getEquipe().getId()),colecaoAcompanhamentoArquivosRoteiro)+1;
				
				acompanharActionForm.setSequencialProgramacao(""+valor);
				acompanharActionForm.setSequencialProgramacaoPrevisto(""+valor);
			}
		}

		this.setaRequest(httpServletRequest,acompanharActionForm);
		
		return retorno;
	}
	
	private void pesquisarRegistroAtendimento(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm) {
		
		ObterDadosRegistroAtendimentoHelper obter = 
			fachada.obterDadosRegistroAtendimento(new Integer(
					acompanharActionForm.getNumeroRA()));

		if (obter.getRegistroAtendimento()  != null) {
			RegistroAtendimento registroAtendimento = obter.getRegistroAtendimento();
			
			if (registroAtendimento.getCodigoSituacao() == 2){
				throw new ActionServletException("atencao.situacao.ra.pendente");				
			}
			
			acompanharActionForm.setNumeroRA(registroAtendimento.getId().toString());
			acompanharActionForm.setDescricaoRA(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
			
		} else {
			acompanharActionForm.setDescricaoRA("Registro Atendimento inexistente");
			acompanharActionForm.setNumeroRA("");
		}
	}
	
	private void pesquisarOrdemServico(AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm,
		Integer idUnidadeLotacao) {
		
		OrdemServico os = 
			fachada.recuperaOSPorId(new Integer(acompanharActionForm.getIdOrdemServico()));

		if (os != null) {

			// [FS0015] - Verificar registro de atendimento e ordem de serviço
			String numeroRa = acompanharActionForm.getNumeroRA();
			if(numeroRa != null && !numeroRa.equals("")){
				
				if(os.getRegistroAtendimento() == null || (os.getRegistroAtendimento().getId().intValue() != new Integer(numeroRa).intValue())){
					String[] parametros = new String[2];
					parametros[0] = os.getId().toString();
					parametros[1] = numeroRa;
					throw new ActionServletException("atencao.registro_nao_pertence_ordem_servico",null,parametros);					
				}
			}
			
			if(os.getRegistroAtendimento() == null){
				String[] parametros = new String[2];
				parametros[0] = os.getId().toString();
				parametros[1] = numeroRa;
				throw new ActionServletException("atencao.registro_nao_pertence_ordem_servico",null,parametros);					
			}
			
			// [FS0008] - Verificar possibilidade da inclusão da ordem de serviço
			fachada.validarInclusaoOsNaProgramacao(os,idUnidadeLotacao);
			
			acompanharActionForm.setIdOrdemServico(os.getId().toString());
			acompanharActionForm.setDescricaoOrdemServico(os.getServicoTipo().getDescricao());

		} else {
			acompanharActionForm.setDescricaoOrdemServico("Ordem de Serviço inexistente");
			acompanharActionForm.setIdOrdemServico("");
		}
	}

	private void setaRequest(HttpServletRequest httpServletRequest,
			AcompanharRoteiroProgramacaoOrdemServicoActionForm acompanharActionForm){
		
		// Registro Atendimento
		if(acompanharActionForm.getNumeroRA() != null && 
			!acompanharActionForm.getNumeroRA().equals("") && 
			acompanharActionForm.getDescricaoRA() != null && 
			!acompanharActionForm.getDescricaoRA().equals("")){
					
			httpServletRequest.setAttribute("numeroRAEncontrada","true");
		}

		// Ordem de Serviço
		if(acompanharActionForm.getIdOrdemServico() != null && 
			!acompanharActionForm.getIdOrdemServico().equals("") && 
			acompanharActionForm.getDescricaoOrdemServico() != null && 
			!acompanharActionForm.getDescricaoOrdemServico().equals("")){
					
			httpServletRequest.setAttribute("idOsEncontrada","true");
		}
	}
	
	private int retornaUltimoSequencial(String idEquipe, Collection colecaoAcompanhamentoArquivosRoteiro){
		short valorSequencial = 0;
		if (colecaoAcompanhamentoArquivosRoteiro != null && !colecaoAcompanhamentoArquivosRoteiro.isEmpty()){	
			Iterator itDadosAcompanhamentoArquivosRoteiroHelper = colecaoAcompanhamentoArquivosRoteiro.iterator();
			while (itDadosAcompanhamentoArquivosRoteiroHelper.hasNext()){
				AcompanhamentoArquivosRoteiroHelper acompanhamentoArquivosRoteiroHelper = (AcompanhamentoArquivosRoteiroHelper) itDadosAcompanhamentoArquivosRoteiroHelper.next();
				if (acompanhamentoArquivosRoteiroHelper.getIdEquipe().equals(idEquipe)){
					Collection osProgramacaoAcompServicoHelper = acompanhamentoArquivosRoteiroHelper.getOsProgramacaoAcompServicoHelper();
					
					Iterator iter = osProgramacaoAcompServicoHelper.iterator(); 
							
					while (iter.hasNext()) {
						OSProgramacaoAcompanhamentoServicoHelper helper = (OSProgramacaoAcompanhamentoServicoHelper) iter.next();
						
						if(valorSequencial < Short.parseShort(helper.getNnSequencialProgramacao())){
							valorSequencial = Short.parseShort(helper.getNnSequencialProgramacao());
						}
					}
				}
			}
		}
		return valorSequencial;
	}
}
