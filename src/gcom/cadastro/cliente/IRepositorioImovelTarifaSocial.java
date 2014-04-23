package gcom.cadastro.cliente;

import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 22 de Julho de 2005
 */
public interface IRepositorioImovelTarifaSocial {


	/**
	 * Pesquisa os imoveis com o filtro passado que tenha tarifa social 
	 *
	 * @param filtroClienteImovel
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImovelTarfiaSocial(
			FiltroClienteImovel filtroClienteImovel, Integer numeroPagina)
			throws ErroRepositorioException;

	/**
	 * Pesquisa a quantidade de tarifa social
	 *  @author Rafael Santos
	 *  @since 05/09/2006 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public int pesquisarQuantidadeImovelTarfiaSocial(
			FiltroClienteImovel filtroClienteImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa as Tarifas Sociais Dado Economia pelo id do Imóvel carregando a Tarifa Social Revisao Motivo 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarTarifaSocialDadoEconomia(Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Verifica se o cliente usuário do imóvel já está relacionado em outro imóvel na tarifa social 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 02/01/2007
	 */
	public Collection verificarClienteCadastradoTarifaSocial(Integer idCliente) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verifica se o mesmo cliente está vinculado a mais de uma economia como
	 * usuário
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 03/01/2007
	 */
	public int pesquisarClienteImovelEconomiaCount(Integer idImovel,
			Integer idCliente) throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorna os clientes usuários das economias do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 03/01/2007
	 */
	public Collection pesquisarClientesUsuariosImovelEconomia(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verifica se o cliente usuário está vinculado na tarifa social a outro
	 * imóvel ou economia com motivo de revisão que permita recadastramento
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 04/01/2007
	 */
	public Collection pesquisarClientesUsuarioExistenteTarifaSocial(Integer idCliente) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verificar se existe uma OS de vistoria para um imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 05/01/2007
	 */
	public Collection verificarOSVistoriaImovel(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verificar se existe um motivo de exclusão para o cliente que não permite
	 * recadastramento na tarifa social
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 05/01/2007
	 */
	public Collection verificarClienteMotivoExclusaoRecadastramento(Integer idCliente) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorna os cliente a partir do id do clienteImovelEconomia
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 08/01/2007
	 */
	public Integer pesquisarClienteImovelEconomia(Integer idClienteImovelEconomia) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Verificar se existe uma OS de vistoria para uma economia do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 05/01/2007
	 */
	public Collection verificarOSVistoriaImovelEconomia(Integer idImovelEconomia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorno o histórico medição atual do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 08/01/2007
	 */
	public Collection pesquisarMedicaoHistoricoImovel(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorno o histórico de consumo atual do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 08/01/2007
	 */
	public Collection pesquisarConsumoHistoricoImovel(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa os dados da tarifa social e do cliente usuário
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 15/01/2007
	 */
	public Collection pesquisarDadosClienteTarifaSocial(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna a tarifa social a partir do seu id
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 16/01/2007
	 */
	public TarifaSocialDadoEconomia pesquisarTarifaSocial(Integer idTarifaSocial) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Verifica se existe tarifa social para o imóvel que não tenha sido
	 * excluído
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 16/01/2007
	 */
	public Collection pesquisarTarifaSocialImovel(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna os clientes do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 19/01/2007
	 */
	public Collection pesquisarClientesImovelTarifaSocial(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna os clientes das economias do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 19/01/2007
	 */
	public Collection pesquisarClientesImovelEconomiaTarifaSocial(Integer idImovelEconomia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa o cliente pelo seu id carregando o seu tipo
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 22/01/2007
	 */
	public Collection pesquisarClienteComClienteTipoTarifaSocial(Integer idCliente) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa os dados da tarifa social e do cliente usuário para cada economia
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 25/01/2007
	 */
	public Collection pesquisarDadosClienteEconomiaTarifaSocial(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social 
	 * 
	 * Verifica se o cliente usuário do imóvel já está relacionado em outro imóvel na tarifa social 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 30/01/2007
	 */
	public Collection verificarClienteCadastradoManterTarifaSocialUmaEconomia(Integer idCliente, Integer idImovel) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social 
	 * 
	 * Verifica se o cliente usuário da economia do imóvel já está relacionado em outro imóvel na tarifa social 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 30/01/2007
	 */
	public Collection verificarClienteCadastradoManterTarifaSocialMultiplasEconomias(Integer idCliente, Integer idImovelEconomia) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Pesquisa a economia do imóvel pelo seu id
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 01/02/2007
	 */
	public ImovelEconomia pesquisarImovelEconomiaPeloId(Integer idImovelEconomia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Seta o indicador do nome da conta para 2 nos clientes proprietário e
	 * usuários
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 01/02/2007
	 */
	public void atualizarNomeContaClienteImovelTarifaSocial(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa as Tarifas Sociais Dado Economia pelo id da Economia do Imóvel carregando a Tarifa Social Revisao Motivo 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarTarifaSocialDadoEconomiaImovelEconomia(Integer idImovelEconomia) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0069] - Manter Dados Tarifa Social
	 * 
	 * Retorna o id cliente usuário da economias do imóvel
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 19/01/2007
	 */
	public Integer pesquisarClienteUsuarioImovelEconomiaTarifaSocial(Integer idImovelEconomia) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social
	 * 
	 * Retorna a economia do imóvel a partir do id do clienteImovelEconomia
	 * 
	 * Autor: Rafael Corrêa
	 * 
	 * Data: 15/02/2007
	 */
	public ImovelEconomia pesquisarImovelEconomiaPeloCliente(Integer idClienteImovelEconomia) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0009] - Manter Cliente 
	 * 
	 * Verifica se o cliente usuário está na tarifa social 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 16/02/2007
	 */
	public Collection verificarClienteUsuarioCadastradoTarifaSocial(Integer idCliente) 
		throws ErroRepositorioException;
	
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Autor: Vivianne Sousa
	 * 
	 * Data: 27/10/2008
	 */
	public Collection pesquisarImovelEconomia(Integer idImovel) 
		throws ErroRepositorioException;
}
