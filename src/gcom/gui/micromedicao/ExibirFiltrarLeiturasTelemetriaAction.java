package gcom.gui.micromedicao;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC1070] Filtrar Leituras Telemetria
 * 
 * @author Hugo Amorim
 * @date 27/09/2010
 *
 */
public class ExibirFiltrarLeiturasTelemetriaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.
			findForward("exibirFiltrarLeiturasTelemetriaAction");
	
		FiltrarLeiturasTelemetriaForm form = 
			(FiltrarLeiturasTelemetriaForm) actionForm;
		
		if(form.getSituacaoLeitura()==null){
			//	Caso Situação Leitura estaja nula seja
			//como default o valor 3('AMBOS')
			form.setSituacaoLeitura("3");
		}
			
		return retorno;
	}
}
