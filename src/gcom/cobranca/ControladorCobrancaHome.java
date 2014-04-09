package gcom.cobranca;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

/**
 * 
 * @author Rafael Santos
 * @since 02/01/2006 
 *
 */
public interface ControladorCobrancaHome extends javax.ejb.EJBHome {
    public ControladorCobrancaRemote create() throws CreateException,
            RemoteException;
}
