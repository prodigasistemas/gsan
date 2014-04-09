package gcom.gui.atendimentopublico;

import org.apache.struts.validator.ValidatorActionForm;


/**
 *  
 * @author 	Vinícius Medeiros 
 * @date  	03/04/2008
 */

public class AtualizarMotivoCorteActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idMotivoCorte;
	
	private String descricao;
		
	private String dataUltimaAlteracao;
	
	private String indicadorUso;

	public String getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(String dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdMotivoCorte() {
		return idMotivoCorte;
	}

	public void setIdMotivoCorte(String idMotivoCorte) {
		this.idMotivoCorte = idMotivoCorte;
	}

	public String getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

}
