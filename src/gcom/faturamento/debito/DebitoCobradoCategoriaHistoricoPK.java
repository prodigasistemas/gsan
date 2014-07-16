package gcom.faturamento.debito;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DebitoCobradoCategoriaHistoricoPK implements Serializable {
	private static final long serialVersionUID = 1L;
   
    private Integer debitoCobradoHistoricoId;
    private Integer categoriaId;

    public DebitoCobradoCategoriaHistoricoPK(Integer debitoCobradoHistoricoId, Integer categoriaId) {
        this.debitoCobradoHistoricoId = debitoCobradoHistoricoId;
        this.categoriaId = categoriaId;
    }

    public DebitoCobradoCategoriaHistoricoPK() {
    }

    public Integer getDebitoCobradoHistoricoId() {
        return this.debitoCobradoHistoricoId;
    }

    public void setDebitoCobradoHistoricoId(Integer debitoCobradoHistoricoId) {
        this.debitoCobradoHistoricoId = debitoCobradoHistoricoId;
    }

    public Integer getCategoriaId() {
        return this.categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("debitoCobradoHistoricoId", getDebitoCobradoHistoricoId())
            .append("categoriaId", getCategoriaId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof DebitoCobradoCategoriaHistoricoPK) ) return false;
        DebitoCobradoCategoriaHistoricoPK castOther = (DebitoCobradoCategoriaHistoricoPK) other;
        return new EqualsBuilder()
            .append(this.getDebitoCobradoHistoricoId(), castOther.getDebitoCobradoHistoricoId())
            .append(this.getCategoriaId(), castOther.getCategoriaId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getDebitoCobradoHistoricoId())
            .append(getCategoriaId())
            .toHashCode();
    }

}
