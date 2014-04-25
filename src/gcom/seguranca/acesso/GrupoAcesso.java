package gcom.seguranca.acesso;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class GrupoAcesso implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.seguranca.acesso.GrupoAcessoPK comp_id;

    /** nullable persistent field */
    private gcom.seguranca.acesso.Grupo grupo;

    /** full constructor */
    public GrupoAcesso(gcom.seguranca.acesso.GrupoAcessoPK comp_id, Grupo grupo) {
        this.comp_id = comp_id;
        this.grupo = grupo;
    }

    /** default constructor */
    public GrupoAcesso() {
    }

    /** minimal constructor */
    public GrupoAcesso(gcom.seguranca.acesso.GrupoAcessoPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.seguranca.acesso.GrupoAcessoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.seguranca.acesso.GrupoAcessoPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.seguranca.acesso.Grupo getGrupo() {
        return this.grupo;
    }

    public void setGrupo(gcom.seguranca.acesso.Grupo grupo) {
        this.grupo = grupo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof GrupoAcesso) ) return false;
        GrupoAcesso castOther = (GrupoAcesso) other;
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
