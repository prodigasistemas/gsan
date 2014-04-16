package gcom.gui.gerencial.cobranca;

import gcom.gui.WizardAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Sávio Luiz
 */
public class ConsultarResumoAcaoCobrancaEventualWizardAction extends WizardAction {

    /**
     * Description of the Method
     */

    public ActionForward exibirDadosGeracaoConsultaEventualAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return  new ExibirDadosGeracaoConsultaEventualAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    public ActionForward exibirConsultarResumoAcaoCobrancaEventualAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirConsultarResumoAcaoCobrancaEventualAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }
}
