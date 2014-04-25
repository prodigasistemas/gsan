package gcom.relatorio;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe que recupera do arquivo .properties os valores máximos que um
 * relatório pode apresentar em uma exibição online
 * 
 * @author Rodrigo Silveira, Thiago Toscano
 * @date 25/05/2006
 */
public class ConstantesExecucaoRelatorios {
	
	public static int QUANTIDADE_NAO_INFORMADA = -1;

	// Nome do Arquivo de propriedades
	private final static String NOME_ARQUIVO_PROPRIEDADES;

	// guarda as constantes contidas no
	// constantes_execucao_relatorios.properties juntamente com
	// seus valores
	private static Properties propriedades = null;

	// inicialização estática
	static {
		// Nome do Arquivo de propriedades
		NOME_ARQUIVO_PROPRIEDADES = "/constantes_execucao_relatorios.properties";
		loadResources();
	}

	/**
	 * Este método retorna o valor da constante passada como parâmetro.
	 * 
	 * @param key
	 *            Nome da constante
	 * @return Descrição do retorno
	 */

	public static int get(String key) {
		
		int retorno = QUANTIDADE_NAO_INFORMADA;

		try {
			retorno = Integer.parseInt(propriedades.getProperty(key));
		} catch (Exception e) {
		}

		return retorno;
	}

	/**
	 * Carrega o arquivo de properties do sistema
	 */
	private static void loadResources() {
		propriedades = new Properties();

		InputStream stream;

		try {

			ClassLoader classLoader = ClassLoader.getSystemClassLoader();

			stream = classLoader.getResourceAsStream(NOME_ARQUIVO_PROPRIEDADES);

			// if system class loader not found try the this class classLoader
			if (stream == null) {
				stream = gcom.util.ConstantesAplicacao.class.getClassLoader()
						.getResourceAsStream(NOME_ARQUIVO_PROPRIEDADES);
			}

			if (stream == null) {
				stream = gcom.util.ConstantesAplicacao.class
						.getResourceAsStream(NOME_ARQUIVO_PROPRIEDADES);
			}

			propriedades.load(stream);

		} catch (IOException e) {
			e.printStackTrace();
			System.err
					.println("Nao foi possivel localizar o arquivo de propriedades. Certifique-se "
							+ "de que o arquivo "
							+ NOME_ARQUIVO_PROPRIEDADES
							+ " esteja na raiz do CLASSPATH");

		}

	}

}
