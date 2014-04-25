package gcom.seguranca.acesso.usuario;

import gcom.seguranca.acesso.Funcionalidade;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UsuarioFavorito implements Serializable {

	private static final long serialVersionUID = 1L;

	
    /** identifier field */
    private UsuarioFavoritoPK comp_id;

    /** persistent field */
    private short indicadorFavoritosUltimosAcessados;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Funcionalidade funcionalidade;

    /** nullable persistent field */
    private Usuario usuario;

    /** full constructor */
    public UsuarioFavorito(UsuarioFavoritoPK comp_id, short indicadorFavoritosUltimosAcessados, Date ultimaAlteracao, Funcionalidade funcionalidade, Usuario usuario) {
        this.comp_id = comp_id;
        this.indicadorFavoritosUltimosAcessados = indicadorFavoritosUltimosAcessados;
        this.ultimaAlteracao = ultimaAlteracao;
        this.funcionalidade = funcionalidade;
        this.usuario = usuario;
    }

    /** default constructor */
    public UsuarioFavorito() {
    }

    /** minimal constructor */
    public UsuarioFavorito(UsuarioFavoritoPK comp_id, short indicadorFavoritosUltimosAcessados, Date ultimaAlteracao) {
        this.comp_id = comp_id;
        this.indicadorFavoritosUltimosAcessados = indicadorFavoritosUltimosAcessados;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public UsuarioFavoritoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(UsuarioFavoritoPK comp_id) {
        this.comp_id = comp_id;
    }

    /**
	 * @return Retorna o campo indicadorFavoritosUltimosAcessados.
	 */
	public short getIndicadorFavoritosUltimosAcessados() {
		return indicadorFavoritosUltimosAcessados;
	}

	/**
	 * @param indicadorFavoritosUltimosAcessados O indicadorFavoritosUltimosAcessados a ser setado.
	 */
	public void setIndicadorFavoritosUltimosAcessados(
			short indicadorFavoritosUltimosAcessados) {
		this.indicadorFavoritosUltimosAcessados = indicadorFavoritosUltimosAcessados;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
    public Funcionalidade getFuncionalidade() {
        return this.funcionalidade;
    }

    public void setFuncionalidade(Funcionalidade funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UsuarioFavorito) ) return false;
        UsuarioFavorito castOther = (UsuarioFavorito) other;
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
