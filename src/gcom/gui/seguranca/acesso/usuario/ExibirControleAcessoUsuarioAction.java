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
import gcom.gui.StatusWizard;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.seguranca.acesso.Grupo;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.FiltroUsuarioGrupo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioGrupo;
import gcom.seguranca.acesso.usuario.UsuarioSituacao;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Sávio Luiz
 */
public class ExibirControleAcessoUsuarioAction extends GcomAction {

	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("controlarAcessosUsuario");

		// obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		// Usuario logado no sistema
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// obtem o gerenciador de paginas na sessão
		// gerenciadorPaginas = (GerenciadorPaginas)
		// sessao.getAttribute("gerenciadorPaginas");

		// limpa a sessão
		ControlarAcessoUsuarioActionForm controlarAcessoUsuarioActionForm = (ControlarAcessoUsuarioActionForm) actionForm;
		controlarAcessoUsuarioActionForm.setPermissoesCheckBoxVazias(null);
		controlarAcessoUsuarioActionForm.setPermissoesEspeciais(null);
		
		
		sessao.removeAttribute("grupo");
		sessao.removeAttribute("usuarioGrupo");
		sessao.removeAttribute("usuarioParaAtualizar");
		sessao.removeAttribute("funcionalidadesMap");
		sessao.removeAttribute("arvoreFuncionalidades");
		sessao.removeAttribute("colecaoPermissaoEspecial");
		sessao.removeAttribute("colecaoPermissaoEspecialDesalibitado");
				
		

		StatusWizard statusWizard = null;
		String idUsuario = null;

