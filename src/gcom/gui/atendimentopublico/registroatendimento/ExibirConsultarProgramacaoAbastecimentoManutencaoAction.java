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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.FiltroBairroArea;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 21/08/2006
 */
public class ExibirConsultarProgramacaoAbastecimentoManutencaoAction extends
		GcomAction {
	/**
	 * Este caso de uso permite a programaçao de abastecimento e manutencao de
	 * uma determinada área de bairro
	 * 
	 * [UC0440] Consultar Programação de Abastecimento e Manutenção
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 21/08/2006
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

		ActionForward retorno = actionMapping
				.findForward("programacaoAbastecimentoManutencaoConsultar");

		ConsultarProgramacaoAbastecimentoManutencaoActionForm consultarProgramacaoAbastecimentoManutencaoActionForm = (ConsultarProgramacaoAbastecimentoManutencaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		String areaBairro = null;

		String idBairro = null;

		String codigoDigitadoMunicipioEnter = null;

		if (httpServletRequest.getParameter("mesAnoReferencia") == null) {

			String ano;
			String mes;

			Date dataCorrente = new Date();
			String dataCorrenteTexto = Util.formatarData(dataCorrente);
			ano = dataCorrenteTexto.substring(6, 10);
			mes = dataCorrenteTexto.substring(3, 5);

			consultarProgramacaoAbastecimentoManutencaoActionForm
					.setMesAnoReferencia(mes + "/" + ano);
		}

		String tela = (String) httpServletRequest.getParameter("tela");

		if (tela != null && !tela.equalsIgnoreCase("")) {
			consultarProgramacaoAbastecimentoManutencaoActionForm
					.setMunicipio("");
			consultarProgramacaoAbastecimentoManutencaoActionForm
					.setNomeMunicipio("");
			consultarProgramacaoAbastecimentoManutencaoActionForm.setBairro("");
			consultarProgramacaoAbastecimentoManutencaoActionForm
					.setNomeBairro("");
			consultarProgramacaoAbastecimentoManutencaoActionForm
					.setAreaBairro("-1");

			Collection<BairroArea> colecaoBairroArea = new ArrayList();

			httpServletRequest.setAttribute("colecaoBairroArea",
					colecaoBairroArea);

		} else {

			// [FS0001]- Verificar existência do município

			if (httpServletRequest.getParameter("idMunicipio") != null) {

				codigoDigitadoMunicipioEnter = (String) httpServletRequest
						.getParameter("idMunicipio");

			} else {

				codigoDigitadoMunicipioEnter = (String) consultarProgramacaoAbastecimentoManutencaoActionForm
						.getMunicipio();
			}

			if (httpServletRequest.getParameter("codigoBairro") != null) {

				idBairro = (String) httpServletRequest
						.getParameter("codigoBairro");
			} else {
				idBairro = consultarProgramacaoAbastecimentoManutencaoActionForm
						.getBairro();
			}

			if (httpServletRequest.getParameter("tipoConsulta") != null) {
				if (httpServletRequest.getParameter("tipoConsulta").equals(
						"municipio")) {

					consultarProgramacaoAbastecimentoManutencaoActionForm
							.setMunicipio(httpServletRequest
									.getParameter("idCampoEnviarDados"));

					consultarProgramacaoAbastecimentoManutencaoActionForm
							.setNomeMunicipio(httpServletRequest
									.getParameter("descricaoCampoEnviarDados"));

				}

				// Verifica se o tipo da consulta de cliente é de bairro
				// se for os parametros de enviarDadosParametros serão mandados
				// para
				// a pagina cliente_pesuisar.jsp
				if (httpServletRequest.getParameter("tipoConsulta").equals(
						"bairro")) {

					consultarProgramacaoAbastecimentoManutencaoActionForm
							.setBairro(httpServletRequest
									.getParameter("idCampoEnviarDados"));

					consultarProgramacaoAbastecimentoManutencaoActionForm
							.setNomeBairro(httpServletRequest
									.getParameter("descricaoCampoEnviarDados"));

				}
			}

			// ****Parte relativa ao campo Área de Bairro******

			if (idBairro == null || idBairro.equalsIgnoreCase("")) {

				Collection<BairroArea> colecaoBairroArea = new ArrayList();

				httpServletRequest.setAttribute("colecaoBairroArea",
						colecaoBairroArea);

			}

			// ***************************************************

			String menu = (String) httpServletRequest.getParameter("menu");

			if (menu == null || !menu.equals("sim")) {

				if (areaBairro == null) {

					consultarProgramacaoAbastecimentoManutencaoActionForm
							.setAreaBairro("");

				} else {

					consultarProgramacaoAbastecimentoManutencaoActionForm
							.setAreaBairro(areaBairro);

				}

				// Verifica se o código foi digitado
				if (codigoDigitadoMunicipioEnter != null
						&& !codigoDigitadoMunicipioEnter.trim().equals("")
						&& Integer.parseInt(codigoDigitadoMunicipioEnter) > 0) {
					FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

					filtroMunicipio.adicionarParametro(new ParametroSimples(
							FiltroMunicipio.ID, codigoDigitadoMunicipioEnter));
					filtroMunicipio.adicionarParametro(new ParametroSimples(
							FiltroMunicipio.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection municipioEncontrado = fachada.pesquisar(
							filtroMunicipio, Municipio.class.getName());

					if (municipioEncontrado != null
							&& !municipioEncontrado.isEmpty()) {
						// O municipio foi encontrado
						Municipio municipio = (Municipio) municipioEncontrado
								.iterator().next();
						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setMunicipio(municipio.getId().toString());
						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setNomeMunicipio(municipio.getNome());
						// httpServletRequest.setAttribute("municipioNaoEncontrado",
						// "true");

					} else {
						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setMunicipio("");
						httpServletRequest.setAttribute(
								"municipioNaoEncontrado", "exception");
						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setNomeMunicipio("Município inexistente");
					}
				}

				// [FS0003]- Verificar existência do bairro

				// Código do Bairro

				String codigoDigitadoBairroEnter = null;
				if (httpServletRequest.getParameter("codigoBairro") != null) {

					codigoDigitadoBairroEnter = (String) httpServletRequest
							.getParameter("codigoBairro");

				} else {

					codigoDigitadoBairroEnter = (String) consultarProgramacaoAbastecimentoManutencaoActionForm
							.getBairro();
				}

				// Verifica se o código foi digitado
				if (codigoDigitadoBairroEnter != null
						&& !codigoDigitadoBairroEnter.trim().equals("")
						&& Integer.parseInt(codigoDigitadoBairroEnter) > 0) {
					FiltroBairro filtroBairro = new FiltroBairro();

					filtroBairro.adicionarParametro(new ParametroSimples(
							FiltroBairro.CODIGO, codigoDigitadoBairroEnter));

					filtroBairro.adicionarParametro(new ParametroSimples(
							FiltroBairro.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));
					// Adiciona a busca por município se ele foi digitado na
					// página
					if (codigoDigitadoMunicipioEnter != null
							&& !codigoDigitadoMunicipioEnter
									.equalsIgnoreCase("")) {
						filtroBairro.adicionarParametro(new ParametroSimples(
								FiltroBairro.MUNICIPIO_ID,
								codigoDigitadoMunicipioEnter));
					}

					Collection bairroEncontrado = fachada.pesquisar(
							filtroBairro, Bairro.class.getName());

					if (bairroEncontrado != null && !bairroEncontrado.isEmpty()) {
						// O bairro foi encontrado

						Bairro bairro = (Bairro) bairroEncontrado.iterator()
								.next();

						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setBairro("" + bairro.getCodigo());
						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setNomeBairro(bairro.getNome());

						// ****Parte relativa ao campo Área de Bairro******
						FiltroBairroArea filtroBairroArea = new FiltroBairroArea();

						filtroBairroArea
								.adicionarParametro(new ParametroSimples(
										FiltroBairroArea.ID_BAIRRO, bairro
												.getId().toString()));

						Collection<BairroArea> colecaoBairroArea = fachada
								.pesquisar(filtroBairroArea, BairroArea.class
										.getName());

						if (colecaoBairroArea == null
								|| colecaoBairroArea.isEmpty()) {
							throw new ActionServletException(
									"atencao.entidade_sem_dados_para_selecao",
									null, "Tabela Bairro Área ");
						}

						httpServletRequest.setAttribute("colecaoBairroArea",
								colecaoBairroArea);

						// Fim Parte relativa ao campo Área de Bairro
					} else {
						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setBairro("");
						httpServletRequest.setAttribute("bairroNaoEncontrado",
								"exception");
						consultarProgramacaoAbastecimentoManutencaoActionForm
								.setNomeBairro("Bairro inexistente");
						
						Collection<BairroArea> colecaoBairroArea = new ArrayList();

						httpServletRequest.setAttribute("colecaoBairroArea",
								colecaoBairroArea);
					}
				}
			}
		}
		return retorno;
	}
}