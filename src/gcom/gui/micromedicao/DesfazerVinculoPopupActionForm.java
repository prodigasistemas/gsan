package gcom.gui.micromedicao;

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * Form do Desfazer Vinculo Popup 
 * 
 * @author Rafael Santos
 * @since 11/01/2006 [UC00098] Manter Vinculos Imoveis Rateio Consumo
 */
public class DesfazerVinculoPopupActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String[] idImovel;

	private String clienteimovel;
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	/**
	 * @return Returns the idImovel.
	 */
	public String[] getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel The idImovel to set.
	 */
	public void setIdImovel(String[] idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Returns the clienteimovel.
	 */
	public String getClienteimovel() {
		return clienteimovel;
	}

	/**
	 * @param clienteimovel The clienteimovel to set.
	 */
	public void setClienteimovel(String clienteimovel) {
		this.clienteimovel = clienteimovel;
	}

}
