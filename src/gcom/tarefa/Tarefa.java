package gcom.tarefa;

import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Classe que representa uma tarefa
 * 
 * @author thiago
 * @date 24/01/2006
 */
public abstract class Tarefa implements Serializable, Job {

	protected static final long serialVersionUID = 1L;
		
	private Set parametroTarefa = new HashSet();

	private Usuario usuario;

	private int idFuncionalidadeIniciada;

	public Tarefa(Usuario usuario, int idFuncionalidadeIniciada) {
		this.usuario = usuario;
		this.idFuncionalidadeIniciada = idFuncionalidadeIniciada;

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set getParametroTarefa() {
		return parametroTarefa;

	}

	public void setParametroTarefa(Set parametroTarefa) {
		this.parametroTarefa = parametroTarefa;
	}

	/**
	 * Método que adiciona um Short
	 * 
	 * @param nome
	 * @param valor
	 */
	public void addParametro(String nome, Object valor) {
		parametroTarefa.add(new ParametroTarefa(nome, valor));
	}

	/**
	 * 
	 * @author Thiago Toscano
	 * @date 23/05/2006
	 * @param nome
	 * @return
	 */
	public Object getParametro(String nome) {
		Object retorno = null;
		Iterator it = parametroTarefa.iterator();
		while (it.hasNext()) {
			ParametroTarefa parametroTarefa = (ParametroTarefa) it.next();
			if (nome != null && nome.equals(parametroTarefa.getNome())) {
				retorno = parametroTarefa.getValor();
			}
		}
		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 */
	public abstract Object executar() throws TarefaException;

	public abstract void execute(JobExecutionContext arg0)
			throws JobExecutionException;

	/**
	 * Faz o agendamento da tarefa batch no sistema
	 * 
	 * @author Rodrigo Silveira
	 * @date 03/08/2006
	 * 
	 */
	public abstract void agendarTarefaBatch();

	public int getIdFuncionalidadeIniciada() {
		return idFuncionalidadeIniciada;
	}

	public void setIdFuncionalidadeIniciada(int idFuncionalidadeIniciada) {
		this.idFuncionalidadeIniciada = idFuncionalidadeIniciada;
	}

}
