package gcom.batch.micromedicao;

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
 * Tarefa que manda para batch Efetuar Rateio Consumo
 * 
 * @author Rodrigo Silveira
 * @created 20/07/2006
 */
public class TarefaBatchEfetuarRateioConsumo extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchEfetuarRateioConsumo(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEfetuarRateioConsumo() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Integer anoMesFaturamento = (Integer) getParametro("anoMesFaturamentoGrupo");
		Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); // (Collection<Rota>)

		Iterator iterator = colecaoRotasParaExecucao.iterator();

		while (iterator.hasNext()) {

			Object[] array = (Object[]) iterator.next();

			System.out
					.println("Rota EFETUAR RATEIO CONSUMO"
							+ ((Rota) array[1]).getId()
							+ "*********************************************************");

			// ((FaturamentoAtivCronRota)array[0]).setRota(((Rota)array[1]));

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_EFETUAR_RATEIO_CONSUMO_MDB,
					new Object[] { Collections.singletonList((Rota) array[1]),
							anoMesFaturamento,
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
		AgendadorTarefas.agendarTarefa("BatchEfetuarRateioConsumo", this);
	}

}
