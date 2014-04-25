package gcom.batch.financeiro;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * Tarefa que manda para batch Gerar os valores do volumes consumidos e não faturados
 * @author Rafael Corrêa
 * @created 22/02/2008
 */
public class TarefaBatchGerarValorVolumesConsumidosNaoFaturados extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarValorVolumesConsumidosNaoFaturados(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarValorVolumesConsumidosNaoFaturados() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection colecaoIdsLocalidadeParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iterator = colecaoIdsLocalidadeParaExecucao.iterator();

		while (iterator.hasNext()) {

			Integer idLocalidade = (Integer)iterator.next();

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_VALOR_VOLUMES_CONSUMIDOS_NAO_FATURADOS_MDB,
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
		AgendadorTarefas.agendarTarefa("GerarValorVolumesConsumidosNaoFaturadosBatch",
				this);
	}

}
