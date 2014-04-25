package gcom.gui.seguranca.acesso;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import org.apache.struts.validator.ValidatorForm;


/**
 * Esse action form manipula os dados da página de Login 
 *
 * @author Pedro Alexandre
 * @date 07/07/2006
 */
public class EfetuarLoginActionForm extends ValidatorForm {
    private String login;
    private String senha;
    private static final long serialVersionUID = 1L;
   
    /**
	 * @return Retorna o campo login.
	 */
	public String getLogin() {
		return login;
	}


	/**
	 * @param login O login a ser setado.
	 */
	public void setLogin(String login) {
		this.login = login;
	}


	/**
	 * @return Retorna o campo senha.
	 */
	public String getSenha() {
		return senha;
	}


	/**
	 * @param senha O senha a ser setado.
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}


	/**
     * <<Descrição do método>>
     *
     * @param actionMapping       Descrição do parâmetro
     * @param httpServletRequest  Descrição do parâmetro
     */
    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        login = null;
        senha = null;
    }
}
