package gcom.gui.relatorio.cobranca;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form responsável pelas propiedades do caso de uso de informar indices
 * de acrescimos por impontualidade.
 * 
 * @author Sávio Luiz
 * @date 26/09/2006
 */
public class IndiceAcrescimosImpontualidadeRelatorioActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String mesAnoReferenciaInicial;

	private String mesAnoReferenciaFinal;
	
	private String todosAcrecimos;

	public String getMesAnoReferenciaFinal() {
		return mesAnoReferenciaFinal;
	}

	public void setMesAnoReferenciaFinal(String mesAnoReferenciaFinal) {
		this.mesAnoReferenciaFinal = mesAnoReferenciaFinal;
	}

	public String getMesAnoReferenciaInicial() {
		return mesAnoReferenciaInicial;
	}

	public void setMesAnoReferenciaInicial(String mesAnoReferenciaInicial) {
		this.mesAnoReferenciaInicial = mesAnoReferenciaInicial;
	}

	public String getTodosAcrecimos() {
		return todosAcrecimos;
	}

	public void setTodosAcrecimos(String todosAcrecimos) {
		this.todosAcrecimos = todosAcrecimos;
	}

	
	
}