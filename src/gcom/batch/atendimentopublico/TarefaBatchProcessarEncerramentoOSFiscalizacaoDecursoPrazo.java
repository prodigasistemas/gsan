package gcom.batch.atendimentopublico;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1210] Processar Encerramento de Ordens de Serviço de Fiscalização por Decurso de Prazo
 *
 * @author Hugo Azevedo
 * @created 10/08/2011
 */
public class TarefaBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazo extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazo(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchProcessarEncerramentoOSFiscalizacaoDecursoPrazo() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_PROCESSAR_ENCERRAMENTO_OS_FISCALIZACAO_DECURSO_PRAZO_MDB,
					new Object[] { this.getIdFuncionalidadeIniciada()
								/*this.getIdFuncionalidadeIniciada(),
								usuario,
								idRegistro,
								idMotivoEncerramento*/});

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
		AgendadorTarefas.agendarTarefa("ProcessarEncerramentoOSFiscalizacaoDecursoPrazoBatch", this);
	}

}
