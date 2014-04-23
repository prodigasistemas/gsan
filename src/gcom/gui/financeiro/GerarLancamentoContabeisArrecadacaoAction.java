package gcom.gui.financeiro;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Rafael Santos
 * @since 21/12/2005
 * 
 */
public class GerarLancamentoContabeisArrecadacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("telaSucesso");

		GerarLancamentosContabeisArrecadacaoActionForm form = (GerarLancamentosContabeisArrecadacaoActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		String mesAno = form.getMesAno();
		
		fachada.gerarLancamentoContabeisArrecadacao(Util.formatarMesAnoComBarraParaAnoMes(mesAno),1,1);
		
		return retorno;
	}
}
