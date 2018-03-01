package gcom.cobranca.controladores;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface ControladorParcelamentoLocalHome extends EJBLocalHome {

	public ControladorParcelamentoLocal create() throws CreateException;
}