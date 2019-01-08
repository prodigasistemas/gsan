package gcom.spcserasa;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.cobranca.CobrancaDebitoSituacao;
import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorContrato;
import gcom.cobranca.NegativadorExclusaoMotivo;
import gcom.cobranca.NegativadorMovimento;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.bean.ComandoNegativacaoHelper;
import gcom.cobranca.bean.ComandoNegativacaoTipoCriterioHelper;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.cobranca.bean.ParametrosComandoNegativacaoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaHistorico;
import gcom.gui.cobranca.spcserasa.RelatorioNegativacoesExcluidasSomatorioDadosParcelamentoHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.bean.InserirComandoNegativacaoPorCriterioHelper;
import gcom.spcserasa.bean.NegativadorMovimentoHelper;
import gcom.util.ControladorException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SuppressWarnings("rawtypes")
public interface ControladorSpcSerasaLocal extends javax.ejb.EJBLocalObject {

	public Integer pesquisarComandoNegativacao(ComandoNegativacaoHelper comandoNegativacaoHelper) throws ControladorException;

	public Collection pesquisarComandoNegativacaoParaPaginacao(ComandoNegativacaoHelper comandoNegativacaoHelper, Integer numeroPagina)
			throws ControladorException;
	public Integer pesquisarDadosInclusoesComandoNegativacao(Integer idComandoNegativacao) throws ControladorException;

	public Collection pesquisarDadosInclusoesComandoNegativacaoParaPaginacao(Integer idComandoNegativacao, Integer numeroPagina) throws ControladorException;

	public ParametrosComandoNegativacaoHelper pesquisarParametrosComandoNegativacao(Integer idComandoNegativacao) throws ControladorException;

	public Boolean verificarExistenciaNegativacaoImovel(Integer idImovel) throws ControladorException;

	public Date pesquisarUltimaDataRealizacaoComando(Integer idNegativador, Integer icSimulacao) throws ControladorException;

	public Integer inserirComandoNegativacao(InserirComandoNegativacaoPorCriterioHelper helper) throws ControladorException;

	public Integer gerarMovimentoInclusaoNegativacao(Integer idComandoNegativacao, Short tipoComando, String comunicacaoInterna, Integer idNegativador,
			int idUsuarioResponsaval, Collection ObjectImovel, Date dataPrevista, String indicadorBaixaRenda, String indicadorContaNomeCliente,
			String indicadorImovelCategoriaPublico) throws ControladorException;

	public void excluirNegativacaoOnLine(Imovel imovel, NegativadorMovimentoReg negativadorMovimentoReg, Collection itensConta, Collection itensGuiaPagamento,
			NegativadorExclusaoMotivo negativadorExclusaoMotivo, Date dataExclusao, Usuario usuario, Usuario usuarioLogado) throws ControladorException;

	public void gerarResumoDiarioNegativacao(Integer idFuncionalidadeIniciada, Integer idRota) throws ControladorException;

	public List consultarLocalidadeParaGerarResumoDiarioNegativacao() throws ControladorException;

	public List consultarNegativacaoParaGerarResumoDiarioNegativacao(Integer idLocalidade) throws ControladorException;

	public Collection consultarNegativadoresParaExclusaoMovimento() throws ControladorException;

	public Collection gerarMovimentoExclusaoNegativacao(Integer[] id) throws ControladorException;

	public Collection gerarMovimentoExclusaoNegativacao(Integer idFuncionalidadeIniciada, Integer[] id) throws ControladorException;

	public Collection gerarArquivoTXTMovimentoExclusaoNegativacao(Integer idMovimento) throws ControladorException;

	public Collection pesquisarComandoNegativacaoHelper(ComandoNegativacaoHelper comandoNegativacaoHelper) throws ControladorException;

	public Collection pesquisarComandoNegativacaoTipoCriterio(ComandoNegativacaoTipoCriterioHelper comandoNegativacaoTipoCriterioHelper)
			throws ControladorException;

	public Collection pesquisarComandoNegativacaoTipoMatricula(ComandoNegativacaoHelper comandoNegativacaoHelper, Integer numeroPagina)
			throws ControladorException;

	public Collection pesquisarComandoNegativacaoTipoMatricula(ComandoNegativacaoHelper comandoNegativacaoHelper) throws ControladorException;

