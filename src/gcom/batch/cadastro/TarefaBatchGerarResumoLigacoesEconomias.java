package gcom.batch.cadastro;

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
 * Tarefa que manda para batch Gerar Resumo Ligacoes Economias
 * 
 * @author Rodrigo Silveira
 * @created 17/01/2007
 */
public class TarefaBatchGerarResumoLigacoesEconomias extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoLigacoesEconomias(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoLigacoesEconomias() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection colecaoSetor = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iterator = colecaoSetor.iterator();

		while (iterator.hasNext()) {

			Integer idSetor = ( (SetorComercial) iterator.next() ).getId();

			System.out
					.println("Localidade GERAR RESUMO LIGACOES ECONOMIAS"
							+ (idSetor)
							+ "*********************************************************");

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_RESUMO_LIGACOES_ECONOMIAS_MDB,
					new Object[]{idSetor,
							this.getIdFuncionalidadeIniciada()});

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
		AgendadorTarefas.agendarTarefa("GerarResumoLigacoesEconomiasBatch",
				this);
	}

}
