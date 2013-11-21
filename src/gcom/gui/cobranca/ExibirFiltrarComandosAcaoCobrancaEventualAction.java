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
package gcom.gui.cobranca;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaCriterio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite consultar comandos de ação de cobrança [UC0326] Filtrar Comandos de
 * Ação de Conbrança - Eventual
 * 
 * @author Rafael Santos
 * @since 08/05/2006
 */
public class ExibirFiltrarComandosAcaoCobrancaEventualAction extends GcomAction {

	private String localidadeID = null;

	private String setorComercialCD = null;

	private HttpSession sessao;

	/**
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarComandosAcaoCobrancaEventual");

		Fachada fachada = Fachada.getInstancia();

		FiltrarComandosAcaoCobrancaEventualActionForm filtrarComandosAcaoCobrancaEventualActionForm = (FiltrarComandosAcaoCobrancaEventualActionForm) actionForm;

		String situacaoComando = filtrarComandosAcaoCobrancaEventualActionForm
		.getSituacaoComando();
		
		if(situacaoComando == null){
			filtrarComandosAcaoCobrancaEventualActionForm
			.setSituacaoComando("Todos");	
		}
		
		String indicadorCriterio = filtrarComandosAcaoCobrancaEventualActionForm
		.getIndicadorCriterio();
		
		if(indicadorCriterio == null){
			filtrarComandosAcaoCobrancaEventualActionForm
			.setIndicadorCriterio("Todos");
		}

		sessao = httpServletRequest.getSession(false);
		
		sessao.removeAttribute("filtroCobrancaAcaoAtividadeComando");
		
		String carregando = httpServletRequest.getParameter("carregando");
		
		if(carregando != null && !carregando.equals("")){
			
			if (sessao.getAttribute("filtrarComandosAcaoCobrancaEventualActionForm") != null) {
				
				FiltrarComandosAcaoCobrancaEventualActionForm filtrarComandosAcaoCobrancaEventualActionFormRecarregar = 
		        	(FiltrarComandosAcaoCobrancaEventualActionForm) sessao.getAttribute("filtrarComandosAcaoCobrancaEventualActionForm");

				
				filtrarComandosAcaoCobrancaEventualActionForm.setAcaoCobranca(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getAcaoCobranca());
				filtrarComandosAcaoCobrancaEventualActionForm.setIndicadorCriterio(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIndicadorCriterio());
				filtrarComandosAcaoCobrancaEventualActionForm.setCriterioCobranca(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getCriterioCobranca());
				filtrarComandosAcaoCobrancaEventualActionForm.setAtividadeCobranca(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getAtividadeCobranca());
				filtrarComandosAcaoCobrancaEventualActionForm.setGrupoCobranca(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getGrupoCobranca());
				filtrarComandosAcaoCobrancaEventualActionForm.setGerenciaRegional(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getGerenciaRegional());
				filtrarComandosAcaoCobrancaEventualActionForm.setLocalidadeOrigemID(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getLocalidadeOrigemID());
				filtrarComandosAcaoCobrancaEventualActionForm.setLocalidadeDestinoID(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getLocalidadeDestinoID());
				filtrarComandosAcaoCobrancaEventualActionForm.setNomeLocalidadeOrigem(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getNomeLocalidadeOrigem());				
				filtrarComandosAcaoCobrancaEventualActionForm.setNomeLocalidadeDestino(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getNomeLocalidadeDestino());
				filtrarComandosAcaoCobrancaEventualActionForm.setSetorComercialOrigemCD(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getSetorComercialOrigemCD());
				filtrarComandosAcaoCobrancaEventualActionForm.setSetorComercialOrigemID(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getSetorComercialOrigemID());
				filtrarComandosAcaoCobrancaEventualActionForm.setSetorComercialDestinoCD(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getSetorComercialDestinoCD());
				filtrarComandosAcaoCobrancaEventualActionForm.setSetorComercialDestinoID(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getSetorComercialDestinoID());
				filtrarComandosAcaoCobrancaEventualActionForm.setNomeSetorComercialOrigem(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getNomeSetorComercialOrigem());
				filtrarComandosAcaoCobrancaEventualActionForm.setNomeSetorComercialDestino(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getNomeSetorComercialDestino());
				filtrarComandosAcaoCobrancaEventualActionForm.setUnidadeNegocio(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getUnidadeNegocio());
				
				filtrarComandosAcaoCobrancaEventualActionForm.setDataEmissaoInicio(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getDataEmissaoInicio());
				filtrarComandosAcaoCobrancaEventualActionForm.setDataEmissaoFim(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getDataEmissaoFim());
				filtrarComandosAcaoCobrancaEventualActionForm.setIdCobrancaAcaoAtividadeComando(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIdCobrancaAcaoAtividadeComando());
				
				if(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getRotaInicial() != null &&
						!filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getRotaInicial().equals("")){
					carregarRota(
							filtrarComandosAcaoCobrancaEventualActionForm,
							fachada, filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getSetorComercialOrigemCD(),
							filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getLocalidadeOrigemID());	
					filtrarComandosAcaoCobrancaEventualActionForm.setRotaInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getRotaInicial());
					
				}else{
					filtrarComandosAcaoCobrancaEventualActionForm.setRotaInicial(null);
					sessao.setAttribute("colecaoRota", null);
				}
				if(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getRotaFinal() != null &&
						!filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getRotaFinal().equals("")){
				
					filtrarComandosAcaoCobrancaEventualActionForm.setRotaFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getRotaFinal());
				}else{
					filtrarComandosAcaoCobrancaEventualActionForm.setRotaFinal(null);
					sessao.setAttribute("colecaoRota", null);
				}
				
				
				filtrarComandosAcaoCobrancaEventualActionForm.setClienteRelacaoTipo(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getClienteRelacaoTipo());
				filtrarComandosAcaoCobrancaEventualActionForm.setIdCliente(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIdCliente());
				filtrarComandosAcaoCobrancaEventualActionForm.setNomeCliente(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getNomeCliente());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoComandoInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoComandoInicial());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoComandoFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoComandoFinal());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoRealizacaoComandoInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoRealizacaoComandoInicial());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoRealizacaoComandoFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoRealizacaoComandoFinal());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoReferenciaContasInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoReferenciaContasInicial());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoReferenciaContasFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoReferenciaContasFinal());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoVencimentoContasInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoVencimentoContasInicial());
				filtrarComandosAcaoCobrancaEventualActionForm.setPeriodoVencimentoContasFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getPeriodoVencimentoContasFinal());
				filtrarComandosAcaoCobrancaEventualActionForm.setIntervaloValorDocumentosInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIntervaloValorDocumentosInicial());
		        filtrarComandosAcaoCobrancaEventualActionForm.setIntervaloValorDocumentosFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIntervaloValorDocumentosFinal());
		        filtrarComandosAcaoCobrancaEventualActionForm.setIntervaloQuantidadeDocumentosInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIntervaloQuantidadeDocumentosInicial());
		        filtrarComandosAcaoCobrancaEventualActionForm.setIntervaloQuantidadeDocumentosFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIntervaloQuantidadeDocumentosFinal());
		        filtrarComandosAcaoCobrancaEventualActionForm.setIntervaloQuantidadeItensDocumentosInicial(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIntervaloQuantidadeItensDocumentosInicial());
		        filtrarComandosAcaoCobrancaEventualActionForm.setIntervaloQuantidadeItensDocumentosFinal(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getIntervaloQuantidadeItensDocumentosFinal());
		        filtrarComandosAcaoCobrancaEventualActionForm.setSituacaoComando(filtrarComandosAcaoCobrancaEventualActionFormRecarregar.getSituacaoComando());
			}
			
		}
		
		//sessao = httpServletRequest.getSession(false);

		// CARREGAR AS COBRANÇAS GRUPO
		if (sessao.getAttribute("colecaoGrupoCobranca") == null) {
			sessao.setAttribute("colecaoGrupoCobranca", fachada
					.obterColecaoCobrancaGrupo());
		}

		// CARREGAR AS COBRANÇAS ATIVIDADE
		if (sessao.getAttribute("colecaoAtividadeCobranca") == null) {
			sessao.setAttribute("colecaoAtividadeCobranca", fachada
					.obterColecaoCobrancaAtividade());
		}

		// CARREGAR AS COBRANÇAS ACAO
		if (sessao.getAttribute("colecaoAcaoCobranca") == null) {
			sessao.setAttribute("colecaoAcaoCobranca", fachada
					.obterColecaoCobrancaAcao());
		}

		// CARREGAR AS GERENCIAIS REGIONAIS
		if (sessao.getAttribute("colecaoGerenciaRegional") == null) {
			sessao.setAttribute("colecaoGerenciaRegional", fachada
					.obterColecaoGerenciaRegional());
		}

		// CARREGAR AS UNIDADE NEGOCIO
		if (sessao.getAttribute("colecaoUnidadeNegocio") == null) {
			sessao.setAttribute("colecaoUnidadeNegocio", fachada
					.obterColecaoGerenciaRegional());
		}
		
		
		// CARREGAR OS CLIENTE RELACAO TIPO
		if (sessao.getAttribute("colecaoClienteRelacaoTipo") == null) {
			sessao.setAttribute("colecaoClienteRelacaoTipo", fachada
					.obterColecaoClienteRelacaoTipo());
		}
		
		//CARREGAR OS TITULOS DE COBRANCA ACAO ATIVIDAD COMAND
		if (sessao.getAttribute("colecaoCobrancaAcaoAtividadeComando") == null) {
			
			Collection colecaoAtividadesEventuaisAcaoCobrancaComandadas = fachada.obterListaAtividadesEventuaisAcaoCobrancaComandadas();
			
			sessao.setAttribute("colecaoCobrancaAcaoAtividadeComando", colecaoAtividadesEventuaisAcaoCobrancaComandadas);
		}

		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		String inscricaoTipo = (String) httpServletRequest
				.getParameter("inscricaoTipo");

		String rota = (String) httpServletRequest
		.getParameter("rota");
		
		
		//String idAcaoCobranca = (String) httpServletRequest
		//.getParameter("idAcaoCobranca");

		//String limparCriterioCobranca = (String) httpServletRequest
		//.getParameter("limparCriterioCobranca");
		
		//carregar as rotas
		if (rota != null && !rota.trim().equalsIgnoreCase("")){
			
			carregarRota(
					filtrarComandosAcaoCobrancaEventualActionForm,
					fachada, filtrarComandosAcaoCobrancaEventualActionForm.getSetorComercialOrigemCD(),
					filtrarComandosAcaoCobrancaEventualActionForm.getLocalidadeOrigemID());
			
		}
		
		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")
				&& inscricaoTipo != null
				&& !inscricaoTipo.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {
			// Localidade
			case 1:

				pesquisarLocalidade(inscricaoTipo,
						filtrarComandosAcaoCobrancaEventualActionForm, fachada,
						httpServletRequest);

				break;
			// Setor Comercial
			case 2:

				pesquisarLocalidade(inscricaoTipo,
						filtrarComandosAcaoCobrancaEventualActionForm, fachada,
						httpServletRequest);

				pesquisarSetorComercial(inscricaoTipo,
						filtrarComandosAcaoCobrancaEventualActionForm, fachada,
						httpServletRequest);

				break;
			case 3:
				pesquisarCliente(inscricaoTipo,
						filtrarComandosAcaoCobrancaEventualActionForm, fachada,
						httpServletRequest);
				break;

			default:
				break;
			}
		}

		String criterioCobranca = filtrarComandosAcaoCobrancaEventualActionForm
				.getCriterioCobranca();
		// pesquisar o critério de cobrança
		if (criterioCobranca != null && !criterioCobranca.equals("")) {

			CobrancaCriterio cobrancaCriterio = fachada
					.obterCobrancaCriterio(criterioCobranca);

			if (cobrancaCriterio == null) {

				filtrarComandosAcaoCobrancaEventualActionForm
						.setCriterioCobranca("");
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeCriterioCobranca("Critério de Cobrança Inexistente");
				httpServletRequest.setAttribute("corCriterioCobranca",
						"exception");
				httpServletRequest
						.setAttribute("nomeCampo", "criterioCobranca");

			} else {
				filtrarComandosAcaoCobrancaEventualActionForm
						.setCriterioCobranca(String.valueOf(cobrancaCriterio
								.getId()));
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeCriterioCobranca(cobrancaCriterio
								.getDescricaoCobrancaCriterio());
				httpServletRequest.setAttribute("corCriterioCobranca", "valor");

				httpServletRequest.setAttribute("nomeCampo", "grupoCobranca");

			}

		}

		return retorno;
	}

	/**
	 * Pesquisa a Localidade
	 * 
	 * @param inscricaoTipo
	 * @param imovelOutrosCriteriosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarLocalidade(
			String inscricaoTipo,
			FiltrarComandosAcaoCobrancaEventualActionForm filtrarComandosAcaoCobrancaEventualActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			filtrarComandosAcaoCobrancaEventualActionForm
					.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) filtrarComandosAcaoCobrancaEventualActionForm
					.getLocalidadeOrigemID();

			Localidade objetoLocalidade = fachada
					.obterLocalidadeGerenciaRegional(localidadeID);

			if (objetoLocalidade == null) {
				// Localidade nao encontrada
				// Limpa os campos localidadeOrigemID e nomeLocalidadeOrigem do
				// formulário
				filtrarComandosAcaoCobrancaEventualActionForm
						.setLocalidadeOrigemID("");
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeLocalidadeOrigem("Localidade Inexistente");
				httpServletRequest.setAttribute("corLocalidadeOrigem",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeOrigemID");

			} else {
				filtrarComandosAcaoCobrancaEventualActionForm
						.setLocalidadeOrigemID(String.valueOf(objetoLocalidade
								.getId()));
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeLocalidadeOrigem(objetoLocalidade
								.getDescricao());
				httpServletRequest.setAttribute("corLocalidadeOrigem", "valor");

				String localidadeDestinoID = (String) filtrarComandosAcaoCobrancaEventualActionForm
						.getLocalidadeDestinoID();
				// verifica o valor das localidades, origem e final
				if (localidadeDestinoID != null) {

					if (localidadeDestinoID.equals("")) {
						filtrarComandosAcaoCobrancaEventualActionForm
								.setLocalidadeDestinoID(String
										.valueOf(objetoLocalidade.getId()));
						filtrarComandosAcaoCobrancaEventualActionForm
								.setNomeLocalidadeDestino(objetoLocalidade
										.getDescricao());
					} else {
						int localidadeDestino = new Integer(localidadeDestinoID)
								.intValue();
						int localidadeOrigem = objetoLocalidade.getId()
								.intValue();
						if (localidadeOrigem > localidadeDestino) {
							filtrarComandosAcaoCobrancaEventualActionForm
									.setLocalidadeDestinoID(String
											.valueOf(objetoLocalidade.getId()));
							filtrarComandosAcaoCobrancaEventualActionForm
									.setNomeLocalidadeDestino(objetoLocalidade
											.getDescricao());
						}
					}
				}
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeDestinoID");

			}
		} else {
			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) filtrarComandosAcaoCobrancaEventualActionForm
					.getLocalidadeDestinoID();

			Localidade objetoLocalidade = fachada
					.obterLocalidadeGerenciaRegional(localidadeID);

			filtrarComandosAcaoCobrancaEventualActionForm
					.setInscricaoTipo("destino");

			if (objetoLocalidade == null) {
				// Localidade nao encontrada
				// Limpa os campos localidadeDestinoID e nomeLocalidadeDestino
				// do formulário
				filtrarComandosAcaoCobrancaEventualActionForm
						.setLocalidadeDestinoID("");
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeLocalidadeDestino("Localidade inexistente");
				httpServletRequest.setAttribute("corLocalidadeDestino",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"localidadeDestinoID");
			} else {
				int localidadeDestino = objetoLocalidade.getId().intValue();

				String localidade = (String) filtrarComandosAcaoCobrancaEventualActionForm
						.getLocalidadeOrigemID();

				if (localidade != null && !localidade.equals("")) {

					int localidadeOrigem = new Integer(localidade).intValue();
					if (localidadeDestino < localidadeOrigem) {
						filtrarComandosAcaoCobrancaEventualActionForm
								.setLocalidadeDestinoID("");
						// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
						// .setNomeLocalidadeDestino("Loc. Final maior que a
						// Inicial");
						httpServletRequest.setAttribute("mensagem",
								"Localidae Final menor que o Inicial");
						filtrarComandosAcaoCobrancaEventualActionForm
								.setNomeLocalidadeDestino("");
						httpServletRequest.setAttribute("corLocalidadeDestino",
								"valor");

						httpServletRequest.setAttribute("nomeCampo",
								"localidadeDestinoID");

					} else {
						filtrarComandosAcaoCobrancaEventualActionForm
								.setLocalidadeDestinoID(String
										.valueOf(objetoLocalidade.getId()));
						filtrarComandosAcaoCobrancaEventualActionForm
								.setNomeLocalidadeDestino(objetoLocalidade
										.getDescricao());
						httpServletRequest.setAttribute("corLocalidadeDestino",
								"valor");
						httpServletRequest.setAttribute("nomeCampo",
								"setorComercialOrigemCD");

						pesquisarLocalidade("origem",
								filtrarComandosAcaoCobrancaEventualActionForm,
								fachada, httpServletRequest);
					}
				} else {
					filtrarComandosAcaoCobrancaEventualActionForm
							.setLocalidadeDestinoID(String
									.valueOf(objetoLocalidade.getId()));
					filtrarComandosAcaoCobrancaEventualActionForm
							.setNomeLocalidadeDestino(objetoLocalidade
									.getDescricao());
					httpServletRequest.setAttribute("corLocalidadeDestino",
							"valor");
					httpServletRequest.setAttribute("nomeCampo",
							"setorComercialOrigemCD");
					pesquisarLocalidade("origem",
							filtrarComandosAcaoCobrancaEventualActionForm,
							fachada, httpServletRequest);

				}
			}
		}

	}

	/**
	 * Pesquisa o Setor Comercial para ser usado no Filtrar Comandos de Ação de
	 * Cobrança
	 * 
	 * @param inscricaoTipo
	 * @param filtrarComandosAcaoCobrancaEventualActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarSetorComercial(
			String inscricaoTipo,
			FiltrarComandosAcaoCobrancaEventualActionForm filtrarComandosAcaoCobrancaEventualActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		if (inscricaoTipo.equalsIgnoreCase("origem")) {
			filtrarComandosAcaoCobrancaEventualActionForm
					.setInscricaoTipo("origem");
			// Recebe o valor do campo localidadeOrigemID do formulário.
			localidadeID = (String) filtrarComandosAcaoCobrancaEventualActionForm
					.getLocalidadeOrigemID();

			// Recebe o valor do campo localidadeOrigemID do formulário.
			setorComercialCD = (String) filtrarComandosAcaoCobrancaEventualActionForm
					.getSetorComercialOrigemCD();

			// O campo localidadeOrigemID será obrigatório
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				SetorComercial objetoSetorComercial = fachada
						.obterSetorComercialLocalidade(localidadeID,
								setorComercialCD);

				if (objetoSetorComercial == null) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialOrigemCD,
					// nomeSetorComercialOrigem e setorComercialOrigemID do
					// formulário
					filtrarComandosAcaoCobrancaEventualActionForm
							.setSetorComercialOrigemCD("");
					filtrarComandosAcaoCobrancaEventualActionForm
							.setSetorComercialOrigemID("");
					filtrarComandosAcaoCobrancaEventualActionForm
							.setNomeSetorComercialOrigem("Setor Comercial Inexistente");
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"exception");
					filtrarComandosAcaoCobrancaEventualActionForm
							.setRotaInicial(null);
					filtrarComandosAcaoCobrancaEventualActionForm
							.setRotaFinal(null);

					httpServletRequest.setAttribute("nomeCampo",
							"setorComercialOrigemCD");

				} else {
					// setorComercialID =
					// objetoSetorComercial.getId().toString();
					// setorComercialOrigem
					filtrarComandosAcaoCobrancaEventualActionForm
							.setSetorComercialOrigemCD(String
									.valueOf(objetoSetorComercial.getCodigo()));
					filtrarComandosAcaoCobrancaEventualActionForm
							.setSetorComercialOrigemID(String
									.valueOf(objetoSetorComercial.getId()));
					filtrarComandosAcaoCobrancaEventualActionForm
							.setNomeSetorComercialOrigem(objetoSetorComercial
									.getDescricao());
					httpServletRequest.setAttribute("corSetorComercialOrigem",
							"valor");

					String setorComercialDestinoCD = (String) filtrarComandosAcaoCobrancaEventualActionForm
							.getSetorComercialDestinoCD();

					// verifica o valor dos setores comerciais, origem e final
					if (setorComercialDestinoCD != null) {

						if (setorComercialDestinoCD.equals("")) {

							// setorComercialDestino
							filtrarComandosAcaoCobrancaEventualActionForm
									.setSetorComercialDestinoCD(String
											.valueOf(objetoSetorComercial
													.getCodigo()));
							filtrarComandosAcaoCobrancaEventualActionForm
									.setSetorComercialDestinoID(String
											.valueOf(objetoSetorComercial
													.getId()));
							filtrarComandosAcaoCobrancaEventualActionForm
									.setNomeSetorComercialDestino(objetoSetorComercial
											.getDescricao());

							carregarRota(
									filtrarComandosAcaoCobrancaEventualActionForm,
									fachada, objetoSetorComercial.getCodigo()
											+ "",localidadeID);

						} else {

							int setorDestino = new Integer(
									setorComercialDestinoCD).intValue();
							int setorOrigem = objetoSetorComercial.getCodigo();
							if (setorOrigem > setorDestino) {

								// setorComercialDestino
								filtrarComandosAcaoCobrancaEventualActionForm
										.setSetorComercialDestinoCD(String
												.valueOf(objetoSetorComercial
														.getCodigo()));
								filtrarComandosAcaoCobrancaEventualActionForm
										.setSetorComercialDestinoID(String
												.valueOf(objetoSetorComercial
														.getId()));
								filtrarComandosAcaoCobrancaEventualActionForm
										.setNomeSetorComercialDestino(objetoSetorComercial
												.getDescricao());

								carregarRota(
										filtrarComandosAcaoCobrancaEventualActionForm,
										fachada, objetoSetorComercial
												.getCodigo()
												+ "",localidadeID);
							}
						}
						httpServletRequest.setAttribute("nomeCampo",
								"setorComercialDestinoCD");
					}
				}
			} else {
				// Limpa o campo setorComercialOrigemCD do formulário
				filtrarComandosAcaoCobrancaEventualActionForm
						.setSetorComercialOrigemCD("");
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeSetorComercialOrigem("Informe a localidade da inscrição de origem.");
				httpServletRequest.setAttribute("corSetorComercialOrigem",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialOrigemCD");
			}
		} else {

			filtrarComandosAcaoCobrancaEventualActionForm
					.setInscricaoTipo("destino");

			// Recebe o valor do campo localidadeDestinoID do formulário.
			localidadeID = (String) filtrarComandosAcaoCobrancaEventualActionForm
					.getLocalidadeDestinoID();

			// O campo localidadeOrigem será obrigatório
			if (localidadeID != null
					&& !localidadeID.trim().equalsIgnoreCase("")) {

				setorComercialCD = (String) filtrarComandosAcaoCobrancaEventualActionForm
						.getSetorComercialDestinoCD();

				SetorComercial objetoSetorComercial = fachada
						.obterSetorComercialLocalidade(localidadeID,
								setorComercialCD);

				if (objetoSetorComercial == null) {
					// Setor Comercial nao encontrado
					// Limpa os campos setorComercialDestinoCD,
					// nomeSetorComercialDestino e setorComercialDestinoID do
					// formulário
					filtrarComandosAcaoCobrancaEventualActionForm
							.setSetorComercialDestinoCD("");
					filtrarComandosAcaoCobrancaEventualActionForm
							.setSetorComercialDestinoID("");
					filtrarComandosAcaoCobrancaEventualActionForm
							.setNomeSetorComercialDestino("Setor Comercial Inexistente");
					httpServletRequest.setAttribute("corSetorComercialDestino",
							"exception");
					filtrarComandosAcaoCobrancaEventualActionForm
							.setRotaInicial(null);
					filtrarComandosAcaoCobrancaEventualActionForm
							.setRotaFinal(null);
					httpServletRequest.setAttribute("nomeCampo",
							"setorComercialDestinoCD");
				} else {
					int setorDestino = objetoSetorComercial.getCodigo();

					String setor = (String) filtrarComandosAcaoCobrancaEventualActionForm
							.getSetorComercialOrigemCD();

					if (setor != null && !setor.equals("")) {

						int setorOrigem = new Integer(setor).intValue();
						if (setorDestino < setorOrigem) {

							filtrarComandosAcaoCobrancaEventualActionForm
									.setSetorComercialDestinoCD("");
							filtrarComandosAcaoCobrancaEventualActionForm
									.setSetorComercialDestinoID("");
							// inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
							// .setNomeSetorComercialDestino("Setor Final maior
							// que Inicial");
							httpServletRequest
									.setAttribute("mensagem",
											"Setor Comercial Final menor que o Inicial");
							filtrarComandosAcaoCobrancaEventualActionForm
									.setNomeSetorComercialDestino("");
							httpServletRequest.setAttribute(
									"corSetorComercialDestino", "valor");

							filtrarComandosAcaoCobrancaEventualActionForm
									.setRotaInicial(null);
							filtrarComandosAcaoCobrancaEventualActionForm
									.setRotaFinal(null);
							httpServletRequest.setAttribute("nomeCampo",
									"setorComercialDestinoCD");

						} else {
							// rota
							carregarRota(
									filtrarComandosAcaoCobrancaEventualActionForm,
									fachada, objetoSetorComercial.getCodigo()
											+ "",localidadeID);

							// setor comercial destino
							filtrarComandosAcaoCobrancaEventualActionForm
									.setSetorComercialDestinoCD(String
											.valueOf(objetoSetorComercial
													.getCodigo()));
							filtrarComandosAcaoCobrancaEventualActionForm
									.setSetorComercialDestinoID(String
											.valueOf(objetoSetorComercial
													.getId()));
							filtrarComandosAcaoCobrancaEventualActionForm
									.setNomeSetorComercialDestino(objetoSetorComercial
											.getDescricao());
							httpServletRequest.setAttribute(
									"corSetorComercialDestino", "valor");
							httpServletRequest.setAttribute("nomeCampo",
									"rotaFinal");
						}
					} else {

						carregarRota(
								filtrarComandosAcaoCobrancaEventualActionForm,
								fachada, objetoSetorComercial.getCodigo() + "",localidadeID);

						// setor comercial destino
						filtrarComandosAcaoCobrancaEventualActionForm
								.setSetorComercialDestinoCD(String
										.valueOf(objetoSetorComercial
												.getCodigo()));
						filtrarComandosAcaoCobrancaEventualActionForm
								.setSetorComercialDestinoID(String
										.valueOf(objetoSetorComercial.getId()));
						filtrarComandosAcaoCobrancaEventualActionForm
								.setNomeSetorComercialDestino(objetoSetorComercial
										.getDescricao());
						httpServletRequest.setAttribute(
								"corSetorComercialDestino", "valor");
						httpServletRequest.setAttribute("nomeCampo",
								"rotaFinal");

					}
				}
			} else {
				// Limpa o campo setorComercialDestinoCD do formulário
				filtrarComandosAcaoCobrancaEventualActionForm
						.setSetorComercialDestinoCD("");
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeSetorComercialDestino("Informe a localidade da inscrição de destino.");
				httpServletRequest.setAttribute("corSetorComercialDestino",
						"exception");
				httpServletRequest.setAttribute("nomeCampo",
						"setorComercialDestinoCD");

			}
		}

	}

	/**
	 * Inicializa a Rota
	 * 
	 * @param inserirComandoAcaoCobrancaEventualCriterioRotaActionForm
	 * @param fachada
	 * @param objetoSetorComercial
	 */
	public void carregarRota(
			FiltrarComandosAcaoCobrancaEventualActionForm filtrarComandosAcaoCobrancaEventualActionForm,
			Fachada fachada, String codigoSetorComercial,String idLocalidade) {

		Collection colecaoRota = fachada
				.obterColecaoRotaSetorComercial(codigoSetorComercial,idLocalidade);

		sessao.setAttribute("colecaoRota", colecaoRota);
		filtrarComandosAcaoCobrancaEventualActionForm.setRotaInicial("");
		filtrarComandosAcaoCobrancaEventualActionForm.setRotaFinal("");

	}

