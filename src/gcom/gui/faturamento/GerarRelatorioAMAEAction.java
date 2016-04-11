package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioUtil;
import gcom.relatorio.arrecadacao.dto.ResumoCreditosAvisosBancariosDTO;
import gcom.relatorio.faturamento.dto.RelatorioAMAEDTO;
import gcom.util.IoUtil;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.prodigasistemas.gsan.relatorio.FormatoRelatorio;
import br.com.prodigasistemas.gsan.relatorio.ReportItemDTO;

public class GerarRelatorioAMAEAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioAMAE");
		
		GerarRelatorioAMAEActionForm form = (GerarRelatorioAMAEActionForm) actionForm;
		Integer municipio = form.getCodigoMunicipio() == 0 ? null : form.getCodigoMunicipio();
		String mesAno = form.getMesAno().replace("/", "");
		String anoMes = mesAno.substring(2) + mesAno.substring(0,2);

		Fachada fachada = Fachada.getInstancia();
		List<RelatorioAMAEDTO> colecaoDadosAMAE = fachada.pesquisarContasFaturadasDesde122015(Integer.parseInt(anoMes), municipio);

		if (colecaoDadosAMAE == null || colecaoDadosAMAE.isEmpty()) {
			throw new ActionServletException("atencao.relatorio.vazio");
		}
		
		String nomeRelatorio = "relatorio_faturamento_amae" + mesAno + ".pdf";
		RelatorioUtil relatorioUtil = new RelatorioUtil(
				"Resumo de Faturamento",
				nomeRelatorio,
				RelatorioAMAEDTO.class, 
				FormatoRelatorio.PDF);

		List<ReportItemDTO> itens = new ArrayList<ReportItemDTO>();
		itens.addAll(colecaoDadosAMAE);

		File relatorio = gerar(relatorioUtil, itens);
		
		downloadRelatorio(httpServletResponse, relatorio);

		
		return retorno;
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


}
