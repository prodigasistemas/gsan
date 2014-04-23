package gcom.batch.gerencial.cobranca;

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
 * @author Pedro Alexandre, Ivan Sérgio
 * @date 24/04/2007, 28/05/2007
 * @Alteracao 28/05/2007 - Troca do parametro Localidade para Setor Comercial
 */
public class TarefaBatchGerarResumoLeituraAnormalidade extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoLeituraAnormalidade(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoLeituraAnormalidade() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

        //Collection<Integer> colecaoIdsLocalidades = (Collection<Integer>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
    	Collection<SetorComercial> colecaoSetor = (Collection<SetorComercial>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

        Integer anoMesReferenciaArrecadacao = (Integer) getParametro("anoMesReferenciaArrecadacao"); 
        
        //Iterator iterator = colecaoIdsLocalidades.iterator();
        Iterator<SetorComercial> iterator = colecaoSetor.iterator();
        
        while (iterator.hasNext()) {

            //Integer idLocalidade = (Integer) iterator.next();
        	Integer idSetor = ( iterator.next() ).getId();

            enviarMensagemControladorBatch(
                    ConstantesJNDI.BATCH_GERAR_RESUMO_LEITURA_ANORMALIDADE_MDB,
                    new Object[] { idSetor, anoMesReferenciaArrecadacao, this.getIdFuncionalidadeIniciada() });
                            
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
		AgendadorTarefas.agendarTarefa("GerarResumoLeituraAnormalidadeBatch",
				this);
	}

}
