package gcom.financeiro;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

/**
 * 
 * @author Raphael Rossiter
 * @since 09/01/2006
 *
 */
public interface ControladorFinanceiroHome extends javax.ejb.EJBHome {
    public ControladorFinanceiroRemote create() throws CreateException,
            RemoteException;
}
