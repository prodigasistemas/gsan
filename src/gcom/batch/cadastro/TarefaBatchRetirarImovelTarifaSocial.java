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
 * @date 01/04/2011
 */
public class TarefaBatchRetirarImovelTarifaSocial extends TarefaBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchRetirarImovelTarifaSocial(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchRetirarImovelTarifaSocial() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		
		TarifaSocialComandoCarta tarifaSocialComandoCarta = (TarifaSocialComandoCarta)getParametro("tarifaSocialComandoCarta");
		Collection idsLocalidade = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		if(tarifaSocialComandoCarta != null){
			enviarMensagemControladorBatch(
	                ConstantesJNDI.BATCH_RETIRAR_IMOVEL_TARIFA_SOCIAL,
	                new Object[] { tarifaSocialComandoCarta, this.getIdFuncionalidadeIniciada(),new Integer(1)});
		}else{
			//rotina mensal
			Iterator iterator = idsLocalidade.iterator();
			
			while(iterator.hasNext()) {
				
				Integer idLocalidade = (Integer) iterator.next();
			
				enviarMensagemControladorBatch(
		                ConstantesJNDI.BATCH_RETIRAR_IMOVEL_TARIFA_SOCIAL,
		                new Object[] {idLocalidade,this.getIdFuncionalidadeIniciada(),new Integer(2)});
			
			}
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
		AgendadorTarefas.agendarTarefa("BatchRetirarImovelTarifaSocial",this);

	}


}
