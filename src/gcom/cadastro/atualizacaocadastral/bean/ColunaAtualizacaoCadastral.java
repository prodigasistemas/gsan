package gcom.cadastro.atualizacaocadastral.bean;

import java.io.Serializable;

public class ColunaAtualizacaoCadastral implements Serializable{
	private static final long serialVersionUID = -8035909929971349295L;

	private String nomeColuna;
	
	private String valorAnterior;

	private String valorAtual;
	
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
	
	public String getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(String valorAtual) {
		this.valorAtual = valorAtual;
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
