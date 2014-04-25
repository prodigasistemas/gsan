package gcom.gui;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

/**
 * Controlador ActionForm 
 * 
 * @author thiago
 * @created 20/12/2005
 */ 
public class ControladorAlteracaoGcomActionForm extends ControladorGcomActionForm {
	private static final long serialVersionUID = 1L;
	private String chavePrimaria = "";
	
	/**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    	this.chavePrimaria = "" ;
    }

	public String getChavePrimaria() {
		return chavePrimaria;
	}

	public void setChavePrimaria(String chavePrimaria) {
		this.chavePrimaria = chavePrimaria;
	}
}
