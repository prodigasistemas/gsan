package gcom.gui.cadastro.sistemaparametro;

import org.apache.struts.action.ActionForm;



/**
 * [UC__?__] Informar Mensagem do Sistema
 * 
 * @author Kassia Albuquerque
 * @created 27/02/2007
 */


public class InformarMensagemSistemaActionForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String mensagemSistema;

	public String getMensagemSistema() {
		return mensagemSistema;
	}

	public void setMensagemSistema(String mensagemSistema) {
		this.mensagemSistema = mensagemSistema;
	}
	
    
}
