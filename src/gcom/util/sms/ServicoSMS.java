package gcom.util.sms;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpResponse;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import gcom.util.IoUtil;

public class ServicoSMS {

	public final static String NOME_ARQUIVO_PROPRIEDADES = "/properties/sms.properties";
	
	private static String API_KEY = "5JYWC9EUAVWLENBLHKZEH4HPARKJNTNMZ2J0FLROKN753I3DNQG0E6BGBJGZ22G9XGLU82A276JLNK3YI1K10Z2AL0ZHN8YH4SZCLEFVX0L1RFFTK0022JKIHKCWA9JB";
	private static String URL = "URL";
	
	public static String MSG_VENCIMENTO = "MSG_VENCIMENTO";
	public static String MSG_CORTE = "MSG_CORTE";
	
	public ServicoSMS() {
			
	}
 	
	public static Properties propriedades;
	
	public static void enviarSMS(String telefone, String tipoMensagem) {
		
		propriedades = IoUtil.carregaPropriedades("sms.properties"); 
		
		String json = getJson(telefone, tipoMensagem);
		
		Client client = Client.create();
		
		String endereco = propriedades.getProperty(URL);
		WebResource webResource = client.resource("http://api.smsdev.com.br/v1/send");
		
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, json);
		
		System.out.println("FIM");
	}
	
	private static String getJson(String telefone, String tipoMensagem) {
		StringBuilder str = new StringBuilder();

			str.append("[")
			   .append("{")
				.append("\"key\" : \"" + API_KEY + "\",") 
				.append("\"type\" : 9,") 
				.append("\"number\" : ").append(telefone).append(",") 
				.append("\"msg\" : " + propriedades.getProperty(tipoMensagem))
				.append("}")
				.append("]");
		
		return str.toString();
	}
	
	public static void main(String[] args) {
		ServicoSMS.enviarSMS("91988436943", ServicoSMS.MSG_CORTE);
	}
	
}
	
