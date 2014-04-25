package gcom.cadastro.cliente;

import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * Interface para o repositório de cliente endereço
 * 
 * @author Sávio Luiz
 * @created 23 de Julho de 2005
 */
public interface IRepositorioClienteEndereco {

	/**
	 * Pesquisa uma coleção de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarClienteEndereco(
			FiltroClienteEndereco filtroClienteEndereco)
			throws ErroRepositorioException;

	/**
	 * Pesquisa ClienteEndereco percorrendo o ClienteImovel
	 * 
	 * @param filtroClienteEndereco
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Cliente> pesquisarClienteEnderecoClienteImovel(
			FiltroClienteEndereco filtroClienteEndereco)
			throws ErroRepositorioException;
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEnderecosClienteAbreviado(Integer idCliente)
		throws ErroRepositorioException;
	
	
	/**
	 * Atualiza logradouroBairro de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException ;
	
	/**
	 * Atualiza logradouroCep de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, 
			LogradouroCep logradouroCepNovo) throws ErroRepositorioException ;

}
