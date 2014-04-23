package gcom.seguranca.acesso.usuario;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UsuarioFavoritoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	
    /** identifier field */
    private Integer usuarioId;

    /** identifier field */
    private Integer funcionalidadeId;

    /** full constructor */
    public UsuarioFavoritoPK(Integer usuarioId, Integer funcionalidadeId) {
        this.usuarioId = usuarioId;
        this.funcionalidadeId = funcionalidadeId;
    }

    /** default constructor */
    public UsuarioFavoritoPK() {
    }

    /**
	 * @return Retorna o campo funcionalidadeId.
	 */
	public Integer getFuncionalidadeId() {
		return funcionalidadeId;
	}

	/**
	 * @param funcionalidadeId O funcionalidadeId a ser setado.
	 */
	public void setFuncionalidadeId(Integer funcionalidadeId) {
		this.funcionalidadeId = funcionalidadeId;
	}

	/**
	 * @return Retorna o campo usuarioId.
	 */
	public Integer getUsuarioId() {
		return usuarioId;
	}

	/**
	 * @param usuarioId O usuarioId a ser setado.
	 */
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("usurId", getUsuarioId())
            .append("fncdId", getFuncionalidadeId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UsuarioFavoritoPK) ) return false;
        UsuarioFavoritoPK castOther = (UsuarioFavoritoPK) other;
        return new EqualsBuilder()
            .append(this.getUsuarioId(), castOther.getUsuarioId())
            .append(this.getFuncionalidadeId(), castOther.getFuncionalidadeId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUsuarioId())
            .append(getFuncionalidadeId())
            .toHashCode();
    }

}
