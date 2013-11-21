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

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.FiltroEspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição
 * 
 * 
 * @author Sávio Luiz
 * @created 28 de Julho de 2006
 */
public class ExibirAdicionarSolicitacaoEspecificacaoAction extends GcomAction {
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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("adicionarSolicitacaoEspecificacao");

		String consultarUltimaAlteracao = httpServletRequest
				.getParameter("ultimaAlteracao");

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		AdicionarSolicitacaoEspecificacaoActionForm adicionarSolicitacaoEspecificacaoActionForm = (AdicionarSolicitacaoEspecificacaoActionForm) actionForm;

		if (consultarUltimaAlteracao != null
				&& !consultarUltimaAlteracao.equals("")) {
			long ultimaAlteracaoTime = Long.parseLong(consultarUltimaAlteracao);
			Collection colecaoSolicitacaoTipoEspecificacao = (Collection) sessao
					.getAttribute("colecaoSolicitacaoTipoEspecificacao");
			Iterator iteEspecificacaoServicoTipo = colecaoSolicitacaoTipoEspecificacao
					.iterator();
			while (iteEspecificacaoServicoTipo.hasNext()) {
				SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) iteEspecificacaoServicoTipo
						.next();
				if (solicitacaoTipoEspecificacao.getUltimaAlteracao().getTime() == ultimaAlteracaoTime) {
					// recupera os dados do objeto da coleção
					adicionarSolicitacaoEspecificacaoActionForm
							.setDescricaoSolicitacao(solicitacaoTipoEspecificacao
									.getDescricao());					
					adicionarSolicitacaoEspecificacaoActionForm
							.setPrazoPrevisaoAtendimento(""
									+ solicitacaoTipoEspecificacao
											.getDiasPrazo());
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorPavimentoCalcada(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorPavimentoCalcada());
					adicionarSolicitacaoEspecificacaoActionForm
					.setIndicadorPavimentoRua(""
							+ solicitacaoTipoEspecificacao
									.getIndicadorPavimentoRua());
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorLigacaoAgua(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorLigacaoAgua());					
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorCobrancaMaterial(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorCobrancaMaterial());					
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorParecerEncerramento(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorParecerEncerramento());					
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorGerarDebito(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorGeracaoDebito());
					
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorGerarCredito(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorGeracaoCredito());
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorCliente(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorCliente());
					adicionarSolicitacaoEspecificacaoActionForm
					        .setIndicadorVerificarDebito(""
							        + solicitacaoTipoEspecificacao
									        .getIndicadorVerificarDebito());					
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorMatriculaImovel(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorMatricula());
					adicionarSolicitacaoEspecificacaoActionForm
					        .setIndicadorUrgencia(""
							        + solicitacaoTipoEspecificacao
									        .getIndicadorUrgencia());
					
					if (solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao() != null
							&& !solicitacaoTipoEspecificacao.getEspecificacaoImovelSituacao().equals("")) {
						adicionarSolicitacaoEspecificacaoActionForm
								.setIdSituacaoImovel(""+ solicitacaoTipoEspecificacao
												.getEspecificacaoImovelSituacao().getId());
					}else{
						adicionarSolicitacaoEspecificacaoActionForm.setIdSituacaoImovel("");
					}
					
					//Colocado por Raphael Rossiter em 25/02/2008
					if (solicitacaoTipoEspecificacao.getDebitoTipo() != null) {
						
						adicionarSolicitacaoEspecificacaoActionForm
						.setIdDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getId().toString());
						
						adicionarSolicitacaoEspecificacaoActionForm
						.setDescricaoDebitoTipo(solicitacaoTipoEspecificacao.getDebitoTipo().getDescricao());
						
					}else{
						adicionarSolicitacaoEspecificacaoActionForm
						.setIdDebitoTipo("");
						
						adicionarSolicitacaoEspecificacaoActionForm
						.setDescricaoDebitoTipo("");
					}
					
					
					//Colocado por Raphael Rossiter em 25/02/2008
					if (solicitacaoTipoEspecificacao.getValorDebito() != null){
						
						adicionarSolicitacaoEspecificacaoActionForm
						.setValorDebito(Util.formatarMoedaReal(solicitacaoTipoEspecificacao.getValorDebito()));
					}else{
						adicionarSolicitacaoEspecificacaoActionForm.setValorDebito("");
					}

					//Colocado por Rafael Corrêa em 14/08/2008
					adicionarSolicitacaoEspecificacaoActionForm.setIndicadorEncerramentoAutomatico(
					        String.valueOf(solicitacaoTipoEspecificacao.getIndicadorEncerramentoAutomatico()));
					
					//Colocado por Raphael Rossiter em 14/03/2008
					adicionarSolicitacaoEspecificacaoActionForm.setIndicadorPermiteAlterarValor(
					         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorPermiteAlterarValor()));
					
