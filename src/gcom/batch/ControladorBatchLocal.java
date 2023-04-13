package gcom.batch;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoCategoria;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.ClienteGuiaPagamento;
import gcom.cobranca.RelatorioPagamentosContasCobrancaEmpresaHelper;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupo;
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
import gcom.micromedicao.MovimentoHidrometroHelper;
import gcom.micromedicao.hidrometro.Hidrometro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;

import java.util.Collection;
import java.util.Map;

public interface ControladorBatchLocal extends javax.ejb.EJBLocalObject {

	public Integer inserirProcessoIniciado(ProcessoIniciado processoIniciado) throws ControladorException;

	public Integer inserirProcessoIniciadoFaturamentoComandado(Collection<Integer> idsFaturamentoAtividadeCronograma, Usuario usuario) throws ControladorException;

	public void verificarProcessosIniciados() throws ControladorException;

	public void encerrarProcessosIniciados() throws ControladorException;

	public void encerrarFuncionalidadesIniciadas() throws ControladorException;

	public int iniciarUnidadeProcessamentoBatch(int idFuncionalidadeIniciada, int idUnidadeProcessamento, int codigoRealUnidadeProcessamento) throws ControladorException;

	public void encerrarUnidadeProcessamentoBatch(Throwable ex, int idUnidadeIniciada, boolean executouComErro) throws ControladorException;

	public void inserirColecaoObjetoParaBatch(Collection<? extends Object> colecaoObjetos) throws ControladorException;

	public void inserirColecaoObjetoParaBatchGerencial(Collection<? extends Object> colecaoObjetos) throws ControladorException;

	public void atualizarColecaoObjetoParaBatch(Collection<? extends Object> colecaoObjetos) throws ControladorException;

	public void verificadorProcessosSistema() throws ControladorException;

	public void iniciarFuncionalidadeIniciadaRelatorio(int idFuncionalidadeIniciada) throws ControladorException;

	public void encerrarFuncionalidadeIniciadaRelatorio(int idFuncionalidadeIniciada, boolean concluiuComErro) throws ControladorException;

	public Collection<Object[]> pesquisarRelatoriosBatchSistema() throws ControladorException;

	public void removerColecaoObjetoParaBatch(Collection<Object> colecaoObjetos) throws ControladorException;

	public void iniciarProcessoRelatorio(TarefaRelatorio tarefaRelatorio) throws ControladorException;

	public Collection<Object[]> pesquisarRelatoriosBatchPorUsuarioSistema(int idProcesso) throws ControladorException;

	public void deletarRelatoriosBatchDataExpiracao() throws ControladorException;

	public Integer inserirProcessoIniciadoCobrancaComandado(Collection<Integer> idsCronograma, Collection<Integer> idsEventuais, Usuario usuario) throws ControladorException;

	public void marcarProcessosInterrompidos() throws ControladorException;

	public Object inserirObjetoParaBatchGerencial(Object objeto) throws ControladorException;

	public Integer inserirProcessoIniciado(ProcessoIniciado processoIniciado, Map<String, Object> dadosProcessamento) throws ControladorException;

	public void reiniciarFuncionalidadesIniciadas(String[] idsFuncionalidadesIniciadas, Integer idProcessoIniciado) throws ControladorException;

	public void removerColecaoGuiaPagamentoCategoriaParaBatch(Collection<GuiaPagamentoCategoria> colecaoGuiaPagamentoCategoria) throws ControladorException;

	public void removerColecaoClienteGuiaPagamentoParaBatch(Collection<ClienteGuiaPagamento> colecaoClienteGuiaPagamento) throws ControladorException;

	public void removerColecaoGuiaPagamentoParaBatch(Collection<GuiaPagamento> colecaoGuiaPagamento) throws ControladorException;

	public void removerColecaoDebitoACobrarParaBatch(Collection<DebitoACobrar> colecaoDebitoACobrar) throws ControladorException;

	public void removerColecaoDebitoACobrarCategoriaParaBatch(Collection<DebitoACobrarCategoria> colecaoDebitoACobrarCategoria) throws ControladorException;

	public void removerColecaoPagamentoParaBatch(Collection<Integer> pagamentos) throws ControladorException;

	public void removerColecaoDevolucaoParaBatch(Collection<Devolucao> colecaoDevolucao) throws ControladorException;

	public void removerColecaoContaParaBatch(Collection<Conta> colecaoConta) throws ControladorException;

	public void removerColecaoContaCategoriaParaBatch(Collection<ContaCategoria> colecaoContaCategoria) throws ControladorException;

	public void removerColecaoContaCategoriaConsumoFaixaParaBatch(Collection<ContaCategoriaConsumoFaixa> colecaoContaCategoriaConsumoFaixa) throws ControladorException;

	public void removerColecaoCreditoRealizadoParaBatch(Collection<CreditoRealizado> colecaoCreditoRealizado) throws ControladorException;

	public void removerColecaoDebitoCobradoParaBatch(Collection<DebitoCobrado> colecaoDebitoCobrado) throws ControladorException;

