package gcom.gerencial.faturamento;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoRefaturamentoOutroPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer idResumoRefaturamentoAguaEsgoto;

    /** identifier field */
    private Integer idDocumentoTipo;

    /** identifier field */
    private Integer idFinanciamentoTipo;

    /** identifier field */
    private Integer idLancamentoItemContabil;

    /** full constructor */
    public UnResumoRefaturamentoOutroPK(Integer idResumoRefaturamentoAguaEsgoto, Integer idDocumentoTipo, Integer idFinanciamentoTipo, Integer idLancamentoItemContabil) {
        this.idResumoRefaturamentoAguaEsgoto = idResumoRefaturamentoAguaEsgoto;
        this.idDocumentoTipo = idDocumentoTipo;
        this.idFinanciamentoTipo = idFinanciamentoTipo;
        this.idLancamentoItemContabil = idLancamentoItemContabil;
    }

    /** default constructor */
    public UnResumoRefaturamentoOutroPK() {
    }

    public Integer getIdResumoRefaturamentoAguaEsgoto() {
        return this.idResumoRefaturamentoAguaEsgoto;
    }

    public void setIdResumoRefaturamentoAguaEsgoto(Integer idResumoRefaturamentoAguaEsgoto) {
        this.idResumoRefaturamentoAguaEsgoto = idResumoRefaturamentoAguaEsgoto;
    }

    public Integer getIdDocumentoTipo() {
        return this.idDocumentoTipo;
    }

    public void setIdDocumentoTipo(Integer idDocumentoTipo) {
        this.idDocumentoTipo = idDocumentoTipo;
    }

    public Integer getIdFinanciamentoTipo() {
        return this.idFinanciamentoTipo;
    }

    public void setIdFinanciamentoTipo(Integer idFinanciamentoTipo) {
        this.idFinanciamentoTipo = idFinanciamentoTipo;
    }

    public Integer getIdLancamentoItemContabil() {
        return this.idLancamentoItemContabil;
    }

    public void setIdLancamentoItemContabil(Integer idLancamentoItemContabil) {
        this.idLancamentoItemContabil = idLancamentoItemContabil;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("idResumoRefaturamentoAguaEsgoto", getIdResumoRefaturamentoAguaEsgoto())
            .append("idDocumentoTipo", getIdDocumentoTipo())
            .append("idFinanciamentoTipo", getIdFinanciamentoTipo())
            .append("idLancamentoItemContabil", getIdLancamentoItemContabil())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UnResumoRefaturamentoOutroPK) ) return false;
        UnResumoRefaturamentoOutroPK castOther = (UnResumoRefaturamentoOutroPK) other;
        return new EqualsBuilder()
            .append(this.getIdResumoRefaturamentoAguaEsgoto(), castOther.getIdResumoRefaturamentoAguaEsgoto())
            .append(this.getIdDocumentoTipo(), castOther.getIdDocumentoTipo())
            .append(this.getIdFinanciamentoTipo(), castOther.getIdFinanciamentoTipo())
            .append(this.getIdLancamentoItemContabil(), castOther.getIdLancamentoItemContabil())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdResumoRefaturamentoAguaEsgoto())
            .append(getIdDocumentoTipo())
            .append(getIdFinanciamentoTipo())
            .append(getIdLancamentoItemContabil())
            .toHashCode();
    }

}
