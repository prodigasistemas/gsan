package gcom.gui.faturamento.consumotarifa;

import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirManterCategoriaFaixaConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("manterCategoriaFaixaConsumoTarifa");

		InserirCategoriaFaixaConsumoTarifaActionForm inserirCategoriaFaixaConsumoTarifaActionForm = (InserirCategoriaFaixaConsumoTarifaActionForm) actionForm;

		//Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String consumoMinimo = null;
		
		
		if ((httpServletRequest.getParameter("parametro2") != null)
				&& (!httpServletRequest.getParameter("parametro2").equals(""))) {
			
			consumoMinimo = (String) httpServletRequest
			.getParameter("parametro2");
			
			sessao.setAttribute("consumoMinimo", consumoMinimo);
		}

		if ((httpServletRequest.getParameter("parametro1") != null)
				&& (!httpServletRequest.getParameter("parametro1").equals(""))) {
			String categoriaSelected = (String) httpServletRequest
					.getParameter("parametro1");
			sessao.setAttribute("categoriaSelected", categoriaSelected);
		}

		if ((httpServletRequest.getParameter("parametro3") != null)
				&& (!httpServletRequest.getParameter("parametro3").equals(""))) {
			String valorMinimo = (String) httpServletRequest
					.getParameter("parametro3");
			sessao.setAttribute("valorMinimo", valorMinimo);
		}

		if ((httpServletRequest.getParameter("limpaFaixa") != null)
				&& (httpServletRequest.getParameter("limpaFaixa").equals("1"))) {
			sessao
					.removeAttribute("InserirCategoriaFaixaConsumoTarifaActionForm");
		}
		
		Collection colecaoFaixa = (Collection) sessao.getAttribute("colecaoFaixa");
		
		if ((colecaoFaixa != null) && (!colecaoFaixa.isEmpty())){
			Iterator colecaoFaixaIt = colecaoFaixa.iterator();
			//boolean i = false;
			while (colecaoFaixaIt.hasNext()) {
				ConsumoTarifaFaixa consumoTarifaFaixa = (ConsumoTarifaFaixa) colecaoFaixaIt.next();
				
				if (consumoTarifaFaixa.getNumeroConsumoFaixaFim().toString().equals("999999")){
					//i = true;
				}
				
			}
		///	if (i){
		//		throw new ActionServletException(
		//			"atencao.faixa_limite_superior_existe");
		//	}
		}


		if (httpServletRequest.getParameter("limpaForm") != null){
			inserirCategoriaFaixaConsumoTarifaActionForm.setLimiteSuperiorFaixa("");
			inserirCategoriaFaixaConsumoTarifaActionForm.setValorM3Faixa("");
		}
		
		return retorno;
	}

}
