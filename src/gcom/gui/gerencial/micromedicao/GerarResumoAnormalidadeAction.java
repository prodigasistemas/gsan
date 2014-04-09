package gcom.gui.gerencial.micromedicao;

import gcom.fachada.FachadaBatch;
import gcom.gui.GcomAction;
import gcom.util.email.ErroEmailException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class GerarResumoAnormalidadeAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ErroEmailException {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaPrincipal");
		// Obtém a instância da fachada
		FachadaBatch fachadaBatch = FachadaBatch.getInstancia();

		fachadaBatch.gerarResumoAnormalidadeLeitura();

		return retorno;
	}
}
