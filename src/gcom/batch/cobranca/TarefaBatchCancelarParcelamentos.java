package gcom.batch.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

public class TarefaBatchCancelarParcelamentos extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	@Deprecated
	public TarefaBatchCancelarParcelamentos() {
		super(null, 0);
	}
	
	public TarefaBatchCancelarParcelamentos(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}

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
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_CANCELAR_PARCELAMENTOS_MDB, new Object[] { (Usuario) getParametro("usuario"), this.getIdFuncionalidadeIniciada() });
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("CancelarParcelamentosBatch", this);
	}
}
