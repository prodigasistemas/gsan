package gcom.relatorio.seguranca;

import java.io.Serializable;
import java.util.Collection;


/**
 * [UC1040] Gerar Relatório de Acessos por Usuário
 * 
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de Acessos por Usaário
 *
 * @author Hugo Leonardo
 * @date 13/07/2010
 */
public class FiltrarRelatorioAcessosUsuariosHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String funcionalidade;
	private String modulo;
	private String operacao;
	private Collection<Integer> permissaoEspecial;
	private Collection<Integer> situacaoUsuario;
	private Collection<Integer> usuarioTipo;
	private Collection<Integer> grupoAcesso;
	private Collection<Integer> unidadeOrganizacional;
	
	
	public String getFuncionalidade() {
		return funcionalidade;
	}
	
	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}
	
	public Collection<Integer> getGrupoAcesso() {
		return grupoAcesso;
	}
	
	public void setGrupoAcesso(Collection<Integer> grupoAcesso) {
		this.grupoAcesso = grupoAcesso;
	}
	
	public String getModulo() {
		return modulo;
	}
	
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	
	public String getOperacao() {
		return operacao;
	}
	
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	public Collection<Integer> getPermissaoEspecial() {
		return permissaoEspecial;
	}

	public void setPermissaoEspecial(Collection<Integer> permissaoEspecial) {
		this.permissaoEspecial = permissaoEspecial;
	}

	public Collection<Integer> getSituacaoUsuario() {
		return situacaoUsuario;
	}

	public void setSituacaoUsuario(Collection<Integer> situacaoUsuario) {
		this.situacaoUsuario = situacaoUsuario;
	}

	public Collection<Integer> getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}
	
	public void setUnidadeOrganizacional(Collection<Integer> unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public Collection<Integer> getUsuarioTipo() {
		return usuarioTipo;
	}
	
	public void setUsuarioTipo(Collection<Integer> usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}
	
}