	/**
	 * Pesquisa o Setor Comercial
	 * 
	 * @param inscricaoTipo
	 * @param imovelOutrosCriteriosActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void pesquisarCliente(
			String inscricaoTipo,
			FiltrarComandosAcaoCobrancaEventualActionForm filtrarComandosAcaoCobrancaEventualActionForm,
			Fachada fachada, HttpServletRequest httpServletRequest) {

		String idCliente = filtrarComandosAcaoCobrancaEventualActionForm
				.getIdCliente();

		// -------Parte que trata do código quando o usuário tecla enter
		// se o id do cliente for diferente de nulo
		if (idCliente != null
				&& !idCliente.toString().trim().equalsIgnoreCase("")) {

			Cliente cliente = fachada.obterCliente(idCliente);

			if (cliente != null) {
				// O cliente foi encontrado
				filtrarComandosAcaoCobrancaEventualActionForm
						.setIdCliente(cliente.getId().toString());
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeCliente(cliente.getNome());

				sessao.setAttribute("clienteObj", cliente);
			} else {
				httpServletRequest.setAttribute("codigoClienteNaoEncontrado",
						"true");
				filtrarComandosAcaoCobrancaEventualActionForm
						.setNomeCliente("Cliente Inexistente");
				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}

		}

	}

}
