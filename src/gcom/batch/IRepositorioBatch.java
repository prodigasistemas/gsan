package gcom.batch;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamentoCategoria;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.ClienteGuiaPagamento;
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

/**
 * Interface para o repositório batch
 * 
 * @author Rodrigo Silveira
 * @created 15/08/2006
 */
public interface IRepositorioBatch {
	public Collection pesquisarRotasProcessamentoBatchFaturamentoComandado(
			Integer idFaturamentoAtividadeCronograma)
			throws ErroRepositorioException;

	/**
	 * Encerra os Processos Iniciados no sistema quando todas as funcionalidades
	 * do mesmo finalizarem a execução
	 * 
	 * @author Rodrigo Silveira
	 * @date 22/08/2006
	 * 
	 */
	public Collection<ProcessoIniciado> pesquisarProcessosIniciadosProntosParaEncerramento()
			throws ErroRepositorioException;

	/**
	 * Encerra as Funcionalidades Iniciadas no sistema quando todas as unidades
	 * de processamento do mesmo finalizarem a execução
	 * 
	 * @author Rodrigo Silveira
	 * @date 22/08/2006
	 * 
	 */
	public Collection<FuncionalidadeIniciada> pesquisarFuncionaldadesIniciadasProntasParaEncerramento()
			throws ErroRepositorioException;

	/**
	 * Busca as Funcionalidades Iniciadas no sistema que falharam para marcar o
	 * Processo Iniciado como falho
	 * 
	 * @author Rodrigo Silveira
	 * @date 24/08/2006
	 * 
	 */
	public Collection<ProcessoIniciado> pesquisarProcessosIniciadosExecucaoFalha()
			throws ErroRepositorioException;

	/**
	 * Busca as Unidades Iniciadas no sistema que falharam para marcar o
	 * Funcionalidade Iniciada como falha
	 * 
	 * @author Rodrigo Silveira
	 * @date 24/08/2006
	 * 
	 */
	public Collection<FuncionalidadeIniciada> pesquisarFuncionaldadesIniciadasExecucaoFalha()
			throws ErroRepositorioException;

	/**
	 * Busca as Funcionalidades Iniciadas no sistema que estão prontas para
	 * execução
	 * 
	 * @author Rodrigo Silveira
	 * @date 29/08/2006
	 * 
	 */
	public Collection<Object[]> pesquisarFuncionaldadesIniciadasProntasExecucao()
			throws ErroRepositorioException;

	/**
	 * Verifica se a FuncionalidadeIniciada foi concluida com erro para evitar a
	 * execução da UnidadeIniciada relacionada
	 * 
	 * @author Rodrigo Silveira
	 * @date 01/09/2006
	 * 
	 */
	public int pesquisarFuncionaldadesIniciadasConcluidasErro(
			int idFuncionalidadeIniciada) throws ErroRepositorioException;

	/**
	 * Inseri uma coleção de objetos genéricos na base com um flush para cada 50
	 * registros inseridos.
	 * 
	 * @author Pedro Alexandre
	 * @date 11/09/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void inserirColecaoObjetoParaBatch(Collection<? extends Object> colecaoObjetos)
			throws ErroRepositorioException;
	
	/**
	 * Inseri uma coleção de objetos genéricos na base com um flush para cada 50
	 * registros inseridos.
	 * 
	 * @author Bruno Barros
	 * @date 27/04/2007
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void inserirColecaoObjetoParaBatchGerencial(Collection<? extends Object> colecaoObjetos)
			throws ErroRepositorioException;	

	/**
	 * Atualiza uma coleção de objetos genéricos na base com um flush para cada
	 * 50 registros inseridos.
	 * 
	 * @author Leonardo Vieira
	 * @date 12/10/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void atualizarColecaoObjetoParaBatch(
			Collection<? extends Object> colecaoObjetos) throws ErroRepositorioException;

	/**
	 * Verifica se a Funcionalidade Iniciada no sistema que está na ordem
	 * correta de execução dentro do processoFuncionalidade, as funcionalidades
	 * só podem iniciar se estiverem na ordem correta do sequencial de execução
	 * 
	 * @author Rodrigo Silveira
	 * @date 19/09/2006
	 * 
	 */
	public Integer pesquisarQuantidadeFuncionaldadesIniciadasForaOrdemExecucao(
			int idSequencialExecucao, int idProcessoIniciado)
			throws ErroRepositorioException;

