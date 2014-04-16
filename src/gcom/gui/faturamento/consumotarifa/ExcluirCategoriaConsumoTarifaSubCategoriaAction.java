package gcom.gui.faturamento.consumotarifa;

import gcom.gui.GcomAction;
import gcom.gui.faturamento.consumotarifa.bean.CategoriaFaixaConsumoTarifaHelper;

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
public class ExcluirCategoriaConsumoTarifaSubCategoriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
			
			ActionForward retorno = null;
		if (httpServletRequest.getParameter("manter") == null){
			retorno = actionMapping
				.findForward("inserirConsumoTarifaSubCategoria");

			HttpSession sessao = httpServletRequest.getSession(false);

			String posicaoExcluir = httpServletRequest.getParameter("posicao");

			Collection colecaoConsumoTarifaCategoria = null;

			if (sessao.getAttribute("colecaoConsumoTarifaCategoria") != null) {
				colecaoConsumoTarifaCategoria = (Collection) sessao
						.getAttribute("colecaoConsumoTarifaCategoria");
				Iterator colecaoConsumoTarifaCategoriaIterator = colecaoConsumoTarifaCategoria
						.iterator();

				while (colecaoConsumoTarifaCategoriaIterator.hasNext()) {
					CategoriaFaixaConsumoTarifaHelper categoriaFaixaConsumoTarifaHelper = (CategoriaFaixaConsumoTarifaHelper) colecaoConsumoTarifaCategoriaIterator
							.next();
					if (obterTimestampIdObjeto(categoriaFaixaConsumoTarifaHelper
							.getConsumoTarifaCategoria()) == Long
							.parseLong(posicaoExcluir)) {
						colecaoConsumoTarifaCategoriaIterator.remove();
						break;
					}
				}
			}
			httpServletRequest.setAttribute("testeInserir", false);

		} else {
			retorno = actionMapping
				.findForward("manterConsumoTarifaExistenteAction");

			HttpSession sessao = httpServletRequest.getSession(false);

			String posicaoExcluir = httpServletRequest.getParameter("posicao");

			Collection colecaoConsumoTarifaCategoria = null;

			if (sessao.getAttribute("colecaoCategoria") != null) {
				colecaoConsumoTarifaCategoria = (Collection) sessao
						.getAttribute("colecaoCategoria");
				Iterator colecaoConsumoTarifaCategoriaIterator = colecaoConsumoTarifaCategoria
						.iterator();

				while (colecaoConsumoTarifaCategoriaIterator.hasNext()) {
					CategoriaFaixaConsumoTarifaHelper categoriaFaixaConsumoTarifaHelper = (CategoriaFaixaConsumoTarifaHelper) colecaoConsumoTarifaCategoriaIterator
							.next();
					if (obterTimestampIdObjeto(categoriaFaixaConsumoTarifaHelper
							.getConsumoTarifaCategoria()) == Long
							.parseLong(posicaoExcluir)) {
						colecaoConsumoTarifaCategoriaIterator.remove();
						break;
					}
				}
			}
			httpServletRequest.setAttribute("testeInserir", false);
		}

		return retorno;
	}
}
