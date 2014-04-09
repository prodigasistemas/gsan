package gcom.relatorio.cadastro;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

/**
 * [UC0912] Gerar Boletim de Custo Atualização Cadastral
 * @author Vivianne Sousa
 * @date 30/06/2009
 */
public class RelatorioBoletimCustoAtualizacaoCadastralBean implements RelatorioBean {
	
	private String itemAtributoGrupo;
	private String descricaoAtributoGrupo;
	
	private String itemAtributo;
	private String descricaoAtributo;
	private BigDecimal vlAtualizacao;
	
	private Integer qtdeAtualizacoes;
	
	public RelatorioBoletimCustoAtualizacaoCadastralBean(
			String itemAtributoGrupo,
			String descricaoAtributoGrupo,
			String itemAtributo,
			String descricaoAtributo,
			BigDecimal vlAtualizacao,
			Integer qtdeAtualizacoes) {
	    	
			this.itemAtributoGrupo = itemAtributoGrupo;
			this.descricaoAtributoGrupo = descricaoAtributoGrupo;
			this.itemAtributo = itemAtributo;
			this.descricaoAtributo = descricaoAtributo;
			this.vlAtualizacao = vlAtualizacao;
			this.qtdeAtualizacoes = qtdeAtualizacoes;
	    	
	}

	public String getDescricaoAtributo() {
		return descricaoAtributo;
	}

	public void setDescricaoAtributo(String descricaoAtributo) {
		this.descricaoAtributo = descricaoAtributo;
	}

	public String getDescricaoAtributoGrupo() {
		return descricaoAtributoGrupo;
	}

	public void setDescricaoAtributoGrupo(String descricaoAtributoGrupo) {
		this.descricaoAtributoGrupo = descricaoAtributoGrupo;
	}

	public String getItemAtributo() {
		return itemAtributo;
	}

	public void setItemAtributo(String itemAtributo) {
		this.itemAtributo = itemAtributo;
	}

	public String getItemAtributoGrupo() {
		return itemAtributoGrupo;
	}

	public void setItemAtributoGrupo(String itemAtributoGrupo) {
		this.itemAtributoGrupo = itemAtributoGrupo;
	}

	public BigDecimal getVlAtualizacao() {
		return vlAtualizacao;
	}

	public void setVlAtualizacao(BigDecimal vlAtualizacao) {
		this.vlAtualizacao = vlAtualizacao;
	}

	public Integer getQtdeAtualizacoes() {
		return qtdeAtualizacoes;
	}

	public void setQtdeAtualizacoes(Integer qtdeAtualizacoes) {
		this.qtdeAtualizacoes = qtdeAtualizacoes;
	}
	
}
