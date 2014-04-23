package gcom.batch.financeiro;

import java.util.Collection;
import java.util.Iterator;

import gcom.cadastro.localidade.Localidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class TarefaBatchGerarResumoDocumentosAReceber extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoDocumentosAReceber(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoDocumentosAReceber() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection colecaoIdsLocalidadeParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iterator = colecaoIdsLocalidadeParaExecucao.iterator();

		while (iterator.hasNext()) {

			Integer idLocalidade = ( (Localidade) iterator.next() ).getId();

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_RESUMO_DOCUMENTOS_A_RECEBER_MDB,
					new Object[]{idLocalidade,
							this.getIdFuncionalidadeIniciada()});

		}

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
		AgendadorTarefas.agendarTarefa("GerarResumoDocumentosAReceberBatch",
				this);
	}
}
