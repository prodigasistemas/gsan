package gcom.batch.gerencial.cadastro;

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
 * @date 24/04/2007
 */
public class TarefaBatchGerarResumoParcelamento extends TarefaBatch {

	/**
	 * @since 16/05/2007
	 */
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoParcelamento(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoParcelamento() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

        Collection<Integer> colecaoIdsLocalidades = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 

        Integer anoMesFaturamento = (Integer) getParametro("anoMesFaturamento"); 
        
        Iterator iterator = colecaoIdsLocalidades.iterator();

        while (iterator.hasNext()) {

            Integer idLocalidade = (Integer) iterator.next();

            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_GERAR_RESUMO_PARCELAMENTO_MDB,
                    new Object[] { idLocalidade, this.getIdFuncionalidadeIniciada(),anoMesFaturamento});
                            
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
		AgendadorTarefas.agendarTarefa("GerarResumoParcelamentoBatch",
				this);
	}

}
