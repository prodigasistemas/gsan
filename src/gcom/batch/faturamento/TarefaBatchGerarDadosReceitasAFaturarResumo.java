package gcom.batch.faturamento;

import gcom.faturamento.FaturamentoGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

public class TarefaBatchGerarDadosReceitasAFaturarResumo extends TarefaBatch {

	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarDadosReceitasAFaturarResumo(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchGerarDadosReceitasAFaturarResumo() {
		super(null, 0);
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public Object executar() throws TarefaException {
		Integer anoMesReferencia = (Integer) getParametro("anoMesReferencia");

		Collection<FaturamentoGrupo> colecaoFaturamentoGrupos = (Collection<FaturamentoGrupo>) getParametro(
				ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iterator = colecaoFaturamentoGrupos.iterator();

		while (iterator.hasNext()) {

			FaturamentoGrupo grupo = (FaturamentoGrupo) iterator.next();

			enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_DADOS_RECEITAS_A_FATURAR__RESUMO_MDB,
					new Object[] { anoMesReferencia, grupo.getId(), this.getIdFuncionalidadeIniciada() });
		}

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("GerarDadosRelatorioReceitasAFaturar", this);
	}

}
