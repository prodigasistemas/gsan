package gcom.cobranca;

import gcom.cadastro.imovel.Subcategoria;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoCriterioSubcategoriaPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private NegativacaoCriterio negativacaoCriterio;

    /** identifier field */
    private Subcategoria subcategoria;

    /** full constructor */
    public NegativacaoCriterioSubcategoriaPK(NegativacaoCriterio negativacaoCriterio, Subcategoria subcategoria) {
        this.negativacaoCriterio = negativacaoCriterio;
        this.subcategoria = subcategoria;
    }

    /** default constructor */
    public NegativacaoCriterioSubcategoriaPK() {
    }

	/**
	 * @return Retorna o campo negativacaoCriterio.
	 */
	public NegativacaoCriterio getNegativacaoCriterio() {
		return negativacaoCriterio;
	}

	/**
	 * @param negativacaoCriterio O negativacaoCriterio a ser setado.
	 */
	public void setNegativacaoCriterio(NegativacaoCriterio negativacaoCriterio) {
		this.negativacaoCriterio = negativacaoCriterio;
	}

	/**
	 * @return Retorna o campo subcategoria.
	 */
	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	/**
	 * @param subcategoria O subcategoria a ser setado.
	 */
	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("negativacaoCriterio", getNegativacaoCriterio())
            .append("subcategoria", getSubcategoria())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof NegativacaoCriterioSubcategoriaPK) ) return false;
        NegativacaoCriterioSubcategoriaPK castOther = (NegativacaoCriterioSubcategoriaPK) other;
        return new EqualsBuilder()
            .append(this.getNegativacaoCriterio(), castOther.getNegativacaoCriterio())
            .append(this.getSubcategoria(), castOther.getSubcategoria())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getNegativacaoCriterio())
            .append(getSubcategoria())
            .toHashCode();
    }

}
