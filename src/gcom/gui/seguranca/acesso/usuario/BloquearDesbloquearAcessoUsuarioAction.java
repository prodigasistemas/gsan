/**
 * 
 */
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
package gcom.gui.seguranca.acesso.usuario;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 08/06/2006
 */
public class BloquearDesbloquearAcessoUsuarioAction extends GcomAction {

	/**
	 * Este caso de uso permite bloquear ou desbloquear o acesso do usuário ao
	 * sistema.
	 * 
	 * [UC0291] Bloquear/Desbloquear Acesso Usuário
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 08/06/2006
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		BloquearDesbloquearAcessoUsuarioActionForm bloquearDesbloquearAcessoUsuarioActionForm = (BloquearDesbloquearAcessoUsuarioActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Usuario usuario = new Usuario();

		/*
		 * [UC0107] Registrar Transação
		 * 
		 */

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_ACESSO_USUARIO_ATUALIZAR,
				new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_ACESSO_USUARIO_ATUALIZAR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);

		usuario.setOperacaoEfetuada(operacaoEfetuada);
		usuario.adicionarUsuario(Usuario.USUARIO_TESTE,
				UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);

		// [UC0107] -Fim- Registrar Transação
		
		
		
		String login = bloquearDesbloquearAcessoUsuarioActionForm.getLogin();

		if (login != null && !login.equals("")) {

			FiltroUsuario filtroUsuario = new FiltroUsuario();
			filtroUsuario.adicionarParametro(new ComparacaoTexto(
					FiltroUsuario.LOGIN, login));

			Collection colecaoUsuario = fachada.pesquisar(filtroUsuario,
					Usuario.class.getName());

			if (colecaoUsuario == null || colecaoUsuario.isEmpty()) {
				throw new ActionServletException("atencao.login_nao_existente",
						null, "" + login + "");
			}
			Iterator colecaoUsuarioIterator = colecaoUsuario.iterator();
			
			while (colecaoUsuarioIterator.hasNext() ) {
				usuario = (Usuario) colecaoUsuarioIterator.next();
				
				if ( usuario.getLogin().equalsIgnoreCase(login) ){
					break;
				}
			}
			}

		String idSituacaoUsuario = bloquearDesbloquearAcessoUsuarioActionForm
				.getUsuarioSituacao();

		if (idSituacaoUsuario != null
				&& !idSituacaoUsuario.trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {

			FiltroUsuarioSituacao filtroUsuarioSituacao = new FiltroUsuarioSituacao();
			filtroUsuarioSituacao.adicionarParametro(new ParametroSimples(
					FiltroUsuarioSituacao.ID, idSituacaoUsuario));

			Collection colecaoUsarioSituacao = fachada.pesquisar(
					filtroUsuarioSituacao, UsuarioSituacao.class.getName());

			UsuarioSituacao usuarioSituacao = (UsuarioSituacao) colecaoUsarioSituacao
					.iterator().next();

			if (usuario.getUsuarioSituacao().getId().equals(idSituacaoUsuario)) {
				throw new ActionServletException("atencao.login_nao_existente",
						null, "" + login + "");
			}
		
				if(usuario.getLogin().equalsIgnoreCase(login)) {
					usuario.setUsuarioSituacao(usuarioSituacao);
				}
		}

		registradorOperacao.registrarOperacao(usuario);
		
		fachada.bloquearDesbloquearUsuarioSituacao(usuario);

		

		// Monta a Pagina de sucesso

		montarPaginaSucesso(httpServletRequest, usuario.getUsuarioSituacao()
				.getDescricaoUsuarioSituacao()
				+ " " + "efetuada com sucesso.",
				"Bloquear/Desbloquear acesso de outro usuário",
				"exibirBloquearDesbloquearAcessoUsuarioAction.do?menu=sim");

		return retorno;
	}

}
