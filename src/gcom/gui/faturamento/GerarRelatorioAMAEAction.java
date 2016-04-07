package gcom.gui.faturamento;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioAMAEAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioAMAE");
		
		GerarRelatorioAMAEActionForm form = (GerarRelatorioAMAEActionForm) actionForm;
		String mesAno = form.getMesAno();
		Integer municipio = form.getCodigoMunicipio();

		
		return retorno;
	}


}
