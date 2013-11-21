package gcom.batch.cadastro;

import gcom.cadastro.tarifasocial.TarifaSocialComandoCarta;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/** 
 * @author Vivianne Sousa
 * @date 23/03/2011 
 */
public class TarefaBatchProcessarComandoGerado extends TarefaBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchProcessarComandoGerado(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchProcessarComandoGerado() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		TarifaSocialComandoCarta tarifaSocialComandoCarta = (TarifaSocialComandoCarta)getParametro("tarifaSocialComandoCarta");
		Collection idsLocalidade = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iterator = idsLocalidade.iterator();
		
		while(iterator.hasNext()) {
			
			Integer idLocalidade = (Integer) iterator.next();
		
			enviarMensagemControladorBatch(
	                ConstantesJNDI.BATCH_PROCESSAR_COMANDO_GERADO,
	                new Object[] { idLocalidade,this.getIdFuncionalidadeIniciada(),tarifaSocialComandoCarta});
		
		}
		
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
		AgendadorTarefas.agendarTarefa("BatchProcessarComandoGerado",this);

	}


}
