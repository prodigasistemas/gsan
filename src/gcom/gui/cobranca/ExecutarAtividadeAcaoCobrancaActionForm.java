package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ExecutarAtividadeAcaoCobrancaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String[] idsAtividadesCobrancaCronograma;

	private String[] idsAtividadesCobrancaEventuais;

	public String[] getIdsAtividadesCobrancaCronograma() {
		return idsAtividadesCobrancaCronograma;
	}

	public void setIdsAtividadesCobrancaCronograma(
			String[] idsAtividadesCobrancaCronograma) {
		this.idsAtividadesCobrancaCronograma = idsAtividadesCobrancaCronograma;
	}

	public String[] getIdsAtividadesCobrancaEventuais() {
		return idsAtividadesCobrancaEventuais;
	}

	public void setIdsAtividadesCobrancaEventuais(
			String[] idsAtividadesCobrancaEventuais) {
		this.idsAtividadesCobrancaEventuais = idsAtividadesCobrancaEventuais;
	}

	/**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	idsAtividadesCobrancaCronograma = null;
    	idsAtividadesCobrancaEventuais = null;
    }
}
