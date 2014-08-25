package gcom.batch;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoCategoria;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.ClienteGuiaPagamento;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaCategoria;
import gcom.faturamento.conta.ContaCategoriaConsumoFaixa;
import gcom.faturamento.conta.ContaImpostosDeduzidos;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.faturamento.credito.CreditoRealizadoCategoria;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarCategoria;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoCobradoCategoria;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ErroRepositorioException;

import java.util.Collection;
import java.util.Date;

public interface IRepositorioBatch {

	@SuppressWarnings("rawtypes")
	public Collection pesquisarRotasProcessamentoBatchFaturamentoComandado(Integer idFaturamentoAtividadeCronograma) throws ErroRepositorioException;

	public Collection<ProcessoIniciado> pesquisarProcessosIniciadosProntosParaEncerramento() throws ErroRepositorioException;

	public Collection<FuncionalidadeIniciada> pesquisarFuncionaldadesIniciadasProntasParaEncerramento() throws ErroRepositorioException;

	public Collection<ProcessoIniciado> pesquisarProcessosIniciadosExecucaoFalha() throws ErroRepositorioException;

	public Collection<FuncionalidadeIniciada> pesquisarFuncionaldadesIniciadasExecucaoFalha() throws ErroRepositorioException;

	public Collection<Object[]> pesquisarFuncionaldadesIniciadasProntasExecucao() throws ErroRepositorioException;

	public int pesquisarFuncionaldadesIniciadasConcluidasErro(int idFuncionalidadeIniciada) throws ErroRepositorioException;

	public void inserirColecaoObjetoParaBatch(Collection<? extends Object> colecaoObjetos) throws ErroRepositorioException;
	
	public void inserirColecaoObjetoParaBatchGerencial(Collection<? extends Object> colecaoObjetos) throws ErroRepositorioException;	

	public void atualizarColecaoObjetoParaBatch(Collection<? extends Object> colecaoObjetos) throws ErroRepositorioException;

	public Integer pesquisarQuantidadeFuncionaldadesIniciadasForaOrdemExecucao(int idSequencialExecucao, int idProcessoIniciado) throws ErroRepositorioException;

	public void iniciarFuncionalidadeIniciadaRelatorio(int idFuncionalidadeIniciada) throws ErroRepositorioException;

	public void iniciarProcessoIniciadoRelatorio(int idFuncionalidadeIniciada) throws ErroRepositorioException;

	public void encerrarFuncionalidadeIniciadaRelatorio(int idFuncionalidadeIniciada, int situacaoConclusaoFuncionalidade) throws ErroRepositorioException;

	public void encerrarProcessoIniciadoRelatorio(int idFuncionalidadeIniciada,int situacaoConclusaoFuncionalidade) throws ErroRepositorioException;

	public Collection<byte[]> iniciarRelatoriosAgendados() throws ErroRepositorioException;

	public Collection<Object[]> pesquisarRelatoriosBatchSistema() throws ErroRepositorioException;

	public void removerColecaoObjetoParaBatch(Collection<Object> colecaoObjetos) throws ErroRepositorioException;

	public Collection<Object[]> pesquisarRelatoriosBatchPorUsuarioSistema(int idProcesso) throws ErroRepositorioException;

	public void deletarRelatoriosBatchDataExpiracao(Date dataDeExpiracao) throws ErroRepositorioException;
	
	public Collection<Rota> pesquisarRotasProcessamentoBatchCobrancaGrupoNaoInformado(Integer idCobrancaAcaoAtividadeComando) throws ErroRepositorioException; 

	public Object inserirObjetoParaBatchGerencial(Object objeto) throws ErroRepositorioException; 
	
	public void removerUnidadesIniciadas(Integer idFuncionalidadeIniciada) throws ErroRepositorioException;

	public void removerColecaoGuiaPagamentoCategoriaParaBatch(Collection<GuiaPagamentoCategoria> colecaoGuiaPagamentoCategoria) throws ErroRepositorioException ;

	public void removerColecaoClienteGuiaPagamentoParaBatch(Collection<ClienteGuiaPagamento> colecaoClienteGuiaPagamento) throws ErroRepositorioException ;

	public void removerColecaoGuiaPagamentoParaBatch(Collection<GuiaPagamento> colecaoGuiaPagamento) throws ErroRepositorioException ;

