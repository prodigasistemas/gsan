package gcom.gui.relatorio.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

public class RelatorioContasRetidasActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mesAno;
	private String idGrupoFaturamento;
	
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}
	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}
	
	
	
	

}
