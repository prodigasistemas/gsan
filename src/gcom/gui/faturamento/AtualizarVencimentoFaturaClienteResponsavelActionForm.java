package gcom.gui.faturamento;

import org.apache.struts.validator.ValidatorActionForm;

public class AtualizarVencimentoFaturaClienteResponsavelActionForm extends ValidatorActionForm{

	private static final long serialVersionUID = 1L;
	private String dataVencimento;
	private String anoMesReferencia;
	
	public String getDataVencimento() {
		return dataVencimento;
	}
	
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public String getAnoMesReferencia() {
		return anoMesReferencia;
	}
	
	public void setAnoMesReferencia(String anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
}
