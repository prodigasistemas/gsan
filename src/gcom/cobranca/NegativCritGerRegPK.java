package gcom.cobranca;

import gcom.cadastro.localidade.GerenciaRegional;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativCritGerRegPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private NegativacaoCriterio negativacaoCriterio;

    /** identifier field */
    private GerenciaRegional gerenciaRegional;

    /** full constructor */
    public NegativCritGerRegPK(NegativacaoCriterio negativacaoCriterio, GerenciaRegional gerenciaRegional) {
        this.negativacaoCriterio = negativacaoCriterio;
        this.gerenciaRegional = gerenciaRegional;
    }

    /** default constructor */
    public NegativCritGerRegPK() {
    }

	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public GerenciaRegional getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
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
            .append("gerenciaRegional", getGerenciaRegional())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof NegativCritGerRegPK) ) return false;
        NegativCritGerRegPK castOther = (NegativCritGerRegPK) other;
        return new EqualsBuilder()
            .append(this.getNegativacaoCriterio(), castOther.getNegativacaoCriterio())
            .append(this.getGerenciaRegional(), castOther.getGerenciaRegional())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getNegativacaoCriterio())
            .append(getGerenciaRegional())
            .toHashCode();
    }

}
