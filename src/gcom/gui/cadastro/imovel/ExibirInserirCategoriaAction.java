package gcom.gui.cadastro.imovel;

//import gcom.cadastro.imovel.FiltroCategoria;
//import gcom.fachada.Fachada;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.FiltroCategoriaTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0092] - Inserir Categoria
 * Action responsável pela pre-exibição da pagina de inserir Categoria
 * @author 	Roberta Costa
 * @created 22 de Dezembro de 2005
 */
public class ExibirInserirCategoriaAction extends GcomAction {
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

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("inserirCategoria");

        // Pesquisa os Tipos de Categoria
        FiltroCategoriaTipo filtroCategoriaTipo = new FiltroCategoriaTipo();
        filtroCategoriaTipo.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);

        httpServletRequest.setAttribute("colecaoTipoCategoria", Fachada
                .getInstancia().pesquisar(filtroCategoriaTipo,
                        CategoriaTipo.class.getName()));

        return retorno;
    }
}
