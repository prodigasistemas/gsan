package gcom.gui.relatorio.seguranca;


import org.apache.struts.action.ActionForm;

/**
 * [UC1040] Gerar Relatório de Acessos por Usuário
 * 
 * @author Hugo Leonardo
 *
 * @date 13/07/2010
 */

public class GerarRelatorioAcessosUsuariosActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String[] idsSituacaoUsuario;
	private String[] idsPermissaoEspecial;
	private String operacao;
	private String modulo;
	private String[] idsUnidadeOrganizacional;
	private String[] idsGrupoAcesso;
	private String[] idsUsuarioTipo;
	private String idUsuario;
	private String nomeUsuario;
	private String idFuncionalidade;
	private String nomeFuncionalidade;

	public void reset(){
		
		this.idsPermissaoEspecial = null;
		this.idsUsuarioTipo = null;
		this.idsGrupoAcesso = null;
		this.modulo = null;
		this.operacao = null;
		this.idUsuario = null;
		this.nomeUsuario = null;
		this.idFuncionalidade = null;
		this.nomeFuncionalidade = null;
	}

	public String getIdFuncionalidade() {
		return idFuncionalidade;
	}

	public void setIdFuncionalidade(String idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}

	public String getNomeFuncionalidade() {
		return nomeFuncionalidade;
	}

	public void setNomeFuncionalidade(String nomeFuncionalidade) {
		this.nomeFuncionalidade = nomeFuncionalidade;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String[] getIdsGrupoAcesso() {
		return idsGrupoAcesso;
	}

	public void setIdsGrupoAcesso(String[] idsGrupoAcesso) {
		this.idsGrupoAcesso = idsGrupoAcesso;
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

	public String[] getIdsUsuarioTipo() {
		return idsUsuarioTipo;
	}

	public void setIdsUsuarioTipo(String[] idsUsuarioTipo) {
		this.idsUsuarioTipo = idsUsuarioTipo;
	}

	public String[] getIdsSituacaoUsuario() {
		return idsSituacaoUsuario;
	}

	public void setIdsSituacaoUsuario(String[] idsSituacaoUsuario) {
		this.idsSituacaoUsuario = idsSituacaoUsuario;
	}

	public String[] getIdsUnidadeOrganizacional() {
		return idsUnidadeOrganizacional;
	}

	public void setIdsUnidadeOrganizacional(String[] idsUnidadeOrganizacional) {
		this.idsUnidadeOrganizacional = idsUnidadeOrganizacional;
	}

	public String[] getIdsPermissaoEspecial() {
		return idsPermissaoEspecial;
	}

	public void setIdsPermissaoEspecial(String[] idsPermissaoEspecial) {
		this.idsPermissaoEspecial = idsPermissaoEspecial;
	}
	
}
