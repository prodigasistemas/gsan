package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Karla
 * @created 30 de Dezembro de 2005
 */
public class ExibirManterSubcategoriaAction extends GcomAction {
    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("manterSubcategoria");

        //Fachada fachada = Fachada.getInstancia();

        //FiltrarSubcategoriaActionForm filtrarSubcategoriaActionForm = (FiltrarSubcategoriaActionForm) actionForm;

        Collection subcategorias = null;

        //Mudar isso quando implementar a parte de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        //Parte da verificação do filtro
        FiltroSubCategoria filtroSubcategoria = new FiltroSubCategoria();
        
        if (sessao.getAttribute("i") != null){
			sessao.removeAttribute("i");
		}

        //Verifica se o filtro foi informado pela página de filtragem de categoria
        if(httpServletRequest.getAttribute("filtroSubcategoria") != null){
            filtroSubcategoria = (FiltroSubCategoria) httpServletRequest
                    .getAttribute("filtroSubcategoria");
        }else{
            //Se o limite de registros foi atingido, a página de filtragem é chamada
            retorno = actionMapping.findForward("filtrarSubcategoria");
        }

        //A pesquisa de subcategorias só será feita se o forward estiver direcionado para a página de manterSubCategoria
        if(retorno.getName().equalsIgnoreCase("manterSubcategoria")){
            //Seta a ordenação desejada do filtro
            filtroSubcategoria.setCampoOrderBy(FiltroSubCategoria.DESCRICAO);
            
            //Informa ao filtro para ele buscar objetos associados a SubCategoria
            filtroSubcategoria.adicionarCaminhoParaCarregamentoEntidade("categoria");
            
            // Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroSubcategoria, Subcategoria.class.getName());
			subcategorias = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			if (subcategorias == null || subcategorias.isEmpty()) {
				//Nenhuma SubCategoria cadastrada
                throw new ActionServletException("atencao.subcategoria_inexistente");
            }
            
            sessao.setAttribute("subcategorias", subcategorias);
            
            sessao.setAttribute("filtroSubcategoria", filtroSubcategoria);
        }
        return retorno;
    }
}
