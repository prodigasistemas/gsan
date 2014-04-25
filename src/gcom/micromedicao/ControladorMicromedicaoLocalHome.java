package gcom.micromedicao;

import javax.ejb.CreateException;

/**
 * < <Descrição da Interface>>
 * 
 * @author rodrigo
 */
public interface ControladorMicromedicaoLocalHome extends
        javax.ejb.EJBLocalHome {
    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     * @exception CreateException
     *                Descrição da exceção
     */
    public ControladorMicromedicaoLocal create() throws CreateException;
}
