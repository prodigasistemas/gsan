package gcom.batch.micromedicao;

import gcom.faturamento.FaturamentoAtivCronRota;
import gcom.faturamento.FaturamentoGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;


/**
 * Descrição da classe 
 *
 * @author Bruno Barros
 * @date 15/02/2011
 */
public class TarefaBatchGerarRAOSAnormalidadeConsumo extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarRAOSAnormalidadeConsumo(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarRAOSAnormalidadeConsumo() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {		

		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iterator = colecaoRotasParaExecucao.iterator();

		while (iterator.hasNext()) {

			Object[] array = (Object[]) iterator.next();

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_RA_OS_ANORMALIDADE_CONSUMO,
					new Object[]{
							Collections.singletonList((FaturamentoAtivCronRota) array[0]),
							faturamentoGrupo, this.getIdFuncionalidadeIniciada()});

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
		AgendadorTarefas.agendarTarefa("GerarRAOSAnormalidadeConsumoBatch", this);
	}

}
