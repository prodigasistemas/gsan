package gcom.gui.faturamento;

import gcom.api.GsanRelatorio;
import gcom.api.relatorio.ReportFormat;
import gcom.api.relatorio.ReportItemDTO;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.dto.RelatorioAgenciaReguladoraDTO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioAgenciaReguladoraAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = mapping.findForward("exibirGerarRelatorioAgenciaReguladora");

		GerarRelatorioAgenciaReguladoraActionForm form = (GerarRelatorioAgenciaReguladoraActionForm) actionForm;
		Integer idAgencia = form.getIdAgenciaReguladora() == 0 ? null : form.getIdAgenciaReguladora();
		String mesAno = form.getMesAno().replace("/", "");
		String anoMes = mesAno.substring(2) + mesAno.substring(0, 2);

		List<RelatorioAgenciaReguladoraDTO> dtos = getFachada().pesquisarContasParaRelatorioAgenciaReguladora(Integer.parseInt(anoMes), idAgencia);

		if (dtos == null || dtos.isEmpty()) {
			throw new ActionServletException("atencao.relatorio.vazio");
		}

		GsanRelatorio relatorio = null;
		if (form.getTipoRelatorio() == 1) {
			relatorio = new GsanRelatorio("Relatório faturamento para agência reguladora", getNomeRelatorio(anoMes) + ".pdf", RelatorioAgenciaReguladoraDTO.class, ReportFormat.PDF);
		} else {
			relatorio = new GsanRelatorio("Relatório faturamento para agência reguladora", getNomeRelatorio(anoMes) + ".xls", RelatorioAgenciaReguladoraDTO.class, ReportFormat.XLS);
		}
		relatorio.setOmitirTotalGeral(true);

		List<ReportItemDTO> itens = new ArrayList<ReportItemDTO>();
		itens.addAll(dtos);

		try {
			relatorio.gerar(itens, response);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.erro_baixar_relatorio");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.erro_baixar_relatorio");
		}

		return retorno;
	}

	private String getNomeRelatorio(String mesAno) {
		return "relatorio_faturamento_agencia_reguladora" + mesAno;
	}
}
