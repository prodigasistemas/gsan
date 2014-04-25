package gcom.batch.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.CobrancaGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Date;

/**
 * @author Vivianne Sousa
 * @created 17/11/2006
 */
public class TarefaBatchEmitirDocumentoCobranca extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchEmitirDocumentoCobranca(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchEmitirDocumentoCobranca() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

			CobrancaGrupo grupoCobranca = (CobrancaGrupo) getParametro("grupoCobranca");
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) getParametro("comandoAtividadeAcaoCobranca");
			CobrancaAcaoAtividadeComando comandoAtividadeAcaoComando = (CobrancaAcaoAtividadeComando) getParametro("comandoAtividadeAcaoComando");
			CobrancaAcao acaoCobranca = (CobrancaAcao) getParametro("acaoCobranca");
			CobrancaCriterio cobrancaCriterio = (CobrancaCriterio) getParametro("criterioCobranca");
			Date dataAtual = (Date) getParametro("dataAtual");

			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_EMITIR_DOCUMENTO_COBRANCA,
					new Object[]{grupoCobranca,
							cobrancaAcaoAtividadeCronograma,
							comandoAtividadeAcaoComando,
							acaoCobranca, 
							cobrancaCriterio, 
							dataAtual,
							this.getIdFuncionalidadeIniciada()});
			

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
		AgendadorTarefas.agendarTarefa("EmitirDocumentoCobrancaBatch", this);
	}
	
}
