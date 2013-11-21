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
* Thiago Vieira
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
* Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.Negativador;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir manter Negativador
 * @author Thiago Vieira
 * @created 22/12/2007
 */

public class ExibirManterNegativadorAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterNegativador");
		HttpSession sessao = httpServletRequest.getSession(false);
        Collection collectionNegativador = null;

        FiltroNegativador filtroNegativador = null;
        
		// Verifica se o filtro foi informado pela página de filtragem do Negativador
		if (sessao.getAttribute("filtroNegativador") != null) {
			filtroNegativador = (FiltroNegativador) sessao.getAttribute("filtroNegativador");
		}
		
		String checkAtualizar = "";
		
		if (sessao.getAttribute("checkAtualizar") != null) {
			checkAtualizar = (String) sessao.getAttribute("checkAtualizar");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterNegativador"))) {
				
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("imovel");
			
			filtroNegativador.setCampoOrderBy(FiltroNegativador.ID);

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroNegativador, Negativador.class.getName());
			
			
			collectionNegativador = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			// [FS0004] Nenhum registro encontrado				
			if (collectionNegativador == null || collectionNegativador.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			
			if (collectionNegativador.size()== 1 && checkAtualizar != null && !checkAtualizar.equals("")){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarNegativador");
				
				Negativador negativador = (Negativador)collectionNegativador.iterator().next();
				sessao.setAttribute("idRegistroAtualizacao", new Integer (negativador.getId()).toString());
			}else{
				sessao.setAttribute("collectionNegativador", collectionNegativador);
			}

		}
		
		return retorno;
        
    }

}