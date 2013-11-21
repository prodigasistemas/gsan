package gcom.gui.relatorio.cobranca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

public class AdicionarFaixaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		FaixaForm form = (FaixaForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(form.getDescricao()==null ||
				(form.getDescricao()!=null && form.getDescricao().equals(""))){
			throw new ActionServletException("atencao.informe_os_campos_obrigatorios");
		}	
		if(form.getValorInicial()==null){
			throw new ActionServletException("atencao.informe_os_campos_obrigatorios");
		}
		if(form.getValorFinal()==null){
			throw new ActionServletException("atencao.informe_os_campos_obrigatorios");
		}
		
		FaixaHelper helper = 
			new FaixaHelper(
					form.getDescricao(),
					form.getValorInicial(),
					form.getValorFinal());
		
		sessao.setAttribute("faixaAdicionar", helper);
		httpServletRequest.setAttribute("reload", "");
		
		form.reset();
		
		return actionMapping.findForward("adicionarFaixaAction");
	}
}
