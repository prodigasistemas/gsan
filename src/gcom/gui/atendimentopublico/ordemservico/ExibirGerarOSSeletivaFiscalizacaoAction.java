package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroOrdemServico;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaGrupo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC ] Gerar Os Seletiva de Fiscalização
 * @author Vivianne Sousa
 * @date 20/05/2011
 */
public class ExibirGerarOSSeletivaFiscalizacaoAction extends
		GcomAction {
  
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarOSSeletivaFiscalizacao");	

		Fachada fachada = Fachada.getInstancia();
		
		GerarOSSeletivaFiscalizacaoActionForm form = (GerarOSSeletivaFiscalizacaoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
//		if(sistemaParametro.getQtdeDiasEncerraOSFiscalizacao()== null){
//			
//		}
		
		if(httpServletRequest.getParameter("menu") != null){
			limparForm(form,sistemaParametro,httpServletRequest);
		}
		
		// Grupo de Cobrança
        pesquisarGrupoCobranca(fachada, sessao);
		
		// Gerência Regional
		pesquisarGerenciaRegional(fachada, sessao);
		
		// Unidade de Negócio
		pesquisarUnidadeNegocio(fachada, sessao, form);
		
		//Localidade Inicial
		String idLocalidadeInicio = pesquisarLocalidadeInicial(httpServletRequest, fachada, form);

		// Localidade Final
		pesquisarLocalidadeFinal(httpServletRequest, fachada, form, idLocalidadeInicio);
		
		// Serviço Tipo
		pesquisarServicoTipo(fachada, form);
		
		pesquisarordemServico(fachada, form, sistemaParametro,httpServletRequest);
		
		if(httpServletRequest.getParameter("pesquisar") != null){
			
			form.setQtdeOSEncerradasComConclusao("");
			form.setQtdeOSEncerradasSemConclusao("");
//			form.setPercentualOSgeradas("2,00");
			httpServletRequest.setAttribute("habilitaBtGerar",2);
			
			
			if(form.getQtdeDiasEncerramentoOS() == null ||
					form.getQtdeDiasEncerramentoOS().equals("")){
				throw new ActionServletException("atencao.campo.informado", null, "Quantidade de Dias de Encerramento da OS");
			}
			
			
			Integer qtdeDiasEncerramentoOS = new Integer(form.getQtdeDiasEncerramentoOS());
			if(qtdeDiasEncerramentoOS.equals(new Integer("0")) || 
			qtdeDiasEncerramentoOS.compareTo(sistemaParametro.getQtdeDiasEncerraOSFiscalizacao()) == 1){
				throw new ActionServletException("atencao.quantidade_dias_encerramento_os_maior_zero", 
						null, sistemaParametro.getQtdeDiasEncerraOSFiscalizacao().toString());
			}
			
			//botão pesquisar
			Integer idOrdemServico = null;
			Integer idGrupoCobranca = null;
			Integer idGerencia = null;
			Integer idUnidade = null; 
			Integer idLocalidadeInicial = null;
			Integer idLocalidadeFinal = null; 
			Integer idTipoServico = null; 
			
			if(form.getIdOrdemServico() != null && !form.getIdOrdemServico().equals("")){
				
				idOrdemServico = new Integer(form.getIdOrdemServico());
				//[SB0002] – Verificar Ordem Serviço  
				Integer idMotivoMotivoEncerramentoOS = fachada.pesquisarIdMotivoEncerramentoOS(idOrdemServico);
				 
				if(idMotivoMotivoEncerramentoOS.equals(AtendimentoMotivoEncerramento.CONCLUSAO_SERVICO)){
					form.setQtdeOSEncerradasComConclusao("1");
					form.setQtdeOSEncerradasSemConclusao("0");
				}else{
					form.setQtdeOSEncerradasComConclusao("0");
					form.setQtdeOSEncerradasSemConclusao("1");
				}
				
				httpServletRequest.setAttribute("habilitaBtGerar",1);
				
			}else{
				
				if(form.getGrupoCobranca() != null && !form.getGrupoCobranca().equals("")
						&& !form.getGrupoCobranca().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
					idGrupoCobranca = new Integer(form.getGrupoCobranca());
				}else{
					
					throw new ActionServletException("atencao.campo.informado", null, "Ordem de Serviço ou Grupo de Cobrança");
					
				}
				if(form.getGerenciaRegional() != null && !form.getGerenciaRegional().equals("")
						&& !form.getGerenciaRegional().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
					idGerencia = new Integer(form.getGerenciaRegional());
				}
				if(form.getUnidadeNegocio() != null && !form.getUnidadeNegocio().equals("")
					&& !form.getUnidadeNegocio().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
					idUnidade = new Integer(form.getUnidadeNegocio());
				}
				if(form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().equals("")){
					idLocalidadeInicial = new Integer(form.getIdLocalidadeInicial());
					
					if(form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().equals("")){
						idLocalidadeFinal = new Integer(form.getIdLocalidadeFinal());
					}else{
						idLocalidadeFinal = idLocalidadeInicial;
					}
				}
				if(form.getIdServicoTipo() != null && !form.getIdServicoTipo().equals("")){
					idTipoServico = new Integer(form.getIdServicoTipo());
				}
				if(form.getQtdeDiasEncerramentoOS() != null && !form.getQtdeDiasEncerramentoOS().equals("")){
					qtdeDiasEncerramentoOS = new Integer(form.getQtdeDiasEncerramentoOS());
				}
				
				Map qtdeOs = fachada.recuperaQtdeOSEncerrada(idGrupoCobranca,idGerencia, 
						idUnidade,idLocalidadeInicial,idLocalidadeFinal,idTipoServico,qtdeDiasEncerramentoOS);
				
				Integer qtdeOSConclusaoServico = (Integer)qtdeOs.get("qtdeOSConclusaoServico");
				Integer qtdeOSSemConclusaoServico = (Integer)qtdeOs.get("qtdeOSSemConclusaoServico");
				
				if(qtdeOSConclusaoServico.equals(new Integer("0")) &&
				   qtdeOSSemConclusaoServico.equals(new Integer("0"))){
					//Caso a seleção para o filtro informado não tenha retornado dados, o sistema exibe a msg
				    //'Pesquisa não encontrou ordens de serviço para fiscalização que atendam ao filtro informado’ 
					throw new ActionServletException("atencao.pesquisa_nao_encontrou_os");
				}
				
				form.setQtdeOSEncerradasComConclusao(qtdeOSConclusaoServico.toString());
				form.setQtdeOSEncerradasSemConclusao(qtdeOSSemConclusaoServico.toString());
				httpServletRequest.setAttribute("habilitaBtGerar",1);
			}
			
		}
		
        return retorno;
	}

	private void limparForm(GerarOSSeletivaFiscalizacaoActionForm form,
			SistemaParametro sistemaParametro,HttpServletRequest httpServletRequest) {
		form.setIdLocalidadeInicial("");
		form.setDescricaoLocalidadeInicial("");
		form.setIdLocalidadeFinal("");
		form.setDescricaoLocalidadeFinal("");
		form.setIdServicoTipo("");
		form.setDescricaoServicoTipo("");
		form.setGrupoCobranca("");
		form.setGerenciaRegional("");
		form.setUnidadeNegocio("");
		form.setQtdeDiasEncerramentoOS(sistemaParametro.getQtdeDiasEncerraOSFiscalizacao().toString());
		form.setQtdeOSEncerradasComConclusao("");
		form.setQtdeOSEncerradasSemConclusao("");
		form.setPercentualOSgeradas("2");
		form.setIdOrdemServico("");
		form.setNomeOrdemServico("");
		httpServletRequest.setAttribute("habilitaBtGerar",2);
	}

	private void pesquisarServicoTipo(Fachada fachada, GerarOSSeletivaFiscalizacaoActionForm form) {
		if (form.getIdServicoTipo() != null && !form.getIdServicoTipo().equals("")) {

			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.ID, new Integer(form.getIdServicoTipo())));
			
			Collection colecaoServicoTipo = fachada.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());

			if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {
				ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
				form.setDescricaoServicoTipo(servicoTipo.getDescricao());
			} else {
				form.setIdServicoTipo("");
				form.setDescricaoServicoTipo("TIPO DE SERVIÇO INEXISTENTE");
			}
		}
	}

	private String pesquisarLocalidadeInicial(HttpServletRequest httpServletRequest, Fachada fachada, GerarOSSeletivaFiscalizacaoActionForm form) {
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		Localidade localidadeInicial = null;

		if (idLocalidadeInicial != null && !idLocalidadeInicial.trim().equals("")) {
			FiltroLocalidade filtroLocalidadeInicial = new FiltroLocalidade();
			filtroLocalidadeInicial.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeInicial));

			Collection colecaoLocalidadeInicial = fachada.pesquisar(
					filtroLocalidadeInicial, Localidade.class.getName());

			if (colecaoLocalidadeInicial != null && !colecaoLocalidadeInicial.isEmpty()) {
				localidadeInicial = (Localidade) colecaoLocalidadeInicial.iterator().next();
				form.setDescricaoLocalidadeInicial(localidadeInicial.getDescricao());
			} else {
				httpServletRequest.setAttribute("localidadeInicialInexistente","true");
				form.setIdLocalidadeInicial("");
				form.setDescricaoLocalidadeInicial("LOCALIDADE INEXISTENTE");
			}
		} else {
			form.setDescricaoLocalidadeInicial("");
		}
		return idLocalidadeInicial;
	}

	private void pesquisarLocalidadeFinal(HttpServletRequest httpServletRequest, Fachada fachada, GerarOSSeletivaFiscalizacaoActionForm form, String idLocalidadeInicial) {
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		Localidade localidadeFinal = null;

		if (idLocalidadeFinal != null && !idLocalidadeFinal.trim().equals("")) {
			if (idLocalidadeInicial != null && !idLocalidadeInicial.trim().equals("")) {
				if ((new Integer(idLocalidadeInicial)).compareTo(new Integer(idLocalidadeFinal)) > 0) {
					throw new ActionServletException("atencao.localidade.final.maior.localidade.inicial");
				}
			}

			FiltroLocalidade filtroLocalidadeFinal = new FiltroLocalidade();
			filtroLocalidadeFinal.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, idLocalidadeFinal));

			Collection colecaoLocalidadeFinal = fachada.pesquisar(
					filtroLocalidadeFinal, Localidade.class.getName());

			if (colecaoLocalidadeFinal != null && !colecaoLocalidadeFinal.isEmpty()) {
				localidadeFinal = (Localidade) colecaoLocalidadeFinal.iterator().next();
				form.setDescricaoLocalidadeFinal(localidadeFinal.getDescricao());
			} else {
				httpServletRequest.setAttribute("localidadeFinalInexistente","true");
				form.setIdLocalidadeFinal("");
				form.setDescricaoLocalidadeFinal("LOCALIDADE INEXISTENTE");
			}
		} else {
			form.setDescricaoLocalidadeFinal("");
		}
	}

	private void pesquisarGrupoCobranca(Fachada fachada,HttpSession sessao) {
		if(sessao.getAttribute("collectionCobrancaGrupo") == null){
			FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();
	        filtroCobrancaGrupo.setCampoOrderBy(FiltroCobrancaGrupo.DESCRICAO_ABREVIADA);
	        
	        Collection<CobrancaGrupo> collectionCobrancaGrupo = 
	        	fachada.pesquisar(filtroCobrancaGrupo, CobrancaGrupo.class.getName());
	        
	        if (collectionCobrancaGrupo == null || collectionCobrancaGrupo.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Grupo de Cobrança");
			} else {
				sessao.setAttribute("collectionCobrancaGrupo", collectionCobrancaGrupo);
			}
	        
		}
	}

	private void pesquisarUnidadeNegocio(Fachada fachada, HttpSession sessao, 
			GerarOSSeletivaFiscalizacaoActionForm form) {
		
		if(sessao.getAttribute("colecaoUnidadeNegocio") == null){
			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			
			if(form.getGerenciaRegional() != null && 
			  !form.getGerenciaRegional().equals("") &&
			  !form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
					
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(FiltroUnidadeNegocio.ID_GERENCIA, 
						form.getGerenciaRegional()));		
			}
			
			Collection colecaoUnidadeNegocio = fachada.pesquisar(filtroUnidadeNegocio,
					UnidadeNegocio.class.getName());
			
			if (colecaoUnidadeNegocio == null || colecaoUnidadeNegocio.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null,
						"Unidade de Negócio");
			} else {
				sessao.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
			}
		}
	}

	private void pesquisarGerenciaRegional(Fachada fachada, HttpSession sessao) {
		
		if(sessao.getAttribute("gerenciasRegionais") == null){
			
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection gerenciasRegionais = fachada.pesquisar(filtroGerenciaRegional,
					GerenciaRegional.class.getName());
			
			if (gerenciasRegionais == null || gerenciasRegionais.isEmpty()) {
				throw new ActionServletException("atencao.naocadastrado", null,"Gerência Regional");
			} else {
				sessao.setAttribute("gerenciasRegionais", gerenciasRegionais);
			}
		}
	}
	
	private void pesquisarordemServico(Fachada fachada, GerarOSSeletivaFiscalizacaoActionForm form,
			SistemaParametro sistemaParametro,HttpServletRequest httpServletRequest) {
		
		if (form.getIdOrdemServico() != null && !form.getIdOrdemServico().equals("")) {

			FiltroOrdemServico filtroOrdemServico = new FiltroOrdemServico();
			filtroOrdemServico.adicionarParametro(new ParametroSimples(FiltroOrdemServico.ID,new Integer(form.getIdOrdemServico())));
//			filtroOrdemServico.adicionarCaminhoParaCarregamentoEntidade(FiltroOrdemServico.SOLICITACAO_TIPO_ESPECIFICACAO);
			
			Collection colecaoOrdemServico = fachada.pesquisar(filtroOrdemServico,OrdemServico.class.getName());
			if(colecaoOrdemServico != null && !colecaoOrdemServico.isEmpty()){
			
				OrdemServico ordemServico = (OrdemServico) colecaoOrdemServico.iterator().next();
//				form.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());
				form.setNomeOrdemServico("");
				
				//[FS0001] – Validar Ordem de Serviço.
				if (ordemServico.getAtendimentoMotivoEncerramento() != null &&
					ordemServico.getAtendimentoMotivoEncerramento().getId()
					.equals(new Integer(AtendimentoMotivoEncerramento.CANCELADO_POR_DERCURSO_DE_PRAZO))) {
					form.setIdOrdemServico("");
					throw new ActionServletException("atencao.os.cancelada.decurso.prazo");
				}
				
				if(ordemServico.getDataEncerramento() != null){
					Integer qtdeDias = 0;
					if(sistemaParametro.getQtdeDiasEncerraOSFiscalizacao() != null){
						qtdeDias = sistemaParametro.getQtdeDiasEncerraOSFiscalizacao();
					}
					Date dataEncerramentoMaisDiasLimite = Util.adicionarNumeroDiasDeUmaData(ordemServico.getDataEncerramento(), qtdeDias);
					if(dataEncerramentoMaisDiasLimite.compareTo(new Date()) == -1){
						form.setIdOrdemServico("");
						throw new ActionServletException("atencao.quantidade_dias_os_encerrada_maior");
					}
				}
				
				if (ordemServico.getServicoTipo() != null && ordemServico.getServicoTipo().getId() != null 
						&& ordemServico.getServicoTipo().getId().equals(ServicoTipo.TIPO_FISCALIZACAO)){
					form.setIdOrdemServico("");
					throw new ActionServletException("atencao.tipo_servico_associado_os_fiscalizacao");
				}
				
				Integer idOsPendente = fachada.pesquisarOSFiscalizacaoPendente(ordemServico.getId());
				if(idOsPendente != null){
					form.setIdOrdemServico("");
					throw new ActionServletException("atencao.existe_os_fiscalizacao_pendente");
				}
				
			} else {
				
				form.setIdOrdemServico("");
				form.setNomeOrdemServico("Ordem de Serviço não cadastrada");
				httpServletRequest.setAttribute("OrdemServioInexistente", true);
				throw new ActionServletException("atencao.os.inexistente");
			}
		}
	}
	
}
