package gcom.gerencial.faturamento;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoFaturamentoCreditoPK implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer resumoFaturamento;

    /** identifier field */
    private Integer creditoOrigem;

    /** identifier field */
    private Integer lancamentoItemContabil;

    /** full constructor */
    public UnResumoFaturamentoCreditoPK(Integer resumoFaturamento, Integer creditoOrigem, Integer lancamentoItemContabil) {
        this.resumoFaturamento = resumoFaturamento;
        this.creditoOrigem = creditoOrigem;
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    /** default constructor */
    public UnResumoFaturamentoCreditoPK() {
    }

    public Integer getResumoFaturamento() {
        return this.resumoFaturamento;
    }

    public void setResumoFaturamento(Integer resumoFaturamento) {
        this.resumoFaturamento = resumoFaturamento;
    }

    public Integer getCreditoOrigem() {
        return this.creditoOrigem;
    }

    public void setCreditoOrigem(Integer creditoOrigem) {
        this.creditoOrigem = creditoOrigem;
    }

    public Integer getLancamentoItemContabil() {
        return this.lancamentoItemContabil;
    }

    public void setLancamentoItemContabil(Integer lancamentoItemContabil) {
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("resumoFaturamento", getResumoFaturamento())
            .append("creditoOrigem", getCreditoOrigem())
            .append("lancamentoItemContabil", getLancamentoItemContabil())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UnResumoFaturamentoCreditoPK) ) return false;
        UnResumoFaturamentoCreditoPK castOther = (UnResumoFaturamentoCreditoPK) other;
        return new EqualsBuilder()
            .append(this.getResumoFaturamento(), castOther.getResumoFaturamento())
            .append(this.getCreditoOrigem(), castOther.getCreditoOrigem())
            .append(this.getLancamentoItemContabil(), castOther.getLancamentoItemContabil())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getResumoFaturamento())
            .append(getCreditoOrigem())
            .append(getLancamentoItemContabil())
            .toHashCode();
    }

}
