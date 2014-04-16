package gcom.gui.faturamento.consumotarifa;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirSubCategoriaConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("inserirSubCategoriaConsumoTarifa");
	
		Fachada fachada = Fachada.getInstancia();
	
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
	
		// ----------------FILTRO CATEGORIAS DE ESTABELECIMENTO - PROPULAR
		// DROPDOWN ------
		
		if (httpServletRequest.getParameter("parametroVigencia") != null){
			String vigencia = (String) httpServletRequest.getParameter("parametroVigencia");
			sessao.setAttribute("vigencia", vigencia);
		}
		if (httpServletRequest.getParameter("parametroDescricao") != null){
			String descricao = (String) httpServletRequest.getParameter("parametroDescricao");
			sessao.setAttribute("descricao", descricao);
		}
		if (httpServletRequest.getParameter("parametroSelect") != null){
			String select = (String) httpServletRequest.getParameter("parametroSelect");
			sessao.setAttribute("select", select);
		}
		
		FiltroCategoria filtroCategoria = new FiltroCategoria();
	
		
		if ((httpServletRequest.getParameter("limpa") != null)
				&& (httpServletRequest.getParameter("limpa").equals("1"))) {
			sessao.removeAttribute("InserirCategoriaConsumoTarifaActionForm");
			sessao.removeAttribute("valorMinimo");
			sessao.removeAttribute("colecaoFaixa");
			sessao.removeAttribute("consumoMinimo");
			sessao.setAttribute("categoriaSelected", ConstantesSistema.NUMERO_NAO_INFORMADO);
			sessao.setAttribute("subCategoriaSelected", ConstantesSistema.NUMERO_NAO_INFORMADO);
		}
		
		InserirSubCategoriaConsumoTarifaActionForm inserirSubCategoriaConsumoTarifaActionForm = new InserirSubCategoriaConsumoTarifaActionForm();
		
		if ((sessao.getAttribute("valorMinimo") != null)
				&& (! sessao.getAttribute("valorMinimo").equals(""))) {
			inserirSubCategoriaConsumoTarifaActionForm.setValorTarifaMinima((String) sessao.getAttribute("valorMinimo").toString());
		}
		
		if ((sessao.getAttribute("consumoMinimo") != null)
				&& (! sessao.getAttribute("consumoMinimo").equals(""))) {
			inserirSubCategoriaConsumoTarifaActionForm.setConsumoMinimo((String) sessao.getAttribute("consumoMinimo"));
		}
		
		filtroCategoria.adicionarParametro(new ParametroSimples(
				FiltroCategoria.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		filtroCategoria.setCampoOrderBy(FiltroCategoria.DESCRICAO);
		
		sessao.setAttribute("inserirSubCategoriaConsumoTarifaActionForm", inserirSubCategoriaConsumoTarifaActionForm);
		Collection colecaoCategoria = fachada.pesquisar(filtroCategoria,
				Categoria.class.getName());
		//preenchendo o combo de subcategoria
		if (httpServletRequest.getParameter("pesquisarSubCategoria") != null){
			String idCategoria = httpServletRequest.getParameter("categoria");
			FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
			filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID, idCategoria));
			filtroSubCategoria.adicionarParametro(new ParametroNaoNulo(FiltroSubCategoria.INDICADOR_CONSUMO_TARIFA));
			filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade("categoria");
			if (!idCategoria.equalsIgnoreCase("-1")){
				Collection colecaoSubCategoria = fachada.pesquisar(filtroSubCategoria, Subcategoria.class.getName());
				if (colecaoSubCategoria != null && !colecaoSubCategoria.isEmpty()){
					sessao.setAttribute("colecaoSubCategoria", colecaoSubCategoria);
				} else {
					sessao.removeAttribute("colecaoSubCategoria");
				}
			} else {
				sessao.removeAttribute("colecaoSubCategoria");
			}
			sessao.setAttribute("categoriaSelected", idCategoria);
		}
		
		sessao.setAttribute("colecaoCategoria", colecaoCategoria);
	
		return retorno;
	}

}
