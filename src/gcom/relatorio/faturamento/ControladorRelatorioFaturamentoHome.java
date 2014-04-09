package gcom.relatorio.faturamento;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

/**
 * Foi criador esse controlador para relatorio especificos para faturamento
 * 
 * @author Rafael Pinto
 * @created 16/06/2007
 */

public interface ControladorRelatorioFaturamentoHome extends javax.ejb.EJBHome {
    /**
     * Description of the Method
     * 
     * @return Description of the Return Value
     * @exception CreateException
     *                Description of the Exception
     * @exception RemoteException
     *                Description of the Exception
     */
    public ControladorRelatorioFaturamentoHome create() throws CreateException,
            RemoteException;
}
