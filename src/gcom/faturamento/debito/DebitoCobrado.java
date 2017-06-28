package gcom.faturamento.debito;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cobranca.ParcelamentoGrupo;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.IConta;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;

@ControleAlteracao()
public class DebitoCobrado extends ObjetoTransacao implements IDebitoCobrado {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date debitoCobrado;
	private Integer codigoSetorComercial;
	private Integer numeroQuadra;
	private Short numeroLote;
	private Short numeroSubLote;
	private Integer anoMesReferenciaDebito;
	private Integer anoMesCobrancaDebito;

	@ControleAlteracao(funcionalidade = Conta.ATRIBUTOS_RETIFICAR_CONTA)
	private BigDecimal valorPrestacao;

	private short numeroPrestacao;
	private short numeroPrestacaoDebito;
	private Date ultimaAlteracao;
	private int lictId;
	private Short numeroParcelaBonus;
	private LancamentoItemContabil lancamentoItemContabil;
	private Conta conta;
	private FinanciamentoTipo financiamentoTipo;
	private Quadra quadra;
	private Localidade localidade;
	private gcom.faturamento.debito.DebitoTipo debitoTipo;
	private ParcelamentoGrupo parcelamentoGrupo;
	private DebitoACobrarGeral debitoACobrarGeral;

	@SuppressWarnings("rawtypes")
	private Set debitoCobradoCategorias;

	@SuppressWarnings("rawtypes")
	public DebitoCobrado(Date debitoCobrado, Integer codigoSetorComercial, Integer numeroQuadra, Short numeroLote, Short numeroSubLote, Integer anoMesReferenciaDebito, Integer anoMesCobrancaDebito,
			BigDecimal valorPrestacao, short numeroPrestacao, short numeroPrestacaoDebito, Date ultimaAlteracao, int lictId, LancamentoItemContabil lancamentoItemContabil, Conta conta,
			FinanciamentoTipo financiamentoTipo, Quadra quadra, Localidade localidade, gcom.faturamento.debito.DebitoTipo debitoTipo, ParcelamentoGrupo parcelamentoGrupo, Set debitoCobradoCategorias) {
		this.debitoCobrado = debitoCobrado;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.numeroLote = numeroLote;
		this.numeroSubLote = numeroSubLote;
		this.anoMesReferenciaDebito = anoMesReferenciaDebito;
		this.anoMesCobrancaDebito = anoMesCobrancaDebito;
		this.valorPrestacao = valorPrestacao;
		this.numeroPrestacao = numeroPrestacao;
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
		this.ultimaAlteracao = ultimaAlteracao;
		this.lictId = lictId;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.conta = conta;
		this.financiamentoTipo = financiamentoTipo;
		this.quadra = quadra;
		this.localidade = localidade;
		this.debitoTipo = debitoTipo;
		this.parcelamentoGrupo = parcelamentoGrupo;
		this.debitoCobradoCategorias = debitoCobradoCategorias;
	}

	public DebitoCobrado() {
	}

	public DebitoCobrado(Integer id) {
		this.id = id;
	}

	@SuppressWarnings("rawtypes")
	public DebitoCobrado(Date debitoCobrado, BigDecimal valorPrestacao, short numeroPrestacao, short numeroPrestacaoDebito, LancamentoItemContabil lancamentoItemContabil, Conta conta,
			FinanciamentoTipo financiamentoTipo, Quadra quadra, Localidade localidade, gcom.faturamento.debito.DebitoTipo debitoTipo, ParcelamentoGrupo parcelamentoGrupo, Set debitoCobradoCategorias) {
		this.debitoCobrado = debitoCobrado;
		this.valorPrestacao = valorPrestacao;
		this.numeroPrestacao = numeroPrestacao;
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
		this.lancamentoItemContabil = lancamentoItemContabil;
		this.conta = conta;
		this.financiamentoTipo = financiamentoTipo;
		this.quadra = quadra;
		this.localidade = localidade;
		this.debitoTipo = debitoTipo;
		this.parcelamentoGrupo = parcelamentoGrupo;
		this.debitoCobradoCategorias = debitoCobradoCategorias;
	}

	public DebitoCobrado(Integer anoMesReferenciaDebito, short numeroPrestacaoDebito, short numeroPrestacao, BigDecimal valorPrestacao, DebitoTipo debitoTipo) {
		this.anoMesReferenciaDebito = anoMesReferenciaDebito;
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
		this.numeroPrestacao = numeroPrestacao;
		this.valorPrestacao = valorPrestacao;
		this.debitoTipo = debitoTipo;
	}

