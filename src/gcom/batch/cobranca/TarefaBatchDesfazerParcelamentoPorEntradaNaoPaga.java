package gcom.batch.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * Tarefa que manda para batch Gerar Resumo das Ações de Cobrança do Cronograma
 * 
 * @author Vivianne
 * @created 23/03/2007
 */
public class TarefaBatchDesfazerParcelamentoPorEntradaNaoPaga extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchDesfazerParcelamentoPorEntradaNaoPaga(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchDesfazerParcelamentoPorEntradaNaoPaga() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_DESFAZER_PARCELAMENTO_POR_ENTRADA_NAO_PAGA_MDB,
                new Object[] { this.getIdFuncionalidadeIniciada() });
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
		AgendadorTarefas.agendarTarefa("DesfazerParcelamentoPorEntradaNaoPagaBatch", this);
	}

}
