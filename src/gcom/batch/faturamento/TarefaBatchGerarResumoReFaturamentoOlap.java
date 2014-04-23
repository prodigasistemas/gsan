package gcom.batch.faturamento;

import gcom.cadastro.localidade.SetorComercial;
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
 * @author Bruno Barros
 * @date 24/11/2008
 */
public class TarefaBatchGerarResumoReFaturamentoOlap extends TarefaBatch {

	/**
	 * @since 16/05/2007
	 */
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoReFaturamentoOlap(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoReFaturamentoOlap() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

        Collection<Integer> colSetorFaturamento = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 

        Integer anoMesFaturamento = (Integer) getParametro("anoMesFaturamento"); 
        
        Iterator iterator = colSetorFaturamento.iterator();

        while (iterator.hasNext()) {

            Integer idSetor = ( (SetorComercial) iterator.next() ).getId();
        	//Integer idSetor = (Integer) iterator.next();
            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_GERAR_RESUMO_REFATURAMENTO_OLAP_MDB,
                    new Object[] {idSetor, this.getIdFuncionalidadeIniciada(),anoMesFaturamento});
                            
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
		AgendadorTarefas.agendarTarefa("GerarResumoReFaturamentoOlapBatch",
				this);
	}

}
