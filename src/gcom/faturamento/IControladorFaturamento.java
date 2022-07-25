package gcom.faturamento;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.IClienteConta;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Contrato;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.NacionalFeriado;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.faturamento.bean.CalcularValoresAguaEsgotoHelper;
import gcom.faturamento.bean.ContasEmRevisaoRelatorioHelper;
import gcom.faturamento.bean.DeclaracaoQuitacaoAnualDebitosHelper;
import gcom.faturamento.bean.DeterminarValoresFaturamentoAguaEsgotoHelper;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.bean.EmitirHistogramaAguaHelper;
import gcom.faturamento.bean.ExecutarAtividadeFaturamentoHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaAguaHelper;
import gcom.faturamento.bean.GerarRelatorioAnormalidadeConsumoHelper;
import gcom.faturamento.bean.GerarRelatorioAnormalidadePorAmostragemHelper;
import gcom.faturamento.bean.PrescreverDebitosImovelHelper;
import gcom.faturamento.bean.RetornoAtualizarFaturamentoMovimentoCelularHelper;
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.faturamento.bean.VolumesFaturadosRelatorioHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.conta.ComunicadoEmitirConta;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.ContaImpressaoTermicaQtde;
import gcom.faturamento.conta.ContaMensagem;
import gcom.faturamento.conta.ContaMotivoCancelamento;
import gcom.faturamento.conta.ContaMotivoInclusao;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FaturaItem;
import gcom.faturamento.conta.GerarImpostosDeduzidosContaHelper;
import gcom.faturamento.conta.IConta;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.gui.cobranca.cobrancaporresultado.MovimentarOrdemServicoEmitirOSHelper;
import gcom.gui.faturamento.FaturamentoImediatoAjusteHelper;
import gcom.gui.faturamento.ImovelFaturamentoSeletivo;
import gcom.gui.faturamento.consumotarifa.bean.CategoriaFaixaConsumoTarifaHelper;
import gcom.gui.portal.ConsultarEstruturaTarifariaPortalHelper;
import gcom.interceptor.RegistradorOperacao;
import gcom.micromedicao.ArquivoTextoRetornoIS;
import gcom.micromedicao.Rota;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.consumo.ConsumoTipo;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.relatorio.faturamento.FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper;
import gcom.relatorio.faturamento.FiltrarRelatorioJurosMultasDebitosCanceladosHelper;
import gcom.relatorio.faturamento.RelatorioContasRetidasHelper;
import gcom.relatorio.faturamento.RelatorioFaturasAgrupadasBean;
import gcom.relatorio.faturamento.RelatorioJurosMultasDebitosCanceladosHelper;
import gcom.relatorio.faturamento.RelatorioProtocoloEntregaFaturaBean;
import gcom.relatorio.faturamento.RelatorioReceitasAFaturarHelper;
import gcom.relatorio.faturamento.RelatorioReceitasAFaturarPorCategoriaHelper;
import gcom.relatorio.faturamento.autoinfracao.RelatorioAutoInfracaoBean;
import gcom.relatorio.faturamento.conta.RelatorioContasCanceladasRetificadasHelper;
import gcom.relatorio.faturamento.dto.RelatorioAgenciaReguladoraDTO;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

