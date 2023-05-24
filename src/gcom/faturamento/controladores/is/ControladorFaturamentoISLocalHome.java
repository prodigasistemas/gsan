package gcom.faturamento.controladores.is;

import javax.ejb.CreateException;

public interface ControladorFaturamentoISLocalHome extends javax.ejb.EJBLocalHome {
	    
	public ControladorFaturamentoISLocal create() throws CreateException;

}
