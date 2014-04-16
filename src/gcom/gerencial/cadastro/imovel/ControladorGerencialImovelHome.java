package gcom.gerencial.cadastro.imovel;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

public interface ControladorGerencialImovelHome extends javax.ejb.EJBHome {
    public ControladorGerencialImovelRemote create() throws CreateException, RemoteException;

}
