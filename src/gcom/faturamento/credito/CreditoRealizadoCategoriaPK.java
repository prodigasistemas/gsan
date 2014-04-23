package gcom.faturamento.credito;

import gcom.cadastro.imovel.Categoria;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CreditoRealizadoCategoriaPK implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.faturamento.credito.CreditoRealizado creditoRealizado;

    /** identifier field */
    private Categoria categoria;

    /** full constructor */
    public CreditoRealizadoCategoriaPK(gcom.faturamento.credito.CreditoRealizado creditoRealizado, Categoria categoria) {
        this.creditoRealizado = creditoRealizado;
        this.categoria = categoria;
    }

    /** default constructor */
    public CreditoRealizadoCategoriaPK() {
    }

    public gcom.faturamento.credito.CreditoRealizado getCreditoRealizado() {
        return this.creditoRealizado;
    }

    public void setCreditoRealizado(gcom.faturamento.credito.CreditoRealizado creditoRealizado) {
        this.creditoRealizado = creditoRealizado;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("creditoRealizado", getCreditoRealizado())
            .append("categoria", getCategoria())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof CreditoRealizadoCategoriaPK) ) return false;
        CreditoRealizadoCategoriaPK castOther = (CreditoRealizadoCategoriaPK) other;
        return new EqualsBuilder()
            .append(this.getCreditoRealizado(), castOther.getCreditoRealizado())
            .append(this.getCategoria(), castOther.getCategoria())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCreditoRealizado())
            .append(getCategoria())
            .toHashCode();
    }

}
