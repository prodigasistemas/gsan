package gcom.seguranca.acesso.usuario;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class UsuarioPermissaoEspecial extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    
	private static final int ATRIBUTOS_CONTROLAR_PERMISSOES_ESPECIAIS=100;
	/** identifier field */
	@ControleAlteracao(value=FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_COMP_ID,funcionalidade={ATRIBUTOS_CONTROLAR_PERMISSOES_ESPECIAIS})
	private gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecialPK comp_id;

    /** nullable persistent field */
    @ControleAlteracao(value=FiltroUsuarioPemissaoEspecial.USUARIO_ID ,funcionalidade={ATRIBUTOS_CONTROLAR_PERMISSOES_ESPECIAIS})
    private gcom.seguranca.acesso.usuario.Usuario usuario;

    /** nullable persistent field */
    @ControleAlteracao(value=FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_COMP_ID,funcionalidade={ATRIBUTOS_CONTROLAR_PERMISSOES_ESPECIAIS})
    private gcom.seguranca.acesso.PermissaoEspecial permissaoEspecial;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade=ATRIBUTOS_CONTROLAR_PERMISSOES_ESPECIAIS)
    private Date ultimaAlteracao;
    
    public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** full constructor */
    public UsuarioPermissaoEspecial(gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecialPK comp_id, gcom.seguranca.acesso.usuario.Usuario usuario, gcom.seguranca.acesso.PermissaoEspecial permissaoEspecial) {
        this.comp_id = comp_id;
        this.usuario = usuario;
        this.permissaoEspecial = permissaoEspecial;
    }

    /** default constructor */
    public UsuarioPermissaoEspecial() {
    }

    /** minimal constructor */
    public UsuarioPermissaoEspecial(gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecialPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecialPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecialPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.seguranca.acesso.usuario.Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(gcom.seguranca.acesso.usuario.Usuario usuario) {
        this.usuario = usuario;
    }

    public gcom.seguranca.acesso.PermissaoEspecial getPermissaoEspecial() {
        return this.permissaoEspecial;
    }

    public void setPermissaoEspecial(gcom.seguranca.acesso.PermissaoEspecial permissaoEspecial) {
        this.permissaoEspecial = permissaoEspecial;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UsuarioPermissaoEspecial) ) return false;
        UsuarioPermissaoEspecial castOther = (UsuarioPermissaoEspecial) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();

		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID,
				this.getPermissaoEspecial().getId()));
		filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID,
				this.getUsuario().getId()));
		
		filtroUsuarioPemissaoEspecial.adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroUsuarioPemissaoEspecial.adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtroUsuarioPemissaoEspecial.adicionarCaminhoParaCarregamentoEntidade("permissaoEspecial");
		
		return filtroUsuarioPemissaoEspecial;
	}
	
	@Override
	public Filtro retornaFiltroRegistroOperacao() {
		Filtro filtro = retornaFiltro();
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioPemissaoEspecial.USUARIO);
		return filtro;
	}

	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"permissaoEspecial.descricao",
				"usuario.nomeUsuario",
				"usuario.id"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Per. Esp. Descricao",
				"Nome do Usuario",
				"Id Usuario"};
		return labels;		
	}
	
   
}
