package gcom.gui.portal;

import gcom.cadastro.localidade.Localidade;
import gcom.gui.GcomAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirLojasAtendimentoAction extends GcomAction {

	private ExibirLojasAtendimentoActionForm form;
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		form = (ExibirLojasAtendimentoActionForm) actionForm;

		List<Localidade> localidades = getFachada().pesquisarLocalidadesLojaVirtual();
		if (existeLocalidades(localidades))
			request.setAttribute("localidades", localidades);

		if (isLocalidadeSelecionada()) {
			List<LojaAtendimentoHelper> lojas = getFachada().pesquisarLojasAtendimento(form.getLocalidade());

			if (lojas != null && !lojas.isEmpty())
				request.setAttribute("lojas", lojas);

		} else if (isLocalidadeNaoSelecionada()) {
			request.removeAttribute("lojas");
		}

		return actionMapping.findForward("lojas-de-atendimento");
	}

	private boolean existeLocalidades(List<Localidade> localidades) {
		return localidades != null && !localidades.isEmpty();
	}

	private boolean isLocalidadeNaoSelecionada() {
		return form.getLocalidade() == null || form.getLocalidade() == "-1";
	}

	private boolean isLocalidadeSelecionada() {
		return form.getLocalidade() != null;
	}
}