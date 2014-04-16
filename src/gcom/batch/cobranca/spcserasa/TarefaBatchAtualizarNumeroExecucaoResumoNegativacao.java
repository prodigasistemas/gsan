package gcom.batch.cobranca.spcserasa;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * Tarefa que manda para Gerar Resumo diario Negativacao
 * 
 * @author Yara T. Souza
 * @created 11/11/2008
 */
public class TarefaBatchAtualizarNumeroExecucaoResumoNegativacao extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchAtualizarNumeroExecucaoResumoNegativacao(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchAtualizarNumeroExecucaoResumoNegativacao() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

	
		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_ATUALIZAR_NUMERO_EXECUCAO_RESUMO_NEGATIVACAO_MDB, new Object[] {						
						this.getIdFuncionalidadeIniciada()});

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
		AgendadorTarefas.agendarTarefa("BatchAtualizarNumeroExecucaoResumoNegativacao",
				this);
	}

}
