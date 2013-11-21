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
package gcom.gui.arrecadacao;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de pesquisa de arrecadador
 * 
 * @author Sávio Luiz
 * @created 24 de janeiro de 2006
 */
public class ExibirPesquisarArrecadadorAction extends GcomAction {
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("pesquisarArrecadador");
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarArrecadadorActionForm pesquisarArrecadadorActionForm = (PesquisarArrecadadorActionForm) actionForm;

		// Verifica se o tipoConsulta é diferente de nulo ou vazio esse tipo
		// consulta vem do
		// localidade_resultado_pesquisa.jsp ou do
		// cliente_resultado_pesquisa.jsp ou do imovel_resultado_pesquisa.jsp.
		// É feita essa verificação pois pode ser que ainda não tenha
		// feito a pesquisa de cliente,imovel ou localidade.
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			// Verifica se o tipo da consulta de arrecadador é de localidade
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina arrecadador_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"localidade")) {

				pesquisarArrecadadorActionForm
						.setIdLocalidade(httpServletRequest
								.getParameter("idCampoEnviarDados"));
				pesquisarArrecadadorActionForm
						.setDescricaoLocalidade(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

			}
			// Verifica se o tipo da consulta de arrecadador é de imovel
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina arrecadador_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta")
					.equals("imovel")) {

				pesquisarArrecadadorActionForm.setIdImovel(httpServletRequest
						.getParameter("idCampoEnviarDados"));

				pesquisarArrecadadorActionForm
						.setInscricaoImovel(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

			}

			// Verifica se o tipo da consulta de arrecadador é de cliente
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina arrecadador_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"cliente")) {

				pesquisarArrecadadorActionForm.setIdCliente(httpServletRequest
						.getParameter("idCampoEnviarDados"));
				pesquisarArrecadadorActionForm
						.setNomeCliente(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

			}

		} else {

			if (httpServletRequest.getParameter("objetoConsulta") == null
					|| httpServletRequest.getParameter("objetoConsulta")
							.equals("")) {

				pesquisarArrecadadorActionForm.setIdLocalidade("");

				pesquisarArrecadadorActionForm.setDescricaoLocalidade("");
				pesquisarArrecadadorActionForm.setIdCliente("");
				pesquisarArrecadadorActionForm.setIdImovel("");
				pesquisarArrecadadorActionForm.setNomeCliente("");
				pesquisarArrecadadorActionForm.setInscricaoEstadual("");
				sessao.removeAttribute("caminhoRetornoTelaPesquisa");
				sessao.removeAttribute("caminhoRetornoTelaPesquisaCliente");
				sessao.removeAttribute("caminhoRetornoTelaPesquisaImovel");

			}

			// -------Parte que trata do código quando o usuário tecla enter
			// caso seja o id da localidade
			String idDigitadoEnterLocalidade = pesquisarArrecadadorActionForm
					.getIdLocalidade();
			String descricaoLocalidade = pesquisarArrecadadorActionForm
					.getDescricaoLocalidade();
			// caso seja o id do cliente
			String idDigitadoEnterCliente = pesquisarArrecadadorActionForm
					.getIdCliente();
			String nomeCliente = pesquisarArrecadadorActionForm
					.getNomeCliente();

			String idDigitadoImovel = pesquisarArrecadadorActionForm
					.getIdImovel();
			String inscricaoImovel = pesquisarArrecadadorActionForm
					.getInscricaoImovel();

			// Verifica se o código foi digitado
			if ((idDigitadoEnterLocalidade != null
					&& !idDigitadoEnterLocalidade.trim().equals(""))
					&& (descricaoLocalidade == null
					|| descricaoLocalidade.trim().equals(""))) {
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, idDigitadoEnterLocalidade));
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection localidadeEncontrado = fachada.pesquisar(
						filtroLocalidade, Localidade.class.getName());

				if (localidadeEncontrado != null
						&& !localidadeEncontrado.isEmpty()) {
					// A localidade foi encontrado
					pesquisarArrecadadorActionForm.setIdLocalidade(""
							+ ((Localidade) ((List) localidadeEncontrado)
									.get(0)).getId());
					pesquisarArrecadadorActionForm.setDescricaoLocalidade(""
							+ ((Localidade) ((List) localidadeEncontrado)
									.get(0)).getDescricao());
					httpServletRequest.setAttribute(
							"idLocalidadeNaoEncontrado", "true");
					httpServletRequest.setAttribute("nomeCampo","idCliente");

				} else {

					pesquisarArrecadadorActionForm.setIdLocalidade("");
					httpServletRequest.setAttribute(
							"idLocalidadeNaoEncontrado", "exception");
					pesquisarArrecadadorActionForm
							.setDescricaoLocalidade("LOCALIDADE INEXISTENTE");
					httpServletRequest.setAttribute("nomeCampo","idLocalidade");

				}

			}

			// Verifica se o código foi digitado
			if ((idDigitadoEnterCliente != null
					&& !idDigitadoEnterCliente.trim().equals(""))
					&& (nomeCliente == null
					|| nomeCliente.trim().equals(""))) {
				FiltroCliente filtroCliente = new FiltroCliente();

				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, idDigitadoEnterCliente));
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
						Cliente.class.getName());

				if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
					// O cliente foi encontrado
					pesquisarArrecadadorActionForm.setIdCliente(""
							+ ((Cliente) ((List) clienteEncontrado).get(0))
									.getId());
					pesquisarArrecadadorActionForm
							.setNomeCliente(((Cliente) ((List) clienteEncontrado)
									.get(0)).getNome());
					httpServletRequest.setAttribute("idClienteNaoEncontrado",
							"true");
					httpServletRequest.setAttribute("nomeCampo","idImovel");

				} else {

					pesquisarArrecadadorActionForm.setIdCliente("");
					httpServletRequest.setAttribute("idClienteNaoEncontrado",
							"exception");
					pesquisarArrecadadorActionForm
							.setNomeCliente("CLIENTE INEXISTENTE");
					httpServletRequest.setAttribute("nomeCampo","idCliente");

				}

			}

			// Verifica se o código foi digitado
			if ((idDigitadoImovel != null && !idDigitadoImovel.trim().equals(""))
					&& (inscricaoImovel == null || inscricaoImovel.trim().equals(""))) {
				FiltroImovel filtroImovel = new FiltroImovel();

				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, idDigitadoImovel));
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");

				Collection imovelEncontrado = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());

				if (imovelEncontrado != null && !imovelEncontrado.isEmpty()) {
					// O imovel foi encontrado
					pesquisarArrecadadorActionForm.setIdImovel(""
							+ ((Imovel) ((List) imovelEncontrado).get(0))
									.getId());
					pesquisarArrecadadorActionForm
							.setInscricaoImovel(((Imovel) ((List) imovelEncontrado)
									.get(0)).getInscricaoFormatada());
					httpServletRequest.setAttribute("idImovelNaoEncontrado",
							"true");
					httpServletRequest.setAttribute("nomeCampo","Button");

				} else {

					pesquisarArrecadadorActionForm.setIdCliente("");
					httpServletRequest.setAttribute("idImovelNaoEncontrado",
							"exception");
					pesquisarArrecadadorActionForm
							.setNomeCliente("IMÓVEL INEXISTENTE");
					httpServletRequest.setAttribute("nomeCampo","idImovel");

				}

			}

		}

		// -------Fim da Parte que trata do código quando o usuário tecla enter

		// envia uma flag que será verificado no cliente_resultado_pesquisa.jsp
		// para saber se irá usar o enviar dados ou o enviar dados parametros
		if (httpServletRequest
				.getParameter("caminhoRetornoTelaPesquisaArrecadador") != null) {
			sessao
					.setAttribute(
							"caminhoRetornoTelaPesquisaArrecadador",
							httpServletRequest
									.getParameter("caminhoRetornoTelaPesquisaArrecadador"));
		}

		return retorno;
	}
}
