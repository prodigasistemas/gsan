package gcom.batch.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

public class TarefaBatchCancelarParcelamentos extends TarefaBatch {

	public TarefaBatchCancelarParcelamentos(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}

	private static final long serialVersionUID = 7384165591762776053L;

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public Object executar() throws TarefaException {
		Usuario usuario = (Usuario) getParametro("usuario");
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_CANCELAR_PARCELAMENTOS_MDB, new Object[] { usuario, this.getIdFuncionalidadeIniciada() });
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("CancelarParcelamentosBatch", this);
	}
}
