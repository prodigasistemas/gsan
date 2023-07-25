package gcom.cadastro;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastral;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificado;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoBinario;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoCritica;
import gcom.cadastro.atualizacaocadastralsimplificado.AtualizacaoCadastralSimplificadoLinha;
import gcom.cadastro.cliente.CadastroAguaPara;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.geografico.MunicipioFeriado;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelInscricaoAlterada;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.ImovelGeracaoTabelasTemporariasCadastroHelper;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.QuadraFace;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaAlteracaoHistorico;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.sistemaparametro.bean.DadosEnvioEmailHelper;
import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.relatorio.cadastro.FiltrarRelatorioAcessoSPCHelper;
import gcom.gui.relatorio.cadastro.GerarRelatorioAlteracoesCpfCnpjHelper;
import gcom.gui.relatorio.cadastro.micromedicao.FiltrarRelatorioColetaMedidorEnergiaHelper;
import gcom.gui.relatorio.seguranca.GerarRelatorioAlteracoesSistemaColunaHelper;
import gcom.micromedicao.ArquivoTextoLigacoesHidrometroHelper;
import gcom.micromedicao.Rota;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.relatorio.cadastro.GerarRelatorioAtualizacaoCadastralViaInternetHelper;
import gcom.relatorio.cadastro.RelatorioAcessoSPCBean;
import gcom.relatorio.cadastro.RelatorioBoletimCadastroIndividualBean;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisAtivosNaoMedidosHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisTipoConsumoHelper;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisUltimosConsumosAguaHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisAtivosNaoMedidosHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisFaturasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisProgramasEspeciaisHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisSituacaoLigacaoAguaHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisTipoConsumoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisUltimosConsumosAguaHelper;
import gcom.relatorio.cadastro.micromedicao.RelatorioColetaMedidorEnergiaHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

public interface IControladorCadastro {

	public Integer inserirHistoricoAlteracaoSistema(SistemaAlteracaoHistorico sistemaAlteracaoHistorico) throws ControladorException;

	public Integer inserirGerenciaRegional(GerenciaRegional gerenciaRegional) throws ControladorException;

	public void atualizarGerenciaRegional(GerenciaRegional gerenciaRegional) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarIdsEmpresa() throws ControladorException;

	public void informarParametrosSistema(SistemaParametro sistemaParametro, Usuario usuarioLogado) throws ControladorException;

	public Integer inserirFeriado(NacionalFeriado nacionalFeriado, MunicipioFeriado municipioFeriado, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarFeriado(Short tipoFeriado, String descricao, Date dataFeriado, Date dataFeriadoFim, Integer idMunicipio,
			Integer numeroPagina) throws ControladorException;

	public Integer pesquisarFeriadoCount(Short tipoFeriado, String descricao, Date dataFeriadoInicio, Date dataFeriadoFim, Integer idMunicipio)
			throws ControladorException;

	public void atualizarFeriado(NacionalFeriado nacionalFeriado, MunicipioFeriado municipioFeriado, Usuario usuarioLogado)
			throws ControladorException;

	public void removerFeriado(String[] ids, Usuario usuarioLogado) throws ControladorException;

	public Collection<Integer> pesquisarIdsSetorComercial(Integer idLocalidade) throws ControladorException;

	public void atualizarMensagemSistema(SistemaParametro sistemaParametro, Usuario usuarioLogado) throws ControladorException;

	public EnvioEmail pesquisarEnvioEmail(Integer idEnvioEmail) throws ControladorException;

	public DadosEnvioEmailHelper pesquisarDadosEmailSistemaParametros() throws ControladorException;

	public void inserirFuncionario(Funcionario funcionario, Usuario usuarioLogado) throws ControladorException;

	public void atualizarFuncionario(Funcionario funcionario, Usuario usuarioLogado) throws ControladorException;

	public Collection<Integer> pesquisarTodosIdsSetorComercial() throws ControladorException; 
	
	public Collection pesquisarTodosIdsDmc() throws ControladorException;

	public void emitirBoletimCadastro(
			CobrancaAcaoAtividadeCronograma cronogramaAtividadeAcaoCobranca,
			CobrancaAcaoAtividadeComando comandoAtividadeAcaoCobranca,
			Date dataAtualPesquisa, CobrancaAcao cobrancaAcao,
			int idFuncionalidadeIniciada) throws ControladorException;

	public Integer inserirClienteTipo(ClienteTipo clienteTipo, Usuario usuarioLogado) throws ControladorException;

	public void atualizarClienteTipo(ClienteTipo clienteTipo) throws ControladorException;

	public byte[] emitirBoletimCadastro(String idImovelCondominio,
			String idImovelPrincipal, String idSituacaoLigacaoAgua,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua,
			String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,
			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil,
			String idPocoTipo, String idFaturamentoSituacaoTipo,
			String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial,
			String areaConstruidaFinal, String idCadastroOcorrencia,
			String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal,
			String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro,
			String municipio, String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			String idUnidadeNegocio, String indicadorCpfCnpj, String cpfCnpj)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
 	public Collection pesquisarClientesSubordinados(Integer idCliente) throws ControladorException;

	public Collection<RelatorioImoveisSituacaoLigacaoAguaHelper> pesquisarRelatorioImoveisSituacaoLigacaoAgua(
			FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro)
			throws ControladorException;

	public Integer pesquisarTotalRegistroRelatorioImoveisSituacaoLigacaoAgua(FiltrarRelatorioImoveisSituacaoLigacaoAguaHelper filtro) throws ControladorException;

	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasLocalizacao(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro) 
					throws ControladorException;

	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoAgrupadasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ControladorException;

	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoLocalizacao(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ControladorException;

	public Integer pesquisarTotalRegistroRelatorioImoveisFaturasAtrasoCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ControladorException;

	public Collection<RelatorioImoveisConsumoMedioHelper> pesquisarRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro)
			throws ControladorException;

	public Integer pesquisarTotalRegistroRelatorioImoveisConsumoMedio(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro)
			throws ControladorException;

	public Collection<RelatorioImoveisUltimosConsumosAguaHelper> pesquisarRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro)
			throws ControladorException;

