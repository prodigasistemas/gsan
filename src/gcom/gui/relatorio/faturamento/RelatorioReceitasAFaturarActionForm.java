package gcom.gui.relatorio.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

public class RelatorioReceitasAFaturarActionForm extends ValidatorActionForm {

	private String mesAno;
	private String relatorioTipo;
	private Integer grupoFaturamentoID;

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getRelatorioTipo() {
		return relatorioTipo;
	}

	public void setRelatorioTipo(String relatorioTipo) {
		this.relatorioTipo = relatorioTipo;
	}

	public Integer getGrupoFaturamentoID() {
		return grupoFaturamentoID;
	}

	public void setGrupoFaturamentoID(Integer grupoFaturamentoID) {
		this.grupoFaturamentoID = grupoFaturamentoID;
	}
	
}
