package gcom.gui.cobranca;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento para filtrar o criterio da cobrança
 * 
 * @author Sávio Luiz
 * @date 17/04/2006
 */
public class ExibirFiltrarCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarCriterioCobranca");

		CriterioCobrancaFiltrarActionForm criterioCobrancaFiltrarActionForm = (CriterioCobrancaFiltrarActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		//caso venha da tela atualizar sem ter passado pelo filtro
		//(no caso vim da tela de sucesso do inserir)
		if (sessao.getAttribute("indicadorAtualizar") == null) {
			sessao.setAttribute("indicadorAtualizar", "1");
		}

		String limpar = (String) httpServletRequest.getParameter("limpar");
		if (limpar != null && !limpar.equals("")) {
			criterioCobrancaFiltrarActionForm.setDescricaoCriterio("");
			criterioCobrancaFiltrarActionForm.setDataInicioVigencia("");
			criterioCobrancaFiltrarActionForm.setNumeroAnoContaAntiga("");
			criterioCobrancaFiltrarActionForm
					.setOpcaoAcaoImovelDebitoContasAntigas("");
			criterioCobrancaFiltrarActionForm
					.setOpcaoAcaoImovelDebitoMesConta("");
			criterioCobrancaFiltrarActionForm.setOpcaoAcaoImovelSit("");
			criterioCobrancaFiltrarActionForm.setOpcaoAcaoImovelSitEspecial("");
			criterioCobrancaFiltrarActionForm
					.setOpcaoAcaoInquilinoDebitoMesConta("");
			criterioCobrancaFiltrarActionForm.setOpcaoContasRevisao("");
			criterioCobrancaFiltrarActionForm.setIndicadorUso("3");
			sessao.setAttribute("indicadorAtualizar", "1");
			httpServletRequest.setAttribute("nomeCampo", "descricaoCriterio");

		}
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			criterioCobrancaFiltrarActionForm.setIndicadorUso("3");
			httpServletRequest.setAttribute("nomeCampo", "descricaoCriterio");
		}

		return retorno;
	}
}
