package gcom.cadastro.imovel;

import javax.ejb.CreateException;

/**
 * < <Descrição da Interface>>
 * 
 * @author rodrigo
 */
public interface ControladorImovelLocalHome extends javax.ejb.EJBLocalHome {
    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     * @exception CreateException
     *                Descrição da exceção
     */
    public ControladorImovelLocal create() throws CreateException;

}
