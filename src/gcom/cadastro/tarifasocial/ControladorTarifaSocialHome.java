package gcom.cadastro.tarifasocial;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

public interface ControladorTarifaSocialHome extends javax.ejb.EJBHome {
    public ControladorTarifaSocialRemote create() throws CreateException,
            RemoteException;
}
