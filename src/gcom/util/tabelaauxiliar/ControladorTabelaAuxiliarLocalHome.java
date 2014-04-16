package gcom.util.tabelaauxiliar;

import javax.ejb.CreateException;

/**
 * < <Descrição da Interface>>
 * 
 * @author rodrigo
 */
public interface ControladorTabelaAuxiliarLocalHome extends
        javax.ejb.EJBLocalHome {
    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     * @exception CreateException
     *                Descrição da exceção
     */
    public ControladorTabelaAuxiliarLocal create() throws CreateException;
}
