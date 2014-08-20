package gcom.faturamento.credito;

import gcom.cadastro.imovel.Categoria;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class CreditoRealizadoCategoriaPK implements Serializable {
	private static final long serialVersionUID = 1L;
    
    private CreditoRealizado creditoRealizado;
    private Categoria categoria;

    public CreditoRealizadoCategoriaPK(CreditoRealizado creditoRealizado, Categoria categoria) {
        this.creditoRealizado = creditoRealizado;
        this.categoria = categoria;
    }

    public CreditoRealizadoCategoriaPK() {
    }

    public CreditoRealizado getCreditoRealizado() {
        return this.creditoRealizado;
    }

    public void setCreditoRealizado(CreditoRealizado creditoRealizado) {
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
