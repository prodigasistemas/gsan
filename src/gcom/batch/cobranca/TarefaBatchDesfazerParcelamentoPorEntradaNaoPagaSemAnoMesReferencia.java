package gcom.batch.cobranca;

import java.util.Collection;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class TarefaBatchDesfazerParcelamentoPorEntradaNaoPagaSemAnoMesReferencia extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchDesfazerParcelamentoPorEntradaNaoPagaSemAnoMesReferencia(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchDesfazerParcelamentoPorEntradaNaoPagaSemAnoMesReferencia() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_DESFAZER_PARCELAMENTO_POR_ENTRADA_NAO_PAGA_SEM_ANO_MES_REFERENCIA_MDB,
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
		AgendadorTarefas.agendarTarefa("DesfazerParcelamentoPorEntradaNaoPagaSemAnoMesReferenciaBatch", this);
	}

}
