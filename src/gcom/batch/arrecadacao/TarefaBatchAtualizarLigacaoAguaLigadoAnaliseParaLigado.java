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
 * Tarefa que manda para Gerar Resumo diario Negativacao
 * 
 * @author Yara Taciane de Souza
 * @created 04/07/2008
 */
public class TarefaBatchAtualizarLigacaoAguaLigadoAnaliseParaLigado extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchAtualizarLigacaoAguaLigadoAnaliseParaLigado(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchAtualizarLigacaoAguaLigadoAnaliseParaLigado() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection colecaoLocalidade = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		  Iterator iterator = colecaoLocalidade.iterator();
	        
	        while (iterator.hasNext()) {
	        	Integer idLocalidade = (Integer) iterator.next();
	            enviarMensagemControladorBatch(
	                    ConstantesJNDI.BATCH_ATUALIZAR_LIGACAO_AGUA_LIGADO_ANALISE_PARA_LIGADO_MDB,
	                    new Object[] { this.getIdFuncionalidadeIniciada(),idLocalidade });
	            	
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
		AgendadorTarefas.agendarTarefa("BatchAtualizarLigacaoAguaLigadoAnaliseParaLigado",
				this);
	}

}
