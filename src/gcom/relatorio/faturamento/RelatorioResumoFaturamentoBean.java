package gcom.relatorio.faturamento;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

public class RelatorioResumoFaturamentoBean implements RelatorioBean {

	private BigDecimal[] valorItemFaturamento;

	private Integer lancamentoTipo;

	private Integer lancamentoTipoSuperior;

	private Short indicadorImpressao;

	private String descricaoItemContabil;

	private String descricaoTipoLancamento;

	private Short indicadorTotal;

	private boolean tipoLancamentoSemItem = false;

	@SuppressWarnings("unused")
	private BigDecimal somaValoresItemFaturamento;
	
	private String descricaoItemLancamento;
	
	private String descricaoGerencia;
	
	private String gerencia ;
	
	private String descricaoLocalidade ;
	
	private String descricaoMunicipio;
	
	private String codigoCentroCusto;
	
	private String localidade ;

	private String municipio;
	
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

	public RelatorioResumoFaturamentoBean(
			BigDecimal[] valorItemFaturamento,
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
			String descricaoMunicipio,
			String municipio,
			String descLancamentoTipoSuperior,
			String descricaoUnidadeNegocio,
			String unidadeNegocio,
			String codigoCentroCusto) {
		this.valorItemFaturamento = valorItemFaturamento;
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
		this.descricaoMunicipio = descricaoMunicipio;
		this.municipio = municipio;
		this.descLancamentoTipoSuperior = descLancamentoTipoSuperior;
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
		this.unidadeNegocio = unidadeNegocio;
		this.codigoCentroCusto = codigoCentroCusto;
	
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

	public BigDecimal getSomaValoresItemFaturamento() {
		return this.getValorItemFaturamento4()
		.add(getValorItemFaturamento5());
	}

	public void setSomaValoresItemFaturamento(BigDecimal somaValoresItemFaturamento) {
		this.somaValoresItemFaturamento = somaValoresItemFaturamento;
	}

	public boolean isTipoLancamentoSemItem() {
		return tipoLancamentoSemItem;
	}

	public void setTipoLancamentoSemItem(boolean tipoLancamentoSemItem) {
		this.tipoLancamentoSemItem = tipoLancamentoSemItem;
	}

	public BigDecimal[] getValorItemFaturamento() {
		return valorItemFaturamento;
	}

	public void setValorItemFaturamento(BigDecimal[] valorItemFaturamento) {
		this.valorItemFaturamento = valorItemFaturamento;
	}
	
	
	
	public BigDecimal getValorItemFaturamento1() {
		BigDecimal valorItemFaturamento0 = valorItemFaturamento[0];
		
		if (valorItemFaturamento0 == null) {
			valorItemFaturamento0 = new BigDecimal(0);
		}
		
		return valorItemFaturamento0;
	}

	public BigDecimal getValorItemFaturamento2() {
		BigDecimal valorItemFaturamento1 = valorItemFaturamento[1];
		
		if (valorItemFaturamento1 == null) {
			valorItemFaturamento1 = new BigDecimal(0);
		}
		
		return valorItemFaturamento1;
	}

	public BigDecimal getValorItemFaturamento3() {
		BigDecimal valorItemFaturamento2 = valorItemFaturamento[2];
		
		if (valorItemFaturamento2 == null) {
			valorItemFaturamento2 = new BigDecimal(0);
		}
		
		return valorItemFaturamento2;
	}

	public BigDecimal getValorItemFaturamento4() {
		return this.getValorItemFaturamento1().add(getValorItemFaturamento2())
		.add(getValorItemFaturamento3());
	}

	public BigDecimal getValorItemFaturamento5() {
		BigDecimal valorItemFaturamento4 = valorItemFaturamento[4];
		
		if (valorItemFaturamento4 == null) {
			valorItemFaturamento4 = new BigDecimal(0);
		}
		
		return valorItemFaturamento4;
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

	public String getCodigoCentroCusto() {
		return codigoCentroCusto;
	}

	public void setCodigoCentroCusto(String codigoCentroCusto) {
		this.codigoCentroCusto = codigoCentroCusto;
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
}