	/**
	 * Inicia uma funcionalidade iniciada de um relatório
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 */
	public void iniciarFuncionalidadeIniciadaRelatorio(
			int idFuncionalidadeIniciada) throws ErroRepositorioException;

	/**
	 * Inicia uma processo iniciado de um relatório
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 */
	public void iniciarProcessoIniciadoRelatorio(int idFuncionalidadeIniciada)
			throws ErroRepositorioException;

	/**
	 * Encerra uma funcionalidade iniciada de um relatório
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 */
	public void encerrarFuncionalidadeIniciadaRelatorio(
			int idFuncionalidadeIniciada, int situacaoConclusaoFuncionalidade)
			throws ErroRepositorioException;

	/**
	 * Inicia uma processo iniciado de um relatório
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 */
	public void encerrarProcessoIniciadoRelatorio(int idFuncionalidadeIniciada,
			int situacaoConclusaoFuncionalidade)
			throws ErroRepositorioException;

	/**
	 * Inicia todos os relatórios agendados
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/09/2006
	 * 
	 */
	public Collection<byte[]> iniciarRelatoriosAgendados()
			throws ErroRepositorioException;

	/**
	 * Pesquisa todos as funcionalidades iniciadas que representam os relatórios
	 * batch do sistema
	 * 
	 * @author Rodrigo Silveira
	 * @date 09/10/2006
	 * 
	 */
	public Collection<Object[]> pesquisarRelatoriosBatchSistema()
			throws ErroRepositorioException;

	/**
	 * Remove uma coleção de objetos genéricos na base com um flush para cada 50
	 * registros removidos.
	 * 
	 * @author Pedro Alexandre
	 * @date 09/10/2006
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoObjetoParaBatch(Collection<Object> colecaoObjetos)
			throws ErroRepositorioException;

	/**
	 * Pesquisa todos as funcionalidades iniciadas que representam os relatórios
	 * batch do sistema por Usuário
	 * 
	 * @author Rodrigo Silveira
	 * @date 25/10/2006
	 * 
	 */
	public Collection<Object[]> pesquisarRelatoriosBatchPorUsuarioSistema(
			int idProcesso) throws ErroRepositorioException;

	/**
	 * Remove do sistema todos os relatórios batch que estão na data de
	 * expiração
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/10/2006
	 * 
	 */
	public void deletarRelatoriosBatchDataExpiracao(Date dataDeExpiracao)
			throws ErroRepositorioException;
	
	public Collection<Rota> pesquisarRotasProcessamentoBatchCobrancaGrupoNaoInformado(
			Integer idCobrancaAcaoAtividadeComando)
			throws ErroRepositorioException; 

	
	/**
	 * Inseri uma objeto genérico na base 
	 * 
	 * @author Marcio Roberto
	 * @date 18/05/2007
	 * 
	 * @param Objeto
	 * @throws ErroRepositorioException
	 */
	public Object inserirObjetoParaBatchGerencial(Object objeto) throws ErroRepositorioException; 
	
	/**
	 * Remove do sistema as unidades iniciadas de uma funcionalidade
	 * 
	 * @author Rafael Corrêa
	 * @date 06/11/2006
	 * 
	 */
	public void removerUnidadesIniciadas(Integer idFuncionalidadeIniciada)
			throws ErroRepositorioException;

