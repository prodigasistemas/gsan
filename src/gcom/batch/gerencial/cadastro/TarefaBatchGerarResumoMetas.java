package gcom.batch.gerencial.cadastro;

import gcom.cadastro.localidade.SetorComercial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * Tarefa que manda para batch Gerar Resumo Metas
 * @author Sávio Luiz
 * @created 23/07/2007
 */
public class TarefaBatchGerarResumoMetas extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoMetas(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoMetas() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection colecaoIdsSetorComercialParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		
		Date dataInicial = (Date)getParametro("dataInicial");
		
		Date dataFinal = (Date)getParametro("dataFinal");

		Iterator iterator = colecaoIdsSetorComercialParaExecucao.iterator();

		while (iterator.hasNext()) {

			Integer idSetor = ( (SetorComercial) iterator.next() ).getId();

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_RESUMO_METAS_MDB,
					new Object[]{idSetor,dataInicial,dataFinal,
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
		AgendadorTarefas.agendarTarefa("GerarResumoMetasBatch",
				this);
	}

}
