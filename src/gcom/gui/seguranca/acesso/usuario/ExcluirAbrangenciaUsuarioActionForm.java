package gcom.gui.seguranca.acesso.usuario;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Form que exibe o menu
 * 
 * @author Thiago Tenório
 * @date 24/05/2006
 */
public class ExcluirAbrangenciaUsuarioActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String[] ids = null;

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		ids = null;
	}

	/**
	 * @return Retorna o campo ids.
	 */
	public String[] getIds() {
		return ids;
	}

	/**
	 * @param ids O ids a ser setado.
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}
}
