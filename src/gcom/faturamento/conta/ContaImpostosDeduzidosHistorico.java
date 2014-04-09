package gcom.faturamento.conta;

import gcom.faturamento.ImpostoTipo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContaImpostosDeduzidosHistorico implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private BigDecimal valorImposto;

    /** persistent field */
    private BigDecimal percentualAliquota;

    /** persistent field */
    private Date ultimaAlteracao;
    
    /** persistent field */
    private BigDecimal valorBaseCalculo;

    /** persistent field */
    private gcom.faturamento.ImpostoTipo impostoTipo;

    /** persistent field */
    private gcom.faturamento.conta.ContaHistorico contaHistorico;

    /** full constructor */
    public ContaImpostosDeduzidosHistorico(Integer id, BigDecimal valorImposto, BigDecimal percentualAliquota, Date ultimaAlteracao,BigDecimal valorBaseCalculo ,ImpostoTipo impostoTipo, ContaHistorico contaHistorico) {
        this.id = id;
        this.valorImposto = valorImposto;
        this.percentualAliquota = percentualAliquota;
        this.ultimaAlteracao = ultimaAlteracao;
        this.valorBaseCalculo = valorBaseCalculo;
        this.impostoTipo = impostoTipo;
        this.contaHistorico = contaHistorico;
    }

    /** default constructor */
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

}
