package gcom.cobranca;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativCritCobrGrupoPK implements Serializable {
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private NegativacaoCriterio negativacaoCriterio;

    /** identifier field */
    private CobrancaGrupo cobrancaGrupo;

    /** full constructor */
    public NegativCritCobrGrupoPK(NegativacaoCriterio negativacaoCriterio, CobrancaGrupo cobrancaGrupo) {
        this.negativacaoCriterio = negativacaoCriterio;
        this.cobrancaGrupo = cobrancaGrupo;
    }

    /** default constructor */
    public NegativCritCobrGrupoPK() {
    }

	/**
	 * @return Retorna o campo cobrancaGrupo.
	 */
	public CobrancaGrupo getCobrancaGrupo() {
		return cobrancaGrupo;
	}

	/**
	 * @param cobrancaGrupo O cobrancaGrupo a ser setado.
	 */
	public void setCobrancaGrupo(CobrancaGrupo cobrancaGrupo) {
		this.cobrancaGrupo = cobrancaGrupo;
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
            .append("cobrancaGrupo", getCobrancaGrupo())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof NegativCritCobrGrupoPK) ) return false;
        NegativCritCobrGrupoPK castOther = (NegativCritCobrGrupoPK) other;
        return new EqualsBuilder()
            .append(this.getNegativacaoCriterio(), castOther.getNegativacaoCriterio())
            .append(this.getCobrancaGrupo(), castOther.getCobrancaGrupo())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getNegativacaoCriterio())
            .append(getCobrancaGrupo())
            .toHashCode();
    }

}
