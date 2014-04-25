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
 * @author Fernando Fontelles
 * @date 25/05/2010
 */
public class TarefaBatchGerarResumoFaturamentoPorAno extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoFaturamentoPorAno(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoFaturamentoPorAno() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

        Collection<Integer> colSetorFaturamento = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 

        Integer anoMesFaturamento = (Integer) getParametro("anoMesFaturamento"); 
        
        Iterator iterator = colSetorFaturamento.iterator();

        while (iterator.hasNext()) {

            Integer idSetor = ( (SetorComercial) iterator.next() ).getId();
        	
            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_GERAR_RESUMO_FATURAMENTO_POR_ANO_MDB,
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
		AgendadorTarefas.agendarTarefa("GerarResumoFaturamentoPorAnoBatch",
				this);
	}
}
