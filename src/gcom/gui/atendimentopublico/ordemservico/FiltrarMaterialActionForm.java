package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorForm;

/**
 * [UC0382] Filtrar Material
 *
 * @author Kássia Albuquerque
 * @date 14/11/2006
 */


public class FiltrarMaterialActionForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
private String descricaoMaterial;
private String abrevMaterial;
private String unidadeMaterial;
private String indicadorUso;


public String getAbrevMaterial() {
	return abrevMaterial;
}
public void setAbrevMaterial(String abrevMaterial) {
	this.abrevMaterial = abrevMaterial;
}
public String getDescricaoMaterial() {
	return descricaoMaterial;
}
public void setDescricaoMaterial(String descricaoMaterial) {
	this.descricaoMaterial = descricaoMaterial;
}
public String getIndicadorUso() {
	return indicadorUso;
}
public void setIndicadorUso(String indicadorUso) {
	this.indicadorUso = indicadorUso;
}
public String getUnidadeMaterial() {
	return unidadeMaterial;
}
public void setUnidadeMaterial(String unidadeMaterial) {
	this.unidadeMaterial = unidadeMaterial;
}





	
	
	
}
