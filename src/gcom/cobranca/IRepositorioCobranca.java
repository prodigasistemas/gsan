package gcom.cobranca;

import gcom.arrecadacao.ArrecadadorContratoTarifa;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.aviso.AvisoDeducoes;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.atendimentopublico.registroatendimento.EspecificacaoTipoValidacao;
import gcom.batch.auxiliarbatch.CobrancaDocumentoControleGeracao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ConsultarTransferenciasDebitoHelper;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.cobranca.bean.DadosPesquisaCobrancaDocumentoHelper;
import gcom.cobranca.bean.EmitirDocumentoCobrancaBoletimCadastroHelper;
import gcom.cobranca.bean.EmitirDocumentoCobrancaHelper;
import gcom.cobranca.bean.FiltrarDocumentoCobrancaHelper;
import gcom.cobranca.bean.FiltrarRelacaoParcelamentoHelper;
import gcom.cobranca.bean.FiltroSupressoesReligacoesReestabelecimentoHelper;
import gcom.cobranca.bean.PesquisarQtdeRotasSemCriteriosParaAcoesCobranca;
import gcom.cobranca.bean.SituacaoEspecialCobrancaHelper;
import gcom.cobranca.cobrancaporresultado.ArquivoTextoNegociacaoCobrancaEmpresaHelper;
import gcom.cobranca.cobrancaporresultado.NegociacaoCobrancaEmpresa;
import gcom.cobranca.contratoparcelamento.ContratoParcelamento;
import gcom.cobranca.parcelamento.ParcDesctoInativVista;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoDescontoInatividade;
import gcom.cobranca.parcelamento.ParcelamentoFaixaValor;
import gcom.cobranca.parcelamento.ParcelamentoItem;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamento;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.gui.cobranca.cobrancaporresultado.MovimentarOrdemServicoGerarOSHelper;
import gcom.gui.relatorio.cobranca.FiltroRelatorioDocumentosAReceberHelper;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.relatorio.cobranca.RelatorioAcompanhamentoAcoesCobrancaHelper;
import gcom.relatorio.cobranca.RelatorioAnalisePerdasCreditosBean;
import gcom.relatorio.cobranca.RelatorioBoletimMedicaoCobrancaHelper;
import gcom.relatorio.cobranca.parcelamento.RelacaoParcelamentoRelatorioHelper;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;
import gcom.util.filtro.Filtro;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IRepositorioCobranca {

	public Collection pesquisarImoveisCortados(String situacaoEsgotoLigado, String situacaoAguaCortado, Date anoMesReferenciaFaturamento) throws ErroRepositorioException;

	public String pesquisarImoveisHidrometroAguaConsumoFaturadoReal(String id, String anoMesFaturamento, String consumoTipoReal, String ligacaoTipoLigacaoAgua) throws ErroRepositorioException;

	public void religarImovelCortado(String id, String situacaoAguaLigado, Date dataReligacaoAgua) throws ErroRepositorioException;

	public Collection pesquisarContasImoveis(Collection idsImoveis, int indicadorPagamento, int indicadorConta, String contaSituacaoNormal, String contaSituacaoRetificada,
			String contaSituacaoIncluida, String contaSituacaoParcelada, String anoMesInicialReferenciaDebito, String anoMesFinalReferenciaDebito, Date anoMesInicialVecimentoDebito,
			Date anoMesFinalVencimentoDebito, int indicadorDividaAtiva) throws ErroRepositorioException;

	public Collection pesquisarContasCliente(Integer idCliente, Short relacaoTipo, int indicadorPagamento, int indicadorConta, String contaSituacaoNormal, String contaSituacaoRetificada,
			String contaSituacaoIncluida, String contaSituacaoParcelada, String anoMesInicialReferenciaDebito, String anoMesFinalReferenciaDebito, Date anoMesInicialVecimentoDebito,
			Date anoMesFinalVencimentoDebito, int indicadorDividaAtiva) throws ErroRepositorioException;

	public Collection pesquisarContasImovel(Integer idImovel, int indicadorPagamento, int indicadorConta, String contaSituacaoNormal, String contaSituacaoRetificada, String contaSituacaoIncluida,
			String contaSituacaoParcelada, String anoMesInicialReferenciaDebito, String anoMesFinalReferenciaDebito, Date anoMesInicialVecimentoDebito, Date anoMesFinalVencimentoDebito,
			int indicadorDividaAtiva, boolean contaComDebitoPreterito) throws ErroRepositorioException;

	public int pesquisarQuantidadeContasVencidasPorImovel(Integer idImovel, int indicadorPagamento, int indicadorConta, int indicadorDividaAtiva) throws ErroRepositorioException;

	public Collection pesquisarValorTotalPagamentoMenorDataPagamento(String idConta) throws ErroRepositorioException;

	public Collection pesquisarValorTotalGuiaPagamentoMenorDataGuiaPagamento(String idGuiaPagamento) throws ErroRepositorioException;

	public Collection pesquisarDebitosACobrarImovel(String idImovel, String situacaoNormal) throws ErroRepositorioException;

	public Collection pesquisarIDImoveisClienteImovel(String codigoCliente, Short relacaoTipo) throws ErroRepositorioException;

	public Collection pesquisarIDImoveisClienteConta(String codigoCliente, Short relacaoTipo) throws ErroRepositorioException;

	public Collection pesquisarDebitosACobrarCliente(Collection idsImoveis, String situacaoNormal) throws ErroRepositorioException;

	public Collection pesquisarCreditosARealizarCliente(Collection idsImoveis, String situacaoNormal) throws ErroRepositorioException;

	public Collection pesquisarCreditosARealizarImovel(String idImovel, String situacaoNormal) throws ErroRepositorioException;

	public Collection pesquisarGuiasPagamentoCliente(Integer idCliente, int indicadorPagamento, String situacaoNormal, Short clienteRelacaoTipo, Date dataVencimentoInicial, Date dataVencimentoFinal)
			throws ErroRepositorioException;

	public IndicesAcrescimosImpontualidade pesquisarIndiceAcrescimoImpontualidade(int anoMesReferenciaDebito) throws ErroRepositorioException;

	public IndicesAcrescimosImpontualidade pesquisarMenorIndiceAcrescimoImpontualidade(int anoMesReferenciaDebito) throws ErroRepositorioException;

	public Collection pesquisarGuiasPagamentoImovel(Integer idImovel, int indicadorPagamento, String situacaoNormal, Date dataVencimentoInicial, Date dataVencimentoFinal)
			throws ErroRepositorioException;

	public String verificarDataOpcao(String matriculaImovel, Date dataOpcao, String identificadorCliente, String codigoAgencia) throws ErroRepositorioException;

	public String verificarDataOpcaoExclusao(String matriculaImovel, Date dataOpcao, String identificadorCliente) throws ErroRepositorioException;

	public String verificarDebitoAutomatico(String matriculaImovel) throws ErroRepositorioException;

	public void atualizarDataExclusao(String matriculaImovel, Integer idAgencia) throws ErroRepositorioException;

	public void atualizarDataExclusao(String matriculaImovel) throws ErroRepositorioException;

	public void inserirDebitoAutomatico(DebitoAutomatico debitoAutomatic) throws ErroRepositorioException;

	public void atualizarIndicadorDebitoAutomatico(String matriculaImovel, Integer indicadorDebito) throws ErroRepositorioException;

	public String verificarDebitoAutomaticoBancoAgencia(String codigoBanco, String codigoAgencia) throws ErroRepositorioException;

	public Collection<CobrancaAcaoAtividadeCronograma> pesquisarCobrancaAcaoAtividadeCronograma() throws ErroRepositorioException;

	public Collection<CobrancaAcaoAtividadeComando> pesquisarCobrancaAcaoAtividadeComando() throws ErroRepositorioException;

	public Object[] consultarDadosClienteImovelUsuario(Imovel imovel) throws ErroRepositorioException;

	public Collection consultarConsumoHistoricoImoveisVinculados(ConsumoHistorico consumoHistorico) throws ErroRepositorioException;

	public Object[] consultarDadosConsumoTipoConsumoHistorico(ConsumoHistorico consumoHistorico) throws ErroRepositorioException;

	public Object[] obterConsumoHistoricoMedicaoIndividualizada(Imovel imovel, LigacaoTipo ligacaoTipo, int anoMesReferencia) throws ErroRepositorioException;

	public void atualizarSituacaoConta(String codigoConta, int situacaoAtual, int anoMesReferenciaContabil) throws ErroRepositorioException;

	public void atualizarSituacaoGuiaPagamento(String codigoGuiaPagamento, int situacaoAtualGuia, int anoMesReferenciaContabil) throws ErroRepositorioException;

	public void atualizarParcelamento(Integer codigoParcelamento, Integer parcelamentoSituacao, String motivo, Integer usuarioId) throws ErroRepositorioException;

	public void atualizarSituacaoDebitoACobrar(String codigoDebitoACobrar, int situacaoAtualDebito, int anoMesReferenciaContabil) throws ErroRepositorioException;

	public void atualizarSituacaoCreditoARealizar(String codigoCreditoARealizar, int situacaoAtualCredito, int anoMesReferenciaContabil) throws ErroRepositorioException;

	public void removerDebitoACobrarCategoriaDoParcelamento(Integer idDebito) throws ErroRepositorioException;

	public void removerDebitoACobrarDoParcelamento(Integer codigoImovel, Integer codigoParcelamento) throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitoACobrarDoParcelamento(Integer codigoImovel, Integer codigoParcelamento) throws ErroRepositorioException;

	public void removerCreditoARealizarDoParcelamento(Integer codigoImovel, Integer codigoParcelamento) throws ErroRepositorioException;

	public void removerGuiaPagamentoDoParcelamento(Integer codigoImovel, Integer codigoParcelamento) throws ErroRepositorioException;

	public void removerGuiaPagamentoCobrancaDoParcelamento(Integer idGuiaPagamento) throws ErroRepositorioException;

	public Collection pesquisarCobrancaDocumentoItem(Integer idImovel, int numeroSequencialDocumento) throws ErroRepositorioException;

	public Collection<Conta> pesquisarCobrancaDocumentoItem(Integer idCobrancaDocumento) throws ErroRepositorioException;

	public void inserirCobrancaSituacaoHistorico(Collection collectionCobrancaSituacaoHistorico) throws ErroRepositorioException;

	public void atualizarAnoMesCobrancaSituacaoHistorico(SituacaoEspecialCobrancaHelper situacaoEspecialCobrancaHelper, Integer anoMesReferencia, Collection colecaoImoveis,
			Integer idFaturamentoSituacaoConsumo) throws ErroRepositorioException;

	public Collection pesquisarIDContasClienteConta(String codigoCliente, Short relacaoTipo) throws ErroRepositorioException;

	public void atualizarDadosParcelamentoImovel(Integer codigoImovel, Short numeroParcelamento, Short numeroReparcelamento, Short numeroReparcelamentoConsecutivo) throws ErroRepositorioException;

	public Collection pesquisarParcelamentosSituacaoNormalNoMes(String parcelamentoSituacao, Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	public Collection pesquisarGuiaPagamentoDoParcelamento(String parcelamento) throws ErroRepositorioException;

	public Collection pesquisarPagamentoParaGuiaPagamentoDoParcelamento(String pagamento, Integer idImovel) throws ErroRepositorioException;

	public void removerCobrancaCriterioLinha(String[] idscobrancaCriterio) throws ErroRepositorioException;

	public Collection gerarRelacaoDebitos(String idImovelCondominio, String idImovelPrincipal, String idNomeConta, String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto, String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal, String intervaloMediaMinimaImovelInicial, String intervaloMediaMinimaImovelFinal, String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil, String idPocoTipo, String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial, String areaConstruidaFinal, String idCadastroOcorrencia, String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal, String setorComercialInicial, String setorComercialFinal, String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro, String municipio, String idTipoMedicao, String indicadorMedicao, String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal, String diaVencimento, String idCliente, String idClienteTipo, String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial, String numeroMoradoresFinal, String idAreaConstruidaFaixa, String ordenacao, int quantidadeImovelInicio, String indicadorCpfCnpj, 
			String cpfCnpj) throws ErroRepositorioException;

	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoParaEmitir(Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando, Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio) throws ErroRepositorioException;

	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoParaEmitirPorRota(Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando, Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio) throws ErroRepositorioException;

	public Collection<CobrancaDocumentoItem> selecionarCobrancaDocumentoItemReferenteConta(CobrancaDocumento cobrancaDocumento) throws ErroRepositorioException;

	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoParaRelatorio(Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando) throws ErroRepositorioException;

	public Integer obterQuantidadaeRelacaoImoveisDebitos(String idImovelCondominio, String idImovelPrincipal, String idNomeConta, String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto, String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal, String intervaloMediaMinimaImovelInicial, String intervaloMediaMinimaImovelFinal, String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal, String idImovelPerfil, String idPocoTipo, String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial, String areaConstruidaFinal, String idCadastroOcorrencia, String idConsumoTarifa, String idGerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal, String setorComercialInicial, String setorComercialFinal, String quadraInicial, String quadraFinal, String loteOrigem,
			String loteDestno, String cep, String logradouro, String bairro, String municipio, String idTipoMedicao, String indicadorMedicao, String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal, String diaVencimento, String idCliente, String idClienteTipo, String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial, String numeroMoradoresFinal, String idAreaConstruidaFaixa) throws ErroRepositorioException;

	public Integer pesquisarCobrancaCronogramaCount(Filtro filtro) throws ErroRepositorioException;

	public void removerCobrancaCronograma(Integer idGrupoCronogramaMes) throws ErroRepositorioException;

	public Integer pesquisarConsumoMedioConsumoHistoricoImovel(Integer imovelId) throws ErroRepositorioException;

	public Collection pesquisarCobrancaCriterioLinha(Integer idCriterioCobranca) throws ErroRepositorioException;

	public Collection pesquisarParcelamentoDescontoAntiguidade(Integer idParcelamentoPerfil) throws ErroRepositorioException;

	public Collection pesquisarParcelamentoDescontoInatividade(Integer idParcelamentoPerfil) throws ErroRepositorioException;

	public Collection pesquisarReparcelamentoConsecutivo(Integer idParcelamentoPerfil) throws ErroRepositorioException;

	public Collection<CobrancaAcaoAtividadeCronograma> pesquisarCobrancaAcaoAtividadeCronogramaComandadosNaoRealizados() throws ErroRepositorioException;

	public Collection<CobrancaAcaoAtividadeComando> pesquisarCobrancaAcaoAtividadeCronogramaEventuaisComandadosNaoRealizados() throws ErroRepositorioException;

	public Collection<EmitirDocumentoCobrancaHelper> pesquisarCobrancaDocumentoOrdemCorte(Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando, Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio) throws ErroRepositorioException;

	public Collection<EmitirDocumentoCobrancaHelper> pesquisarCobrancaDocumentoOrdemCortePorRota(Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando, Date dataEmissao,
			Integer idCobrancaAcao, int quantidadeCobrancaDocumentoInicio) throws ErroRepositorioException;

	public Collection<EmitirDocumentoCobrancaBoletimCadastroHelper> pesquisarCobrancaDocumentoBoletimCadastro(Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando, Date dataEmissao,
			Integer idCobrancaAcao, int quantidadeCobrancaDocumentoInicio) throws ErroRepositorioException;

	public Collection consultarImovelDocumentosCobranca(Integer idImovel, Integer numeroPagina) throws ErroRepositorioException;

	public Integer consultarQuantidadeImovelDocumentosCobranca(Integer idImovel) throws ErroRepositorioException;

	public Integer consultarQuantidadeImovelDocumentosItemCobranca(Integer idImovel) throws ErroRepositorioException;

	public Collection pesquisarParcelamentoRelatorio(Integer idParcelamento) throws ErroRepositorioException;

	public Collection pesquisarParcelamentoItemPorIdParcelamentoRelatorio(Integer idParcelamento) throws ErroRepositorioException;

	public ParcelamentoDescontoInatividade obterPercentualDescontoInatividade(Integer idPerfilParc, int qtdeMeses) throws ErroRepositorioException;

	public BigDecimal pesquisarServioAtualizacao(Integer idDocumentoCobranca) throws ErroRepositorioException;

	public void associarContaParcelamento(Conta conta) throws ErroRepositorioException;

	public Collection pesquisarCobrancaGrupoCronogramaMes() throws ErroRepositorioException;

	public Collection pesquisarCobrancaAcaoCronograma(int idCobrancaGrupoCronogramaMes) throws ErroRepositorioException;

	public Collection pesquisarDataRelizacaoCobrancaAtividadeAcaoConograma(int idCobrancaAcaoCronograma, int idCobrancaAtividade) throws ErroRepositorioException;

	public Collection pesquisarCobrancaAcao(int idCobrancaAcao) throws ErroRepositorioException;

	public Collection<DadosPesquisaCobrancaDocumentoHelper> pesquisarCobrancaDocumento(int idCobrancaAtividadeAcaoCronograma) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarOrdemServico(int idDocumentoCobranca) throws ErroRepositorioException;

	public Collection pesquisarAtendimentoMotivoEncerramento(int idAtendimentoMotivoEncerramento) throws ErroRepositorioException;

	public Collection pesquisarCobrancaDocumentoItem(int idCobrancaDocumento) throws ErroRepositorioException;

	public Collection pesquisarContaGeral(int idConta) throws ErroRepositorioException;

	public Object[] pesquisarContaHistorico(int idContaHistorico) throws ErroRepositorioException;

	public Object[] pesquisarConta(int idConta) throws ErroRepositorioException;

	public Date pesquisarParcelamentoConta(int idConta, int idParcelamentoSituacao) throws ErroRepositorioException;

	public Collection pesquisarGuiaPagamentoGeral(int idGuiaPagamento) throws ErroRepositorioException;

	public Object[] pesquisarGuiaPagamentoHistorico(int idGuiaPagamentoHistorico) throws ErroRepositorioException;

	public Object[] pesquisarGuiaPagamento(int idGuiaPagamento) throws ErroRepositorioException;

	public Date pesquisarParcelamentoGuiaPagamento(int idGuiaPagamento, int idParcelamentoSituacao) throws ErroRepositorioException;

	public Collection pesquisarDebitoACobrarGeral(int idDebitoACobrar) throws ErroRepositorioException;

	public Object[] pesquisarDebitoACobrarHistorico(int idDebitoACobrarHistorico) throws ErroRepositorioException;

	public Object[] pesquisarDebitoACobrar(int idDebitoACobrar) throws ErroRepositorioException;

	public Date pesquisarParcelamentoDebitoACobrar(int idDebitoACobrar, int idParcelamentoSituacao) throws ErroRepositorioException;

	public Date pesquisarMenorDataPagamentosContaHistorico(int idContaHistorico) throws ErroRepositorioException;

	public Date pesquisarMenorDataPagamentosConta(int idConta) throws ErroRepositorioException;

	public Date pesquisarMenorDataPagamentosGuiaPagamentoHistorico(int idGuiaPagamentoHistorico) throws ErroRepositorioException;

	public Date pesquisarMenorDataPagamentosGuiaPagamento(int idGuiaPagamento) throws ErroRepositorioException;

	public Date pesquisarMenorDataPagamentosDebitoACobrarHistorico(int idDebitoACobrarHistorico) throws ErroRepositorioException;

	public Date pesquisarMenorDataPagamentosDebitoACobrar(int idDebitoACobrar) throws ErroRepositorioException;

	public Collection pesquisarCobrancaCriterio(int idCobrancaCriterio) throws ErroRepositorioException;

	public void atualizarCobrancaDocumentoItem(Collection colecaoCobrancaDocumentoItem) throws ErroRepositorioException;

	public void atualizarCobrancaDocumento(Collection colecaoCobrancaDocumento) throws ErroRepositorioException;

	public Collection pesquisarDadosImovel(int idImovel) throws ErroRepositorioException;

	public void atualizarCobrancaAcaoAtividadeCronograma(int idCobrancaAcaoAtividadeCrongrama) throws ErroRepositorioException;

	public Collection pesquisarOrdemServicos(int idServicoTipo, Date dataPrevistaAtividadeEncerrar, Date dataPrevistaAtividadeEmitir, int indice) throws ErroRepositorioException;

	public ParcelamentoFaixaValor obterParcelamentoFaixaValor(Integer idParcelamentoQtdePrestacao, BigDecimal valorFaixa) throws ErroRepositorioException;

	public Object[] pesquisarParmsCobrancaDocumento(Integer idImovel, int numeroSequencialDocumento) throws ErroRepositorioException;

	public Collection pesquisarCobrancaDocumentoItemComConta(Integer idCobrancaDocumento) throws ErroRepositorioException;

	public Collection pesquisarCobrancaDocumentoItemComGuiaPagamento(Integer idCobrancaDocumento) throws ErroRepositorioException;

	public void atualizarGuiaPagamento(Collection idsGuiaPagamento, Date dataVencimento) throws ErroRepositorioException;

	public void deletarResumoCobrancaAcao(int idCobrancaAcaoCronograma) throws ErroRepositorioException;

	public Collection<ResolucaoDiretoria> pesquisarResolucaoDiretoriaMaiorDataVigenciaInicio() throws ErroRepositorioException;

	public ParcelamentoQuantidadeReparcelamento obterQtdeReparcelamentoPerfil(Integer idPerfilParc, Short numeroReparcelamentoConsecutivos) throws ErroRepositorioException;

	public void removerClienteGuiaPagamentoDoParcelamento(Integer idGuiaPagamento) throws ErroRepositorioException;

	public CobrancaCriterio pesquisarCriterioCobrancaRota(Integer idRota, Integer idCobrancaAcao) throws ErroRepositorioException;

	public Collection<CobrancaCriterioLinha> pesquisarCobrancaCriterioLinhaCriterio(Integer idCobrancaCriterio) throws ErroRepositorioException;

	public CobrancaCriterio pesquisarCobrancaCriterioIdCriterio(Integer idCobrancaCriterio) throws ErroRepositorioException;

	public Integer pesquisarDocumentoCobrancaAcaoPrecedente(Integer idImovel, Integer idServicoTipo, Short indicadorExecucao, Date dataEncerramento) throws ErroRepositorioException;

	public Integer pesquisarDocumentoCobrancaRelativoAcaoPrecedente(Integer idImovel, Integer idDocumentoTipo, Date dataEmissao, Date dataEmissaoValidade) throws ErroRepositorioException;

	public Collection pesquisarImovelCobrancaSituacao(Integer idImovel) throws ErroRepositorioException;

	public DebitoTipo pesquisarDebitoTipo(Integer idDebitoTipo) throws ErroRepositorioException;

	public CobrancaAcaoAtividadeCronograma pesquisarCobrancaAcaoAtividadeCronograma(Integer idCronogramaAtividadeAcaoCobranca) throws ErroRepositorioException;

	public CobrancaAcaoAtividadeComando pesquisarCobrancaAcaoAtividadeComando(Integer idCobrancaAcaoAtividadeComando) throws ErroRepositorioException;

	public Collection pesquisarCobrancaDocumentoItemContaGuiaPagamentoDebitoACobrar(Integer idCobrancaDocumento) throws ErroRepositorioException;

	public CobrancaDocumento pesquisarCobrancaDocumento(Integer idImovel, Integer idDocumentoTipo) throws ErroRepositorioException;

	public CobrancaAcaoAtividadeCronograma pesquisarCobrancaAcaoAtividadeCronogramaId(Integer idCobrancaAcaoAtividadeCronograma) throws ErroRepositorioException;

	public Collection pesquisarContaDoParcelamento(Integer parcelamento) throws ErroRepositorioException;

	public Collection pesquisarPagamentoParaContaDoParcelamento(String idConta) throws ErroRepositorioException;

	public Conta verificarContaDebitoCobrado(Integer idConta) throws ErroRepositorioException;

	public Collection obterConsumoMedioImovel(Integer idImovel) throws ErroRepositorioException;

	public Collection obterIdDebitoTipoDeFiscalizacaoSituacaoServicoACobrar(Integer idConta) throws ErroRepositorioException;

	public Collection obterNumeroPrestacaoDebitoCobrado(Integer idConta) throws ErroRepositorioException;

	public Collection pesquisarParcelamentosSemDebitos() throws ErroRepositorioException;

	public Collection pesqsuisarAcaoCobrancaPelaPrecedente(Integer idCobracaoAcao) throws ErroRepositorioException;

	public Object[] pesquisarIdDocumentoCobranca(Integer idImovel, Integer idDocumentoTipo, Date dataEmissao) throws ErroRepositorioException;

	public Date pesquisarMenorDataPagamentoGuiaPagamento(Integer idGuiaPagamento, Integer idImovel, Integer idDebitoTipo) throws ErroRepositorioException;

	public Collection obterNumeroConsumoFaturadoMes(Integer idImovel) throws ErroRepositorioException;

	public Collection verificarContaHistoricoParcelamento(Integer idImovel, Integer idParcelamento) throws ErroRepositorioException;

	public Collection verificarDebitoACobrarHistoricoParcelamento(Integer idImovel, Integer idParcelamento) throws ErroRepositorioException;

	public Collection verificarCreditoARealizarHistoricoParcelamento(Integer idImovel, Integer idParcelamento) throws ErroRepositorioException;

	public Collection<Object[]> selecionarDadosCobrancaDocumentoItemReferenteGuiaPagamento(CobrancaDocumento cobrancaDocumento) throws ErroRepositorioException;

	public Collection pesquisarContaCanceladaRetificacao(Integer idImovel, int anoMesReferencia) throws ErroRepositorioException;

	public Object[] pesquisarDadosParcelamentoComMaiorTimestemp(Integer idImovel) throws ErroRepositorioException;

	public Collection pesquisarCobrancaDocumentoItemCliente(Integer idCliente, int numeroSequencialDocumento) throws ErroRepositorioException;

	public Object[] pesquisarDadosOrdemServicoDocumentoCobranca(Integer idDocumentoCobranca) throws ErroRepositorioException;

	public Collection pesquisarIdsParcelamentosItemDebitoACobrar(Collection idsDebitoACobrar) throws ErroRepositorioException;

	public Collection pesquisarIdsCobrancaDocumentoItemDebitoACobrar(Collection idsDebitoACobrar) throws ErroRepositorioException;

	public void deletarCobrancaDocumentoItemDebitoACobrar(Collection idsDocumentoItemDebitoACobrar) throws ErroRepositorioException;

	public void atualizarParcelamentosItemDebitoACobrar(Collection idsParcelamentosItemDebitoACobrar) throws ErroRepositorioException;

	public Collection pesquisarDadosCobrancaDocumentoAgrupadoPorDataPrevista(int idCobrancaAtividadeAcaoCronograma) throws ErroRepositorioException;

	public Collection pesquisarDadosCobrancaDocumentoAgrupadoPorDataComando(int idCobrancaAtividadeAcaoCronograma) throws ErroRepositorioException;

	public void atualizarSequencialCobrancaDocumentoImpressao(Map<Integer, Integer> mapAtualizaSequencial) throws ErroRepositorioException;

	public Collection pesquisarProtocoloDocumentoCobrancaCronograma(Integer idCobrancaAcaoAtividadeCronograma) throws ErroRepositorioException;

	public Collection pesquisarProtocoloDocumentoCobrancaEventual(Integer idCobrancaAcaoAtividadeComand) throws ErroRepositorioException;

	public void atualizarParmsOS(Collection colecaoIdsOS, Integer idMotivoEncerramento) throws ErroRepositorioException;

	public Collection pesquisarDadosCobrancaDocumentoItem(Integer idDocumentoCobranca) throws ErroRepositorioException;

	public Object[] pesquisarRegistroAtendimentoTransferenciaDebitoCredito(Integer idRA) throws ErroRepositorioException;

	public EspecificacaoTipoValidacao pesquisarEspecificacaoTipoValidacaoTransferenciaDebitoCredito(Integer idSolicitacaoTipoEspecificacao) throws ErroRepositorioException;

	public Collection<RelacaoParcelamentoRelatorioHelper> pesquisarRelacaoParcelamento(FiltrarRelacaoParcelamentoHelper filtrarRelacaoParcelamento) throws ErroRepositorioException;

	public Object[] pesquisarDadosImovelPorOS(int idOrdemServico) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarCobrancaAcaoAtividadeComandoSemRealizacao() throws ErroRepositorioException;

	public Collection<DadosPesquisaCobrancaDocumentoHelper> pesquisarCobrancaDocumentoEventual(int idCobrancaAtividadeAcaoComando) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarOrdemServicoEventual(int idCobrancaAtividadeAcaoComando) throws ErroRepositorioException;

	public void deletarResumoCobrancaAcaoEventual(int idCobrancaAcaoCronograma) throws ErroRepositorioException;

	public Collection pesquisarDadosCobrancaDocumentoEventualAgrupadoPorDataPrevista(int idCobrancaAtividadeAcaoComando) throws ErroRepositorioException;

	public void atualizarCobrancaAcaoAtividadeComando(int idCobrancaAtividadeAcaoComando) throws ErroRepositorioException;

	public Collection pesquisarIdsImoveisCliente(String codigoCliente) throws ErroRepositorioException;

	public Collection pesquisarGuiasPagamentoIdsImoveis(Integer idCliente, Collection idsImoveis, int indicadorPagamento, String situacaoNormal, Date dataVencimentoInicial, Date dataVencimentoFinal)
			throws ErroRepositorioException;

	public Collection pesquisarGuiasPagamentoIdsImoveis(Integer idCliente, Collection idsImoveis, int indicadorPagamento, String situacaoNormal, Short clienteRelacaoTipo, Date dataVencimentoInicial,
			Date dataVencimentoFinal) throws ErroRepositorioException;

	public Collection pesquisarIdImoveisClienteSemRelacaoFim(String codigoCliente, Short relacaoTipo) throws ErroRepositorioException;

	public Collection pesquisarIdImoveisClienteSuperiorSemRelacaoFim(String codigoCliente) throws ErroRepositorioException;

	public Collection gerarCurvaAbcDebitos(String classificacao, String indicadorImovelMedicaoIndividualizada, String indicadorImovelParalizacaoFaturamentoCobranca, String[] gerenciaRegional,
			String idLocalidadeInicial, String idLocalidadeFinal, String idSetorComercialInicial, String idSetorComercialFinal, String idMunicipio, String[] situacaoLigacaoAgua,
			String[] situacaoLigacaoEsgoto, String intervaloConsumoMinimoFixadoEsgotoInicial, String intervaloConsumoMinimoFixadoEsgotoFinal, String indicadorMedicao, String idTipoMedicao,
			String idPerfilImovel, String idTipoCategoria, String[] categoria, String idSubCategoria) throws ErroRepositorioException;

	public Collection pesquisarParcelamentosSituacaoNormal(Integer idImovel) throws ErroRepositorioException;

	public Collection pesquisarDebitosACobrarImovelParcelamento(Integer idParcelamento) throws ErroRepositorioException;

	public Collection pesquisarCreditosARealizarParcelamento(Integer idParcelamento) throws ErroRepositorioException;

	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoParaEmitirCAER(Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando, Date dataEmissao, Integer idCobrancaAcao,
			int quantidadeCobrancaDocumentoInicio) throws ErroRepositorioException;

	public Integer verificarRDUtilizadaPeloImovel(Integer idRD, Integer idImovel) throws ErroRepositorioException;

	public BigDecimal pesquisarValorDebitoACobrarSancoes(Integer idImovel, Integer anoMesInicialReferenciaDebito, Integer anoMesFinalReferenciaDebito) throws ErroRepositorioException;

	public BigDecimal pesquisarValorDebitoACobrar(Integer idImovel, Integer anoMesInicialReferenciaDebito, Integer anoMesFinalReferenciaDebito) throws ErroRepositorioException;

	public Collection pesquisarDebitosCliente(Integer idCliente, Short relacaoTipo, Collection idsImoveis, int indicadorPagamento, int indicadorConta, String contaSituacaoNormal,
			String contaSituacaoRetificada, String contaSituacaoIncluida, String contaSituacaoParcelada, String anoMesInicialReferenciaDebito, String anoMesFinalReferenciaDebito,
			Date anoMesInicialVecimentoDebito, Date anoMesFinalVencimentoDebito, int indicadorDividaAtiva) throws ErroRepositorioException;

	public BigDecimal pesquisarValorDebitoCobradoContas(Integer idImovel, Integer anoMesInicialReferenciaDebito, Integer anoMesFinalReferenciaDebito, int indicadorDividaAtiva)
			throws ErroRepositorioException;

	public BigDecimal pesquisarValorDebitoCobradoSancoes(Integer idImovel, Integer anoMesInicialReferenciaDebito, Integer anoMesFinalReferenciaDebito, int indicadorDividaAtiva)
			throws ErroRepositorioException;

	public Integer pesquisarMaximoAnoMesIndicesAcerscimosImpontualidade() throws ErroRepositorioException;

	public IndicesAcrescimosImpontualidade pesquisarMenorIndiceAcrescimoImpontualidade() throws ErroRepositorioException;

	public Collection pesquisarDebitoImovelPorFaixaValores(String idImovel, String valorMinimoDebito, String anoMesReferenciaInicial, String anoMesReferenciaFinal, String classificacao,
			boolean pesquisaMunicipio) throws ErroRepositorioException;

	public Collection pesquisarRotasIntervaloUnidadeNegocio(String idUnidadeNegocio, String idCobrancaAcao) throws ErroRepositorioException;

	public Collection pesquisarRotasIntervaloGrupo(String idGrupoCobranca, String idCobrancaAcao) throws ErroRepositorioException;

	public Collection pesquisarRotasIntervaloGerencia(String idGerenciaRegional, String idCobrancaAcao) throws ErroRepositorioException;

	public Collection pesquisarRotasIntervaloLocalidade(String idLocalidadeInicial, String idLocalidadeFinal, String idCobrancaAcao) throws ErroRepositorioException;

	public Collection pesquisarRotasIntervaloSetor(String codigoSetorComercialInicial, String codigoSetorComercialFinal, String idLocalidade, String idCobrancaAcao) throws ErroRepositorioException;

	public Collection pesquisarRotas(String codigoSetorComercial, String rotaInicial, String rotaFinal, String idLocalidade, String idCobrancaAcao) throws ErroRepositorioException;

	public Collection pesquisarRotasPorCobrancaAcao(String idCobrancaAcao) throws ErroRepositorioException;

	public boolean existeDevolucao(CreditoARealizar creditoARealizar) throws ErroRepositorioException;

	public Cliente pesquisarClienteResponsavelParcelamento(Integer idImovel) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarParcelamentoRDEspecial(Integer situacaoParcelamento, Integer idLocalidade) throws ErroRepositorioException;

	public String verificarDataOpcaoJaExcluida(String matriculaImovel, Date dataOpcao) throws ErroRepositorioException;

	public void atualizarNumeroParcelasPagasConsecutivasParcelamento(Integer idParcelamento, Short numeroParcelas) throws ErroRepositorioException;

	public Collection consultarNegativacao(DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper, int tipo) throws ErroRepositorioException;

	public Collection pesquisarCobrancaDocumentoResumoAcaoCobranca(int idCobrancaAtividadeAcaoCronograma, int idCobrancaAtividadeAcaoComando) throws ErroRepositorioException;

	public void atualizarSituacaoCobrancaDocumentoItem(Integer situacaoDebito, Date dataSituacao, Integer idConta, Integer idGuiaPagamento, Integer idDebitoACobrar) throws ErroRepositorioException;

	public Collection pesquisarDadosCobrancaDocumentoItemSituacaoJaAtualizada(Integer idDocumentoCobranca) throws ErroRepositorioException;

	public Collection pesquisarCobrancaDocumentoEventualSemCriterio(int idCobrancaAtividadeAcaoComando) throws ErroRepositorioException;

	public Collection<CriterioSituacaoCobranca> pesquisarCobrancaCriterioSituacaoCobranca(Integer idCobrancaCriterio) throws ErroRepositorioException;

	public Collection<CriterioSituacaoLigacaoAgua> pesquisarCobrancaCriterioSituacaoLigacaoAgua(Integer idCobrancaCriterio) throws ErroRepositorioException;

	public Collection<CriterioSituacaoLigacaoEsgoto> pesquisarCobrancaCriterioSituacaoLigacaoEsgoto(Integer idCobrancaCriterio) throws ErroRepositorioException;

	public Object[] pesquisarParmsCobrancaDocumento(Integer idImovel, Date dataEmissao) throws ErroRepositorioException;

	public Collection pesquisarCobrancaDocumentoItem(Integer idImovel, Date dataEmissao) throws ErroRepositorioException;

	public boolean exitePagamentoContaEntradaParcelamento(Integer idImovel, Date dataParcelamento) throws ErroRepositorioException;

	public Collection<Object[]> consultarContasTransferidas(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper) throws ErroRepositorioException;

	public Collection<Object[]> consultarDebitosACobrarTransferidos(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper) throws ErroRepositorioException;

	public Collection<Object[]> consultarGuiasDePagamentoTransferidas(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper) throws ErroRepositorioException;

	public Collection<Object[]> consultarCreditosARealizarTransferidos(ConsultarTransferenciasDebitoHelper consultarTransferenciasDebitoHelper) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarContasInformarContasEmCobranca(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, Collection<Integer> idsImoveis, SistemaParametro sistemaParametro)
			throws ErroRepositorioException;

	public Collection<Object[]> obterNomeCPFTestemunhas(Integer unidadeUsuario) throws ErroRepositorioException;

	public void cancelarGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException;

	public void desassociarContaParcelamento(Integer idConta) throws ErroRepositorioException;

	public Collection pesquisarContaDoParcelamentoNaoPago(Integer parcelamento) throws ErroRepositorioException;

	public List<ParcelamentoItem> pesquisarItensParcelamentos(Integer idParcelamento) throws ErroRepositorioException;

	public Integer pesquisarEmpresaCobrancaConta(Integer idConta) throws ErroRepositorioException;

	public Object[] pesquisarDadosDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarItensParcelamentosNivel2(Integer idPagamento) throws ErroRepositorioException;

	public Collection pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaParaCobranca(Integer idEmpresa, Date comandoInicial, Date comandoFinal, int numeroIndice, int quantidadeRegistros)
			throws ErroRepositorioException;

	public Collection pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaParaCobrancaResumido(Integer idEmpresa, Date comandoInicial, Date comandoFinal, int numeroIndice, int quantidadeRegistros)
			throws ErroRepositorioException;

	public Integer pesquisarDadosGerarArquivoTextoContasCobrancaEmpresaParaCobrancaCount(Integer idEmpresa, Date comandoInicial, Date comandoFinal) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarDadosArquivoTextoContasCobrancaEmpresa(Collection ids, Integer idProgramaEspecial) throws ErroRepositorioException;

	public void removerEmpresaCobrancaContaPagamentos(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException;

	public Object[] pesquisarParmsCobrancaDocumento(Integer idImovel, BigDecimal valorPagamento) throws ErroRepositorioException;

	public Collection pesquisarCobrancaDocumentoItem(Integer idImovel, BigDecimal valorPagamento) throws ErroRepositorioException;

	public Collection<ResolucaoDiretoria> pesquisarResolucaoDiretoriaMaiorDataVigenciaInicioPermissaoEspecial() throws ErroRepositorioException;

	public Integer pesquisarQtdeRotasSemCriteriosParaAcoesCobranca(PesquisarQtdeRotasSemCriteriosParaAcoesCobranca filtro) throws ErroRepositorioException;

	public Integer deletarOrdemServicoUnidadeGeradasPelosDocumentosCobranca(Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;

	public void removerDebitoACobrarOrdemServicoGeradasPelosDocumentosCobranca(Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;

	public Integer deletarOrdemServicoGeradasPelosDocumentosCobranca(Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;

	public Integer deletarDebitoACobrarGeradasPelosDocumentosCobranca(Collection<Integer> idsDocumentosCobranca, Integer anoMesReferencia) throws ErroRepositorioException;

	public Integer deletarCobrancaDocumentoItemGeradasPelosDocumentosCobranca(Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;

	public Integer deletarCobrancaDocumentos(Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;

	public void removerImoveisNaoGerados(Integer idCobrancaComandoCronograma, Integer idCobrancaComandoEventual) throws ErroRepositorioException;

	public void inserirImoveisNaoGeradosParaDocumentosExcedentes(Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeOrdensServicoEncerradasPorCobrancaAcaoAtividade(Integer idCobrancaAcaoAtividadeCronograma, Integer idCobrancaAcaoAtividadeComando)
			throws ErroRepositorioException;

	public Integer pesquisarQuantidadePagamentosPorDocumentosCobranca(Integer idCobrancaAcaoAtividadeCronograma, Integer idCobrancaAcaoAtividadeComando) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeComandosSucessores(Integer idCobrancaAcaoAtividadeCronograma, Integer idCobrancaAcaoAtividadeComando) throws ErroRepositorioException;

	public Collection pesquisarDadosGerarExtensaoComandoContasCobrancaEmpresaParaCobranca(Integer idComandoEmpresaCobrancaConta) throws ErroRepositorioException;

	public Integer retornaAnoMesContaUltimaExtensao(Integer idComandoEmpresaCobrancaConta) throws ErroRepositorioException;

	public Collection<Object[]> filtrarRelacaoParcelamentoAnalitico(FiltrarRelacaoParcelamentoHelper filtrarRelacaoParcelamento, Integer anoMesReferenciaFaturamento) throws ErroRepositorioException;

	public Object[] obterParcelamentoMaisAtualDoImovel(Integer idImovel) throws ErroRepositorioException;

	public Collection obterDebitoACobrarDoParcelamento(Integer idParcelamento) throws ErroRepositorioException;

	public Collection obterContasComParcelasEmAtrasoDoParcelamento(Integer idImovel, Integer refInicialInformada, Integer refFinalInformada) throws ErroRepositorioException;

	public void inserirMovimentoExtensaoContasEmCobranca(Integer idLocalidade, Integer idPerfil) throws ErroRepositorioException;

	public Collection pesquisarDadosGerarRelatorioImoveisComAcordo(Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal, Integer idGerenciaRegional,
			Date dataInicialAcordo, Date dataFinalAcordo, Integer rotaInicial, Integer rotaFinal, Integer sequencialRotaInicial, Integer sequencialRotaFinal, Integer idSetorComercialInicial,
			Integer idSetorComercialFinal) throws ErroRepositorioException;

	public Integer pesquisarDadosGerarRelatorioImoveisComAcordoCount(Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal, Integer idGerenciaRegional,
			Date dataInicialAcordo, Date dataFinalAcordo, Integer rotaInicial, Integer rotaFinal, Integer sequencialRotaInicial, Integer sequencialRotaFinal, Integer idSetorComercialInicial,
			Integer idSetorComercialFinal) throws ErroRepositorioException;

	public CicloMetaGrupo pesquisarCicloMetaGrupoPorGrupoLocalidade(int anoMes, int idGrupo, int idLocalidade) throws ErroRepositorioException;

	public CicloMeta pesquisarMetaCiclo(int anoMes, int idCobrancaAcao) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeDocumentosGeradosAcimaValorLimite(Integer idCAAC, Integer idCACM, Integer idLocalidade, BigDecimal valorLimite) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeDocumentosGerados(Integer idCAAC, Integer idCACM, Integer idLocalidade) throws ErroRepositorioException;

	public Collection pesquisarDocumentosCobrancaExcedentes(Integer idCAAC, Integer idCACM, int quantidadeParaRemover, Integer idLocalidade) throws ErroRepositorioException;

	public void adicionarMetaCicloLocalidade(int idMetaCiclo, int idGrupo, int idLocalidade, int quantidadeASerAdicionada) throws ErroRepositorioException;

	public Collection<CicloMetaGrupo> pesquisarCicloMetaGrupoPorCicloMeta(Integer idCicloMeta, Integer idGrupo) throws ErroRepositorioException;

	public Collection pesquisarQuantidadeImoveisPorGrupoLocalidade(Collection idsLast) throws ErroRepositorioException;

	public Integer pesquisarPagamentoDeContaPorDebitoAutomatico(Integer idConta) throws ErroRepositorioException;

	public Collection pesquisarDebitosACobrarParcelamento(Integer idParcelamento) throws ErroRepositorioException;

	public Integer pesquisarIdParcelamentoNormal(Integer idImovel, Integer referencia) throws ErroRepositorioException;

	public BigDecimal pesquisarValorDebitoCobradoParcelamentoConta(Integer idConta) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeContasNaoPagasParcelamento(Integer idParcelamento, Integer idDebitoACobrar) throws ErroRepositorioException;

	public Integer pesquisarResolucaoDiretoriaComPercentualDoacao() throws ErroRepositorioException;

	public Collection pesquisarRotasPorGrupoFaturamento(Integer idGrupoFaturamento) throws ErroRepositorioException;

	public Collection pesquisarDadosImoveisPorRota(Integer idRota) throws ErroRepositorioException;

	public void deletarCobrancaDocumentoECobrancaDocumentoItem(Integer idRota, Integer documentoTipo) throws ErroRepositorioException;

	public BigDecimal selecionarValorTotalCobrancaDocumentoItemReferenteDebito(CobrancaDocumento cobrancaDocumento) throws ErroRepositorioException;

	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoParaEmitir(Integer idRota, Integer idDocumentoTipo) throws ErroRepositorioException;

	public Date pesquisarDataVencimentoRota(Integer idRota, Integer anoMesFaturamento, Integer grupoFaturamento) throws ErroRepositorioException;

	public Collection pesquisaFaturaClienteResponsavelFederal(Integer anoMes, Integer clienteID) throws ErroRepositorioException;

	public Collection pesquisaImpostoFaturaClienteResponsavelFederal(Integer anoMes, Integer idCliente) throws ErroRepositorioException;

	public Collection<Imovel> pesquisarImoveisFaturaClienteResponsavel(Integer idFatura) throws ErroRepositorioException;

	public Collection<CobrancaAcaoAtividadeCronograma> pesquisarAcoesCiclo(Collection<Integer> idsAcao, Integer anoMesReferencia) throws ErroRepositorioException;

	public Object[] calcularTotaisCronogramaAcaoCobranca(Integer idCAAC) throws ErroRepositorioException;

	public List consultarColecaoCicloMetaGrupoRelatorio(Integer idCicloMeta) throws ErroRepositorioException;

	public Collection pesquisarValorTotalCobranca(Integer idComando, Date dateInicial, Date dateFinal) throws ErroRepositorioException;

	public Collection pesquisarValorTotalCobrancaCriterio(Integer idComando, Date dateInicial, Date dateFinal) throws ErroRepositorioException;

	public IndicesAcrescimosImpontualidade pesquisarMenorIgualIndiceAcrescimoImpontualidade(int anoMesReferenciaDebito) throws ErroRepositorioException;

	public Collection pesquisarDadosPopup(Integer idComando) throws ErroRepositorioException;

	public List filtrarCobrancaDocumento(FiltrarDocumentoCobrancaHelper filtro) throws ErroRepositorioException;

	public List filtrarSupressoesReligacoesReestabelecimentos(FiltroSupressoesReligacoesReestabelecimentoHelper filtro) throws ErroRepositorioException;

	public Integer filtrarCobrancaDocumentoCount(FiltrarDocumentoCobrancaHelper filtro) throws ErroRepositorioException;

	public List consultarColecaoAcaoCobranca(RelatorioAcompanhamentoAcoesCobrancaHelper helper) throws ErroRepositorioException;

	public List consultarDocumentosCobranca(FiltrarDocumentoCobrancaHelper filtro) throws ErroRepositorioException;

	public Collection pesquisarDadosArquivoTextoPagamentosContasCobrancaEmpresa(Integer idEmpresa) throws ErroRepositorioException;

	public Collection obterUnidadeNegocioPagamentosEmpresaCobrancaConta() throws ErroRepositorioException;

	public CobrancaDocumento pesquisarCobrancaDocumentoImpressaoSimultanea(Date dataEmissao, Integer idImovel) throws ErroRepositorioException;

	public DocumentoTipo pesquisarTipoAcaoCobrancaParaRelatorio(Integer cobrancaAcaoAtividadeComando, Integer cobrancaAcaoAtividadeCronograma) throws ErroRepositorioException;

	public Integer obterOrdemServicoAssociadaDocumentoCobranca(Integer idCobrancaDocumento) throws ErroRepositorioException;

	public CobrancaDocumento pesquisarCobrancaDocumento(Integer idCobrancaDocumento) throws ErroRepositorioException;

	public void removerCicloMetaGrupo(Integer idCicloMeta) throws ErroRepositorioException;

	public Collection<Integer> pesquisarEmpresaCobrancaDaRota(Integer idLocalidade) throws ErroRepositorioException;

	public Collection pesquisarDebitoACobrarParceladoComIDNulo(Integer idImovel) throws ErroRepositorioException;

	public void atualizarValorDocumentoEValorDescontoCobrancaDocumento(Integer idCobrancaDocumento, BigDecimal valorDocumento, BigDecimal valorDesconto) throws ErroRepositorioException;

	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoDoImovel(Integer idImovel, Integer idDocumentoTipo) throws ErroRepositorioException;

	public void inserirCartaFinalAno(Integer idCobrancaGrupo, Integer idEmpresa, Integer idLocalidade, Integer codigoSetor, Integer numeroQuadra, Integer lote, Integer subLote, Integer sequencial,
			String txt_parte1, String txt_parte2, Integer idRota) throws ErroRepositorioException;

	public Collection pesquisarCartaFinalAnoGrupo(Integer idCobrancaGrupo) throws ErroRepositorioException;

	public void deletarCartaFinalAno(Integer idRota) throws ErroRepositorioException;

	/**
	 * @author Anderson Italo
	 * @date 26/11/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisPorGrupoCobranca(Integer idCobrancaGrupo, Integer gerencia, Integer unidade, Integer localidade, Integer setorComercial, Integer quadra)
			throws ErroRepositorioException;

	/**
	 * @author Anderson Italo
	 * @date 30/11/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisarQuantidadeImoveisPorComandoEventual(Integer idCobrancaAcaoAtividadeComando, Integer gerencia, Integer unidade, Integer localidade, Integer setorComercial, Integer quadra)
			throws ErroRepositorioException;

	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * [FS0008] – Verificar validade da data
	 *
	 * @author Raphael Rossiter
	 * @date 07/01/2010
	 *
	 * @param idCliente
	 * @param idArrecadacaoForma
	 * @return Short
	 * @throws ErroRepositorioException
	 */
	public Short pesquisarNumeroDiasFloatCartao(Integer idCliente, Integer idArrecadacaoForma) throws ErroRepositorioException;

	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 *
	 * @author Raphael Rossiter
	 * @date 11/01/2010
	 *
	 * @param idCliente
	 * @param dataVencimento
	 * @return GuiaPagamento
	 * @throws ErroRepositorioException
	 */
	public GuiaPagamento pesquisarGuiaPagamentoCartaoCredito(Integer idCliente, Date dataVencimento) throws ErroRepositorioException;

	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 *
	 * @author Raphael Rossiter
	 * @date 12/01/2010
	 *
	 * @param guiaPagamento
	 * @throws ErroRepositorioException
	 */
	public void atualizarGuiaPagamentoCartaoCredito(GuiaPagamento guiaPagamento) throws ErroRepositorioException;

	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 *
	 * @author Raphael Rossiter
	 * @date 12/01/2010
	 *
	 * @return Localidade
	 * @throws ErroRepositorioException
	 */
	public Localidade pesquisarLocalidadeSede() throws ErroRepositorioException;

	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 *
	 * @author Raphael Rossiter
	 * @date 12/01/2010
	 *
	 * @return DebitoTipo
	 * @throws ErroRepositorioException
	 */
	public DebitoTipo pesquisarDebitoTipoCartaoCredito() throws ErroRepositorioException;

	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * [SB0004] – Incluir Dados da Confirmação dos Pagamentos
	 *
	 * @author Raphael Rossiter
	 * @date 18/01/2010
	 *
	 * @param idArrecadador
	 * @param dataLancamento
	 * @return AvisoBancario
	 * @throws ErroRepositorioException
	 */
	public AvisoBancario pesquisarAvisoBancario(Integer idArrecadador, Date dataLancamento) throws ErroRepositorioException;

	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * [SB0005 – Calcular Valor da Dedução]
	 *
	 * @author Raphael Rossiter
	 * @date 19/01/2010
	 *
	 * @param idArrecadador
	 * @param idArrecadacaoForma
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ArrecadadorContratoTarifa pesquisarArrecadadorContratoTarifa(Integer idArrecadador, Integer idArrecadacaoForma) throws ErroRepositorioException;

	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * [SB0004] – Incluir Dados da Confirmação dos Pagamentos
	 *
	 * @author Raphael Rossiter
	 * @date 28/04/2010
	 *
	 * @param avisoBancario
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorAvisoBancario(AvisoBancario avisoBancario) throws ErroRepositorioException;

	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * [SB0004] – Incluir Dados da Confirmação dos Pagamentos
	 *
	 * @author Raphael Rossiter
	 * @date 19/01/2010
	 *
	 * @param idAvisoBancario
	 * @return
	 * @throws ErroRepositorioException
	 */
	public AvisoDeducoes pesquisarAvisoDeducoes(Integer idAvisoBancario) throws ErroRepositorioException;

	/**
	 * [UC0927] – Confirmar Cartão de Crédito/Débito
	 * 
	 * [SB0004] – Incluir Dados da Confirmação dos Pagamentos
	 *
	 * @author Raphael Rossiter
	 * @date 19/01/2010
	 *
	 * @param idAvisoBancario
	 * @param valorTotalAvisoDeducoes
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorAvisoDeducoes(Integer idAvisoBancario, BigDecimal valorTotalAvisoDeducoes) throws ErroRepositorioException;

	/**
	 * [UC0349] Emitir Documento de Cobrança – Aviso de Corte
	 * 
	 * inserir na tabela temporaria os dados para gerar os arquivos do aviso de
	 * corte
	 * 
	 * @author Vivianne Sousa
	 * @date 11/12/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirDocumentoCobrancaImpressao(Integer idCobrancaDocumento, String linhaTxt, Integer idCobrancaAcaoAtividadeComando, Integer idCobrancaAcaoAtividadeCronograma,
			Integer sequencialImpressao) throws ErroRepositorioException;

	/**
	 * [UC0349] Emitir Documento de Cobrança – Aviso de Corte
	 * 
	 * @author Vivianne Sousa
	 * @date 11/12/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDocumentoCobrancaImpressao(Integer idCobrancaAcaoAtividadeComando, Integer idCobrancaAcaoAtividadeCronograma) throws ErroRepositorioException;

	/**
	 * [UC0349] Emitir Documento de Cobrança – Aviso de Corte
	 * 
	 * @author Vivianne Sousa
	 * @date 11/12/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarDocumentoCobrancaImpressao(Integer idCobrancaAcaoAtividadeComando, Integer idCobrancaAcaoAtividadeCronograma) throws ErroRepositorioException;

	/**
	 * [UC0349] Emitir Documento de Cobrança – Aviso de Corte
	 * 
	 * @author Vivianne Sousa
	 * @date 11/12/2009
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void deletarDocumentoCobrancaImpressao(Collection<Integer> idsDocumentosCobranca) throws ErroRepositorioException;

	/**
	 * 
	 * [UC0987] Inserir Faixa de Dias Vencidos para Documentos a Receber
	 * 
	 * Verificar se existe Faixa inicial já cadastrada.
	 * 
	 * @author Hugo Leonardo
	 * @param valorInicialFaixa
	 * @throws ControladorException
	 * @data 22/02/2010
	 *
	 * @return String
	 */
	public String verificarExistenciaFaixaInicial(Integer valorInicialFaixa) throws ErroRepositorioException;

	/**
	 * 
	 * [UC0987] Inserir Faixa de Dias Vencidos para Documentos a Receber
	 * 
	 * Verificar se existe Faixa final já cadastrada.
	 * 
	 * @author Hugo Leonardo
	 * @param valorFinalFaixa
	 * @throws ControladorException
	 * @data 22/02/2010
	 *
	 * @return Boolean
	 */
	public Integer verificarExistenciaFaixaFinal(Integer valorFinalFaixa) throws ErroRepositorioException;

	/**
	 * [UC990] Gerar Relatório de Documentos a Receber
	 *
	 * @author Hugo Amorim
	 * @param quantidadeMaxima
	 * @param quantidadeInicio
	 * @date 22/02/2010
	 *
	 */
	public Collection pesquisarRelatorioDocumentosAReceber(FiltroRelatorioDocumentosAReceberHelper helper, String tipoTotalizacao, int quantidadeInicio, int quantidadeMaxima)
			throws ErroRepositorioException;

	/**
	 * [UC990] Count Relatório de Documentos a Receber
	 *
	 * @author Hugo Amorim
	 * @date 22/02/2010
	 *
	 */
	public Integer countRelatorioDocumentosAReceber(FiltroRelatorioDocumentosAReceberHelper helper) throws ErroRepositorioException;

	/**
	 * [UC????] Relatorio Comando Documento Cobranca Retorna a ação de cobrança
	 * para exibição de parametros do relatório
	 * 
	 * @author Anderson Italo
	 * @data 04/05/2010
	 */
	public CobrancaAcao pesquisarAcaoCobrancaParaRelatorio(Integer cobrancaAcaoAtividadeComando, Integer cobrancaAcaoAtividadeCronograma) throws ErroRepositorioException;

	// /**
	// * [UC0251] Gerar Atividade de Ação de Cobrança
	// *
	// * @author Vivianne Sousa
	// * @date 07/04/2010
	// */
	// public void inserirCobrancaDocumentoControleGeracao(
	// Integer idProcesso,
	// Integer quantidadeCobrancaDocumento,
	// Integer quantidadeCobrancaDocumentoItem,
	// BigDecimal valorTotalCobrancaDocumento)
	// throws ErroRepositorioException;

	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2010
	 */
	public void atualizarQuantidadeCobrancaDocumento(Integer idCobrancaDocumentoControleGeracao, Integer quantidadeCobrancaDocumento) throws ErroRepositorioException;

	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2010
	 */
	public void atualizarCobrancaDocumentoControleGeracaoSomar(Integer idCobrancaDocumentoControleGeracao, Integer quantidadeCobrancaDocumento, Integer quantidadeCobrancaDocumentoItem,
			BigDecimal valorTotalCobrancaDocumento) throws ErroRepositorioException;

	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2010
	 */
	public Integer pesquisarQuantidadeCobrancaDocumento(Integer idCobrancaDocumentoControleGeracao) throws ErroRepositorioException;

	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2010
	 */
	public void atualizarCobrancaDocumentoControleGeracaoSomar(Integer idCobrancaDocumentoControleGeracao, Integer quantidadeCobrancaDocumentoItem, BigDecimal valorTotalCobrancaDocumento)
			throws ErroRepositorioException;

	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @date 07/04/2010
	 */
	public CobrancaDocumentoControleGeracao pesquisarCobrancaDocumentoControleGeracao(Integer idCobrancaDocumentoControleGeracao) throws ErroRepositorioException;

	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @date 19/04/2010
	 */
	public void atualizarCobrancaDocumentoControleGeracao(Integer quantidadeCobrancaDocumento, Integer quantidadeCobrancaDocumentoItem, BigDecimal valorTotalCobrancaDocumento,
			Integer idCobrancaAcaoAtividadeComando, Integer idCobrancaAcaoAtividadeCronograma) throws ErroRepositorioException;

	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * @author Vivianne Sousa
	 * @date 19/04/2010
	 */
	public void atualizarCobrancaDocumentoControleGeracaoSubtrair(Integer idCobrancaDocumentoControleGeracao, Integer quantidadeCobrancaDocumento, Integer quantidadeCobrancaDocumentoItem,
			BigDecimal valorTotalCobrancaDocumento) throws ErroRepositorioException;

	/**
	 * @author Vivianne Sousa
	 * @date 29/04/2010
	 */
	public Collection pesquisarImovelCobrancaSituacaoPorImovel(Integer idImovel) throws ErroRepositorioException;

	/**
	 * @author Vivianne Sousa
	 * @date 03/05/2010
	 */
	public Collection pesquisarDadosImovelCobrancaSituacaoPorImovel(Integer idImovel) throws ErroRepositorioException;

	/**
	 * 
	 * Atualiza valores do Documento de Cobrança de cartas.
	 * 
	 * @author Hugo Amorim
	 * @data 29/04/2010
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarValoresDocumentoCobrancaCartas(Integer id, BigDecimal descontoTotalPagamentoAVista, BigDecimal valorTotalImpostosConta) throws ErroRepositorioException;

	/**
	 * Author: Arthur Carvalho Data: 14/05/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarTodosParcelamentosSituacaoNormal(String situacao) throws ErroRepositorioException;

	/**
	 * [UC0998] Gerar Relação de Parcelamento - Visão Cartão de Crédito
	 * 
	 * Bean que preencherá o relatorio
	 * 
	 * @author Hugo Amorim
	 * @date 11/06/2010
	 *
	 */
	public Collection<Object[]> filtrarRelacaoParcelamentoCartaoCredito(FiltrarRelacaoParcelamentoHelper filtrarRelacaoParcelamento) throws ErroRepositorioException;

	/**
	 * 
	 * [UC1038] Prescrever Débitos de Imóveis
	 * 
	 * @author Hugo Leonardo
	 * @date 07/07/2010
	 * 
	 * @param idFuncionalidadeIniciada
	 * @param anoMesFaturamento
	 * @throws ErroRepositorioException
	 */
	public void prescreverDebitosDeImoveis(Integer anoMesFaturamento, String dataFormatada, Integer usuario) throws ErroRepositorioException;

	public void prescreverDebitosDeImoveisContasInlcuidas(Integer anoMesFaturamento, String dataFormatada, Integer usuario) throws ErroRepositorioException;

	/**
	 * 
	 * [UC1038] Prescrever Débitos de Imóveis
	 * 
	 * @author Hugo Leonardo
	 * @date 07/07/2010
	 * 
	 * @param idFuncionalidadeIniciada
	 * @param anoMesFaturamento
	 * @throws ErroRepositorioException
	 */
	public Collection obterCobrancaSituacaoParaPrescreverDebitos() throws ErroRepositorioException;

	/**
	 * [UC0244] Manter Comando Ação de Cobrança
	 * 
	 * @author Hugo Amorim
	 * @created 14/07/2010
	 *
	 * @exception ErroRepositorioException
	 * 
	 */
	public void removerCobrancaAcaoAtividadeComandoFiscalizacaoSituacao(Integer idComando) throws ErroRepositorioException;

	/**
	 * Obtem os percentuais de desconto por tempo de inatividade a vista
	 * 
	 * [UC0214] - Efetuar Parcelamento de Débitos
	 * 
	 * @author Vivianne Sousa
	 * @date 20/07/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParcDesctoInativVista obterPercentualDescontoInatividadeAVista(Integer idPerfilParc, int qtdeMeses) throws ErroRepositorioException;

	/**
	 * [UC1038] Prescrever Débitos de Imóveis Pesquisa imoveis para execução do
	 * batch
	 * 
	 * @author Hugo Leonardo
	 * @date 19/07/2010
	 */
	public Collection obterContasPrescreverDebitosDeImoveis(Integer idLocalidade, Integer anoMesFaturamento, String idsCobrancaSituacao, int numeroIndice, int quantidadeRegistros)
			throws ErroRepositorioException;

	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança
	 * 
	 * Atualizar total de documentos, itens e valores realizados nos comandos de
	 * acao de cobranca
	 * 
	 * Data: 19/07/2010
	 * 
	 * @author Arthur Carvalho
	 * 
	 * @param idCACM
	 *            Identificador de CobrancaAcaoAtividadeComando
	 */
	public Object[] calcularTotaisComandoAcaoCobranca(Integer idCAAC) throws ErroRepositorioException;

	/**
	 * 
	 * atualiza a forma da emissão do documento de cobrança
	 * 
	 * @author Sávio Luiz
	 * @date 12/08/2010
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarFormaEmissaoCobrancaDocumento(Integer idCobrancaDocumento) throws ErroRepositorioException;

	/**
	 * Pesquisa Documentos de cobranças validos para imovel para determinado
	 * tipo de documento
	 * 
	 * @author Hugo Amorim
	 * @date 09/09/2010
	 */
	public Collection<CobrancaDocumento> pesquisarDadosCobrancaDocumentoValidoImovel(Integer idImovel, Integer idDocumentoTipo, Integer idAcaoCobranca) throws ErroRepositorioException;

	/**
	 * [UC0478] Gerar Resumo das Ações de Cobrança do Cronograma
	 *
	 * Data: 16/09/2010
	 * 
	 * @author Vivianne Sousa
	 */
	public Integer pesquisarCobrancaDocumentoFisc(Integer idFiscalizacaoSituacao, Integer idOrdemServico, Integer idCobrancaDocumento) throws ErroRepositorioException;

	/**
	 * [UC0852] – Incluir Débito a Cobrar de Entrada de Parcelamento Não Paga
	 * 
	 * @author Arthur Carvalho
	 * @date 22/09/2010
	 * @param parcelamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGuiaPagamentoDoParcelamentoPorEntradaNaoPaga(String parcelamento) throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisa por todos os impostos a partir de uma fatura de um cliente
	 * responsavel federal
	 *
	 * OBS: O id da fatura passado tem que ser de uma fatura de um cliente
	 * responsavel federal
	 *
	 * @author Fernando Fontelles
	 * @date 24/09/2010
	 *
	 * @param Integer
	 *            anoMes
	 * @param Integer
	 *            idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisaImpostoFaturaClienteResponsavelFederalAnalitico(Integer anoMes, Integer idCliente) throws ErroRepositorioException;

	/**
	 * [UC1112] Processar Encerramento Ordens de Serviço da Ação de Cobrança
	 * 
	 * @author Mariana Victor
	 * @created 02/12/2010
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<Integer> pesquisarOrdemServicoParaEncerrar(Integer idCobrancaAcaoCronograma) throws ErroRepositorioException;

	/**
	 * [UC1112] Processar Encerramento Ordens de Serviço da Ação de Cobrança
	 * 
	 * @author Mariana Victor
	 * @created 07/12/2010
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<Object[]> pesquisarAtividadeCronograma(Integer idCobrancaAcao) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarLocalidade(int idOrdemServico) throws ErroRepositorioException;

	/**
	 * [UC1112] Processar Encerramento Ordens de Serviço da Ação de Cobrança
	 * 
	 * @author Mariana Victor
	 * @created 10/12/2010
	 * 
	 * @exception ErroRepositorioException
	 *                Repositorio Exception
	 */
	public Collection<Object[]> pesquisarAtivCronogOrdemServicoParaEncerrar(Integer idAtividadeCronograma) throws ErroRepositorioException;

	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0003] Gerar Atividade de
	 * Ação de Cobrança para Imóvel
	 * 
	 * @author Vivianne Sousa
	 * @created 22/12/2010
	 **/
	public Collection pesquisarIdDocumentoCobrancaParaImovel(Integer idImovel, Integer idDocumentoTipo) throws ErroRepositorioException;

	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0003] Gerar Atividade de
	 * Ação de Cobrança para Imóvel
	 * 
	 * @author Vivianne Sousa
	 * @created 22/12/2010
	 **/
	public Integer pesquisarQtdeDocumentoCobrancaItemConta(Collection idsCobrancaDocumento, Collection colecaoConta) throws ErroRepositorioException;

	/**
	 * [UC0251] Gerar Atividade de Ação de Cobrança [SB0003] Gerar Atividade de
	 * Ação de Cobrança para Imóvel
	 * 
	 * @author Vivianne Sousa
	 * @created 22/12/2010
	 **/
	public Integer pesquisarQtdeDocumentoCobrancaItemDebitoACobrar(Collection idsCobrancaDocumento, Collection colecaoDebitoACobrar) throws ErroRepositorioException;

	/**
	 * [UC676] Consultar Resumo Negativação
	 * 
	 * @author Ivan Sergio
	 * @date 14/01/2011
	 * 
	 * @param dadosConsultaNegativacaoHelper
	 * @param idSituacaoDebito
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarNegativacaoLigacaoAguaPorSituacaoDebito(DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper, int idSituacaoDebito) throws ErroRepositorioException;

	/**
	 * [UC0xxx] Emitir Documentos de Cobrança Em Lote
	 * 
	 * @author Mariana Victor
	 * @created 20/01/2011
	 **/
	public Integer pesquisarQuantidadeContasDebito(Integer idCobrancaDocumento) throws ErroRepositorioException;

	/**
	 * [UC0xxx] Emitir Documentos de Cobrança Em Lote
	 * 
	 * @author Mariana Victor
	 * @created 20/01/2011
	 **/
	public List<String> pesquisarTipoDeCorte() throws ErroRepositorioException;

	/**
	 * [UC0xxx] Emitir Documentos de Cobrança Em Lote
	 * 
	 * @author Mariana Victor
	 * @created 26/01/2011
	 **/
	public List<String> pesquisarOcorrenciasFiscalizacao() throws ErroRepositorioException;

	/**
	 * [UC0xxx] Emitir Documentos de Cobrança Em Lote
	 * 
	 * @author Rômulo Aurélio
	 * @created 17/02/2011
	 **/

	public void atualizarDataRealizacaoCobrancaAcaoAtivCronograma(Integer idCobAcaoAtivCron) throws ErroRepositorioException;

	/**
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * Seleciona os itens do documento de cobrança correspondentes a guia
	 * 
	 * @author Mariana Victor
	 * @data 16/03/2011
	 * 
	 * @param CobrancaDocumento
	 * @return Collection<CobrancaDocumentoItem>
	 */
	public Collection<CobrancaDocumentoItem> selecionarCobrancaDocumentoItemReferenteGuia(CobrancaDocumento cobrancaDocumento) throws ErroRepositorioException;

	/**
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * Seleciona os itens do documento de cobrança correspondentes a débito à
	 * cobrar
	 * 
	 * @author Mariana Victor
	 * @data 16/03/2011
	 * 
	 * @param CobrancaDocumento
	 * @return Collection<CobrancaDocumentoItem>
	 */
	public Collection<CobrancaDocumentoItem> selecionarCobrancaDocumentoItemReferenteDebitoACobrar(CobrancaDocumento cobrancaDocumento) throws ErroRepositorioException;

	/**
	 * [UC0349] Emitir Documento de Cobrança
	 * 
	 * Seleciona os itens do documento de cobrança correspondentes a credito à
	 * cobrar
	 * 
	 * @author Mariana Victor
	 * @data 16/03/2011
	 * 
	 * @param CobrancaDocumento
	 * @return Collection<CobrancaDocumentoItem>
	 */
	public Collection<CobrancaDocumentoItem> selecionarCobrancaDocumentoItemReferenteCreditoACobrar(CobrancaDocumento cobrancaDocumento) throws ErroRepositorioException;

	/**
	 * [UC0968] Emitir Cartas da Campanha de Final de Ano 2009
	 * 
	 * inserir na tabela temporaria os dados para gerar os arquivos do aviso de
	 * corte
	 * 
	 * @author Mariana Victor
	 * @date 17/03/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void inserirDocumentoCobrancaImpressaoFichaCompensasao(Integer idCobrancaDocumento, String linhaTxt, String conteudoFichaCompensacao, Integer idCobrancaAcaoAtividadeComando,
			Integer idCobrancaAcaoAtividadeCronograma, Integer sequencialImpressao) throws ErroRepositorioException;

	/**
	 * [UC0968] Emitir Cartas da Campanha de Final de Ano 2009
	 * 
	 * @author Mariana Victor
	 * @date 17/03/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDocumentoCobrancaImpressaoFichaCompensacao(Integer idCobrancaAcaoAtividadeComando, Integer idCobrancaAcaoAtividadeCronograma) throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisa por todos os impostos arrecadados a partir de um mês de
	 * referência de um determinado cliente federal ou de TODOS os clientes
	 * federais.
	 *
	 * @author Diogo Peixoto
	 * @date 23/03/2011
	 *
	 * @param Integer
	 *            anoMesFerencia
	 * @param Integer
	 *            idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImpostosArrecadacaoClienteResponsavelFederal(Integer anoMesFerencia, Integer idCliente) throws ErroRepositorioException;

	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException
	 * @data 22/03/2011
	 * 
	 * @throws ControladorException
	 * */
	public Collection pesquisarItemServicoContrato(Integer idGrupoCobranca) throws ErroRepositorioException;

	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException
	 * */
	public Collection pesquisarOSEncerradasPorBoletim(Integer idGrupoCobranca, Integer idItemServicoContrato, Integer referencia) throws ErroRepositorioException;

	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException
	 * */
	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimento(Integer idGrupoCobranca, Integer idItemServicoContrato, Short indicadorPavimento, Integer referencia)
			throws ErroRepositorioException;

	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRepAsfalto(Integer idGrupoCobranca, Integer idItemServicoContrato, Integer referencia) throws ErroRepositorioException;

	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRepParalalo(Integer idGrupoCobranca, Integer idItemServicoContrato, Integer referencia) throws ErroRepositorioException;

	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException
	 * */
	public Collection pesquisarOSEncerradasPorBoletimRepCalcada(Integer idGrupoCobranca, Integer idItemServicoContrato, Integer referencia) throws ErroRepositorioException;

	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException
	 * */
	public Collection pesquisarOSEncerradasPorBoletimPorDesconto(Integer idGrupoCobranca, Integer idItemServicoContrato, Integer referencia) throws ErroRepositorioException;

	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException
	 * */
	public Collection pesquisarOSEncerradasPorBoletimPorDescontoSemDecursoPrazo(Integer idGrupoCobranca, Integer idItemServicoContrato, Integer referencia) throws ErroRepositorioException;

	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException
	 * */
	public Collection pesquisarSituacaoAtualContaPeloCronogramaCobranca(Integer idGrupoCobranca, Integer referencia) throws ErroRepositorioException;

	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException
	 * */
	public void atualizaIndicadorBoletimOS(Collection idsOS) throws ErroRepositorioException;

	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException
	 * */
	public void atualizaIndicadorCobrancaAcaoOSNaoAceitas(Collection idsOSNaoAceitas) throws ErroRepositorioException;

	/**
	 * 
	 * Pesquisa por todos os impostos arrecadados a partir de um mês de
	 * referência de um determinado cliente federal ou de TODOS os clientes
	 * federais.
	 *
	 * @author Diogo Peixoto
	 * @date 24/03/2011
	 *
	 * @param Integer
	 *            anoMesFerencia
	 * @param Integer
	 *            idCliente
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarImpostosArrecadacaoClienteResponsavelFederalAnalitico(Integer anoMesFerencia, Integer idCliente) throws ErroRepositorioException;

	/**
	 * [UC1153] Solicitar Geração/Emissão Boletim de Medição de Cobrança
	 * 
	 * [FS0002] – Ações não encerradas no cronograma.
	 * 
	 * @author Mariana Victor
	 * @created 21/03/2011
	 **/
	public Integer pesquisarAcoesEncerradasCronograma(Integer anoMesReferencia, Integer idCobrancaGrupo) throws ErroRepositorioException;

	/**
	 * [UC1152] Emissão Boletim Medição Cobrança
	 * 
	 * Pesquisa os Itens de Serviço relacionados ao boletim de medição de
	 * cobrança selecionado
	 * 
	 * @author Mariana Victor
	 * @created 21/03/2011
	 **/
	public Collection<Object[]> pesquisarItensServicoCobrancaBoletimDesconto(Integer anoMesReferencia, Integer idCobrancaGrupo, Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal) throws ErroRepositorioException;

	/**
	 * [UC1152] Emissão Boletim Medição Cobrança
	 * 
	 * Pesquisa os Itens de Serviço relacionados ao boletim de medição de
	 * cobrança selecionado
	 * 
	 * @author Mariana Victor
	 * @created 21/03/2011
	 **/
	public Collection<Object[]> pesquisarItensServicoCobrancaBoletimExecutados(Integer anoMesReferencia, Integer idCobrancaGrupo, Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal) throws ErroRepositorioException;

	/**
	 * [UC1152] Emissão Boletim Medição Cobrança
	 * 
	 * Pesquisa os Itens de Serviço relacionados ao boletim de medição de
	 * cobrança selecionado
	 * 
	 * @author Mariana Victor
	 * @created 21/03/2011
	 **/
	public Collection<Object[]> pesquisarItensServicoCobrancaBoletimSucesso(Integer anoMesReferencia, Integer idCobrancaGrupo, Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal) throws ErroRepositorioException;

	/**
	 * [UC1152] Emissão Boletim Medição Cobrança
	 * 
	 * De acordo com o código da constate do item, pesquisa os valores do mesmo.
	 * 
	 * @author Mariana Victor
	 * @created 22/03/2011
	 **/
	public Object[] obterQuantidadeOSBoletimMedicaoCobranca(RelatorioBoletimMedicaoCobrancaHelper helper) throws ErroRepositorioException;

	/**
	 * [UC1152] Emissão Boletim Medição Cobrança
	 * 
	 * De acordo com o código da constate do item, pesquisa os valores do mesmo.
	 * 
	 * @author Mariana Victor
	 * @created 22/03/2011
	 **/
	public Object[] obterSomatorioOSBoletimMedicaoCobranca(RelatorioBoletimMedicaoCobrancaHelper helper) throws ErroRepositorioException;

	/**
	 * [UC1152] Emissão Boletim Medição Cobrança
	 * 
	 * De acordo com o código da constate do item, pesquisa os valores do mesmo.
	 * 
	 * @author Mariana Victor
	 * @created 23/03/2011
	 **/
	public Object[] obterQuantidadeOSBoletimMedicaoCobrancaDesconto(RelatorioBoletimMedicaoCobrancaHelper helper) throws ErroRepositorioException;

	/**
	 * [UC1152] Emissão Boletim Medição Cobrança
	 * 
	 * Consulta os valores da totalização da taxa de sucesso.
	 * 
	 * @author Mariana Victor
	 * @created 23/03/2011
	 **/
	public Object[] obterTotalizacaoOSBoletimMedicaoCobrancaSucesso(RelatorioBoletimMedicaoCobrancaHelper helper) throws ErroRepositorioException;

	/**
	 * [UC1152] Emissão Boletim Medição Cobrança
	 * 
	 * Pesquisa dados da empresa e do contrado do boletim de cobrança
	 * 
	 * @author Mariana Victor
	 * @created 24/03/2011
	 **/
	public Object[] pesquisarDadosBoletimMedicaoCobranca(Integer anoMesReferencia, Integer idCobrancaGrupo) throws ErroRepositorioException;

	/**
	 * Gerar Relatório de Análise de Perdas com Crédito
	 * 
	 * [UC1155] Gerar Relatório de Análise de Perdas com Crédito
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param mesAno
	 *            para análise
	 * @throws ErroRepositorioException
	 */
	public RelatorioAnalisePerdasCreditosBean gerarRelatorioAnalisePerdasCreditos(String anoMesReferencia) throws ErroRepositorioException;

	/**
	 * Retorna o maior ano mesReferencia da tabela docs_a_rec_resumo
	 * 
	 * [UC1155] Gerar Relatório de Análise de Perdas com Crédito
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param mesAno
	 *            para análise
	 * @throws ErroRepositorioException
	 * @throws ErroRepositorioException
	 */
	public int maiorAnoMesReferenciaDocumentosAReceberResumo() throws ErroRepositorioException;

	/**
	 * [UC1151] Gerar Boletim de Medição
	 * 
	 * 
	 * @author Sávio Luiz
	 * @throws ControladorException
	 * @data 23/03/2011
	 * 
	 * @throws ControladorException
	 * */
	public Collection<BigDecimal> pesquisarValorContaouContaHistorico(Integer idImovel, Integer referencia) throws ErroRepositorioException;

	/**
	 * [UC0870] Gerar Movimento de Contas em Cobrança por Empresa
	 * 
	 * Pesquisa a quantidade de contas associadas ao imóvel
	 * 
	 * @author: Mariana Victor
	 * @date: 13/04/2011
	 */
	public Integer pesquisarQuantidadeContasEmCobrancaPorImovel(ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta, Integer idImovel, SistemaParametro sistemaParametro)
			throws ErroRepositorioException;

	/**
	 * [UC0879] Gerar Extensão de Comando de Contas em Cobrança por Empresa -
	 * Pesquisa dados do popup
	 * 
	 * @author Mariana Victor
	 * @date 13/04/2011
	 */
	public Collection<Object[]> pesquisarDadosPopupExtensaoComandoImovelPerfil(Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC0879] Gerar Extensão de Comando de Contas em Cobrança por Empresa -
	 * Pesquisa dados do popup
	 * 
	 * @author Mariana Victor
	 * @date 13/04/2011
	 */
	public Collection<Object[]> pesquisarDadosPopupExtensaoComandoGerenciaRegional(Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC0879] Gerar Extensão de Comando de Contas em Cobrança por Empresa -
	 * Pesquisa dados do popup
	 * 
	 * @author Mariana Victor
	 * @date 13/04/2011
	 */
	public Collection<Object[]> pesquisarDadosPopupExtensaoComandoUnidadeNegocio(Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * @author: Mariana Victor
	 * @date: 13/04/2011
	 */
	public Integer pesquisarQuantidadeContasArquivoTextoContasCobrancaEmpresa(Collection ids, Integer idImovel) throws ErroRepositorioException;

	/**
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobranca por Empresa
	 * 
	 * Pesquisa a Situação de cobrança a partir do código constante.
	 * 
	 * @author: Mariana Victor
	 * @date: 18/04/2011
	 */
	public Integer pesquisarCobrancaSituacao(Integer codigoConstante) throws ErroRepositorioException;

	public boolean pesquisarDebitoCobradoParcelamento(Integer codigoParcelamento) throws ErroRepositorioException;

	/**
	 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
	 * 
	 * @author Rômulo Aurélio
	 * @throws ErroRepositorioException
	 * @date 12/05/2011
	 */
	public ContratoParcelamento pesquisarContratoParcelamento(String numeroParcelamento) throws ErroRepositorioException;

	/**
	 * [UC1140] Cancelar Contrato de Parcelamento por Cliente
	 * 
	 * @author Rômulo Aurélio
	 * @throws ErroRepositorioException
	 * @date 12/05/2011
	 */
	public Collection pesquisarDebitoContratoParcelamentoPorTipoDocumento(ContratoParcelamento contratoParcelamento, Integer idDocumentoTipo) throws ErroRepositorioException;

	/**
	 * [UC1167] Consultar Comandos de Cobrança por Empresa
	 * 
	 * Pesquisa os dados dos comandos
	 * 
	 * @author: Mariana Victor
	 * @date: 04/05/2011
	 */
	public Collection pesquisarDadosConsultarComandosContasCobrancaEmpresaResumido(Integer idEmpresa, Date cicloInicial, Date cicloFinal, int numeroIndice, int quantidadeRegistros)
			throws ErroRepositorioException;

	/**
	 * [UC1167] Consultar Comandos de Cobrança por Empresa
	 * 
	 * Pesquisa os dados de um comando para exibir no popup
	 * 
	 * @author: Mariana Victor
	 * @date: 04/05/2011
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarDadosPopupExtensaoComandoCobranca(Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC1167] Consultar Comandos de Cobrança por Empresa
	 * 
	 * - Pesquisa dados da cobrança
	 * 
	 * @author: Mariana Victor
	 * @date: 06/05/2011
	 * @throws ErroRepositorioException
	 * 
	 */
	public Collection pesquisarValorTotalCobrancaComandoEmpresa(Integer idComando) throws ErroRepositorioException;

	/**
	 * [UC1167] Consultar Comandos de Cobrança por Empresa
	 * 
	 * Pesquisa a quantidade de contas, agrupando por imóvel
	 * 
	 * @author: Mariana Victor
	 * @date: 06/05/2011
	 * @throws ErroRepositorioException
	 * 
	 */
	public Collection pesquisarValorTotalCobrancaComandoEmpresaPorImovel(Integer idComando) throws ErroRepositorioException;

	/**
	 * 
	 * [UC0869] Gerar Arquivo Texto das Contas em Cobrança por Empresa
	 * 
	 * @author Mariana Victor
	 * @data 09/05/2011
	 * 
	 * @param
	 * @return void
	 */
	public void atualizarIndicadorGeracaoTxt(Collection idsComandos) throws ErroRepositorioException;

	/**
	 * [UC1168] Encerrar Comandos de Cobrança por Empresa
	 *
	 * Pesquisa os ids dos imóveis e das ordens de serviços geradas para um
	 * determinado comando
	 *
	 * @author Mariana Victor
	 * @created 09/05/2011
	 * @throws ErroRepositorioException
	 * 
	 */
	public Collection<Object[]> pesquisarImovelOrdemServicoParaEncerrarComando(int quantidadeInicio, Integer idComando) throws ErroRepositorioException;

	public void atualizarDataEncerramentoComando(Integer idComando) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsImoveis(MovimentarOrdemServicoGerarOSHelper helper) throws ErroRepositorioException;

	public Integer pesquisarReferenciaContaPorId(Integer idConta) throws ErroRepositorioException;

	public boolean verificaContaVinculadaAContratoParcelAtivo(Integer idConta) throws ErroRepositorioException;

	public boolean verificaGuiaVinculadaAContratoParcelAtivo(Integer idGuia) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarDadosOSGeradasPelaEmpresa(Integer idComando, Integer idTipoServico) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarDadosOSRegistroAtendimento(Integer idComando, Integer idTipoServico) throws ErroRepositorioException;

	public Collection pesquisarContasImovelDataVencimentoOriginal(Integer idImovel, int indicadorPagamento, int indicadorConta, String contaSituacaoNormal, String contaSituacaoRetificada,
			String contaSituacaoIncluida, String anoMesInicialReferenciaDebito, String anoMesFinalReferenciaDebito, Date anoMesInicialVecimentoDebito, Date anoMesFinalVencimentoDebito,
			int indicadorDividaAtiva) throws ErroRepositorioException;

	public ComandoEmpresaCobrancaConta pesquisarComandoEmpresaCobrancaConta(Integer idComando) throws ErroRepositorioException;

	public Short pesquisarSituacaoOrdemServico(Integer numeroOS) throws ErroRepositorioException;

	public Boolean verificarOrdemServicoComando(Integer numeroOS, Integer idComando) throws ErroRepositorioException;

	public List<Object[]> pesquisarOrdensServicoContasPagasParceladas() throws ErroRepositorioException;

	public Collection obterColecaoEmpresaCobrancaContaResultadoporImovel(Integer id) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarEmpresasComandosCobrancaAtivosExecutados() throws ErroRepositorioException;

	public Collection<Integer> pesquisarComandosCobrancaAtivosExecutados(Integer idEmpresa) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarOrdensServicoAtivasComando(Integer idComando) throws ErroRepositorioException;

	public Boolean verificarExisteContasEmAberto(Integer idOS) throws ErroRepositorioException;

	public boolean verificaDebitoACobrarVinculadoAContratoParcelAtivo(Integer idDebitoACobrar) throws ErroRepositorioException;

	public Object[] obterDadosDocumentoCobrancaItemContratoParcelamento(Integer idPrestacao) throws ErroRepositorioException;

	public Object[] pesquisarDadosQtdContasEDiasVencidos(Integer idComando) throws ErroRepositorioException;

	public Collection pesquisarDadosPopupExtensaoComandoAguaSituacao(Integer idComando) throws ErroRepositorioException;

	public Collection pesquisarOSEncerradasPorBoletimRotaAlternativa(Integer idGrupoCobranca, Integer idItemServicoContrato, Integer referencia) throws ErroRepositorioException;

	public Collection pesquisarOSEncerradasPorBoletimComIndicadorPavimentoRotaAlternativa(Integer idGrupoCobranca, Integer idItemServicoContrato, Short indicadorPavimento, Integer referencia)
			throws ErroRepositorioException;

	public Collection pesquisarOSEncerradasPorBoletimRepAsfaltoRotaAlternativa(Integer idGrupoCobranca, Integer idItemServicoContrato, Integer referencia) throws ErroRepositorioException;

	public Collection pesquisarOSEncerradasPorBoletimRepParalaloRotaAlternativa(Integer idGrupoCobranca, Integer idItemServicoContrato, Integer referencia) throws ErroRepositorioException;

	public Collection pesquisarOSEncerradasPorBoletimRepCalcadaRotaAlternativa(Integer idGrupoCobranca, Integer idItemServicoContrato, Integer referencia) throws ErroRepositorioException;

	public Collection pesquisarOSEncerradasPorBoletimPorDescontoRotaAlternativa(Integer idGrupoCobranca, Integer idItemServicoContrato, Integer referencia) throws ErroRepositorioException;

	public Collection pesquisarOSEncerradasPorBoletimPorDescontoSemDecursoPrazoRotaAlternativa(Integer idGrupoCobranca, Integer idItemServicoContrato, Integer referencia)
			throws ErroRepositorioException;

	public Collection<CmdEmpresaCobrancaContaLigacaoAguaSituacao> pesquisarColecaoLigacaoAguaSituacaoPorComandoEmpresaCobrancaConta(Integer idComando) throws ErroRepositorioException;

	public Short obterIndicadorAcrescimosClienteResponsavel(Integer idImovel) throws ErroRepositorioException;

	public Short obterIndicadorAcrescimosCliente(Integer idCliente) throws ErroRepositorioException;

	public Collection<CobrancaDocumento> pesquisarCobrancaDocumentoEmitirEtiquetasEnderecoAlternativo(Integer idCobrancaAcaoCronograma, Integer idCobrancaAcaoComando, Date dataEmissao,
			Integer idCobrancaAcao) throws ErroRepositorioException;

	public void atualizarDocumentoDeCobrancaHistorico(Integer codigoImovel, Integer codigoParcelamento) throws ErroRepositorioException;

	public BigDecimal getPercentualDescontoPorFaixa(Integer referencia) throws ErroRepositorioException;
	
	public Collection<ArquivoTextoNegociacaoCobrancaEmpresaHelper> pesquisarDadosArquivoTextoParcelamentosContasCobrancaEmpresa(Integer idEmpresa, Integer referenciaInicial,
		      Integer referenciaFinal)
		      throws ErroRepositorioException;
		  
	public Collection<ArquivoTextoNegociacaoCobrancaEmpresaHelper> pesquisarDadosArquivoTextoExtratosContasCobrancaEmpresa(Integer idEmpresa, Integer referenciaInicial,
      Integer referenciaFinal) throws ErroRepositorioException;
	  
	public List<Parcelamento> obterParcelamentosCobrancaEmpresa(Integer idEmpresa) throws ErroRepositorioException;
	  
	public List<ContaGeral> obterContasParcelamentosCobrancaEmpresa(Integer idParcelamento, Integer idEmpresa) throws ErroRepositorioException;
	  
	public List<CobrancaDocumento> obterExtratosCobrancaEmpresa(Integer idEmpresa) throws ErroRepositorioException;
	  
	public List<ContaGeral> obterContasExtratosCobrancaEmpresa(Integer idExtrato, Integer idEmpresa) throws ErroRepositorioException;
	  
	public List<GuiaPagamentoGeral> obterGuiasCobrancaEmpresa(Integer idEmpresa) throws ErroRepositorioException;
	  
	public List<ContaGeral> obterContasGuiaCobrancaEmpresa(Integer idGuia, Integer idEmpresa) throws ErroRepositorioException;
	
	public List<NegociacaoCobrancaEmpresa> obterNegociacoesEmpresa(List<Integer> negociacoes) throws ErroRepositorioException;

	public void atualizarPagamentosCobrancaPorEmpresaGerados(List<Integer> idsPagamentos) throws ErroRepositorioException;

	public Date obterDataVencimentoEntradaParcelamento(Integer idParcelamento) throws ErroRepositorioException;
}