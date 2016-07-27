package gcom.cadastro.endereco;

import java.util.Collection;

import gcom.cadastro.endereco.to.LogradouroTO;
import gcom.util.ErroRepositorioException;

/**
 * Interface para o repositório de cliente
 * 
 * @author Sávio Luiz
 * @created 22 de Abril de 2005
 */

public interface IRepositorioEndereco {

	/**
	 * pesquisa uma coleção de cep(s) de acordo com o código
	 * 
	 * @param codigoCep
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarCep() throws ErroRepositorioException;

	/**
	 * Description of the Method
	 * 
	 * @param cepsImportados
	 *            Description of the Parameter
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public void inserirImportacaoCep(Collection cepsImportados)
			throws ErroRepositorioException;

	/**
	 * Description of the Method
	 * 
	 * @param cepsImportados
	 *            Description of the Parameter
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public void atualizarImportacaoCep(Collection cepsImportados)
			throws ErroRepositorioException;

	/**
	 * Description of the Method
	 * 
	 * @param cepsImportados
	 *            Description of the Parameter
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarEndereco(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * Description of the Method
	 * 
	 * @param cepsImportados
	 *            Description of the Parameter
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */
	public Collection pesquisarEnderecoFormatado(Integer idImovel)
			throws ErroRepositorioException;

	/**
	 * [UC0003] Informar Endereço
	 * 
	 * Pesquisar associação de LogradouroCep já existente
	 * 
	 * @author Raphael Rossiter
	 * @data 12/05/2006
	 * 
	 * @param idCep,
	 *            idLogradouro
	 * @return LogradouroCep
	 */
	public LogradouroCep pesquisarAssociacaoLogradouroCep(Integer idCep,
			Integer idLogradouro) throws ErroRepositorioException;

	/**
	 * [UC0003] Informar Endereço
	 * 
	 * Pesquisar associação de LogradouroCep apenas por logradouro
	 * 
	 * @author Raphael Rossiter
	 * @data 12/05/2006
	 * 
	 * @param idLogradouro
	 * @return LogradouroCep
	 */
	public Collection<LogradouroCep> pesquisarAssociacaoLogradouroCepPorLogradouro(
			Integer idLogradouro) throws ErroRepositorioException;

	/**
	 * [UC0003] Informar Endereço
	 * 
	 * Atualiza a situação de uma associação de LogradouroCep (Motivo: CEP
	 * Inicial de Município)
	 * 
	 * @author Raphael Rossiter
	 * @data 12/05/2006
	 * 
	 * @param idCep,
	 *            idLogradouro, indicadorUso
	 * @return void
	 */
	public void atualizarIndicadorUsoLogradouroCep(Integer idCep,
			Integer idLogradouro, Short indicadorUso)
			throws ErroRepositorioException;

	/**
	 * [UC0003] Informar Endereço
	 * 
	 * Pesquisar associação de LogradouroBairro já existente
	 * 
	 * @author Raphael Rossiter
	 * @data 24/05/2006
	 * 
	 * @param idBairro,
	 *            idLogradouro
	 * @return LogradouroBairro
	 */
	public LogradouroBairro pesquisarAssociacaoLogradouroBairro(
			Integer idBairro, Integer idLogradouro)
			throws ErroRepositorioException;

	public Collection<Logradouro> pesquisarLogradouro(
			FiltroLogradouro filtroLogradouro, Integer numeroPaginas)
			throws ErroRepositorioException;

	public Integer pesquisarLogradouroCount(FiltroLogradouro filtroLogradouro)
			throws ErroRepositorioException;

	public Collection pesquisarEnderecoClienteAbreviado(Integer idCliente)
			throws ErroRepositorioException;

	// metodo que serve para fazer a pesquisa do logradouro
	// apartir dos parametros informados
	public Collection pesquisarLogradouroCompleto(String codigoMunicipio,
			String codigoBairro, String nome, String nomePopular,
			String logradouroTipo, String logradouroTitulo, String cep,
			String codigoLogradouro, String indicadorUso, String tipoPesquisa,
			String tipoPesquisaPopular, Integer numeroPaginas)
			throws ErroRepositorioException;

	public Integer pesquisarLogradouroCompletoCount(String codigoMunicipio,
			String codigoBairro, String nome, String nomePopular,
			String logradouroTipo, String logradouroTitulo, String cep,
			String codigoLogradouro, String indicadorUso, String tipoPesquisa,
			String tipoPesquisaPopular) throws ErroRepositorioException;

