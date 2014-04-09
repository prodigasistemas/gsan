package gcom.gui.cadastro.tarifasocial;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 *  asda
 * @author compesa
 * @created 28 de Junho de 2004
 */ 
public class ExibirAtualizarDadosTarifaSocialActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String idRegistroAtualizacao = null;

    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        idRegistroAtualizacao = null;
    }

	public String getIdRegistroAtualizacao() {
		return idRegistroAtualizacao;
	}

	public void setIdRegistroAtualizacao(String idRegistroAtualizacao) {
		this.idRegistroAtualizacao = idRegistroAtualizacao;
	}

}
