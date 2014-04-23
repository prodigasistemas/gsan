package gcom.gui.relatorio.faturamento.conta;

import org.apache.struts.action.ActionForm;

public class GerarRelatorioContasEmitidasActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String grupoFaturamento;
	
	private String mesAno;
	
	private String[] esferaPoder;
	
	private String tipoImpressao;

	public String[] getEsferaPoder() {
		return esferaPoder;
	}

	public void setEsferaPoder(String[] esferaPoder) {
		this.esferaPoder = esferaPoder;
	}

	public String getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(String grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getTipoImpressao() {
		return tipoImpressao;
	}

	public void setTipoImpressao(String tipoImpressao) {
		this.tipoImpressao = tipoImpressao;
	}

	
	
}
