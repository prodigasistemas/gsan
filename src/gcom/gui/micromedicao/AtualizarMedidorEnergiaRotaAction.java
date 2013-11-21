/**
 * 
 */
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
package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Hugo Leonardo
 * @date 15/03/2010
 * 
 */
public class AtualizarMedidorEnergiaRotaAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		
		// Recupera o ponto de coleta da sessão
		Collection colecao = (Collection) sessao.getAttribute("colecaoColetaMedidorEnergia");
    	
    	ColetaMedidorEnergiaHelper helper = null;
    	String numeroMedidor = null;
    	String matricula = null;
		
		if(colecao != null && !colecao.isEmpty()){
        	
        	Iterator iteratorColecaoMedidor = colecao.iterator();
        	
        	int valor = 0;
        	// Atualiza os valores do helper
    		while (iteratorColecaoMedidor.hasNext()) {
    			helper = (ColetaMedidorEnergiaHelper) iteratorColecaoMedidor.next();
    			// teste para ver se existe na página alguma categoria 
    			valor++;
    			if (requestMap.get("medidor"+ helper.getImovel()) != null) {

    				numeroMedidor = (requestMap.get("medidor" + helper.getImovel()))[0];
    				if(numeroMedidor != null && !numeroMedidor.trim().equals("")){
    					helper.setMedidorEnergia(numeroMedidor);	
    				}
    			}
        	}
    		
    		iteratorColecaoMedidor = colecao.iterator();
    		//Atualiza os novos valores na base
    		while (iteratorColecaoMedidor.hasNext()) {
    			helper = (ColetaMedidorEnergiaHelper) iteratorColecaoMedidor.next();
    			
    			matricula = helper.getImovel();
    			numeroMedidor = helper.getMedidorEnergia();
    			
    			// Atualiza os dados
    			fachada.atualizarNumeroMedidorEnergiaImovel(matricula, numeroMedidor);
    		}
    		
        } else {
			// Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			
			sessao.removeAttribute("colecaoColetaMedidorEnergia");
			
			montarPaginaSucesso(
					httpServletRequest,"Medidor de energia atualizado com sucesso.",
					"Realizar outra Atualização de Medidor de energia.",
					"exibirInformarMedidorEnergiaRotaAction.do?menu=sim");
		}

		// devolve o mapeamento de retorno
		return retorno;
	}

}
