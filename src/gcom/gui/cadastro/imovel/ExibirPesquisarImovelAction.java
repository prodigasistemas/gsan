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
package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.FiltroCep;
import gcom.cadastro.endereco.FiltroLogradouro;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
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
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que define o pré-processamento da página de pesquisa de imovel
 * 
 * @author Sávio Luiz
 * @created 21 de Julho de 2005
 */

public class ExibirPesquisarImovelAction extends GcomAction {
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
		// Seta o mapeamento de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;
		
		// Parte da pesquisa da lupa que é rotornada do logradouro_pesquisar.jsp
		// para ser redirecionado para a pesquisa que foi chamado
		if (httpServletRequest.getParameter("chamarPesquisaImovel") != null) {
			retorno = actionMapping.findForward(httpServletRequest
					.getParameter("chamarPesquisaImovel"));
			if (httpServletRequest.getParameter("chamarPesquisaImovel").equals(
					"pesquisarLogradouro")) {
				httpServletRequest.setAttribute("primeriaVez",
						"1");
				httpServletRequest.setAttribute("codigoMunicipio",
						pesquisarActionForm.get("idMunicipioImovel"));
				httpServletRequest.setAttribute("codigoBairro",
						pesquisarActionForm.get("codigoBairroImovel"));				
			}
			if (httpServletRequest.getParameter("chamarPesquisaImovel").equals(
					"pesquisarCep")) {
				httpServletRequest.setAttribute("idMunicipioDefinido",
						pesquisarActionForm.get("idMunicipioImovel"));
			}
			return retorno;
		} else {
			retorno = actionMapping
					.findForward("pesquisarImovel");
		}

