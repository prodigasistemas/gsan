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
 * Tarefa que manda para batch Gerar Crédito Situação Especial Faturamento
 * 
 * @author Raphael Rossiter
 * @created 22/01/2009
 */
public class TarefaBatchGerarCreditoSituacaoEspecialFaturamento extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarCreditoSituacaoEspecialFaturamento(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarCreditoSituacaoEspecialFaturamento() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		Integer atividade = (Integer) getParametro("atividade");

		Iterator iterator = colecaoRotasParaExecucao.iterator();

		while (iterator.hasNext()) {

			Object[] array = (Object[]) iterator.next();
			
			/*
			 * Mandar só as rotas de impressão simultânea caso a atividade seja o de
			 * gerar dados para leitura.
			 * 
			 * Caso a atividade seja da faturar grupo, então manda todas as rotas
			 * 
			 * Analista: Sávio Luiz em 10/06/2010 
			 */
				
				System.out.println("ROTA GERAR CREDITO SITUACAO ESPECIAL FATURAMENTO "
						+ ((Rota) array[1]).getId()
						+ "*********************************************************");

				enviarMensagemControladorBatch(
						ConstantesJNDI.BATCH_GERAR_CREDITO_SITUACAO_ESPECIAL_FATURAMENTO_MDB,
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
		AgendadorTarefas.agendarTarefa("GerarCreditoSituacaoEspecialFaturamentoBatch", this);
	}

}

