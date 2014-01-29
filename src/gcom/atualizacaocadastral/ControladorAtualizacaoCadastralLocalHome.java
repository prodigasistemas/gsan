package gcom.atualizacaocadastral;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface ControladorAtualizacaoCadastralLocalHome  extends EJBLocalHome{

	public ControladorAtualizacaoCadastralLocal create() throws CreateException;
	
}
