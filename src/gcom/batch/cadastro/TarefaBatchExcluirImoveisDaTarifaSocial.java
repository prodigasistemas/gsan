package gcom.batch.cadastro;

import gcom.cadastro.localidade.SetorComercial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * [UCXXXX] Excluir Imoveis Da Tarifa Social
 * Tarefa que manda para batch Excluir Imoveis Da Tarifa Social
 * 
 * @author Genival Barbosa
 * @created 15/09/2009
 */
public class TarefaBatchExcluirImoveisDaTarifaSocial extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchExcluirImoveisDaTarifaSocial(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchExcluirImoveisDaTarifaSocial() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {
		
		Integer anoMesFaturamento = (Integer) getParametro("anoMesReferenciaFaturamento");
		Collection colecaoSetor = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iterator = colecaoSetor.iterator();

		while (iterator.hasNext()) {

			Integer idSetor = ( (SetorComercial) iterator.next() ).getId();

			System.out
					.println("Localidade EXCLUIR IMOVEIS DA TARIFA SOCIAL"
							+ (idSetor)
							+ "*********************************************************");
			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_EXCLUIR_IMOVEIS_DA_TARIFA_SOCIAL,
					new Object[]{idSetor,
							this.getIdFuncionalidadeIniciada(),
							anoMesFaturamento});

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
		AgendadorTarefas.agendarTarefa("ExcluirImoveisDaTarifaSocialBatch", this);
	}

}
