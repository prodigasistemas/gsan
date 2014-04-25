package gcom.cobranca;

import gcom.cadastro.imovel.ImovelPerfil;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoCriterioImovelPerfilPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private NegativacaoCriterio negativacaoCriterio;

    /** identifier field */
    private ImovelPerfil imovelPerfil;

    /** full constructor */
    public NegativacaoCriterioImovelPerfilPK(NegativacaoCriterio negativacaoCriterio, ImovelPerfil imovelPerfil) {
        this.negativacaoCriterio = negativacaoCriterio;
        this.imovelPerfil = imovelPerfil;
    }

    /** default constructor */
    public NegativacaoCriterioImovelPerfilPK() {
    }

	/**
	 * @return Retorna o campo imovelPerfil.
	 */
	public ImovelPerfil getImovelPerfil() {
		return imovelPerfil;
	}

	/**
	 * @param imovelPerfil O imovelPerfil a ser setado.
	 */
	public void setImovelPerfil(ImovelPerfil imovelPerfil) {
		this.imovelPerfil = imovelPerfil;
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

	public String toString() {
        return new ToStringBuilder(this)
            .append("negativacaoCriterio", getNegativacaoCriterio())
            .append("imovelPerfil", getImovelPerfil())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof NegativacaoCriterioImovelPerfilPK) ) return false;
        NegativacaoCriterioImovelPerfilPK castOther = (NegativacaoCriterioImovelPerfilPK) other;
        return new EqualsBuilder()
            .append(this.getNegativacaoCriterio(), castOther.getNegativacaoCriterio())
            .append(this.getImovelPerfil(), castOther.getImovelPerfil())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getNegativacaoCriterio())
            .append(getImovelPerfil())
            .toHashCode();
    }

}
