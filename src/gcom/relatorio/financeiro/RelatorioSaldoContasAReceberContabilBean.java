package gcom.relatorio.financeiro;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

public class RelatorioSaldoContasAReceberContabilBean implements RelatorioBean {

	private Integer idGerenciaRegional;
	
	private String nomeGerenciaRegional;
	
	private Integer idUnidadeNegocio;
	
	private String nomeUnidadeNegocio;
	
	private String codigoCentroCusto;
	
	private String tipoGrupo ;

	private Integer idGrupo;
	
	private String descricaoGrupo;

	private String descricaoLancamentoTipo;
	
	private String descricaoLancamentoItem;
	
	private Integer codigoContabilParticular;
	
	private Integer codigoContabilPublico;

	private BigDecimal valorItemParticular;
	
	private BigDecimal valorItemPublico;
	
	private Integer sequenciaLancamentoTipo;
	
	private Integer sequenciaLancamentoItem;
	
	private String descricaoLancamentoItemContabil;
	
	private BigDecimal totalGeralSemPerdasParticular;
	
	private BigDecimal totalGeralSemPerdasPublico;
	
	private BigDecimal totalGeralSemPerdas;
	
	private String descImpostosDeduzidos;
	private BigDecimal valorImpostosDeduzidosParticular = new BigDecimal(0);
	private BigDecimal valorImpostosDeduzidosPublico = new BigDecimal(0);
	private BigDecimal valorImpostosDeduzidosTotal = new BigDecimal(0);
	
	private BigDecimal valorItemResidencial = new BigDecimal(0);
	private BigDecimal valorItemComercial = new BigDecimal(0);
	private BigDecimal valorItemIndustrial = new BigDecimal(0);
	private BigDecimal totalGeralSemPerdasResidencial = new BigDecimal(0);
	private BigDecimal totalGeralSemPerdasComercial = new BigDecimal(0);
	private BigDecimal totalGeralSemPerdasIndustrial = new BigDecimal(0);
	
	private Integer idLancamentoTipo;
	
	
	public Integer getIdLancamentoTipo() {
		return idLancamentoTipo;
	}

	public void setIdLancamentoTipo(Integer idLancamentoTipo) {
		this.idLancamentoTipo = idLancamentoTipo;
	}

	public BigDecimal getTotalGeralSemPerdas() {
		return totalGeralSemPerdas;
	}

	public void setTotalGeralSemPerdas(BigDecimal totalGeralSemPerdas) {
		this.totalGeralSemPerdas = totalGeralSemPerdas;
	}

	public BigDecimal getTotalGeralSemPerdasPublico() {
		return totalGeralSemPerdasPublico;
	}

	public void setTotalGeralSemPerdasPublico(BigDecimal totalGeralSemPerdasPublico) {
		this.totalGeralSemPerdasPublico = totalGeralSemPerdasPublico;
	}

	public BigDecimal getTotalGeralSemPerdasParticular() {
		return totalGeralSemPerdasParticular;
	}

	public void setTotalGeralSemPerdasParticular(BigDecimal totalGeralSemPerdasParticular) {
		this.totalGeralSemPerdasParticular = totalGeralSemPerdasParticular;
	}

	public String getDescricaoLancamentoItemContabil() {
		return descricaoLancamentoItemContabil;
	}

	public void setDescricaoLancamentoItemContabil(String descricaoLancamentoItemContabil) {
		this.descricaoLancamentoItemContabil = descricaoLancamentoItemContabil;
	}

	public RelatorioSaldoContasAReceberContabilBean(Integer idGerenciaRegional,
			String nomeGerenciaRegional, Integer idUnidadeNegocio,
			String nomeUnidadeNegocio, String codigoCentroCusto,
			String tipoGrupo, Integer idGrupo, String descricaoGrupo,
			String descricaoLancamentoTipo, String descricaoLancamentoItem, String descricaoLancamentoItemContabil,
			BigDecimal totalGeralSemPerdasParticular, BigDecimal totalGeralSemPerdasPublico, BigDecimal totalGeralSemPerdas) {
		super();
		// TODO Auto-generated constructor stub
		this.idGerenciaRegional = idGerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
		this.codigoCentroCusto = codigoCentroCusto == null ? "" : codigoCentroCusto;
		this.tipoGrupo = tipoGrupo;
		this.idGrupo = idGrupo;
		this.descricaoGrupo = descricaoGrupo;
		this.descricaoLancamentoTipo = descricaoLancamentoTipo;
		this.descricaoLancamentoItem = descricaoLancamentoItem;
		this.codigoContabilParticular = new Integer(0);
		this.codigoContabilPublico = new Integer(0);
		this.valorItemParticular = new BigDecimal(0);
		this.valorItemPublico = new BigDecimal(0);
		this.descricaoLancamentoItemContabil = descricaoLancamentoItemContabil;
		this.totalGeralSemPerdasParticular = totalGeralSemPerdasParticular;
		this.totalGeralSemPerdasPublico = totalGeralSemPerdasPublico;
		this.totalGeralSemPerdas = totalGeralSemPerdas;
		
	}

//	public RelatorioSaldoContasAReceberContabilBean(String codigoCentroCusto, String tipoGrupo, Integer idGrupo, String descricaoGrupo, String descricaoLancamentoTipo, String descricaoLancamentoItem) {
//		super();
//		// TODO Auto-generated constructor stub
//		this.codigoCentroCusto = codigoCentroCusto == null ? "" : codigoCentroCusto;
//		this.tipoGrupo = tipoGrupo;
//		this.idGrupo = idGrupo;
//		this.descricaoGrupo = descricaoGrupo;
//		this.descricaoLancamentoTipo = descricaoLancamentoTipo;
//		this.descricaoLancamentoItem = descricaoLancamentoItem;
//
//	}

