package gcom.cadastro.endereco;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

public interface ControladorEnderecoHome extends javax.ejb.EJBHome {
    public ControladorEnderecoRemote create() throws CreateException,
            RemoteException;
}
