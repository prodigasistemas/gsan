package gcom.gerencial.faturamento;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UnResumoRefaturamentoCreditoPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer IdResumoRefaturamentoAguaEsgoto;

    /** identifier field */
    private Integer IdCreditoOrigem;

    /** identifier field */
    private Integer idLancamentoItemContabil;

    /** full constructor */
    public UnResumoRefaturamentoCreditoPK(Integer IdResumoRefaturamentoAguaEsgoto, Integer IdCreditoOrigem, Integer idLancamentoItemContabil) {
        this.IdResumoRefaturamentoAguaEsgoto = IdResumoRefaturamentoAguaEsgoto;
        this.IdCreditoOrigem = IdCreditoOrigem;
        this.idLancamentoItemContabil = idLancamentoItemContabil;
    }

    /** default constructor */
    public UnResumoRefaturamentoCreditoPK() {
    }

    public Integer getIdResumoRefaturamentoAguaEsgoto() {
        return this.IdResumoRefaturamentoAguaEsgoto;
    }

    public void setIdResumoRefaturamentoAguaEsgoto(Integer IdResumoRefaturamentoAguaEsgoto) {
        this.IdResumoRefaturamentoAguaEsgoto = IdResumoRefaturamentoAguaEsgoto;
    }

    public Integer getIdCreditoOrigem() {
        return this.IdCreditoOrigem;
    }

    public void setIdCreditoOrigem(Integer IdCreditoOrigem) {
        this.IdCreditoOrigem = IdCreditoOrigem;
    }

    public Integer getIdLancamentoItemContabil() {
        return this.idLancamentoItemContabil;
    }

    public void setIdLancamentoItemContabil(Integer idLancamentoItemContabil) {
        this.idLancamentoItemContabil = idLancamentoItemContabil;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("IdResumoRefaturamentoAguaEsgoto", getIdResumoRefaturamentoAguaEsgoto())
            .append("IdCreditoOrigem", getIdCreditoOrigem())
            .append("idLancamentoItemContabil", getIdLancamentoItemContabil())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UnResumoRefaturamentoCreditoPK) ) return false;
        UnResumoRefaturamentoCreditoPK castOther = (UnResumoRefaturamentoCreditoPK) other;
        return new EqualsBuilder()
            .append(this.getIdResumoRefaturamentoAguaEsgoto(), castOther.getIdResumoRefaturamentoAguaEsgoto())
            .append(this.getIdCreditoOrigem(), castOther.getIdCreditoOrigem())
            .append(this.getIdLancamentoItemContabil(), castOther.getIdLancamentoItemContabil())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIdResumoRefaturamentoAguaEsgoto())
            .append(getIdCreditoOrigem())
            .append(getIdLancamentoItemContabil())
            .toHashCode();
    }

}
