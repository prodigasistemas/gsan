package gcom.cadastro.atualizacaocadastral.bean;

import java.io.Serializable;

public class ColunaAtualizacaoCadastral implements Serializable{
	private static final long serialVersionUID = -8035909929971349295L;

	private String nomeColuna;
	
	private String valorColuna;
	
	public String getNomeColuna() {
		return nomeColuna;
	}

	public void setNomeColuna(String nomeColuna) {
		this.nomeColuna = nomeColuna;
	}
	
	public String getValorColuna() {
		return valorColuna;
	}

	public void setValorColuna(String valorColuna) {
		this.valorColuna = valorColuna;
	}

	public String toString() {
		return "ColunaAtualizacaoCadastral [nomeColuna=" + nomeColuna + "]";
	}
}
