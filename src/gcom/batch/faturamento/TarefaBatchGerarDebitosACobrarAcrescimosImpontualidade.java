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
public class TarefaBatchGerarDebitosACobrarAcrescimosImpontualidade extends
		TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarDebitosACobrarAcrescimosImpontualidade(
			Usuario usuario, int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarDebitosACobrarAcrescimosImpontualidade() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH); // (Collection<Rota>)

		Short indicadorGeracaoMulta = (Short) getParametro("indicadorGeracaoMulta");
		Short indicadorGeracaoJuros = (Short) getParametro("indicadorGeracaoJuros");
		Short indicadorGeracaoAtualizacao = (Short) getParametro("indicadorGeracaoAtualizacao");
		Boolean indicadorEncerrandoArrecadacao = ((Boolean) getParametro("indicadorEncerrandoArrecadacao"));

		Iterator iterator = colecaoRotasParaExecucao.iterator();

		if(indicadorEncerrandoArrecadacao){
			while (iterator.hasNext()) {

				Rota rota = (Rota) iterator.next();

				enviarMensagemControladorBatch(
						ConstantesJNDI.BATCH_GERAR_DEBITO_A_COBRAR_DE_ACRESCIMOS_POR_IMPONTUALIDADE_MDB,
						new Object[] { Collections.singletonList(rota),
								indicadorGeracaoMulta, indicadorGeracaoJuros,
								indicadorGeracaoAtualizacao,
								this.getIdFuncionalidadeIniciada(),
								indicadorEncerrandoArrecadacao});

			}
		}else{
			while (iterator.hasNext()) {
	
				Object[] array = (Object[]) iterator.next();
	
				System.out
						.println("Rota GERAR DEBITOS IMPONTUALIDADE"
								+ ((Rota) array[1]).getId()
								+ "*********************************************************");
	
				enviarMensagemControladorBatch(
						ConstantesJNDI.BATCH_GERAR_DEBITO_A_COBRAR_DE_ACRESCIMOS_POR_IMPONTUALIDADE_MDB,
						new Object[] { Collections.singletonList((Rota) array[1]),
								indicadorGeracaoMulta, indicadorGeracaoJuros,
								indicadorGeracaoAtualizacao,
								this.getIdFuncionalidadeIniciada(),
								indicadorEncerrandoArrecadacao});
	
			}
		}
		return null;
	}

	@Override
	public Collection pesquisarTodasUnidadeProcessamentoBatch() {
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		FiltroRota filtroRota = new FiltroRota();
		
		//VERIFICAR ISSO COM RODRIGO
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
				"GerarDebitosACobrarAcrescimosImpontualidadeBatch", this);
	}

}