		if (httpServletRequest.getParameter("desfazer") == null) {

			idUsuario = (String) httpServletRequest
					.getParameter("idRegistroControleAcesso");

			// verifica se chegou no atualizar imovel atraves da tela de filtrar
			// devido a um unico registro
			// ou atraves da lista de imoveis no manter imovel
			statusWizard = new StatusWizard(
					"controlarAcessosUsuarioWizardAction",
					"concluirControlarAcessosUsuarioAction",
					"cancelarControlarAcessoUsuarioAction",
					"exibirManterUsuarioAction",
					"exibirControleAcessoUsuarioAction.do", idUsuario);

			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							1, "DadosGeraisPrimeiraAbaA.gif",
							"DadosGeraisPrimeiraAbaD.gif",
							"exibirControlarRestrincoesAcessoUsuarioAction",
							"controlarRestrincoesAcessoUsuarioAction"));
			statusWizard
					.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
							2, "AcessosUsuarioUltimaAbaA.gif",
							"AcessosUsuarioUltimaAbaD.gif",
							"exibirControlarPermissoesEspeciaisUsuarioAction",
							"controlarPremissoesEspeciaisUsuarioAction"));
			// manda o statusWizard para a sessao
			sessao.setAttribute("statusWizard", statusWizard);

		} else {
			statusWizard = (StatusWizard) sessao.getAttribute("statusWizard");

			idUsuario = statusWizard.getId();
		}

		// Parte da verificação do filtro
		FiltroUsuario filtroUsuario = new FiltroUsuario();

		// filtroUsuario.setCampoOrderBy(FiltroUsuario.NOME_USUARIO);
		filtroUsuario.adicionarParametro(new ParametroSimples(FiltroUsuario.ID,
				idUsuario));
		filtroUsuario
				.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("usuarioTipo");
		filtroUsuario
				.adicionarCaminhoParaCarregamentoEntidade("funcionario.unidadeOrganizacional");
		filtroUsuario
				.adicionarCaminhoParaCarregamentoEntidade("usuarioAbrangencia");
		filtroUsuario
				.adicionarCaminhoParaCarregamentoEntidade("usuarioSituacao");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("localidadeElo");
		filtroUsuario.adicionarCaminhoParaCarregamentoEntidade("localidade");

		Collection colecaoUsuario = Fachada.getInstancia().pesquisar(
				filtroUsuario, Usuario.class.getName());

		Usuario usuarioParaAtualizar = (Usuario) Util
				.retonarObjetoDeColecao(colecaoUsuario);
		// [FS0008] - Verificar permissão para atualização

		UnidadeOrganizacional unidadeEmpresa = usuarioParaAtualizar
				.getUnidadeOrganizacional();
		if (unidadeEmpresa == null) {
			if (usuarioParaAtualizar.getFuncionario() != null
					&& !usuarioParaAtualizar.getFuncionario().equals("")) {
				unidadeEmpresa = usuarioParaAtualizar.getFuncionario()
						.getUnidadeOrganizacional();
			}
		}

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
		if (colecaoUsuarioGrupo == null || colecaoUsuarioGrupo.isEmpty()) {
			// se a unidade de lotacao do usuario que estiver
			// efetuando seja diferente da unidade de
			// lotação informada
			if (usuario.getUnidadeOrganizacional() != null
					&& unidadeEmpresa != null
					&& usuario.getUnidadeOrganizacional().getId() != null
					&& !usuario.getUnidadeOrganizacional().getId().equals(
							unidadeEmpresa.getId())) {
				// recupera a unidade do usuário
				FiltroUnidadeOrganizacional filtroUnidadeEmpresaUsuario = new FiltroUnidadeOrganizacional();
				filtroUnidadeEmpresaUsuario
						.adicionarParametro(new ParametroSimples(
								FiltroUnidadeOrganizacional.ID, usuario
										.getUnidadeOrganizacional().getId()));
				Collection colecaoUnidadeEmpresa = Fachada.getInstancia()
						.pesquisar(filtroUnidadeEmpresaUsuario,
								UnidadeOrganizacional.class.getName());
				UnidadeOrganizacional unidadeEmpresaUsuario = null;
				if (colecaoUnidadeEmpresa != null
						&& !colecaoUnidadeEmpresa.isEmpty()) {
					unidadeEmpresaUsuario = (UnidadeOrganizacional) Util
							.retonarObjetoDeColecao(colecaoUnidadeEmpresa);
				}
				// se o nivel da unidade de lotação do usuário
				// que
				// estiver efetuando a inserção seja maior ou
				// igual
				// ao nivel de unidade de lotação informada
				if (unidadeEmpresaUsuario != null) {
					if (unidadeEmpresaUsuario.getUnidadeTipo().getNivel()
							.intValue() >= unidadeEmpresa.getUnidadeTipo()
							.getNivel().intValue()) {
						throw new ActionServletException(
								"atencao.usuario.sem.permissao.atualizacao",
								usuario.getLogin(), unidadeEmpresa.getDescricao());
					}
				}
				// ou a unidade superior da unidade de lotação
				// informada seja diferente da unidade de
				// lotação do usuário

				// enquanto o nivel superior da unidade de
				// lotação não esteja no mesmo nivel da unidade
				// de lotação do usuário
				boolean mesmoNivel = true;
				Integer idNivelUsuario = unidadeEmpresaUsuario.getUnidadeTipo()
						.getNivel().intValue();
				UnidadeOrganizacional unidadeEmpresaSuperior = null;
				while (mesmoNivel) {
					Integer idUnidadeEmpresaSuperior = null;
					if (unidadeEmpresaSuperior == null) {
						idUnidadeEmpresaSuperior = unidadeEmpresa
								.getUnidadeSuperior().getId();
					} else {
						idUnidadeEmpresaSuperior = unidadeEmpresaSuperior
								.getUnidadeSuperior().getId();
					}
					// recupera a unidade do usuário
					FiltroUnidadeOrganizacional filtroUnidadeEmpresaSuperior = new FiltroUnidadeOrganizacional();
					filtroUnidadeEmpresaSuperior
							.adicionarParametro(new ParametroSimples(
									FiltroUnidadeOrganizacional.ID,
									idUnidadeEmpresaSuperior));
					Collection colecaoUnidadeEmpresaSuperior = Fachada
							.getInstancia().pesquisar(
									filtroUnidadeEmpresaSuperior,
									UnidadeOrganizacional.class.getName());
					if (colecaoUnidadeEmpresaSuperior != null
							&& !colecaoUnidadeEmpresaSuperior.isEmpty()) {
						unidadeEmpresaSuperior = (UnidadeOrganizacional) Util
								.retonarObjetoDeColecao(colecaoUnidadeEmpresaSuperior);
					}
					if (unidadeEmpresaSuperior != null) {
						// caso seja o mesmo nivel
						if (unidadeEmpresaSuperior.getUnidadeTipo().getNivel()
								.equals(idNivelUsuario)) {
							mesmoNivel = false;
							// caso o id da unidade empresa
							// informado for diferente do id da
							// unidade empresa do usuário no
							// mesmo nivel
							if (!unidadeEmpresaSuperior.getId().equals(
									unidadeEmpresaUsuario.getId())) {
								throw new ActionServletException(
										"atencao.usuario.sem.permissao.atualizacao",
										usuario.getLogin(),
										unidadeEmpresa.getDescricao());
							}

						}
					}
				}

			}
		}

		// [FS0018] - Verificar situação do usuário
		if (usuarioParaAtualizar.getUsuarioSituacao() != null
				&& !usuarioParaAtualizar.getUsuarioSituacao().getId().equals(
						UsuarioSituacao.ATIVO)) {
			throw new ActionServletException("atencao.usuario.situacao",
					usuarioParaAtualizar.getLogin(), usuarioParaAtualizar
							.getUsuarioSituacao().getDescricaoAbreviada());
		}
		// cria um array de string para os ids do grupo
		Integer[] idsGrupos = null;
		Collection colecaoGruposUsuario = Fachada.getInstancia()
				.pesquisarGruposUsuario(new Integer(idUsuario));
		if (colecaoGruposUsuario != null && !colecaoGruposUsuario.isEmpty()) {
			idsGrupos = new Integer[colecaoGruposUsuario.size()];
			Iterator iteratorGrupos = colecaoGruposUsuario.iterator();
			int i = 0;
			while (iteratorGrupos.hasNext()) {
				Grupo grupo = (Grupo) iteratorGrupos.next();
				idsGrupos[i] = grupo.getId();
				i++;
			}
		} else {
			// [SF0020] - Verificar grupos de acesso para o usuário
			throw new ActionServletException("atencao.usuario.sem.grupo");
		}

		Collection gruposFuncionalidadeOperacao = Fachada.getInstancia()
				.pesquisarGruposFuncionalidadeOperacoes(idsGrupos);
		// [SF0020] - Verificar grupos funcionalidade operacao de acesso para o
		// usuário
		if ( Util.isVazioOrNulo(gruposFuncionalidadeOperacao)) {
			throw new ActionServletException(
					"atencao.usuario.sem.grupo.funcionalidade.operacao");
		}

		if (usuarioParaAtualizar.getId() != null
				&& !usuarioParaAtualizar.getId().equals("")) {
			statusWizard.adicionarItemHint("Código:", usuarioParaAtualizar
					.getId().toString());
		}
		if (usuarioParaAtualizar.getNomeUsuario() != null
				&& !usuarioParaAtualizar.getNomeUsuario().equals("")) {
			statusWizard.adicionarItemHint("Nome:", usuarioParaAtualizar
					.getNomeUsuario());
		}
		if (usuarioParaAtualizar.getUsuarioTipo() != null
				&& !usuarioParaAtualizar.getUsuarioTipo().equals("")) {
			if (usuarioParaAtualizar.getUsuarioTipo().getId() != null
					&& !usuarioParaAtualizar.getUsuarioTipo().equals("")) {
				statusWizard.adicionarItemHint("Tipo:", usuarioParaAtualizar
						.getUsuarioTipo().getDescricao());
			}
		}
		if (usuarioParaAtualizar.getUsuarioAbrangencia() != null
				&& !usuarioParaAtualizar.getUsuarioAbrangencia().equals("")) {
			if (usuarioParaAtualizar.getUsuarioAbrangencia().getId() != null
					&& !usuarioParaAtualizar.getUsuarioAbrangencia().equals("")) {
				statusWizard.adicionarItemHint("Abrangência:",
						usuarioParaAtualizar.getUsuarioAbrangencia()
								.getDescricao());
			}
		}
		sessao.setAttribute("statusWizard", statusWizard);

		sessao.setAttribute("usuarioParaAtualizar", usuarioParaAtualizar);
		sessao.setAttribute("usuario", usuario);

		sessao.setAttribute("grupo", idsGrupos);
		return retorno;
	}
}
