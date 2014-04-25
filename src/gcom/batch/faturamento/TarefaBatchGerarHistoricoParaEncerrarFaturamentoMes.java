package gcom.batch.faturamento;

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
 * @date 22/03/2007
 */
public class TarefaBatchGerarHistoricoParaEncerrarFaturamentoMes extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarHistoricoParaEncerrarFaturamentoMes(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarHistoricoParaEncerrarFaturamentoMes() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

        // Se o sequencial de execucao do processo_funcionalidade for 1 ou o
        // primeiro --> Registrar Processo Iniciado

        Collection<Integer> colecaoIdsLocalidades = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 
        
        Integer anoMesFaturamentoSistemaParametro = (Integer) getParametro("anoMesFaturamentoSistemaParametro"); 

        Iterator iterator = colecaoIdsLocalidades.iterator();

        while (iterator.hasNext()) {

            Integer idSetor = (Integer) iterator.next();

            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_GERAR_HISTORICO_PARA_ENCERRAR_FATURAMENTO_MES_MDB,
                    new Object[] {anoMesFaturamentoSistemaParametro,idSetor,this.getIdFuncionalidadeIniciada()});
                            
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
		AgendadorTarefas.agendarTarefa("GerarHistoricoParaEncerrarFaturamentoMesBatch",
				this);
	}

}
