package gcom.batch.atendimentopublico;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaBatch;
import gcom.tarefa.TarefaException;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * [UC1190] Programação Automática do Roteiro para Acompanhamento das OS
 *
 * @author Sávio Luiz
 * @date 30/07/2011
 */
public class TarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS extends TarefaBatch {
	
	private static final long serialVersionUID = 1L;

	public TarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS(Usuario usuario,
			int idFuncionalidadeIniciada) {

		super(usuario, idFuncionalidadeIniciada);
	}

	@Deprecated
	public TarefaBatchProgramacaoAutoRoteiroAcompanhamentoOS() {
		super(null, 0);
	}

	public Object executar() throws TarefaException {

			Collection idsUnidadesOrganizacionais = (Collection) getParametro(ConstantesSistema.COLECAO_UNIDADES_PROCESSAMENTO_BATCH);
			Date dataAtual = (Date) getParametro("dataAtual");

			Iterator iterator = idsUnidadesOrganizacionais.iterator();

			while (iterator.hasNext()) {

				Integer idUnidadeOrganizacional = (Integer) iterator.next();

				System.out.println("UNIDADE ORGANIZACIONAL "
								+ idUnidadeOrganizacional
								+ "*********************************************************");

				enviarMensagemControladorBatch(
						ConstantesJNDI.BATCH_PROGRAMACAO_AUTO_ROTEIRO_ACOMP_SERVICO,
						new Object[]{idUnidadeOrganizacional,
								dataAtual, this.getIdFuncionalidadeIniciada()});

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
		AgendadorTarefas.agendarTarefa("ProgramacaoAutoRoteiroAcompanhamentoOSBatch", this);
	}
	
}
