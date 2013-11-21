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

import java.util.Collection;
import java.util.List;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */
public class ExibirInserirTarifaSocialDadosEconomiaAction extends GcomAction {
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

		ActionForward retorno = null;
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega uma instancia do actionform
		DynaValidatorForm inserirTarifaSocialActionForm = (DynaValidatorForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		int quantidadeEconomiasImovel = 0;

		if (inserirTarifaSocialActionForm.get("nomeRegistroAtendimento") != null
				&& !inserirTarifaSocialActionForm
						.get("nomeRegistroAtendimento").equals("")) {

			quantidadeEconomiasImovel = ((Integer) inserirTarifaSocialActionForm
					.get("qtdEconomia")).intValue();

		} else {
			String idRA = (String) inserirTarifaSocialActionForm
					.get("registroAtendimento");

			RegistroAtendimento registroAtendimento = fachada
					.verificarRegistroAtendimentoTarifaSocial(idRA);

			if (registroAtendimento != null) {

				String idImovel = registroAtendimento.getImovel().getId()
						.toString();

				inserirTarifaSocialActionForm.set("nomeRegistroAtendimento",
						registroAtendimento.getSolicitacaoTipoEspecificacao()
								.getDescricao());

				Collection clientesImoveis = fachada
						.pesquisarClienteImovelPeloImovelParaEndereco(new Integer(
								idImovel));

				if (!clientesImoveis.isEmpty()) {
					ClienteImovel clienteImovel = (ClienteImovel) ((List) clientesImoveis)
							.get(0);

					Imovel imovel = clienteImovel.getImovel();

					if (imovel.getImovelPerfil().getId().intValue() == ImovelPerfil.TARIFA_SOCIAL
							.intValue()) {
						// FS0002 - Verificar imóvel na tarifa social
						throw new ActionServletException(
								"atencao.imovel.associado.registro_atendimento.ja.tarifa_social",
								null, imovel.getId().toString());
					}

					// Obter a quantidade de economias do imóvel escolhido
					quantidadeEconomiasImovel = fachada
							.obterQuantidadeEconomias(imovel);

					// Seta no request
					sessao.setAttribute("clienteImovel", clienteImovel);
					sessao.setAttribute("quantEconomias", String
							.valueOf(quantidadeEconomiasImovel));
					inserirTarifaSocialActionForm.set("qtdEconomia", quantidadeEconomiasImovel);
					httpServletRequest.setAttribute("idImovelRA", idImovel);
					
				} else {
					// Matrícula inexistente
					// httpServletRequest.setAttribute("matriculaInvalida",
					// "Matrícula do imóvel " + idImovel + " inexistente");

					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Matrícula do imóvel " + idImovel);
				}
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Registro de Atendimento");
			}
		}

		// Dependendo da quantidade de economias do imovel, o action será
		// redirecionado para o
		// caso de uso correspondente
		if (quantidadeEconomiasImovel == 1) {
			// Chama o caso de uso [UC0065 - Inserir Dados Tarifa Social - Uma
			// Economia]
			sessao.removeAttribute("colecaoClienteImovelEconomia");
			retorno = actionMapping
					.findForward("inserirTarifaSocialDadosUmaEconomia");

		} else if (quantidadeEconomiasImovel > 1) {
			// Chama o caso de uso [UC0066 - Inserir Dados Tarifa Social - Mais
			// de Uma Economia]
			sessao.removeAttribute("clienteImovel");
			sessao.removeAttribute("colecaoDadosTarifaSocial");
			retorno = actionMapping
					.findForward("inserirTarifaSocialDadosMultiplasEconomias");
		}

		return retorno;
	}

}
