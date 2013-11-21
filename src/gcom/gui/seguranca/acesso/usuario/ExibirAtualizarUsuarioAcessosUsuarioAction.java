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
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.FiltroUsuarioSituacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAbrangencia;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
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
 * Action que exibe o menu
 * 
 * @author Sávio Luiz
 * @date 02/05/2006
 */
public class ExibirAtualizarUsuarioAcessosUsuarioAction extends GcomAction {

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
				.findForward("atualizarUsuarioAcessoUsuario");

		AtualizarUsuarioDadosGeraisActionForm form = (AtualizarUsuarioDadosGeraisActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		// Usuario que vai ser cadastrado no sistema, usado só nessa
		// funcionalidade
		Usuario usuarioParaAtualizar = (Usuario) sessao
				.getAttribute("usuarioParaAtualizar");

		String[] grupo = (String[]) sessao.getAttribute("grupo");

		String onLoad = httpServletRequest.getParameter("onLoad");

		if (onLoad != null && !onLoad.equals("")) {
			if (form.getIdElo() != null
					&& !form.getIdElo().equals("")
					&& (usuarioParaAtualizar.getLocalidadeElo() == null || (usuarioParaAtualizar
							.getLocalidadeElo() != null
							&& usuarioParaAtualizar.getLocalidadeElo().getId() != null && !form
							.getIdElo().equals(
									usuarioParaAtualizar.getLocalidadeElo()
											.getId().toString())))) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, form.getIdElo()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroLocalidade, Localidade.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					Localidade l = (Localidade) coll.iterator().next();
					if (l.getLocalidade() != null
							&& !l.getId().equals(l.getLocalidade().getId())) {
						throw new ActionServletException(
								"atencao.localidade.nao.elo");
					}
					usuarioParaAtualizar.setLocalidadeElo(l);
					form.setIdElo(l.getId().toString());
					form.setNomeElo(l.getDescricao());
					form.setEloNaoEncontrada("false");
				} else {
					usuarioParaAtualizar.setLocalidadeElo(null);
					form.setIdElo("");
					form.setNomeElo("Elo inexistente.");
					form.setEloNaoEncontrada("true");
				}
			} else {
				if (form.getIdElo() != null && !form.getIdElo().equals("")) {
					form.setIdElo(usuarioParaAtualizar.getLocalidadeElo()
							.getId().toString());
					form.setNomeElo(usuarioParaAtualizar.getLocalidadeElo()
							.getDescricao());
					form.setEloNaoEncontrada("false");
				}
			}

