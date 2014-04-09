package gcom.batch.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * 
 * @author: Vivianne Sousa
 * @date: 11/11/2009
 */
public class TarefaBatchGerarCartasDeFinalDeAno extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarCartasDeFinalDeAno(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarCartasDeFinalDeAno() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

//		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
//		Map parametros = (Map) getParametro(ConstantesSistema.PARAMETROS_BATCH);
		
		Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		Iterator iterator = colecaoRotasParaExecucao.iterator();

		while (iterator.hasNext()) {

			Integer idRota = (Integer) iterator.next();

			System.out.println("ROTA GERAR CARTAS " + idRota + " *********************************************************");

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_CARTAS_DE_FINAL_DE_ANO_MDB,
					new Object[]{idRota, this.getIdFuncionalidadeIniciada()});
			
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
		AgendadorTarefas.agendarTarefa("GerarCartasDeFinalDeAnoBatch", this);
	}

}
