package gcom.cobranca;

import gcom.cobranca.NegativacaoCriterioImovelPerfilPK;
import gcom.cadastro.imovel.ImovelPerfil;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoCriterioImovelPerfil implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private NegativacaoCriterioImovelPerfilPK comp_id;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.cobranca.NegativacaoCriterio negativacaoCriterio;

    /** nullable persistent field */
    private ImovelPerfil imovelPerfil;

    /** full constructor */
    public NegativacaoCriterioImovelPerfil(NegativacaoCriterioImovelPerfilPK comp_id, Date ultimaAlteracao, gcom.cobranca.NegativacaoCriterio negativacaoCriterio, ImovelPerfil imovelPerfil) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativacaoCriterio = negativacaoCriterio;
        this.imovelPerfil = imovelPerfil;
    }

    /** default constructor */
    public NegativacaoCriterioImovelPerfil() {
    }

    /** minimal constructor */
    public NegativacaoCriterioImovelPerfil(NegativacaoCriterioImovelPerfilPK comp_id, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public NegativacaoCriterioImovelPerfilPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(NegativacaoCriterioImovelPerfilPK comp_id) {
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

    public ImovelPerfil getImovelPerfil() {
        return this.imovelPerfil;
    }

    public void setImovelPerfil(ImovelPerfil imovelPerfil) {
        this.imovelPerfil = imovelPerfil;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof NegativacaoCriterioImovelPerfil) ) return false;
        NegativacaoCriterioImovelPerfil castOther = (NegativacaoCriterioImovelPerfil) other;
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
