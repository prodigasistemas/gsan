package gcom.cadastro.imovel;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.bean.ImovelSubcategoriaHelper;
import gcom.cadastro.tarifasocial.TarifaSocialCarta;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.bean.EmitirDocumentoCobrancaBoletimCadastroHelper;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.gui.faturamento.bean.FiltrarImovelInserirManterContaHelper;
import gcom.micromedicao.Rota;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.cadastro.GerarRelatorioImoveisDoacoesHelper;
import gcom.relatorio.cadastro.RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper;
import gcom.relatorio.micromedicao.FiltrarAnaliseExcecoesLeiturasHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ErroRepositorioException;

public interface IRepositorioImovel {

	public Imovel obterImovelPorId(Integer idImovel) throws ErroRepositorioException;

	public void atualizarImovelExecucaoOrdemServicoLigacaoAgua(Imovel imovel, LigacaoAguaSituacao situacaoAgua) throws ErroRepositorioException;

	public void atualizarImovelExecucaoOrdemServicoLigacaoEsgoto(Imovel imovel, LigacaoEsgotoSituacao situacaoEsgoto) throws ErroRepositorioException;

	public void inserirImovel(Imovel imovel) throws ErroRepositorioException;

	public void atualizarImovel(Imovel imovel) throws ErroRepositorioException;

	public void removerClienteImovelEconomia(Integer id) throws ErroRepositorioException;

	public void removerTodos(String objeto, String condicional, Integer id) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovel(Integer idLocalidade, Integer idSetorComercial, Integer idQuadra, Short lote, int indicadorExclusao)
			throws ErroRepositorioException;

	public void atualizarImovelInscricao(Imovel imovel) throws ErroRepositorioException;

	public void atualizarImovelSubCategoria(ImovelSubcategoria imovelSubcategoria) throws ErroRepositorioException;

	public Integer pesquisarObterQuantidadeEconomias(Imovel imovel) throws ErroRepositorioException;

