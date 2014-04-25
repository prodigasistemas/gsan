package gcom.gui.faturamento.conta;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class VisualizarMensagemContaActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;


	private String mensagemConta01;
	private String mensagemConta02;
	private String mensagemConta03;
	
	public String getMensagemConta01() {
		return mensagemConta01;
	}
	public void setMensagemConta01(String mensagemConta01) {
		this.mensagemConta01 = mensagemConta01;
	}
	public String getMensagemConta02() {
		return mensagemConta02;
	}
	public void setMensagemConta02(String mensagemConta02) {
		this.mensagemConta02 = mensagemConta02;
	}
	public String getMensagemConta03() {
		return mensagemConta03;
	}
	public void setMensagemConta03(String mensagemConta03) {
		this.mensagemConta03 = mensagemConta03;
	}
	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		mensagemConta01="";
		mensagemConta02="";
		mensagemConta03="";
	}
}
