package gcom.gui.cadastro.atualizacaocadastral;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImovelRelatorioAtualizacaoCadastral implements Serializable{
	private static final long serialVersionUID = 7062418545895587657L;
	
	private String descImovel;
	
	private List<AlteracaoImovelRelatorioAtualizacaoCadastral> alteracoes = new ArrayList<AlteracaoImovelRelatorioAtualizacaoCadastral>();

	public String getDescImovel() {
		return descImovel;
	}

	public void setDescImovel(String descImovel) {
		this.descImovel = descImovel;
	}

	public List<AlteracaoImovelRelatorioAtualizacaoCadastral> getAlteracoes() {
		return alteracoes;
	}

	public void addAlteracao(AlteracaoImovelRelatorioAtualizacaoCadastral alteracao) {
		this.alteracoes.add(alteracao);
	}

	public String toString() {
		return "ImovelRelatorioAtualizacaoCadastral [descImovel=" + descImovel + ", alteracoes=" + alteracoes + "]";
	}
}
