package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <<>>
 * 
 * @author lms
 * @date 03/08/2006
 */
public class PesquisarServicoTipoMaterialActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	String idMaterial;
	String descricaoMaterial;
	String quantidadePadrao;
	String method;
	
	public String getDescricaoMaterial() {
		return descricaoMaterial;
	}
	public void setDescricaoMaterial(String descricaoMaterial) {
		this.descricaoMaterial = descricaoMaterial;
	}
	public String getIdMaterial() {
		return idMaterial;
	}
	public void setIdMaterial(String idMaterial) {
		this.idMaterial = idMaterial;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getQuantidadePadrao() {
		return quantidadePadrao;
	}
	public void setQuantidadePadrao(String quantidadePadrao) {
		this.quantidadePadrao = quantidadePadrao;
	}	
	
}
