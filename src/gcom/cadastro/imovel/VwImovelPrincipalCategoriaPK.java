package gcom.cadastro.imovel;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VwImovelPrincipalCategoriaPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private gcom.cadastro.imovel.Imovel imovel;

    /** identifier field */
    private gcom.cadastro.imovel.Categoria categoria;

    /** full constructor */
    public VwImovelPrincipalCategoriaPK(gcom.cadastro.imovel.Imovel imovel, gcom.cadastro.imovel.Categoria categoria) {
        this.imovel = imovel;
        this.categoria = categoria;
    }

    /** default constructor */
    public VwImovelPrincipalCategoriaPK() {
    }

    public gcom.cadastro.imovel.Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(gcom.cadastro.imovel.Imovel imovel) {
        this.imovel = imovel;
    }

    public gcom.cadastro.imovel.Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(gcom.cadastro.imovel.Categoria categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("imovel", getImovel())
            .append("categoria", getCategoria())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof VwImovelPrincipalCategoriaPK) ) return false;
        VwImovelPrincipalCategoriaPK castOther = (VwImovelPrincipalCategoriaPK) other;
        return new EqualsBuilder()
            .append(this.getImovel(), castOther.getImovel())
            .append(this.getCategoria(), castOther.getCategoria())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getImovel())
            .append(getCategoria())
            .toHashCode();
    }

}
