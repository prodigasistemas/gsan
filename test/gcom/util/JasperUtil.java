package gcom.util;

import gcom.gui.cadastro.atualizacaocadastral.AlteracaoImovelRelatorioAtualizacaoCadastral;
import gcom.gui.cadastro.atualizacaocadastral.ImovelRelatorioAtualizacaoCadastral;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

public class JasperUtil {
	public void gerarRelatorio(){
		try {
			Collection dados = new ArrayList();
			
			ImovelRelatorioAtualizacaoCadastral item = new ImovelRelatorioAtualizacaoCadastral();
			item.setDescImovel("1234567");
			AlteracaoImovelRelatorioAtualizacaoCadastral atua = new AlteracaoImovelRelatorioAtualizacaoCadastral("Alteração de Hidrômetro "
					, "POTENCIAL"
					, "DESLIGADO");
			item.addAlteracao(atua);
			atua = new AlteracaoImovelRelatorioAtualizacaoCadastral("Alteração de Situação de Água", "LIGADO", "POTENCIAL");
			item.addAlteracao(atua);
			dados.add(item);
			
			item = new ImovelRelatorioAtualizacaoCadastral();
			item.setDescImovel("4747383829");
			atua = new AlteracaoImovelRelatorioAtualizacaoCadastral("Alteração de Situação de ESGOTO", "LIGADO", "DESLIGADO");
			item.addAlteracao(atua);
			atua = new AlteracaoImovelRelatorioAtualizacaoCadastral("Quantidade de economias - RESIDENCIAL  R2", "LIGADO", "DESLIGADO");
			item.addAlteracao(atua);
			dados.add(item);

			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("P_MESA", "40");
			parametros.put("SUBREPORT_DIR", "/desenvolvimento/");


			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);
			String str = "/desenvolvimento/relatorioConsultaAtualizacaoCadastral.jasper";
			JasperPrint jasperPrint = JasperFillManager.fillReport(str, parametros, dataSource);
			File dest = new File("/desenvolvimento/pdf.pdf");
			dest.createNewFile();
			OutputStream out = new FileOutputStream(dest);
			pdfExporter(jasperPrint, out);
		} catch (JRException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void pdfExporter(JasperPrint jasperPrint, OutputStream out){
		try {
			JRPdfExporter exporter = new JRPdfExporter();
			
			exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, out);
			exporter.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS, Boolean.TRUE);
			exporter.exportReport();
			out.close();
			
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new JasperUtil().gerarRelatorio();
	}
}
