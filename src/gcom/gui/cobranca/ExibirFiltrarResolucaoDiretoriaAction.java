package gcom.gui.cobranca;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarResolucaoDiretoriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		FiltrarResolucaoDiretoriaActionForm form = (FiltrarResolucaoDiretoriaActionForm) actionForm;

		form.setAtualizar("1");

		HttpSession sessao = request.getSession(false);

		sessao.removeAttribute("filtroResolucaoDiretoria");

		if (request.getParameter("paginacao") != null) {
			form.setNumero((String) sessao.getAttribute("numero"));
			form.setAssunto((String) sessao.getAttribute("assunto"));
			form.setDataInicio((String) sessao.getAttribute("dataInicio"));
			form.setDataFim((String) sessao.getAttribute("dataFim"));
			form.setIndicadorParcelamentoUnico((String) sessao.getAttribute("indicadorParcelamentoUnico"));
			form.setIndicadorUtilizacaoLivre((String) sessao.getAttribute("indicadorUtilizacaoLivre"));
			form.setIndicadorDescontoFaixaReferenciaConta((String) sessao.getAttribute("indicadorDescontoFaixaReferenciaConta"));
			form.setIndicadorDescontoSancoes((String) sessao.getAttribute("indicadorDescontoSancoes"));
			form.setIndicadorParcelasEmAtraso((String) sessao.getAttribute("indicadorParcelasEmAtraso"));

			if (sessao.getAttribute("idParcelasEmAtraso") != null) {
				form.setIdParcelasEmAtraso((String) sessao.getAttribute("idParcelasEmAtraso"));
			}

			form.setIndicadorParcelamentoEmAndamento((String) sessao.getAttribute("indicadorParcelamentoEmAndamento"));

			if (sessao.getAttribute("idParcelamentoEmAndamento") != null) {
				form.setIdParcelamentoEmAndamento((String) sessao.getAttribute("idParcelamentoEmAndamento"));
			}
		}

		ActionForward retorno = actionMapping.findForward("exibirFiltrarResolucaoDiretoria");

		return retorno;
	}
}
