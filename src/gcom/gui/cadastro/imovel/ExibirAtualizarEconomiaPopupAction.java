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
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.imovel.AreaConstruidaFaixa;
import gcom.cadastro.imovel.FiltroAreaConstruidaFaixa;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para exibir a página de economia popup
 * 
 * @author Sávio Luiz
 * @created 19 de Maio de 2004
 */
public class ExibirAtualizarEconomiaPopupAction extends GcomAction {

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

		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping
				.findForward("atualizarEconomiaPopup");

		// Cria a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoImovelSubCategoriasCadastradas = null;

		// Coleção vinda do exibirInserirEconomiaAcion
		// nessa coleção estão todos os imoveis sub categorias que foi
		// pesquisado no economia_inserir_jsp
		if (sessao.getAttribute("colecaoImovelSubCategoriasCadastradas") != null) {
			colecaoImovelSubCategoriasCadastradas = (Collection) sessao
					.getAttribute("colecaoImovelSubCategoriasCadastradas");

		} else {
			colecaoImovelSubCategoriasCadastradas = new ArrayList();
		}

		// Obtém o action form
		EconomiaPopupActionForm economiaPopupActionForm = (EconomiaPopupActionForm) actionForm;

		// Obtém a fachada
		Fachada fachada = Fachada.getInstancia();

		ImovelEconomia imovelEconomia = null;

		// incicializa o achou para false e caso entre no loop do while ele
		// passa a ser true
		boolean achou = false;

