package gcom.cadastro.cliente;

import gcom.cadastro.cliente.bean.ClienteEmitirBoletimCadastroHelper;
import gcom.cadastro.cliente.bean.PesquisarClienteResponsavelSuperiorHelper;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.rmi.RemoteException;
import java.util.Collection;

/**
 * Declara��o p�blica de servi�os do Session Bean de ControladorCliente
 * 
 * @author S�vio Luiz
 * @created 25 de Abril de 2005
 */
public interface ControladorClienteLocal extends javax.ejb.EJBLocalObject {

	/**
	 * Insere um cliente no sistema
	 * 
	 * @param cliente
	 *            Cliente a ser inserido
	 * @param telefones
	 *            Telefones do cliente
	 * @param enderecos
	 *            Endere�os do cliente
	 * @return Descri��o do retorno
	 */
	public Integer inserirCliente(Cliente cliente, Collection telefones,
			Collection enderecos, Usuario usuario) throws ControladorException;

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param clienteImovel
	 *            Descri��o do par�metro
	 */
	public void inserirClienteImovel(ClienteImovel clienteImovel)
			throws ControladorException;

	/**
	 * atualiza um cliente imovel economia com a data final da rela��o e o
	 * motivo.
	 * 
	 * @param clienteImovelEconomia
	 *            Description of the Parameter
	 * @exception RemoteException
	 *                Description of the Exception
	 */
	public void atualizarClienteImovelEconomia(
			Collection clientesImoveisEconomia) throws ControladorException;

	/**
	 * Atualiza um cliente no sistema
	 * 
	 * @param cliente
	 *            Cliente a ser atualizado
	 * @param telefones
	 *            Telefones do cliente
	 * @param enderecos
	 *            Endere�os do cliente
	 */
	public void atualizarCliente(Cliente cliente, Collection telefones,
			Collection enderecos, Usuario usuario) throws ControladorException;

	/**
	 * Pesquisa uma cole��o de cliente imovel que estao na tarifa social
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 */
	public Collection pesquisarClienteImovelTarifaSocial(
			FiltroClienteImovel filtroClienteImovel, Integer numerpPagina)
			throws ControladorException;

	/**
	 * Pesquisa uma cole��o de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 */
	public Collection pesquisarClienteImovel(
			FiltroClienteImovel filtroClienteImovel, Integer numeroPagina)
			throws ControladorException;

	/**
	 * Pesquisa uma cole��o de cliente endereco com uma query especifica
	 * 
	 * @param filtroClienteEndereco
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */

	public Collection pesquisarClienteEndereco(
			FiltroClienteEndereco filtroClienteEndereco)
			throws ControladorException;

	/**
	 * Metodo que retorno todos os clinte do filtro passado
	 * 
	 * @param filtroCliente
	 *            Descri��o do par�metro
	 * @return Description of the Return Value
	 * @autor thiago toscano
	 * @date 15/12/2005
	 * @throws ControladorException
	 */

	public Collection pesquisarCliente(FiltroCliente filtroCliente)
			throws ControladorException;

	public Collection<Cliente> pesquisarClienteEnderecoClienteImovel(
			FiltroClienteEndereco filtroClienteEndereco)
			throws ControladorException;

	public Collection<Cliente> pesquisarClienteDadosClienteEndereco(
			FiltroCliente filtroCliente, Integer numeroPagina)
			throws ControladorException;

	public Collection<Cliente> pesquisarClienteDadosClienteEnderecoRelatorio(
			FiltroCliente filtroCliente) throws ControladorException;

	/**
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * Retrona a quantidade de endere�os que existem para o Cliente
	 * 
	 * pesquisarClienteDadosClienteEnderecoCount
	 * 
	 * @author Roberta Costa
	 * @date 29/06/2006
	 * 
	 * @param filtroCliente
	 * @return
	 * @throws ControladorException
	 */
	public Integer pesquisarClienteDadosClienteEnderecoCount(
			FiltroCliente filtroCliente) throws ControladorException;

	public Collection<Cliente> pesquisarClienteOutrosCriterios(
			FiltroCliente filtroCliente) throws ControladorException;

	/**
	 * Inseri uma cole��o de pagamentos no sistema
	 * 
	 * [UC0265] Inserir Pagamentos
	 * 
	 * Este fluxo secund�rio tem como objetivo pesquisar o cliente digitado pelo
	 * usu�rio
	 * 
	 * [FS0011] - Verificar exist�ncia do c�digo do cliente
	 * 
	 * @author Pedro Alexandre
	 * @date 16/02/2006
	 * 
	 * @param idClienteDigitado
	 * @return
	 * @throws ControladorException
	 */
	public Cliente pesquisarClienteDigitado(Integer idClienteDigitado)
			throws ControladorException;

