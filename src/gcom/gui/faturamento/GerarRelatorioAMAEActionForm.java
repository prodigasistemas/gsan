package gcom.gui.faturamento;

import org.apache.struts.action.ActionForm;

public class GerarRelatorioAMAEActionForm extends ActionForm {
	
	private static final long serialVersionUID = 2862244916755659541L;
	
	private String mesAno;
	private Integer codigoMunicipio;
	private String descricaoMunicipio;

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}


}
