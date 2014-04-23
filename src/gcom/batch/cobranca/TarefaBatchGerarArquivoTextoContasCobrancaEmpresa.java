package gcom.batch.cobranca;

import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

public class TarefaBatchGerarArquivoTextoContasCobrancaEmpresa extends
		TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarArquivoTextoContasCobrancaEmpresa(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarArquivoTextoContasCobrancaEmpresa() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

		Collection idsRegistros = (Collection) getParametro("idsRegistros");

		Integer idEmpresa = (Integer) getParametro("idEmpresa");

		Collection colecaoUnidadeNegocio = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator it = colecaoUnidadeNegocio.iterator();

		while (it.hasNext()) {

			UnidadeNegocio unidadeNegocio = (UnidadeNegocio) it.next();

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_ARQUIVO_TEXTO_CONTAS_COBRANCA_POR_EMPRESA,
					new Object[] { idsRegistros, idEmpresa, unidadeNegocio.getId(),
							this.getIdFuncionalidadeIniciada() });

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
		AgendadorTarefas.agendarTarefa(
				"GerarArquivoTextoContasCobrancaEmpresaBatch", this);
	}

}
