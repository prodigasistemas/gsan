package gcom.cadastro.imovel;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasHelper;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaConclusaoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaConclusaoRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaEnderecoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaEnderecoRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeHelper;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelSubcategoriaHelper;
import gcom.cadastro.imovel.bean.InserirImovelHelper;
import gcom.cadastro.tarifasocial.TarifaSocialCarta;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.faturamento.conta.Conta;
import gcom.gui.faturamento.bean.FiltrarImovelInserirManterContaHelper;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.Rota;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.cadastro.GerarRelatorioImoveisDoacoesHelper;
import gcom.relatorio.cadastro.dto.ContratoDTO;
import gcom.relatorio.micromedicao.FiltrarAnaliseExcecoesLeiturasHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

public interface ControladorImovelLocal extends javax.ejb.EJBLocalObject {

	public void atualizarImovelLigacaoAgua(Imovel imovel, Integer idLigacaoAguaSituacao) throws ControladorException;

	public void inserirImovel(Imovel imovel) throws ControladorException;

	public int obterQuantidadeEconomias(Imovel imovel) throws ControladorException;

	public Object pesquisarObterQuantidadeCategoria() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterColecaoImovelSubcategorias(Imovel imovel, Integer quantidadeMinimaEconomia) throws ControladorException;
	
	@SuppressWarnings("rawtypes")
	public Collection obterQuantidadeEconomiasCategoria(Imovel imovel) throws ControladorException; 

	@SuppressWarnings("rawtypes")
	public Collection obterQuantidadeEconomiasCategoria(Imovel imovel, Integer categoria) throws ControladorException; 
	
	public Integer inserirImovelRetorno(InserirImovelHelper inserirImovelHelper) throws ControladorException;

	public void inserirImovelSubCategoria(ImovelSubcategoria imovelSubcategoria) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void informarImovelEconomias(Collection imoveisEconomias, Usuario usuarioLogado) throws ControladorException;

	public void removerImovelEconomia(ImovelEconomia imovelEconomia, Usuario usuarioLogado) throws ControladorException;

	public void atualizarImovel(Imovel imovel, Usuario usuario) throws ControladorException;

	public void transferirImovel(Imovel imovel, Usuario usuario) throws ControladorException;

	public void atualizarImovelAlterarFaturamento(Imovel imovel, Usuario usuarioLogado) throws ControladorException;

	public void atualizarImovel(InserirImovelHelper inserirImovelHelper) throws ControladorException;
	
