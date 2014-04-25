package gcom.batch.gerencial.cobranca;

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
 * Tarefa que manda para batch Gerar Resumo Pendencia
 * 
 * @author Bruno Leonardo R. Barros
 * @created 24/07/2007
 */
public class TarefaBatchGerarResumoPendenciaPorAno extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarResumoPendenciaPorAno(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarResumoPendenciaPorAno() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection colecaoLocalidade = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iterator = colecaoLocalidade.iterator();

		while (iterator.hasNext()) {

			Integer idSetor = ( (SetorComercial) iterator.next() ).getId();

			System.out
					.println("SETOR COMERCIAL GERAR RESUMO PENDENCIA POR ANO"
							+ (idSetor)
							+ "*********************************************************");

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_RESUMO_PENDENCIA_POR_ANO,
					new Object[]{idSetor,
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
		AgendadorTarefas.agendarTarefa("GerarResumoPendenciaPorAno",
				this);
	}

}
