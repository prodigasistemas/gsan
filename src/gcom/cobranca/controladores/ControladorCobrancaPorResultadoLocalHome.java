package gcom.cobranca.controladores;

import javax.ejb.CreateException;

public interface ControladorCobrancaPorResultadoLocalHome extends javax.ejb.EJBLocalHome {
	    
	public ControladorCobrancaPorResultadoLocal create() throws CreateException;

}
