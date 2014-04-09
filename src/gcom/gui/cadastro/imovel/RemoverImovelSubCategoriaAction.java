package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que remove a o objeto selecionado de cliente imovel economia que está
 * com o imovel economia
 * 
 * @author Sávio Luiz
 * @created 20 de Maio de 2004
 * 
 */
public class RemoverImovelSubCategoriaAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping
				.findForward("inserirImovelSubCategoria");

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		//DynaValidatorForm inserirImovelActionForm = (DynaValidatorForm) actionForm;

		// Cria variaveis
		Collection colecaoImovelSubCategorias = (Collection) sessao
				.getAttribute("colecaoImovelSubCategorias");

		Collection colecaoImovelSubcategoriasRemoviadas = new ArrayList();

		ImovelSubcategoria imovelSubcategoria = null;

		// Obtém os ids de remoção
		String[] removerImovelSubCategoria = httpServletRequest
				.getParameterValues("removerImovelSubCategoria");

		if (colecaoImovelSubCategorias != null
				&& !colecaoImovelSubCategorias.equals("")) {

			Iterator imovelSubcategoriaIterator = colecaoImovelSubCategorias
					.iterator();

			while (imovelSubcategoriaIterator.hasNext()) {
				imovelSubcategoria = (ImovelSubcategoria) imovelSubcategoriaIterator
						.next();
				for (int i = 0; i < removerImovelSubCategoria.length; i++) {
					if (imovelSubcategoria.getUltimaAlteracao().getTime() == Long
							.parseLong(removerImovelSubCategoria[i])) {
						if(!(colecaoImovelSubcategoriasRemoviadas.contains(imovelSubcategoria))){
							colecaoImovelSubcategoriasRemoviadas
									.add(imovelSubcategoria);
							imovelSubcategoriaIterator.remove();
						}
					}
				}
			}
			sessao.setAttribute(
					"colecaoImovelSubcategoriasRemoviadas",
					colecaoImovelSubcategoriasRemoviadas);

		}

		return retorno;
	}
}
