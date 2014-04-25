package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.bean.AnalisarImoveisReleituraHelper;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarAnalisarReleituraImoveisAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirReleituraImoveisAction");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		// Adicionamos os parametros à pesquisa
		FiltrarAnalisarReleituraImoveisActionForm form = (FiltrarAnalisarReleituraImoveisActionForm) actionForm;

		if (sessao.getAttribute("form") != null) {
			if (form.getMesAno() == null) {
				form = (FiltrarAnalisarReleituraImoveisActionForm) sessao
						.getAttribute("form");
			} else {
				sessao.setAttribute("form", form);
			}
		} else {
			sessao.setAttribute("form", form);
		}

		Collection<AnalisarImoveisReleituraHelper> colAnalisarImoveisReleituraHelper = fachada
				.pesquisarImovelParaReleitura(form.getMesAno(), form
						.getIdGrupo(), form.getIdRota(), form.getIdQuadra(),
						form.getIdSituacaoReleitura(), form.getEmpresa());

		sessao.setAttribute("colAnalisarImoveisReleituraHelper",
				colAnalisarImoveisReleituraHelper);
		sessao.setAttribute("formularioFiltrarReleituraImoveis", form);

		return retorno;
	}
}
