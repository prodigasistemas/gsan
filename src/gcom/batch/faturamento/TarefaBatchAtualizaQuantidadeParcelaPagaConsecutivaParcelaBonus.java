package gcom.batch.faturamento;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * Tarefa que manda para batch Atualiza Quantidade de Parcela Paga Consecutiva e Parcela Bônus
 * 
 * @author Vivianne Sousa
 * @created 08/02/2008
 */
public class TarefaBatchAtualizaQuantidadeParcelaPagaConsecutivaParcelaBonus extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchAtualizaQuantidadeParcelaPagaConsecutivaParcelaBonus(Usuario usuario, int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchAtualizaQuantidadeParcelaPagaConsecutivaParcelaBonus() {
		super(null, 0);
	}

     public Object executar() throws TarefaException {

        // Se o sequencial de execucao do processo_funcionalidade for 1 ou o
        // primeiro --> Registrar Processo Iniciado

        Collection<Integer> colecaoIdsLocalidades = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 

        Iterator iterator = colecaoIdsLocalidades.iterator();

        while (iterator.hasNext()) {

            Integer idLocalidade = (Integer) iterator.next();

            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_ATUALIZA_QUANTIDADE_PARCELA_PAGA_CONSECUTIVA_PARCELA_BONUS_MDB,
                    new Object[] {idLocalidade,this.getIdFuncionalidadeIniciada() });
                            
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
		AgendadorTarefas.agendarTarefa("AtualizaQuantidadeParcelaPagaConsecutivaParcelaBonusBatch", this);
	}

}
