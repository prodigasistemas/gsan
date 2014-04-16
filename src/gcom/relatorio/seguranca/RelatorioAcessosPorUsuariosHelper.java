package gcom.relatorio.seguranca;

import java.io.Serializable;

/**
 * [UC1040] Gerar Relatório de Acessos por Usuário
 * 
 * @author Hugo Leonardo
 *
 * @date 13/07/2010
 */
public class RelatorioAcessosPorUsuariosHelper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String permissaoEspecial;
	private String usuario;
	private String funcionalidade;
	private String modulo;
	private String operacao;
	private String situacaoUsuario;
	private String usuarioTipo;
	private String grupoAcesso;
	private String unidadeOrganizacional;
	
	
	public String getFuncionalidade() {
		return funcionalidade;
	}
	
	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
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
	
	public String getPermissaoEspecial() {
		return permissaoEspecial;
	}
	
	public void setPermissaoEspecial(String permissaoEspecial) {
		this.permissaoEspecial = permissaoEspecial;
	}
	
	public String getSituacaoUsuario() {
		return situacaoUsuario;
	}
	
	public void setSituacaoUsuario(String situacaoUsuario) {
		this.situacaoUsuario = situacaoUsuario;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getGrupoAcesso() {
		return grupoAcesso;
	}

	public void setGrupoAcesso(String grupoAcesso) {
		this.grupoAcesso = grupoAcesso;
	}

	public String getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(String unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public String getUsuarioTipo() {
		return usuarioTipo;
	}

	public void setUsuarioTipo(String usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	
}
