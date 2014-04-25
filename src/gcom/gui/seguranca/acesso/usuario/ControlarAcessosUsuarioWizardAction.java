package gcom.gui.seguranca.acesso.usuario;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Rodrigo
 */
public class ControlarAcessosUsuarioWizardAction extends WizardAction {

	/**
	 * Description of the Method
	 */
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
	public ActionForward exibirControlarRestrincoesAcessoUsuarioAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ControlarAcessoUsuarioActionForm controlarAcessoUsuarioActionForm = (ControlarAcessoUsuarioActionForm) actionForm;

		// verifica se o usuário desmarcou todas as opções de permissão especial
		if (httpServletRequest.getParameter("permissoesEspeciais") == null
				&& (httpServletRequest.getParameter("numeroPagina") != null && httpServletRequest
						.getParameter("numeroPagina").equals("2"))) {
			controlarAcessoUsuarioActionForm.setPermissoesEspeciais(null);
			controlarAcessoUsuarioActionForm
					.setPermissoesCheckBoxVazias("true");
		}
		// recebe o parametros e consulta o objeto da sessao para chamar outro
		// metodo desta classe
		return new ExibirControlarRestrincoesAcessoUsuarioAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}

	/**
	 * Description of the Method
	 */
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
	public ActionForward controlarRestrincoesAcessoUsuarioAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// recebe o parametros e consulta o objeto da sessao para chamar outro
		// metodo desta classe
		new ControlarRestrincoesAcessoUsuarioAction().execute(actionMapping,
				actionForm, httpServletRequest, httpServletResponse);
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}

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
	public ActionForward exibirControlarPermissoesEspeciaisUsuarioAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//ControlarAcessoUsuarioActionForm controlarAcessoUsuarioActionForm = (ControlarAcessoUsuarioActionForm) actionForm;

		return new ExibirControlarPermissoesEspeciaisUsuarioAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}

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
	public ActionForward controlarPremissoesEspeciaisUsuarioAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ControlarAcessoUsuarioActionForm controlarAcessoUsuarioActionForm = (ControlarAcessoUsuarioActionForm) actionForm;

		// verifica se o usuário desmarcou todas as opções de permissão especial
		if (httpServletRequest.getParameter("permissoesEspeciais") == null
				&& (httpServletRequest.getParameter("numeroPagina") != null && httpServletRequest
						.getParameter("numeroPagina").equals("2"))) {
			controlarAcessoUsuarioActionForm.setPermissoesEspeciais(null);
			controlarAcessoUsuarioActionForm
					.setPermissoesCheckBoxVazias("true");
		}

		new ControlarPremissoesEspeciaisUsuarioAction().execute(actionMapping,
				actionForm, httpServletRequest, httpServletResponse);
		return this.redirecionadorWizard(actionMapping, actionForm,
				httpServletRequest, httpServletResponse);
	}

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
	public ActionForward concluirControlarAcessosUsuarioAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new ConcluirControlarAcessosUsuarioAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}

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
	public ActionForward cancelarControlarAcessoUsuarioAction(
			ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		return new CancelarControlarAcessoUsuarioAction().execute(
				actionMapping, actionForm, httpServletRequest,
				httpServletResponse);
	}

}
