package gcom.interceptor;

import java.io.Serializable;

/**
 * Objeto que representa um objeto do sistema Gcom
 *
 * date 16/01/2006
 * @author thiago
 */
public abstract class ObjetoGcom implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Método que retorna os campos das chaves primarias
	 *
	 * @return
	 */
	public abstract String[] retornaCamposChavePrimaria();
}
