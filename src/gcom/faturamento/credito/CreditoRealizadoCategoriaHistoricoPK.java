package gcom.faturamento.credito;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class CreditoRealizadoCategoriaHistoricoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer creditoRealizadoHistoricoId;
    private Integer categoriaId;

    public CreditoRealizadoCategoriaHistoricoPK(Integer creditoRealizadoHistoricoId, Integer categoriaId) {
        this.creditoRealizadoHistoricoId = creditoRealizadoHistoricoId;
        this.categoriaId = categoriaId;
    }

    public CreditoRealizadoCategoriaHistoricoPK() {
    }

    public Integer getCreditoRealizadoHistoricoId() {
        return this.creditoRealizadoHistoricoId;
    }

    public void setCreditoRealizadoHistoricoId(Integer creditoRealizadoHistoricoId) {
        this.creditoRealizadoHistoricoId = creditoRealizadoHistoricoId;
    }

    public Integer getCategoriaId() {
        return this.categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("creditoRealizadoHistoricoId", getCreditoRealizadoHistoricoId())
            .append("categoriaId", getCategoriaId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof CreditoRealizadoCategoriaHistoricoPK) ) return false;
        CreditoRealizadoCategoriaHistoricoPK castOther = (CreditoRealizadoCategoriaHistoricoPK) other;
        return new EqualsBuilder()
            .append(this.getCreditoRealizadoHistoricoId(), castOther.getCreditoRealizadoHistoricoId())
            .append(this.getCategoriaId(), castOther.getCategoriaId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCreditoRealizadoHistoricoId())
            .append(getCategoriaId())
            .toHashCode();
    }

}
