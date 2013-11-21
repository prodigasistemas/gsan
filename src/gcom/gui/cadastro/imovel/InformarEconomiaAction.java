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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InformarEconomiaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		EconomiaActionForm economiaActionForm = (EconomiaActionForm) actionForm;

		// obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoImovelEconomiasModificadas = (Collection) sessao
				.getAttribute("colecaoImovelEconomiasModificadas");

		Collection colecaoClientesImoveisEconomiaRemovidas = (Collection) sessao.getAttribute("colecaoClientesImoveisEconomiaRemovidas");
				
		Collection colecaoRemovidas = (Collection) sessao.getAttribute("colecaoRemovidas");
		
        /** alterado por pedro alexandre dia 18/11/2006 
         * Recupera o usuário logado para passar no metódo de remover quadra
         * para verificar se o usuário tem abrangência para remover as quadras 
         * selecionadas.
         */
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

        
		int alteracao = 0;
		
		if (colecaoRemovidas != null && !colecaoRemovidas.isEmpty()){
			for (Iterator iter = colecaoRemovidas.iterator(); iter.hasNext();) {
				ImovelEconomia imovelEconomiaIterator = (ImovelEconomia) iter.next();
               
                 /** alterado por pedro alexandre dia 19/11/2006
                 * alterado para acoplar o esquema de segurança de acesso por abragência */
				//fachada.removerImovelEconomia(imovelEconomiaIterator);
               
				fachada.removerImovelEconomia(imovelEconomiaIterator, usuarioLogado);
				
			}
			
			alteracao = 1;
			
		}
		
		
		if (colecaoImovelEconomiasModificadas != null
				&& !colecaoImovelEconomiasModificadas.isEmpty()) {
			// Cria variaveis

            /** alterado por pedro alexandre dia 19/11/2006
             * alterado para acoplar o esquema de segurança de acesso por abragência */
			// inseri os clientes imoveis economias e os imoveis economia na
			// subcategoria
            //fachada.informarImovelEconomia(colecaoImovelEconomiasModificadas);
			
			
			
			fachada.informarImovelEconomia(colecaoImovelEconomiasModificadas, usuarioLogado);
			
			
			
			alteracao = 1;
		}
		
				
		if (colecaoClientesImoveisEconomiaRemovidas != null && !colecaoClientesImoveisEconomiaRemovidas.isEmpty()){
			
			Iterator iterator = colecaoClientesImoveisEconomiaRemovidas.iterator();
			
			while (iterator.hasNext()) {
				ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) iterator.next();
				
				fachada.remover(clienteImovelEconomia);
				
			}
			
		}
		
		
		if (alteracao == 0){
			throw new ActionServletException(
					"atencao.nao_atualizado_subcategoria_economia");
		}

		// limpa o form e a sessao
		sessao.removeAttribute("colecaoImovelSubCategoriasCadastradas");
		sessao.removeAttribute("colecaoImovelEconomiasModificadas");
		sessao.removeAttribute("colecaoClientesImoveisEconomiaRemovidas");
		economiaActionForm.setCodigoImovelSubCategoria(null);
		
		montarPaginaSucesso(httpServletRequest, "Imóvel "
				+ economiaActionForm.getIdImovel()
				+ " com economia(s) informada(s) com sucesso.",
				"Informar outro(s) Imóvel(eis) Economia(s)",
				"exibirInformarEconomiaAction.do?menu=sim");

		economiaActionForm.setIdImovel(null);
		economiaActionForm.setEnderecoImovel(null);

		return retorno;
	}
}