	/**
	 * Remove uma coleção de GuiaPagamentoCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoGuiaPagamentoCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoGuiaPagamentoCategoriaParaBatch(Collection<GuiaPagamentoCategoria> colecaoGuiaPagamentoCategoria) throws ErroRepositorioException ;

	
	/**
	 * Remove uma coleção de ClienteGuiaPagamento
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoClienteGuiaPagamento
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoClienteGuiaPagamentoParaBatch(Collection<ClienteGuiaPagamento> colecaoClienteGuiaPagamento) throws ErroRepositorioException ;

	
	/**
	 * Remove uma coleção de GuiaPagamento
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoGuiaPagamento
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoGuiaPagamentoParaBatch(Collection<GuiaPagamento> colecaoGuiaPagamento) throws ErroRepositorioException ;

	
	/**
	 * Remove uma coleção de DebitoACobrar
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoACobrar
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoDebitoACobrarParaBatch(Collection<DebitoACobrar> colecaoDebitoACobrar) throws ErroRepositorioException ;

	/**
	 * Remove uma coleção de DebitoACobrarCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoACobrarCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoDebitoACobrarCategoriaParaBatch(Collection<DebitoACobrarCategoria> colecaoDebitoACobrarCategoria) throws ErroRepositorioException ;

	
	/**
	 * Remove uma coleção de Pagamento
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoPagamento
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoPagamentoParaBatch(Collection<Pagamento> colecaoPagamento) throws ErroRepositorioException ;

	
	/**
	 * Remove uma coleção de Devolução
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDevolucao
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoDevolucaoParaBatch(Collection<Devolucao> colecaoDevolucao) throws ErroRepositorioException ;

	
	/**
	 * Remove uma coleção de Conta
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoConta
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoContaParaBatch(Collection<Conta> colecaoConta) throws ErroRepositorioException ;

	/**
	 * Remove uma coleção de ContaCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoContaCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoContaCategoriaParaBatch(Collection<ContaCategoria> colecaoContaCategoria) throws ErroRepositorioException ;

	
	/**
	 * Remove uma coleção de CreditoRealizado
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoCreditoRealizado
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoCreditoRealizadoParaBatch(Collection<CreditoRealizado> colecaoCreditoRealizado) throws ErroRepositorioException ;

	/**
	 * Remove uma coleção de DebitoCobrado
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoCobrado
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoDebitoCobradoParaBatch(Collection<DebitoCobrado> colecaoDebitoCobrado) throws ErroRepositorioException ;

	
	/**
	 * Remove uma coleção de ContaImpostosDeduzidos
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoContaImpostosDeduzidos
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoContaImpostosDeduzidosParaBatch(Collection<ContaImpostosDeduzidos> colecaoContaImpostosDeduzidos) throws ErroRepositorioException ;

	
	/**
	 * Remove uma coleção de ClienteConta
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoClienteConta
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoClienteContaParaBatch(Collection<ClienteConta> colecaoClienteConta) throws ErroRepositorioException ;

	
	/**
	 * Remove uma coleção de ContaCategoriaConsumoFaixa
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoContaCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoContaCategoriaConsumoFaixaParaBatch(Collection<ContaCategoriaConsumoFaixa> colecaoContaCategoriaConsumoFaixa) throws ErroRepositorioException ;

	/**
	 * Remove uma coleção de DebitoCobradoCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoDebitoCobradoCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoDebitoCobradoCategoriaParaBatch(Collection<DebitoCobradoCategoria> colecaoDebitoCobradoCategoria) throws ErroRepositorioException ;

	
	/**
	 * Remove uma coleção de CreditoRealizadoCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoCreditoRealizadoCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoCreditoRealizadoCategoriaParaBatch(Collection<CreditoRealizadoCategoria> colecaoCreditoRealizadoCategoria) throws ErroRepositorioException ;
	
	/**
	 * Pesquisa e registra as queries demoradas do sistema
	 * 
	 * @author Rodrigo Silveira
	 * @date 27/02/2008
	 * 
	 * @throws ErroRepositorioException
	 */
	public void pesquisarQueriesDemoradasSistema()
			throws ErroRepositorioException; 
	
