package gcom.gerencial.arrecadacao;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

/**
 * Description of the Interface
 * 
 * @author Ivan Sérgio
 * @created 11 de Maio de 2007
 */
public interface ControladorGerencialArrecadacaoLocalHome extends javax.ejb.EJBLocalHome {
    /**
     * Description of the Method
     * 
     * @return Description of the Return Value
     * @exception CreateException
     *                Description of the Exception
     * @exception RemoteException
     *                Description of the Exception
     */
    public ControladorGerencialArrecadacaoLocal create() throws CreateException;
}
