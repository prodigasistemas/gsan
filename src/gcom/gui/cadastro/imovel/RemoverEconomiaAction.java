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

import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que remove a o objeto selecionado de cliente imovel economia que está
 * com o imovel economia
 * 
 * @author Sávio Luiz
 * @created 20 de Maio de 2004
 */
public class RemoverEconomiaAction extends GcomAction {
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

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("informarEconomia");

		// Obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		//Fachada fachada = Fachada.getInstancia();

		// Cria variaveis
		Collection colecaoImovelSubCategoriasCadastradas = (Collection) sessao
				.getAttribute("colecaoImovelSubCategoriasCadastradas");

		Collection colecaoImovelEconomiasModificadas = (Collection) sessao
				.getAttribute("colecaoImovelEconomiasModificadas");

		String codigoImovelEconomia = (String) httpServletRequest
				.getParameter("codigoImovelEconomia");

		Collection colecaoImovelSubCategoriaNova = new HashSet();
		// Verifica se veio algum parametro no economia_inserir.jsp
		// caso tenha vindo pega o parametro e procura na coleção um objeto
		// que tenha um hashCode igual ao do parametro
		if (httpServletRequest.getParameter("codigoImovelEconomia") != null
				&& !httpServletRequest.getParameter("codigoImovelEconomia")
						.trim().equals("")) {

			Iterator imovelSubCategoriaIterator = colecaoImovelSubCategoriasCadastradas
					.iterator();
			while (imovelSubCategoriaIterator.hasNext()) {
				ImovelSubcategoria imovelSubCategoria = null;
				imovelSubCategoria = (ImovelSubcategoria) imovelSubCategoriaIterator
						.next();

				Iterator imovelEconomiaIterator = imovelSubCategoria
						.getImovelEconomias().iterator();

				Collection colecaoImovelEconomiaiNaoRemovidas = new HashSet();

				
				while (imovelEconomiaIterator.hasNext()) {

					ImovelEconomia imovelEconomia = (ImovelEconomia) imovelEconomiaIterator
							.next();

					if (imovelEconomia.getUltimaAlteracao().getTime() == Long
							.parseLong(codigoImovelEconomia)) {
						// caso o imovel economia tenha codigo igual a nulo

						if (imovelEconomia.getId() == null
								|| imovelEconomia.getId().equals("")) {
							// remove o imovel economia só da coleção
							// pois não existe na base ainda.
							imovelEconomiaIterator.remove();

							// caso o imovel economia tenha codigo não seja
							// igual a nulo
						} else {
							// remove o imovel economia só da coleção
							// e como ja existe na base também remove da
							// base

							//fachada.removerImovelEconomia(imovelEconomia);
							
							if (sessao.getAttribute("colecaoRemovidas") != null){
								Collection colecaoRemovidas = (Collection) sessao.getAttribute("colecaoRemovidas");
								colecaoRemovidas.add(imovelEconomia);
								sessao.setAttribute("colecaoRemovidas", colecaoRemovidas);
							} else {
								Collection<ImovelEconomia> colecaoRemovidas = new HashSet();
								colecaoRemovidas.add(imovelEconomia);
								sessao.setAttribute("colecaoRemovidas", colecaoRemovidas);
							}
							
							imovelEconomiaIterator.remove();

						}

					} else {
						if (imovelSubCategoria.getComp_id().getSubcategoria()
								.getId().equals(
										imovelEconomia.getImovelSubcategoria()
												.getComp_id().getSubcategoria()
												.getId())) {
							colecaoImovelEconomiaiNaoRemovidas
									.add(imovelEconomia);
							imovelEconomiaIterator.remove();
						}
					}

				}

				imovelSubCategoria.setImovelEconomias(new HashSet(
						colecaoImovelEconomiaiNaoRemovidas));
				colecaoImovelSubCategoriaNova.add(imovelSubCategoria);
			}
		}

		// É preciso remover , caso o getTime da ultimaAlteração sejá igual,os
		// imóveis economia
		// da colecao de imovelEconomiasModificadas que foi removido.
		Iterator imoveisEconomiaModificadasIterator = colecaoImovelEconomiasModificadas
				.iterator();
		while (imoveisEconomiaModificadasIterator.hasNext()) {
			ImovelEconomia imovelEconomiaModificada = (ImovelEconomia) imoveisEconomiaModificadasIterator
					.next();

			if (imovelEconomiaModificada.getUltimaAlteracao().getTime() == Long
					.parseLong(codigoImovelEconomia)) {

				imoveisEconomiaModificadasIterator.remove();
				break;
			}
		}

		sessao.setAttribute("colecaoImovelSubCategoriasCadastradas",
				colecaoImovelSubCategoriaNova);

		return retorno;
	}
}
