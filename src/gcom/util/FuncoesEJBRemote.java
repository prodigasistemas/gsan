package gcom.util;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

/**
 * < <Descrição da Interface>>
 * 
 * @author administrator
 */
public interface FuncoesEJBRemote extends EJBObject {
    /**
     * < <Descrição do método>>
     * 
     * @param contextoJNDI
     *            Descrição do parâmetro
     * @return Descrição do retorno
     * @exception RemoteException
     *                Descrição da exceção
     */
    public java.sql.Connection alocarConexao(java.lang.String contextoJNDI)
            throws RemoteException;

    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     * @exception RemoteException
     *                Descrição da exceção
     */
    public javax.transaction.UserTransaction alocarTransacao()
            throws RemoteException;

}
