package gcom.arrecadacao;

import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.aviso.AvisoDeducoes;
import gcom.arrecadacao.aviso.bean.AcertosAvisoBancarioHelper;
import gcom.arrecadacao.aviso.bean.AvisoBancarioHelper;
import gcom.arrecadacao.aviso.bean.DeducoesHelper;
import gcom.arrecadacao.aviso.bean.PagamentosDevolucoesHelper;
import gcom.arrecadacao.aviso.bean.ValoresArrecadacaoDevolucaoAvisoBancarioHelper;
import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.bean.ArrecadadorMovimentoItemHelper;
import gcom.arrecadacao.bean.ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper;
import gcom.arrecadacao.bean.DadosConteudoRegistroMovimentoArrecadadorHelper;
import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.arrecadacao.bean.InformarAcertoDocumentosNaoAceitosHelper;
import gcom.arrecadacao.bean.MovimentoArrecadadoresPorNSAHelper;
import gcom.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gcom.arrecadacao.bean.PesquisarAnaliseArrecadacaoHelper;
import gcom.arrecadacao.bean.PesquisarAnaliseAvisosBancariosHelper;
import gcom.arrecadacao.bean.PesquisarAvisoBancarioPorContaCorrenteHelper;
import gcom.arrecadacao.bean.RegistroHelperCodigo0;
import gcom.arrecadacao.bean.RegistroHelperCodigoA;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperFichaCompensacao;
import gcom.arrecadacao.debitoautomatico.DebitoAutomaticoMovimento;
import gcom.arrecadacao.pagamento.FiltroPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumentoItem;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.bean.DebitoACobrarValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.micromedicao.bean.ConsultarArquivoTextoRoteiroEmpresaHelper;
import gcom.relatorio.arrecadacao.GuiaDevolucaoRelatorioHelper;
import gcom.relatorio.arrecadacao.RelatorioAnaliseArrecadacaoBean;
import gcom.relatorio.arrecadacao.RelatorioAnaliseAvisosBancariosBean;
import gcom.relatorio.arrecadacao.RelatorioAnalisePagamentoCartaoDebitoBean;
import gcom.relatorio.arrecadacao.RelatorioAvisoBancarioPorContaCorrenteBean;
import gcom.relatorio.arrecadacao.RelatorioComparativoFatArrecExpurgoBean;
import gcom.relatorio.arrecadacao.RelatorioDocumentoNaoAceitosBean;
import gcom.relatorio.arrecadacao.RelatorioTranferenciaPagamentoBean;
import gcom.relatorio.arrecadacao.dto.ResumoCreditosAvisosBancariosDTO;
import gcom.relatorio.arrecadacao.pagamento.GuiaPagamentoRelatorioHelper;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ControladorException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IControladorArrecadacao {

	@SuppressWarnings("rawtypes")
	public Collection registrarMovimentoArrecadadores(StringBuilder stringBuilderTxt, Short codigoArrecadador, String nomeArrecadador, String idTipoMovimento, int quantidadeRegistros,
			Usuario usuario, Integer idArrecadador, ArrecadadorContrato arrecadadorContrato) throws ControladorException;

	public Double pesquisarDeducoesAvisoBancario(String codigoAgente, Date dataLancamento, String numeroSequencial) throws ControladorException;

	public Short pesquisarValorMaximoNumeroSequencial(Date dataLancamento, String idArrecadador) throws ControladorException;

	public FiltroArrecadadorMovimento filtrarMovimentoArrecadadores(FiltroArrecadadorMovimento filtroArrecadadorMovimento, String movimentoOcorrencia, String movimentoAceito,
			String movimentoAbertoFechado) throws ControladorException;

	public Integer obterNumeroRegistrosEmOcorrenciaPorMovimentoArrecadadores(ArrecadadorMovimento arrecadadorMovimento, String descricaoOcorrencia) throws ControladorException;

	public Integer obterNumeroRegistrosNaoAceitosPorMovimentoArrecadadores(ArrecadadorMovimento arrecadadorMovimento, Short indicadorAceitacao) throws ControladorException;

	public BigDecimal obterTotalArrecadacaoAvisoBancarioPorMovimentoArrecadadores(ArrecadadorMovimento arrecadadorMovimento) throws ControladorException;

	public String obterSituacaoArrecadadorMovimento(ArrecadadorMovimento arrecadadorMovimento) throws ControladorException;

	public Collection<AvisoBancarioHelper> obterColecaoAvisosBancariosPorArrecadadorMovimento(ArrecadadorMovimento arrecadadorMovimento) throws ControladorException;

	public String obterSituacaoAvisoBancarioParaArrecadadorMovimento(AvisoBancario avisoBancario) throws ControladorException;

	public void verificarExistenciaContaParaAvisoBancario(String idArrecadador, String idConta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarAvisoBancario(AvisoBancario avisoBancario, Collection duducoes, Collection deducoesParaRemover, Collection acertos, Collection acertosParaRemover, Usuario usuarioLogado)
			throws ControladorException;

	public Collection<Devolucao> pesquisarDevolucao(FiltroDevolucao filtroDevolucao) throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoImovel(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ControladorException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoImovel(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ControladorException;

	public PagamentoHelperCodigoBarras processarPagamentosCodigoBarras(String codigoBarras, Date dataPagamento, Integer idFormaPagamento, SistemaParametro sistemaParametro, Usuario usuarioLogado)
			throws ControladorException;

	public RegistroHelperCodigoBarras distribuirDadosCodigoBarras(String codigoBarras) throws ControladorException;

	public void validacaoFinal(Date dataLancamentoInicio, Date dataLancamentoFim, Integer periodoArrecadacaoInicio, Integer periodoArrecadacaoFim, Date dataPrevisaoCreditoDebitoInicio,
			Date dataPrevisaoCreditoDebitoFim, BigDecimal intervaloValorPrevistoInicio, BigDecimal intervaloValorPrevistoFim, Date dataRealizacaoCreditoDebitoInicio,
			Date dataRealizacaoCreditoDebitoFim, BigDecimal intervaloValorRealizadoInicio, BigDecimal intervaloValorRealizadoFim) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection<AvisoBancarioHelper> filtrarAvisoBancarioAbertoFechado(Collection collectionAvisoBancario, String indicadorAbertoFechado, String tipoAviso) throws ControladorException;

	public void removerAvisosBancarios(Integer[] ids, OperacaoEfetuada operacaoEfetuada, Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper) throws ControladorException;

	public Collection<ArrecadadorMovimentoItemHelper> consultarItensMovimentoArrecadador(ArrecadadorMovimento arrecadadorMovimento, Integer idImovel, Short indicadorAceitacao,
			String descricaoOcorrencia) throws ControladorException;

	public Collection<ArrecadadorMovimentoItemHelper> consultarItensMovimentoArrecadador(ArrecadadorMovimento arrecadadorMovimento, Integer idImovel, Short indicadorAceitacao,
			String descricaoOcorrencia, String codigoArrecadacaoForma, Short indicadorDiferencaValorMovimentoValorPagamento) throws ControladorException;

	public DadosConteudoRegistroMovimentoArrecadadorHelper apresentarDadosConteudoRegistroMovimentoArrecadador(ArrecadadorMovimentoItem arrecadadorMovimentoItem) throws ControladorException;

	public void removerPagamentos(String[] idsPagamentos, Usuario usuarioLogado) throws ControladorException;

	public AvisoBancarioHelper apresentarAnaliseAvisoBancario(AvisoBancario avisoBancario) throws ControladorException;

	public DebitoACobrar pesquisarDebitoACobrarDigitado(String idImovel, String idDebitoACobrar) throws ControladorException;

	public GuiaPagamento pesquisarGuiaPagamentoDigitada(String idImovel, String idCliente, String idGuiaPagamento) throws ControladorException;

	public void verificarPreeenchimentoGuiaPagamentoETipoDebito(String idGuiaPagamento, String idTipoDebito) throws ControladorException;

	public void verificarPreeenchimentoDebitoACobrarETipoDebito(String idDebitoACobrar, String idTipoDebito) throws ControladorException;

	public void verificarLocalidadeGuiaPagamento(GuiaPagamento guiaPagamento, String idLocalidade) throws ControladorException;

	public void verificarLocalidadeDebitoACobrar(DebitoACobrar debitoACobrar, String idLocalidade) throws ControladorException;

	public DebitoACobrar verificarExistenciaDebitoACobrarComTipoDebito(DebitoTipo tipoDebito, String idImovel, BigDecimal valorInformado) throws ControladorException;

	public GuiaPagamento verificarExistenciaGuiaPagamentoComTipoDebito(DebitoTipo tipoDebito, String idImovel, String idCliente) throws ControladorException;

	public void atualizarPagamento(Pagamento pagamento) throws ControladorException;

	public void inserirAvisosDeducoes(AvisoDeducoes avisoDeducoes, AvisoBancario avisoBancario) throws ControladorException;

	public void gerarDadosDiariosArrecadacao(int idFuncionalidadeIniciada, Collection<Integer> colecaoLocalidades) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Map<Banco, Collection<DebitoAutomaticoMovimento>> pesquisaDebitoAutomaticoMovimento(Collection colecaoIdsFaturamentoGrupo, Integer anoMesReferenciaFaturamento) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarAvisoBancarioAbertoFechado(AvisoBancarioHelper avisoBancarioHelper) throws ControladorException;

	public void classificarPagamentosDevolucoes(Collection<Integer> colecaoLocalidades, int idFuncionalidadeIniciada) throws ControladorException;

	public void gerarMovimentoDebitoAutomaticoBanco(Map<Banco, Collection<DebitoAutomaticoMovimento>> debitosAutomaticoBancosMap, Usuario usuario) throws ControladorException;

	public void regerarArquivoTxtMovimentoDebitoAutomatico(ArrecadadorMovimento arrecadadorMovimento, String envioBanco, Usuario usuario) throws ControladorException;

	public Integer inserirGuiaDevolucao(GuiaDevolucao guiaDevolucao, Usuario usuarioLogado) throws ControladorException;

	public void atualizaValorArrecadacaoAvisoBancaraio(BigDecimal valor, Integer codigoAvisoBancario) throws ControladorException;

	public Collection<GuiaDevolucao> pesquisarGuiaDevolucaoRelatorio(FiltroGuiaDevolucao filtroGuiaDevolucao) throws ControladorException;

	public Collection<GuiaDevolucao> pesquisarGuiaDevolucao(FiltroGuiaDevolucao filtroGuiaDevolucao, Integer numeroPagina) throws ControladorException;

	public Integer pesquisarGuiaDevolucaoCount(FiltroGuiaDevolucao filtroGuiaDevolucao) throws ControladorException;

	public void atualizarGuiaDevolucao(GuiaDevolucao guiaDevolucao, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void acumularDadosArrecadacao(Collection colecaoCategoriaPorArrecadacaoDadosDiarios, ArrecadacaoDadosDiarios arrecadacaoDadosDiarios, int indicador, String idElo, String idGerencia,
			String idLocalidade) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarResumoArrecadacaoRelatorio(String opcaoTotalizacao, int mesAnoReferencia, Integer gerenciaRegional, Integer localidade, Integer unidadeNegocio, Integer municipio)
			throws ControladorException;

	public Integer consultarQtdeRegistrosResumoArrecadacaoRelatorio(String opcaoTotalizacao, int mesAnoReferencia, Integer gerenciaRegional, Integer localidade, Integer municipio)
			throws ControladorException;

	public void encerrarArrecadacaoMes(Collection<Integer> colecaoIdsLocalidades, int idFuncionalidadeIniciada) throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoClienteConta(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ControladorException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteConta(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoClienteDebitoACobrar(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			String valorPagamentoInicial, String valorPagamentoFinal) throws ControladorException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteDebitoACobrar(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoClienteGuiaPagamento(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			String valorPagamentoInicial, String valorPagamentoFinal) throws ControladorException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoClienteGuiaPagamento(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoLocalidade(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, Integer numeroPagina,
			String valorPagamentoInicial, String valorPagamentoFinal) throws ControladorException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoLocalidade(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina) throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoAvisoBancario(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ControladorException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoAvisoBancario(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoMovimentoArrecadador(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			String valorPagamentoInicial, String valorPagamentoFinal) throws ControladorException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoMovimentoArrecadador(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection consultarDadosDiarios(int anoMesReferencia, int id, String descricao, int idElo) throws ControladorException;

	public Integer filtrarAvisoBancarioAbertoFechadoCount(AvisoBancarioHelper avisoBancarioHelper, AvisoBancarioHelper avisoBancarioHelperNovo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarAvisoBancarioAbertoFechadoParaPaginacao(AvisoBancarioHelper avisoBancarioHelper, Integer numeroPagina) throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoCliente(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, Integer numeroPagina,
			String valorPagamentoInicial, String valorPagamentoFinal) throws ControladorException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoCliente(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina) throws ControladorException;

	public Integer pesquisarPagamentoClienteCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ControladorException;

	public Integer pesquisarPagamentoHistoricoClienteCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos) throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoAvisoBancarioParaPaginacao(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina, String valorPagamentoInicial, String valorPagamentoFinal) throws ControladorException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoAvisoBancarioParaPaginacao(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial,
			String localidadeFinal, String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio,
			String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma,
			String[] idsDocumentosTipos, Integer numeroPagina) throws ControladorException;

	public Integer pesquisarPagamentoAvisoBancarioCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ControladorException;

	public Integer pesquisarPagamentoHistoricoAvisoBancarioCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos) throws ControladorException;

	public Integer pesquisarPagamentoImovelCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ControladorException;

	public Integer pesquisarPagamentoHistoricoImovelCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos) throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoImovelParaPaginacao(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina, String valorPagamentoInicial, String valorPagamentoFinal) throws ControladorException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoImovelParaPaginacao(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina) throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoImovelRelatorio(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoClienteRelatorio(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoAvisoBancarioRelatorio(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			String valorPagamentoInicial, String valorPagamentoFinal) throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoMovimentoArrecadadorRelatorio(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			String valorPagamentoInicial, String valorPagamentoFinal) throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoLocalidadeRelatorio(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			String valorPagamentoInicial, String valorPagamentoFinal) throws ControladorException;

	public Integer pesquisarPagamentoMovimentoArrecadadorCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ControladorException;

	public Integer pesquisarPagamentoLocalidadeCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ControladorException;

	public Integer pesquisarPagamentoHistoricoLocalidadeCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos) throws ControladorException;

	public Integer verificarExistenciaGuiaDevolucao(Integer idGuiaDevolucao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarAvisoBancarioRelatorio(AvisoBancarioHelper avisoBancarioHelper) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarAvisoDeducoesAvisoBancarioRelatorio(Integer idAvisoBancario) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarAvisoAcertosAvisoBancarioRelatorio(Integer idAvisoBancario) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarDadosDiariosArrecadacao(String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String idLocalidade, String idGerenciaRegional, String idArrecadador,
			String idElo, String[] idsImovelPerfil, String[] idsLigacaoAgua, String[] idsLigacaoEsgoto, String[] idsDocumentosTipos, String[] idsCategoria, String[] idsEsferaPoder)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarDadosDiariosArrecadacaoValoresDiarios(String idGerenciaRegional) throws ControladorException;

	public Collection<ArrecadadorMovimento> retornarColecaoMovimentoArrecadadores(FiltroArrecadadorMovimento filtro, Integer numeroPagina, String movimentoOcorrencia, String movimentoAceito,
			String movimentoAbertoFechado) throws ControladorException;

	public Collection<GuiaPagamentoRelatorioHelper> pesquisarGuiaPagamentoRelatorio(String[] ids) throws ControladorException;

	public Collection<GuiaDevolucaoRelatorioHelper> pesquisarGuiaDevolucaoRelatorio(String[] ids) throws ControladorException;

	public Collection<DevolucaoHistorico> pesquisarDevolucaoHistorico(FiltroDevolucaoHistorico filtroDevolucaoHistorico) throws ControladorException;

	public Collection<PagamentoHistorico> pesquisarPagamentoHistoricoLocalidadeRelatorio(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			String valorPagamentoInicial, String valorPagamentoFinal) throws ControladorException;

	public ArrecadacaoDadosDiarios consultarDadosDiarios(int idGerenciaRegional, int idLocalidade, int idElo) throws ControladorException;

	public void validarExibirInserirGuiaDevolucao(RegistroAtendimento ra, OrdemServico ordemServico) throws ControladorException;

	public void removerGuiaDevolucao(String idImovel, Usuario usuarioLogado, String[] ids, String pacoteNomeObjeto, OperacaoEfetuada operacaoEfetuada,
			Collection<UsuarioAcaoUsuarioHelper> acaoUsuarioHelper) throws ControladorException;

	public Collection<ArrecadadorMovimento> filtrarMovimentoArrecadadorParaPaginacao(String codigoBanco, String codigoRemessa, String descricaoIdentificacaoServico, String numeroSequencialArquivo,
			Date dataGeracaoInicio, Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim, String descricaoOcorrencia, String indicadorAceitacao, Integer numeroPagina,
			String indicadorAbertoFechado) throws ControladorException;

	public Integer filtrarMovimentoArrecadadoresCount(String codigoBanco, String codigoRemessa, String descricaoIdentificacaoServico, String numeroSequencialArquivo, Date dataGeracaoInicio,
			Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim, String descricaoOcorrencia, String indicadorAceitacao, String indicadorAbertoFechado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoLocalidadeAmbosRelatorio(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ControladorException;

	public Imovel pesquisarImovelPagamento(Integer idImovel) throws ControladorException;

	public Cliente pesquisarClientePagamento(Integer idCliente) throws ControladorException;

	public ClienteEndereco pesquisarClienteEnderecoPagamento(Integer idCliente) throws ControladorException;

	public IClienteFone pesquisarClienteFonePagamento(Integer idCliente) throws ControladorException;

	public Collection<ClienteImovel> pesquisarClientesImoveisPagamento(Integer idImovel) throws ControladorException;

	public Collection<Pagamento> pesquisarPagamentoMovimentoArrecadadorParaPaginacao(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal,
			String idAvisoBancario, String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim,
			Date dataPagamentoInicial, Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos,
			Integer numeroPagina, String valorPagamentoInicial, String valorPagamentoFinal) throws ControladorException;

	public Collection<Integer> pesquisarIdsLocalidadeComPagamentosOuDevolucoes() throws ControladorException;

	public Collection<AcertosAvisoBancarioHelper> pesquisarAcertosAvisoBancario(Integer idAvisoBancario, Integer indicadorArrecadacaoDevolucao) throws ControladorException;

	public Collection<DeducoesHelper> pesquisarDeducoesAvisoBancario(Integer idAvisoBancario) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarMovimentoArrecadadorParaRelatorio(String codigoBanco, String codigoRemessa, String descricaoIdentificacaoServico, String numeroSequencialArquivo, Date dataGeracaoInicio,
			Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim, String descricaoOcorrencia, String indicadorAceitacao, String indicadorAbertoFechado) throws ControladorException;


	public Integer filtrarMovimentoArrecadadoresRelatorioCount(String codigoBanco, String codigoRemessa, String descricaoIdentificacaoServico, String numeroSequencialArquivo, Date dataGeracaoInicio,
			Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim, String descricaoOcorrencia, String indicadorAceitacao, String indicadorAbertoFechado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarIndicadorGuiaPagamentoNoHistorico(Collection colecaoGuiasPagamento) throws ControladorException;

	public void transferirGuiaPagamentoParaHistorico(Collection<GuiaPagamento> colecaoGuiasPagamento) throws ControladorException;

	public void transferirPagamentoParaHistorico(Collection<Pagamento> colecaoPagamentos) throws ControladorException;

	public void transferirDevolucaoParaHistorico(Collection<Devolucao> colecaoDevolucoes) throws ControladorException;

	public void atualizarAnoMesArrecadacao(Integer anoMesArrecadacaoSistemaParametro) throws ControladorException;

	public Integer inserirAgenciaBancaria(Agencia agencia) throws ControladorException;

	public void atualizarAgenciaBancaria(Agencia agencia) throws ControladorException;

	public Integer inserirArrecadador(String idAgente, String idCliente, String inscricaoEstadual, String idImovel, Usuario usuarioLogado) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarGuiaPagemento(Integer idGuiaPagamento) throws ControladorException;

	public void gerarHistoricoParaEncerrarArrecadacaoMes(Integer anoMesReferenciaArrecadacao, Integer idLocalidade, int idFuncionalidadeIniciada) throws ControladorException;

	public boolean verificarExistenciaAgente(Integer codigoAgente) throws ControladorException;

	public void removerArrecadador(String[] ids, Usuario usuarioLogado) throws ControladorException;

	public void atualizarArrecadador(Arrecadador arrecadador, Usuario usuarioLogado) throws ControladorException;

	public void atualizarLogradouroCep(LogradouroCep logradouroCepAntigo, LogradouroCep logradouroCepNovo) throws ControladorException;

	public void atualizarLogradouroBairro(LogradouroBairro logradouroBairroAntigo, LogradouroBairro logradouroBairroNovo) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoImovelAmbosRelatorio(String idImovel) throws ControladorException;

	public Integer inserirContaBancaria(ContaBancaria contaBancaria) throws ControladorException;

	public void atualizarContaBancaria(ContaBancaria contaBancaria) throws ControladorException;

	public void removerGuiaPagamentoPagamento(Integer idPagamento) throws ControladorException;

	public Integer pesquisarQuantidadePagamentosPorDebitoACobrar(Integer idDebitoACobrar) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarMovimentoArrecadadoresRelatorio(Integer mesAnoReferencia, Integer idArrecadador, Integer idFormaArrecadacao, Date dataPagamentoInicial, Date dataPagamentoFinal)
			throws ControladorException;

	public void gerarResumoAcompanhamentoMovimentoArrecadadores(Usuario usuario, String mesAnoReferencia, Arrecadador arrecadador, ArrecadacaoForma arrecadacaoForma) throws ControladorException;

	public Collection<Integer> pesquisarIdsSetoresComPagamentosOuDevolucoes() throws ControladorException;

	public void gerarHistoricoConta(Integer anoMesReferenciaArrecadacao, Integer idSetorComercial, int idFuncionalidadeIniciada) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoHistoricoAvisoBancario(Integer idAvisoBancario) throws ControladorException;

	public Pagamento pesquisarPagamentoDeConta(Integer idConta) throws ControladorException;

	public Integer pesquisarIdPagamentoDoDebitoACobrar(Integer idDebitoACobrar) throws ControladorException;

	public Integer pesquisarIdPagamentoDaGuia(Integer idGuiaPagamento) throws ControladorException;

	public void alterarVencimentoItensDocumentoCobranca(Integer idCobrancaDocumento, Date dataEmissao) throws ControladorException;

	public Integer inserirContratoArrecadador(ArrecadadorContrato contrato, Collection<ArrecadadorContratoTarifa> colecaoArrecadadorContratoTarifa, Usuario usuarioLogado) throws ControladorException;

	public boolean verificarExistenciaArrecadador(Integer codigoArrecadador) throws ControladorException;

	public boolean verificarExistenciaContrato(String numeroContrato) throws ControladorException;

	public void atualizarContratoArrecadador(ArrecadadorContrato arrecadadorContrato, Collection<ArrecadadorContratoTarifa> colecaoArrecadadorContratoTarifa, Usuario usuarioLogado)
			throws ControladorException;

	public void removerContratoArrecadador(String[] ids, Usuario usuarioLogado) throws ControladorException;

	public Collection<Integer> pesquisarIdsCategoria() throws ControladorException;

	public PagamentosDevolucoesHelper filtrarPagamentos(FiltroPagamento filtroPagamento) throws ControladorException;

	public PagamentosDevolucoesHelper filtrarDevolucoes(FiltroDevolucao filtroDevolucao) throws ControladorException;

	public ValoresArrecadacaoDevolucaoAvisoBancarioHelper pesquisarValoresAvisoBancario(Integer idAvisoBancario) throws ControladorException;

	public void atualizarAvisoBancarioPagamentos(Collection<Integer> idsPagamentos, String arrecadacaoInformadoDepoisOrigem, String arrecadacaoCalculadoDepoisOrigem,
			String arrecadacaoInformadoDepoisDestino, String arrecadacaoCalculadoDepoisDestino, Integer idAvisoBancarioO, Integer idAvisoBancarioD) throws ControladorException;

	public void atualizarAvisoBancarioDevolucoes(Collection<Integer> idsDevolucoes, String devolucaoInformadoDepoisOrigem, String devolucaoCalculadoDepoisOrigem,
			String devolucaoInformadoDepoisDestino, String devolucaoCalculadoDepoisDestino, Integer idAvisoBancarioO, Integer idAvisoBancarioD) throws ControladorException;

	public Collection<Integer> filtrarIdsMovimentoArrecadador(String codigoBanco, String codigoRemessa, String descricaoIdentificacaoServico, String numeroSequencialArquivo, Date dataGeracaoInicio,
			Date dataGeracaoFim, Date ultimaAlteracaoInicio, Date ultimaAlteracaoFim, String descricaoOcorrencia, String indicadorAceitacao, String indicadorAbertoFechado) throws ControladorException;

	public Collection<MovimentoArrecadadoresPorNSAHelper> gerarMovimentoArrecadadoresNSA(Collection<Integer> idsArrecadadorMovimento, Integer codigoFormaArrecadacao) throws ControladorException;

	public String obterIdentificacaoPagamento(Integer tipoPagamento, Integer idLocalidade, Integer matriculaImovel, String mesAnoReferenciaConta, Integer digitoVerificadorRefContaModulo10,
			Integer idTipoDebito, String anoEmissaoGuiaPagamento, String sequencialDocumentoCobranca, Integer idTipoDocumento, Integer idCliente, Integer seqFaturaClienteResponsavel,
			String idGuiaPagamento) throws ControladorException;

	public String obterRepresentacaoNumericaCodigoBarra(Integer tipoPagamento, BigDecimal valorCodigoBarra, Integer idLocalidade, Integer matriculaImovel, String mesAnoReferenciaConta,
			Integer digitoVerificadorRefContaModulo10, Integer idTipoDebito, String anoEmissaoGuiaPagamento, String sequencialDocumentoCobranca, Integer idTipoDocumento, Integer idCliente,
			Integer seqFaturaClienteResponsavel, String idGuiaPagamento) throws ControladorException;

	public Collection<Conta> pesquisarContaComPagamentoHistorico() throws ControladorException;

	public Integer inserirPagamentos(Collection<Pagamento> colecaoPagamento, Usuario usuarioLogado, AvisoBancario avisoBancario) throws ControladorException;

	public Integer inserirPagamentosCodigoBarras(Collection<Pagamento> colecaoPagamentos, Collection<Devolucao> colecaoDevolucoes, Usuario usuarioLogado, AvisoBancario avisoBancario)
			throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoDeContas(Collection colecaoConta) throws ControladorException;

	public Object[] gerarColecaoDadosPagamentoPelaData(String dataPagamento, Integer idCliente, Integer anoMesArrecadacao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public void atualizarSituacaoExpurgoPagamento(Collection colecaoPagamento) throws ControladorException;

	public Collection<RelatorioComparativoFatArrecExpurgoBean> pesquisarDadosComparativosFaturamentoArrecadacaoExpurgo(Integer anoMesReferencia, String idGerenciaRegional, String idUnidadeNegocio)
			throws ControladorException;

	public int pesquisarQuantidadeDadosComparativosFaturamentoArrecadacaoExpurgo(Integer anoMesReferencia, String idGerenciaRegional, String idUnidadeNegocio) throws ControladorException;

	public GuiaDevolucao pesquisarGuiaDevolucao(Integer guiaDevolucaoId) throws ControladorException;

	public Date pesquisarDataPagamentoDeConta(Integer idConta) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public ContaGeral verificarLocalidadeContaGuiaDevolucao(Collection colecaoConta, String idLocalidade) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarContaParaGuiaDevolucao(Imovel imovel, Integer anoMesReferencia) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public DebitoACobrarGeral verificarDebitoACobrarParaGuiaDevolucao(Collection colecaoDebitoACobrar, Integer idImovel, Integer idLocalidade, OrdemServico ordemServico) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDebitoACobrarParaGuiaDevolucao(Imovel imovel, Integer idDebitoACobrar) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarGuiaPagamentoParaGuiaDevolucao(Integer idImovel, Integer idCliente, Integer idGuiaPagamento) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public GuiaPagamentoGeral verificarGuiaPagamentoParaGuiaDevolucao(Collection colecaoGuiaPagamento, RegistroAtendimento registroAtendimento, Integer idClienteRegistroAtendimento,
			Integer idLocalidadeImovel, OrdemServico ordemServico) throws ControladorException;

	public Object[] pesquisarParmsDebitoAutomatico(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public List pesquisarLocalidadeComImoveisComSituacaoLigadoEmAnalise() throws ControladorException;

	public void atualizarLigacaoAguaLigadoAnaliseParaLigado(Integer idFuncionalidadeIniciada, Integer idLocalidade) throws ControladorException;

	public boolean verificarExistenciaPagamentoEntradaParcelamento(Integer idImovel, Date dataParcelamento) throws ControladorException;

	public List<RelatorioAnaliseArrecadacaoBean> pesquisarAnaliseArrecadacao(PesquisarAnaliseArrecadacaoHelper helper) throws ControladorException;

	public List<RelatorioAnaliseAvisosBancariosBean> pesquisarAnaliseAvisosBancarios(PesquisarAnaliseAvisosBancariosHelper helper) throws ControladorException;

	public List<RelatorioAvisoBancarioPorContaCorrenteBean> pesquisarAvisoBancarioPorContaCorrente(PesquisarAvisoBancarioPorContaCorrenteHelper helper) throws ControladorException;

	public void atualizarDiferencaAcumuladaNoMes(int idFuncionalidadeIniciada, int anoMesArrecadacao) throws ControladorException;

	public boolean verificarExistenciaResumoArrecadacaoParaAnoMes(Integer anoMesReferencia) throws ControladorException;

	public RegistroHelperCodigoA validarArquivoMovimentoArrecadador(String codigoRegistro, String linha, Short codigoArrecadador, String nomeArrecadador, String idTipoMovimento,
			ArrecadadorContrato arrecadadorContrato, Integer numeroSequecialArquivoRetornoCodigoBarras, Integer numeroSequencialArquivoRetornoDebitoAutomatico,
			Integer numeroSequencialArquivoEnvioDebitoAutomatico, Integer idArrecadador) throws ControladorException;

	public RegistroHelperCodigo0 validarArquivoMovimentoArrecadadorFichaCompensacao(String codigoRegistro, String linha, Short codigoArrecadador, String nomeArrecadador, String idTipoMovimento,
			ArrecadadorContrato arrecadadorContrato, Integer numeroSequecialArquivoRetornoFichaComp, Integer idArrecadador) throws ControladorException;

	public Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> filtrarDadosDiariosArrecadacao(int anoMesInicial, int anoMesFinal, FiltroConsultarDadosDiariosArrecadacao filtro)
			throws ControladorException;

	public boolean verificarExistenciaDadosDiariosArrecadacao(int anoMesInicial, int anoMesFinal, FiltroConsultarDadosDiariosArrecadacao filtro) throws ControladorException;

	public DebitoACobrarGeral pesquisarDebitoACobrarGeralDigitado(String idImovel, String idDebitoACobrar) throws ControladorException;

	public void verificarLocalidadeDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral, String idLocalidade) throws ControladorException;

	public DebitoACobrarGeral verificarExistenciaDebitoACobrarGeralComTipoDebito(DebitoTipo tipoDebito, String idImovel, BigDecimal valorInformado) throws ControladorException;

	public Collection<Banco> pesquisarBancoDebitoAutomatico() throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisBancoDebitoAutomatico(String[] bancos) throws ControladorException;

	public Integer countImoveisBancoDebitoAutomatico(String[] bancos, Integer anoMesInicial, Integer anoMesFinal, Date dataVencimentoInicial, Date dataVencimentoFinal, String indicadorContaPaga,
			Integer somenteDebitoAutomatico) throws ControladorException;
	
	@SuppressWarnings("rawtypes")
	public Collection selecionarImoveisBancoDebitoAutomatico(String[] bancos, Integer anoMesInicial, Integer anoMesFinal, Date dataVencimentoInicial, Date dataVencimentoFinal,
			String indicadorContaPaga) throws ControladorException;

	public Collection<Object[]> consultarNomeArrecadadorNomeAgencia(String idArrecadadorMovimento) throws ControladorException;

	public Integer pesquisarPagamentoCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario, String idArrecadador,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial, Date dataPagamentoFinal,
			String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial, String valorPagamentoFinal)
			throws ControladorException;

	public PagamentoHelperCodigoBarras processarFaturaComCodigoBarras(String codigoBarras, SistemaParametro sistemaParametro) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer inserirPagamentosFaturasEspeciais(Integer idFuncionalidadeIniciada, Map parametros) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoEntidadesBeneficentesAnalitico(String mesAnoInicial, String mesAnoFinal, String idEntidadeBeneficente, String idGerenciaRegional, String idUnidadeNegocio,
			String idLocalidade, int opcaoTotalizacao) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoEntidadesBeneficentesSintetico(String mesAnoInicial, String mesAnoFinal, String idEntidadeBeneficente, String idGerenciaRegional, String idUnidadeNegocio,
			String idLocalidade, int opcaoTotalizacao) throws ControladorException;

	public int pesquisarPagamentoEntidadesBeneficentesCount(String mesAnoInicial, String mesAnoFinal, String idEntidadeBeneficente, String idGerenciaRegional, String idUnidadeNegocio,
			String idLocalidade, int opcaoTotalizacao, String relatorioTipo) throws ControladorException;

	public Integer pesquisarContaBancaria(Short codigoAgente) throws ControladorException;

	public Arrecadador verificarArrecadacaoFormaCartaoCredito(Integer idArrecadador) throws ControladorException;

	public void registrarMovimentoCartaoCredito(Arrecadador arrecadador, StringBuilder stringBuilderTxt, int quantidadeRegistros, Usuario usuarioLogado) throws ControladorException;

	public void verificaPagamentoDebitoACobrarParcelamento(Integer idDebitoACobrar, Integer numeroParcelasAntecipadas) throws ControladorException;

	public Object distribuirdadosRegistroMovimentoArrecadador(String linha, String descricaoOcorrencia) throws ControladorException;

	public Object gerarDebitoCreditoParcelasAntecipadas(Integer idImovel, CobrancaDocumentoItem cobrancaDocumentoItem, Usuario usuarioLogado) throws ControladorException;

	public DebitoACobrar pesquisarDebitoACobrarJurosParcelamento(Integer idParcelamento) throws ControladorException;

	public void atualizarNumeroParcelasBonus(DebitoACobrar debitoACobrar) throws ControladorException;

	public Integer pesquisarPagamentoHistoricoAvisoBancarioCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ControladorException;

	public Integer verificarExistenciaCreditoARealizar(Integer idImovel, Integer anoMesReferenciaConta) throws ControladorException;

	public Integer verificarExistenciaCreditoARealizarHistorico(Integer idImovel, Integer anoMesReferenciaConta) throws ControladorException;

	public Integer verificarExistenciaGuiaDevolucao(Integer idImovel, Integer anoMesReferenciaGuiaDevolucao) throws ControladorException;

	public Integer filtrarArquivoTextoRoteiroEmpresaCount(ConsultarArquivoTextoRoteiroEmpresaHelper helper) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection filtrarArquivoTextoRoteiroEmpresaParaPaginacao(ConsultarArquivoTextoRoteiroEmpresaHelper helper, Integer numeroPagina) throws ControladorException;

	public Date pesquisarDataProcessamentoMes(Integer anoMes) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public ContaGeral retornaContaGeral(Collection colecaoConta) throws ControladorException;

	public void validarGerarRelatorioAnalisePagamentoCartaoDebito(ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper helper) throws ControladorException;

	public Integer relatorioAnalisePagamentoCartaoDebitoCount(ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper helper) throws ControladorException;

	public List<RelatorioAnalisePagamentoCartaoDebitoBean> pesquisarBeansRelatorioAnalisePagamentoCartaoDebito(ConsultarRelatorioAnalisePagamentoCartaoDebitoHelper helper) throws ControladorException;

	public PagamentoHelperCodigoBarras processarPagamentosFichaCompensacao(SistemaParametro sistemaParametro, Date dataPagamento, BigDecimal valorPagamento, String nossoNumero,
			Integer idFormaArrecadacao, Usuario usuarioLogado) throws ControladorException;

	public RegistroHelperFichaCompensacao distribuirDadosFichaCompensacao(String codigoBarras) throws ControladorException;

	public BigDecimal pesquisarFaturamentoCobradoEmConta(Integer anoMes) throws ControladorException;

	public BigDecimal pesquisarFaturamentoCobradoEmContaComQuebra(Integer anoMes, Integer idGerenciaRegional, Integer idCategoria) throws ControladorException;

	public Collection<Integer> verificarBloqueioGuiaPagamento(Collection<GuiaPagamento> guiasPagamentos) throws ControladorException;

	public List<RelatorioDocumentoNaoAceitosBean> pesquisarDocumentosNaoAceitos(Arrecadador arrecadador, String periodoInicial, String periodoFinal, Integer movimentoArrecadadorCodigo,
			AvisoBancario avisoBancario, ArrecadacaoForma arrecadacaoForma) throws ControladorException;

	public List<RelatorioTranferenciaPagamentoBean> pesquisarTransfereciasPagamento(Arrecadador arrecadador, String periodoInicial, String periodoFinal, AvisoBancario avisoBancario,
			ArrecadacaoForma arrecadacaoForma, DebitoTipo debitoTipo, DocumentoTipo documentoTipo) throws ControladorException;

	public Collection<PagamentoDocumentosNaoAceitosHelper> pesquisarPagamentosDocumentosNaoAceitos(InformarAcertoDocumentosNaoAceitosPagamentoHelper helper) throws ControladorException;

	public BigDecimal pesquisarValorPagamento(Integer idPagamento) throws ControladorException;

	public GuiaPagamentoValoresHelper pesquisarGuiaPagamentoDigitada(String idImovel, String idGuiaPagamento) throws ControladorException;

	public DebitoACobrarValoresHelper pesquisarDebitoACobrarImovelDigitado(String idImovel, String idDebitoACobrar) throws ControladorException;

	public void efetuarAcertosPagamentos(InformarAcertoDocumentosNaoAceitosHelper helper) throws ControladorException;

	public Integer pesquisarLocalidadeGuiaPagamento(Integer idGuia) throws ControladorException;

	public Integer pesquisarLocalidadeConta(Integer idConta) throws ControladorException;

	public Integer pesquisarLocalidadeDebitoACobrar(Integer idDebitoACobrar) throws ControladorException;

	public Integer pesquisarPagamentoHistoricoClienteCount(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarPagamentoHistoricoClienteRelatorio(String idImovel, String idCliente, String idTipoRelacao, String localidadeInicial, String localidadeFinal, String idAvisoBancario,
			String idArrecadador, String periodoArrecadacaoInicial, String periodoArrecadacaoFinal, String periodoPagamentoInicio, String periodoPagamentoFim, Date dataPagamentoInicial,
			Date dataPagamentoFinal, String[] idsPagamentosSituacoes, String[] idsDebitosTipos, String[] idsArrecadacaoForma, String[] idsDocumentosTipos, String valorPagamentoInicial,
			String valorPagamentoFinal) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarImoveisBancoDebitoAutomaticoEPorGrupoFaturamento(String[] bancos, Integer idGrupoFaturamento) throws ControladorException;

	public Integer countImoveisBancoDebitoAutomaticoPorGrupoFaturamento(String[] bancos, Integer anoMesInicial, Integer anoMesFinal, Date dataVencimentoInicial, Date dataVencimentoFinal,
			String indicadorContaPaga, Integer idGrupoFaturamento, Integer somenteDebitoAutomatico) throws ControladorException;

	public PagamentoHistorico pesquisarPagamentoDeContaEmHistorico(Integer idConta) throws ControladorException;

	public Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> filtrarDadosDiariosFormasArrecadacaoComTarifa(int anoMesInicial, int anoMesFinal,
			FiltroConsultarDadosDiariosArrecadacao filtro) throws ControladorException;

	public boolean verificarExistenciaDadosDiariosArrecadacaoAuxiliar(int anoMesInicial, int anoMesFinal, FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro) throws ControladorException;

	public Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> filtrarDadosDiariosArrecadacaoAuxiliar(int anoMesInicial, int anoMesFinal,
			FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro) throws ControladorException;

	public Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> filtrarDadosDiariosArrecadacaoAuxiliarFormasArrecadacaoComTarifa(int anoMesInicial, int anoMesFinal,
			FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro) throws ControladorException;

	public void gerarDadosRelatorioBIG(Integer anoMesReferencia, Localidade localidade, Integer idFuncionalidadeIniciada) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarDadosRelatorioBIG(Integer anoMesReferencia) throws ControladorException;

	public void atualizarGuiasPagamentoNaoPagasAtePeriodo(Integer idFuncionalidadeIniciada, Date dataVencimentoLimite, Integer financiamentoTipoServico, Integer idLocalidade)
			throws ControladorException;

	public Collection<Integer> pesquisarIdsLocalidadeComGuiasPagamentoNaoPagas(Date dataVencimentoLimite, Integer financiamentoTipoServico) throws ControladorException;

	public void processarPagamentosDiferencaDoisReais(Integer anoMesReferenciaArrecadacao, Localidade localidade, Integer idFuncionalidadeIniciada) throws Exception;

	public Collection<Pagamento> obterPagamentos(Collection<Integer> pagamentos) throws ControladorException;

	public void recuperarCredito(Collection<Pagamento> pagamentos, Usuario usuarioLogado, CreditoTipo creditoTipo, CreditoOrigem creditoOrigem, boolean indicadorIncluirCredito,
			Integer idSituacaoPagamento) throws ControladorException;

	public void atualizarIndicadorDebitoAutomaticoComDataExclusao(Integer idImovel) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Collection pesquisarClienteGuiaPagamentoECliente(Integer idGuiaPagamento) throws ControladorException;

	public List<ResumoCreditosAvisosBancariosDTO> pesquisarResumoCreditosAvisosBancarios(Date data) throws ControladorException;

	public Object[] pesquisarPagamentoInconformeImovel(String idImovel) throws ControladorException;

	public List<ArrecadadorMovimentoItemDTO> obterItensPorAviso(Integer idAvisoBancario) throws ControladorException;
}