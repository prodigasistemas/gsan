package gcom.gui.relatorio.arrecadacao;

import gcom.api.GsanRelatorio;
import gcom.api.relatorio.ReportFormat;
import gcom.api.relatorio.ReportItemDTO;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.relatorio.arrecadacao.dto.ResumoCreditosAvisosBancariosDTO;
import gcom.util.Util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioResumoCreditosAvisosBancariosAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		GerarRelatorioResumoCreditosAvisosBancariosActionForm form = (GerarRelatorioResumoCreditosAvisosBancariosActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		String data = form.getDataConsulta().replace("/", "-");
		String nomeRelatorio = "resumo_creditos_avisos_bancarios_" + data + ".pdf";

		GsanRelatorio relatorio = new GsanRelatorio("Resumo de Creditos dos Avisos Bancarios", nomeRelatorio, ResumoCreditosAvisosBancariosDTO.class, ReportFormat.PDF);

		List<ResumoCreditosAvisosBancariosDTO> resumos = fachada.pesquisarResumoCreditosAvisosBancarios(Util.converteStringParaDate(form.getDataConsulta()));

		if (resumos == null || resumos.isEmpty()) {
			throw new ActionServletException("atencao.relatorio.vazio");
		}

		List<ReportItemDTO> itens = new ArrayList<ReportItemDTO>();
		itens.addAll(resumos);

		try {
			relatorio.gerar(itens, response);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.erro_baixar_relatorio");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.erro_baixar_relatorio");
		}

		return null;
	}
}
