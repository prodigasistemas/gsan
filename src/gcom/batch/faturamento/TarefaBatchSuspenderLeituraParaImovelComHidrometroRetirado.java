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
 * [UC1216] Suspender Leitura para Imóvel com Hidrômetro Retirado
 * 
 * @author Vivianne Sousa
 * @date 23/08/2011
 */
public class TarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchSuspenderLeituraParaImovelComHidrometroRetirado() {
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
					ConstantesJNDI.BATCH_SUSPENDER_LEITURA_PARA_IMOVEL_COM_HIDROMETRO_RETIRADO_MDB,
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
		AgendadorTarefas.agendarTarefa("SuspenderLeituraParaImovelComHidrometroRetiradoBatch", this);
	}

}
