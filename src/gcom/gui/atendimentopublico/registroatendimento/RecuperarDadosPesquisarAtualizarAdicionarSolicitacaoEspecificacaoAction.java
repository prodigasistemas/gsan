package gcom.gui.atendimentopublico.registroatendimento;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 04/12/2006
 */
public class RecuperarDadosPesquisarAtualizarAdicionarSolicitacaoEspecificacaoAction
		extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarAdicionarSolicitacaoEspecificacaoActionForm atualizarAdicionarSolicitacaoEspecificacaoActionForm = 
			(AtualizarAdicionarSolicitacaoEspecificacaoActionForm) actionForm;

		// Set no mapeamento de retorno
		ActionForward retorno = null;
	
		// recupera o caminho de retorno passado como parametro no jsp
		// que
		// chama
		// essa funcionalidade
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaUnidadeOrganizacional") != null) {
			
			retorno = actionMapping.findForward("exibirPesquisarUnidadeOrganizacional");
			
			sessao.setAttribute("caminhoRetornoTelaPesquisaUnidadeOrganizacional",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaUnidadeOrganizacional"));
			
			sessao.setAttribute("tipoUnidade","unidadeAtendimento");

			String caminhoRetornoTelaPesquisaTipoServico = 
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaUnidadeOrganizacional");

			if (caminhoRetornoTelaPesquisaTipoServico.equals("exibirAtualizarAdicionarSolicitacaoEspecificacaoAction")) {

				// Seta o que tem no form na sessao
				// para não perder os dados na volta da pesquisa
				sessao.setAttribute("atualizarAdicionarSolicitacaoEspecificacaoActionForm",
					atualizarAdicionarSolicitacaoEspecificacaoActionForm);

			}

		} else {
			sessao.removeAttribute("caminhoRetornoTelaPesquisaUnidadeOrganizacional");
			sessao.removeAttribute("tipoUnidade");
		}
		// recupera o caminho de retorno passado como parametro no jsp
		// que
		// chama
		// essa funcionalidade
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTipoServico") != null) {
			
			retorno = actionMapping.findForward("exibirPesquisarServicoTipo");
			
			sessao.setAttribute("caminhoRetornoTelaPesquisaTipoServico",
				httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTipoServico"));
			
			// Seta o que tem no form na sessao
			// para não perder os dados na volta da pesquisa
			sessao.setAttribute("atualizarAdicionarSolicitacaoEspecificacaoActionForm",
				atualizarAdicionarSolicitacaoEspecificacaoActionForm);

		} else {
			sessao.removeAttribute("caminhoRetornoTelaPesquisaTipoServico");
		}
		
		
		/*
		 * Colocado por Raphael Rossiter em 26/02/2008
		 * Recupera o caminho de retorno passado como parametro no jsp que chama essa funcionalidade
		 */
		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTipoDebito") != null) {
			
			retorno = actionMapping.findForward("exibirPesquisarDebitoTipo");
			
			sessao.setAttribute("caminhoRetornoTelaPesquisaTipoDebito",
			httpServletRequest.getParameter("caminhoRetornoTelaPesquisaTipoDebito"));
		
		} else {
			sessao.removeAttribute("caminhoRetornoTelaPesquisaTipoDebito");
		}

		return retorno;
	}
}
