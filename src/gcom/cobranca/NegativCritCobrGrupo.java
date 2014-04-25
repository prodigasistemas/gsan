package gcom.cobranca;

import gcom.cobranca.NegativCritCobrGrupoPK;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativCritCobrGrupo implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private NegativCritCobrGrupoPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.cobranca.CobrancaGrupo cobrancaGrupo;

    /** nullable persistent field */
    private gcom.cobranca.NegativacaoCriterio negativacaoCriterio;

    /** full constructor */
    public NegativCritCobrGrupo(NegativCritCobrGrupoPK comp_id, Date ultimaAlteracao, gcom.cobranca.CobrancaGrupo cobrancaGrupo, gcom.cobranca.NegativacaoCriterio negativacaoCriterio) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cobrancaGrupo = cobrancaGrupo;
        this.negativacaoCriterio = negativacaoCriterio;
    }

    /** default constructor */
    public NegativCritCobrGrupo() {
    }

    /** minimal constructor */
    public NegativCritCobrGrupo(NegativCritCobrGrupoPK comp_id, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public NegativCritCobrGrupoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(NegativCritCobrGrupoPK comp_id) {
        this.comp_id = comp_id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.CobrancaGrupo getCobrancaGrupo() {
        return this.cobrancaGrupo;
    }

    public void setCobrancaGrupo(gcom.cobranca.CobrancaGrupo cobrancaGrupo) {
        this.cobrancaGrupo = cobrancaGrupo;
    }

    public gcom.cobranca.NegativacaoCriterio getNegativacaoCriterio() {
        return this.negativacaoCriterio;
    }

    public void setNegativacaoCriterio(gcom.cobranca.NegativacaoCriterio negativacaoCriterio) {
        this.negativacaoCriterio = negativacaoCriterio;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof NegativCritCobrGrupo) ) return false;
        NegativCritCobrGrupo castOther = (NegativCritCobrGrupo) other;
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
