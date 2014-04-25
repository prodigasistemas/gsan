package gcom.integracao;

import javax.ejb.CreateException;

public interface ControladorIntegracaoLocalHome extends javax.ejb.EJBLocalHome {
    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     * @exception CreateException
     *                Descrição da exceção
     */
    public ControladorIntegracaoLocal create() throws CreateException;
}
