package gcom.gerencial.cobranca;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

/**
 * 
 * 
 * @author Thiago Toscano
 * @created 19/04/2006
 */
public interface ControladorGerencialCobrancaHome extends javax.ejb.EJBHome {
    /**
     * Description of the Method
     * 
     * @return Description of the Return Value
     * @exception CreateException
     *                Description of the Exception
     * @exception RemoteException
     *                Description of the Exception
     */
    public ControladorGerencialCobrancaRemote create() throws CreateException,RemoteException;
}
