package gcom.faturamento.conta;

import gcom.faturamento.ImpostoTipo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContaImpostosDeduzidos implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private BigDecimal valorBaseCalculo;

    /** nullable persistent field */
    //private BigDecimal valorIR;

    /** nullable persistent field */
    //private BigDecimal valorCS11;

    /** nullable persistent field */
    //private BigDecimal valorCofins;

    /** nullable persistent field */
    //private BigDecimal valorPisPasep;

    private BigDecimal valorImposto;

    private BigDecimal percentualAliquota;
    
    private ImpostoTipo impostoTipo; 

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.faturamento.conta.Conta conta;

    /** full constructor */
    public ContaImpostosDeduzidos(BigDecimal valorBaseCalculo, Date ultimaAlteracao, gcom.faturamento.conta.Conta conta,BigDecimal valorImposto, BigDecimal percentualAliquota , ImpostoTipo impostoTipo) {
        this.valorBaseCalculo = valorBaseCalculo;
//        this.valorIR = valorIR;
//        this.valorCS11 = valorCS11;
//        this.valorCofins = valorCofins;
//        this.valorPisPasep = valorPisPasep;
        this.ultimaAlteracao = ultimaAlteracao;
        this.conta = conta;
        this.valorImposto = valorImposto;
        this.percentualAliquota = percentualAliquota;
        this.impostoTipo = impostoTipo; 
    }

    /** default constructor */
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
/*
    public BigDecimal getValorIR() {
        return this.valorIR;
    }

    public void setValorIR(BigDecimal valorIR) {
        this.valorIR = valorIR;
    }

    public BigDecimal getValorCS11() {
        return this.valorCS11;
    }

    public void setValorCS11(BigDecimal valorCS11) {
        this.valorCS11 = valorCS11;
    }

    public BigDecimal getValorCofins() {
        return this.valorCofins;
    }

    public void setValorCofins(BigDecimal valorCofins) {
        this.valorCofins = valorCofins;
    }

    public BigDecimal getValorPisPasep() {
        return this.valorPisPasep;
    }

    public void setValorPisPasep(BigDecimal valorPisPasep) {
        this.valorPisPasep = valorPisPasep;
    }
*/
    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

  
    public gcom.faturamento.conta.Conta getConta() {
		return conta;
	}

	public void setConta(gcom.faturamento.conta.Conta conta) {
		this.conta = conta;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo impostoTipo.
	 */
	public ImpostoTipo getImpostoTipo() {
		return impostoTipo;
	}

	/**
	 * @param impostoTipo O impostoTipo a ser setado.
	 */
	public void setImpostoTipo(ImpostoTipo impostoTipo) {
		this.impostoTipo = impostoTipo;
	}

	/**
	 * @return Retorna o campo percentualAliquota.
	 */
	public BigDecimal getPercentualAliquota() {
		return percentualAliquota;
	}

	/**
	 * @param percentualAliquota O percentualAliquota a ser setado.
	 */
	public void setPercentualAliquota(BigDecimal percentualAliquota) {
		this.percentualAliquota = percentualAliquota;
	}

	/**
	 * @return Retorna o campo valorImposto.
	 */
	public BigDecimal getValorImposto() {
		return valorImposto;
	}

	/**
	 * @param valorImposto O valorImposto a ser setado.
	 */
	public void setValorImposto(BigDecimal valorImposto) {
		this.valorImposto = valorImposto;
	}
}
