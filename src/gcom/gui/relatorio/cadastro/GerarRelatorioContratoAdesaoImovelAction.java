package gcom.gui.relatorio.cadastro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.relatorio.RelatorioUtil;
import gcom.relatorio.arrecadacao.dto.ResumoCreditosAvisosBancariosDTO;
import gcom.relatorio.cadastro.dto.ContratoAdesaoimovelDTO;
import gcom.relatorio.cliente.FormatoRelatorio;
import gcom.relatorio.cliente.ReportItemDTO;
import gcom.util.IoUtil;
import gcom.util.Util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioContratoAdesaoImovelAction extends GcomAction {



	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		GerarRelatorioContratoAdesaoImovelActionForm form = (GerarRelatorioContratoAdesaoImovelActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		String data = Util.formatarDataComTracoAAAAMMDD(new Date());
		String nomeRelatorio = "contrato_adesao_"+ form.getIdImovel() + data + ".pdf";
		
		RelatorioUtil relatorioUtil = new RelatorioUtil(
				"Contrato de Adesão de Imóvel",
				nomeRelatorio,
				ResumoCreditosAvisosBancariosDTO.class, 
				FormatoRelatorio.PDF);
		
		//url
		// gsan-api 
		// invoke

		File relatorio = gerar(relatorioUtil, obterLinha());
		
		downloadRelatorio(response, relatorio);

		return null;
	}

	private File gerar(RelatorioUtil relatorioUtil, List<ReportItemDTO> itens) {
		File relatorio = null;
		
		try {
			relatorio = relatorioUtil.gerarRelatorio(itens);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.erro_baixar_relatorio");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.erro_baixar_relatorio");
		}
		
		return relatorio;
	}

	private void downloadRelatorio(HttpServletResponse response, File relatorio) {
		try {
			response.setContentType(FormatoRelatorio.PDF.getContentType());
			response.addHeader("Content-Disposition", "attachment; filename=" + relatorio.getName());

			ServletOutputStream sos = response.getOutputStream();
			sos.write(IoUtil.getBytesFromFile(relatorio));
			sos.flush();
			sos.close();

			relatorio.delete();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ActionServletException("atencao.erro_baixar_relatorio");
		}
	}
	
	private String getTextoContrato() {
		return new StringBuilder("Contrato de adesão").toString();
	}
	
	private List<ReportItemDTO> obterLinha() {
		List<ReportItemDTO> itens = new ArrayList<ReportItemDTO>();
		
		itens.add(new ContratoAdesaoimovelDTO(getTextoContrato()));
		
		return itens;
	}

}
