package gcom.batch.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * Tarefa que manda para batch Gerar Resumo das Ações de Cobrança do Cronograma
 * o subfluxo de Encerrar as OSs
 * @author Francisco do Nascimento
 * @created 16/05/08
 */
public class TarefaBatchGerarResumoAcoesCobrancaCronogramaEncerrarOS extends
		TarefaBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoAcoesCobrancaCronogramaEncerrarOS(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoAcoesCobrancaCronogramaEncerrarOS() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

//		Collection colecaoDadosCobrancaAcaoAtividadeCronograma = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
//		Iterator iterator = colecaoDadosCobrancaAcaoAtividadeCronograma
//				.iterator();
//		while (iterator.hasNext()) {
//			Object[] dadosCobrancaAcaoAtividadeCronograma = (Object[]) iterator
//					.next();
//
//			enviarMensagemControladorBatch(
//					ConstantesJNDI.BATCH_GERAR_RESUMO_ACAO_COBRANCA_CRONOGRAMA_ENCERRAR_OS_MDB,
//					new Object[] { dadosCobrancaAcaoAtividadeCronograma,
//							this.getIdFuncionalidadeIniciada() });
//
//		}

		// Falta verificar os nulos para o outro caso
		/*
		 * enviarMensagemControladorBatch(
		 * ConstantesJNDI.BATCH_GERAR_RESUMO_ACOES_COBRANCA_CRONOGRAMA_MDB, new
		 * Object[]{colecaoCobrancaGrupoCronogramaMes,
		 * this.getIdFuncionalidadeIniciada()});
		 */
		return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"GerarResumoAcoesCobrancaCronogramaEncerrarOSBatch", this);
	}

}