		// Verifica se veio algum parametro no economia_inserir.jsp
		// caso tenha vindo pega o parametro e procura na coleção um objeto
		// que tenha um hashCode igual ao do parametro
		if (httpServletRequest.getParameter("codigoImovelEconomia") != null
				&& !httpServletRequest.getParameter("codigoImovelEconomia")
						.equals("")) {

			// para incluir mais relações entre cliente e imoveis, se preciso
			sessao.setAttribute("colecaoClientesImoveisEconomia",
					new ArrayList());

			Iterator imovelSubCategoriaIterator = colecaoImovelSubCategoriasCadastradas
					.iterator();

			String codigoImovelEconomia = (String) httpServletRequest
					.getParameter("codigoImovelEconomia");

			while (imovelSubCategoriaIterator.hasNext()) {

				if (!achou) {

					ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) imovelSubCategoriaIterator
							.next();
					Iterator imovelEconomiaIterator = imovelSubcategoria
							.getImovelEconomias().iterator();
					while (imovelEconomiaIterator.hasNext()) {
						imovelEconomia = (ImovelEconomia) imovelEconomiaIterator
								.next();
						if (imovelEconomia.getUltimaAlteracao().getTime() == Long
								.parseLong(codigoImovelEconomia)) {

							sessao.setAttribute("imovelEconomia",
									imovelEconomia);
							// manda os parametros para o form
							economiaPopupActionForm
									.setComplementoEndereco(formatarResultado(imovelEconomia
											.getComplementoEndereco()));
							economiaPopupActionForm
									.setNumeroPontosUtilizacao(formatarResultado(""
											+ imovelEconomia
													.getNumeroPontosUtilizacao()));
							economiaPopupActionForm
									.setNumeroMorador(formatarResultado(""
											+ imovelEconomia.getNumeroMorador()));
							economiaPopupActionForm
									.setNumeroIptu(formatarResultado(""
											+ imovelEconomia.getNumeroIptu()));
							economiaPopupActionForm
									.setNumeroCelpe(formatarResultado(""
											+ imovelEconomia.getNumeroCelpe()));
							economiaPopupActionForm.setAreaConstruida(Util
									.formatarMoedaReal(imovelEconomia
											.getAreaConstruida()));
							economiaPopupActionForm.setIdCliente("");
							economiaPopupActionForm.setNomeCliente("");

							SimpleDateFormat dataFormatoAtual = new SimpleDateFormat(
									"dd/MM/yyyy");
							// joga em dataInicial a parte da data
							String dataAtual = dataFormatoAtual
									.format(new Date());

							economiaPopupActionForm
									.setDataInicioClienteImovelRelacao(dataAtual);

							// verifica se o imóvel é de tarifa social caso seja
							// desabilita alguns campos.
							if (imovelEconomia.getImovelSubcategoria()
									.getComp_id().getImovel().getImovelPerfil()
									.getId().equals(ImovelPerfil.TARIFA_SOCIAL)) {
								sessao.setAttribute("tarifaSocial", "1");
							} else {
								sessao.removeAttribute("tarifaSocial");
							}

							achou = true;
							break;
						}
					}
				} else {
					break;
				}
			}

		}

		// parametro que testa se dará um reload(true) ou nao(false)
		httpServletRequest.setAttribute("testeInserir", new Boolean(false));
		Collection colecaoClientesImoveisEconomia = null;
		// HashSet verifica se existe objeto igual na collection
		if (sessao.getAttribute("colecaoClientesImoveisEconomia") != null) {
			colecaoClientesImoveisEconomia = (Collection) sessao
					.getAttribute("colecaoClientesImoveisEconomia");

		} else {
			colecaoClientesImoveisEconomia = new HashSet();
		}

		// caso o parametro de pesquisa enter que é colocado no jsp de
		// atualizar_economia_popup estiver nulo então
		// não foi feita a pesquisa de enter e entra no if.
		if (httpServletRequest.getParameter("pesquisaEnter") == null
				|| httpServletRequest.getParameter("pesquisaEnter")
						.equalsIgnoreCase("")) {

			// Criação das coleções
			Collection areasConstruidasFaixas = null;
			Collection clientesRelacoesTipos = null;

			// caso venha do jsp imovel_economia_fim_relacao_cliente e não entre
			// do if do codigoImovelEconomia que é onde o achou
			// fica true.
			if (!achou) {

				if (sessao.getAttribute("imovelEconomia") != null
						&& !sessao.getAttribute("imovelEconomia").equals("")) {
					imovelEconomia = (ImovelEconomia) sessao
							.getAttribute("imovelEconomia");
					// manda os parametros para o form
					economiaPopupActionForm
							.setComplementoEndereco(formatarResultado(imovelEconomia
									.getComplementoEndereco()));
					economiaPopupActionForm
							.setNumeroPontosUtilizacao(formatarResultado(""
									+ imovelEconomia
											.getNumeroPontosUtilizacao()));
					economiaPopupActionForm
							.setNumeroMorador(formatarResultado(""
									+ imovelEconomia.getNumeroMorador()));
					economiaPopupActionForm.setNumeroIptu(formatarResultado(""
							+ imovelEconomia.getNumeroIptu()));
					economiaPopupActionForm.setNumeroCelpe(formatarResultado(""
							+ imovelEconomia.getNumeroCelpe()));
					economiaPopupActionForm
							.setAreaConstruida(formatarResultado(""
									+ imovelEconomia.getAreaConstruida()));

					SimpleDateFormat dataFormatoAtual = new SimpleDateFormat(
							"dd/MM/yyyy");
					// joga em dataInicial a parte da data
					String dataAtual = dataFormatoAtual.format(new Date());

					economiaPopupActionForm
							.setDataInicioClienteImovelRelacao(dataAtual);

				}
			}

			// Verifica se o tipoConsulta é diferente de nulo ou vazio esse tipo
			// consulta vem do
			// cliente_resultado_pesquisa.jsp.
			// É feita essa verificação pois pode ser que ainda não tenha
			// feito a pesquisa de cliente.
			if (httpServletRequest.getParameter("tipoConsulta") == null
					|| httpServletRequest.getParameter("tipoConsulta").equals(
							"")) {

				// Filtro AreaConstruidaFaixa
				FiltroAreaConstruidaFaixa filtroAreaConstruidaFaixa = new FiltroAreaConstruidaFaixa(
						FiltroAreaConstruidaFaixa.MENOR_FAIXA);

				filtroAreaConstruidaFaixa
						.adicionarParametro(new ParametroSimples(
								FiltroAreaConstruidaFaixa.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
				areasConstruidasFaixas = fachada.pesquisar(
						filtroAreaConstruidaFaixa, AreaConstruidaFaixa.class
								.getName());

				// Filtro TipoClienteImovel
				FiltroClienteRelacaoTipo filtroClienteRelacaoTipo = new FiltroClienteRelacaoTipo(
						FiltroClienteTipo.DESCRICAO);
				filtroClienteRelacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroClienteRelacaoTipo.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroClienteRelacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,
								ClienteRelacaoTipo.USUARIO,
								ParametroSimples.CONECTOR_OR));
				filtroClienteRelacaoTipo
						.adicionarParametro(new ParametroSimples(
								FiltroClienteRelacaoTipo.CLIENTE_RELACAO_TIPO_ID,
								ClienteRelacaoTipo.PROPRIETARIO));
				clientesRelacoesTipos = fachada.pesquisar(
						filtroClienteRelacaoTipo, ClienteRelacaoTipo.class
								.getName());
				// a coleção de clientesImoveisTipos é obrigatório
				if (clientesRelacoesTipos == null
						|| clientesRelacoesTipos.equals("")) {

					throw new ActionServletException(
							"atencao.pesquisa.nenhumresultado", null,
							"cliente tipo");
				} else {

					if (economiaPopupActionForm.getTextoSelecionadoEconomia() == null
							|| economiaPopupActionForm
									.getTextoSelecionadoEconomia().equals("")) {
						economiaPopupActionForm
								.setTextoSelecionadoEconomia(((ClienteRelacaoTipo) ((List) clientesRelacoesTipos)
										.get(0)).getDescricao());
					}
				}

				if (imovelEconomia.getAreaConstruidaFaixa() != null
						&& !imovelEconomia.getAreaConstruidaFaixa().equals("")) {
					economiaPopupActionForm
							.setIdAreaConstruidaFaixa(imovelEconomia
									.getAreaConstruidaFaixa().getId()
									.toString());
				}
				// Envia os objetos no request
				sessao.setAttribute("areasConstruidasFaixas",
						areasConstruidasFaixas);

				sessao.setAttribute("clientesRelacoesTipos",
						clientesRelacoesTipos);
				// caso venha algum parametro do tipoConsulta então
			} else {
				// Verifica se o tipo da consulta é de cliente
				// se for os parametros de enviarDadosParametros serão mandados
				// para
				// a pagina atualizar_economia_popup.jsp
				if (httpServletRequest.getParameter("tipoConsulta").equals(
						"cliente")) {

					economiaPopupActionForm.setIdCliente(httpServletRequest
							.getParameter("idCampoEnviarDados"));

					economiaPopupActionForm.setNomeCliente(httpServletRequest
							.getParameter("descricaoCampoEnviarDados"));

				}
			}

			// Realiza a pesquisa de Cliente se necessário (caso o usuário
			// informou um código do cliente e teclou <enter>)
		} else {

			Collection clientes;
			// Criação dos objetos
			String idCliente = null;
			// Cliente cliente = null;

			idCliente = economiaPopupActionForm.getIdCliente();
			FiltroCliente filtroCliente = new FiltroCliente();

			// Adiciona parametro
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));
			// Realiza a pesquisa de cliente
			clientes = fachada
					.pesquisar(filtroCliente, Cliente.class.getName());
			if (clientes != null && !clientes.isEmpty()) {
				// O cliente foi encontrado
				economiaPopupActionForm
						.setIdCliente(((Cliente) ((List) clientes).get(0))
								.getId().toString());
				economiaPopupActionForm
						.setNomeCliente(((Cliente) ((List) clientes).get(0))
								.getNome());
				// cliente = new Cliente();
				/* cliente = (Cliente) */clientes.iterator().next();

			} else {
				httpServletRequest.setAttribute("codigoClienteNaoEncontrado",
						"true");
				economiaPopupActionForm.setNomeCliente("");
			}
		}

		sessao.setAttribute("colecaoClientesImoveisEconomia",
				colecaoClientesImoveisEconomia);

		if (httpServletRequest.getParameter("limpa") != null) {
			economiaPopupActionForm
					.setClienteRelacaoTipo(ConstantesSistema.NUMERO_NAO_INFORMADO
							+ "");
		}

		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param parametro
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	private String formatarResultado(String parametro) {
		if (parametro != null && !parametro.trim().equals("")) {
			if (parametro.equals("null")) {
				return "";
			} else {
				return parametro.trim();
			}
		} else {
			return "";
		}
	}

}
