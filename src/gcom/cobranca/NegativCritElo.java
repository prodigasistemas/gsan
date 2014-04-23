package gcom.cobranca;

import gcom.cobranca.NegativCritEloPK;
import gcom.cadastro.localidade.Localidade;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativCritElo implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private NegativCritEloPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Localidade localidade;

    /** nullable persistent field */
    private gcom.cobranca.NegativacaoCriterio negativacaoCriterio;

    /** full constructor */
    public NegativCritElo(NegativCritEloPK comp_id, Date ultimaAlteracao, Localidade localidade, gcom.cobranca.NegativacaoCriterio negativacaoCriterio) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.localidade = localidade;
        this.negativacaoCriterio = negativacaoCriterio;
    }

    /** default constructor */
    public NegativCritElo() {
    }

    /** minimal constructor */
    public NegativCritElo(NegativCritEloPK comp_id, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public NegativCritEloPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(NegativCritEloPK comp_id) {
        this.comp_id = comp_id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Localidade getLocalidade() {
        return this.localidade;
    }

    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
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
        if ( !(other instanceof NegativCritElo) ) return false;
        NegativCritElo castOther = (NegativCritElo) other;
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
