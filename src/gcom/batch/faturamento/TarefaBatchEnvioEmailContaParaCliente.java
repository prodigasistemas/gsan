package gcom.batch.faturamento;

import gcom.cadastro.localidade.Localidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;


/**
 * Descrição da classe 
 * 
 * [UC0994] - Envio de Email da Conta para o Cliente
 *
 * @author Fernando Fontelles Filho
 * @date 01/03/2010
 */
public class TarefaBatchEnvioEmailContaParaCliente extends TarefaBatch {

	/**
	 * @since 01/03/2010
	 */
	private static final long serialVersionUID = 1L;

	public TarefaBatchEnvioEmailContaParaCliente(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEnvioEmailContaParaCliente() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

    	Collection colecaoLocalidadesParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
    	
    	Iterator iterator = colecaoLocalidadesParaExecucao.iterator();
    	
    	while(iterator.hasNext()){
    		
    		enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_ENVIO_EMAIL_CONTA_PARA_CLIENTE,
					new Object[]{
							(Localidade) iterator.next(),
							this.getIdFuncionalidadeIniciada()});
    		
    	}
    	
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
		AgendadorTarefas.agendarTarefa("EnvioEmailContaParaClienteBatch",
				this);
	}

}
