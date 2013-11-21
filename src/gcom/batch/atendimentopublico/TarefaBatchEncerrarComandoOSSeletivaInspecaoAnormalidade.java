package gcom.batch.atendimentopublico;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC1197] Encerrar Comandos de OS Seletiva de Inspeção de Anormalidade
 *
 * @author Vivianne Sousa
 * @created 21/07/2011
 */
public class TarefaBatchEncerrarComandoOSSeletivaInspecaoAnormalidade extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchEncerrarComandoOSSeletivaInspecaoAnormalidade(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEncerrarComandoOSSeletivaInspecaoAnormalidade() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Integer idRegistro = (Integer) getParametro("idRegistro");
		Usuario usuario = (Usuario) getParametro("usuario");
		Short idMotivoEncerramento = (Short) getParametro("idMotivoEncerramento");
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_ENCERRAR_COMANDO_OS_SELETIVA_INSPECAO_ANORMALIDADE,
					new Object[] {
								this.getIdFuncionalidadeIniciada(),
								usuario,
								idRegistro,
								idMotivoEncerramento});

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
		AgendadorTarefas.agendarTarefa("EncerrarComandoOSSeletivaInspecaoAnormalidadeBatch", this);
	}

}
