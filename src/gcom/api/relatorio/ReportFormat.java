package gcom.api.relatorio;

public enum ReportFormat {
	PDF("application/pdf"), XLS("application/vnd.ms-excel");
	
	String contentType;

	ReportFormat(String type){
		contentType = type;
	}

	public String getContentType() {
		return contentType;
	}
}