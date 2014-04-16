package gcom.cobranca;

import gcom.cobranca.NegativCritGerRegPK;
import gcom.cadastro.localidade.GerenciaRegional;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativCritGerReg implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private NegativCritGerRegPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private GerenciaRegional gerenciaRegional;

    /** nullable persistent field */
    private gcom.cobranca.NegativacaoCriterio negativacaoCriterio;

    /** full constructor */
    public NegativCritGerReg(NegativCritGerRegPK comp_id, Date ultimaAlteracao, GerenciaRegional gerenciaRegional, gcom.cobranca.NegativacaoCriterio negativacaoCriterio) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.gerenciaRegional = gerenciaRegional;
        this.negativacaoCriterio = negativacaoCriterio;
    }

    /** default constructor */
    public NegativCritGerReg() {
    }

    /** minimal constructor */
    public NegativCritGerReg(NegativCritGerRegPK comp_id, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public NegativCritGerRegPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(NegativCritGerRegPK comp_id) {
        this.comp_id = comp_id;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public GerenciaRegional getGerenciaRegional() {
        return this.gerenciaRegional;
    }

    public void setGerenciaRegional(GerenciaRegional gerenciaRegional) {
        this.gerenciaRegional = gerenciaRegional;
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
        if ( !(other instanceof NegativCritGerReg) ) return false;
        NegativCritGerReg castOther = (NegativCritGerReg) other;
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
