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
package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoHelper;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de remover um objeto do tipo ParcelamentoQuantidadeReparcelamentoHelper 
 * da collectionParcelamentoQuantidadeReparcelamentoHelper
 * 
 * @author Vivianne Sousa
 * @created 09/05/2006
 */
public class RemoverParcelamentoQuantidadeReparcelamentoHelperAction extends GcomAction {
	/**
	 * @author Vivianne Sousa
	 * @date 09/05/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
    	ActionForward retorno = null;
    	if (sessao.getAttribute("UseCase")!= null &&
    			sessao.getAttribute("UseCase").equals("INSERIRPERFIL")){
    		retorno = actionMapping.findForward("inserirPerfilRemoverParcelamentoQuantidadeReparcelamentoHelper");
    		//ParcelamentoPerfilActionForm parcelamentoPerfilActionForm = (ParcelamentoPerfilActionForm) actionForm;
    	}else if (sessao.getAttribute("UseCase")!= null &&
    			sessao.getAttribute("UseCase").equals("ATUALIZARPERFIL")){
    		retorno = actionMapping.findForward("atualizarPerfilRemoverParcelamentoQuantidadeReparcelamentoHelper");
    		//AtualizarParcelamentoPerfilActionForm atualizarParcelamentoPerfilActionForm = (AtualizarParcelamentoPerfilActionForm) actionForm;	
    	}
    	
    	sessao.getAttribute("ParcelamentoPerfilActionForm");
    	sessao.getAttribute("AtualizarParcelamentoPerfilActionForm");
    	
    	String quantidadeMaximaReparcelamento = httpServletRequest.getParameter("qtdeMaxReparcelamento");

        if (quantidadeMaximaReparcelamento != null && !quantidadeMaximaReparcelamento.equalsIgnoreCase("") &&
        	sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") != null){
        	        	
        	Collection collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao
            					.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
        	
        	Collection collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas = null;
    		if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas") != null
    				&& !sessao
    						.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas")
    						.equals("")) {
    			collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas = (Collection) sessao
    					.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas");
    		} else {
    			collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas = new ArrayList();
    		}
    		
        	ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQuantidadeReparcelamentoHelper = null;
        	ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQuantidadeReparcelamentoHelperExcluir = null;
			Iterator iterator = collectionParcelamentoQuantidadeReparcelamentoHelper.iterator();
						
			while (iterator.hasNext()) {
				parcelamentoQuantidadeReparcelamentoHelper = (ParcelamentoQuantidadeReparcelamentoHelper) iterator.next();
		
				//procura na coleção a rotaAcaoCriterio que tem o idCobrancaAcao selecionado
				if (parcelamentoQuantidadeReparcelamentoHelper.getQuantidadeMaximaReparcelamento().toString().equals
						(quantidadeMaximaReparcelamento)){
					parcelamentoQuantidadeReparcelamentoHelperExcluir =  parcelamentoQuantidadeReparcelamentoHelper;
					collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas.add(parcelamentoQuantidadeReparcelamentoHelper);
					
				}
			}
			
			collectionParcelamentoQuantidadeReparcelamentoHelper.remove(parcelamentoQuantidadeReparcelamentoHelperExcluir);
       	 	sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper", 
       	 			collectionParcelamentoQuantidadeReparcelamentoHelper);
       	 	sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas",
       	 		collectionParcelamentoQuantidadeReparcelamentoHelperLinhaRemovidas);
        }
        
		if (sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper") == null || 
			((Collection) sessao.getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper")).size() == 0){
			sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperVazia","1");
		}else{
			sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelperVazia","2");
		}
        
       return retorno;
    }
    
 }
 