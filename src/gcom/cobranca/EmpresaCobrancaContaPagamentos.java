package gcom.cobranca;

import gcom.faturamento.debito.DebitoTipo;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

public class EmpresaCobrancaContaPagamentos extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** nullable persistent field */
	private EmpresaCobrancaConta empresaCobrancaConta;
	
	/** nullable persistent field */
	private Integer anoMesPagamentoArrecadacao;

	/** nullable persistent field */
	private DebitoTipo debitoTipo;
	/** nullable persistent field */
	private Date ultimaAlteracao;
	private BigDecimal valorPagamentoMes;
	private Integer anoMesReferenciaPagamento; 
	private Date dataPagamento;
	private Integer idImovel;
	private Integer idArrecadador;
	private Short indicadorTipoPagamento;
	private Integer numeroParcelaAtual;
	private Integer numeroTotalParcelas;
	

	
	

	public EmpresaCobrancaContaPagamentos(Integer id, EmpresaCobrancaConta empresaCobrancaConta, Integer anoMesPagamentoArrecadacao,
			DebitoTipo debitoTipo,
			Date ultimaAlteracao,BigDecimal valorPagamentoMes) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.empresaCobrancaConta = empresaCobrancaConta;
		this.anoMesPagamentoArrecadacao = anoMesPagamentoArrecadacao;
		this.debitoTipo = debitoTipo;
		this.ultimaAlteracao = ultimaAlteracao;
		this.valorPagamentoMes = valorPagamentoMes;
	}

	public EmpresaCobrancaContaPagamentos() {

	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroEmpresaCobrancaContaPagamentos filtroEmpresaCobrancaContaPagamentos = new FiltroEmpresaCobrancaContaPagamentos();

		filtroEmpresaCobrancaContaPagamentos.adicionarParametro(new ParametroSimples(
				FiltroEmpresaCobrancaContaPagamentos.ID, this.getId()));
		return null;
	}
	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
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
	

}
