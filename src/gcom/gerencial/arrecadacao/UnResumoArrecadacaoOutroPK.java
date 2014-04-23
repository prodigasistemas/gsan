package gcom.gerencial.arrecadacao;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoArrecadacaoOutroPK implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer resumoArrecadacao;

    /** identifier field */
    private Integer financiamentoTipo;

    /** identifier field */
    private Integer lancamentoItemContabil;

    /** full constructor */
    public UnResumoArrecadacaoOutroPK(Integer resumoArrecadacao, Integer financiamentoTipo, Integer lancamentoItemContabil) {
        this.resumoArrecadacao = resumoArrecadacao;
        this.financiamentoTipo = financiamentoTipo;
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    /** default constructor */
    public UnResumoArrecadacaoOutroPK() {
    }

    public Integer getResumoArrecadacao() {
        return this.resumoArrecadacao;
    }

    public void setResumoArrecadacao(Integer resumoArrecadacao) {
        this.resumoArrecadacao = resumoArrecadacao;
    }

    public Integer getFinanciamentoTipo() {
        return this.financiamentoTipo;
    }

    public void setFinanciamentoTipo(Integer financiamentoTipo) {
        this.financiamentoTipo = financiamentoTipo;
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
            .append("financiamentoTipo", getFinanciamentoTipo())
            .append("lancamentoItemContabil", getLancamentoItemContabil())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UnResumoArrecadacaoOutroPK) ) return false;
        UnResumoArrecadacaoOutroPK castOther = (UnResumoArrecadacaoOutroPK) other;
        return new EqualsBuilder()
            .append(this.getResumoArrecadacao(), castOther.getResumoArrecadacao())
            .append(this.getFinanciamentoTipo(), castOther.getFinanciamentoTipo())
            .append(this.getLancamentoItemContabil(), castOther.getLancamentoItemContabil())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getResumoArrecadacao())
            .append(getFinanciamentoTipo())
            .append(getLancamentoItemContabil())
            .toHashCode();
    }

}