	public String getDescricaoLancamentoItem() {
		return descricaoLancamentoItem;
	}

	public void setDescricaoLancamentoItem(String descricaoLancamentoItem) {
		this.descricaoLancamentoItem = descricaoLancamentoItem;
	}

	public String getDescricaoLancamentoTipo() {
		return descricaoLancamentoTipo;
	}

	public void setDescricaoLancamentoTipo(String descricaoLancamentoTipo) {
		this.descricaoLancamentoTipo = descricaoLancamentoTipo;
	}

	public String getDescricaoGrupo() {
		return descricaoGrupo;
	}

	public void setDescricaoGrupo(String descricaoGrupo) {
		this.descricaoGrupo = descricaoGrupo;
	}

	public String getTipoGrupo() {
		return tipoGrupo;
	}

	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public BigDecimal getValorItemTotal() {
		return this.getValorItemParticular().add(getValorItemPublico());
	}

	public Integer getCodigoContabilParticular() {
		return codigoContabilParticular;
	}

	public void setCodigoContabilParticular(Integer codigoContabilParticular) {
		this.codigoContabilParticular = codigoContabilParticular;
	}

	public Integer getCodigoContabilPublico() {
		return codigoContabilPublico;
	}

	public void setCodigoContabilPublico(Integer codigoContabilPublico) {
		this.codigoContabilPublico = codigoContabilPublico;
	}

	public BigDecimal getValorItemParticular() {
		return valorItemParticular;
	}

	public void setValorItemParticular(BigDecimal valorItemParticular) {
		this.valorItemParticular = valorItemParticular;
	}

	public BigDecimal getValorItemPublico() {
		return valorItemPublico;
	}

	public void setValorItemPublico(BigDecimal valorItemPublico) {
		this.valorItemPublico = valorItemPublico;
	}

	public Integer getSequenciaLancamentoItem() {
		return sequenciaLancamentoItem;
	}

	public void setSequenciaLancamentoItem(Integer sequenciaLancamentoItem) {
		this.sequenciaLancamentoItem = sequenciaLancamentoItem;
	}

	public Integer getSequenciaLancamentoTipo() {
		return sequenciaLancamentoTipo;
	}

	public void setSequenciaLancamentoTipo(Integer sequenciaLancamentoTipo) {
		this.sequenciaLancamentoTipo = sequenciaLancamentoTipo;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getCodigoCentroCusto() {
		return codigoCentroCusto;
	}

	public void setCodigoCentroCusto(String codigoCentroCusto) {
		this.codigoCentroCusto = codigoCentroCusto;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public String getDescImpostosDeduzidos() {
		return descImpostosDeduzidos;
	}

	public void setDescImpostosDeduzidos(String descImpostosDeduzidos) {
		this.descImpostosDeduzidos = descImpostosDeduzidos;
	}

	public BigDecimal getValorImpostosDeduzidosParticular() {
		return valorImpostosDeduzidosParticular;
	}

	public void setValorImpostosDeduzidosParticular(
			BigDecimal valorImpostosDeduzidosParticular) {
		this.valorImpostosDeduzidosParticular = valorImpostosDeduzidosParticular;
	}

	public BigDecimal getValorImpostosDeduzidosPublico() {
		return valorImpostosDeduzidosPublico;
	}

	public void setValorImpostosDeduzidosPublico(
			BigDecimal valorImpostosDeduzidosPublico) {
		this.valorImpostosDeduzidosPublico = valorImpostosDeduzidosPublico;
	}

	public BigDecimal getValorImpostosDeduzidosTotal() {
		return valorImpostosDeduzidosTotal;
	}

	public void setValorImpostosDeduzidosTotal(
			BigDecimal valorImpostosDeduzidosTotal) {
		this.valorImpostosDeduzidosTotal = valorImpostosDeduzidosTotal;
	}

	public BigDecimal getValorItemComercial() {
		return valorItemComercial;
	}

	public void setValorItemComercial(BigDecimal valorItemComercial) {
		this.valorItemComercial = valorItemComercial;
	}

	public BigDecimal getValorItemIndustrial() {
		return valorItemIndustrial;
	}

	public void setValorItemIndustrial(BigDecimal valorItemIndustrial) {
		this.valorItemIndustrial = valorItemIndustrial;
	}

	public BigDecimal getValorItemResidencial() {
		return valorItemResidencial;
	}

	public void setValorItemResidencial(BigDecimal valorItemResidencial) {
		this.valorItemResidencial = valorItemResidencial;
	}

	public BigDecimal getTotalGeralSemPerdasComercial() {
		return totalGeralSemPerdasComercial;
	}

	public void setTotalGeralSemPerdasComercial(
			BigDecimal totalGeralSemPerdasComercial) {
		this.totalGeralSemPerdasComercial = totalGeralSemPerdasComercial;
	}

	public BigDecimal getTotalGeralSemPerdasIndustrial() {
		return totalGeralSemPerdasIndustrial;
	}

	public void setTotalGeralSemPerdasIndustrial(
			BigDecimal totalGeralSemPerdasIndustrial) {
		this.totalGeralSemPerdasIndustrial = totalGeralSemPerdasIndustrial;
	}

	public BigDecimal getTotalGeralSemPerdasResidencial() {
		return totalGeralSemPerdasResidencial;
	}

	public void setTotalGeralSemPerdasResidencial(
			BigDecimal totalGeralSemPerdasResidencial) {
		this.totalGeralSemPerdasResidencial = totalGeralSemPerdasResidencial;
	}

}
