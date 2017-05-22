package gcom.cobranca;

import gcom.arrecadacao.ArrecadadorContratoTarifa;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cobranca.bean.CalcularAcrescimoPorImpontualidadeHelper;
import gcom.cobranca.bean.CalcularValorDataVencimentoAnteriorHelper;
import gcom.cobranca.bean.CobrancaAcaoHelper;
import gcom.cobranca.bean.CobrancaCronogramaHelper;
import gcom.cobranca.bean.CobrancaDocumentoHelper;
import gcom.cobranca.bean.ConcluirParcelamentoDebitosHelper;
import gcom.cobranca.bean.ConsultarTransferenciasDebitoHelper;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.cobranca.bean.DadosPesquisaCobrancaDocumentoHelper;
import gcom.cobranca.bean.DebitoCreditoParcelamentoHelper;
import gcom.cobranca.bean.FiltrarDocumentoCobrancaHelper;
import gcom.cobranca.bean.FiltrarRelacaoParcelamentoHelper;
import gcom.cobranca.bean.FiltroSupressoesReligacoesReestabelecimentoHelper;
import gcom.cobranca.bean.GerarAtividadeAcaoCobrancaHelper;
import gcom.cobranca.bean.GerarResumoAcoesCobrancaCronogramaHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.ObterDadosConfirmarCartaoCreditoDebitoHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gcom.cobranca.bean.ParcelamentoCartaoCreditoHelper;
import gcom.cobranca.bean.ParcelamentoRelatorioHelper;
import gcom.cobranca.bean.PesquisarQtdeRotasSemCriteriosParaAcoesCobranca;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.cobranca.bean.TransferenciasDebitoHelper;
import gcom.cobranca.cobrancaporresultado.ConsultarComandosContasCobrancaEmpresaHelper;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ImpostoDeduzidoHelper;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaEventualHelper;
import gcom.gerencial.bean.InformarDadosGeracaoResumoAcaoConsultaHelper;
import gcom.gui.cobranca.cobrancaporresultado.MovimentarOrdemServicoEncerrarOSHelper;
import gcom.gui.cobranca.cobrancaporresultado.MovimentarOrdemServicoGerarOSHelper;
import gcom.gui.relatorio.cobranca.FiltroRelatorioDocumentosAReceberHelper;
import gcom.micromedicao.Rota;
import gcom.micromedicao.bean.ConsumoHistoricoCondominio;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.relatorio.cobranca.FiltrarRelatorioBoletimMedicaoCobrancaHelper;
import gcom.relatorio.cobranca.RelatorioAcompanhamentoAcoesCobrancaHelper;
import gcom.relatorio.cobranca.RelatorioAnalisePerdasCreditosBean;
import gcom.relatorio.cobranca.RelatorioBoletimMedicaoCobrancaHelper;
import gcom.relatorio.cobranca.RelatorioDocumentoCobrancaOrdemCorteBean;
import gcom.relatorio.cobranca.RelatorioDocumentoCobrancaOrdemFiscalizacaoBean;
import gcom.relatorio.cobranca.RelatorioEmitirDeclaracaoTransferenciaDebitoBean;
import gcom.relatorio.cobranca.RelatorioNotificacaoDebitoBean;
import gcom.relatorio.cobranca.RelatorioVisitaCobrancaBean;
import gcom.relatorio.cobranca.parcelamento.ExtratoDebitoRelatorioHelper;
import gcom.relatorio.cobranca.parcelamento.RelacaoParcelamentoRelatorioHelper;
import gcom.relatorio.cobranca.parcelamento.RelatorioRelacaoParcelamentoAnaliticoBean;
import gcom.relatorio.cobranca.parcelamento.RelatorioRelacaoParcelamentoCartaoCreditoBean;
import gcom.relatorio.faturamento.conta.RelatorioContaBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.filtro.Filtro;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.fileupload.FileItem;

public interface IControladorCobranca {

	public void religarAutomaticamenteImovelCortado() throws ControladorException;

	public void religarImovelCortado(String id, String situacaoAguaLigado, Date dataReligacaoAgua) throws ControladorException;

	public ObterDebitoImovelOuClienteHelper obterDebitoImovelOuCliente(int indicadorDebito, String idImovel, String codigoCliente, Short clienteRelacaoTipo,
			String anoMesInicialReferenciaDebito, String anoMesFinalReferenciaDebito, Date anoMesInicialVencimentoDebito, Date anoMesFinalVencimentoDebito,
			int indicadorPagamento, int indicadorConta, int indicadorDebitoACobrar, int indicadorCreditoARealizar, int indicadorNotasPromissorias,
			int indicadorGuiasPagamento, int indicadorCalcularAcrescimoImpontualidade, Boolean indicadorContas) throws ControladorException;

	public ObterDebitoImovelOuClienteHelper obterDebitoImovelOuCliente(int indicadorDebito, String idImovel, String codigoCliente, Short clienteRelacaoTipo,
			String anoMesInicialReferenciaDebito, String anoMesFinalReferenciaDebito, Date anoMesInicialVencimentoDebito, Date anoMesFinalVencimentoDebito,
			int indicadorPagamento, int indicadorConta, int indicadorDebitoACobrar, int indicadorCreditoARealizar, int indicadorNotasPromissorias,
			int indicadorGuiasPagamento, int indicadorCalcularAcrescimoImpontualidade, Boolean indicadorContas, int indicadorDividaAtiva)
			throws ControladorException;

	public CalcularAcrescimoPorImpontualidadeHelper calcularAcrescimoPorImpontualidade(int anoMesReferenciaDebito, Date dataVencimento, Date dataPagamento,
			BigDecimal valorDebito, BigDecimal valorMultasCobradas, short indicadorMulta, String anoMesArrecadacao, Integer idConta, Short indicadorArrecadacao)
			throws ControladorException;

	public String inserirDebitoAutomatico(String matriculaImovel, String codigoBanco, String codigoAgencia, String identificacaoCliente, Date dataOpcao)
			throws ControladorException;

	public String removerDebitoAutomatico(String matriculaImovel, String codigoBanco, String codigoAgencia, String identificacaoCliente, Date dataOpcao)
			throws ControladorException;

