package gcom.cobranca;

import gcom.cadastro.cliente.ClienteTipo;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoCriterioClienteTipo implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private gcom.cobranca.NegativacaoCriterioClienteTipoPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.cobranca.NegativacaoCriterio negativacaoCriterio;

    /** nullable persistent field */
    private ClienteTipo clienteTipo;

    /** full constructor */
    public NegativacaoCriterioClienteTipo(gcom.cobranca.NegativacaoCriterioClienteTipoPK comp_id, Date ultimaAlteracao, gcom.cobranca.NegativacaoCriterio negativacaoCriterio, ClienteTipo clienteTipo) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativacaoCriterio = negativacaoCriterio;
        this.clienteTipo = clienteTipo;
    }

    /** default constructor */
    public NegativacaoCriterioClienteTipo() {
    }

    /** minimal constructor */
    public NegativacaoCriterioClienteTipo(gcom.cobranca.NegativacaoCriterioClienteTipoPK comp_id, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.NegativacaoCriterioClienteTipoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.cobranca.NegativacaoCriterioClienteTipoPK comp_id) {
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

    public ClienteTipo getClienteTipo() {
        return this.clienteTipo;
    }

    public void setClienteTipo(ClienteTipo clienteTipo) {
        this.clienteTipo = clienteTipo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof NegativacaoCriterioClienteTipo) ) return false;
        NegativacaoCriterioClienteTipo castOther = (NegativacaoCriterioClienteTipo) other;
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
