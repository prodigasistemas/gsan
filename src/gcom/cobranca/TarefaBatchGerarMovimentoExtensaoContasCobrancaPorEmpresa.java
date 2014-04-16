package gcom.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

public class TarefaBatchGerarMovimentoExtensaoContasCobrancaPorEmpresa extends
		TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarMovimentoExtensaoContasCobrancaPorEmpresa(
			Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarMovimentoExtensaoContasCobrancaPorEmpresa() {
		super(null, 0);
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object executar() throws TarefaException {

		Collection colecaoLocalidades = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		//Collection colecaoComandoEmpresaCobrancaContaExtensao = (Collection) getParametro("colecaoComandoEmpresaCobrancaContaExtensao");

		Iterator it = colecaoLocalidades.iterator();
		while (it.hasNext()) {

			Integer idLocalidade = (Integer) it.next();

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_MOVIMENTO_EXTENSAO_CONTAS_COBRANCA_POR_EMPRESA,
					new Object[] { idLocalidade,
							this.getIdFuncionalidadeIniciada() });

		}

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"GerarMovimentoExtensaoContasCobrancaPorEmpresaBatch", this);

	}
}
