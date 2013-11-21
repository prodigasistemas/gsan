package gcom.gui.cobranca;

import org.apache.struts.action.ActionForm;

/**
 * @author Raphael Rossiter
 * @date 13/09/2007
 */
public class ManterAtividadeCobrancaActionForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] cobrancaAtividadeSelectID;

	public String[] getCobrancaAtividadeSelectID() {
		return cobrancaAtividadeSelectID;
	}

	public void setCobrancaAtividadeSelectID(String[] cobrancaAtividadeSelectID) {
		this.cobrancaAtividadeSelectID = cobrancaAtividadeSelectID;
	}

}
