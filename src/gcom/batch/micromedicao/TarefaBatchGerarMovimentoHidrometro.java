package gcom.batch.micromedicao;

import gcom.micromedicao.MovimentoHidrometroHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

public class TarefaBatchGerarMovimentoHidrometro extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarMovimentoHidrometro(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarMovimentoHidrometro() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {
		
		MovimentoHidrometroHelper helper = (MovimentoHidrometroHelper) 
		getParametro("helper");
		
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_MOVIMENTO_HIDROMETRO,
				new Object[] { helper, this.getIdFuncionalidadeIniciada() } ) ;
		
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
		AgendadorTarefas.agendarTarefa("GerarMovimentoHidrometroBatch", this);
	}

}
