package gcom.util;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * < <Descrição da Interface>>
 * 
 * @author Administrador
 */
public interface FuncoesEJBHome extends EJBHome {
    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     * @exception RemoteException
     *                Descrição da exceção
     * @exception CreateException
     *                Descrição da exceção
     */
    public FuncoesEJBRemote create() throws RemoteException, CreateException;
}
