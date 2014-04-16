package gcom.batch.arrecadacao;

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
 * @author Pedro Alexandre
 * @date 21/03/2007
 */
public class TarefaBatchGerarHistoricoConta extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarHistoricoConta(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarHistoricoConta() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

        Collection<Integer> colecaoIdsSetoresEncerrarArrecadacaoMes = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 

        Integer anoMesReferenciaArrecadacao = (Integer) getParametro("anoMesReferenciaArrecadacao"); 
        
        Iterator iterator = colecaoIdsSetoresEncerrarArrecadacaoMes.iterator();

        while (iterator.hasNext()) {

            Integer idSetor = (Integer) iterator.next();

            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_GERAR_HISTORICO_CONTA_MDB,
                    new Object[] { anoMesReferenciaArrecadacao,idSetor, this.getIdFuncionalidadeIniciada() });
                            
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
		AgendadorTarefas.agendarTarefa("GerarHistoricoContaBatch",
				this);
	}

}
