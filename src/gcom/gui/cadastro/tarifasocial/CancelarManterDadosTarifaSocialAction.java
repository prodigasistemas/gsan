package gcom.gui.cadastro.tarifasocial;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Consulta o imovel da tarifa social
 * 
 * @author thiago toscano
 * @date 
 */
public class CancelarManterDadosTarifaSocialAction extends GcomAction  {


    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = null;

        //obtendo uma instancia da sessao
        //HttpSession sessao = httpServletRequest.getSession(false);

        retorno = actionMapping.findForward("concluir");

        CancelarManterDadosTarifaSocialActionForm form = (CancelarManterDadosTarifaSocialActionForm) actionForm;

		try {
		    OTDManterDadosClienteImovelEconomia otd = (OTDManterDadosClienteImovelEconomia) form.getOTD(httpServletRequest);
		    otd.resetCollectionCliente(new Integer(form.getIdImovelEconomia()));
		} catch (Exception e) { }

        httpServletRequest.setAttribute("reexibir","false");

        return retorno;
	}
}
