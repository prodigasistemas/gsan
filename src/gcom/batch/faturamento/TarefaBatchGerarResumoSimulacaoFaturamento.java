package gcom.batch.faturamento;

import gcom.faturamento.FaturamentoAtivCronRota;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.Rota;
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
 * @author Fernando Fontelles Filho
 * @date 19/01/2010
 */
public class TarefaBatchGerarResumoSimulacaoFaturamento extends TarefaBatch {

	/**
	 * @since 19/01/2010
	 */
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoSimulacaoFaturamento(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoSimulacaoFaturamento() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

    	FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
    	
    	Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
    	Integer atividade = (Integer) getParametro("atividade");
    	
    	Iterator iterator = colecaoRotasParaExecucao.iterator();
    	
    	while(iterator.hasNext()){
    		
    		Object[] array = (Object[]) iterator.next();
    		
    		System.out.println("ROTA FATURAR GRUPO "
					+ ((Rota) array[1]).getId()
					+ "*********************************************************");
    		
    		enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_RESUMO_SIMULACAO_FATURAMENTO,
					new Object[]{
							Collections.singletonList((FaturamentoAtivCronRota) array[0]),
							faturamentoGrupo, atividade, this.getIdFuncionalidadeIniciada()});
    		
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
		AgendadorTarefas.agendarTarefa("GerarResumoSimulacaoFaturamentoBatch",
				this);
	}

}