		if (httpServletRequest.getParameter("objetoConsulta") == null
				&& httpServletRequest.getParameter("tipoConsulta") == null) {

			pesquisarActionForm.set("idLocalidade", "");
			pesquisarActionForm.set("descricaoLocalidade", "");
			pesquisarActionForm.set("codigoSetorComercial", "");
			pesquisarActionForm.set("descricaoSetorComercial", "");
			pesquisarActionForm.set("idQuadra", "");
			pesquisarActionForm.set("descricaoQuadra", "");
			pesquisarActionForm.set("lote", "");
			pesquisarActionForm.set("subLote", "");
			pesquisarActionForm.set("idCliente", "");
			pesquisarActionForm.set("idCliente", "");
			pesquisarActionForm.set("nomeCliente", "");
			pesquisarActionForm.set("idMunicipioImovel", "");
			pesquisarActionForm.set("descricaoMunicipioImovel", null);
			pesquisarActionForm.set("codigoBairroImovel", "");
			pesquisarActionForm.set("descricaoBairroImovel", "");
			pesquisarActionForm.set("cep", "");
			pesquisarActionForm.set("descricaoCep", "");
			pesquisarActionForm.set("nomeLogradouro", "");
			pesquisarActionForm.set("idLogradouro", "");
			pesquisarActionForm.set("numeroImovelInicialFiltro", "");
			pesquisarActionForm.set("numeroImovelFinalFiltro", "");
			// limpa todos os caminhos retorno da sessao
			sessao.removeAttribute("caminhoRetornoTelaPesquisa");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaCliente");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaMunicipio");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaBairro");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaLogradouro");
			sessao.removeAttribute("idMunicipio");
			if (httpServletRequest.getParameter("novaPesquisa") == null
					|| httpServletRequest.getParameter("novaPesquisa").equals(
							"")) {
				sessao.removeAttribute("caminhoRetorno");
			}

		}

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Verifica se o tipoConsulta é diferente de nulo ou vazio esse tipo
		// consulta vem do
		// municipio_resultado_pesquisa.jsp ou do bairro_resultado_pesquisa.jsp.
		// É feita essa verificação pois pode ser que ainda não tenha
		// feito a pesquisa de municipio ou bairro.
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {

			// Verifica se o tipo da consulta de imovel é de Localidade
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"localidade")) {

				pesquisarActionForm.set("idLocalidade", httpServletRequest
						.getParameter("idCampoEnviarDados"));

				pesquisarActionForm.set("descricaoLocalidade",
						httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

				httpServletRequest.setAttribute("nomeCampo",
						"codigoSetorComercial");

			}
			
//			 Verifica se o tipo da consulta de imovel é de municipio
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"cep")) {

				pesquisarActionForm.set("cep", httpServletRequest
						.getParameter("idCampoEnviarDados"));

				pesquisarActionForm.set("descricaoCep",
						httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

				httpServletRequest.setAttribute("nomeCampo",
						"idMunicipioImovel");

			}

			// Verifica se o tipo da consulta de imovel é de setorComercial
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"setorComercial")) {

				String codigoSetorComercial = httpServletRequest
						.getParameter("idCampoEnviarDados");

				pesquisarActionForm.set("codigoSetorComercial",
						codigoSetorComercial);

				pesquisarActionForm.set("descricaoSetorComercial",
						httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

				httpServletRequest.setAttribute("nomeCampo", "idQuadra");

				// recupera o setore comercial na base para setar o município

				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						new Integer(codigoSetorComercial)));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroSetorComercial
						.adicionarCaminhoParaCarregamentoEntidade("municipio");

				// pesquisa
				Collection setorComerciais = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());
				if (setorComerciais != null && !setorComerciais.isEmpty()) {
					httpServletRequest.setAttribute(
							"idSetorComercialNaoEncontrada", "true");
					httpServletRequest.setAttribute(
							"idMunicipioFiltroImovelNaoEncontrado", "true");

					// seta os valores do imóvel
					pesquisarActionForm
							.set(
									"idMunicipioImovel",
									""
											+ ((SetorComercial) ((List) setorComerciais)
													.get(0)).getMunicipio()
													.getId());
					pesquisarActionForm.set("descricaoMunicipioImovel",
							((SetorComercial) ((List) setorComerciais).get(0))
									.getMunicipio().getNome());
				}

				// limpa os campos de bairro
				pesquisarActionForm.set("codigoBairroImovel", "");
				pesquisarActionForm.set("descricaoBairroImovel", "");

			}

			// Verifica se o tipo da consulta de imovel é de quadra
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta")
					.equals("quadra")) {

				pesquisarActionForm.set("idQuadra", httpServletRequest
						.getParameter("idCampoEnviarDados"));

				pesquisarActionForm.set("descricaoQuadra", httpServletRequest
						.getParameter("descricaoCampoEnviarDados"));

				httpServletRequest.setAttribute("nomeCampo", "lote");

			}

			// Verifica se o tipo da consulta de imovel é de cliente
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"cliente")) {

				pesquisarActionForm.set("idCliente", httpServletRequest
						.getParameter("idCampoEnviarDados"));

				pesquisarActionForm.set("nomeCliente", httpServletRequest
						.getParameter("descricaoCampoEnviarDados"));

				httpServletRequest.setAttribute("nomeCampo", "cep");

			}

			// Verifica se o tipo da consulta de imovel é de municipio
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"municipio")) {

				pesquisarActionForm.set("idMunicipioImovel", httpServletRequest
						.getParameter("idCampoEnviarDados"));

				pesquisarActionForm.set("descricaoMunicipioImovel",
						httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

				httpServletRequest.setAttribute("nomeCampo",
						"codigoBairroImovel");

			}
			// Verifica se o tipo da consulta de imovel é de bairro
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina cliente_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta")
					.equals("bairro")) {

				pesquisarActionForm.set("codigoBairroImovel",
						httpServletRequest.getParameter("idCampoEnviarDados"));

				pesquisarActionForm.set("descricaoBairroImovel",
						httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));

				httpServletRequest.setAttribute("nomeCampo", "idLogradouro");

			}
			// Verifica se o tipo da consulta de imovel é de logradouro
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina imovel_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"logradouro")) {

				pesquisarActionForm.set("idLogradouro", httpServletRequest
						.getParameter("idCampoEnviarDados"));

				pesquisarActionForm.set("nomeLogradouro", httpServletRequest
						.getParameter("descricaoCampoEnviarDados"));

				httpServletRequest.setAttribute("nomeCampo", "Button");

			}
		} else {

			// -------Parte que trata do código quando o usuário tecla enter
			// atribui os valores q vem pelo request as variaveis
			// caso seja o codigo de localidade
			String idLocalidade = (String) pesquisarActionForm
					.get("idLocalidade");
			// caso seja o codigo de setor comercial
			String codigoSetorComercial = (String) pesquisarActionForm
					.get("codigoSetorComercial");
			// caso seja o codigo de quadra
			String numeroQuadra = (String) pesquisarActionForm.get("idQuadra");

			// caso seja o codigo do municipio
			String idDigitadoEnterCliente = (String) pesquisarActionForm
					.get("idCliente");
			// caso seja o codigo do municipio
			String idDigitadoEnterMunicipio = (String) pesquisarActionForm
					.get("idMunicipioImovel");
			// caso seja o codigo do bairro
			String codigoDigitadoEnterBairro = (String) pesquisarActionForm
					.get("codigoBairroImovel");
			//caso seja o codigo do cep
			String codigoDigitadoEnterCep = (String) pesquisarActionForm
					.get("cep");

			// caso seja o codigo do logradouro
			String codigoDigitadoEnterLogradouro = (String) pesquisarActionForm
					.get("idLogradouro");

			if (idLocalidade != null
					&& !idLocalidade.toString().trim().equalsIgnoreCase("")) {

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				// coloca parametro no filtro
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, new Integer(idLocalidade)));

				// coloca parametro no filtro
				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				// pesquisa
				Collection localidades = fachada.pesquisar(filtroLocalidade,
						Localidade.class.getName());
				if (localidades != null && !localidades.isEmpty()) {
					// A localidade foi encontrada
					pesquisarActionForm.set("idLocalidade",
							((Localidade) ((List) localidades).get(0)).getId()
									.toString());
					pesquisarActionForm.set("descricaoLocalidade",
							((Localidade) ((List) localidades).get(0))
									.getDescricao());
					httpServletRequest.setAttribute(
							"idLocalidadeNaoEncontrada", "true");

					httpServletRequest.setAttribute("nomeCampo",
							"codigoSetorComercial");
				} else {
					httpServletRequest.setAttribute(
							"idLocalidadeNaoEncontrada", "exception");
					pesquisarActionForm.set("idLocalidade", "");
					pesquisarActionForm.set("descricaoLocalidade",
							"Localidade inexistente");

					httpServletRequest
							.setAttribute("nomeCampo", "idLocalidade");

				}
			}
			if (codigoSetorComercial != null
					&& !codigoSetorComercial.toString().trim()
							.equalsIgnoreCase("")) {

				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
				if (idLocalidade != null
						&& !idLocalidade.toString().trim().equalsIgnoreCase("")) {
					// coloca parametro no filtro
					filtroSetorComercial
							.adicionarParametro(new ParametroSimples(
									FiltroSetorComercial.ID_LOCALIDADE,
									new Integer(idLocalidade)));
				}
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						new Integer(codigoSetorComercial)));

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroSetorComercial
						.adicionarCaminhoParaCarregamentoEntidade("municipio");

				// pesquisa
				Collection setorComerciais = fachada.pesquisar(
						filtroSetorComercial, SetorComercial.class.getName());
				if (setorComerciais != null && !setorComerciais.isEmpty()) {
					// O cliente foi encontrado
					pesquisarActionForm
							.set(
									"codigoSetorComercial",
									""
											+ ((SetorComercial) ((List) setorComerciais)
													.get(0)).getCodigo());
					pesquisarActionForm.set("descricaoSetorComercial",
							((SetorComercial) ((List) setorComerciais).get(0))
									.getDescricao());
					httpServletRequest.setAttribute(
							"idSetorComercialNaoEncontrada", "true");
					httpServletRequest.setAttribute("nomeCampo", "idQuadra");

					// seta os valores do imóvel
					pesquisarActionForm
							.set(
									"idMunicipioImovel",
									""
											+ ((SetorComercial) ((List) setorComerciais)
													.get(0)).getMunicipio()
													.getId());
					pesquisarActionForm.set("descricaoMunicipioImovel",
							((SetorComercial) ((List) setorComerciais).get(0))
									.getMunicipio().getNome());

				} else {
					pesquisarActionForm.set("codigoSetorComercial", "");
					httpServletRequest.setAttribute(
							"idSetorComercialNaoEncontrada", "exception");
					pesquisarActionForm.set("descricaoSetorComercial",
							"Setor comercial inexistente");
					httpServletRequest.setAttribute("nomeCampo",
							"codigoSetorComercial");

				}

			}
			if (numeroQuadra != null
					&& !numeroQuadra.toString().trim().equalsIgnoreCase("")) {
				FiltroQuadra filtroQuadra = new FiltroQuadra();

				if (codigoSetorComercial != null
						&& !codigoSetorComercial.toString().trim()
								.equalsIgnoreCase("")) {
					// coloca parametro no filtro
					filtroQuadra.adicionarParametro(new ParametroSimples(
							FiltroQuadra.CODIGO_SETORCOMERCIAL, new Integer(
									codigoSetorComercial)));
				}
				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.NUMERO_QUADRA, new Integer(numeroQuadra)));

				filtroQuadra.adicionarParametro(new ParametroSimples(
						FiltroQuadra.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				//filtroQuadra.adicionarCaminhoParaCarregamentoEntidade("bairro");
				// pesquisa
				Collection quadras = fachada.pesquisar(filtroQuadra,
						Quadra.class.getName());
				if (quadras != null && !quadras.isEmpty()) {
					// O cliente foi encontrado
					pesquisarActionForm.set("idQuadra", ""
							+ ((Quadra) ((List) quadras).get(0))
									.getNumeroQuadra());
					httpServletRequest.setAttribute("idQuadraNaoEncontrada",
							"true");
					httpServletRequest.setAttribute("nomeCampo", "lote");

				} else {
					httpServletRequest.setAttribute("idQuadraNaoEncontrada",
							"exception");
					pesquisarActionForm.set("idQuadra", "");
					pesquisarActionForm.set("descricaoQuadra",
							"Quadra inexistente");
					httpServletRequest.setAttribute("nomeCampo", "idQuadra");
				}

			}

			// verifica se o codigo do cliente foi digitado
			if (idDigitadoEnterCliente != null
					&& !idDigitadoEnterCliente.trim().equals("")
					&& Integer.parseInt(idDigitadoEnterCliente) > 0) {
				FiltroCliente filtroCliente = new FiltroCliente();

				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.ID, idDigitadoEnterCliente));
				filtroCliente.adicionarParametro(new ParametroSimples(
						FiltroCliente.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
						Cliente.class.getName());

				if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
					// O municipio foi encontrado
					pesquisarActionForm.set("idCliente", ""
							+ ((Cliente) ((List) clienteEncontrado).get(0))
									.getId());
					pesquisarActionForm.set("nomeCliente",
							((Cliente) ((List) clienteEncontrado).get(0))
									.getNome());
					httpServletRequest.setAttribute("idClienteNaoEncontrado",
							"true");
					httpServletRequest.setAttribute("nomeCampo", "cep");

				} else {
					pesquisarActionForm.set("idCliente", "");
					httpServletRequest.setAttribute("idClienteNaoEncontrado",
							"exception");
					pesquisarActionForm.set("nomeCliente",
							"Cliente inexistente");

					httpServletRequest.setAttribute("nomeCampo", "idCliente");

				}

			}

			// Verifica se o código do municipio foi digitado
			if (idDigitadoEnterMunicipio != null
					&& !idDigitadoEnterMunicipio.trim().equals("")
					&& Integer.parseInt(idDigitadoEnterMunicipio) > 0) {
				FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.ID, idDigitadoEnterMunicipio));
				filtroMunicipio.adicionarParametro(new ParametroSimples(
						FiltroMunicipio.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection municipioEncontrado = fachada.pesquisar(
						filtroMunicipio, Municipio.class.getName());

				if (municipioEncontrado != null
						&& !municipioEncontrado.isEmpty()) {
					// O municipio foi encontrado
					pesquisarActionForm.set("idMunicipioImovel", ""
							+ ((Municipio) ((List) municipioEncontrado).get(0))
									.getId());
					pesquisarActionForm.set("descricaoMunicipioImovel",
							((Municipio) ((List) municipioEncontrado).get(0))
									.getNome());
					httpServletRequest.setAttribute(
							"idMunicipioImovelNaoEncontrado", "true");

					httpServletRequest.setAttribute("nomeCampo",
							"codigoBairroImovel");

				} else {
					pesquisarActionForm.set("idMunicipioImovel", "");
					httpServletRequest.setAttribute(
							"idMunicipioImovelNaoEncontrado", "exception");
					pesquisarActionForm.set("descricaoMunicipioImovel",
							"Município inexistente");

					httpServletRequest.setAttribute("nomeCampo",
							"idMunicipioImovel");

				}

			}

			// Verifica se o código do bairro foi digitado
			if (codigoDigitadoEnterBairro != null
					&& !codigoDigitadoEnterBairro.trim().equals("")
					&& Integer.parseInt(codigoDigitadoEnterBairro) > 0) {
				FiltroBairro filtroBairro = new FiltroBairro();

				if (idDigitadoEnterMunicipio != null
						&& !idDigitadoEnterMunicipio.trim().equals("")
						&& Integer.parseInt(idDigitadoEnterMunicipio) > 0) {

					filtroBairro
							.adicionarParametro(new ParametroSimples(
									FiltroBairro.MUNICIPIO_ID,
									idDigitadoEnterMunicipio));
				}

				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.CODIGO, codigoDigitadoEnterBairro));
				filtroBairro.adicionarParametro(new ParametroSimples(
						FiltroBairro.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection bairroEncontrado = fachada.pesquisar(filtroBairro,
						Bairro.class.getName());

				if (bairroEncontrado != null && !bairroEncontrado.isEmpty()) {
					// O municipio foi encontrado
					pesquisarActionForm.set("codigoBairroImovel", ""
							+ ((Bairro) ((List) bairroEncontrado).get(0))
									.getCodigo());
					pesquisarActionForm.set("descricaoBairroImovel",
							((Bairro) ((List) bairroEncontrado).get(0))
									.getNome());
					httpServletRequest.setAttribute(
							"codigoBairroImovelNaoEncontrado", "true");

					httpServletRequest
							.setAttribute("nomeCampo", "idLogradouro");

				} else {
					pesquisarActionForm.set("codigoBairroImovel", "");
					httpServletRequest.setAttribute(
							"codigoBairroImovelNaoEncontrado", "exception");
					pesquisarActionForm.set("descricaoBairroImovel",
							"Bairro inexistente");

					httpServletRequest.setAttribute("nomeCampo",
							"codigoBairroImovel");

				}

			}

			// Verifica se o código do logradouro foi digitado
			if (codigoDigitadoEnterLogradouro != null
					&& !codigoDigitadoEnterLogradouro.trim().equals("")
					&& Integer.parseInt(codigoDigitadoEnterLogradouro) > 0) {
				FiltroLogradouro filtroLogradouro = new FiltroLogradouro();

				filtroLogradouro
						.adicionarCaminhoParaCarregamentoEntidade("logradouroTipo");
				filtroLogradouro
						.adicionarCaminhoParaCarregamentoEntidade("logradouroTitulo");

				filtroLogradouro.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.ID, codigoDigitadoEnterLogradouro));
				filtroLogradouro.adicionarParametro(new ParametroSimples(
						FiltroLogradouro.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection logradouroEncontrado = fachada.pesquisar(
						filtroLogradouro, Logradouro.class.getName());

				if (logradouroEncontrado != null
						&& !logradouroEncontrado.isEmpty()) {
					// O municipio foi encontrado

					Logradouro logradouro = (Logradouro) ((List) logradouroEncontrado)
							.get(0);
					String logradouroFormatado = logradouro
							.getDescricaoFormatada();

					pesquisarActionForm.set("idLogradouro", ""
							+ logradouro.getId());
					pesquisarActionForm.set("nomeLogradouro",
							logradouroFormatado);

					httpServletRequest.setAttribute(
							"idLogradouroNaoEncontrado", "true");

					httpServletRequest.setAttribute("nomeCampo", "numeroImovelInicialFiltro");

				} else {
					pesquisarActionForm.set("idLogradouro", "");
					httpServletRequest.setAttribute(
							"idLogradouroNaoEncontrado", "exception");
					pesquisarActionForm.set("nomeLogradouro",
							"Logradouro inexistente");

					httpServletRequest
							.setAttribute("nomeCampo", "idLogradouro");

				}

			}
			
//			 Verifica se o código do cep foi digitado
			if (codigoDigitadoEnterCep != null
					&& !codigoDigitadoEnterCep.trim().equals("")
					&& Integer.parseInt(codigoDigitadoEnterCep) > 0) {
				FiltroCep filtroCep = new FiltroCep();

				if (codigoDigitadoEnterCep != null
						&& !codigoDigitadoEnterCep.trim().equals("")
						&& Integer.parseInt(codigoDigitadoEnterCep) > 0) {

					filtroCep
							.adicionarParametro(new ParametroSimples(
									FiltroCep.CODIGO,
									codigoDigitadoEnterCep));
				}

				filtroCep.adicionarParametro(new ParametroSimples(
						FiltroCep.CODIGO, codigoDigitadoEnterCep));
				filtroCep.adicionarParametro(new ParametroSimples(
						FiltroCep.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection cepEncontrado = fachada.pesquisar(filtroCep,
						Cep.class.getName());

				if (cepEncontrado != null && !cepEncontrado.isEmpty()) {
					// O municipio foi encontrado
					pesquisarActionForm.set("cep", ""
							+ ((Cep) ((List) cepEncontrado).get(0))
									.getCodigo());
					pesquisarActionForm.set("descricaoCep",
							((Cep) ((List) cepEncontrado).get(0))
									.getDescricaoLogradouroFormatada());
					
					httpServletRequest
							.setAttribute("nomeCampo", "idLogradouro");
					
					httpServletRequest.setAttribute("nomeCampo",
					"idMunicipioImovel");

				} else {
					pesquisarActionForm.set("cep", "");
					httpServletRequest.setAttribute(
							"cepNaoEncontrada", "exception");
					pesquisarActionForm.set("descricaoCep",
							"Cep inexistente");

					httpServletRequest.setAttribute("nomeCampo",
							"cep");

				}

			}
		}

		// -------Fim da Parte que trata do código quando o usuário tecla enter

		// verifica a existencia do atributo caminhoRetorno e se o conteudo é
		// igual a
		// exibirInserirTarifaSocialImovelAction vindo do request. Será
		// repassada para sessao,
		// para que ao executar a pesquisa de imovel através do
		// pesquisarImovelAction seja verificada
		// para adicionar parametros expecificos da pesquisa realizada por
		// tarifasocial_inserir_imovel.jsp
		// a qual necessita apenas dos imoveis sem perfil de tarifa social
		if (httpServletRequest.getParameter("caminhoRetorno") != null) {
			if (httpServletRequest.getParameter("caminhoRetorno").equals(
					"exibirInserirTarifaSocialImovelAction")) {
				sessao.setAttribute("caminhoRetorno", httpServletRequest
						.getParameter("caminhoRetorno"));
			}
		}

		if (httpServletRequest.getParameter("tipo") != null) {
			sessao
					.setAttribute("tipo", httpServletRequest
							.getParameter("tipo"));

		}
		
		//verifica se é para pesquisar apenas os imoveis condominios
		String pesquisarImovelCondominio = httpServletRequest.getParameter("pesquisarImovelCondominio");
		if (pesquisarImovelCondominio != null &&
				!pesquisarImovelCondominio.equals("")) {
			sessao
					.setAttribute("pesquisarImovelCondominio", pesquisarImovelCondominio);
		}

		if (httpServletRequest.getParameter("caminhoRetornoTelaPesquisaImovel") != null) {
			sessao.setAttribute("caminhoRetornoTelaPesquisaImovel",
					httpServletRequest
							.getParameter("caminhoRetornoTelaPesquisaImovel"));

		}

		return retorno;
	}
}