	public Cliente consultarDadosClienteImovelUsuario(Imovel imovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarHistoricoMedicaoIndividualizada(Imovel imovelCondominio, String anoMesFaturamento) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarConsumoHistoricoImoveisVinculados(ConsumoHistoricoCondominio consumoHistorico) throws ControladorException;

	public ConsumoTipo consultarDadosConsumoTipoConsumoHistorico(ConsumoHistorico consumoHistorico) throws ControladorException;

	public void atualizarSituacaoConta(String codigoConta, int situacaoAtual, int anoMesReferenciaContabil) throws ControladorException;

	public void atualizarSituacaoGuiaPagamento(String codigoGuiaPagamento, int situacaoAtualGuia, int anoMesReferenciaContabil) throws ControladorException;

	public void atualizarParcelamento(Integer codigoParcelamento, Integer parcelamentoSituacao, String motivo, Integer usuarioId) throws ControladorException;

	public void atualizarSituacaoDebitoACobrar(String codigoDebitoACobrar, int situacaoAtualDebito, int anoMesReferenciaContabil) throws ControladorException;

	public void atualizarSituacaoCreditoARealizar(String codigoCreditoARealizar, int situacaoAtualCredito, int anoMesReferenciaContabil)
			throws ControladorException;

	public void removerDebitoACobrarDoParcelamento(Integer codigoImovel, Integer codigoParcelamento) throws ControladorException;

	public void removerCreditoARealizarDoParcelamento(Integer codigoImovel, Integer codigoParcelamento) throws ControladorException;

	public void removerGuiaPagamentoDoParcelamento(Integer codigoImovel, Integer codigoParcelamento) throws ControladorException;

	public void executarAtividadeAcaoCobranca(String[] idsAtividadesCobrancaCronograma, String[] idsAtividadesCobrancaEventuais) throws ControladorException;

	public GerarAtividadeAcaoCobrancaHelper gerarAtividadeAcaoCobranca(CobrancaGrupo grupoCobranca, int anoMesReferenciaCicloCobranca,
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma, CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, Collection<Rota> rotas,
			CobrancaAcao acaoCobranca, CobrancaAtividade atividadeCobranca, Integer indicadorCriterio, CobrancaCriterio criterioCobranca, Cliente cliente,
			ClienteRelacaoTipo relacaoClienteImovel, String anoMesReferenciaInicial, String anoMesReferenciaFinal, Date dataVencimentoInicial,
			Date dataVencimentoFinal, Date dataAtual, int idFuncionalidadeIniciada, Cliente clienteSuperior) throws ControladorException;

	public Collection<Rota> pesquisarListaRotasComando(CobrancaGrupo cobrancaGrupo, CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando)
			throws ControladorException;

	public Collection<CobrancaAcaoAtividadeCronograma> pesquisarCobrancaAcaoAtividadeCronograma() throws ControladorException;

	public Collection<CobrancaAcaoAtividadeComando> pesquisarCobrancaAcaoAtividadeComando() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void inserirCobrancaSituacaoHistorico(Collection collectionCobrancaSituacaoHistorico) throws ControladorException;

	public Collection<Parcelamento> verificarParcelamentoMesImovel(Integer codigoImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterListasRotas(String idRotaInicial, String idRotaFinal, String idSetorComercialInicial, String idSetorComercialFinal,
			String idLocalidadeInicial, String idLocalidadeFinal, String idGerenciaRegional, String idUnidadeNegocio, String codigoRotaIncial,
			String codigoRotaFinal, String numeroQuadraInicial, String numeroQuadraFinal) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterListaRotasComando(String idCobrancaGrupo, Collection colecaoIdCobrancaAtividadeComandoRota) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public GerarAtividadeAcaoCobrancaHelper executarComandoEventual(CobrancaAtividade cobrancaAtividade,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, CobrancaAcao cobrancaAcao, Collection colecaoRotas) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterListaAtividadeCronogramaAcaoCobrancaComandadas() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterListaAtividadesEventuaisAcaoCobrancaComandadas() throws ControladorException;

	public void atualizarContaEfetuarParcelamentoDebito(Conta conta, boolean isContaEntradaParcelamento, Integer maiorAnoMesContas) throws ControladorException;

	public NegociacaoOpcoesParcelamentoHelper obterOpcoesDeParcelamento(ObterOpcoesDeParcelamentoHelper helper) throws ControladorException;

	public void excluirComandoAtividadeCronogramaAcaoCobranca(String[] idsCobrancaAcaoAtividadeCronograma) throws ControladorException;

	public void excluirComandoAtividadeEventualAcaoCobranca(String[] idsCobrancaAcaoAtividadeEventual) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarLinhasCriterio(String idCriterioCobranca) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarCriteriosComando(String idCobrancaAcao) throws ControladorException;

	public CobrancaAcaoAtividadeComando consultarCobrancaAcaoAtividadeComando(String idCobrancaAcaoAtividadeComando) throws ControladorException;

	public String consultarPeriodoFinalContaCobrancaAcaoAtividadeComando() throws ControladorException;

	public String consultarPeriodoVencimentoContaFinalCobrancaAcaoAtividadeComando() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterColecaoCobrancaGrupo() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterColecaoCobrancaAtividade() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterColecaoCobrancaAcao() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterColecaoGerenciaRegional() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterColecaoUnidadeNegocio() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterColecaoClienteRelacaoTipo() throws ControladorException;

	public CobrancaAtividade obterCobrancaAtividade(String idCobrancaAtividade) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterColecaoRota(String idSetorComercial) throws ControladorException;

	public Integer inserirResolucaoDiretoria(ResolucaoDiretoria resolucaoDiretoria, Usuario usuarioLogado) throws ControladorException;

	public void gerarDebitosACobrarAcrescimosImpontualidade(Imovel imovel, BigDecimal valorAtualizacaoMonetaria, BigDecimal valorJurosMora,
			BigDecimal valorMulta, BigDecimal taxaJuros, Integer parcelamentoId, Collection<Categoria> colecaoCategoria, Usuario usuarioLogado,
			boolean isContaEntradaParcelamento, Integer anoMesGuiaEntrada, Integer maiorAnoMesConta) throws ControladorException;

	public void gerarDebitosACobrarParcelamento(Imovel imovel, Short numeroPrestacao, BigDecimal valorTotalContas, BigDecimal valorTotalGuiasPagamento,
			BigDecimal valorTotalAcrescimosImpontualidade, BigDecimal valorTotalServicosDebitosACobrarCurtoPrazo,
			BigDecimal valorTotalServicosDebitosACobrarLongoPrazo, BigDecimal valorTotalReparcelamentosCurtoPrazo,
			BigDecimal valorTotalReparcelamentosLongoPrazo, BigDecimal valorTotalJurosParcelamento, BigDecimal taxaJuros, Integer parcelamentoId,
			Collection<Categoria> colecaoCategoria, BigDecimal valorEntrada, Integer indicadorDividaAtiva, Usuario usuarioLogado,
			boolean isContaEntradaParcelamento, Integer anoMesGuiaEntrada, Integer maiorAnoMesConta) throws ControladorException;

	public void inserirComandoAcaoCobrancaEventual(String idCobrancaAcao, String idCobrancaAtividade, String idCobrancaGrupo, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal, String codigoSetorComercialInicial, String codigoSetorComercialFinal,
			String idSetorComercialInicial, String idSetorComercialFinal, String idCliente, String idClienteRelacaoTipo, String anoMesReferencialInicial,
			String anoMesReferencialFinal, String dataVencimentoContaInicial, String dataVencimentoContaFinal, String indicador, String idRotaInicial,
			String idRotaFinal, String idUnidadeNegocio, String codigoRotaInicial, String codigoRotaFinal, String numeroQuadraInicial, String numeroQuadraFinal)
			throws ControladorException;

	public CobrancaDocumentoHelper apresentaItensDocumentoCobranca(CobrancaDocumento cobrancaDocumento) throws ControladorException;

	public void verficarExistenciaComandoEventual(String[] idsCobrancaAcao, String idCobrancaAtividade, String idCobrancaGrupo, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal, String codigoSetorComercialInicial, String codigoSetorComercialFinal, String idCliente,
			String idClienteRelacaoTipo, String anoMesReferencialInicial, String anoMesReferencialFinal, String dataVencimentoContaInicial,
			String dataVencimentoContaFinal, String indicador, String rotaInicial, String rotaFinal, String idComando, String idUnidadeNegocio,
			String codigoClienteSuperior, String numeroQuadraInicial, String numeroQuadraFinal) throws ControladorException;

	public void validarAnoMesInicialFinalComandoAcaoCobranca(String anoMesContaInicial, String anoMesContaFinal) throws ControladorException;

	public void verificarVencimentoContaComandoAcaoCobranca(String anoMesVencimentoInicial, String anoMesVencimentoFinal) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection inserirComandoAcaoCobrancaCriterioEventual(String[] idsCobrancaAcao, String idCobrancaAtividade, String idCobrancaGrupo,
			String idGerenciaRegional, String idLocalidadeInicial, String idLocalidadeFinal, String codigoSetorComercialInicial,
			String codigoSetorComercialFinal, String idSetorComercialInicial, String idSetorComercialFinal, String idCliente, String idClienteRelacaoTipo,
			String anoMesReferencialInicial, String anoMesReferencialFinal, String dataVencimentoContaInicial, String dataVencimentoContaFinal,
			String indicador, String idRotaInicial, String idRotaFinal, String idComando, String unidadeNegocio, Usuario usuarioLogado, String titulo,
			String descricaoSolicitacao, String prazoExecucao, String quantidadeMaximaDocumentos, String valorLimiteObrigatoria, String indicadorImoveisDebito,
			String indicadorGerarBoletimCadastro, String codigoClienteSuperior, String codigoRotaInicial, String codigoRotaFinal, String logradouroId,
			String consumoMedioInicial, String consumoMedioFinal, String tipoConsumo, String periodoInicialFiscalizacao, String periodoFinalFiscalizacao,
			String[] situacaoFiscalizacao, String numeroQuadraInicial, String numeroQuadraFinal) throws ControladorException;

	public CobrancaAcao consultarCobrancaAcao(String idCobrancaAcao) throws ControladorException;

	public CobrancaAtividade consultarCobrancaAtividade(String idCobrancaAtividade) throws ControladorException;

	public void atualizarCobrancaAcaoAtividadeComando(CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			GerarAtividadeAcaoCobrancaHelper gerarAtividadeAcaoCobrancaHelper) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection concluirComandoAcaoCobranca(String periodoInicialConta, String periodoFinalConta, String periodoVencimentoContaInicial,
			String periodoVencimentoContaFinal, String[] idsCobrancaAcao, String idCobrancaAtividade, String idCobrancaGrupo, String idGerenciaRegional,
			String localidadeOrigemID, String localidadeDestinoID, String setorComercialOrigemCD, String setorComercialDestinoCD, String idCliente,
			String clienteRelacaoTipo, String indicador, String rotaInicial, String rotaFinal, String setorComercialOrigemID, String setorComercialDestinoID,
			String idComando, String unidadeNegocio, Usuario usuarioLogado, String titulo, String descricaoSolicitacao, String prazoExecucao,
			String quantidadeMaximaDocumentos, String valorLimiteObrigatoria, String indicadorImoveisDebito, String indicadorGerarBoletimCadastro,
			String codigoClienteSuperior, String codigoRotaInicial, String codigoRotaFinal, String logradouroId, String cosumoMedioInicial,
			String cosumoMedioFinal, String tipoConsumo, String periodoInicialFiscalizacao, String periodoFinalFiscalizacao, String[] situacaoFiscalizacao,
			String numeroQuadraInicial, String numeroQuadraFinal) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection executarComandoAcaoCobranca(String periodoInicialConta, String periodoFinalConta, String periodoVencimentoContaInicial,
			String periodoVencimentoContaFinal, String[] idsCobrancaAcao, String idCobrancaAtividade, String idCobrancaGrupo, String idGerenciaRegional,
			String localidadeOrigemID, String localidadeDestinoID, String setorComercialOrigemCD, String setorComercialDestinoCD, String idCliente,
			String clienteRelacaoTipo, String indicador, String rotaInicial, String rotaFinal, String setorComercialOrigemID, String setorComercialDestinoID,
			String idComando, Usuario usuarioLogado, String titulo, String descricaoSolicitacao, String prazoExecucao, String quantidadeMaximaDocumentos,
			String valorLimiteObrigatoria, String indicadorImoveisDebito, String indicadorGerarBoletimCadastro, String codigoClienteSuperior,
			String codigoRotaInicial, String codigoRotaFinal, String consumoMedioInicial, String consumoMedioFinal, String tipoConsumo,
			String periodoInicialFiscalizacao, String periodoFinalFiscalizacao, String[] situacaoFiscalizacao, String numeroQuadraInicial,
			String numeroQuadraFinal) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterListaRotasComando(String idCobrancaGrupo, String idCobrancaAcaoAtividadeComando) throws ControladorException;

	public void atualizarResolucaoDiretoria(ResolucaoDiretoria resolucaoDiretoria, Usuario usuarioLogado) throws ControladorException;

	public ParcelamentoPerfil obterPerfilParcelamento(Integer codigoImovel, Integer imovelSituacaoId, Integer perfilImovelId, Integer subcategoriaId,
			Integer resolucaoDiretoria, Integer categoriaId) throws ControladorException;

	public void concluirManterComandoAcaoCobranca(String periodoInicialConta, String periodoFinalConta, String periodoVencimentoContaInicial,
			String periodoVencimentoContaFinal, String idCobrancaAcao, String idCobrancaAtividade, String idCobrancaGrupo, String idGerenciaRegional,
			String localidadeOrigemID, String localidadeDestinoID, String setorComercialOrigemCD, String setorComercialDestinoCD, String idCliente,
			String clienteRelacaoTipo, String indicador, String rotaInicial, String rotaFinal, String setorComercialOrigemID, String setorComercialDestinoID,
			String idCobrancaAcaoAtividadeComando, Date realizacao, Date comando, Date ultimaDataAtualizacao, Usuario usuario, Empresa empresa,
			Integer quantidadeDocumentos, BigDecimal valorDocumentos, Integer quantidadeItensCobrados, String idComando, String unidadeNegocio, String titulo,
			String descricaoSolicitacao, String prazoExecucao, String quantidadeMaximaDocumentos, String valorLimiteObrigatoria, String indicadorImoveisDebito,
			String indicadorGerarBoletimCadastro, String codigoClienteSuperior, String codigoRotaInicial, String codigoRotaFinal, String consumoMedioInicial,
			String consumoMedioFinal, String tipoConsumo, String periodoInicialFiscalizacao, String periodoFinalFiscalizacao, String[] situacaoFiscalizacao,
			String numeroQuadraInicial, String numeroQuadraFinal) throws ControladorException;

	public void executarComandoManterAcaoCobranca(String periodoInicialConta, String periodoFinalConta, String periodoVencimentoContaInicial,
			String periodoVencimentoContaFinal, String idCobrancaAcao, String idCobrancaAtividade, String idCobrancaGrupo, String idGerenciaRegional,
			String localidadeOrigemID, String localidadeDestinoID, String setorComercialOrigemCD, String setorComercialDestinoCD, String idCliente,
			String clienteRelacaoTipo, String indicador, String rotaInicial, String rotaFinal, String setorComercialOrigemID, String setorComercialDestinoID,
			String idComando, String idCobrancaAcaoAtividadeComando, Date ultimaDataAtualizacao, Date comando, Date realizacao, Usuario usuario,
			Empresa empresa, Integer quantidadeDocumentos, BigDecimal valorDocumentos, Integer quantidadeItensCobrados, String titulo,
			String descricaoSolicitacao, String prazoExecucao, String quantidadeMaximaDocumentos, String valorLimiteObrigatoria, String indicadorImoveisDebito,
			String indicadorGerarBoletimCadastro, String codigoClienteSuperior, String codigoRotaInicial, String codigoRotaFinal, String consumoMedioInicial,
			String consumoMedioFinal, String tipoConsumo, String periodoInicialFiscalizacao, String periodoFinalFiscalizacao, String[] situacaoFiscalizacao,
			String numeroQuadraInicial, String numeroQuadraFinal) throws ControladorException;

	public CobrancaAcaoAtividadeComando atualizarComandoAcaoCobrancaEventual(String idCobrancaAcao, String idCobrancaAtividade, String idCobrancaGrupo,
			String idGerenciaRegional, String idLocalidadeInicial, String idLocalidadeFinal, String codigoSetorComercialInicial,
			String codigoSetorComercialFinal, String idSetorComercialInicial, String idSetorComercialFinal, String idCliente, String idClienteRelacaoTipo,
			String anoMesReferencialInicial, String anoMesReferencialFinal, String dataVencimentoContaInicial, String dataVencimentoContaFinal,
			String indicador, String idRotaInicial, String idRotaFinal, String idCobrancaAcaoAtividadeComando, Date ultimaDataAtualizacao, Date comando,
			Date realizacao, Usuario usuario, Empresa empresa, Integer quantidadeDocumentos, BigDecimal valorDocumentos, Integer quantidadeItensCobrados,
			String idComando, String unidadeNegocio, String titulo, String descricaoSolicitacao, String prazoExecucao, String quantidadeMaximaDocumentos,
			String valorLimiteObrigatoria, String indicadorImoveisDebito, String indicadorGerarBoletimCadastro, String codigoClienteSuperior,
			String codigoRotaInicial, String codigoRotaFinal, String consumoMedioInicial, String consumoMedioFinal, String tipoConsumo,
			String periodoInicialFiscalizacao, String periodoFinalFiscalizacao, String[] situacaoFiscalizacao, String numeroQuadraInicial,
			String numeroQuadraFinal) throws ControladorException;

	public Integer concluirParcelamentoDebitos(ConcluirParcelamentoDebitosHelper helper, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void inserirCobrancaCronograma(Collection colecaoCobrancaCronogramaHelper, Usuario usuarioLogado) throws ControladorException;

	public FiltroCobrancaAcaoAtividadeCronograma filtrarCobrancaCronograma(String idGrupoCobranca, String mesAno) throws ControladorException;

	public void atualizarDadosParcelamentoParaImovel(Integer codigoImovel) throws ControladorException;

	public void desfazerParcelamentosPorEntradaNaoPaga(int idFuncionalidadeIniciada) throws ControladorException;

	public Integer desfazerParcelamentosDebito(String motivo, Integer codigo, Usuario usuario) throws ControladorException;

	public Integer inserirCobrancaCriterio(CobrancaCriterio cobrancaCriterio, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarCobrancaCriterio(CobrancaCriterio cobrancaCriterio, Collection colecaoCobrancaCriterioLinha,
			Collection colecaoCobrancaCriterioLinhaRemovidas, Collection colecaoCriterioSituacaoCobrancaNovos,
			Collection colecaoCriterioSituacaoLigacaoAguaNovos, Collection colecaoCriterioSituacaoLigacaoEsgotoNovos, Usuario usuarioLogado)
			throws ControladorException;

	public void removerCobrancaCriterio(String[] idsCobrancaCriterio, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarCobrancaCronograma(Collection colecaoCobrancaCronogramaHelper, Collection colecaoCronogramaHelperErroAtualizacao, Usuario usuarioLogado)
			throws ControladorException;

	public void removerCobrancaCronograma(Collection<CobrancaCronogramaHelper> colecaocobrancaCronogramaHelperRemover) throws ControladorException;

	public void removerCobrancaCronograma(String[] idsCobrancaCronograma, Usuario usuarioLogado) throws ControladorException;

	public void removerCobrancaAtividadeCronograma(String[] ids) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer inserirPerfilParcelamento(ParcelamentoPerfil parcelamentoPerfilNova, Collection collectionParcelamentoQuantidadeReparcelamentoHelper,
			Collection collectionParcelamentoDescontoInatividade, Collection collectionParcelamentoDescontoAntiguidade, Usuario usuarioLogado,
			Collection collectionParcelamentoDescontoInatividadeAVista) throws ControladorException;

	public void removerPerfilParcelamento(String[] ids, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarPerfilParcelamento(ParcelamentoPerfil parcelamentoPerfil, Collection collectionParcelamentoQuantidadeReparcelamentoHelper,
			Collection collectionParcelamentoDescontoInatividade, Collection collectionParcelamentoDescontoAntiguidade,
			Collection collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas, Collection collectionParcelamentoDescontoInatividadeLinhaRemovidas,
			Collection collectionParcelamentoDescontoAntiguidadeLinhaRemovidas, Collection collectionParcelamentoQuantidadePrestacaoLinhaRemovidas,
			Usuario usuarioLogado, Collection collectionParcelamentoDescontoInatividadeAVista,
			Collection collectionParcelamentoDescontoInatividadeAVistaLinhaRemovidas) throws ControladorException;

	public FiltroCobrancaAcaoAtividadeCronograma construirFiltroCobrancaAcaoAtividadeCronograma(String anoMesPeriodoReferenciaCobrancaInicial,
			String anoMesPeriodoReferenciaCobrancaFinal, String[] grupoCobranca, String[] acaoCobranca, String[] atividadeCobranca,
			String dataPeriodoPrevisaoComandoInicial, String dataPeriodoPrevisaoComandoFinal, String dataPeriodoComandoInicial, String dataPeriodoComandoFinal,
			String dataPeriodoRealizacaoComandoInicial, String dataPeriodoRealizacaoComandoFinal, String intervaloValorDocumentosInicial,
			String intervaloValorDocumentosFinal, String intervaloQuantidadeDocumentosInicial, String intervaloQuantidadeDocumentosFinal,
			String intervaloQuantidadeItensDocumentosInicial, String intervaloQuantidadeItensDocumentosFinal, String situacaoCronograma, String situacaoComando)
			throws ControladorException;

	public Collection<CobrancaAcaoAtividadeCronograma> pesquisarCobrancaAcaoAtividadeCronograma(
			FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma) throws ControladorException;

	public CobrancaAcaoAtividadeCronograma obterCobrancaAcaoAtividadeCronograma(String idCobrancaAcaoAtividadeCronograma) throws ControladorException;

	public FiltroCobrancaAcaoAtividadeComando construirFiltroCobrancaAcaoAtividadeEventual(String[] grupoCobranca, String[] acaoCobranca,
			String[] atividadeCobranca, String anoMesPeriodoReferenciaContasInicial, String anoMesPeriodoReferenciaContasFinal,
			String dataPeriodoComandoInicial, String dataPeriodoComandoFinal, String dataPeriodoRealizacaoComandoInicial,
			String dataPeriodoRealizacaoComandoFinal, String dataPeriodoVencimentoContasInicial, String dataPeriodoVencimentoContasFinal,
			String intervaloValorDocumentosInicial, String intervaloValorDocumentosFinal, String intervaloQuantidadeDocumentosInicial,
			String intervaloQuantidadeDocumentosFinal, String intervaloQuantidadeItensDocumentosInicial, String intervaloQuantidadeItensDocumentosFinal,
			String situacaoComando, String indicadorCriterio, String idGerenciaRegional, String idLocalidadeInicial, String idLocalidadeFinal,
			String codigoSetorComercialInicial, String codigoSetorComercialFinal, String idRotaInicial, String idRotaFinal, String idCliente,
			String idClienteRelacaoTipo, String criterioCobranca, String unidadeNegocio, String[] idCobrancaAcaoAtividadeComando, String dataEmissaoInicial,
			String dataEmissaoFinal, String consumoMedioInicial, String consumoMedioFinal, String tipoConsumo, String periodoInicialFiscalizacao,
			String periodoFinalFiscalizacao, String[] situacaoFiscalizacao, String numeroQuadraInicial, String numeroQuadraFinal) throws ControladorException;

	public SetorComercial obterSetorComercialLocalidade(String localidadeID, String setorComercialCD) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterColecaoRotaSetorComercialLocalidade(String codigoSetorComercial, String idLocalidade) throws ControladorException;

	public Localidade obterLocalidadeGerenciaRegional(String localidadeID) throws ControladorException;

	public Cliente obterCliente(String idCliente) throws ControladorException;

	public CobrancaCriterio obterCobrancaCriterio(String idCobrancaCriterio) throws ControladorException;

	public Collection<CobrancaAcaoAtividadeComando> pesquisarCobrancaAcaoAtividadeEventual(FiltroCobrancaAcaoAtividadeComando filtroCobrancaAcaoAtividadeComando)
			throws ControladorException;

	public CobrancaAcaoAtividadeComando obterCobrancaAcaoAtividadeComando(String idCobrancaAcaoAtividadeComando) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection gerarRelacaoDebitos(String idImovelCondominio, String idImovelPrincipal, String idNomeConta, String idSituacaoLigacaoAgua,
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
			String[] tipoDebito, String valorDebitoInicial, String valorDebitoFinal, String qtdContasInicial, String qtdContasFinal,
			String referenciaFaturaInicial, String referenciaFaturaFinal, String vencimentoInicial, String vencimentoFinal, String qtdImoveis,
			String qtdMaiores, String ordenacao, String indicadorCpfCnpj, String cpfCnpj

	) throws ControladorException;

	public Integer obterQuantidadaeRelacaoImoveisDebitos(String idImovelCondominio, String idImovelPrincipal, String idNomeConta, String idSituacaoLigacaoAgua,
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
			String numeroMoradoresInicial, String numeroMoradoresFinal, String idAreaConstruidaFaixa) throws ControladorException;

	public Integer pesquisarCobrancaCronogramaCount(Filtro filtro) throws ControladorException;

	public Integer pesquisarConsumoMedioConsumoHistoricoImovel(Integer imovelId) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCobrancaCriterioLinha(Integer idCriterioCobranca) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarParcelamentoDescontoAntiguidade(Integer idParcelamentoPerfil) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarParcelamentoDescontoInatividade(Integer idParcelamentoPerfil) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarReparcelamentoConsecutivo(Integer idParcelamentoPerfil) throws ControladorException;

	public Object[] pesquisarDebitosImovel(String codigoImovel, String codigoImovelAntes, String dataParcelamento, String resolucaoDiretoria,
			String fimIntervaloParcelamento, String inicioIntervaloParcelamento, String indicadorContasRevisao, String indicadorGuiasPagamento,
			String indicadorAcrescimosImpotualidade, String indicadorDebitosACobrar, String indicadorCreditoARealizar, Boolean indicadorContas,
			String indicadorDividaAtiva) throws ControladorException;

	public Collection<CobrancaAcaoAtividadeCronograma> pesquisarCobrancaAcaoAtividadeCronogramaComandadosNaoRealizados() throws ControladorException;

	public Collection<CobrancaAcaoAtividadeComando> pesquisarCobrancaAcaoAtividadeCronogramaEventuaisComandadosNaoRealizados() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public ExtratoDebitoRelatorioHelper gerarEmitirExtratoDebito(Imovel imovel, Short indicadorGeracaoTaxaCobranca, Collection colecaoContas,
			Collection colecaoGuiasPagamento, Collection colecaoDebitosACobrar, BigDecimal valorAcrescimosImpontualidade, BigDecimal valorDesconto,
			BigDecimal valorDocumento, Collection<CreditoARealizar> colecaoCreditoARealizar, Cliente cliente, ResolucaoDiretoria resolucaoDiretoria,
			Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoDebitosDeParcelamento,
			Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoCreditosDeParcelamento) throws ControladorException;

	public CalcularValorDataVencimentoAnteriorHelper calcularValorDataVencimentoAnterior(Collection<CobrancaDocumentoItem> colecaoCobrancaDocumentoItem,
			int qtdMaxItens) throws ControladorException;

	public Collection<CobrancaDocumento> consultarImovelDocumentosCobranca(Integer idImovel, Integer numeroPagina) throws ControladorException;

	public Integer consultarQuantidadeImovelDocumentosCobranca(Integer idImovel) throws ControladorException;

	public Integer consultarQuantidadeImovelDocumentosItemCobranca(Integer idImovel) throws ControladorException;

	public ParcelamentoRelatorioHelper pesquisarParcelamentoRelatorio(Integer idParcelamento) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarParcelamentoItemPorIdParcelamentoRelatorio(Integer idParcelamento) throws ControladorException;

	public void gerarResumoAcoesCobrancaCronograma(Object[] dadosCobrancaAcaoAtividadeCronograma, int idFuncionalidadeIniciada) throws ControladorException;

	public void atualizarItemDocumentoCobranca(int idSituacaoDebito, BigDecimal valorItemCobrado, Date dataSituacaoDebito, Collection<GerarResumoAcoesCobrancaCronogramaHelper>
		colecaoGerarResumoAcoesCobrancaCronogramaHelper);

	public Imovel pesquisarDadosImovel(int idImovel) throws ControladorException;

	public void processarAcaoOrdemServico(Date dataPrevistaAtividadeEncerrar, Date dataPrevistaAtividadeEmitir, Date dataComandoAtividadeEncerrar,
			Date dataRealizacaoAtividadeEmitir, Usuario usuarioLogado, int anoMesReferenciaCobrancaGrupoCronogramaMes, int idCobrancaAcaoCronograma,
			int idCobrancaGrupo, CobrancaAcao cobrancaAcao, Date dataRealizacaoAtividadeEncerrar) throws ControladorException;

	public void acumularResumoCobrancaAcaoOrdemServico(Collection<ResumoCobrancaAcao> colecaoResumoCobrancaAcao,
			int anoMesReferenciaCobrancaGrupoCronogramaMes, int idCobrancaAcaoCronograma, Date dataRealizacaoAtividadeEmitir,
			Date dataPrevistaAtividadeEncerrar, int idCobrancaGrupo, Imovel imovel, Categoria categoria, int idCobrancaAcao, Integer idSituacaoAcao,
			Integer idSituacaoPredominanteDebito, int idFiscalizacao, int indicadorCronogramaComando, BigDecimal valorDocumento, Integer indicadorAntesApos,
			Integer indicadorAcimaLimite, Date dataRealizacaoAtividadeEncerrar);

	public Collection<ResolucaoDiretoria> pesquisarResolucaoDiretoriaMaiorDataVigenciaInicio() throws ControladorException;

	public Collection<Imovel> pesquisarImovelEfetuarParcelamento(FiltroImovel filtroImovel, Usuario usuarioLogado) throws ControladorException;

	public Boolean verificarQtdeReparcelamentoPerfil(Integer idPerfilParc, Short numeroReparcelamentoConsecutivos) throws ControladorException;

	public CobrancaAcaoAtividadeCronograma pesquisarCobrancaAcaoAtividadeCronogramaId(Integer idCobrancaAcaoAtividadeCronograma) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCobrancaGrupoCronogramaMes() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void verificarUnicaFatura(Collection colecaoContas, ParcelamentoPerfil parcelamentoPerfil) throws ControladorException;

	public void gerarDebitoCobrarNaoCriados();

	public boolean verificarItensParcelamentoNoHistorico(Integer idImovel, Integer idParcelamento) throws ControladorException;

	public Object[] pesquisarDadosOrdemServicoDocumentoCobranca(Integer idDocumentoCobranca) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarParcelamentoItensDebitoACobrar(Collection colecaoIdsDebitoACobrar) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void removerDocumentosItensDebitoACobrar(Collection colecaoIdsDebitoACobrar) throws ControladorException;

	public void inserirResumoAcoesCobrancaCronograma(Object[] dadosCobrancaAcaoAtividadeCronograma, int idFuncionalidadeIniciada) throws ControladorException;

	public InformarDadosGeracaoResumoAcaoConsultaHelper informarDadosGeracaoResumoAcaoConsulta(String mesAnoFaturamento, String[] idsCobrancaGrupo,
			String[] idsGerenciaRegional, String[] idsUnidadeNegocio, Integer idEloPolo, Integer idLocalidade, Integer idSetorComercial, Integer nmQuadra,
			String[] idsImovelPerfil, String[] idsLigacaoAguaSituacao, String[] idsLigacaoEsgotoSituacao, String[] idsCategoria, String[] idsEsferaPoder,
			String[] idsEmpresas, String tipoImpressao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarProtocoloDocumentoCobrancaCronograma(Integer idCobrancaAcaoAtividadeCronograma) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarProtocoloDocumentoCobrancaEventual(Integer idCobrancaAcaoAtividadeComand) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCobrancaAcaoCronograma(int idCobrancaGrupoCronogramaMes) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(int idCobrancaAcaoCronograma, int idCobrancaAtividade) throws ControladorException;

	public Object[] validarRegistroAtendimentoTransferenciaDebitoCredito(Integer idRA, boolean levantarExcecao) throws ControladorException;

	public Integer validarTransferenciaDebitoCreditoDadosImoveis(Integer idRA, Integer idImovelDestino) throws ControladorException;

	public ObterDebitoImovelOuClienteHelper apresentarDebitoCreditoImovelOrigem(Integer idImovelOrigem) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void transferirDebitosCreditos(Integer idImovelDestino, Collection colecaoContas, Collection colecaoDebitosACobrar,
			Collection colecaoCreditosARealizar, Collection colecaoGuiasPagamento, Usuario usuarioLogado, Integer idRegistroAtendimento,
			String identificadoresConta) throws ControladorException;

	public Collection<RelacaoParcelamentoRelatorioHelper> filtrarRelacaoParcelamento(FiltrarRelacaoParcelamentoHelper filtrarRelacaoParcelamento)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection gerarColecaoDocumentoCobrancaOrdemServico(CobrancaAcao cobrancaAcao, Date dataRealizacaoAtividadeEncerrar, Usuario usuarioLogado,
			Collection<DadosPesquisaCobrancaDocumentoHelper> colecaoCobrancaDocumentoParaAtualizar, Date dataPrevistaAtividadeEncerrar,
			Date dataComandoAtividadeEncerrar) throws ControladorException;

	public Collection<Object[]> pesquisarCobrancaAcaoAtividadeComandoSemRealizacao() throws ControladorException;

	public void pesquisarDocumentosCobrancaParaGeracaoResumoEventual(Integer idCobrancaAtividadeAcaoComando, Usuario usuarioLogado, Integer idCobrancaAcao,
			Date dataEncerramentoPrevista, Date dataRealizacaoEncerrar, Date dataRealizacao) throws ControladorException;

	public void gerarResumoAcoesCobrancaEventual(Object[] dadosCobrancaAcaoAtividadeEventual, int idFuncionalidadeIniciada) throws ControladorException;

	public void inserirResumoAcoesCobrancaEventual(Object[] dadosCobrancaAcaoAtividadeEventual, int idFuncionalidadeIniciada) throws ControladorException;

	public InformarDadosGeracaoResumoAcaoConsultaEventualHelper informarDadosGeracaoResumoAcaoConsultaEventual(String dataEmissaoInicial,
			String dataEmissaoFinal, String idCobrancaAcaoAtividadeComando, String tituloCobrancaAcaoAtividadeComando, String[] idsCobrancaGrupo,
			String[] idsGerenciaRegional, Integer idEloPolo, Integer idLocalidade, Integer idSetorComercial, Integer nmQuadra, String[] idsImovelPerfil,
			String[] idsLigacaoAguaSituacao, String[] idsLigacaoEsgotoSituacao, String[] idsCategoria, String[] idsEsferaPoder, String[] idsEmpresas,
			String[] idsUnidadeNegocio) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection gerarCurvaAbcDebitos(String classificacao, String referenciaCobrancaInicial, String referenciaCobrancaFinal,
			String indicadorImovelMedicaoIndividualizada, String indicadorImovelParalizacaoFaturamentoCobranca, String[] gerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal, String idSetorComercialInicial, String idSetorComercialFinal, String idMunicipio,
			String[] situacaoLigacaoAgua, String[] situacaoLigacaoEsgoto, String intervaloMesesCortadoSuprimidoInicial,
			String intervaloMesesCortadoSuprimidoFinal, String intervaloConsumoMinimoFixadoEsgotoInicial, String intervaloConsumoMinimoFixadoEsgotoFinal,
			String indicadorMedicao, String idTipoMedicao, String idPerfilImovel, String idTipoCategoria, String[] categoria, String idSubCategoria,
			String valorMinimoDebito, String intervaloQuantidadeDocumentosInicial, String intervaloQuantidadeDocumentosFinal,
			String indicadorPagamentosNaoClassificados) throws ControladorException;

	public ObterDebitoImovelOuClienteHelper apresentarDebitoCreditoImovelExtratoDebito(Integer idImovel, boolean indicadorParcelamento)
			throws ControladorException;

	public BigDecimal obterValorTaxaDocumentoCobranca(Imovel imovel, Short indicadorCobrancaTaxaExtrato) throws ControladorException;

	public Integer verificarRDUtilizadaPeloImovel(Integer idRD, Integer idImovel) throws ControladorException;

	public BigDecimal pesquisarValorDebitoACobrarSancoes(Integer idImovel, Integer anoMesLimiteMinimo, Integer anoMesLimiteMaximo) throws ControladorException;

	public BigDecimal pesquisarValorDebitoCobradoContas(Integer idImovel, Integer anoMesInicialReferenciaDebito, Integer anoMesFinalReferenciaDebito,
			int indicadorDividaAtiva) throws ControladorException;

	public Integer inserirAcaoCobranca(CobrancaAcaoHelper cobrancaAcaoHelper) throws ControladorException;

	public Integer pesquisarMaximoAnoMesIndicesAcerscimosImpontualidade() throws ControladorException;

	public FiltroCobrancaAcao filtrarAcaoCobranca(String descricaoAcaoCobranca, String numeroDiasValidade, String idAcaoPredecessora,
			String numeroDiasEntreAcoes, String idTipoDocumentoGerado, String idSituacaoLigacaoAgua, String idSituacaoLigacaoEsgoto, String idCobrancaCriterio,
			String descricaoCobrancaCriterio, String idServicoTipo, String descricaoServicoTipo, String ordemCronograma, String icCompoeCronograma,
			String icAcaoObrigatoria, String icRepetidaCiclo, String icSuspensaoAbastecimento, String icDebitosACobrar, String icAcrescimosImpontualidade,
			String icGeraTaxa, String icEmitirBoletimCadastro, String icImoveisSemDebitos, String icMetasCronograma, String icOrdenamentoCronograma,
			String icOrdenamentoEventual, String icDebitoInterfereAcao, String numeroDiasRemuneracaoTerceiro, String icUso, String icCreditosARealizar,
			String icNotasPromissoria) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRotasIntervaloGerencia(String idGerenciaRegional, String idCobrancaAcao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRotasIntervaloLocalidade(String idLocalidadeInicial, String idLocalidadeFinal, String idCobrancaAcao)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRotasIntervaloSetor(String codigoSetorComercialInicial, String codigoSetorComercialFinal, String idLocalidade,
			String idCobrancaAcao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRotas(String codigoSetorComercial, String rotaInicial, String rotaFinal, String idLocalidade, String idCobrancaAcao)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRotasIntervaloUnidadeNegocio(String idUnidadeNegocio, String idCobrancaAcao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRotasIntervaloGrupo(String idGrupoCobranca, String idCobrancaAcao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRotasPorCobrancaAcao(String idCobrancaAcao) throws ControladorException;

	public void atualizarAcaoCobranca(CobrancaAcao cobrancaAcao, CobrancaAcaoHelper cobrancaAcaoHelper) throws ControladorException;

	public Cliente pesquisarClienteResponsavelParcelamento(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void associarConjuntoRotasCriterioCobranca(Collection colecaoRotas, Usuario usuarioLogado, RotaAcaoCriterioHelper rotaAcaoCriterioHelper)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarParcelamentoRDEspecial(Integer situacaoParcelamento, Integer idLocalidade) throws ControladorException;

	public void atualizarNumeroParcelasPagasConsecutivasParcelamento(Integer idParcelamento, Short numeroParcelas) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoNegativacao(DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper, int tipo) throws ControladorException;

	public void informarUnidadeOrganizacionalTestemunha(Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunhaAdicionadas,
			Collection<UnidadeOrganizacionalTestemunha> colecaoUnidadeOrganizacionalTestemunhaRemovidas, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Object gerarRelatorioParcelamentoCobranca(Usuario usuario, String idParcelamento, UnidadeOrganizacional unidadeUsuario,
			Collection colecaoFaturasEmAberto, Collection colecaoGuiasPagamento, Collection colecaoServicosACobrar, Collection colecaoCreditoARealizar,
			boolean parcelamentoLojaVirtual);

	public void atualizarCobrancaDocumentoAposEncerrarOS(OrdemServico OS) throws ControladorException;

	public void gerarResumoAcoesCobrancaCronogramaEncerrarOS(Object[] dadosCobrancaAcaoAtividadeCronograma, int idFuncionalidadeIniciada)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarCobrancaDocumento(Collection colecaoCobrancaDocumento) throws ControladorException;

	public void atualizarSituacaoCobrancaDocumentoItemAPartirPagamento(Pagamento pagamento, Integer idCobrancaDebitoSituacao) throws ControladorException;

	public Collection<TransferenciasDebitoHelper> consultarContasTransferidas(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ControladorException;

	public Collection<TransferenciasDebitoHelper> consultarDebitosACobrarTransferidos(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ControladorException;

	public Collection<TransferenciasDebitoHelper> consultarGuiasDePagamentoTransferidas(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ControladorException;

	public Collection<TransferenciasDebitoHelper> consultarCreditosARealizarTransferidos(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper)
			throws ControladorException;

	public Collection<Usuario> obterNomeCPFTestemunhas(Integer unidadeUsuario) throws ControladorException;

	public void incluirDebitoACobrarEntradaParcelamentoNaoPaga(int idFuncionalidadeIniciada) throws ControladorException;

	public Integer inserirComandoEmpresaCobrancaConta(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, Usuario usuarioLogado)
			throws ControladorException;

	public Date obterDataValidadeDocumentoCobranca(CobrancaDocumento cobrancaDocumento, Usuario usuario, Date maiorDataVencimentoContas)
			throws ControladorException;

	public Collection<ResolucaoDiretoria> pesquisarResolucaoDiretoriaMaiorDataVigenciaInicioPermissaoEspecial() throws ControladorException;

	public Collection<GerarArquivoTextoContasCobrancaEmpresaHelper> pesquisarDadosGerarArquivoTextoContasCobrancaEmpresa(Integer idEmpresa,
			Date comandoInicial, Date comandoFinal, int pagina) throws ControladorException;

	public Integer pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaCount(Integer idEmpresa, Date comandoInicial, Date comandoFinal)
			throws ControladorException;

	public Integer pesquisarQtdeRotasSemCriteriosParaAcoesCobranca(PesquisarQtdeRotasSemCriteriosParaAcoesCobranca filtro) throws ControladorException;

	public Collection<ParcelamentoDescontoAntiguidade> obterParcelamentoDescontoAntiguidadeParaConta(ParcelamentoPerfil parcelamentoPerfil, Conta conta)
			throws ControladorException;

	public void cancelarDocumentosCobrancaDoCronogramaOuEventual(Usuario usuarioLogado, Integer idCobrancaAcaoAtividadeCronograma,
			Integer idCobrancaAcaoAtividadeComando) throws ControladorException;

	public boolean verificarCancelamentoDocumentosCobranca(Integer idCobrancaAcaoAtividadeCronograma, Integer idCobrancaAcaoAtividadeComando)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresa(RelatorioPagamentosContasCobrancaEmpresaHelper helper)
			throws ControladorException;

	public Integer pesquisarDadosGerarRelatorioPagamentosContasCobrancaEmpresaCount(Integer idEmpresa, Integer referenciaPagamentoInicial,
			Integer referenciaPagamentoFinal) throws ControladorException;

	public Collection<GerarExtensaoComandoContasCobrancaEmpresaHelper> pesquisarDadosGerarExtensaoComandoContasCobrancaEmpresa(Integer idEmpresa,
			Date comandoInicial, Date comandoFinal, int numeroIndice) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void inserirExtensaoComandoContasCobrancaEmpresa(ComandoEmpresaCobrancaContaExtensao comandoEmpresaCobrancaContaExtensao,
			Collection colecaoGerarExtensaoComandoContasCobrancaEmpresaHelper) throws ControladorException;

	public void gerarMovimentoExtensaoContasEmCobranca(Integer idLocalidade, int idFuncionalidadeIniciada) throws ControladorException;

	public Collection<RelatorioRelacaoParcelamentoAnaliticoBean> filtrarRelacaoParcelamentoAnalitico(FiltrarRelacaoParcelamentoHelper filtrarRelacaoParcelamento)
			throws ControladorException;

	public void verificaSeExisteParcelasEmAtraso(Integer idImovel, Integer idResolucaoDiretoria, Integer refInicialInformada, Integer refFinalInformada)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosGerarRelatorioImoveisComAcordo(Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idGerenciaRegional, Date dataInicialAcordo, Date dataFinalAcordo, Integer rotaInicial, Integer rotaFinal, Integer sequencialRotaInicial,
			Integer sequencialRotaFinal, Integer idSetorComercialInicial, Integer idSetorComercialFinal) throws ControladorException;

	public Integer pesquisarDadosGerarRelatorioImoveisComAcordoCount(Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idGerenciaRegional, Date dataInicialAcordo, Date dataFinalAcordo, Integer rotaInicial, Integer rotaFinal, Integer sequencialRotaInicial,
			Integer sequencialRotaFinal, Integer idSetorComercialInicial, Integer idSetorComercialFinal) throws ControladorException;

	public void distribuirMetasCiclo(CicloMeta cicloMeta) throws ControladorException;

	public void atualizarDistribuicaoMetasCicloGrupoLocalidade(CicloMeta cicloMeta, Collection<InformarCicloMetaGrupoHelper> helpersLocalidade)
			throws ControladorException;

	public TreeMap<String, InformarCicloMetaGrupoHelper> consultarColecaoCicloMetaGrupo(CicloMeta cicloMeta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void inserirSituacaoEspecialCobranca(Collection colecaoImoveisParaSerInseridos, SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper,
			Usuario usuarioLogado, Integer idCobrancaSituacaoTipo, Integer anoMesReferenciaInicial, Integer anoMesReferenciaFinal) throws ControladorException;

	public Integer inserirCobrancaSituacaoComando(SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper, boolean retirar) throws ControladorException;

	public Integer pesquisarResolucaoDiretoriaComPercentualDoacao() throws ControladorException;

	public void gerarCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista(Integer idRota, Integer idFuncionalidadeIniciada) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRotasPorGrupoFaturamento(Integer idGrupoFaturamento) throws ControladorException;

	public void emitirCartasCampanhaSolidariedadeCriancaParaNegociacaoAVista(Integer idGrupoFaturamento, Integer idFuncionalidadeIniciada)
			throws ControladorException;

	public Collection<ImpostoDeduzidoHelper> pesquisarImpostosPorClienteResponsavelFederal(Integer anoMes, Integer clienteID, String tipoRelatorio)
			throws ControladorException;

	public IndicesAcrescimosImpontualidade pesquisarIndiceAcrescimoImpontualidade(int anoMesReferenciaDebito) throws ControladorException;

	public Collection<RelatorioNotificacaoDebitoBean> gerarRelatorioNotificacaoDebito(Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			int tamanhoMaximoDebito, String quantidadeRelatorios, String tipoEnderecoRelatorio) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public List consultarColecaoCicloMetaGrupoRelatorio(CicloMeta cicloMeta) throws ControladorException;

	public Object[] pesquisarDadosPopupExtensaoComando(Integer idComando, Date dateInicial, Date dateFinal) throws ControladorException;

	public List<RelatorioContaBean> pesquisarDadosContaRelatorio(Integer anoMes, Integer idFaturamentoGrupo, Integer idLocalidadeInicial,
			Integer idLocalidadeFinal, Integer codigoSetorComercialInicial, Integer codigoSetorComercialFinal, Short codigoRotaInicial, Short codigoRotaFinal,
			Short sequencialRotaInicial, Short sequencialRotaFinal, String indicadorEmissao, String indicadorOrdenacao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public List consultarColecaoAcaoCobranca(RelatorioAcompanhamentoAcoesCobrancaHelper helper) throws ControladorException;

	public Collection<Integer> pesquisarIdsAcoesCiclo(Collection<Integer> idsAcao, Integer anoMesReferencia) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public List filtrarCobrancaDocumento(FiltrarDocumentoCobrancaHelper filtro) throws ControladorException;

	public Integer filtrarCobrancaDocumentoCount(FiltrarDocumentoCobrancaHelper filtro) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public List filtrarSupressoesReligacoesReestabelecimentos(FiltroSupressoesReligacoesReestabelecimentoHelper filtro) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarParcelamentosSituacaoNormal(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterUnidadeNegocioPagamentosEmpresaCobrancaConta() throws ControladorException;

	public Collection<CobrancaDocumento> consultarCobrancaDocumento(FiltrarDocumentoCobrancaHelper filtro) throws ControladorException;

	public void removerCicloMetaGrupo(Integer idCicloMeta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarQuantidadeImoveisPorGrupoLocalidade(Collection colecaoIdsSituacaoLigacaoAgua) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void transferirRotasEntreGrupoEmpresa(FaturamentoGrupo grupoFaturamentoDestino, CobrancaGrupo grupoCobrancaDestino,
			Empresa empresaFaturamentoDestino, Empresa empresaCobrancaDestino, Collection colecaoRotas, Usuario usuarioLogado) throws ControladorException;

	public DocumentoTipo pesquisarTipoAcaoCobrancaParaRelatorio(Integer idCobrancaAcaoAtividadeComando, Integer idCobrancaAcaoAtividadeCronograma)
			throws ControladorException;

	public Collection<RelatorioComandoDocumentoCobrancaHelper> gerarRelatorioComandoDocumentoCobranca(Integer idCobrancaAcaoCronograma,
			Integer idCobrancaAcaoComando) throws ControladorException;

	public void gerarCartasDeFinalDeAno(Integer idRota, Integer idFuncionalidadeIniciada) throws ControladorException;

	public void emitirCartasDeFinalDeAno(Integer idGrupoFaturamento, Integer idFuncionalidadeIniciada) throws ControladorException;

	public boolean existeCobrancaDocumentoDoImovel(Integer idImovel, Integer idDocumentoTipo) throws ControladorException;

	public void emitirDocumentoCobranca(CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, Date dataAtualPesquisa, CobrancaAcao acaoCobranca, CobrancaGrupo grupoCobranca,
			CobrancaCriterio cobrancaCriterio) throws ControladorException;

	public void emitirDocumentoCobranca(CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, CobrancaAcao acaoCobranca, CobrancaGrupo grupoCobranca) throws ControladorException;

	public Integer pesquisarQuantidadeImoveisPorGrupoCobranca(Integer idCobrancaGrupo, Integer gerencia, Integer unidade, Integer localidade,
			Integer setorComercial, Integer quadra) throws ControladorException;

	public Integer pesquisarQuantidadeImoveisPorComandoEventual(Integer idCobrancaAcaoAtividadeComando, Integer gerencia, Integer unidade, Integer localidade,
			Integer setorComercial, Integer quadra) throws ControladorException;

	public ObterDadosConfirmarCartaoCreditoDebitoHelper obterDadosConfirmarCartaoCreditoDebito(Short modalidadeCartao, Integer matriculaImovel)
			throws ControladorException;

	public void verificarValidadeData(Date dataConfirmacaoOperadora, Integer idCliente, Integer idArrecadacaoForma) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void verificarSomatorio(Collection colecaoTransacao, BigDecimal valorTotalParaQuitacao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void confirmarCartaoCredito(Integer idParcelamentoSelecionado, Collection colecaoTransacao, Usuario usuarioLogado) throws ControladorException;

	public int validarParcelamentoCartaoCredito(Parcelamento parcelamento) throws ControladorException;

	public void confirmarCartaoDebito(ParcelamentoCartaoCreditoHelper parcelamentoCartaoCreditoHelper, Collection<ContaValoresHelper> colecaoConta,
			Collection<GuiaPagamentoGeral> colecaoGuia, Collection<DebitoACobrarGeral> colecaoDebitoACobrar, Collection<Parcelamento> colecaoParcelamento,
			Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoDebitosDeParcelamento,
			Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoCreditosDeParcelamento, Usuario usuarioLogado) throws ControladorException;

	public ArrecadadorContratoTarifa pesquisarArrecadadorContratoTarifa(Integer idArrecadador, Integer idArrecadacaoForma) throws ControladorException;

	public GuiaPagamento pesquisarGuiaPagamentoCartaoCredito(Integer idCliente, Date dataVencimento) throws ControladorException;

	public GuiaPagamento inserirGuiaPagamentoCliente(Integer idCliente, Date dataVencimento, BigDecimal valorDebito, Integer idDebitoTipo, Usuario usuarioLogado)
			throws ControladorException;

	public void atualizarGuiaPagamentoCartaoCredito(GuiaPagamento guiaPagamento) throws ControladorException;

	public BigDecimal calcularValorDeducao(Integer idArrecadador, BigDecimal valorPagamento, Integer idArrecadacaoForma) throws ControladorException;

	public void gerarAtualizarAvisoDeducoes(AvisoBancario avisoBancario, BigDecimal valorDeducao) throws ControladorException;

	public Localidade pesquisarLocalidadeSede() throws ControladorException;

	public Integer inserirDocumentosReceberFaixaDiasVencidos(DocumentosReceberFaixaDiasVencidos documentosReceberFaixaDiasVencidos, Usuario usuarioLogado)
			throws ControladorException;

	public String verificarExistenciaFaixaInicial(Integer valorInicialFaixa) throws ControladorException;

	public Boolean verificarExistenciaFaixaFinal(Integer valorFinalFaixa) throws ControladorException;

	public Collection<RelatorioEmitirDeclaracaoTransferenciaDebitoBean> gerarRelatorioEmitirDeclaracaoTransferenciaDebitoCredito(String clienteUsuarioDestino,
			String clienteUsuarioOrigem, String valorNovaConta, String indicadorTipoEmissao, String municipio) throws ControladorException;

	public BigDecimal calcularValorTotalDebitos(ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper);

	public BigDecimal calcularValorContas(ObterDebitoImovelOuClienteHelper debitoImovelClienteHelper);

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRelatorioDocumentosAReceber(FiltroRelatorioDocumentosAReceberHelper helper) throws ControladorException;

	public Integer countRelatorioDocumentosAReceber(FiltroRelatorioDocumentosAReceberHelper helper) throws ControladorException;

	public void verificarQuantidadeParcelasInformada(DebitoACobrar debitoACobrar, Short quantidadeParcelas) throws ControladorException;

	public void emitirDocumentoCobrancaCartasCampanha(CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, CobrancaAcao acaoCobranca, CobrancaGrupo grupoCobranca) throws ControladorException;

	public CobrancaAcao pesquisarAcaoCobrancaParaRelatorio(Integer idCobrancaAcaoAtividadeComando, Integer idCobrancaAcaoAtividadeCronograma)
			throws ControladorException;

	public void gerarAtividadeAcaoCobranca(CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, Rota rota, CobrancaAcao acaoCobranca, CobrancaAtividade atividadeCobranca,
			Integer indicadorCriterio, CobrancaCriterio criterioCobranca, Cliente cliente, ClienteRelacaoTipo relacaoClienteImovel,
			String anoMesReferenciaInicial, String anoMesReferenciaFinal, Date dataVencimentoInicial, Date dataVencimentoFinal, Date dataAtual,
			int idFuncionalidadeIniciada, Cliente clienteSuperior, Integer idCobrancaDocumentoControleGeracao) throws ControladorException;

	public void atualizarComandoAtividadeAcaoCobranca(CobrancaGrupo grupoCobranca, int anoMesReferenciaCicloCobranca,
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma, CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando,
			CobrancaAcao acaoCobranca, Integer indicadorCriterio, CobrancaCriterio criterioCobranca, int idFuncionalidadeIniciada,
			Integer idCobrancaDocumentoControleGeracao) throws ControladorException;

	public void emitirDocumentoCobranca(CobrancaGrupo grupoCobranca, CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, CobrancaAcao acaoCobranca, CobrancaCriterio criterioCobranca, Date dataAtual,
			int idFuncionalidadeIniciada) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelCobrancaSituacaoPorImovel(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosImovelCobrancaSituacaoPorImovel(Integer idImovel) throws ControladorException;

	public void gerarDocumentoCobrancaImpressaoCartasCampanha(CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, Date dataAtualPesquisa, CobrancaAcao acaoCobranca, CobrancaGrupo grupoCobranca,
			CobrancaCriterio cobrancaCriterio) throws ControladorException;

	public void emitirCartasCampanha(CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma,
			CobrancaAcaoAtividadeComando cobrancaAcaoAtividadeComando, Date dataAtualPesquisa, CobrancaAcao acaoCobranca, CobrancaGrupo grupoCobranca,
			CobrancaCriterio cobrancaCriterio) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelCobrancaSituacao(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarParcelamentoPagamentoCartaoCredito(Collection colecaoParcelamentoPagamentoCartaoCredito, Usuario usuarioLogado)
			throws ControladorException;

	public boolean parcelamentoPagamentoCartaoCreditoJaConfirmado(Integer idParcelamento) throws ControladorException;

	public Collection<RelatorioRelacaoParcelamentoCartaoCreditoBean> filtrarRelacaoParcelamentoCartaoCredito(
			FiltrarRelacaoParcelamentoHelper filtrarRelacaoParcelamento) throws ControladorException;

	public void gerarPrescreverDebitosDeImoveis(Integer idFuncionalidadeIniciada, Integer anoMesFaturamento, Date dataPrescricao, Integer usuario,
			String idsCobrancaSituacao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterCobrancaSituacaoParaPrescreverDebitos() throws ControladorException;

	public void removerCobrancaAcaoAtividadeComandoFiscalizacaoSituacao(Integer idComando) throws ControladorException;

	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoValidoImovel(Integer idImovel, Integer idDocumentoTipo, Integer idAcaoCobranca)
			throws ControladorException;

	public NegociacaoOpcoesParcelamentoHelper calcularValorDosDescontosPagamentoAVista(ObterOpcoesDeParcelamentoHelper helper) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer recuperaMaiorAnoMesContasParcelamento(Collection colecaoContas) throws ControladorException;

	public void processarEncerramentoOSAcaoCobranca(Integer dadosAtividadeCronograma, Integer idCobrancaAcaoAtividadeCronograma, int idFuncionalidadeIniciada)
			throws ControladorException;

	public Collection<Integer> pesquisarOrdemServicoParaEncerrar(Integer idCobrancaAcaoCronograma) throws ControladorException;

	public Collection<Object[]> pesquisarAtividadeCronograma(Integer idCobrancaAcao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoNegativacaoLigacaoAguaPorSituacaoDebito(DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper, int idSituacaoDebito)
			throws ControladorException;

	public Collection<RelatorioVisitaCobrancaBean> gerarRelatorioVisitaCobranca(Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando,
			int tamanhoMaximoDebito, String quantidadeRelatorios, Collection<CobrancaDocumento> colecaoDocumentoCobranca) throws ControladorException;

	public Collection<RelatorioDocumentoCobrancaOrdemCorteBean> gerarRelatorioDocumentoCobrancaOrdemCorte(Integer idCobrancaAcaoCronograma,
			Integer idCobrancaAcaoComando, int tamanhoMaximoDebito, String quantidadeRelatorios) throws ControladorException;

	public Collection<RelatorioDocumentoCobrancaOrdemFiscalizacaoBean> gerarRelatorioDocumentoCobrancaOrdemFiscalizacao(Integer idCobrancaAcaoCronograma,
			Integer idCobrancaAcaoComando, int tamanhoMaximoDebito, String quantidadeRelatorios) throws ControladorException;

	public List<String> pesquisarTipoDeCorte() throws ControladorException;

	public List<String> pesquisarOcorrenciasFiscalizacao() throws ControladorException;

	public Collection<ImpostoDeduzidoHelper> pesquisarImpostosArrecadacaoClienteResponsavelFederal(Integer anoMes, Integer clienteID, String tipoRelatorio)
			throws ControladorException;

	public Collection<RelatorioBoletimMedicaoCobrancaHelper> pesquisarItensServico(FiltrarRelatorioBoletimMedicaoCobrancaHelper helper,
			String tipoCobrancaBoletim) throws ControladorException;

	public Integer pesquisarAcoesEncerradasCronograma(Integer anoMesReferencia, Integer idCobrancaGrupo) throws ControladorException;

	public Object[] obterQuantidadeOSBoletimMedicaoCobranca(RelatorioBoletimMedicaoCobrancaHelper helper) throws ControladorException;

	public Object[] obterSomatorioOSBoletimMedicaoCobranca(RelatorioBoletimMedicaoCobrancaHelper helper) throws ControladorException;

	public Object[] obterQuantidadeOSBoletimMedicaoCobrancaDesconto(RelatorioBoletimMedicaoCobrancaHelper helper) throws ControladorException;

	public Object[] obterTotalizacaoOSBoletimMedicaoCobrancaSucesso(RelatorioBoletimMedicaoCobrancaHelper helper) throws ControladorException;

	public Object[] pesquisarDadosBoletimMedicaoCobranca(Integer anoMesReferencia, Integer idCobrancaGrupo) throws ControladorException;

	public RelatorioAnalisePerdasCreditosBean gerarRelatorioAnalisePerdasCreditos(String anoMesReferencia) throws ControladorException;

	public int maiorAnoMesReferenciaDocumentosAReceberResumo() throws ControladorException;

	public void gerarBoletimMedicao(Integer idGrupoCobranca, Integer referencia, Usuario usuarioLogado) throws ControladorException;

	public Collection<Object[]> pesquisarDadosPopupExtensaoComandoImovelPerfil(Integer idComando) throws ControladorException;

	public Collection<Object[]> pesquisarDadosPopupExtensaoComandoGerenciaRegional(Integer idComando) throws ControladorException;

	public Collection<Object[]> pesquisarDadosPopupExtensaoComandoUnidadeNegocio(Integer idComando) throws ControladorException;

	public Integer pesquisarCobrancaSituacao(Integer codigoConstante) throws ControladorException;

	public Integer pesquisarReferenciaContaPorId(Integer idConta) throws ControladorException;

	public ContratoParcelamento pesquisarContratoParcelamento(String numeroParcelamento) throws ControladorException;

	public void verificarPossibilidadeCancelamentoContratoParcelamento(ContratoParcelamento contratoParcelamento) throws ControladorException;

	public Collection<ConsultarComandosContasCobrancaEmpresaHelper> pesquisarConsultarComandosContasCobrancaEmpresa(Integer idEmpresa, Date comandoInicial,
			Date comandoFinal, int pagina) throws ControladorException;

	public Object[] pesquisarDadosPopupExtensaoComandoCobranca(Integer idComando, Date dateInicial, Date dateFinal) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarValorTotalCobrancaComandoEmpresaPorImovel(Integer idComando) throws ControladorException;

	public void encerrarComandosCobrancaPorEmpresa(Integer idFuncionalidadeIniciada, String idEmpresa, Usuario usuarioLogado, Integer idComando,
			Integer idCobrancaSituacao) throws ControladorException;

	public void movimentarOrdemServicoEncerrarOS(MovimentarOrdemServicoEncerrarOSHelper helper, Usuario usuarioLogado) throws ControladorException;

	public Collection<Integer> pesquisarIdsImoveis(MovimentarOrdemServicoGerarOSHelper helper) throws ControladorException;

	public Collection<Object[]> pesquisarDadosOSGeradasPelaEmpresa(Integer idComando, Integer idTipoServico) throws ControladorException;

	public Collection<Object[]> pesquisarDadosOSRegistroAtendimento(Integer idComando, Integer idTipoServico) throws ControladorException;

	public void cancelarContratoParcelamentoCliente(ContratoParcelamento contratoParcelamento, Usuario usuarioLogado) throws ControladorException;

	public void atualizarSituacaoCobrancaDocumentoItemAPartirPagamento(Pagamento pagamento, Integer idCobrancaDebitoSituacao, Date dataPagamneto)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection<ContaValoresHelper> pesquisarContasDebito(Integer idCliente, Short relacaoTipo, Integer idImovel, Collection idImoveis,
			Collection idImoveisAtuais, int indicadorDebito, int indicadorPagamento, int indicadorConta, int indicadorCalcularAcrescimoImpontualidade,
			String anoMesInicialReferenciaDebito, String anoMesFinalReferenciaDebito, Date anoMesInicialVencimentoDebito, Date anoMesFinalVencimentoDebito,
			String anoMesArrecadacao, int indicadorDividaAtiva) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasImovelDataVencimentoOriginal(Integer idImovel, int indicadorPagamento, int indicadorConta,
			String anoMesInicialReferenciaDebito, String anoMesFinalReferenciaDebito, Date anoMesInicialVencimentoDebito, Date anoMesFinalVencimentoDebito,
			int indicadorDividaAtiva) throws ControladorException;

	public StringBuilder validarArquivoTxtEncerramentoOSCobranca(FileItem arquivoAnexo) throws ControladorException;

	public void processarArquivoTxtEncerramentoOSCobranca(int idFuncionalidadeIniciada, String idEmpresa, Usuario usuario, StringBuilder stringBuilder,
			String nomeArquivo) throws ControladorException;

	public List<Object[]> pesquisarOrdensServicoContasPagasParceladas() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterColecaoEmpresaCobrancaContaResultadoporImovel(Integer id) throws ControladorException;

	public void gerarArquivoTxtOSContasPagasParceladas(int idFuncionalidadeIniciada) throws ControladorException;

	public Object[] pesquisarDadosQtdContasEDiasVencidos(Integer idComando) throws ControladorException;

	public Collection<CmdEmpresaCobrancaContaLigacaoAguaSituacao> pesquisarColecaoLigacaoAguaSituacaoPorComandoEmpresaCobrancaConta(Integer idComando)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosPopupExtensaoComandoAguaSituacao(Integer idComando) throws ControladorException;
	
	public Date obterDataVencimentoEntradaParcelamento(Integer idParcelamento) throws ControladorException;
}
