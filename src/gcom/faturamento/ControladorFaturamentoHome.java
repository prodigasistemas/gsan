package gcom.faturamento;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

public interface ControladorFaturamentoHome extends javax.ejb.EJBHome {
    public ControladorFaturamentoRemote create() throws CreateException,
            RemoteException;

}
