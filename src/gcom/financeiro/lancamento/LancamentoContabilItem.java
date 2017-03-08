package gcom.financeiro.lancamento;

import gcom.financeiro.ContaContabil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

public class LancamentoContabilItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
    private Short indicadorDebitoCredito;
    private BigDecimal valorLancamento;
    private Date ultimaAlteracao;
    private String descricaoHistorico;
    private Integer codigoTerceiro;
    private Date dataLancamento;

    private LancamentoContabil lancamentoContabil;
    private ContaContabil contaContabil;

    public LancamentoContabilItem(Short indicadorDebitoCredito, BigDecimal valorLancamento, Date ultimaAlteracao, gcom.financeiro.lancamento.LancamentoContabil lancamentoContabil, ContaContabil contaContabil) {
        this.indicadorDebitoCredito = indicadorDebitoCredito;
        this.valorLancamento = valorLancamento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.lancamentoContabil = lancamentoContabil;
        this.contaContabil = contaContabil;
    }

    public LancamentoContabilItem(Short indicadorDebitoCredito, BigDecimal valorLancamento, String descricaoHistorico, Date ultimaAlteracao, gcom.financeiro.lancamento.LancamentoContabil lancamentoContabil, ContaContabil contaContabil) {
        this.indicadorDebitoCredito = indicadorDebitoCredito;
        this.valorLancamento = valorLancamento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.lancamentoContabil = lancamentoContabil;
        this.contaContabil = contaContabil;
        this.descricaoHistorico = descricaoHistorico;
    }

	public String getDescricaoHistorico() {
		return descricaoHistorico;
	}

	public void setDescricaoHistorico(String descricaoHistorico) {
		this.descricaoHistorico = descricaoHistorico;
	}

    public LancamentoContabilItem() {
    }

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
