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
public class UsuarioGrupoRestricao extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    
	private static final int ATRIBUTOS_CONTROLE_PERMISSAO_ESPECIAL = 100;
	
	/** identifier field */
    private gcom.seguranca.acesso.usuario.UsuarioGrupoRestricaoPK comp_id;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade=ATRIBUTOS_CONTROLE_PERMISSAO_ESPECIAL)
    private gcom.seguranca.acesso.GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao;

    /** nullable persistent field */
    private gcom.seguranca.acesso.usuario.UsuarioGrupo usuarioGrupo;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "comp_id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroUsuarioGrupoRestricao filtroUsuarioGrupoRestricao = new FiltroUsuarioGrupoRestricao();
		
		filtroUsuarioGrupoRestricao. adicionarCaminhoParaCarregamentoEntidade("comp_id");
		filtroUsuarioGrupoRestricao. adicionarCaminhoParaCarregamentoEntidade("grupoFuncionalidadeOperacao");
		filtroUsuarioGrupoRestricao. adicionarCaminhoParaCarregamentoEntidade("usuarioGrupo");
		filtroUsuarioGrupoRestricao. adicionarParametro(
				new ParametroSimples(FiltroUsuarioGrupoRestricao.USUARIO_ID, comp_id.getUsuarioId()));
		filtroUsuarioGrupoRestricao. adicionarParametro(
				new ParametroSimples(FiltroUsuarioGrupoRestricao.FUNCIONALIDADE_ID, comp_id.getFuncionalidadeId()));
		filtroUsuarioGrupoRestricao. adicionarParametro(
				new ParametroSimples(FiltroUsuarioGrupoRestricao.GRUPO_ID, comp_id.getGrupoId()));
		filtroUsuarioGrupoRestricao. adicionarParametro(
				new ParametroSimples(FiltroUsuarioGrupoRestricao.OPERACAO_ID, comp_id.getOperacaoId()));
		return filtroUsuarioGrupoRestricao; 
	}
    
    public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** full constructor */
    public UsuarioGrupoRestricao(gcom.seguranca.acesso.usuario.UsuarioGrupoRestricaoPK comp_id, gcom.seguranca.acesso.GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao, gcom.seguranca.acesso.usuario.UsuarioGrupo usuarioGrupo) {
        this.comp_id = comp_id;
        this.grupoFuncionalidadeOperacao = grupoFuncionalidadeOperacao;
        this.usuarioGrupo = usuarioGrupo;
    }

    /** default constructor */
    public UsuarioGrupoRestricao() {
    }

    /** minimal constructor */
    public UsuarioGrupoRestricao(gcom.seguranca.acesso.usuario.UsuarioGrupoRestricaoPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.seguranca.acesso.usuario.UsuarioGrupoRestricaoPK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(gcom.seguranca.acesso.usuario.UsuarioGrupoRestricaoPK comp_id) {
        this.comp_id = comp_id;
    }

    public gcom.seguranca.acesso.GrupoFuncionalidadeOperacao getGrupoFuncionalidadeOperacao() {
        return this.grupoFuncionalidadeOperacao;
    }

    public void setGrupoFuncionalidadeOperacao(gcom.seguranca.acesso.GrupoFuncionalidadeOperacao grupoFuncionalidadeOperacao) {
        this.grupoFuncionalidadeOperacao = grupoFuncionalidadeOperacao;
    }

    public gcom.seguranca.acesso.usuario.UsuarioGrupo getUsuarioGrupo() {
        return this.usuarioGrupo;
    }

    public void setUsuarioGrupo(gcom.seguranca.acesso.usuario.UsuarioGrupo usuarioGrupo) {
        this.usuarioGrupo = usuarioGrupo;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("comp_id", getComp_id())
            .toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( !(other instanceof UsuarioGrupoRestricao) ) return false;
        UsuarioGrupoRestricao castOther = (UsuarioGrupoRestricao) other;
        return new EqualsBuilder()
            .append(this.getComp_id(), castOther.getComp_id())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getComp_id())
            .toHashCode();
    }
    public Filtro retornaFiltroRegistroOperacao() {
    	FiltroUsuarioPemissaoEspecial filtro = new FiltroUsuarioPemissaoEspecial();

//		filtro.adicionarParametro(
//			new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_COMP_ID,this.getComp_id()));
		
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioPemissaoEspecial.USUARIO_ID);
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUsuarioPemissaoEspecial.GRUPO_FUNCIONALIDADE_OPERACAO);
		return filtro;
	}
	
//	@Override
//	public String getDescricaoParaRegistroTransacao() {
//		return getGrupoFuncionalidadeOperacao().getComp_id().toString();
//	}
	
//	@Override
//	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
//		String []labels = {"usuario.id"};
//		return labels;		
//	}
//	
//	@Override
//	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
//		String []labels = {"Id usuario"};
//		return labels;		
//	}
	@Override
	public void initializeLazy() {
	}
}
