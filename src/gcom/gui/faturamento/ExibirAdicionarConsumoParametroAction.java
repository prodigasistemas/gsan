package gcom.gui.faturamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.faturamento.ConsumoMinimoParametro;
import gcom.faturamento.FiltroConsumoMinimoParametro;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAdicionarConsumoParametroAction extends GcomAction {

	/**
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
				.findForward("exibirAdicionarConsumoParametro");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AdicionarConsumoParametroActionForm form = (AdicionarConsumoParametroActionForm) actionForm;

		List<ConsumoMinimoParametro> colecaoConsumoMinimoParametro = (List<ConsumoMinimoParametro>) sessao.getAttribute("colecaoConsumoMinimoParametro");

		if (httpServletRequest.getParameter("adicionar") != null) {
			
			httpServletRequest.setAttribute("adicionar", true);
			form.setNumeroParametro("");
			form.setNumeroConsumo("");
		}

		if (httpServletRequest.getParameter("mesAnoReferencia") != null) {

			form.setMesAnoReferencia("" + httpServletRequest.getParameter("mesAnoReferencia"));

			sessao.setAttribute("mesAnoReferencia", httpServletRequest.getParameter("mesAnoReferencia"));

		}

		if (httpServletRequest.getParameter("idCategoria") != null
				&& !httpServletRequest.getParameter("idCategoria").equals("")
				&& !httpServletRequest.getParameter("idCategoria").equals("-1")) {

			String idCategoria = httpServletRequest.getParameter("idCategoria");

			FiltroCategoria filtroCategoria = new FiltroCategoria();
			filtroCategoria.adicionarParametro(new ParametroSimples(
					FiltroCategoria.CODIGO, idCategoria));

			sessao.setAttribute("idCategoria", idCategoria);

			List<Categoria> colecaoCategoria = (List) fachada.pesquisar(
					filtroCategoria, Categoria.class.getName());

			if (colecaoCategoria != null && !colecaoCategoria.isEmpty()) {
				
				Categoria categoria = colecaoCategoria.iterator().next();
				form.setIdCategoria(categoria.getDescricao());
			}

		} else {
			sessao.removeAttribute("idCategoria");
		}

		if (httpServletRequest.getParameter("idSubCategoria") != null
				&& !httpServletRequest.getParameter("idSubCategoria").equals("")
				&& !httpServletRequest.getParameter("idSubCategoria").equals("-1")) {
			
			String idSubcategoria = httpServletRequest
					.getParameter("idSubCategoria");

			FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
			filtroSubCategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.CODIGO, idSubcategoria));

			sessao.setAttribute("idSubCategoria", idSubcategoria);

			List<Subcategoria> colecaoSubCategoria = (List) fachada.pesquisar(
					filtroSubCategoria, Subcategoria.class.getName());

			if (colecaoSubCategoria != null && !colecaoSubCategoria.isEmpty()) {
				
				Subcategoria subCategoria = colecaoSubCategoria.iterator().next();
				form.setIdSubCategoria(subCategoria.getDescricao());
			}

		} else {
			sessao.removeAttribute("idSubCategoria");
			form.setIdSubCategoria("");
		}

		if (httpServletRequest.getAttribute("idRegistroAtualizacao") != null) {

			String id = (String) httpServletRequest
					.getAttribute("idRegistroAtualizacao");

			FiltroConsumoMinimoParametro filtroConsumoMinimoParametro = new FiltroConsumoMinimoParametro();
			filtroConsumoMinimoParametro.adicionarParametro(new ParametroSimples(
					FiltroConsumoMinimoParametro.ID, id));
			filtroConsumoMinimoParametro
					.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoMinimoParametro.CATEGORIA);
			filtroConsumoMinimoParametro
					.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoMinimoParametro.SUBCATEGORIA);

			List<ConsumoMinimoParametro> colecaoConsumoMinimoParametroBase = (List) fachada.pesquisar(
					filtroConsumoMinimoParametro, ConsumoMinimoParametro.class.getName());

			ConsumoMinimoParametro consumoMinimoParametro = colecaoConsumoMinimoParametroBase.iterator().next();

			form.setIdCategoria(consumoMinimoParametro.getCategoria().getDescricao());
			form.setMesAnoReferencia(Util.formatarAnoMesParaMesAno(
					consumoMinimoParametro.getAnoMesReferencia()));
			form.setNumeroParametro(Util.formatarMoedaReal(consumoMinimoParametro.getNumeroParametroFinal()));
			form.setNumeroConsumo(consumoMinimoParametro.getNumeroConsumo().toString());

		}


		if (httpServletRequest.getParameter("atualizarComponente") != null) {

			int posicaoComponente = 0;

			if (httpServletRequest.getParameter("atualizaComponente") != null
					&& !httpServletRequest.getParameter("atualizaComponente")
							.equals("")) {

				posicaoComponente = new Integer(httpServletRequest
						.getParameter("atualizaComponente")).intValue();

				sessao.setAttribute("posicaoComponente", posicaoComponente);

				if (colecaoConsumoMinimoParametro != null
						&& !colecaoConsumoMinimoParametro.isEmpty()) {

					posicaoComponente = (Integer) sessao
							.getAttribute("posicaoComponente");

					int index = 0;

					Iterator<ConsumoMinimoParametro> colecaoConsumoMinimoParametroIterator = colecaoConsumoMinimoParametro.iterator();

					while (colecaoConsumoMinimoParametroIterator.hasNext()) {
						index++;

						ConsumoMinimoParametro consumoMinimoParametro = colecaoConsumoMinimoParametroIterator.next();

						if (index == posicaoComponente) {

							sessao.setAttribute("posicaoComponente", posicaoComponente);

							form.setMesAnoReferencia(Util.formatarAnoMesParaMesAno(
									consumoMinimoParametro.getAnoMesReferencia()));

							if (consumoMinimoParametro.getCategoria() != null) {

								FiltroCategoria filtroCategoria = new FiltroCategoria();
								filtroCategoria.adicionarParametro(new ParametroSimples(
												FiltroCategoria.CODIGO, consumoMinimoParametro.getCategoria().getId()));

								List<Categoria> colecaoCategoria = (List) fachada
										.pesquisar(filtroCategoria, Categoria.class.getName());

								Categoria categoria =colecaoCategoria.iterator().next();
								form.setIdCategoria(categoria.getDescricao());

							} else {
								form.setIdCategoria("");
							}

							if (consumoMinimoParametro.getSubCategoria() != null) {

								FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
								filtroSubCategoria.adicionarParametro(new ParametroSimples(
												FiltroSubCategoria.CODIGO, consumoMinimoParametro.getSubCategoria().getId()));

								List<Subcategoria> colecaoSubcategoria = (List) fachada
										.pesquisar(filtroSubCategoria, Subcategoria.class.getName());

								Subcategoria subCategoria = colecaoSubcategoria.iterator().next();

								form.setIdSubCategoria(subCategoria.getDescricao());

							}

							form.setNumeroParametro(Util.formatarMoedaReal(
									consumoMinimoParametro.getNumeroParametroFinal()));
							form.setNumeroConsumo(consumoMinimoParametro.getNumeroConsumo().toString());

						}

					}

				}


			}

		}

		httpServletRequest.setAttribute("nomeCampo","numeroParametro");

		return retorno;

	}

}
