package gcom.atualizacaocadastral;

import java.util.Collection;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class TarefaBatchAtualizacaoCadastral extends TarefaBatch {

	private static final long serialVersionUID = 8564102611933688902L;

	public TarefaBatchAtualizacaoCadastral(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}

	public TarefaBatchAtualizacaoCadastral() {
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

	@SuppressWarnings("unchecked")
	@Override
	public Object executar() throws TarefaException {
		Collection<Integer> idRotas = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		for (Integer idRota : idRotas) {
			enviarMensagemControladorBatch(ConstantesJNDI.BATCH_ATUALIZACAO_CADASTRAL, new Object[] { 
					this.getIdFuncionalidadeIniciada(), 
					idRota });
		}
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("AtualizacaoCadastralBatch", this);
	}
}
