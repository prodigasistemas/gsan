package gcom.gui.seguranca.acesso;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rafael Pinto
 * @date 07/08/2008
 */

public class OrganizarMenuActionForm extends
		ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String modulo;
	private String tipoOrdem;
	private String numeroOrdemFuncionalidade;
	private String numeroOrdemPasta;

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getNumeroOrdemPasta() {
		return numeroOrdemPasta;
	}

	public void setNumeroOrdemPasta(String numeroOrdemPasta) {
		this.numeroOrdemPasta = numeroOrdemPasta;
	}

	public String getNumeroOrdemFuncionalidade() {
		return numeroOrdemFuncionalidade;
	}

	public void setNumeroOrdemFuncionalidade(String numeroOrdemFuncionalidade) {
		this.numeroOrdemFuncionalidade = numeroOrdemFuncionalidade;
	}

	public String getTipoOrdem() {
		return tipoOrdem;
	}

	public void setTipoOrdem(String tipoOrdem) {
		this.tipoOrdem = tipoOrdem;
	}

}
