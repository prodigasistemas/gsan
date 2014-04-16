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
public class ExcluirManterCategoriaConsumoTarifaExistenteAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("inserirConsumoTarifa");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio

		String posicaoExcluir = httpServletRequest.getParameter("posicao");

		Collection colecaoConsumoTarifaCategoria = null;

		if (sessao != null) {
			colecaoConsumoTarifaCategoria = (Collection) sessao
					.getAttribute("colecaoConsumoTarifaCategoria");
			Iterator colecaoConsumoTarifaCategoriaIterator = colecaoConsumoTarifaCategoria.iterator();

			while (colecaoConsumoTarifaCategoriaIterator.hasNext()) {
				CategoriaFaixaConsumoTarifaHelper categoriaFaixaConsumoTarifaHelper = (CategoriaFaixaConsumoTarifaHelper) colecaoConsumoTarifaCategoriaIterator
						.next();
				if (categoriaFaixaConsumoTarifaHelper.getConsumoTarifaCategoria().getUltimaAlteracao().getTime() == Long
						.parseLong(posicaoExcluir)) {
					colecaoConsumoTarifaCategoriaIterator.remove();
				}
			}
		}
		httpServletRequest.setAttribute("testeInserir", false);
		return retorno;
	}
}
