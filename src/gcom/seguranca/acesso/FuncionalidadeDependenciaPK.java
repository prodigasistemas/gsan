package gcom.seguranca.acesso;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FuncionalidadeDependenciaPK implements Serializable {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer funcionalidadeDependenciaId;

    /** identifier field */
    private Integer funcionalidadeId;

    /** full constructor */
    public FuncionalidadeDependenciaPK(Integer funcionalidadeDependenciaId, Integer funcionalidadeId) {
        this.funcionalidadeDependenciaId = funcionalidadeDependenciaId;
        this.funcionalidadeId = funcionalidadeId;
    }

    /** default constructor */
    public FuncionalidadeDependenciaPK() {
    }

    public Integer getFuncionalidadeDependenciaId() {
        return this.funcionalidadeDependenciaId;
    }

    public void setFuncionalidadeDependenciaId(Integer funcionalidadeDependenciaId) {
        this.funcionalidadeDependenciaId = funcionalidadeDependenciaId;
    }

    public Integer getFuncionalidadeId() {
        return this.funcionalidadeId;
    }

    public void setFuncionalidadeId(Integer funcionalidadeId) {
        this.funcionalidadeId = funcionalidadeId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("funcionalidadeDependenciaId", getFuncionalidadeDependenciaId())
            .append("funcionalidadeId", getFuncionalidadeId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof FuncionalidadeDependenciaPK) ) return false;
        FuncionalidadeDependenciaPK castOther = (FuncionalidadeDependenciaPK) other;
        return new EqualsBuilder()
            .append(this.getFuncionalidadeDependenciaId(), castOther.getFuncionalidadeDependenciaId())
            .append(this.getFuncionalidadeId(), castOther.getFuncionalidadeId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getFuncionalidadeDependenciaId())
            .append(getFuncionalidadeId())
            .toHashCode();
    }

}
