package gcom.batch.faturamento;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import gcom.faturamento.FaturamentoAtivCronRota;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

public class TarefaBatchEnvioEmailNotificacaoVencimentoFatura extends TarefaBatch{

	public TarefaBatchEnvioEmailNotificacaoVencimentoFatura(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	private static final long serialVersionUID = 1L;
	
	@Deprecated
	public TarefaBatchEnvioEmailNotificacaoVencimentoFatura() {
		super(null, 0);
	}

	@Override
	public Object executar() throws TarefaException {
		Integer anoMesReferenciaArrecadacao = (Integer) getParametro("anoMesReferenciaArrecadacao");

		enviarMensagemControladorBatch(ConstantesJNDI.BATCH_ENVIO_EMAIL_NOTIFICACAO_VENCIMENTO_FATURA,
				new Object[] { this.getIdFuncionalidadeIniciada(), anoMesReferenciaArrecadacao });
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
		AgendadorTarefas.agendarTarefa("FaturarGrupoFaturamentoBatch", this);
	}

}
