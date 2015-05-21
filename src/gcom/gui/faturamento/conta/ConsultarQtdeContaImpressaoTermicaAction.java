package gcom.gui.faturamento.conta;

import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.ContaImpressaoTermicaQtde;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConsultarQtdeContaImpressaoTermicaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("consultarQtdeContaImpressaoTermica");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarQtdeContaImpressaoTermicaActionForm form = (ConsultarQtdeContaImpressaoTermicaActionForm) actionForm;

		Integer idGrupoFaturamento = (Integer) form.getIdGrupoFaturamento();
		Integer referencia = Util.formatarMesAnoComBarraParaAnoMes(form.getReferencia());
		
		if (idGrupoFaturamento != null && referencia != null) {
			Collection<ContaImpressaoTermicaQtde> colecaoQtdeContas = fachada.pesquisarQtdeContaImpressaoTermica(idGrupoFaturamento, referencia);
			sessao.setAttribute("colecaoQtdeContas",colecaoQtdeContas);
		} else {
			
		}
        
        return retorno;
		
	}
}
