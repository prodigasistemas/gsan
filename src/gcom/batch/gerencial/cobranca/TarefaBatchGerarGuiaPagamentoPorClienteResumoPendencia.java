package gcom.batch.gerencial.cobranca;

import gcom.cadastro.localidade.Localidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

/**
 * Tarefa que manda para batch Gerar Guia Pagamento por Cliente Resumo Pendencia
 * 
 * @author Arthur Carvalho
 * @created 24/07/2007
 */
public class TarefaBatchGerarGuiaPagamentoPorClienteResumoPendencia extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarGuiaPagamentoPorClienteResumoPendencia(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarGuiaPagamentoPorClienteResumoPendencia() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection colecaoLocalidade = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iterator = colecaoLocalidade.iterator();

		while (iterator.hasNext()) {

			Integer idLocaldiade = ( (Localidade) iterator.next() ).getId();

			System.out
					.println("LOCALIDADE GERAR RESUMO PENDENCIA"
							+ (idLocaldiade)
							+ "*********************************************************");

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_GUIA_PAGAMENTO_POR_CLIENTE_RESUMO_PENDENCIA,
					new Object[]{idLocaldiade,
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
		AgendadorTarefas.agendarTarefa("GerarGuiaPagamentoPorClienteResumoPendencia",
				this);
	}

}
