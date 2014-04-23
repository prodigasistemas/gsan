package gcom.gui.gerencial.cobranca;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Ana Maria
 */
public class ConsultarResumoAcaoCobrancaWizardAction extends WizardAction {

    /**
     * Description of the Method
     */

    public ActionForward exibirDadosGeracaoConsultaAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return  new ExibirDadosGeracaoConsultaAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    public ActionForward exibirConsultarResumoAcaoCobrancaAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirConsultarResumoAcaoCobrancaAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }
}
