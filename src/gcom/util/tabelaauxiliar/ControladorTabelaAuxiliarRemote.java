package gcom.util.tabelaauxiliar;

import gcom.util.filtro.Filtro;

import java.rmi.RemoteException;
import java.util.Collection;

/**
 * < <Descrição da Interface>>
 * 
 * @author rodrigo
 */
public interface ControladorTabelaAuxiliarRemote extends javax.ejb.EJBObject {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param tabelaAuxiliar
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void atualizarTabelaAuxiliar(TabelaAuxiliarAbstrata tabelaAuxiliar)
			throws RemoteException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param filtro
	 *            Descrição do parâmetro
	 * @param nomePacoteObjeto
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public Collection pesquisarTeste(Filtro filtro, String nomePacoteObjeto)
			throws RemoteException;
}
