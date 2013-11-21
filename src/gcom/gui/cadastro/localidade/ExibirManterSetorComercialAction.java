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
package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirManterSetorComercialAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
//    	Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //PesquisarFiltrarSetorComercialActionForm pesquisarFiltrarSetorComercialActionForm = (PesquisarFiltrarSetorComercialActionForm) actionForm;
        
        ActionForward retorno = actionMapping.findForward("manterSetorComercial");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String atualizar = (String)sessao.getAttribute("indicadorAtualizar");


        FiltroSetorComercial filtroSetorComercial = (FiltroSetorComercial)sessao.getAttribute("filtroSetorComercial");
		
		//		Retorna Setor Comercial
        Collection colecaoPesquisa = fachada.pesquisar(filtroSetorComercial,
                SetorComercial.class.getName());

        
        if ( !Util.isVazioOrNulo(colecaoPesquisa)) {
	        if (atualizar != null && colecaoPesquisa.size() == 1){
	        	SetorComercial setorComercial = (SetorComercial) colecaoPesquisa.iterator().next();
	        	httpServletRequest.setAttribute("setorComercialID",
						setorComercial.getId());
	        	
	        	retorno = actionMapping
	            	.findForward("atualizarSetorComercial");
	        	
	        } else {
	        	retorno = actionMapping
                .findForward("manterSetorComercial");
	        	
	        	Collection setoresComercial = null;
	            //      Aciona o controle de paginação para que sejam pesquisados apenas
	    		// os registros que aparecem na página
	    		Map resultado = controlarPaginacao(httpServletRequest, retorno,
	    				filtroSetorComercial, SetorComercial.class.getName());
	    		setoresComercial = (Collection) resultado.get("colecaoRetorno");
	    		retorno = (ActionForward) resultado.get("destinoActionForward");

	    		if (setoresComercial == null || setoresComercial.isEmpty()) {
	    			// Nenhum cliente cadastrado
	    			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
	    		}
	    		// Manda o filtro pelo request para o ExibirManterClienteAction
	    		sessao.setAttribute("colecaoSetorComercial", setoresComercial);
	        }
        } else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
        if (httpServletRequest.getAttribute("voltar") == null){
			sessao.setAttribute("manter", "manter");
        }
        else
        {
    		sessao.removeAttribute("manter");
        }
        
        return retorno;
        
    }

}