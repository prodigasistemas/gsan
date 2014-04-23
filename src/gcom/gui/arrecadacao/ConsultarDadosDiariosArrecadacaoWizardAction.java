package gcom.gui.arrecadacao;

import gcom.fachada.Fachada;
import gcom.gui.WizardAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Paiva
 */
public class ConsultarDadosDiariosArrecadacaoWizardAction extends WizardAction {
	
	@Override
	public ActionForward execute(
			ActionMapping arg0, 
			ActionForm arg1, 
			HttpServletRequest arg2, 
			HttpServletResponse arg3) throws Exception {
		
        // Verificamos se o processo de dados diários da arrecadação está rodando
        Fachada.getInstancia().verificarBatchDadosDiariosArracadacaoRodando();
        
		return super.execute(arg0, arg1, arg2, arg3);
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
    public ActionForward exibirConsultarDadosDiariosParametrosAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirConsultarDadosDiariosParametrosAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    public ActionForward exibirConsultarDadosDiariosGerenciaAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirConsultarDadosDiariosGerenciaAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    public ActionForward exibirConsultarDadosDiariosArrecadadorAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirConsultarDadosDiariosArrecadadorAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    public ActionForward exibirConsultarDadosDiariosCategoriaAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirConsultarDadosDiariosCategoriaAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    public ActionForward exibirConsultarDadosDiariosPerfilAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirConsultarDadosDiariosPerfilAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }

    public ActionForward exibirConsultarDadosDiariosDocumentoAction(
            ActionMapping actionMapping, ActionForm actionForm,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        return new ExibirConsultarDadosDiariosDocumentoAction().execute(
                actionMapping, actionForm, httpServletRequest,
                httpServletResponse);
    }
}
