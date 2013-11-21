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

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
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
 * Action que exibe o menu
 * 
 * @author Sávio Luiz
 * @date 02/05/2006
 */
public class AtualizarUsuarioAcessosUsuarioAction extends GcomAction {

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

		AtualizarUsuarioDadosGeraisActionForm form = (AtualizarUsuarioDadosGeraisActionForm) actionForm;

		ActionForward retorno = actionMapping
				.findForward("gerenciadorProcesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario que vai ser cadastrado no sistema, usado só nessa
		// funcionalidade
		Usuario usuarioParaAtualizar = (Usuario) sessao
				.getAttribute("usuarioParaAtualizar");
		if (usuarioParaAtualizar == null)
			usuarioParaAtualizar = new Usuario();

		if (!"".equals(form.getAbrangencia())) {
			if (!(usuarioParaAtualizar.getUsuarioAbrangencia() != null
					&& usuarioParaAtualizar.getUsuarioAbrangencia().getId() != null && usuarioParaAtualizar
					.getUsuarioAbrangencia().getId().toString().equals(
							form.getAbrangencia()))) {

				FiltroUsuarioAbrangencia filtroUsuarioAbrangencia = new FiltroUsuarioAbrangencia();
				filtroUsuarioAbrangencia
						.adicionarParametro(new ParametroSimples(
								FiltroUsuarioAbrangencia.ID, form
										.getAbrangencia()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroUsuarioAbrangencia,
						UsuarioAbrangencia.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioParaAtualizar
							.setUsuarioAbrangencia((UsuarioAbrangencia) coll
									.iterator().next());
				}
			}
		} else {
			usuarioParaAtualizar.setUsuarioAbrangencia(null);
		}

		if (!"".equals(form.getGerenciaRegional())) {
			if (!(usuarioParaAtualizar.getGerenciaRegional() != null
					&& usuarioParaAtualizar.getGerenciaRegional().getId() != null && usuarioParaAtualizar
					.getGerenciaRegional().getId().toString().equals(
							form.getGerenciaRegional()))) {

				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
						FiltroGerenciaRegional.ID, form.getGerenciaRegional()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroGerenciaRegional,
						GerenciaRegional.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioParaAtualizar
							.setGerenciaRegional((GerenciaRegional) coll
									.iterator().next());
				}
			}
		} else {
			usuarioParaAtualizar.setGerenciaRegional(null);
		}

		if (!"".equals(form.getUnidadeNegocio())) {
			if (!(usuarioParaAtualizar.getUnidadeNegocio() != null
					&& usuarioParaAtualizar.getUnidadeNegocio().getId() != null && usuarioParaAtualizar
					.getUnidadeNegocio().getId().toString().equals(
							form.getUnidadeNegocio()))) {

				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
						FiltroUnidadeNegocio.ID, form.getUnidadeNegocio()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroUnidadeNegocio,
						UnidadeNegocio.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioParaAtualizar.setUnidadeNegocio((UnidadeNegocio) coll
							.iterator().next());
				}
			}
		} else {
			usuarioParaAtualizar.setUnidadeNegocio(null);
		}

		if (!"".equals(form.getIdElo())) {
			if (!(usuarioParaAtualizar.getLocalidadeElo() != null
					&& usuarioParaAtualizar.getLocalidadeElo().getId() != null && usuarioParaAtualizar
					.getLocalidadeElo().getId().toString().equals(
							form.getIdElo()))) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, form.getIdElo()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroLocalidade, Localidade.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioParaAtualizar.setLocalidadeElo((Localidade) coll
							.iterator().next());
				}
			}
		} else {
			usuarioParaAtualizar.setLocalidadeElo(null);
		}

		if (!"".equals(form.getIdLocalidade())) {
			if (!(usuarioParaAtualizar.getLocalidade() != null
					&& usuarioParaAtualizar.getLocalidade().getId() != null && usuarioParaAtualizar
					.getLocalidade().getId().toString().equals(
							form.getIdLocalidade()))) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, form.getIdLocalidade()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroLocalidade, Localidade.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioParaAtualizar.setLocalidade((Localidade) coll
							.iterator().next());
				}
			}
		} else {
			usuarioParaAtualizar.setLocalidade(null);
		}
		
		if (form.getSituacao() != null && !form.getSituacao().equalsIgnoreCase("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			UsuarioSituacao usuarioSituacao = new UsuarioSituacao();
			usuarioSituacao.setId(new Integer(form.getSituacao()));
			
			usuarioParaAtualizar.setUsuarioSituacao(usuarioSituacao);
		} else {
			usuarioParaAtualizar.setUsuarioSituacao(null);
		}

		String[] grupo = form.getGrupo();

		sessao.setAttribute("grupo", grupo);
		sessao.setAttribute("usuarioParaAtualizar", usuarioParaAtualizar);

		return retorno;
	}
}