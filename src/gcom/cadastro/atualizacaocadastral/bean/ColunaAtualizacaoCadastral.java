package gcom.cadastro.atualizacaocadastral.bean;

import java.io.Serializable;

public class ColunaAtualizacaoCadastral implements Serializable{
	private static final long serialVersionUID = -8035909929971349295L;

	private String nomeColuna;
	
	private String valorAnterior;

	private String valorTransmitido;
	
	private String valorRevisado;
	
	private String valorFiscalizado;
	
	private String complemento;
	
	public String getNomeColuna() {
		return nomeColuna;
	}

	public void setNomeColuna(String nomeColuna) {
		this.nomeColuna = nomeColuna;
	}
	
	public String getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(String valorColuna) {
		this.valorAnterior = valorColuna;
	}
	
	public String getValorTransmitido() {
		return valorTransmitido;
	}

	public void setValorTransmitido(String valorTransmitido) {
		this.valorTransmitido = valorTransmitido;
	}

	public String getValorRevisado() {
		return valorRevisado;
	}

	public void setValorRevisado(String valorRevisado) {
		this.valorRevisado = valorRevisado;
	}

	public String getValorFiscalizado() {
		return valorFiscalizado;
	}

	public void setValorFiscalizado(String valorFiscalizado) {
		this.valorFiscalizado = valorFiscalizado;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String toString() {
		return "ColunaAtualizacaoCadastral [nomeColuna=" + nomeColuna + "]";
	}
}
