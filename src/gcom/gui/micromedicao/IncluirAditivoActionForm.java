package gcom.gui.micromedicao;

import org.apache.struts.validator.ValidatorActionForm;

public class IncluirAditivoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String dataInicioContrato;
	
	private String dataFimContrato;

	private String valorAditivoContrato;
	
	private String percentualTaxaSucesso;

	public String getDataFimContrato() {
		return dataFimContrato;
	}

	public void setDataFimContrato(String dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}

	public String getDataInicioContrato() {
		return dataInicioContrato;
	}

	public void setDataInicioContrato(String dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}

	public String getPercentualTaxaSucesso() {
		return percentualTaxaSucesso;
	}

	public void setPercentualTaxaSucesso(String percentualTaxaSucesso) {
		this.percentualTaxaSucesso = percentualTaxaSucesso;
	}

	public String getValorAditivoContrato() {
		return valorAditivoContrato;
	}

	public void setValorAditivoContrato(String valorAditivoContrato) {
		this.valorAditivoContrato = valorAditivoContrato;
	}
	
	
}
