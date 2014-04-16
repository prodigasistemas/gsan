package gcom.seguranca.acesso.usuario;

import java.util.Date;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.Grupo;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class UsuarioGrupo extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	
    /** identifier field */
    private UsuarioGrupoPK comp_id;

    /** nullable persistent field */
    private Usuario usuario;

    /** nullable persistent field */
    private Grupo grupo;
    
    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();
		
		filtroUsuarioGrupo. adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroUsuarioGrupo. adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtroUsuarioGrupo. adicionarCaminhoParaCarregamentoEntidade("grupo");
		filtroUsuarioGrupo. adicionarParametro(
				new ParametroSimples(FiltroUsuarioGrupo.GRUPO_ID, grupo.getId()));
		filtroUsuarioGrupo. adicionarParametro(
				new ParametroSimples(FiltroUsuarioGrupo.USUARIO_ID, usuario.getId()));
		return filtroUsuarioGrupo; 
	}

    /** full constructor */
    public UsuarioGrupo(UsuarioGrupoPK comp_id, Usuario usuario, Grupo grupo) {
        this.comp_id = comp_id;
        this.usuario = usuario;
        this.grupo = grupo;
    }

    /** default constructor */
    public UsuarioGrupo() {
    }

    /** minimal constructor */
    public UsuarioGrupo(UsuarioGrupoPK comp_id) {
        this.comp_id = comp_id;
    }

    public UsuarioGrupoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(UsuarioGrupoPK comp_id) {
        this.comp_id = comp_id;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupo() {
        return this.grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UsuarioGrupo) ) return false;
        UsuarioGrupo castOther = (UsuarioGrupo) other;
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
