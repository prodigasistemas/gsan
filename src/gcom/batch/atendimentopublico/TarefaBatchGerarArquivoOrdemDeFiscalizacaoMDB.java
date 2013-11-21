package gcom.batch.atendimentopublico;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;

public class TarefaBatchGerarArquivoOrdemDeFiscalizacaoMDB extends TarefaBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TarefaBatchGerarArquivoOrdemDeFiscalizacaoMDB(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}
	
	@Deprecated
	public TarefaBatchGerarArquivoOrdemDeFiscalizacaoMDB() {
		super(null, 0);
	}
	
	@Override
	public Object executar() throws TarefaException {
		Usuario usuarioLogado = this.getUsuario();

		enviarMensagemControladorBatch(
                ConstantesJNDI.BATCH_GERAR_ARQUIVO_ORDEM_FISCALIZACAO,
                new Object[] { this.getIdFuncionalidadeIniciada(),usuarioLogado});
		
		return null;
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
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("BatchGerarArquivoOrdemDeFiscalizacao",this);

	}


}
