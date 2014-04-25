package gcom.cobranca.contratoparcelamento;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

/**
 * Description of the Interface
 * 
 * @author Paulo Diniz
 * @created 18 de março de 2011
 */
public interface ControladorContratoParcelamentoHome extends javax.ejb.EJBHome {
    /**
     * Description of the Method
     * 
     * @return Description of the Return Value
     * @exception CreateException
     *                Description of the Exception
     * @exception RemoteException
     *                Description of the Exception
     */
    public ControladorContratoParcelamentoRemote create() throws CreateException,
            RemoteException;
}
