package gcom.gui.cadastro;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class AtualizarDadosCelularActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private FormFile uploadPicture;

	public FormFile getUploadPicture() {
		return uploadPicture;
	}

	public void setUploadPicture(FormFile uploadPicture) {
		this.uploadPicture = uploadPicture;
	}
	
}
