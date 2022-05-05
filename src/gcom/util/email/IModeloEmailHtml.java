package gcom.util.email;

public interface IModeloEmailHtml {

	static String CABECALHO_PAGINA = "";
	static String CORPO_EMAIL = "";
	static String RODAPE_PAGINA = "";
	
	abstract String getMensagem(String nomeCliente);
}
