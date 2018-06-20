package gcom.cadastro.endereco;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.endereco.bean.AtualizarLogradouroBairroHelper;
import gcom.cadastro.endereco.bean.AtualizarLogradouroCepHelper;
import gcom.cadastro.endereco.to.LogradouroTO;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Imovel;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ControladorException;

import java.util.Collection;

/**
 * Description of the Interface
 * 
 * @author Sávio Luiz
 * @created 20 de Julho de 2005
 */
public interface ControladorEnderecoLocal extends javax.ejb.EJBLocalObject {

	public Integer inserirLogradouro(Logradouro logradouro, Collection<Bairro> colecaoBairros, Collection<Cep> colecaoCeps) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarLogradouro(Logradouro logradouro, Collection colecaoBairros, Collection colecaoCeps,
			Collection<AtualizarLogradouroBairroHelper> colecaoBairrosAtualizacao, Collection<AtualizarLogradouroCepHelper> colecaoCepsAtualizacao)
			throws ControladorException;

	public void removerLogradouro(String[] ids, String pacoteLogradouro, OperacaoEfetuada operacaoEfetuada,
			Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper) throws ControladorException;

	
	@SuppressWarnings("rawtypes")
	public void inserirCepImportados(Collection cepsImportados) throws ControladorException;

	public String pesquisarEndereco(Integer idImovel) throws ControladorException;

	public String pesquisarEnderecoFormatado(Integer idImovel) throws ControladorException;

	public Cep obterCepInicialMunicipio(Municipio municipio) throws ControladorException;

	public boolean verificarCepUnicoMunicipio(Cep cep) throws ControladorException;

	public boolean verificarBairroTipoBairroNaoInformado(Bairro bairro) throws ControladorException;

	public Logradouro verificarCepAssociadoOutroLogradouro(Cep cep) throws ControladorException;

	public Collection<Bairro> obterBairrosPorLogradouro(Logradouro logradouro) throws ControladorException;

	public Integer inserirAssociacaoLogradouroCep(LogradouroCep logradouroCep) throws ControladorException;

	public Collection<LogradouroCep> pesquisarAssociacaoLogradouroCepPorLogradouro(Logradouro logradouro) throws ControladorException;

	public boolean verificarLogradouroAssociadoCepTipoLogradouro(Logradouro logradouro) throws ControladorException;

	public Cep obterCepUnicoMunicipio(Municipio municipio) throws ControladorException;

	public LogradouroBairro pesquisarAssociacaoLogradouroBairro(Integer idBairro, Integer idLogradouro) throws ControladorException;

	public LogradouroCep pesquisarAssociacaoLogradouroCep(Integer idCep, Integer idLogradouro) throws ControladorException;

	public Collection<Logradouro> pesquisarLogradouro(FiltroLogradouro filtroLogradouro, Integer numeroPaginas) throws ControladorException;

	public Integer pesquisarLogradouroCount(FiltroLogradouro filtroLogradouro) throws ControladorException;

	public String pesquisarEnderecoClienteAbreviado(Integer idCliente) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarLogradouroCompleto(String codigoMunicipio, String codigoBairro, String nome, String nomePopular, String logradouroTipo,
			String logradouroTitulo, String cep, String codigoLogradouro, String indicadorUso, String tipoPesquisa, String tipoPesquisaPopular,
			Integer numeroPaginas) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarLogradouroCompletoRelatorio(String codigoMunicipio, String codigoBairro, String nome, String nomePopular, String logradouroTipo,
			String logradouroTitulo, String cep, String codigoLogradouro, String indicadorUso, String tipoPesquisa, String tipoPesquisaPopular)
			throws ControladorException;

	public Integer pesquisarLogradouroCompletoCount(String codigoMunicipio, String codigoBairro, String nome, String nomePopular, String logradouroTipo,
			String logradouroTitulo, String cep, String codigoLogradouro, String indicadorUso, String tipoPesquisa, String tipoPesquisaPopular)
			throws ControladorException;

	public boolean verificarCepInicialMunicipio(Cep cep) throws ControladorException;

	public String pesquisarEnderecoRegistroAtendimentoFormatado(Integer idRegistroAtendimento) throws ControladorException;

	public String pesquisarEnderecoRegistroAtendimentoSolicitanteFormatado(Integer idRegistroAtendimentoSolicitante) throws ControladorException;

	public RegistroAtendimento pesquisarRegistroAtendimentoEndereco(Integer idRegistroAtendimento) throws ControladorException;

	public LogradouroCep pesquisarLogradouroCepEndereco(Integer idLogradouroCep) throws ControladorException;

	public LogradouroBairro pesquisarLogradouroBairroEndereco(Integer idLogradouroBairro) throws ControladorException;

	public Imovel pesquisarImovelParaEndereco(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClientesEnderecosAbreviado(Integer idCliente) throws ControladorException;

	public String obterEnderecoAbreviadoImovel(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarEnderecoTotalmenteDividido(Integer idImovel) throws ControladorException;

	public String obterEnderecoAbreviadoRA(Integer idRA) throws ControladorException;

	public String[] pesquisarEnderecoClienteAbreviadoDividido(Integer idCliente) throws ControladorException;

	public String[] pesquisarEnderecoFormatadoDividido(Integer idImovel) throws ControladorException;

	public String[] pesquisarEnderecoFormatadoClienteDividido(Integer idCliente) throws ControladorException;

	public String pesquisarEnderecoAbreviadoCAER(Integer idImovel) throws ControladorException;

	public Cep pesquisarCep(Integer codigoCep) throws ControladorException;

	public boolean verificarExistenciaClienteEndereco(Integer idCliente) throws ControladorException;

	public String pesquisarBairroLogradouroRegistroAtendimentoSolicitante(Integer idRegistroAtendimentoSolicitante) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection retornaTipoLogradouroPeloCep() throws ControladorException;

	public Collection<Object[]> pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresa(Integer idCliente) throws ControladorException;

	public LogradouroTO pesquisarLogradouro(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarLogradouroCliente(Integer idCliente) throws ControladorException;

	public String[] pesquisarEnderecoClienteAbreviadoDivididoCosanpa(Integer idCliente) throws ControladorException;

	public String[] pesquisarEnderecoFormatadoDivididoCosanpa(Integer idImovel) throws ControladorException;

	public String pesquisarEnderecoRegistroAtendimentoFormatadoIniciadoPeloBairro(Integer idRegistroAtendimento) throws ControladorException;

	public String pesquisarEnderecoSolicitanteRAReiteracaoFormatado(Integer idraReiteracao) throws ControladorException;

	public Collection<Object[]> pesquisarDadosClienteEnderecoArquivoTextoContasCobrancaEmpresaLayout02(Integer idCliente) throws ControladorException;

	public String[] pesquisarEnderecoImovelDividido(Integer idImovel) throws ControladorException;

	public String[] pesquisarEnderecoClienteDividido(Integer idCliente) throws ControladorException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarEnderecoFormatadoCliente(Integer idCliente) throws ControladorException;
}
