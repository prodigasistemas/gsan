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
import gcom.cadastro.imovel.FiltroCategoria;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AdicionarCategoriaContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirAdicionarCategoriaConta");

        Fachada fachada = Fachada.getInstancia();
        
        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        //Instância do formulário que está sendo utilizado
        AdicionarCategoriaContaActionForm adicionarCategoriaContaActionForm = 
        (AdicionarCategoriaContaActionForm) actionForm;
        
        //Parãmetros recebidos para adicionar uma categoria
        String idCategoria = adicionarCategoriaContaActionForm.getCategoriaID();
        String qtdEconomias = adicionarCategoriaContaActionForm.getQtdEconomia();
        
        
        //Validação dos campos recebidos
        if (idCategoria == null || idCategoria.equalsIgnoreCase("")){
        	throw new ActionServletException(
                    "atencao.campo_texto.obrigatorio", null, "categoria");
        }
        
        if (qtdEconomias == null || qtdEconomias.equalsIgnoreCase("")){
        	throw new ActionServletException(
                    "atencao.campo_texto.obrigatorio", null, "quantidade de economias");
        }
        
        
        /*
		 * Colocado por Raphael Rossiter em 14/03/2007
		 * Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa
		 */
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		if (sistemaParametro.getIndicadorTarifaCategoria().equals(SistemaParametro.INDICADOR_TARIFA_SUBCATEGORIA)){
			
			String idSubcategoria = adicionarCategoriaContaActionForm.getSubcategoriaID();
		
			//Validação dos campos recebidos
	        if (idSubcategoria == null || idSubcategoria.equalsIgnoreCase("")){
	        	throw new ActionServletException(
	                    "atencao.campo_texto.obrigatorio", null, "subcategoria");
	        }
	        
	        this.adicionarSubCategoria(idSubcategoria, qtdEconomias, sessao, httpServletRequest);
		}
		else{
			
			this.adicionarCategoria(idCategoria, qtdEconomias, sessao, httpServletRequest);
		}
        
        
        
        
        //Limpa o formulário que adiciona categorias a conta.
        adicionarCategoriaContaActionForm.setCategoriaID("");
        adicionarCategoriaContaActionForm.setSubcategoriaID("");
        adicionarCategoriaContaActionForm.setQtdEconomia("");
        sessao.setAttribute("retorno","sim");
        sessao.setAttribute("adicionar","1");
        
        
        /*
		 * Colocado por Raphael Rossiter em 29/03/2007
		 * Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa
		 */
