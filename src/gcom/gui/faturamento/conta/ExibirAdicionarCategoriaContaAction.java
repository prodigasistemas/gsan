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


