package gcom.seguranca;

import javax.ejb.CreateException;

/**
 * Definição da lógica de negócio do Session Bean de
 * ControladorPermissaoEspecial
 * 
 * @author Rodrigo Silveira
 * @created 07/11/2006
 */

public interface ControladorPermissaoEspecialLocalHome
		extends
			javax.ejb.EJBLocalHome {
	public ControladorPermissaoEspecialLocal create() throws CreateException;
}
