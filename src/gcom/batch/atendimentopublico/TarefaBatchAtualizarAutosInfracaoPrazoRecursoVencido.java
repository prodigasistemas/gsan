package gcom.batch.atendimentopublico;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * Tarefa que manda para batch Gerar Resumo Metas
 * @author Sávio Luiz
 * @created 12/05/2009
 */
public class TarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchAtualizarAutosInfracaoPrazoRecursoVencido() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {
		
		SistemaParametro sistemaParametros = (SistemaParametro)getParametro("sistemaParametro");
			
		enviarMensagemControladorBatch(
			ConstantesJNDI.BATCH_ATUALIZAR_AUTOS_INFRACAO_PRAZO_RECURSO_VENCIDO_MDB,
			new Object[] { sistemaParametros,
					this.getIdFuncionalidadeIniciada() });

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
		AgendadorTarefas.agendarTarefa("AtualizarAutosInfracaoPrazoRecursoVencidoBatch",
				this);
	}

}
