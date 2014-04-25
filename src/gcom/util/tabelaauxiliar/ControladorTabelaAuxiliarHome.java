package gcom.util.tabelaauxiliar;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

public interface ControladorTabelaAuxiliarHome extends javax.ejb.EJBHome {
    public ControladorTabelaAuxiliarRemote create() throws CreateException,
            RemoteException;
}
