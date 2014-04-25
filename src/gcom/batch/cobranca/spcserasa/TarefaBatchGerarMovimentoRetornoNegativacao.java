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
 * @created 12/12/2008
 */
public class TarefaBatchGerarMovimentoRetornoNegativacao extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarMovimentoRetornoNegativacao(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarMovimentoRetornoNegativacao() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {


	            enviarMensagemControladorBatch(
	                    ConstantesJNDI.BATCH_GERAR_MOVIMENTO_RETORNO_NEGATIVACAO_MDB,
	                    new Object[] { this.getIdFuncionalidadeIniciada()});
	  
	
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
		AgendadorTarefas.agendarTarefa("BatchGerarMovimentoRetornoNegativacao",
				this);
	}

}