					//Colocado por Raphael Rossiter em 14/03/2008
					adicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrarJuros(
					         String.valueOf(solicitacaoTipoEspecificacao.getIndicadorCobrarJuros()));					
					
					if (solicitacaoTipoEspecificacao.getUnidadeOrganizacional() != null
							&& !solicitacaoTipoEspecificacao.getUnidadeOrganizacional().equals("")) {
						adicionarSolicitacaoEspecificacaoActionForm
								.setIdUnidadeTramitacao(""+ solicitacaoTipoEspecificacao
												.getUnidadeOrganizacional().getId());
						adicionarSolicitacaoEspecificacaoActionForm
								.setDescricaoUnidadeTramitacao(solicitacaoTipoEspecificacao
										.getUnidadeOrganizacional().getDescricao());
					}else{
						adicionarSolicitacaoEspecificacaoActionForm
						        .setIdUnidadeTramitacao("");
				        adicionarSolicitacaoEspecificacaoActionForm
						        .setDescricaoUnidadeTramitacao("");
					}

					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorGeraOrdemServico(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorGeracaoOrdemServico());

					if (solicitacaoTipoEspecificacao.getServicoTipo() != null
							&& !solicitacaoTipoEspecificacao.getServicoTipo().equals("")) {
						adicionarSolicitacaoEspecificacaoActionForm
								.setIdServicoOS(""+ solicitacaoTipoEspecificacao
												.getServicoTipo().getId());
						adicionarSolicitacaoEspecificacaoActionForm
								.setDescricaoServicoOS(solicitacaoTipoEspecificacao
										.getServicoTipo().getDescricao());
					}else{
						adicionarSolicitacaoEspecificacaoActionForm
						        .setIdServicoOS("");
				        adicionarSolicitacaoEspecificacaoActionForm
						        .setDescricaoServicoOS("");
					}

					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorInformarContaRA(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorInformarContaRA());
					
					adicionarSolicitacaoEspecificacaoActionForm
							.setIndicadorAlterarVencimentoAlternativo(""
									+ solicitacaoTipoEspecificacao
											.getIndicadorAlterarVencimentoAlternativo());
					
                    adicionarEspecificacao( 
                            adicionarSolicitacaoEspecificacaoActionForm,
                            solicitacaoTipoEspecificacao,
                            sessao,
                            fachada,
                            true );				
					
					httpServletRequest.setAttribute(
							"colecaoEspecificacaoServicoTipo",
							solicitacaoTipoEspecificacao
									.getEspecificacaoServicoTipos());

