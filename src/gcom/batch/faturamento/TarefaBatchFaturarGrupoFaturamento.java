package gcom.batch.faturamento;

import gcom.faturamento.FaturamentoAtivCronRota;
import gcom.faturamento.FaturamentoGrupo;
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
 * Tarefa que manda para batch Faturar Grupo Faturamento
 * 
 * @author Rodrigo Silveira
 * @created 20/07/2006
 */
public class TarefaBatchFaturarGrupoFaturamento extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchFaturarGrupoFaturamento(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchFaturarGrupoFaturamento() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		Integer atividade = (Integer) getParametro("atividade");

		Iterator iterator = colecaoRotasParaExecucao.iterator();

		while (iterator.hasNext()) {

			Object[] array = (Object[]) iterator.next();

			System.out.println("ROTA FATURAR GRUPO "
							+ ((Rota) array[1]).getId()
							+ "*********************************************************");

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_FATURAR_GRUPO_FATURAMENTO_MDB,
					new Object[]{
							Collections.singletonList((FaturamentoAtivCronRota) array[0]),
							faturamentoGrupo, atividade, this.getIdFuncionalidadeIniciada()});

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
		AgendadorTarefas.agendarTarefa("FaturarGrupoFaturamentoBatch", this);
	}

}
