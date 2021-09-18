package gcom.batch.faturamento;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.ejb.CreateException;

import gcom.cadastro.localidade.Localidade;
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

public class TarefaBatchEnvioNotificacaoAvisoCorte extends TarefaBatch{

	public TarefaBatchEnvioNotificacaoAvisoCorte(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	private static final long serialVersionUID = 1L;
	
	@Deprecated
	public TarefaBatchEnvioNotificacaoAvisoCorte() {
		super(null, 0);
	}

	@Override
	public Object executar() throws TarefaException {
		
		Collection colecaoLocalidade = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
		
        Iterator iterator = colecaoLocalidade.iterator();
        
        while (iterator.hasNext()) {

            Localidade localidade = (Localidade) iterator.next();
            
			enviarMensagemControladorBatch(ConstantesJNDI.BATCH_ENVIO_NOTIFICACAO_AVISO_CORTE,
					new Object[] { this.getIdFuncionalidadeIniciada(), Collections.singletonList(localidade.getId()) });
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
		AgendadorTarefas.agendarTarefa("EnvioNotificacaoAvisoCorteBatch", this);
	}

}
