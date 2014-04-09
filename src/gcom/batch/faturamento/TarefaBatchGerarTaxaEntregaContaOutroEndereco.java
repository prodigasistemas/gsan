package gcom.batch.faturamento;

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
public class TarefaBatchGerarTaxaEntregaContaOutroEndereco extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarTaxaEntregaContaOutroEndereco(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarTaxaEntregaContaOutroEndereco() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Integer anoMesFaturamento = (Integer) getParametro("anoMesFaturamentoGrupo");
		Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); // (Collection<Rota>)

		Iterator iterator = colecaoRotasParaExecucao.iterator();

		while (iterator.hasNext()) {

			Object[] array = (Object[]) iterator.next();

			System.out
					.println("Rota TAXA ENTREGA"
							+ ((Rota) array[1]).getId()
							+ "*********************************************************");

			// ((FaturamentoAtivCronRota)array[0]).setRota(((Rota)array[1]));

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_TAXA_ENTREGA_OUTRO_ENDERECO_MDB,
					new Object[] { Collections.singletonList((Rota) array[1]),
							anoMesFaturamento,
							this.getIdFuncionalidadeIniciada() });

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
				"GerarTaxaEntregaContaOutroEnderecoBatch", this);
	}

}
