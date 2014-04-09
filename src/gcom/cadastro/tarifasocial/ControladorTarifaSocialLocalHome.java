package gcom.cadastro.tarifasocial;

import javax.ejb.CreateException;

/**
 * < <Descrição da Interface>>
 * 
 * @author rodrigo
 */
public interface ControladorTarifaSocialLocalHome extends
        javax.ejb.EJBLocalHome {
    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     * @exception CreateException
     *                Descrição da exceção
     */
    public ControladorTarifaSocialLocal create() throws CreateException;
}