	public Collection registrarNegativadorMovimentoRetorno(Integer idFuncionalidadeIniciada) throws ControladorException;

	public Collection pesquisarComandoNegativacaoTipoCriterio(ComandoNegativacaoTipoCriterioHelper comandoNegativacaoTipoCriterioHelper, Integer numeroPagina)
			throws ControladorException;

	public Integer pesquisarNegativadorMovimentoCount(NegativadorMovimentoHelper negativadorMovimentoHelper) throws ControladorException;

	public Integer verificarTotalRegistrosAceitos(Integer idNegativadorMovimento) throws ControladorException;

	public Collection pesquisarNegativadorMovimentoRegistroAceito(NegativadorMovimentoHelper negativadorMovimentoHelper) throws ControladorException;

	public void removerComandoNegativacaoPorCriterio(String[] ids) throws ControladorException;

	public void atualizarComandoNegativacao(InserirComandoNegativacaoPorCriterioHelper helper) throws ControladorException;

	public Collection pesquisarNegativadorMovimento(NegativadorMovimentoHelper negativadorMovimentoHelper, Integer numeroPagina) throws ControladorException;

	public Collection pesquisarNegativadorMovimentoRegistroAceito(NegativadorMovimentoHelper negativadorMovimentoHelper, Integer numeroPagina)
			throws ControladorException;

	public Collection pesquisarNegatiacaoParaImovel(Imovel imovel, Negativador negativador) throws ControladorException;

	public Integer pesquisarRelatorioAcompanhamentoClientesNegativadorCount(DadosConsultaNegativacaoHelper helper) throws ControladorException;

	public Collection pesquisarRelatorioAcompanhamentoClientesNegativador(DadosConsultaNegativacaoHelper helper) throws ControladorException;

	public BigDecimal pesquisarSomatorioValorDebito(NegativadorMovimentoReg negativadorMovimentoReg, CobrancaDebitoSituacao cobrancaDebitoSituacao)
			throws ControladorException;

	public ImovelCobrancaSituacao getImovelCobrancaSituacao(Imovel imovel) throws ControladorException;

	public Collection pesquisarRelatorioNegativacoesExcluidas(DadosConsultaNegativacaoHelper helper) throws ControladorException;

	public Integer pesquisarRelatorioNegativacoesExcluidasCount(DadosConsultaNegativacaoHelper helper) throws ControladorException;

	public Collection pesquisarImovelNegativado(Imovel imovel, Negativador negativador) throws ControladorException;

	public NegativadorMovimentoReg pesquisarNegativadorMovimentoRegInclusao(Imovel imovel, Negativador negativador) throws ControladorException;

	public void apagarResumoNegativacao() throws ControladorException;

	public void apagarResumoNegativacao(Integer numeroPenultimaExecResumoNegat) throws ControladorException;

	public boolean verificarExistenciaComandoNegativador(String idNegativador, Date dataPrevista) throws ControladorException;

	public Date pesquisarDataExclusaoNegativacao(int idImovel, int idNegativacaoComando) throws ControladorException;

	public NegativacaoCriterio pesquisarComandoNegativacaoSimulado(Integer idComandoNegativacao) throws ControladorException;

	public Collection pesquisarNegativadorResultadoSimulacao(Integer idNegativacaoComando) throws ControladorException;

	public NegativacaoCriterio pesquisarNegativacaoCriterio(Integer idNegativacaoComando) throws ControladorException;

	public Date getDataUltimaAtualizacaoResumoNegativacao(Integer numeroExecucaoResumoNegativacao) throws ControladorException;

	public NegativacaoComando consultarNegativacaoComandadoParaExecutar() throws ControladorException;

	public NegativacaoComando consultarNegativacaoComando(Integer idNegativacaoComando) throws ControladorException;

	public NegativadorContrato consultarNegativadorContratoVigente(Integer negativador) throws ControladorException;

	public List pesquisarParametroNegativacaoCriterio(Integer idNegativacaoCriterio) throws ControladorException;

	public Collection pesquisarRotasImoveis() throws ControladorException;

	public Collection pesquisarRotasImoveisComandoSimulacao(Integer idNegativacaoComando) throws ControladorException;

