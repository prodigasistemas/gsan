package gcom.gui.arrecadacao.banco;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Rodrigo
 */
public class ValidarProcessoUmInserirAvisoBancarioAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 * 
	 * Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter*
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = null;

		InserirAvisoBancarioActionForm form = (InserirAvisoBancarioActionForm) actionForm;
		HttpSession session = httpServletRequest.getSession(false);

		String sDataLancamento = form.getDataLancamento();
		Date dataLancamento = Util.converteStringParaDate(sDataLancamento);
		Date atual = new Date(System.currentTimeMillis());
		if (dataLancamento.getTime() > atual.getTime()) {
			throw new ActionServletException("atencao.data.lancamento.posterior",
					null, Util.formatarData(atual));
		}
		
		session.setAttribute("dataLancamento", sDataLancamento);

		return retorno;
	}
}
