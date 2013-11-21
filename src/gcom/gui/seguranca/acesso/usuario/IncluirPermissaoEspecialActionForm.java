package gcom.gui.seguranca.acesso.usuario;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1115] Incluir Permissão Especial por Unidade Organizacional
 *
 * @author Mariana Victor
 * @date 29/12/2010
 * 
 */
public class IncluirPermissaoEspecialActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idUnidade;
	
	private String nomeUnidade;
	
	private String[] idUsuario;
	
	private String[] idPermissao;
	
	
	public String getIdUnidade() {
		return idUnidade;
	}
	
	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}
	
	public String getNomeUnidade() {
		return nomeUnidade;
	}
	
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}
	
	public String[] getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(String[] idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String[] getIdPermissao() {
		return idPermissao;
	}

	public void setIdPermissao(String[] idPermissao) {
		this.idPermissao = idPermissao;
	}
	
}
