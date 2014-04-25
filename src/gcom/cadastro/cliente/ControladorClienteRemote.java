package gcom.cadastro.cliente;

import gcom.util.ControladorException;

import java.rmi.RemoteException;
import java.util.Collection;

/**
 * Declaração pública de serviços do Session Bean de ControladorCliente
 * 
 * @author Sávio Luiz
 * @created 25 de Abril de 2005
 */
public interface ControladorClienteRemote extends javax.ejb.EJBObject {

	/**
	 * Insere um cliente no sistema
	 * 
	 * @param cliente
	 *            Cliente a ser inserido
	 * @param telefones
	 *            Telefones do cliente
	 * @param enderecos
	 *            Endereços do cliente
	 * @return Descrição do retorno
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public Integer inserirCliente(Cliente cliente, Collection telefones,
			Collection enderecos) throws RemoteException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param clienteImovel
	 *            Descrição do parâmetro
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void inserirClienteImovel(ClienteImovel clienteImovel)
			throws RemoteException;

	/**
	 * atualiza um cliente imovel economia com a data final da relação e o
	 * motivo.
	 * 
	 * @param clienteImovelEconomia
	 *            Description of the Parameter
	 * @exception RemoteException
	 *                Description of the Exception
	 */
	public void atualizarClienteImovelEconomia(
			Collection clientesImoveisEconomia) throws RemoteException;

	/**
	 * Atualiza um cliente no sistema
	 * 
	 * @param cliente
	 *            Cliente a ser atualizado
	 * @param telefones
	 *            Telefones do cliente
	 * @param enderecos
	 *            Endereços do cliente
	 * @exception RemoteException
	 *                Descrição da exceção
	 */
	public void atualizarCliente(Cliente cliente, Collection telefones,
			Collection enderecos) throws RemoteException;
	
	/**
	 * Metodo que retorno todos os clinte do filtro passado
	 * 
	 * @param filtroCliente
	 *            Descrição do parâmetro
	 * @return Description of the Return Value
	 * @autor thiago toscano 
	 * @date 15/12/2005
	 * @throws ControladorException
	 */
	public Collection pesquisarCliente(FiltroCliente filtroCliente) throws ControladorException ;


}
