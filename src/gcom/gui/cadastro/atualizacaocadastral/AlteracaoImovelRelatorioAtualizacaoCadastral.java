package gcom.gui.cadastro.atualizacaocadastral;

import java.io.Serializable;

public class AlteracaoImovelRelatorioAtualizacaoCadastral implements Serializable{
	private static final long serialVersionUID = -7489943181894459094L;

	private String descricao;
	
	private String valorAnterior;
	
	private String valorAtual;
	
	public AlteracaoImovelRelatorioAtualizacaoCadastral(String descricao, String valorAnterior, String valorAtual) {
		this.descricao = descricao;
		this.valorAnterior = valorAnterior;
		this.valorAtual = valorAtual;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public String getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(String valorAtual) {
		this.valorAtual = valorAtual;
	}

	public String toString() {
		return "AlteracaoImovelRelatorioAtualizacaoCadastral [descricao=" + descricao + ", valorAnterior=" + valorAnterior + ", valorAtual=" + valorAtual + "]";
	}
}
