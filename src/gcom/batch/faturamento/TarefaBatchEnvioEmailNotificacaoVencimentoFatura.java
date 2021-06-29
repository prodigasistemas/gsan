package gcom.batch.faturamento;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.ejb.CreateException;

import gcom.faturamento.FaturamentoAtivCronRota;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.Rota;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
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
		
		Collection colecaoRota = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);

		Iterator iteratorRotas = colecaoRota.iterator();

		while (iteratorRotas.hasNext()) {
			Rota rota = (Rota) iteratorRotas.next();

			enviarMensagemControladorBatch(ConstantesJNDI.BATCH_ENVIO_EMAIL_NOTIFICACAO_VENCIMENTO_FATURA,
					new Object[] { this.getIdFuncionalidadeIniciada(), rota.getId() });
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
		AgendadorTarefas.agendarTarefa("EnvioEmailNotificacaoVencimentoFaturaBatch", this);
	}
	
	
	private ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorMicromedicaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

}
