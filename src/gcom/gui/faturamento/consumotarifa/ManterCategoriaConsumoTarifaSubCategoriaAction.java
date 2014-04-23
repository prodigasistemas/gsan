package gcom.gui.faturamento.consumotarifa;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifaCategoria;
import gcom.faturamento.consumotarifa.ConsumoTarifaFaixa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.faturamento.consumotarifa.bean.CategoriaFaixaConsumoTarifaHelper;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
public class ManterCategoriaConsumoTarifaSubCategoriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterCategoriaConsumoTarifaSubCategoria");

		Fachada fachada = Fachada.getInstancia();

		InserirCategoriaConsumoTarifaActionForm inserirCategoriaConsumoTarifaActionForm = (InserirCategoriaConsumoTarifaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoFaixa = (Collection) sessao
				.getAttribute("colecaoFaixa");

		if (colecaoFaixa != null) {

			if (colecaoFaixa == null || colecaoFaixa.isEmpty()) {
				throw new ActionServletException(
						"atencao.faixa_categoria_consumo_tarifa");
			}

		} else {
			throw new ActionServletException(
					"atencao.faixa_categoria_consumo_tarifa");
		}

		Iterator iteratorColecaoFaixa = colecaoFaixa.iterator();

		while (iteratorColecaoFaixa.hasNext()) {
			ConsumoTarifaFaixa consumoTarifaFaixa = (ConsumoTarifaFaixa) iteratorColecaoFaixa
					.next();
			String parametroConsumoTarifaFaixa = "valorConsumoTarifa"
					+ consumoTarifaFaixa.getUltimaAlteracao().getTime();
			consumoTarifaFaixa.setValorConsumoTarifa(Util
					.formatarMoedaRealparaBigDecimal(httpServletRequest
							.getParameter(parametroConsumoTarifaFaixa)));

		}

		// ######## Colocando os dados dos Forms na SessaoCategoria

		String idCategoria = inserirCategoriaConsumoTarifaActionForm
				.getSlcCategoria();
		
		String idSubCategoria = inserirCategoriaConsumoTarifaActionForm
		.getSlcSubCategoria();

		FiltroCategoria filtroCategoria = new FiltroCategoria();

		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.CODIGO, idCategoria));

		Collection colecaoCategoriaPesquisadas = fachada.pesquisar(
				filtroCategoria, Categoria.class.getName());

		Categoria categoriaSelected = (Categoria) Util
				.retonarObjetoDeColecao(colecaoCategoriaPesquisadas);
		
		FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();

		filtroSubCategoria.adicionarParametro(new ParametroSimples(
				FiltroSubCategoria.ID, idSubCategoria));

		Collection colecaoSubCategoriaPesquisadas = fachada.pesquisar(
				filtroSubCategoria, Subcategoria.class.getName());

		Subcategoria subcategoriaSelected = (Subcategoria) Util
				.retonarObjetoDeColecao(colecaoSubCategoriaPesquisadas);
		

		ConsumoTarifaCategoria consumoTarifaCategoria = new ConsumoTarifaCategoria();

		// Categoria
		consumoTarifaCategoria.setCategoria(categoriaSelected);
		
		//SubCategoria
		consumoTarifaCategoria.setSubCategoria(subcategoriaSelected);

		// Consumo mínimo
		consumoTarifaCategoria.setNumeroConsumoMinimo(new Integer(
				inserirCategoriaConsumoTarifaActionForm.getConsumoMinimo()));

		// Tarifa mínima
		consumoTarifaCategoria
				.setValorTarifaMinima(Util
						.formatarMoedaRealparaBigDecimal(inserirCategoriaConsumoTarifaActionForm
								.getValorTarifaMinima()));

		// Ultima alteração
		consumoTarifaCategoria.setUltimaAlteracao(new Date());

		Collection colecaoCategoria = new ArrayList();
		int numeroFaixasCategoria = 0;

		if (colecaoFaixa != null) {
			numeroFaixasCategoria = colecaoFaixa.size();
		}

		// Atribuindo a colecao faixa valores da categoria
		colecaoFaixa = (Collection) sessao.getAttribute("colecaoFaixa");

		Iterator colecaoFaixaIterator = colecaoFaixa.iterator();

		if ((colecaoFaixa != null) && (!colecaoFaixa.isEmpty())) {
			Iterator colecaoFaixaIt = colecaoFaixa.iterator();
			boolean i = false;
			while (colecaoFaixaIt.hasNext()) {
				ConsumoTarifaFaixa consumoTarifaFaixa = (ConsumoTarifaFaixa) colecaoFaixaIt
						.next();
				if(new Integer(inserirCategoriaConsumoTarifaActionForm.getConsumoMinimo()).intValue() > consumoTarifaFaixa.getNumeroConsumoFaixaFim().intValue() ){
					throw new ActionServletException(
					"atencao.consumo_minimo.maior.faixa_limite_superior_menor_existe");
				}
				
				
				if (consumoTarifaFaixa.getNumeroConsumoFaixaFim().toString()
						.equals("999999")) {
					i = true;
				}

			}
			if (!i) {
				throw new ActionServletException(
						"atencao.faixa_limite_superior");
			}
		}

		while (colecaoFaixaIterator.hasNext()) {
			ConsumoTarifaFaixa consumoTarifaFaixa = (ConsumoTarifaFaixa) colecaoFaixaIterator
					.next();
			consumoTarifaFaixa
					.setConsumoTarifaCategoria(consumoTarifaCategoria);
		}

		if (sessao.getAttribute("colecaoCategoria") != null) {

			colecaoCategoria = (Collection) sessao
					.getAttribute("colecaoCategoria");

			CategoriaFaixaConsumoTarifaHelper consumoTarifaHelper = new CategoriaFaixaConsumoTarifaHelper(
					numeroFaixasCategoria, consumoTarifaCategoria,
					(Collection) sessao.getAttribute("colecaoFaixa"));
			
			String trava = (String) sessao.getAttribute("trava");
			if (this.existeNaColecao(consumoTarifaHelper,httpServletRequest)) {
				
				String novaCategoria = (String) sessao
						.getAttribute("novaCategoria");
				
				if (novaCategoria != null && novaCategoria.equals("sim")) {
					throw new ActionServletException(
							"atencao.consumotaria.categoria_existente");
				}
				
				if (novaCategoria != null && novaCategoria.equals("sim") && !"sim".equals(trava)) {
					throw new ActionServletException(
							"atencao.consumotaria.categoria_existente");
				} else {
					Iterator iteratorColecaoCategoria = colecaoCategoria
							.iterator();
					while (iteratorColecaoCategoria.hasNext()) {
						CategoriaFaixaConsumoTarifaHelper helper = (CategoriaFaixaConsumoTarifaHelper) iteratorColecaoCategoria
								.next();
						if (helper.getConsumoTarifaCategoria().getSubCategoria()
								.getId().equals(
										consumoTarifaHelper
												.getConsumoTarifaCategoria()
												.getSubCategoria().getId())) {
							iteratorColecaoCategoria.remove();
						}

					}

					colecaoCategoria.add(consumoTarifaHelper);

				}
			} else {

				colecaoCategoria.add(consumoTarifaHelper);
			}
		} else {
			colecaoCategoria.add(new CategoriaFaixaConsumoTarifaHelper(
					numeroFaixasCategoria, consumoTarifaCategoria,
					(Collection) sessao.getAttribute("colecaoFaixa")));

			sessao.setAttribute("colecaoCategoria", colecaoCategoria);
		}
		
		// fim.
		sessao.removeAttribute("categoriaNova");
		sessao.removeAttribute("trava");
		if (httpServletRequest.getParameter("testeInserir").equalsIgnoreCase(
				"true")) {
			httpServletRequest.setAttribute("testeInserir", "true");
		}

		return retorno;
	}
	
	
	private boolean existeNaColecao(CategoriaFaixaConsumoTarifaHelper categoriaFaixaConsumoTarifaHelperForm,
		HttpServletRequest httpServletRequest){
		
		boolean retorno = false;
		
		Collection colecaoCategoria = (Collection) this.getSessao(httpServletRequest).getAttribute("colecaoCategoria");
		
		Iterator itera = colecaoCategoria.iterator();
		if (colecaoCategoria!= null){
			while(itera.hasNext()){
				CategoriaFaixaConsumoTarifaHelper categoriaHelper = (CategoriaFaixaConsumoTarifaHelper) itera.next();
				
				if(categoriaHelper.getConsumoTarifaCategoria().getCategoria().getId().intValue() == 
					categoriaFaixaConsumoTarifaHelperForm.getConsumoTarifaCategoria().getCategoria().getId().intValue() &&
					categoriaHelper.getConsumoTarifaCategoria().getSubCategoria().getId().intValue() == 
						categoriaFaixaConsumoTarifaHelperForm.getConsumoTarifaCategoria().getSubCategoria().getId().intValue()){
					
					retorno = true;
					
				}
			}
		}
		return retorno;
	}
}