	/**
	 * Pesquisa um cliente carregando os dados do relacionamento com ClienteTipo
	 * 
	 * [UC0321] Emitir Fatura de Cliente Respons�vel
	 * 
	 * @author Pedro Alexandre
	 * @date 02/05/2006
	 * 
	 * @param idCliente
	 * @return
	 * @throws ControladorException
	 */
	public Cliente pesquisarCliente(Integer idCliente)
			throws ControladorException;

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
	 * @throws ControladorException
	 */
	public int pesquisarQuantidadeClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ControladorException;

	/**
	 * Pesquisa uma cole��o de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarClienteImovel(
			FiltroClienteImovel filtroClienteImovel)
			throws ControladorException;

	/**
	 * Pesquisa uma cole��o de cliente imovel com uma query especifica
	 * 
	 * @param filtroClienteImovel
	 *            parametros para a consulta
	 * @return Description of the Return Value
	 * @exception ErroRepositorioException
	 *                Description of the Exception
	 */

	public Collection pesquisarClienteImovelRelatorio(
			FiltroClienteImovel filtroClienteImovel)
			throws ControladorException;

	/**
	 * Pesquisa um cliente pelo id
	 * 
	 * @author Rafael Corr�a
	 * @date 01/08/2006
	 * 
	 * @return Cliente
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Cliente pesquisarObjetoClienteRelatorio(Integer idCliente)
			throws ControladorException;
	
	/**
	 * Pesquisa as quantidades de im�veis e as quantidades de economias
	 * associadas a um cliente
	 * 
	 * @author Rafael Corr�a
	 * @date 23/08/2007
	 * 
	 * @return Object[]
	 * @exception ControladorException
	 *                Erro no hibernate
	 */
	public Object[] pesquisarQtdeImoveisEEconomiasCliente(Integer idCliente)
			throws ControladorException;

	/**
	 * Verifica a existencia do cliente
	 * 
	 * @author Fernanda Paiva
	 * @date 01/09/2006
	 * 
	 * @return id Cliente
	 * @exception ErroRepositorioException
	 *                Erro no hibernate
	 */
	public Integer verificarExistenciaCliente(Integer codigoCliente)
			throws ControladorException;

	/**
	 * [UC0366] Inserir Registro de Atendimento
	 * 
	 * Pesquisar ClienteImovel
	 * 
	 * @author Raphael Rossiter
	 * @date 21/08/2006
	 * 
	 * 
	 * @param idCliente,
	 *            idImovel
	 * @return Colletion
	 * @throws ControladorException
	 */
	public ClienteImovel pesquisarClienteImovel(Integer idCliente,
			Integer idImovel) throws ControladorException;

	/**
	 * Pesquisa todos os telefones de um cliente
	 * 
	 * @author Raphael Rossiter
	 * @date 23/08/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteFone(Integer idCliente)
			throws ControladorException;

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
			throws ControladorException;

	/**
	 * Pesquisa os dados do Cliente
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Cliente consultarCliente(Integer idCliente)
			throws ControladorException;

	/**
	 * Pesquisa todos os endere�os do cliente
	 * 
	 * @author Rafael Santos
	 * @date 13/09/2006
	 * 
	 * @param idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarEnderecoCliente(Integer idCliente)
			throws ControladorException;

	/**
	 * Pesquisa o nome do cliente a partir do im�vel Autor: S�vio Luiz Data:
	 * 21/12/2005
	 */
	public String pesquisarNomeClientePorImovel(Integer idImovel)
			throws ControladorException;

	/**
	 * Pesquisa o nome, cnpj e id do cliente a partir do im�vel Autor: Rafael
	 * Corr�a Data: 25/09/2006
	 */
	public Cliente pesquisarDadosClienteRelatorioParcelamentoPorImovel(
			Integer idImovel) throws ControladorException;
	
	/**
	 * [UC0458] - Imprimir Ordem de Servi�o
	 * 
	 * Pesquisa o telefone principal do Cliente para o
	 * Relat�rio de OS
	 * 
	 * @author Rafael Corr�a
	 * @date 17/10/2006
	 * 
	 * 
	 * @param idRegistroAtendimento
	 * @throws ErroRepositorioException
	 */
	public String pesquisarClienteFonePrincipal(
			Integer idCliente) throws ControladorException;