	public void removerColecaoDebitoACobrarParaBatch(Collection<DebitoACobrar> colecaoDebitoACobrar) throws ErroRepositorioException ;

	public void removerColecaoDebitoACobrarCategoriaParaBatch(Collection<DebitoACobrarCategoria> colecaoDebitoACobrarCategoria) throws ErroRepositorioException ;

	public void removerColecaoPagamentoParaBatch(Collection<Pagamento> colecaoPagamento) throws ErroRepositorioException ;

	public void removerColecaoDevolucaoParaBatch(Collection<Devolucao> colecaoDevolucao) throws ErroRepositorioException ;

	public void removerColecaoContaParaBatch(Collection<Conta> colecaoConta) throws ErroRepositorioException ;

	public void removerColecaoContaCategoriaParaBatch(Collection<ContaCategoria> colecaoContaCategoria) throws ErroRepositorioException ;

	public void removerColecaoCreditoRealizadoParaBatch(Collection<CreditoRealizado> colecaoCreditoRealizado) throws ErroRepositorioException ;

	public void removerColecaoDebitoCobradoParaBatch(Collection<DebitoCobrado> colecaoDebitoCobrado) throws ErroRepositorioException ;

	public void removerColecaoContaImpostosDeduzidosParaBatch(Collection<ContaImpostosDeduzidos> colecaoContaImpostosDeduzidos) throws ErroRepositorioException ;

	public void removerColecaoClienteContaParaBatch(Collection<ClienteConta> colecaoClienteConta) throws ErroRepositorioException ;

	public void removerColecaoContaCategoriaConsumoFaixaParaBatch(Collection<ContaCategoriaConsumoFaixa> colecaoContaCategoriaConsumoFaixa) throws ErroRepositorioException ;

	public void removerColecaoDebitoCobradoCategoriaParaBatch(Collection<DebitoCobradoCategoria> colecaoDebitoCobradoCategoria) throws ErroRepositorioException ;

	public void removerColecaoCreditoRealizadoCategoriaParaBatch(Collection<CreditoRealizadoCategoria> colecaoCreditoRealizadoCategoria) throws ErroRepositorioException ;
	
	public void pesquisarQueriesDemoradasSistema() throws ErroRepositorioException; 

	public void removerColecaoCreditoARealizarParaBatch(Collection<CreditoARealizar> colecaoCreditoARealizar) throws ErroRepositorioException ;

	public void removerColecaoCreditoARealizarCategoriaParaBatch(Collection<Integer> colecaoIdsCreditoARealizar) throws ErroRepositorioException ;

	public void inserirLogExcecaoFuncionalidadeIniciada(UnidadeIniciada unidadeIniciada, Throwable excecao)  throws ErroRepositorioException;
	
	public void inserirColecaoObjetoParaBatchTransacao(Collection<Object> colecaoObjetos) throws ErroRepositorioException ;

	public void atualizarSituacaoFuncionalidadeIniciadaConcluida(FuncionalidadeIniciada funcionalidadeIniciada) throws ErroRepositorioException;

	public boolean verificarProcessoEmExecucao(Integer idProcesso) throws ErroRepositorioException;

	public boolean validarAutorizacaoInserirRelatorioBatch(Usuario usuario, int idProcesso) throws ErroRepositorioException;
	
	public void autorizarProcessoIniciado(ProcessoIniciado processoIniciado, Integer processoSituacao) throws ErroRepositorioException;
	
	public void autorizarFuncionalidadeIniciada(ProcessoIniciado processoIniciado,Integer funcionalidadeSituacao) throws ErroRepositorioException;
	
	public void atualizarObjetoParaBatch(Object objetoParaAtualizar) throws ErroRepositorioException;
	
	public Object inserirObjetoParaBatch(Object objeto) throws ErroRepositorioException ;

	public void removerObjetoParaBatch(Object objeto) throws ErroRepositorioException;
	
	@SuppressWarnings("rawtypes")
	public Collection retornaProcessoFuncionalidadeEmExecucao() throws ErroRepositorioException ;
	
	public FaturamentoAtividadeCronograma pesquisarProcessoIniciadoParaGrupo(Integer idGrupo, Integer referencia, Integer idAtividadeFaturamento) throws ErroRepositorioException;
}
