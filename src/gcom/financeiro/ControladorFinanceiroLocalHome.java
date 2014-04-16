package gcom.financeiro;

import javax.ejb.CreateException;

/**
 * < <Descrição da Interface>>
 * 
 * @author Raphael Rossiter
 * @since 09/01/2006
 * 
 */
public interface ControladorFinanceiroLocalHome extends
        javax.ejb.EJBLocalHome {
    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     * @exception CreateException
     *                Descrição da exceção
     */
    public ControladorFinanceiroLocal create() throws CreateException;
}
