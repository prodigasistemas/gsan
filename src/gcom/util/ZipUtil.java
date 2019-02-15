package gcom.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * Esta classe reúne funções que manipulam um arquivo zip no sistema
 * 
 * @author Rodrigo Silveira
 * @date 19/05/2006
 */
public class ZipUtil {

	/**
	 * Adiciona o arquivo especificado ao zipOutputStream que representa o
	 * arquivo zip
	 * 
	 * @author Rodrigo Silveira
	 * @date 19/05/2006
	 * 
	 * @param zipFile
	 *            Stream que representa o arquivo zip
	 * @param file
	 *            Arquivo a ser adicionado no arquivo zip
	 * @throws IOException
	 */
	public static void adicionarArquivo(ZipOutputStream zipFile, File file)
			throws IOException {

		FileInputStream inputStream = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int INPUT_BUFFER_SIZE = 1024;
		byte[] temp = new byte[INPUT_BUFFER_SIZE];
		int numBytesRead = 0;

		while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
			baos.write(temp, 0, numBytesRead);
		}

		inputStream.close();
		inputStream = null;

		byte[] data = baos.toByteArray();

		ZipEntry zen = new ZipEntry(file.getName());
		zipFile.putNextEntry(zen);
		zipFile.write(data, 0, data.length);
		zipFile.closeEntry();
	}

	/**
	 * Adiciona um diretório a um arquivo zip especificado
	 * 
	 * @author Rodrigo Silveira
	 * @date 19/05/2006
	 * 
	 * @param dir2zip
	 *            Diretório que será adicionado ao arquivo zip
	 * @param zos
	 *            Stream que representa o arquivo zip
	 * @throws IOException
	 */
	public static void adicionarPasta(File dir2zip, ZipOutputStream zos)
			throws IOException {

		String[] dirList = dir2zip.list();
		byte[] readBuffer = new byte[2156];
		int bytesIn = 0;

		for (int i = 0; i < dirList.length; i++) {
			File f = new File(dir2zip, dirList[i]);
			if (f.isDirectory()) {
				adicionarPasta(f, zos);

				continue;
			}
			FileInputStream fis = new FileInputStream(f);
			ZipEntry anEntry = new ZipEntry(f.getPath());

			zos.putNextEntry(anEntry);

			while ((bytesIn = fis.read(readBuffer)) != -1) {
				zos.write(readBuffer, 0, bytesIn);
			}

			fis.close();
		}

	}
	
	/*
	  Adiciona o arquivo especificado ao zipOutputStream que representa o
	 * arquivo zip
	 * 
	 * @author Rodrigo Silveira
	 * @date 19/05/2006
	 * 
	 * @param zipFile
	 *            Stream que representa o arquivo zip
	 * @param file
	 *            Arquivo a ser adicionado no arquivo zip
	 * @throws IOException
	 */
	public static void adicionarArquivo(GZIPOutputStream zipFile, File file)
			throws IOException {

		FileInputStream inputStream = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int INPUT_BUFFER_SIZE = 1024;
		byte[] temp = new byte[INPUT_BUFFER_SIZE];
		int numBytesRead = 0;

		while ((numBytesRead = inputStream.read(temp, 0, INPUT_BUFFER_SIZE)) != -1) {
			baos.write(temp, 0, numBytesRead);
		}

		inputStream.close();
		inputStream = null;

		byte[] data = baos.toByteArray();

//		ZipEntry zen = new ZipEntry(file.getName());
//		zipFile.putNextEntry(zen);
		zipFile.write(data, 0, data.length);
		zipFile.close();
//		zipFile.closeEntry();
	}

	/*
	 * @TODO - COSANPA
	 * 
	 * Método para descomprimir um arquivo com extensão Gzip (.gz)
	 * 
	 * @author Felipe Santos
	 * 
	 * @date 26/05/2011
	 * 
	 * @param arquivoGz
	 */
	public static File descomprimirGzip(File arquivoGz) {
		File file = null;

		try {
			
			String arquivoEntradaNome = arquivoGz.getAbsolutePath();
			
			// Abre o arquivo comprimido
			GZIPInputStream in = new GZIPInputStream(new FileInputStream(
					arquivoEntradaNome));

			// Abre o arquivo de saída
			String arquivoSaidaNome = arquivoEntradaNome.replace(".gz", "").trim();
			
			OutputStream out = new FileOutputStream(arquivoSaidaNome);			

			// Transfere os bytes do arquivo comprimido para o arquivo de saída
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}

			file = new File(arquivoSaidaNome);
			
			// Fecha os arquivos
			in.close();
			out.close();
			
			//Deleta o arquivo antigo
			arquivoGz.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}
	
	/*
	 * @TODO - COSANPA
	 * 
	 * Método para comprimir um arquivo com extensão Gzip (.gz)
	 * 
	 * @author Felipe Santos
	 * 
	 * @date 26/05/2011
	 * 
	 * @param arquivoGz
	 */
	public static File comprimirGzip(File arquivoOriginal) throws IOException {
		
		String comprimidoTipo = arquivoOriginal.getAbsolutePath()+".gz";
		
		File comprimido = new File(comprimidoTipo);
		
		InputStream is = new FileInputStream(arquivoOriginal);
		GZIPOutputStream gzos = new GZIPOutputStream(new FileOutputStream(
				comprimido));
		byte[] buffer = new byte[16 * 1024];
		for (int nBytesLidos = is.read(buffer); nBytesLidos > 0; nBytesLidos = is
				.read(buffer)) {
			gzos.write(buffer, 0, nBytesLidos);
		}
		
		is.close();
		gzos.close();
		
		return comprimido;
	}
	
	public static void criarZip(InputStream in, String nomeArquivo, String caminho) {
		try {
			byte[] buffer = new byte[1024];
	        
			File pasta = new File(caminho);
			if (!pasta.exists()) {
				pasta.mkdir();
			}
			OutputStream out = new FileOutputStream(new File(pasta.getAbsolutePath() + "/" + nomeArquivo));
			
			int len;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void criarZip(File arquivo, String caminho) {
		try {
			byte[] buffer = new byte[1024];
	        
			File pasta = new File(caminho);
			if (!pasta.exists()) {
				pasta.mkdir();
			}
			
			FileOutputStream fos = new FileOutputStream(pasta.getAbsolutePath() + "/" + arquivo.getName());
			ZipOutputStream zip = new ZipOutputStream(fos);

			escrever(buffer, arquivo, zip);

			zip.closeEntry();
			zip.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void escrever(byte[] buffer, File file, ZipOutputStream zip) throws IOException {
		if (file.exists()) {
			ZipEntry ze = new ZipEntry(file.getName());
			zip.putNextEntry(ze);
			FileInputStream in = new FileInputStream(file);
			int len;
			while ((len = in.read(buffer)) > 0) {
				zip.write(buffer, 0, len);
			}
			in.close();
		}
	}

	public static void adicionarEmZip(ZipOutputStream zip, String nome, byte[] bytes) throws IOException, ClassNotFoundException {
		File temp = new File(nome + ".txt");

		FileOutputStream outputStream = new FileOutputStream(temp);
		outputStream.write(((StringBuilder) IoUtil.transformarBytesParaObjeto(bytes)).toString().getBytes());
		outputStream.close();

		ZipUtil.adicionarArquivo(zip, temp);

		temp.delete();

		zip.closeEntry();
	}
	
	public static void download(HttpServletResponse response, ZipOutputStream zip, String nomeArquivoZIP, File arquivoZIP) throws IOException {
		zip.flush();
		zip.close();

		response.setContentType("application/zip");
		response.addHeader("Content-Disposition", "attachment; filename=" + nomeArquivoZIP + ".zip");

		ServletOutputStream sos = response.getOutputStream();
		sos.write(IoUtil.getBytesFromFile(arquivoZIP));
		sos.flush();
		sos.close();

		arquivoZIP.delete();
	}
	
	public static void salvarArquivoZip(StringBuilder arquivo, String nomeZip, File compactadoTipo, File leituraTipo)
			throws FileNotFoundException, IOException {
		
//		File compactadoTipo = new File(getControladorUtil().getCaminhoDownloadArquivos("faturamento") + nomeZip + ".zip");
//		File leituraTipo = new File(getControladorUtil().getCaminhoDownloadArquivos("faturamento") +nomeZip + ".txt");
		BufferedWriter out = null;
		ZipOutputStream zos = null;

		System.out.println("Gerando arquivo " + leituraTipo.getAbsolutePath().toString());
		if (arquivo != null && arquivo.length() != 0) {
			zos = new ZipOutputStream(new FileOutputStream(compactadoTipo));
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(leituraTipo.getAbsolutePath())));
			out.write(arquivo.toString());
			out.flush();
			ZipUtil.adicionarArquivo(zos, leituraTipo);
			zos.close();
			leituraTipo.delete();
			out.close();
		}
		
		out = null;
		zos = null;
		nomeZip = null;
		compactadoTipo = null;
		leituraTipo = null;
		arquivo = null;
	}
}