			if (form.getIdLocalidade() != null
					&& !form.getIdLocalidade().equals("")
					&& (usuarioParaAtualizar.getLocalidade() == null || (usuarioParaAtualizar
							.getLocalidade() != null
							&& usuarioParaAtualizar.getLocalidade().getId() != null && !form
							.getIdLocalidade().equals(
									usuarioParaAtualizar.getLocalidade()
											.getId().toString())))) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, form.getIdLocalidade()));
				Collection coll = Fachada.getInstancia().pesquisar(
						filtroLocalidade, Localidade.class.getSimpleName());
				if (coll != null && !coll.isEmpty()) {
					Localidade l = (Localidade) coll.iterator().next();
					usuarioParaAtualizar.setLocalidade(l);
					form.setIdLocalidade(l.getId().toString());
					form.setNomeLocalidade(l.getDescricao());
					form.setLocalidadeNaoEncontrada("false");
				} else {
					usuarioParaAtualizar.setLocalidade(null);
					form.setIdLocalidade("");
					form.setNomeLocalidade(" Localidade inexistente.");
					form.setLocalidadeNaoEncontrada("true");
				}
			} else {
				if (form.getIdLocalidade() != null
						&& !form.getIdLocalidade().equals("")) {
					form.setLocalidadeNaoEncontrada("false");
					form.setIdLocalidade(""
							+ usuarioParaAtualizar.getLocalidade().getId());
					form.setNomeLocalidade(usuarioParaAtualizar.getLocalidade()
							.getDescricao());
				}
			}
		} else {

			if (usuarioParaAtualizar != null) {

				if (grupo != null) {
					form.setGrupo(grupo);
				}

				if (usuarioParaAtualizar.getUsuarioAbrangencia() != null
						&& usuarioParaAtualizar.getUsuarioAbrangencia().getId() != null)
					form.setAbrangencia(usuarioParaAtualizar
							.getUsuarioAbrangencia().getId().toString());
				if (usuarioParaAtualizar.getGerenciaRegional() != null
						&& usuarioParaAtualizar.getGerenciaRegional().getId() != null)
					form.setGerenciaRegional(usuarioParaAtualizar
							.getGerenciaRegional().getId().toString());

				if (usuarioParaAtualizar.getUnidadeNegocio() != null
						&& usuarioParaAtualizar.getUnidadeNegocio().getId() != null)
					form.setUnidadeNegocio(usuarioParaAtualizar
							.getUnidadeNegocio().getId().toString());

				if ("".equals(form.getIdElo())) {
					if (usuarioParaAtualizar.getLocalidadeElo() != null) {
						if (usuarioParaAtualizar.getLocalidadeElo().getId() != null)
							form.setIdElo(usuarioParaAtualizar
									.getLocalidadeElo().getId().toString());
						if (usuarioParaAtualizar.getLocalidadeElo()
								.getDescricao() != null)
							form.setNomeElo(usuarioParaAtualizar
									.getLocalidadeElo().getDescricao());
					}
				}

				if ("".equals(form.getIdLocalidade())) {
					if (usuarioParaAtualizar.getLocalidade() != null) {
						if (usuarioParaAtualizar.getLocalidade().getId() != null)
							form.setIdLocalidade(usuarioParaAtualizar
									.getLocalidade().getId().toString());
						if (usuarioParaAtualizar.getLocalidade().getDescricao() != null)
							form.setNomeLocalidade(usuarioParaAtualizar
									.getLocalidade().getDescricao());
					}
				}
			}
		}

		Collection colecaoUsuarioAbrangencia = null;

		if (sessao.getAttribute("collUsuarioAbrangencia") == null
				|| sessao.getAttribute("collUsuarioAbrangencia").equals("")) {

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

				colecaoUsuarioAbrangencia = Fachada.getInstancia().pesquisar(
						new FiltroUsuarioAbrangencia(),
						UsuarioAbrangencia.class.getSimpleName());
			} else {
				Integer idUsuarioAbrangencia = usuario.getUsuarioAbrangencia()
						.getId();
				FiltroUsuarioAbrangencia filtroUsuarioAbrangencia = new FiltroUsuarioAbrangencia();
				filtroUsuarioAbrangencia
						.adicionarParametro(new ParametroSimples(
								FiltroUsuarioAbrangencia.ID,
								idUsuarioAbrangencia));
				colecaoUsuarioAbrangencia = Fachada.getInstancia().pesquisar(
						filtroUsuarioAbrangencia,
						UsuarioAbrangencia.class.getName());
				// caso exista mais de uma abrangência na pesquisa passando o id
				// da
				// abrangência como superior
				boolean mesmoNivel = true;
				// cria um boolean para verificar se existe o ususário
				// abrangencia
				// que está sendo atualizado na coleção de usuarios abrangencia
				// permitido para o usuário logado do usuário
				boolean achouUsuarioAbrangencia = false;
				Collection usuarioAbrangenciaParaPesquisa = new ArrayList();
				Iterator iteratorUsuarioAbrangencia = colecaoUsuarioAbrangencia
						.iterator();
				while (iteratorUsuarioAbrangencia.hasNext()) {
					UsuarioAbrangencia usuarioAbrangencia = (UsuarioAbrangencia) iteratorUsuarioAbrangencia
							.next();
					if (usuarioParaAtualizar.getUsuarioAbrangencia() != null
							&& usuarioAbrangencia.getId().equals(
									usuarioParaAtualizar
											.getUsuarioAbrangencia().getId())) {
						achouUsuarioAbrangencia = true;
					}
				}
				usuarioAbrangenciaParaPesquisa
						.addAll(colecaoUsuarioAbrangencia);
				// enquanto exista mais usuário abrangência(s)
				while (mesmoNivel) {
					Collection colecaoUsuarioAbrangenciaPorSuperior = Fachada
							.getInstancia()
							.pesquisarUsuarioAbrangenciaPorSuperior(
									usuarioAbrangenciaParaPesquisa,
									idUsuarioAbrangencia);
					if (colecaoUsuarioAbrangenciaPorSuperior != null
							&& !colecaoUsuarioAbrangenciaPorSuperior.isEmpty()) {
						Iterator iteratorUsuarioAbrangenciaSuperior = colecaoUsuarioAbrangenciaPorSuperior
								.iterator();
						// verifica se existe na coleção de usuarios
						// abrangências o
						// usuario abrangência do usuario atualizar
						while (iteratorUsuarioAbrangenciaSuperior.hasNext()) {
							UsuarioAbrangencia usuarioAbrangencia = (UsuarioAbrangencia) iteratorUsuarioAbrangenciaSuperior
									.next();
							if (usuarioParaAtualizar.getUsuarioAbrangencia() != null
									&& usuarioAbrangencia.getId().equals(
											usuarioParaAtualizar
													.getUsuarioAbrangencia()
													.getId())) {
								achouUsuarioAbrangencia = true;
							}
						}

						colecaoUsuarioAbrangencia
								.addAll(colecaoUsuarioAbrangenciaPorSuperior);
						usuarioAbrangenciaParaPesquisa = new ArrayList();
						usuarioAbrangenciaParaPesquisa
								.addAll(colecaoUsuarioAbrangenciaPorSuperior);
					} else {
						mesmoNivel = false;
					}
				}
				// se não achou o usuário abrangencia do usuário que está
				// atualizando na coleção de usuários abrangencias então inseri
				// essa
				// abrangencia na coleção e não permite alteração
				if (!achouUsuarioAbrangencia) {
					filtroUsuarioAbrangencia = new FiltroUsuarioAbrangencia();
					filtroUsuarioAbrangencia
							.adicionarParametro(new ParametroSimples(
									FiltroUsuarioAbrangencia.ID,
									usuarioParaAtualizar
											.getUsuarioAbrangencia().getId()));
					Collection colecaoUsuarioAbrangenciaAdicionar = Fachada
							.getInstancia().pesquisar(filtroUsuarioAbrangencia,
									UsuarioAbrangencia.class.getName());
					colecaoUsuarioAbrangencia
							.addAll(colecaoUsuarioAbrangenciaAdicionar);
					sessao.setAttribute(
							"desabilitaUsuarioAbrangencia", "1");

				}
			}
			// caso exista mais de uma abrangência na pesquisa passando o id da
			// abrangência como superior

			sessao.setAttribute("collUsuarioAbrangencia",
					colecaoUsuarioAbrangencia);

			Collection colecaoGrupo = null;
			// caso o usuário que esteja efetuando a inserção não
			// seja
			// do grupo de administradores
			if (colecaoUsuarioGrupo != null && !colecaoUsuarioGrupo.isEmpty()) {
				FiltroGrupo filtroGrupo = new FiltroGrupo();
				filtroGrupo.setCampoOrderBy(FiltroGrupo.DESCRICAO);
				filtroGrupo.adicionarParametro(new ParametroSimples(
						FiltroGrupo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				colecaoGrupo = Fachada.getInstancia().pesquisar(
						filtroGrupo, Grupo.class.getSimpleName());
			} else {
				Collection colecaoGrupoUsuario = (Collection)sessao.getAttribute("colecaoGruposUsuario");
				colecaoGrupo = new ArrayList();
				Collection colecaoGrupoParaSelecao = Fachada.getInstancia()
						.pesquisarGruposUsuarioAcesso(colecaoGrupoUsuario);
				colecaoGrupo.addAll(colecaoGrupoParaSelecao);

				boolean primeiraVez = true;
				String grupoNaoDesabilitado = "";
				for (int i = 0; i < grupo.length; i++) {
					boolean achouGrupo = true;
					Iterator iteratorGrupo = colecaoGrupoParaSelecao.iterator();
					while (iteratorGrupo.hasNext()) {
						Grupo grupoParaAtualizar = (Grupo) iteratorGrupo.next();
						if (new Integer(grupo[i]).equals(grupoParaAtualizar
								.getId())) {
							achouGrupo = false;
						}
					}
					if (achouGrupo) {
						if (primeiraVez) {
							grupoNaoDesabilitado = grupoNaoDesabilitado
									+ grupo[i];
							primeiraVez = false;
						} else {
							grupoNaoDesabilitado = grupoNaoDesabilitado + ","
									+ grupo[i];
						}
						FiltroGrupo filtroGrupo = new FiltroGrupo();
						filtroGrupo.setCampoOrderBy(FiltroGrupo.DESCRICAO);
						filtroGrupo.adicionarParametro(new ParametroSimples(
								FiltroGrupo.ID, grupo[i]));
						Collection colecaoGrupoSelecionado = Fachada
								.getInstancia().pesquisar(filtroGrupo,
										Grupo.class.getName());
						Grupo grupoSelecionado = (Grupo) Util
								.retonarObjetoDeColecao(colecaoGrupoSelecionado);
						colecaoGrupo.add(grupoSelecionado);
					}

				}
				form.setGrupoNaoDesabilitados(grupoNaoDesabilitado);
			}

			sessao.setAttribute("collGrupo", colecaoGrupo);

		}

		if (sessao.getAttribute("collGerenciaRegional") == null
				|| sessao.getAttribute("collGerenciaRegional").equals("")) {

			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
					FiltroGerenciaRegional.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoGerenciaRegional = Fachada.getInstancia()
					.pesquisar(filtroGerenciaRegional,
							GerenciaRegional.class.getSimpleName());
			sessao
					.setAttribute("collGerenciaRegional",
							colecaoGerenciaRegional);
		}

		if (sessao.getAttribute("collUnidadeNegocio") == null
				|| sessao.getAttribute("collUnidadeNegocio").equals("")) {

			FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
			filtroUnidadeNegocio.adicionarParametro(new ParametroSimples(
					FiltroUnidadeNegocio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUnidadeNegocio = Fachada.getInstancia()
					.pesquisar(filtroUnidadeNegocio,
							UnidadeNegocio.class.getSimpleName());
			sessao.setAttribute("collUnidadeNegocio", colecaoUnidadeNegocio);
		}
		
		if (sessao.getAttribute("collUsuarioSituacao") == null
				|| sessao.getAttribute("collUsuarioSituacao").equals("")) {

			FiltroUsuarioSituacao filtroUsuarioSituacao = new FiltroUsuarioSituacao(FiltroUsuarioSituacao.DESCRICAO);
			filtroUsuarioSituacao.adicionarParametro(new ParametroSimples(
					FiltroUsuarioSituacao.INDICADOR_DE_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoUsuarioSituacao = Fachada.getInstancia()
					.pesquisar(filtroUsuarioSituacao,
							UsuarioSituacao.class.getSimpleName());
			sessao.setAttribute("collUsuarioSituacao", colecaoUsuarioSituacao);
		}
		
		sessao.setAttribute("processo","2");

		return retorno;
	}
}