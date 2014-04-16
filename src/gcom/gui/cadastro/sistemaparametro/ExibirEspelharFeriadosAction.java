package gcom.gui.cadastro.sistemaparametro;

import gcom.gui.GcomAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC0535]MANTER FERIADO
 * [SB0003] Criar espelhos dos feriados existentes
 * 
 * @author Bruno Barros
 * @date 12/01/2009
 */

public class ExibirEspelharFeriadosAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("espelharFeriados");
        
        EspelharFeriadosActionForm form = ( EspelharFeriadosActionForm ) actionForm;
        form.setIndicadorTipoFeriado( "3" );
        
		return retorno;
	}
}
