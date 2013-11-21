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
package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Prepara a página para a exibição de Inserir Tarifa Social
 * 
 * @author rodrigo
 */
public class ExibirConsultarTarifaSocialAction extends GcomAction {

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

		ActionForward retorno = actionMapping
				.findForward("consultarTarifaSocial");

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega uma instancia do actionform
		ConsultarTarifaSocialActionForm consultarTarifaSocialActionForm = (ConsultarTarifaSocialActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();		
		
		String idImovel = consultarTarifaSocialActionForm.getIdImovel();


		// Verifica se foi feita uma pesquisa de imovel que retornou para este
		// exibir
		if (idImovel != null  && !idImovel.equals("")) {

			Collection clientesImoveis = fachada.pesquisarClienteImovelPeloImovelParaEndereco(
					new Integer(idImovel));

			if (!clientesImoveis.isEmpty()) {
				ClienteImovel clienteImovel = (ClienteImovel) ((List) clientesImoveis)
						.get(0);
				
				Imovel imovel = clienteImovel.getImovel();

//				if (imovel.getImovelPerfil().getId().intValue() != ImovelPerfil.TARIFA_SOCIAL.intValue()){
//						consultarTarifaSocialActionForm.setIdImovel("");
//						consultarTarifaSocialActionForm.setInscricaoImovel("");
//						
//				}
				
				consultarTarifaSocialActionForm.setInscricaoImovel(imovel.getInscricaoFormatada());
				
				// Obter a quantidade de economias do imóvel escolhido
				int quantEconomias = Fachada.getInstancia()
						.obterQuantidadeEconomias(imovel);

				// Seta na sessão
				sessao.setAttribute("imovel", imovel);
				sessao.setAttribute("quantEconomias", String
						.valueOf(quantEconomias));
				
				Collection colecaoTarifaSocialHelper = null;
				
				if (quantEconomias == 1) {
					colecaoTarifaSocialHelper = fachada.pesquisarDadosClienteTarifaSocial(new Integer(idImovel));
				} else {
					colecaoTarifaSocialHelper = fachada.pesquisarDadosClienteEconomiaTarifaSocial(new Integer(idImovel));
		        }
		        
		        if (colecaoTarifaSocialHelper != null && !colecaoTarifaSocialHelper.isEmpty()) {
		        	sessao.setAttribute("colecaoTarifaSocialHelper", colecaoTarifaSocialHelper);
		        } else {
		        	sessao.removeAttribute("colecaoTarifaSocialHelper");
		        	throw new ActionServletException(
							"atencao.imovel.nao.esta.tarifa_social",
							null, imovel.getId().toString());
		        } 
				
			} else {
				// Matrícula inexistente
				httpServletRequest.setAttribute("imovelNaoEncontrado", true);
				consultarTarifaSocialActionForm.setIdImovel("");
				consultarTarifaSocialActionForm.setInscricaoImovel("Imóvel Inexistente");
				httpServletRequest.setAttribute("nomeCampo", "idImovel");
			}
		}
		
		return retorno;
	}

}
