package gcom.gui.cadastro.entidadebeneficente;

import gcom.fachada.Fachada;import gcom.gui.GcomAction;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import org.apache.struts.action.ActionForm;import org.apache.struts.action.ActionForward;import org.apache.struts.action.ActionMapping;
/**
 * [UC0915] Inserir Entidade Beneficente.
 *  
 * @author Samuel Valerio
 * @date 11/06/2009
 * @since 4.1.6.4
 *
 */
public class InserirEntidadeBeneficenteAction extends GcomAction {
	@Override
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");
		 
		Fachada fachada = Fachada.getInstancia();
		 
		EntidadeBeneficenteActionForm form = (EntidadeBeneficenteActionForm) actionForm;
		
		Integer idRegistroAtualizacao = fachada.inserirEntidadeBeneficente(form.getEntidadeBeneficente());
		
		httpServletRequest.setAttribute("idRegistroAtualizacao",idRegistroAtualizacao);

		// [FS0007] - Verificar sucesso da operação
		montarPaginaSucesso(httpServletRequest, "Entidade Beneficente de código " + idRegistroAtualizacao
				+ " inserida com sucesso.", "Inserir outra Entidade Beneficente",
				"exibirInserirEntidadeBeneficenteAction.do?menu=sim");
		
		return retorno;
	}
}
