package gcom.batch.financeiro;

import gcom.cadastro.localidade.Localidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;


public class TarefaBatchGerarLancamentosContabeisFaturamento extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarLancamentosContabeisFaturamento(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarLancamentosContabeisFaturamento() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

        // Se o sequencial de execucao do processo_funcionalidade for 1 ou o
        // primeiro --> Registrar Processo Iniciado

        Collection<Localidade> colecaoIdsLocalidades = 
        	(Collection<Localidade>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 

        Integer anoMesFaturamento = 
        	(Integer) getParametro("anoMesFaturamento");
        
        Iterator iterator = colecaoIdsLocalidades.iterator();

        while (iterator.hasNext()) {

            Localidade localidade = (Localidade) iterator.next();

            enviarMensagemControladorBatch(
            		ConstantesJNDI.BATCH_GERAR_LANCAMENTOS_CONTABEIS_FATURAMENTO_MDB,
                    new Object[] {  
            			anoMesFaturamento,
            			localidade.getId(),
                    	this.getIdFuncionalidadeIniciada() }
            );
                            
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
		AgendadorTarefas.agendarTarefa("GerarLancamentosContabeisFaturamentoBatch",
				this);
	}

}
