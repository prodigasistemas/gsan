package gcom.gui.cobranca.spcserasa;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarMovimentoExclusaoNegativacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirGerarMovimentoExclusaoNegativacao");

		GerarMovimentoExclusaoNegativacaoActionForm form = (GerarMovimentoExclusaoNegativacaoActionForm) actionForm;

		if (form.getOpcao() != null && form.getOpcao().equals("1")) {
			Collection colecaoNegativadores = Fachada.getInstancia().consultarNegativadoresParaExclusaoMovimento();
			form.setCollNegativadores(colecaoNegativadores);
		}

		return retorno;
	}
}
