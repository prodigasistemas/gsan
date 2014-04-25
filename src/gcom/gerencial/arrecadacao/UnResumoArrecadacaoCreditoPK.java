package gcom.gerencial.arrecadacao;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoArrecadacaoCreditoPK implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer resumoArrecadacao;

    /** identifier field */
    private Integer creditoOrigem;

    /** identifier field */
    private Integer lancamentoItemContabil;

    /** full constructor */
    public UnResumoArrecadacaoCreditoPK(Integer resumoArrecadacao, Integer creditoOrigem, Integer lancamentoItemContabil) {
        this.resumoArrecadacao = resumoArrecadacao;
        this.creditoOrigem = creditoOrigem;
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    /** default constructor */
    public UnResumoArrecadacaoCreditoPK() {
    }

    public Integer getResumoArrecadacao() {
        return this.resumoArrecadacao;
    }

    public void setResumoArrecadacao(Integer resumoArrecadacao) {
        this.resumoArrecadacao = resumoArrecadacao;
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
            .append("resumoArrecadacao", getResumoArrecadacao())
            .append("creditoOrigem", getCreditoOrigem())
            .append("lancamentoItemContabil", getLancamentoItemContabil())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UnResumoArrecadacaoCreditoPK) ) return false;
        UnResumoArrecadacaoCreditoPK castOther = (UnResumoArrecadacaoCreditoPK) other;
        return new EqualsBuilder()
            .append(this.getResumoArrecadacao(), castOther.getResumoArrecadacao())
            .append(this.getCreditoOrigem(), castOther.getCreditoOrigem())
            .append(this.getLancamentoItemContabil(), castOther.getLancamentoItemContabil())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getResumoArrecadacao())
            .append(getCreditoOrigem())
            .append(getLancamentoItemContabil())
            .toHashCode();
    }

}
