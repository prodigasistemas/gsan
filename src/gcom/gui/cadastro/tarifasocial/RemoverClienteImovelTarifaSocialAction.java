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
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rafael Corrêa
 * @since 16/01/2007
 */
public class RemoverClienteImovelTarifaSocialAction extends GcomAction {

	/**
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
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirDadosCliente");
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarDadosTarifaSocialClienteActionForm atualizarDadosTarifaSocialClienteActionForm = (AtualizarDadosTarifaSocialClienteActionForm) actionForm;

		String motivoFimRelacao = atualizarDadosTarifaSocialClienteActionForm.getClienteImovelFimRelacaoMotivo();
		
		String dataFimRelacao = atualizarDadosTarifaSocialClienteActionForm.getDataFimRelacao();
		Date dataFimRelacaoFormatada = null;
		Date dataAtual = new Date();
		
		if (dataFimRelacao != null && !dataFimRelacao.trim().equals("")) {
			dataFimRelacaoFormatada = Util.converteStringParaDate(dataFimRelacao);
			
			if (dataFimRelacaoFormatada.compareTo(dataAtual) > 0) {
				throw new ActionServletException("atencao.data_fim_relacao_cliente_imovel");
			}
			
		} else {
			dataFimRelacaoFormatada = dataAtual;
		}
		
		TarifaSocialHelper tarifaSocialHelperAtualizar = (TarifaSocialHelper) sessao.getAttribute("tarifaSocialHelperAtualizar");
		
		if (sessao.getAttribute("colecaoClienteImovelRemover") != null) {

			Collection colecaoClienteImovelRemover = (Collection) sessao
					.getAttribute("colecaoClienteImovelRemover");

			Collection colecaoClienteImovel = (Collection) sessao
					.getAttribute("colecaoClienteImovel");

			Iterator colecaoClienteImovelRemoverIterator = colecaoClienteImovelRemover
					.iterator();

			Collection colecaoClienteRemoverTarifaSocial = null;

			if (tarifaSocialHelperAtualizar.getColecaoClientesRemovidos() != null) {
				colecaoClienteRemoverTarifaSocial = tarifaSocialHelperAtualizar
						.getColecaoClientesRemovidos();
			} else {
				colecaoClienteRemoverTarifaSocial = new ArrayList();
			}

			while (colecaoClienteImovelRemoverIterator.hasNext()) {

				ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovelRemoverIterator
						.next();
				Date dataInicioRelacao = clienteImovel.getDataInicioRelacao();

				if (dataInicioRelacao.compareTo(dataFimRelacaoFormatada) > 0) {
					throw new ActionServletException(
							"atencao.data_fim_relacao_cliente_imovel_menor_inicial");
				}

				colecaoClienteImovel.remove(clienteImovel);

				ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
				clienteImovelFimRelacaoMotivo.setId(new Integer(
						motivoFimRelacao));
				clienteImovel.setDataFimRelacao(dataFimRelacaoFormatada);
				clienteImovel
						.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacaoMotivo);

				colecaoClienteRemoverTarifaSocial.add(clienteImovel);

			}

			tarifaSocialHelperAtualizar
					.setColecaoClientesRemovidos(colecaoClienteImovelRemover);

		} else if (sessao.getAttribute("colecaoClienteImovelEconomiaRemover") != null) {
			
			Collection colecaoClienteImovelEconomiaRemover = (Collection) sessao
					.getAttribute("colecaoClienteImovelEconomiaRemover");

			Collection colecaoClienteImovelEconomia = (Collection) sessao
					.getAttribute("colecaoClienteImovelEconomia");

			Iterator colecaoClienteImovelEconomiaRemoverIterator = colecaoClienteImovelEconomiaRemover
					.iterator();

			Collection colecaoClienteEconomiaRemoverTarifaSocial = null;

			if (tarifaSocialHelperAtualizar.getColecaoClientesEconomiaRemovidos() != null) {
				colecaoClienteEconomiaRemoverTarifaSocial = tarifaSocialHelperAtualizar
						.getColecaoClientesEconomiaRemovidos();
			} else {
				colecaoClienteEconomiaRemoverTarifaSocial = new ArrayList();
			}

			while (colecaoClienteImovelEconomiaRemoverIterator.hasNext()) {

				ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) colecaoClienteImovelEconomiaRemoverIterator
						.next();
				Date dataInicioRelacao = clienteImovelEconomia.getDataInicioRelacao();

				if (dataInicioRelacao.compareTo(dataFimRelacaoFormatada) > 0) {
					throw new ActionServletException(
							"atencao.data_fim_relacao_cliente_imovel_menor_inicial");
				}

				colecaoClienteImovelEconomia.remove(clienteImovelEconomia);

				ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
				clienteImovelFimRelacaoMotivo.setId(new Integer(
						motivoFimRelacao));
				clienteImovelEconomia.setDataFimRelacao(dataFimRelacaoFormatada);
				clienteImovelEconomia
						.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacaoMotivo);

				colecaoClienteEconomiaRemoverTarifaSocial.add(clienteImovelEconomia);

			}

			tarifaSocialHelperAtualizar
					.setColecaoClientesEconomiaRemovidos(colecaoClienteEconomiaRemoverTarifaSocial);
			
		}
		
		sessao.setAttribute("tarifaSocialHelperAtualizar", tarifaSocialHelperAtualizar);
		
		sessao.setAttribute("telaLimpa", true);

		return retorno;

	}

}
