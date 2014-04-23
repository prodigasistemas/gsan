package gcom.tarefa;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Classe que representa uma tarefa batch no sistema
 * 
 * @author Rodrigo Silveira
 * @date 20/07/2006
 */
public abstract class TarefaBatch extends Tarefa {
	
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	
	protected Collection unidadesJaExecutadas = new ArrayList();
	
	public TarefaBatch(Usuario usuario, int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);

	}

	/**
	 * Pesquisa no sistema para descobrir todas as unidades de processamento
	 * referentes à tarefa
	 * 
	 * @author Rodrigo Silveira
	 * @date 20/07/2006
	 * 
	 * @return coleção com todas as unidades de processamento para o projeto
	 */
	protected abstract Collection<Object> pesquisarTodasUnidadeProcessamentoBatch();

	/**
	 * Pesquisa no sistema para descobrir quais unidades de processamento restam
	 * processar caso a tarefa seja reiniciada
	 * 
	 * @author Rodrigo Silveira
	 * @date 20/07/2006
	 * 
	 * @return coleção com todas as unidades de processamento para o projeto
	 */
	protected abstract Collection<Object> pesquisarTodasUnidadeProcessamentoReinicioBatch();

	protected void enviarMensagemControladorBatch(String queueMDB,
			Object[] parametros) {

		// pega a instância do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();
			locator.enviarMensagemJms(queueMDB, parametros);

		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}

	public final void execute(JobExecutionContext arg0)
			throws JobExecutionException {

		this.setIdFuncionalidadeIniciada((Integer) arg0.getJobDetail()
				.getJobDataMap().get("idFuncionalidadeIniciada"));
		this.setUsuario((Usuario) arg0.getJobDetail().getJobDataMap().get(
				"usuario"));
		this.setParametroTarefa((Set) arg0.getJobDetail().getJobDataMap().get(
				"parametroTarefa"));
		this.executar();
	}

	public Collection getUnidadesJaExecutadas() {
		return unidadesJaExecutadas;
	}

	public void setUnidadesJaExecutadas(Collection unidadesJaExecutadas) {
		this.unidadesJaExecutadas = unidadesJaExecutadas;
	}

}
