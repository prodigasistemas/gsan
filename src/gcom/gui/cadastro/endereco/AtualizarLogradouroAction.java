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
package gcom.gui.cadastro.endereco;

import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.interceptor.RegistradorOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.seguranca.acesso.usuario.UsuarioAcaoUsuarioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesDiferenteDe;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela atualização de um logradouro na base
 * 
 * @author Sávio Luiz
 */
public class AtualizarLogradouroAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		LogradouroActionForm logradouroActionForm = (LogradouroActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// Recupera a variável para indicar se o usuário apertou o botão de
		// confirmar da tela de
		// confirmação do wizard
		String confirmado = httpServletRequest.getParameter("confirmado");

		// ===========================================================================

		RegistradorOperacao registradorOperacao = new RegistradorOperacao(
				Operacao.OPERACAO_LOGRADOURO_ATUALIZAR,
				// new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
				// UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO),
				// new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
				// UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO),
				// new UsuarioAcaoUsuarioHelper(Usuario.USUARIO_TESTE,
				// UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO),
				new UsuarioAcaoUsuarioHelper(usuario,
						UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO));

		// // [UC0107] Registrar Transação
		// Operacao operacao = new Operacao();
		// operacao.setId(Operacao.OPERACAO_LOGRADOURO_ATUALIZAR);
		//
		// OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		// operacaoEfetuada.setOperacao(operacao);

		// ==========================================================================

		Logradouro logradouro = (Logradouro) sessao.getAttribute("logradouro");

		Municipio municipio = null;

		String idMunicipio = (String) logradouroActionForm.getIdMunicipio();
		// String codigoBairro = (String)
		// logradouroActionForm.getCodigoBairro();

		if (idMunicipio != null && !idMunicipio.trim().equals("")
				&& Integer.parseInt(idMunicipio) > 0) {

			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.ID, idMunicipio));

			filtroMunicipio.adicionarParametro(new ParametroSimples(
					FiltroMunicipio.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
				municipio = ((Municipio) ((List) municipioEncontrado).get(0));

			} else {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null, "município");
			}
		}

		logradouro.setMunicipio(municipio);

		LogradouroTipo logradouroTipo = new LogradouroTipo();

		if (logradouroActionForm.getIdTipo() != null
				&& !logradouroActionForm.getIdTipo().equals(0)) {

			logradouroTipo.setId(new Integer(""
					+ logradouroActionForm.getIdTipo()));

			logradouro.setLogradouroTipo(logradouroTipo);
		} else {
			throw new ActionServletException("atencao.required", null, "Tipo");
		}

		LogradouroTitulo logradouroTitulo = null;

		if (logradouroActionForm.getIdTitulo() != null) {

			logradouroTitulo = new LogradouroTitulo();
			if (!logradouroActionForm.getIdTitulo().equals(0)) {
				logradouroTitulo.setId(new Integer(""
						+ logradouroActionForm.getIdTitulo()));
			} else {
				logradouroTitulo = null;
			}

			logradouro.setLogradouroTitulo(logradouroTitulo);
		}

		Collection colecaoBairros = (Collection) sessao
				.getAttribute("colecaoBairrosSelecionadosUsuario");
		Collection colecaoCeps = (Collection) sessao
				.getAttribute("colecaoCepSelecionadosUsuario");

		if (colecaoBairros == null || colecaoBairros.isEmpty()) {
			throw new ActionServletException("atencao.required", null,
					"Bairro(s)");
		}

		if (colecaoCeps == null || colecaoCeps.isEmpty()) {
			throw new ActionServletException("atencao.required", null, "CEP(s)");
		}

		Short indicadorDeUso = new Short(logradouroActionForm.getIndicadorUso());

		logradouro.setIndicadorUso(indicadorDeUso);

		logradouro.setNome(logradouroActionForm.getNome());

		logradouro.setNomePopular(logradouroActionForm.getNomePopular());

		// ======================================================================

		// [UC0107] Registrar Transação
		// logradouro.setOperacaoEfetuada(operacaoEfetuada);
		// logradouro.adicionarUsuario(Usuario.USUARIO_TESTE,
		// UsuarioAcao.USUARIO_ACAO_TESTE);

		// ======================================================================

		registradorOperacao.registrarOperacao(logradouro);

		FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

		// -------------Parte que atualiza um logradouro na
		// base----------------------
		filtroLogradouro.adicionarParametro(new ParametroSimplesDiferenteDe(
				FiltroLogradouro.ID, logradouro.getLogradouroTipo().getId()));
		filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.ID_LOGRADOUROTIPO, logradouro
						.getLogradouroTipo().getId()));
		filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.ID_MUNICIPIO, logradouro.getMunicipio()
						.getId()));
		filtroLogradouro.adicionarParametro(new ParametroSimples(
				FiltroLogradouro.NOME, logradouro.getNome()));

		if (logradouro.getLogradouroTitulo() == null
				|| logradouro.getLogradouroTitulo().equals("")) {
			filtroLogradouro.adicionarParametro(new ParametroNulo(
					FiltroLogradouro.ID_LOGRADOUROTITULO));
		} else {
			filtroLogradouro.adicionarParametro(new ParametroSimples(
					FiltroLogradouro.ID_LOGRADOUROTITULO, logradouro
							.getLogradouroTitulo().getId()));
		}

		Collection logradouros = fachada.pesquisar(filtroLogradouro,
				Logradouro.class.getName());
		if (logradouros != null && !logradouros.isEmpty()) {
			Logradouro logradouroPesquisado = (Logradouro) ((List) logradouros)
					.get(0);
			if (!logradouro.getId().equals(logradouroPesquisado.getId())) {
				if (confirmado == null
						|| !confirmado.trim().equalsIgnoreCase("ok")) {
					httpServletRequest.setAttribute("caminhoActionConclusao",
							"/gsan/atualizarLogradouroAction.do");
					// Monta a página de confirmação do wizard para perguntar se
					// o usuário quer inserir
					// o pagamento mesmo sem a conta existir para a referência e
					// o imóvel informados
					return montarPaginaConfirmacao(
							"atencao.logradouro_ja_existente.confirmacao",
							httpServletRequest, actionMapping);
				}
			}
		}
		
		Collection colecaoAtualizarLogradouroBairroHelper = null;
		if (sessao.getAttribute("colecaoAtualizarLogradouroBairroHelper") != null){
			colecaoAtualizarLogradouroBairroHelper = (Collection)
			sessao.getAttribute("colecaoAtualizarLogradouroBairroHelper");
		}
		
		Collection colecaoAtualizarLogradouroCepHelper = null;
		if (sessao.getAttribute("colecaoAtualizarLogradouroCepHelper") != null){
			colecaoAtualizarLogradouroCepHelper = (Collection)
			sessao.getAttribute("colecaoAtualizarLogradouroCepHelper");
		}
		
		fachada.atualizarLogradouro(logradouro, colecaoBairros, colecaoCeps, 
		colecaoAtualizarLogradouroBairroHelper, colecaoAtualizarLogradouroCepHelper);

		montarPaginaSucesso(httpServletRequest, "Logradouro de código "
				+ logradouro.getId() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Logradouro",
				"exibirManterLogradouroAction.do");

		return retorno;
	}
}
