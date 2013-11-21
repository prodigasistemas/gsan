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
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroPemissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição das permissões especiais do usuário.
 * 
 * @author Sávio Luiz
 * @date 12/07/2006
 */
public class ExibirControlarPermissoesEspeciaisUsuarioAction extends GcomAction {

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
				.findForward("controlarPermissoesEspeciaisUsuario");

		ControlarAcessoUsuarioActionForm controlarAcessoUsuarioActionForm = (ControlarAcessoUsuarioActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Recupera os acessos do grupo da sessão
		//Collection grupoFuncionalidades = (Collection) sessao
		//		.getAttribute("grupoFuncionalidades");

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Usuario que vai ser cadastrado no sistema, usado só nessa
		// funcionalidade
		Usuario usuarioParaAtualizar = (Usuario) sessao
				.getAttribute("usuarioParaAtualizar");

		Collection colecaoPermissaoEspecial = null;

		Collection colecaoPermissaoEspecialDesalibitado = null;
		
		String permissoesCheckBoxVazias = controlarAcessoUsuarioActionForm
		.getPermissoesCheckBoxVazias(); 

		String[] permissaoEspecial = controlarAcessoUsuarioActionForm
				.getPermissoesEspeciais();

		if (permissaoEspecial == null && permissoesCheckBoxVazias == null) {

			// caso o usuário que esteja efetuando a inserção não
			// seja
			// do grupo de administradores
			FiltroUsuarioGrupo filtroUsuarioGrupo = new FiltroUsuarioGrupo();

			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.USUARIO_ID, usuario.getId()));
			filtroUsuarioGrupo.adicionarParametro(new ParametroSimples(
					FiltroUsuarioGrupo.GRUPO_ID, Grupo.ADMINISTRADOR));
			Collection colecaoUsuarioGrupo = Fachada.getInstancia().pesquisar(
					filtroUsuarioGrupo, UsuarioGrupo.class.getName());
			if (colecaoUsuarioGrupo != null && !colecaoUsuarioGrupo.isEmpty()) {

				FiltroPemissaoEspecial filtroPemissaoEspecial = new FiltroPemissaoEspecial(FiltroPemissaoEspecial.DESCRICAO);
				filtroPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroPemissaoEspecial.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO)); 

				colecaoPermissaoEspecial = Fachada.getInstancia().pesquisar(
						filtroPemissaoEspecial,
						PermissaoEspecial.class.getName());

				FiltroUsuarioPemissaoEspecial filtroUsuarioPermissaoEspecialAtualizar = new FiltroUsuarioPemissaoEspecial();
				filtroUsuarioPermissaoEspecialAtualizar
						.adicionarCaminhoParaCarregamentoEntidade("permissaoEspecial");
				filtroUsuarioPermissaoEspecialAtualizar
						.adicionarParametro(new ParametroSimples(
								FiltroUsuarioPemissaoEspecial.USUARIO_COMP_ID,
								usuarioParaAtualizar.getId()));
				// colecao de usuário permissão especial
				Collection colecaoUsuarioPermissaoEspecialParaAtualizar = Fachada
						.getInstancia().pesquisar(
								filtroUsuarioPermissaoEspecialAtualizar,
								UsuarioPermissaoEspecial.class.getName());
				if (colecaoUsuarioPermissaoEspecialParaAtualizar != null
						&& !colecaoUsuarioPermissaoEspecialParaAtualizar
								.isEmpty()) {
					
					Collection colecaoPermissaoEspecialAux = new ArrayList();
					
					Iterator ite = colecaoUsuarioPermissaoEspecialParaAtualizar.iterator();
					while(ite.hasNext()){
						UsuarioPermissaoEspecial usuarioPermissaoEspecial = (UsuarioPermissaoEspecial)ite.next();
						colecaoPermissaoEspecialAux.add(usuarioPermissaoEspecial.getPermissaoEspecial());		
					}
					// seta os campos de permissão especial no form para
					// aparecer no
					// jsp como checado
					permissaoEspecial = Fachada
							.getInstancia()
							.retornarPermissoesMarcadas(
									colecaoPermissaoEspecialAux);

				}
			} else {
				Object[] permissoesEspeciais = Fachada.getInstancia()
						.pesquisarPermissoesEspeciaisUsuarioEUsuarioLogado(
								usuarioParaAtualizar, usuario);
				colecaoPermissaoEspecial = (Collection) permissoesEspeciais[0];
				colecaoPermissaoEspecialDesalibitado = (Collection) permissoesEspeciais[1];
				permissaoEspecial = (String[]) permissoesEspeciais[2];
				/*
				 * // caso o usuário não seja do grupos de administradores
				 * FiltroUsuarioPemissaoEspecial
				 * filtroUsuarioPemissaoEspecialLogado = new
				 * FiltroUsuarioPemissaoEspecial();
				 * filtroUsuarioPemissaoEspecialLogado .adicionarParametro(new
				 * ParametroSimples(
				 * FiltroUsuarioPemissaoEspecial.USUARIO_COMP_ID,
				 * usuario.getId())); filtroUsuarioPemissaoEspecialLogado
				 * .adicionarCaminhoParaCarregamentoEntidade("permissaoEspecial"); //
				 * recupera as permissões do usuario logado Collection
				 * colecaoUsuarioLogadoPermissaoEspecial =
				 * Fachada.getInstancia()
				 * .pesquisar(filtroUsuarioPemissaoEspecialLogado,
				 * UsuarioPermissaoEspecial.class.getName());
				 * 
				 * FiltroUsuarioPemissaoEspecial filtroUsuarioPermissaoEspecial =
				 * new FiltroUsuarioPemissaoEspecial();
				 * filtroUsuarioPermissaoEspecial .adicionarParametro(new
				 * ParametroSimples(
				 * FiltroUsuarioPemissaoEspecial.USUARIO_COMP_ID,
				 * usuarioParaAtualizar.getId()));
				 * filtroUsuarioPermissaoEspecial
				 * .adicionarCaminhoParaCarregamentoEntidade("usuario");
				 * filtroUsuarioPemissaoEspecialLogado
				 * .adicionarCaminhoParaCarregamentoEntidade("permissaoEspecial"); //
				 * colecao de usuário permissão especial Collection
				 * colecaoUsuarioPermissaoEspecial =
				 * Fachada.getInstancia().pesquisar(
				 * filtroUsuarioPermissaoEspecial,
				 * UsuarioPermissaoEspecial.class.getName()); if
				 * (colecaoUsuarioPermissaoEspecial != null &&
				 * !colecaoUsuarioPermissaoEspecial.isEmpty()) { // remove os
				 * usuario permissão especial da coleção de // usuário // logado
				 * com permissão especial que tenha na coleção de // usuário //
				 * permissão usuário que está sendo atualizado Iterator
				 * iteratorUsuarioPermissaoEspecialLogado =
				 * colecaoUsuarioLogadoPermissaoEspecial.iterator(); // seta os
				 * campos de permissão especial no form para // aparecer no jsp
				 * como checado Iterator iteratorUsuarioPermissaoEspecial =
				 * colecaoPermissaoEspecial .iterator(); int i = 0;
				 * permissaoEspecial = new String[colecaoPermissaoEspecial
				 * .size()];
				 * 
				 * while (iteratorUsuarioPermissaoEspecial.hasNext()) {
				 * UsuarioPermissaoEspecial usuarioPermissaoEspecialObject =
				 * (UsuarioPermissaoEspecial) iteratorUsuarioPermissaoEspecial
				 * .next(); permissaoEspecial[i] = "" +
				 * usuarioPermissaoEspecialObject.getComp_id()
				 * .getPermissaoEspecialId(); } }
				 */
			}
			// seta os campos que vão aparecer como checado
			controlarAcessoUsuarioActionForm
					.setPermissoesEspeciais(permissaoEspecial);
			sessao.setAttribute("colecaoPermissaoEspecial",
					colecaoPermissaoEspecial);
			sessao.setAttribute("colecaoPermissaoEspecialDesalibitado",
					colecaoPermissaoEspecialDesalibitado);
		}

		return retorno;
	}
}