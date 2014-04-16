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


public class TarefaBatchGerarLancamentosContabeisArrecadacao extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarLancamentosContabeisArrecadacao(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarLancamentosContabeisArrecadacao() {
		super(null, 0);
	}

    public Object executar() throws TarefaException {

        // Se o sequencial de execucao do processo_funcionalidade for 1 ou o
        // primeiro --> Registrar Processo Iniciado

        Collection<Localidade> colecaoIdsLocalidades = 
        	(Collection<Localidade>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); 

        Integer anoMesArrecadacao = (Integer) getParametro("anoMesArrecadacao");
        
        Iterator iterator = colecaoIdsLocalidades.iterator();

        while (iterator.hasNext()) {

        	Localidade localidade = (Localidade) iterator.next();

            enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_GERAR_LANCAMENTOS_CONTABEIS_ARRECADACAO_MDB,
            		new Object[] {  
                		anoMesArrecadacao,
                		localidade.getId(),
                		this.getIdFuncionalidadeIniciada() });
                            
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
		AgendadorTarefas.agendarTarefa("GerarLancamentosContabeisArrecadacaoBatch",this);
	}

}
