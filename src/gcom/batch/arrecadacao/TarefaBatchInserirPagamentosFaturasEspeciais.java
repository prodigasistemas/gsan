package gcom.batch.arrecadacao;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * @author: Vivianne Sousa
 * @date: 23/12/2009
 */
public class TarefaBatchInserirPagamentosFaturasEspeciais extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchInserirPagamentosFaturasEspeciais(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchInserirPagamentosFaturasEspeciais() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Map parametros = (Map) getParametro(ConstantesSistema.PARAMETROS_BATCH);

		System.out.println("INSERIR PAGAMENTOS PARA FATURAS ESPECIAIS");
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_INSERIR_PAGAMENTOS_FATURAS_ESPECIAIS, 
				new Object[] {this.getIdFuncionalidadeIniciada(),parametros});

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
		AgendadorTarefas.agendarTarefa("InserirPagamentosFaturasEspeciaisBatch", this);
	}

}
