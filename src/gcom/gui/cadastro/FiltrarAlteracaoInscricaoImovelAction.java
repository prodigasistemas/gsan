package gcom.gui.cadastro;

import gcom.cadastro.ImovelInscricaoAlteradaHelper;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1162]	AUTORIZAR ALTERACAO INSCRICAO IMOVEL
 * 
 * @author Rodrigo Cabral
 * @date 05/04/2011
 */

public class FiltrarAlteracaoInscricaoImovelAction extends GcomAction {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("autorizarAlteracaoInscricaoImovel");
		
//		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarAlteracaoInscricaoImovelActionForm form = (FiltrarAlteracaoInscricaoImovelActionForm) actionForm;
		
		
		// Recupera todos os campos da página para ser colocada no filtro
		// posteriormente
		Integer idLocalidade = null;
		String desLocalidade = null;
		Integer codigoSetorComercial = null;
		String desSetorComercial = null;
		
		
		if (form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")){
			idLocalidade = new Integer(form.getIdLocalidade());
			desLocalidade = form.getDesLocalidade();
		}
		
		if (form.getCodigoSetorComercial() != null && !form.getCodigoSetorComercial().equals("")){
			codigoSetorComercial = new Integer(form.getCodigoSetorComercial());
			desSetorComercial = form.getDesSetorComercial();
		}
	
		
		
		ImovelInscricaoAlteradaHelper helper = new ImovelInscricaoAlteradaHelper();
		helper.setCodigoSetorComercial(codigoSetorComercial);
		helper.setDesSetorComercial(desSetorComercial);
		helper.setIdLocalidade(idLocalidade);
		helper.setDesLocalidade(desLocalidade);
		
		// Manda o filtro pela sessao para o
		// ExibirAutorizarAlteracaoInscricaoImovelAction
		
		sessao.setAttribute("imovelInscricaoAlterada", helper);
				
		return retorno;
		}
}

				
				
			
				
				
				
				