	@SuppressWarnings("rawtypes")
	public void verificarExistenciaIPTU(Collection imoveisEconomia, Imovel imovel, String numeroIptu, Date dataUltimaAlteracao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void verificarExistenciaCelpe(Collection imoveisEconomia, Imovel imovel, String numeroCelpe, Date dataUltimaAlteracao) throws ControladorException;

	public void removerImovel(String[] ids, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovel(Integer idLocalidade, Integer idSetorComercial, Integer idQuadra,Integer quadraFace, Short lote) throws ControladorException;
	

	public void atualizarImovelInscricao(Imovel imovel, Usuario usuario) throws ControladorException;

	public void atualizarImovelSubCategoria(ImovelSubcategoria imovelSubcategoria) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarImovelOutrosCriterios(String tipoMedicao) throws ControladorException;

	public Collection<Imovel> pesquisarImovelParametrosClienteImovel(FiltroClienteImovel filtroClienteImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection carregarImovelMicromedicao(Collection imoveisMicromedicao) throws ControladorException;

	public Categoria obterDescricoesCategoriaImovel(Imovel imovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterValorPorCategoria(Collection<Categoria> colecaoCategorias, BigDecimal valor);

	public void atualizarCategoria(Categoria categoria) throws ControladorException;

	public Integer inserirCategoria(Categoria categoria) throws ControladorException;

	public void atualizarSubcategoria(Subcategoria subcategoria, Subcategoria subcategoriaVelha) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelSituacaoEspecialFaturamento(String valor, SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelSituacaoEspecialCobranca(String valor, SituacaoEspecialCobrancaHelper situacaoEspecialFaturamentoHelper)
			throws ControladorException;

	public int validarMesAnoReferencia(SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper) throws ControladorException;

	public Collection<Categoria> pesquisarCategoria() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarFaturamentoSituacaoTipo(Collection colecaoIdsImoveis, Integer idFaturamentoTipo, Usuario usuario) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void retirarSituacaoEspecialFaturamento(SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper,
			Collection pesquisarImoveisParaSerRetirados) throws ControladorException;

	public Collection<Integer> pesquisarImoveisIds(Integer rotaId) throws ControladorException;

	public Object pesquisarImovelIdComConta(Integer imovelId, Integer anoMesReferencia) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public boolean verificarExistenciaClienteImovel(Collection colecaoClienteImovel, ClienteImovel clienteImovel);

	@SuppressWarnings("rawtypes")
	public boolean verificarExistenciaImovelSubcategoria(Collection colecaoImovelSubCategoria, ImovelSubcategoria imovelSubcategoria);

	public void verificarExistenciaIPTU(String numeroIptu, Integer idSetorComercial) throws ControladorException;

	public void verificarExistenciaCELPE(String numeroCelp, Integer idSetorComercial) throws ControladorException;

	public Integer verificarExistenciaImovel(Integer idImovel) throws ControladorException;

	public Integer validarMesAnoReferenciaCobranca(SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarCobrancaSituacaoTipo(Collection colecaoIdsImoveis, Integer idCobrancaTipo, Usuario usuario) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void retirarSituacaoEspecialCobranca(SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper, Collection imoveisParaSerRetirados,
			Usuario usuario) throws ControladorException;

	public ImovelSituacao obterSituacaoImovel(Integer situacaoAguaId, Integer situacaoEsgotoId) throws ControladorException;

	public Imovel pesquisarImovelDigitado(Integer idImovelDigitado) throws ControladorException;

	public boolean verificarLocalidadeMatriculaImovel(String idLocalidadeInformada, Imovel imovelInformado) throws ControladorException;

	public void verificarPreeenchimentoImovelECliente(String idImovel, String idCliente) throws ControladorException;

	public Integer inserirSituacaoImovel(String idImovelSituacaoTipo, String idLigacaoAguaSituacao, String idLigacaoEsgotoSituacao) throws ControladorException;

	public int obterIndicadorExistenciaHidrometroImovel(Integer idImovel) throws ControladorException;

	public Categoria obterPrincipalCategoriaImovel(Integer idImovel) throws ControladorException;

	public void atualizarImovelExecucaoOrdemServicoLigacaoAgua(Imovel imovel, LigacaoAguaSituacao situacaoAgua) throws ControladorException;

	public void atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(Imovel imovel, LigacaoEsgotoSituacao situacaoEsgoto) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection gerarRelatorioImovelOutrosCriterios(String idImovelCondominio, String idImovelPrincipal, String idSituacaoLigacaoAgua,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto, String intervaloValorPercentualEsgotoInicial, String intervaloValorPercentualEsgotoFinal,

			String intervaloMediaMinimaImovelInicial, String intervaloMediaMinimaImovelFinal, String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal,

			String idImovelPerfil, String idPocoTipo, String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial, String areaConstruidaFinal, String idCadastroOcorrencia, String idConsumoTarifa,
			String idGerenciaRegional, String idLocalidadeInicial, String idLocalidadeFinal, String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem, String loteDestno, String cep, String logradouro, String bairro, String municipio,
			String idTipoMedicao, String indicadorMedicao, String idSubCategoria, String idCategoria, String quantidadeEconomiasInicial,
			String quantidadeEconomiasFinal, String diaVencimento, String idCliente, String idClienteTipo, String idClienteRelacaoTipo,
			String numeroPontosInicial, String numeroPontosFinal, String numeroMoradoresInicial, String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio, String cdRotaInicial, String cdRotaFinal, String sequencialRotaInicial, String sequencialRotaFinal)
			throws ControladorException;

	public Integer obterQuantidadaeRelacaoImoveisDebitos(String idImovelCondominio, String idImovelPrincipal, String idSituacaoLigacaoAgua,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto, String intervaloValorPercentualEsgotoInicial, String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial, String intervaloMediaMinimaImovelFinal, String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil, String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca, String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia, String idConsumoTarifa, String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial, String setorComercialFinal, String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro, String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria, String quantidadeEconomiasInicial, String quantidadeEconomiasFinal, String diaVencimento,
			String idCliente, String idClienteTipo, String idClienteRelacaoTipo, String numeroPontosInicial, String numeroPontosFinal,
			String numeroMoradoresInicial, String numeroMoradoresFinal, String idAreaConstruidaFaixa, String idUnidadeNegocio, Integer relatorio,
			String cdRotaInicial, String cdRotaFinal, String sequencialRotaInicial, String sequencialRotaFinal) throws ControladorException;

	public String pesquisarInscricaoImovel(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection gerarRelatorioDadosEconomiaImovel(String idImovelCondominio, String idImovelPrincipal, String idSituacaoLigacaoAgua,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto, String intervaloValorPercentualEsgotoInicial, String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial, String intervaloMediaMinimaImovelFinal, String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil, String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca, String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia, String idConsumoTarifa, String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial, String setorComercialFinal, String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro, String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria, String quantidadeEconomiasInicial, String quantidadeEconomiasFinal, String diaVencimento,
			String idCliente, String idClienteTipo, String idClienteRelacaoTipo, String numeroPontosInicial, String numeroPontosFinal,
			String numeroMoradoresInicial, String numeroMoradoresFinal, String idAreaConstruidaFaixa, String idUnidadeNegocio) throws ControladorException;

	public Boolean confirmarImovelExcluido(Integer idImovel);

	public Collection<EntidadeBeneficente> pesquisarEntidadeBeneficente(Integer idEntidadeBeneficente) throws ControladorException;

	public Collection<ImovelDoacao> pesquisarImovelDoacao(FiltroImovelDoacao filtroImovelDoacao) throws ControladorException;

	public ImovelDoacao verificarExistenciaImovelDoacao(Integer idImovel, Integer idEntidadeBeneficente) throws ControladorException;

	public void atualizarImovelDoacao(ImovelDoacao imovelDoacao, Usuario usuarioLogado) throws ControladorException;

	public Imovel pesquisarImovelRegistroAtendimento(Integer idImovel) throws ControladorException;

	public void validarExistenciaHidrometro(Imovel imovel, Integer medicaoTipo) throws ControladorException;

	public Imovel pesquisarImovelSituacaoAtiva(FiltroImovel filtroImovel) throws ControladorException;

	public void verificarImovelControleConcorrencia(Imovel imovel) throws ControladorException;

	public Imovel consultarImovelDadosCadastrais(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClientesImovel(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClientesImovelDataMax(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClientesImovelExcluidoOuNao(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCategoriasImovel(Integer idImovel) throws ControladorException;

	public ImovelPerfil obterImovelPerfil(Integer idImovel) throws ControladorException;

	public Imovel consultarImovelDadosComplementares(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarVencimentoAlternativoImovel(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitosAutomaticosImovel(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarFaturamentosSituacaoHistorico(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCobrancasSituacaoHistorico(Integer idImovel) throws ControladorException;

	public Imovel consultarImovelAnaliseMedicaoConsumo(Integer idImovel) throws ControladorException;

	public Imovel consultarImovelHistoricoFaturamento(Integer idImovel) throws ControladorException;

	public String consultarNomeClienteUsuarioImovel(Integer idImovel) throws ControladorException;

	public Cliente consultarClienteUsuarioImovel(Imovel imovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarContasImovel(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarContasHistoricosImovel(Integer idImovel) throws ControladorException;

	public void atualizarImovelLeituraAnormalidade(Map<Integer, MedicaoHistorico> mapAtualizarLeituraAnormalidadeImovel, Date dataAtual)
			throws ControladorException;

	public Imovel consultarParcelamentosDebitosImovel(Integer idImovel) throws ControladorException;

	public Cliente pesquisarClienteUsuarioImovel(Integer idImovel) throws ControladorException;

	public void atualizarImovelLigacaoAguaEsgoto(Integer idImovel, Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao,
			short indicadorLigacaoAtualizada, short indiacadorConsumoFixado, Integer consumoDefinido, short indicadorVolumeFixado) throws ControladorException;

	public Integer inserirImovelDoacaoRetorno(ImovelDoacao imovelDoacao, Usuario usuarioLogado) throws ControladorException;

	public Integer recuperarIdConsumoTarifa(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovel(String idImovel, String idLocalidade, String codigoSetorComercial, String numeroQuadra, String lote, String subLote,
			String codigoCliente, String idMunicipio, String cep, String idBairro, String idLogradouro, String numeroImovelInicial, String numeroImovelFinal,
			boolean pesquisarImovelManterVinculo, boolean pesquisarImovelCondominio, Integer numeroPagina) throws ControladorException;

	public Integer pesquisarQuantidadeImovel(String idImovel, String idLocalidade, String codigoSetorComercial, String numeroQuadra, String lote,
			String subLote, String codigoCliente, String idMunicipio, String cep, String idBairro, String idLogradouro, String numeroImovelInicial,
			String numeroImovelFinal, boolean pesquisarImovelManterVinculo, boolean pesquisarImovelCondominio) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelInscricao(String idImovel, String idLocalidade, String codigoSetorComercial, String numeroQuadra, String lote,
			String subLote, String codigoCliente, String idMunicipio, String cep, String idBairro, String idLogradouro, String numeroImovelInicial,
			String numeroImovelFinal, boolean pesquisarImovelCondominio, Integer numeroPagina) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void informarOcorrenciaCadastroAnormalidadeElo(String idImovel, String idOcorrenciaCadastro, String idAnormalidadeElo,
			String dataOcorrenciaCadastro, String dataAnormalidadeElo, byte[] uploadPictureCadastro, byte[] uploadPictureAnormalidade, Usuario usuarioLogado,
			Collection colecaoIdCadastroOcorrenciaRemover, Collection colecaoIdAnormalidadeRemover) throws ControladorException;

	public Integer pesquisarIdLigacaoAguaSituacao(Integer idImovel) throws ControladorException;

	public Integer pesquisarIdLigacaoEsgotoSituacao(Integer idImovel) throws ControladorException;

	public Integer pesquisarTarifaImovel(Integer idImovel) throws ControladorException;

	public void inserirImovelSitucaoCobranca(Imovel imovel, CobrancaSituacao cobrancaSituacao, Cliente cliente, Cliente clienteEscritorio,
			Cliente clienteAdvogado, Date dataImplantacao, Integer anoMesInicio, Integer anoMesFim, Usuario usuarioLogado) throws ControladorException;

	public void retirarImovelSitucaoCobranca(Integer idImovel, Usuario usuarioLogado, String[] idsImovelCobrancaSituacao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisClientesRelacao(Cliente cliente, ClienteRelacaoTipo relacaoClienteImovel, Integer numeroInicial)
			throws ControladorException;

	public Integer pesquisarExistenciaImovelSubCategoria(Integer idImovel, Integer idCategoria) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisPorRota(Integer idRota) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisTarifaConsumo(String idLocalidadeInicial, String idLocalidadeFinal, String codigoSetorComercialInicial,
			String codigoSetorComercialFinal, String quadraInicial, String quadraFinal, String loteInicial, String loteFinal, String subLoteInicial,
			String subLoteFinal, String idTarifaAnterior) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarImoveisTarifaConsumo(String matricula, String tarifaAtual, Collection colecaoImoveis) throws ControladorException;

	public void atualizarImovelPerfilNormal(Imovel imovel, RegistradorOperacao registradorOperacao) throws ControladorException;

	public Imovel pesquisarImovelComSituacaoAguaEsgoto(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisPorRotaComPaginacao(Rota rota, Collection idsSituacaoLigacaoAgua, Collection idsSituacaoLigacaoEsgoto, int numeroInicial,
			int numeroMaximo) throws ControladorException;

	public Integer verificarNumeroIptu(String numeroIptu, Integer idImovel, Integer idImovelEconomia, Integer idMunicipio) throws ControladorException;

	public Integer verificarNumeroCompanhiaEletrica(Long numeroCompanhiaEletrica, Integer idImovel, Integer idImovelEconomia) throws ControladorException;

	public Collection<Subcategoria> obterQuantidadeEconomiasSubCategoria(Integer idImovel) throws ControladorException;

	public Imovel pesquisarImovel(Integer idImovel) throws ControladorException;
	
	public Collection<Imovel> pesquisarImoveis(Collection<Integer> idsImoveis) throws ControladorException;

	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo) throws ControladorException;

	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, LogradouroBairro logradouroBairroNovo) throws ControladorException;

	public Short obterIndicadorGeracaoAcrescimosClienteImovel(Integer idImovel) throws ControladorException;

	public Integer obterConsumoMedioNaoMedidoImovel(Imovel imovel) throws ControladorException;

	public String obterSituacaoCobrancaImovel(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarColecaoImovel(FiltrarImovelInserirManterContaHelper filtro) throws ControladorException;

	public Integer pesquisarColecaoImovelPerfilBloqueado(FiltrarImovelInserirManterContaHelper filtro) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarColecaoImovelCliente(Integer codigoCliente, Integer relacaoTipo, Boolean verificarImovelPerfilBloqueado)
			throws ControladorException;

	public Integer pesquisarColecaoImovelClienteBloqueadoPerfil(Integer codigoCliente, Integer relacaoTipo) throws ControladorException;

	public ImovelSubcategoria obterPrincipalSubcategoria(Integer idCategoria, Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterQuantidadeEconomiasContaCategoriaPorSubcategoria(Conta conta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterQuantidadeEconomiasContaCategoriaPorSubcategoria(Integer conta) throws ControladorException;

	public Object pesquisarObterQuantidadeSubcategoria() throws ControladorException;

	public Collection<Integer> pesquisarTodosIdsPerfilImovel() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelClientesEspeciaisRelatorio(String idUnidadeNegocio, String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String codigoSetorInicial, String codigoSetorFinal, String codigoRotaInicial, String codigoRotaFinal,
			String[] idsPerfilImovel, String[] idsCategoria, String[] idsSubcategoria, String idSituacaoAgua, String idSituacaoEsgoto,
			String qtdeEconomiasInicial, String qtdeEconomiasFinal, String intervaloConsumoAguaInicial, String intervaloConsumoAguaFinal,
			String intervaloConsumoEsgotoInicial, String intervaloConsumoEsgotoFinal, String idClienteResponsavel, String intervaloConsumoResponsavelInicial,
			String intervaloConsumoResponsavelFinal, Date dataInstalacaoHidrometroInicial, Date dataInstalacaoHidrometroFinal,
			String[] idsCapacidadesHidrometro, String[] idsTarifasConsumo, Integer anoMesFaturamento, String idLeituraAnormalidade, String leituraAnormalidade,
			String idConsumoAnormalidade, String consumoAnormalidade) throws ControladorException;

	public FaturamentoGrupo pesquisarGrupoImovel(Integer idImovel);

	public LigacaoEsgotoSituacao pesquisarLigacaoEsgotoSituacao(Integer idImovel) throws ControladorException;

	public LigacaoAguaSituacao pesquisarLigacaoAguaSituacao(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection gerarRelatorioImovelEnderecoOutrosCriterios(String idImovelCondominio, String idImovelPrincipal, String idSituacaoLigacaoAgua,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto, String intervaloValorPercentualEsgotoInicial, String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial, String intervaloMediaMinimaImovelFinal, String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil, String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca, String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia, String idConsumoTarifa, String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial, String setorComercialFinal, String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro, String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria, String quantidadeEconomiasInicial, String quantidadeEconomiasFinal, String diaVencimento,
			String idCliente, String idClienteTipo, String idClienteRelacaoTipo, String numeroPontosInicial, String numeroPontosFinal,
			String numeroMoradoresInicial, String numeroMoradoresFinal, String idAreaConstruidaFaixa, String idUnidadeNegocio, String seqRotaInicial,
			String seqRotaFinal, String rotaInicial, String rotaFinal, String ordenacaoRelatorio) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection gerarRelatorioCadastroConsumidoresInscricao(String idImovelCondominio, String idImovelPrincipal, String idSituacaoLigacaoAgua,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto, String intervaloValorPercentualEsgotoInicial, String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial, String intervaloMediaMinimaImovelFinal, String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil, String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca, String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia, String idConsumoTarifa, String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial, String setorComercialFinal, String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro, String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria, String quantidadeEconomiasInicial, String quantidadeEconomiasFinal, String diaVencimento,
			String idCliente, String idClienteTipo, String idClienteRelacaoTipo, String numeroPontosInicial, String numeroPontosFinal,
			String numeroMoradoresInicial, String numeroMoradoresFinal, String idAreaConstruidaFaixa, String idUnidadeNegocio, String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial, String sequencialRotaFinal, String ordenacao, String[] pocoTipoListIds)
			throws ControladorException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoPeloId(Integer idPagamentoHistorico) throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoPeloId(Integer idPagamento) throws ControladorException;

	public Cliente obterDescricaoIdCliente(Integer idImovel) throws ControladorException;

	public String pesquisarNomeAgenteArrecadador(Integer idPagamentoHistorico) throws ControladorException;

	public Cep pesquisarCepImovel(Imovel imovel) throws ControladorException;

	public String pesquisarCaracterRetorno(Integer idConteudoRegistro) throws ControladorException;

	public ImovelSubcategoria obterPrincipalSubcategoriaComCodigoGrupo(Integer idCategoria, Integer idImovel) throws ControladorException;

	public Categoria obterPrincipalCategoriaConta(Integer idConta) throws ControladorException;

	public Object[] pesquisarLocalidadeSetorImovel(Integer idImovel) throws ControladorException;

	public Integer pesquisarSequencialRota(Integer idImovel) throws ControladorException;

	public Imovel pesquisarDadosImovel(Integer idImovel) throws ControladorException;

	public Collection<Categoria> pesquisarCategoriasImovel(Imovel imovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosImovel(String idsImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosImovel(Collection colecaoIdsImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosImovel(FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper, Integer anoMes)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisClientesRelacao(Collection idsCliente, Integer numeroInicial) throws ControladorException;

	public CobrancaSituacao pesquisarImovelCobrancaSituacao(Integer idImmovel) throws ControladorException;

	public void atualizarSituacaoCobrancaImovel(Integer idSituacaoCobranca, Integer idImovel) throws ControladorException;

	public String pesquisarInscricaoImovelSemPonto(Integer idImovel) throws ControladorException;

	public Collection<ImovelSubcategoria> pesquisarImovelSubcategoria(Imovel imovel) throws ControladorException;

	public Integer pesquisarQuantidadeImovelInscricao(String idImovel, String idLocalidade, String codigoSetorComercial, String numeroQuadra, String lote,
			String subLote, String codigoCliente, String idMunicipio, String cep, String idBairro, String idLogradouro, String numeroImovelInicial,
			String numeroImovelFinal, boolean pesquisarImovelCondominio) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelPerfilDiferenteCorporativo() throws ControladorException;

	public Integer obterQuantidadeEconomiasVirtuais(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelPerfilTarifaSocialDiferenteCorporativo() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public List pesquisarLocalidadeComImoveisComSituacaoLigadoEmAnalise() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisComSituacaoLigadoEmAnalise(Integer idLocalidade) throws ControladorException;

	public void atualizarSituacaoAguaPorImovel(String id, String situacaoAguaLigado) throws ControladorException;

	public Integer obterQuadraPerfil(Integer idImovel) throws ControladorException;

	public Object pesquisarImovelIdComContaNaoPreFaturada(Integer imovelId, Integer anoMesReferencia) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void validarAbaInserirImovelSubcategoria(Collection colecaoSubcategorias, int permissaoEspecial, Usuario usuarioLogado) throws ControladorException;

	public void atualizarImovelSituacaoAtualizacaoCadastral(Integer idImovel, Integer idSituacaoAtualizacaoCadastral) throws ControladorException;

	public void atualizarImovelAtualizacaoCadastralSituacaoAtualizacaoCadastral(Integer idImovel, Integer idSituacaoAtualizacaoCadastral, Integer idEmpresa)
			throws ControladorException;

	public Integer verificaExistenciaImovelAtualizacaoCadastral(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelEconomia(Integer idImovel, Integer idSubcategoria) throws ControladorException;

	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastralInscricao(Integer idImovel, Integer idEmpresa) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterImoveisAtualizacaoCadastral(Integer idLocalidade, String codigoSetor, Integer quadraInicial, Integer quadraFinal, Integer idEmpresa,
			Integer idRota) throws ControladorException;

	public Boolean pesquisarExistenciaImovelEconomia(Integer idImovel, Integer idSubcategoria) throws ControladorException;

	public ImovelAbaLocalidadeRetornoHelper validarImovelAbaLocalidade(ImovelAbaLocalidadeHelper helper) throws ControladorException;

	public ImovelAbaEnderecoRetornoHelper validarImovelAbaEndereco(ImovelAbaEnderecoHelper helper) throws ControladorException;

	public void validarImovelAbaCliente(Collection<ClienteImovel> imoveisClientes, Usuario usuario) throws ControladorException;

	public ImovelAbaCaracteristicasRetornoHelper validarImovelAbaCaracteristicas(ImovelAbaCaracteristicasHelper helper) throws ControladorException;

	public ImovelAbaConclusaoRetornoHelper validarImovelAbaConclusao(ImovelAbaConclusaoHelper helper) throws ControladorException;

	public Object[] consultarDadosClienteUsuarioImovel(String idImovel) throws ControladorException;

	public Integer gerarRelatorioCadastroConsumidoresInscricaoCount(String idImovelCondominio, String idImovelPrincipal, String idSituacaoLigacaoAgua,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto, String intervaloValorPercentualEsgotoInicial, String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial, String intervaloMediaMinimaImovelFinal, String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil, String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca, String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia, String idConsumoTarifa, String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial, String setorComercialFinal, String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro, String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria, String quantidadeEconomiasInicial, String quantidadeEconomiasFinal, String diaVencimento,
			String idCliente, String idClienteTipo, String idClienteRelacaoTipo, String numeroPontosInicial, String numeroPontosFinal,
			String numeroMoradoresInicial, String numeroMoradoresFinal, String idAreaConstruidaFaixa, String idUnidadeNegocio, String cdRotaInicial,
			String cdRotaFinal, String sequencialRotaInicial, String sequencialRotaFinal, String[] pocoTipoListIds) throws ControladorException;

	public Integer gerarRelatorioImovelEnderecoOutrosCriteriosCount(String idImovelCondominio, String idImovelPrincipal, String idSituacaoLigacaoAgua,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto, String intervaloValorPercentualEsgotoInicial, String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial, String intervaloMediaMinimaImovelFinal, String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil, String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca, String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia, String idConsumoTarifa, String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial, String setorComercialFinal, String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro, String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria, String quantidadeEconomiasInicial, String quantidadeEconomiasFinal, String diaVencimento,
			String idCliente, String idClienteTipo, String idClienteRelacaoTipo, String numeroPontosInicial, String numeroPontosFinal,
			String numeroMoradoresInicial, String numeroMoradoresFinal, String idAreaConstruidaFaixa, String idUnidadeNegocio, String rotaInicial,
			String rotaFinal, String ordenacaoRelatorio, String seqRotaInicial, String seqRotaFinal) throws ControladorException;

	public void atualizarSituacaoCobrancaETipoIdsImoveis(Integer idSituacaoCobranca, Integer idSituacaoCobrancaTipo, Collection<Integer> idsImovel)
			throws ControladorException;

	public String pesquisarInscricaoImovelExcluidoOuNao(Integer idImovel) throws ControladorException;

	public Cliente pesquisarClienteUsuarioImovelExcluidoOuNao(Integer idImovel) throws ControladorException;

	public Boolean verificarImovelPerfilBloqueado(Integer idImovel) throws ControladorException;

	public Short pesquisarFatorEconomiasCategoria(Integer idCategoria) throws ControladorException;

	public Date pesquisarUltimaAlteracaoImovel(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCategoriaSubcategoriaImovel(Integer idImovel) throws ControladorException;

	public Integer obterConsumoFaturadoImovelNoMes(Integer idImovel, Integer mesAnoReferencia) throws ControladorException;

	public ImovelPerfil recuperaPerfilImovel(Integer idImovel) throws ControladorException;

	public void atualizarDataRetiradaImovelSituacaoCobranca(Integer idImovelSituacaoCobranca, Date dataRetirada) throws ControladorException;

	public void atualizarSituacaoCobrancaImovel(Integer idSituacaoCobrancaNova, Integer idSituacaoCobrancaBanco, Integer idImovel) throws ControladorException;

	public void verificaRestricaoDaTabelaClienteImovel(ClienteImovel clienteImovel) throws ControladorException;

	public Object pesquisarImovelCondominioIdComContaNaoPreFaturada(Integer imovelId, Integer anoMesReferencia) throws ControladorException;

	public void atualizarNumeroMedidorEnergiaImovel(Integer matricula, String medidorEnergia) throws ControladorException;

	public Integer pesquisarImovelClientesEspeciaisRelatorioCount(String idUnidadeNegocio, String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String codigoSetorInicial, String codigoSetorFinal, String codigoRotaInicial, String codigoRotaFinal,
			String[] idsPerfilImovel, String[] idsCategoria, String[] idsSubcategoria, String idSituacaoAgua, String idSituacaoEsgoto,
			String qtdeEconomiasInicial, String qtdeEconomiasFinal, String intervaloConsumoAguaInicial, String intervaloConsumoAguaFinal,
			String intervaloConsumoEsgotoInicial, String intervaloConsumoEsgotoFinal, String idClienteResponsavel, String intervaloConsumoResponsavelInicial,
			String intervaloConsumoResponsavelFinal, Date dataInstalacaoHidrometroInicial, Date dataInstalacaoHidrometroFinal,
			String[] idsCapacidadesHidrometro, String[] idsTarifasConsumo, Integer anoMesFaturamento, String idLeituraAnormalidade, String leituraAnormalidade,
			String idConsumoAnormalidade, String consumoAnormalidade) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCobrancaSituacao(Integer idImovel, boolean temPermissaoEspecial) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterQuantidadeEconomiasContaCategoria(Integer idConta) throws ControladorException;

	public Object[] pesquisarLocalidadeCodigoSetorImovel(Integer idImovel) throws ControladorException;

	public void verificarIndicadorNomeConta(Integer idImovel) throws ControladorException;

	public boolean verificarImovelEmProcessoDeFaturamento(Integer idImovel) throws ControladorException;

	public ImovelPerfil pesquisarImovelPerfil(Integer idImovelPerfil) throws ControladorException;

	public FaturamentoGrupo[] verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(Integer idQuadraAnterior, Integer idQuadraAtual)
			throws ControladorException;

	public Collection<ImovelSubcategoriaHelper> consultaSubcategorias(int idImovel) throws ControladorException;

	public Boolean pesquisarExistenciaImovelPublico(Integer idCliente) throws ControladorException;

	public boolean verificaGeracaoDadosLeituraGrupoFaturamento(FaturamentoGrupo faturamentoGrupo) throws ControladorException;

	public ImovelInscricaoAlterada verificarDuplicidadeImovelInscricaoAlterada(Integer idLocalidade, Integer idSetorComercial, Integer idQuadra,
			Integer idQuadraFace, Integer subLote, Integer lote) throws ControladorException;

	public Municipio pesquisarMunicipioImovel(Integer idImovel) throws ControladorException;

	public Short consultarNumeroReparcelamentoConsecutivosImovel(Integer idImovel) throws ControladorException;

	public boolean verificaGeracaoDadosLeituraRota(FaturamentoGrupo faturamentoGrupo, Rota rota) throws ControladorException;

	public ImovelPerfil pesquisarImovelPerfilIdImovel(Integer idImovel) throws ControladorException;

	public boolean isFaturamentoAguaAtivo(Imovel imovel);

	public boolean isFaturamentoEsgotoAtivo(Imovel imovel);

	@SuppressWarnings("rawtypes")
	public Collection obterColecaoCategoriaOuSubcategoriaDoImovel(Imovel imovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarImovel(Integer idLocalidade, Integer idGerenciaRegional, Integer idUnidadeNegocio) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarImovelCadastro(Integer idLocalidade, Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer anoMesPesquisaInicial,
			Integer anoMesPesquisaFinal) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelEconomia(Integer idImovel) throws ControladorException;

	public TarifaSocialDadoEconomia pesquisarTarifaSocialDadoEconomia(Integer idImovel) throws ControladorException;

	public TarifaSocialCarta pesquisarTarifaSocialCarta(Integer idImovel, Integer codigoTipoCarta) throws ControladorException;

	public void atualizarTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta, Integer qtdeImoveis) throws ControladorException;

	public Integer pesquisarQuantidadeImoveisTarifaSocialCarta(Integer idTarifaSocialComandoCarta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarTarifaSocialCarta(Integer idTarifaSocialComandoCarta) throws ControladorException;

	public BigDecimal pesquisarValorContaTarifaSocialCartaDebito(Integer idTarifaSocialComandoCarta, Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarTarifaSocialComandoCarta(Integer codigoTipoCarta, String situacao, Integer numeroPagina) throws ControladorException;

	public Integer pesquisarQtdeTarifaSocialComandoCarta(Integer codigoTipoCarta, String situacao) throws ControladorException;

	public void removerComando(Integer idTarifaSocialComandoCarta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisTarifaSocialCarta(Integer idTarifaSocialComandoCarta, Integer codigoTipoCarta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasTarifaSocialCartaDebito(Integer idTarifaSocialComandoCarta, Integer idImovel) throws ControladorException;

	public void retirarImovelTarifaSocial(Integer motivoExclusao, Imovel imovel, String observacaoRetira) throws ControladorException;

	public void atualizarDataExecucaoTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisTarifaSocial(Integer idLocalidade) throws ControladorException;

	public TarifaSocialComandoCarta pesquisarTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarQtdeImoveisExcluidostarifaSocial(Integer codigoTipoCarta, Integer idGerencia, Integer idUnidade, Integer idLocalidade,
			Integer referenciaInicial, Integer refereciaFinal) throws ControladorException;

	public String pesquisarDescricaoMotivoCarta(Integer idCodigoMotivo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarQtdeTarifaSocialDadoEconomia(Integer idtarifaSocialExclusaoMotivo, Integer referenciaInicial, Integer refereciaFinal,
			Integer idGerencia, Integer idUnidade, Integer idLocalidade) throws ControladorException;

	public void removerCartasComando(Integer idTarifaSocialComandoCarta, Integer idLocalidade, Integer tipoCarta) throws ControladorException;

	public Integer pesquisarAnoMesExclusaoTarifaSocialImovel(Integer idImovel) throws ControladorException;

	public void retirarCobrancaImovelCobrancaPorEmpresa(Integer idImovel, Integer idCobrancaSituacao, Usuario usuario) throws ControladorException;

	public Collection<Integer[]> pesquisarIdsImoveis(String[] idsOrdemServico) throws ControladorException;

	public Integer pesquisarQuantidadeImoveisDoacoes(GerarRelatorioImoveisDoacoesHelper filtro) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisDoacoes(GerarRelatorioImoveisDoacoesHelper filtro) throws ControladorException;

	public Integer pesquisarImovelAreaComum(Integer idImovelCondominio) throws ControladorException;

	public Short verificarExistenciaDoImovel(Integer idImovel) throws ControladorException;

	public Integer pesquisarImovelCondominio(Integer idImovel) throws ControladorException;

	public void atualizarIndicadorImovelAreaComumDoImovel(Integer idImovel, Short indicadorImovelAreaComum) throws ControladorException;

	public Imovel pesquisarImovelSituacaoEspecialFaturamento(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Categoria obterPrincipalCategoria(Collection colecaoImovelSubCategorias) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public ImovelSubcategoria obterPrincipalSubcategoria(Integer idCategoria, Collection colecaoImovelSubCategorias) throws ControladorException;

	public Collection<ImovelRamoAtividade> pesquisarRamoAtividadeDoImovel(Integer idImovel) throws ControladorException;

	public void atualizarIdArquivoTextoImovelAtualizacaoCadastral(Integer idArquivoTexto, Integer idImovel) throws ControladorException;

	public Collection<ImovelSubcategoria> pesquisarImovelSubcategorias(Imovel imovel) throws ControladorException;

	public ImovelCobrancaSituacao obterImovelCobrancaSituacao(Integer idImovelSituacaoCobranca) throws ControladorException;
	
	public ContratoDTO obterContratoAdesao(int idContrato) throws ControladorException; 

	public void incluirImovelCobranca(Integer idCobrancaSituacao, Integer idCobrancaSituacaoTipo, Integer idImovel, Usuario usuario) throws ControladorException;

	public boolean isDataMudancaTitularidaRetroativaPermitida(Integer idImovel, Date data) throws ControladorException;
	
	public Short pesquisarObterQuantidadeEconomias(Imovel imovel, Categoria categoria) throws ControladorException;
	
	public boolean isImovelHidrometrado(Integer idImovel) throws ErroRepositorioException;
	
	public Cliente consultarClienteResponsavel(Imovel imovel) throws ControladorException;
	
	public String consultarNomeClienteResponsavelImovel(Integer idImovel) throws ControladorException;

	public List<Imovel> pesquisarCondominios(Rota rota) throws ControladorException;
	
	public ContratoDTO obterContratoInstalacaoReservacao(int idContrato) throws ControladorException;

	public void gerarContratoInstalacaoReservacao(Integer idImovel, Integer idCliente) throws ControladorException;
	
	public List<Imovel> pesquisarImoveisBolsaAgua(Rota rota) throws ControladorException;
	
	public Categoria obterCategoria(Integer idCategoria) throws ControladorException;

	public Collection pesquisarImoveisBolsaAguaPorClienteId(Integer idCliente) throws ControladorException;

	public boolean isImovelBolsaAgua(Integer idImovel) throws ControladorException;
	
	public boolean isContaBolsaAgua(Integer idConta) throws ControladorException;
	
    public List<Integer> pesquisarImovelBolsaAguaPorRota(Integer idRota) throws ControladorException;
    
    public List<Integer> pesquisarImovelElegivelBolsaAguaPorRota(Integer idRota) throws ControladorException;
	
	public void atualizarPerfilImovel(Integer idImovel, Integer idPerfil) throws ControladorException;
	
}
