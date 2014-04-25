package gcom.cadastro.unidade;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

public interface ControladorUnidadeHome extends javax.ejb.EJBHome {
    
	public ControladorUnidadeRemote create() throws CreateException,
            RemoteException;
}
