package gcom.cobranca.contratoparcelamento;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

/**
 * Description of the Interface
 * 
 * @author Paulo Diniz
 * @created 18 de março 2011
 */
public interface ControladorContratoParcelamentoLocalHome extends javax.ejb.EJBLocalHome {
    /**
     * Description of the Method
     * 
     * @return Description of the Return Value
     * @exception CreateException
     *                Description of the Exception
     * @exception RemoteException
     *                Description of the Exception
     */
    public ControladorContratoParcelamentoLocal create() throws CreateException;
}
