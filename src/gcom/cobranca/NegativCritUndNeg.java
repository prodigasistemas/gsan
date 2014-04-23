package gcom.cobranca;

import gcom.cobranca.NegativCritUndNegPK;
import gcom.cadastro.localidade.UnidadeNegocio;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativCritUndNeg implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private NegativCritUndNegPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.cobranca.NegativacaoCriterio negativacaoCriterio;

    /** nullable persistent field */
    private UnidadeNegocio unidadeNegocio;

    /** full constructor */
    public NegativCritUndNeg(NegativCritUndNegPK comp_id, Date ultimaAlteracao, gcom.cobranca.NegativacaoCriterio negativacaoCriterio, UnidadeNegocio unidadeNegocio) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativacaoCriterio = negativacaoCriterio;
        this.unidadeNegocio = unidadeNegocio;
    }

    /** default constructor */
    public NegativCritUndNeg() {
    }

    /** minimal constructor */
    public NegativCritUndNeg(NegativCritUndNegPK comp_id, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public NegativCritUndNegPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(NegativCritUndNegPK comp_id) {
        this.comp_id = comp_id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.NegativacaoCriterio getNegativacaoCriterio() {
        return this.negativacaoCriterio;
    }

    public void setNegativacaoCriterio(gcom.cobranca.NegativacaoCriterio negativacaoCriterio) {
        this.negativacaoCriterio = negativacaoCriterio;
    }

    public UnidadeNegocio getUnidadeNegocio() {
        return this.unidadeNegocio;
    }

    public void setUnidadeNegocio(UnidadeNegocio unidadeNegocio) {
        this.unidadeNegocio = unidadeNegocio;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof NegativCritUndNeg) ) return false;
        NegativCritUndNeg castOther = (NegativCritUndNeg) other;
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