	public Integer gerarRegistroDeInclusaoTipoHeader(Short tipoComando, Integer quantidadeRegistro, Negativador n, NegativadorContrato nContrato,
			NegativacaoComando nComando, NegativacaoCriterio nCriterio, Integer idNegativacaoMovimento) throws ControladorException;

	public Object[] pesquisarQuantidadeInclusaoNegativacao(Integer idNegativacaoComando) throws ControladorException;

	public Integer pesquisarQuantidadeInclusaoItemNegativacao(Integer idNegativacaoComando) throws ControladorException;

	public Object[] pesquisarQuantidadeInclusaoNegativacaoSimulacao(Integer idNegComando) throws ControladorException;

	public void gerarArquivoNegativacao(NegativadorMovimento negativadorMovimento, boolean trailler) throws ControladorException;

	public void gerarMovimentodeInclusaoNegativacaoPorCriterio(Integer idLocalidade, NegativacaoCriterio nCriterio, Negativador n, NegativacaoComando nComando,
			NegativadorContrato nContrato, NegativadorMovimento negMovimento, Integer idFuncionalidadeIniciada) throws ControladorException;

	public void atualizarNumeroExecucaoResumoNegativacao(Integer idFuncionalidadeIniciada) throws ControladorException;

	public boolean verificarNegativacaoDoClienteImovel(Integer idCliente, Integer idImovel) throws ControladorException;

	public Collection pesquisarClienteImovelParaNegativacao(Integer idImovel) throws ControladorException;

	public Object[] pesquisarDadosImovelParaNegativacao(Integer idImovel) throws ControladorException;

	public List pesquisarRotasPorCobrancaGrupoParaNegativacao(NegativacaoCriterio nCriterio) throws ControladorException;

	public List pesquisarRotasPorGerenciaRegionalParaNegativacao(NegativacaoCriterio nCriterio) throws ControladorException;

	public List pesquisarRotasPorUnidadeNegocioParaNegativacao(NegativacaoCriterio nCriterio) throws ControladorException;

	public List pesquisarRotasPorLocalidadeParaNegativacao(NegativacaoCriterio nCriterio) throws ControladorException;

	public List pesquisarRotasPorLocalidadesParaNegativacao(NegativacaoCriterio nCriterio) throws ControladorException;

	public Object[] validarArquivoMovimentoRetorno(StringBuilder stringBuilderTxt, Negativador negativador) throws ControladorException;

	public void inserirProcessoRegistrarNegativadorMovimentoRetorno(Usuario usuario) throws ControladorException;

	public List pesquisarNegativadorMovimentoRegItens(Integer idNegativadorMovimentoReg) throws ControladorException;

	public BigDecimal pesquisarSomatorioNegativadorMovimentoRegItens(Integer idNegativadorMovimentoReg, Integer idCobrancaDebitoSituacao)
			throws ControladorException;

	public List consultarRotasParaGerarResumoDiarioNegativacao() throws ControladorException;

	public RelatorioNegativacoesExcluidasSomatorioDadosParcelamentoHelper pesquisarNegativadorMovimentoRegParcelamento(Integer idNegativadorMovimentoReg)
			throws ControladorException;

	public Object[] pesquisarSomatorioValorPagoEValorCancelado(Integer idNegativadorMovimentoReg) throws ControladorException;

	public Object[] pesquisarDadosParcelamentoRelatorioAcompanhamentoClientesNegativados(Integer idNegativadorMovimentoReg) throws ControladorException;

	public void atualizarNegativadorMovimentoRegItemAPartirPagamento(Pagamento pagamento) throws ControladorException;

	public void verificaAssociacaoPagamentoComItensNegativacao(Pagamento pagamento) throws ControladorException;

	public void verificaAssociacaoPagamentoComItensNegativacao(Pagamento pagamento, Pagamento pagamentoAnterior) throws ControladorException;

	public void atualizarNegativadorMovimentoRegItemAPartirConta(Conta conta) throws ControladorException;

	public void verificarExistenciaItensNegativacaoParaContaIncluida(Conta conta) throws ControladorException;

	public void verificarRelacaoDoCancelamentoComItensNegativacao(Conta conta, Integer idContaCanceladaRetificacao) throws ControladorException;

	public Collection obterItensNegativacaoAssociadosAConta(Integer idImovel, Integer referencia) throws ControladorException;

