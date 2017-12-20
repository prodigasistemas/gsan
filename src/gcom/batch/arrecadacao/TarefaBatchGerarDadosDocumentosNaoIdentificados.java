package gcom.batch.arrecadacao;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

public class TarefaBatchGerarDadosDocumentosNaoIdentificados extends TarefaBatch {

	private static final long serialVersionUID = 2127305283765363295L;

	public TarefaBatchGerarDadosDocumentosNaoIdentificados(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchGerarDadosDocumentosNaoIdentificados() {
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

	@Override
	public Object executar() throws TarefaException {
		Integer anoMesReferenciaArrecadacao = (Integer) getParametro("anoMesReferenciaArrecadacao");

		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_GERAR_DADOS_DOCUMENTOS_NAO_IDENTIFICADOS,
				new Object[] { this.getIdFuncionalidadeIniciada(), anoMesReferenciaArrecadacao });
		return null;
	}

	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("TarefaBatchGerarDadosPagamentosNaoClassificados", this);
	}

}

