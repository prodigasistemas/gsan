package gcom.cadastro.imovel;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

public interface ControladorImovelHome extends javax.ejb.EJBHome {
    public ControladorImovelRemote create() throws CreateException,
            RemoteException;

}
