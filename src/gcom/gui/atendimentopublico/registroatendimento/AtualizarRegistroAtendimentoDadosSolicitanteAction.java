package gcom.gui.atendimentopublico.registroatendimento;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade validar as informações da terceira aba do
 * processo de asualização de um registro de atendimento
 * 
 * @author Sávio Luiz
 * @date 10/08/2006
 */
public class AtualizarRegistroAtendimentoDadosSolicitanteAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		HttpSession sessao = httpServletRequest.getSession(false);

		// recupera a coleção de RA solicitante
		Collection colecaoRASolicitante = (Collection) sessao
				.getAttribute("colecaoRASolicitante");

		//AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm = (AtualizarRegistroAtendimentoActionForm) actionForm;

		if (colecaoRASolicitante == null || colecaoRASolicitante.isEmpty()) {

			throw new ActionServletException(
					"atencao.informar_registro_atendimento_solicitante");
		}

		return retorno;
	}
}
