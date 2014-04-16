package gcom.gui.cadastro;



import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0884]Filtrar Cep
 * 
 * @author Vinicius Medeiros
 * @date 12/02/2009
 */

public class FiltrarCepActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
