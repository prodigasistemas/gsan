package gcom.gui.cadastro.atualizacaocadastral;

import gcom.relatorio.RelatorioBean;

import java.util.ArrayList;
import java.util.Collection;

public class ImovelRelatorioAtualizacaoCadastral implements RelatorioBean{
	private String descImovel;
	
	private Collection<AlteracaoImovelRelatorioAtualizacaoCadastral> alteracoes = new ArrayList<AlteracaoImovelRelatorioAtualizacaoCadastral>();

	public String getDescImovel() {
		return descImovel;
	}

	public void setDescImovel(String descImovel) {
		this.descImovel = descImovel;
	}

	public Collection<AlteracaoImovelRelatorioAtualizacaoCadastral> getAlteracoes() {
		return alteracoes;
	}

	public void addAlteracao(AlteracaoImovelRelatorioAtualizacaoCadastral alteracao) {
		this.alteracoes.add(alteracao);
	}

	public String toString() {
		return "ImovelRelatorioAtualizacaoCadastral [descImovel=" + descImovel + ", alteracoes=" + alteracoes + "]";
	}
}
