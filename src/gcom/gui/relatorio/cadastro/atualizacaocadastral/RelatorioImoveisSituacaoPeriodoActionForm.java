package gcom.gui.relatorio.cadastro.atualizacaocadastral;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

public class RelatorioImoveisSituacaoPeriodoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;
	
	private String idSituacaoCadastral;
	private String dataInicial;
	private String dataFinal;
	public String getIdSituacaoCadastral() {
		return idSituacaoCadastral;
	}
	public void setIdSituacaoCadastral(String idSituacaoCadastral) {
		this.idSituacaoCadastral = idSituacaoCadastral;
	}
	public String getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
