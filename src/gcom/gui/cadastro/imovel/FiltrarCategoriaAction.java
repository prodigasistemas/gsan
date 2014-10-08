package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Description of the Class
 * 
 * @author Roberta Costa
 * @created 26 de Dezembro de 2005
 */
public class FiltrarCategoriaAction extends GcomAction {
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

        ActionForward retorno = actionMapping
                .findForward("retornarFiltroCategoria");

        //obtem instancia da fachada
        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);

        DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;

        //Recupera os parâmetros do form
        String id = (String) pesquisarActionForm.get("id");
        String descricao = (String) pesquisarActionForm.get("descricao");
        String tipoCategoria = (String) pesquisarActionForm.get("tipoCategoria");
        String indicadorUso = (String) pesquisarActionForm.get("indicadorUso");
        String atualizar = httpServletRequest.getParameter("atualizar");

        FiltroCategoria filtroCategoria  = new FiltroCategoria (FiltroCategoria.DESCRICAO);

        filtroCategoria.adicionarCaminhoParaCarregamentoEntidade("categoriaTipo");
        
        boolean peloMenosUmParametroInformado = false;

        //Insere os parâmetros informados no filtro
        if(id != null && !id .trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroCategoria.adicionarParametro(new ParametroSimples(
                    FiltroCategoria.CODIGO, new Integer(id)));
        }
        if(tipoCategoria != null && !tipoCategoria.trim().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
            peloMenosUmParametroInformado = true;
            
            filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.TIPO_CATEGORIA, tipoCategoria));
            //filtroCategoria.adicionarParametro(new ComparacaoTexto(FiltroCategoria.TIPO_CATEGORIA, tipoCategoria));
        }
        if(descricao != null && !descricao.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroCategoria.adicionarParametro(new ComparacaoTexto(
                    FiltroCategoria.DESCRICAO, descricao));
        }
        if(indicadorUso != null && !indicadorUso.trim().equalsIgnoreCase("")) {
            peloMenosUmParametroInformado = true;
            filtroCategoria.adicionarParametro(new ParametroSimples(
                    FiltroCategoria.INDICADOR_USO, indicadorUso));
        }

        //Erro caso o usuário mandou filtrar sem nenhum parâmetro
        if(!peloMenosUmParametroInformado) {
            throw new ActionServletException(
                    "atencao.filtro.nenhum_parametro_informado");
        }else{
			Collection<Categoria> categoriasRetorno = fachada.pesquisar(filtroCategoria, Categoria.class.getName());
			
			if(categoriasRetorno.isEmpty()){
				throw new ActionServletException(
	                    "atencao.pesquisa.nenhumresultado", null, "exceção de categoria");
	        } else {
	        	httpServletRequest.setAttribute("categoriasFiltradas", categoriasRetorno);
				if (atualizar != null && categoriasRetorno.size() == 1)
		        {
		        	Categoria categoria = (Categoria) categoriasRetorno.iterator().next();
		        	httpServletRequest.setAttribute("idRegistroAtualizacao",
							categoria.getId());
		        	
		        	retorno = actionMapping
		            	.findForward("atualizarCategoria");
		        	
		        } else {
		        	retorno = actionMapping
						.findForward("retornarFiltroCategoria");
		        }
	        }    

			// Manda o filtro pelo request para o ExibirManterCategoriaAction
	        sessao.setAttribute("filtroCategoria", filtroCategoria);
	        httpServletRequest.setAttribute("filtroCategoria", filtroCategoria);
        }

        
        return retorno;
    }
}
