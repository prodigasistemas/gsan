package gcom.batch.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

public class TarefaBatchGerarArquivoTextoContasCobrancaEmpresa extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarArquivoTextoContasCobrancaEmpresa(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarArquivoTextoContasCobrancaEmpresa() {
		super(null, 0);
	}

	@SuppressWarnings("rawtypes")
	public Object executar() throws TarefaException {
		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_ARQUIVO_TEXTO_CONTAS_COBRANCA_POR_EMPRESA,
				new Object[] { (Collection) getParametro("idsRegistros"), (Integer) getParametro("idEmpresa"), this.getIdFuncionalidadeIniciada() });

		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection pesquisarTodasUnidadeProcessamentoBatch() {
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection pesquisarTodasUnidadeProcessamentoReinicioBatch() {
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("GerarArquivoTextoContasCobrancaEmpresaBatch", this);
	}

}
