package gcom.gui.relatorio.faturamento;

import gcom.util.ConstantesSistema;

import org.apache.struts.validator.ValidatorActionForm;

public class RelatorioReceitasAFaturarActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = -669949559541569487L;

	private String mesAno;
	private String relatorioTipo;
	private Integer grupoFaturamentoID;
	private Short indicadorCategoria;

	public RelatorioReceitasAFaturarActionForm() {
		this.indicadorCategoria = new Short(ConstantesSistema.NAO.toString());
	}
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

	public Short getIndicadorCategoria() {
		return indicadorCategoria;
	}

	public void setIndicadorCategoria(Short indicadorCategoria) {
		this.indicadorCategoria = indicadorCategoria;
	}
	
}