	public void removerColecaoContaImpostosDeduzidosParaBatch(Collection<ContaImpostosDeduzidos> colecaoContaImpostosDeduzidos) throws ControladorException;

	public void removerColecaoClienteContaParaBatch(Collection<ClienteConta> colecaoClienteConta) throws ControladorException;

	public void removerColecaoDebitoCobradoCategoriaParaBatch(Collection<DebitoCobradoCategoria> colecaoDebitoCobradoCategoria) throws ControladorException;

	public void removerColecaoCreditoRealizadoCategoriaParaBatch(Collection<CreditoRealizadoCategoria> colecaoCreditoRealizadoCategoria) throws ControladorException;

	public void verificadorProcessosIntegracaoUPA() throws ControladorException;

	public void verificadorQueriesDemoradasSistema() throws ControladorException;

	public void removerColecaoCreditoARealizarParaBatch(Collection<CreditoARealizar> colecaoCreditoARealizar) throws ControladorException;

	public void removerColecaoCreditoARealizarCategoriaParaBatch(Collection<Integer> colecaoIdsCreditoARealizar) throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer inserirProcessoIniciadoParametrosLivres(Map parametros, int idProcesso, Usuario usuario) throws ControladorException;

	public void inserirColecaoObjetoParaBatchTransacao(Collection<Object> colecaoObjetos) throws ControladorException;

	public void continuarFuncionalidadesIniciadas(String[] idsFuncionalidadesIniciadas, Integer idProcessoIniciado) throws ControladorException;

	public boolean isProcessoEmExecucao(Integer idProcesso)throws ControladorException;

	@SuppressWarnings("rawtypes")
	public Integer inserirProcessoIniciadoContasCobranca(Collection ids, Integer idEmpresa, Usuario usuario) throws ControladorException;

	public Integer inserirProcessoIniciadoRelatorioPagamentosContasCobranca( RelatorioPagamentosContasCobrancaEmpresaHelper helper,
			int opcaoRelatorio,Usuario usuario) throws ControladorException;

	public void iniciarProcessoRelatorioControleAutorizacao(TarefaRelatorio tarefaRelatorio) throws ControladorException; 

	public Integer inserirProcessoAtualizarConjuntoHidrometro(String fixo,String inicialFixo,String finalFixo,
			Hidrometro hidrometroAtualizado,Usuario usuarioLogado,Integer totalRegistros) throws ControladorException;

	public Integer inserirAtualizarMovimentacaoHidrometroIdsBatch( MovimentoHidrometroHelper helper ) throws ControladorException;
	
	public void autorizarProcessoIniciado(ProcessoIniciado processoIniciado,Integer processoSituacao,Integer funcionalidadeSituacao) throws ControladorException;

	public Integer inserirProcessoGerarTxtContasProcessosEspeciais(String anoMes, Integer idCliente, Usuario usuario) throws ControladorException;
	
	public void atualizarObjetoParaBatch(Object objetoParaAtualizar) throws ControladorException;
	
	public Object inserirObjetoParaBatch(Object objeto) throws ControladorException ;
	
	@SuppressWarnings("rawtypes")
	public void removerColecaoObjetoParaBatchSemTransacao(Collection colecaoObjetos) throws ControladorException;
	
	public void removerObjetoParaBatchSemTransacao(Object objeto) throws ControladorException;
	
	public void inserirColecaoObjetoParaBatchSemTransacao(Collection<? extends Object> colecaoObjetos) throws ControladorException; 
	
	public void atualizarColecaoObjetoParaBatchSemTransacao(Collection<? extends Object> colecaoObjetos) throws ControladorException;
	
	public Integer inserirProcessoGerarTxtDeclaracaoQuitacaoDebitos(Integer idGrupoFaturamento, Usuario usuario) throws ControladorException;
	
	@SuppressWarnings("rawtypes")
	public Collection retornaProcessoFuncionalidadeEmExecucao() throws ControladorException;
	
	public Object inserirObjetoParaBatchSemTransacao(Object objeto) throws ControladorException;
	
	@SuppressWarnings("rawtypes")
	public Integer inserirProcessoIniciadoParametrosLivresAguardandoAutorizacao(Map parametros, int idProcesso, Usuario usuario) throws ControladorException;
	
	public FaturamentoAtividadeCronograma pesquisarProcessoIniciadoParaGrupo(Integer idGrupo, Integer referencia, Integer idAtividadeFaturamento) throws ControladorException; 
	
	public String getIpNovoBatch();
	
	public Usuario obterUsuarioQueDisparouProcesso(Integer idFuncionalidadeIniciada) throws ControladorException;
	
	public boolean isProcessoEmEspera(Integer idProcesso) throws ControladorException;
	
	public void validarInclusaoProcessosNegativacao(Integer idFuncionalidade) throws ControladorException;
	
	public boolean isProcessoFaturamentoIniciado(FaturamentoGrupo grupo) throws ControladorException;
}
