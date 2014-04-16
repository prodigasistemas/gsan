package gcom.faturamento;

import javax.ejb.CreateException;

/**
 * < <Descrição da Interface>>
 * 
 * @author Administrador
 */
public interface ControladorFaturamentoLocalHome extends javax.ejb.EJBLocalHome {

    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     * @exception CreateException
     *                Descrição da exceção
     */
    public ControladorFaturamentoLocal create() throws CreateException;

}
