package gcom.gui.atendimentopublico;

import java.util.Collection;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInformarAssociacaoSituacaoCobrancaAction extends GcomAction {
	
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ActionForward retorno = actionMapping
			.findForward("exibirInformarAssociacaoSituacaoCobranca");

		InformarTramiteAssociacaoSituacaoCobrancaActionForm informarTramiteAssociacaoSituacaoCobrancaActionForm = (InformarTramiteAssociacaoSituacaoCobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		

		if (httpServletRequest.getParameter("abrirPopup") != null && 
				httpServletRequest.getParameter("abrirPopup").trim().equals("SIM") &&
				httpServletRequest.getParameter("idSituacaoCobranca") != null && 
				!httpServletRequest.getParameter("idSituacaoCobranca").trim().equals("")) {
			FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.ID, httpServletRequest.getParameter("idSituacaoCobranca")));
	
			Collection colecao = fachada.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName());
			
			if (colecao != null && !colecao.isEmpty()) {
				
				CobrancaSituacao cobrancaSituacao = (CobrancaSituacao) Util.retonarObjetoDeColecao(colecao);
	
				informarTramiteAssociacaoSituacaoCobrancaActionForm.setDescricaoSituacaoCobranca(cobrancaSituacao.getDescricao());
				informarTramiteAssociacaoSituacaoCobrancaActionForm.setIdSituacaoCobranca(cobrancaSituacao.getId().toString());
				
			} else {
				throw new ActionServletException(
						"atencao.situacao_cobranca.inexistente", null, "Cobranca Situacao");
			}
			
			FiltroSolicitacaoTipo filtroSolicitacaoTipo = new FiltroSolicitacaoTipo();
			filtroSolicitacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipo.INDICADOR_USO, ConstantesSistema.SIM));
			filtroSolicitacaoTipo.setCampoOrderBy(FiltroSolicitacaoTipo.DESCRICAO);
			
			Collection colecaoTipoSolicitacao = fachada.pesquisar(filtroSolicitacaoTipo, SolicitacaoTipo.class.getName());
			
			if (colecaoTipoSolicitacao != null && !colecaoTipoSolicitacao.isEmpty()) {
				sessao.setAttribute("colecaoTipoSolicitacao", colecaoTipoSolicitacao);
			} else {
				sessao.removeAttribute("colecaoTipoSolicitacao");
			}
			
			sessao.removeAttribute("colecaoTipoEspecificacao");

			informarTramiteAssociacaoSituacaoCobrancaActionForm.setDescricaoUnidadeAtendimento("");
			informarTramiteAssociacaoSituacaoCobrancaActionForm.setIdUnidadeAtendimento("");
			informarTramiteAssociacaoSituacaoCobrancaActionForm.setIdTipoEspecificacao("-1");
			informarTramiteAssociacaoSituacaoCobrancaActionForm.setIdTipoSolicitacao("-1");
    	}
		
		// Carregar os registros de SolicitacaoTipoEspecificacao relativos ao TipoSolicitacao selecionado
		if (httpServletRequest.getParameter("carregarEspecificacao") != null && 
				httpServletRequest.getParameter("carregarEspecificacao").trim().equalsIgnoreCase("SIM")) {

			FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipoEspecificacao.INDICADOR_USO, ConstantesSistema.SIM));
			filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO_ID, informarTramiteAssociacaoSituacaoCobrancaActionForm.getIdTipoSolicitacao()));
			filtroSolicitacaoTipoEspecificacao.setCampoOrderBy(FiltroSolicitacaoTipoEspecificacao.DESCRICAO);
			
			Collection colecaoTipoEspecificacao = fachada.pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName());
			
			if (colecaoTipoEspecificacao != null && !colecaoTipoEspecificacao.isEmpty()) {
				sessao.setAttribute("colecaoTipoEspecificacao", colecaoTipoEspecificacao);
			} else {
				sessao.removeAttribute("colecaoTipoEspecificacao");
			}
		}
		
		// Pesquisar Unidade Organizacional
		if(informarTramiteAssociacaoSituacaoCobrancaActionForm.getIdUnidadeAtendimento() != null
				&& !informarTramiteAssociacaoSituacaoCobrancaActionForm.getIdUnidadeAtendimento().equals("")) {
			
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, informarTramiteAssociacaoSituacaoCobrancaActionForm.getIdUnidadeAtendimento()));
			
			Collection colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());
			
			if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {
				sessao.removeAttribute("unidadeSuperiorEncontrada");
				informarTramiteAssociacaoSituacaoCobrancaActionForm.setIdUnidadeAtendimento("");
				informarTramiteAssociacaoSituacaoCobrancaActionForm.setDescricaoUnidadeAtendimento("UNIDADE INEXISTENTE");
			} else {
				UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);
				
				sessao.setAttribute("unidadeSuperiorEncontrada","true");
				informarTramiteAssociacaoSituacaoCobrancaActionForm.setIdUnidadeAtendimento(unidadeOrganizacional.getId().toString());
				informarTramiteAssociacaoSituacaoCobrancaActionForm.setDescricaoUnidadeAtendimento(unidadeOrganizacional.getDescricao());
			}
		}
		
		if (httpServletRequest.getParameter("idCampoEnviarDados") != null
				&& !httpServletRequest.getParameter("idCampoEnviarDados").equals("")
				&& httpServletRequest.getParameter("descricaoCampoEnviarDados") != null
				&& !httpServletRequest.getParameter("descricaoCampoEnviarDados").equals("")) {
			informarTramiteAssociacaoSituacaoCobrancaActionForm.setIdUnidadeAtendimento(httpServletRequest.getParameter("idCampoEnviarDados"));
			informarTramiteAssociacaoSituacaoCobrancaActionForm.setDescricaoUnidadeAtendimento(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
			sessao.setAttribute("unidadeSuperiorEncontrada","true");
			
		}
		
		return retorno;
	}

}
