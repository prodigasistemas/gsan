package gcom.relatorio.financeiro;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

public class RelatorioResumoDevedoresDuvidososBean implements RelatorioBean {

	private BigDecimal[] valorItemDevedoresDuvidosos;

	private Integer lancamentoTipo;

	private Integer lancamentoTipoSuperior;

	private Short indicadorImpressao;

	private String descricaoItemContabil;

	private String descricaoTipoLancamento;

	private Short indicadorTotal;

	private boolean tipoLancamentoSemItem = false;
	
	private String descricaoItemLancamento;
	
	private String descricaoGerencia;
	
	private String gerencia ;
	
	private String descricaoLocalidade ;
	
	private String localidade ;
	
	private String descLancamentoTipoSuperior;
	
	private String descricaoUnidadeNegocio ;
	
	private String unidadeNegocio ;

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}


	public String getDescricaoItemLancamento() {
		return descricaoItemLancamento;
	}

	public void setDescricaoItemLancamento(String descricaoItemLancamento) {
		this.descricaoItemLancamento = descricaoItemLancamento;
	}

	public RelatorioResumoDevedoresDuvidososBean(
			BigDecimal[] valorItemDevedoresDuvidosos,
			String descricaoTipoLancamento,
			String descricaoItemLancamento,
			String descricaoItemContabil,
			Short indicadorImpressao,
			Short indicadorTotal,
			Integer lancamentoTipo,
			Integer lancamentoTipoSuperior,
			boolean tipoLancamentoSemItem,
			String descricaoGerencia,
			String gerencia,
			String descricaoLocalidade,
			String localidade,
			String descLancamentoTipoSuperior,
			String descricaoUnidadeNegocio,
			String unidadeNegocio) {
		this.valorItemDevedoresDuvidosos = valorItemDevedoresDuvidosos;
		this.lancamentoTipo = lancamentoTipo;
		this.lancamentoTipoSuperior = lancamentoTipoSuperior;
		this.indicadorImpressao = indicadorImpressao;
		this.descricaoItemContabil = descricaoItemContabil;
		this.indicadorTotal = indicadorTotal;
		this.tipoLancamentoSemItem = tipoLancamentoSemItem;
		this.descricaoTipoLancamento = descricaoTipoLancamento;
		this.descricaoItemLancamento = descricaoItemLancamento;
		this.descricaoGerencia = descricaoGerencia;
		this.gerencia = gerencia;
		this.descricaoLocalidade = descricaoLocalidade;
		this.localidade =localidade;
		this.descLancamentoTipoSuperior = descLancamentoTipoSuperior;
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
		this.unidadeNegocio = unidadeNegocio;
	
	}

	public String getDescricaoGerencia() {
		return descricaoGerencia;
	}

	public void setDescricaoGerencia(String descricaoGerencia) {
		this.descricaoGerencia = descricaoGerencia;
	}

	public String getDescricaoItemContabil() {
		return descricaoItemContabil;
	}

	public void setDescricaoItemContabil(String descricaoItemContabil) {
		this.descricaoItemContabil = descricaoItemContabil;
	}

	public String getDescricaoTipoLancamento() {
		return descricaoTipoLancamento;
	}

	public void setDescricaoTipoLancamento(String descricaoTipoLancamento) {
		this.descricaoTipoLancamento = descricaoTipoLancamento;
	}

	public String getGerencia() {
		return gerencia;
	}

	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}

	public Short getIndicadorImpressao() {
		return indicadorImpressao;
	}

	public void setIndicadorImpressao(Short indicadorImpressao) {
		this.indicadorImpressao = indicadorImpressao;
	}

	public Short getIndicadorTotal() {
		return indicadorTotal;
	}

	public void setIndicadorTotal(Short indicadorTotal) {
		this.indicadorTotal = indicadorTotal;
	}

	public Integer getLancamentoTipo() {
		return lancamentoTipo;
	}

	public void setLancamentoTipo(Integer lancamentoTipo) {
		this.lancamentoTipo = lancamentoTipo;
	}

	public Integer getLancamentoTipoSuperior() {
		return lancamentoTipoSuperior;
	}

	public void setLancamentoTipoSuperior(Integer lancamentoTipoSuperior) {
		this.lancamentoTipoSuperior = lancamentoTipoSuperior;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public BigDecimal getSomaValoresItemDevedoresDuvidosos() {
		return this.getValorItemDevedoresDuvidosos4()
		.add(getValorItemDevedoresDuvidosos5());
	}

	public boolean isTipoLancamentoSemItem() {
		return tipoLancamentoSemItem;
	}

	public void setTipoLancamentoSemItem(boolean tipoLancamentoSemItem) {
		this.tipoLancamentoSemItem = tipoLancamentoSemItem;
	}

	public BigDecimal[] getValorItemDevedoresDuvidosos() {
		return valorItemDevedoresDuvidosos;
	}

	public void setValorItemDevedoresDuvidosos(BigDecimal[] valorItemDevedoresDuvidosos) {
		this.valorItemDevedoresDuvidosos = valorItemDevedoresDuvidosos;
	}
	
	public BigDecimal getValorItemDevedoresDuvidosos1() {
		BigDecimal valorItemDevedoresDuvidosos0 = valorItemDevedoresDuvidosos[0];
		
		if (valorItemDevedoresDuvidosos0 == null) {
			valorItemDevedoresDuvidosos0 = new BigDecimal(0);
		}
		
		return valorItemDevedoresDuvidosos0;
	}

	public BigDecimal getValorItemDevedoresDuvidosos2() {
		BigDecimal valorItemDevedoresDuvidosos1 = valorItemDevedoresDuvidosos[1];
		
		if (valorItemDevedoresDuvidosos1 == null) {
			valorItemDevedoresDuvidosos1 = new BigDecimal(0);
		}
		
		return valorItemDevedoresDuvidosos1;
	}

	public BigDecimal getValorItemDevedoresDuvidosos3() {
		BigDecimal valorItemDevedoresDuvidosos2 = valorItemDevedoresDuvidosos[2];
		
		if (valorItemDevedoresDuvidosos2 == null) {
			valorItemDevedoresDuvidosos2 = new BigDecimal(0);
		}
		
		return valorItemDevedoresDuvidosos2;
	}

	public BigDecimal getValorItemDevedoresDuvidosos4() {
		return this.getValorItemDevedoresDuvidosos1().add(getValorItemDevedoresDuvidosos2())
		.add(getValorItemDevedoresDuvidosos3());
	}

	public BigDecimal getValorItemDevedoresDuvidosos5() {
		BigDecimal valorItemDevedoresDuvidosos4 = valorItemDevedoresDuvidosos[4];
		
		if (valorItemDevedoresDuvidosos4 == null) {
			valorItemDevedoresDuvidosos4 = new BigDecimal(0);
		}
		
		return valorItemDevedoresDuvidosos4;
	}

	public String getDescLancamentoTipoSuperior() {
		return descLancamentoTipoSuperior;
	}

	public void setDescLancamentoTipoSuperior(String descLancamentoTipoSuperior) {
		this.descLancamentoTipoSuperior = descLancamentoTipoSuperior;
	}

	public String getDescricaoUnidadeNegocio() {
		return descricaoUnidadeNegocio;
	}

	public void setDescricaoUnidadeNegocio(String descricaoUnidadeNegocio) {
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	
	
}
