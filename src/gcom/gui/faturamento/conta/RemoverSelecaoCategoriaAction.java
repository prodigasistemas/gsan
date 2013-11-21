/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
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