	public Short pesquisarObterQuantidadeEconomias(Imovel imovel, Categoria categoria) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarObterQuantidadeEconomiasCategoria(Integer idImovel, Integer categoria) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterQuantidadeEconomiasCategoria(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterDescricoesCategoriaImovel(Imovel imovel) throws ErroRepositorioException;

	public Collection<Imovel> pesquisarImovelParametrosClienteImovel(FiltroClienteImovel filtroClienteImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelSituacaoEspecialFaturamento(String valor, SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelSituacaoEspecialCobranca(String valor, SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper)
			throws ErroRepositorioException;

	public Integer verificarExistenciaImovel(Integer idImovel) throws ErroRepositorioException;

	public Integer recuperarMatriculaImovel(Integer idImovel) throws ErroRepositorioException;

	public Integer validarMesAnoReferencia(SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void atualizarFaturamentoSituacaoTipo(Collection colecaoIdsImoveis, Integer idFaturamentoTipo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void retirarSituacaoEspecialFaturamento(Collection colecaoIdsImoveis) throws ErroRepositorioException;

	public Collection<Integer> pesquisarImoveisIds(Integer rotaId) throws ErroRepositorioException;

	public Object pesquisarImovelIdComConta(Integer imovelId, Integer anoMesReferencia) throws ErroRepositorioException;

	public Integer validarMesAnoReferenciaCobranca(SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void atualizarCobrancaSituacaoTipo(Collection colecaoIdsImoveis, Integer idCobrancaTipo) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public void retirarSituacaoEspecialCobranca(Collection colecaoIdsImoveis) throws ErroRepositorioException;

	public Integer obterIndicadorExistenciaHidrometroImovel(Integer idImovel) throws ErroRepositorioException;

	public void atualizarImovelLigacaoAgua(Imovel imovel, Integer idLigacaoAguaSituacao) throws ErroRepositorioException;

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
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarSubcategoriasImovelRelatorio(Integer idImovel) throws ErroRepositorioException;

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
			String cdRotaInicial, String cdRotaFinal, String sequencialRotaInicial, String sequencialRotaFinal) throws ErroRepositorioException;

	public Object[] pesquisarInscricaoImovel(Integer idImovel) throws ErroRepositorioException;

	public Boolean confirmarImovelExcluido(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection gerarRelatorioDadosEconomiasImovelOutrosCriterios(String idImovelCondominio, String idImovelPrincipal, String idSituacaoLigacaoAgua,
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
			String numeroMoradoresInicial, String numeroMoradoresFinal, String idAreaConstruidaFaixa, String idUnidadeNegocio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection gerarRelatorioDadosEconomiasImovelSubcategoria(String idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection gerarRelatorioDadosEconomiasImovelEconomia(String idImovel, String idSubCategoria) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection gerarRelatorioDadosEconomiasImovelClienteEconomia(String idImovelEconomia) throws ErroRepositorioException;

	public String montarInnerJoinFiltrarImoveisOutrosCriterios(

	String intervaloMediaMinimaImovelInicial, String intervaloMediaMinimaImovelFinal, String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String idCadastroOcorrencia, String idConsumoTarifa, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria, String idCliente, String idClienteTipo, String idClienteRelacaoTipo, Integer relatorio, String cpfCnpj);

	public Collection<EntidadeBeneficente> pesquisarEntidadeBeneficente(FiltroEntidadeBeneficente filtroEntidadeBeneficente) throws ErroRepositorioException;

	public Collection<ImovelDoacao> pesquisarImovelDoacao(FiltroImovelDoacao filtroImovelDoacao) throws ErroRepositorioException;

	public void atualizarImovelDoacao(ImovelDoacao imovelDoacao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelRegistroAtendimento(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarImovelDadosCadastrais(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClientesImovel(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClientesImovelDataMax(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClientesImovelExcluidoOuNao(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCategoriasImovel(Integer idImovel) throws ErroRepositorioException;

	public ImovelPerfil obterImovelPerfil(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarImovelDadosComplementares(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarVencimentoAlternativoImovel(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitosAutomaticosImovel(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarFaturamentosSituacaoHistorico(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCobrancasSituacaoHistorico(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarImovelAnaliseMedicaoConsumo(Integer idImovel) throws ErroRepositorioException;

	public void atualizarImovelLeituraAnormalidade(Map<Integer, MedicaoHistorico> mapAtualizarLeituraAnormalidadeImovel, Date dataAtual)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarImovelHistoricoFaturamento(Integer idImovel) throws ErroRepositorioException;

	public String consultarNomeClienteImovel(Integer idImovel, Short relacaoTipo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarContasImovel(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarContasHistoricosImovel(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarParcelamentosDebitosImovel(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteUsuarioImovel(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteUsuarioImovelExcluidoOuNao(Integer idImovel) throws ErroRepositorioException;

	public void atualizarDadosImovel(Integer codigoImovel, Integer numeroParcelamento, Integer numeroReparcelamentoConsecutivo, Integer numeroReparcelamento)
			throws ErroRepositorioException;

	public Collection<Categoria> pesquisarCategoriasImovel(Imovel imovel) throws ErroRepositorioException;

	public void atualizarImovelLigacaoAguaEsgoto(Integer idImovel, Integer idLigacaoAguaSituacao, Integer idLigacaoEsgotoSituacao)
			throws ErroRepositorioException;

	public void atualizarLigacaoAgua(Integer idLigacaoAgua, Integer consumoMinimo, short indiacadorConsumoFixado) throws ErroRepositorioException;

	public void atualizarLigacaoEsgoto(Integer idLigacaoEsgoto, Integer consumoMinimo, short indicadorVolumeFixado) throws ErroRepositorioException;

	public Integer pesquisarConsumoTarifa(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovel(String idImovel, String idLocalidade, String codigoSetorComercial, String numeroQuadra, String lote, String subLote,
			String codigoCliente, String idMunicipio, String cep, String idBairro, String idLogradouro, String numeroImovelInicial, String numeroImovelFinal,
			boolean pesquisarImovelManterVinculo, boolean pesquisarImovelCondominio, Integer numeroPagina) throws ErroRepositorioException;

	public Object pesquisarQuantidadeImovel(String idImovel, String idLocalidade, String codigoSetorComercial, String numeroQuadra, String lote,
			String subLote, String codigoCliente, String idMunicipio, String cep, String idBairro, String idLogradouro, String numeroImovelInicial,
			String numeroImovelFinal, boolean pesquisarImovelManterVinculo, boolean pesquisarImovelCondominio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelInscricao(String idImovel, String idLocalidade, String codigoSetorComercial, String numeroQuadra, String lote,
			String subLote, String codigoCliente, String idMunicipio, String cep, String idBairro, String idLogradouro, boolean pesquisarImovelCondominio,
			Integer numeroPagina) throws ErroRepositorioException;

	public Integer pesquisaridLigacaoEsgotoSituacao(Integer idImovel) throws ErroRepositorioException;

	public Integer pesquisaridLigacaoAguaSituacao(Integer idImovel) throws ErroRepositorioException;

	public Integer pesquisarTarifaImovel(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisClientesRelacao(Cliente cliente, ClienteRelacaoTipo relacaoClienteImovel, Integer numeroInicial)
			throws ErroRepositorioException;

	public Integer pesquisarExistenciaImovelSubCategoria(Integer idImovel, Integer idCategoria) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisPorRota(Integer idRota) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisTarifaConsumo(String idLocalidadeInicial, String idLocalidadeFinal, String codigoSetorComercialInicial,
			String codigoSetorComercialFinal, String quadraInicial, String quadraFinal, String loteInicial, String loteFinal, String subLoteInicial,
			String subLoteFinal, String idTarifaAnterior) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void atualizarImoveisTarifaConsumo(String matricula, String tarifaAtual, Collection colecaoImoveis) throws ErroRepositorioException;

	public void atualizarImovelPerfilNormal(Integer idImovel) throws ErroRepositorioException;

	public Imovel pesquisarImovelComSituacaoAguaEsgoto(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisPorRotaComPaginacaoSemRotaAlternativa(Rota rota, Collection idsSituacaoLigacaoAgua, Collection idsSituacaoLigacaoEsgoto,
			Integer numeroInicial, int numeroMaximo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisPorRotaComPaginacaoComRotaAlternativa(Rota rota, Collection idsSituacaoLigacaoAgua, Collection idsSituacaoLigacaoEsgoto,
			Integer numeroInicial, int numeroMaximo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelInscricaoNew(String idImovel, String idLocalidade, String codigoSetorComercial, String numeroQuadra, String lote,
			String subLote, String codigoCliente, String idMunicipio, String cep, String idBairro, String idLogradouro, String numeroImovelInicial,
			String numeroImovelFinal, boolean pesquisarImovelCondominio, Integer numeroPagina) throws ErroRepositorioException;

	public Integer verificarNumeroIptu(String numeroIptu, Integer idImovel, Integer idImovelEconomia, Integer idMunicipio) throws ErroRepositorioException;

	public Integer verificarNumeroCompanhiaEletrica(Long numeroCompanhiaEletrica, Integer idImovel, Integer idImovelEconomia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterQuantidadeEconomiasSubCategoria(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelSubcategorias(Integer idImovel) throws ErroRepositorioException;

	public Imovel pesquisarImovel(Integer idImovel) throws ErroRepositorioException;
	
	public Collection<Imovel> pesquisarImoveis(Collection<Integer> idsImoveis) throws ErroRepositorioException;

	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException;

	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo) throws ErroRepositorioException;

	public Short obterIndicadorGeracaoAcrescimosClienteImovel(Integer idImovel) throws ErroRepositorioException;

	public Integer obterConsumoMedioNaoMedidoImovel(Imovel imovel) throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentoPeloId(Integer idPagamento) throws ErroRepositorioException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoPeloId(Integer idPagamentoHistorico) throws ErroRepositorioException;

	public String obterSituacaoCobrancaImovel(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarColecaoImovel(FiltrarImovelInserirManterContaHelper filtro) throws ErroRepositorioException;

	public Integer pesquisarColecaoImovelPerfilBloqueado(FiltrarImovelInserirManterContaHelper filtro) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarColecaoImovelCliente(Integer codigoCliente, Integer relacaoTipo, Boolean verificarImovelPerfilBloqueado)
			throws ErroRepositorioException;

	public Integer pesquisarColecaoImovelClienteBloqueadoPerfil(Integer codigoCliente, Integer relacaoTipo) throws ErroRepositorioException;

	public Integer pesquisarContaMotivoRevisao(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterQuantidadeEconomiasCategoriaPorSubcategoria(Integer conta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterSubCategoriasPorCategoria(Integer idCategoria, Integer idImovel) throws ErroRepositorioException;

	public Collection<Integer> pesquisarTodosIdsPerfilImovel() throws ErroRepositorioException;

	public Cliente consultarClienteImovel(Imovel imovel, Short relacaoTipo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelClientesEspeciaisRelatorio(String idUnidadeNegocio, String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String codigoSetorInicial, String codigoSetorFinal, String codigoRotaInicial, String codigoRotaFinal,
			String[] idsPerfilImovel, String[] idsCategoria, String[] idsSubcategoria, String idSituacaoAgua, String idSituacaoEsgoto,
			String qtdeEconomiasInicial, String qtdeEconomiasFinal, String intervaloConsumoAguaInicial, String intervaloConsumoAguaFinal,
			String intervaloConsumoEsgotoInicial, String intervaloConsumoEsgotoFinal, String idClienteResponsavel, String intervaloConsumoResponsavelInicial,
			String intervaloConsumoResponsavelFinal, Date dataInstalacaoHidrometroInicial, Date dataInstalacaoHidrometroFinal,
			String[] idsCapacidadesHidrometro, String[] idsTarifasConsumo, Integer anoMesFaturamento, String idLeituraAnormalidade, String leituraAnormalidade,
			String idConsumoAnormalidade, String consumoAnormalidade) throws ErroRepositorioException;

	public LigacaoEsgotoSituacao pesquisarLigacaoEsgotoSituacao(Integer idImovel) throws ErroRepositorioException;

	public LigacaoAguaSituacao pesquisarLigacaoAguaSituacao(Integer idImovel) throws ErroRepositorioException;

	public FaturamentoGrupo pesquisarGrupoImovel(Integer idImovel) throws ErroRepositorioException;

	public String obterDescricaoSubcategoria(Integer idSubcategoria) throws ErroRepositorioException;

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
			String seqRotaFinal, String rotaInicial, String rotaFinal, String ordenacaoRelatorio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection gerarRelatorioCadastroConsumidoreInscricao(String idImovelCondominio, String idImovelPrincipal, String idSituacaoLigacaoAgua,
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
			throws ErroRepositorioException;

	public Object[] obterDescricaoIdCliente(Integer idImovel) throws ErroRepositorioException;

	public String pesquisarNomeAgenteArrecadador(Integer idPagamentoHistorico) throws ErroRepositorioException;

	public String pesquisarCaracterRetorno(Integer idConteudoRegistro) throws ErroRepositorioException;

	public Object[] obterSubCategoriasComCodigoGrupoPorCategoria(Integer idCategoria, Integer idImovel) throws ErroRepositorioException;

	public Object[] pesquisarLocalidadeSetorImovel(Integer idImovel) throws ErroRepositorioException;

	public Integer pesquisarSequencialRota(Integer idImovel) throws ErroRepositorioException;

	public Cep pesquisarCepImovel(Imovel imovel) throws ErroRepositorioException;

	public Collection<EmitirDocumentoCobrancaBoletimCadastroHelper> pesquisarBoletimCadastro(String idImovelCondominio, String idImovelPrincipal,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua, String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto, String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal, String intervaloMediaMinimaImovelInicial, String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial, String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil, String idPocoTipo,
			String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca, String idEloAnormalidade,
			String areaConstruidaInicial, String areaConstruidaFinal, String idCadastroOcorrencia, String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal, String setorComercialInicial, String setorComercialFinal, String quadraInicial,
			String quadraFinal, String loteOrigem, String loteDestno, String cep, String logradouro, String bairro, String municipio, String idTipoMedicao,
			String indicadorMedicao, String idSubCategoria, String idCategoria, String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo, String idClienteRelacaoTipo, String numeroPontosInicial, String numeroPontosFinal,
			String numeroMoradoresInicial, String numeroMoradoresFinal, String idAreaConstruidaFaixa, String idUnidadeNegocio,
			int quantidadeCobrancaDocumentoInicio, String indicadorCpfCnpj, String cpfCnpj) throws ErroRepositorioException;

	public CobrancaSituacao pesquisarImovelCobrancaSituacao(Integer idImovel) throws ErroRepositorioException;

	public Imovel pesquisarDadosImovel(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarInscricaoImoveleRota(String idsImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisClientesRelacao(Collection idsCliente, Integer numeroInicial) throws ErroRepositorioException;

	public Empresa buscarEmpresaPorMatriculaImovel(Integer imovel) throws ErroRepositorioException;

	public void atualizarSituacaoCobrancaImovel(Integer idSituacaoCobranca, Integer idImovel) throws ErroRepositorioException;

	public Object pesquisarQuantidadeImovelInscricao(String idImovel, String idLocalidade, String codigoSetorComercial, String numeroQuadra, String lote,
			String subLote, String codigoCliente, String idMunicipio, String cep, String idBairro, String idLogradouro, String numeroImovelInicial,
			String numeroImovelFinal, boolean pesquisarImovelCondominio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelSubcategoriasMaxCinco(Integer idImovel) throws ErroRepositorioException;

	public Object[] pesquisarAreaConstruida(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarInscricaoImoveleRota(Collection colecaoIdsImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarInscricaoImoveleRota(FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper, Integer anoMes)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelPerfilDiferenteCorporativo() throws ErroRepositorioException;

	public Integer obterIdEsferaPoder(Integer idImovel) throws ErroRepositorioException;

	public Integer obterIdCategoriaPrincipal(Integer idImovel, boolean ordemDecrescente) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelPerfilTarifaSocialDiferenteCorporativo() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public List pesquisarLocalidadeComImoveisComSituacaoLigadoEmAnalise() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisComSituacaoLigadoEmAnalise(Integer idLocalidade) throws ErroRepositorioException;

	public void atualizarSituacaoAguaPorImovel(String id, String situacaoAguaLigado) throws ErroRepositorioException;

	public Object pesquisarImovelIdComContaNaoPreFaturada(Integer imovelId, Integer anoMesReferencia) throws ErroRepositorioException;

	public Integer obterQuadraPerfil(Integer idImovel) throws ErroRepositorioException;

	public void atualizarImovelSituacaoAtualizacaoCadastral(Integer idImovel, Integer idSituacaoAtualizacaoCadastral) throws ErroRepositorioException;

	public void atualizarImovelAtualizacaoCadastralSituacaoAtualizacaoCadastral(Integer idImovel, Integer idSituacaoAtualizacaoCadastral, Integer idEmpresa)
			throws ErroRepositorioException;

	public Integer verificaExistenciaImovelAtualizacaoCadastral(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelEconomia(Integer idImovel, Integer idSubcategoria) throws ErroRepositorioException;

	public ImovelAtualizacaoCadastral pesquisarImovelAtualizacaoCadastralInscricao(Integer idImovel, Integer idEmpresa) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterImoveisAtualizacaoCadastral(Integer idLocalidade, String codigoSetor, Integer quadraInicial, Integer quadraFinal, Integer idEmpresa,
			Integer idRota) throws ErroRepositorioException;

	public Boolean pesquisarExistenciaImovelEconomia(Integer idImovel, Integer idSubcategoria) throws ErroRepositorioException;

	public Boolean verificarExistenciaTarifaConsumoCategoriaParaCategoriaImovel(Integer idImovel, Integer idCategoria) throws ErroRepositorioException;

	public void removerImovelSubcategoria(Integer idImovel) throws ErroRepositorioException;

	public void removerImovelRamoAtividade(Integer idImovel) throws ErroRepositorioException;

	public Integer pesquisarMaiorNumeroLoteDaQuadra(Integer idQuadra) throws ErroRepositorioException;

	public Object[] consultarDadosClienteUsuarioImovel(String idImovel) throws ErroRepositorioException;

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
			String cdRotaFinal, String sequencialRotaInicial, String sequencialRotaFinal, String[] pocoTipoListIds) throws ErroRepositorioException;

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
			String rotaFinal, String ordenacaoRelatorio, String seqRotaInicial, String seqRotaFinal) throws ErroRepositorioException;

	public Object[] pesquisarInscricaoImovelExcluidoOuNao(Integer idImovel) throws ErroRepositorioException;

	public void atualizarSituacaoCobrancaETipoIdsImoveis(Integer idSituacaoCobranca, Integer idSituacaoCobrancaTipo, Collection<Integer> idsImovel)
			throws ErroRepositorioException;

	public Boolean verificarImovelPerfilBloqueado(Integer idImovel) throws ErroRepositorioException;

	public Date pesquisarUltimaAlteracaoImovel(Integer idImovel) throws ErroRepositorioException;

	public Integer pesquisarImovelComNumeroMedidorEnergiaImovel(String numeroMedidorEnergia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCategoriaSubcategoriaImovel(Integer idImovel) throws ErroRepositorioException;

	public Integer obterConsumoFaturadoImovelNoMes(Integer idImovel, Integer mesAnoReferencia) throws ErroRepositorioException;

	public ImovelPerfil recuperaPerfilImovel(Integer idImovel) throws ErroRepositorioException;

	public Object pesquisarImovelCondominioIdComContaNaoPreFaturada(Integer imovelId,

	Integer anoMesReferencia) throws ErroRepositorioException;

	public void atualizarImovelLeituraAnormalidadeProcessoMOBILE(Imovel imovel) throws ErroRepositorioException;

	public void atualizarDataRetiradaImovelSituacaoCobranca(Integer idImovelSituacaoCobranca, Date dataRetirada) throws ErroRepositorioException;

	public void atualizarSituacaoCobrancaImovel(Integer idSituacaoCobrancaNova, Integer idSituacaoCobrancaBanco, Integer idImovel)
			throws ErroRepositorioException;

	public void atualizarNumeroMedidorEnergiaImovel(Integer matricula, String medidorEnergia) throws ErroRepositorioException;

	public Integer pesquisarImovelClientesEspeciaisRelatorioCount(String idUnidadeNegocio, String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String codigoSetorInicial, String codigoSetorFinal, String codigoRotaInicial, String codigoRotaFinal,
			String[] idsPerfilImovel, String[] idsCategoria, String[] idsSubcategoria, String idSituacaoAgua, String idSituacaoEsgoto,
			String qtdeEconomiasInicial, String qtdeEconomiasFinal, String intervaloConsumoAguaInicial, String intervaloConsumoAguaFinal,
			String intervaloConsumoEsgotoInicial, String intervaloConsumoEsgotoFinal, String idClienteResponsavel, String intervaloConsumoResponsavelInicial,
			String intervaloConsumoResponsavelFinal, Date dataInstalacaoHidrometroInicial, Date dataInstalacaoHidrometroFinal,
			String[] idsCapacidadesHidrometro, String[] idsTarifasConsumo, Integer anoMesFaturamento, String idLeituraAnormalidade, String leituraAnormalidade,
			String idConsumoAnormalidade, String consumoAnormalidade) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelCobrancaSituacaoPorImovel(String[] idsImovelCobrancaSituacao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCobrancaSituacao(Integer idImovel, boolean temPermissaoEspecial) throws ErroRepositorioException;

	public Object[] pesquisarLocalidadeCodigoSetorImovel(Integer idImovel) throws ErroRepositorioException;

	public void atualizarClienteImovelUsuario(Integer idImovel) throws ErroRepositorioException;

	public Integer pesquisarDebitoTipoImovelDoacao(Integer idImovel) throws ErroRepositorioException;

	public ImovelPerfil pesquisarImovelPerfil(Integer idImovelPerfil) throws ErroRepositorioException;

	public Collection<ImovelSubcategoriaHelper> consultaSubcategorias(int idImovel) throws ErroRepositorioException;

	public FaturamentoGrupo[] verificaInscricaoAlteradaAcarretaMudancaDoGrupoFaturamento(Integer idQuadraAnterior, Integer idQuadraAtual)
			throws ErroRepositorioException;

	public boolean verificaGeracaoDadosLeituraGrupoFaturamento(FaturamentoGrupo faturamentoGrupo) throws ErroRepositorioException;

	public Integer obterQuantidadeImoveisPublicosAssociadoAoCliente(Integer idCliente) throws ErroRepositorioException;

	public ImovelInscricaoAlterada verificarDuplicidadeImovelInscricaoAlterada(Integer idLocalidade, Integer idSetorComercial, Integer idQuadra,
			Integer idQuadraFace, Integer subLote, Integer lote) throws ErroRepositorioException;

	public Municipio pesquisarMunicipioImovel(Integer idImovel) throws ErroRepositorioException;

	public Short consultarNumeroReparcelamentoConsecutivosImovel(Integer idImovel) throws ErroRepositorioException;

	public Integer pesquisaIdQuadraImovel(Integer idImovel) throws ErroRepositorioException;

	public boolean verificaGeracaoDadosLeituraRota(FaturamentoGrupo faturamentoGrupo, Rota rota) throws ErroRepositorioException;

	public ImovelPerfil pesquisarImovelPerfilIdImovel(Integer idImovel) throws ErroRepositorioException;

	public Collection<Imovel> consultarImovelLocalidade(Integer idLocalidade) throws ErroRepositorioException;

	public void atualizarImovelPerfil(Integer idImovel, Integer idPerfil) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarImovel(Integer idLocalidade, Integer idGerenciaRegional, Integer idUnidadeNegocio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarImovelCadastro(Integer idLocalidade, Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer anoMesPesquisaInicial,
			Integer anoMesPesquisaFinal) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelEconomia(Integer idImovel) throws ErroRepositorioException;

	public TarifaSocialDadoEconomia pesquisarTarifaSocialDadoEconomia(Integer idImovel) throws ErroRepositorioException;

	public TarifaSocialCarta pesquisarTarifaSocialCarta(Integer idImovel, Integer codigoTipoCarta) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeImoveisTarifaSocialCarta(Integer idTarifaSocialComandoCarta) throws ErroRepositorioException;

	public void atualizarTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta, Integer qtdeImoveis) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarTarifaSocialCarta(Integer idTarifaSocialComandoCarta) throws ErroRepositorioException;

	public BigDecimal pesquisarValorContaTarifaSocialCartaDebito(Integer idTarifaSocialComandoCarta, Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarTarifaSocialComandoCarta(Integer codigoTipoCarta, String situacao, Integer numeroPagina) throws ErroRepositorioException;

	public Integer pesquisarQtdeTarifaSocialComandoCarta(Integer codigoTipoCarta, String situacao) throws ErroRepositorioException;

	public void removerComando(Integer idTarifaSocialComandoCarta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisTarifaSocialCarta(Integer idTarifaSocialComandoCarta, Integer codigoTipoCarta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasTarifaSocialCartaDebito(Integer idTarifaSocialComandoCarta, Integer idImovel) throws ErroRepositorioException;

	public void retirarImovelTarifaSocial(Integer motivoExclusao, Integer idImovel) throws ErroRepositorioException;

	public void atualizarDataExecucaoTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisTarifaSocial(Integer idLocalidade) throws ErroRepositorioException;

	public TarifaSocialComandoCarta pesquisarTarifaSocialComandoCarta(Integer idTarifaSocialComandoCarta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarQtdeImoveisExcluidostarifaSocial(Integer codigoTipoCarta, Integer idGerencia, Integer idUnidade, Integer idLocalidade,
			Integer referenciaInicial, Integer refereciaFinal) throws ErroRepositorioException;

	public Integer pesquisarQtdeImoveisExcluidostarifaSocial(Integer referenciaInicial, Integer refereciaFinal, Integer codigoTipoCarta,
			RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper helper) throws ErroRepositorioException;

	public String pesquisarDescricaoMotivoCarta(Integer idCodigoMotivo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarQtdeTarifaSocialDadoEconomia(Integer idtarifaSocialExclusaoMotivo, Integer referenciaInicial, Integer refereciaFinal,
			Integer idGerencia, Integer idUnidade, Integer idLocalidade) throws ErroRepositorioException;

	public void atualizarDadosFaturamentoSituacaoHistorico(Integer idImovel, Integer referenciaFaturamentoGrupo, String observacaoRetira)
			throws ErroRepositorioException;

	public void atualizarDadosImovel(Integer idImovel, Integer referencia) throws ErroRepositorioException;

	public void removerCartasComando(Integer idTarifaSocialComandoCarta, Integer idLocalidade, Integer tipoCarta) throws ErroRepositorioException;

	public Integer pesquisarAnoMesExclusaoTarifaSocialImovel(Integer idImovel) throws ErroRepositorioException;

	public void retirarCobrancaImovelCobrancaPorEmpresa(Integer idImovel, Integer idCobrancaSituacao, Usuario usuario) throws ErroRepositorioException;

	public Collection<Integer[]> pesquisarIdsImoveis(String[] idsOrdemServico) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeImoveisDoacoes(GerarRelatorioImoveisDoacoesHelper filtro) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisDoacoes(GerarRelatorioImoveisDoacoesHelper filtro) throws ErroRepositorioException;

	public Short pesquisarPontosUtilizacaoImovel(Integer idImovel) throws ErroRepositorioException;

	public Short pesquisarNumeroMoradoresImovel(Integer idImovel) throws ErroRepositorioException;

	public void atualizarSituacaoEspecialFaturamentoImovel(Integer idImovel, Integer idFaturamentoSituacaoTipo, Integer idFaturamentoSituacaoMotivo)
			throws ErroRepositorioException;

	public Integer pesquisarImovelAreaComum(Integer idImovelCondominio) throws ErroRepositorioException;

	public Short verificarExistenciaDoImovel(Integer idImovel) throws ErroRepositorioException;

	public Integer pesquisarImovelCondominio(Integer idImovel) throws ErroRepositorioException;

	public void atualizarIndicadorImovelAreaComumDoImovel(Integer idImovel, Short indicadorImovelAreaComum) throws ErroRepositorioException;

	public Imovel pesquisarImovelSituacaoEspecialFaturamento(Integer idImovel) throws ErroRepositorioException;

	public Collection<ImovelRamoAtividade> pesquisarRamoAtividadeDoImovel(Integer idImovel) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdImoveisAprovados() throws ErroRepositorioException;

	public LogradouroTipo pesquisarTipoLogradouro(Integer idTipoLogradouro) throws ErroRepositorioException;

	public Logradouro pesquisarLogradouro(Integer codigoLogradouro) throws ErroRepositorioException;

	public Integer pesquisarLogradouroImovelAtualizacaoCadastral(Integer matriculaImovel) throws ErroRepositorioException;

	public ImovelCobrancaSituacao obterImovelCobrancaSituacao(Integer idImovelSituacaoCobranca) throws ErroRepositorioException;

	public DebitoAutomatico pesquisarDebitoAutomaticoAtivoImovel(Integer idImovel) throws ErroRepositorioException;

	public void incluirImovelCobranca(Integer idCobrancaSituacao, Integer idCobrancaSituacaoTipo, Integer idImovel) throws ErroRepositorioException;
	
	public List<Imovel> pesquisarCondominios(Rota rota) throws ErroRepositorioException;
	
	public List<Imovel> pesquisarImoveisBolsaAgua(Rota rota) throws ErroRepositorioException;
	
	public Collection pesquisarImoveisBolsaAguaPorClienteId(Integer idCliente) throws ErroRepositorioException;
	
	public Imovel consultarImovelBolsaAgua(Integer idImovel) throws ErroRepositorioException;
	
	public List<Integer> pesquisarImovelBolsaAguaPorRota(Integer idRota) throws ErroRepositorioException;
	
	public List<Integer> pesquisarImovelElegivelBolsaAguaPorRota(Integer idRota) throws ErroRepositorioException;
	
	public void atualizarPerfilImovel(Integer idImovel, Integer idPerfil) throws ErroRepositorioException;
	
}
