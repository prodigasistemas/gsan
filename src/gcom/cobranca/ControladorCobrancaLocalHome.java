package gcom.cobranca;

import javax.ejb.CreateException;

/**
 * < <Descrição da Interface>>
 * 
 * @author Rafael Santos
 * @since 02/01/2006
 * 
 */
public interface ControladorCobrancaLocalHome extends
        javax.ejb.EJBLocalHome {
    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     * @exception CreateException
     *                Descrição da exceção
     */
    public ControladorCobrancaLocal create() throws CreateException;
}
