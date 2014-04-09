package gcom.batch.financeiro;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;


/**
 * 
 * @author Arthur Carvalho
 * @date 14/09/2010
 */
public class TarefaBatchAtualizarResumoDevedoresDuvidosos extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchAtualizarResumoDevedoresDuvidosos(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchAtualizarResumoDevedoresDuvidosos() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

        // Se o sequencial de execucao do processo_funcionalidade for 1 ou o
        // primeiro --> Registrar Processo Iniciado

//        List<Integer> colecaoIdsQuadras = (List<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 
//        Collections.shuffle(colecaoIdsQuadras);
        Integer anoMesReferenciaContabil = (Integer) getParametro("anoMesReferenciaContabil");
//        Iterator iterator = colecaoIdsQuadras.iterator();
//        while (iterator.hasNext()) {

//            Integer idLocalidade = (Integer) iterator.next();

            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_ATUALIZAR_RESUMO_DEVEDORES_DUVIDOSOS,
                    new Object[] {  anoMesReferenciaContabil,
                    				this.getIdFuncionalidadeIniciada() });
                            
//        }

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
		AgendadorTarefas.agendarTarefa("AtualizarResumoDevedoresDuvidososBatch",
				this);
	}

}
