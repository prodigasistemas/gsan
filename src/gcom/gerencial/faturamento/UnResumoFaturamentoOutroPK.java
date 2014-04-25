package gcom.gerencial.faturamento;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoFaturamentoOutroPK implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer resumoFaturamento;

    /** identifier field */
    private Integer documentoTipo;

    /** identifier field */
    private Integer financiamentoTipo;

    /** identifier field */
    private Integer lancamentoItemContabil;

    /** full constructor */
    public UnResumoFaturamentoOutroPK(Integer resumoFaturamento, Integer documentoTipo, Integer financiamentoTipo, Integer lancamentoItemContabil) {
        this.resumoFaturamento = resumoFaturamento;
        this.documentoTipo = documentoTipo;
        this.financiamentoTipo = financiamentoTipo;
        this.lancamentoItemContabil = lancamentoItemContabil;
    }

    /** default constructor */
    public UnResumoFaturamentoOutroPK() {
    }

    public Integer getResumoFaturamento() {
        return this.resumoFaturamento;
    }

    public void setResumoFaturamento(Integer resumoFaturamento) {
        this.resumoFaturamento = resumoFaturamento;
    }

    public Integer getDocumentoTipo() {
        return this.documentoTipo;
    }

    public void setDocumentoTipo(Integer documentoTipo) {
        this.documentoTipo = documentoTipo;
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
            .append("resumoFaturamento", getResumoFaturamento())
            .append("documentoTipo", getDocumentoTipo())
            .append("financiamentoTipo", getFinanciamentoTipo())
            .append("lancamentoItemContabil", getLancamentoItemContabil())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UnResumoFaturamentoOutroPK) ) return false;
        UnResumoFaturamentoOutroPK castOther = (UnResumoFaturamentoOutroPK) other;
        return new EqualsBuilder()
            .append(this.getResumoFaturamento(), castOther.getResumoFaturamento())
            .append(this.getDocumentoTipo(), castOther.getDocumentoTipo())
            .append(this.getFinanciamentoTipo(), castOther.getFinanciamentoTipo())
            .append(this.getLancamentoItemContabil(), castOther.getLancamentoItemContabil())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getResumoFaturamento())
            .append(getDocumentoTipo())
            .append(getFinanciamentoTipo())
            .append(getLancamentoItemContabil())
            .toHashCode();
    }

}