	/**
	 * 
	 * Usado pelo Filtrar Cliente
	 * Filtra o Cliente usando os paramentos informados
	 *
	 * @author Rafael Santos
	 * @date 27/11/2006
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection filtrarCliente(
			String codigo,
			String cpf,
			String rg,
			String cnpj,
			String nome,
			String nomeMae,		
			String cep,
			String idMunicipio,
			String idBairro,
			String idLogradouro,
			String indicadorUso,
			String tipoPesquisa,
			String tipoPesquisaNomeMae,
			String clienteTipo, String idEsferaPoder,
		    Integer numeroPagina) throws ControladorException;

	/**
	 * 
	 * Usado pelo Filtrar Cliente
	 * Filtra a quantidade de Clientes usando os paramentos informados
	 *
	 * @author Rafael Santos
	 * @date 27/11/2006
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object filtrarQuantidadeCliente(
			String codigo,
			String cpf,
			String rg,
			String cnpj,
			String nome,
			String nomeMae,		
			String cep,
			String idMunicipio,
			String idBairro,
			String idLogradouro,
			String indicadorUso,
			String tipoPesquisa,
			String tipoPesquisaNomeMae,
			String clienteTipo, String idEsferaPoder) throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Im�veis pelo id do Cliente, indicador de uso, motivo
	 * do fim da rela��o, pelo perfil do im�vel e pelo tipo da rela��o do cliente carregando o im�vel
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloClienteTarifaSocial(Integer idCliente)
			throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Im�veis pelo id do Im�vel carregando o im�vel, o cliente, o perfil do im�vel, 
	 * o org�o expedidor do RG e a unidade da federa��o
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloImovelTarifaSocial(Integer idImovel) 
			throws ControladorException;
	
	/**
	 * [UC0054] - Inserir Dados Tarifa Social 
	 * 
	 * Pesquisa os Clientes Im�veis pelo id do Im�vel carregando os dados necess�rios para retornar o seu endere�o 
	 * 
	 * Autor: Rafael Corr�a 
	 * 
	 * Data: 27/12/2006
	 */
	public Collection pesquisarClienteImovelPeloImovelParaEndereco(Integer idImovel) 
			throws ControladorException;
	
	/**
	 * 
	 * Verifica se � usuario iquilino ou n�o
	 *
	 * @author S�vio Luiz
	 * @date 08/01/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public boolean verificaUsuarioinquilino(Integer idImovel)throws ControladorException;
	
	
	
	/**
	 * Atualiza logradouroCep de um ou mais im�veis  
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
			LogradouroCep logradouroCepNovo) throws ControladorException ;
	
	
	/**
	 * Atualiza logradouroBairro de um ou mais im�veis  
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
			LogradouroBairro logradouroBairroNovo) throws ControladorException ;
	
	/**
	 * [UC0544] Gerar Arwuivo Texto do Faturamento
	 * 
	 * Pesquisar ClienteImovel
	 * 
	 * @author Fl�vio Cordeiro
	 * @date 4/04/2006
	 * 
	 * 
	 * @return Colletion
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarClienteImovelGerarArquivoFaturamento()
		throws ControladorException;
	
	/**
	 * [UC0582] - Emitir Boletim de Cadastro
	 * 
	 * Pesquisa os dados do cliente para a emiss�o do boletim
	 * 
	 * @author Rafael Corr�a
	 * @date 16/05/2007
	 * 
	 * @param idImovel, clienteRelacaoTipo
	 * @throws ControladorException
	 */
	public ClienteEmitirBoletimCadastroHelper pesquisarClienteEmitirBoletimCadastro(
			Integer idImovel, Short clienteRelacaoTipo) throws ControladorException;
	
	/**
	 * [UC0864] Gerar Certid�o Negativa por Cliente
	 * 
	 * @author Rafael Corr�a
	 * @date 25/09/2008
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection<Integer> pesquisarClientesAssociadosResponsavel(Integer idCliente) throws ControladorException;
	
	/**
	 * Pesquisa o rg do cliente do parcelamento a partir do idParcelamento
	 * Autor: Vivianne Sousa 
	 * Data: 20/06/2007
	 */
	public Cliente pesquisarDadosClienteDoParcelamentoRelatorioParcelamento(Integer idParcelamento)
			throws ControladorException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 *
	 * @author Vivianne Sousa
	 * @date 27/07/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente obterIdENomeCliente(String cpf) throws ControladorException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos
	 *
	 *Alterado para registrar a transa��o na atualiza��o
	 *do CPF do cliente.
	 *
	 * @author Anderson Italo, Vivianne Sousa
	 * @date 11/08/2009, 30/07/2007
	 *
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarCPFCliente(String cpf,Integer idCliente, Usuario usuarioLogado) throws ControladorException;
	