	public DebitoCobrado(Date debitoCobrado, DebitoACobrar debitoACobrar, Conta conta, BigDecimal valor) {
		super();
		
		this.debitoCobrado = new Date();
		this.localidade = debitoACobrar.getImovel().getLocalidade();
		this.codigoSetorComercial = debitoACobrar.getImovel().getSetorComercial().getCodigo();
		this.quadra = debitoACobrar.getImovel().getQuadra();
		this.numeroQuadra = debitoACobrar.getImovel().getQuadra().getNumeroQuadra();
		this.numeroLote = debitoACobrar.getImovel().getLote();
		this.numeroSubLote = debitoACobrar.getImovel().getSubLote();
		this.debitoTipo = debitoACobrar.getDebitoTipo();
		this.conta = conta;
		this.lancamentoItemContabil = debitoACobrar.getLancamentoItemContabil();
		this.anoMesReferenciaDebito = debitoACobrar.getAnoMesReferenciaDebito();
		this.anoMesCobrancaDebito = debitoACobrar.getAnoMesCobrancaDebito();
		this.valorPrestacao = valor;
		this.numeroPrestacao = debitoACobrar.getNumeroPrestacaoDebito();
		this.numeroPrestacaoDebito = debitoACobrar.getNumeroPrestacaoDebito();
		this.numeroParcelaBonus = debitoACobrar.getNumeroParcelaBonus();
		this.financiamentoTipo = debitoACobrar.getFinanciamentoTipo();
		this.debitoACobrarGeral = debitoACobrar.getDebitoACobrarGeral();
		this.ultimaAlteracao = new Date();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDebitoCobrado() {
		return this.debitoCobrado;
	}

	public void setDebitoCobrado(Date debitoCobrado) {
		this.debitoCobrado = debitoCobrado;
	}

	public Integer getCodigoSetorComercial() {
		return this.codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getNumeroQuadra() {
		return this.numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Short getNumeroLote() {
		return this.numeroLote;
	}

	public void setNumeroLote(Short numeroLote) {
		this.numeroLote = numeroLote;
	}

	public Short getNumeroSubLote() {
		return this.numeroSubLote;
	}

	public void setNumeroSubLote(Short numeroSubLote) {
		this.numeroSubLote = numeroSubLote;
	}

	public Integer getAnoMesReferenciaDebito() {
		return this.anoMesReferenciaDebito;
	}

	public void setAnoMesReferenciaDebito(Integer anoMesReferenciaDebito) {
		this.anoMesReferenciaDebito = anoMesReferenciaDebito;
	}

	public Integer getAnoMesCobrancaDebito() {
		return this.anoMesCobrancaDebito;
	}

	public void setAnoMesCobrancaDebito(Integer anoMesCobrancaDebito) {
		this.anoMesCobrancaDebito = anoMesCobrancaDebito;
	}

	public BigDecimal getValorPrestacao() {
		return this.valorPrestacao;
	}

	public void setValorPrestacao(BigDecimal valorPrestacao) {
		this.valorPrestacao = valorPrestacao;
	}

	public Short getNumeroPrestacao() {
		return this.numeroPrestacao;
	}

	public void setNumeroPrestacao(Short numeroPrestacao) {
		this.numeroPrestacao = numeroPrestacao;
	}

	public Short getNumeroPrestacaoDebito() {
		return this.numeroPrestacaoDebito;
	}

	public void setNumeroPrestacaoDebito(Short numeroPrestacaoDebito) {
		this.numeroPrestacaoDebito = numeroPrestacaoDebito;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public int getLictId() {
		return this.lictId;
	}

	public void setLictId(int lictId) {
		this.lictId = lictId;
	}

	public LancamentoItemContabil getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public IConta getConta() {
		return this.conta;
	}

	public void setConta(IConta conta) {
		this.conta = new Conta(conta.getId());
	}

	public FinanciamentoTipo getFinanciamentoTipo() {
		return this.financiamentoTipo;
	}

	public void setFinanciamentoTipo(FinanciamentoTipo financiamentoTipo) {
		this.financiamentoTipo = financiamentoTipo;
	}

	public Quadra getQuadra() {
		return this.quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public gcom.faturamento.debito.DebitoTipo getDebitoTipo() {
		return this.debitoTipo;
	}

	public void setDebitoTipo(gcom.faturamento.debito.DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	public ParcelamentoGrupo getParcelamentoGrupo() {
		return this.parcelamentoGrupo;
	}

	public void setParcelamentoGrupo(ParcelamentoGrupo parcelamentoGrupo) {
		this.parcelamentoGrupo = parcelamentoGrupo;
	}

	@SuppressWarnings("rawtypes")
	public Set getDebitoCobradoCategorias() {
		return this.debitoCobradoCategorias;
	}

	@SuppressWarnings("rawtypes")
	public void setDebitoCobradoCategorias(Set debitoCobradoCategorias) {
		this.debitoCobradoCategorias = debitoCobradoCategorias;
	}

	public String toString() {
		return "debitoCobrado";
	}

	@Override
	public Filtro retornaFiltro() {
		FiltroDebitoCobrado filtro = new FiltroDebitoCobrado();

		filtro.adicionarParametro(new ParametroSimples(FiltroDebitoCobrado.CODIGO, this.getId()));
		return filtro;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof DebitoCobrado))
			return false;
		DebitoCobrado castOther = (DebitoCobrado) other;
		return new EqualsBuilder().append(this.getId(), castOther.getId()).isEquals();
	}

	@Override
	public void initializeLazy() {
		if (this.getDebitoTipo() != null) {
			this.getDebitoTipo().initializeLazy();
		}

	}

	public String getDescricao() {
		String desc = "";
		if (getDebitoTipo() != null) {
			desc = getDebitoTipo().getDescricao();
		}
		return desc;
	}

	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao();
	}

	public Short getNumeroParcelaBonus() {
		return numeroParcelaBonus;
	}

	public void setNumeroParcelaBonus(Short numeroParcelaBonus) {
		this.numeroParcelaBonus = numeroParcelaBonus;
	}

	public short getNumeroTotalParcelasMenosBonus() {
		short retorno = getNumeroPrestacao();

		if (getNumeroParcelaBonus() != null) {
			retorno = Short.parseShort("" + (retorno - getNumeroParcelaBonus().shortValue()));
		}

		return retorno;
	}

	public DebitoACobrarGeral getDebitoACobrarGeral() {
		return debitoACobrarGeral;
	}

	public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) {
		this.debitoACobrarGeral = debitoACobrarGeral;
	}
}
