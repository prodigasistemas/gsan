package gcom.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Esta classe é usada para recuperar a descricao da estrutura do banco.
 * Ex: 
 * cadastro.imovel= Imóvel
 * cadastro.imovel.imov_id = Identificador
 * cadastro.imovel.nome = Nome
 * 
 *  
 *  
 *   @author   Thiago Toscano 
 * 
 */

public class ConstantesDescricaoBanco {

    // Nome do Arquivo de propriedades
    private final static String NOME_ARQUIVO_PROPRIEDADES;

    // guarda as constantes contidas no application.properties juntamente com
    // seus valores
    private static Properties propriedades = null;

    // inicialização estática
    static {
        // Nome do Arquivo de propriedades
        NOME_ARQUIVO_PROPRIEDADES = "/descricaoBanco.properties";
        loadResources();
    }

    /**
     * Este método retorna o valor da constante passada como parâmetro.
     * 
     * @param key
     *            Nome da constante
     * @return Descrição do retorno
     */

    public static String get(String key) {
    	String retorno = null;
    	if (propriedades != null) {
    		retorno = propriedades.getProperty(key);
    	}
    	if (retorno != null && "".equals(retorno.trim())) {
    		retorno = null;
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
        	try {
				new File(NOME_ARQUIVO_PROPRIEDADES).createNewFile();
			} catch (Exception ee) {
			}

        }
    }
}
