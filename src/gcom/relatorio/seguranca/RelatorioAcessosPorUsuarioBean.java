package gcom.relatorio.seguranca;

import gcom.relatorio.RelatorioBean;

/**
 * classe responsável por criar o relatório de Acessos por Usuário
 * 
 * [UC1040] Gerar Relatório de Acessos por Usuário
 * 
 * @author Hugo Leonardo
 *
 * @date 13/07/2010
 */
public class RelatorioAcessosPorUsuarioBean implements RelatorioBean {
	
	private static final long serialVersionUID = 1L;
	
	
	private String usuario;
	private String funcionalidade;
	private String modulo;
	private String operacao;
	private String situacaoUsuario;
	private String usuarioTipo;
	private String grupoAcesso;
	private String unidadeOrganizacional;
	private String permissaoEspecial;

	public RelatorioAcessosPorUsuarioBean(String usuario, String funcionalidade, 
			String modulo, String operacao, String situacaoUsuario, String usuarioTipo, String grupoAcesso,
			String unidadeOrganizacional, String permissaoEspecial) {
		
		this.usuario = usuario;
		this.funcionalidade = funcionalidade;
		this.modulo = modulo;
		this.operacao = operacao;
		this.situacaoUsuario = situacaoUsuario;
		this.usuarioTipo = usuarioTipo;
		this.grupoAcesso = grupoAcesso;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.permissaoEspecial = permissaoEspecial;
	}

	public String getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public String getGrupoAcesso() {
		return grupoAcesso;
	}

	public void setGrupoAcesso(String grupoAcesso) {
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

	public String getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	public void setUnidadeOrganizacional(String unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuarioTipo() {
		return usuarioTipo;
	}

	public void setUsuarioTipo(String usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}
	
}
