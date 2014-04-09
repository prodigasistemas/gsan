package gcom.seguranca.acesso.usuario;

import gcom.interceptor.ObjetoGcom;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UsuarioGrupoPK extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer grupoId;

    /** identifier field */
    private Integer usuarioId;

    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[2];
		retorno[0] = "grupoId";
		retorno[1] = "usuarioId";
		return retorno;
	}
    
    /** full constructor */
    public UsuarioGrupoPK(Integer grupoId, Integer usuarioId) {
        this.grupoId = grupoId;
        this.usuarioId = usuarioId;
    }

    /** default constructor */
    public UsuarioGrupoPK() {
    }

    public Integer getGrupoId() {
        return this.grupoId;
    }

    public void setGrupoId(Integer grupoId) {
        this.grupoId = grupoId;
    }

    public Integer getUsuarioId() {
        return this.usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("grupoId", getGrupoId())
            .append("usuarioId", getUsuarioId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UsuarioGrupoPK) ) return false;
        UsuarioGrupoPK castOther = (UsuarioGrupoPK) other;
        return new EqualsBuilder()
            .append(this.getGrupoId(), castOther.getGrupoId())
            .append(this.getUsuarioId(), castOther.getUsuarioId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getGrupoId())
            .append(getUsuarioId())
            .toHashCode();
    }

}
