package gcom.batch.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/**
 * 
 * @author: Vivianne Sousa
 * @date: 11/11/2009
 */
public class TarefaBatchEmitirCartasDeFinalDeAno extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchEmitirCartasDeFinalDeAno(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEmitirCartasDeFinalDeAno() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Integer idFaturamentoGrupo = new Integer((String) getParametro("faturamentoGrupo"));

		System.out.println("EMITIR CARTAS " + idFaturamentoGrupo + " *********************************************************");
		
		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_EMITIR_CARTAS_DE_FINAL_DE_ANO_MDB,
				new Object[]{idFaturamentoGrupo, this.getIdFuncionalidadeIniciada()});

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
		AgendadorTarefas.agendarTarefa("EmitirCartasDeFinalDeAnoBatch", this);
	}

}
