package gcom.gui.cadastro.imovel;

import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ClientesImoveisRelacionadosAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirClientesImoveisRelacionados");
		
		//Fachada fachada = Fachada.getInstancia();
		
		//FiltroNomeConta filtroNomeConta = new FiltroNomeConta();
		
		Collection collectionsNomeConta = null;//fachada.pesquisar(filtroNomeConta, NomeConta.class.getName() );
		
		httpServletRequest.setAttribute("collectionsNomeConta", collectionsNomeConta);
		
		return retorno;
	}

}
