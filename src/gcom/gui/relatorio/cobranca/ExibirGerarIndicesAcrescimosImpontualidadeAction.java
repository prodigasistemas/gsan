package gcom.gui.relatorio.cobranca;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pre- processamento gerar os dados para leitura do txt
 * 
 * @author Sávio Luiz
 */
public class ExibirGerarIndicesAcrescimosImpontualidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarIndicesAcrescimosImpontualidade");

		IndiceAcrescimosImpontualidadeRelatorioActionForm indiceAcrescimosImpontualidadeRelatorioActionForm = (IndiceAcrescimosImpontualidadeRelatorioActionForm) actionForm;
		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {

			indiceAcrescimosImpontualidadeRelatorioActionForm
					.setMesAnoReferenciaInicial("");
			indiceAcrescimosImpontualidadeRelatorioActionForm
					.setMesAnoReferenciaFinal("");
			indiceAcrescimosImpontualidadeRelatorioActionForm
					.setTodosAcrecimos("2");

		}

		return retorno;
	}
}
