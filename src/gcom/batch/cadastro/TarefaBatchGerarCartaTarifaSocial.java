package gcom.batch.cadastro;

import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

/** 
 * @author Vivianne Sousa
 * @date 23/03/2011 
 */
public class TarefaBatchGerarCartaTarifaSocial extends TarefaBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarCartaTarifaSocial(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarCartaTarifaSocial() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		TarifaSocialComandoCarta tarifaSocialComandoCarta = (TarifaSocialComandoCarta)getParametro("tarifaSocialComandoCarta");

		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_GERAR_CARTA_TARIFA_SOCIAL,
				new Object[]{tarifaSocialComandoCarta, this.getIdFuncionalidadeIniciada()});

		
		return null;
	}


	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchGerarCartaTarifaSocial",this);

	}


}