	/**
	 * 
	 *Retorna o cliente usuario apartir do id do imovel
	 *
	 * @author Fl�vio Cordeiro
	 * @date 08/01/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Cliente retornaClienteUsuario(Integer idImovel)throws ControladorException;
	
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
	public Cliente retornaClienteProprietario(Integer idImovel) throws ControladorException;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualiza��o Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * @return Cliente
	 * @throws ErroRepositorioException
	 */

	public IClienteAtualizacaoCadastral obterClienteAtualizacaoCadastral(Integer idImovel, 
			Short idClienteRelacaoTipo) throws ControladorException;
	
	/**
	 * [UC0831] Gerar Tabelas para Atualiza��o Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/08/2008
	 * 
	 * @return Cliente Fone
	 * @throws ErroRepositorioException
	 */

	public Collection obterDadosClienteFone(Integer idCliente)
			throws ControladorException;
	
    /**
	 * [UC0831] Gerar Tabelas para Atualiza��o Cadastral via celular 
	 * 
	 * @author Vinicius Medeiros
	 * @date 25/09/2008
	 */
	public Integer verificaExistenciaClienteAtualizacaoCadastral(Integer idCliente) throws ControladorException;
	
	/**
	 * [UC0014] Manter Im�vel
	 * [FS0017] Registra Fim de Rela��o do(s) Cliente(s) com Im�vel
	 *
	 * @author Ana Maria
	 * @date 13/10/2008
	 *
	 * @return Collection
	 * @throws ControladorException
	 */
	public Collection pesquisarClienteImovel(Integer idImovel)
		throws ControladorException;
	
	/**
	 * Pesquisa a quantidade de clientes respons�veis superiores com os condicionais informados
	 * 
	 * @author Rafael Corr�a
	 * @date 18/11/08
	 */
	public Integer pesquisarClienteResponsavelSuperiorParaPaginacaoCount(PesquisarClienteResponsavelSuperiorHelper helper) throws ControladorException;
	
	/**
	 * Pesquisa os clientes respons�veis superiores com os condicionais informados
	 * 
	 * @author Rafael Corr�a
	 * @date 18/11/08
	 */
	public Collection<Cliente> pesquisarClienteResponsavelSuperiorParaPaginacao(PesquisarClienteResponsavelSuperiorHelper helper, Integer numeroPagina) throws ControladorException;
	
	/**
	 * Pesquisar dados do Cliente Atualiza��o Cadastral
	 * 
	 * @param idCliente, idImovel
	 * @return ClienteAtualizacaoCadastral
	 * 
	 * @author Ana Maria
     * @date 15/05/2009
	 * @exception ErroRepositorioException
	 */
	public IClienteAtualizacaoCadastral pesquisarClienteAtualizacaoCadastral(Integer idCliente, Integer idImovel, Integer idClienteRelacaoTipo)
		throws ControladorException;
	
	/**
	 * 
	 * Pesquisar Cliente Fone Atualiza��o Cadastral
	 *
	 * @author Ana Maria
	 * @date 24/10/2008
	 *
	 * @param idCliente
	 * @throws ErroRepositorioException 
	 */
	public Collection<ClienteFoneAtualizacaoCadastral> pesquisarClienteFoneAtualizacaoCadastral(Integer idCliente, Integer idMatricula, 
			Integer idTipoFone, Integer idClienteRelacaoTipo, String numeroFone)
		throws ControladorException;
	
	/**
	 * @author Daniel Alves
	 * @date 02/09/2010
	 * @param idClienteImovel
	 * @throws ErroRepositorioException
	 */
	public void atualizarIndicadorNomeContaClienteImovel(int idClienteImovel) throws ControladorException;
	
	
	/**
	 * 
	 * Atualiza telefone padr�o
	 *
	 * @author Daniel Alves
	 * @date 06/09/2010
	 *
	 * @param idCliente
	 * @param idClienteFonePadrao  (novo telefone padr�o do cliente).
	 * @throws ControladorException 
	 */
	public void atualizarTelefonePadrao(String idCliente, String idClienteFonePadrao) throws ControladorException;
	
	/**
	 * Remove todos os telefones de um determinado cliente
	 * 
	 * @param idCliente
	 *            C�digo do cliente que ter� seus telefones apagados
	 * @exception ErroRepositorioException
	 *                Erro no BD
	 */
	public void removerTodosTelefonesPorCliente(Integer idCliente) throws ControladorException;
	

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
	public Cliente retornaDadosClienteUsuario(Integer idImovel) throws ControladorException;
	
