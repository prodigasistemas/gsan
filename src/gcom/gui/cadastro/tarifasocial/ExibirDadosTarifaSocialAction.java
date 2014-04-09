package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */
public class ExibirDadosTarifaSocialAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        ActionForward retorno = actionMapping.findForward("exibirAtualizarDadosTarifaSocial");

        ExibirDadosTarifaSocialActionForm form = (ExibirDadosTarifaSocialActionForm) actionForm;

        HttpSession sessao = httpServletRequest.getSession(false);
        
        //FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();
        //filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples(FiltroTarifaSocialDadoEconomia.ID,new Integer(form.getIdTarifaSocialDadoEconomia())));

        //Cliente cliente = (Cliente) sessao.getAttribute("cliente");

        Collection coll = (Collection) sessao.getAttribute("collTarifaSocialDadoEconomia");
        TarifaSocialDadoEconomia tarifaSocialDadoEconomia = null;
        if (coll != null && !coll.isEmpty()) {
        	Iterator it = coll.iterator();
        	while(it.hasNext()){
        		TarifaSocialDadoEconomia tarifa = (TarifaSocialDadoEconomia) it.next();
        		if (tarifa.getId().intValue() == new Integer(form.getIdTarifaSocialDadoEconomia()).intValue()) {
        			tarifaSocialDadoEconomia = tarifa;
        		}
        	}
        }



        sessao.setAttribute("tarifaSocialDadoEconomia",tarifaSocialDadoEconomia);

        return retorno;
    }
}
