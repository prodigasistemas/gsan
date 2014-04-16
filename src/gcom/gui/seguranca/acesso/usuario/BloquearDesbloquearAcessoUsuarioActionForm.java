package gcom.gui.seguranca.acesso.usuario;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 08/06/2006
 */
public class BloquearDesbloquearAcessoUsuarioActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String login;

	private String usuarioSituacao;

	/**
	 * @return Retorna o campo login.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            O login a ser setado.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return Retorna o campo usuarioSituacao.
	 */
	public String getUsuarioSituacao() {
		return usuarioSituacao;
	}

	/**
	 * @param usuarioSituacao
	 *            O usuarioSituacao a ser setado.
	 */
	public void setUsuarioSituacao(String usuarioSituacao) {
		this.usuarioSituacao = usuarioSituacao;
	}

}
