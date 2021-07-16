package gcom.util.email;

public class ModeloEmailVencimento {

	private static String NOME_CLIENTE = "NOME_CLIENTE";
	
	private static String CABECALHO_PAGINA = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">"
			+ "<html>" 
			+ "<head>" 
			+ "<title>Untitled Document</title>"
			+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">" 
			+ "</head>"; 
	
	private static String CORPO_EMAIL = "<body>"
			+ "<div class=\"page-wrap\">"
			+ "<div class=\"container pagina\">"
			+ "<div class=\"pagina-titulo\">"
			+ "<h1>Sua fatura est&aacute; pr&oacute;xima do vencimento. </h1>" 
			+ "<h2>Ol&aacute; <b>NOME_CLIENTE</b></h2>" 
			+ "<p>Faltam 15 dias para o vencimento da sua fatura. Se voc&ecirc; j&aacute; efetuou o pagamento, " 
			+ " por favor, desconsidere essa mensagem. Voc&ecirc; pode efetuar o pagamento utilizando o " 
			+ " boleto que anexamos nesse email.</p>"
			+ " <p>Voc&ecirc; pode pagar o boleto no seu internet banking, em lot&eacute;ricas, ag&ecirc;ncias banc&aacute;rias " 
			+ " ou em caixas eletr&ocirc;nicos.</p>"
			+ "<p>Se voc&ecirc; tiver o DDA (D&eacute;bito Direto Autorizado) cadastrado em seu banco, poder&aacute; " 
			+ " consultar, agendar e pagar este boleto utilizando esse servi&ccedil;o.</p>"
			+ "<p>A COSANPA agradece sua aten&ccedil;&atilde;o!</p>";
	
	private static String RODAPE_EMAIL = "<footer>"  
			+ "<div class=\"container\">"  
			+ "<div class=\"row\">" 
			+ "<div class=\"col-sm-4\">" 
			+ "<h2>Companhia de Saneamento do Par&aacute;</h2>" 
			+ "<h3><b>Endereço:</b> Av. Magalhães Barata, 1201 - São Br&aacute;s. CEP: 66060-901 - Belém - Par&aacute;</h3>"  
			+ "<h3><b>Telefone:</b> (91) 3202-8400 / <b>Fax:</b> (91) 3236-2199</h3>"  
			+ "</div>" 
			+ "<div class=\"col-sm-3\">"  
			+ "<h5>A Empresa</h5>"  
			+ "<ul>" 
			+ "<li><a href=\"http://www.cosanpa.pa.gov.br/index.php/a-empresa/2013-06-20-08-51-26\" target=\"_blank\">Hist&oacute;rico</a></li>" 
			+ "<li><a href=\"http://www.cosanpa.pa.gov.br/index.php/acesso-a-informacao/2015-11-12-19-08-37\" target=\"_blank\">Institucional</a></li>"  
			+ "<li><a href=\"http://www.cosanpa.pa.gov.br/index.php/a-empresa/diretoria\"target=\"_blank\">Diretoria</a></li>"  
			+ "</ul>"  
			+ "</div>" 
			+ "<div class=\"col-sm-3\">" 
			+ "<h5>Acesso à Informações</h5>"  
			+ "<ul>" 
			+ "<li><a href=\"http://www.cosanpa.pa.gov.br/index.php/acesso-a-informacao/sobre-a-lai\"target=\"_blank\">Sobre a LAI</a></li>" 
			+ "<li><a href=\"http://www.cosanpa.pa.gov.br/index.php/editais-pregoes/editais-pregoes\"target=\"_blank\">Editais e Pregões</a></li>"  
			+ "<li><a href=\"http://www.cosanpa.pa.gov.br/index.php/a-empresa/downloaddocumentos\"target=\"_blank\">Downloads</a></li>"  
			+ "</ul>"  
			+ "</div>" 
			+ "<div class=\"col-sm-2\">" 
			+ "<div class=\"social-networks\">" 
			+ "<a href=\"https://twitter.com/cosanpaoficial\" class=\"twitter\" target=\"_blank\"><i class=\"fa fa-twitter\"></i></a>" 
			+ "<a href=\"https://www.facebook.com/cosanpa.pa\" class=\"facebook\" target=\"_blank\"><i class=\"fa fa-facebook-official\"></i></a>"  
			+ "</div>"  
			+ "</div>"  
			+ "</div>"  
			+ "</div>" 
			+ "<div class=\"footer-copyright\">" 
			+ "<p>© 2017 - COSANPA. Todos os direitos reservados.</p>"  
			+ "</div>"  
			+ "	</footer>";
	
	private static String RODAPE_PAGINA = "<div><p>\"Esta é uma mensagem autom&aacute;tica, este email não deve ser respondido.\"</p></div></body></html>";
	
	public static String getMensagem(String nomeCliente) {
		StringBuilder texto = new StringBuilder();
		
		texto.append(CABECALHO_PAGINA)
			.append(CORPO_EMAIL)
			.append(RODAPE_EMAIL)
			.append(RODAPE_PAGINA);
		
		return texto.toString().replace(NOME_CLIENTE, nomeCliente);
	}
}
