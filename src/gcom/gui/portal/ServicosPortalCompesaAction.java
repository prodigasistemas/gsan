package gcom.gui.portal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import gcom.gui.GcomAction;

public class ServicosPortalCompesaAction  extends GcomAction{

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("servicosCompesaAction");
		ExibirServicosPortalCompesaActionForm form = (ExibirServicosPortalCompesaActionForm) actionForm;
		
		String method = httpServletRequest.getParameter("method");
		
		if(method != null && method.equalsIgnoreCase("servicos")){
			try{
				Integer matricula = Integer.valueOf(form.getMatricula());
				Integer matriculaExistente = this.getFachada().verificarExistenciaImovel(matricula);
				if (matriculaExistente == 1){
					String nomeUsuario = this.getFachada().consultarClienteUsuarioImovel(matricula);
					form.setNomeUsuario(nomeUsuario);
					retorno = actionMapping.findForward("servicosPortalCompesaAction");
				}else{
					httpServletRequest.setAttribute("imovelInvalido", true);
				}
			}catch(NumberFormatException e){
				httpServletRequest.setAttribute("imovelInvalido", true);
			}
		}
		return retorno;
	}
}