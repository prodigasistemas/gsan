package gcom.gui.atendimentopublico.registroatendimento;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action auxiliar responsável por não perder as informações preenchidas no
 * solicitacao_tipo_inserir.jsp na hora de adicionar solicitações especificações
 * 
 * @author Sávio Luiz
 * @created 28 de Julho de 2006
 */
public class RecuperarDadosInserirTipoSolicitacaoEspecificacaoAction extends
		GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAdicionarSolicitacaoEspecificacao");
		//InserirTipoSolicitacaoEspecificacaoActionForm inserirTipoSolicitacaoEspecificacaoActionForm = (InserirTipoSolicitacaoEspecificacaoActionForm) actionForm;

		// recupera o caminho de retorno passado como parametro no jsp
		// que
		// chama
		// essa funcionalidade
		if (httpServletRequest.getParameter("retornarTela") != null) {
			sessao.setAttribute("retornarTela", httpServletRequest
					.getParameter("retornarTela"));
		}

		return retorno;
	}
}
