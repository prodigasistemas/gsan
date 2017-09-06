package gcom.relatorio.cliente;

public enum FormatoRelatorio {
	PDF("application/pdf"), XLS("application/vnd.ms-excel");
	
	String contentType;

	FormatoRelatorio(String type){
		contentType = type;
	}

	public String getContentType() {
		return contentType;
	}
}