import java.io.BufferedReader;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IControladorFaturamento {

	@SuppressWarnings("rawtypes")
	public void inserirFaturamentoCronograma(Collection faturamentoAtividadeCronogramas, FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal, RegistradorOperacao registradorOperacao,
			Usuario usuarioLogado, Integer anoMesInformado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void inserirFaturamentoGrupoCronogramaMensal(FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal, Collection faturamentoAtividadeCronogramas, Usuario usuarioLogado,
			Integer anoMesInformado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void faturarGrupoFaturamento(Collection colecaoRotas, FaturamentoGrupo faturamentoGrupo, int atividade, int idFuncionalidadeIniciada) throws ControladorException;

	public void preFaturarGrupoFaturamento(Rota rota, Integer anoMesFaturamento, Integer idFaturamentoGrupo, int idFuncionalidadeIniciada) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarFaturamentoGrupoCronogramaMensal(FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal, Collection faturamentoAtividadeCronogramas,
			Collection colecaoTodasAtividades, Usuario usuarioLogado) throws ControladorException;

	public void verificarExistenciaCronogramaGrupo(FaturamentoGrupo faturamentoGrupo) throws ControladorException;

	public boolean verificarExistenciaCronogramaAtividadeGrupo(FaturamentoAtividade faturamentoAtividade, FaturamentoGrupo faturamentoGrupo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection selecionarAtividadeFaturamentoQuePodeSerComandada(FaturamentoGrupo faturamentoGrupo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection verificarExistenciaRotaGrupo(FaturamentoGrupo faturamentoGrupo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection verificarSituacaoAtividadeRota(Collection colecaoRotasGrupo, FaturamentoAtividade faturamentoAtividade, FaturamentoGrupo faturamentoGrupo, boolean habilitada)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer inserirComandoAtividadeFaturamento(FaturamentoGrupo faturamentoGrupo, FaturamentoAtividade faturamentoAtividade, Collection colecaoRotas, Date dataVencimentoGrupo,
			Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection buscarAtividadeComandadaNaoRealizada(Integer numeroPagina) throws ControladorException;

	public Integer buscarAtividadeComandadaNaoRealizadaCount() throws ControladorException;

	public void removerComandoAtividadeFaturamento(String[] ids) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarComandoAtividadeFaturamento(FaturamentoAtividadeCronograma faturamentoAtividadeCronograma, Collection colecaoFaturamentoAtividadeCronogramaRota) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgoto(Integer anoMesReferencia, Integer ligacaoSituacaoAguaId, Integer ligacaoSituacaoEsgotoId,
			Short indicadorFaturamentoAgua, Short indicadorFaturamentoEsgoto, Collection categoriasImovel, Integer consumoFaturadoAguaMes, Integer consumoFaturadoEsgotoMes, int consumoMinimoLigacao,
			Date dataLeituraAnterior, Date dataLeituraAtual, BigDecimal percentualEsgoto, Integer tarifaImovel, ConsumoTipo consumoTipoAgua, ConsumoTipo consumoTipoEsgoto) throws ControladorException;

	public Date buscarDataLeituraCronograma(Imovel imovel, boolean situacao, Integer anoMesReferencia) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta(String mesAnoConta, String imovelID, Integer situacaoAguaConta, Integer situacaoEsgotoConta,
			Collection colecaoCategoriaOUSubcategoria, String consumoAgua, String consumoEsgoto, String percentualEsgoto, Integer idConsumoTarifaConta, Usuario usuarioLogado)
			throws ControladorException;

	public BigDecimal calcularValorTotalDebitoConta(Collection<DebitoCobrado> colecaoDebitoCobrado, Map<String, String[]> requestMap) throws ControladorException;

	public BigDecimal calcularValorTotalCreditoConta(Collection<CreditoRealizado> colecaoCreditoRealizado, Map<String, String[]> requestMap) throws ControladorException;
	
	@SuppressWarnings("rawtypes")
	public Integer inserirConta(Integer mesAnoConta, Imovel imovel, Collection colecaoDebitoCobrado, LigacaoAguaSituacao ligacaoAguaSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao,
			Collection colecaoCategoria, String consumoAgua, String consumoEsgoto, String percentualEsgoto, Date dataVencimentoConta, Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta,
			ContaMotivoInclusao contaMotivoInclusao, Map<String, String[]> requestMap, Usuario usuarioLogado, Integer leituraAnterior, Integer leituraAtual) throws ControladorException;

	public void cancelarConta(Collection<Conta> colecaoContas, String identificadores, ContaMotivoCancelamento contaMotivoCancelamento, Usuario usuarioLogado, boolean removerIdContaPagamento)
			throws ControladorException;

	public void colocarRevisaoConta(Collection<Conta> colecaoContas, String identificadores, ContaMotivoRevisao contaMotivoRevisao, Usuario usuarioLogado) throws ControladorException;

	public void retirarRevisaoConta(Collection<Conta> colecaoContas, String identificadores, Usuario usuario, boolean verificarPermissaoEspecial, Integer funcionalidade) throws ControladorException;

	public void alterarVencimentoConta(Collection<Conta> colecaoContas, String identificadores, Date dataVencimento, Usuario usuario) throws ControladorException;

	public Collection<DebitoCobrado> obterDebitosCobradosConta(Conta conta) throws ControladorException;

	public Collection<CreditoRealizado> obterCreditosRealizadosConta(Conta conta) throws ControladorException;

	public Integer inserirDebitoACobrar(Integer numeroPrestacoes, DebitoACobrar debitoACobrar, BigDecimal valorTotalServico, Imovel imovel, BigDecimal percentualAbatimento, BigDecimal valorEntrada,
			Usuario usuarioLogado, boolean debitoParaPagamentoAntecipado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public ArrayList calcularValorPrestacao(BigDecimal taxaJurosFinanciamento, Integer numeroPrestacoes, BigDecimal valorTotalServico, BigDecimal valorEntrada, BigDecimal percentualAbatimento,
			String idTipoDebito, BigDecimal valorTotalServicoAParcelar, Imovel imovel, Usuario usuario) throws ControladorException;

	public void inserirDebitoACobrarCategoria(DebitoACobrar debitoACobrar, Imovel imovel) throws ControladorException;

	public void cancelarDebitoACobrar(String[] ids, Usuario usuarioLogado, Integer matriculaImovel) throws ControladorException;

	public void removerTarifaConsumo(String[] ids) throws ControladorException;

	public void inserirConsumoTarifa(ConsumoTarifa consumoTarifa, ConsumoTarifaVigencia consumoTarifaVigencia, Collection<ConsumoTarifaCategoria> colecaoConsumoTarifaCategoria)
			throws ControladorException;

	public void atualizarConsumoTarifa(ConsumoTarifaVigencia consumoTarifaVigencia, Collection<CategoriaFaixaConsumoTarifaHelper> colecaoCategoriaFaixaConsumoTarifaHelper, String func)
			throws ControladorException;

	public BigDecimal calcularValorTotalAguaOuEsgotoPorCategoria(Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgotoHelper, String tipoRetorno) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public String[] inserirGuiaPagamento(GuiaPagamento guiaPagamento, Usuario usuarioLogado, Integer qtdeDiasVencimento, Collection colecaoGuiaPagamentoItem, Localidade localidadeParaCliente,
			boolean verificarPermissaoEspecial) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void manterGuiaPagamento(GuiaPagamento guiaPagamento, Collection guiasPagamento, String[] registrosRemocao, ImovelCobrancaSituacao imovelCobrancaSituacao, Usuario usarioLogado)
			throws ControladorException;

	public boolean verificarReferenciaFaturamentoCorrente(String anoMesFaturamento) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void inserirFaturamentoSituacaoHistorico(Collection collectionFaturamentoSituacaoHistorico) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoFaturamentoRelatorio(String opcaoTotalizacao, int anoMesReferencia, Integer gerenciaRegional, Integer localidade, Integer municipio, Integer unidadeNegocio,
			String opcaoRelatorio) throws ControladorException;

	public void inserirCreditoARealizar(Imovel imovel, CreditoARealizar creditoARealizaro, Usuario usuarioLogado) throws ControladorException;

	public void cancelarCreditoARealizar(String[] ids, Imovel imovel, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasImovelManter(Imovel imovel, Integer situacaoNormal, Integer situacaoIncluida, Integer situacaoRetificada) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasImovel(Integer imovel, Integer situacaoNormal, Integer situacaoIncluida, Integer situacaoRetificada, Integer anoMesReferenciaArrecadacao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterGuiasPagamentoImovel(Integer imovel, Integer situacaoNormal, Integer situacaoIncluida, Integer situacaoRetificada, Integer anoMesReferenciaArrecadacao)
			throws ControladorException;

	public void encerrarFaturamentoMes(Collection<Integer> colecaoIdsLocalidades, int idFuncionalidadeIniciada) throws ControladorException;

	public BigDecimal[] obterValorCurtoELongoPrazo(short numeroPrestacoes, short numeroPrestacoesCobradas, BigDecimal valorCategoria) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgotoTotalizando(Collection colecaoCalcularValoresAguaEsgotoHelper) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterDebitoACobrarCategoria(Integer debitoACobrarID) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterDebitoACobrarImovel(Integer imovelID, Integer debitoCreditoSituacaoAtualID, int anoMesFaturamento) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterCreditoARealizarImovelPorSituacao(Integer imovelID, Integer debitoCreditoSituacaoAtualID, int anoMesFaturamento, boolean prefaturamento) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterCreditoRealizarCategoria(Integer creditoARealizarID) throws ControladorException;

	public void removerFaturamentoCronograma(String[] ids, String pacoteNomeObjeto) throws ControladorException;

	public Conta pesquisarContaDigitada(String idImovel, String referenciaConta) throws ControladorException;

	public DebitoTipo pesquisarTipoDebitoDigitado(Integer idTipoDebitoDigitado) throws ControladorException;

	public Collection<ExecutarAtividadeFaturamentoHelper> obterAtividadesFaturamentoCronogramaComandada(Integer numeroPagina) throws ControladorException;

	public DebitoTipo pesquisarDebitoTipo(String idDebitoTipo) throws ControladorException;

	public BigDecimal calcularPrestacao(BigDecimal taxaJurosFinanciamento, Integer numeroPrestacoes, BigDecimal valorTotalServico, BigDecimal valorEntrada) throws ControladorException;

	public void reajustarTarifaConsumo(Map<ConsumoTarifaVigencia, Map<ConsumoTarifaCategoria, BigDecimal>> mapReajuste) throws ControladorException;

	public void gerarTaxaEntregaDeContaEmOutroEndereco(Collection<Rota> rotas, Integer anoMes, int idFuncionalidadeIniciada) throws ControladorException;

	public Collection<FaturamentoGrupo> pesquisarFaturamentoGrupoComCronogramaMensalParaMesCorrente() throws ControladorException;

	public Collection<FaturamentoGrupo> pesquisarFaturamentoGrupoComCronogramaMensalParaMesCorrenteSemGupoSelecionado(Integer grupoSelecionado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection gerarDebitosACobrarDeAcrescimosPorImpontualidade(Collection rotas, Short indicadorGeracaoMulta, Short indicadorGeracaoJuros, Short indicadorGeracaoAtualizacao,
			int idFuncionalidadeIniciada, boolean indicadorEncerrandoArrecadacao) throws ControladorException;

	public void gerarFaturaClienteResponsavel(int idFuncionalidadeIniciada) throws ControladorException;

	public void inserirMensagemConta(ContaMensagem contaMensagem, String[] setorComercial, String[] quadra) throws ControladorException;

	public void executarAtividadeFaturamento(String[] idsFaturamentoAtividadeCronograma) throws ControladorException;

	public void emitirFaturaClienteResponsavel(Collection<Fatura> colecaoFatura, Integer anoMesFaturamentoCorrente) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void restabelecerSituacaoAnteriorConta(Collection colecaoContas, Usuario usuario) throws ControladorException;

	public Integer pesquisarFaturamentoAtividadeCronogramaComandadaNaoRealizadaCount() throws ControladorException;

	public void emitirContas(Integer anoMesReferenciaFaturamento, FaturamentoGrupo faturamentoGrupo, int idFuncionalidadeIniciada, int tipoConta, Integer idEmpresa,
			Short indicadorEmissaoExtratoFaturamento) throws ControladorException;

	public void emitirContasOrgaoPublico(Integer anoMesReferenciaFaturamento, FaturamentoGrupo faturamentoGrupo, int idFuncionalidadeIniciada, int tipoConta, Integer idEmpresa,
			Short indicadorEmissaoExtratoFaturamento) throws ControladorException;

	public void atualizarMensagemConta(ContaMensagem contaMensagem) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarIdsTodasConta() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection gerarRelacaoAcompanhamentoFaturamento(String idImovelCondominio, String idImovelPrincipal, String idNomeConta, String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua,
			String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto, String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,

			String intervaloMediaMinimaImovelInicial, String intervaloMediaMinimaImovelFinal, String intervaloMediaMinimaHidrometroInicial, String intervaloMediaMinimaHidrometroFinal,

			String idImovelPerfil, String idPocoTipo, String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca, String idEloAnormalidade,
			String areaConstruidaInicial, String areaConstruidaFinal, String idCadastroOcorrencia, String idConsumoTarifa, String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial, String setorComercialFinal, String quadraInicial, String quadraFinal, String loteOrigem, String loteDestno, String cep,
			String logradouro, String bairro, String municipio, String idTipoMedicao, String indicadorMedicao, String idSubCategoria, String idCategoria, String quantidadeEconomiasInicial,
			String quantidadeEconomiasFinal, String diaVencimento, String idCliente, String idClienteTipo, String idClienteRelacaoTipo, String numeroPontosInicial, String numeroPontosFinal,
			String numeroMoradoresInicial, String numeroMoradoresFinal, String idAreaConstruidaFaixa, int anoMesReferencia
	) throws ControladorException;

	public Collection<FaturamentoAtividadeCronograma> pesquisarRelacaoAtividadesGrupo(Integer faturamentoGrupoId) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void validarFaturamentoCronograma(Collection faturamentoAtividadeCronogramas) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void iniciarProcessoReajustarTarifaConsumo(Map listaParametrosValoresCategoria, Date dataNovaVigencia, String[] idsRecuperados)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void validarFaturamentoCronogramaAtividadeMaiorQueMesAnoCronograma(int anoMes, Collection faturamentoAtividadeCronogramas)
			throws ControladorException;

	public Integer calcularConsumoTotalAguaOuEsgotoPorCategoria(Collection<CalcularValoresAguaEsgotoHelper> calcularValoresAguaEsgotoHelper, String tipoRetorno)
			throws ControladorException;

	public BigDecimal obterTarifaMinimaAguaImovel(Imovel imovel) throws ControladorException;

	public Collection<FaturamentoAtividadeCronograma> pesquisarFaturamentoAtividadeCronogramaComandadasNaoRealizadas(int numeroPagina)
			throws ControladorException;

	public int pesquisarFaturamentoAtividadeCronogramaComandadasNaoRealizadasCount() throws ControladorException;

	public Integer pesquisarExistenciaContaParaConcorrencia(String idConta, String ultimaAlteracao) throws ControladorException;

	public Integer verificarExistenciaDebitoTipo(Integer idDebitoTipo) throws ControladorException;

	public DebitoTipo pesquisarDebitoTipo(Integer idDebitoTipo) throws ControladorException;

	public CreditoTipo pesquisarCreditoTipo(Integer idCreditoTipo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarConta(Integer idConta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarContaHistorico(Integer idConta) throws ControladorException;

	public BigDecimal pesquisarValorMultasCobradas(int idConta) throws ControladorException;

	public Collection<EmitirContaHelper> emitir2ViaContas(Collection<Integer> idsContaEP, boolean cobrarTaxaEmissaoConta, Short contaSemCodigoBarras) throws ControladorException;

	public Collection<EmitirContaHelper> emitirGuiaPagamento(Integer idParcelamento) throws ControladorException;
	
	public Integer pesquisarIdClienteResponsavelConta(Integer idConta, boolean contaHistorico) throws ControladorException;

	public Integer[] determinarTipoLigacaoMedicao(EmitirContaHelper emitirContaHelper) throws ControladorException;

	public StringBuilder obterDadosConsumoAnterior(EmitirContaHelper emitirConta, int qtdMeses, Integer tipoLigacao, Integer tipoMedicao)
			throws ControladorException;

	public Object[] obterDadosMedicaoConta(EmitirContaHelper emitirContaHelper, Integer tipoMedicao) throws ControladorException;

	public String[] obterConsumoFaturadoConsumoMedioDiario(EmitirContaHelper emitirContaHelper, Integer tipoMedicao, String diasConsumo)
			throws ControladorException;

	public Short obterQuantidadeEconomiasConta(Integer idConta, boolean contaHistorico) throws ControladorException;

	public StringBuilder obterMensagemRateioConsumo(EmitirContaHelper emitirContaHelper, String consumoRateio, Object[] parmsMedicaoHistorico,
			Integer tipoMedicao) throws ControladorException;

	public String[] obterMensagemConta3Partes(EmitirContaHelper emitirContaHelper, SistemaParametro sistemaParametro) throws ControladorException;

	public Object[] pesquisarParmsQualidadeAgua(EmitirContaHelper emitirContaHelper) throws ControladorException;

	public StringBuilder[] gerarLinhasDemaisContas(EmitirContaHelper emitirContaHelper, Integer sequencialEmpresa, BigDecimal valorConta)
			throws ControladorException;

	public StringBuilder gerarLinhasDescricaoServicoTarifas(EmitirContaHelper emitirContaHelper, String consumoRateio, Object[] parmsMedicaoHistorico,
			Integer tipoMedicao) throws ControladorException;

	public GerarImpostosDeduzidosContaHelper gerarImpostosDeduzidosConta(Integer idImovel, Integer anoMesReferencia, BigDecimal valorAgua,
			BigDecimal valorEsgoto, BigDecimal valorDebito, BigDecimal valorCredito, boolean preFaturamento) throws ControladorException;
	
	public GerarImpostosDeduzidosContaHelper gerarImpostosDeduzidosConta(Conta conta, boolean preFaturamento) throws ControladorException;

	public void inserirClienteImovel(Imovel imovel, Conta contaAtual) throws ControladorException;

	public void inserirImpostosDeduzidosConta(GerarImpostosDeduzidosContaHelper impostosDeduzidosConta, Conta contaAtual) throws ControladorException;

	public void gerarDebitoACobrarDoacao(Collection<Rota> rotas, int idFuncionalidadeIniciada) throws ControladorException;

	public void validarExibirInserirGuiaPagamento(RegistroAtendimento ra, OrdemServico ordemServico, Integer idImovel, Integer idCliente)
			throws ControladorException;

	public Integer inserirGuiaPagamentoCodigoBarras(GuiaPagamento guiaPagamento, Integer idDebitoTipo) throws ControladorException;

	public void atualizarAnoMesReferenciaFaturamentoGrupo(FaturamentoGrupo faturamentoGrupo, Integer anoMesReferenciaFaturamento, int atividade)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public HashMap obterContaAgrupadasPorImovel(int anoMesReferenciaContabil, int idLocalidade, int idQuadra) throws ControladorException;

	public Imovel pesquisarImovelContaManter(FiltroImovel filtroImovel, Usuario usuarioLogado) throws ControladorException;

	public Object pesquisarDataUltimaAlteracaoConta(Integer idConta) throws ControladorException;

	public void atualizarDataHoraRealizacaoAtividade(Integer idAtividade, Integer anoMesReferencia, Integer idFaturamentoGrupo) throws ControladorException;

	public Date pesquisarDataRealizacaoFaturamentoAtividadeCronagrama(Integer idImovel, int quantidadeMeses) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasImovelIntervalo(Integer imovel, Integer situacaoNormal, Integer situacaoIncluida, Integer situacaoRetificada,
			Integer anoMesInicio, Integer anoMesFim, Integer idContaMotivoRevisao) throws ControladorException;

	public void atualizarAnoMesFaturamento(Integer anoMesFaturamentoSistemaParametro) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarIdsLocalidadeParaEncerrarFaturamento() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarIdsLocalidadeParaGerarResumoLigacoesEconomias() throws ControladorException;

	public void transferirContasParaHistorico(Collection<Conta> contas, int anoMesFaturamentoSistemaParametro) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarIndicadorContaNoHistorico(Collection colecaoContas) throws ControladorException;

	public void transferirDebitosACobrarParaHistorico(Collection<DebitoACobrar> debitosACobrar) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarIndicadorDebitoACobrarNoHistorico(Collection colecaoDebitosACobrar) throws ControladorException;

	public void transferirCreditoARealizarParaHistorico(Collection<CreditoARealizar> creditosARealizar) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarIndicadorCreditosARealizarNoHistorico(Collection colecaoCreditosARealizar) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarFaturamentoLigacoesMedicaoIndividualizadaRelatorio(FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql,
			String anoMesfaturamentoGrupo) throws ControladorException;

	public void emitirExtratoConsumoImovelCondominio(String anoMesFaturamento, String idFaturamento, int idFuncionalidadeIniciada) throws ControladorException;

	public Integer consultarQtdeRegistrosResumoFaturamentoRelatorio(int mesAnoReferencia, Integer localidade, Integer municipio, Integer gerenciaRegional,
			String opcaoTotalizacao) throws ControladorException;

	public Collection<Integer> pesquisarIdsLocalidade() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterConta(Integer idConta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarContasEmitidasRelatorio(int anoMesReferencia, Integer grupoFaturamento, Collection esferaPoder, String tipoImpressao)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer consultarQtdeContasEmitidasRelatorio(int anoMesReferencia, Integer grupoFaturamento, Collection esferaPoder) throws ControladorException;

	public void gerarHistoricoParaEncerrarFaturamento(int anoMesFaturamentoSistemaParametro, Integer idLocalidade, int idFuncionalidadeIniciada)
			throws ControladorException;

	public Integer retornaAnoMesFaturamentoGrupo(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarMapaControleContaRelatorio(Integer idGrupoFaturamento, String mesAno, Usuario usuarioLogado, String tipoRelatorio,
			String indicadorFichaCompensacao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarResumoContasLocalidade(Integer idGrupoFaturamento, String anoMes, Integer idFirma, String tipoImpressao)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasImoveis(Integer anoMes, Collection idsImovel, Date dataVencimentoContaInicio, Date dataVencimentoContaFim,
			Integer anoMesFim, String indicadorContaPaga) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void chamarGerarArquivoTextoFaturamento(int anoMes, String idCliente, Collection colecaoClientesAptos);

	@SuppressWarnings("rawtypes")
	public void cancelarConjuntoConta(Collection colecaoImovel, ContaMotivoCancelamento contaMotivoCancelamento, Integer anoMes,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim, Usuario usuarioLogado, String indicadorContaPaga)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void alterarVencimentoConjuntoConta(Collection colecaoImovel, Date dataVencimento, Integer anoMes, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim, Usuario usuario, String indicadorContaPaga, String[] bancos) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void alterarVencimentoConjuntoConta(Collection colecaoImovel, Date dataVencimento, Integer anoMes, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim, Usuario usuario, String indicadorContaPaga, String[] bancos, boolean isDebitoAutomatico)
			throws ControladorException;

	public void informarConsumoTarifaSubcategoria(ConsumoTarifa consumoTarifa, ConsumoTarifaVigencia consumoTarifaVigencia,
			Collection<ConsumoTarifaCategoria> colecaoConsumoTarifaCategoria) throws ControladorException;

	public void verificarConsumoFaturadoAgua(Integer idLigacaoAguaSituacao, Integer consumoFaturado) throws ControladorException;

	public void verificarConsumoFaturadoEsgoto(Integer idLigacaoEsgotoSituacao, Integer consumoFaturado) throws ControladorException;

	public Object[] pesquisarAnoMesEDiaVencimentoFaturamentoGrupo(Integer idImovel) throws ControladorException;

	public BigDecimal pesquisarValorMultasCobradasPorFinanciamnetoTipo(int idConta) throws ControladorException;

	public BigDecimal obterTarifaMinimaAguaImovelPorSubcategoria(Imovel imovel) throws ControladorException;

	public int pesquisarQuantidadeDebitosCobradosComParcelamento(Collection<ContaValoresHelper> colecaoContasValores) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarConjuntoContaEmitir2Via(Collection colecaoImovel, Integer anoMes, Date dataVencimentoContaInicio, Date dataVencimentoContaFim,
			Integer anoMesFim, String indicadorContaPaga) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void gerarCreditoARealizarPorImoveisDoGrupo(Collection idsGrupos, Integer anoMesReferenciaConta, Integer anoMesReferenciaDebito)
			throws ControladorException;

	public Date obterDataVencimentoFaturamentoGrupo(int diaVencimento, int mesVencimento, int anoVencimento) throws ControladorException;

	public Integer pesquisarAnoMesReferenciaFaturamentoGrupo(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarConsumoTarifaRelatorio(String descricao, Date dataVigenciaInicial, Date dataVigenciaFinal) throws ControladorException;

	public Date pesquisarDataFinalValidadeConsumoTarifa(Integer idConsumoTarifa, Date dataInicioVigencia) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer obterContasConjuntoImoveis(Integer anoMes, Collection idsImovel, Integer codigoCliente, Short relacaoTipo, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer idGrupoFaturamento, Integer anoMesFinal, String indicadorContaPaga, Integer somenteDebitoAutomatico)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer obterContasRevisaoConjuntoImoveis(Integer anoMes, Collection idsImovel, Integer codigoCliente, Short relacaoTipo,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer idGrupoFaturamento, Integer anoMesFinal, String indicadorContaPaga)
			throws ControladorException;

	public void cancelarConjuntoContaCliente(Integer codigoCliente, Short relacaoTipo, ContaMotivoCancelamento contaMotivoCancelamento, Integer anoMes,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim, Usuario usuarioLogado) throws ControladorException;

	public void alterarVencimentoConjuntoContaCliente(Integer codigoCliente, Short relacaoTipo, Date dataVencimentoInformada, Integer anoMes,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim, Usuario usuario, Integer codigoClienteSuperior,
			boolean isDebitoAutomatico) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarConjuntoContaClienteEmitir2Via(Integer codigoCliente, Short relacaoTipo, Integer anoMes, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasNaoEmRevisaoOuEmRevisaoPorAcaoUsuario(Collection idsConta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasEmRevisaoPorAcaoUsuario(Collection idsConta) throws ControladorException;

	public Integer inserirTipoCredito(CreditoTipo creditoTipo, Usuario usuarioLogado) throws ControladorException;

	public Integer atualizarTipoCredito(CreditoTipo creditoTipo, Usuario usuarioLogado) throws ControladorException;

	public void removerTipoCredito(String[] ids, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioAnaliticoFaturamento(int anoMesFaturamento, Integer idFaturamentoGrupo, int indicadorLocalidadeInformatizada,
			Collection idLocalidades, Collection idSetores, Collection idQuadras, String tipoRelatorio, Usuario usuarioLogado);

	@SuppressWarnings("rawtypes")
	public Integer informarNaoEntregaDocumentos(Collection colecaoDocumentosNaoEntregues, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection<EmitirContaHelper> emitir2ViaContasHistorico(Collection idsContaEP, boolean cobrarTaxaEmissaoConta, Short contaSemCodigoBarras)
			throws ControladorException;

	public Collection<EmitirHistogramaAguaHelper> pesquisarEmitirHistogramaAgua(FiltrarEmitirHistogramaAguaHelper filtro) throws ControladorException;

	public void inserirClienteConta(Conta conta, Imovel imovel) throws ControladorException;

	public Integer inserirDebitoTipo(String descricao, String descricaoAbreviada, String idTipoFinanciamento, String indicadorGeracaoDebitoAutomatica,
			String indicadorGeracaoDebitoConta, String idLancamentoItemContabil, String valorLimeteDebito, Usuario usuarioLogado, String valorSugerido,
			String indicadorDebitoCartaoCredito, String indicadorJurosParCliente) throws ControladorException;

	public void atualizarDebitoTipo(DebitoTipo debitoTipoBase, String id, String descricao, String descricaoAbreviada, String idTipoFinanciamento,
			String indicadorGeracaoDebitoAutomatica, String indicadorGeracaoDebitoConta, String idLancamentoItemContabil, String valorLimiteDebito,
			String indicadorUso, Usuario usuarioLogado, String valorSugerido, String indicadorDebitoCartaoCredito, String indicadorJurosParCliente)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Date determinarMenorDataVencimentoConta(Collection colecaoContas) throws ControladorException;

	public void removerContratosDemanda(String[] idsContratosDemanda, Usuario usuarioLogado) throws ControladorException;

	public Date obterDataVencimentoGrupo(Integer idFaturamentoGrupo, Integer anoMesInformado) throws ControladorException;

	public void atualizarContratoDemanda(Contrato contratoDemanda, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDataRevisaoConta(Collection idsConta) throws ControladorException;

	public boolean verificarImoveisComDebito(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarResumoMetas(Integer anoMesReferencia) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarResumoMetasAcumulado(Integer anoMesReferencia) throws ControladorException;

	public Short recuperarValorMaximoSequencialImpressaoMais10() throws ControladorException;

	public Integer pesquisarDebitoCreditoSituacaoAtualConta(Integer idImovel, Integer anoMesReferencia) throws ControladorException;

	public void cancelarConjuntoConta(Integer idGrupoFaturamento, ContaMotivoCancelamento contaMotivoCancelamento, Integer anoMes,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Integer anoMesFim, Usuario usuarioLogado) throws ControladorException;

	public void alterarVencimentoConjuntoConta(Integer idGrupoFaturamento, Date dataVencimentoInformada, Integer anoMes, Integer anoMesFim,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim, Usuario usuarioLogado, boolean somenteDebitoAutomatico) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarConjuntoContaEmitir2Via(Integer idGrupoFaturamento, Integer anoMes, Date dataVencimentoContaInicio, Date dataVencimentoContaFim,
			Integer anoMesFim) throws ControladorException;

	public void gerarCreditoARealizarPorImoveisComContasComVencimento14_08_2007() throws ControladorException;

	public Conta pesquisarContaAtualizacaoTarifaria(Integer idConta) throws ControladorException;

	public Date pesquisarFaturamentoAtividadeCronogramaDataPrevista(Integer faturamentoGrupoId, Integer faturamentoAtividadeId, Integer anoMesReferencia)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void inserirQualidadeAgua(QualidadeAgua qualidadeAgua, Collection colecaoQualidadeAgua, Usuario usuarioLogado,
			QualidadeAguaPadrao qualidadeAguaPadrao) throws ControladorException;

	public Fatura pesquisarFaturaPorQualificador(Short codigoQualificador, Integer anoMesReferencia, BigDecimal valorDebito) throws ControladorException;

	public Integer obterValorConsumoMedio6meses(Integer idImovel) throws ControladorException;

	public Collection<VolumesFaturadosRelatorioHelper> pesquisarDadosRelatorioVolumesFaturados(Integer idLocalidade, Integer anoMes, Integer anoMes1,
			Integer anoMes2, Integer anoMes3, Integer anoMes4, Integer anoMes5, Integer anoMes6) throws ControladorException;

	public Collection<VolumesFaturadosRelatorioHelper> pesquisarDadosRelatorioVolumesFaturadosResumido(Integer idLocalidade, Integer anoMes, Integer anoMes1,
			Integer anoMes2, Integer anoMes3, Integer anoMes4, Integer anoMes5, Integer anoMes6) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection<ContasEmRevisaoRelatorioHelper> pesquisarDadosRelatorioContasRevisao(Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal, Integer codigoSetorComercialInicial, Integer codigoSetorComercialFinal,
			Collection colecaoIdsMotivoRevisao, Integer idImovelPerfil, Integer referenciaInicial, Integer referenciaFinal, Integer idCategoria,
			Integer idEsferaPoder) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection<ContasEmRevisaoRelatorioHelper> pesquisarDadosRelatorioContasRevisaoResumido(Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal, Integer codigoSetorComercialInicial, Integer codigoSetorComercialFinal,
			Collection colecaoIdsMotivoRevisao, Integer idImovelPerfil, Integer referenciaInicial, Integer referenciaFinal, Integer idCategoria,
			Integer idEsferaPoder) throws ControladorException;

	public Collection<GerarRelatorioAnormalidadeConsumoHelper> pesquisarDadosRelatorioAnormalidadeConsumo(Integer idGrupoFaturamento, Short codigoRota,
			Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal, Integer idSetorComercialInicial,
			Integer idSetorComercialFinal, Integer referencia, Integer idImovelPerfil, Integer numOcorConsecutivas, String indicadorOcorrenciasIguais,
			Integer mediaConsumoInicial, Integer mediaConsumoFinal, Collection<Integer> colecaoIdsAnormalidadeConsumo,
			Collection<Integer> colecaoIdsAnormalidadeLeitura, Collection<Integer> colecaoIdsAnormalidadeLeituraInformada, Integer tipoMedicao,
			Collection<Integer> colecaoIdsEmpresa, Integer numeroQuadraInicial, Integer numeroQuadraFinal, Integer idCategoria) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasAtualizacaoTarifaria(Integer idImovel, Integer inicialReferencia, Integer finalReferencia, Date inicialVencimento,
			Date finalVencimento, Integer indicadorContasRevisao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoFaixaValores(Integer idFaixaValor, Double valorFaixa) throws ControladorException;

	public void removerQualidadeAgua(String[] ids, Usuario usuarioLogado) throws ControladorException;

	public boolean verificarDebitoMais3MesesFaturaEmAberto(Integer anoMesReferencia, Integer idImovel) throws ControladorException;

	public Boolean pesquisarExisteciaParcelamentoConta(Integer idConta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection montarColecaoContaCategoria(Collection colecaoSubcategoria, Conta conta);

	@SuppressWarnings("rawtypes")
	public Collection pesquisarFaturamentoLigacoesMedicaoIndividualizadaRelatorio(Collection<Imovel> colecaoImoveisGerarRelatorio, String anoMesfaturamentoGrupo) throws ControladorException;

	public Conta obterImovelLocalidadeConta(Integer idConta) throws ControladorException;

	public ContaHistorico obterImovelLocalidadeContaHistorico(Integer idConta) throws ControladorException;

	public void atualizaQtdeParcelaPagaConsecutivaEParcelaBonus(Integer idLocalidade, int idFuncionalidadeIniciada) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection gerarRelatorioContasCanceladas(RelatorioContasCanceladasRetificadasHelper helper) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection gerarRelatorioContasRetificadas(RelatorioContasCanceladasRetificadasHelper helper) throws ControladorException;

	public Integer pesquisarMaxIdConta() throws ControladorException;

	public Integer pesquisarMaxIdContaHistorico() throws ControladorException;

	public Collection<RelatorioFaturasAgrupadasBean> pesquisarDadosRelatorioFaturasAgrupadas(Integer anoMesReferencia, Cliente cliente, Collection<Integer> idsClientes) throws ControladorException;

	public Integer pesquisarDadosRelatorioFaturasAgrupadasCount(Integer anoMesReferencia, Cliente cliente, Collection<Integer> idsClientes) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClientesFaturas(Integer idEsferaPoder) throws ControladorException;

	public BigDecimal pesquisarPercentualAliquota() throws ControladorException;

	public Collection<RelatorioProtocoloEntregaFaturaBean> pesquisarDadosRelatorioProtocoloEntregaFatura(Integer anoMesReferencia, Cliente cliente,
			Collection<Integer> idsClientes) throws ControladorException;

	public Integer inserirContratoDemanda(Contrato contratoDemanda, Usuario usuarioLogado) throws ControladorException;

	public Collection<FaturamentoSituacaoHistorico> pesquisarSituacaoEspecialFaturamentoVigente(Integer idImovel, Integer anoMesReferencia)
			throws ControladorException;

	public Integer pesquisarQuantidadeContasEContasHistorico(Integer idImovel, Integer referenciaConta) throws ControladorException;

	public Collection<RelatorioAutoInfracaoBean> pesquisarDadosRelatorioAutoInfracao(Integer idUnidadeNegocio, Integer idFuncionario,
			Integer dataPagamentoInicial, Integer dataPagamentoFinal) throws ControladorException;

	public boolean permiteFaturamentoParaAgua(LigacaoAguaSituacao ligacaoAguaSituacao, Integer consumoAgua, ConsumoTipo consumoTipo)
			throws ControladorException;

	public void gerarArquivoTextoParaFaturamento(Rota rota, Integer anoMesFaturamento, FaturamentoGrupo faturamentoGrupo, Date dataComando,
			int idFuncionalidadeIniciada) throws ControladorException;

	public Boolean verificarGrupoFaturamentoComandado(int anoMesReferenciaFaturamento, int idGrupoFaturamento) throws ControladorException;

	public Integer obterReferenciaContabilConta(SistemaParametro sistemaParametro);

	@SuppressWarnings("rawtypes")
	public Collection obterDebitoACobrarImovel(Integer imovelID) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterDebitoACobrarHistoricoImovel(Integer imovelID) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterCreditoARealizarImovel(Integer imovelID) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterCreditoARealizarHistoricoImovel(Integer imovelID) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterGuiaPagamentoImovel(Integer imovelID) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterGuiaPagamentoHistoricoImovel(Integer imovelID) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCategoriaPorTarifaConsumo(Integer idConsumoTarifa) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioGuiaPagamentoEmAtraso(FiltroGuiaPagamento filtro) throws ControladorException;

	public boolean permiteFaturamentoParaEsgoto(LigacaoEsgotoSituacao ligacaoEsgotoSituacao, Integer consumoEsgoto, ConsumoTipo consumoTipo)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public DeterminarValoresFaturamentoAguaEsgotoHelper determinarValoresFaturamentoAguaEsgoto(Imovel imovel, Integer anoMesFaturamento,
			Collection colecaoCategoriasOUSubCategorias, FaturamentoGrupo faturamentoGrupo, ConsumoHistorico consumoHistoricoAgua,
			ConsumoHistorico consumoHistoricoEsgoto) throws ControladorException;

	public int inserirFaturaItemFaturaItemHistorico(Collection<FaturaItem> colecaoFaturaItem, Usuario usuarioLogado) throws ControladorException;

	public void removerFaturaItemFaturaItemHistorico(Collection<FaturaItem> colecaoFaturaItemRemover, Usuario usuarioLogado) throws ControladorException;

	public BigDecimal somarValorFaturasItemFatura(Fatura fatura) throws ControladorException;

	public Date vencimentoFaturasItemFatura(Fatura fatura) throws ControladorException;

	public void alterarVencimentoFaturaFaturaItem(Fatura fatura) throws ControladorException;

	public Integer calcularConsumoMinimo(BigDecimal areaTotal, Integer anoMes, Collection<Categoria> colecaoCategoria,
			Collection<Subcategoria> colecaoSubcategoria, BigDecimal pontosUtilizacao, BigDecimal numeroMoradores) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void gerarCreditoSituacaoEspecialFaturamento(Collection colecaoFaturamentoAtividadeCronogramaRota, FaturamentoGrupo faturamentoGrupo, int atividade,
			int idFuncionalidadeIniciada) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection retornarLeiturasNaoRegistradas(FaturamentoGrupo faturamentoGrupo) throws ControladorException;

	public BigDecimal pesquisarValorCreditoPorOrigem(int idConta) throws ControladorException;

	public BigDecimal pesquisarValorAguaConta(Integer idImovel, Integer referencia) throws ControladorException;

	public Integer inserirFaturamentoSituacaoComando(SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper, boolean retirar)
			throws ControladorException;

	public void atualizarAutoInfracao(AutosInfracao autosInfracao, Usuario usuarioLogado) throws ControladorException;

	public void validarExistenciaDebitoAutoInfracao(Integer idAutosInfracao) throws ControladorException;

	public void validarDataEmissaoAutoInfracao(Date dataEmissao, Integer idAutoInfracaoSituacao, SistemaParametro sistemaParametro) throws ControladorException;

	public void validarDataInicioRecursoAutoInfracao(Date dataEmissao, Date dataInicioRecurso, SistemaParametro sistemaParametro) throws ControladorException;

	public void validarDataTerminoRecursoAutoInfracao(Date dataInicioRecurso, Date dataTerminoRecurso) throws ControladorException;

	public void validarQuantidadeParcelasAutoInfracao(Integer numeroParcelas) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void inserirSituacaoEspecialFaturamento(SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper, boolean retirar,
			Collection colecaoImoveisParaInserir, Integer idFaturamentoSituacaoTipo, Integer anoMesReferenciaInicial, Integer anoMesReferenciaFinal)
			throws ControladorException;

	public int countRelatorioAutoInfracao(Integer idUnidadeNegocio, Integer idFuncionario, Integer dataPagamentoInicial, Integer dataPagamentoFinal)
			throws ControladorException;

	public boolean verificarAutosAssociadosAoDebito(String[] idsDebitosACobrar) throws ControladorException;

	public void cancelarAutosInfracao(String[] idsDebitosACobrar) throws ControladorException;

	public boolean validarExistenciaDebitoAtivosAutoInfracao(Integer idAutoInfracao) throws ControladorException;

	public boolean validarExistenciaDeDebitosAutoInfracao(Integer idAutoInfracao) throws ControladorException;

	public void atualizarSituacaoEspecialFaturamento(FaturamentoSituacaoComando comandoOriginal, FaturamentoSituacaoComando comandoInserir,
			ArrayList<FaturamentoSituacaoHistorico> colecaoHistoricoInserir, FaturamentoSituacaoComando comandoRetirar,
			ArrayList<FaturamentoSituacaoHistorico> colecaoHistoricoRetirar) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCartoes(Integer idArrecadacaoForma) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void confimarParcelamentoCartaoCredito(Parcelamento parcelamento, Collection parcelamentoPagamentoCartaoCreditoCollection, Collection debitoACobrar,
			Collection<CreditoARealizar> colecaoCreditoAtualizar, Usuario usuario) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasParaPagamentoParcial(Integer idImovel, Integer idDebitoTipo) throws ControladorException;

	public void validarValorTotalServicoParaPagamentoParcial(Integer idConta, Integer idDebitoTipo, BigDecimal valorTotalServico) throws ControladorException;

	public Collection<Integer> pesquisarIdsSetorParaGerarHistoricoParaEncerrarFaturamento() throws ControladorException;

	public Integer pesquisarSituacaoEspecialFaturamentoCount(FaturamentoSituacaoComando comando) throws ControladorException;

	public Collection<FaturamentoSituacaoComando> pesquisarSituacaoEspecialFaturamento(FaturamentoSituacaoComando comando, Integer numeroPaginasPesquisa)
			throws ControladorException;

	public void gerarBonusTarifaSocial(FaturamentoGrupo faturamentoGrupo, SistemaParametro sistemaParametro, Collection<Rota> colecaoRotas,
			int idFuncionalidadeIniciada) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarVencimentoConta(Integer idImovel, Integer anoMesReferencia) throws ControladorException;

	public QualidadeAgua getQualidadeAgua(Imovel imovel, Integer anoMes) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDataPagamento(Integer idContal) throws ControladorException;

	public Collection<Conta> obterContas(int anoMesReferenciaContabil, int idLocalidade, int idQuadra) throws ControladorException;

	public Date pesquisarDataPrevistaFaturamentoAtividadeCronograma(Integer idImovel, int quantidadeMeses) throws ControladorException;

	public Integer pesquisarQuantidadeContasCanceladasOuRetificadas(RelatorioContasCanceladasRetificadasHelper helper, int tipoPesquisa)
			throws ControladorException;

	public boolean verificarExistenciaIdGrupoFaturamento(Integer id) throws ControladorException;

	public RetornoAtualizarFaturamentoMovimentoCelularHelper atualizarFaturamentoMovimentoCelular(BufferedReader buffer, String nomeArquivo, boolean offLine,
			boolean finalizarArquivo, Integer idRota, ArquivoTextoRetornoIS atualizarFaturamentoMovimentoCelular, BufferedReader bufferOriginal)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer pesquisarDadosRelatorioContasRevisaoCount(Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idLocalidadeInicial,
			Integer idLocalidadeFinal, Integer codigoSetorComercialInicial, Integer codigoSetorComercialFinal, Collection colecaoIdsMotivoRevisao,
			Integer idImovelPerfil, Integer referenciaInicial, Integer referenciaFinal, Integer idCategoria, Integer idEsferaPoder) throws ControladorException;

	public Integer gerarRelacaoAcompanhamentoFaturamentoCount(String idImovelCondominio, String idImovelPrincipal, String idNomeConta,
			String idSituacaoLigacaoAgua, String consumoMinimoInicialAgua, String consumoMinimoFinalAgua, String idSituacaoLigacaoEsgoto,
			String consumoMinimoInicialEsgoto, String consumoMinimoFinalEsgoto, String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,

			String intervaloMediaMinimaImovelInicial, String intervaloMediaMinimaImovelFinal, String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal,

			String idImovelPerfil, String idPocoTipo, String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo, String idSituacaoEspecialCobranca,
			String idEloAnormalidade, String areaConstruidaInicial, String areaConstruidaFinal, String idCadastroOcorrencia, String idConsumoTarifa,
			String idGerenciaRegional, String idLocalidadeInicial, String idLocalidadeFinal, String setorComercialInicial, String setorComercialFinal,
			String quadraInicial, String quadraFinal, String loteOrigem, String loteDestno, String cep, String logradouro, String bairro, String municipio,
			String idTipoMedicao, String indicadorMedicao, String idSubCategoria, String idCategoria, String quantidadeEconomiasInicial,
			String quantidadeEconomiasFinal, String diaVencimento, String idCliente, String idClienteTipo, String idClienteRelacaoTipo,
			String numeroPontosInicial, String numeroPontosFinal, String numeroMoradoresInicial, String numeroMoradoresFinal, String idAreaConstruidaFaixa,
			int anoMesReferencia

	) throws ControladorException;

	public Collection<RelatorioJurosMultasDebitosCanceladosHelper> pesquisarRelatorioJurosMultasDebitosCancelados(
			FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro) throws ControladorException;

	public int countRelatorioJurosMultasDebitosCancelados(FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro) throws ControladorException;

	public void gerarTxtContasProjetosEspeciais(String anoMes, Integer idCliente, Integer idFuncionalidadeIniciada) throws ControladorException;

	public Integer countTxtContasProjetosEspeciais(String anoMes, Integer idCliente) throws ControladorException;

	public String[] obterMensagemAnormalidadeConsumo(EmitirContaHelper emitirContaHelper) throws ControladorException;

	public Date[] gerarPeriodoLeituraFaturamento(Date dataLeituraAtualFaturamento, Date dataLeituraAnteriorFaturamento, FaturamentoGrupo faturamentoGrupo,
			Integer anoMesFaturamento, Integer anoMesFaturamentoAnterior) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void gerarResumoSimulacaoFaturamento(Collection colecaoFaturamentoAtividadeCronogramaRota, FaturamentoGrupo faturamentoGrupo, int atividade,
			int idFuncionalidadeIniciada) throws ControladorException;

	public Object[] obterLeituraAnteriorEAtual(Integer idImovel, Integer amReferencia) throws ControladorException;

	public void envioEmailContaParaCliente(int idFuncionalidadeIniciada, Integer referencia, Integer idRota) throws ControladorException;

	public Collection<FaturamentoImediatoAjuste> pesquisarFaturamentoImediatoAjuste(FaturamentoImediatoAjusteHelper helper, int qtd)
			throws ControladorException;

	public Integer contarFaturamentoImediatoAjuste(FaturamentoImediatoAjusteHelper helper) throws ControladorException;

	public void processarMovimentoContaPrefaturada(Rota rota, Collection<MovimentoContaPrefaturada> colContaPreFaturada, boolean efetuarRateio)
			throws ControladorException;

	public void gerarDadosDeclaracaoQuitacaoAnualDebitos(int idFuncionalidadeIniciada, Collection<Integer> anos, Rota rota, Short indicadorContaParcelada,
			Short indicadorCobrancaJudical, Date dataVerificacaoPagamentos) throws ControladorException;

	public Collection<Integer> pesquisarAnosParaGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos() throws ControladorException;

	public void gerarArquivoTextoDeclaracaoQuitacaoAnualDebitos(Integer idFuncionalidadeIniciada, Integer idGrupoFaturamento, Empresa empresa)
			throws ControladorException;

	public Collection<ExtratoQuitacaoItem> pesquisarExtratoQuitacaoItensParaGeracaoArquivoTexto(Integer idExtratoQuitacao) throws ControladorException;

	public Integer gerarCreditoARealizar(CreditoARealizar creditoARealizar, Imovel imovel, Usuario usuarioLogado) throws ControladorException;

	public void atualizarSituacaoAtualDebitoACobrar(Integer idDebitoACobrar) throws ControladorException;

	public Collection<DebitoTipoVigencia> pesquisarDebitoTipoVigenciaUltimaVigencia(Integer numeroPagina) throws ControladorException;

	public Integer pesquisarDebitoTipoVigenciaUltimaVigenciaTotal() throws ControladorException;

	public Collection<DebitoTipoVigencia> pesquisarDebitoTipoVigenciaUltimaVigenciaSelecionados(String[] selecionados) throws ControladorException;

	public DeclaracaoQuitacaoAnualDebitosHelper pesquisarDadosParaGeracaoDaDeclaracaodeQuitacaoDebitos(Integer idImovel, Integer ano,
			Date dataVerificacaoPagamentos, Short indicadorContaParcelada, Short indicadorCobrancaJudical) throws ControladorException;

	public void verificarExistenciaVigenciaDebito(String dataVigenciaInicial, String dataVigenciaFinal, Integer idDebitoTipo, Integer opcao)
			throws ControladorException;

	public Collection<Empresa> pesquisarEmpresasParaGeraracaoExtrato(Integer idGrupoFaturamento) throws ControladorException;

	public Boolean validarVigenciaValorCobrancaServico(ServicoCobrancaValor servicoCobrancaValor) throws ControladorException;

	public Collection<Object[]> pesquisarParmsDebitoAutomatico(Integer idConta) throws ControladorException;

	public Collection<Object[]> pesquisarParmsDebitoAutomaticoHistorico(Integer idConta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public List pesquisarParmsDebitoCobradoPorTipo(Integer idConta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public List pesquisarParmsDebitoCobradoHistoricoPorTipo(Integer idConta) throws ControladorException;

	public Object[] pesquisarDebitoCobradoDeParcelamento(Conta conta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoCobradoDeParcelamentoIS(Conta conta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoCobradoNaoParcelamento(Conta conta) throws ControladorException;

	public StringBuilder obterMensagemRateioConsumoFichaCompensacao(EmitirContaHelper emitirContaHelper, String consumoRateio, Object[] parmsMedicaoHistorico,
			Integer tipoMedicao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void gerarTaxaPercentualTarifaMinimaCortado(Collection colecaoFaturamentoAtividadeCronogramaRota, FaturamentoGrupo faturamentoGrupo, int atividade,
			int idFuncionalidadeIniciada) throws ControladorException;

	public void alterarInscricoesImoveis(Integer idFuncionalidadeIniciada, Integer idLocalidade) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void verificarFaturamentoImoveisCortados(Collection colecaoFaturamentoAtividadeCronogramaRota, FaturamentoGrupo faturamentoGrupo, int atividade,
			int idFuncionalidadeIniciada) throws ControladorException;

	public Conta pesquisarUltimaContaDoImovel(Integer idImovel) throws ControladorException;

	public ContaHistorico pesquisarContaHistoricoDigitada(String idImovel, String referenciaConta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void verificarQuantidadeAlteracoesVencimentoConta(Collection idsConta) throws ControladorException;

	public void verificarQuantidadeAlteracoesVencimentoConta(Integer idConta) throws ControladorException;

	public Collection<GerarRelatorioAnormalidadePorAmostragemHelper> pesquisarDadosRelatorioAnormalidadeConsumoPorAmostragem(Integer idGrupoFaturamento,
			Short codigoRota, Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal, Integer referencia, Integer idImovelPerfil, Integer numOcorConsecutivas,
			String indicadorOcorrenciasIguais, Integer mediaConsumoInicial, Integer mediaConsumoFinal, Collection<Integer> colecaoIdsAnormalidadeConsumo,
			Collection<Integer> colecaoIdsAnormalidadeLeitura, Collection<Integer> colecaoIdsAnormalidadeLeituraInformada, Integer tipoMedicao,
			Collection<Integer> colecaoIdsEmpresa, Integer numeroQuadraInicial, Integer numeroQuadraFinal, Integer idCategoria, Integer limite)
			throws ControladorException;

	public Integer pesquisarDiferencaQuantidadeMovimentoContaPrefaturadaArquivoTextoRoteiroEmpresa(Integer idRota, Integer anoMesFaturamento)
			throws ControladorException;

	public Integer retornaAnoMesFaturamentoGrupoDaRota(Integer idRota) throws ControladorException;

	public BufferedReader removerImoveisJaProcessadosBufferImpressaoSimultanea(Integer idRota, BufferedReader reader) throws ControladorException;

	public FaturamentoGrupo recuperaGrupoFaturamentoDoImovel(Integer idImovel) throws ControladorException;

	public boolean verificarExistenciaAutosInfracaoPorOS(Integer idOrdemServico) throws ControladorException;

	public AutosInfracao pesquisarAutosInfracaoPorOS(Integer idOrdemServico) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisaAutosInfracaoDebitoACobrar(Integer idAutoInfracao) throws ControladorException;

	public StringBuilder obterNossoNumeroFichaCompensacao(String idDocumentoTipo, String idDocumentoEmitido, Integer codigoConvenio) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarAnoImovelEmissao2ViaDeclaracaoAnualQuitacaoDebitos(String idImovel) throws ControladorException;

	public void religarImovelCortadoComConsumoReal(Integer anoMesReferenciaFaturamento, Integer idLocalidade, int idFuncionalidadeIniciada)
			throws ControladorException;

	public boolean liberaProximoArquivoImpressaoSimultaneaOnLine() throws ControladorException;

	public Integer prescreverDebitosImoveisPublicos(PrescreverDebitosImovelHelper helper) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void prescreverDebitosImoveisPublicosManual(Integer idFuncionalidadeIniciada, Map parametros) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterDadosPrescricaoDebitosAutomaticos() throws ControladorException;

	public void prescreverDebitosImoveisPublicosAutomatico(Integer idFuncionalidadeIniciada, Integer anoMesReferencia, Date dataPrescricao, Integer usuario,
			String idsEsferaPoder) throws ControladorException;

	public String[] obterMensagemConta(EmitirContaHelper emitirContaHelper, SistemaParametro sistemaParametro, int tipoConta,
			Collection<NacionalFeriado> colecaoNacionalFeriado) throws ControladorException;

	public void gerarArquivoImoveisNaoEnviados(String[] idsArquivos) throws ControladorException;

	public void gerarArquivoTextoParaFaturamento(Rota rota, Integer anoMesFaturamento, FaturamentoGrupo faturamentoGrupo, Date dataComando, Boolean regerar)
			throws ControladorException;

	public boolean reprocessarImovelImpressaoSimultanea(Integer anoMes, Integer idImovel, Short tipoMedicao, Integer leitura, Integer idAnormalidade,
			Short icImpresso) throws ControladorException;

	public Integer pesquisarDebitoCreditoSituacaoAtualDaConta(Integer idImovel, Integer anoMesReferencia) throws ControladorException;

	public Object[] pesquisarArquivoTextoRoteiroEmpresa(Integer idRota, Integer anoMesReferencia) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasPagasSemDebitoCreditoPago(Integer amreferencia, Integer idGrupo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasComValorFaixasErradas(Integer amreferencia) throws ControladorException;

	public void inserirDebitosContasComValorFaixasErradas(Integer amreferencia, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public boolean verificarSeExisteClienteConta(Integer idCliente, Collection colecaoContasIds) throws ControladorException;

	public Object[] consultarConsumoCadastrado(Integer idImovel) throws ControladorException;

	public Collection<Integer> consultarMatriculasAssociadas(Integer idConsumoTarifa, Integer idImovel) throws ControladorException;

	public Conta pesquisarContaAnoMesImovel(Integer idImovel, int anoMesReferencia) throws ControladorException;

	public Integer pesquisaQtdeContaEContaHistoricoRetificadaMotivo(Integer idMotivo, Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisaTabelaColunaContaMotivoRetificacaoColuna(Integer idMotivo) throws ControladorException;

	public void identificarGrandesConsumidores(Integer idLocalidade, int idFuncionalidadeIniciada) throws ControladorException;

	public String obterNomeCliente(Integer idConta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRelatorioDevolucaoPagamentosDuplicidade(FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper helper) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void inserirCreditoRealizado(Conta conta, Collection colecaoCreditoRealizado, Imovel imovel, Collection colecaoCategoria)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void inserirCreditoRealizadoCategoria(CreditoRealizado creditoRealizado, Collection colecaoCategoriaOuSubcategoria) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer pesquisarQtdeContaNaoPaga(Collection idContas) throws ControladorException;

	public Conta pesquisarContaTipoBoleto(Integer identificacaoCodigoBarras, BigDecimal valorPagamento) throws ControladorException;

	public ContaHistorico pesquisarContaHistoricoTipoBoleto(Integer identificacaoCodigoBarras, BigDecimal valorPagamento) throws ControladorException;

	public void alterarLeituristaMovimentoRoteiroEmpresa(Integer IdRota, Integer anoMes, Integer idLeituristaNovo) throws ControladorException;

	public void alterarLeituristaMovimentoRoteiroEmpresa(Collection<Integer> idsImovel, Integer anoMes, Integer idLeituristaNovo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterConsumoTarifaVigencia(ConsumoTarifa consumoTarifa, Integer anoMesReferencia, Date dataLeituraAnterior, Date dataLeituraAtual)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarGrupoFaturamentoGrupoNaoFaturados(Integer anoMesReferenciaFaturamento) throws ControladorException;

	public void gerarTxtImpressaoContasBraille(int idFuncionalidadeIniciada) throws ControladorException;

	public Collection<Object[]> pesquisarQuantidadeContasComandoAgrupandoPorImovel(MovimentarOrdemServicoEmitirOSHelper movimentarOrdemServicoEmitirOSHelper)
			throws ControladorException;

	public Object[] pesquisarQuantidadeContasComando(MovimentarOrdemServicoEmitirOSHelper movimentarOrdemServicoEmitirOSHelper) throws ControladorException;

	public Collection<Integer[]> pesquisarOSComandoSelecionado(MovimentarOrdemServicoEmitirOSHelper movimentarOrdemServicoEmitirOSHelper)
			throws ControladorException;

	public Integer pesquisarAnoMesReferenciaMenorAnoMesReferenciaFaturamentoGrupo(int anoMesReferenciaInformado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer informarConsumoMinimoParametro(Collection colecaoConsumoMinimoParametro, Collection colecaoConsumoMinimoParametroBase, Usuario usuarioLogado)
			throws ControladorException;

	public boolean pesquisarContaDoImovelDiferentePreFaturada(Integer idImovel, Integer anoMesReferencia) throws ControladorException;

	public Integer pesquisarFaturamentoGrupoImovel(Integer idImovel) throws ControladorException;

	public void atualizarIndicadorContaHistorico(Integer idFaturaItem) throws ControladorException;

	public void deletarResumoFaturamento(Integer anoMes) throws ControladorException;

	public ArrayList<ConsultarEstruturaTarifariaPortalHelper> pesquisarEstruturaTarifaria(Integer idCategoria) throws ControladorException;

	public ConsultarEstruturaTarifariaPortalHelper pesquisarEstruturaTarifariaChafarizPublico() throws ControladorException;

	public ArrayList<ConsultarEstruturaTarifariaPortalHelper> pesquisarEstruturaTarifariaAguaBruta(Integer idCategoria) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection<Conta> obterColecaoSemContasEmContratoParcelamento(Collection colecaoContas) throws ControladorException;

	public Collection<Integer> obterColecaoSemContasEmContratoParcelamentoIDs(Collection<Integer> colecaoIdsContas) throws ControladorException;

	public void colocarRevisaoDebitoACobrar(Collection<DebitoACobrar> colecaoDebitosACobrar, ContaMotivoRevisao contaMotivoRevisao, Usuario usuarioLogado)
			throws ControladorException;

	public void retirarRevisaoDebitoACobrar(Collection<Conta> colecaoDebitosACobrar, Usuario usuarioLogado) throws ControladorException;

	public DebitoACobrar obterDebitoACobrar(Integer idDebitoACobrar) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterColecaoGrupoFaturamento() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioAutoInfracaoPendentes(Integer grupo, Integer funcionario) throws ControladorException;

	public Conta pesquisarContaOuContaHistoricoDigitada(String idImovel, String referenciaConta) throws ControladorException;

	public void suspenderLeituraParaImovelComHidrometroRetirado(Integer idFuncionalidadeIniciada, Integer referenciaFaturamento, Integer grupofaturamento,
			Integer idRota) throws ControladorException;

	public void suspenderLeituraParaImovelComConsumoRealNaoSuperiorA10(Integer idFuncionalidadeIniciada, Integer referenciaFaturamento,
			Integer grupofaturamento, Integer idRota) throws ControladorException;

	public void atualizarConsumoMovimentoCelular(Conta conta, Integer consumoAguaMovimentoCelular, Integer consumoAguaGSAN,
			Integer consumoEsgotoMovimentoCelular, Integer consumoEsgotoGSAN) throws ControladorException;

	public BigDecimal obterPercentualColetaEsgotoImovel(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterImoveisComContaPF(Integer anoMesReferencia, Rota rota) throws ControladorException;

	public Date pesquisarFaturamentoAtividadeCronogramaDataRealizada(Integer faturamentoGrupoId, Integer faturamentoAtividadeId, Integer anoMesReferencia)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection obterImoveisComConta(Integer anoMesReferencia, Rota rota) throws ControladorException;

	public Conta obterContaImovel(Integer idImovel, Integer anoMesReferencia) throws ControladorException;

	public List<Integer> obterImoveisMovimentoContaPF(Integer idRota, Integer anoMesFaturamento) throws ControladorException;

	public List<Integer> obterImoveisFaltandoTransmitir(Integer idRota, Integer anoMesFaturamento) throws ControladorException;

	public Collection<RelatorioContasRetidasHelper> pesquisarDadosRelatorioContasRetidas(int anoMesReferencia, Integer idFaturamentoGrupo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioMedicaoFaturamento(int anoMesReferencia, Integer idFaturamentoGrupo, Integer idEmpresa) throws ControladorException;

	public void atualizarConta(Conta conta) throws ControladorException;

	public BigDecimal verificarPercentualEsgotoAlternativo(Imovel imovel, Integer consumoFaturadoEsgoto) throws ControladorException;

	public BigDecimal[] calcularValorRateioImovel(Imovel imovel, FaturamentoGrupo faturamentoGrupo) throws ControladorException, ErroRepositorioException;

	public StringBuilder obterNomeArquivoRetorno(ArquivoTextoRetornoIS arquivoRetorno);

	public MovimentoContaPrefaturada obterMovimentoImovel(Integer idImovel, Integer anoMesReferencia) throws ControladorException;

	public ExtratoQuitacao obterExtratoQuitacaoImovel(Integer idImovel, Integer anoReferencia) throws ControladorException;

	public String obterMsgQuitacaoDebitos(Imovel imovel, Integer anoMesReferencia) throws ControladorException;

	public long obterDiferencaDiasCronogramas(Integer anoMesAtual, Rota rota, Integer idFaturamentoAtividade) throws ControladorException;

	public Integer inserirGuiaPagamentoCodigoBarrasPorCliente(GuiaPagamento guiaPagamento, Integer idDebitoTipo, Integer idLocalidade) throws ControladorException;

	public DebitoACobrar gerarDebitoACobrar(Integer anoMesReferenciaArrecadacao, Integer anoMesReferenciaFaturamento, Imovel imovel, Short numeroPrestacaoDebito, Short numeroPrestacaoCobradas,
			Integer anoMesReferenciaDebito, BigDecimal valorDebito, DebitoTipo debitoTipo, Usuario usuario) throws ControladorException;

	public Map<Integer, Conta> incluirContasParaRefaturarPagamentos(Collection<Pagamento> pagamentos, Usuario usuarioLogado) throws ControladorException, ErroRepositorioException;

	public Collection<Integer> getListaIdContas(Collection<Pagamento> pagamentos);

	public Collection<ContaHistorico> pesquisarContaOuContaHistorico(Collection<Pagamento> pagamentos) throws ControladorException;

	public void atualizarVecimentoFaturaClienteResponsavel(Date dataVencimento, String anoMesReferencia) throws ControladorException;

	public Integer countFaturasClienteResponsaveis(String anoMesReferencia) throws ControladorException;

	public Conta incluirDebitoContaRetificadaPagamentosDiferenca2Reais(Integer idConta, DebitoACobrar debito) throws Exception;

	public Conta incluirCreditoContaRetificadaPagamentosDiferenca2Reais(Integer idConta, CreditoARealizar credito) throws Exception;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteContaECliente(Integer idConta, String cnpjEmpresa) throws ControladorException;

	public void faturarImovelSeletivo(ImovelFaturamentoSeletivo imovelFaturamentoSeletivo) throws ControladorException;

	public Collection<RelatorioReceitasAFaturarHelper> pesquisarDadosRelatorioReceitasAFaturarAnalitico(Integer idGrupo, Integer anoMes) throws ControladorException;

	public Collection<RelatorioReceitasAFaturarPorCategoriaHelper> pesquisarDadosRelatorioReceitasAFaturarSintetico(Integer anoMes, Short indicadorCategoria) throws ControladorException;

	public int pesquisarMaiorAnoMesReferenciaCronogramaGrupoFaturamentoMensal(Integer idGrupo) throws ControladorException;

	public boolean verificarAnoMesReferenciaCronogramaGrupoFaturamentoMensal(Integer idGrupo, Integer referencia) throws ControladorException;

	public Collection<RelatorioReceitasAFaturarHelper> gerarDadosReceitasAFaturarResumo(Integer anoMes, Integer idGrupo, Integer idFuncionalidadeIniciada) 
			throws ControladorException, ErroRepositorioException;

	public Collection<ContaImpressaoTermicaQtde> pesquisarQtdeContaImpressaoTermica(Integer idGrupoFaturamento, Integer referencia) throws ControladorException;

	public Fatura pesquisarFaturaDeConta(Integer idConta) throws ControladorException;

	public List<RelatorioAgenciaReguladoraDTO> pesquisarContasParaRelatorioAgenciaReguladora(Integer anoMes, Integer idAgencia);

	public void verificarValoresLeituraAnteriorEAtual(Integer leituraAnterior, Integer leituraAtual, String retorno, Integer contaID, Integer idImovel, Integer consumoAguaCalculado,
			Integer consumoAguaMedido) throws ControladorException;

	public void verificarValoresLeituraAnteriorEAtual(Integer leituraAnterior, Integer leituraAtual, Integer consumoAgua) throws ControladorException;

	public BigDecimal calcularValorTotalDebitoConta(Collection<DebitoCobrado> colecaoDebitoCobrado) throws ControladorException;

	public BigDecimal calcularValorTotalCreditoConta(Collection<CreditoRealizado> colecaoCreditoRealizado) throws ControladorException;

	public Date retornaDataValidadeConta(Date dataVencimento) throws ControladorException;

	public Integer verificarGeracaoBoleto(SistemaParametro sistemaParametro, Conta conta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void inserirContaCategoria(Collection<CalcularValoresAguaEsgotoHelper> calcularValoresConta, Collection colecaoCategoriaOuSubcategoria, Conta conta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void inserirDebitoCobrado(Conta conta, Collection colecaoDebitoCobrado, Imovel imovel, Collection colecaoCategoria) throws ControladorException;

	public Integer obterReferenciaContabilConta(SistemaParametro sistemaParametro, Integer anoMesReferenciaConta) throws ControladorException;

	public BigDecimal[] obterValorCreditoReparcelamentoDeCurtoELongoPrazo(short numeroPrestacoes, BigDecimal valorCredito)
			throws ControladorException;
	
	public BigDecimal[] obterValorCurtoELongoPrazoParaParcelamento(short numeroPrestacoes, short numeroPrestacoesCobradas, BigDecimal valorTotal, BigDecimal valorRestante) throws ControladorException;
	
	public BigDecimal[] obterValorConsumoASerRateado(Imovel imovelCondominio, FaturamentoGrupo faturamentoGrupo) throws ControladorException;
	
	@SuppressWarnings("rawtypes")
	public Collection<Object[]> pesquisaridDebitoTipoDoDebitoCobradoDeParcelamento(Integer idConta, Collection idsFinanciamentoTipo) throws ControladorException;
	
	@SuppressWarnings("rawtypes")
	public void inserirDebitoCobradoCategoria(DebitoCobrado debitoCobrado, Collection colecaoCategoriaOuSubcategoria) throws ControladorException;

	public Short obterDiaVencimentoConta(Integer idImovel) throws ControladorException;
	
	public Object[] pesquisarParmsContaMensagem(EmitirContaHelper helper, Integer idGrupo, Integer idGerencia, Integer idLocalidade, Integer idSetor) throws ControladorException;
	
	public boolean isAlgumaContaEmProcessoJudicial (Integer idImovel, Date data);
	
	public List<IClienteConta> pesquisarClienteContaDeContasEmitidasAPartirDeUmaData(Integer idImovel, Date dataEmissao) throws ControladorException;
	
	public IConta obterContaOuContaHistorico(Integer idImovel, Integer referencia);
	
	public boolean isImovelEmsituacaoEspecialFaturamento(Integer idImovel, Integer anoMesReferencia) throws ControladorException;
	
	public ComunicadoEmitirConta pesquisarComunicadoNaoEmitido(Integer idImovel, Integer tipo) throws ControladorException;
	
	public ComunicadoEmitirConta pesquisarUltimoComunicadoGerado(Integer idImovel, Integer tipoComunicado) throws ControladorException;
	
	public Collection<ComunicadoEmitirConta> pesquisarComunicadosNaoEmitidos(Integer tipoComunicado) throws ControladorException;
	
	public ComunicadoEmitirConta pesquisarComunicado(Integer idImovel, Integer referencia, Integer tipoComunicado) throws ControladorException;
	
	public String getFaturamentoParametro(String parametro) throws ControladorException;
	
	public Object[] obterDadosAgenciaReguladora() throws ControladorException;
	
	public Localidade pesquisarLocalidadeConta(Integer parametro) throws ControladorException;

	public Object[] pesquisarContatosAgenciaReguladora(EmitirContaHelper emitirContaHelper) throws ErroRepositorioException, ControladorException;
	
	public void envioNotificacaoVencimentoFatura (Integer idFuncionalidadeIniciada, Collection<Integer> colecaoIdsLocalidades) throws ControladorException;
	
	public void gerarCreditosBolsaAgua(Rota rota, int idFuncionalidadeIniciada, FaturamentoGrupo grupo) throws ControladorException;
	
	public void atualizarValorCreditoBolsaAgua(Integer anoMes, Imovel imovel, BigDecimal valorBolsaAgua, Conta conta) throws ControladorException;
	
	public BigDecimal retornaValorBolsaAgua (Integer anoMesReferencia, Imovel imovel) throws ControladorException;
	
	public DeterminarValoresFaturamentoAguaEsgotoHelper obterValoresCreditosBolsaAgua(Imovel imovel, FaturamentoGrupo grupo );
	
	public void registrarFichaCompensacaoGrupo(Integer idGrupoFaturamento, Integer anoMesReferencia, int idFuncionalidadeIniciada) throws ControladorException;

	public Collection pesquisarIdContasGrupoFaturamentoRegistrarBoletos(Integer anoMesFaturamento, Integer idGrupoFaturamento) throws ControladorException;
	
	public void registrarEntradaParcelamento(Parcelamento parcelamento, boolean primeiraVia) throws ControladorException;
	
	public String consultarCpfCnpjCliente(Integer idImovel) throws ControladorException;
	
	public String pesquisarClienteCpfCnpj (Integer idCliente) throws ControladorException;

}