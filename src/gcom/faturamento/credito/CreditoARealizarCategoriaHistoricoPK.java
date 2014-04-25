package gcom.faturamento.credito;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CreditoARealizarCategoriaHistoricoPK implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer creditoARealizarHistoricoId;

    /** identifier field */
    private Integer categoriaId;

    /** full constructor */
    public CreditoARealizarCategoriaHistoricoPK(Integer creditoARealizarHistoricoId, Integer categoriaId) {
        this.creditoARealizarHistoricoId = creditoARealizarHistoricoId;
        this.categoriaId = categoriaId;
    }

    /** default constructor */
    public CreditoARealizarCategoriaHistoricoPK() {
    }

    public Integer getCreditoARealizarHistoricoId() {
        return this.creditoARealizarHistoricoId;
    }

    public void setCreditoARealizarHistoricoId(Integer creditoARealizarHistoricoId) {
        this.creditoARealizarHistoricoId = creditoARealizarHistoricoId;
    }

    public Integer getCategoriaId() {
        return this.categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("creditoARealizarHistoricoId", getCreditoARealizarHistoricoId())
            .append("categoriaId", getCategoriaId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof CreditoARealizarCategoriaHistoricoPK) ) return false;
        CreditoARealizarCategoriaHistoricoPK castOther = (CreditoARealizarCategoriaHistoricoPK) other;
        return new EqualsBuilder()
            .append(this.getCreditoARealizarHistoricoId(), castOther.getCreditoARealizarHistoricoId())
            .append(this.getCategoriaId(), castOther.getCategoriaId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCreditoARealizarHistoricoId())
            .append(getCategoriaId())
            .toHashCode();
    }

}
