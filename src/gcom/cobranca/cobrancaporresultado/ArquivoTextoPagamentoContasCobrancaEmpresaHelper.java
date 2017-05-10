package gcom.cobranca.cobrancaporresultado;

import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ArquivoTextoPagamentoContasCobrancaEmpresaHelper implements
		Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer matricula;
	
	private String nomeClienteConta;
	
	private Integer referenciaConta;
	
	private BigDecimal valorConta;
	
	private Integer referenciaPagamento;
	
	private Integer tipoPagamento;
	
	private Integer numeroParcelas;
	
	private Integer numeroParcelasTotal;
	
	private BigDecimal valorPagamentoPrincipal;
	
	private BigDecimal valorEncargos;
	
	private BigDecimal valorPagamentoTotal;
	
	private BigDecimal percentualEmpresa;
	
	private BigDecimal valorPagamentoEmpresa;
	
	private Integer idUnidadeNegocio;
	
	private String nomeUnidadeNegocio;
	
	private Integer idLocalidade;
	
	private String nomeLocalidade;
	
	private Integer codigoSetor;
	
	private Integer numeroQuadra;
	
	private Integer lote;
	
	private Integer subLote;
	
	private Integer codigoRota;
	
	private Integer sequencialRota;
	
	private Date dataPagamento;

	public Integer getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Integer codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getCodigoSetor() {
		return codigoSetor;
	}

	public void setCodigoSetor(Integer codigoSetor) {
		this.codigoSetor = codigoSetor;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public Integer getLote() {
		return lote;
	}

	public void setLote(Integer lote) {
		this.lote = lote;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNomeClienteConta() {
		return nomeClienteConta;
	}

	public void setNomeClienteConta(String nomeClienteConta) {
		this.nomeClienteConta = nomeClienteConta;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelasTotal() {
		return numeroParcelasTotal;
	}

	public void setNumeroParcelasTotal(Integer numeroParcelasTotal) {
		this.numeroParcelasTotal = numeroParcelasTotal;
	}

	public BigDecimal getPercentualEmpresa() {
		return percentualEmpresa;
	}

	public void setPercentualEmpresa(BigDecimal percentualEmpresa) {
		this.percentualEmpresa = percentualEmpresa;
	}

	public Integer getReferenciaConta() {
		return referenciaConta;
	}

	public void setReferenciaConta(Integer referenciaConta) {
		this.referenciaConta = referenciaConta;
	}

	public Integer getReferenciaPagamento() {
		return referenciaPagamento;
	}

	public void setReferenciaPagamento(Integer referenciaPagamento) {
		this.referenciaPagamento = referenciaPagamento;
	}

	public Integer getSequencialRota() {
		return sequencialRota;
	}

	public void setSequencialRota(Integer sequencialRota) {
		this.sequencialRota = sequencialRota;
	}

	public Integer getSubLote() {
		return subLote;
	}

	public void setSubLote(Integer subLote) {
		this.subLote = subLote;
	}

	public Integer getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(Integer tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public BigDecimal getValorConta() {
		return valorConta;
	}

	public void setValorConta(BigDecimal valorConta) {
		this.valorConta = valorConta;
	}

	public BigDecimal getValorEncargos() {
		return valorEncargos;
	}

	public void setValorEncargos(BigDecimal valorEncargos) {
		this.valorEncargos = valorEncargos;
	}

	public BigDecimal getValorPagamentoEmpresa() {
		return valorPagamentoEmpresa;
	}

	public void setValorPagamentoEmpresa(BigDecimal valorPagamentoEmpresa) {
		this.valorPagamentoEmpresa = valorPagamentoEmpresa;
	}

	public BigDecimal getValorPagamentoPrincipal() {
		return valorPagamentoPrincipal;
	}

	public void setValorPagamentoPrincipal(BigDecimal valorPagamentoPrincipal) {
		this.valorPagamentoPrincipal = valorPagamentoPrincipal;
	}

	public BigDecimal getValorPagamentoTotal() {
		return valorPagamentoTotal;
	}

	public void setValorPagamentoTotal(BigDecimal valorPagamentoTotal) {
		this.valorPagamentoTotal = valorPagamentoTotal;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public StringBuilder getArquivoTexto() {

		StringBuilder arquivo = new StringBuilder();

		arquivo.append(this.matricula != null ? this.matricula.toString() : "").append(";");
		arquivo.append(this.nomeClienteConta != null ? this.nomeClienteConta.toString() : "").append(";");
		arquivo.append(this.referenciaConta != null ? Util.formatarAnoMesParaMesAno(this.referenciaConta) : "").append(";");
		arquivo.append(this.valorConta != null ? Util.formatarBigDecimalComPonto(this.valorConta) : "").append(";");
		arquivo.append(this.referenciaPagamento != null ? Util.formatarAnoMesParaMesAno(this.referenciaPagamento) : "").append(";");
		arquivo.append(this.tipoPagamento != null ? this.tipoPagamento : "").append(";");
		arquivo.append(this.dataPagamento != null ? Util.formatarData(this.dataPagamento) : "").append(";");
		arquivo.append(this.numeroParcelas != null ? this.numeroParcelas : "").append(";");
		arquivo.append(this.numeroParcelasTotal != null ? this.numeroParcelasTotal : "").append(";");
		arquivo.append(this.valorPagamentoPrincipal != null ? Util.formatarBigDecimalComPonto(this.valorPagamentoPrincipal) : "").append(";");
		arquivo.append(this.valorEncargos != null ? Util.formatarBigDecimalComPonto(this.valorEncargos) : "").append(";");
		arquivo.append(this.valorPagamentoTotal != null ? Util.formatarBigDecimalComPonto(this.valorPagamentoTotal) : "").append(";");
		arquivo.append(this.percentualEmpresa != null ? Util.formatarBigDecimalComPonto(this.percentualEmpresa) : "").append(";");
		arquivo.append(this.valorPagamentoEmpresa != null ? Util.formatarBigDecimalComPonto(this.valorPagamentoEmpresa) : "").append(";");
		arquivo.append(this.idUnidadeNegocio != null ? this.idUnidadeNegocio.toString() : "").append(";");
		arquivo.append(this.nomeUnidadeNegocio != null ? this.nomeUnidadeNegocio.toString() : "").append(";");
		arquivo.append(this.idLocalidade != null ? this.idLocalidade.toString() : "").append(";");
		arquivo.append(this.nomeLocalidade != null ? this.nomeLocalidade.toString() : "").append(";");
		arquivo.append(this.codigoSetor != null ? this.codigoSetor.toString() : "").append(";");
		arquivo.append(this.numeroQuadra != null ? this.numeroQuadra.toString() : "").append(";");
		arquivo.append(this.lote != null ? this.lote.toString() : "").append(";");
		arquivo.append(this.subLote != null ? this.subLote.toString() : "").append(";");
		arquivo.append(this.codigoRota != null ? this.codigoRota.toString() : "").append(";");
		arquivo.append(this.sequencialRota != null ? this.sequencialRota.toString() : "").append(";");

		arquivo.append(System.getProperty("line.separator"));

		return arquivo;
	}
}
