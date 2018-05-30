package gcom.batch.atendimentopublico;

import gcom.atendimentopublico.registroatendimento.RaEncerramentoComando;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * Tarefa que manda para batch Gerar Resumo Coleta Esgoto
 * @author Marcio Roberto
 * @created 04/10/2007
 */
public class TarefaBatchExecutarComandoEncerramentoRA extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchExecutarComandoEncerramentoRA(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchExecutarComandoEncerramentoRA() {
		super(null, 0);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object executar() throws TarefaException {

		Collection colecaoIdsLoclaidadeRA = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Collection<RaEncerramentoComando> raEncerramentoComandos = (Collection<RaEncerramentoComando>) getParametro("raEncerramentoComandos");
		
		Iterator iterator = colecaoIdsLoclaidadeRA.iterator();

		while (iterator.hasNext()) {

			Integer idLocalidade = (Integer) iterator.next();

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_EXECUTAR_COMANDO_ENCERRAMENTO_RA_MDB,
					new Object[]{raEncerramentoComandos,
							this.getIdFuncionalidadeIniciada(), idLocalidade});

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
		AgendadorTarefas.agendarTarefa("ExecutarComandoEncerramentoRABatch",
				this);
	}

}
