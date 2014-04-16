package gcom.batch.gerencial.micromedicao;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;


/**
 * Componente que inicia o processamento do caso de uso GerarResumoAnormalidades em paralelo 
 *
 * @author Rodrigo Silveira
 * @date 01/02/2007
 */
public class TarefaBatchGerarResumoAnormalidades extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoAnormalidades(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoAnormalidades() {
		super(null, 0);
	}

    
    public Object executar() throws TarefaException {


        Collection<Integer> colecaoIdsLocalidades = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 

        Iterator iterator = colecaoIdsLocalidades.iterator();

        while (iterator.hasNext()) {

            Integer idLocalidade = (Integer) iterator.next();

            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_GERAR_RESUMO_ANORMALIDADES_MDB,
                    new Object[] { idLocalidade,
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
		AgendadorTarefas.agendarTarefa("GerarResumoAnormalidadeBatch",this);
	}

}
