package gcom.faturamento.conta;

import gcom.faturamento.ImpostoTipo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ContaImpostosDeduzidosHistorico implements Serializable, IContaImpostosDeduzidos {
	private static final long serialVersionUID = 1L;

	private Integer id;
    private BigDecimal valorImposto;
    private BigDecimal percentualAliquota;
    private Date ultimaAlteracao;
    private BigDecimal valorBaseCalculo;
    private ImpostoTipo impostoTipo;
    private ContaHistorico contaHistorico;

    public ContaImpostosDeduzidosHistorico(Integer id, BigDecimal valorImposto, BigDecimal percentualAliquota, Date ultimaAlteracao,BigDecimal valorBaseCalculo ,ImpostoTipo impostoTipo, ContaHistorico contaHistorico) {
        this.id = id;
        this.valorImposto = valorImposto;
        this.percentualAliquota = percentualAliquota;
        this.ultimaAlteracao = ultimaAlteracao;
        this.valorBaseCalculo = valorBaseCalculo;
        this.impostoTipo = impostoTipo;
        this.contaHistorico = contaHistorico;
    }

    public ContaImpostosDeduzidosHistorico() {
    }
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public gcom.faturamento.conta.ContaHistorico getContaHistorico() {
        return contaHistorico;
    }

    public void setContaHistorico(gcom.faturamento.conta.ContaHistorico contaHistorico) {
        this.contaHistorico = contaHistorico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public gcom.faturamento.ImpostoTipo getImpostoTipo() {
        return impostoTipo;
    }

    public void setImpostoTipo(gcom.faturamento.ImpostoTipo impostoTipo) {
        this.impostoTipo = impostoTipo;
    }

    public BigDecimal getPercentualAliquota() {
        return percentualAliquota;
    }

    public void setPercentualAliquota(BigDecimal percentualAliquota) {
        this.percentualAliquota = percentualAliquota;
    }

    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public BigDecimal getValorImposto() {
        return valorImposto;
    }

    public void setValorImposto(BigDecimal valorImposto) {
        this.valorImposto = valorImposto;
    }

    public BigDecimal getValorBaseCalculo() {
        return valorBaseCalculo;
    }

    public void setValorBaseCalculo(BigDecimal valorBaseCalculo) {
        this.valorBaseCalculo = valorBaseCalculo;
    }

	public IConta getConta() {
		return new Conta(this.contaHistorico.getId());
	}

	public void setConta(IConta conta) {
		this.contaHistorico = new ContaHistorico(conta.getId());
	}

}
