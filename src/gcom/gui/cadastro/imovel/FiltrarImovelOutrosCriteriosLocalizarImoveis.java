package gcom.gui.cadastro.imovel;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

public class FiltrarImovelOutrosCriteriosLocalizarImoveis extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("filtrarImovelOutrosCriterios");

		Fachada fachada = Fachada.getInstancia();

		//Pesquisar Gerências Regionais 
		Collection<GerenciaRegional> colecaoGerenciasRegionais = fachada
				.pesquisar(new FiltroGerenciaRegional(), GerenciaRegional.class
						.getName());		
		
		httpServletRequest.setAttribute("colecaoGerenciasRegionais",
				colecaoGerenciasRegionais);

		return retorno;
	}

}
