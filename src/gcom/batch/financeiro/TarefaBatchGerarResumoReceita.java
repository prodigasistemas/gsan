package gcom.batch.financeiro;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * Tarefa que manda para o Acompanhar Pagamento Do Parcelamento
 * 
 * @author Vivianne Sousa
 * @created 03/11/2009
 */
public class TarefaBatchGerarResumoReceita extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoReceita(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoReceita() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

        enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_GERAR_RESUMO_RECEITA,
                new Object[] { this.getIdFuncionalidadeIniciada()});
	            	
	
        return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchGerarResumoReceita",
				this);
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		// TODO Auto-generated method stub
		return null;
	}

}
