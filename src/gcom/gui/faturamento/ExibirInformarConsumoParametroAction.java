package gcom.gui.faturamento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.faturamento.ConsumoMinimoParametro;
import gcom.faturamento.FiltroConsumoMinimoParametro;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ConsumoMinimoArea;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Este caso de uso permite a inclusão ou alteração de um novo consumo por parâmetro
 * 
 * [UC1173] Informar Consumo por Parâmetros
 * 
 * @author Mariana Victor
 * @date 20/05/2011
 */
public class ExibirInformarConsumoParametroAction extends GcomAction {

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
				.findForward("exibirInformarConsumoParametro");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InformarConsumoParametroActionForm form = (InformarConsumoParametroActionForm) actionForm;
		
		this.carregarDadosTela(fachada, httpServletRequest, sessao, form);

		if (httpServletRequest.getParameter("consultar") != null) {

			String ano = form.getMesAnoReferencia().substring(3, 7);
			String mes = form.getMesAnoReferencia().substring(0, 2);
			String anoMesInformado = ano + mes;

			this.pesquisarConsumoMinimoParametro(form, anoMesInformado, sessao,
					fachada, httpServletRequest);
		}
		
		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			// -------------- bt DESFAZER ---------------
			form.setMesAnoReferencia("");
			form.setIdCategoria("-1");
			form.setIdSubCategoria("-1");

