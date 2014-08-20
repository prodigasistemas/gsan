package gcom.faturamento.conta;

import gcom.faturamento.ImpostoTipo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ContaImpostosDeduzidos implements Serializable, IContaImpostosDeduzidos {
	private static final long serialVersionUID = 1L;
    
    private Integer id;
    private BigDecimal valorBaseCalculo;
    private BigDecimal valorImposto;
    private BigDecimal percentualAliquota;
    private ImpostoTipo impostoTipo; 
    private Date ultimaAlteracao;
    private Conta conta;

    public ContaImpostosDeduzidos(BigDecimal valorBaseCalculo, Date ultimaAlteracao, Conta conta,BigDecimal valorImposto, BigDecimal percentualAliquota , ImpostoTipo impostoTipo) {
        this.valorBaseCalculo = valorBaseCalculo;
        this.ultimaAlteracao = ultimaAlteracao;
        this.conta = conta;
        this.valorImposto = valorImposto;
        this.percentualAliquota = percentualAliquota;
        this.impostoTipo = impostoTipo; 
    }

    public ContaImpostosDeduzidos() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorBaseCalculo() {
        return this.valorBaseCalculo;
    }

    public void setValorBaseCalculo(BigDecimal valorBaseCalculo) {
        this.valorBaseCalculo = valorBaseCalculo;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

  
    public Conta getConta() {
		return conta;
	}

	public void setConta(IConta conta) {
		this.conta = new Conta(conta.getId());
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public ImpostoTipo getImpostoTipo() {
		return impostoTipo;
	}

	public void setImpostoTipo(ImpostoTipo impostoTipo) {
		this.impostoTipo = impostoTipo;
	}

	public BigDecimal getPercentualAliquota() {
		return percentualAliquota;
	}

	public void setPercentualAliquota(BigDecimal percentualAliquota) {
		this.percentualAliquota = percentualAliquota;
	}

	public BigDecimal getValorImposto() {
		return valorImposto;
	}

	public void setValorImposto(BigDecimal valorImposto) {
		this.valorImposto = valorImposto;
	}
}
