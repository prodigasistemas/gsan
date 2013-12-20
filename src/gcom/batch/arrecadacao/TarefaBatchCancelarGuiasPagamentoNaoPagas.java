package gcom.batch.arrecadacao;

import gcom.cadastro.localidade.Localidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class TarefaBatchCancelarGuiasPagamentoNaoPagas extends TarefaBatch {

	
	private static final long serialVersionUID = 1L;

	public TarefaBatchCancelarGuiasPagamentoNaoPagas(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchCancelarGuiasPagamentoNaoPagas() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

        @SuppressWarnings("unchecked")
		Collection<Localidade> colecaoIdLocalidades = (Collection<Localidade>) getParametro(
				ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 
		
		Iterator iterator = colecaoIdLocalidades.iterator();

		while (iterator.hasNext()) {

			Integer idLocalidade = (Integer) iterator.next();
			
			Date dataReferencia = Util.subtrairNumeroDiasDeUmaData(new Date(), 60);
			
			enviarMensagemControladorBatch(
	                  ConstantesJNDI.BATCH_CANCELAR_GUIAS_PAGAMENTO_NAO_PAGAS,
	                  new Object[] { this.getIdFuncionalidadeIniciada(),dataReferencia, idLocalidade });
			
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
		AgendadorTarefas.agendarTarefa("BatchCancelarGuiasPagamentoNaoPagas",
				this);
	}


}
