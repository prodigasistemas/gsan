package gcom.batch.faturamento;

import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * [UC1218] Suspender Leitura para Imóvel com Consumo Real não Superior a 10m3
 * 
 * @author Vivianne Sousa
 * @date 26/08/2011
 */
public class TarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10 extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchSuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo) getParametro("faturamentoGrupo");
		Integer anoMesFaturamentoGrupo = (Integer) getParametro("anoMesFaturamentoGrupo");
		Collection colecaoRotasParaExecucao = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iterator = colecaoRotasParaExecucao.iterator();

		while (iterator.hasNext()) {

			Object[] array = (Object[]) iterator.next();
			Integer idRota = ((Rota) array[1]).getId();
			System.out.println("ROTA "	+ idRota + "*********************************************************");

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_SUSPENDER_LEITURA_PARA_IMOVEL_COM_CONSUMO_REAL_NAO_SUPERIOR_A_10_MDB,
					new Object[]{this.getIdFuncionalidadeIniciada(),anoMesFaturamentoGrupo,faturamentoGrupo.getId(),idRota});
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
		AgendadorTarefas.agendarTarefa("SuspenderLeituraParaImovelComConsumoRealNaoSuperiorA10Batch", this);
	}

}
