package gcom.tarefa;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import gcom.seguranca.acesso.usuario.Usuario;

/**
 * Classe que representa uma tarefa
 * 
 * @author thiago
 * @date 24/01/2006
 */
public class ImplementacaoTesteTarefa extends Tarefa {
	private static final long serialVersionUID = 1L;
	public ImplementacaoTesteTarefa(Usuario usuario,
			int idFuncionalidadeIniciada) {
		super(usuario, idFuncionalidadeIniciada);

	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public Object executar() throws TarefaException {
		return null;
	}

	public void agendarTarefaBatch() {
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		
	}

}
