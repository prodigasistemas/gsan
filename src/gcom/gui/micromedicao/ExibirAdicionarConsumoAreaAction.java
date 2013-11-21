/**
 * 
 */
package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.ConsumoMinimoArea;
import gcom.micromedicao.FiltroConsumoMinimoArea;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 14/05/2008
 */
public class ExibirAdicionarConsumoAreaAction extends GcomAction {
	/**
	 * Este caso de uso permite a inclusão ou alteração de um novo consumo pela
	 * área
	 * 
	 * [UC0781] Informar Consumo por Área
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 12/05/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirAdicionarConsumoArea");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AdicionarConsumoAreaActionForm form = (AdicionarConsumoAreaActionForm) actionForm;

		Collection<ConsumoMinimoArea> colecaoConsumoMinimoArea = (Collection<ConsumoMinimoArea>) sessao.getAttribute("colecaoConsumoMinimoArea");

		if (httpServletRequest.getParameter("adicionar") != null) {
			httpServletRequest.setAttribute("adicionar", true);
			form.setNumeroArea("");
			form.setNumeroConsumo("");

		}
		if (httpServletRequest.getParameter("mesAnoReferencia") != null) {

			form.setMesAnoReferencia(""
					+ httpServletRequest.getParameter("mesAnoReferencia"));
			sessao.setAttribute("mesAnoReferencia", httpServletRequest.getParameter("mesAnoReferencia"));

		}

		if (httpServletRequest.getParameter("categoria") != null) {

			String idCategoria = httpServletRequest.getParameter("categoria");

			FiltroCategoria filtroCategoria = new FiltroCategoria();

			filtroCategoria.adicionarParametro(new ParametroSimples(
					FiltroCategoria.CODIGO, idCategoria));

			sessao.setAttribute("idCategoria", idCategoria);

			Collection<Categoria> colecaoCategoria = fachada.pesquisar(
					filtroCategoria, Categoria.class.getName());

			if (colecaoCategoria != null && !colecaoCategoria.isEmpty()) {
				Categoria categoria = colecaoCategoria.iterator().next();

				form.setCategoria(categoria.getDescricao());

			}
		}

		if (httpServletRequest.getParameter("subCategoria") != null) {

			String idSubcategoria = httpServletRequest
					.getParameter("subCategoria");

			FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();

			filtroSubCategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.CODIGO, idSubcategoria));

			sessao.setAttribute("idSubCategoria", idSubcategoria);

			Collection<Subcategoria> colecaoSubCategoria = fachada.pesquisar(
					filtroSubCategoria, Subcategoria.class.getName());

			if (colecaoSubCategoria != null && !colecaoSubCategoria.isEmpty()) {
				Subcategoria subCategoria = colecaoSubCategoria.iterator().next();

				form.setSubCategoria(subCategoria.getDescricao());

			}
		}

		if (httpServletRequest.getAttribute("idRegistroAtualizacao") != null) {

			String id = (String) httpServletRequest
					.getAttribute("idRegistroAtualizacao");

			FiltroConsumoMinimoArea filtroConsumoMinimoArea = new FiltroConsumoMinimoArea();

			filtroConsumoMinimoArea.adicionarParametro(new ParametroSimples(
					FiltroConsumoMinimoArea.ID, id));

			filtroConsumoMinimoArea
					.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoMinimoArea.CATEGORIA);
			filtroConsumoMinimoArea
					.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoMinimoArea.SUBCATEGORIA);

			Collection<ConsumoMinimoArea> colecaoConsumoMinimoAreaBase = fachada.pesquisar(
					filtroConsumoMinimoArea, ConsumoMinimoArea.class.getName());

			ConsumoMinimoArea consumoMinimoArea = colecaoConsumoMinimoAreaBase.iterator().next();

			form.setCategoria(consumoMinimoArea.getCategoria().getDescricao());

			form.setMesAnoReferencia(Util
					.formatarAnoMesParaMesAno(consumoMinimoArea
							.getAnoMesReferencia()));

			form.setNumeroArea(Util.formatarMoedaReal(consumoMinimoArea
					.getNumeroAreaFinal()));

			form.setNumeroConsumo(consumoMinimoArea.getNumeroConsumo()
					.toString());

		}

		if (httpServletRequest.getParameter("atualizarComponente") != null) {

			int posicaoComponente = 0;

			if (httpServletRequest.getParameter("atualizaComponente") != null
					&& !httpServletRequest.getParameter("atualizaComponente")
							.equals("")) {
				posicaoComponente = new Integer(httpServletRequest
						.getParameter("atualizaComponente")).intValue();
				sessao.setAttribute("posicaoComponente", posicaoComponente);

				if (colecaoConsumoMinimoArea != null
						&& !colecaoConsumoMinimoArea.isEmpty()) {

					posicaoComponente = (Integer) sessao
							.getAttribute("posicaoComponente");

					int index = 0;

					Iterator<ConsumoMinimoArea> colecaoConsumoMinimoAreaIterator = colecaoConsumoMinimoArea.iterator();

					while (colecaoConsumoMinimoAreaIterator.hasNext()) {

						index++;

						ConsumoMinimoArea consumoMinimoArea = colecaoConsumoMinimoAreaIterator.next();

						if (index == posicaoComponente) {

							sessao.setAttribute("posicaoComponente",
									posicaoComponente);

							form.setMesAnoReferencia(Util
									.formatarAnoMesParaMesAno(consumoMinimoArea
											.getAnoMesReferencia()));

							if (consumoMinimoArea.getCategoria() != null) {

								FiltroCategoria filtroCategoria = new FiltroCategoria();
								filtroCategoria
										.adicionarParametro(new ParametroSimples(
												FiltroCategoria.CODIGO,
												consumoMinimoArea
														.getCategoria().getId()));
								Collection<Categoria> colecaoCategoria = fachada
										.pesquisar(filtroCategoria,
												Categoria.class.getName());

								Categoria categoria =colecaoCategoria.iterator().next();

								form.setCategoria(categoria.getDescricao());

							} else {
								form.setCategoria("");
							}

							if (consumoMinimoArea.getSubCategoria() != null) {

								FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();

								filtroSubCategoria
										.adicionarParametro(new ParametroSimples(
												FiltroSubCategoria.CODIGO,
												consumoMinimoArea
														.getSubCategoria()
														.getId()));

								Collection<Subcategoria> colecaoSubcategoria = fachada
										.pesquisar(filtroSubCategoria,
												Subcategoria.class.getName());

								Subcategoria subCategoria = colecaoSubcategoria.iterator().next();

								form.setSubCategoria(subCategoria
										.getDescricao());
							}

							form.setNumeroArea(Util
									.formatarMoedaReal(consumoMinimoArea
											.getNumeroAreaFinal()));

							form.setNumeroConsumo(consumoMinimoArea
									.getNumeroConsumo().toString());

						}
					}
				}

			}
		}
		httpServletRequest.setAttribute("nomeCampo","numeroArea");

		return retorno;

	}
}
