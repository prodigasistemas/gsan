package gcom.faturamento.credito;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.faturamento.conta.ContaHistorico;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CreditoRealizadoHistorico implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date creditoRealizado;

    /** persistent field */
    //private Date geracaoHistoricoCredito;

    /** nullable persistent field */
    private Integer codigoSetorComercial;

    /** nullable persistent field */
    private Integer numeroQuadra;

    /** nullable persistent field */
    private Short numeroLote;

    /** nullable persistent field */
    private Short numeroSubLote;

    /** nullable persistent field */
    private Integer anoMesReferenciaCredito;

    /** nullable persistent field */
    private Integer anoMesCobrancaCredito;

    /** nullable persistent field */
    private BigDecimal valorCredito;

    /** nullable persistent field */
    private Short numeroPrestacao;

    /** nullable persistent field */
    private Short numeroPrestacaoCredito;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    private Short numeroParcelaBonus;

    /** persistent field */
    private Quadra quadra;

    /** persistent field */
    private Localidade localidade;

    /** nullable persistent field */
    private gcom.faturamento.credito.CreditoTipo creditoTipo;

    /** persistent field */
    private ContaHistorico contaHistorico;

    /** persistent field */
    private LancamentoItemContabil lancamentoItemContabil;
    
    /** persistent field */
    private CreditoOrigem creditoOrigem;
    
    /** persistent field */
    private CreditoARealizarGeral creditoARealizarGeral;

    /** full constructor */
    public CreditoRealizadoHistorico(Date creditoRealizado, Integer codigoSetorComercial, Integer numeroQuadra, Short numeroLote, Short numeroSubLote, Integer anoMesReferenciaCredito, Integer anoMesCobrancaCredito, BigDecimal valorCredito, Short numeroPrestacao, Short numeroPrestacaoCredito, Date ultimaAlteracao, Quadra quadra, Localidade localidade, CreditoTipo creditoTipo, ContaHistorico contaHistorico, LancamentoItemContabil lancamentoItemContabil, CreditoOrigem creditoOrigem) {
        this.creditoRealizado = creditoRealizado;
        //this.geracaoHistoricoCredito = geracaoHistoricoCredito;
        this.codigoSetorComercial = codigoSetorComercial;
        this.numeroQuadra = numeroQuadra;
        this.numeroLote = numeroLote;
        this.numeroSubLote = numeroSubLote;
        this.anoMesReferenciaCredito = anoMesReferenciaCredito;
        this.anoMesCobrancaCredito = anoMesCobrancaCredito;
        this.valorCredito = valorCredito;
        this.numeroPrestacao = numeroPrestacao;
        this.numeroPrestacaoCredito = numeroPrestacaoCredito;
        this.ultimaAlteracao = ultimaAlteracao;
        this.quadra = quadra;
        this.localidade = localidade;
        this.creditoTipo = creditoTipo;
        this.contaHistorico = contaHistorico;
        this.lancamentoItemContabil = lancamentoItemContabil;
        this.creditoOrigem = creditoOrigem;
    }

    /** default constructor */
    public CreditoRealizadoHistorico() {
    }

    /** minimal constructor */
    public CreditoRealizadoHistorico(Date creditoRealizado, Quadra quadra, Localidade localidade, ContaHistorico contaHistorico, LancamentoItemContabil lancamentoItemContabil) {
        this.creditoRealizado = creditoRealizado;
        //this.geracaoHistoricoCredito = geracaoHistoricoCredito;
        this.quadra = quadra;
        this.localidade = localidade;
        this.contaHistorico = contaHistorico;
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreditoRealizado() {
        return this.creditoRealizado;
    }

    public void setCreditoRealizado(Date creditoRealizado) {
        this.creditoRealizado = creditoRealizado;
    }

    /*public Date getGeracaoHistoricoCredito() {
        return this.geracaoHistoricoCredito;
    }

    public void setGeracaoHistoricoCredito(Date geracaoHistoricoCredito) {
        this.geracaoHistoricoCredito = geracaoHistoricoCredito;
    }*/

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

    public Integer getAnoMesReferenciaCredito() {
        return this.anoMesReferenciaCredito;
    }

    public void setAnoMesReferenciaCredito(Integer anoMesReferenciaCredito) {
        this.anoMesReferenciaCredito = anoMesReferenciaCredito;
    }

    public Integer getAnoMesCobrancaCredito() {
        return this.anoMesCobrancaCredito;
    }

    public void setAnoMesCobrancaCredito(Integer anoMesCobrancaCredito) {
        this.anoMesCobrancaCredito = anoMesCobrancaCredito;
    }

    /*public BigDecimal getValorPrestacao() {
        return this.valorPrestacao;
    }

    public void setValorPrestacao(BigDecimal valorPrestacao) {
        this.valorPrestacao = valorPrestacao;
    }*/

    public Short getNumeroPrestacao() {
        return this.numeroPrestacao;
    }

    public void setNumeroPrestacao(Short numeroPrestacao) {
        this.numeroPrestacao = numeroPrestacao;
    }

    public Short getNumeroPrestacaoCredito() {
        return this.numeroPrestacaoCredito;
    }

    public void setNumeroPrestacaoCredito(Short numeroPrestacaoCredito) {
        this.numeroPrestacaoCredito = numeroPrestacaoCredito;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
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

    public gcom.faturamento.credito.CreditoTipo getCreditoTipo() {
        return this.creditoTipo;
    }

    public void setCreditoTipo(gcom.faturamento.credito.CreditoTipo creditoTipo) {
        this.creditoTipo = creditoTipo;
    }

    public ContaHistorico getContaHistorico() {
        return this.contaHistorico;
    }

    public void setContaHistorico(ContaHistorico contaHistorico) {
        this.contaHistorico = contaHistorico;
    }

    public LancamentoItemContabil getLancamentoItemContabil() {
        return this.lancamentoItemContabil;
    }

    public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil) {
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    /**
     * @return Retorna o campo creditoOrigem.
     */
    public CreditoOrigem getCreditoOrigem() {
        return creditoOrigem;
    }

    /**
     * @param creditoOrigem O creditoOrigem a ser setado.
     */
    public void setCreditoOrigem(CreditoOrigem creditoOrigem) {
        this.creditoOrigem = creditoOrigem;
    }

	public BigDecimal getValorCredito() {
		return valorCredito;
	}

	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}

    public Short getNumeroParcelaBonus() {
        return numeroParcelaBonus;
    }

    public void setNumeroParcelaBonus(Short numeroParcelaBonus) {
        this.numeroParcelaBonus = numeroParcelaBonus;
    }
    
    

	public CreditoARealizarGeral getCreditoARealizarGeral() {
		return creditoARealizarGeral;
	}

	public void setCreditoARealizarGeral(CreditoARealizarGeral creditoARealizarGeral) {
		this.creditoARealizarGeral = creditoARealizarGeral;
	}

	/**
     * @author Vivianne Sousa
     * @created 05/03/2008
    */
    public short getNumeroTotalParcelasMenosBonus() {
        short retorno = getNumeroPrestacao();
        
        if (getNumeroParcelaBonus() != null){
            retorno = Short.parseShort(""+ (retorno - getNumeroParcelaBonus().shortValue()));
        }
             
        return retorno;
    }
}
