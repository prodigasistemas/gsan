package gcom.arrecadacao;

import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.aviso.bean.AvisoBancarioHelper;
import gcom.arrecadacao.aviso.bean.PagamentosDevolucoesHelper;
import gcom.arrecadacao.aviso.bean.ValoresArrecadacaoDevolucaoAvisoBancarioHelper;
import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.bean.ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper;
import gcom.arrecadacao.bean.MovimentoArrecadadoresPorNSAHelper;
import gcom.arrecadacao.bean.PesquisarAnaliseArrecadacaoHelper;
import gcom.arrecadacao.bean.PesquisarAnaliseAvisosBancariosHelper;
import gcom.arrecadacao.bean.PesquisarAvisoBancarioPorContaCorrenteHelper;
import gcom.arrecadacao.debitoautomatico.DebitoAutomatico;
import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoCartaoDebito;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.micromedicao.bean.ConsultarArquivoTextoRoteiroEmpresaHelper;
import gcom.relatorio.arrecadacao.GuiaDevolucaoRelatorioHelper;
import gcom.relatorio.arrecadacao.RelatorioAnaliseArrecadacaoBean;
import gcom.relatorio.arrecadacao.RelatorioAnaliseAvisosBancariosBean;
import gcom.relatorio.arrecadacao.RelatorioAvisoBancarioPorContaCorrenteBean;
import gcom.relatorio.arrecadacao.RelatorioDocumentoNaoAceitosBean;
import gcom.relatorio.arrecadacao.RelatorioTranferenciaPagamentoBean;
import gcom.relatorio.arrecadacao.dto.ResumoCreditosAvisosBancariosDTO;
import gcom.relatorio.arrecadacao.pagamento.GuiaPagamentoRelatorioHelper;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IRepositorioArrecadacao {

	public Integer pesquisarIdRegistroCodigo(String codigoRegistro) throws ErroRepositorioException;

	public Short pesquisarNumeroDiasFloat(Integer codigoBanco, Integer idFormaArrecadacao) throws ErroRepositorioException;

	public AvisoBancario pesquisarAvisoBancario(Integer codigoBanco, Date dataGeracaoArquivo, Date dataPrevistaCredito, Integer idArrecadadorMovimento,
			Integer idFormaArrecadacao) throws ErroRepositorioException;

	public Integer pesquisarExistenciaGuiaPagamento(Imovel imovel, Integer idDebitoTipo, BigDecimal valorPagamento) throws ErroRepositorioException;

	public GuiaPagamento pesquisarExistenciaGuiaPagamento(Imovel imovel, BigDecimal valorPagamento) throws ErroRepositorioException;

	public Integer pesquisarExistenciaGuiaPagamentoCliente(Integer idCliente, Integer idDebitoTipo) throws ErroRepositorioException;

	public Double pesquisarDeducoesAvisoBancario(String codigoAgente, Date dataLancamento, String numeroSequencial) throws ErroRepositorioException;

	public Short pesquisarValorMaximoNumeroSequencial(Date dataLancamento, String idArrecadador) throws ErroRepositorioException;

	public ArrecadadorContrato pesquisarNumeroSequecialArrecadadorContrato(Integer idArrecadadorContrato) throws ErroRepositorioException;

	public Integer pesquisarIdArrecadacaoForma(String codigoArrecadacaoForma) throws ErroRepositorioException;

	public Integer verificarExistenciaAgencia(String codigoAgencia, Integer idBanco) throws ErroRepositorioException;

	public Integer verificarExistenciaBanco(Integer idBanco) throws ErroRepositorioException;

	public Integer pesquisarIdDepositoArrecadacao(Integer codigoBanco, String codigoConvenio) throws ErroRepositorioException;

	public Collection<ArrecadadorMovimento> filtrarMovimentoArrecadadores(FiltroArrecadadorMovimento filtroArrecadadorMovimento)
			throws ErroRepositorioException;

	public BigDecimal obterTotalArrecadacaoAvisoBancarioPorMovimentoArrecadadores(ArrecadadorMovimento arrecadadorMovimento) throws ErroRepositorioException;

	public Integer obterNumeroRegistrosEmOcorrenciaPorMovimentoArrecadadores(ArrecadadorMovimento arrecadadorMovimento, String descricaoOcorrencia)
			throws ErroRepositorioException;

	public Integer obterNumeroRegistrosNaoAceitosPorMovimentoArrecadadores(ArrecadadorMovimento arrecadadorMovimento, Short indicadorAceitacao)
			throws ErroRepositorioException;

	public Collection<AvisoBancario> obterAvisosBancariosPorArrecadadorMovimento(ArrecadadorMovimento arrecadadorMovimento) throws ErroRepositorioException;

	public BigDecimal obterTotalPagamentoPorAvisoBancario(AvisoBancario avisoBancario) throws ErroRepositorioException;

	public BigDecimal obterTotalDevolucaoPorAvisoBancario(AvisoBancario avisoBancario) throws ErroRepositorioException;

	public Collection<ArrecadadorMovimentoItem> consultarItensMovimentoArrecadador(ArrecadadorMovimento arrecadadorMovimento, Integer idImovel,
			Short indicadorAceitacao, String descricaoOcorrencia) throws ErroRepositorioException;

	public Collection<ArrecadadorMovimentoItem> consultarItensMovimentoArrecadador(ArrecadadorMovimento arrecadadorMovimento, Integer idImovel,
			Short indicadorAceitacao, String descricaoOcorrencia, String codigoArrecadacaoForma) throws ErroRepositorioException;

	public Collection<Devolucao> pesquisarDevolucao(FiltroDevolucao filtroDevolucao) throws ErroRepositorioException;

	public void excluirDadosDiariosArrecadacaoPorAnoMesArrecadacao(int anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularQuantidadeEValorPagamentoPorAnoMesArrecadacao(int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularQuantidadeEValorDevolucaoPorAnoMesArrecadacao(int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void processarPagamentoConta(Map<Integer, Collection> mapPagamentosProcessados) throws ErroRepositorioException;

	public void atualizarSituacaoPagamento(String[] idsPagamentos, Integer pagamentoSituacao) throws ErroRepositorioException;

	public Collection<Banco> pesquisaBancosDebitoAutomatico() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisaDebitoAutomaticoMovimento(Collection colecaoFaturamentoGrupo, Integer anoMesReferenciaFaturamento, Collection colecaoidsBanco)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarAvisoBancarioAbertoFechado(AvisoBancarioHelper avisoBancarioHelper) throws ErroRepositorioException;

	public Object[] pesquisaCamposArrecadadorContrato(Integer idBanco) throws ErroRepositorioException;

	public Agencia pesquisaAgenciaPorBanco(Integer idBanco) throws ErroRepositorioException;

	public void atualizarNumeroSequencialArrecadadorContrato(Integer idArrecadadorContrato, Integer numeroSequencialArquivo) throws ErroRepositorioException;

	public String pesquisarEmailArrecadadorContrato(Short codigoBanco) throws ErroRepositorioException;

	public void atualizarValorExcedentePagamento(Pagamento pagamento) throws ErroRepositorioException;

	public void atualizarValorExcedentePagamento(Collection<Pagamento> colecaoPagamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void processarPagamentoGuiaPagamento(Map<Integer, Collection> mapPagamentosProcessados) throws ErroRepositorioException;

	public Collection<GuiaPagamento> selecionarGuiaPagamentoPelaLocalidadeImovelClienteDebitoTipo(Imovel imovel, Cliente cliente, DebitoTipo debitoTipo,
			Integer anoMesFaturamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void processarPagamentoDebitoACobrar(Map<Integer, Collection> mapPagamentosProcessados) throws ErroRepositorioException;

	public Collection<DebitoACobrar> selecionarDebitoACobrarPelaLocalidadeImovelDebitoTipo(Imovel imovel, DebitoTipo debitoTipo, Integer anoMesFaturamento)
			throws ErroRepositorioException;

	public void atualizaValorArrecadacaoAvisoBancaraio(BigDecimal valor, Integer codigoAvisoBancario) throws ErroRepositorioException;

	public Collection<GuiaDevolucao> pesquisarGuiaDevolucaoRelatorio(FiltroGuiaDevolucao filtroGuiaDevolucao) throws ErroRepositorioException;

	public Collection<GuiaDevolucao> pesquisarGuiaDevolucao(FiltroGuiaDevolucao filtroGuiaDevolucao, Integer numeroPagina) throws ErroRepositorioException;

	public Integer pesquisarGuiaDevolucaoCount(FiltroGuiaDevolucao filtroGuiaDevolucao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoArrecadacaoRelatorioPorEstado(int anoMesReferencia, boolean estadoMunicipio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoArrecadacaoRelatorioPorEstadoPorGerenciaRegional(int anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoArrecadacaoRelatorioPorEstadoPorLocalidade(int anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoArrecadacaoRelatorioPorEstadoPorMunicipio(int anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoArrecadacaoRelatorioPorMunicipio(int anoMesReferencia, Integer idMunicipio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoArrecadacaoRelatorioPorGerenciaRegional(int anoMesReferencia, Integer gerenciaRegional) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoArrecadacaoRelatorioPorGerenciaRegionalPorLocalidade(int anoMesReferencia, Integer gerenciaRegional)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoArrecadacaoRelatorioPorLocalidade(int anoMesReferencia, Integer localidade) throws ErroRepositorioException;

	public Object[] pesquisarParmsDebitoAutomatico(Integer idImovel) throws ErroRepositorioException;

	public Integer consultarQtdeRegistrosResumoArrecadacaoRelatorio(int anoMesReferencia, Integer localidade, Integer gerenciaRegional, Integer municipio,
			String opcaoTotalizacao) throws ErroRepositorioException;

	public void inserirResumoArrecadacao(Collection<ResumoArrecadacao> colecaoResumoArrecadacao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarResumoArrecadacaoPorAnoMesArrecadacao(Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentosClassificadosContas(Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentosClassificadosGuiasPagamento(Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentosClassificadosDebitoACobrar(Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentosNaoClassificadosMes(Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	public Collection<Devolucao> pesquisarDevolucoesClassificadas(Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	public Collection<Devolucao> pesquisarDevolucoesNaoClassificadasMes(Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentosContasEfetuadosEmMesesAnterioresClassificadosMes(Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentosGuiasPagamentoEfetuadosEmMesesAnterioresClassificadosMes(Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentosDebitoACobrarEfetuadosEmMesesAnterioresClassificadosMes(Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	public Collection<Devolucao> pesquisarDevolucoesEfetuadasEmMesesAnterioresClassificadasNoMes(Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentosNaoClassificadosComBaixaComandada() throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentosNaoClassificados(Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	public Collection<Devolucao> pesquisarDevolucoesNaoClassificadas(Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	public BigDecimal acumularValorAguaPagamentosClassificadosConta(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria)
			throws ErroRepositorioException;

	public BigDecimal acumularValorEsgotoPagamentosClassificadosConta(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria)
			throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaFinanciamentoTipoParcelamentoServicoGrupoParcelamentoIgualJurosCobrados(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorCreditoRealizadoPagamentosClassificadosContaOrigemCreditoValoresCobradosIndevidamente(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idLancamentoItemContabil, Collection<Integer> idsCreditosOrigem)
			throws ErroRepositorioException;

	public BigDecimal acumularValorCreditoRealizadoPagamentosClassificadosContaOrigemCredito(Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria, Integer[] idsCreditosOrigem) throws ErroRepositorioException;

	public BigDecimal acumularValorEntradaParcelamentoPagamentosClassificadosGuiaPagamentoFinanciamentoTipoEntradaParcelamento(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorEntradaParcelamentoPagamentosClassificadosGuiaPagamentoFinanciamentoTipoServico(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idLancamentoItemContabil, Integer idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorQueFaltaSerCobradoPagamentosClassificadosDebitoACobrar(Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, Integer idCategoria) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDevolucoesClassificadasSituacaoAtualDevolucaoClassificada(Integer anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDevolucoesClassificadasSituacaoAtualDevolucaoOutrosValores(Integer anoMesReferenciaArrecadacao, Integer idLocalidade,
			Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public BigDecimal acumularValorAguaPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMes(Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorEsgotoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMes(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoParcelamentoServicoGrupoParcelamentoIgualJurosCobrados(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorCreditoRealizadoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesOrigemCreditoValoresCobradosIndevidamente(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public BigDecimal acumularValorEntradaParcelamentoPagamentosGuiaPagamentoEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoEntradaParcelamento(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorEntradaParcelamentoPagamentosGuiaPagamentoEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoServico(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idLancamentoItemContabil, Integer idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorQueFaltaSerCobradoPagamentosDebitoACobrarEfetuadosEmMesesAnteriores(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idLancamentoItemContabil, Integer idCategoria) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDevolucoesEfetuadasEmMesesAnterioresClassificadasNoMesSituacaoAtualDevolucaoClassificada(Integer anoMesReferenciaArrecadacao,
			Integer idLocalidade) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDevolucoesEfetuadasEmMesesAnterioresClassificadasNoMesSituacaoAtualDevolucaoOutrosValores(Integer anoMesReferenciaArrecadacao,
			Integer idLocalidade, Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public BigDecimal acumularValorAgua_EsgotoPagamentosClassificadosNoMes_EfetuadosEmMesesAnterioresContaContabilizadasComoPerdas(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosNoMes_EfetuadosEmMesesAnterioresContaContabilizadaComoPerdasFinanciamentoTipoServico_ParcelamentoAgua_ParcelamentoEsgoto_ParcelamentoServico(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorCreditoRealizadoPagamentosClassificadosNoMes_EfetuadosMesesAnterioresContaContabilizadaComoPerdasOrigemCredito_ContasPagasEmDuplicidadeExcesso_ValoresCobradosIndevidamente_DescontosConcedidos(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasImpostosDeduzidosPagamentosClassificadosNoMes_MesesAnterioresContaContabilizadasComoPerdasImpostoTipo_IR_CSLL_COFINS_PISPASEP(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	public Collection<DebitoCobrado> pesquisarDebitosCobradosContasPagamentosClassificados_PagamentosAnterioresContaClassificadosNoMes(
			Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	public Collection<CreditoRealizado> pesquisarCreditosRealizadosContasPagamentosClassificados_PagamentosAnterioresContaClassificadosNoMes(
			Integer anoMesReferenciaArrecadacao) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPagamentosPorConta(Integer anoMesReferencia) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPagamentosPorConta(Integer anoMesReferencia, Integer idLocalidade, Integer idImovel, Integer anoMesReferenciaPagamento)
			throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoComGuiaInformada(Integer anoMesReferencia) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoComGuiaInformada(Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoSemGuiaInformada(Integer anoMesReferencia) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoSemGuiaInformada(Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarSemDebitoInformada(Integer anoMesReferencia) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarSemDebitoInformada(Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarComDebitoInformado(Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	public Object[] selecionarContaPorImovelAnoMesReferencia(Imovel imovel, Integer anoMesReferenciaPagamento, Integer anoMesFaturamento)
			throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentoImovel(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoClienteConta(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoClienteGuiaPagamento(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoClienteDebitoACobrar(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentoLocalidade(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, Integer numeroPagina, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentoAvisoBancario(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentoMovimentoArrecadador(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarDadosDiarios(int anoMesReferencia, int id, String descricao, int idElo) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarDevolucoesEmDuplicidadeOUExcesso(Integer anoMesReferencia) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarDevolucoesEmDuplicidadeOUExcesso(Integer anoMesReferencia, Integer idLocalidade) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarDevolucoesCobradasIndevidamente(Integer anoMesReferencia) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarDevolucoesCobradasIndevidamente(Integer anoMesReferencia, Integer idLocalidade) throws ErroRepositorioException;

	public Collection<Pagamento> selecionarPagamentosNaoClassificadosConta(Imovel imovel, Integer anoMesReferenciaDevolucao) throws ErroRepositorioException;

	public void atualizarSituacaoDevolucao(String[] idsDevolucao, Integer devolucaoSituacao) throws ErroRepositorioException;

	public void atualizarSituacaoAnteriorPagamento(String[] idsPagamentos, Integer pagamentoSituacao) throws ErroRepositorioException;

	public Collection<Pagamento> selecionarPagamentosNaoClassificadosGuiaPagamentoDebitoACobrar(Imovel imovel, Cliente cliente, DebitoTipo debitoTipo)
			throws ErroRepositorioException;

	public Integer filtrarAvisoBancarioAbertoFechadoCount(AvisoBancarioHelper avisoBancarioHelper, AvisoBancarioHelper avisoBancarioHelperNovo)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarAvisoBancarioAbertoFechadoParaPaginacao(AvisoBancarioHelper avisoBancarioHelper, Integer numeroPagina)
			throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentoCliente(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, Integer numeroPagina, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoClienteCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentoAvisoBancarioParaPaginacao(String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, Integer numeroPagina,
			String valorPagamentoInicial, String valorPagamentoFinal) throws ErroRepositorioException;

	public Integer pesquisarPagamentoAvisoBancarioCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	public Integer pesquisarPagamentoImovelCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentoImovelParaPaginacao(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, Integer numeroPagina, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentoImovelRelatorio(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoClienteRelatorio(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentoAvisoBancarioRelatorio(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentoMovimentoArrecadadorRelatorio(String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentoLocalidadeRelatorio(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	public Integer pesquisarPagamentoMovimentoArrecadadorCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	public Integer pesquisarPagamentoLocalidadeCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	public Integer verificarExistenciaGuiaDevolucao(Integer idGuiaDevolucao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarAvisoBancarioRelatorio(AvisoBancarioHelper avisoBancarioHelper) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarAvisoDeducoesAvisoBancarioRelatorio(Integer idAvisoBancario) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarAvisoAcertosAvisoBancarioRelatorio(Integer idAvisoBancario) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarDadosDiariosArrecadacao(String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String idLocalidade,
			String idGerenciaRegional, String idArrecadador, String idElo, String[] idsImovelPerfil, String[] idsLigacaoAgua, String[] idsLigacaoEsgoto,
			String[] idsDocumentosTipos, String[] idsCategoria, String[] idsEsferaPoder) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarDevolucaoDadosDiarios(String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String idLocalidade,
			String idGerenciaRegional, String idArrecadador, String idElo, String[] idsImovelPerfil, String[] idsLigacaoAgua, String[] idsLigacaoEsgoto,
			String[] idsDocumentosTipos, String[] idsCategoria, String[] idsEsferaPoder) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarDadosDiariosArrecadacaoValoresDiarios(String idGerenciaRegional) throws ErroRepositorioException;

	public Collection<ArrecadadorMovimento> retornarColecaoMovimentoArrecadadores(FiltroArrecadadorMovimento filtro, Integer numeroPagina)
			throws ErroRepositorioException;

	public BigDecimal pesquisarSomatorioAvisoAcerto(Integer indicadorCreditoDebito, Integer idAviso, Integer indicadorArrecadacaoDevolucao)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarAvisoBancarioAbertoFechadoFinal(AvisoBancarioHelper avisoBancarioHelper) throws ErroRepositorioException;

	public GuiaPagamentoRelatorioHelper pesquisarGuiaPagamentoRelatorio(Integer idGuiaPagamento) throws ErroRepositorioException;

	public String pesquisarNomeClienteGuiaPagamentoRelatorio(Integer idGuiaPagamento) throws ErroRepositorioException;

	public GuiaDevolucaoRelatorioHelper pesquisarGuiaDevolucaoRelatorio(Integer idGuiaDevolucao) throws ErroRepositorioException;

	public Object[] pesquisarClienteImovel(Integer idImovel) throws ErroRepositorioException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoImovel(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos) throws ErroRepositorioException;

	public Integer pesquisarPagamentoHistoricoImovelCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos) throws ErroRepositorioException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoImovelParaPaginacao(String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, Integer numeroPagina)
			throws ErroRepositorioException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteConta(String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteGuiaPagamento(String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteDebitoACobrar(String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	public Integer pesquisarPagamentoHistoricoClienteCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos) throws ErroRepositorioException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoCliente(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, Integer numeroPagina) throws ErroRepositorioException;

	public Integer pesquisarPagamentoHistoricoLocalidadeCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos) throws ErroRepositorioException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoLocalidade(String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, Integer numeroPagina)
			throws ErroRepositorioException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoAvisoBancario(String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	public Integer pesquisarPagamentoHistoricoAvisoBancarioCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos) throws ErroRepositorioException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoAvisoBancarioParaPaginacao(String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, Integer numeroPagina)
			throws ErroRepositorioException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoMovimentoArrecadador(String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ErroRepositorioException;

	public Collection<DevolucaoHistorico> pesquisarDevolucaoHistorico(FiltroDevolucaoHistorico filtroDevolucaoHistorico) throws ErroRepositorioException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoLocalidadeRelatorio(String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarDadosDiarios(int idGerenciaRegional, int idLocalidade, int idElo) throws ErroRepositorioException;

	public Object[] pesquisarContaAgenciaSistemaParametro() throws ErroRepositorioException;

	public Integer pesquisarIdLancamentoItemContabil(Integer idCreditoTipo) throws ErroRepositorioException;

	public String pesquisarDescricaoDebitoAutomatico(Integer codigoRetorno) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarAnoMesArrecadacaoMenorIgualAtual(Integer anoMesArrecadacaoAtual) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarMovimentoArrecadadorParaPaginacao(String codigoBanco, String codigoRemessa, String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio, Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim,
			String descricaoOcorrencia, String indicadorAceitacao, Integer numeroPagina, String indicadorAbertoFechado) throws ErroRepositorioException;

	public Integer filtrarMovimentoArrecadadoresCount(String codigoBanco, String codigoRemessa, String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio, Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim,
			String descricaoOcorrencia, String indicadorAceitacao, String indicadorAbertoFechado) throws ErroRepositorioException;

	public BigDecimal recuperaValorPagamentoArrecadadorMovimentoItem(Integer idArrecadadorMovimentoItem) throws ErroRepositorioException;

	public String recuperaDescricaoArrecadacaoForma(String codigoArrecadacaoForma) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void atualizarSituacaoPagamento(Collection colecaoIdsPagamentos, Integer pagamentoSituacao) throws ErroRepositorioException;

	public void atualizarSituacaoEValorExcedentePagamento(Collection<Pagamento> colecaoPagamento, Integer pagamentoSituacao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarAnoMesArrecadacaoMenorIgualAtualPorImovel(Integer anoMesArrecadacaoAtual, Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarIdsImovelPorLocalidade(Integer idLocalidade, Integer numeroPaginas, Integer quantidadeRegistros) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsLocalidadeComPagamentosOuDevolucoes() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarAnoMesReferenciaPagamentoParaImovel(Integer anoMesArrecadacaoAtual, Integer idImovel, Integer idDocumentoTipo)
			throws ErroRepositorioException;

	public Integer pesquisarEsferaPoderClienteResponsavelPeloImovel(Integer idImovel) throws ErroRepositorioException;

	public void atualizarSituacaoPagamento(Integer pagamentoSituacao, Collection<Pagamento> colecaoPagamentos) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoLocalidadeAmbosRelatorio(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	public Imovel pesquisarImovelPagamento(Integer idImovel) throws ErroRepositorioException;

	public Cliente pesquisarClientePagamento(Integer idCliente) throws ErroRepositorioException;

	public ClienteEndereco pesquisarClienteEnderecoPagamento(Integer idCliente) throws ErroRepositorioException;

	public IClienteFone pesquisarClienteFonePagamento(Integer idCliente) throws ErroRepositorioException;

	public Collection<ClienteImovel> pesquisarClientesImoveisPagamento(Integer idImovel) throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentoMovimentoArrecadadorParaPaginacao(String idImovel, String idCliente, String idTipoRelacao,
			String localidadeInicial, String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial,
			String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, Integer numeroPagina,
			String valorPagamentoInicial, String valorPagamentoFinal) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarResumoArrecadacaoPorAnoMesArrecadacao(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsCategorias() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosLancamentosItemContabil() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarAcertosAvisoBancario(Integer idAvisoBancario, Integer indicadorArrecadacaoDevolucao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoAvisoBancario(Integer idAvisoBancario) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDevolucaoAvisoBancario(Integer idAvisoBancario) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDeducoesAvisoBancario(Integer idAvisoBancario) throws ErroRepositorioException;

	public Object[] pesquisarValorAcertosAvisoBancario(Integer idAvisoBancario) throws ErroRepositorioException;

	public BigDecimal pesquisarSomatorioDeducoesAvisoBancario(Integer idAvisoBancario) throws ErroRepositorioException;

	public Object[] pesquisarAvisoBancario(Integer idAvisoBancario) throws ErroRepositorioException;

	public Object[] pesquisarAvisoBancarioAvisoAcertos(Integer idAvisoBancario) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasImpostosDeduzidosPagamentosClassificadosContaPorTipoImposto(Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idTipoImposto) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentosNaoClassificadosMesPorSituacaoAnterior(Integer anoMesReferenciaArrecadacao, Integer idLocalidade,
			Integer idSituacaoAnterior) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDevolucoesNaoClassificadasMesPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade,
			Integer idDevolucaoSituacaoAtual) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasImpostosDeduzidosPagamentosContasEfetuadosEmMesesAnterioresClassificadosMesPorTipoImposto(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idImpostoTipo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentosNaoClassificadosComBaixaComandadaPorSituacaoAnterior(Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idPagamentoSituacaoAnterior) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentosNaoClassificadosMesEMesesAnterioresPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade,
			Integer idPagamentoSituacaoAtual) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDevolucoesNaoClassificadasMesEAnterioresPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade,
			Integer idDevolucaoSituacaoAtual) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentosNaoClassificadosMesPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idSituacaoAtual)
			throws ErroRepositorioException;

	public void excluirDadosDiariosArrecadacaoPorAnoMesArrecadacaoPorLocalidade(int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarMovimentoArrecadadorParaRelatorio(String codigoBanco, String codigoRemessa, String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio, Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim,
			String descricaoOcorrencia, String indicadorAceitacao, String indicadorAbertoFechado) throws ErroRepositorioException;

	public Integer filtrarMovimentoArrecadadoresRelatorioCount(String codigoBanco, String codigoRemessa, String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio, Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim,
			String descricaoOcorrencia, String indicadorAceitacao, String indicadorAbertoFechado) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarGuiaPagamentoCategoria(Integer idGuiaPagamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void atualizarIndicadorGuiaPagamentoNoHistorico(Collection idsGuiasPagamento) throws ErroRepositorioException;

	public void atualizarAnoMesArrecadacao(int anoMesArrecadacaoAtual, int anoMesArrecadacaoNovo) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasDePagamentosClassificadosContaEPagamentosAnterioresContaClassificadosNoMes(Integer anoMesReferenciaArrecadacao,
			Integer idLocalidade, Integer numeroIndice, Integer quantidadeRegistros, Integer idSetorComercial) throws ErroRepositorioException;

	public Collection<Integer> pesquisarPagamentosClassificadosOuValorExcedenteBaixado(Integer anoMesReferenciaArrecadacao, Integer idLocalidade,
			int numeroIndice, int quantidadeRegistros) throws ErroRepositorioException;

	public Collection<Devolucao> pesquisarDevolucoesClassificadasPorLocalidade(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer numeroIndice,
			Integer quantidadeRegistros) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoPorAvisoBancario(Integer idAvisoBancario) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException;

	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException;

	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo) throws ErroRepositorioException;

	public Object[] pesquisarClienteDeGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException;

	public Object[] pesquisarImovelDeClienteGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException;

	public Object[] pesquisarClienteDeClienteImovel(Integer idGuiaPagamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoImovelAmbosRelatorio(String idImovel) throws ErroRepositorioException;

	public void removerGuiaPagamentoPagamento(Integer idPagamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarAnoMesArrecadacaoPagamentoMaiorIgualAnoMesArrecadacaoAtual(Integer anoMesArrecadacaoAtual, Integer idLocalidade)
			throws ErroRepositorioException;

	public Integer pesquisarQuantidadePagamentosPorDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException;

	public void atualizarSituacaoPagamentoClassificado(Collection<Integer> colecaoIdsPagamentos) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void processarPagamentoValorNaoConfereConta(Map<Integer, Collection> mapPagamentosValorNaoConfere) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void processarPagamentoValorNaoConfereGuiaPagamento(Map<Integer, Collection> mapPagamentosValorNaoConfere) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void processarPagamentoValorNaoConfereDebitoACobrar(Map<Integer, Collection> mapPagamentosValorNaoConfere) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void processarPagamentoValorNaoConfereIdentificadorDocumentoIgualANulo(Collection colecaoPagamentos) throws ErroRepositorioException;

	public void apagarIdGuiaPagamentoPagamentos(Integer idGuiaPagamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarMovimentoArrecadadoresRelatorio(Integer mesAnoReferencia, Integer idArrecadador, Integer idFormaArrecadacao,
			Date dataPagamentoInicial, Date dataPagamentoFinal) throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaFinanciamentoTipoDoacoes(Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria, Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoParcelamentoDoacoes(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public Collection<GuiaPagamento> pesquisarGuiasPagamentoDePagamentosClassificadosGuiasPagamentoEPagamentosAnterioresGuiaPagamentoClassificadosNoMes(
			Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException;

	public Collection<Pagamento> pesquisarPagamentosClassificadosOuValorExcedenteBaixado(Integer anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoDoacoes(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public Collection<Devolucao> pesquisarDevolucoesClassificadasPorLocalidade(Integer anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

<<<<<<< HEAD
    

    /**
     * [UC0276] - Encerrar Arrecadação do Mês
     * 
     * Verifica se já existe resumo da arrecadação para o ano/mês de referência
     * da arrecadação
     * 
     * [FS0003] - Verificar a existência do resumo da arrecadação
     * 
     * @author Pedro Alexandre
     * @date 16/05/2006
     *
     * @param anoMesReferenciaArrecadacao
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection pesquisarResumoArrecadacaoPorAnoMesArrecadacao(Integer anoMesReferenciaArrecadacao,Integer idLocalidade) throws ErroRepositorioException ;

    /**
     * Pesquisa uma coleção de ids das categorias cadastradas
     *
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * @author Pedro Alexandre
     * @date 15/12/2006
     *
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Integer> pesquisarIdsCategorias() throws ErroRepositorioException ;
    
    /**
     * Pesquisa uma coleção de ids dos lançamentos de itens contábeis cadastrados
     *
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * @author Pedro Alexandre
     * @date 15/12/2006
     *
     * @return
     * @throws ErroRepositorioException
     */
    public Collection pesquisarDadosLancamentosItemContabil() throws ErroRepositorioException ;

    /**
     * pesquisa a lista dos acertos da Arrecadação/Devolucao do Aviso Bancario
     * 
     *[UC0268] - Apresentar Análise do Aviso Bancário
     * 
     * @author Vivianne Sousa
     * @date 13/12/2006
     * 
     * @param idAvisoBancario
     * @return
     * @throws ErroRepositorioException
     */
     public Collection pesquisarAcertosAvisoBancario(Integer idAvisoBancario,
   		  Integer indicadorArrecadacaoDevolucao) 
     	throws ErroRepositorioException;
     
    /**
     * O sistema seleciona a lista de pagamentos associados ao aviso bancário 
     * a partir da tabela PAGAMENTO com AVBC_ID=AVBC_ID da tabela AVISO_BANCARIO 
     * classificados por LOCA_ID ,IMOV_ID e PGMT_AMREFERENCIAPAGAMENTO
     * 
     * [UC0268] - Apresentar Análise do Aviso Bancário
     * 
     * @author Vivianne Sousa
     * @date 15/12/2006
     * 
     * @return Collection
     * @throws ErroRepositorioException
     */
    public Collection pesquisarPagamentoAvisoBancario(Integer idAvisoBancario) 
    	throws ErroRepositorioException;
    
    /**
     * O sistema seleciona a lista de desvoluções associados ao aviso bancário 
     * a partir da tabela DEVOLUCAO com AVBC_ID=AVBC_ID da tabela AVISO_BANCARIO 
     * 
     * [UC0268] - Apresentar Análise do Aviso Bancário
     * 
     * @author Vivianne Sousa
     * @date 15/12/2006
     * 
     * @return Collection
     * @throws ErroRepositorioException
     */
    public Collection pesquisarDevolucaoAvisoBancario(Integer idAvisoBancario) 
    	throws ErroRepositorioException;
    
    /**
     * Pesquisa os avisos deduções de um aviso bancário para o relatório através
     * do id do aviso bancário
     * 
     *[UC0268] - Apresentar Análise do Aviso Bancário
     * 
     * @author Vivianne Sousa
     * @date 13/12/2006
     * 
     * @param idAvisoBancario
     * @return
     * @throws ErroRepositorioException
     */
     public Collection pesquisarDeducoesAvisoBancario(Integer idAvisoBancario) 
     	throws ErroRepositorioException;
     
     /**
      *[UC0268] - Apresentar Análise do Aviso Bancário
      * 
      * @author Vivianne Sousa
      * @date 13/12/2006
      * 
      * @param idAvisoBancario
      * @return
      * @throws ErroRepositorioException
      */
     public Object[] pesquisarValorAcertosAvisoBancario(Integer idAvisoBancario)
     	throws ErroRepositorioException ;
     
     /**
      * somatorio do valor das deduções existentes para o aviso bancario
      * 
      * [UC0268] - Apresentar Análise do Aviso Bancário
      * 
      * @author Vivianne Sousa
      * @date 13/12/2006
      * 
      * @param idAvisoBancario
      * @return
      * @throws ErroRepositorioException
      */
     public BigDecimal pesquisarSomatorioDeducoesAvisoBancario(Integer idAvisoBancario) 
     	throws ErroRepositorioException ;
 	
     /**
      * [UC0268] - Apresentar Análise do Aviso Bancário
      * 
      * @author Vivianne Sousa
      * @date 13/12/2006
      * 
      * @param idAvisoBancario
      * @return
      * @throws ErroRepositorioException
      */
     public Object[] pesquisarAvisoBancario(Integer idAvisoBancario)
     	throws ErroRepositorioException;
     
     /**
      * Pesquisa alguns valores necessarios para 
      * obter a situação do aviso bancario, se aberto ou fechado
      * 
      * [UC0254] - Efetuar Análise do Movimento dos Arrecadadores
      * 
      * @author Vivianne Sousa
      * @date 11/12/2006
      * 
      * @param idAvisoBancario
      * @return
      * @throws ErroRepositorioException
      */
     public Object[] pesquisarAvisoBancarioAvisoAcertos(Integer idAvisoBancario)
     	throws ErroRepositorioException;
     
     /**
      * Sequencial do tipo lançamento igual a 1200
       * 
       * [UC0276] - Encerrar Arrecadação do Mês
       * 
       * Pesquisa os daods de ContaImpostosDeduzidos dos pagamentos
       * classificados de contas para acumular o valor do imposto por
       * localidade e categoria e tipo de imposto.
       * 
       * @author Pedro ALexandre
       * @date 15/12/2006
       *
       * @param idLocalidade
       * @param anoMesReferenciaArrecadacao
       * @param idTipoImposto
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarContasImpostosDeduzidosPagamentosClassificadosContaPorTipoImposto(
    		  Integer idLocalidade, 
    		  Integer anoMesReferenciaArrecadacao,
    		  Integer idTipoImposto) throws ErroRepositorioException ;

      /**
       * <Breve descrição sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 18/12/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param idSituacaoAnterior
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarPagamentosNaoClassificadosMesPorSituacaoAnterior(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idSituacaoAnterior) throws ErroRepositorioException ;

      /**
       * <Breve descrição sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 18/12/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param idDevolucaoSituacaoAtual
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarDevolucoesNaoClassificadasMesPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idDevolucaoSituacaoAtual) throws ErroRepositorioException ;

      /**
       * <Breve descrição sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 18/12/2006
       *
       * @param idLocalidade
       * @param anoMesReferenciaArrecadacao
       * @param idImpostoTipo
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarContasImpostosDeduzidosPagamentosContasEfetuadosEmMesesAnterioresClassificadosMesPorTipoImposto(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idImpostoTipo) throws ErroRepositorioException ;

      /**
       * <Breve descrição sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 18/12/2006
       *
       * @param idLocalidade
       * @param anoMesReferenciaArrecadacao
       * @param idPagamentoSituacaoAnterior
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarPagamentosNaoClassificadosComBaixaComandadaPorSituacaoAnterior(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idPagamentoSituacaoAnterior) throws ErroRepositorioException ;

      /**
       * <Breve descrição sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 18/12/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param idPagamentoSituacaoAtual
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarPagamentosNaoClassificadosMesEMesesAnterioresPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idPagamentoSituacaoAtual) throws ErroRepositorioException ;

      /**
       * <Breve descrição sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro ALexandre
       * @date 18/12/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param idDevolucaoSituacaoAtual
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarDevolucoesNaoClassificadasMesEAnterioresPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idDevolucaoSituacaoAtual) throws ErroRepositorioException ;

      /**
       * <Breve descrição sobre o caso de uso>
       *
       * <Identificador e nome do caso de uso>
       *
       * @author Pedro Alexandre
       * @date 16/12/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param idSituacaoAtual
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarPagamentosNaoClassificadosMesPorSituacaoAtual(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idSituacaoAtual) throws ErroRepositorioException ;

      /**
       * Exclui os dados diários da arrecadação do ano/mês da arrecadação corrente por localidade
       * 
       * [UC0301] Gerar Dados Diários da Arrecadação
       * 
       * @author Pedro Alexandre
       * @date 11/04/2006
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @throws ErroRepositorioException
       */
      public void excluirDadosDiariosArrecadacaoPorAnoMesArrecadacaoPorLocalidade(int anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;

      /**
       * [UC0263] Filtrar Movimento dos Arrecadadores
       * 
       * @author Vivianne Sousa
       * @date 30/11/06
       * 
       * @param codigoBanco
       * @param codigoRemessa
       * @param descricaoIdentificacaoServico
       * @param numeroSequencialArquivo
       * @param dataGeracaoInicio
       * @param dataGeracaoFim
       * @param ultimaAlteracaoInicio
       * @param ultimaAlteracaoFim
       * @param descricaoOcorrencia
       * @param indicadorAceitacao
       * @param indicadorAbertoFechado
       * @return
       * @throws ErroRepositorioException
       */
      public Collection filtrarMovimentoArrecadadorParaRelatorio(String codigoBanco, String codigoRemessa, 
    		  String descricaoIdentificacaoServico, String numeroSequencialArquivo, Date dataGeracaoInicio, 
    		  Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim, String descricaoOcorrencia,
    		  String indicadorAceitacao, String indicadorAbertoFechado) throws ErroRepositorioException;
      
      /**
       * [UC0263] Filtrar Movimento dos Arrecadadores
       * 
       * @author Vivianne Sousa
       * @date  04/01/07
       * 
       * @param codigoBanco
       * @param codigoRemessa
       * @param descricaoIdentificacaoServico
       * @param numeroSequencialArquivo
       * @param dataGeracaoInicio
       * @param dataGeracaoFim
       * @param ultimaAlteracaoInicio
       * @param ultimaAlteracaoFim
       * @param descricaoOcorrencia
       * @param indicadorAceitacao
       * @param indicadorAbertoFechado
       * 
       * @throws ControladorException
       */
      public Integer filtrarMovimentoArrecadadoresRelatorioCount(String codigoBanco, String codigoRemessa, 
      		String descricaoIdentificacaoServico, String numeroSequencialArquivo, Date dataGeracaoInicio, 
      		Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim, String descricaoOcorrencia, 
      		String indicadorAceitacao, String indicadorAbertoFechado) throws ErroRepositorioException;
      
      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Pesquisa a coleção de guias de pagamento categoria 
       * para o id da guia informada.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param idGuiaPagamento
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarGuiaPagamentoCategoria(Integer idGuiaPagamento) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Pesquisa a coleção de cliente de guias de pagamento  
       * para o id da guia informada.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param idGuiaPagamento
       * @return
       * @throws ErroRepositorioException
       */
      public Collection pesquisarClienteGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Para cada guia de pagamento transferida para o histórico 
       * atualiza o indicador de que a guia de pagamento está no histórico.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param idsGuiasPagamento
       * @throws ErroRepositorioException
       */
      public void atualizarIndicadorGuiaPagamentoNoHistorico(Collection idsGuiasPagamento) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Atualiza o ano/mês de referência da arrecadação.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param anoMesArrecadacaoAtual
       * @param anoMesArrecadacaoNovo
       * @throws ErroRepositorioException
       */
      public void atualizarAnoMesArrecadacao(int anoMesArrecadacaoAtual, int anoMesArrecadacaoNovo) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Pesquisa as contas correspondentes aos pagamentos classificados de conta 
       * e os pagamentos anteriores de conta classificados no mês.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param numeroIndice
       * @param quantidadeRegistros
       * @return
       * @throws ErroRepositorioException
       */

      public Collection pesquisarContasDePagamentosClassificadosContaEPagamentosAnterioresContaClassificadosNoMes(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer numeroIndice, Integer quantidadeRegistros, Integer idSetorComercial) throws ErroRepositorioException ;


      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Pesquisa as guias de pagamento correspondentes aos pagamentos classificados de guia de 
       * pagamento e aos pagamentos anteriores de guia de pagamento classificados no mês.
       *
       * @author Pedro Alexandre
       * @date 09/01/2007
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param numeroIndice
       * @param quantidadeRegistros
       * @return
       * @throws ErroRepositorioException
       */
     // public Collection<GuiaPagamento> pesquisarGuiasPagamentoDePagamentosClassificadosGuiasPagamentoEPagamentosAnterioresGuiaPagamentoClassificadosNoMes(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer numeroIndice, Integer quantidadeRegistros) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Pesquisar os pagamentos classificados ou com valor excedente baixado e com 
       * valor excedente maior do que zero.
       *
       * @author Pedro Alexandre
       * @date 10/01/2007
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param numeroIndice
       * @param quantidadeRegistros
       * @return
       * @throws ErroRepositorioException
       */
      public Collection<Integer> pesquisarPagamentosClassificadosOuValorExcedenteBaixado(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, int numeroIndice, int quantidadeRegistros) throws ErroRepositorioException ;

      /**
       * [UC0276] Encerrar Arrecadação do Mês
       *
       * Pesquisa as devoluções classificadas para transferir para o histórico.
       *
       * @author Pedro Alexandre
       * @date 10/01/2007
       *
       * @param anoMesReferenciaArrecadacao
       * @param idLocalidade
       * @param numeroIndice
       * @param quantidadeRegistros
       * @return
       * @throws ErroRepositorioException
       */
      public Collection<Devolucao> pesquisarDevolucoesClassificadasPorLocalidade(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer numeroIndice, Integer quantidadeRegistros) throws ErroRepositorioException ;

      /**
       * O sistema seleciona a lista de pagamentos associados ao aviso bancário 
       * a partir da tabela PAGAMENTO com AVBC_ID=AVBC_ID da tabela AVISO_BANCARIO 
       * 
       * @author Vivianne Sousa
       * @date 17/01/2007
       * 
       * @return Collection
       * @throws ErroRepositorioException
       */
    @SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoPorAvisoBancario(Integer idAvisoBancario) throws ErroRepositorioException;
      
  	public GuiaPagamento pesquisarGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException;      
  	
	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, 
			LogradouroBairro logradouroBairroNovo) throws ErroRepositorioException ;
	
	/**
	 * Atualiza logradouroCep de um ou mais imóveis  
	 * 
	 * [UC0] Atualizar Logradouro
	 * 
	 * @author Raphael Rossiter
	 * @date 22/02/2007
	 * 
	 * @param 
	 * @return void
	 */
	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, 
			LogradouroCep logradouroCepNovo) throws ErroRepositorioException ;
	
	/**
     * Pesquisa o cliente da guia de pagamento 
     * através do id da Guia de Pagamento 
     * 
     * @author Vivianne Sousa
     * @date 28/02/2007
     * 
     * @return String
     * @throws ErroRepositorioException
     */

    public Object[] pesquisarClienteDeGuiaPagamento(Integer idGuiaPagamento)
    	throws ErroRepositorioException ;
    
	 /**
     * Pesquisa o cliente da guia de pagamento 
     * através do id da Guia de Pagamento 
     * 
     * @author Vivianne Sousa
     * @date 06/03/2007
     * 
     * @return String
     * @throws ErroRepositorioException
     */

    public Object[] pesquisarImovelDeClienteGuiaPagamento(Integer idGuiaPagamento) throws ErroRepositorioException;
    
    /**
     * Pesquisa o cliente da guia de pagamento 
     * através do id da Guia de Pagamento 
     * 
     * @author Vivianne Sousa
     * @date 28/02/2007
     * 
     * @return String
     * @throws ErroRepositorioException
     */

    public Object[] pesquisarClienteDeClienteImovel(Integer idGuiaPagamento) 
    	throws ErroRepositorioException;
    
    /**
     * Este caso de uso cria um filtro que será usado na pesquisa de pagamentos
     * para o Relatório
     * 
     * [UC0255] Filtrar Pagamentos
     * 
     * Pesquisa os pagamentos do tipo Debito a Cobrar do Cliente
     * pesquisarPagamentoLocalidade
     * 
     * @author Rafael Corrêa
     * @date 12/12/06
     * 
     * @return Collection
     * @throws ErroRepositorioException
     */
    public Collection pesquisarPagamentoImovelAmbosRelatorio(String idImovel)
            throws ErroRepositorioException;
    
    
    /**
     * [UC0213] Desfazer Parcelamento Debito -
     *  remover guia pagamento referente ao parcelamento
     * 
     * remove a guia de pagamento do Pagamento
     * 
     * @author Vivianne Sousa
     * @date 06/03/2007
     * 
     * @param 
     * @return void
     */
    public void removerGuiaPagamentoPagamento(Integer idPagamento) throws ErroRepositorioException ;

    /**
     * [UC0301] Gerar Dados Diários da Arrecadação
     *
     * Pesquisa os ano/mês de referência dos pagamentos para ano/mês 
     * de referência maior ou igual ao ano/mês de referência atual da arrecadação
     *
     * @author Pedro Alexandre 
     * @date 07/03/2007
     *
     * @param anoMesArrecadacaoAtual
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection pesquisarAnoMesArrecadacaoPagamentoMaiorIgualAnoMesArrecadacaoAtual(Integer anoMesArrecadacaoAtual, Integer idLocalidade) throws ErroRepositorioException ;


    /**
     * [UC0113] - Faturar Grupo de Faturamento
     *
     * @author Raphael Rossiter 
     * @date 20/03/2007
     *
     * @param idDebitoACobrar
     * @return Integer 
     * @throws ErroRepositorioException
     */
    public Integer pesquisarQuantidadePagamentosPorDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException ;

    /**
     * [UC0300] Classificar Pagamentos e Devoluções
     * 
     * Atualiza a situacao atual dos pagamentos (PGST_IDATUAL) (tabela
     * PAGAMENTO_SITUACAO)
     *
     * @author Pedro Alexandre
     * @date 23/03/2007
     *
     * @param colecaoIdsPagamentos
     * @throws ErroRepositorioException
     */
    public void atualizarSituacaoPagamentoClassificado(Collection<Integer> colecaoIdsPagamentos) throws ErroRepositorioException ;

    /**
     * [UC0300] Classificar Pagamentos e Devoluções
     * 
     * Caso o valor total dos pagamentos seja menor que o valor do documento,
     * atualiza a situação atual dos pagamentos (PGST_IDATUAL) com valor
     * correspondente a valor não confere (tabela PAGAMENTO_SITUACAO) e
     * atualiza o id da conta nos pagamentos (seta CNTA_ID da tabela PAGAMENTO
     * para CNTA_ID da tabela CONTA)
     * 
     * [SB0008] Processar Pagamento a Maior ou a Menor
     * 
     * @author Pedro Alexandre
     * @date 28/03/2007
     *
     * @param mapPagamentosValorNaoConfere
     * @throws ErroRepositorioException
     */
    public void processarPagamentoValorNaoConfereConta(Map<Integer, Collection> mapPagamentosValorNaoConfere) throws ErroRepositorioException ;

    /**
     * [UC0300] Classificar Pagamentos e Devoluções
     * 
     * Caso o valor total dos pagamentos seja menor que o valor do documento,
     * atualiza a situação atual dos pagamentos (PGST_IDATUAL) com valor
     * correspondente a valor não confere (tabela PAGAMENTO_SITUACAO) e
     * atualiza o id da guia de pagamento nos pagamentos (seta GPAG_ID da tabela PAGAMENTO
     * para GPAG_ID da tabela GUIA PAGAMENTO)
     * 
     * [SB0008] Processar Pagamento a Maior ou a Menor
     * 
     * @author Pedro Alexandre
     * @date 28/03/2007
     *
     * @param mapPagamentosValorNaoConfere
     * @throws ErroRepositorioException
     */
    public void processarPagamentoValorNaoConfereGuiaPagamento(Map<Integer, Collection> mapPagamentosValorNaoConfere) throws ErroRepositorioException ;

    /**
     * [UC0300] Classificar Pagamentos e Devoluções
     * 
     * Caso o valor total dos pagamentos seja menor que o valor do documento,
     * atualiza a situação atual dos pagamentos (PGST_IDATUAL) com valor
     * correspondente a valor não confere (tabela PAGAMENTO_SITUACAO) e
     * atualiza o id do débito a cobrar nos pagamentos (seta DBAC_ID da tabela PAGAMENTO
     * para DBAC_ID da tabela DEBITO A COBRAR)
     * 
     * [SB0008] Processar Pagamento a Maior ou a Menor
     * 
     * @author Pedro Alexandre
     * @date 28/03/2007
     *
     * @param mapPagamentosValorNaoConfere
     * @throws ErroRepositorioException
     */
    public void processarPagamentoValorNaoConfereDebitoACobrar(Map<Integer, Collection> mapPagamentosValorNaoConfere) throws ErroRepositorioException ;

    /**
     * [UC0300] - Classificar Pagamentos e Devoluções
     *
     * <Breve descrição sobre o subfluxo>
     *
     * [SB0008] - Processar Pagamento a Maior ou a Menor	
     *
     * @author Pedro Alexandre
     * @date 28/03/2007
     *
     * @param colecaoPagamentos
     * @throws ErroRepositorioException
     */
    public void processarPagamentoValorNaoConfereIdentificadorDocumentoIgualANulo(Collection colecaoPagamentos) throws ErroRepositorioException ;

    /**
     * Remove o id da guia de pagamento dos pagamentos referentes a conta
     * para poder mandar a guia de pagamento para o histórico.
     *
     * [UC0000] Gerar Histórco para encerrar Faturamento
     *
     * @author Pedro Alexandre
     * @date 01/04/2007
     *
     * @param idConta
     * @return
     * @throws ErroRepositorioException
     */
    public void apagarIdGuiaPagamentoPagamentos(Integer idGuiaPagamento) throws ErroRepositorioException ;
    
    /**
	 * Pesquisa os movimentos dos arrecadores para a geração do relatório
	 * 
	 * [UCXXXX] Acompanhar Movimento dos Arrecadadores
	 * 
	 * @author Rafael Corrêa
	 * @date 02/04/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarMovimentoArrecadadoresRelatorio(
			Integer mesAnoReferencia, Integer idArrecadador,
			Integer idFormaArrecadacao, Date dataPagamentoInicial,
			Date dataPagamentoFinal) throws ErroRepositorioException;


	/**
     * Sequencial do tipo lançamento igual a 750
     * 
     * [UC0276] - Encerrar Arrecadação do Mês
     * 
     * Acumula o valor do débitos cobrados por localidade, categoria dos
     * pagamentos classificados de conta para tipo de financiamento igual a
     * doações
     * 
     * @author Pedro Alexandre
     * @date 03/04/2007
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idCategoria
     * @param idLancamentoItemContabil
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaFinanciamentoTipoDoacoes(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idLancamentoItemContabil) throws ErroRepositorioException ;

    
    /**
     * Sequencial do tipo lançamento igual a 4150
     * 
     * [UC0276] - Encerrar Arrecadação do Mês
     * 
     * Acumula o valor dos débitos cobrados por localidade, categoria e item
     * contábil para os pagamentos de contas efetuados em meses anteriores
     * classificados no mês para tipo de financiamento igual doações.
     * 
     * @author Pedro Alexandre
     * @date 03/04/2007
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idCategoria
     * @param idLancamentoItemContabil
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoParcelamentoDoacoes(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idLancamentoItemContabil) throws ErroRepositorioException ;

    /**
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * Pesquisa as guias de pagamento correspondentes aos pagamentos classificados de guia de 
     * pagamento e aos pagamentos anteriores de guia de pagamento classificados no mês.
     *
     * @author Pedro Alexandre
     * @date 09/01/2007
     *
     * @param anoMesReferenciaArrecadacao
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<GuiaPagamento> pesquisarGuiasPagamentoDePagamentosClassificadosGuiasPagamentoEPagamentosAnterioresGuiaPagamentoClassificadosNoMes(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;

    /**
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * Pesquisar os pagamentos classificados ou com valor excedente baixado e com 
     * valor excedente maior do que zero para transferir para o histórico.
     *
     * @author Pedro Alexandre
     * @date 10/01/2007
     *
     * @param anoMesReferenciaArrecadacao
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Pagamento> pesquisarPagamentosClassificadosOuValorExcedenteBaixado(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;

    /**
     * Sequencial do tipo lançamento igual a 4000
     * 
     * [UC0276] - Encerrar Arrecadação do Mês
     * 
     * Acumula o valor dos débitos cobrados por localidade, categoria e item
     * contábil para os pagamentos de contas efetuados em meses anteriores
     * classificados no mês para tipo de financiamento igual a parcelamento de
     * serviço e grupo de parcelamento diferente de juros cobrados.
     * 
     * @author Pedro Alexandre
     * @date 23/05/2006
     * 
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @param idCategoria
     * @param idLancamentoItemContabil
     * @return
     * @throws ErroRepositorioException
     */
    public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesFinanciamentoTipoDoacoes(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idLancamentoItemContabil) throws ErroRepositorioException ;

    /**
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * Pesquisa as devoluções classificadas para transferir para o histórico.
     *
     * @author Pedro Alexandre
     * @date 10/01/2007
     *
     * @param anoMesReferenciaArrecadacao
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Devolucao> pesquisarDevolucoesClassificadasPorLocalidade(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;

    /**
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * Pesquisa as contas correspondentes aos pagamentos classificados de conta 
     * e os pagamentos anteriores de conta classificados no mês.
     *
     * @author Pedro Alexandre
     * @date 09/01/2007
     *
     * @param anoMesReferenciaArrecadacao
     * @param idLocalidade
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Conta> pesquisarContasDePagamentosClassificadosContaEPagamentosAnterioresContaClassificadosNoMes(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;


    /**
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * Pesquisa a conta  
     * @author Pedro Alexandre
     * @date 10/01/2007
     *
     * @param idConta
     * @return
     * @throws ErroRepositorioException
     */
    public Conta pesquisarConta(Integer idConta) throws ErroRepositorioException ;

    /**
     * [UC0276] Encerrar Arrecadação do Mês
     *
     * Pesquisar o pagamento
     *
     * @author Pedro Alexandre
     * @date 10/04/2007
     *
     * @param idPagamento
     * @return
     * @throws ErroRepositorioException
     */
    public Pagamento pesquisarPagamento(Integer idPagamento) throws ErroRepositorioException ;
=======
	public Collection<Conta> pesquisarContasDePagamentosClassificadosContaEPagamentosAnterioresContaClassificadosNoMes(Integer anoMesReferenciaArrecadacao,
			Integer idLocalidade) throws ErroRepositorioException;

	public Conta pesquisarConta(Integer idConta) throws ErroRepositorioException;

	public Pagamento pesquisarPagamento(Integer idPagamento) throws ErroRepositorioException;
>>>>>>> master

	public Collection<Integer> pesquisarIdsSetoresComPagamentosOuDevolucoes() throws ErroRepositorioException;

	public Integer pesquisarIdLocalidadePorSetorComercial(Integer idSetorComercial) throws ErroRepositorioException;

	public Collection<DebitoACobrar> pesquisarDebitosACobrarDePagamentosClassificadosGuiasPagamentoEPagamentosAnterioresGuiaPagamentoClassificadosNoMes(
			Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException;

	public BigDecimal acumularValorCreditoRealizadoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesPorOrigemCredito(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idOrigemCredito) throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaPorFinanciamentoTipo(Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria, Integer idFinanciamentoTipo) throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesPorFinanciamentoTipo(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idFinanciamentoTipo) throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPagamentosClassificadosContaPorFinanciamentoTipo(Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, Integer idCategoria, Collection<Integer> colecaoIdsFinanciamentoTipo) throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMesPorFinanciamentoTipo(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idLancamentoItemContabil, Integer idCategoria, Collection<Integer> idsFinanciamentoTipos)
			throws ErroRepositorioException;

	public void atualizarDadosArrecadadorContrato(ArrecadadorContrato arrecadadorContrato, boolean flagEnvioDebitoAutomatico, boolean flagRetornoCodigoBarras,
			boolean flagRetornoDebitoAutomatico, boolean flagRetornoFichaCompensacao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarAnoMesReferenciaPagamentoParaImovel(Integer anoMesArrecadacaoAtual, Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoHistoricoAvisoBancario(Integer idAvisoBancario) throws ErroRepositorioException;

	public Pagamento pesquisarPagamentoDeConta(Integer idConta) throws ErroRepositorioException;

	public void atualizarContaEmPagamento(Integer idPagamento, Integer idConta) throws ErroRepositorioException;

	public Integer pesquisarIdPagamentoDaGuia(Integer idGuiaPagamento) throws ErroRepositorioException;

	public Integer pesquisarIdPagamentoDoDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoArrecadacaoRelatorioPorEstadoPorUnidadeNegocio(int anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoArrecadacaoRelatorioPorUnidadeNegocio(int anoMesReferencia, Integer localidade) throws ErroRepositorioException;

	public PagamentosDevolucoesHelper filtrarPagamentos(FiltroPagamento filtroPagamento) throws ErroRepositorioException;

	public PagamentosDevolucoesHelper filtrarDevolucoes(FiltroDevolucao filtroDevolucao) throws ErroRepositorioException;

	public ValoresArrecadacaoDevolucaoAvisoBancarioHelper pesquisarValoresAvisoBancario(Integer idAvisoBancario) throws ErroRepositorioException;

	public void atualizarAvisoBancarioPagamentos(Collection<Integer> idsPagamentos, Integer idAvisoBancarioD) throws ErroRepositorioException;

	public void atualizarValorArrecadacaoAvisoBancario(String valorArrecadacaoInformado, String valorArrecadacaoCalculado, Integer idAvisoBancario)
			throws ErroRepositorioException;

	public void atualizarAvisoBancarioDevolucoes(Collection<Integer> idsDevolucoes, Integer idAvisoBancarioD) throws ErroRepositorioException;

	public void atualizarValorDevolucaoAvisoBancario(String valorDevolucaoInformado, String valorDevolucaoCalculado, Integer idAvisoBancario)
			throws ErroRepositorioException;

	public Collection<Integer> filtrarIdsMovimentoArrecadador(String codigoBanco, String codigoRemessa, String descricaoIdentificacaoServico,
			String numeroSequencialArquivo, Date dataGeracaoInicio, Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim,
			String descricaoOcorrencia, String indicadorAceitacao, String indicadorAbertoFechado) throws ErroRepositorioException;

	public Collection<MovimentoArrecadadoresPorNSAHelper> gerarMovimentoArrecadadoresNSA(Collection<Integer> idsArrecadadorMovimento,
			Integer codigoFormaArrecadacao) throws ErroRepositorioException;

	public Collection<Conta> pesquisarContaComPagamentoHistorico() throws ErroRepositorioException;

	public Agencia pesquisarAgencia(String codigoAgencia, Integer idBanco) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoDeContas(Collection colecaoConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosPagamentoExpurgado(String dataPagamento, Integer idCliente, Integer anoMesArrecadacao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosPagamentoHistoricoExpurgado(String dataPagamento, Integer idCliente, Integer anoMesArrecadacao)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public void atualizarSituacaoExpurgado(Collection colecaoPagamento) throws ErroRepositorioException;

	public Object[] selecionarContaHistoricoPorImovelAnoMesReferencia(Imovel imovel, Integer anoMesReferenciaPagamento, Integer anoMesFaturamento)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosComparativosFaturamentoArrecadacaoExpurgo(Integer anoMesReferencia, String idGerenciaRegional, String idUnidadeNegocio)
			throws ErroRepositorioException;

	public GuiaDevolucao pesquisarGuiaDevolucao(Integer guiaDevolucaoId) throws ErroRepositorioException;

	public void atualizarValorMovimentoArrecadadorMovimento(Integer idArrecadadorMovimento, BigDecimal valorTotalMovimento) throws ErroRepositorioException;

	public Date pesquisarDataPagamentoDeConta(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoExpurgado(Integer anoMesReferencia, String idGerenciaRegional, String idUnidadeNegocio) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoHistoricoExpurgado(Integer anoMesReferencia, String idGerenciaRegional, String idUnidadeNegocio)
			throws ErroRepositorioException;

	public Object[] acumularValorAguaEsgotoPagamentosClassificadosConta(Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria)
			throws ErroRepositorioException;

	public Object[] acumularValorAguaEsgotoPagamentosContasEfetuadosEmMesesAnterioresClassificadosNoMes(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idCategoria) throws ErroRepositorioException;

	public Integer[] pesquisarExistenciaGuiaPagamento(Imovel imovel, Integer numeroGuia, Integer anoGuia) throws ErroRepositorioException;

	public Integer[] pesquisarExistenciaGuiaPagamentoPorLotePagamento(Imovel imovel, Integer lotePagamento, Integer anoGuia) throws ErroRepositorioException;

	public ArrecadadorContratoTarifa pesquisarArrecadadorContratoTarifa(Integer idArrecadador, Integer idFormaArrecadacao) throws ErroRepositorioException;

	public void excluirResumoArrecadacaoPorAnoMesArrecadacaoPorLocalidade(int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	public Collection<CreditoARealizar> pesquisarCreditoaRealizarDeDevolucoesClassificadas(Integer anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	public List<RelatorioAnaliseArrecadacaoBean> pesquisarAnaliseArrecadacao(PesquisarAnaliseArrecadacaoHelper helper) throws ErroRepositorioException;

	public List<RelatorioAnaliseAvisosBancariosBean> pesquisarAnaliseAvisosBancarios(PesquisarAnaliseAvisosBancariosHelper helper)
			throws ErroRepositorioException;

	public List<RelatorioAvisoBancarioPorContaCorrenteBean> pesquisarAvisoBancarioPorContaCorrente(PesquisarAvisoBancarioPorContaCorrenteHelper helper)
			throws ErroRepositorioException;

	public List<Object[]> pesquisarPagamentosDosAvisos(PesquisarAvisoBancarioPorContaCorrenteHelper helper, Collection<Integer> idsAvisos)
			throws ErroRepositorioException;

	public List<Object[]> pesquisarDiferencaAcumuladaNoMes(int anoMesArrecadacao, int anoMesArrecadacaoAnterior) throws ErroRepositorioException;

	public void removerDiferencasAcumuladasNoMes(int anoMesArrecadacao) throws ErroRepositorioException;

	public void excluirDadosDiariosDevolucaoPorAnoMesArrecadacaoPorLocalidade(int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	public Collection<Pagamento> obterPagamentosClassificadosNaoRegistradosCobrancaPorEmpresa(Integer idLocalidade, Integer referencia, int numeroPaginas, int quantidadeRegistros) throws ErroRepositorioException;

	public Integer verificarExistenciaResumoArrecadacaoParaAnoMes(Integer anoMesReferencia) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarDadosDiariosArrecadacao(FiltroConsultarDadosDiariosArrecadacao filtro) throws ErroRepositorioException;

	public boolean verificarExistenciaDadosDiariosArrecadacao(FiltroConsultarDadosDiariosArrecadacao filtro) throws ErroRepositorioException;

	public void atualizarSituacaoAnteriorAtualEValorExcedentePagamento(Collection<Pagamento> colecaoPagamento, Integer pagamentoSituacao)
			throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoComGuiaInformadaRefContabilMenorRefFaturamento(Integer anoMesReferencia,
			Integer idLocalidade, Integer referenciafaturamento) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPagamentosPorGuiaPagamentoComGuiaInformadaRefContabilMaiorIgualRefFaturamento(Integer anoMesReferencia,
			Integer idLocalidade, Integer referenciafaturamento) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarComDebitoInformadoRefContabilMaiorIgualRefFaturamento(Integer anoMesReferencia,
			Integer idLocalidade, Integer referenciafaturamento) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarComDebitoInformadoRefContabilMenorRefFaturamento(Integer anoMesReferencia,
			Integer idLocalidade, Integer referenciafaturamento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDevolucoesDescontosPagamentoAVista(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDevolucoesDescontosCreditos(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarBancoDebitoAutomatico() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisBancoDebitoAutomatico(String[] bancos) throws ErroRepositorioException;

	public Integer countImoveisBancoDebitoAutomatico(String[] bancos, Integer anoMesInicial, Integer anoMesFinal, Date dataVencimentoInicial,
			Date dataVencimentoFinal, String indicadorContaPaga, Integer somenteDebitoAutomatico) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection selecionarImoveisBancoDebitoAutomatico(String[] bancos, Integer anoMesInicial, Integer anoMesFinal, Date dataVencimentoInicial,
			Date dataVencimentoFinal, String indicadorContaPaga) throws ErroRepositorioException;

	public Collection<Object[]> consultarNomeArrecadadorNomeAgencia(String idArrecadadorMovimento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentosMesEMesesAnterioresCampanhaSolidariedadeCrianca(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, Integer idRD)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDevolucoesDescontosPagamentoAVistaCampanhaCrianca(Integer anoMesReferenciaArrecadacao, Integer idLocalidade,
			Integer idRDComPercentualDoacao) throws ErroRepositorioException;

	public Object[] pesquisarExistenciaContaPorNumeroFatura(String numeroFatura) throws ErroRepositorioException;

	public Object[] pesquisarParmsCobrancaDocumentoPorNumeroDocumentoFatura(String numeroDocumentoFatura) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarCobrancaDocumentoItem(Integer idCobrancaDocumento) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarPagamentosPorDebitoACobrarHistoricoComDebitoInformadoRefContabil(Integer anoMesReferencia, Integer idLocalidade)
			throws ErroRepositorioException;

	public Object[] pesquisarExistenciaGuiaPagamentoPorNumeroGuiaFatura(String numeroGuiaFatura) throws ErroRepositorioException;

	public Integer pesquisarFaturaPorNumeroFatura(String numeroFatura) throws ErroRepositorioException;

	public Conta pesquisarContaParaPagamentoParcial(Integer idGuiaPagamento) throws ErroRepositorioException;

	public Fatura pesquisarFaturaPorNumeroFaturaObjetpCompleto(String numeroFatura) throws ErroRepositorioException;

	public Object[] pesquisarQuantidadeContasFaturadas(Integer idLocalidade, Integer anoMesReferencia) throws ErroRepositorioException;

	public Object[] pesquisarQuantidadeDocumentosPagos(Integer idLocalidade, Integer anoMesReferencia) throws ErroRepositorioException;

	public Integer pesquisarPagamentoCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos,
			String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	public Integer pesquisarExistenciaGuiaPagamento(Integer idGuia) throws ErroRepositorioException;
<<<<<<< HEAD
	
	/**
	 * [UC0978] Pesquisa Relatório de Pagamento para Entidades Beneficentes Analitico
	 * 
	 * @author Daniel Alves
	 * @data   26/01/2010
	 * @param  anoMesInicial = periodo inicial do relatorio
	 *         anoMesFinal   = periodo final do relatorio
	 *         idUnidadeBeneficente
	 *         idGerenciaRegional
	 *         idUnidadeNegocio
	 *         idLocalidade
	 */ 
	public Collection pesquisarPagamentoEntidadesBeneficentesAnalitico(String anoMesInicial, String anoMesFinal,
			String idEntidadeBeneficente, String idGerenciaRegional, 
			String idUnidadeNegocio, String idLocalidade, int opcaoTotalizacao) throws ErroRepositorioException;
	
	
	/**
	 * [UC0978] Pesquisa Relatório de Pagamento para Entidades Beneficentes Sintetico
	 * 
	 * @author Daniel Alves
	 * @data   26/01/2010
	 * @param  anoMesInicial = periodo inicial do relatorio
	 *         anoMesFinal   = periodo final do relatorio
	 *         idUnidadeBeneficente
	 *         idGerenciaRegional
	 *         idUnidadeNegocio
	 *         idLocalidade
	 */ 
	public Collection pesquisarPagamentoEntidadesBeneficentesSintetico(String anoMesInicial, String anoMesFinal,
			String idEntidadeBeneficente, String idGerenciaRegional, String idUnidadeNegocio, String idLocalidade,
			int opcaoTotalizacao
			) throws ErroRepositorioException;
	/**
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * Pesquisar o pagamento
	 * 
	 * @author Ivan Sergio
	 * @date 26/03/2010
	 * 
	 * @param idPagamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public PagamentoHistorico obterPagamentoHistoricoDePagamentoParaEncerrarArrecadacao(Integer idPagamento)
=======

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoEntidadesBeneficentesAnalitico(String anoMesInicial, String anoMesFinal, String idEntidadeBeneficente,
			String idGerenciaRegional, String idUnidadeNegocio, String idLocalidade, int opcaoTotalizacao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoEntidadesBeneficentesSintetico(String anoMesInicial, String anoMesFinal, String idEntidadeBeneficente,
			String idGerenciaRegional, String idUnidadeNegocio, String idLocalidade, int opcaoTotalizacao) throws ErroRepositorioException;

	public Pagamento pesquisarPagamentoParaEncerrarArrecadacao(Integer idPagamento) throws ErroRepositorioException;

	public int pesquisarPagamentoEntidadesBeneficentesAnaliticoCount(String anoMesInicial, String anoMesFinal, String idEntidadeBeneficente,
			String idGerenciaRegional, String idUnidadeNegocio, String idLocalidade, int opcaoTotalizacao) throws ErroRepositorioException;

	public int pesquisarPagamentoEntidadesBeneficentesSinteticoCount(String anoMesInicial, String anoMesFinal, String idEntidadeBeneficente,
			String idGerenciaRegional, String idUnidadeNegocio, String idLocalidade, int opcaoTotalizacao) throws ErroRepositorioException;

	public Integer pesquisarNumeroNsaPorArrecadador(Integer idArrecadador, Integer numeroNsa, String codigoOpcaoExtrato) throws ErroRepositorioException;

	public void atualizarNumeroPrestacoesAntecipadasECobradas(Integer idDebitoACobrar, Integer numeroPrestacoesCobradas, Integer numeroPrestacoesAntecipadas)
>>>>>>> master
			throws ErroRepositorioException;

	public void atualizarNumeroPrestacoesAntecipadasERealizadas(Integer idCreditoARealizar, Integer numeroPrestacoesRealizadas,
			Integer numeroPrestacoesAntecipadas) throws ErroRepositorioException;

	public DebitoACobrar pesquisarDebitoACobrarJurosParcelamento(Integer idParcelamento) throws ErroRepositorioException;

	public void atualizarNumeroParcelasBonus(DebitoACobrar debitoACobrar) throws ErroRepositorioException;

	public Integer pesquisarPagamentoHistoricoAvisoBancarioCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	public Integer verificarExistenciaCreditoARealizar(Integer idImovel, Integer anoMesReferenciaConta) throws ErroRepositorioException;

	public Integer verificarExistenciaCreditoARealizarHistorico(Integer idImovel, Integer anoMesReferenciaConta) throws ErroRepositorioException;

	public Integer verificarExistenciaGuiaDevolucao(Integer idImovel, Integer anoMesReferenciaGuiaDevolucao) throws ErroRepositorioException;

	public void confirmarPagamentoCartaoCreditoOperadora(ParcelamentoPagamentoCartaoCredito parcelamentoPagamentoCartaoCredito) throws ErroRepositorioException;

	public void confirmarPagamentoCartaoDebitoOperadora(PagamentoCartaoDebito pagamentoCartaoDebito) throws ErroRepositorioException;

	public boolean verificarExistenciaContrato(String numeroContrato) throws ErroRepositorioException;

	public Date pesquisarDataProcessamentoMes(Integer anoMes) throws ErroRepositorioException;

	public Integer filtrarArquivoTextoRoteiroEmpresaCount(ConsultarArquivoTextoRoteiroEmpresaHelper helper) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarArquivoTextoRoteiroEmpresaParaPaginacao(ConsultarArquivoTextoRoteiroEmpresaHelper helper, Integer numeroPagina)
			throws ErroRepositorioException;

	public Integer relatorioAnalisePagamentoCartaoDebitoCount(ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper helper) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarDadosRelatorioAnalisePagamentoCartaoDebito(ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper helper)
			throws ErroRepositorioException;

	public Collection<Object[]> pesquisarDadosItenRelatorioAnalisePagamentoCartaoDebito(Integer integer) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarDetalheItenRelatorioAnalisePagamentoCartaoDebito(Integer tipoItem, Integer idItem) throws ErroRepositorioException;

	public CobrancaDocumento pesquisarCobrancaDocumentoProcessarFichaCompensacao(Integer idCobrancaDocumento) throws ErroRepositorioException;

	public BigDecimal pesquisarFaturamentoCobradoEmConta(Integer anoMes) throws ErroRepositorioException;

	public BigDecimal pesquisarFaturamentoCobradoEmContaComQuebra(Integer anoMes, Integer idGerenciaRegional, Integer idCategoria)
			throws ErroRepositorioException;

	public Collection<Integer> verificarBloqueioGuiaPagamento(Collection<GuiaPagamento> guiasPagamentos) throws ErroRepositorioException;

	public void atualizarPagamentoBatimentoRelatorioPrimeiraSituacao(Integer idLocalidade, Integer anoMesReferencia) throws ErroRepositorioException;

	public void atualizarPagamentoBatimentoRelatorioSegundaSituacao(Integer idLocalidade, Integer anoMesReferencia) throws ErroRepositorioException;

	public void atualizarPagamentoBatimentoRelatorioTerceiraSituacao(Integer idLocalidade, Integer anoMesReferencia) throws ErroRepositorioException;

	public void atualizarPagamentoBatimentoRelatorioQuartaSituacao(Integer idLocalidade, Integer anoMesReferencia) throws ErroRepositorioException;

	public CobrancaDocumentoItem pesquisarCobrancaDocumentoItemProcessarFichaCompensacao(Integer idPrestacao) throws ErroRepositorioException;

	public GuiaPagamento pesquisarGuiaPagamentoProcessarFichaCompensacao(Integer idGuiaPagamento) throws ErroRepositorioException;

	public List<RelatorioDocumentoNaoAceitosBean> pesquisarDocumentosNaoAceitos(Arrecadador arrecadador, String periodoInicial, String periodoFinal,
			Integer movimentoArrecadadorCodigo, AvisoBancario avisoBancario, ArrecadacaoForma arrecadacaoForma) throws ErroRepositorioException;

	public boolean existeClienteIdDocNaoIdentificado();

	public List<RelatorioTranferenciaPagamentoBean> pesquisarTransfereciasPagamento(Arrecadador arrecadador, String periodoInicial, String periodoFinal,
			AvisoBancario avisoBancario, ArrecadacaoForma arrecadacaoForma, DebitoTipo debitoTipo, DocumentoTipo documentoTipo) throws ErroRepositorioException;

	public DebitoTipo obterDebitoTipoCodigoConstante(Integer codigoConstante) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentosDocumentosNaoAceitos(InformarAcertoDocumentosNaoAceitosPagamentoHelper helper) throws ErroRepositorioException;

	public BigDecimal pesquisarValorPagamento(Integer idPagamento) throws ErroRepositorioException;

	public Integer pesquisarLocalidadeGuiaPagamento(Integer idGuia) throws ErroRepositorioException;

	public Integer pesquisarLocalidadeConta(Integer idConta) throws ErroRepositorioException;

	public Integer pesquisarLocalidadeDebitoACobrar(Integer idDebitoACobrar) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarValorAcertosArrecadadorMovimento(Integer idArrecadadorMovimento) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoHistoricoClienteCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoHistoricoClienteRelatorio(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal,
			String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes,
			String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisBancoDebitoAutomaticoEPorGrupoFaturamento(String[] bancos, Integer idGrupoFaturamento) throws ErroRepositorioException;

	public Integer countImoveisBancoDebitoAutomaticoPorGrupoFaturamento(String[] bancos, Integer anoMesInicial, Integer anoMesFinal,
			Date dataVencimentoInicial, Date dataVencimentoFinal, String indicadorContaPaga, Integer idGrupoFaturamento, Integer somenteDebitoAutomatico)
			throws ErroRepositorioException;

	public Object[] pesquisarPagamentoDeContaEmHistorico(Integer idConta) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterFormasDeArrecadacaoPorDia(Object helper, FiltroConsultarDadosDiariosArrecadacao filtro) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterFormasDeArrecadacaoComTarifaPorDia(Object helper, FiltroConsultarDadosDiariosArrecadacao filtro) throws ErroRepositorioException;

	public void excluirDadosDiariosArrecadacaoAuxiliarPorAnoMesArrecadacaoPorLocalidade(int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularQuantidadeEValorPagamentoPorAnoMesArrecadacaoAuxiliar(int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	public boolean verificarExistenciaDadosDiariosArrecadacaoAuxiliar(FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarDadosDiariosArrecadacaoAuxiliar(FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro) throws ErroRepositorioException;

	public void excluirDadosDiariosDevolucaoPorAnoMesArrecadacaoAuxiliarPorLocalidade(int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection acumularQuantidadeEValorDevolucaoPorAnoMesArrecadacaoAuxiliar(int anoMesReferenciaArrecadacao, Integer idLocalidade)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection obterFormasDeArrecadacaoComTarifaPorDiaAuxiliar(Object helper, FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro)
			throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasImpostosDeduzidosPagamentosContasEfetuadosAte122012ClassificadosMesPorTipoImposto(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idImpostoTipo) throws ErroRepositorioException;

	public Object[] acumularValorAguaEsgotoPagamentosContasEfetuadosAte122012ClassificadosNoMes(Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosAte122012ClassificadosNoMesPorFinanciamentoTipo(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idFinanciamentoTipo) throws ErroRepositorioException;

	public BigDecimal acumularValorCreditoRealizadoPagamentosContasEfetuadosAte122012ClassificadosNoMesPorOrigemCredito(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idOrigemCredito) throws ErroRepositorioException;

	public BigDecimal acumularValorEntradaParcelamentoPagamentosGuiaPagamentoEfetuadosAte122012ClassificadosNoMesFinanciamentoTipoEntradaParcelamento(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosAte122012ClassificadosNoMesPorFinanciamentoTipo(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idLancamentoItemContabil, Integer idCategoria, Collection<Integer> idsFinanciamentoTipos)
			throws ErroRepositorioException;

	public BigDecimal acumularValorDebitoCobradoPagamentosContasEfetuadosAte122012ClassificadosNoMesFinanciamentoTipoDoacoes(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public BigDecimal acumularValorCreditoRealizadoPagamentosContasEfetuadosAte122012ClassificadosNoMesOrigemCreditoValoresCobradosIndevidamente(
			Integer idLocalidade, Integer anoMesReferenciaArrecadacao, Integer idCategoria, Integer idLancamentoItemContabil) throws ErroRepositorioException;

	public BigDecimal acumularValorEntradaParcelamentoPagamentosGuiaPagamentoEfetuadosAte122012ClassificadosNoMesFinanciamentoTipoServico(Integer idLocalidade,
			Integer anoMesReferenciaArrecadacao, Integer idLancamentoItemContabil, Integer idCategoria) throws ErroRepositorioException;

	public BigDecimal acumularValorQueFaltaSerCobradoPagamentosDebitoACobrarEfetuadosAte122012(Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idLancamentoItemContabil, Integer idCategoria) throws ErroRepositorioException;

	public BigDecimal pesquisarTotalArrecadacaoRelatorioBIG(Integer anoMesReferencia, Integer idLocalidade) throws ErroRepositorioException;

	public Object[] pesquisarPrazoMedioRecebimentoContasRelatorioBIG(Integer anoMesReferencia, Integer idLocalidade) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioBIG(Integer anoMesReferencia) throws ErroRepositorioException;

	public Collection<PagamentoHelper> pesquisarValoresPagamentos(Integer pagamentoSituacao, Integer idLocalidade, Integer anoMesReferenciaArrecadacao)
			throws ErroRepositorioException;

	public void atualizarSituacaoPagamento(Integer pagamentoSituacao, Integer idPagamento) throws ErroRepositorioException;

	public void atualizarGuiasPagamentoNaoPagasAtePeriodo(Integer financiamentoTipoServico, Collection<Integer> idsGuiasPagamentoNaoPagas,
			Integer anoMesReferencia) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsGuiasPagamentoNaoPagas(Date dataVencimentoLimite, Integer idLocalidade) throws ErroRepositorioException;

	public Collection<Integer> pesquisarIdsLocalidadeComGuiasPagamentoNaoPagas(Integer financiamentoTipoServico, Date dataVencimentoLimite)
			throws ErroRepositorioException;

	public Collection<Pagamento> obterPagamentos(Collection<Integer> idsPagamentos) throws ErroRepositorioException;

	public Collection<DebitoAutomatico> pesquisarDebitoAutomaticoSemDataExclusao(Integer idImovel) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteGuiaPagamentoECliente(Integer idGuiaPagamento) throws ErroRepositorioException;

	public Object[] acumularValorAguaEsgotoPagamentosClassificadosRecuperacaoCreditoConta(Integer idLocalidade, Integer referenciaArrecadacao,
			Integer idCategoria) throws ErroRepositorioException;

	public Collection<LancamentoItemContabil> pesquisarLancamentosItemContabil() throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasPagamentosClassificadosRecuperacaoCredito(Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idPagamentoSituacao) throws ErroRepositorioException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContasPagamentosClassificadosRecuperacaoCreditoMesesAnteriores(Integer idLocalidade, Integer anoMesReferenciaArrecadacao,
			Integer idPagamentoSituacao) throws ErroRepositorioException;

	public List<ResumoCreditosAvisosBancariosDTO> pesquisarResumoCreditosAvisosBancarios(Date data) throws ErroRepositorioException;

	public Object[] pesquisarPagamentoInconformeImovel(String idImovel) throws ErroRepositorioException;
<<<<<<< HEAD
	
	public PagamentoHistorico pesquisarPagamentoHistorico(Integer idPagamento) throws ErroRepositorioException;
	
	public Integer pesquisarIdGuiaPagamento(Integer idPagamento) throws ErroRepositorioException;
=======

	public List<ArrecadadorMovimentoItemDTO> obterItensPorAviso(Integer idAvisoBancario) throws ErroRepositorioException;
>>>>>>> master
}
