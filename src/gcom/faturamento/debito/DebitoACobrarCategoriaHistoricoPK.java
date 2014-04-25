package gcom.faturamento.debito;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class DebitoACobrarCategoriaHistoricoPK implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer debitoACobrarHistoricoId;

    /** identifier field */
    private Integer categoriaId;

    /** full constructor */
    public DebitoACobrarCategoriaHistoricoPK(Integer debitoACobrarHistoricoId, Integer categoriaId) {
        this.debitoACobrarHistoricoId = debitoACobrarHistoricoId;
        this.categoriaId = categoriaId;
    }

    /** default constructor */
    public DebitoACobrarCategoriaHistoricoPK() {
    }

    public Integer getDebitoACobrarHistoricoId() {
        return this.debitoACobrarHistoricoId;
    }

    public void setDebitoACobrarHistoricoId(Integer debitoACobrarHistoricoId) {
        this.debitoACobrarHistoricoId = debitoACobrarHistoricoId;
    }

    public Integer getCategoriaId() {
        return this.categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("debitoACobrarHistoricoId", getDebitoACobrarHistoricoId())
            .append("categoriaId", getCategoriaId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof DebitoACobrarCategoriaHistoricoPK) ) return false;
        DebitoACobrarCategoriaHistoricoPK castOther = (DebitoACobrarCategoriaHistoricoPK) other;
        return new EqualsBuilder()
            .append(this.getDebitoACobrarHistoricoId(), castOther.getDebitoACobrarHistoricoId())
            .append(this.getCategoriaId(), castOther.getCategoriaId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getDebitoACobrarHistoricoId())
            .append(getCategoriaId())
            .toHashCode();
    }

}
