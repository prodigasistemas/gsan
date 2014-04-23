package gcom.batch.faturamento;

import gcom.faturamento.FaturamentoAtivCronRota;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * Tarefa que manda para batch Gerar Taxa Percentual da Tarifa Mínima para Cortado
 * 
 * @author Raphael Rossiter
 * @created 09/07/2010
 */
public class TarefaBatchGerarTaxaPercentualTarifaMinimaCortado extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarTaxaPercentualTarifaMinimaCortado(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarTaxaPercentualTarifaMinimaCortado() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		Integer atividade = (Integer) getParametro("atividade");

		Iterator iterator = colecaoRotasParaExecucao.iterator();

		while (iterator.hasNext()) {

			Object[] array = (Object[]) iterator.next();
			
			System.out.println("ROTA GERAR TAXA PERCENTUAL TARIFA MINIMA CORTADO "
				+ ((Rota) array[1]).getId()
				+ "*********************************************************");

			enviarMensagemControladorBatch(
				ConstantesJNDI.BATCH_GERAR_TAXA_PERCENTUAL_TARIFA_MINIMA_CORTADO_MDB,
				new Object[]{Collections.singletonList((FaturamentoAtivCronRota) array[0]),
				faturamentoGrupo,atividade, this.getIdFuncionalidadeIniciada()});
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
		AgendadorTarefas.agendarTarefa("GerarTaxaPercentualTarifaMinimaCortadoBatch", this);
	}

}
