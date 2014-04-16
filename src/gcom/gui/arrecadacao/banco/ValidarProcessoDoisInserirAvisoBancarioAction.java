package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

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
public class ValidarProcessoDoisInserirAvisoBancarioAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter*
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = null;
		
		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		
		InserirAvisoBancarioActionForm form = (InserirAvisoBancarioActionForm) actionForm;
		
		if (form.getAvisoBancario() != null) {
			String[] idsAvisosBancarios = form.getAvisoBancario();
			String idAvisoBancario = (String) idsAvisosBancarios[0];
			FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
			filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
			
			Collection colecaoAvisosBancarios = fachada.pesquisar(filtroAvisoBancario, AvisoBancario.class.getName());
			
			if (colecaoAvisosBancarios != null && !colecaoAvisosBancarios.isEmpty()) {
				AvisoBancario avisoBancario = (AvisoBancario) colecaoAvisosBancarios.iterator().next();
				sessao.setAttribute("avisoBancario", avisoBancario);
			} else {
				if (sessao.getAttribute("avisoBancario") != null) {
					sessao.removeAttribute("avisoBancario");
				}
			}
			
		}
			

		
		
		
		return retorno;
	}
}
