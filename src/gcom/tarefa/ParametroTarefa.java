package gcom.tarefa;

import java.io.Serializable;


/**
 * Classe que representa uma Parametros Tarefa
 * 
 * @author thiago
 * @date 24/01/2006
 */
public class ParametroTarefa implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nome;
	private Object valor;

	public ParametroTarefa(String nome, Object obj) {
		this.nome = nome;
		this.valor = obj; 
	}
	public ParametroTarefa() {

	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setValor(Object valor) {
		this.valor = valor;
	}
	/**
	 * @return Retorna o campo valor.
	 */
	public Object getValor() {
		return valor;
	}
}
