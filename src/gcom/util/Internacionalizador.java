package gcom.util;

import java.util.Locale;

import org.apache.struts.util.PropertyMessageResources;

/**
 *Classe criada para traduzir chaves do properties de internacionalização
 *no seus valores correspondentes.
 *
 * @author Marlon Patrick
 * @since 14/10/2009
 */
public class Internacionalizador {
		
	private static PropertyMessageResources properties;

	private static Locale locale;

	public static Locale getLocale() {
		return locale;
	}

	public static void setLocale(Locale locale) {
		Internacionalizador.locale = locale;
	}

	public static void setProperties(PropertyMessageResources properties) {
		Internacionalizador.properties = properties;
	}

	public static PropertyMessageResources getProperties() {
		return properties;
	}

	/**
	 * Retorna a mensagem correspondente a chave passada como parâmetro.
	 *
	 *@since 14/10/2009
	 *@author Marlon Patrick
	 */
	public static String getMensagem(String chaveMsg){
		return properties.getMessage(locale,chaveMsg);
	}

	/**
	 *Obtem a mensagem correspondente a chave do parametro e a usa como parametro
	 *na mensagem principal. Retorna a mensagem principal.
	 *
	 *@since 14/10/2009
	 *@author Marlon Patrick
	 */
	public static String getMensagem(String chaveMsg,String chaveParametro){		
		return properties.getMessage(locale,chaveMsg,properties.getMessage(locale,chaveParametro));
	}

	/**
	 *Obtem as mensagens correspondentes a cada uma das chaves parametro e as usa como parametros
	 *na mensagem principal. Retorna a mensagem principal. 
	 *
	 *@since 14/10/2009
	 *@author Marlon Patrick
	 */
	public static String getMensagem(String chaveMsg,String... chavesParametros){		
		String[] msgParametros = new String[chavesParametros.length];
		
		for (int i = 0; i < chavesParametros.length; i++) {
			msgParametros[i] = properties.getMessage(locale,chavesParametros[i]);			
		}
		
		return properties.getMessage(locale,chaveMsg,msgParametros);
	}

}
