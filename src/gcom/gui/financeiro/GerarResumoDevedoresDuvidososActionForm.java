package gcom.gui.financeiro;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author pedro alexandre
 * @created 08/06/2007
 */
public class GerarResumoDevedoresDuvidososActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
    
    private String anoMesReferenciaContabil;

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	anoMesReferenciaContabil = null;
    }

	public String getAnoMesReferenciaContabil() {
		return anoMesReferenciaContabil;
	}

	public void setAnoMesReferenciaContabil(String anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	

}
