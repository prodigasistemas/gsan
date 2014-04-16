package gcom.faturamento.credito;

import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CreditoARealizarCategoriaPK extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer creditoARealizarId;

    /** identifier field */
    private Integer categoriaId;

    /** full constructor */
    public CreditoARealizarCategoriaPK(Integer creditoARealizarId, Integer categoriaId) {
        this.creditoARealizarId = creditoARealizarId;
        this.categoriaId = categoriaId;
    }

    /** default constructor */
    public CreditoARealizarCategoriaPK() {
    }

    public Integer getCreditoARealizarId() {
        return this.creditoARealizarId;
    }

    public void setCreditoARealizarId(Integer creditoARealizarId) {
        this.creditoARealizarId = creditoARealizarId;
    }

    public Integer getCategoriaId() {
        return this.categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("creditoARealizarId", getCreditoARealizarId())
            .append("categoriaId", getCategoriaId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof CreditoARealizarCategoriaPK) ) return false;
        CreditoARealizarCategoriaPK castOther = (CreditoARealizarCategoriaPK) other;
        return new EqualsBuilder()
            .append(this.getCreditoARealizarId(), castOther.getCreditoARealizarId())
            .append(this.getCategoriaId(), castOther.getCategoriaId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getCreditoARealizarId())
            .append(getCategoriaId())
            .toHashCode();
    }

    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "creditoARealizarId";
		retorno[1] = "categoriaId";
		return retorno;
	}
}
