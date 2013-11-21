package gcom.gui.seguranca.acesso;

import org.apache.struts.validator.ValidatorActionForm;

public class ExibirInserirControleLiberacaoPMEPActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String funcionalidade;
	private String idFuncionalidade;
	private String permissaoEspecial;
	private String idPermissaoEspecial;
	
		
	public String getFuncionalidade() {
		return funcionalidade;
	}
	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}
	public String getIdFuncionalidade() {
		return idFuncionalidade;
	}
	public void setIdFuncionalidade(String idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}
	public String getPermissaoEspecial() {
		return permissaoEspecial;
	}
	public void setPermissaoEspecial(String permissaoEspecial) {
		this.permissaoEspecial = permissaoEspecial;
	}
	public String getIdPermissaoEspecial() {
		return idPermissaoEspecial;
	}
	public void setIdPermissaoEspecial(String idPermissaoEspecial) {
		this.idPermissaoEspecial = idPermissaoEspecial;
	}
	
}
