package gcom.batch.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

public class TarefaBatchProcessarEncerramentoOSAcaoCobranca extends TarefaBatch {

	private static final long serialVersionUID = 1L;
	
	public TarefaBatchProcessarEncerramentoOSAcaoCobranca(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchProcessarEncerramentoOSAcaoCobranca() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {
    	Collection colecaoDadosCobrancaAcaoAtividadeCronograma = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
    	Integer idCobrancaAcaoAtividadeCronograma = (Integer) getParametro("idCobrancaAcaoAtividadeCronograma");
		Iterator iterator = colecaoDadosCobrancaAcaoAtividadeCronograma
				.iterator();
		while (iterator.hasNext()) {
			Integer dadosCobrancaAcaoAtividadeCronograma = (Integer) iterator
					.next();

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_PROCESSAR_ENCERRAMENTO_ORDEM_SERVICO_ACAO_COBRANCA_MDB,
					new Object[] { dadosCobrancaAcaoAtividadeCronograma, idCobrancaAcaoAtividadeCronograma,
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
		AgendadorTarefas.agendarTarefa("EncerrarOSAcaoCobrancaBatch",
				this);
	}
}
