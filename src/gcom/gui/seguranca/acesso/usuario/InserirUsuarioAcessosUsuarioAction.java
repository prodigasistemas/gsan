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
 * @author Administrador
 * @date 02/05/2006
 */
public class InserirUsuarioAcessosUsuarioAction extends GcomAction {

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

		InserirUsuarioDadosGeraisActionForm form = (InserirUsuarioDadosGeraisActionForm) actionForm;

		ActionForward retorno = actionMapping
				.findForward("gerenciadorProcesso");

		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Usuario que vai ser cadastrado no sistema, usado só nessa
		// funcionalidade
		Usuario usuarioCadastrar = (Usuario) sessao
				.getAttribute("usuarioCadastrar");
		if (usuarioCadastrar == null)
			usuarioCadastrar = new Usuario();

		if (!"".equals(form.getAbrangencia())) {
			if (!(usuarioCadastrar.getUsuarioAbrangencia() != null
					&& usuarioCadastrar.getUsuarioAbrangencia().getId() != null && usuarioCadastrar
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
					usuarioCadastrar
							.setUsuarioAbrangencia((UsuarioAbrangencia) coll
									.iterator().next());
				}
			}
		} else {
			usuarioCadastrar.setUsuarioAbrangencia(null);
		}

		if (!"".equals(form.getGerenciaRegional())) {
			if (!(usuarioCadastrar.getGerenciaRegional() != null
					&& usuarioCadastrar.getGerenciaRegional().getId() != null && usuarioCadastrar
					.getGerenciaRegional().getId().toString().equals(
							form.getGerenciaRegional()))) {

				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
				filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
						FiltroGerenciaRegional.ID, form.getGerenciaRegional()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroGerenciaRegional,
						GerenciaRegional.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioCadastrar
							.setGerenciaRegional((GerenciaRegional) coll
									.iterator().next());
				}
			}
		} else {
			usuarioCadastrar.setGerenciaRegional(null);
		}

		if (!"".equals(form.getUnidadeNegocio())) {
			if (!(usuarioCadastrar.getUnidadeNegocio() != null
					&& usuarioCadastrar.getUnidadeNegocio().getId() != null && usuarioCadastrar
					.getUnidadeNegocio().getId().toString().equals(
							form.getUnidadeNegocio()))) {

				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
				filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
						FiltroUnidadeNegocio.ID, form.getUnidadeNegocio()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroUnidadeNegocio,
						UnidadeNegocio.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioCadastrar.setUnidadeNegocio((UnidadeNegocio) coll
							.iterator().next());
				}
			}
		} else {
			usuarioCadastrar.setUnidadeNegocio(null);
		}

		if (!"".equals(form.getIdElo())) {
			if (!(usuarioCadastrar.getLocalidadeElo() != null
					&& usuarioCadastrar.getLocalidadeElo().getId() != null && usuarioCadastrar
					.getLocalidadeElo().getId().toString().equals(
							form.getIdElo()))) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, form.getIdElo()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroLocalidade, Localidade.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioCadastrar.setLocalidadeElo((Localidade) coll
							.iterator().next());
				}
			}
		} else {
			usuarioCadastrar.setLocalidadeElo(null);
		}

		if (!"".equals(form.getIdLocalidade())) {
			if (!(usuarioCadastrar.getLocalidade() != null
					&& usuarioCadastrar.getLocalidade().getId() != null && usuarioCadastrar
					.getLocalidade().getId().toString().equals(
							form.getIdLocalidade()))) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, form.getIdLocalidade()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroLocalidade, Localidade.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					usuarioCadastrar.setLocalidade((Localidade) coll.iterator()
							.next());
				}
			}
		} else {
			usuarioCadastrar.setLocalidade(null);
		}

		String[] grupo = form.getGrupo();

		sessao.setAttribute("grupo", grupo);
		sessao.setAttribute("usuario", usuario);
		sessao.setAttribute("usuarioCadastrar", usuarioCadastrar);

		return retorno;
	}
}