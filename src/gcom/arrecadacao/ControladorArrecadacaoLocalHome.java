package gcom.arrecadacao;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

/**
 * Description of the Interface
 * 
 * @author Administrador
 * @created 25 de Abril de 2005
 */
public interface ControladorArrecadacaoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Description of the Method
	 * 
	 * @return Description of the Return Value
	 * @exception CreateException
	 *                Description of the Exception
	 * @exception RemoteException
	 *                Description of the Exception
	 */
	public ControladorArrecadacaoLocal create() throws CreateException;
}
