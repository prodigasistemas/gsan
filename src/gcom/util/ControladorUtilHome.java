package gcom.util;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

public interface ControladorUtilHome extends javax.ejb.EJBHome {
    public ControladorUtilRemote create() throws CreateException,
            RemoteException;
}
