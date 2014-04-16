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
 * Descrição da classe 
 *
 * @author Pedro Alexandre
 * @date 24/04/2007
 */
public class TarefaBatchGerarResumoInstalacoesHidrometros extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoInstalacoesHidrometros(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoInstalacoesHidrometros() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

        Collection<Integer> colecaoIdsSetoresComercial = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 

        Integer anoMesReferenciaFaturamento = (Integer) getParametro("anoMesReferenciaFaturamento"); 
        
        Iterator iterator = colecaoIdsSetoresComercial.iterator();

        while (iterator.hasNext()) {

            Integer idSetorComercial = (Integer) iterator.next();

            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_GERAR_RESUMO_INSTALACOES_HIDROMETROS_MDB,
                    new Object[] { anoMesReferenciaFaturamento,idSetorComercial, this.getIdFuncionalidadeIniciada() });
                            
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
		AgendadorTarefas.agendarTarefa("GerarResumoInstalacoesHidrometrosBatch",
				this);
	}

}
