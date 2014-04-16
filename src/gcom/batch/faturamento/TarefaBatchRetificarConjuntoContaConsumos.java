package gcom.batch.faturamento;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Map;

/**
 * @author Vivianne Sousa
 * @created 03/08/2009
 */
public class TarefaBatchRetificarConjuntoContaConsumos extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchRetificarConjuntoContaConsumos(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchRetificarConjuntoContaConsumos() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Map parametros = (Map) getParametro(ConstantesSistema.PARAMETROS_BATCH);
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_RETIFICAR_CONJUNTO_CONTA_CONSUMOS_MDB, new Object[] {this.getIdFuncionalidadeIniciada(),parametros});

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
		AgendadorTarefas.agendarTarefa("RetificarConjuntoContaConsumosBatch", this);
	}

}
