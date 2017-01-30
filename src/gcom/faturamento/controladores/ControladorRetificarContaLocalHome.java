package gcom.faturamento.controladores;

import javax.ejb.CreateException;

public interface ControladorRetificarContaLocalHome extends javax.ejb.EJBLocalHome {
	    
	public ControladorRetificarContaLocal create() throws CreateException;

}
