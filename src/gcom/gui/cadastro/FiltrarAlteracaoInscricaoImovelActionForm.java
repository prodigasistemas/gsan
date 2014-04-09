package gcom.gui.cadastro;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC1162]	Autorizar Alteracao Inscricao Imovel
 * 
 * @author Rodrigo Cabral
 * @date 04/04/2011
 */

public class FiltrarAlteracaoInscricaoImovelActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idLocalidade;
	private String desLocalidade;
	private String idSetorComercial;
	private String codigoSetorComercial;
	private String desSetorComercial;
	
	
	public String getDesLocalidade() {
		return desLocalidade;
	}
	public void setDesLocalidade(String desLocalidade) {
		this.desLocalidade = desLocalidade;
	}
	public String getDesSetorComercial() {
		return desSetorComercial;
	}
	public void setDesSetorComercial(String desSetorComercial) {
		this.desSetorComercial = desSetorComercial;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	
	

}
