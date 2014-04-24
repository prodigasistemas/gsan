package gcom.tarefa;

import gcom.batch.ControladorBatchLocal;
import gcom.batch.ControladorBatchLocalHome;
import gcom.batch.FuncionalidadeIniciada;
import gcom.batch.Relatorio;
import gcom.batch.RelatorioGerado;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ControladorUtilLocal;
import gcom.util.ControladorUtilLocalHome;
import gcom.util.Internacionalizador;
import gcom.util.IoUtil;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.ZipUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.zip.ZipOutputStream;

import javax.ejb.CreateException;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class TarefaRelatorio extends Tarefa {

	private static final long serialVersionUID = 1L;

	public static final int TIPO_PDF = 1;

	public static final int TIPO_RTF = 2;

	public static final int TIPO_XLS = 3;

	public static final int TIPO_HTML = 4;

	public static final int INDICADOR_EXIBE_MENSAGEM = 1;

	public static final int INDICADOR_NAO_EXIBE_MENSAGEM = 2;

	public static final String PARAMETRO_LOCALE_RELATORIO = "REPORT_LOCALE";

	public static final String PARAMETRO_CAMINHO_PROPERTIES_RELATORIO = "REPORT_RESOURCE_BUNDLE";

	public static final String VALOR_PARAMETRO_CAMINHO_PROPERTIES_RELATORIO = "gcom.properties.application";

	protected String nomeRelatorio = null;

	public byte[] gerarRelatorio(String nomeRelatorio, Map parametrosRelatorio,
			RelatorioDataSource relatorioDataSource, int tipoSaidaRelatorio)
			throws RelatorioVazioException {

		// valor de retorno
		ByteArrayOutputStream retorno = new ByteArrayOutputStream();
		byte[] retornoArray = null;

		// cria uma instância da classe JasperReport que vai conter o relatório
		// criado
		JasperReport jasperReport = null;

		InputStream stream = null;

		try {

			// carrega o arquivo do relatório (jasper) já compilado
			stream = (ConstantesRelatorios.getURLRelatorio(nomeRelatorio)).openStream();

			// carrega o relatório compilado
			jasperReport = (JasperReport) JRLoader.loadObject(stream);

			stream.close();

			if(Internacionalizador.getLocale() != null){
				parametrosRelatorio.put(PARAMETRO_CAMINHO_PROPERTIES_RELATORIO, 
						ResourceBundle.getBundle(VALOR_PARAMETRO_CAMINHO_PROPERTIES_RELATORIO, Internacionalizador.getLocale())) ;

				parametrosRelatorio.put( PARAMETRO_LOCALE_RELATORIO, Internacionalizador.getLocale());				
			}else{
				parametrosRelatorio.put(PARAMETRO_CAMINHO_PROPERTIES_RELATORIO, 
						ResourceBundle.getBundle(VALOR_PARAMETRO_CAMINHO_PROPERTIES_RELATORIO, new Locale("pt","BR"))) ;

				parametrosRelatorio.put( PARAMETRO_LOCALE_RELATORIO, new Locale("pt","BR"));								
			}
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
					parametrosRelatorio, relatorioDataSource);

			// Verifica qual o tipo de relatório para definir a geração
			switch (tipoSaidaRelatorio) {
			case TIPO_PDF:

				JRPdfExporter exporterPDF = new JRPdfExporter();
				exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,
						retorno);
				exporterPDF.exportReport();

				break;
			case TIPO_RTF:

				JRRtfExporter exporterRTF = new JRRtfExporter();
				exporterRTF.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporterRTF.setParameter(JRExporterParameter.OUTPUT_STREAM,
						retorno);
				exporterRTF.exportReport();

				break;
			case TIPO_XLS:

				JRXlsExporter exporterXLS = new JRXlsExporter();
				exporterXLS.setParameter(JRExporterParameter.JASPER_PRINT,
						jasperPrint);
				exporterXLS.setParameter(JRExporterParameter.OUTPUT_STREAM,
						retorno);
				
				exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
				exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);
				exporterXLS.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, true);

				exporterXLS.exportReport();

				break;
			case TIPO_HTML:

				// Para evitar problemas de concorrência, o nome do arquivo html
				// gerado possui um número aleatório no nome
				int numeroNomeRelatorio = new Double((Math.random() * 10000))
						.intValue();
				JasperExportManager.exportReportToHtmlFile(jasperPrint,
						"relatorio" + numeroNomeRelatorio + ".html");

				// pegar o arquivo, zipar pasta e arquivo e escrever no stream
				// criar o arquivo zip
				File arquivoZip = File.createTempFile("zipHtml"
						+ numeroNomeRelatorio, ".zip");

				ZipOutputStream zos = new ZipOutputStream(
						new FileOutputStream(arquivoZip));

				// Pega o arquivo gerado do relatório
				File pagina = new File("relatorio" + numeroNomeRelatorio
						+ ".html");
				// Pega a pasta que acompanha o arquivo do relatório gerado
				File pastaDeImagens = new File("relatorio"
						+ numeroNomeRelatorio + ".html_files");
				ZipUtil.adicionarArquivo(zos, pagina);
				ZipUtil.adicionarPasta(pastaDeImagens, zos);
				// close the stream
				zos.close();

				FileInputStream inputStream = new FileInputStream(
						arquivoZip);

				int INPUT_BUFFER_SIZE = 1024;
				byte[] temp = new byte[INPUT_BUFFER_SIZE];
				int numBytesRead = 0;

				while ((numBytesRead = inputStream.read(temp, 0,
						INPUT_BUFFER_SIZE)) != -1) {
					retorno.write(temp, 0, numBytesRead);
				}

				inputStream.close();
				inputStream = null;

				// Apaga todo o conteúdo gerado
				pagina.delete();
				IoUtil.deleteDir(pastaDeImagens);
				arquivoZip.delete();

				break;
			default:
				throw new RelatorioVazioException(
						"Escolha um tipo de relatório");

			}

			retornoArray = retorno.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SistemaException(e, "Erro ao criar relatório");
		}finally{
			if (stream != null) {
				try {
					stream.close();					
				} catch (IOException e) {
					e.printStackTrace();
					throw new SistemaException(e, "Erro ao fechar relatório");
				}
			}
		}

		return retornoArray;
	}

	public TarefaRelatorio(Usuario usuario, String nomeRelatorio) {

		super(usuario, ConstantesSistema.NUMERO_NAO_INFORMADO);
		this.nomeRelatorio = nomeRelatorio;
	}

	public abstract int calcularTotalRegistrosRelatorio();

	public String getNomeRelatorio() {
		return nomeRelatorio;
	}

	public void persistirRelatorioConcluido(byte[] relatorioConcluido,
			int idRelatorio, int idFuncionalidadeIniciada)
			throws ControladorException {

		if (idFuncionalidadeIniciada != 0
				&& idFuncionalidadeIniciada != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			RelatorioGerado relatorioGerado = new RelatorioGerado();
			relatorioGerado.setArquivoRelatorio(relatorioConcluido);
			relatorioGerado.setNumeroPaginas(0);
			relatorioGerado.setUltimaAlteracao(new Date());

			Relatorio relatorio = new Relatorio();
			relatorio.setId(idRelatorio);

			relatorioGerado.setRelatorio(relatorio);

			FuncionalidadeIniciada funcionalidadeIniciada = new FuncionalidadeIniciada();
			funcionalidadeIniciada.setId(idFuncionalidadeIniciada);
			relatorioGerado.setFuncionalidadeIniciada(funcionalidadeIniciada);

			getControladorUtil().inserir(relatorioGerado);
		}

	}

	public final void execute(JobExecutionContext arg0)
			throws JobExecutionException {

		Integer idFuncionalidadeIniciada = (Integer) arg0.getJobDetail()
				.getJobDataMap().get("idFuncionalidadeIniciada");

		try {

			// ------------------------------------
			// ------------------------------------
			// Inicia a Funcionalidade Iniciada para execução do relatório
			// ------------------------------------
			// ------------------------------------
			if (idFuncionalidadeIniciada != null) {
				getControladorBatch().iniciarFuncionalidadeIniciadaRelatorio(
						idFuncionalidadeIniciada);
			}

			this.setIdFuncionalidadeIniciada(idFuncionalidadeIniciada);
			this.setUsuario((Usuario) arg0.getJobDetail().getJobDataMap().get(
					"usuario"));
			this.setParametroTarefa((Set) arg0.getJobDetail().getJobDataMap()
					.get("parametroTarefa"));
			this.executar();

			// ------------------------------------
			// ------------------------------------
			// Encerra a Funcionalidade Iniciada
			// ------------------------------------
			// ------------------------------------
			if (idFuncionalidadeIniciada != null) {
				getControladorBatch().encerrarFuncionalidadeIniciadaRelatorio(
						idFuncionalidadeIniciada, false);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// ------------------------------------
			// ------------------------------------
			// Encerra a Funcionalidade Iniciada indicando erro
			// ------------------------------------
			// ------------------------------------
			if (idFuncionalidadeIniciada != null) {
				try {
					getControladorBatch()
							.encerrarFuncionalidadeIniciadaRelatorio(
									idFuncionalidadeIniciada, true);
				} catch (ControladorException e1) {

					e1.printStackTrace();
				}
			}

		}

	}

	private ControladorUtilLocal getControladorUtil() {

		ControladorUtilLocalHome localHome = null;
		ControladorUtilLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorUtilLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_UTIL_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas à
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}

	private ControladorBatchLocal getControladorBatch() {
		ControladorBatchLocalHome localHome = null;
		ControladorBatchLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorBatchLocalHome) locator
					.getLocalHome(ConstantesJNDI.CONTROLADOR_BATCH_SEJB);
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}

	}
	
	public void exibirRelatorio(HttpServletResponse response, byte[] dados){
		response.addHeader("Content-Disposition","attachment; filename=relatorio.pdf");
		String mimeType = "application/pdf";
		response.setContentType(mimeType);
		OutputStream out;
		try {
			out = response.getOutputStream();
			out.write(dados);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
