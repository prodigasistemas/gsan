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
 * @author Thiago Toscano
 * @created 21/01/2007
 */
public class TarefaBatchGerarMovimentoExclusaoNegativacao extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarMovimentoExclusaoNegativacao(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarMovimentoExclusaoNegativacao() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {


	            enviarMensagemControladorBatch(
	                    ConstantesJNDI.BATCH_GERAR_MOVIMENTO_EXCLUSAO_NEGATIVACAO_MDB,
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
		AgendadorTarefas.agendarTarefa("BatchGerarMovimentoExclusaoNegativacao",
				this);
	}

}
