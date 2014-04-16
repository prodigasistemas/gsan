package gcom.gui.seguranca.acesso;

import org.apache.struts.validator.ValidatorForm;


/**
 * Esse action form manipula os dados da página de alterar a senha do usuário 
 *
 * @author Pedro Alexandre
 * @date 07/07/2006
 */
public class EfetuarAlteracaoSenhaPorMatriculaActionForm extends ValidatorForm {
    private String login;
    private String nomeUsuario;
    private String dataNascimento;
    private static final long serialVersionUID = 1L;
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
       
    
}