	public void atualizarNegativadorMovimentoRegItem(Integer idNegativadorMovimentoRegItem, Date dataSituacaoDebito, Integer idCobrancaDebitoSituacao,
			Integer idContaCanceladaPorRetificacao, Integer idContaRetificadaECancelada) throws ControladorException;

	public void verificarRelacaoDoDesfazerCancelamentoOuRetificacaoComItensNegativacao(Collection colecaoNegativadorMovimentoRegItem,
			Integer situacaoAtualConta, Conta contaCanceladaOuCanceladaPorRetificacao, Conta contaRetificada) throws ControladorException;

	public Collection pesquisarNegativadorMovimentoRegItemAssociadosAGuiaPagamento(Integer idGuia) throws ControladorException;

	public void verificarRelacaoDoCancelamentoComItensNegativacao(GuiaPagamento guiaPagamento) throws ControladorException;

	public void verificarRelacaoDoParcelamentoComItensNegativacao(Parcelamento parcelamento, Conta conta, GuiaPagamento guiaPagamento)
			throws ControladorException;

	public void verificarRelacaoDaTransfereciaPHistoricoComItensNegativacao(Collection<GuiaPagamento> colecaoGuiasPagamento) throws ControladorException;

	public void verificarRelacaoDaTransfereciaPHistoricoComItensNegativacao(ContaHistorico contaHistorico) throws ControladorException;

	public void gerarResumoNegativacao(Integer idFuncionalidadeIniciada, Integer idRota) throws ControladorException;

	public void acompanharPagamentoDoParcelamento(Integer idFuncionalidadeIniciada, Integer idRota) throws ControladorException;

	public List consultarSetorParaGerarResumoDiarioNegativacao() throws ControladorException;

	public boolean existeOcorrenciaMovimentoExclusaoIncompleto() throws ControladorException;

	public void determinarConfirmacaoDaNegativacao(Integer idFuncionalidadeIniciada, Integer idLocalidade) throws ControladorException;

	public List consultarLocalidadeParaDeterminarConfirmacaoDaNegativacao() throws ControladorException;

	public Collection consultarDadosNegativadorMovimentoReg(Integer idImovel) throws ControladorException;

	public boolean verificarExistenciaDeInclusaoNoNegativadorParaImovel(Integer idImovel, Integer idNegativador) throws ControladorException;

	public Collection retirarContaPagaOuParceladaEEntradaParcelamento(Collection colecaoContasValores) throws ControladorException;

	public Collection retirarGuiaPagamentoDeEntradaParcelamento(Collection colecaoGuiaPagamentoValoresHelper) throws ControladorException;

	public Collection pesquisarNegativadorRetornoMotivoDoReg(Integer idImovel) throws ControladorException;

	public void atualizarIndicadorCorrecaoEUsuarioCorrecao(Integer usuarioCorrecao, Short indicadorCorrecao, Collection colecaoIdsNegativadorMovimentoReg)
			throws ControladorException;

	public boolean existeImovelCobrancaSituacaoParaImovel(Integer codigoImovel, Integer codigoCobrancaSituacao) throws ControladorException;

	public Integer pesquisarRelatorioAcompanhamentoClientesNegativadorCountClientes(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido) throws ControladorException;

	public BigDecimal pesquisarRelatorioAcompanhamentoClientesNegativadorValorDebitosUnidade(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido) throws ControladorException;

	public BigDecimal pesquisarRelatorioAcompanhamentoClientesNegativadorValorPagoUnidade(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido) throws ControladorException;

	public Integer pesquisarRelatorioAcompanhamentoClientesNegativadorCountValorPago(DadosConsultaNegativacaoHelper helper,
			NegativadorMovimentoReg negativadorMovimentoReg, Integer idGerencia, Integer idUnidade, Short indicadorExcluido) throws ControladorException;

	public void desfazerAtualizacaoExecucaoDescontinuada(Integer idFuncionalidadeIniciada, NegativacaoComando nComando) throws ControladorException;

	public Collection obterNegativadorMovimentoRegItemAssociadosAGuiaPagamento(Integer idGuia) throws ControladorException;
	
	public Integer gerarNegativadorMovimento(Integer idNegativador, Integer numeroSequencialEnvio, Integer idNegativadorComando) throws ControladorException;
	
	public boolean isImovelNegativado(Integer idImovel) throws ControladorException;
	
}
