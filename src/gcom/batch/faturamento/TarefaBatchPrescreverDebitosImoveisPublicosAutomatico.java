package gcom.batch.faturamento;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Hugo Leonardo
 * @created 19/10/2010
 */
public class TarefaBatchPrescreverDebitosImoveisPublicosAutomatico extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchPrescreverDebitosImoveisPublicosAutomatico(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchPrescreverDebitosImoveisPublicosAutomatico() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {
		
		Collection<Integer> colecaoDadosPrescricaoAutomaticos = (Collection<Integer>) 
			getParametro("colecaoDadosPrescricao"); 
		
		Iterator iterator = colecaoDadosPrescricaoAutomaticos.iterator();
		
    	String idsEsferaPoder = "";
	
    	Object[] idEsferapoder = null;
	
		if(iterator.hasNext()){
			idEsferapoder = (Object[]) iterator.next();
			
			if ( idEsferapoder[0] != null ) {				
				idsEsferaPoder += idEsferapoder[0].toString() + ",";				
			}
			
			if ( idEsferapoder[1] != null ) {				
				idsEsferaPoder += idEsferapoder[1].toString() + ",";				
			}
			
			if(idEsferapoder[0] != null || idEsferapoder[1] != null){
				
				idsEsferaPoder = Util.removerUltimosCaracteres(idsEsferaPoder, 1);
			}
		}
		
		Integer anoMesFaturamento = (Integer) getParametro("anoMesFaturamento"); 
	    
		// AnoMesReferencia - 5 Anos
	    Integer anoMesPrescricao = Util.subtrairAnoAnoMesReferencia(anoMesFaturamento, 5);
	    
	    Integer usuarioLogado = Usuario.USUARIO_BATCH.getId();
		
		enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_PRESCREVER_DEBITOS_IMOVEIS_PUBLICOS_AUTOMATICO_MDB, 
				new Object[] {this.getIdFuncionalidadeIniciada(),
						anoMesFaturamento, anoMesPrescricao, usuarioLogado, idsEsferaPoder});

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
		AgendadorTarefas.agendarTarefa("PrescreverDebitosImoveisPublicosAutomatico", this);
	}

}
