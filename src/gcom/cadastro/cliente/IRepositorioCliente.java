package gcom.cadastro.cliente;

import gcom.cadastro.cliente.bean.PesquisarClienteResponsavelSuperiorHelper;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;

public interface IRepositorioCliente {

	@SuppressWarnings("rawtypes")
	public Collection pesquisarResponsavelSuperior(FiltroCliente filtroCliente)	throws ErroRepositorioException;

	public void removerTodosEnderecosPorCliente(Integer idCliente) throws ErroRepositorioException;

	public Collection<Cliente> pesquisarClienteDadosClienteEndereco(FiltroCliente filtroCliente, Integer numeroPagina) throws ErroRepositorioException;
	
	public Collection<Cliente> pesquisarClienteDadosClienteEnderecoRelatorio(FiltroCliente filtroCliente) throws ErroRepositorioException;

	public Integer pesquisarClienteDadosClienteEnderecoCount(FiltroCliente filtroCliente) throws ErroRepositorioException;
	
	public Collection<Cliente> pesquisarClienteOutrosCriterios(FiltroCliente filtroCliente) throws ErroRepositorioException;

	public Integer verificarExistenciaCliente(Integer idCliente) throws ErroRepositorioException;

	public Cliente pesquisarCliente(Integer idCliente) throws ErroRepositorioException;
	
	public Object[] pesquisarObjetoClienteRelatorio( Integer idCliente) throws ErroRepositorioException;
	
	public Object[] pesquisarQtdeImoveisEEconomiasCliente(Integer idCliente) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteFone(Integer idCliente) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection consultarCliente(Integer idCliente) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarEnderecosCliente(Integer idCliente) throws ErroRepositorioException;
	
	public Object[] pesquisarClienteFonePrincipal( Integer idCliente) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
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
		    Integer numeroPagina) throws ErroRepositorioException; 
	
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
			String clienteTipo, String idEsferaPoder
			) throws ErroRepositorioException; 
	
	@SuppressWarnings("rawtypes")	
	public Collection pesquisarClienteEmitirBoletimCadastro(Integer idImovel, Short clienteRelacaoTipo) throws ErroRepositorioException;
	
	public Collection<Integer> pesquisarClientesAssociadosResponsavel(Integer idCliente) throws ErroRepositorioException;
	
	public Object[] obterIdENomeCliente(String cpf) throws ErroRepositorioException;
	
	public void atualizarCPFCliente(String cpf,Integer idCliente) throws ErroRepositorioException;
	
	public Object[] obterDadosCliente(Integer idImovel, Short idClienteRelacaoTipo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterDadosClienteFone(Integer idCliente) throws ErroRepositorioException;
	
	public Integer verificaExistenciaClienteAtualizacaoCadastral(Integer idCliente) throws ErroRepositorioException;
	
	public Integer pesquisarClienteResponsavelSuperiorParaPaginacaoCount(PesquisarClienteResponsavelSuperiorHelper helper) throws ErroRepositorioException;
	
	public Collection<Cliente> pesquisarClienteResponsavelSuperiorParaPaginacao(PesquisarClienteResponsavelSuperiorHelper helper, Integer numeroPagina) throws ErroRepositorioException;
	
	public IClienteAtualizacaoCadastral pesquisarClienteAtualizacaoCadastral(Integer idCliente, Integer idImovel, Integer idClienteRelacaoTipo)
		throws ErroRepositorioException;
	
	public Collection<ClienteFoneAtualizacaoCadastral> pesquisarClienteFoneAtualizacaoCadastral(Integer idCliente, Integer idMatricula, 
			Integer idTipoFone, Integer idClienteRelacaoTipo,String numeroFone)
		throws ErroRepositorioException;
	
	public void atualizarTelefonePadrao(String idCliente, String idClienteFonePadrao) throws ErroRepositorioException;
	
	public void removerTodosTelefonesPorCliente(Integer idCliente) throws ErroRepositorioException;
	
	public Cliente pesquisarClienteUsuarioDoImovel(Integer idImovel) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection filtrarAutocompleteCliente(String valor)throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection filtrarAutocompleteClienteResponsavel(String valor, ClienteTipo tipo)throws ErroRepositorioException;
	
	public Integer pesquisarQtdClientesAssociadosResponsavelNaoJuridica(Integer idCliente) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisAssociadosCliente(Integer idCliente, Short relacaoTipo )throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")	
	public Collection obterClienteImovelporRelacaoTipo(Integer idImovel, Integer idRelacaoTipo) throws ErroRepositorioException;
	
	public String validarCliente(String cpfCliente, Integer matricula) throws ErroRepositorioException;

	public Cliente pesquisarDadosCliente(Integer idCliente) throws ErroRepositorioException;
	
    public ClienteTipo pesquisarClienteTipo(Integer idClienteTipo) throws ErroRepositorioException;
    
	public Collection<ClienteFone> pesquisarClienteFoneDoImovel(Integer idImovel) throws ErroRepositorioException;
	
	public Collection<Cliente> pesquisarClientePorCpfCnpj(String cpfCnpj) throws ErroRepositorioException;
	
	public Integer pesquisarEnderecoClienteParaNegativacao(Integer idCliente) throws ErroRepositorioException;
	
	public Cliente pesquisarDadosClienteParaNegativacao(Integer idCliente, String cnpjEmpresa) throws ErroRepositorioException;
	
	public String obterNomeClienteConta(Integer idImovel) throws ErroRepositorioException;

	public Cliente obterUsuarioImovelPorData(Integer idImovel, Integer idClienteRelacaoTipo, Date data) throws ErroRepositorioException;
	
}
