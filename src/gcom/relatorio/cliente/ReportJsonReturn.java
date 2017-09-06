package gcom.relatorio.cliente;

import java.io.Serializable;

public class ReportJsonReturn implements Serializable{
	private static final long serialVersionUID = 8636380455283643802L;

	private String url;
	
	private String erro;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}
	
	public boolean temErro(){
		return erro != null && erro.length() > 0;
	}

	public String toString() {
		return "ReportJsonReturn [url=" + url + ", erro=" + erro + "]";
	}
}
