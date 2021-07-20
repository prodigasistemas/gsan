package gcom.util.email;

public class ModeloEmailVencimento {

	private static String NOME_CLIENTE = "NOME_CLIENTE";
	
	private static String CABECALHO_PAGINA = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">"
			+ "<html>" 
			+ "<head>" 
			+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">" 
			+ "</head>"; 
	
	private static String CORPO_EMAIL = "<body >\r\n" + 
			"	\r\n" + 
			"	<div >\r\n" + 
			"		<div style='font-size: 20px; font-family: Helvetica; color: MidnightBlue; repeat-y 0 0; display:table; margin:35px auto 0; padding:0 5px; width:950px	'>\r\n" + 
			"		\r\n" + 
			"			<div style=' background-color:MidnightBlue; color: white'; width:100 px; height:100 px;'><br><br>\r\n" + 
			"				<img src=\"http://www.cosanpa.pa.gov.br/wp-content/themes/cosanpa2019/img/logo-cosanpa.png\" class=\"img-fluid\" alt=\"COSANPA\" width=\"50%\" height=\"50%\">\r\n" + 
			"			<br><br>\r\n" + 
			"			</div>\r\n" + 
			"\r\n" + 
			"			<h1>Sua fatura est&aacute; pr&oacute;xima do vencimento. </h1><br>\r\n" + 
			"			<h2>Ol&aacute; <b>NOME_CLIENTE</b></h2>\r\n" + 
			"			<p style=\"font-size: 24px;\">Faltam 15 dias para o vencimento da sua fatura. Voc&ecirc; pode efetuar o pagamento utilizando o boleto que anexamos nesse email.</p>\r\n" + 
			"			<br>\r\n" + 
			"			<p><b>Se voc&ecirc; j&aacute; efetuou o pagamento, \r\n" + 
			"			 por favor, desconsidere essa mensagem.</b></p> \r\n" + 
			"			 <br>\r\n" + 
			"			 <p>Voc&ecirc; pode pagar o boleto no seu internet banking, em lot&eacute;ricas, ag&ecirc;ncias banc&aacute;rias ou em caixas eletr&ocirc;nicos. Se voc&ecirc; tiver o DDA (D&eacute;bito Direto Autorizado) cadastrado em seu banco, poder&aacute; \r\n" + 
			"			 consultar, agendar e pagar este boleto utilizando esse servi&ccedil;o.</p>\r\n" + 
			"			 <br>\r\n" + 
			"			<p>A COSANPA agradece sua aten&ccedil;&atilde;o!</p>\r\n" + 
			"			<br>";
	
	private static String RODAPE_EMAIL = "<div><p>\"Esta &eacute; uma mensagem autom&aacute;tica, este email n&atilde;o deve ser respondido.\"</p></div>";
	
	private static String RODAPE_PAGINA = "<footer>\r\n" + 
			"			<div style='background-color:MidnightBlue; color: white'; width:100 px; height:100 px; align=\"center\" '><br><br>\r\n" + 
			"					<img  src=\"http://www.cosanpa.pa.gov.br/wp-content/themes/cosanpa2019/img/logo-governo-branco.png\" alt=\"GOVERNO DO PARÁ\" width=\"25%\" height=\"25%\">	\r\n" + 
			"					  \r\n" + 
			"						<div class=\"col-sm-4\">\r\n" + 
			"							<h5>Companhia de Saneamento do Par&aacute;<br>\r\n" + 
			"							 <b>Endere&ccedil;o:</b> Av. Magalh&atilde;es Barata, 1201 - S&atilde;o Br&aacute;s. CEP: 66060-901 - Beleacute;m - Par&aacute;<br>	\r\n" + 
			"							<b>Telefone:</b> (91) 3202-8400 / <b>Fax:</b> (91) 3236-2199</h5>\r\n" + 
			"						</div>\r\n" + 
			"			</div> \r\n" + 
			"			</footer> <br><br>\r\n" + 
			"		</div>			\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"</body>\r\n" + 
			"</html:html>";
	
	public static String getMensagem(String nomeCliente) {
		StringBuilder texto = new StringBuilder();
		
		texto.append(CABECALHO_PAGINA)
			.append(CORPO_EMAIL)
			.append(RODAPE_EMAIL)
			.append(RODAPE_PAGINA);
		
		return texto.toString().replace(NOME_CLIENTE, nomeCliente);
	}
}
