package gcom.batch.cadastro;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

public class TarefaBatchEmitirComunicadoAlteracaoCadastral extends TarefaBatch {

	private static final long serialVersionUID = 8564102611933688902L;

	public TarefaBatchEmitirComunicadoAlteracaoCadastral(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	public TarefaBatchEmitirComunicadoAlteracaoCadastral() {
		super(null, 0);
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
		
		Usuario usuario = (Usuario) getParametro("usuario");
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_EMITIR_COMUNICADO_ALTERACAO_CADASTRAL, 
				new Object[] {this.getIdFuncionalidadeIniciada(),usuario});
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("EmitirComunicadoAlteracaoCadastralBatch", this);
	}

}

