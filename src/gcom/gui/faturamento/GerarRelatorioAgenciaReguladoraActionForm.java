package gcom.gui.faturamento;

import org.apache.struts.action.ActionForm;

public class GerarRelatorioAgenciaReguladoraActionForm extends ActionForm {
	
	private static final long serialVersionUID = 2862244916755659541L;
	
	private String mesAno;
	private Integer idAgenciaReguladora;
	private String descricaoAgenciaReguladora;

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public Integer getIdAgenciaReguladora() {
		return idAgenciaReguladora;
	}

	public void setIdAgenciaReguladora(Integer idAgenciaReguladora) {
		this.idAgenciaReguladora = idAgenciaReguladora;
	}

	public String getDescricaoAgenciaReguladora() {
		return descricaoAgenciaReguladora;
	}

	public void setDescricaoAgenciaReguladora(String descricaoAgenciaReguladora) {
		this.descricaoAgenciaReguladora = descricaoAgenciaReguladora;
	}

}
