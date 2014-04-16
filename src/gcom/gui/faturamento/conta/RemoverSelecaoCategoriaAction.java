package gcom.gui.faturamento.conta;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class RemoverSelecaoCategoriaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	
    	//Define o caso de uso que receberá o resultado desta remoção
    	String mapeamentoStruts = httpServletRequest.getParameter("mapeamento");

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward(mapeamentoStruts);

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        String idCategoria = httpServletRequest.getParameter("idCategoria");
        
        this.removerCategoria(idCategoria, sessao);
        
        String idSubcategoria = httpServletRequest.getParameter("idSubcategoria");
        
        this.removerSubcategoria(idSubcategoria, sessao);
        
        //Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
        
        /*
		 * Colocado por Raphael Rossiter em 29/03/2007
		 * Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa
		 */
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		httpServletRequest.setAttribute("empresaNome", sistemaParametro.getNomeAbreviadoEmpresa().trim());
        
        
        return retorno;
    }
    
    
    
    private void removerCategoria(String idCategoria, HttpSession sessao){
    	
    	if (idCategoria != null && !idCategoria.equalsIgnoreCase("") &&
            sessao.getAttribute("colecaoCategoria") != null){
            	
            Collection colecaoCategoria = (Collection) sessao.getAttribute("colecaoCategoria");
            Categoria categoriaSelect = new Categoria();
            categoriaSelect.setId(new Integer(idCategoria));
            	
            colecaoCategoria.remove(categoriaSelect);
            if(colecaoCategoria.isEmpty() || colecaoCategoria == null){
            	sessao.setAttribute("colecao", 1);
            	sessao.removeAttribute("adicionar");
            }
            else{
            	sessao.removeAttribute("existeColecao");
            }
        }
    }
    
    
    private void removerSubcategoria(String idSubcategoria, HttpSession sessao){
    	
    	if (idSubcategoria != null && !idSubcategoria.equalsIgnoreCase("") &&
            sessao.getAttribute("colecaoSubcategoria") != null){
            	
            Collection colecaoSubcategoria = (Collection) sessao.getAttribute("colecaoSubcategoria");
            
            //Pesquisa a subcategoria selecionada para carregar os atributos.
            FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
            
            filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade("categoria");
            
            filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID,
            idSubcategoria));
            
            Collection colecaoSubCategoriaSelected = Fachada.getInstancia().pesquisar(filtroSubCategoria, 
            Subcategoria.class.getName());
            
            Subcategoria subcategoriaSelect = (Subcategoria) Util.retonarObjetoDeColecao(colecaoSubCategoriaSelected);
            	
            colecaoSubcategoria.remove(subcategoriaSelect);
            
            if(colecaoSubcategoria.isEmpty() || colecaoSubcategoria == null){
            	sessao.setAttribute("colecao", 1);
            	sessao.removeAttribute("adicionar");
            }
            else{
            	sessao.removeAttribute("existeColecao");
            }
            
            Collections.sort((List) colecaoSubcategoria, new Comparator() {
				public int compare(Object a, Object b) {
					Subcategoria subcategoria1 = (Subcategoria) a;
					Subcategoria subcategoria2 = (Subcategoria) b;
					
					int comparacaoCategoria = subcategoria1.getCategoria().getDescricao()
					.compareTo(subcategoria2.getCategoria().getDescricao());
					
					if (comparacaoCategoria == 0){
				
						return subcategoria1.getDescricao()
    					.compareTo(subcategoria2.getDescricao());
						
					}
					else{
						
						return comparacaoCategoria;
					}
				}
			});
        }
    }

}


