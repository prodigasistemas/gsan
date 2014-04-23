package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Ivan Sergio
 * @date 02/12/2010
 */
public class ExibirInserirTipoCorteAction extends GcomAction {

	/**
	 * [UC1102] Inserir Tipo de Corte
	 * 
	 * @author Ivan Sergio
	 * @date 02/12/2010
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("tipoCorteInserir");

		InserirTipoCorteActionForm form = (InserirTipoCorteActionForm) actionForm;
		form.setIndicadorUso("1");
		form.setIndicadorCorteAdministrativo("1");
		
		return retorno;
	}
}
