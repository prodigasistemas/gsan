package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0243] Inserir Comando de Ação de Cobrança - Popup 
 * @author Rafael Santos
 * @since 02/03/2006
 */
public class InserirCriterioCobrancaLinhaConsultarPopupActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String descricaoCobrancaCriterio;
	private String rotaInicial;

	private String rotaFinal;
	
	
	public String getRotaFinal() {
		return rotaFinal;
	}

	public void setRotaFinal(String rotaFinal) {
		this.rotaFinal = rotaFinal;
	}

	public String getRotaInicial() {
		return rotaInicial;
	}

	public void setRotaInicial(String rotaInicial) {
		this.rotaInicial = rotaInicial;
	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		
	}

	/**
	 * @return Returns the descricaoCobrancaCriterio.
	 */
	public String getDescricaoCobrancaCriterio() {
		return descricaoCobrancaCriterio;
	}

	/**
	 * @param descricaoCobrancaCriterio The descricaoCobrancaCriterio to set.
	 */
	public void setDescricaoCobrancaCriterio(String descricaoCobrancaCriterio) {
		this.descricaoCobrancaCriterio = descricaoCobrancaCriterio;
	}

}