	/**
	 * [UC0541] Emitir 2a Via Conta Internet
	 * 
	 * [SB0003] � Obter Nome do Cliente
	 * @author Mariana Victor
	 * @date 11/03/2011
	 * 
	 * */
	public String obterNomeCliente(Integer idImovel) throws ControladorException;

	
	/**
	 * [UC1160] Processar Comando Gerado Carta Tarifa Social  
	 * [SB0002]�Verifica Crit�rio Recadastramento
	 * 
	 * @author Vivianne Sousa
	 * @date 25/03/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Cliente pesquisarClienteUsuarioDoImovel(Integer idImovel) throws ControladorException;

	
	
	/**
	 * Verifica Clientes Associados a um Cliente sem CNPJ ou ICPESSOAFISICAJURIDICA diferente de 2
	 * 
	 * @author Paulo Diniz
	 * @date 10/04/2011
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Integer pesquisarQtdClientesAssociadosResponsavelNaoJuridica(Integer idCliente)
			throws ControladorException;
	
	/**
	 * Retorna Lista de Im�veis associados ao cliente
	 * 
	 * @author Paulo Diniz
	 * @date 10/04/2011
	 * 
	 * @return
	 * @throws ControladorException
	 */
	public Collection pesquisarImoveisAssociadosCliente(Integer idCliente, Short relacaoTipo )throws ControladorException;
	
	
	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * Filtra os Clientes por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ControladorException 
	 */
	public Collection filtrarAutocompleteCliente(String valor) throws ControladorException;
	
	/**
	 * [UC1136] Inserir Contrato de Parcelamento por Cliente
	 * Filtra os Clientes Responsavel por Id ou Nome para ser utilizado no Autocomplete
	 *
	 * @author Paulo Diniz
	 * @date 04/04/2011
	 *
	 * @param valor
	 * @throws ControladorException 
	 */
	public Collection filtrarAutocompleteClienteResponsavel(String valor) throws ControladorException;


	/**
	 * [UC1186] Gerar Relat�rio Ordem de Servi�o Cobran�a p/Resultado
	 * 
	 * Pesquisar os clientes a partir do im�vel e o tipo de rela��o com o cliente
	 * 
	 * @author Hugo Azevedo
	 * @data 02/07/2011
	 */
	
	public Collection obterClienteImovelporRelacaoTipo(Integer idImovel, Integer idRelacaoTipo) throws ControladorException;
	
	/**
	 * [UC0214] Efetuar Parcelamento de D�bitos Atrav�s da Loja Virtual
	 * 
	 * Caso o CPF do cliente passado no par�metro seja do cliente propriet�rio
	 * ou do cliente usu�rio do im�vel o m�todo retorna o nome do cliente, caso
	 * contr�rio o m�todo retorna null.
	 * 
	 * @author Diogo Peixoto
	 * @date 28/06/2011
	 * 
	 * @param CPFCliente
	 * @param Matricula
	 * 
	 * @throws ControladorException
	 * @return String
	 */
	public String validarCliente(String cpfCliente, Integer matricula) throws ControladorException;
	
	/**
	 * 
	 * Retorna o cliente respons�vel
	 *
	 * @author S�vio Luiz
	 * @date 04/04/2007
	 *
	 * @param idImovel
	 * @return
	 * @throws ErroRepositorioException 
	 */
	public Integer retornaIdClienteResponsavelIndicadorEnvioConta(Integer idImovel)	throws ControladorException;

	public Cliente pesquisarDadosCliente(Integer idCliente)	throws ControladorException;
	
	public ClienteImovel pesquisarClienteImovelOSFiscalizada(Integer idImovel) throws ControladorException;
	
	public ClienteTipo pesquisarClienteTipo(Integer idClienteTipo) throws ControladorException;
	
	public Collection<ClienteFone> pesquisarClienteFoneDoImovel(Integer idImovel) throws ControladorException;

	public Collection<Cliente> pesquisarClientePorCpfCnpj(String cpfCnpj) throws Exception;
	
	public Short pesquisarIndicadorNegativacaoPeriodoClienteResponsavel(Integer idImovel, Integer idClienteRelacaoTipo) throws ControladorException;
	
	public boolean existeEnderecoParaCliente(Integer idCliente)throws ControladorException;
	
	public Cliente pesquisarDadosClienteParaNegativacao(Integer idCliente, String cnpjEmpresa) throws ControladorException;
}

