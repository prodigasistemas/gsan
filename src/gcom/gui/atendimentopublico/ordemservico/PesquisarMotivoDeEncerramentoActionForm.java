package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

public class PesquisarMotivoDeEncerramentoActionForm extends ValidatorActionForm{
	
	private static final long serialVersionUID = 1L;
	
	String[] idsMotivoEncerramento;
	

	public String[] getIdsMotivoEncerramento() {
		return idsMotivoEncerramento;
	}
	
	public void setIdsMotivoEncerramento(String[] idsMotivoEncerramento) {
		this.idsMotivoEncerramento = idsMotivoEncerramento;
	}

}