			sessao.setAttribute("colecaoConsumoMinimoParametro", new ArrayList());
			// ------------- bt DESFAZER ---------------

		}

		// --------------Remover ConsumoMinimoParametro-------------
		if (httpServletRequest.getParameter("remover") != null) {
			httpServletRequest.setAttribute("adicionar", true);
			httpServletRequest.setAttribute("informar", true);

			List colecaoConsumoMinimoParametro = (List) sessao
					.getAttribute("colecaoConsumoMinimoParametro");

			if (colecaoConsumoMinimoParametro != null
					&& !colecaoConsumoMinimoParametro.isEmpty()) {

				int posicao = new Integer(httpServletRequest
						.getParameter("remover")).intValue();

				int index = 0;

				Iterator colecaoConsumoMinimoParametroIterator = colecaoConsumoMinimoParametro.iterator();

				while (colecaoConsumoMinimoParametroIterator.hasNext()) {

					index++;

					ConsumoMinimoParametro consumoMinimoParametro = (ConsumoMinimoParametro) colecaoConsumoMinimoParametroIterator.next();

					if (index == posicao) {
						colecaoConsumoMinimoParametro.remove(consumoMinimoParametro);

						sessao.setAttribute("colecaoConsumoMinimoParametro", colecaoConsumoMinimoParametro);

						break;
					}
				}
			}
		}
		// --------------Remover ConsumoMinimoParametro-------------
		
		return retorno;
	}
	
	void carregarDadosTela(Fachada fachada,
			HttpServletRequest httpServletRequest, HttpSession sessao,
			InformarConsumoParametroActionForm form) {

		FiltroCategoria filtroCategoria = new FiltroCategoria();
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);

		List<Categoria> colecaoCategoria = (List) fachada.pesquisar(
				filtroCategoria, Categoria.class.getName());

		if (colecaoCategoria == null || colecaoCategoria.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Categoria");
		}

		httpServletRequest.setAttribute("colecaoCategoria", colecaoCategoria);

		List<Subcategoria> colecaoSubcategoria = new ArrayList();

		if (form.getIdCategoria() != null
				&& !form.getIdCategoria().equalsIgnoreCase("-1")) {
			FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
			filtroSubcategoria.adicionarParametro(new ParametroSimples(
					FiltroSubCategoria.CATEGORIA_ID, form.getIdCategoria()));
			filtroSubcategoria
					.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);

			colecaoSubcategoria = (List) fachada.pesquisar(filtroSubcategoria,
					Subcategoria.class.getName());

			if (colecaoSubcategoria == null || colecaoSubcategoria.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Subcategoria");
			}
		}

		httpServletRequest.setAttribute("colecaoSubcategoria", colecaoSubcategoria);

		List<ConsumoMinimoArea> colecaoConsumoMinimoArea;

		if (sessao.getAttribute("colecaoConsumoMinimoArea") != null) {

			colecaoConsumoMinimoArea = (List) sessao.getAttribute("colecaoConsumoMinimoArea");

		} else {
			colecaoConsumoMinimoArea = new ArrayList();
		}

		sessao.setAttribute("colecaoConsumoMinimoArea", colecaoConsumoMinimoArea);

		if(sessao.getAttribute("adicionar")!= null){

			httpServletRequest.setAttribute("adicionar",true);
		}

		if(sessao.getAttribute("informar")!= null){

			httpServletRequest.setAttribute("informar",true);
		}

	}


	void pesquisarConsumoMinimoParametro(InformarConsumoParametroActionForm form,
			String anoMesInformado, HttpSession sessao, Fachada fachada,
			HttpServletRequest httpServletRequest) {

		List colecaoConsumoMinimoParametro;

		sessao.removeAttribute("colecaoConsumoMinimoParametro");
		
		if (form.getIdCategoria() != null
				&& !form.getIdCategoria().equals("")
				&& !form.getIdCategoria().equals("-1")) {
			sessao.setAttribute("idCategoria", form.getIdCategoria());
		} else {
			sessao.removeAttribute("idCategoria");
		}
		
		if (form.getIdSubCategoria() != null
				&& !form.getIdSubCategoria().equals("")
				&& !form.getIdSubCategoria().equals("-1")) {
			sessao.setAttribute("subCategoria", form.getIdSubCategoria());
		} else {
			sessao.removeAttribute("subCategoria");
		}
		
		sessao.setAttribute("mesAnoReferencia", form.getMesAnoReferencia());

		Integer anoMesInformadoInteiro = new Integer(anoMesInformado);

		Integer qtde;

		/**

		 * [FS0005] - Validar ano maior ou igual

		 */
		qtde = fachada
				.pesquisarAnoMesMenorAnoMesReferenciaFaturamentoGrupo(anoMesInformadoInteiro
						.intValue());

		if (qtde == 0) {
			httpServletRequest.removeAttribute("adicionar");
			httpServletRequest.removeAttribute("informar");

		} else {
			httpServletRequest.setAttribute("adicionar", true);
			//httpServletRequest.setAttribute("informar", true);
		}

		/**

		 * [FS0005] - FIM- Validar ano maior ou igual

		 */
		FiltroConsumoMinimoParametro filtroConsumoMinimoParametro = new FiltroConsumoMinimoParametro();

		filtroConsumoMinimoParametro.adicionarParametro(new ParametroSimples(
				FiltroConsumoMinimoParametro.ANO_MES_REFERENCIA,
				anoMesInformadoInteiro));

		filtroConsumoMinimoParametro.adicionarParametro(new ParametroSimples(
				FiltroConsumoMinimoParametro.CATEGORIA, form.getIdCategoria()));

		if (form.getIdSubCategoria() != null
				&& !form.getIdSubCategoria().equals("")
				&& !form.getIdSubCategoria().equals("-1")) {
		
			filtroConsumoMinimoParametro.adicionarParametro(new ParametroSimples(
					FiltroConsumoMinimoParametro.SUBCATEGORIA, form.getIdSubCategoria()));
		} else {
			filtroConsumoMinimoParametro.adicionarParametro(new ParametroNulo(
					FiltroConsumoMinimoParametro.SUBCATEGORIA));
		}

		filtroConsumoMinimoParametro.adicionarParametro(new ParametroSimples(
				FiltroConsumoMinimoParametro.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		filtroConsumoMinimoParametro.setCampoOrderBy(FiltroConsumoMinimoParametro.NUMERO_PARAMETRO_FINAL);

		filtroConsumoMinimoParametro
				.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoMinimoParametro.CATEGORIA);

		filtroConsumoMinimoParametro
				.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoMinimoParametro.SUBCATEGORIA);

		colecaoConsumoMinimoParametro = (List) fachada.pesquisar(filtroConsumoMinimoParametro,
				ConsumoMinimoParametro.class.getName());

		if ((colecaoConsumoMinimoParametro == null)
				|| (colecaoConsumoMinimoParametro.isEmpty())) {

			colecaoConsumoMinimoParametro = new ArrayList();

		}

		sessao.setAttribute("colecaoConsumoMinimoParametro",
				colecaoConsumoMinimoParametro);


		Collection colecaoConsumoMinimoParametroBase =  fachada.pesquisar(filtroConsumoMinimoParametro,
				ConsumoMinimoParametro.class.getName());


		if ((colecaoConsumoMinimoParametroBase == null)
				|| (colecaoConsumoMinimoParametroBase.isEmpty())) {

			colecaoConsumoMinimoParametroBase = new ArrayList();
		}

		sessao.setAttribute("colecaoConsumoMinimoParametroBase",
				colecaoConsumoMinimoParametroBase);

	}
}
