package gcom.gui.seguranca.acesso.usuario;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Realiza a pesquisa de usuario de acordo com os parâmetros informados
 * 
 * @author Vivianne Sousa
 * @created 24 de fevereiro  de 2006
 */
public class PesquisarUsuarioActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String tipoUsuario;
	private String nome;
	private String matriculaFuncionario;
	private String nomeFuncionario;
	private String tipoPesquisa;
	private String login;
	private String idUnidadeOrganizacional;
	private String nomeUnidadeOrganizacional;

	/**
	 * @return Retorna o campo idUnidadeOrganizacional.
	 */
	public String getIdUnidadeOrganizacional() {
		return idUnidadeOrganizacional;
	}

	/**
	 * @param idUnidadeOrganizacional O idUnidadeOrganizacional a ser setado.
	 */
	public void setIdUnidadeOrganizacional(String idUnidadeOrganizacional) {
		this.idUnidadeOrganizacional = idUnidadeOrganizacional;
	}

	/**
	 * @return Retorna o campo nomeUnidadeOrganizacional.
	 */
	public String getNomeUnidadeOrganizacional() {
		return nomeUnidadeOrganizacional;
	}

	/**
	 * @param nomeUnidadeOrganizacional O nomeUnidadeOrganizacional a ser setado.
	 */
	public void setNomeUnidadeOrganizacional(String nomeUnidadeOrganizacional) {
		this.nomeUnidadeOrganizacional = nomeUnidadeOrganizacional;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getMatriculaFuncionario() {
		return matriculaFuncionario;
	}

	public void setMatriculaFuncionario(String matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	

}

