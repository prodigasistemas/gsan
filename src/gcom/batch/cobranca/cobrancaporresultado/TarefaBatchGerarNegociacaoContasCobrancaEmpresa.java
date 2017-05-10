package gcom.batch.cobranca.cobrancaporresultado;

import gcom.cadastro.empresa.Empresa;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Iterator;

public class TarefaBatchGerarNegociacaoContasCobrancaEmpresa extends TarefaBatch {

	private static final long serialVersionUID = 1L;

	public TarefaBatchGerarNegociacaoContasCobrancaEmpresa(Usuario usuario, int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchGerarNegociacaoContasCobrancaEmpresa() {
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

		Collection<Empresa> empresas = (Collection<Empresa>) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator<Empresa> it = empresas.iterator();

		while (it.hasNext()) {

			Empresa empresa = (Empresa) it.next();

			enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_NEGOCIACAO_CONTAS_COBRANCA_POR_EMPRESA,
					new Object[] { this.getIdFuncionalidadeIniciada(), empresa.getId() });

		}

		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("TarefaBatchGerarNegociacaoContasCobrancaEmpresa", this);

	}
	

}