					FiltroEspecificacaoImovelSituacao filtroEspecificacaoImovelSituacao = new FiltroEspecificacaoImovelSituacao();
					Collection colecaoImovelSituacao = fachada.pesquisar(
							filtroEspecificacaoImovelSituacao,
							EspecificacaoImovelSituacao.class.getName());
					httpServletRequest.setAttribute("colecaoImovelSituacao",
							colecaoImovelSituacao);
					httpServletRequest.setAttribute("consultaDados", "SIM");
					if (httpServletRequest.getParameter("atualizar") != null) {
						httpServletRequest.removeAttribute("consultaDados");
						sessao.setAttribute("atualizar", true);
					}

				}
			}
		} else {

			// caso exista o parametro então limpa a sessão e o form
			if (httpServletRequest.getParameter("limpaSessao") != null
					&& !httpServletRequest.getParameter("limpaSessao").equals(
							"")) {

				adicionarSolicitacaoEspecificacaoActionForm
						.setDescricaoSolicitacao("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setPrazoPrevisaoAtendimento("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorPavimentoCalcada("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorLigacaoAgua("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorPavimentoRua("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorCobrancaMaterial("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorParecerEncerramento("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorGerarDebito("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorGerarCredito("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorCliente("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorVerificarDebito("");				
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorMatriculaImovel("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIdSituacaoImovel("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIdUnidadeTramitacao("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setDescricaoUnidadeTramitacao("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setIndicadorGeraOrdemServico("");
				adicionarSolicitacaoEspecificacaoActionForm.setIdServicoOS("");
				adicionarSolicitacaoEspecificacaoActionForm
						.setDescricaoServicoOS("");
				
				//Colocado por Raphael Rossiter em 25/02/2008
				adicionarSolicitacaoEspecificacaoActionForm.setIdDebitoTipo("");
				adicionarSolicitacaoEspecificacaoActionForm.setDescricaoDebitoTipo("");
				adicionarSolicitacaoEspecificacaoActionForm.setValorDebito("");
				adicionarSolicitacaoEspecificacaoActionForm.setIndicadorPermiteAlterarValor("");
				adicionarSolicitacaoEspecificacaoActionForm.setIndicadorCobrarJuros("");
                adicionarSolicitacaoEspecificacaoActionForm.setIdTipoSolicitacao("");
                adicionarSolicitacaoEspecificacaoActionForm.setIdEspecificacao("");
                
                adicionarSolicitacaoEspecificacaoActionForm.setIndicadorInformarContaRA("");

                sessao.removeAttribute("colecaoEspecificacaoServicoTipo");
			}

			// Verifica se o tipoConsulta é diferente de nulo ou vazio.Nesse
			// caso é
			// quando um o retorno da consulta vem para o action ao inves de
			// ir
			// direto para o jsp
			if (httpServletRequest.getParameter("tipoConsulta") != null
					&& !httpServletRequest.getParameter("tipoConsulta").equals(
							"")) {
				// verifica se retornou da pesquisa de unidade de tramite
				if (httpServletRequest.getParameter("tipoConsulta").equals("unidadeAtual")) {

					adicionarSolicitacaoEspecificacaoActionForm
							.setIdUnidadeTramitacao(httpServletRequest
									.getParameter("idCampoEnviarDados"));

					adicionarSolicitacaoEspecificacaoActionForm
							.setDescricaoUnidadeTramitacao(httpServletRequest
									.getParameter("descricaoCampoEnviarDados"));

				}
				// verifica se retornou da pesquisa de tipo de serviço
				if (httpServletRequest.getParameter("tipoConsulta").equals(
						"tipoServico")) {

					adicionarSolicitacaoEspecificacaoActionForm
							.setIdServicoOS(httpServletRequest
									.getParameter("idCampoEnviarDados"));

					adicionarSolicitacaoEspecificacaoActionForm
							.setDescricaoServicoOS(httpServletRequest
									.getParameter("descricaoCampoEnviarDados"));

				}
				
				/*
				 * Colocado por Raphael Rossiter em 25/02/2008
				 * Verifica se retornou da pesquisa de tipo de débito
				 */
				if (httpServletRequest.getParameter("tipoConsulta").equals("tipoDebito")) {

					adicionarSolicitacaoEspecificacaoActionForm
					.setIdDebitoTipo(httpServletRequest.getParameter("idCampoEnviarDados"));

					adicionarSolicitacaoEspecificacaoActionForm
					.setDescricaoDebitoTipo(httpServletRequest.getParameter("descricaoCampoEnviarDados"));
				}
			}

			// -------Parte que trata do código quando o usuário tecla enter
			String idUnidadeInicialTramitacao = adicionarSolicitacaoEspecificacaoActionForm
					.getIdUnidadeTramitacao();
			String descricaoInicialTramitacao = adicionarSolicitacaoEspecificacaoActionForm
					.getDescricaoUnidadeTramitacao();
			String idTipoServicoOS = (String) adicionarSolicitacaoEspecificacaoActionForm
					.getIdServicoOS();
			String descricaoServicoOS = adicionarSolicitacaoEspecificacaoActionForm
					.getDescricaoServicoOS();
			
			//Colocado por Raphael Rossiter em 25/02/2008
			String idDebitoTipo = (String) adicionarSolicitacaoEspecificacaoActionForm
			.getIdDebitoTipo();
			String descricaoDebitoTipo = adicionarSolicitacaoEspecificacaoActionForm
			.getDescricaoDebitoTipo();

			
			// Verifica se o código foi digitado pela primeira vez ou se foi
			// modificado
			if (idUnidadeInicialTramitacao != null
					&& !idUnidadeInicialTramitacao.trim().equals("")
					&& (descricaoInicialTramitacao == null || descricaoInicialTramitacao
							.trim().equals(""))) {

				FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

				filtroUnidadeOrganizacional
						.adicionarParametro(new ParametroSimples(
								FiltroUnidadeOrganizacional.ID,
								idUnidadeInicialTramitacao));

				filtroUnidadeOrganizacional
						.adicionarParametro(new ParametroSimples(
								FiltroUnidadeOrganizacional.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection unidadeOrganizacionalEncontrado = fachada.pesquisar(
						filtroUnidadeOrganizacional,
						UnidadeOrganizacional.class.getName());

				if (unidadeOrganizacionalEncontrado != null
						&& !unidadeOrganizacionalEncontrado.isEmpty()) {
					adicionarSolicitacaoEspecificacaoActionForm
							.setIdUnidadeTramitacao(""
									+ ((UnidadeOrganizacional) ((List) unidadeOrganizacionalEncontrado)
											.get(0)).getId());
					adicionarSolicitacaoEspecificacaoActionForm
							.setDescricaoUnidadeTramitacao(((UnidadeOrganizacional) ((List) unidadeOrganizacionalEncontrado)
									.get(0)).getDescricao());
					httpServletRequest.setAttribute(
							"idUnidadeTramitacaoNaoEncontrado", "true");

					httpServletRequest.setAttribute("nomeCampo",
							"indicadorGeraOrdemServico");

				} else {

					adicionarSolicitacaoEspecificacaoActionForm
							.setIdUnidadeTramitacao("");
					httpServletRequest.setAttribute("nomeCampo",
							"idUnidadeTramitacao");
					httpServletRequest.setAttribute(
							"idUnidadeTramitacaoNaoEncontrado", "exception");
					adicionarSolicitacaoEspecificacaoActionForm
							.setDescricaoUnidadeTramitacao("Unidade Organizacional Inexistente");

				}

			}

			// Verifica se o código foi digitado pela primeira vez ou se foi
			// modificado
			if (idTipoServicoOS != null
					&& !idTipoServicoOS.trim().equals("")
					&& (descricaoServicoOS == null || descricaoServicoOS.trim()
							.equals(""))) {

				FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

				filtroServicoTipo.adicionarParametro(new ParametroSimples(
						FiltroServicoTipo.ID, idTipoServicoOS));

				Collection servicoTipoEncontrado = fachada.pesquisar(
						filtroServicoTipo, ServicoTipo.class.getName());

				if (servicoTipoEncontrado != null
						&& !servicoTipoEncontrado.isEmpty()) {

					// [SF0003] - Validar Tipo Serviço
					fachada.verificarServicoTipoReferencia(new Integer(
							idTipoServicoOS));

					adicionarSolicitacaoEspecificacaoActionForm
							.setIdServicoOS(""
									+ ((ServicoTipo) ((List) servicoTipoEncontrado)
											.get(0)).getId());
					adicionarSolicitacaoEspecificacaoActionForm
							.setDescricaoServicoOS(((ServicoTipo) ((List) servicoTipoEncontrado)
									.get(0)).getDescricao());
					httpServletRequest.setAttribute("idServicoOSNaoEncontrado",
							"true");

					httpServletRequest.setAttribute("nomeCampo",
							"adicionarTipoServico");

				} else {

					adicionarSolicitacaoEspecificacaoActionForm
							.setIdServicoOS("");
					httpServletRequest.setAttribute("nomeCampo", "idServicoOS");
					httpServletRequest.setAttribute("idServicoOSNaoEncontrado",
							"exception");
					adicionarSolicitacaoEspecificacaoActionForm
							.setDescricaoServicoOS("Tipo Serviço Inexistente");

				}

			}
			
			/*
			 * Colocado por Raphael Rossiter em 25/02/2008
			 * Verifica se o código foi digitado pela primeira vez ou se foi  modificado
			 */
			if (idDebitoTipo != null && !idDebitoTipo.trim().equals("") && 
				(descricaoDebitoTipo == null || descricaoDebitoTipo.trim().equals(""))) {

				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

				filtroDebitoTipo.adicionarParametro(new ParametroSimples(
				FiltroDebitoTipo.ID, idDebitoTipo));

				Collection debitoTipoEncontrado = fachada.pesquisar(
				filtroDebitoTipo, DebitoTipo.class.getName());

				if (debitoTipoEncontrado != null && !debitoTipoEncontrado.isEmpty()) {

					adicionarSolicitacaoEspecificacaoActionForm
					.setIdDebitoTipo(idDebitoTipo);
					
					adicionarSolicitacaoEspecificacaoActionForm
					.setDescricaoDebitoTipo(((DebitoTipo) ((List) debitoTipoEncontrado)
					.get(0)).getDescricao());
					
					httpServletRequest.setAttribute("nomeCampo", "valorDebito");

				} else {

					//[FS0007] - Validar Tipo de débito
					adicionarSolicitacaoEspecificacaoActionForm
					.setIdDebitoTipo("");
					
					adicionarSolicitacaoEspecificacaoActionForm
					.setDescricaoDebitoTipo("Tipo de Débito Inexistente");
					
					httpServletRequest.setAttribute("corDebitoTipo", "exception");
					httpServletRequest.setAttribute("nomeCampo", "idDebitoTipo");

				}

			}
            
            adicionarEspecificacao( 
                    adicionarSolicitacaoEspecificacaoActionForm,
                    new SolicitacaoTipoEspecificacao(),
                    sessao,
                    fachada,
                    false );            

			FiltroEspecificacaoImovelSituacao filtroEspecificacaoImovelSituacao = new FiltroEspecificacaoImovelSituacao();
			Collection colecaoImovelSituacao = fachada.pesquisar(
					filtroEspecificacaoImovelSituacao,
					EspecificacaoImovelSituacao.class.getName());
			sessao.setAttribute("colecaoImovelSituacao", colecaoImovelSituacao);

			// -------Fim da Parte que trata do código quando o usuário
			// tecla
			// enter

			// remove o retorno da
			// solicitação_especificação_tipo_servico_adicionar_popup.jsp
			sessao.removeAttribute("retornarTelaPopup");
			sessao
					.removeAttribute("caminhoRetornoTelaPesquisaUnidadeOrganizacional");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaTipoServico");            
		}
        
		return retorno;
	}

    /**
     * 
     * [UC0401] Manter tipo de solicitacao com especificações
     * Mostra os dados necessários para a inclusão do novo RA
     *
     * @author bruno
     * @date 13/04/2009
     *
     * @param atualizarAdicionarSolicitacaoEspecificacaoActionForm
     * @param solicitacaoTipoEspecificacao
     * @param sessao
     */
    private void adicionarEspecificacao( 
            AdicionarSolicitacaoEspecificacaoActionForm adicionarSolicitacaoEspecificacaoActionForm,
            SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao,
            HttpSession sessao,
            Fachada fachada,
            boolean trocou ){
        
        if ( trocou ){
            if ( solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA() != null ){
                adicionarSolicitacaoEspecificacaoActionForm.setIdEspecificacao( "" + solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA().getId() );
            } else {
                adicionarSolicitacaoEspecificacaoActionForm.setIdEspecificacao( "" );
                adicionarSolicitacaoEspecificacaoActionForm.setIdTipoSolicitacao( "" );
            }
        }
        
       FiltroSolicitacaoTipo filtro = new FiltroSolicitacaoTipo();
       filtro.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipo.INDICADOR_USO_SISTEMA, 2 ) );
       filtro.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipo.INDICADOR_USO, 1 ) );
       filtro.setCampoOrderBy( "descricao" );
       Collection<SolicitacaoTipo> colSolTip = fachada.pesquisar( filtro, SolicitacaoTipo.class.getName() );
       
       sessao.setAttribute( "colecaoTipoSolicitacao", colSolTip );                            
    
       // Verificamos se o tipo de especificação já foi informado
       if ( solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA() != null ){
           
           // Pesquisamos qual o tipo de solicitacao desta especificação
           filtro.limparCamposOrderBy();
           filtro.limparListaParametros();
           filtro.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipo.ID, solicitacaoTipoEspecificacao.getSolicitacaoTipoEspecificacaoNovoRA().getSolicitacaoTipo().getId() ) );
           colSolTip = fachada.pesquisar( filtro, SolicitacaoTipo.class.getName() );
           
           SolicitacaoTipo solicitacaoTipo = ( SolicitacaoTipo ) Util.retonarObjetoDeColecao( colSolTip );                                
           adicionarSolicitacaoEspecificacaoActionForm.setIdTipoSolicitacao( solicitacaoTipo.getId()+"" );
       }
       
       Collection<SolicitacaoTipoEspecificacao> colSolTipEsp = new ArrayList();
       
       if ( !adicionarSolicitacaoEspecificacaoActionForm.getIdTipoSolicitacao().equals( "" ) ){
           FiltroSolicitacaoTipoEspecificacao filtro2 = new FiltroSolicitacaoTipoEspecificacao();
           filtro2.adicionarParametro( new ParametroSimples( FiltroSolicitacaoTipoEspecificacao.SOLICITACAO_TIPO, adicionarSolicitacaoEspecificacaoActionForm.getIdTipoSolicitacao() ) );
           filtro2.setCampoOrderBy( "descricao" );
           colSolTipEsp = fachada.pesquisar( filtro2, SolicitacaoTipoEspecificacao.class.getName() );
       }
       
       sessao.setAttribute( "colecaoEspecificacao", colSolTipEsp );        
    }
}
