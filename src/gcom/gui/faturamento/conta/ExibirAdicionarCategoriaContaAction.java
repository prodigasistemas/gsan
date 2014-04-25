package gcom.gui.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExibirAdicionarCategoriaContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirAdicionarCategoriaConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);

        Fachada fachada = Fachada.getInstancia();
        
        
        //Carregar categorias
        if (sessao.getAttribute("colecaoAdicionarCategoria") == null){
        
        	FiltroCategoria filtroCategoria = new FiltroCategoria(FiltroCategoria.DESCRICAO);
        	
        	filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.INDICADOR_USO,
        			ConstantesSistema.INDICADOR_USO_ATIVO));
        
        	Collection colecaoAdicionarCategoria = fachada.pesquisar(filtroCategoria,
        		Categoria.class.getName());
        
        	if (colecaoAdicionarCategoria == null || colecaoAdicionarCategoria.isEmpty()){
        	throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "CATEGORIA");
        	}
        
        	// Disponibiliza a coleção pela sessão
        	sessao.setAttribute("colecaoAdicionarCategoria", colecaoAdicionarCategoria);
        
        }
        
        
        /*
		 * Colocado por Raphael Rossiter em 14/03/2007
		 * Objetivo: Carregar as subcategorias de acordo com as categorias
		 */
        String idCategoria = (String) httpServletRequest.getParameter("carregarSubcategoria");
        
        if (idCategoria != null && !idCategoria.equalsIgnoreCase("")){
        	
        	FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria(FiltroSubCategoria.DESCRICAO);
        	
        	filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.CATEGORIA_ID,
        	idCategoria));
        	
        	filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.INDICADOR_USO,
        	ConstantesSistema.INDICADOR_USO_ATIVO));
        
        	Collection colecaoAdicionarSubCategoria = fachada.pesquisar(filtroSubCategoria,
        	Subcategoria.class.getName());
        
        	if (colecaoAdicionarSubCategoria == null || colecaoAdicionarSubCategoria.isEmpty()){
        	
        		sessao.removeAttribute("colecaoSubCategoria");
        		
        		throw new ActionServletException(
                    "atencao.pesquisa.nenhum_registro_tabela", null,
                    "SUBCATEGORIA");
        	}
        
        	// Disponibiliza a coleção pela sessão
        	sessao.setAttribute("colecaoAdicionarSubCategoria", colecaoAdicionarSubCategoria);
        }
        
        
        /*
		 * Colocado por Raphael Rossiter em 14/03/2007
		 * Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa
		 */
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
//		httpServletRequest.setAttribute("empresaNome", sistemaParametro.getNomeAbreviadoEmpresa().trim());
		
		httpServletRequest.setAttribute("indicadorTarifaCategoria", sistemaParametro.getIndicadorTarifaCategoria().toString());
        
		
        return retorno;
    }

}


