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

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rafael Corrêa
 * @since 22/08/2008
 */
public class ExibirConsultarTransferenciasDebitoAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirConsultarTransferenciasDebito");
		
		ConsultarTransferenciasDebitoActionForm consultarTransferenciasDebitoActionForm = (ConsultarTransferenciasDebitoActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		sessao.removeAttribute("colecaoContasTransferidas");
		sessao.removeAttribute("colecaoDebitosACobrarTransferidos");
		sessao.removeAttribute("colecaoGuiasPagamentoTransferidas");
		sessao.removeAttribute("colecaoCreditosARealizarTransferidos");
		sessao.removeAttribute("consultarTransferenciasDebitoHelper");
		
		String idImovelOrigem = consultarTransferenciasDebitoActionForm.getIdImovelOrigem();
		String idImovelDestino = consultarTransferenciasDebitoActionForm.getIdImovelDestino();
		String idUsuario = consultarTransferenciasDebitoActionForm.getIdUsuario();
		String loginUsuario = consultarTransferenciasDebitoActionForm.getLoginUsuario();
		
		// Pesquisa Imóvel Origem
		if (idImovelOrigem != null && !idImovelOrigem.trim().equals("")) {
			
			String inscricao = fachada.pesquisarInscricaoImovel(new Integer(idImovelOrigem));
			
			if (inscricao != null && !inscricao.trim().equals("")) {
				consultarTransferenciasDebitoActionForm.setInscricaoImovelOrigem(inscricao);
				httpServletRequest.setAttribute("nomeCampo", "idImovelDestino");
			} else {
				consultarTransferenciasDebitoActionForm.setIdImovelOrigem("");
				consultarTransferenciasDebitoActionForm.setInscricaoImovelOrigem("IMÓVEL INEXISTENTE");
				
				httpServletRequest.setAttribute("imovelOrigemInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idImovelOrigem");
			}
			
		} else {
			consultarTransferenciasDebitoActionForm.setInscricaoImovelOrigem("");
		}
		
		// Pesquisa Imóvel Destino
		if (idImovelDestino != null && !idImovelDestino.trim().equals("")) {
			
			String inscricao = fachada.pesquisarInscricaoImovel(new Integer(idImovelDestino));
			
			if (inscricao != null && !inscricao.trim().equals("")) {
				consultarTransferenciasDebitoActionForm.setInscricaoImovelDestino(inscricao);
				httpServletRequest.setAttribute("nomeCampo", "dataInicio");
			} else {
				consultarTransferenciasDebitoActionForm.setIdImovelDestino("");
				consultarTransferenciasDebitoActionForm.setInscricaoImovelDestino("IMÓVEL INEXISTENTE");
				
				httpServletRequest.setAttribute("imovelDestinoInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idImovelDestino");
			}
			
		} else {
			consultarTransferenciasDebitoActionForm.setInscricaoImovelDestino("");
		}
		
		// Pesquisa o usuário
		if ((loginUsuario != null && !loginUsuario.trim().equals("")) || (idUsuario != null && !idUsuario.trim().equals(""))) {
			
			FiltroUsuario filtroUsuario = new FiltroUsuario();
			
			// Verifica se a pesquisa foi pelo enter
			if ((loginUsuario != null && !loginUsuario.trim().equals(""))) {
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.LOGIN, loginUsuario));
			} 
			// Verifica se a pesquisa foi feita pela lupa
			else {
				filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, idUsuario));
			}
			
			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
			
			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
				Usuario usuario = (Usuario) Util.retonarObjetoDeColecao(colecaoUsuario);
				consultarTransferenciasDebitoActionForm.setIdUsuario(usuario.getId().toString());
				consultarTransferenciasDebitoActionForm.setLoginUsuario(usuario.getLogin());
				consultarTransferenciasDebitoActionForm.setNomeUsuario(usuario.getNomeUsuario());
			} else {
				consultarTransferenciasDebitoActionForm.setIdUsuario("");
				consultarTransferenciasDebitoActionForm.setLoginUsuario("");
				consultarTransferenciasDebitoActionForm.setNomeUsuario("USUÁRIO INEXISTENTE");
				
				httpServletRequest.setAttribute("usuarioInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "loginUsuario");
			}
			
		} else {
			consultarTransferenciasDebitoActionForm.setNomeUsuario("");
		}

		return retorno;

	}

}
