package gcom.util.email;

public class ModeloEmailVencimento {

	private static String NOME_CLIENTE = "NOME_CLIENTE";
	
	private static String CABECALHO_PAGINA = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">"
			+ "<html>" 
			+ "<head>" 
			+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">" 
			+ "</head>"; 
	
	private static String CORPO_EMAIL = "<body>"
			+ "<div >"
			+ "<div style='font-size: 20px; font-family: Helvetica; color: MidnightBlue; repeat-y 0 0; display:table; " 
			+ " margin:35px auto 0; padding:0 5px; width:950px'>"
			+ "<div style=' background-color:MidnightBlue; color: white'; width:100 px; height:100 px;'><br><br>"
			+ "<br><h1>Sua fatura est&aacute; pr&oacute;xima do vencimento. </h1></div>" 
			+ "<h2>Ol&aacute; <b>NOME_CLIENTE</b></h2><br>" 
			+ "<p style=\"font-size: 24px;\">Faltam 15 dias para o vencimento da sua fatura. Voc&ecirc; pode efetuar o " 
			+ " pagamento utilizando o boleto que anexamos nesse email.</p> <br>"
			+ " <p><b>Se voc&ecirc; j&aacute; efetuou o pagamento, por favor, desconsidere essa mensagem.</b></p><br>"  
			+ " <p>Voc&ecirc; pode pagar o boleto no seu internet banking, em lot&eacute;ricas, ag&ecirc;ncias banc&aacute;rias " 
			+ " ou em caixas eletr&ocirc;nicos. Se voc&ecirc; tiver o DDA (D&eacute;bito Direto Autorizado) cadastrado em seu " 
			+ " banco, poder&aacute; consultar, agendar e pagar este boleto utilizando esse servi&ccedil;o.</p><br><br>"
			+ " <p>A COSANPA agradece sua aten&ccedil;&atilde;o!</p>";
	
	private static String RODAPE_EMAIL = "<footer>"  
			+ "<div class=\"container\">"  
			+ "<div class=\"row\">" 
			+ "<div class=\"col-sm-4\">" 
			+ "<h3>Companhia de Saneamento do Par&aacute;</h3>" 
			+ "<h3><b>Endere&ccedil;o:</b> Av. Magalh&atilde;es Barata, 1201 - S&atilde;o Br&aacute;s. CEP: 66060-901 - Bel&eacute;m - Par&aacute;</h3>"  
			+ "<h3><b>Telefone:</b> (91) 3202-8400 / <b>Fax:</b> (91) 3236-2199</h3>"  
			+ "</div>"  
			+ "</div>"  
			+ "</div>" 
			+ "</div>"  
			+ "	</footer>";
	
	private static String RODAPE_PAGINA = "<div><p>\"Esta &eacute; uma mensagem autom&aacute;tica, este email n&atilde;o deve ser respondido.\"</p></div></body></html>";
	
	public static String getMensagem(String nomeCliente) {
		StringBuilder texto = new StringBuilder();
		
		texto.append(CABECALHO_PAGINA)
			.append(CORPO_EMAIL)
			.append(RODAPE_EMAIL)
			.append(RODAPE_PAGINA);
		
		return texto.toString().replace(NOME_CLIENTE, nomeCliente);
	}
}
