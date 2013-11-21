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
package gcom.gui.cobranca;

import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
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

/**
 * < <Descrição da Classe>>
 * 
 * @author Fernanda Paiva
 */
public class ConsultarDebitoAction extends GcomAction {
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

		// Seta a ação de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		 HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		ConsultarDebitoActionForm consultarDebitoActionForm = (ConsultarDebitoActionForm) actionForm;

		String codigoImovel = null;

		if (httpServletRequest.getParameter("codigoImovel") != null) {

			codigoImovel = httpServletRequest
					.getParameter("codigoImovel");

		} else {

			codigoImovel = consultarDebitoActionForm.getCodigoImovel();

		}
        	
		String codigoClienteSuperior = consultarDebitoActionForm
			.getCodigoClienteSuperior();
		
		String codigoCliente = consultarDebitoActionForm
				.getCodigoCliente();

		Integer tipoRelacao = null;

		if (codigoCliente != null && !codigoCliente.trim().equals("")) {
		
		if (consultarDebitoActionForm.getTipoRelacao() != null
					&& !consultarDebitoActionForm.getTipoRelacao().trim()
							.equals("-1")) {
				tipoRelacao = new Integer(consultarDebitoActionForm
						.getTipoRelacao());
			} else {
				tipoRelacao = null;
			}

		}

		// Verifica se o usuário não digitou código do cliente nem a matricula
		// do imovel
		if ((codigoImovel == null || codigoImovel.equals(""))
				&& (codigoCliente == null || codigoCliente.equals(""))
				&& (codigoClienteSuperior == null || codigoClienteSuperior
						.equals(""))) {
			throw new ActionServletException(
					"atencao.verificar.informado.imovel_cliente");
		}

		boolean peloMenosUmParametroInformado = false;

		if (codigoImovel != null && !codigoImovel.trim().equals("")) {

			codigoImovel = codigoImovel.trim();

			// Seta o retorno para a página que vai detalhar o imovel
			retorno = actionMapping.findForward("exibirDebitoImovel");

			peloMenosUmParametroInformado = true;
		} else if ((codigoCliente != null && !codigoCliente.trim().equals(""))) {
			codigoCliente = codigoCliente.trim();

			// Seta o retorno para a página que vai detalhar o cliente
			retorno = actionMapping.findForward("exibirDebitoCliente");

			peloMenosUmParametroInformado = true;

		} else if ((codigoClienteSuperior != null && !codigoClienteSuperior
				.trim().equals(""))) {
			
			codigoClienteSuperior = codigoClienteSuperior.trim();
			
			// Seta o retorno para a página que vai detalhar o cliente
			retorno = actionMapping.findForward("exibirDebitoCliente");

			peloMenosUmParametroInformado = true;
			
		}

		// ClienteRelacaoTipo tipoRelacaoSelecionada = null;

		// Verifica o tipo de relação
		if (tipoRelacao != null && !tipoRelacao.equals("")
				&& tipoRelacao != ConstantesSistema.NUMERO_NAO_INFORMADO) {
			FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo();

			filtroClienteRelacaoTipo.adicionarParametro(new ParametroSimples(
					FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,
					tipoRelacao));

			Collection<ClienteRelacaoTipo> collectionClienteRelacaoTipo = fachada
					.pesquisar(filtroClienteRelacaoTipo,
							ClienteRelacaoTipo.class.getName());

			if (collectionClienteRelacaoTipo != null
					&& collectionClienteRelacaoTipo.isEmpty()) {
				throw new ActionServletException(
						"atencao.collectionClienteRelacaoTipo_inexistente",
						null, "id");
			}

			/* tipoRelacaoSelecionada = */sessao.setAttribute("tipoRelacao", collectionClienteRelacaoTipo
					.iterator().next());
		} else {
			tipoRelacao = null;
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.selecionar.nenhum_parametro_informado");
		}

		if (httpServletRequest.getParameter("ehPopup") != null) {
			sessao.setAttribute("ehPopup", "true");
		}

		if (httpServletRequest.getParameter("caminhoRetornoTelaConsultaDebito") != null) {

			httpServletRequest.setAttribute("caminhoRetornoTelaConsultaDebito",
					httpServletRequest
							.getParameter("caminhoRetornoTelaConsultaDebito"));

		}

		return retorno;
	}
}