	/**
	 * Remove uma coleção de CreditoARealizar
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 12/02/2008
	 * 
	 * @param colecaoCreditoARealizar
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoCreditoARealizarParaBatch(Collection<CreditoARealizar> colecaoCreditoARealizar) throws ErroRepositorioException ;

	/**
	 * Remove uma coleção de CreditoARealizarCategoria
	 * 
	 * [UC0276] Encerrar Arrecadação do Mês
	 * 
	 * @author Pedro Alexandre
	 * @date 09/04/2008
	 * 
	 * @param colecaoCreditoARealizarCategoria
	 * @throws ErroRepositorioException
	 */
	public void removerColecaoCreditoARealizarCategoriaParaBatch(Collection<Integer> colecaoIdsCreditoARealizar) throws ErroRepositorioException ;

	public void inserirLogExcecaoFuncionalidadeIniciada(UnidadeIniciada unidadeIniciada, Throwable excecao) 
		throws ErroRepositorioException;
	
	/**
	 * Inseri uma coleção de objetos genéricos na base
	 * 
	 * @author Rafael Pinto
	 * @date 20/05/2008
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void inserirColecaoObjetoParaBatchTransacao(Collection<Object> colecaoObjetos)
			throws ErroRepositorioException ;

	public void atualizarSituacaoFuncionalidadeIniciadaConcluida(FuncionalidadeIniciada funcionalidadeIniciada) throws ErroRepositorioException;

	/**
	 * Verifica se o processo está em execução
	 * 
	 * @author Ana Maria
	 * @date 18/12/2008
	 * 
	 */
	public boolean verificarProcessoEmExecucao(Integer idProcesso) throws ErroRepositorioException;

	public boolean validarAutorizacaoInserirRelatorioBatch(Usuario usuario, int idProcesso) throws ErroRepositorioException;
	
	/**
	 * Autoriza Processo Iniciado
	 * 
	 * @author Genival Barbosa
	 * @date 06/08/2009
	 * 
	 * @param ProcessoIniciado
	 */
	public void autorizarProcessoIniciado(ProcessoIniciado processoIniciado, Integer processoSituacao) throws ErroRepositorioException;
	
	/**
	 * Autoriza Processo Iniciado
	 * 
	 * @author Genival Barbosa
	 * @date 06/08/2009
	 * 
	 * @param ProcessoIniciado
	 */
	public void autorizarFuncionalidadeIniciada(ProcessoIniciado processoIniciado,Integer funcionalidadeSituacao) throws ErroRepositorioException;
	
	/**
	 * Atualiza um objeto genérico na base 
	 * 
	 * @author Vivianne Sousa
	 * @date 03/02/2009
	 * 
	 * @param objetoParaAtualizar
	 * @throws ErroRepositorioException
	 */
	public void atualizarObjetoParaBatch(
			Object objetoParaAtualizar) throws ErroRepositorioException;
	
	/**
	 * Insere uma objeto genérico na base 
	 * 
	 * @author Vivianne Sousa
	 * @date 03/02/2009
	 * 
	 * @param Objeto
	 * @throws ErroRepositorioException
	 */
	public Object inserirObjetoParaBatch(Object objeto)
			throws ErroRepositorioException ;
	
	/**
	 * Remove uma coleção de objetos genéricos na base com um flush para cada 50
	 * registros removidos.
	 * 
	 * @author Sávio Luiz
	 * @date 31/03/2010
	 * 
	 * @param colecaoObjetos
	 * @throws ErroRepositorioException
	 */
	public void removerObjetoParaBatch(Object objeto)
			throws ErroRepositorioException;
	
	/**
	 * Retorna o(s) processo(s) que está em execução
	 * 
	 * @author Arthur Carvalho
	 * @date 04/06/2010
	 * 
	 */
	public Collection retornaProcessoFuncionalidadeEmExecucao() throws ErroRepositorioException ;
}
