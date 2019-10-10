package gcom.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import gcom.fachada.Fachada;
import gcom.seguranca.SegurancaParametro;

/**
 * Classe utilitaria com funcoes de manipulacao de imagem
 * 
 * @author Ewertton Bravo
 * @date 02/10/2019
 *
 */
public class ImagemUtil {
	
	private static Logger logger = Logger.getLogger(ImagemUtil.class);
	
	public static final String URL_IMAGEM_NAO_ENCONTRADA = "/nao-encontrada.jpg";
	
	private static String urlServidor = null;

	/**
	 * Monta a URL completa de acesso ao servidor de arquivo
	 * 
	 * 
	 * @param caminho da midia no servidor de arquivos
	 * @return URL completa ate o arquivo
	 */
	public static String montarUrlCompleta(String caminho) {
		if (StringUtils.isEmpty(urlServidor)) {
			urlServidor = Fachada.getInstancia().getSegurancaParametro(
					SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_SERVIDOR_IMAGENS.toString());
		}
		return String.format("%s%s", urlServidor, caminho);
	}
	
	public static InputStream carregarImagemDoServidorDeArquivos(String caminho) {
		try {
			String urlCompleta = montarUrlCompleta(caminho);
			return new URL(urlCompleta).openStream();
		} catch (MalformedURLException e) {
			logger.error("Problemas com a URL para se obter a imagem.", e);
		} catch (FileNotFoundException e) {
			try {
				return new URL(montarUrlCompleta(URL_IMAGEM_NAO_ENCONTRADA)).openStream();
			} catch (MalformedURLException e1) {
				logger.error("Problemas com a URL para se obter a imagem padrao.", e1);
			} catch (IOException e1) {
				logger.error("Ocorreu algum problema ao carregar imagem padrao do servidor de arquivos.", e1);
			}
		} catch (IOException e) {
			logger.error("Ocorreu algum problema ao carregar imagem do servidor de arquivos.", e);
		}
		return null;
	}
	
	/**
	 * Copia todos os dados de um {@code InputStream} para um {@code OutputStream}
	 * 
	 * 
	 * @param inputStream
	 * @param outputStream
	 * @param fechar
	 */
	public static void copiar(InputStream inputStream, OutputStream outputStream, boolean fechar) {
		if (inputStream == null || outputStream == null) {
			return;
		}
		try {
			byte[] buffer = new byte[1024];
			while (true) {
				int bytesRead = inputStream.read(buffer);
				if (bytesRead == -1)
					break;
				outputStream.write(buffer, 0, bytesRead);
			}
			if (fechar) {
				inputStream.close();
				outputStream.close();
			}
		} catch (Exception e) {
			logger.error("Ocorreu algo inexperado ao copiar os bytes do inputStream para outputStream.", e);
		}
	}
	
}
