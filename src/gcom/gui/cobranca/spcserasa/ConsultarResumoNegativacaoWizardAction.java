package gcom.gui.cobranca.spcserasa;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Ana Maria
 */
public class ConsultarResumoNegativacaoWizardAction extends WizardAction {

    /**
     * Description of the Method
     */

    public ActionForward exibirDadosGeracaoConsultaNegativacaoAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return  new ExibirDadosGeracaoConsultaNegativacaoAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    public ActionForward exibirConsultarResumoNegativacaoAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirConsultarResumoNegativacaoAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }
}
