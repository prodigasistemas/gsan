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
 * Tarefa que manda para batch Gerar Resumo de Situação Especial de Faturamento
 * 
 * @author Rodrigo Silveira
 * @created 18/01/2007
 */
public class TarefaBatchGerarResumoSituacaoEspecialFaturamento extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoSituacaoEspecialFaturamento(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoSituacaoEspecialFaturamento() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection colecaoLocalidadesParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iterator = colecaoLocalidadesParaExecucao.iterator();

		while (iterator.hasNext()) {

			Integer idLocalidade = (Integer) iterator.next();

			System.out
					.println("Localidade GERAR RESUMO SITUACAO ESPECIAL DE FATURAMENTO"
							+ (idLocalidade)
							+ "*********************************************************");

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_RESUMO_SITUACAO_ESPECIAL_FATURAMENTO_MDB,
					new Object[]{idLocalidade,
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
		AgendadorTarefas.agendarTarefa("GerarResumoSituacaoEspecialFaturamentoBatch",
				this);
	}

}
