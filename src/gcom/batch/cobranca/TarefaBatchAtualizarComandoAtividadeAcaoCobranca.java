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

/**
 * @author Vivianne Sousa
 * @created 17/11/2006
 */
public class TarefaBatchAtualizarComandoAtividadeAcaoCobranca extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchAtualizarComandoAtividadeAcaoCobranca(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchAtualizarComandoAtividadeAcaoCobranca() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

			CobrancaGrupo grupoCobranca = (CobrancaGrupo) getParametro("grupoCobranca");
			Integer anoMesReferenciaCicloCobranca = (Integer) getParametro("anoMesReferenciaCicloCobranca");
			CobrancaAcaoAtividadeCronograma cobrancaAcaoAtividadeCronograma = (CobrancaAcaoAtividadeCronograma) getParametro("comandoAtividadeAcaoCobranca");
			CobrancaAcaoAtividadeComando comandoAtividadeAcaoComando = (CobrancaAcaoAtividadeComando) getParametro("comandoAtividadeAcaoComando");
			CobrancaAcao acaoCobranca = (CobrancaAcao) getParametro("acaoCobranca");
			Short indicadorCriterio = (Short) getParametro("indicadorCriterio");
			CobrancaCriterio cobrancaCriterio = (CobrancaCriterio) getParametro("criterioCobranca");
			Integer idCobrancaDocumentoControleGeracao = (Integer)getParametro("idCobrancaDocumentoControleGeracao");
			
			
			enviarMensagemControladorBatch(
					ConstantesJNDI.BATCH_ATUALIZAR_COMANDO_ATIVIDADE_ACAO_COBRANCA,
					new Object[]{grupoCobranca, 
							anoMesReferenciaCicloCobranca,
							cobrancaAcaoAtividadeCronograma,
							comandoAtividadeAcaoComando,
							acaoCobranca,
							indicadorCriterio.intValue(),
							cobrancaCriterio, 
							this.getIdFuncionalidadeIniciada(),
							idCobrancaDocumentoControleGeracao
							});
			
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
		AgendadorTarefas.agendarTarefa("AtualizarComandoAtividadeAcaoCobrancaBatch", this);
	}
	
}
