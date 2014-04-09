package gcom.seguranca.acesso;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FuncionalidadeDependencia implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private gcom.seguranca.acesso.FuncionalidadeDependenciaPK comp_id;

    /** nullable persistent field */
    private gcom.seguranca.acesso.Funcionalidade funcionalidade;

    /** nullable persistent field */
    private gcom.seguranca.acesso.Funcionalidade funcionalidadeDependencia;

    /** full constructor */
    public FuncionalidadeDependencia(gcom.seguranca.acesso.FuncionalidadeDependenciaPK comp_id, gcom.seguranca.acesso.Funcionalidade funcionalidade, gcom.seguranca.acesso.Funcionalidade funcionalidadeDependencia) {
        this.comp_id = comp_id;
        this.funcionalidade = funcionalidade;
        this.funcionalidadeDependencia = funcionalidadeDependencia;
    }

    /** default constructor */
    public FuncionalidadeDependencia() {
    }

    /** minimal constructor */
    public FuncionalidadeDependencia(gcom.seguranca.acesso.FuncionalidadeDependenciaPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.seguranca.acesso.FuncionalidadeDependenciaPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.seguranca.acesso.FuncionalidadeDependenciaPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.seguranca.acesso.Funcionalidade getFuncionalidade() {
        return this.funcionalidade;
    }

    public void setFuncionalidade(gcom.seguranca.acesso.Funcionalidade funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    public gcom.seguranca.acesso.Funcionalidade getFuncionalidadeDependencia() {
        return this.funcionalidadeDependencia;
    }

    public void setFuncionalidadeDependencia(gcom.seguranca.acesso.Funcionalidade funcionalidadeDependencia) {
        this.funcionalidadeDependencia = funcionalidadeDependencia;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof FuncionalidadeDependencia) ) return false;
        FuncionalidadeDependencia castOther = (FuncionalidadeDependencia) other;
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
