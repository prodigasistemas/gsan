package gcom.batch.faturamento;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/** 
 * [UC1166]  Gerar  txt para impress�o de contas no formato braille
 * @author Vivianne Sousa
 * @date 20/04/2011
 */
public class TarefaBatchGerarTxtImpressaoContasBraille extends TarefaBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarTxtImpressaoContasBraille(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarTxtImpressaoContasBraille() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_GERAR_TXT_IMPRESSAO_CONTAS_BRAILLE,
                new Object[] {this.getIdFuncionalidadeIniciada()});
	
		return null;
	}


	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchGerarTxtImpressaoContasBraille",this);

	}


}
