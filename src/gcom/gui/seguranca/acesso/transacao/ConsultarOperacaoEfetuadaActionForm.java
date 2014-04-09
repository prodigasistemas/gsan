package gcom.gui.seguranca.acesso.transacao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ConsultarOperacaoEfetuadaActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idOperacaoEfetuada = "";

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		// TODO Auto-generated method stub
		super.reset(arg0, arg1);
	}

	public String getIdOperacaoEfetuada() {
		return idOperacaoEfetuada;
	}

	public void setIdOperacaoEfetuada(String idOperacaoEfetuada) {
		this.idOperacaoEfetuada = idOperacaoEfetuada;
	}
}
