package gcom.batch.cobranca.cobrancaporresultado;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

public class TarefaBatchAtualizarPagamentosContasCobranca extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchAtualizarPagamentosContasCobranca(Usuario usuario,
		int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);		
	}
		
	@Deprecated
	public TarefaBatchAtualizarPagamentosContasCobranca() {
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
	public Object executar() throws TarefaException {
		Collection<Integer> colecaoIdsLocalidade = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		Integer anoMesArrecadacao = (Integer) getParametro("anoMesArrecadacao");

		Iterator<Integer> iterator = colecaoIdsLocalidade.iterator();

		while (iterator.hasNext()) {

			Integer idLocalidade = ( (Integer) iterator.next() );

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_ATUALIZAR_PAGAMENTOS_CONTAS_COBRANCA,
					new Object[]{this.getIdFuncionalidadeIniciada(), idLocalidade, anoMesArrecadacao});

		}

		return null;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("AtualizarPagamentosContasCobrancaBatch", this);

	}
}
