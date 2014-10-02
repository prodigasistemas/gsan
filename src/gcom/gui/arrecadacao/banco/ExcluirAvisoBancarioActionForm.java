package gcom.gui.arrecadacao.banco;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter aviso bancario 
 *
 * @author thiago
 *
 * @date 10/03/2006
 */
public class ExcluirAvisoBancarioActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String[] idRegistrosRemocao = null;

	/**
	 * Método que limpa os atributos 
	 *
	 * @author Administrador
	 * @date 10/03/2006
	 *
	 * @param arg0 mapeamento
	 * @param arg1 request
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		
		super.reset(arg0, arg1);

		this.idRegistrosRemocao = null;
	}

	/**
	 * @return Retorna o campo idRegistrosRemocao.
	 */
	public String[] getIdRegistrosRemocao() {
		return idRegistrosRemocao;
	}

	/**
	 * @param idRegistrosRemocao O idRegistrosRemocao a ser setado.
	 */
	public void setIdRegistrosRemocao(String[] idRegistrosRemocao) {
		this.idRegistrosRemocao = idRegistrosRemocao;
	}
}
