package gcom.gui.faturamento.conta;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class CancelarContaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String contaSelected;

	private String motivoCancelamentoContaID;

	private String contasEmExtratoDebito;

	public String getContaSelected() {
		return contaSelected;
	}

	public void setContaSelected(String contaSelected) {
		this.contaSelected = contaSelected;
	}

	public String getMotivoCancelamentoContaID() {
		return motivoCancelamentoContaID;
	}

	public void setMotivoCancelamentoContaID(String motivoCancelamentoContaID) {
		this.motivoCancelamentoContaID = motivoCancelamentoContaID;
	}
	
	/**
	 * @return Retorna o campo contasEmExtratoDebito.
	 */
	public String getContasEmExtratoDebito() {
		return contasEmExtratoDebito;
	}
	
	/**
	 * @param contasEmExtratoDebito
	 *            O contasEmExtratoDebito a ser setado.
	 */
	public void setContasEmExtratoDebito(String contasEmExtratoDebito) {
		this.contasEmExtratoDebito = contasEmExtratoDebito;
	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

}
