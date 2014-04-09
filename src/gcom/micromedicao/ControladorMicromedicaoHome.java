package gcom.micromedicao;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

public interface ControladorMicromedicaoHome extends javax.ejb.EJBHome {
    public ControladorMicromedicaoRemote create() throws CreateException,
            RemoteException;
}
