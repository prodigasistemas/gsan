package gcom.batch.faturamento;

import java.util.Collection;
import java.util.Iterator;

import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;


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

	@SuppressWarnings("rawtypes")
    public Object executar() throws TarefaException {

		Collection colecaoRotasExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
    	
    	Integer anoMesFaturamento = (Integer) getParametro("anoMesFaturamento");

		Iterator iterator = colecaoRotasExecucao.iterator();

		while (iterator.hasNext()) {

			Object[] array = (Object[]) iterator.next();
			Integer idRota = ((Rota) array[1]).getId();

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_ENVIO_EMAIL_CONTA_PARA_CLIENTE,
					new Object[]{this.getIdFuncionalidadeIniciada(),anoMesFaturamento,idRota});
		}
    	
    	return null;
    
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
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
