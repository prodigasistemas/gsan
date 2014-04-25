package gcom.gui.cadastro.endereco;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author compesa
 * @created 19 de Agosto de 2005
 */
public class ImportarCepActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String uploadPicture;

    /**
     * Gets the uploadPicture attribute of the ImportarCepCorreiosActionForm
     * object
     * 
     * @return The uploadPicture value
     */
    public String getUploadPicture() {
        return uploadPicture;
    }

    /**
     * Sets the uploadPicture attribute of the ImportarCepCorreiosActionForm
     * object
     * 
     * @param uploadPicture
     *            The new uploadPicture value
     */
    public void setUploadPicture(String uploadPicture) {
        this.uploadPicture = uploadPicture;
    }

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
    }
}
