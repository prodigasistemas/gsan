package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

public class PesquisarServicoTipoMotivoEncerramentoActionForm extends ValidatorActionForm{
	private static final long serialVersionUID = 1L;
	
	String[] motivosEncerramento;
	String method;
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String[] getMotivosEncerramento() {
		return motivosEncerramento;
	}
	public void setMotivosEncerramento(String[] motivosEncerramento) {
		this.motivosEncerramento = motivosEncerramento;
	}
	
}
