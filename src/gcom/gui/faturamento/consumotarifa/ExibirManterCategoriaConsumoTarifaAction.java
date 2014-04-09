package gcom.gui.faturamento.consumotarifa;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifaFaixa;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.consumotarifa.bean.CategoriaFaixaConsumoTarifaHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterCategoriaConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("manterCategoriaConsumoTarifa");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// ----------------FILTRO CATEGORIAS DE ESTABELECIMENTO - PROPULAR
		// DROPDOWN ------
		CategoriaFaixaConsumoTarifaHelper categoriaFaixaConsumoTarifaHelperAtualizacao = null;

		if ("sim".equals(httpServletRequest.getParameter("trava"))) {
			httpServletRequest.setAttribute("travar", "false");
			sessao.setAttribute("trava", "sim");
			sessao.setAttribute("novaCategoria", "nao");
		} else if((httpServletRequest.getParameter("trava") != null) && !("sim".equals(httpServletRequest.getParameter("trava"))) ){
			httpServletRequest.setAttribute("travar", "false");
			sessao.setAttribute("novaCategoria", "sim");
		}
		
		if (httpServletRequest.getParameter("posicao") != null) {
			categoriaFaixaConsumoTarifaHelperAtualizacao = pesquisarPosicaoCategoria(
					Long.parseLong(httpServletRequest.getParameter("posicao")),
					(Collection) sessao.getAttribute("colecaoCategoria"));

			sessao.setAttribute("categoriaFaixaConsumoTarifaHelperAtualizacao",
					categoriaFaixaConsumoTarifaHelperAtualizacao);
		}

		if (httpServletRequest.getAttribute("parametroVigencia") != null) {
			String Vigencia = (String) httpServletRequest
					.getAttribute("parametroVigencia");
			sessao.setAttribute("Vigencia", Vigencia);
		}
		
		if (httpServletRequest.getParameter("parametroIdPerfilLigacao") != null) {
			String idPerfilLigacao = (String) httpServletRequest.getParameter("parametroIdPerfilLigacao");
			sessao.setAttribute("idLigacaoAguaPerfil", idPerfilLigacao);
		}
		
		if (httpServletRequest.getParameter("parametroIdTarifaTipoCalculo") != null){
			String selectTarifaTipoCalculo = (String) httpServletRequest.getParameter("parametroIdTarifaTipoCalculo");
			sessao.setAttribute("selectTarifaTipoCalculo", selectTarifaTipoCalculo);
		}
		

		FiltroCategoria filtroCategoria = new FiltroCategoria();

		//FiltroConsumoTarifaCategoria filtroConsumoTarifaCategoria = new FiltroConsumoTarifaCategoria();

		InserirCategoriaConsumoTarifaActionForm inserirCategoriaConsumoTarifaActionForm = new InserirCategoriaConsumoTarifaActionForm();
		
		if ((httpServletRequest.getParameter("limpa") != null)
				&& (httpServletRequest.getParameter("limpa").equals("1"))) {
			sessao.removeAttribute("InserirCategoriaConsumoTarifaActionForm");
			sessao.removeAttribute("valorMinimo");
			sessao.removeAttribute("consumoMinimo");
			sessao.removeAttribute("colecaoFaixa");
			sessao.setAttribute("novaCategoria", "sim");
			inserirCategoriaConsumoTarifaActionForm.setSlcCategoria(null);
		}

		

		if ((sessao.getAttribute("valorMinimo") != null)
				&& (!sessao.getAttribute("valorMinimo").equals(""))) {
			inserirCategoriaConsumoTarifaActionForm
					.setValorTarifaMinima((String) sessao
							.getAttribute("valorMinimo"));
		}

		if ((sessao.getAttribute("consumoMinimo") != null)
				&& (!sessao.getAttribute("consumoMinimo").equals(""))) {
			inserirCategoriaConsumoTarifaActionForm
					.setConsumoMinimo((String) sessao
							.getAttribute("consumoMinimo"));
		}

		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);

		if (categoriaFaixaConsumoTarifaHelperAtualizacao != null) {
			inserirCategoriaConsumoTarifaActionForm
					.setSlcCategoria(""
							+ categoriaFaixaConsumoTarifaHelperAtualizacao
									.getConsumoTarifaCategoria().getCategoria()
									.getId());
			inserirCategoriaConsumoTarifaActionForm.setConsumoMinimo(""
					+ categoriaFaixaConsumoTarifaHelperAtualizacao
							.getConsumoTarifaCategoria()
							.getNumeroConsumoMinimo());
			inserirCategoriaConsumoTarifaActionForm
					.setValorTarifaMinima(Util
							.formatarMoedaReal(categoriaFaixaConsumoTarifaHelperAtualizacao
									.getConsumoTarifaCategoria()
									.getValorTarifaMinima()));

			if (categoriaFaixaConsumoTarifaHelperAtualizacao.getColecaoFaixas() == null
					|| categoriaFaixaConsumoTarifaHelperAtualizacao
							.getColecaoFaixas().isEmpty()) {
				FiltroConsumoTarifaFaixa filtroConsumoTarifaFaixa = new FiltroConsumoTarifaFaixa();
				filtroConsumoTarifaFaixa
						.adicionarParametro(new ParametroSimples(
								FiltroConsumoTarifaFaixa.CONSUMO_TARIFA_CATEGORIA_ID,
								categoriaFaixaConsumoTarifaHelperAtualizacao
										.getConsumoTarifaCategoria().getId()));
				Collection colecaoFaixa = fachada.pesquisar(
						filtroConsumoTarifaFaixa, ConsumoTarifaFaixa.class
								.getName());

				List listColecaoFaixa = new ArrayList();
				listColecaoFaixa.addAll(colecaoFaixa);

				Collections.sort((List) listColecaoFaixa, new Comparator() {
					public int compare(Object a, Object b) {
						Integer codigo1 = ((ConsumoTarifaFaixa) a)
								.getNumeroConsumoFaixaFim();
						Integer codigo2 = ((ConsumoTarifaFaixa) b)
								.getNumeroConsumoFaixaFim();

						return codigo1.compareTo(codigo2);
					}
				});

				sessao.setAttribute("colecaoFaixa", listColecaoFaixa);

			} else {

				Collection colecaoFaixa = categoriaFaixaConsumoTarifaHelperAtualizacao
						.getColecaoFaixas();

				List listColecaoFaixa = new ArrayList();
				listColecaoFaixa.addAll(colecaoFaixa);

				Collections.sort(listColecaoFaixa, new Comparator() {
					public int compare(Object a, Object b) {
						Integer codigo1 = ((ConsumoTarifaFaixa) a)
								.getNumeroConsumoFaixaFim();
						Integer codigo2 = ((ConsumoTarifaFaixa) b)
								.getNumeroConsumoFaixaFim();

						return codigo1.compareTo(codigo2);
					}
				});

				sessao.setAttribute("colecaoFaixa", listColecaoFaixa);

			}
		}
		sessao.setAttribute("InserirCategoriaConsumoTarifaActionForm",
				inserirCategoriaConsumoTarifaActionForm);
		Collection colecaoCategoriaLista = fachada.pesquisar(filtroCategoria,
				Categoria.class.getName());

		sessao.setAttribute("colecaoCategoriaLista", colecaoCategoriaLista);
		
		return retorno;
	}

	private CategoriaFaixaConsumoTarifaHelper pesquisarPosicaoCategoria(
			long posicao, Collection colecaoCategoriaSessao) {

		CategoriaFaixaConsumoTarifaHelper retorno = null;

		if (colecaoCategoriaSessao != null) {

			Iterator colecaoConsumoTarifaCategoriaIterator = colecaoCategoriaSessao
					.iterator();

			while (colecaoConsumoTarifaCategoriaIterator.hasNext()) {
				CategoriaFaixaConsumoTarifaHelper categoriaFaixaConsumoTarifaHelper = (CategoriaFaixaConsumoTarifaHelper) colecaoConsumoTarifaCategoriaIterator
						.next();
				if (obterTimestampIdObjeto(categoriaFaixaConsumoTarifaHelper
						.getConsumoTarifaCategoria()) == posicao) {
					retorno = categoriaFaixaConsumoTarifaHelper;
					break;
				}
			}
		}
		
		
		
		return retorno;
	}
}