	public Integer pesquisarTotalRegistroRelatorioImoveisUltimosConsumosAgua(
			FiltrarRelatorioImoveisUltimosConsumosAguaHelper filtro)
			throws ControladorException;

	public Collection<RelatorioImoveisAtivosNaoMedidosHelper> pesquisarRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro)
			throws ControladorException;

	public Integer pesquisarTotalRegistroRelatorioImoveisAtivosNaoMedidos(
			FiltrarRelatorioImoveisAtivosNaoMedidosHelper filtro)
			throws ControladorException;

	public Collection<RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper> pesquisarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro)
			throws ControladorException;

	public Integer pesquisarTotalRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(
			FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro)
			throws ControladorException;

	public Collection<RelatorioImoveisTipoConsumoHelper> pesquisarRelatorioImoveisTipoConsumo(
			FiltrarRelatorioImoveisTipoConsumoHelper filtro)
			throws ControladorException;

	public Integer pesquisarTotalRegistroRelatorioImoveisTipoConsumo(
			FiltrarRelatorioImoveisTipoConsumoHelper filtro)
			throws ControladorException;

	public void gerarArquivoTextoDadosCadastrais(
			ArquivoTextoDadosCadastraisHelper arquivoTextoDadosCadastraisHelper)
			throws ControladorException;

	public Collection<HidrometroInstalacaoHistorico> recuperaImoveisArquivoTextoLigacoesHidrometro(
			ArquivoTextoLigacoesHidrometroHelper arquivoTextoLigacoesHidrometroHelper)
			throws ControladorException;

	public Collection<Integer> pesquisarIdsCodigosSetorComercial()
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarSetorComercialPorQualidadeAgua(
			int tipoArgumento, BigDecimal indiceInicial,
			BigDecimal indiceFinal, Integer anoMesReferencia)
			throws ControladorException;

	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoDescritasLocalizacao(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ControladorException;

	public Collection<RelatorioImoveisFaturasAtrasoHelper> pesquisarRelatorioImoveisFaturasAtrasoDescritasCliente(
			FiltrarRelatorioImoveisFaturasAtrasoHelper filtro)
			throws ControladorException;

	public Integer inserirUnidadeNegocio(UnidadeNegocio unidadeNegocio, Usuario usuarioLogado) throws ControladorException;

	public void atualizarUnidadeNegocio(UnidadeNegocio unidadeNegocio, Usuario usuarioLogado) throws ControladorException;

	public Integer inserirEmpresa(Empresa empresa,
			EmpresaContratoCobranca empresaCobranca, Usuario usuarioLogado,
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa)
			throws ControladorException;

	public void atualizarEmpresa(Empresa empresa,
			EmpresaContratoCobranca empresaCobranca, Usuario usuarioLogado,
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa,
			List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixaRemover)
			throws ControladorException;

	public void gerarTabelasTemporariasAtualizacaoCadastral(Integer idSetor,
			Integer idFuncionalidadeIniciada,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ControladorException;

	public void gerarArquivoTextoAtualizacaoCadastralDispositivoMovel(
			Integer idFuncionalidadeIniciada,
			ImovelGeracaoTabelasTemporariasCadastroHelper helper, Integer idRota)
			throws ControladorException;

	public void espelharFeriados(String indicadorTipoFeriado,
			String anoOrigemFeriado, String anoDestinoFeriado)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarLocalidades() throws ControladorException;

	public List<ArquivoTextoAtualizacaoCadastral> pesquisarArquivoTextoAtualizacaoCadastro(String idEmpresa, String idLocalidade, 
			String codigoSetorComercial, String idAgenteComercial, String idSituacaoTransmissao, String exibicao) throws ControladorException;

	public ArquivoTextoAtualizacaoCadastral pesquisarArquivoTextoAtualizacaoCadastro(
			Integer idArquivoTxt) throws ControladorException;

	public Collection<ArquivoTextoAtualizacaoCadastral> pesquisarArquivoTextoAtualizacaoCadastro(
			String[] idsArquivoTxt) throws ControladorException;

	public void atualizarArquivoTextoAtualizacaoCadstral(Integer idArquivoTxt)
			throws ControladorException;

	public Integer clienteSelecionadoFuncionario(Integer idCliente) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void validarQuadraFace(QuadraFace quadraFaceNova,
			Collection colecaoQuadraFace, boolean verificarExistencia)
			throws ControladorException;

	public Collection<Object[]> pesquisarQuadraFaceAssociadaQuadra(Integer idQuadra) throws ControladorException;

	public void validarSeClienteEhPessoaJuridica(Cliente cliente) throws ControladorException;

	public void validarSeDebitoTipoNaoEhGeradoAutomaticamente(DebitoTipo debitoTipo) throws ControladorException;

	public void validarPreExistenciaEntidadeBeneficente(
			EntidadeBeneficente entidadeBeneficente)
			throws ControladorException;

	public Integer inserirEntidadeBeneficente(
			EntidadeBeneficente entidadeBeneficente)
			throws ControladorException;

	public Collection<Integer> pesquisarSetorComercialGeracaoTabelasTemporarias(
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ControladorException;

	public Collection<Integer> obterIdsImovelGeracaoTabelasTemporarias(
			ImovelGeracaoTabelasTemporariasCadastroHelper helper)
			throws ControladorException;

	public Object[] gerarBoletimCustoAtualizacaoCadastral(Empresa empresa,
			Date dataAtualizacaoInicio, Date dataAtualizacaoFim)
			throws ControladorException;

	public void emitirBoletos(Integer idFuncionalidadeIniciada, Integer grupo) throws ControladorException;

	public int obterQuantidadeEconomiasCategoria(Categoria categoria) throws ControladorException;

	public int obterQuantidadeEconomiasSubcategoria(Subcategoria subcategoria) throws ControladorException;

	public UnidadeNegocio pesquisarUnidadeNegocioUsuario(Integer idUsuario) throws ControladorException;

	public boolean verificarExistenciaSetorComercial(Integer idSetorComercial) throws ControladorException;

	public void excluirImoveisDaTarifaSocial(Integer idSetor,
			Integer idFuncionalidadeIniciada, Integer anoMesFaturamento)
			throws ControladorException;

	public Integer pesquisarRelatorioImoveisConsumoMedioCount(
			FiltrarRelatorioImoveisConsumoMedioHelper filtro)
			throws ControladorException;

	public Integer pesquisarImovelAtualizacaoCadastralComIndicadorExclusaoCount() throws ControladorException;

	public Integer inserirArquivoTextoAtualizacaoCadastralSimplificado(
			AtualizacaoCadastralSimplificado arquivo,
			AtualizacaoCadastralSimplificadoBinario arquivoBinario,
			Collection<AtualizacaoCadastralSimplificadoLinha> linhas)
			throws ControladorException;

	public Collection<AtualizacaoCadastralSimplificadoCritica> pesquisarAtualizacaoCadastralSimplificadoCritica(int idArquivo) throws ControladorException;

	public void validarDadosInserirImovelProgramaEspecial(
			ImovelProgramaEspecial imovelProgramaEspecial)
			throws ControladorException;

	public void validarDadosSuspensaoImovelProgramaEspecial(
			ImovelProgramaEspecial imovelProgramaEspecial)
			throws ControladorException;

	public void suspenderImovelEmProgramaEspecialOnline(
			ImovelProgramaEspecial imovelProgramaEspecial,
			Usuario usuarioLogado, Short formaSuspensao)
			throws ControladorException;

	public Integer inserirImovelEmProgramaEspecial(
			ImovelProgramaEspecial imovelProgramaEspecial, Usuario usuarioLogado)
			throws ControladorException;

	public void suspenderImovelEmProgramaEspecialBatch(
			int idFuncionalidadeIniciada, Usuario usuarioLogado, Rota rota)
			throws ControladorException;

	public Integer pesquisarTotalRegistroRelatorioImoveisProgramaEspecial(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper filtro)
			throws ControladorException;

	public Collection<RelatorioImoveisProgramasEspeciaisHelper> pesquisarRelatorioImoveisProgramasEspeciaisAnalitico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper filtro)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRelatorioImoveisProgramasEspeciaisSintetico(
			FiltrarRelatorioImoveisProgramasEspeciaisHelper helper)
			throws ControladorException;

	public Boolean verificarExistenciaParcelamentoImovel(Integer idImovel)
			throws ControladorException;

	public Collection<RelatorioColetaMedidorEnergiaHelper> pesquisarRelatorioColetaMedidorEnergia(
			FiltrarRelatorioColetaMedidorEnergiaHelper helper)
			throws ControladorException;

	public Integer countRelatorioColetaMedidorEnergia(
			FiltrarRelatorioColetaMedidorEnergiaHelper helper)
			throws ControladorException;

	public RelatorioBoletimCadastroIndividualBean criarDadosRelatorioBoletimCadastroIndividual(
			Integer idImovel) throws ControladorException;

	public void atualizarCodigoDebitoAutomatico(Integer integer, SetorComercial setorComercial) throws ControladorException;

	public byte[] baixarNovaVersaoJad() throws ControladorException;

	public byte[] baixarNovaVersaoJar() throws ControladorException;

	public boolean verificarSituacaoImovelCobrancaJudicial(Integer idImovel) throws ControladorException;

	public boolean verificarSituacaoImovelNegativacao(Integer idImovel) throws ControladorException;

	public Integer inserirCadastroEmailCliente(Integer idCliente,
			String nomeClienteAnterior, String cpfAnterior,
			String cnpjAnterior, String emailAnterior, String nomeSolicitante,
			String cpfSolicitante, String nomeClienteAtual,
			String cpfClienteAtual, String cnpjClienteAtual, String emailAtual)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioAlteracoesSistemaColuna(
			GerarRelatorioAlteracoesSistemaColunaHelper helper)
			throws ControladorException;

	public boolean verificarRelacaoColuna(Integer idColuna)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioAtualizacaoCadastralViaInternet(
			GerarRelatorioAtualizacaoCadastralViaInternetHelper filtro)
			throws ControladorException;

	public ClienteImovel pesquisarClienteResponsavelComEsferaPoderPublico(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioResumoAtualizacaoCadastralViaInternet(
			GerarRelatorioAtualizacaoCadastralViaInternetHelper filtro)
			throws ControladorException;

	public Integer countRelatorioAtualizacaoCadastralViaInternet(
			GerarRelatorioAtualizacaoCadastralViaInternetHelper helper)
			throws ControladorException;

	public Collection<EsferaPoder> pesquisarEsferaPoder() throws ControladorException;

	public Collection<ImovelInscricaoAlterada> pesquisarRelatorioImoveisAlteracaoInscricaoViaBatch(
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper)
			throws ControladorException;

	public Integer countTotalRelatorioImoveisAlteracaoInscricaoViaBatch(
			FiltrarRelatorioImoveisAlteracaoInscricaoViaBatchHelper relatorioHelper)
			throws ControladorException;

	public Collection<Object[]> pesquisarDadosRelatorioAlteracoesCpfCnpj(GerarRelatorioAlteracoesCpfCnpjHelper helper) throws ControladorException;

	public Integer inserirSolicitacaoContaBraile(ContaBraileHelper contaBraileHelper) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisaImovelInscricaoAlterada(ImovelInscricaoAlteradaHelper helper) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarLocalidadesPorGerencia(Integer idGerenciaRegional) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarLocalidadesPorUnidadeNegocio(Integer idUnidadeNegocio) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarLocalidade() throws ControladorException;

	public void processarComandoGerado(Integer idLocalidade,
			Integer idFuncionalidadeIniciada,
			TarifaSocialComandoCarta tarifaSocialComandoCarta)
			throws ControladorException;

	public void gerarCartaTarifaSocial(TarifaSocialComandoCarta tscc, Integer idFuncionalidadeIniciada) throws ControladorException;

	public void retirarImovelTarifaSocial(Integer idLocalidade, int idFuncionalidadeIniciada) throws ControladorException;

	public void retirarImovelTarifaSocial(TarifaSocialComandoCarta tscc, int idFuncionalidadeIniciada) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarLocalidadesPorGerenciaEUnidade(
			Integer idGerenciaRegional, Integer idUnidadeNegocio)
			throws ControladorException;

	public Collection<RelatorioAcessoSPCBean> filtrarRelatorioAcessoSPC(
			FiltrarRelatorioAcessoSPCHelper filtrarRelatorioAcessoSPCHelper)
			throws ControladorException;

	public void atualizarGrauImportancia(Logradouro logradouro,
			Integer grauImportancia, Usuario usuario)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterCategorias() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterPerfisImoveis() throws ControladorException;

	public void validarSistemaParametroLojaVirtual(byte[] fileData, String extensao) throws ControladorException;

	public List<HidrometroInstalacaoHistorico> pesquisarHidrometroPeloIdImovel(Integer idImovel) throws ControladorException;

	public AtualizacaoCadastral carregarImovelAtualizacaoCadastral(BufferedReader buffer, List<String> imagens) throws Exception;
	
	public SituacaoAtualizacaoCadastral pesquisarSituacaoAtualizacaoCadastralPorId(Integer idSituacaoCadastral) throws ControladorException;

	public Collection<Integer> pesquisarRotasAtualizacaoCadastral(ImovelGeracaoTabelasTemporariasCadastroHelper helper) throws ControladorException;
	
	public Integer pesquisarIdSetorComercialPorCodigoELocalidade(Integer idLocalidade, Integer codigoSetor) throws ControladorException;
	
	public Integer pesquisarIdQuadraPorNumeroQuadraEIdSetor(Integer idSetorComercial, Integer numeroQuadra) throws ControladorException;
	
	public String retornaIpServidorOperacional() throws Exception;
	
	public String retornaIpServidorRelatorios() throws Exception;
	
	public Object[] pesquisarQtdeDebitosPreteritos(Integer idImovel) throws Exception;
	
	public boolean verificarExistenciaEmpresa(Integer idEmpresa) throws ControladorException;
	
	public ArquivoTextoAtualizacaoCadastral regerarArquivoTextoAtualizacaoCadastral(List<Integer> idsImoveis, Integer idArquivoTexto, String tipoArquivo, Integer idEmpresa) throws ControladorException;
	
	public ArquivoTextoAtualizacaoCadastral regerarArquivoTextoAtualizacaoCadastralComObjetos(List<ImovelControleAtualizacaoCadastral> imoveisControle, 
			Integer idArquivoTexto, String tipoArquivo, Integer idEmpresa) throws ControladorException;

	public UnidadeOrganizacional obterUnidadePorLocalidade(Integer idImovel) throws ControladorException;
	
	public Hidrometro obterHidrometroAtualmenteInstalado(Integer idImovel) throws ErroRepositorioException;
	
	public Collection pesquisarRotaArquivoTextoAtualizacaoCadastroPorIdArquivo(String[] idsArquivoTxt) throws ControladorException;
	
	public StringBuilder gerarArquivoTxt(Collection colecaoImovelFiltrado, Integer idArquivoTexto, Integer idEmpresa, Rota rota) throws ControladorException;
	
	public Integer obterFuncionarioPorImovelRetornoId(Integer idUsuario) throws ControladorException;
	
	public Boolean pesquisarCpfCadastroAguaPara(String cpf) throws ControladorException;
	
	public Collection pesquisarRecadastramentoAguaParaSituacao(Integer situacao) throws ControladorException;
	
	public Collection pesquisarRecadastramentoAguaParaMatriculaSituacao(Integer matricula,Integer situacao) throws ControladorException;
	
	public Collection pesquisarRecadastramentoAguaParaMatricula(Integer matricula,Integer pageOffSet, Integer maxItemPage, Boolean flagTotalRegistros ) throws ControladorException;
	
	public CadastroAguaPara pesquisarRecadastramentoAguaParaPorCpf(String cpf) throws ControladorException;

	public Boolean pesquisarNisCadastroAguaPara(String nis) throws ControladorException;
	
	public Boolean pesquisarNisJaCadastradoInserirCliente(String nis) throws ControladorException;
	
	public Boolean pesquisarNisJaCadastradoManterCliente(String nis, Integer idCliente) throws ControladorException;
	
	public void isNisValido(String numeroNIS, Short tipoPessoa, Integer idCliente, Short tipoOperacao) throws ControladorException;
	
}
