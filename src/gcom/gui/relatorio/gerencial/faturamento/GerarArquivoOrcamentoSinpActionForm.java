package gcom.gui.relatorio.gerencial.faturamento;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC00750] Gerar Arquivo texto para orçamento e SINP
 * 
 * @author Sávio Luiz
 *
 * @date 12/02/2008
 */

public class GerarArquivoOrcamentoSinpActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String mesAnoFaturamento;

	
	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}	
	
}
