package gcom.faturamento.debito;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cobranca.ParcelamentoGrupo;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.IConta;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.LancamentoItemContabil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class DebitoCobradoHistorico implements Serializable, IDebitoCobrado {
	private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Date debitoCobrado;
    private Integer codigoSetorComercial;
    private Integer numeroQuadra;
    private Short numeroLote;
    private Short numeroSubLote;
    private Integer anoMesReferenciaDebito;
    private Integer anoMesCobrancaDebito;
    private BigDecimal valorPrestacao;
    private Short numeroPrestacao;
    private Short numeroPrestacaoDebito;
    private Short numeroParcelaBonus;
    private Date ultimaAlteracao;
    private FinanciamentoTipo financiamentoTipo;
    private Quadra quadra;
    private Localidade localidade;
    private ContaHistorico contaHistorico;
    private gcom.faturamento.debito.DebitoTipo debitoTipo;
    private LancamentoItemContabil lancamentoItemContabil;
    private ParcelamentoGrupo parcelamentoGrupo;
    private DebitoACobrarGeral debitoACobrarGeral;

    public DebitoCobradoHistorico(Date debitoCobrado,  Integer codigoSetorComercial, Integer numeroQuadra, Short numeroLote, Short numeroSubLote, Integer anoMesReferenciaDebito, Integer anoMesCobrancaDebito, BigDecimal valorPrestacao, Short numeroPrestacao, Short numeroPrestacaoDebito, Date ultimaAlteracao, FinanciamentoTipo financiamentoTipo, Quadra quadra, Localidade localidade, ContaHistorico contaHistorico, gcom.faturamento.debito.DebitoTipo debitoTipo, LancamentoItemContabil lancamentoItemContabil, ParcelamentoGrupo parcelamentoGrupo) {
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
        this.financiamentoTipo = financiamentoTipo;
        this.quadra = quadra;
        this.localidade = localidade;
        this.contaHistorico = contaHistorico;
        this.debitoTipo = debitoTipo;
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.parcelamentoGrupo = parcelamentoGrupo;
    }

    public DebitoCobradoHistorico() {
    }

    public DebitoCobradoHistorico(Date debitoCobrado,  FinanciamentoTipo financiamentoTipo, Quadra quadra, Localidade localidade, ContaHistorico contaHistorico, gcom.faturamento.debito.DebitoTipo debitoTipo, LancamentoItemContabil lancamentoItemContabil, ParcelamentoGrupo parcelamentoGrupo) {
        this.debitoCobrado = debitoCobrado;
        this.financiamentoTipo = financiamentoTipo;
        this.quadra = quadra;
        this.localidade = localidade;
        this.contaHistorico = contaHistorico;
        this.debitoTipo = debitoTipo;
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.parcelamentoGrupo = parcelamentoGrupo;
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

    public ContaHistorico getContaHistorico() {
        return this.contaHistorico;
    }

    public void setContaHistorico(ContaHistorico contaHistorico) {
        this.contaHistorico = contaHistorico;
    }

    public gcom.faturamento.debito.DebitoTipo getDebitoTipo() {
        return this.debitoTipo;
    }

    public void setDebitoTipo(gcom.faturamento.debito.DebitoTipo debitoTipo) {
        this.debitoTipo = debitoTipo;
    }

    public LancamentoItemContabil getLancamentoItemContabil() {
        return this.lancamentoItemContabil;
    }

    public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil) {
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    public ParcelamentoGrupo getParcelamentoGrupo() {
        return this.parcelamentoGrupo;
    }

    public void setParcelamentoGrupo(ParcelamentoGrupo parcelamentoGrupo) {
        this.parcelamentoGrupo = parcelamentoGrupo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public Short getNumeroParcelaBonus() {
        return numeroParcelaBonus;
    }

    public void setNumeroParcelaBonus(Short numeroParcelaBonus) {
        this.numeroParcelaBonus = numeroParcelaBonus;
    }
    
    public DebitoACobrarGeral getDebitoACobrarGeral() {
		return debitoACobrarGeral;
	}

	public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) {
		this.debitoACobrarGeral = debitoACobrarGeral;
	}

    public short getNumeroTotalParcelasMenosBonus() {
        short retorno = getNumeroPrestacao();
        
        if (getNumeroParcelaBonus() != null){
            retorno = Short.parseShort(""+ (retorno - getNumeroParcelaBonus().shortValue()));
        }
             
        return retorno;
    }

	public IConta getConta() {
		return contaHistorico;
	}

	public void setConta(IConta conta) {
		this.contaHistorico = new ContaHistorico(conta.getId());
	}

}
