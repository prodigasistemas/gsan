package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir subcategoria
 * 
 * [UC0058] Inserir Subcategoria
 * 
 * @author Fernanda Paiva
 * @date 28/12/2005
 */
public class ExibirInserirSubcategoriaAction extends GcomAction {
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
        ActionForward retorno = actionMapping.findForward("exibirInserirSubcategoria");
        
        InserirSubcategoriaActionForm  inserirSubcategoriaActionForm = (InserirSubcategoriaActionForm) actionForm;
        
        FiltroCategoria filtroCategoria = new FiltroCategoria();
        filtroCategoria.adicionarParametro(
        	new ParametroSimples(
       			FiltroCategoria.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));
        
		Collection<Categoria> collectionImovelCategoria = 
			this.getFachada().pesquisar(filtroCategoria, Categoria.class.getName() );
		

		
		inserirSubcategoriaActionForm.setIndicadorSazonalidade(""+ConstantesSistema.INDICADOR_USO_DESATIVO);

		httpServletRequest.setAttribute("collectionImovelCategoria", collectionImovelCategoria); 
		
        return retorno;
    }
}
