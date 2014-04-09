package gcom.cadastro.unidade;

import javax.ejb.CreateException;

public interface ControladorUnidadeLocalHome extends javax.ejb.EJBLocalHome {
    
	public ControladorUnidadeLocal create() throws CreateException;
}
