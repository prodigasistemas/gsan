package gcom.batch.faturamento;

import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Tarefa que manda para batch Consistir Leituras e Consumos
 * 
 * @author Rodrigo Silveira
 * @created 20/07/2006
 */
public class TarefaBatchGerarDebitosACobrarDoacao extends
		TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarDebitosACobrarDoacao(
			Usuario usuario, int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarDebitosACobrarDoacao() {
		super(null, 0);
	}

	
	public Object executar() throws TarefaException {

		Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); // (Collection<Rota>)

		Iterator iterator = colecaoRotasParaExecucao.iterator();

		while (iterator.hasNext()) {

			Object[] array = (Object[]) iterator.next();

//			System.out
//					.println("Rota GERAR DEBITOS A COBRAR DOACAO"
//							+ ((Rota) array[1]).getId()
//							+ "*********************************************************");

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_DEBITOS_COBRAR_DOACAO_MDB,
					new Object[] { Collections.singletonList((Rota) array[1]),
							this.getIdFuncionalidadeIniciada() });

		}

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
				"GerarDebitosACobrarDoacaoBatch", this);
	}

}
