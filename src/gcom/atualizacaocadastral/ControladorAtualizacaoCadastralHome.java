package gcom.atualizacaocadastral;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

public interface ControladorAtualizacaoCadastralHome extends javax.ejb.EJBHome {
    public ControladorAtualizacaoCadastralRemote create() throws CreateException,
            RemoteException;

}