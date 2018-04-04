package gcom.gui.cobranca.cobrancaporresultado;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class RetirarSituacaoCobrancaActionForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private FormFile arquivo;

	public FormFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(FormFile arquivo) {
		this.arquivo = arquivo;
	}
}
