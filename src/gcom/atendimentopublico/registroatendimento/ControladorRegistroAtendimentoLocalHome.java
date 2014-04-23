package gcom.atendimentopublico.registroatendimento;


import javax.ejb.CreateException;

public interface ControladorRegistroAtendimentoLocalHome extends javax.ejb.EJBLocalHome {
    
	public ControladorRegistroAtendimentoLocal create() throws CreateException;
}
