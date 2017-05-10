package gcom.batch.cobranca;

import gcom.cadastro.localidade.Localidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

public class TarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa extends
		TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa(
			Usuario usuario, int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa() {
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

	@SuppressWarnings("unchecked")
	@Override
	public Object executar() throws TarefaException {

		Collection<Localidade> localidades = (Collection<Localidade>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator<Localidade> it = localidades.iterator();

		while (it.hasNext()) {

			Localidade localidade = (Localidade) it.next();

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_GERAR_ARQUIVO_TEXTO_PAGAMENTOS_CONTAS_COBRANCA_POR_EMPRESA,
					new Object[] { this.getIdFuncionalidadeIniciada(), localidade.getId()});
		}

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa(
				"TarefaBatchGerarArquivoTextoPagametosContasCobrancaEmpresa",
				this);

	}

}
