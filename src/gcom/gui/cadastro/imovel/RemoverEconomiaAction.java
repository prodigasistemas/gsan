package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.HashSet;
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
 */
public class RemoverEconomiaAction extends GcomAction {
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
		ActionForward retorno = actionMapping.findForward("informarEconomia");

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		//Fachada fachada = Fachada.getInstancia();

		// Cria variaveis
		Collection colecaoImovelSubCategoriasCadastradas = (Collection) sessao
				.getAttribute("colecaoImovelSubCategoriasCadastradas");

		Collection colecaoImovelEconomiasModificadas = (Collection) sessao
				.getAttribute("colecaoImovelEconomiasModificadas");

		String codigoImovelEconomia = (String) httpServletRequest
				.getParameter("codigoImovelEconomia");

		Collection colecaoImovelSubCategoriaNova = new HashSet();
		// Verifica se veio algum parametro no economia_inserir.jsp
		// caso tenha vindo pega o parametro e procura na coleção um objeto
		// que tenha um hashCode igual ao do parametro
		if (httpServletRequest.getParameter("codigoImovelEconomia") != null
				&& !httpServletRequest.getParameter("codigoImovelEconomia")
						.trim().equals("")) {

			Iterator imovelSubCategoriaIterator = colecaoImovelSubCategoriasCadastradas
					.iterator();
			while (imovelSubCategoriaIterator.hasNext()) {
				ImovelSubcategoria imovelSubCategoria = null;
				imovelSubCategoria = (ImovelSubcategoria) imovelSubCategoriaIterator
						.next();

				Iterator imovelEconomiaIterator = imovelSubCategoria
						.getImovelEconomias().iterator();

				Collection colecaoImovelEconomiaiNaoRemovidas = new HashSet();

				
				while (imovelEconomiaIterator.hasNext()) {

					ImovelEconomia imovelEconomia = (ImovelEconomia) imovelEconomiaIterator
							.next();

					if (imovelEconomia.getUltimaAlteracao().getTime() == Long
							.parseLong(codigoImovelEconomia)) {
						// caso o imovel economia tenha codigo igual a nulo

						if (imovelEconomia.getId() == null
								|| imovelEconomia.getId().equals("")) {
							// remove o imovel economia só da coleção
							// pois não existe na base ainda.
							imovelEconomiaIterator.remove();

							// caso o imovel economia tenha codigo não seja
							// igual a nulo
						} else {
							// remove o imovel economia só da coleção
							// e como ja existe na base também remove da
							// base

							//fachada.removerImovelEconomia(imovelEconomia);
							
							if (sessao.getAttribute("colecaoRemovidas") != null){
								Collection colecaoRemovidas = (Collection) sessao.getAttribute("colecaoRemovidas");
								colecaoRemovidas.add(imovelEconomia);
								sessao.setAttribute("colecaoRemovidas", colecaoRemovidas);
							} else {
								Collection<ImovelEconomia> colecaoRemovidas = new HashSet();
								colecaoRemovidas.add(imovelEconomia);
								sessao.setAttribute("colecaoRemovidas", colecaoRemovidas);
							}
							
							imovelEconomiaIterator.remove();

						}

					} else {
						if (imovelSubCategoria.getComp_id().getSubcategoria()
								.getId().equals(
										imovelEconomia.getImovelSubcategoria()
												.getComp_id().getSubcategoria()
												.getId())) {
							colecaoImovelEconomiaiNaoRemovidas
									.add(imovelEconomia);
							imovelEconomiaIterator.remove();
						}
					}

				}

				imovelSubCategoria.setImovelEconomias(new HashSet(
						colecaoImovelEconomiaiNaoRemovidas));
				colecaoImovelSubCategoriaNova.add(imovelSubCategoria);
			}
		}

		// É preciso remover , caso o getTime da ultimaAlteração sejá igual,os
		// imóveis economia
		// da colecao de imovelEconomiasModificadas que foi removido.
		Iterator imoveisEconomiaModificadasIterator = colecaoImovelEconomiasModificadas
				.iterator();
		while (imoveisEconomiaModificadasIterator.hasNext()) {
			ImovelEconomia imovelEconomiaModificada = (ImovelEconomia) imoveisEconomiaModificadasIterator
					.next();

			if (imovelEconomiaModificada.getUltimaAlteracao().getTime() == Long
					.parseLong(codigoImovelEconomia)) {

				imoveisEconomiaModificadasIterator.remove();
				break;
			}
		}

		sessao.setAttribute("colecaoImovelSubCategoriasCadastradas",
				colecaoImovelSubCategoriaNova);

		return retorno;
	}
}
