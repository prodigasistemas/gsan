package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros
 * para realização da atualização do dados das atividades de uma OS
 * (Material)
 * 
 * @author Raphael Rossiter
 * @date 15/09/2006
 */
public class ManterMaterialExecucaoOSActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String numeroOS;
	
	private String idAtividade;
	
	private String descricaoAtividade;
	
	private String idMaterialProgramado;
	
	private String idMaterialNaoProgramado;
	
	private String descricaoMaterialNaoProgramado;

	public String getDescricaoAtividade() {
		return descricaoAtividade;
	}

	public void setDescricaoAtividade(String descricaoAtividade) {
		this.descricaoAtividade = descricaoAtividade;
	}

	public String getDescricaoMaterialNaoProgramado() {
		return descricaoMaterialNaoProgramado;
	}

	public void setDescricaoMaterialNaoProgramado(
			String descricaoMaterialNaoProgramado) {
		this.descricaoMaterialNaoProgramado = descricaoMaterialNaoProgramado;
	}

	public String getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(String idAtividade) {
		this.idAtividade = idAtividade;
	}

	public String getIdMaterialNaoProgramado() {
		return idMaterialNaoProgramado;
	}

	public void setIdMaterialNaoProgramado(String idMaterialNaoProgramado) {
		this.idMaterialNaoProgramado = idMaterialNaoProgramado;
	}

	public String getIdMaterialProgramado() {
		return idMaterialProgramado;
	}

	public void setIdMaterialProgramado(String idMaterialProgramado) {
		this.idMaterialProgramado = idMaterialProgramado;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}
	
	

}
