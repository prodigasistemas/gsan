package gcom.cadastro.cliente;

import gcom.cadastro.imovel.Imovel;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.util.Collection;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 * @created 22 de Julho de 2005
 */
public interface IRepositorioClienteImovel {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param clienteImovel
	 *            Descrição do parâmetro
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public void inserirClienteImovel(ClienteImovel clienteImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisa uma coleção de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */

	public Collection pesquisarClienteImovel(
			FiltroClienteImovel filtroClienteImovel,Integer numeroPagina)
			throws ErroRepositorioException;

	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * @param clienteRelacaoTipo
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Collection pesquisarClienteImovelResponsavelEsferaPoder(
			Imovel imovel, ClienteRelacaoTipo clienteRelacaoTipo)
			throws ErroRepositorioException;

	public String pesquisarNomeClientePorImovel(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Pesquisa uma a quantidade de cliente imovel com uma query especifica
	 * [UC0015] Filtrar Imovel
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @author Rafael Santos
	 * @since 26/06/2006           
	 *            
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public int pesquisarQuantidadeClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ErroRepositorioException; 

	
	/**
	 * Pesquisa uma coleção de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa uma coleção de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarClienteImovelRelatorio(
			FiltroClienteImovel filtroClienteImovel)
			throws ErroRepositorioException; 
	
	
	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Pesquisar ClienteImovel
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * 
	 * @param idCliente, idImovel
	 * @return Colletion
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteImovel(Integer idCliente, Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa o nome, cnpj e id do cliente a partir do imóvel Autor: Rafael Corrêa Data:
	 * 25/09/2006
	 */
	public Object[] pesquisarDadosClienteRelatorioParcelamentoPorImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * Autor: Sávio Luiz Data:
	 * 27/11/2006
	 */
	public Collection pesquisarParmsClienteImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Imóveis pelo id do Cliente, indicador de uso, motivo
	 * do fim da relação, pelo perfil do imóvel e pelo tipo da relação do cliente carregando o imóvel
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloClienteTarifaSocial(Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Imóveis pelo id do Imóvel carregando o imóvel, o cliente, o perfil do imóvel, 
	 * o orgão expedidor do RG e a unidade da federação
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloImovelTarifaSocial(Integer idImovel) 
			throws ErroRepositorioException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Imóveis pelo id do Imóvel carregando os dados necessários para retornar o seu endereço 
	 * 
	 * Autor: Rafael Corrêa 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloImovelParaEndereco(
			Integer idImovel) throws ErroRepositorioException;
	
	
	/**
	 * 
	 *Retorna o cliente usuario apartir do id do imovel
	 *
	 * @author Flávio Cordeiro
	 * @date 08/01/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Cliente retornaClienteUsuario(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * Retorna os clientes e suas relações tipos a partir do id do imovel
	 *
	 * @author Sávio Luiz
	 * @date 08/01/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Collection retornaClientesRelacao(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * Retorna o cliente usuário
	 *
	 * @author Sávio Luiz
	 * @date 22/01/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Integer retornaIdClienteUsuario(Integer idImovel) throws ErroRepositorioException;
	

	/**
	 * [UC0544] Gerar Arwuivo Texto do Faturamento
	 * 
	 * Pesquisar ClienteImovel
	 * 
	 * @author Flávio Cordeiro
	 * @date 4/04/2006
	 * 
	 * 
	 * @return Colletion
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteImovelGerarArquivoFaturamento()
			throws ErroRepositorioException;
	

	/**
	 * 
	 * Retorna o cliente usuário
	 *
	 * @author Sávio Luiz
	 * @date 04/04/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Integer retornaIdClienteResponsavel(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * Retorna o tipo da relação do cliente com indicador nome conta
	 *
	 * @author Rafael Corrêa
	 * @date 17/05/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Integer retornaTipoRelacaoClienteImovelNomeConta(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * Pesquisa o rg do cliente do parcelamento a partir do idParcelamento
	 * Autor: Vivianne Sousa 
	 * Data: 20/06/2007
	 */
	public Object[] pesquisarDadosClienteDoParcelamentoRelatorioParcelamento(Integer idParcelamento)
			throws ErroRepositorioException ;
	

	/**
	 * 
	 * Retorna o cliente usuário
	 *
	 * @author Sávio Luiz
	 * @date 04/04/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Integer retornaIdClienteResponsavelIndicadorEnvioConta(Integer idImovel) throws ErroRepositorioException;
	
	
	/**
	 * Pesquisa o codigo da corta e o sequencia da rota 
	 * apartir do codigo do cliente
	 * 
	 * @date 19/09/2007
	 * @author Rafael Pinto
	 * @throws ErroRepositorioException
	 * @return String[2] onde String[0]=codigo e String[1]=sequencial
	 */
	public Object[] pesquisarCodigoSequencialRotaPorUsuario(Integer idCliente)
	 			throws ErroRepositorioException ;
	
	/**
	 * 
	 *Retorna o cliente proprietario a partir do id do imovel
	 *
	 * @author Vinicius Medeiros
	 * @date 29/08/2008
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Cliente retornaClienteProprietario(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC0014] Manter Imóvel
	 * [FS0017] Registra Fim de Relação do(s) Cliente(s) com Imóvel
	 *
	 * @author Ana Maria
	 * @date 13/10/2008
	 *
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection<ClienteImovel> pesquisarClienteImovel(Integer idImovel) throws ErroRepositorioException;
	
	
	/**
	 * EMITIR CONTAS CAEMA
	 * 
	 * Pesquisa os Clientes Imóveis pelo id do Imóvel cujo cliente seja o responsavel pela conta
	 * 
	 * Autor: Tiago Moreno
	 * 
	 * Data: 29/10/2008
	 */
	public Collection pesquisarClienteImovelResponsavelConta(
			Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisar Cliente Imovel Atualização Cadastral
	 *
	 * @author Ana Maria
	 * @date 24/10/2008
	 *
	 * @param idCliente
	 * @throws ErroRepositorioException 
	 */
	public Collection pesquisarClienteImovelAtualizacaoCadastral(Integer idImovel)
		throws ErroRepositorioException;
	
	/**
	 * < <Descrição do método>>
	 * 
	 * @param imovel
	 *            Descrição do parâmetro
	 * 
	 * @return Descrição do retorno
	 * @exception ErroRepositorioException
	 *                Descrição da exceção
	 */
	public Integer pesquisarEsferaPoderClienteImovelResponsavel(Integer imovel)
			throws ErroRepositorioException;
	
	/**
	 * @author Daniel Alves
	 * @date 02/09/2010
	 * @param idClienteImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorNomeContaClienteImovel(int idClienteImovel) throws ErroRepositorioException;

	/**
	 * 
	 * Retorna o cliente usuario apartir do id do imovel
	 *
	 * @author Mariana Victor
	 * @date 17/01/2011
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Cliente retornaDadosClienteUsuario(Integer idImovel) throws ErroRepositorioException;
	
	/**
	 * [UC1213] Emitir Relatorio de Ordem de Servico de Fiscalizacao
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 06/08/2011
	 * 
	 * @throws ErroRepositorioException
	 */	
	public ClienteImovel pesquisarClienteImovelOSFiscalizada(Integer idImovel) throws ErroRepositorioException;
	
	public Cliente pesquisarClienteImovelTipo(Integer idCliente, Integer idImovel, Integer idTipo) throws ErroRepositorioException;
	
	public Short pesquisarIndicadorNegativacaoPeriodoClienteResponsavel(Integer idImovel, Integer idClienteRelacaoTipo) throws ErroRepositorioException;
}
