package gcom.batch.financeiro;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * @author Arthur Carvalho 
 *
 */
public class TarefaBatchApagarResumoDevedoresDuvidosos extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchApagarResumoDevedoresDuvidosos(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchApagarResumoDevedoresDuvidosos() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

        // Se o sequencial de execucao do processo_funcionalidade for 1 ou o
        // primeiro --> Registrar Processo Iniciado

        List<Integer> colecaoIdsLocalidades = (List<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 
        Collections.shuffle(colecaoIdsLocalidades);
        Integer anoMesReferenciaContabil = (Integer) getParametro("anoMesReferenciaContabil");
        Iterator iterator = colecaoIdsLocalidades.iterator();
        while (iterator.hasNext()) {

            Integer idLocalidade = (Integer) iterator.next();

            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_APAGAR_RESUMO_DEVEDORES_DUVIDOSOS_MDB,
                    new Object[] {  anoMesReferenciaContabil,
                    				idLocalidade,
                    				this.getIdFuncionalidadeIniciada() });
                            
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
		AgendadorTarefas.agendarTarefa("GerarResumoDevedoresDuvidososBatch",
				this);
	}

}
