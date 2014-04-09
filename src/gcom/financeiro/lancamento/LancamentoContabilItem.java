package gcom.financeiro.lancamento;

import gcom.financeiro.ContaContabil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class LancamentoContabilItem implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Short indicadorDebitoCredito;

    /** nullable persistent field */
    private BigDecimal valorLancamento;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    private String descricaoHistorico;

    /** persistent field */
    private gcom.financeiro.lancamento.LancamentoContabil lancamentoContabil;

    /** persistent field */
    private ContaContabil contaContabil;
    
    private Integer codigoTerceiro;
    
    private Date dataLancamento;

    /** full constructor */
    public LancamentoContabilItem(Short indicadorDebitoCredito, BigDecimal valorLancamento, Date ultimaAlteracao, gcom.financeiro.lancamento.LancamentoContabil lancamentoContabil, ContaContabil contaContabil) {
        this.indicadorDebitoCredito = indicadorDebitoCredito;
        this.valorLancamento = valorLancamento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.lancamentoContabil = lancamentoContabil;
        this.contaContabil = contaContabil;
    }

    /** full constructor */
    public LancamentoContabilItem(Short indicadorDebitoCredito, BigDecimal valorLancamento, String descricaoHistorico, Date ultimaAlteracao, gcom.financeiro.lancamento.LancamentoContabil lancamentoContabil, ContaContabil contaContabil) {
        this.indicadorDebitoCredito = indicadorDebitoCredito;
        this.valorLancamento = valorLancamento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.lancamentoContabil = lancamentoContabil;
        this.contaContabil = contaContabil;
        this.descricaoHistorico = descricaoHistorico;
    }

    /**
	 * @return Retorna o campo descricaoHistorico.
	 */
	public String getDescricaoHistorico() {
		return descricaoHistorico;
	}

	/**
	 * @param descricaoHistorico O descricaoHistorico a ser setado.
	 */
	public void setDescricaoHistorico(String descricaoHistorico) {
		this.descricaoHistorico = descricaoHistorico;
	}

	/** default constructor */
    public LancamentoContabilItem() {
    }

    /** minimal constructor */
    public LancamentoContabilItem(gcom.financeiro.lancamento.LancamentoContabil lancamentoContabil, ContaContabil contaContabil) {
        this.lancamentoContabil = lancamentoContabil;
        this.contaContabil = contaContabil;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getIndicadorDebitoCredito() {
        return this.indicadorDebitoCredito;
    }

    public void setIndicadorDebitoCredito(Short indicadorDebitoCredito) {
        this.indicadorDebitoCredito = indicadorDebitoCredito;
    }

    public BigDecimal getValorLancamento() {
        return this.valorLancamento;
    }

    public void setValorLancamento(BigDecimal valorLancamento) {
        this.valorLancamento = valorLancamento;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.financeiro.lancamento.LancamentoContabil getLancamentoContabil() {
        return this.lancamentoContabil;
    }

    public void setLancamentoContabil(gcom.financeiro.lancamento.LancamentoContabil lancamentoContabil) {
        this.lancamentoContabil = lancamentoContabil;
    }

    public ContaContabil getContaContabil() {
        return this.contaContabil;
    }

    public void setContaContabil(ContaContabil contaContabil) {
        this.contaContabil = contaContabil;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Integer getCodigoTerceiro() {
		return codigoTerceiro;
	}

	public void setCodigoTerceiro(Integer codigoTerceiro) {
		this.codigoTerceiro = codigoTerceiro;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
}