	public Collection pesquisarLogradouroCompletoRelatorio(
			String codigoMunicipio, String codigoBairro, String nome,
			String nomePopular, String logradouroTipo, String logradouroTitulo,
			String cep, String codigoLogradouro, String indicadorUso,
			String tipoPesquisa, String tipoPesquisaPopular)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0085] Obter Endereço
	 * 
	 * 
	 * @author Ana Maria
	 * @data 07/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * 
	 * @return Collection
	 */
	public Collection pesquisarEnderecoRegistroAtendimentoFormatado(Integer idRegistroAtendimento)
			throws ErroRepositorioException ;
	
	
	/**
	 * [UC0085] Obter Endereço
	 * 
	 * 
	 * @author Ana Maria
	 * @data 07/08/2006
	 * 
	 * @param idRegistroAtendimento
	 * 
	 * @return Collection
	 */
	public Collection pesquisarEnderecoRegistroAtendimentoSolicitanteFormatado(Integer idRegistroAtendimentoSolicitante)
			throws ErroRepositorioException ;

	/**
	 * Obter dados do Logradouro cep para o endereço
	 * 
	 * 
	 * @author Sávio Luiz
	 * @data 05/09/2006
	 * 
	 * @param idLogradouroCep
	 * 
	 * @return Collection
	 */
	public Collection obterDadosLogradouroCepEndereco(Integer idLogradouroCep)
			throws ErroRepositorioException;

	/**
	 * Obter dados do Logradouro bairro para o endereço
	 * 
	 * 
	 * @author Sávio Luiz
	 * @data 05/09/2006
	 * 
	 * @param idLogradouroCep
	 * 
	 * @return Collection
	 */
	public Collection obterDadosLogradouroBairroEndereco(
			Integer idLogradouroBairro) throws ErroRepositorioException;
	/**
	 * 
	 * Pesquisar os Endereços do Cliente
	 *
	 * [UC0474] Consultar Imóvel
	 *
	 * @author Rafael Santos
	 * @date 19/09/2006
	 *
	 * @param idCliente
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClientesEnderecosAbreviado(Integer idCliente)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisar o endereço abreviado a partir do id do imóvel
	 * 
	 * [UC0483] - Obter Endereço Abreviado
	 * 
	 * @author Rafael Corrêa
	 * @date 18/10/2006
	 * 
	 * @param idImovel
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */

	public Object[] obterEnderecoAbreviadoImovel(Integer idImovel)
			throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisar o endereço abreviado a partir do id do ra
	 * 
	 * [UC0483] - Obter Endereço Abreviado
	 * 
	 * @author Rafael Corrêa
	 * @date 18/10/2006
	 * 
	 * @param idRA
	 * @return Object[]
	 * @throws ErroRepositorioException
	 */

	public Object[] obterEnderecoAbreviadoRA(Integer idRA)
			throws ErroRepositorioException;
	
	
	/**
	 * 
	 * [UC0348] Emitir Contas por cliente responsavel CAERN
	 * 
	 * Pesquisar endereco formatado para cliente
	 * 
	 * @author Raphael Rossiter
	 * @data 22/05/2007
	 * 
	 * @param idCliente,
	 * @return Collection
	 */
	public Collection pesquisarEnderecoFormatadoCliente(Integer idCliente)
	throws ErroRepositorioException ;
	
	public Collection pesquisarEnderecoAbreviadoCAER(Integer idImovel)
	throws ErroRepositorioException;
	
	/**
	 * 
	 * Pesquisar o cep pelo codigo do cep
	 * 
	 * @author Sávio Luiz
	 * @date 05/11/2007
	 * 
	 */

	public Cep pesquisarCep(Integer codigoCep) throws ErroRepositorioException;
	
	/**
	 * Verifica a existência do endereço de correspondência do cliente pelo seu id 
	 * 
	 */
	public boolean verificarExistenciaClienteEndereco(Integer idCliente)
			throws ErroRepositorioException;
	
	/**
	 * Retorna a coleção de Logradouro Tipos presentes na tabela CEP 
	 * 
	 * @author: Vinicius Medeiros 
	 */
	public Collection retornaTipoLogradouroPeloCep()
			throws ErroRepositorioException;
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * 
	 * 
	 * @author: Rômulo Aurélio
	 * @date: 29/10/2008
	 */
	public Collection<Object[]> pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresa(
			Integer idCliente) throws ErroRepositorioException;
	
	public LogradouroTO pesquisarLogradouro(Integer idImovel)	throws ErroRepositorioException;
	
	/**
	 * Obter Logradouro(Tipo + Nome Logradouro + Título)
	 */
	public Collection pesquisarLogradouroCliente(Integer idCliente)
	throws ErroRepositorioException;
	
	/**
	 * [UC0085] Obter Endereço
	 * 
	 * @author Vivianne Sousa
	 * @data 17/05/2011
	 * 
	 * @param idraReiteracao
	 * 
	 * @return Collection
	 */
	public Collection pesquisarEnderecoSolicitanteRAReiteracaoFormatado(
			Integer idraReiteracao)throws ErroRepositorioException;
	
	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * @author: Mariana Victor
	 * @date: 20/05/2011
	 */
	public Collection<Object[]> pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresaLayout02(
			Integer idCliente) throws ErroRepositorioException;
}
