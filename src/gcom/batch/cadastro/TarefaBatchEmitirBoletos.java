package gcom.batch.cadastro;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * [UC0925] Emitir Boletos
 * Tarefa que manda para batch Emitir Boletos
 * 
 * @author Vivianne Sousa
 * @created 10/07/2009
 */
public class TarefaBatchEmitirBoletos extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchEmitirBoletos(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEmitirBoletos() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

//		CobrancaAcaoAtividadeCronograma comandoAtividadeAcaoCobranca = (CobrancaAcaoAtividadeCronograma) getParametro("comandoAtividadeAcaoCobranca");
//		CobrancaAcaoAtividadeComando comandoAtividadeAcaoComando = (CobrancaAcaoAtividadeComando) getParametro("comandoAtividadeAcaoComando");
//		CobrancaAcao acaoCobranca = (CobrancaAcao) getParametro("acaoCobranca");
//		Date dataAtual = (Date) getParametro("dataAtual");

		Integer idGrupo = new Integer((String) getParametro("faturamentoGrupo"));
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_EMITIR_BOLETOS_MDB, new Object[] {this.getIdFuncionalidadeIniciada(),idGrupo});

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
		AgendadorTarefas.agendarTarefa("EmitirBoletosBatch", this);
	}

}
