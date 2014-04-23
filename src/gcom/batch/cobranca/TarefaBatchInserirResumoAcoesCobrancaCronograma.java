package gcom.batch.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * Tarefa que manda para batch Gerar Resumo das Ações de Cobrança do Cronograma
 * 
 * @author Pedro Alexandre
 * @created 22/01/2007
 */
public class TarefaBatchInserirResumoAcoesCobrancaCronograma extends
		TarefaBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TarefaBatchInserirResumoAcoesCobrancaCronograma(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchInserirResumoAcoesCobrancaCronograma() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection colecaoDadosCobrancaAcaoAtividadeCronograma = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		Iterator iterator = colecaoDadosCobrancaAcaoAtividadeCronograma
				.iterator();
		while (iterator.hasNext()) {
			Object[] dadosCobrancaAcaoAtividadeCronograma = (Object[]) iterator
					.next();

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_INSERIR_RESUMO_ACAO_COBRANCA_CRONOGRAMA_MDB,
					new Object[] { dadosCobrancaAcaoAtividadeCronograma,
							this.getIdFuncionalidadeIniciada() });

		}

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
				"InserirResumoAcoesCobrancaCronogramaBatch", this);
	}

}
