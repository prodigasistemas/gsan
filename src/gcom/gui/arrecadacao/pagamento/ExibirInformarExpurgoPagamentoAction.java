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
package gcom.gui.arrecadacao.pagamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [UC0247] Consultar Pagamentos
 * 
 * @author Sávio Luiz
 * @date 19/12/2007
 */
public class ExibirInformarExpurgoPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("informarExpurgoPagamento");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		InformarExpurgoPagamentoActionForm informarExpurgoPagamentoActionForm = (InformarExpurgoPagamentoActionForm) actionForm;
		
		String limpar = httpServletRequest
				.getParameter("limpar");

		if (limpar != null) {
			if(limpar.equals("cliente")){
				informarExpurgoPagamentoActionForm.setIdCliente("");
				informarExpurgoPagamentoActionForm.setNomeCliente("");	
			}
			if(limpar.equals("dataPagamento")){
				informarExpurgoPagamentoActionForm.setDataPagamento("");	
			}
			
			if(limpar.equals("sim")){
				informarExpurgoPagamentoActionForm.setIdCliente("");
				informarExpurgoPagamentoActionForm.setNomeCliente("");	
				informarExpurgoPagamentoActionForm.setDataPagamento("");	
				informarExpurgoPagamentoActionForm.setMesAnoReferencia("");	
			}
			
			informarExpurgoPagamentoActionForm.setQuantidadePagamentosExpurgados("");
			informarExpurgoPagamentoActionForm.setQuantidadePagamentosNaoExpurgados("");
			
			sessao.removeAttribute("colecaoExpurgado");
			sessao.removeAttribute("colecaoNaoExpurgado");
		}

		String botaoConsultar = httpServletRequest
				.getParameter("botaoConsultar");

		if (botaoConsultar != null) {
			
			Integer anoMesArrecadacao = Util.formatarMesAnoComBarraParaAnoMes(informarExpurgoPagamentoActionForm.getMesAnoReferencia());

			Object[] colecaoDadosPagamento = fachada
					.gerarColecaoDadosPagamentoPelaData(informarExpurgoPagamentoActionForm
							.getDataPagamento(),Util.converterStringParaInteger(informarExpurgoPagamentoActionForm.getIdCliente()),anoMesArrecadacao);

			Collection colecaoExpurgado = new ArrayList();

			Collection colecaoNaoExpurgado = new ArrayList();
			

			if (colecaoDadosPagamento != null
					&& !colecaoDadosPagamento.equals("")) {
				
				colecaoExpurgado = (Collection)colecaoDadosPagamento[0];
				
				colecaoNaoExpurgado = (Collection)colecaoDadosPagamento[1];

			}

			informarExpurgoPagamentoActionForm
					.setQuantidadePagamentosExpurgados(""+colecaoExpurgado.size());
			
			informarExpurgoPagamentoActionForm
			.setQuantidadePagamentosNaoExpurgados(""+colecaoNaoExpurgado.size());
			
			sessao.setAttribute("colecaoExpurgado",colecaoExpurgado);
			sessao.setAttribute("colecaoNaoExpurgado",colecaoNaoExpurgado);

		}

		String idCliente = informarExpurgoPagamentoActionForm.getIdCliente();
		String nomeCliente = informarExpurgoPagamentoActionForm
				.getNomeCliente();

		if (idCliente != null && !idCliente.equals("")
				&& (nomeCliente == null || nomeCliente.equals(""))) {

			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection<Cliente> colecaoCliente = fachada.pesquisar(
					filtroCliente, Cliente.class.getName());
			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
				Cliente cliente = (Cliente) Util
						.retonarObjetoDeColecao(colecaoCliente);
				informarExpurgoPagamentoActionForm.setNomeCliente(cliente
						.getNome());
			} else {
				informarExpurgoPagamentoActionForm.setNomeCliente("");
				informarExpurgoPagamentoActionForm
						.setNomeCliente("CLIENTE INEXISTENTE");
				httpServletRequest.setAttribute("clienteInexistente", "sim");
			}
		}

		return retorno;
	}
}