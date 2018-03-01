package gcom.portal;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface ControladorLojaVirtualLocalHome extends EJBLocalHome {

	public ControladorLojaVirtualLocal create() throws CreateException;
}
