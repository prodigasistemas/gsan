
package gcom.faturamento;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ordemservico.ServicoCobrancaValor;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.IClienteConta;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.ImovelCobrarDoacaoHelper;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.ComandoEmpresaCobrancaContaHelper;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.faturamento.bean.ApagarDadosFaturamentoHelper;
import gcom.faturamento.bean.EmitirContaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaAguaEconomiaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaAguaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaEsgotoEconomiaHelper;
import gcom.faturamento.bean.FiltrarEmitirHistogramaEsgotoHelper;
import gcom.faturamento.bean.PrescreverDebitosImovelHelper;
import gcom.faturamento.bean.SituacaoEspecialFaturamentoHelper;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.faturamento.consumotarifa.ConsumoTarifaVigencia;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaHistorico;
import gcom.faturamento.conta.ContaImpostosDeduzidos;
import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FaturaItem;
import gcom.faturamento.conta.IContaCategoria;
import gcom.faturamento.conta.IContaImpostosDeduzidos;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoRealizadoHistorico;
import gcom.faturamento.credito.ICreditoRealizado;
import gcom.faturamento.credito.ICreditoRealizadoCategoria;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.DebitoTipoVigencia;
import gcom.faturamento.debito.IDebitoCobrado;
import gcom.faturamento.debito.IDebitoCobradoCategoria;
import gcom.financeiro.ResumoFaturamento;
import gcom.gui.cobranca.cobrancaporresultado.MovimentarOrdemServicoEmitirOSHelper;
import gcom.micromedicao.Rota;
import gcom.micromedicao.consumo.ConsumoHistorico;
import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.relatorio.faturamento.FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper;
import gcom.relatorio.faturamento.FiltrarRelatorioJurosMultasDebitosCanceladosHelper;
import gcom.relatorio.faturamento.conta.RelatorioContasCanceladasRetificadasHelper;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IRepositorioFaturamento {

	public boolean verificarExistenciaIdGrupoFaturamento(Integer id) throws ErroRepositorioException;

	public Integer pesquisarExistenciaConta(Imovel imovel, int anoMesReferencia) throws ErroRepositorioException;

	public Object[] obterDataPrevistaRealizadaFaturamentoAtividadeCronograma(
			FaturamentoGrupo faturamentoGrupo, int anoMesReferencia,
			FaturamentoAtividade faturamentoAtividade)
			throws ErroRepositorioException;

	public void removerTodasRotasPorCronogramaFaturamento(Integer idFaturamentoAtividadeCronograma) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection buscarAtividadeComandadaNaoRealizada(Integer numeroPagina) throws ErroRepositorioException;

	public Integer buscarAtividadeComandadaNaoRealizadaCount() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection buscarFaturamentoAtividadeCronograma(String ids) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection buscarDebitosCobradosConta(Conta conta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection buscarCreditosRealizadosConta(Conta conta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarConsumoTarifaVigenciaEntreDataLeituraAnterioreDataLeituraAtual(
			ConsumoTarifa consumoTarifa, Date dataLeituraAnterior,
			Date dataLeituraAtual) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarConsumoTarifaVigenciaMenorDataLeituraAnterior(ConsumoTarifa consumoTarifa, Date dataLeituraAnterior) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarConsumoTarifaCategoria(ConsumoTarifaVigencia consumoTarifaVigencia, Categoria categoria) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarConsumoTarifaFaixa(ConsumoTarifaCategoria consumoTarifaCategoria) throws ErroRepositorioException;

	public ConsumoTarifaFaixa pesquisarConsumoTarifaFaixa(ConsumoTarifaCategoria consumoTarifaCategoria, Integer consumo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarConsumoTarifaVigenciaEmVigor(ConsumoTarifa consumoTarifa) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarConsumoTarifaVigenciaEmVigor(ConsumoTarifa consumoTarifa, int idConsumoTarifaVigencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteImovelDataRelacaoFimNull(Imovel imovel) throws ErroRepositorioException;

	public Integer pesquisarFaturamentoGrupoCronogramaMensal(Integer idFaturamentoGrupo, Integer anoMes) throws ErroRepositorioException;

	public Integer pesquisarFaturamentoAtividadeCronograma(Integer idFaturamentoGrupoCronogramaMensal, Integer idFaturamentoAtividade) throws ErroRepositorioException;

	public void atualizarFaturamentoAtividadeCronograma(Integer idFaturamentoAtividadeCronograma, Date dataRealizada) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void inserirFaturamentoSituacaoHistorico(Collection collectionFaturamentoSituacaoHistorico) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoFaturamentoRelatorioPorEstado(int anoMesReferencia, String opcaoRelatorio, boolean estadoMunicipio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoFaturamentoRelatorioPorEstadoPorGerenciaRegional(int anoMesReferencia, String opcaoRelatorio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoFaturamentoRelatorioPorEstadoPorLocalidade(int anoMesReferencia, String opcaoRelatorio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoFaturamentoRelatorioPorEstadoPorMunicipio(int anoMesReferencia, String opcaoRelatorio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoFaturamentoRelatorioPorGerenciaRegional(int anoMesReferencia, Integer gerenciaRegional, String opcaoRelatorio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoFaturamentoRelatorioPorGerenciaRegionalPorLocalidade(int anoMesReferencia, Integer gerenciaRegional, String opcaoRelatorio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoFaturamentoRelatorioPorLocalidade(int anoMesReferencia, Integer localidade, String opcaoRelatorio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoFaturamentoRelatorioPorMunicipio(int anoMesReferencia, Integer municipio, String opcaoRelatorio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasImovelManter(Imovel imovel,
			Integer situacaoNormal, Integer situacaoIncluida,
			Integer situacaoRetificada) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasImovel(Integer imovel, Integer situacaoNormal,
			Integer situacaoIncluida, Integer situacaoRetificada,
			Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterGuiasPagamentoImovel(Integer imovel,
			Integer situacaoNormal, Integer situacaoIncluida,
			Integer situacaoRetificada, Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	public void cancelarContaReferenciaContabilMenorSistemaParametro(Conta conta, Integer debitoCreditoSituacaoAnterior) throws ErroRepositorioException;

	public void cancelarContaReferenciaContabilMaiorIgualSistemaParametro(Conta conta) throws ErroRepositorioException;

	public void colocarContaRevisao(Conta conta) throws ErroRepositorioException;

	public void retirarContaRevisao(Conta conta) throws ErroRepositorioException;

	public void alterarVencimentoConta(Conta conta) throws ErroRepositorioException;

	public Collection<Integer> pesquisarQuadras(Integer rotaId) throws ErroRepositorioException;

	public void deletarResumoFaturamentoSimulacao(Integer idFaturamentoGrupo,
			Integer anoMesReferencia, Integer idRota)
			throws ErroRepositorioException;

	public Object pesquisarFaturamentoAtividadeCronogramaDataRealizacao(Integer faturamentoGrupoId, Integer faturamentoAtividadeId)	throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitosACobrar(Integer imovelId, Integer debitoCreditoSituacaoAtualId) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCreditoARealizar(Integer imovelId, Integer debitoCreditoSituacaoAtualId, int anoMesFaturamentoGrupo, SistemaParametro sistemaParametro) throws ErroRepositorioException;

	public void inserirResumoFaturamentoAnoMesReferencia(ResumoFaturamento resumoFaturamento) throws ErroRepositorioException;

	public ResumoFaturamento acumularValorAguaSituacaoContaNormal(int anoMesReferencia, int localidade, int categoria) throws ErroRepositorioException;

	public ResumoFaturamento acumularValorEsgotoSituacaoContaNormal(int anoMesReferencia, int localidade, int categoria) throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoNormalFinanciamentoServico(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorGuiaPagamentoSituacaoNormalFinanciamentoServico(
			int anoMesReferencia, int localidade, int categoria)
			throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoCanceladoFinanciamentoServico(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public BigDecimal acumularValorAguaSituacaoCancelada(int anoMesReferencia,
			int idLocalidade, int idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorEsgotoSituacaoCancelada(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoTipoFinanciamentoServicoSituacaoCancelada(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoTipoFinanciamentoParcelamentoAguaSituacaoCancelada(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoTipoFinanciamentoParcelamentoEsgotoSituacaoCancelada(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoTipoFinanciamentoParcelamentoServicoSituacaoCancelada(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoTipoFinanciamentoJurosParcelamentoSituacaoCancelada(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public BigDecimal acumularValorAguaSituacaoIncluida(int anoMesReferencia,
			int idLocalidade, int idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorEsgotoSituacaoIncluida(int anoMesReferencia,
			int idLocalidade, int idCategoria) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorCategoriaDebitoTipoFinanciamentoServicoSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorCategoriaDebitoTipoFinanciamentoServicoSituacaoNormalNumeroPrestacoesCobradasMaiorQue11(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoNormalGrupoParcelamentoDocumentosEmitidos(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoNormalGrupoParcelamentoFinanciamentosACobrarCurtoPrazo(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoNormalGrupoParcelamentoFinanciamentosACobrarLongoPrazo(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoNormalGrupoParcelamentoParcelamentosACobrarCurtoPrazo(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoNormalGrupoParcelamentoParcelamentosACobrarLongoPrazo(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoNormalGrupoParcelamentoJurosCobrados(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoCanceladoPorParcelamentoGrupoParcelamentoJurosCobrados(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorDebitoSituacaoNormalTipoFinanciamentoArrastoAguaArrastoEsgotoArrastoServico(
			int anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoCanceladoTipoFinanciamentoParcelamentoAgua(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoCanceladoTipoFinanciamentoParcelamentoEsgoto(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoCanceladoTipoFinanciamentoParcelamentoServico(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitoACobrarSituacaoCanceladoTipoFinanciamentoJurosParcelamento(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoAguaSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoEsgotoSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoServicosSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoJurosParcelamentoSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoJurosParcelamentoSituacaoNormalDiferencaPrestacoesMaiorQue11(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoArrastoAguaSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoArrastoEsgotoSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoArrastoServicoSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaOrigemCreditoContasPagasEmDuplicidadeEmExcessoSituacaoContaNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaOrigemCreditoDevolucaoTarifaAguaSituacaoContaNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaOrigemCreditoDevolucaoTarifaEsgotoSituacaoContaNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorCategoriaCreditoRealizadoCategoriaOrigemCreditoServicosIndiretosPagosIndevidamenteSituacaoContaNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaOrigemCreditoDevolucaoJurosParcelamentoSituacaoContaNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValor_IR_SituacaoContaNormalCategoriaPublica(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValor_COFINS_SituacaoContaNormalCategoriaPublica(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValor_CSLL_SituacaoContaNormalCategoriaPublica(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValor_PIS_PASEP_SituacaoContaNormalCategoriaPublica(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria)
			throws ErroRepositorioException;

	public BigDecimal pesquisarSomaValorAguaSituacaoCanceladaPorRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public BigDecimal pesquisarSomaValorAguaSituacaoRetificada(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public BigDecimal pesquisarSomaValorDebitoCobradoParcelamentoAguaSituacaoCanceladaPorRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public BigDecimal pesquisarSomaValorDebitoCobradoParcelamentoAguaSituacaoRetificada(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public BigDecimal pesquisarSomaValorEsgotoSituacaoCanceladaPorRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public BigDecimal pesquisarSomaValorEsgotoSituacaoRetificada(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public BigDecimal acumularValorGuiaPagamentoSituacaoCancelada(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer tipoFinanciamento, Integer itemContabil)
			throws ErroRepositorioException;

	public void atualizarImoveisSituacaoEspecialFaturamentoFinalizada(
			int anoMesFaturamento, Integer idSetorComercial)
			throws ErroRepositorioException;

	public Collection<Conta> pesquisarContasCanceladasPorMesAnoReferenciaContabil(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;

	public Collection<DebitoCobrado> pesquisarDebitosCobradosCanceladosPorMesAnoReferenciaContabil(
			int anoMesReferenciaContabil, Integer idConta)
			throws ErroRepositorioException;

	public Collection<CreditoRealizado> pesquisarCreditosRealizadosCanceladosPorMesAnoReferenciaContabil(
			int anoMesReferenciaContabil, Integer idConta)
			throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitosACobrarCanceladosPorMesAnoReferenciaContabil(int anoMesReferenciaContabil, Integer idSetorComercial)
			throws ErroRepositorioException;

	public Collection<CreditoARealizar> pesquisarCreditosARealizarCanceladosPorMesAnoReferenciaContabil(
			int anoMesReferenciaContabil, Integer idSetorComercial)
			throws ErroRepositorioException;

	public void atualizarAnoMesfaturamento(int anoMesFaturamentoAtual,
			int anoMesFaturamentoNovo) throws ErroRepositorioException;

	public Collection<ResumoFaturamento> pesquisarResumoFaturamentoPorAnoMes(
			int anoMesFaturameto, Integer idLocalidade)
			throws ErroRepositorioException;

	public void retificarContaAtualizarSituacao(Conta conta, Integer situacaoAnterior) throws ErroRepositorioException;

	public void retificarContaAtualizarValores(Conta conta) throws ErroRepositorioException;

	public void removerContaCategoria(Conta conta) throws ErroRepositorioException;

	public void removerDebitoCobrado(Conta conta) throws ErroRepositorioException;

	public void removerCreditoRealizado(Conta conta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitosACobrarCategoria(Integer debitoACobrarID) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCreditoRealizarCategoria(Integer creditoARealizarID) throws ErroRepositorioException;

	public Integer obterDebitoAutomatico(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelFaturarGrupo(Integer idImovel,
			int numeroIndice, int quantidadeRegistros, boolean preFaturar,
			boolean resumo) throws ErroRepositorioException;

	public Cliente pesquisarClienteImovelGrupoFaturamento(Integer idImovel, Short relacaoTipo) throws ErroRepositorioException;

	public DebitoAutomaticoMovimento obterDebitoAutomaticoMovimento(
			Integer idImovel, Integer anoMesReferencia)
			throws ErroRepositorioException;

	public Integer pesquisarExistenciaContaComSituacaoAtual(Imovel imovel, int anoMesReferencia) throws ErroRepositorioException;

	public void atualizarSituacaoAtualDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarFaturaItem(Integer idCliente,
			Integer anoMesReferencia, Integer numeroSequencial,
			BigDecimal valordebito) throws ErroRepositorioException;

	public void atualizarAnoMesFaturamentoSituacaoHistorico(
			SituacaoEspecialFaturamentoHelper situacaoEspecialFaturamentoHelper,
			Collection<Integer> colecaoIdsImoveisRetirar,
			Integer idFaturamentoSituacaoComando)
			throws ErroRepositorioException;

	public Collection<FaturamentoAtividadeCronograma> pesquisarFaturamentoAtividadeCronogramaComandadaNaoRealizada(Integer numeroPagina) throws ErroRepositorioException;

	public Integer pesquisarFaturamentoAtividadeCronogramaComandadaNaoRealizadaCount() throws ErroRepositorioException;

	public Collection<Imovel> obterImoveisPorRotasComContaEntregaEmOutroEndereco(Integer idRota) throws ErroRepositorioException;

	public DebitoTipo getDebitoTipo(Integer id) throws ErroRepositorioException;

	public BigDecimal obterValorTarifa(Integer consumoTarifaId) throws ErroRepositorioException;

	public Object[] getDebitoTipoHql(Integer id) throws ErroRepositorioException;

	public Object[] pesquisarDebitoACobrar(Integer idImovel,
			Integer idDebitoTipo, Integer anoMesReferenciaDebito)
			throws ErroRepositorioException;

	public void deletarDebitoACobrarCategoria(Integer idDebitoACobrar) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection<DebitoACobrar> insereOuAtualizaDebitoACobrar(Collection colecaoDebitosACobrar) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void inserirDebitoACobrarCategoria(Collection colecaoDebitosACobrarCategoria) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisDasQuadrasPorRota(Integer idRota) throws ErroRepositorioException;

	public Date obterPagamentoContasMenorData(Integer conta, Integer idImovel, Integer anoMesReferenciaConta) throws ErroRepositorioException;

	public Object gerarDebitoACobrar(DebitoACobrar debitoACobrar) throws ErroRepositorioException;

	public Object[] obterDebitoTipo(Integer debitoTipo) throws ErroRepositorioException;

	public void atualizarIndicadorMultaDeConta(Collection<Integer> colecaoIdsContas) throws ErroRepositorioException;

	public void atualizarIndicadorMultaDeGuiaPagamento(
			Collection<Integer> colecaoIdsGuiasPagamento)
			throws ErroRepositorioException;

	public Collection<Integer> pesquisarClienteImovelPorClienteResponsavel(Integer idCliente) throws ErroRepositorioException;

	public Collection<Integer> pesquisarClientesResponsaveis(
			SistemaParametro sistemaParametro, Integer numeroIndice,
			Integer quantidadeRegistros) throws ErroRepositorioException;

	public Collection<Integer> pesquisarClientesResponsaveisFaturamentoAntecipado(
			SistemaParametro sistemaParametro, Integer numeroIndice,
			Integer quantidadeRegistros) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection<Conta> pesquisarContaImovelResponsabilidadeCliente(
			Collection idsConcatenadosImoveis, Integer anoMesReferenciaConta)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Object pesquisarResumoContasClienteResponsavel(Collection idsConcatenadosImoveis, Integer anoMesReferenciaConta)
			throws ErroRepositorioException;

	public Collection<FaturaItem> pesquisarItemsFatura(Integer idFatura) throws ErroRepositorioException;

	public void restabelecerSituacaoAnteriorContaCancelada(String idConta) throws ErroRepositorioException;

	public void alterarSituacaoConta(String idConta, Integer situacao) throws ErroRepositorioException;

	public void alterarSituacaoAnteriorAtualConta(String idConta, Integer situacaoAnterior, Integer situacaoAtual) throws ErroRepositorioException;

	public Object pesquisarFaturamentoAtividadeCronogramaDataRealizacao(
			Integer faturamentoGrupoId, Integer faturamentoAtividadeId,
			Integer anoMesReferencia) throws ErroRepositorioException;

	public void atualizarDebitoAcobrar(DebitoACobrar debitoACobrar) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void atualizarDebitoACobrar(List colecaoDebitosACobrar) throws ErroRepositorioException;

	public void atualizarCreditoARealizar(CreditoARealizar creditoARealizar) throws ErroRepositorioException;

	public LigacaoEsgoto obterLigacaoEsgotoImovel(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasEmitir(Collection<Integer> idTipoConta,
			Integer idEmpresa, Integer numeroPaginas, Integer anoMesReferencia,
			Integer idFaturamentoGrupo,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasDebitoAutomatico(Collection idsContas) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasClienteResponsavel(
			Collection<Integer> idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Integer idFaturamentoGrupo,
			Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasNormais(Collection idsContas) throws ErroRepositorioException;

	public String pesquisarNomeClienteUsuarioConta(Integer idConta) throws ErroRepositorioException;

	public Integer pesquisarIdClienteResponsavelConta(Integer idConta) throws ErroRepositorioException;

	public Short obterQuantidadeEconomiasConta(Integer idConta) throws ErroRepositorioException;

	public Collection<IContaCategoria> pesquisarContaCategoria(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaCategoriaFaixas(Integer idConta, Integer idCategoria) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarParmsDebitoAutomatico(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public List pesquisarParmsDebitoCobradoPorTipo(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public List pesquisarParmsCreditoRealizadoPorTipo(Integer idConta) throws ErroRepositorioException;

	public Object[] pesquisarParmsContaMensagem(
			EmitirContaHelper emitirContaHelper, Integer idFaturamentoGrupo,
			Integer idGerenciaRegional, Integer idLocalidade,
			Integer idSetorComercial) throws ErroRepositorioException;

	public Object[] pesquisarParmsQualidadeAgua(EmitirContaHelper emitirContaHelper) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarParmsContaImpostosDeduzidos(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarIdsTodasConta() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection gerarRelacaoAcompanhamentoFaturamento(
			String idImovelCondominio, String idImovelPrincipal,
			String idNomeConta, String idSituacaoLigacaoAgua,
			String consumoMinimoInicialAgua, String consumoMinimoFinalAgua,
			String idSituacaoLigacaoEsgoto, String consumoMinimoInicialEsgoto,
			String consumoMinimoFinalEsgoto,
			String intervaloValorPercentualEsgotoInicial,
			String intervaloValorPercentualEsgotoFinal,

			String intervaloMediaMinimaImovelInicial,
			String intervaloMediaMinimaImovelFinal,
			String intervaloMediaMinimaHidrometroInicial,
			String intervaloMediaMinimaHidrometroFinal,

			String idImovelPerfil, String idPocoTipo,
			String idFaturamentoSituacaoTipo, String idCobrancaSituacaoTipo,
			String idSituacaoEspecialCobranca, String idEloAnormalidade,
			String areaConstruidaInicial, String areaConstruidaFinal,
			String idCadastroOcorrencia, String idConsumoTarifa,
			String idGerenciaRegional, String idLocalidadeInicial,
			String idLocalidadeFinal, String setorComercialInicial,
			String setorComercialFinal, String quadraInicial,
			String quadraFinal, String loteOrigem, String loteDestno,
			String cep, String logradouro, String bairro, String municipio,
			String idTipoMedicao, String indicadorMedicao,
			String idSubCategoria, String idCategoria,
			String quantidadeEconomiasInicial, String quantidadeEconomiasFinal,
			String diaVencimento, String idCliente, String idClienteTipo,
			String idClienteRelacaoTipo, String numeroPontosInicial,
			String numeroPontosFinal, String numeroMoradoresInicial,
			String numeroMoradoresFinal, String idAreaConstruidaFaixa

	) throws ErroRepositorioException;

	public Collection<Conta> pesquisarContasDoImovelPorMesAnoReferencia(int anoMesReferencia, String idImovel) throws ErroRepositorioException;

	public Collection<ConsumoHistorico> pesquisarConsumoMedioLigacaoAgua(String idImovel, int anoMesReferencia) throws ErroRepositorioException;

	public Collection<ConsumoHistorico> pesquisarConsumoMedioLigacaoEsgoto(String idImovel, int anoMesReferencia) throws ErroRepositorioException;

	public Collection<ConsumoHistorico> pesquisarConsumoMesLigacaoAgua(String idImovel, int anoMesReferencia) throws ErroRepositorioException;

	public Collection<ConsumoHistorico> pesquisarConsumoMesLigacaoEsgoto(String idImovel, int anoMesReferencia) throws ErroRepositorioException;

	public Collection<MedicaoHistorico> pesquisarLeituraFaturadaLigacaoAgua(String idImovel, int anoMesReferencia) throws ErroRepositorioException;

	public Collection<MedicaoHistorico> pesquisarLeituraFaturadaLigacaoEsgoto(String idImovel, int anoMesReferencia) throws ErroRepositorioException;

	public String pesquisarAnormalidadeLeitura(Integer idAnormalidadeLeitura) throws ErroRepositorioException;

	public Collection<HidrometroInstalacaoHistorico> pesquisarDataHidrometroLigacaoAgua(String idImovel) throws ErroRepositorioException;

	public Collection<HidrometroInstalacaoHistorico> pesquisarDataHidrometroLigacaoEsgoto(String idImovel) throws ErroRepositorioException;

	public Collection<ConsumoHistorico> pesquisarConsumoFaturadoMes(String idImovel, int anoMesReferencia) throws ErroRepositorioException;

	public Collection<FaturamentoAtividadeCronograma> pesquisarRelacaoAtividadesGrupo(Integer faturamentoGrupoId, Integer anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarMenorDataConsumoTarifaVigenciaEmVigor(ConsumoTarifa consumoTarifa, int idConsumoTarifaVigencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarMaiorDataConsumoTarifaVigenciaEmVigor(ConsumoTarifa consumoTarifa, int idConsumoTarifaVigencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarMaiorDataConsumoTarifaVigencia(ConsumoTarifa consumoTarifa) throws ErroRepositorioException;

	public Object pesquisarTarifaMinimaCategoriaVigencia(Categoria categoria,
			ConsumoTarifaVigencia consumoTarifaVigencia, Integer idSubCategoria)
			throws ErroRepositorioException;

	public Collection<FaturamentoAtividadeCronograma> pesquisarFaturamentoAtividadeCronogramaComandadasNaoRealizadas(int numeroPagina) throws ErroRepositorioException;

	public int pesquisarFaturamentoAtividadeCronogramaComandadasNaoRealizadasCount() throws ErroRepositorioException;

	public Integer pesquisarExistenciaContaParaConcorrencia(String idConta, String ultimaAlteracao) throws ErroRepositorioException;

	public Integer verificarExistenciaDebitoTipo(Integer idDebitoTipo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarConta(Integer idConta) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection consultarContaHistorico(Integer idConta) throws ErroRepositorioException;

	public BigDecimal obterPercentualLigacaoEsgotoImovel(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasImpressao(Integer anoMesReferencia, FaturamentoGrupo faturamentoGrupo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasImpressao(Integer anoMesReferencia, FaturamentoGrupo faturamentoGrupo, Integer numeroPaginas) throws ErroRepositorioException;

	public BigDecimal pesquisarValorMultasCobradas(int idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarConta(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void atualizaDebitoACobrar(Collection colecaoDebitosACobrar) throws ErroRepositorioException;

	public Integer pesquisarClienteResponsavelImovel(Integer idImovel) throws ErroRepositorioException;

	public ImpostoTipoAliquota pesquisarAliquotaImposto(Integer idImpostoTipo, Integer anoMesReferencia) throws ErroRepositorioException;

	public void removerClientesConta(Integer idConta) throws ErroRepositorioException;

	public void removerImpostosDeduzidosConta(Integer idConta) throws ErroRepositorioException;

	public void atualizarDataHoraRealizacaoAtividade(Integer idAtividade, Integer anoMesReferencia, Integer idFaturamentoGrupo) throws ErroRepositorioException;

	public void atualizarAnoMesReferenciaFaturamentoGrupo(Integer idFaturamentoGrupo, Integer anoMesReferencia) throws ErroRepositorioException;

	public Integer inserirDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral) throws ErroRepositorioException;

	public void inserirDebitoACobrar(DebitoACobrar debitoACobrar) throws ErroRepositorioException; 
	
	public void atualizarImoveisSituacaoEspecialCobrancaFinalizada(int anoMesFaturamento, Integer idSetorComercial) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoCobradoCategoria(Integer idDebitoCobrado) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCreditoRealizadoCategoria(Integer idCreditoRealizado) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitosACobrarCategoria(DebitoACobrar debitoACobrar) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCreditoARealizarCategoria(CreditoARealizar creditoARealizar) throws ErroRepositorioException;

	public Collection<IContaImpostosDeduzidos> pesquisarContaImpostosDeduzidos(Integer idConta)	throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaCategoriaConsumoFaixa(Integer idConta) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaCategoriaConsumoFaixaHistorico(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void atualizarIndicadorContaNoHistorico(Collection idsContas) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void atualizarIndicadorDebitoACobrarNoHistorico(Collection idsDebitosACobrar) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void atualizarIndicadorCreditoARealizarNoHistorico(Collection idsCreditoARealizar) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarFaturamentoGrupoNaoFaturados(Integer anoMesReferenciaFaturamento) throws ErroRepositorioException;

	public Collection<IClienteConta> pesquisarClienteConta(Integer idConta) throws ErroRepositorioException;

	public void inserirDebitoAutomaticoMovimento(DebitoAutomaticoMovimento debitoAutomaticoMovimento) throws ErroRepositorioException;

	public Object[] pesquisarContaRetificacao(Integer idConta) throws ErroRepositorioException;

	public Collection<Conta> obterContaAgrupadasPorImovel(int anoMesReferenciaContabil, int idLocalidade, int idQuadra) throws ErroRepositorioException;

	public Object pesquisarDataUltimaAlteracaoConta(Integer idConta) throws ErroRepositorioException;

	public Object[] pesquisarParmsFaturamentoGrupo(Integer idImovel) throws ErroRepositorioException;

	public Date pesquisarDataRealizacaoFaturamentoAtividadeCronograma(
			Integer idFaturamentoGrupo, Integer idFaturamentoAtividade,
			Integer amReferencia) throws ErroRepositorioException;

	public Object[] pesquisarParmsClienteResponsavelConta(Integer idConta) throws ErroRepositorioException;

	public Object[] pesquisarContaDigitada(String idImovel, String referenciaConta) throws ErroRepositorioException;

	public Object pesquisarClienteResponsavel(Integer idImovel)
			throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection obterContasImovelIntervalo(Integer imovel,
			Integer situacaoNormal, Integer situacaoIncluida,
			Integer situacaoRetificada, Integer anoMesInicio, Integer anoMesFim)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasImovelIntervalo(Integer imovel,
			Integer situacaoNormal, Integer situacaoIncluida,
			Integer situacaoRetificada, Integer anoMesInicio,
			Integer anoMesFim, Integer idContaMotivoRevisao)
			throws ErroRepositorioException;

	public ResumoFaturamentoSimulacao pesquisarResumoFaturamentoSimulacao(ResumoFaturamentoSimulacao resumoFaturamentoSimulacao) throws ErroRepositorioException;

	public void apagarContaCategoriaConsumoFaixa(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void apagarContaCategoria(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void apagarContaImpressao(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void apagarClienteConta(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void apagarContaImpostosDeduzidos(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void apagarDebitoAutomaticoMovimento(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void apagarDebitoCobradoCategoria(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void apagarDebitoCobrado(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void apagarCreditoRealizadoCategoria(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void apagarCreditoRealizado(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void atualizarDebitoACobrar(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void atualizarCreditoARealizar(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void atualizarContaGeral(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void apagarConta(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void apagarContaGeral(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public Integer quantidadeContasRota(Integer anoMesFaturamento, Rota rota, Integer debitoCreditoSituacaoAtual, Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarConsumoTarifaCategoriaPorSubCategoria(ConsumoTarifaVigencia consumoTarifaVigencia, Categoria categoria, 
			Subcategoria subCategoria) throws ErroRepositorioException;

	public void deletarFaturaClienteResponsavel(Integer idCliente,
			Integer anoMesReferenciaFatura,
			Integer anoMesReferenciaFaturaAntecipada)
			throws ErroRepositorioException;

	public void removerFaturamentoGrupoAtividades( Integer idFaturamentoGrupoMensal) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsLocalidadeParaEncerrarFaturamento() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarIdsLocalidadeParaGerarResumoLigacoesEconomias() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaERota(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarIdImovelCondominioLigacoesMedicaoIndividualizada(
			FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql,
			String anoMesfaturamentoGrupo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarLigacoesMedicaoIndividualizadaRelatorio(Integer idImovel, String anoMesfaturamentoGrupo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarEmitirExtratoConsumoImovelCondominio(Collection idsRotas, String anoMesFaturamento) throws ErroRepositorioException;

	public Integer somaConsumosImoveisAssociados(Integer idImovel, String anoMes) throws ErroRepositorioException;

	public Integer quantidadeImoveisAssociados(Integer idImovel, String anoMes) throws ErroRepositorioException;

	public Integer consultarQtdeRegistrosResumoFaturamentoRelatorio(
			int anoMesReferencia, Integer localidade, Integer municipio,
			Integer gerenciaRegional, String opcaoTotalizacao)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterConta(Integer idConta) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsLocalidade() throws ErroRepositorioException;

	public void atualizarSequencialContaImpressao(Map<Integer, Integer> mapAtualizaSequencial) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarContasEmitidasRelatorio(int anoMesReferencia,
			Integer grupoFaturamento, Collection esferaPoder)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Integer consultarQtdeContasEmitidasRelatorio(int anoMesReferencia, Integer grupoFaturamento, Collection esferaPoder) throws ErroRepositorioException;

	public Integer retornaAnoMesFaturamentoGrupo(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarMapaControleContaRelatorio(Integer idGrupoFaturamento, String anoMes, String indicadorFichaCompensacao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarResumoContasLocalidade(Integer idGrupoFaturamento, String anoMes, Integer idFirma) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterGuiasPagamentoParcelamentoItem(Integer idGuiaPagamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterGuiasPagamentoCobrancaDocumentoItem(Integer idGuiaPagamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterGuiasPagamentoPagamento(Integer idGuiaPagamento) throws ErroRepositorioException;

	public boolean obterIndicadorPagamentosClassificadosContaReferenciaMenorIgualAtual(
			Integer conta, Integer idImovel, Integer anoMesReferenciaConta,
			Integer anoMesReferenciaAtual) throws ErroRepositorioException;

	public boolean obterIndicadorPagamentosClassificadosGuiaPagamentoReferenciaMenorIgualAtual(
			Integer idGuiaPagamento, Integer idImovel, Integer idDebitoTipo,
			Integer anoMesReferenciaAtual) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasImovel(Integer imovel, Integer situacaoNormal,
			Integer situacaoIncluida, Integer situacaoRetificada,
			Date dataAnoMesReferenciaUltimoDia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection gerarArquivoTextoFaturamento(int anoMes, Integer idCliente) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection gerarArquivoTextoFaturamentoCreditos(int anoMes, Integer idCliente, Integer imovelId) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection gerarArquivoTextoFaturamentoServicos(int anoMes, Integer idCliente, Integer imovelId) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection gerarArquivoTextoFaturamentoImpostos(int anoMes, Integer idCliente, Integer imovelId) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoDoacoesSituacaoNormal(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Integer pesquisarQuantidadeContasImoveis(Integer anoMes,
			Collection idsImovel, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim,
			String indicadorContaPaga, Integer somenteDebitoAutomatico) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Integer pesquisarQuantidadeContasRevisaoImoveis(Integer anoMes,
			Collection idsImovel, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim,
			String indicadorContaPaga) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasImoveis(Integer anoMes,
			Collection idsImovel, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim,
			String indicadorContaPaga) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasImoveis(Collection idsImovel, Integer anoMes, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim, String indicadorContaPaga, Date dataVencimentoInformada,
			boolean isDebitoEmConta) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasImoveis(Collection idsImovel,
			Integer anoMes, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim,
			String indicadorContaPaga, Date dataVencimentoInformada) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasImoveis(Integer anoMes, Collection idsImovel,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim,
			Integer anoMesFim, String indicadorContaPaga)
			throws ErroRepositorioException;

	public Short recuperarValorMaximoSequencialImpressaoMais10() throws ErroRepositorioException;

	public void apagarIdContaPagamentos(Integer idConta) throws ErroRepositorioException;

	public void apagarIdDebitoACobrarPagamentos(Integer idDebitoACobrar) throws ErroRepositorioException;

	public Collection<Conta> pesquisarContasCanceladasPorMesAnoReferenciaContabil(
			int anoMesReferenciaContabil, Integer idSetorComercial,
			Integer numeroIndice, Integer quantidadeRegistros)
			throws ErroRepositorioException;

	public String pesquisarClienteUsuarioConta(Integer idConta) throws ErroRepositorioException;

	public Object[] pesquisarAnoMesEDiaVencimentoFaturamentoGrupo(Integer idImovel) throws ErroRepositorioException;

	public BigDecimal pesquisarValorMultasCobradasPorFinanciamnetoTipo(int idConta) throws ErroRepositorioException;

	public Object pesquisarTarifaMinimaCategoriaVigenciaPorSubcategoria(
			ConsumoTarifaVigencia consumoTarifaVigencia,
			Subcategoria subcategoria) throws ErroRepositorioException;

	public Collection<IDebitoCobrado> pesquisarDebitosCobrados(Integer idConta) throws ErroRepositorioException;

	public Collection<ICreditoRealizado> pesquisarCreditosRealizados(Integer idConta) throws ErroRepositorioException;

	public int pesquisarQuantidadeDebitosCobradosComParcelamento(Collection<ContaValoresHelper> colecaoContasValores) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarIdContasImoveis(Integer anoMes,
			Collection idsImovel, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim,
			String indicadorContaPaga) throws ErroRepositorioException;

	public Integer pesquisarDebitoCreditoSituacaoAtualConta(Integer idConta) throws ErroRepositorioException;

	public Integer pesquisarDebitoCreditoSituacaoAtualConta(Integer idImovel, Integer anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaCategoriaFaixas(Integer idConta, Integer idCategoria, Integer idSubCategoria) throws ErroRepositorioException;

	public Integer pesquisarContaRetificada(Integer idImovel, int anoMesReferenciaConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosImoveisParaGerarCreditoARealizar(Collection idsGrupos, Integer anoMesReferenciaConta,
			Integer anoMesReferenciaDebito) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaCategoriaSubCategoria(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasEmitirCAERN(Integer idTipoConta,
			Integer idEmpresa, Integer numeroPaginas, Integer anoMesReferencia,
			Integer idFaturamentoGrupo, BigDecimal valorContaFichaComp)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasEmitirOrgaoPublicoCAERN(
			Integer idTipoConta, Integer idEmpresa, Integer numeroPaginas,
			Integer anoMesReferencia, Integer idFaturamentoGrupo,
			BigDecimal valorContaFichaComp) throws ErroRepositorioException;

	public Integer pesquisarAnoMesReferenciaFaturamentoGrupo(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoACobrarParaRemocao(Rota rota, Integer anoMesReferenciaContabil) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void deletarDebitosACobrarCategoria(Collection idsDebitoACobrar) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void deletarDebitosACobrar(Collection idsDebitoACobrar) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarConsumoTarifaRelatorio(String descricao, Date dataVigenciaInicial, Date dataVigenciaFinal) throws ErroRepositorioException;

	public Date pesquisarDataFinalValidadeConsumoTarifa(Integer idConsumoTarifa, Date dataInicioVigencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasEmRevisaoPorAcaoUsuario(Collection idsConta) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeContasCliente(Integer codigoCliente,
			Short relacaoTipo, Integer anoMes, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasCliente(Integer codigoCliente,
			Short relacaoTipo, Integer anoMes, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim,
			Integer codigoClienteSuperior) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasCliente(Integer codigoCliente,
			Short relacaoTipo, Integer anoMes, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim,
			Integer codigoClienteSuperior, Date dataVencimentoInformada,
			boolean isDebitoAutomatico) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasCliente(Integer codigoCliente,
			Short relacaoTipo, Integer anoMes, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarIdContasCliente(Integer codigoCliente,
			Short relacaoTipo, Integer anoMes, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasNaoEmRevisaoOuEmRevisaoPorAcaoUsuario(Collection idsConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaHistorico(Integer idConta) throws ErroRepositorioException;

	public Integer pesquisarIdClienteResponsavelContaHistorico(Integer idConta) throws ErroRepositorioException;

	public Short obterQuantidadeEconomiasContaHistorico(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasClienteResponsavel(
			Collection<Integer> idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaCategoriaHistorico(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaCategoriaHistoricoFaixas(Integer idConta, Integer idCategoria) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarParmsContaImpostosDeduzidosHistorico(Integer idConta) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarParmsDebitoAutomaticoHistorico(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public List pesquisarParmsDebitoCobradoHistoricoPorTipo(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public List pesquisarParmsCreditoRealizadoHistoricoPorTipo(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioAnaliticoFaturamento(
			int anoMesFaturamento, Integer idFaturamentoGrupo,
			int indicadorLocalidadeInformatizada, Collection idLocalidades,
			Collection idSetores, Collection idQuadras)
			throws ErroRepositorioException;

	public Integer pesquisarImovelDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException;

	public Object[] obterArrecadacaoFormaPagamentoContasMenorData(Conta conta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoFaturamentoRelatorioEstadoPorUnidadeNegocio(int anoMesReferencia, String opcaoRelatorio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoFaturamentoRelatorioPorUnidadeNegocio(
			int anoMesReferencia, Integer unidadeNegocio, String opcaoRelatorio)
			throws ErroRepositorioException;

	public Collection<Object[]> pesquisarEmitirHistogramaAgua(
			FiltrarEmitirHistogramaAguaHelper filtro)
			throws ErroRepositorioException;

	public Integer pesquisarEmitirHistogramaAguaVolumeConsumo(
			FiltrarEmitirHistogramaAguaHelper filtro, Short consumo,
			Categoria categoria, Short medicao) throws ErroRepositorioException;

	public Object[] pesquisarEmitirHistogramaAguaTotalGeral(FiltrarEmitirHistogramaAguaHelper filtro, Categoria categoria)
			throws ErroRepositorioException;

	public Object[] pesquisarEmitirHistogramaAguaEconomia(FiltrarEmitirHistogramaAguaEconomiaHelper filtro)
			throws ErroRepositorioException;

	public Collection<Object[]> pesquisarEmitirHistogramaAguaEconomiaChavesAgrupadas(
			FiltrarEmitirHistogramaAguaEconomiaHelper filtro)
			throws ErroRepositorioException;

	public ConsumoTarifaVigencia pesquisarConsumoTarifaVigenciaMenorOUIgualDataFaturamento(
			Integer idConsumoTarifa, Date dataFaturamento)
			throws ErroRepositorioException;

	public Integer pesquisarDebitoCreditoSituacaoAtualContaHistorico(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaHistoricoERota(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaCategoriaHistoricoFaixas(Integer idConta,
			Integer idCategoria, Integer idSubCategoria)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaHistoricoCategoriaSubCategoria(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoAutomaticoMovimentoContaRetificada(Integer idContaRetificada) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void atualizarDebitoAutomaticoMovimentoContaRetificada(
			Collection colecaoDebitoAutomaticoMovimento, Integer idConta)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDataRevisaoConta(Collection idsConta) throws ErroRepositorioException;

	public void atualizarDebitoCreditoSituacaoAtualDoCreditoARealizar(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterIdsContasImovel(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public int obterPagamentosContas(Collection idsContas) throws ErroRepositorioException;

	public boolean verificarExistenciaClienteResponsavelConta(int idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarResumoMetas(Integer anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarResumoMetasAcumulado(Integer anoMesReferencia) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeContasGrupoFaturamento(Integer anoMes, 
			Integer idGrupoFaturamento, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim, Integer somenteDebitoAutomatico)
			throws ErroRepositorioException;

	public Integer pesquisarQuantidadeContasRevisaoGrupoFaturamento(
			Integer anoMes, Integer idGrupoFaturamento,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim,
			Integer anoMesFim) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasGrupoFaturamento(Integer anoMes,
			Integer idGrupoFaturamento, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasGrupoFaturamento(Integer anoMes,
			Integer idGrupoFaturamento, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarIdContasGrupoFaturamento(Integer anoMes,
			Integer idGrupoFaturamento, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim)
			throws ErroRepositorioException;

	public void alterarVencimentoContaGrupoFaturamento(Date dataVencimento,
			Date dataValidade, Short indicadorAlteracaoVencimento,
			Integer idGrupoFaturamento, Integer anoMes, Integer anoMesFim,
			Date dataVencimentoContaInicio, Date dataVencimentoContaFim,
			boolean somenteDebitoAutomatico) throws ErroRepositorioException;

	public Integer pesquisarTipoConta(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosImoveisParaGerarCreditoARealizarPorImoveisComContasComVencimento14_08_2007() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection buscarDebitosCobradosEmitirContaCaern(Conta conta) throws ErroRepositorioException;

	public Date pesquisarFaturamentoAtividadeCronogramaDataPrevista(
			Integer faturamentoGrupoId, Integer faturamentoAtividadeId,
			Integer anoMesReferencia) throws ErroRepositorioException;

	public Object[] pesquisarContaAtualizacaoTarifaria(Integer idConta) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeFaturaPorQualificador(Short codigoQualificador) throws ErroRepositorioException;

	public Object[] pesquisarFaturaPorQualificador(Short codigoQualificador,
			Integer anoMesReferencia, BigDecimal valorDebito)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarFaturaItem(Integer idFatura) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioVolumesFaturados(
			Integer idLocalidade, Integer anoMes, Integer anoMes1,
			Integer anoMes2, Integer anoMes3, Integer anoMes4, Integer anoMes5,
			Integer anoMes6) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioVolumesFaturadosResumido(
			Integer idLocalidade, Integer anoMes, Integer anoMes1,
			Integer anoMes2, Integer anoMes3, Integer anoMes4, Integer anoMes5,
			Integer anoMes6) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioContasRevisao(
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal,
			Collection colecaoIdsMotivoRevisao, Integer idImovelPerfil,
			Integer referenciaInicial, Integer referenciaFinal,
			Integer idCategoria, Integer idEsferaPoder)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioContasRevisaoResumido(
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal,
			Collection colecaoIdsMotivoRevisao, Integer idImovelPerfil,
			Integer referenciaInicial, Integer referenciaFinal,
			Integer idCategoria, Integer idEsferaPoder)
			throws ErroRepositorioException;

	public void atualizaClienteResponsavelOrgaoPublicoCAERN(Integer anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasAtualizacaoTarifaria(Integer idImovel,
			Integer inicialReferencia, Integer finalReferencia,
			Date inicialVencimento, Date finalVencimento,
			Integer indicadorContasRevisao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoFaixaValores(Integer idFaixaValor, Double valorFaixa) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer idOrigemCredito, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCredito(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idCreditoOrigem, Integer idSituacaoAtual,
			Integer idSituacaoAnterior) throws ErroRepositorioException;

	public Collection<ResumoFaturamento> acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idFinanciamentoTipo, Integer idSituacaoAtual,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilNaoPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idFinanciamentoTipo, Integer idSituacaoAtual,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public ResumoFaturamento acumularValorImpostoPorTipoImpostoESituacaoConta(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria,
			Integer idImpostoTipo, Integer[] idsSituacaoAtual)
			throws ErroRepositorioException;

	public BigDecimal acumularValorImpostoPorTipoImpostoESituacaoAtualConta(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria,
			Integer idImpostoTipo, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	public BigDecimal acumularValorImpostoPorTipoImpostoESituacaoAtualConta(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria,
			Integer idImpostoTipo, Integer idSituacaoAtual,
			Integer idSituacaoAnterior) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer[] idsOrigemCredito, Integer idSituacaoAtual,
			Integer idSituacaoAnterior) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer[] idsOrigemCredito, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCreditoARealizarPorOrigemCredito(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsOrigemCredito, Integer idSituacaoAtual,
			Integer idSituacaoAnterior) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioAnormalidadeConsumo(
			Integer idGrupoFaturamento, Short codigoRota,
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal,
			Integer referencia, Integer idImovelPerfil,
			Integer numOcorConsecutivas, String indicadorOcorrenciasIguais,
			Integer mediaConsumoInicial, Integer mediaConsumoFinal,
			Collection<Integer> colecaoIdsAnormalidadeConsumo,
			Collection<Integer> colecaoIdsAnormalidadeLeitura,
			Collection<Integer> colecaoIdsAnormalidadeLeituraInformada,
			Integer tipoMedicao, Collection<Integer> colecaoIdsEmpresa,
			Integer numeroQuadraInicial, Integer numeroQuadraFinal,
			Integer idCategoria) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarEmitirHistogramaEsgoto(FiltrarEmitirHistogramaEsgotoHelper filtro) throws ErroRepositorioException;

	public Object[] pesquisarEmitirHistogramaEsgotoTotalGeral(
			FiltrarEmitirHistogramaEsgotoHelper filtro, Categoria categoria)
			throws ErroRepositorioException;

	public Integer pesquisarEmitirHistogramaEsgotoVolumeConsumo(
			FiltrarEmitirHistogramaEsgotoHelper filtro, Short consumo,
			Categoria categoria, Short medicao) throws ErroRepositorioException;

	public Object[] pesquisarEmitirHistogramaEsgotoEconomia(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro)
			throws ErroRepositorioException;

	public Collection<Object[]> pesquisarEmitirHistogramaEsgotoEconomiaChavesAgrupadas(
			FiltrarEmitirHistogramaEsgotoEconomiaHelper filtro)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasNaoFichaCompensacao(Integer idTipoConta,
			Integer idEmpresa, Integer numeroPaginas, Integer anoMesReferencia,
			Integer idFaturamentoGrupo,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasFichaCompensacao(Integer idTipoConta,
			Integer idEmpresa, Integer numeroPaginas, Integer anoMesReferencia,
			Integer idFaturamentoGrupo,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasClienteResponsavelNaoFichaCompensacao(
			Integer idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Integer idFaturamentoGrupo,
			Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasClienteResponsavelFichaCompensacao(
			Integer idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Integer idFaturamentoGrupo,
			Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasClienteResponsavelNaoFichaCompensacao(
			Integer idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasClienteResponsavelFichaCompensacao(
			Integer idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado,
			Integer imovelContaEnvio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarIdsContasDoImovelPorMesAnoReferencia(
			int anoMesReferencia, Integer idImovel)
			throws ErroRepositorioException;

	public Boolean pesquisarExisteciaParcelamentoConta(Integer idConta) throws ErroRepositorioException;

	public Conta pesquisarExistenciaContaComSituacaoAtual(Integer idConta) throws ErroRepositorioException;

	public void atualizarSequencialContaImpressaoFichaCompensacao(
			Map<Integer, Integer> mapAtualizaSequencial)
			throws ErroRepositorioException;

	public ConsumoTarifaVigencia pesquisarConsumoTarifaVigenciaEmVigor(Integer idConsumoTarifa) throws ErroRepositorioException;

	public Object[] pesquisarQuantidadeFaturasValorFaturas(Integer idImovel) throws ErroRepositorioException;

	public Integer pesquisarReferenciaAntigaContaSemPagamento(Integer idImovel) throws ErroRepositorioException;

	public Integer pesquisarReferenciaAtualContaSemPagamento(Integer idImovel) throws ErroRepositorioException;

	public Object[] pesquisarContaImovel(Integer idImovel, int anoMesReferencia) throws ErroRepositorioException;

	public Object[] obterPercentualAlternativoLigacaoEsgotoImovel(Integer idImovel) throws ErroRepositorioException;

	public ContaHistorico pesquisarExistenciaContaHistorico(Integer idConta) throws ErroRepositorioException;

	public Conta obterImovelLocalidadeConta(Integer idConta) throws ErroRepositorioException;

	public ContaHistorico obterImovelLocalidadeContaHistorico(Integer idConta) throws ErroRepositorioException;

	public void adicionaUmNNParcelaBonusDebitoAcobrar(Integer idParcelamento) throws ErroRepositorioException;

	public void adicionaUmNNParcelaBonusCreditoARealizar(Integer idParcelamento) throws ErroRepositorioException;

	public Object[] pesquisarContaParaAcrescimoPorImpontualidade(Integer idConta) throws ErroRepositorioException;

	public FaturamentoAtivCronRota pesquisarFaturamentoAtivCronRota(
			Integer idRota, Integer idFaturamentoAtividade,
			Integer idFaturamentoGrupo, Integer anoMesReferencia)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorGuiaDevolucaoPorLancamentoItemContabil(
			int anoMesReferencia, Integer idLocalidade,
			Integer idSituacaoAtual, Integer idLancamentoItemContabil)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorGuiaDevolucaoPorLancamentoItemContabil(
			int anoMesReferencia, Integer idLocalidade,
			Integer idSituacaoAtual, Integer idSituacaoAnterior,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public Object[] acumularValorAguaEsgotoPorSituacaoConta(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idSituacaoAtual, int idSituacaoAnterior)
			throws ErroRepositorioException;

	public Object[] pesquisarValorLongoECurtoPrazoDebitoACobrarPorTipoFinanciamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoFinanciamento, int idLancamentoItemContabil,
			int idSituacaoAtual, int idSituacaoAnterior)
			throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoTipoFinanciamentoServicoSituacaoIncluida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoLancamentoItemContabilRetificada(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idLancamentoItemContabil)
			throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPorTipoFinanciamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idSituacaoAtual, int idFinanciamentoTipo)
			throws ErroRepositorioException;

	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoLancamentoItemContabilCanceladoPorRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idLancamentoItemContabil)
			throws ErroRepositorioException;

	public Object[] pesquisarValorLongoECurtoPrazoDebitoACobrarPorTipoFinanciamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoFinanciamento, int idLancamentoItemContabil,
			int idSituacaoAtual) throws ErroRepositorioException;

	public BigDecimal acumularValorGuiaPagamentoPorTipoFinanciamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int[] idTipoFinanciamento, int idItemContabil, int idSituacaoAtual)
			throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPorTipoFinanciamentoSituacaoContaCanceladaPorRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Collection<Integer> tipoFinanciamento, Integer itemContabil)
			throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPorTipoFinanciamentoSituacaoContaRetificada(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Collection<Integer> tipoFinaciamento, Integer itemContabil)
			throws ErroRepositorioException;

	public Collection<Object[]> acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsFinanciamentoTipo, Integer idSituacaoAtual,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoComBaixaContabilPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public Collection<Object[]> acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilNaoPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsFinanciamentoTipo, Integer idSituacaoAtual,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoComBaixaContabilNaoPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorGuiaPagamentoPorTipoFinanciamentoAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idSituacaoAtual, int idSituacaoAnterior, int idFinanciamentoTipo)
			throws ErroRepositorioException;

	public BigDecimal acumularValorAguaPorSituacaoConta(int anoMesReferencia,
			int idLocalidade, int idCategoria, int idSituacaoAtual)
			throws ErroRepositorioException;

	public BigDecimal acumularValorAguaPorSituacaoConta(int anoMesReferencia,
			int idLocalidade, int idCategoria, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;

	public BigDecimal acumularValorEsgotoPorSituacaoConta(int anoMesReferencia,
			int idLocalidade, int idCategoria, int idSituacaoAtual)
			throws ErroRepositorioException;

	public BigDecimal acumularValorEsgotoPorSituacaoConta(int anoMesReferencia,
			int idLocalidade, int idCategoria, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;

	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoRetificada(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem) throws ErroRepositorioException;

	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoCanceladoPorRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem) throws ErroRepositorioException;

	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCredito(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	public Object[] pesquisarValorLongoECurtoPrazoDebitoACobrarPorTipoFinanciamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoFinanciamento, int idSituacaoAtual)
			throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPorTipoFinanciamentoCanceladaPorRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idFinanciamentoTipo) throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPorTipoFinanciamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idLancamentoItemContabil, int idSituacaoAtual,
			Collection<Integer> idFinanciamentoTipo)
			throws ErroRepositorioException;

	public BigDecimal acumularValorCategoriaDebitoTipoFinanciamentoServicoSituacaoNormalNumeroPrestacoesCobradasMaiorQue11(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idSituacaoAtual, int idSituacaoAnterior, int idFinanciamentoTipo)
			throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoJurosParcelamentoSituacaoNormalDiferencaPrestacoesMaiorQue11(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idFinanciamentoTipo, int idSituacaoAtual, int idSituacaoAnterior)
			throws ErroRepositorioException;

	public Object[] pesquisarValorLongoECurtoPrazoDebitoACobrarPorGrupoParcelamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idGrupoParcelamento, int idSituacaoAtual,
			int idSituacaoAnterior, boolean flagNPrestacaoCobradaIgualAZero)
			throws ErroRepositorioException;

	public Object[] pesquisarValorLongoECurtoPrazoCreditoARealizarPorOrigemCredito(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditosOrigem, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorDebitoCobradoPorTipoFinanciamentoAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idSituacaoAtual, int idSituacaoAnterior, int idTipoFinanciamento)
			throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPorTipoFinanciamentoPorReferenciaConta(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idSituacaoAtual, int idSituacaoAnterior, int idFinanciamentoTipo)
			throws ErroRepositorioException;

	public BigDecimal acumularValorContaCategoriaPorTipoImposto(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoImposto, int idSituacaoAtual, int idSituacaoAnterior)
			throws ErroRepositorioException;

	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoPorReferenciaConta(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorCreditoRealizadoCategoriaPorOrigemCreditoAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;

	public BigDecimal acumularValorContaCategoriaPorTipoImposto(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoImposto, int idSituacaoAtual)
			throws ErroRepositorioException;

	public BigDecimal acumularValorContaCategoriaPorTipoImpostoReferenciaContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoImposto, int idSituacaoAtual, int idSituacaoAnterior)
			throws ErroRepositorioException;

	public Object[] acumularValorAguaEsgotoPorSituacaoContaComBaixaContabilPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsSituacaoAtual) throws ErroRepositorioException;

	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaComBaixaContabilPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsSituacaoAtual) throws ErroRepositorioException;

	public Object[] acumularValorAguaEsgotoPorSituacaoContaComBaixaContabilNaoPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsSituacaoAtual) throws ErroRepositorioException;

	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idFinanciamentoTipo, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoComBaixaContabilPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idCreditoOrigem, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	public BigDecimal acumularValorImpostoPorTipoImpostoESituacaoContaComBaixaContabilPreenchida(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria,
			Integer idImpostoTipo, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilNaoPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idFinanciamentoTipo, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	public ResumoFaturamento acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoComBaixaContabilNaoPreenchida(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer idCreditoOrigem, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	public BigDecimal acumularValorImpostoPorTipoImpostoESituacaoContaComBaixaContabilNaoPreenchida(
			int anoMesReferencia, Integer idLocalidade, Integer idCategoria,
			Integer idImpostoTipo, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer[] idsOrigemCredito, Integer idSituacaoAtual,
			Categoria categoria) throws ErroRepositorioException;

	public BigDecimal acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer idOrigemCredito, Integer idSituacaoAtual,
			Categoria categoria) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer[] idsOrigemCredito, Integer idSituacaoAtual,
			Integer idSituacaoAnterior, Categoria categoria)
			throws ErroRepositorioException;

	public BigDecimal acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer idOrigemCredito, Integer idSituacaoAtual,
			Integer idSituacaoAnterior, Categoria categoria)
			throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoACobrarCategoriaPorTipoFinanciamento(
			int anoMesReferenciaContabil, int idLocalidade, int idCategoria,
			Integer[] idsTipoFinanciamento, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;

	public BigDecimal acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer idCategoria, Integer idOrigemCredito,
			Integer idSituacaoAtual, Integer idSituacaoAnterior)
			throws ErroRepositorioException;

	public BigDecimal acumularValorCreditoARealizarPorOrigemCredito(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer idCategoria, Integer idOrigemCredito,
			Integer idSituacaoAtual) throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPorTipoFinanciamentoRetificada(
			int anoMesReferencia, Localidade localidade, int idCategoria,
			int idFinanciamentoTipo) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarValorLongoECurtoPrazoDebitoACobrarPorTipoFinanciamentoAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoFinanciamento, int idSituacaoAtual)
			throws ErroRepositorioException;

	public Collection<Object[]> pesquisarValorLongoECurtoPrazoDebitoACobrarPorTipoFinanciamentoAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoFinanciamento, int idSituacaoAtual, int idSituacaoAnterior)
			throws ErroRepositorioException;

	public Collection<Object[]> acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilNaoPreenchidaAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsFinanciamentoTipo, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	public Collection<Object[]> acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamentoComBaixaContabilPreenchidaAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsFinanciamentoTipo, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	public Collection<Object[]> acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoComBaixaContabilNaoPreenchidaAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	public Collection<Object[]> acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoComBaixaContabilPreenchidaAgrupandoPorLancamentoItemContabil(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPrincipalCategoriaImovelPorRota(Short codigoEmpresaFebraban, Integer idRota) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasImovelComPagamento(Integer imovel,
			Integer situacaoNormal, Integer situacaoIncluida,
			Integer situacaoRetificada, Date dataAnoMesReferenciaUltimoDia,
			Integer anoMesArrecadacao) throws ErroRepositorioException;

	public Collection<Integer> obterIndicadorPagamentosClassificadosContaReferenciaMenorIgualAtual(
			Collection<Integer> idsConta, Integer anoMesReferenciaAtual)
			throws ErroRepositorioException;

	public Collection<Object[]> obterIndicadorGeracaoAcrescimosClienteImovel(Integer idRota) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasCanceladasFaturamentoFechado(RelatorioContasCanceladasRetificadasHelper helper);

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasCanceladasFaturamentoAberto(RelatorioContasCanceladasRetificadasHelper helper);

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasRetificadasFaturamentoFechado(RelatorioContasCanceladasRetificadasHelper helper);
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasRetificadasFaturamentoAberto(RelatorioContasCanceladasRetificadasHelper helper);
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasRetificadasValorNovoConta(RelatorioContasCanceladasRetificadasHelper helper, String idImovel, String anoMesReferenciaContaOriginal);
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasRetificadasValorNovoContaAberta(RelatorioContasCanceladasRetificadasHelper helper, String idImovel, String anoMesReferenciaContaOriginal);
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasRetificadasValorNovoContaHistorico(RelatorioContasCanceladasRetificadasHelper helper, String idImovel, String anoMesReferenciaContaOriginal);

	public Integer pesquisarMaxIdConta() throws ErroRepositorioException;

	public Integer pesquisarMaxIdContaHistorico() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelGerarArquivoTextoFaturamento(Rota rota,
			int numeroPaginas, int quantidadeRegistros,
			SistemaParametro sistemaParametro, Integer idImovelCondominio)
			throws ErroRepositorioException;

	public Object[] pesquisarArquivoTextoRoteiroEmpresa(Integer idRota, Integer anoMesReferencia) throws ErroRepositorioException;

	public Object[] pesquisarContaGerarArquivoTextoFaturamento(Integer idImovel, Integer anoMesReferencia, Integer idFaturamentoGrupo) throws ErroRepositorioException;

	public Object[] pesquisarDebitoCobradoDeParcelamento(Conta conta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoCobradoNaoParcelamento(Conta conta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCreditoRealizado(Conta conta) throws ErroRepositorioException;

	public Object[] pesquisarIntervaloNumeroQuadraPorRota(Integer idRota) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarFaturaItem(Integer idCliente, Integer anoMesReferencia, BigDecimal valorDebito) throws ErroRepositorioException;

	public void atualizarContaCanceladaOuRetificada(Conta conta, RegistroAtendimento registroAtendimento) throws ErroRepositorioException;

	public BigDecimal obterValorDebitoServico(Integer idConta) throws ErroRepositorioException;

	public BigDecimal obterValorDebitoMultas(Integer idConta) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarDadosRelatorioFaturasAgrupadas(
			Integer anoMesReferencia, Cliente cliente,
			Collection<Integer> idsClientes) throws ErroRepositorioException;

	public Integer pesquisarDadosRelatorioFaturasAgrupadasCount(
			Integer anoMesReferencia, Cliente cliente,
			Collection<Integer> idsClientes) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeFaturasReponsavel(
			Integer anoMesReferencia, Integer idCliente)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClientesFaturas(Integer idEsferaPoder) throws ErroRepositorioException;

	public BigDecimal pesquisarPercentualAliquota() throws ErroRepositorioException;

	public Collection<Object[]> pesquisarDadosRelatorioProtocoloEntregaFatura(
			Integer anoMesReferencia, Cliente cliente,
			Collection<Integer> idsClientes) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarSituacaoEspecialFaturamentoVigente(Integer idImovel, Integer anoMesReferencia) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarConsumoTarifaFaixaPelaSubcategoria(
			Integer idSubcategoria) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeContasEContasHistorico(Integer idImovel, Integer referenciaConta) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarDadosRelatorioAutoInfracao(
			Integer idUnidadeNegocio, Integer idFuncionario,
			Integer dataPagamentoInicial, Integer dataPagamentoFinal)
			throws ErroRepositorioException;

	public void excluirResumoFaturamentoPorAnoMesArrecadacaoPorLocalidade(
			int anoMesReferenciaFaturamento, Integer idLocalidade)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarQuantidadeContas(
			ComandoEmpresaCobrancaContaHelper comandoEmpresaCobrancaContaHelper)
			throws ErroRepositorioException;

	public Collection<Integer> pesquisarImoveisInformarContasEmCobranca(
			ComandoEmpresaCobrancaContaHelper comandoEmpresaCobrancaContaHelper,
			Integer numeroPagina, boolean percentualInformado)
			throws ErroRepositorioException;

	public Boolean verificarGrupoFaturamentoComandado(int anoMesReferenciaFaturamento, int idGrupoFaturamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection<Object[]> pesquisaridDebitoTipoDoDebitoCobradoDeParcelamento(Integer idConta, Collection idsFinanciamentoTipo) throws ErroRepositorioException;

	public Object[] pesquisarFaturaItemDeConta(Integer idConta) throws ErroRepositorioException;

	public void atualizarContaEmFaturaItem(Integer idFaturaItem, Conta conta, Integer consumoFaturaItem) throws ErroRepositorioException;

	public void atualizarValorDebitoFatura(Integer idFatura, BigDecimal valorDebitoFatura) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterDebitoACobrarImovel(Integer imovelID) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterDebitoACobrarHistoricoImovel(Integer imovelID) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterCreditoARealizarImovel(Integer imovelID) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterCreditoARealizarHistoricoImovel(Integer imovelID) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterGuiaPagamentoImovel(Integer imovelID) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterGuiaPagamentoHistoricoImovel(Integer imovelID) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarFaturasCliente(Integer codigoCliente,
			Integer anoMes, Date dataVencimentoFaturaInicio,
			Date dataVencimentoFaturaFim, Integer anoMesFim,
			Integer codigoClienteSuperior) throws ErroRepositorioException;

	public void alterarVencimentoFatura(Fatura fatura) throws ErroRepositorioException;

	public void atualizarLigacaoEsgotoPorRota(BigDecimal percentualAlternativo,
			Integer consumoPercentualAlternativo, Rota rota)
			throws ErroRepositorioException;

	public ContaMotivoRevisao pesquisarContaMotivoRevisao(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCategoriaPorTarifaConsumo(Integer idConsumoTarifa) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioGuiaPagamentoEmAtraso(
			FiltroGuiaPagamento filtro) throws ErroRepositorioException;

	public CreditoRealizado pesquisarCreditoRealizadoNitrato(Conta conta) throws ErroRepositorioException;

	public BigDecimal somarValorFaturasItemFatura(Fatura fatura) throws ErroRepositorioException;

	public Integer maximoNumeroSequencia(Fatura fatura) throws ErroRepositorioException;

	public Integer maximoNumeroSequenciaFaturaItem(Fatura fatura) throws ErroRepositorioException;

	public Date vencimentoFaturasItemFatura(Fatura fatura) throws ErroRepositorioException;

	public void alterarVencimentoFaturaFaturaItem(Fatura fatura) throws ErroRepositorioException;

	public Integer quantidadeCreditosARealizarRota(Integer anoMesFaturamento, Rota rota, Integer idCreditoTipo) throws ErroRepositorioException;

	public void apagarCreditoARealizarCategoria(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void atualizarCreditoARealizarGeral(	ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void apagarCreditoARealizar(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public void apagarCreditoARealizarGeral(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public Integer pesquisarResumoFaturamento(Integer anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection retornaLeiturasNaoRegistradas(FaturamentoGrupo faturamentoGrupo) throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoACobrarParcelamentoConcedidoBonus(Integer idLocalidade, Integer idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorCreditoARealizarParcelamentoConcedidoBonus(
			Integer idLocalidade, Integer idCategoria)
			throws ErroRepositorioException;

	public BigDecimal pesquisarValorCreditoPorOrigem(int idConta)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasImoveisDebitoAutomatico(Integer anoMes,
			Collection idsImovel, Date dataVencimentoContaInicio,
			Date dataVencimentoContaFim, Integer anoMesFim,
			String indicadorContaPaga, String[] bancos)
			throws ErroRepositorioException;

	public BigDecimal pesquisarValorAguaConta(Integer idImovel, Integer referencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosConsumoTarifaVigenciaProporcional(
			Date dataLeituraAnterior, Integer idConsumoTarifa,
			Integer idCategoria, Integer idSubcategoria)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosConsumoTarifaVigencia(Date dataFaturamento,
			Integer idConsumoTarifa, Integer idCategoria, Integer idSubcategoria)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosConsumoMaiorTarifaVigenciaPorTarifa(Date dataFaturamento, Integer idTarifaVigencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosConsumoTarifaFaixa(Collection idsConsumoTarifaCategoria) throws ErroRepositorioException;

	public int countRelatorioAutoInfracao(Integer idUnidadeNegocio,
			Integer idFuncionario, Integer dataPagamentoInicial,
			Integer dataPagamentoFinal) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection<Integer> pesquisarConsumoTarifaImoveis(Collection idsImoveis) throws ErroRepositorioException;

	public boolean verificarAutosAssociadosAoDebito(String[] idsDebitosACobrar) throws ErroRepositorioException;

	public void cancelarAutosInfracao(String[] idsDebitosACobrar) throws ErroRepositorioException;

	public boolean validarExistenciaDebitoAtivosAutoInfracao(Integer idAutoInfracao) throws ErroRepositorioException;

	public boolean validarExistenciaDeDebitosAutoInfracao(Integer idAutoInfracao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCartoes(Integer idArrecadacaoForma) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarDadosContaRelatorio(Integer anoMes,
			Integer idFaturamentoGrupo, Integer idLocalidadeInicial,
			Integer idLocalidadeFinal, Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal, Short codigoRotaInicial,
			Short codigoRotaFinal, Short sequencialRotaInicial,
			Short sequencialRotaFinal, String indicadorEmissao,
			String indicadorOrdenacao) throws ErroRepositorioException;

	public void apagarDadosCobranca(ApagarDadosFaturamentoHelper helper) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsSetorParaGerarHistoricoParaEncerrarFaturamento() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelFaturarGrupoPorRotaAlternativa(
			Integer idRota, int numeroPaginas, int quantidadeRegistros,
			boolean preFaturar, boolean resumo) throws ErroRepositorioException;

	public Collection<Imovel> obterImoveisPorRotasComContaEntregaEmOutroEnderecoPorRotaAlternativa(Integer idRota) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisDasQuadrasPorRotaAlternativa(Integer idRota) throws ErroRepositorioException;

	public Collection<ImovelCobrarDoacaoHelper> pesquisarImovelDoacaoPorRota(Integer idRota) throws ErroRepositorioException;

	public Collection<ImovelCobrarDoacaoHelper> pesquisarImovelDoacaoPorRotaAlternativa(Integer idRota) throws ErroRepositorioException;

	public Integer pesquisarSituacaoEspecialFaturamentoCount(FaturamentoSituacaoComando comando) throws ErroRepositorioException;

	public Collection<FaturamentoSituacaoComando> pesquisarSituacaoEspecialFaturamento(
			FaturamentoSituacaoComando comando, Integer numeroPaginasPesquisa)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarGrupoFaturamentoNaoFaturados(
			Integer anoMesReferenciaFaturamento)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarVencimentoConta(Integer idImovel, Integer anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public List pesquisarDebitoCobradoConta(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDataPagamento(Integer idContal) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPrincipalCategoriaImovelPorRotaAlternativa(
			Short codigoEmpresaFebraban, Integer idRota)
			throws ErroRepositorioException;

	public Collection<Object[]> obterIndicadorGeracaoAcrescimosClienteImovelPorRotaAlternativa(Integer idRota) throws ErroRepositorioException;

	public Date pesquisarDataPrevistaFaturamentoAtividadeCronograma(
			Integer idFaturamentoGrupo, Integer idFaturamentoAtividade,
			Integer amReferencia) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeContasCanceladasFaturamentoFechado(RelatorioContasCanceladasRetificadasHelper helper);

	public Integer pesquisarQuantidadeContasCanceladasFaturamentoAberto(RelatorioContasCanceladasRetificadasHelper helper);

	public Integer pesquisarQuantidadeContasRetificadasFaturamentoFechado(RelatorioContasCanceladasRetificadasHelper helper);

	public Integer pesquisarQuantidadeContasRetificadasFaturamentoAberto(RelatorioContasCanceladasRetificadasHelper helper);

	public void atualizarIndicadorHistoricoContaGeral(Integer idContaGeral, Short indicadorHistorico) throws ErroRepositorioException;

	public Integer pesquisarTipoCalculoConta(Integer idConta) throws ErroRepositorioException;

	public Integer pesquisarTipoCalculoContaHistorico(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Integer pesquisarDadosRelatorioContasRevisaoCount(
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer codigoSetorComercialInicial,
			Integer codigoSetorComercialFinal,
			Collection colecaoIdsMotivoRevisao, Integer idImovelPerfil,
			Integer referenciaInicial, Integer referenciaFinal,
			Integer idCategoria, Integer idEsferaPoder)
			throws ErroRepositorioException;

	public Integer gerarRelacaoAcompanhamentoFaturamentoCount(
			String idImovelCondominio, String idImovelPrincipal,
			String idNomeConta, String idSituacaoLigacaoAgua,
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
			String numeroMoradoresFinal, String idAreaConstruidaFaixa)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasEmitirCOSANPA(Integer idTipoConta,
			Integer idEmpresa, Integer numeroPaginas, Integer anoMesReferencia,
			Integer idFaturamentoGrupo) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarRelatorioJurosMultasDebitosCancelados(
			FiltrarRelatorioJurosMultasDebitosCanceladosHelper filtro)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCreditoARealizarPeloCreditoRealizado(
			Integer IdCreditoARealizar, Integer anoMesFaturamento)
			throws ErroRepositorioException;

	public void deletarCreditoRealizadoCategoria(Integer idCreditoRealizado) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasEmitirCAEMA(Integer numeroPaginas,
			Integer anoMesReferencia, Integer idFaturamentoGrupo)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasEmitirCAER(
			Collection<Integer> idTipoConta, Integer idEmpresa,
			Integer numeroPaginas, Integer anoMesReferencia,
			Integer idFaturamentoGrupo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasClienteResponsavelCAER(
			Collection<Integer> idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Integer idFaturamentoGrupo,
			Short indicadorEmissaoExtratoFaturamento)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasClienteResponsavelCAER(
			Collection<Integer> idTipoConta, Integer numeroPaginas,
			Integer anoMesReferencia, Short indicadorEmissaoExtratoFaturamento)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosTxtContasProjetosEspeciais(String anoMes,
			Integer idCliente, Integer quantidadeRegistros, Integer numeroIndice)
			throws ErroRepositorioException;

	public Integer countTxtContasProjetosEspeciais(String anoMes, Integer idCliente) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarFatura(Integer idCliente,
			Integer anoMesReferencia, Integer numeroSequencial,
			BigDecimal valordebito) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarFaturaItem(Integer idCliente,
			Integer anoMesReferencia, Integer numeroSequencial,
			BigDecimal valordebito, int numeroPaginas, int quantidadeRegistros)
			throws ErroRepositorioException;

	public Object[] pesquisarContasResumoSimulacaoFaturamento(Integer idImovel, Integer anoMes) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoACobrarDeDoacao(Integer idImovel,
			Integer anoMesReferenciaContabil, Integer idDebitoTipo)
			throws ErroRepositorioException;

	public Conta pesquisarContaPreFaturada(Integer idImovel,
			Integer anoMesReferencia, Integer idDebitoCreditoSituacaoAtual)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasImpressasParaEnvioEmail(
			Integer idLocalidade, SistemaParametro sistemaParametro)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarFaturamentoImediatoAjuste(
			String anoMesReferencia, String faturamentoGrupo, String imovelId,
			String rotaId, int qtd) throws ErroRepositorioException;

	public Integer contarFaturamentoImediatoAjuste(String anoMesReferencia,
			String faturamentoGrupo, String imovelId, String rotaId)
			throws ErroRepositorioException;

	public Collection<Integer> pesquisarImoveisParaGeracaoDaDeclaracaodeQuitacaoDebitos(
			Integer idRota, int quantidadeInicio, int quantidadeMaxima)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasPagasGeracaoDeclaracaoQuitacao(Integer id,
			String ano, Date dataVerificacaoPagamentos)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasParceladasGeracaoDeclaracaoQuitacao(
			Integer id, String ano, Date dataVerificacaoPagamentos)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasCanceladasGeracaoDeclaracaoQuitacao(
			Integer id, String ano, Date dataVerificacaoPagamentos)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasEmCobrancaJudicialGeracaoDeclaracaoQuitacao(
			Integer id, String ano, Date dataVerificacaoPagamentos)
			throws ErroRepositorioException;

	public Collection<Integer> pesquisarAnosParaGerarArquivoTextoDeclaracaoQuitacaoAnualDebitos() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarExtratoQuitacaoParaGeracaoArquivoTexto(
			Integer ano, int empresaId, int quantidadeMaxima,
			Integer idGrupoFaturamento) throws ErroRepositorioException;

	public Collection<ExtratoQuitacaoItem> pesquisarExtratoQuitacaoItensParaGeracaoArquivoTexto(Integer id) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoACobrarDeDoacaoAtivos(Integer idImovel,
			Integer anoMesReferenciaContabil, Integer idDebitoTipo)
			throws ErroRepositorioException;

	public Collection<DebitoTipoVigencia> pesquisarDebitoTipoVigenciaUltimaVigencia(Integer numeroPagina) throws ErroRepositorioException;

	public Integer pesquisarDebitoTipoVigenciaUltimaVigenciaTotal() throws ErroRepositorioException;

	public Collection<DebitoTipoVigencia> pesquisarDebitoTipoVigenciaUltimaVigenciaSelecionados(String[] selecionados) throws ErroRepositorioException;

	public Collection<Empresa> pesquisarEmpresasParaGeraracaoExtrato(Integer idGrupoFaturamento) throws ErroRepositorioException;

	public void atualizarContaProcessoMOBILE(Conta conta) throws ErroRepositorioException;

	public void atualizarContaCategoriaProcessoMOBILE(ContaCategoria contaCategoria) throws ErroRepositorioException;

	public void atualizarContaImpostosDeduzidosProcessoMOBILE(
			ContaImpostosDeduzidos contaImpostosDeduzidos)
			throws ErroRepositorioException;

	public void atualizarMovimentoContaPrefaturadaProcessoMOBILE(
			MovimentoContaPrefaturada movimentoContaPrefaturada)
			throws ErroRepositorioException;

	public void zerarValoresContaPassarDebitoCreditoSituacaoAtualPreFaturadaMOBILE(Conta conta) throws ErroRepositorioException;

	public String verificarExistenciaVigenciaDebito(String dataVigenciaInicial,
			String dataVigenciaFinal, Integer idDebitoTipo)
			throws ErroRepositorioException;

	public DebitoTipoVigencia pesquisarDebitoTipoVigenciaPorDebitoTipo(Integer idDebitoTipo) throws ErroRepositorioException;

	public Object[] pesquisarDadosReceitaLiquidaAguaEsgoto(
			Integer anoMesFaturamentoSistemaParametro, Integer idLocalidade,
			Integer id) throws ErroRepositorioException;

	public BigDecimal pesquisarDadosReceitaLiquidaIndireta(
			Integer anoMesFaturamentoSistemaParametro, Integer idLocalidade,
			Integer id) throws ErroRepositorioException;

	public Object[] pesquisarCreditoARealizar(Integer imovelId,
			Integer idCreditoTipo, Integer debitoCreditoSituacaoAtualId,
			Integer anoMesFaturamento) throws ErroRepositorioException;

	public void atualizarValorCreditoARealizar(Integer idCreditoARealizar,
			BigDecimal valorCredito, Integer idDebitoCreditoSituacaoAtual)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void atualizarValorCreditoARealizarCategoria(
			Integer idCreditoARealizar, Collection colecaoCategoria,
			Collection colecaoValorPorCategoria)
			throws ErroRepositorioException;

	public void atualizarValorCreditoRealizado(Integer idCreditoRealizado, BigDecimal valorCredito) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void atualizarValorCreditoRealizadoCategoria(
			Integer idCreditoRealizado, Collection colecaoCategoriasObterValor,
			Collection colecaoCategoriasCalculadasValor)
			throws ErroRepositorioException;

	public Boolean validarVigenciaValorCobrancaServico(ServicoCobrancaValor servicoCobrancaValor) throws ErroRepositorioException;

	public Integer pesquisarIdCreditoRealizadoNitrato(Conta conta) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarParmsDebitoAutomaticoParcelasMaisJurosParcelamento(Integer idConta) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarParmsDebitoAutomaticoHistoricoParcelasMaisJurosParcelamento(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public List pesquisarParmsDebitoCobradoPorTipoSemParcelasEJurosParcelamento(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public List pesquisarParmsDebitoCobradoHistoricoPorTipoSemParcelasEJurosParcelamento(Integer idConta) throws ErroRepositorioException;

	public Object[] pesquisarDebitoCobradoDeParcelamentoMaisJurosParcelamento(Conta conta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoCobradoNaoParcelamentoEJurosParcelamento(Conta conta) throws ErroRepositorioException;

	public void deletaArquivoTextoRoteiroEmpresaDivisao(
			Integer idArquivoTextoRoteiroEmpresa)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarContasEmitidasImpressaoSimultaneaRelatorio(
			int anoMesReferencia, Integer grupoFaturamento,
			Collection esferaPoder) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarResumoContasLocalidadeImpressaoSimultanea(
			Integer idGrupoFaturamento, String anoMes, Integer idFirma)
			throws ErroRepositorioException;

	public Integer pesquisarMesagemExtrato(Integer anoMesReferencia, Integer idImovel) throws ErroRepositorioException;

	public Integer retornaAnoMesFaturamentoGrupoDaRota(Integer idRota) throws ErroRepositorioException;

	public void deletarArquivoTextoRoteiroEmpresa(Integer idArquivoTextoRoteiroEmpresa) throws ErroRepositorioException;

	public boolean verificarImovelEmProcessoDeFaturamento(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelCortadoSemTarifaSocialPorRota(
			Integer idRota, int numeroPaginas, int quantidadeRegistros)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelCortadoSemTarifaSocialPorRotaAlternativa(
			Integer idRota, int numeroPaginas, int quantidadeRegistros)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoACobrarTarifaCortado(Integer idImovel,
			Integer anoMesFaturamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection atualizarValorDebitoDaConta(int idImovel,
			int anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisComInscricaoPedenteParaAtualizacao(
			Integer idLocalidade, int numeroIndice, int quantidadeRegistros)
			throws ErroRepositorioException;

	public void atualizarIndicadorFaturamentoConsumoHistorico(
			Integer idConsumoHistorico, short indicadorFaturamento)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelComDebitoTarifaCortadoPorRota(
			Integer idRota, Integer anoMesFaturamento, int numeroPaginas,
			int quantidadeRegistros) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelComDebitoTarifaCortadoPorRotaAlternativa(
			Integer idRota, Integer anoMesFaturamento, int numeroPaginas,
			int quantidadeRegistros) throws ErroRepositorioException;

	public Conta pesquisarUltimaContaDoImovel(Integer idImovel) throws ErroRepositorioException;

	public void deletarDebitosCobradosCategoriaImoveisCortados(int idImovel, int anoMesReferencia) throws ErroRepositorioException;

	public void deletarDebitosCobradosImoveisCortados(int idImovel, int anoMesReferencia) throws ErroRepositorioException;

	public void deletarResumoFaturamentoSimulacaoDetalheDebito(
			Integer idFaturamentoGrupo, Integer anoMesReferencia, Integer idRota)
			throws ErroRepositorioException;

	public void deletarResumoFaturamentoSimulacaoDetalheCredito(
			Integer idFaturamentoGrupo, Integer anoMesReferencia, Integer idRota)
			throws ErroRepositorioException;

	public Collection<ResumoFaturamentoSimulacaoDebito> pesquisarResumoFaturamentoDebitoSimulacao(
			ResumoFaturamentoSimulacao resumoFaturamentoSimulacao)
			throws ErroRepositorioException;

	public Collection<ResumoFaturamentoSimulacaoCredito> pesquisarResumoFaturamentoCreditoSimulacao(
			ResumoFaturamentoSimulacao resumoFaturamentoSimulacao)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioAnormalidadeConsumoPorAmostragem(
			Integer idGrupoFaturamento, Short codigoRota,
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal,
			Integer referencia, Integer idImovelPerfil,
			Integer numOcorConsecutivas, String indicadorOcorrenciasIguais,
			Integer mediaConsumoInicial, Integer mediaConsumoFinal,
			Collection<Integer> colecaoIdsAnormalidadeConsumo,
			Collection<Integer> colecaoIdsAnormalidadeLeitura,
			Collection<Integer> colecaoIdsAnormalidadeLeituraInformada,
			Integer tipoMedicao, Collection<Integer> colecaoIdsEmpresa,
			Integer numeroQuadraInicial, Integer numeroQuadraFinal,
			Integer idCategoria, Integer limite)
			throws ErroRepositorioException;

	public Integer pesquisarTotalDadosRelatorioAnormalidadeConsumoPorAmostragem(
			Integer idGrupoFaturamento, Short codigoRota,
			Integer idGerenciaRegional, Integer idUnidadeNegocio,
			Integer idLocalidadeInicial, Integer idLocalidadeFinal,
			Integer idSetorComercialInicial, Integer idSetorComercialFinal,
			Integer referencia, Integer idImovelPerfil,
			Integer numOcorConsecutivas, String indicadorOcorrenciasIguais,
			Integer mediaConsumoInicial, Integer mediaConsumoFinal,
			Collection<Integer> colecaoIdsAnormalidadeConsumo,
			Collection<Integer> colecaoIdsAnormalidadeLeitura,
			Collection<Integer> colecaoIdsAnormalidadeLeituraInformada,
			Integer tipoMedicao, Collection<Integer> colecaoIdsEmpresa,
			Integer numeroQuadraInicial, Integer numeroQuadraFinal,
			Integer idCategoria) throws ErroRepositorioException;

	public Integer pesquisarDiferencaQuantidadeMovimentoContaPrefaturadaArquivoTextoRoteiroEmpresa(
			Integer idRota, Integer anoMesFaturamento)
			throws ErroRepositorioException;

	public Object[] pesquisarContaHistoricoDigitada(String idImovel, String referenciaConta) throws ErroRepositorioException;

	public Collection<Integer> pesquisarImoveisJaProcessadosBufferImpressaoSimultanea(Integer idRota) throws ErroRepositorioException;

	public FaturamentoGrupo recuperaGrupoFaturamentoDoImovel(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection verificarExistenciaAutosInfracaoPorOS(Integer idOrdemServico) throws ErroRepositorioException;

	public AutosInfracao pesquisarAutosInfracaoPorOS(Integer idOrdemServico) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisaAutosInfracaoDebitoACobrar(Integer idAutoInfracao) throws ErroRepositorioException;

	public Integer obterQuantidadeAlteracoesVencimentoConta(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisCortados(Integer situacaoAgua,
			Date dataCorte, Integer idLocalidade)
			throws ErroRepositorioException;

	public int pesquisarExisteClienteAssociadoAoImovelEmClienteImovel(
			Integer idImovel, Integer idCliente)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasPrefaturadasParaEnvioEmail(
			SistemaParametro sistemaParametro, Integer idRota)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarAnoImovelEmissao2ViaDeclaracaoAnualQuitacaoDebitos(String idImovel) throws ErroRepositorioException;

	public String pesquisarImoveisConsumoFaturadoReal(Integer idImovel,
			Integer anoMesReferencia, Integer consumoTipo, Integer ligacaoTipo)
			throws ErroRepositorioException;

	public void religarImovelCortado(Integer idImovel,
			Integer idLigacaoAguaSituacao, Date dataReligacaoAgua)
			throws ErroRepositorioException;

	public Object[] obterLeituraAnteriorEAtualConta(Integer idConta) throws ErroRepositorioException;

	public Object[] obterLeituraAnteriorEAtualContaHistorico(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasFichaCompensacaoEmitirCAERN(
			Integer idTipoConta, Integer idEmpresa, Integer anoMesReferencia,
			Integer idFaturamentoGrupo, BigDecimal valorContaFichaComp)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasFichaCompensacaoEmitirOrgaoPublicoCAERN(
			Integer idTipoConta, Integer idEmpresa, Integer anoMesReferencia,
			Integer idFaturamentoGrupo, BigDecimal valorContaFichaComp)
			throws ErroRepositorioException;

	public void prescreverDebitosImoveisPublicosManual(PrescreverDebitosImovelHelper helper) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterDadosPrescricaoDebitosAutomaticos() throws ErroRepositorioException;

	public void prescreverDebitosImoveisPublicosAutomatico(
			Integer anoMesReferencia, Date dataPrescricao,
			Integer usuario, String idsEsferaPoder)
			throws ErroRepositorioException;

	public Integer pesquisarEmitirHistogramaAguaVolumeConsumo(
			FiltrarEmitirHistogramaAguaHelper filtro, Short consumo,
			Subcategoria subcategoria, Short medicao)
			throws ErroRepositorioException;

	public Date pesquisarDataLeituraAtualMovimentoContaPreFaturada(Integer amMovimento, Integer idImovel) throws ErroRepositorioException;

	public int verificarQuantidadeImoveisMovimentoContaPreFaturada(int anoMesReferencia, int idRota) throws ErroRepositorioException;

	public boolean reprocessarImovelImpressaoSimultanea(Integer anoMes,
			Integer idImovel, Short tipoMedicao, Integer leitura,
			Integer idAnormalidade, Short icImpresso)
			throws ErroRepositorioException;

	public Integer pesquisarDebitoCreditoSituacaoAtualDaConta(Integer idImovel, Integer anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection verificarSeExisteClienteConta(Integer idCliente, Collection colecaoContasIds) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasPagasSemDebitoCreditoPago(Integer amreferencia, Integer idGrupo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public List pesquisarValorPrestacaoDebitoCobradoSemreferencia(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasComValorFaixasErradas(Integer amreferencia) throws ErroRepositorioException;

	public Rota pesquisarRotaParaRetificacao(Integer idConta) throws ErroRepositorioException;

	public Integer pesquisarArquivoTextoRoteiroEmpresaNaoFinalizado(Conta conta, Rota rota) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImovelGerarArquivoTextoFaturamentoPorRotaAlternativa(
			Rota rota, int numeroPaginas, int quantidadeRegistros,
			SistemaParametro sistemaParametro, Integer idImovelCondominio)
			throws ErroRepositorioException;

	public Object[] consultarConsumoCadastrado(Integer idImovel) throws ErroRepositorioException;

	public Collection<Integer> consultarMatriculasAssociadas(Integer idConsumoTarifa, Integer idImovel) throws ErroRepositorioException;

	public Integer pesquisarEmitirHistogramaEsgotoVolumeConsumo(
			FiltrarEmitirHistogramaEsgotoHelper filtro, Short consumo,
			Subcategoria subcategoria, Short medicao)
			throws ErroRepositorioException;

	public Conta pesquisarContaAnoMesImovel(Integer idImovel, int anoMesReferencia) throws ErroRepositorioException;

	public Integer pesquisaQtdeContaRetificadaMotivo(Integer idMotivo, Integer idImovel, Date dataLimite) throws ErroRepositorioException;

	public Integer pesquisaQtdeContaHistoricoRetificadaMotivo(Integer idMotivo, Integer idImovel, Date dataLimite) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisaTabelaColunaContaMotivoRetificacaoColuna(Integer idMotivo) throws ErroRepositorioException;

	public Integer consultarImovelConsumoHistorico(Integer idImovel) throws ErroRepositorioException;

	public BigDecimal obterValorConta(Conta conta) throws ErroRepositorioException;

	public Integer consultarContaTipodeContaImpressao(Integer idConta) throws ErroRepositorioException;

	public Integer retornaAnoMesGrupoFaturamento(Integer idImovel) throws ErroRepositorioException;

	public Cliente obterClienteConta(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRelatorioDevolucaoPagamentosDuplicidade(
			FiltrarRelatorioDevolucaoPagamentosDuplicidadeHelper helper)
			throws ErroRepositorioException;

	public Collection<CreditoRealizadoHistorico> pesquisarCreditosRealizadoHistorico(
			Integer idImovel, Integer anoMesCobrancaCredito)
			throws ErroRepositorioException;

	public Collection<CreditoRealizado> pesquisarCreditosRealizado(
			Integer idImovel, Integer anoMesCobrancaCredito)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Integer pesquisarQtdeContaNaoPaga(Collection idContas) throws ErroRepositorioException;

	public Integer gerarSequencialContaBoleto() throws ErroRepositorioException;

	public Integer pesquisarSequencialContaBoleto(Integer idConta) throws ErroRepositorioException;

	public Conta pesquisarExistenciaContaPorNumeroBoleto(Integer nnBoleto) throws ErroRepositorioException;

	public Conta pesquisarExistenciaContaPorIdentificadorEValor(Integer idConta, BigDecimal valorPagamento) throws ErroRepositorioException;

	public Conta pesquisarExistenciaContaPorIdentificadorTruncadoEValor(Integer idConta, BigDecimal valorPagamento) throws ErroRepositorioException;

	public ContaHistorico pesquisarExistenciaContaHistoricoPorNumeroBoleto(Integer nnBoleto) throws ErroRepositorioException;

	public ContaHistorico pesquisarExistenciaContaHistoricoPorIdentificadorEValor(
			Integer idConta, BigDecimal valorPagamento)
			throws ErroRepositorioException;

	public ContaHistorico pesquisarExistenciaContaHistoricoPorIdentificadorTruncadoEValor(
			Integer idConta, BigDecimal valorPagamento)
			throws ErroRepositorioException;

	public Object[] pesquisarCreditoARealizar(Integer IdCreditoARealizar, Integer anoMesFaturamento) throws ErroRepositorioException;

	public BigDecimal pesquisarValorCreditosARealizarHistorico(
			Integer idImovel, Integer anoMesCredito)
			throws ErroRepositorioException;

	public BigDecimal pesquisarValorCreditosARealizar(Integer idImovel, Integer anoMesCredito) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarQuantidadeContasAgrupandoPorImovel(
			ComandoEmpresaCobrancaContaHelper comandoEmpresaCobrancaContaHelper)
			throws ErroRepositorioException;

	public void alterarLeituristaMovimentoRoteiroEmpresa(Integer idRota,
			Integer anoMes, Integer idLeituristaNovo)
			throws ErroRepositorioException;

	public void alterarLeituristaMovimentoRoteiroEmpresa(
			Collection<Integer> idsImovel, Integer anoMes,
			Integer idLeituristaNovo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaBraille(Integer anoMesFaturamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterQuantidadeEconomiasContaCategoria(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarGrupoFaturamentoGrupoNaoFaturados(
			Integer anoMesReferenciaFaturamento)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasBrailleEmitir(
			Collection<Integer> idTipoConta, Integer anoMesReferencia,
			Integer anoMesReferenciaFaturamentoAntecipado)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasBrailleClienteResponsavelFichaCompensacao(
			Integer idTipoConta, Integer anoMesReferencia,
			Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasBrailleClienteResponsavelNaoFichaCompensacao(
			Integer idTipoConta, Integer anoMesReferencia,
			Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasBrailleClienteResponsavel(
			Collection<Integer> idTipoConta, Integer anoMesReferencia,
			Short indicadorEmissaoExtratoFaturamento,
			Integer anoMesReferenciaFaturamentoAntecipado)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasBrailleFichaCompensacao(
			Integer idTipoConta, Integer anoMesReferencia,
			Integer anoMesReferenciaFaturamentoAntecipado)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasBrailleNaoFichaCompensacao(
			Integer idTipoConta, Integer anoMesReferencia,
			Integer anoMesReferenciaFaturamentoAntecipado)
			throws ErroRepositorioException;

	public Conta obterObjetoConta(Integer idConta) throws ErroRepositorioException;

	public boolean pesquisarContaDoImovelDiferentePreFaturada(Integer idImovel, Integer anoMesReferencia) throws ErroRepositorioException;

	public BigDecimal pesquisarValorContaComSituacaoAtual(Integer idImovel, Integer anoMesReferencia) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarQuantidadeContasComandoAgrupandoPorImovel(
			MovimentarOrdemServicoEmitirOSHelper movimentarOrdemServicoEmitirOSHelper)
			throws ErroRepositorioException;

	public Object[] pesquisarQuantidadeContasComando(
			MovimentarOrdemServicoEmitirOSHelper movimentarOrdemServicoEmitirOSHelper)
			throws ErroRepositorioException;

	public Collection<Integer[]> pesquisarOSComandoSelecionado(
			MovimentarOrdemServicoEmitirOSHelper movimentarOrdemServicoEmitirOSHelper)
			throws ErroRepositorioException;

	public Integer pesquisarAnoMesReferenciaMenorAnoMesReferenciaFaturamentoGrupo(int anoMesReferenciaInformado) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarConsumoTarifaVigenciaImovel(Integer idImovel, Date dataFaturamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterColecaoGrupoFaturamento() throws ErroRepositorioException;

	public Integer pesquisarFaturamentoGrupoImovel(Integer idImovel) throws ErroRepositorioException;

	public BigDecimal diferencaValorAguaCanceladaRetificacao(int anoMesReferencia, int idLocalidade, int idCategoria) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaCategoriaResumo(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoCobradoCategoriaResumo(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCreditoRealizadoCategoriaResumo(Integer idConta) throws ErroRepositorioException;

	public Integer obterQuantidadeCreditosRealizados(Integer idConta) throws ErroRepositorioException;

	public Integer obterQuantidadeDebitoCobrados(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarGuiaPagamentoCategoriaResumo(Integer idGuiaPagamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoACobrarCategoriaResumo(Integer idDebitoACobrar) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCreditoARealizarCategoriaResumo(Integer idCreditoARealizar) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeEconomiasCreditoARealizar(Integer idCreditoARealizar) throws ErroRepositorioException;

	public BigDecimal diferencaValorEsgotoCanceladaRetificacao(
			int anoMesReferencia, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;

	public BigDecimal diferencaValorEsgotoRetificada(int anoMesReferencia, int idLocalidade, int idCategoria) throws ErroRepositorioException;

	public MovimentoContaPrefaturada pesquisarMovimentoContaPrefaturadaPorIdConta(
			Integer idConta, Integer idMedicaoTipo)
			throws ErroRepositorioException;

	public void atualizarMedicaoHistoricoMovimentoCelular(MovimentoContaPrefaturada movimentoContaPrefaturada) throws ErroRepositorioException;

	public void atualizarConsumoHistoricoMovimentoCelular(
			MovimentoContaPrefaturada movimentoContaPrefaturada,
			Integer consumo, Integer idConsumoHistoricoImovelCondominio,
			Integer consumoImovelVinculadosCondominio)
			throws ErroRepositorioException;

	public Collection<Object[]> pesquisarTarifaSocialOuTarifaMinima(Integer idTarifa, Integer idCategoria)	throws ErroRepositorioException;

	public BigDecimal diferencaValorAguaRetificada(int anoMesReferencia, int idLocalidade, int idCategoria) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarTarifaNormal(Integer idTarifa, Integer idCategoria) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarEstruturaTarifariaChafarizPublico() throws ErroRepositorioException;

	public void colocarRevisaoDebitoACobrar(DebitoACobrar debitoACobrar) throws ErroRepositorioException;

	public void retirarDebitoACobrarRevisao(DebitoACobrar debitoACobrar) throws ErroRepositorioException;

	public DebitoACobrar obterDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException;

	public void atualizarIndicadorContaHistorico(Integer idFaturaItem) throws ErroRepositorioException;

	public void deletarResumoFaturamento(Integer anoMesReferencia) throws ErroRepositorioException;

	public DebitoTipo obterDebitoTipoCodigoConstante(Integer codigoConstante) throws ErroRepositorioException;

	public BigDecimal obterPercentualColetaEsgotoImovel(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioAutoInfracaoPendentes(Integer grupo, Integer funcionario) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarItensParcelamentosNivel1(Integer idDebACobrar) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarItensParcelamentosNivel2(Integer idDebACobrar) throws ErroRepositorioException;

	public Object[] pesquisarContaOuContaHistoricoDigitada(String idImovel, String referenciaConta) throws ErroRepositorioException;

	public Integer pesquisarMovimentoContaPrefaturadaArquivoTextoFaturamento(
			Integer idImovelMacro, Integer anoMesReferencia)
			throws ErroRepositorioException;

	public Collection<Integer> pesquisarImovelNumeroDeOcorrenciasConsecultivasAnormalidades(
			Integer idAnormalidade, Integer qtdAnormalidades,
			Integer referenciaFaturamento, Integer grupofaturamento,
			Integer idRota) throws ErroRepositorioException;

	public LeituraAnormalidade obterNumeroMesesLeituraSuspensaLeituraAnormalidade(Integer idLeituraAnormalidade) throws ErroRepositorioException;

	public Collection<Integer> pesquisarImovelComConsumoRealNaoSuperiorA10(
			Integer qtdConsumoRealNaoSuperiorA10,
			Integer referenciaFaturamento, Integer grupofaturamento,
			Integer idRota, Integer numeroMesesReinicioSitEspFaturamento)
			throws ErroRepositorioException;

	public FaturamentoSituacaoHistorico pesquisarFaturamentoSituacaoHistorico(Integer idImovel) throws ErroRepositorioException;

	public void atualizarFaturamentoSituacaoHistorico(FaturamentoSituacaoHistorico faturamentoSituacaoHistorico) throws ErroRepositorioException;

	public String[] obterContaMensagemImovel(Integer imovelId, Integer amRef) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterImoveisComContaPF(Integer anoMesReferencia, Rota rota) throws ErroRepositorioException;

	public Integer obterQtdImoveisRotaDivididaComContaPF(Integer anoMesReferencia, List<Integer> imoveisIds) throws ErroRepositorioException;

	public Integer pesquisarConsumoAguaImovel(Integer idImovel, Integer anoMesReferencia) throws ErroRepositorioException;

	public Integer pesquisarSubcategoriaImovel(Integer idImovel) throws ErroRepositorioException;

	public CreditoRealizado pesquisarCreditoRealizadoBonusSocial(Integer idConta) throws ErroRepositorioException;

	public CreditoARealizar pesquisarCreditoARealizarBonusSocial(Integer idImovel, Integer anoMesReferencia) throws ErroRepositorioException;

	public void excluirCreditoRealizadoBonusSocial(Integer idCreditoRealizado) throws ErroRepositorioException;

	public void excluirCreditoARealizarBonusSocial(Integer idCreditoARealizar) throws ErroRepositorioException;

	public boolean verificaImovelPorRotasComContaEntregaEmOutroEndereco(Rota rota, Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterImoveisComConta(Integer anoMesReferencia, Rota rota) throws ErroRepositorioException;

	public Conta obterContaImovel(Integer idImovel, Integer anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection buscarCreditoARealizarPorImovelValorResidualDiferenteZero(Integer idImovel) throws ErroRepositorioException;

	public BigDecimal acumularValorContaCategoriaPorTipoImpostoResumoFaturamento(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			int idTipoImposto, int idSituacaoAtual, int idSituacaoAnterior)
			throws ErroRepositorioException;

	public List<Integer> obterImoveisMovimentoContaPF(Integer idRota, Integer anoMesFaturamento) throws ErroRepositorioException;

	public List<Integer> obterImoveisFaltandoTransmitir(Integer idRota, Integer anoMesFaturamento) throws ErroRepositorioException;

	public boolean verificaContaCancelada(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioContasRetidas(int anoMesReferencia, Integer idFaturamentoGrupo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioMedicaoFaturamento(
			int anoMesReferencia, Integer idFaturamentoGrupo, Integer idEmpresa)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisaMovimentoContaPF(Integer idImovel, Integer anoMes) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisaIndicadorRetransmissaoMovimentoContaPF(Integer idImovel, Integer anoMes) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterContasNaoImpressas(FaturamentoGrupo grupo, Integer anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisCondominioMacro(Rota rota) throws ErroRepositorioException;

	public List<Integer> pesquisarImoveisCondominioMicro(Integer idImovelCondominio) throws ErroRepositorioException;

	public MovimentoContaPrefaturada obterMovimentoImovel(Integer idImovel, Integer anoMesReferencia) throws ErroRepositorioException;

	public Conta pesquisarContaParaPrescricao(Integer idConta) throws ErroRepositorioException;

	public ExtratoQuitacao obterExtratoQuitacaoImovel(Integer idImovel, Integer anoReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoCobradoDeParcelamentoIS(Conta conta) throws ErroRepositorioException;
	
	public BigDecimal acumularValorCreditoARealizarPorOrigemCreditoDuplicidadeAte122012(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer idCategoria, Integer idOrigemCredito,
			Integer idSituacaoAtual, Integer idSituacaoAnterior)
			throws ErroRepositorioException;
	
	public BigDecimal acumularValorCreditoARealizarPorOrigemCreditoDuplicidade(
			int anoMesReferenciaContabil, Integer idLocalidade,
			Integer idCategoria, Integer idOrigemCredito,
			Integer idSituacaoAtual, Integer idSituacaoAnterior)
			throws ErroRepositorioException;
	
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoPorReferenciaContaDuplicidadeAte122012(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;
	
	
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoPorReferenciaContaDuplicidade(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, int idSituacaoAtual,
			int idSituacaoAnterior) throws ErroRepositorioException;
	
	
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoDuplicidadeAte201212(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual)
			throws ErroRepositorioException;
	
	
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoDuplicidade(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem, Integer idSituacaoAtual)
			throws ErroRepositorioException;
	
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoCanceladoPorRetificacaoDuplicidadeAte201212(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem) throws ErroRepositorioException;
	
	
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoCanceladoPorRetificacaoDuplicidade(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem) throws ErroRepositorioException;
	
	
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoRetificadaDuplicidadeAte201212(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem)
			throws ErroRepositorioException;
	
	
	public BigDecimal acumularValorCategoriaCreditoRealizadoCategoriaPorOrigemCreditoRetificadaDuplicidade(
			int anoMesReferencia, int idLocalidade, int idCategoria,
			Integer[] idsCreditoOrigem)
			throws ErroRepositorioException;

	public Object[] pesquisarContasRelatorioBIG(Integer anoMesReferencia,
			Integer idLocalidade) throws ErroRepositorioException;

	public Object[] pesquisarErrosContasRelatorioBIG(
			Integer anoMesReferencia, Date dataInicial, Date dataFinal,
			Integer idLocalidade) throws ErroRepositorioException;

	public Object[] pesquisarInadimplenciaVencidasRelatorioBIG(
			Date dataReferencia, Date dataInicial, Date dataFinal,
			Integer idLocalidade) throws ErroRepositorioException;

	public Object[] pesquisarInadimplenciaEmitidasRelatorioBIG(Date dataInicial,
			Date dataFinal, Integer idLocalidade)
			throws ErroRepositorioException;

	public Object[] pesquisarInadimplenciaVencidasMaior90RelatorioBIG(
			Date dataReferencia, Date dataFinal, Integer idLocalidade)
			throws ErroRepositorioException;

	public Object[] pesquisarInadimplenciaEmitidasMaior90RelatorioBIG(Date dataFinal,
			Integer idLocalidade) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarCreditoARealizarPeloCreditoRealizadoAntigo(
			Integer imovelId, Integer idCreditoTipo, BigDecimal valorCredito,
			Integer debitoCreditoSituacaoAtualId, Integer anoMesFaturamento)
			throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaOuContaHistorico(Collection idsContas, String className) throws ErroRepositorioException;

	public void atualizarVecimentoFaturaClienteResponsavel(Date dataVencimento, String anoMesReferencia) throws ErroRepositorioException;
	
	public Integer countFaturasClienteResponsaveis(String anoMesReferencia) throws ErroRepositorioException;
	
	public Date obterDataVencimentoContasFaturarGrupo(FaturamentoGrupo faturamentoGrupo) throws ErroRepositorioException;
	
	public Collection<IDebitoCobrado> pesquisarDebitosCobradosHistorico(Integer idConta) throws ErroRepositorioException;
	
	public Collection<IDebitoCobradoCategoria> pesquisarDebitosCobradosCategoriaHistorico(Integer idDebitoCobradoHistorico) throws ErroRepositorioException;
	
	public Collection<ICreditoRealizado> pesquisarCreditosRealizadosHistorico(Integer idConta) throws ErroRepositorioException;
	
	public Collection<ICreditoRealizadoCategoria> pesquisarCreditoRealizadoCategoriaHistorico(Integer idCreditoRealizado) throws ErroRepositorioException;
	
	public Collection<IContaImpostosDeduzidos> pesquisarContaImpostosDeduzidosHistorico(Integer idConta) throws ErroRepositorioException;
	
	public Collection<IClienteConta> pesquisarClienteContaHistorico(Integer idConta) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteContaECliente(Integer idConta, String cnpjEmpresa) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioReceitasAFaturarDataLeituraPrevista(Integer idGrupo, Integer anoMes) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioReceitasAFaturarDataLeituraAnterior(Integer idGrupo, Integer anoMes) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioReceitasAFaturarValorAFaturar(Integer idGrupo, Integer anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioReceitasAFaturarValorAFaturarPorGrupo(Integer idGrupo, Integer anoMesReferencia) throws ErroRepositorioException;

	public int pesquisarMaiorAnoMesReferenciaCronogramaGrupoFaturamentoMensal(Integer idGrupo) throws ErroRepositorioException;
	
	public boolean verificarAnoMesReferenciaCronogramaGrupoFaturamentoMensal(Integer idGrupo, Integer referencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarGerarQuantidadeContasImpressaoTermica(Integer referencia, Integer idFaturamentoGrupo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarQuantidadeContasImpressaoTermica(Integer referencia, Integer idFaturamentoGrupo) throws ErroRepositorioException;
	
	public Fatura pesquisarFaturaDeConta(Integer idConta) throws ErroRepositorioException;

	public void concluirFaturamentoConta(Integer id) throws ErroRepositorioException;
	
	public FaturamentoAtividadeCronograma pesquisarFaturamentoAtividadeCronograma(Integer faturamentoGrupoId, Integer faturamentoAtividadeId,
			Integer anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasParaRelatorioAgenciaReguladora(Integer anoMes, Integer idMunicipio) throws ErroRepositorioException;
	
	public BigDecimal acumularValorAguaPorSituacaoContaEReferenciaContabil(int anoMesReferencia,int idLocalidade, int idSituacaoAtual, int idSituacaoAnterior, boolean aPartirNovembro) throws ErroRepositorioException;
	
	public BigDecimal acumularValorEsgotoPorSituacaoContaEReferenciaContabil(int anoMesReferencia,int idLocalidade, int idSituacaoAtual, int idSituacaoAnterior, boolean aPartirNovembro) throws ErroRepositorioException;
	
	public Object[] acumularValorAguaEsgotoPorSituacaoConta(int anoMesReferencia, int idLocalidade,int idSituacaoAtual, int idSituacaoAnterior) throws ErroRepositorioException;
	
	public BigDecimal calcularDiferencaValorAguaCanceladaRetificacao(int anoMesReferencia,int idLocalidade) throws ErroRepositorioException;
	
	public BigDecimal calcularDiferencaValorEsgotoCanceladaRetificacao(int anoMesReferencia,int idLocalidade) throws ErroRepositorioException;
	
	public void excluirLancamentoAgenciaReguladoraPorAnoMesArrecadacaoPorLocalidade(int anoMesReferenciaFaturamento, Integer idLocalidade) throws ErroRepositorioException;
	
	public Collection<ReceitasAFaturarResumo> obterDadosRelatorioSinteticoReceitasAFaturarPorCategoria(Integer anoMes, Integer idCategoria) throws ErroRepositorioException;
	
	public Collection<ReceitasAFaturarResumo> obterDadosRelatorioSinteticoReceitasAFaturar(Integer anoMes) throws ErroRepositorioException;
	
	public List<Conta> obterContasParaGerarMovimentoDebitoAutomatico() throws ErroRepositorioException;
}