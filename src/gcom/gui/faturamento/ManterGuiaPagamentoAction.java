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
package gcom.gui.faturamento;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelCobrancaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ManterGuiaPagamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection guiasPagamento = (Collection) sessao
				.getAttribute("guiasPagamentos");

		ManterGuiaPagamentoActionForm manterGuiaPagamentoActionForm = (ManterGuiaPagamentoActionForm) actionForm;

		String[] registrosRemocao = manterGuiaPagamentoActionForm
				.getIdRegistrosRemocao();
		String idImovel = manterGuiaPagamentoActionForm.getIdImovel();
		String idCliente = manterGuiaPagamentoActionForm.getCodigoCliente();

		GuiaPagamento guiaPagamento = new GuiaPagamento();

		Cliente cliente = new Cliente();

		if (idCliente != null && !idCliente.equals("")) {
			cliente.setId(new Integer(idCliente));

		}

		guiaPagamento.setCliente(cliente);

		Imovel imovel = new Imovel();

		ImovelCobrancaSituacao imovelCobrancaSituacao = null;

		if (idImovel != null && !idImovel.equals("")) {
			imovel.setId(new Integer(idImovel));
			imovelCobrancaSituacao = (ImovelCobrancaSituacao) sessao
					.getAttribute("imovelCobrancaSituacao");
		}

		guiaPagamento.setImovel(imovel);

        /** alterado por pedro alexandre dia 20/11/2006 
         * Recupera o usuário logado para passar no metódo de inserir guia de pagamento 
         * para verificar se o usuário tem abrangência para inserir a guia de pagamento
         * para o imóvel caso a guia de pagamentoseja para o imóvel.
         */
        Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        guiaPagamento.setUsuario(usuarioLogado);
        fachada.manterGuiaPagamento(guiaPagamento, guiasPagamento,registrosRemocao, imovelCobrancaSituacao,usuarioLogado);
		//fachada.manterGuiaPagamento(guiaPagamento, guiasPagamento,registrosRemocao, imovelCobrancaSituacao);

		sessao.removeAttribute("imovelCobrancaSituacao");
		sessao.removeAttribute("guiasPagamentos");
		sessao.removeAttribute("ManterGuiaPagamentoActionForm");

		if (idImovel != null && !idImovel.equals("")) {

			montarPaginaSucesso(httpServletRequest, registrosRemocao.length
					+ " Guia(s) de Pagamento do imóvel " + idImovel
					+ " cancelada(s) com sucesso.",
					"Realizar outro Cancelamento de Guia de Pagamento",
					"exibirManterGuiaPagamentoAction.do?menu=sim");

		}

		if (idCliente != null && !idCliente.equals("")) {

			montarPaginaSucesso(httpServletRequest, registrosRemocao.length
					+ " Guia(s) de Pagamento do cliente " + idCliente
					+ " cancelada(s) com sucesso.",
					"Realizar outro Cancelamento de Guia de Pagamento",
					"exibirManterGuiaPagamentoAction.do?menu=sim");

		}

		return retorno;
	}
}
