package gcom.batch.cobranca;

import java.util.Collection;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * Tarefa que manda para batch Incluir Débito a Cobrar de Entrada de Parcelamento Não Paga
 * 
 * @author Raphael Rossiter
 * @created 26/08/2008
 */
public class TarefaBatchIncluirDebitoACobrarEntradaParcelamentoNaoPaga extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchIncluirDebitoACobrarEntradaParcelamentoNaoPaga(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchIncluirDebitoACobrarEntradaParcelamentoNaoPaga() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_INCLUIR_DEBITO_A_COBRAR_ENTRADA_PARCELAMENTO_NAO_PAGA_MDB ,
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
		AgendadorTarefas.agendarTarefa("IncluirDebitoACobrarEntradaParcelamentoNaoPaga", this);
	}
}
