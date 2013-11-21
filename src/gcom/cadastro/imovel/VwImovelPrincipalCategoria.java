package gcom.cadastro.imovel;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class VwImovelPrincipalCategoria implements Serializable {

    
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private VwImovelPrincipalCategoriaPK comp_id;

    /** persistent field */
    private short quantidadeEconomias;

    /** full constructor */
    public VwImovelPrincipalCategoria(VwImovelPrincipalCategoriaPK comp_id, short quantidadeEconomias) {
        this.comp_id = comp_id;
        this.quantidadeEconomias = quantidadeEconomias;
    }

    /** default constructor */
    public VwImovelPrincipalCategoria() {
    }

    public VwImovelPrincipalCategoriaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(VwImovelPrincipalCategoriaPK comp_id) {
        this.comp_id = comp_id;
    }

    public short getQuantidadeEconomias() {
        return this.quantidadeEconomias;
    }

    public void setQuantidadeEconomias(short quantidadeEconomias) {
        this.quantidadeEconomias = quantidadeEconomias;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof VwImovelPrincipalCategoria) ) return false;
        VwImovelPrincipalCategoria castOther = (VwImovelPrincipalCategoria) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }

}
