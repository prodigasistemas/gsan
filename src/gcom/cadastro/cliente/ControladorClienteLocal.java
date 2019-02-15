package gcom.cadastro.cliente;

import gcom.cadastro.cliente.bean.ClienteEmitirBoletimCadastroHelper;
import gcom.cadastro.cliente.bean.PesquisarClienteResponsavelSuperiorHelper;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.localidade.Localidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import java.util.Collection;

public interface ControladorClienteLocal extends javax.ejb.EJBLocalObject {

	@SuppressWarnings("rawtypes")
	public Integer inserirCliente(Cliente cliente, Collection telefones, Collection enderecos, Usuario usuario) throws ControladorException;

	public void inserirClienteImovel(ClienteImovel clienteImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarClienteImovelEconomia(Collection clientesImoveisEconomia) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarCliente(Cliente cliente, Collection telefones, Collection enderecos, Usuario usuario) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteImovelTarifaSocial(FiltroClienteImovel filtroClienteImovel, Integer numerpPagina) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteImovel(FiltroClienteImovel filtroClienteImovel, Integer numeroPagina) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteEndereco(FiltroClienteEndereco filtroClienteEndereco) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCliente(FiltroCliente filtroCliente) throws ControladorException;

	public Collection<Cliente> pesquisarClienteEnderecoClienteImovel(FiltroClienteEndereco filtroClienteEndereco) throws ControladorException;

	public Collection<Cliente> pesquisarClienteDadosClienteEndereco(FiltroCliente filtroCliente, Integer numeroPagina) throws ControladorException;

	public Collection<Cliente> pesquisarClienteDadosClienteEnderecoRelatorio(FiltroCliente filtroCliente) throws ControladorException;

	public Integer pesquisarClienteDadosClienteEnderecoCount(FiltroCliente filtroCliente) throws ControladorException;

	public Collection<Cliente> pesquisarClienteOutrosCriterios(FiltroCliente filtroCliente) throws ControladorException;

	public Cliente pesquisarClienteDigitado(Integer idClienteDigitado) throws ControladorException;

	public Cliente pesquisarCliente(Integer idCliente) throws ControladorException;

	public int pesquisarQuantidadeClienteImovel(FiltroClienteImovel filtroClienteImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteImovel(FiltroClienteImovel filtroClienteImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteImovelRelatorio(FiltroClienteImovel filtroClienteImovel) throws ControladorException;

	public Cliente pesquisarObjetoClienteRelatorio(Integer idCliente) throws ControladorException;

	public Object[] pesquisarQtdeImoveisEEconomiasCliente(Integer idCliente) throws ControladorException;

	public Integer verificarExistenciaCliente(Integer codigoCliente) throws ControladorException;

	public ClienteImovel pesquisarClienteImovel(Integer idCliente, Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteFone(Integer idCliente) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarEnderecosClienteAbreviado(Integer idCliente) throws ControladorException;

	public Cliente consultarCliente(Integer idCliente) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarEnderecoCliente(Integer idCliente) throws ControladorException;

	public String pesquisarNomeClientePorImovel(Integer idImovel) throws ControladorException;

	public Cliente pesquisarDadosClienteRelatorioParcelamentoPorImovel(Integer idImovel) throws ControladorException;

	public String pesquisarClienteFonePrincipal(Integer idCliente) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarCliente(String codigo, String cpf, String rg, String cnpj, String nome, String nomeMae, String cep, String idMunicipio,
			String idBairro, String idLogradouro, String indicadorUso, String tipoPesquisa, String tipoPesquisaNomeMae, String clienteTipo,
			String idEsferaPoder, Integer numeroPagina) throws ControladorException;

	public Object filtrarQuantidadeCliente(String codigo, String cpf, String rg, String cnpj, String nome, String nomeMae, String cep, String idMunicipio,
			String idBairro, String idLogradouro, String indicadorUso, String tipoPesquisa, String tipoPesquisaNomeMae, String clienteTipo, String idEsferaPoder)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteImovelPeloClienteTarifaSocial(Integer idCliente) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteImovelPeloImovelTarifaSocial(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteImovelPeloImovelParaEndereco(Integer idImovel) throws ControladorException;

	public boolean verificaUsuarioinquilino(Integer idImovel) throws ControladorException;

	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo) throws ControladorException;

	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, LogradouroBairro logradouroBairroNovo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteImovelGerarArquivoFaturamento() throws ControladorException;

	public ClienteEmitirBoletimCadastroHelper pesquisarClienteEmitirBoletimCadastro(Integer idImovel, Short clienteRelacaoTipo) throws ControladorException;

	public Collection<Integer> pesquisarClientesAssociadosResponsavel(Integer idCliente) throws ControladorException;

	public Cliente pesquisarDadosClienteDoParcelamentoRelatorioParcelamento(Integer idParcelamento) throws ControladorException;

	public Cliente obterIdENomeCliente(String cpf) throws ControladorException;

	public void atualizarCPFCliente(String cpf, Integer idCliente, Usuario usuarioLogado) throws ControladorException;

	public Cliente retornaClienteUsuario(Integer idImovel) throws ControladorException;

	public Cliente retornaClienteProprietario(Integer idImovel) throws ControladorException;

	public IClienteAtualizacaoCadastral obterClienteAtualizacaoCadastral(Integer idImovel, Short idClienteRelacaoTipo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterDadosClienteFone(Integer idCliente) throws ControladorException;

	public Integer verificaExistenciaClienteAtualizacaoCadastral(Integer idCliente) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteImovel(Integer idImovel) throws ControladorException;

	public Integer pesquisarClienteResponsavelSuperiorParaPaginacaoCount(PesquisarClienteResponsavelSuperiorHelper helper) throws ControladorException;

	public Collection<Cliente> pesquisarClienteResponsavelSuperiorParaPaginacao(PesquisarClienteResponsavelSuperiorHelper helper, Integer numeroPagina)
			throws ControladorException;

	public IClienteAtualizacaoCadastral pesquisarClienteAtualizacaoCadastral(Integer idCliente, Integer idImovel, Integer idClienteRelacaoTipo)
			throws ControladorException;

	public Collection<ClienteFoneAtualizacaoCadastral> pesquisarClienteFoneAtualizacaoCadastral(Integer idCliente, Integer idMatricula, Integer idTipoFone,
			Integer idClienteRelacaoTipo, String numeroFone) throws ControladorException;

	public void atualizarIndicadorNomeContaClienteImovel(int idClienteImovel) throws ControladorException;

	public void atualizarTelefonePadrao(String idCliente, String idClienteFonePadrao) throws ControladorException;

	public void removerTodosTelefonesPorCliente(Integer idCliente) throws ControladorException;

	public Cliente retornaDadosClienteUsuario(Integer idImovel) throws ControladorException;

	public String obterNomeCliente(Integer idImovel) throws ControladorException;

	public Cliente pesquisarClienteUsuarioDoImovel(Integer idImovel) throws ControladorException;

	public Integer pesquisarQtdClientesAssociadosResponsavelNaoJuridica(Integer idCliente) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisAssociadosCliente(Integer idCliente, Short relacaoTipo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarAutocompleteCliente(String valor) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarAutocompleteClienteResponsavel(String valor) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterClienteImovelporRelacaoTipo(Integer idImovel, Integer idRelacaoTipo) throws ControladorException;

	public String validarCliente(String cpfCliente, Integer matricula) throws ControladorException;

	public Integer retornaIdClienteResponsavelIndicadorEnvioConta(Integer idImovel) throws ControladorException;

	public Cliente pesquisarDadosCliente(Integer idCliente) throws ControladorException;

	public ClienteImovel pesquisarClienteImovelOSFiscalizada(Integer idImovel) throws ControladorException;

	public ClienteTipo pesquisarClienteTipo(Integer idClienteTipo) throws ControladorException;

	public Collection<ClienteFone> pesquisarClienteFoneDoImovel(Integer idImovel) throws ControladorException;

	public Collection<Cliente> pesquisarClientePorCpfCnpj(String cpfCnpj) throws Exception;

	public Short pesquisarIndicadorNegativacaoPeriodoClienteResponsavel(Integer idImovel, Integer idClienteRelacaoTipo) throws ControladorException;

	public boolean existeEnderecoParaCliente(Integer idCliente) throws ControladorException;

	public Cliente pesquisarDadosClienteParaNegativacao(Integer idCliente, String cnpjEmpresa) throws ControladorException;

	public Localidade pesquisarLocalidadeCliente(Integer idCliente) throws ControladorException;
	
	public String obterEnderecoCorrespondencia(Integer idCliente) throws ControladorException;
	
	public String obterNomeClienteConta(Integer idImovel) throws ControladorException;
}
