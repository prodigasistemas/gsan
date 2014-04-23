package gcom.gui.cadastro.tarifasocial;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0217] Inserir Resolução de Diretoria
 * @author Rafael Corrêa
 * @since 30/03/2006
 */
public class RemoverTarifaSocialActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String idMotivoExclusao;
	
	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		idMotivoExclusao = null;
	}

	/**
	 * @return Retorna o campo idMotivoExclusao.
	 */
	public String getIdMotivoExclusao() {
		return idMotivoExclusao;
	}

	/**
	 * @param idMotivoExclusao O idMotivoExclusao a ser setado.
	 */
	public void setIdMotivoExclusao(String idMotivoExclusao) {
		this.idMotivoExclusao = idMotivoExclusao;
	}

	

}
