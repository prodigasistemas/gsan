package gcom.gui.seguranca.acesso.usuario;

import java.util.Collection;

import org.apache.struts.validator.ValidatorForm;

/**
 * 
 * Form que exibe o menu
 * 
 * @author Sávio Luiz
 * @date 02/05/2006
 */
public class ControlarAcessoUsuarioActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	private String cadastrarOperacao;

	private String[] operacoes;

	private String nomeUsuario;

	private String loginUsuario;

	private String[] permissoesEspeciais;

	private String permissoesCheckBoxVazias;

	private Collection idsFuncionalidadesRemovidas;

	public String getCadastrarOperacao() {
		return cadastrarOperacao;
	}

	public void setCadastrarOperacao(String cadastrarOperacao) {
		this.cadastrarOperacao = cadastrarOperacao;
	}

	public String[] getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(String[] operacoes) {
		this.operacoes = operacoes;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String[] getPermissoesEspeciais() {
		return permissoesEspeciais;
	}

	public void setPermissoesEspeciais(String[] permissoesEspeciais) {
		this.permissoesEspeciais = permissoesEspeciais;
	}

	public Collection getIdsFuncionalidadesRemovidas() {
		return idsFuncionalidadesRemovidas;
	}

	public void setIdsFuncionalidadesRemovidas(
			Collection idsFuncionalidadesRemovidas) {
		this.idsFuncionalidadesRemovidas = idsFuncionalidadesRemovidas;
	}

	public String getPermissoesCheckBoxVazias() {
		return permissoesCheckBoxVazias;
	}

	public void setPermissoesCheckBoxVazias(String permissoesCheckBoxVazias) {
		this.permissoesCheckBoxVazias = permissoesCheckBoxVazias;
	}

}
