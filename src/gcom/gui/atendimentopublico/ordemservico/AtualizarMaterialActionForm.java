package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorForm;


/**[UC0383] MANTER TIPO MATERIAL
 * [SB0001] Atualizar Material
 *
 * @author Kássia Albuquerque
 * @date 17/11/2006
 */

public class AtualizarMaterialActionForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;
	
	private String id;	
	private String codigo;
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

	public String getId() {
		return id;
	}

	public void setId(String codigoMterial) {
		this.id = codigoMterial;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
