package gcom.gui;

import gcom.gui.StatusWizard.StatusWizardItem;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */
public class WizardAction extends DispatchAction {

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
	protected ActionForward redirecionadorWizard(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (httpServletRequest.getAttribute("confirmacao") != null
				&& ((String) httpServletRequest.getAttribute("confirmacao"))
						.trim().equalsIgnoreCase("true")) {
			return actionMapping.findForward("telaConfirmacao");
		} else {

			HttpSession sessao = httpServletRequest.getSession(false);
			StatusWizard statusWizard = (StatusWizard) sessao
					.getAttribute("statusWizard");
			String destino = httpServletRequest.getParameter("destino");
			String concluir = null;

			if (httpServletRequest.getParameter("concluir") != null
					&& !httpServletRequest.getParameter("concluir").equals("")) {
				concluir = httpServletRequest.getParameter("concluir");
			} else if (httpServletRequest.getAttribute("concluir") != null
					&& !httpServletRequest.getAttribute("concluir").equals("")) {
				concluir = (String) httpServletRequest.getAttribute("concluir");
			}

			String proximoCaminhoAction = null;

			if (concluir != null && concluir.trim().equalsIgnoreCase("true")) {
				// se o action for o de concluir validar o form
				ActionErrors errors = actionForm.validate(actionMapping,
						httpServletRequest);

				if (errors != null && !errors.isEmpty()) {
					saveErrors(httpServletRequest, errors);
					return actionMapping.findForward("telaErrosApresentacao");

				}

				proximoCaminhoAction = statusWizard.getCaminhoActionConclusao();
			} else {

				StatusWizardItem statusWizardItem = statusWizard
						.retornarItemWizard(Integer.parseInt(destino));

				proximoCaminhoAction = statusWizardItem
						.getCaminhoActionInicial();
			}

			try {
				return ((ActionForward) getClass().getMethod(
						proximoCaminhoAction,
						new Class[] { ActionMapping.class, ActionForm.class,
								HttpServletRequest.class,
								HttpServletResponse.class }).invoke(
						this,
						new Object[] { actionMapping, actionForm,
								httpServletRequest, httpServletResponse }));
			} catch (SecurityException ex) {
				throw new ActionServletException("erro.sistema", ex);
			} catch (NoSuchMethodException ex) {
				throw new ActionServletException("erro.sistema", ex);
			} catch (InvocationTargetException ex) {
				// caso o método execute jogue ActionServletException ou
				// ControladorException
				throw ((RuntimeException) ex.getCause());
			} catch (IllegalArgumentException ex) {
				throw new ActionServletException("erro.sistema", ex);
			} catch (IllegalAccessException ex) {
				throw new ActionServletException("erro.sistema", ex);
			}
		}
	}

}
