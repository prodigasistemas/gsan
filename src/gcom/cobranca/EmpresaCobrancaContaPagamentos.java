package gcom.cobranca;

import gcom.faturamento.debito.DebitoTipo;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

public class EmpresaCobrancaContaPagamentos extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private EmpresaCobrancaConta empresaCobrancaConta;
	private Integer anoMesPagamentoArrecadacao;
	private DebitoTipo debitoTipo;
	private Date ultimaAlteracao;
	private BigDecimal valorPagamentoMes;
	private Integer anoMesReferenciaPagamento;
	private Date dataPagamento;
	private Integer idImovel;
	private Integer idArrecadador;
	private Short indicadorTipoPagamento;
	private Integer numeroParcelaAtual;
	private Integer numeroTotalParcelas;
	private Short indicadorGeracaoArquivo;
	private Integer idPagamento;
	private BigDecimal valorDesconto;

	public EmpresaCobrancaContaPagamentos(
			Integer id, 
			EmpresaCobrancaConta empresaCobrancaConta, 
			Integer anoMesPagamentoArrecadacao, 
			DebitoTipo debitoTipo, 
			Date ultimaAlteracao, 
			BigDecimal valorPagamentoMes,
			Integer idPagamento) {
		super();

		this.id = id;
		this.empresaCobrancaConta = empresaCobrancaConta;
		this.anoMesPagamentoArrecadacao = anoMesPagamentoArrecadacao;
		this.debitoTipo = debitoTipo;
		this.ultimaAlteracao = ultimaAlteracao;
		this.valorPagamentoMes = valorPagamentoMes;
		this.idPagamento = idPagamento;
	}

	public EmpresaCobrancaContaPagamentos() {

	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroEmpresaCobrancaContaPagamentos();
		filtro.adicionarParametro(new ParametroSimples(FiltroEmpresaCobrancaContaPagamentos.ID, this.getId()));
		return filtro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getAnoMesPagamentoArrecadacao() {
		return anoMesPagamentoArrecadacao;
	}

	public void setAnoMesPagamentoArrecadacao(Integer anoMesPagamentoArrecadacao) {
		this.anoMesPagamentoArrecadacao = anoMesPagamentoArrecadacao;
	}

	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}

	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public EmpresaCobrancaConta getEmpresaCobrancaConta() {
		return empresaCobrancaConta;
	}

	public void setEmpresaCobrancaConta(EmpresaCobrancaConta empresaCobrancaConta) {
		this.empresaCobrancaConta = empresaCobrancaConta;
	}

	public BigDecimal getValorPagamentoMes() {
		return valorPagamentoMes;
	}

	public void setValorPagamentoMes(BigDecimal valorPagamentoMes) {
		this.valorPagamentoMes = valorPagamentoMes;
	}

	public Integer getAnoMesReferenciaPagamento() {
		return anoMesReferenciaPagamento;
	}

	public void setAnoMesReferenciaPagamento(Integer anoMesReferenciaPagamento) {
		this.anoMesReferenciaPagamento = anoMesReferenciaPagamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Integer getIdArrecadador() {
		return idArrecadador;
	}

	public void setIdArrecadador(Integer idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Short getIndicadorTipoPagamento() {
		return indicadorTipoPagamento;
	}

	public void setIndicadorTipoPagamento(Short indicadorTipoPagamento) {
		this.indicadorTipoPagamento = indicadorTipoPagamento;
	}

	public Integer getNumeroParcelaAtual() {
		return numeroParcelaAtual;
	}

	public void setNumeroParcelaAtual(Integer numeroParcelaAtual) {
		this.numeroParcelaAtual = numeroParcelaAtual;
	}

	public Integer getNumeroTotalParcelas() {
		return numeroTotalParcelas;
	}

	public void setNumeroTotalParcelas(Integer numeroTotalParcelas) {
		this.numeroTotalParcelas = numeroTotalParcelas;
	}

	public Short getIndicadorGeracaoArquivo() {
		return indicadorGeracaoArquivo;
	}

	public void setIndicadorGeracaoArquivo(Short indicadorGeracaoArquivo) {
		this.indicadorGeracaoArquivo = indicadorGeracaoArquivo;
	}

	public Integer getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
}
