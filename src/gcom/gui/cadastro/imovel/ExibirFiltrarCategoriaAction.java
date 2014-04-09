package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroCategoriaTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Description of the Class
 * 
 * @author Roberta Costa
 * @created 28 de Dezembro de 2005
 */
public class ExibirFiltrarCategoriaAction extends GcomAction {
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
        ActionForward retorno = actionMapping.findForward("filtrarCategoria");

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        Fachada fachada = Fachada.getInstancia();

        //caso seja o código da categoria
        String idDigitadoEnterCategoria = (String) pesquisarActionForm.get("id");

        //Verifica se o código foi digitado
        if(idDigitadoEnterCategoria != null && !idDigitadoEnterCategoria.trim().equals("")
                && Integer.parseInt(idDigitadoEnterCategoria) > 0) {
            FiltroCategoria filtroCategoria = new FiltroCategoria();

            filtroCategoria.adicionarParametro(new ParametroSimples(
                    FiltroCategoria.CODIGO, idDigitadoEnterCategoria));
            filtroCategoria.adicionarParametro(new ParametroSimples(
                    FiltroCategoria.INDICADOR_USO,
                    ConstantesSistema.INDICADOR_USO_ATIVO));

            Collection idEncontrado = fachada.pesquisar(filtroCategoria,
            		Categoria.class.getName());

            if(idEncontrado != null && !idEncontrado.isEmpty()) {
                //O id foi encontrado
                pesquisarActionForm.set("id",(((Categoria) ((List) idEncontrado).get(0))
                                .getId().toString()));
                pesquisarActionForm.set("descricao",(((Categoria) ((List) idEncontrado).get(0))
                                .getDescricao()));
                pesquisarActionForm.set("descricaoAbreviada",(((Categoria) ((List) idEncontrado).get(0))
                        .getDescricaoAbreviada()));
                pesquisarActionForm.set("consumoMinimo",("" +((Categoria) ((List) idEncontrado).get(0)).getConsumoMinimo()));
            }else{
                pesquisarActionForm.set("id", "");
                httpServletRequest.setAttribute("idNaoEncontrado","true");
                pesquisarActionForm.set("id", "Código inexistente");
            }
        }

        // Pesquisa os Tipos de Categoria
        FiltroCategoriaTipo filtroCategoriaTipo = new FiltroCategoriaTipo();
        filtroCategoriaTipo.setCampoOrderBy(FiltroClienteTipo.DESCRICAO);

        httpServletRequest.setAttribute("colecaoTipoCategoria", Fachada
                .getInstancia().pesquisar(filtroCategoriaTipo,
                        CategoriaTipo.class.getName()));

        return retorno;
    }
}
