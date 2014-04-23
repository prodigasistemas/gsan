package gcom.util;

import javax.ejb.CreateException;

/**
 * < <Descrição da Interface>>
 * 
 * @author rodrigo
 */
public interface ControladorUtilLocalHome extends javax.ejb.EJBLocalHome {
    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     * @exception CreateException
     *                Descrição da exceção
     */
    public ControladorUtilLocal create() throws CreateException;
}
