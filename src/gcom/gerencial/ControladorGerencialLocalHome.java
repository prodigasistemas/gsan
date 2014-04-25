package gcom.gerencial;

import javax.ejb.CreateException;

/**
 * 
 * 
 * @author Raphael Rossiter
 * @created 20/05/2006
 */
public interface ControladorGerencialLocalHome extends javax.ejb.EJBLocalHome {
	
	public ControladorGerencialLocal create() throws CreateException;
    
}