//		httpServletRequest.setAttribute("empresaNome", sistemaParametro.getNomeAbreviadoEmpresa().trim());
        
        httpServletRequest.setAttribute("indicadorTarifaCategoria", sistemaParametro.getIndicadorTarifaCategoria().toString());
        
        return retorno;
    }
    
    
    
    private void adicionarCategoria(String idCategoria, String qtdEconomias, HttpSession sessao,
    		HttpServletRequest httpServletRequest){
    	
    	//Pesquisa a categoria selecionada para carregar os atributos.
        FiltroCategoria filtroCategoria = new FiltroCategoria();
        
        filtroCategoria.adicionarParametro(new ParametroSimples(FiltroCategoria.CODIGO,
        idCategoria));
        
        Collection colecaoCategoriaSelected = Fachada.getInstancia().pesquisar(filtroCategoria, 
        Categoria.class.getName());
        
        Categoria categoriaSelected = (Categoria) Util.retonarObjetoDeColecao(colecaoCategoriaSelected);
        
        //Carrega a categoria com a quantidade de economias informada pelo usuário
        categoriaSelected.setQuantidadeEconomiasCategoria(new Integer(qtdEconomias));
        
        //Carrega o novo objeto na sessão.
        if (sessao.getAttribute("colecaoCategoria") == null){
        	Collection colecaoCategoria = new Vector();
        	colecaoCategoria.add(categoriaSelected);
        	sessao.setAttribute("colecaoCategoria", colecaoCategoria);
        	
        	httpServletRequest.setAttribute("reloadPage", "OK");
        	
        	//Definindo o caso de uso que receberá o retorno
        	if (sessao.getAttribute("UseCase").equals("INSERIRCONTA")){
        		httpServletRequest.setAttribute("reloadPageURL", "INSERIRCONTA");
        	}
        	else if (sessao.getAttribute("UseCase").equals("RETIFICARCONTA")) {
        		httpServletRequest.setAttribute("reloadPageURL", "RETIFICARCONTA");
        		
        	} else if (sessao.getAttribute("UseCase").equals("SIMULARCALCULOCONTA")) {
        		httpServletRequest.setAttribute("reloadPageURL", "SIMULARCALCULOCONTA");
        	}
        	sessao.setAttribute("totalEconomia",categoriaSelected.getQuantidadeEconomiasCategoria());
        }
        else{
        	Collection colecaoCategoria = (Collection) sessao.getAttribute("colecaoCategoria");
        	if (!colecaoCategoria.contains(categoriaSelected)){
        		colecaoCategoria.add(categoriaSelected);
        		httpServletRequest.setAttribute("reloadPage", "OK");
        		
        		//Definindo o caso de uso que receberá o retorno
            	if (sessao.getAttribute("UseCase").equals("INSERIRCONTA")){
            		httpServletRequest.setAttribute("reloadPageURL", "INSERIRCONTA");
            	}
            	else if (sessao.getAttribute("UseCase").equals("RETIFICARCONTA")) {
            		httpServletRequest.setAttribute("reloadPageURL", "RETIFICARCONTA");
            		
            	} else if (sessao.getAttribute("UseCase").equals("SIMULARCALCULOCONTA")) {
            		httpServletRequest.setAttribute("reloadPageURL", "SIMULARCALCULOCONTA");
            	}
            	Iterator colecaoCategoriaIt = colecaoCategoria.iterator();
            	Categoria categoria;
            	String qtdPorEconomia;
        		Integer qtdEconnomia = 0;
        		while (colecaoCategoriaIt.hasNext()) {
        			categoria = (Categoria) colecaoCategoriaIt.next();

        			if (categoria.getId() != null) {

        				qtdPorEconomia = categoria.getQuantidadeEconomiasCategoria().toString();

        				qtdEconnomia = Util.somaInteiros(qtdEconnomia,new Integer(qtdPorEconomia));
        			}
        		}
        		sessao.setAttribute("totalEconomia",qtdEconnomia);
        	}
        	// [FS0009] - Verificar categoria já existente
        	else{
        		throw new ActionServletException(
                        "atencao.adicionar_categoria_ja_existente");
        	}
        }
    }
    
    
    
    private void adicionarSubCategoria(String idSubcategoria, String qtdEconomias, HttpSession sessao,
    		HttpServletRequest httpServletRequest){
    	
    	//Pesquisa a subcategoria selecionada para carregar os atributos.
        FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
        
        filtroSubCategoria.adicionarCaminhoParaCarregamentoEntidade("categoria");
        
        filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID,
        idSubcategoria));
        
        Collection colecaoSubCategoriaSelected = Fachada.getInstancia().pesquisar(filtroSubCategoria, 
        Subcategoria.class.getName());
        
        Subcategoria subcategoriaSelected = (Subcategoria) Util.retonarObjetoDeColecao(colecaoSubCategoriaSelected);
        
        //Carrega a subcategoria com a quantidade de economias informada pelo usuário
        subcategoriaSelected.setQuantidadeEconomias(new Integer(qtdEconomias));
        
        //Carrega o novo objeto na sessão.
        if (sessao.getAttribute("colecaoSubcategoria") == null){
        	Collection colecaoSubcategoria = new Vector();
        	colecaoSubcategoria.add(subcategoriaSelected);
        	sessao.setAttribute("colecaoSubcategoria", colecaoSubcategoria);
        	
        	httpServletRequest.setAttribute("reloadPage", "OK");
        	
        	//Definindo o caso de uso que receberá o retorno
        	if (sessao.getAttribute("UseCase").equals("INSERIRCONTA")){
        		httpServletRequest.setAttribute("reloadPageURL", "INSERIRCONTA");
        	}
        	else if (sessao.getAttribute("UseCase").equals("RETIFICARCONTA")) {
        		httpServletRequest.setAttribute("reloadPageURL", "RETIFICARCONTA");
        		
        	} else if (sessao.getAttribute("UseCase").equals("SIMULARCALCULOCONTA")) {
        		httpServletRequest.setAttribute("reloadPageURL", "SIMULARCALCULOCONTA");
        	}
        	sessao.setAttribute("totalEconomia",subcategoriaSelected.getQuantidadeEconomias());
        }
        else{
        	Collection colecaoSubcategoria = (Collection) sessao.getAttribute("colecaoSubcategoria");
        	if (!colecaoSubcategoria.contains(subcategoriaSelected)){
        		colecaoSubcategoria.add(subcategoriaSelected);
        		httpServletRequest.setAttribute("reloadPage", "OK");
        		
        		//Definindo o caso de uso que receberá o retorno
            	if (sessao.getAttribute("UseCase").equals("INSERIRCONTA")){
            		httpServletRequest.setAttribute("reloadPageURL", "INSERIRCONTA");
            	}
            	else if (sessao.getAttribute("UseCase").equals("RETIFICARCONTA")) {
            		httpServletRequest.setAttribute("reloadPageURL", "RETIFICARCONTA");
            		
            	} else if (sessao.getAttribute("UseCase").equals("SIMULARCALCULOCONTA")) {
            		httpServletRequest.setAttribute("reloadPageURL", "SIMULARCALCULOCONTA");
            	}
            	Iterator colecaoSubcategoriaIt = colecaoSubcategoria.iterator();
            	Subcategoria subcategoria;
            	String qtdPorEconomia;
        		Integer qtdEconnomia = 0;
        		while (colecaoSubcategoriaIt.hasNext()) {
        			subcategoria = (Subcategoria) colecaoSubcategoriaIt.next();

        			if (subcategoria.getId() != null) {

        				qtdPorEconomia = subcategoria.getQuantidadeEconomias().toString();

        				qtdEconnomia = Util.somaInteiros(qtdEconnomia,new Integer(qtdPorEconomia));
        			}
        		}
        		sessao.setAttribute("totalEconomia",qtdEconnomia);
        		
        		
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
    					return comparacaoCategoria;
    				}
    			});
        		
        	}
        	// [FS0009] - Verificar subcategoria já existente
        	else{
        		throw new ActionServletException(
                        "atencao.adicionar_subcategoria_ja_existente");
        	}
        }
    }

}
