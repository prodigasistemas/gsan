package gcom.batch.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Tarefa que manda para batch Consistir Leituras e Consumos
 * 
 * @author Rodrigo Silveira
 * @created 20/07/2006
 */
public class TarefaBatchConsistirLeiturasCalcularConsumos extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchConsistirLeiturasCalcularConsumos(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchConsistirLeiturasCalcularConsumos() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		// Se o sequencial de execucao do processo_funcionalidade for 1 ou o
		// primeiro --> Registrar Processo Iniciado

		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		SistemaParametro sistemaParametro = (SistemaParametro) getParametro("sistemaParametros");
		Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); // (Collection<Rota>)

		Iterator iterator = colecaoRotasParaExecucao.iterator();

		while (iterator.hasNext()) {

			Object[] array = (Object[]) iterator.next();

			System.out
					.println("Rota CONSISTIR LEITURAS"
							+ ((Rota) array[1]).getId()
							+ "*********************************************************");

			// ((FaturamentoAtivCronRota)array[0]).setRota(((Rota)array[1]));

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_CONSISTIR_LEITURAS_CALCULAR_CONSUMOS_MDB,
					new Object[]{faturamentoGrupo, sistemaParametro,
							Collections.singletonList((Rota) array[1]),
							this.getIdFuncionalidadeIniciada()});

		}

		return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoBatch() {
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		FiltroRota filtroRota = new FiltroRota();
		filtroRota.adicionarParametro(new ParametroSimples(
				FiltroRota.FATURAMENTO_GRUPO_ID, faturamentoGrupo.getId()));

		return Fachada.getInstancia().pesquisar(filtroRota,
				Rota.class.getName());

	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"ConsistirLeiturasCalcularConsumosBatch", this);
	}

}
