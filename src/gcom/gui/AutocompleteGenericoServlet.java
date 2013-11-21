package gcom.gui;

import gcom.fachada.Fachada;
import gcom.util.Util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.StringCharacterIterator;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutocompleteGenericoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final int CLIENTE = 1;
	private static final int USUARIO = 2;
	private static final int CLIENTE_RESPONSAVEL = 3;

	private Fachada fachada = Fachada.getInstancia();
	
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String valor = req.getParameter("q");
		String method = req.getParameter("method");
		
		PrintWriter print = resp.getWriter();
		boolean numerico = Util.validarStringNumerica(method);

		//Valida se o parâmetro method possui valor válido
		if(numerico){
			
			switch (Integer.parseInt(method)) {
			
			case AutocompleteGenericoServlet.CLIENTE:
				filtrarAutocompleteCliente(print, valor);
				break;
				
			case AutocompleteGenericoServlet.USUARIO:
				filtrarAutocompleteUsuario(print, valor);
				break;	
				
			case AutocompleteGenericoServlet.CLIENTE_RESPONSAVEL:
				filtrarAutocompleteClienteResponsavel(print, valor);
				break;	
				
			default:
				break;
			}
		}
	}
	
	//Inicio dos metodos privados
	private void filtrarAutocompleteCliente(PrintWriter print, String valor){
		Collection coll = fachada.filtrarAutocompleteCliente(valor);
		System.out.println(coll.size());
		print.println("[");
		int indice = 1;
		for (Object object : coll) {
			Object[] cliente = (Object[]) object;
			String cnpj = "";
			if(cliente[1] != null && !cliente[1].equals("")){
				cnpj = Util.formatarCnpj(cliente[1] + "");
			}
			if(indice < coll.size()){
				print.println("{ resultado: '" + cliente[0] + " - " + encodeCaracteresEspeciaisJSON(cliente[2].toString()) +"' , identificador: "+cliente[3]+", cnpj: '"+cnpj+ "' },");
			}else{
				print.println("{ resultado: '" + cliente[0] + " - " + encodeCaracteresEspeciaisJSON(cliente[2].toString()) +"' , identificador: "+cliente[3]+", cnpj: '"+cnpj+ "' }");
			}
			indice++;
		}
		print.println("]");
	}
	
	private void filtrarAutocompleteClienteResponsavel(PrintWriter print, String valor){
		Collection coll = fachada.filtrarAutocompleteClienteResponsavel(valor);
		System.out.println(coll.size());
		print.println("[");
		int indice = 1;
		for (Object object : coll) {
			Object[] cliente = (Object[]) object;
			String cnpj = "";
			if(cliente[1] != null && !cliente[1].equals("")){
				cnpj = Util.formatarCnpj(cliente[1] + "");
			}
			
			if(indice < coll.size()){
				print.println("{ resultado: '" + cliente[0] + " - " + encodeCaracteresEspeciaisJSON(cliente[2].toString()) +"' , identificador: "+cliente[3]+", cnpj: '"+cnpj+ "' },");
			}else{
				print.println("{ resultado: '" + cliente[0] + " - " + encodeCaracteresEspeciaisJSON(cliente[2].toString()) +"' , identificador: "+cliente[3]+", cnpj: '"+cnpj+ "' }");
			}
			indice++;
		}
		print.println("]");
	}
	
	private void filtrarAutocompleteUsuario(PrintWriter print, String valor){
		Collection coll = fachada.filtrarAutocompleteUsuario(valor);
		System.out.println(coll.size());
		for (Object object : coll) {
			Object[] usuario = (Object[]) object;
			print.println(usuario[0] + " - " + encodeCaracteresEspeciaisJSON(usuario[1].toString()));
		}
	}
	
	private static String encodeCaracteresEspeciaisJSON(String aText){
		    final StringBuilder result = new StringBuilder();
		    StringCharacterIterator iterator = new StringCharacterIterator(aText);
		    char character = iterator.current();
		    while (character != StringCharacterIterator.DONE){
		      if( character == '\"' ){
		        result.append("\\\"");
		      }
		      else if(character == '\\'){
		        result.append("\\\\");
		      }
		      else if(character == '/'){
		        result.append("\\/");
		      }
		      else if(character == '\b'){
		        result.append("\\b");
		      }
		      else if(character == '\f'){
		        result.append("\\f");
		      }
		      else if(character == '\n'){
		        result.append("\\n");
		      }
		      else if(character == '\r'){
		        result.append("\\r");
		      }
		      else if(character == '\t'){
		        result.append("\\t");
		      }else if(character == '\''){
		    	  result.append("\\'");
		      }
		      else {
		        //the char is not a special one
		        //add it to the result as is
		        result.append(character);
		      }
		      character = iterator.next();
		    }
		    return result.toString();    
		  }
	
	

}
