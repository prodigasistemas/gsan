package gcom.batch.cobranca.spcserasa;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * Tarefa que manda para o Acompanhar Pagamento Do Parcelamento
 * 
 * @author Vivianne Sousa
 * @created 03/11/2009
 */
public class TarefaBatchAcompanharPagamentoDoParcelamento extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchAcompanharPagamentoDoParcelamento(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchAcompanharPagamentoDoParcelamento() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection colecaoRotas = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		  Iterator iterator = colecaoRotas.iterator();
	        
	        while (iterator.hasNext()) {
	        	Integer idRotas = (Integer) iterator.next();
	            enviarMensagemControladorBatch(
	                    ConstantesJNDI.BATCH_ACOMPANHAR_PAGAMENTO_DO_PARCELAMENTO_MDB,
	                    new Object[] { this.getIdFuncionalidadeIniciada(),idRotas });
	            	
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
		AgendadorTarefas.agendarTarefa("BatchAcompanharPagamentoDoParcelamento",
				this);
	}

}
