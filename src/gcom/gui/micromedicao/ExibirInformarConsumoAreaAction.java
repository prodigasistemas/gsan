package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ConsumoMinimoArea;
import gcom.micromedicao.FiltroConsumoMinimoArea;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

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
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 12/05/2008
 */
public class ExibirInformarConsumoAreaAction extends GcomAction {

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
				.findForward("exibirInformarConsumoArea");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		InformarConsumoAreaActionForm form = (InformarConsumoAreaActionForm) actionForm;

		this.carregarDadosTela(fachada, httpServletRequest, sessao, form);

		if (httpServletRequest.getParameter("consultar") != null) {

			String ano = form.getMesAnoReferencia().substring(3, 7);

			String mes = form.getMesAnoReferencia().substring(0, 2);

			String anoMesInformado = ano + mes;

			this.pesquisarConsumoMinimoArea(form, anoMesInformado, sessao,
					fachada, httpServletRequest);

		}

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			// -------------- bt DESFAZER ---------------

			form.setMesAnoReferencia("");
			form.setCategoria("-1");
			form.setSubCategoria("-1");
			sessao.setAttribute("colecaoConsumoMinimoArea", new ArrayList());

			// ------------- bt DESFAZER ---------------

		}

		// --------------Remover ConsumoMinimoArea-------------

		if (httpServletRequest.getParameter("remover") != null) {
			httpServletRequest.setAttribute("adicionar", true);
			httpServletRequest.setAttribute("informar", true);

			Collection colecaoConsumoMinimoArea = (Collection) sessao
					.getAttribute("colecaoConsumoMinimoArea");

			if (colecaoConsumoMinimoArea != null
					&& !colecaoConsumoMinimoArea.isEmpty()) {

				int posicao = new Integer(httpServletRequest
						.getParameter("remover")).intValue();

				int index = 0;

				Iterator colecaoConsumoMinimoAreaIterator = colecaoConsumoMinimoArea
						.iterator();

				while (colecaoConsumoMinimoAreaIterator.hasNext()) {

					index++;

					ConsumoMinimoArea consumoMinimoArea = (ConsumoMinimoArea) colecaoConsumoMinimoAreaIterator
							.next();

					if (index == posicao) {
						colecaoConsumoMinimoArea.remove(consumoMinimoArea);

						sessao.setAttribute("colecaoConsumoMinimoArea",
								colecaoConsumoMinimoArea);

						break;
					}
				}
			}
		}

		// --------------Remover ConsumoMinimoArea-------------

		return retorno;

	}

	void pesquisarConsumoMinimoArea(InformarConsumoAreaActionForm form,
			String anoMesInformado, HttpSession sessao, Fachada fachada,
			HttpServletRequest httpServletRequest) {

		Collection colecaoConsumoMinimoArea;
		
		sessao.removeAttribute("colecaoConsumoMinimoArea");

		sessao.setAttribute("categoria", form.getCategoria());
		sessao.setAttribute("subCategoria", form.getSubCategoria());
		sessao.setAttribute("mesAnoReferencia", form.getMesAnoReferencia());

		Integer anoMesInformadoInteiro = new Integer(anoMesInformado);

		Integer qtde;
		/**
		 * [FS0005] - Validar ano maior ou igual
		 */
		qtde = fachada
				.pesquisarAnoMesReferenciaMenorAnoMesReferenciaFaturamentoGrupo(anoMesInformadoInteiro
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

		FiltroConsumoMinimoArea filtroConsumoMinimoArea = new FiltroConsumoMinimoArea();

		filtroConsumoMinimoArea.adicionarParametro(new ParametroSimples(
				FiltroConsumoMinimoArea.ANO_MES_REFERENCIA,
				anoMesInformadoInteiro));

		filtroConsumoMinimoArea.adicionarParametro(new ParametroSimples(
				FiltroConsumoMinimoArea.CATEGORIA, form.getCategoria()));

		filtroConsumoMinimoArea.adicionarParametro(new ParametroSimples(
				FiltroConsumoMinimoArea.SUBCATEGORIA, form.getSubCategoria()));

		filtroConsumoMinimoArea.adicionarParametro(new ParametroSimples(
				FiltroConsumoMinimoArea.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroConsumoMinimoArea.setCampoOrderBy(FiltroConsumoMinimoArea.NUMERO_AREA_FINAL);

		filtroConsumoMinimoArea
				.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoMinimoArea.CATEGORIA);
		filtroConsumoMinimoArea
				.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoMinimoArea.SUBCATEGORIA);

		colecaoConsumoMinimoArea = fachada.pesquisar(filtroConsumoMinimoArea,
				ConsumoMinimoArea.class.getName());

		if ((colecaoConsumoMinimoArea == null)
				|| (colecaoConsumoMinimoArea.isEmpty())) {

			colecaoConsumoMinimoArea = new ArrayList();

		}

		sessao.setAttribute("colecaoConsumoMinimoArea",
				colecaoConsumoMinimoArea);
		
		Collection colecaoConsumoMinimoAreaBase =  fachada.pesquisar(filtroConsumoMinimoArea,
				ConsumoMinimoArea.class.getName());

		if ((colecaoConsumoMinimoArea == null)
				|| (colecaoConsumoMinimoArea.isEmpty())) {

			colecaoConsumoMinimoArea = new ArrayList();

		}
		sessao.setAttribute("colecaoConsumoMinimoAreaBase",
				colecaoConsumoMinimoAreaBase);
	}

	void carregarDadosTela(Fachada fachada,
			HttpServletRequest httpServletRequest, HttpSession sessao,
			InformarConsumoAreaActionForm form) {

		FiltroCategoria filtroCategoria = new FiltroCategoria();

		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<Categoria> colecaoCategoria = fachada.pesquisar(
				filtroCategoria, Categoria.class.getName());

		if (colecaoCategoria == null || colecaoCategoria.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Categoria");
		}

		httpServletRequest.setAttribute("colecaoCategoria", colecaoCategoria);

		Collection<Subcategoria> colecaoSubcategoria = new ArrayList();

		if (form.getCategoria() != null) {
			if (!form.getCategoria().equalsIgnoreCase("-1")) {
				FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();

				filtroSubcategoria.adicionarParametro(new ParametroSimples(
						FiltroSubCategoria.CATEGORIA_ID, form.getCategoria()));

				filtroSubcategoria
						.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);

				// Verifica se os dados foram informados da tabela existem e
				// joga
				// numa
				// colecao

				colecaoSubcategoria = fachada.pesquisar(filtroSubcategoria,
						Subcategoria.class.getName());

				if (colecaoSubcategoria == null
						|| colecaoSubcategoria.isEmpty()) {
					throw new ActionServletException(
							"atencao.entidade_sem_dados_para_selecao", null,
							"Subcategoria");
				}
			}
		} 

		httpServletRequest.setAttribute("colecaoSubcategoria",
				colecaoSubcategoria);

		Collection<ConsumoMinimoArea> colecaoConsumoMinimoArea;

		if (sessao.getAttribute("colecaoConsumoMinimoArea") != null) {
			
			colecaoConsumoMinimoArea = (Collection) sessao
					.getAttribute("colecaoConsumoMinimoArea");

			
		} else {

			colecaoConsumoMinimoArea = new ArrayList();
		}
		sessao.setAttribute("colecaoConsumoMinimoArea",
				colecaoConsumoMinimoArea);

		if(sessao.getAttribute("adicionar")!= null){
			httpServletRequest.setAttribute("adicionar",true);
		}
		if(sessao.getAttribute("informar")!= null){
			httpServletRequest.setAttribute("informar",true);
		}
		
	}
}
