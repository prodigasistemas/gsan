package gcom.util.email;

public class ModeloEmailConfirmacao {
	
	private static String NOME_CLIENTE = "NOME_CLIENTE";


	
	private static String CABECALHO_PAGINA = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">" + 
			"<html>" + 
			"<head> " + 
			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">" + 
			"</head>";
	
	private static String CORPO_EMAIL = "<body>" + 
			"<div>" + 
			"	<div style='font-size: 20px; font-family: Helvetica; color: MidnightBlue; repeat-y 0 0; display:table; margin:35px auto 0; padding:0 5px; width:950px	'>" + 
			"		<div style=' background-color:MidnightBlue; color: white'; width:100 px; height:100 px;'><br><br>" + 
			"			<img src=\"http://www.cosanpa.pa.gov.br/wp-content/themes/cosanpa2019/img/logo-cosanpa.png\" width=\"50%\" height=\"50%\">" + 
			"		<br><br>" + 
			"		</div> " + 
			"		<h1>Cadastro efetuado com sucesso! </h1><br>" + 
			"		<h2>Ol&aacute; <b>NOME_CLIENTE</b></h2><br>" + 
			"		<p style=\"font-size: 24px;\">Seu cadastro foi efetuado com sucesso e est&aacute; passando por um processo de an&aacute;lise. Estaremos retornando, em breve, o resultado para o e-mail cadastrado.</p><br>" + 
			"		<p style=\"color: MidnightBlue; font-size: 20px;\"><b>Se voc&ecirc; j&aacute; efetuou o pagamento,  " + 
			"		 por favor, desconsidere essa mensagem.</b></p>" + 
			"		 <br>" + 
			"		 <p style=\"color: MidnightBlue; font-size: 20px;\">Voc&ecirc; pode pagar o boleto no seu internet banking, em lot&eacute;ricas, ag&ecirc;ncias banc&aacute;rias ou em caixas eletr&ocirc;nicos. Se voc&ecirc; tiver o DDA (D&eacute;bito Direto Autorizado) cadastrado em seu banco, poder&aacute;" + 
			"		 consultar, agendar e pagar este boleto utilizando esse servi&ccedil;o.</p>" + 
			"		 <br>" + 
			"		<p style=\"color: MidnightBlue; font-size: 20px;\">A COSANPA agradece sua aten&ccedil;&atilde;o!</p>" + 
			"		<br>" + 
			"	" + 
			"	<div><p>\"Esta &eacute; uma mensagem autom&aacute;tica, este email n&atilde;o deve ser respondido.\"</p></div>";
	
	private static String RODAPE_PAGINA = "<footer>" + 
			"		<div style='background-color:MidnightBlue; color: white'; width:100 px; height:100 px; align=\"center\"><br><br>" + 
			"			<img src=\"http://www.cosanpa.pa.gov.br/wp-content/themes/cosanpa2019/img/logo-governo-branco.png\" width=\"25%\" height=\"25%\">	" + 
			"					<div class=\"col-sm-4\">" + 
			"						<h5>Companhia de Saneamento do Par&aacute;<br>" + 
			"						 <b>Endere&ccedil;o:</b> Av. Magalh&atilde;es Barata, 1201 - S&atilde;o Br&aacute;s. CEP: 66060-901 - Bel&eacute;m - Par&aacute;<br>" + 
			"						<b>Telefone:</b> (91) 3202-8400 / <b>Fax:</b> (91) 3236-2199</h5>" + 
			"					</div>" + 
			"		</div> " + 
			"		</footer> <br><br>" + 
			"	</div>			" + 
			"</div>" + 
			"</body>" + 
			"</html:html>";
	

	public static String getMensagem(String nomeCliente) {

		StringBuilder texto = new StringBuilder();
		
		texto.append(CABECALHO_PAGINA)
			.append(CORPO_EMAIL)
			.append(RODAPE_PAGINA);
		
		return texto.toString().replace(NOME_CLIENTE, nomeCliente);
	}
}

