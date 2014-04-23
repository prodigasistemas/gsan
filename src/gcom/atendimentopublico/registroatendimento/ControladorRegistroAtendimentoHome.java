package gcom.atendimentopublico.registroatendimento;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

public interface ControladorRegistroAtendimentoHome extends javax.ejb.EJBHome {
    
	public ControladorRegistroAtendimentoHome create() throws CreateException,
            RemoteException;
}
