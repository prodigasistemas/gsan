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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.atendimentopublico.ordemservico.FiltroOSReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.FiltroOrdemServicoUnidade;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.OrdemServicoUnidade;
import gcom.atendimentopublico.ordemservico.OsReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.bean.ObterDescricaoSituacaoOSHelper;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.AtendimentoRelacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
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
 * Action que define o pré-processamento da página de encerra OS caso precise
 * entrar na tela
 * 
 * @author Sávio Luiz
 * @created 18/09/2006
 */
public class ExibirEncerrarOrdemServicoPopupAction extends GcomAction {
	/**
	 * Execute do Consultar OS Popup
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("encerrarOrdemServicoPopup");

		EncerrarOrdemServicoActionForm encerrarOrdemServicoActionForm = (EncerrarOrdemServicoActionForm) actionForm;

		if (httpServletRequest.getParameter("redirecionarPagina") != null
				&& !httpServletRequest.getParameter("redirecionarPagina").equals("")) {
			String redirecionarPagina = httpServletRequest.getParameter("redirecionarPagina");
			if(redirecionarPagina.equals("exibirGerarOSInserirRA")){
				httpServletRequest.setAttribute("numeroRA",encerrarOrdemServicoActionForm.getNumeroRA());
				httpServletRequest.setAttribute("numeroOS",encerrarOrdemServicoActionForm.getNumeroOS());
				httpServletRequest.setAttribute("veioEncerrarOS","SIM");
			}

			retorno = actionMapping.findForward(redirecionarPagina);
			return retorno;

		}

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		Integer numeroOS = Util.converterStringParaInteger(httpServletRequest.getParameter("numeroOS"));
		String idMotivoEncerramento = httpServletRequest.getParameter("idMotivoEncerramento");
		String dataEncerramento = httpServletRequest.getParameter("dataEncerramento");
		String dataRetorno = httpServletRequest.getParameter("dataRetorno");
		String carregarCampos = httpServletRequest.getParameter("carregarCampos");
		String retornoConsulta = httpServletRequest.getParameter("retornoConsulta");
		String retornoTela = httpServletRequest.getParameter("retornoTela");

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		if (retornoTela != null && !retornoTela.equals("")) {
			sessao.setAttribute("retornoTela", retornoTela);
		}

		String carregarCamposComReferencia = httpServletRequest.getParameter("carregarCamposComReferencia");

		String pesquisaServicoTipo = httpServletRequest.getParameter("pesquisaServicoTipo");

		// caso tenha escolhido um tipo de serviço
		if (pesquisaServicoTipo != null && !pesquisaServicoTipo.equals("")) {
			// verifica se veio do resultado da pesquisa de serviço.
			if (httpServletRequest.getParameter("tipoConsulta") != null
					&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
				encerrarOrdemServicoActionForm.setIdServicoTipo(httpServletRequest.getParameter("idCampoEnviarDados"));
				encerrarOrdemServicoActionForm.setDescricaoServicoTipo(httpServletRequest.getParameter("descricaoCampoEnviarDados"));

				httpServletRequest.setAttribute("nomeCampo", "ButtonAtividade");

			} else {
				// valida enter
				String idServicoTipo = encerrarOrdemServicoActionForm.getIdServicoTipo();
				String descricaoServicoTipo = encerrarOrdemServicoActionForm.getDescricaoServicoTipo();
				if ((idServicoTipo != null && !idServicoTipo.equals(""))
						&& (descricaoServicoTipo == null || descricaoServicoTipo.equals(""))) {

					FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

					filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, idServicoTipo));
					filtroServicoTipo.adicionarParametro(new ParametroSimples(
							FiltroServicoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection servicoTipoEncontrado = fachada.pesquisar(
							filtroServicoTipo, ServicoTipo.class.getName());

					if (servicoTipoEncontrado != null && !servicoTipoEncontrado.isEmpty()) {
						ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(servicoTipoEncontrado);
						// O serviço tipo foi encontrado
						encerrarOrdemServicoActionForm.setIdServicoTipo("" + servicoTipo.getId());
						encerrarOrdemServicoActionForm.setDescricaoServicoTipo(servicoTipo.getDescricao());
						httpServletRequest.setAttribute("idServicoTipoNaoEncontrado", "true");

						httpServletRequest.setAttribute("nomeCampo","ButtonAtividade");

					} else {

						encerrarOrdemServicoActionForm.setIdServicoTipo("");
						httpServletRequest.setAttribute("nomeCampo","idServicoTipo");
						httpServletRequest.setAttribute("idServicoTipoNaoEncontrado", "exception");
						encerrarOrdemServicoActionForm.setDescricaoServicoTipo("Tipo Serviço Inexistente");

					}
					
				}
			}
		} else {
			if (retornoConsulta == null || retornoConsulta.equals("")) {
				if (carregarCamposComReferencia == null
						|| carregarCamposComReferencia.equals("")) {
					// caso o mitivo de encerramento não tenha sido mudado
					if (carregarCampos == null || carregarCampos.equals("")) {
						// [FS0001] - Verificar unidade do usuário
						fachada
								.verificarUnidadeUsuario(numeroOS,
										usuarioLogado);
						OrdemServico ordemServico = pesquisarOrdemServico(numeroOS, httpServletRequest);
						// limpa do campos do form
						encerrarOrdemServicoActionForm.resetarConsultarDadosOSPopup();
						// seta o id do motivo encerramento
						if (idMotivoEncerramento != null && !idMotivoEncerramento.equals("")) {
							encerrarOrdemServicoActionForm.setIdMotivoEncerramento(idMotivoEncerramento);
						}
						
						if (ordemServico.getImovel() != null && ordemServico.getImovel().getId() != null &&
								ordemServico.getServicoTipo() != null && ordemServico.getServicoTipo().getDebitoTipo() != null) {
							
								// Alterado por Rafael Corrêa em 06/11/2008
								FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
								filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.DEBITO_TIPO_ID, ordemServico.getServicoTipo().getDebitoTipo().getId()));
								filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.EMISSAO_GUIA_PAGAMENTO, ordemServico.getDataGeracao()));
								filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.IMOVEL_ID, ordemServico.getImovel().getId()));
							
								Collection colecaoGuiasPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
							
								if (colecaoGuiasPagamento != null && !colecaoGuiasPagamento.isEmpty()) {
									encerrarOrdemServicoActionForm.setMostrarAlert("sim");
								}
							
							}
						
						/*
						 * Alterado por Raphael Rossiter em 01/08/2007 (Analista: Rosana Carvalho)
						 * 
						 * OBJETIVO: Não colocar a data e a hora atual como sugestão para a data e
						 * a hora de encerramento da Ordem de Serviço
						 */
						if (dataEncerramento != null
							&& dataEncerramento.equals("")) {
							encerrarOrdemServicoActionForm
							.setDataEncerramento(dataEncerramento);
						} 
						
                        //checar se a ordem de serviço é assinalada com encerramento automático
                        if (ordemServico.getIndicadorEncerramentoAutomatico() != null &&
                                ordemServico.getIndicadorEncerramentoAutomatico().equals(ConstantesSistema.SIM)){
                            throw new ActionServletException("atencao.indicador_encerramento_automatico_ordem_servico");
                        }
                        
						// Dados Gerais da OS
						encerrarOrdemServicoActionForm.setNumeroOS(ordemServico.getId()	+ "");
						encerrarOrdemServicoActionForm.setSituacaoOSId(ordemServico.getSituacao() + "");
						// Caso de Uso [UC0454]
						ObterDescricaoSituacaoOSHelper situacaoOS = fachada
								.obterDescricaoSituacaoOS(ordemServico.getId());
						encerrarOrdemServicoActionForm.setSituacaoOS(situacaoOS
								.getDescricaoSituacao());
						if (ordemServico.getRegistroAtendimento() != null) {
							encerrarOrdemServicoActionForm
									.setNumeroRA(ordemServico
											.getRegistroAtendimento().getId()
											+ "");
							// Caso de Uso [UC0420]
							ObterDescricaoSituacaoRAHelper situacaoRA = fachada
									.obterDescricaoSituacaoRA(ordemServico
											.getRegistroAtendimento().getId());
							encerrarOrdemServicoActionForm
									.setSituacaoRA(situacaoRA
											.getDescricaoSituacao());
						}
						if (ordemServico.getCobrancaDocumento() != null) {
							encerrarOrdemServicoActionForm
									.setNumeroDocumentoCobranca(ordemServico
											.getCobrancaDocumento().getId()
											+ "");
						}
						encerrarOrdemServicoActionForm.setDataGeracao(Util
								.formatarData(ordemServico.getDataGeracao()));
						encerrarOrdemServicoActionForm
								.setTipoServicoOSId(ordemServico
										.getServicoTipo().getId()
										+ "");

						if (ordemServico.getServicoTipo() != null
								&& !ordemServico.getServicoTipo().equals("")) {
							encerrarOrdemServicoActionForm
									.setTipoServicoOSDescricao(ordemServico
											.getServicoTipo().getDescricao());
							encerrarOrdemServicoActionForm
									.setIndicadorPavimento(""
											+ ordemServico.getServicoTipo()
													.getIndicadorPavimento());
							encerrarOrdemServicoActionForm
									.setIndicadorAtualizaComercial(""
											+ ordemServico
													.getServicoTipo()
													.getIndicadorAtualizaComercial());
							encerrarOrdemServicoActionForm
							.setIndicadorVistoriaServicoTipo(""
									+ ordemServico.getServicoTipo()
											.getIndicadorVistoria());

							if (ordemServico.getServicoTipo()
									.getServicoTipoReferencia() != null
									&& !ordemServico.getServicoTipo()
											.getServicoTipoReferencia().equals(
													"")) {
								encerrarOrdemServicoActionForm
										.setTipoServicoReferenciaId(""
												+ ordemServico
														.getServicoTipo()
														.getServicoTipoReferencia()
														.getId());
								encerrarOrdemServicoActionForm
								.setTipoServicoReferenciaIndicadorFiscalizacao(""
										+ ordemServico.getServicoTipo().getServicoTipoReferencia()
												.getIndicadorFiscalizacao());
								encerrarOrdemServicoActionForm
										.setServicoTipoReferenciaDescricao(ordemServico
												.getServicoTipo()
												.getServicoTipoReferencia()
												.getDescricao());
                                
                                encerrarOrdemServicoActionForm.setIndicadorDiagnostico(ordemServico
                                        .getServicoTipo()
                                        .getServicoTipoReferencia().getIndicadorDiagnostico() + "");
							}

						}
						// dados da referencia do serviço tipo da OS
						if (ordemServico.getServicoTipoReferencia() != null
								&& !ordemServico.getServicoTipoReferencia()
										.equals("")) {
							encerrarOrdemServicoActionForm
									.setServicoTipoReferenciaOS(""
											+ ordemServico
													.getServicoTipoReferencia()
													.getId());
							encerrarOrdemServicoActionForm
									.setServicoTipoReferenciaOSDescricao(""
											+ ordemServico
													.getServicoTipoReferencia()
													.getDescricao());
						}
						if (ordemServico.getOsReferencia() != null) {
							sessao.setAttribute("osReferencia", ordemServico
									.getOsReferencia());
							if (ordemServico.getOsReferencia().getServicoTipo() != null
									&& !ordemServico.getOsReferencia()
											.getServicoTipo().equals("")) {
								encerrarOrdemServicoActionForm
										.setTipoServicoReferenciaDescricao(ordemServico
												.getOsReferencia()
												.getServicoTipo()
												.getDescricao());

							}
						}

						encerrarOrdemServicoActionForm
								.setObservacao(ordemServico.getObservacao());
						encerrarOrdemServicoActionForm
								.setValorServicoOriginal(ordemServico
										.getValorOriginal()
										+ "");
						encerrarOrdemServicoActionForm
								.setPrioridadeOriginal(ordemServico
										.getServicoTipoPrioridadeOriginal()
										.getDescricao());
						encerrarOrdemServicoActionForm
								.setPrioridadeAtual(ordemServico
										.getServicoTipoPrioridadeAtual()
										.getDescricao()
										+ "");
						OrdemServicoUnidade ordemServicoUnidade = consultarOrdemServicoUnidade(
								ordemServico.getId(),
								AtendimentoRelacaoTipo.ABRIR_REGISTRAR);
						if (ordemServicoUnidade != null) {
							encerrarOrdemServicoActionForm
									.setUnidadeGeracaoId(ordemServicoUnidade
											.getUnidadeOrganizacional().getId()
											+ "");
							encerrarOrdemServicoActionForm
									.setUnidadeGeracaoDescricao(ordemServicoUnidade
											.getUnidadeOrganizacional()
											.getDescricao());
							encerrarOrdemServicoActionForm
									.setUsuarioGeracaoId(ordemServicoUnidade
											.getUsuario().getId()
											+ "");
							encerrarOrdemServicoActionForm
									.setUsuarioGeracaoNome(ordemServicoUnidade
											.getUsuario().getNomeUsuario());
						}
						if (ordemServico.getDataEmissao() != null) {
							encerrarOrdemServicoActionForm
									.setDataUltimaEmissao(Util
											.formatarData(ordemServico
													.getDataEmissao()));
						}
						encerrarOrdemServicoActionForm
								.setDataRoteiro(dataRetorno);

						encerrarOrdemServicoActionForm
								.setUltimaAlteracao(ordemServico
										.getUltimaAlteracao());
						FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
						filtroAtendimentoMotivoEncerramento
								.adicionarParametro(new ParametroSimples(
										FiltroAtendimentoMotivoEncerramento.INDICADOR_USO,
										ConstantesSistema.INDICADOR_USO_ATIVO));
						filtroAtendimentoMotivoEncerramento
								.adicionarParametro(new ParametroSimples(
										FiltroAtendimentoMotivoEncerramento.INDICADOR_DUPLICIDADE,
										AtendimentoMotivoEncerramento.INDICADOR_DUPLICIDADE_INATIVO));
						Collection colecaoAtendimentoMotivoEncerrado = fachada
								.pesquisar(filtroAtendimentoMotivoEncerramento,
										AtendimentoMotivoEncerramento.class
												.getName());
						sessao.setAttribute(
								"colecaoAtendimentoMotivoEncerrado",
								colecaoAtendimentoMotivoEncerrado);
						
						
						FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
						filtroServicoTipo.adicionarParametro(new ParametroSimples( FiltroServicoTipo.ID, 
								encerrarOrdemServicoActionForm.getTipoServicoOSId()));
						
						Collection colecaoServicoTipo = fachada
											.pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
						
						if ( colecaoServicoTipo != null && !colecaoServicoTipo.equals("") ) {
							ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);
							sessao.setAttribute( "PavimentoCalcadaObrigatorio", "false" );
							sessao.setAttribute( "PavimentoRuaObrigatorio", "false" );
							if ( servicoTipo.getIndicadorPavimentoRua().intValue() == ConstantesSistema.SIM.intValue() ) {
								sessao.setAttribute( "PavimentoRuaObrigatorio", "SIM" );
							} 
							if ( servicoTipo.getIndicadorPavimentoCalcada().intValue() == ConstantesSistema.SIM.intValue() ) {
								sessao.setAttribute( "PavimentoCalcadaObrigatorio", "SIM" );
							}
							
						}
						
						
						//IndicadorPavimentoRua
						encerrarOrdemServicoActionForm.setIndicadorPavimentoRua(""+ ordemServico.getServicoTipo().getIndicadorPavimentoRua());							
						
						FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
						filtroPavimentoRua.adicionarParametro(new ParametroSimples(
								FiltroPavimentoRua.INDICADOR_USO,
										ConstantesSistema.INDICADOR_USO_ATIVO));
						
						Collection colecaoPavimentoRua = fachada
								.pesquisar(filtroPavimentoRua,PavimentoRua.class.getName());
						
						sessao.setAttribute("colecaoPavimentoRua",colecaoPavimentoRua);

					
						//IndicadorPavimentoCalcada
						encerrarOrdemServicoActionForm.setIndicadorPavimentoCalcada(""+ ordemServico.getServicoTipo()
										.getIndicadorPavimentoCalcada());
											
				
						FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
						filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(
						FiltroPavimentoCalcada.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));
				
						Collection colecaoPavimentoCalcada = fachada
						.pesquisar(filtroPavimentoCalcada,PavimentoCalcada.class.getName());
				
						sessao.setAttribute("colecaoPavimentoCalcada",colecaoPavimentoCalcada);
						
						/**
						 * Verifica se a ordem de servico é do tipo pavimento
						 * @author Arthur Carvalho
						 * @date 26/05/2010
						 */
						if ( ordemServico.getServicoTipo().getIndicadorPavimentoRua().equals(ConstantesSistema.SIM)) {
							
							//SB0006 – Obter Unidade Repavimentadora do Município] 
							UnidadeOrganizacional unidadeOrganizacional = null;
							String tipoPesquisa = "";
							unidadeOrganizacional = fachada.obterUnidadeRepavimentadorAPartirMunicipio(ordemServico,
									tipoPesquisa);
							encerrarOrdemServicoActionForm.setIdUnidadeRepavimentadora("-1");
							//[FS0010] – Verificar existência da unidade repavimentadora
							if(unidadeOrganizacional != null && !unidadeOrganizacional.equals("") ) {
								encerrarOrdemServicoActionForm.setIdUnidadeRepavimentadora(
										unidadeOrganizacional.getId().toString());
								
								encerrarOrdemServicoActionForm.setDescricaoUnidadeRepavimentadora(
										unidadeOrganizacional.getDescricao());
							} 
						
							pesquisaUnidadeOrganizacional(encerrarOrdemServicoActionForm, fachada, sessao);
						}

						if (encerrarOrdemServicoActionForm
								.getIdMotivoEncerramento() != null
								&& !encerrarOrdemServicoActionForm
										.getIdMotivoEncerramento().equals("")) {
							Iterator iteAtendimentoMotivoEncerramento = colecaoAtendimentoMotivoEncerrado
									.iterator();
							while (iteAtendimentoMotivoEncerramento.hasNext()) {
								AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento) iteAtendimentoMotivoEncerramento
										.next();
								if (atendimentoMotivoEncerramento.getId() != null
										&& atendimentoMotivoEncerramento
												.getId()
												.equals(
														Util
																.converterStringParaInteger(encerrarOrdemServicoActionForm
																		.getIdMotivoEncerramento()))) {
									encerrarOrdemServicoActionForm
											.setIndicadorExecucao(""
													+ atendimentoMotivoEncerramento
															.getIndicadorExecucao());

									break;
								}
							}

						} else {
							encerrarOrdemServicoActionForm
									.setIndicadorExecucao("");
						}

					} else {

						if (encerrarOrdemServicoActionForm
								.getIdMotivoEncerramento() != null
								&& !encerrarOrdemServicoActionForm
										.getIdMotivoEncerramento().equals("")) {

							Collection colecaoAtendimentoMotivoEncerrado = (Collection) sessao
									.getAttribute("colecaoAtendimentoMotivoEncerrado");
							Iterator iteAtendimentoMotivoEncerramento = colecaoAtendimentoMotivoEncerrado
									.iterator();
							while (iteAtendimentoMotivoEncerramento.hasNext()) {
								AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = (AtendimentoMotivoEncerramento) iteAtendimentoMotivoEncerramento
										.next();
								if (atendimentoMotivoEncerramento.getId() != null
										&& atendimentoMotivoEncerramento
												.getId()
												.equals(
														Util
																.converterStringParaInteger(encerrarOrdemServicoActionForm
																		.getIdMotivoEncerramento()))) {

									encerrarOrdemServicoActionForm
											.setIndicadorExecucao(""
													+ atendimentoMotivoEncerramento
															.getIndicadorExecucao());
									// 4.6 caso o indicador de execução seja
									// igual a
									// sim(1)
									if (atendimentoMotivoEncerramento
											.getIndicadorExecucao() == AtendimentoMotivoEncerramento.INDICADOR_EXECUCAO_SIM) {
										// 4.6.2 caso o serviço tipo da ordem de
										// serviço
										// tenha referência
										if (encerrarOrdemServicoActionForm
												.getTipoServicoReferenciaId() != null
												&& !encerrarOrdemServicoActionForm
														.getTipoServicoReferenciaId()
														.equals(""))
											encerrarComExecucaoComReferencia(
													fachada,
													sessao,
													httpServletRequest,
													encerrarOrdemServicoActionForm,
													numeroOS);
									}
									break;
								}
							}
						} else {
							encerrarOrdemServicoActionForm
									.setIndicadorExecucao("");
							encerrarOrdemServicoActionForm.setPavimento("");
							encerrarOrdemServicoActionForm.setObservacao("");
							encerrarOrdemServicoActionForm
									.setServicoTipoObrigatorio("");
							encerrarOrdemServicoActionForm
									.setIndicadorDeferimento("");
							encerrarOrdemServicoActionForm.setIdServicoTipo("");
							encerrarOrdemServicoActionForm
									.setDescricaoServicoTipo("");
							encerrarOrdemServicoActionForm
									.setIdTipoRetornoReferida("");
							sessao.removeAttribute("colecaoServicoTipo");
						}

					}
				} else {
					// caso seja mudado o Tipo de Retorno Referida
					encerrarComExecucaoComReferencia(fachada, sessao,
							httpServletRequest, encerrarOrdemServicoActionForm,
							numeroOS);
				}
			} else {
				sessao.removeAttribute("caminhoRetornoDadosAtividadesOS");
				if (retornoConsulta.equals("informarOS")) {
					httpServletRequest.setAttribute("nomeCampo",
							"ButtonOSFiscalizacao");
				}
			}
		}
		
		/*
		 * Colocado por Raphael Rossiter em 01/08/2007
		 * OBJETIVO: Informar qual o campo receberá o foco no carregamento, caso nenhum já tenh sido
		 * informado 
		 */
		if (httpServletRequest.getAttribute("nomeCampo") == null){
			httpServletRequest.setAttribute("nomeCampo", "dataEncerramento");
		}
		

		if(encerrarOrdemServicoActionForm.getIndicadorServicoAceito() == null ||
				encerrarOrdemServicoActionForm.getIndicadorServicoAceito().equalsIgnoreCase("")){
			encerrarOrdemServicoActionForm.setIndicadorServicoAceito("1");
		}
		
		return retorno;
	}
	
	/**
	 * Consulta a ordem de serviço pelo id informado
	 * 
	 * @author Sávio Luiz
	 * @created 22/03/2007
	 */
	private void validarOrdemServicoFiscalizacao(OrdemServico ordemServico) {

		if (ordemServico.getServicoTipo() != null
				&& ordemServico.getServicoTipo()
						.getIndicadorFiscalizacaoInfracao() == ConstantesSistema.INDICADOR_USO_ATIVO) {

			if (ordemServico.getFiscalizacaoSituacao() == null
					|| ordemServico.getFiscalizacaoSituacao().getId() == null || ordemServico
							.getFiscalizacaoSituacao().getId().equals("")) {
				throw new ActionServletException("atencao.ordem_servico_fiscalizacao_imovel");
			}
		}
	}

	/**
	 * Consulta a ordem de serviço pelo id informado
	 * 
	 * @author Sávio Luiz
	 * @created 18/09/2006
	 */
	private OrdemServico pesquisarOrdemServico(Integer id, HttpServletRequest httpServletRequest) {
		Fachada fachada = Fachada.getInstancia();
		OrdemServico retorno = fachada.consultarDadosOrdemServico(id);
		if (retorno == null) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Ordem de Serviço");
		}
		
		String veioAcompanhamento = httpServletRequest.getParameter("veioAcompanhamentoRoteiro");
		
		if(veioAcompanhamento != null && !veioAcompanhamento.equals(null) && !veioAcompanhamento.equals("true")){
			validarOrdemServicoFiscalizacao(retorno);
		}
		return retorno;
	}

	/**
	 * Consulta a Ordem Serviço Unidade pelo id do OS e Tipo (1=ABRIR/REGISTRAR
	 * e 3-ENCERRAR)
	 * 
	 * @author Sávio luiz
	 * @date 18/09/2006
	 */
	private OrdemServicoUnidade consultarOrdemServicoUnidade(Integer idOS,
			Integer idAtendimentoTipo) {

		OrdemServicoUnidade retorno = null;

		Fachada fachada = Fachada.getInstancia();

		Collection colecaoOrdemServicoUnidade = null;

		FiltroOrdemServicoUnidade filtroOrdemServicoUnidade = new FiltroOrdemServicoUnidade();
		filtroOrdemServicoUnidade.adicionarParametro(new ParametroSimples(
				FiltroOrdemServicoUnidade.ORDEM_SERVICO_ID, idOS));
		filtroOrdemServicoUnidade.adicionarParametro(new ParametroSimples(
				FiltroOrdemServicoUnidade.ATENDIMENTO_RELACAO_TIPO_ID,
				idAtendimentoTipo));

		filtroOrdemServicoUnidade
				.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacional");
		filtroOrdemServicoUnidade
				.adicionarCaminhoParaCarregamentoEntidade("usuario");

		colecaoOrdemServicoUnidade = fachada.pesquisar(
				filtroOrdemServicoUnidade, OrdemServicoUnidade.class.getName());
		if (colecaoOrdemServicoUnidade != null
				&& !colecaoOrdemServicoUnidade.isEmpty()) {
			retorno = (OrdemServicoUnidade) Util
					.retonarObjetoDeColecao(colecaoOrdemServicoUnidade);
		}

		return retorno;
	}

	private void encerrarComExecucaoComReferencia(Fachada fachada,
			HttpSession sessao, HttpServletRequest httpServletRequest,
			EncerrarOrdemServicoActionForm encerrarOrdemServicoActionForm,
			Integer numeroOS) {
		// verifica a existencia da coleção na sessão
		Collection colecaoOSReferidaRetornoTipo = (Collection) sessao
				.getAttribute("colecaoOSReferidaRetornoTipo");
		// caso não exista então pesquisa na base
		if (colecaoOSReferidaRetornoTipo == null
				|| colecaoOSReferidaRetornoTipo.isEmpty()) {
			FiltroOSReferidaRetornoTipo filtroOSReferidaRetornoTipo = new FiltroOSReferidaRetornoTipo(
					FiltroOSReferidaRetornoTipo.DESCRICAO);
			filtroOSReferidaRetornoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroOSReferidaRetornoTipo.INDICADOR_USO,
							ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroOSReferidaRetornoTipo
					.adicionarParametro(new ParametroSimples(
							FiltroOSReferidaRetornoTipo.ID_SERVICO_TIPO_REFERENCIA,
							encerrarOrdemServicoActionForm
									.getTipoServicoReferenciaId()));
			colecaoOSReferidaRetornoTipo = fachada.pesquisar(
					filtroOSReferidaRetornoTipo, OsReferidaRetornoTipo.class
							.getName());
			sessao.setAttribute("colecaoOSReferidaRetornoTipo",
					colecaoOSReferidaRetornoTipo);
		} else {
			// verifica se foi escolhida um tipo de retorno referida
			if (encerrarOrdemServicoActionForm.getIdTipoRetornoReferida() != null
					&& !encerrarOrdemServicoActionForm
							.getIdTipoRetornoReferida().equals("")) {
				Integer idTipoRetornoReferida = Util
						.converterStringParaInteger(encerrarOrdemServicoActionForm
								.getIdTipoRetornoReferida());
				Iterator iteratorOSReferidaRetorno = colecaoOSReferidaRetornoTipo
						.iterator();
				while (iteratorOSReferidaRetorno.hasNext()) {
					OsReferidaRetornoTipo osReferidaRetornoTipo = (OsReferidaRetornoTipo) iteratorOSReferidaRetorno
							.next();
					// procura pelo id o objeto que foi escolhido na coleção que
					// está na sessão
					if (osReferidaRetornoTipo.getId() != null
							&& osReferidaRetornoTipo.getId().equals(
									idTipoRetornoReferida)) {

						encerrarOrdemServicoActionForm
								.setIndicadorDeferimento(""
										+ osReferidaRetornoTipo
												.getIndicadorDeferimento());

						// 9.1 indicador de deferimento igual a sim(1)
						if (osReferidaRetornoTipo.getIndicadorDeferimento() == OsReferidaRetornoTipo.INDICADOR_DEFERIMENTO_SIM) {

							encerrarOrdemServicoActionForm
									.setServicoTipoObrigatorio("SIM");

							encerrarOrdemServicoActionForm
									.setIndicadorTrocaServico(""
											+ osReferidaRetornoTipo
													.getIndicadorTrocaServico());

							// 9.1.2 indicador de serviço de troca igual a
							// sim(1)
							if (osReferidaRetornoTipo
									.getIndicadorTrocaServico() == OsReferidaRetornoTipo.INDICADOR_TROCA_SERVICO_SIM) {
								// 9.1.2.1 caso a ordem de serviço tenha
								// referência
								if (encerrarOrdemServicoActionForm
										.getNumeroOSReferencia() != null
										&& !encerrarOrdemServicoActionForm
												.getNumeroOSReferencia()
												.equals("")) {

									Collection colecaoServicoTipo = fachada
											.pesquisarColecaoServicoTipo(numeroOS);
									if (colecaoServicoTipo != null
											&& !colecaoServicoTipo.isEmpty()) {
										sessao.setAttribute(
												"colecaoServicoTipo",
												colecaoServicoTipo);
									}
								}
								// 9.1.3 serviço tipo não é obrigatório então
								// não mostra o label
							} else {
								encerrarOrdemServicoActionForm
										.setServicoTipoObrigatorio("NAO");
								sessao.removeAttribute("colecaoServicoTipo");
							}
							// 9.2 indicador de deferimento igual a não(2)
						} else {
							// [FS0003] - Alerta de Indeferimento
							httpServletRequest
									.setAttribute("atencaoIndeferimento",
											"O Tipo de Retorno selecionado é de indeferimento.");
							encerrarOrdemServicoActionForm
									.setServicoTipoObrigatorio("NAO");
							sessao.removeAttribute("colecaoServicoTipo");
						}
						break;
					}

				}
			} else {
				encerrarOrdemServicoActionForm.setServicoTipoObrigatorio("");
				encerrarOrdemServicoActionForm.setIndicadorDeferimento("");
				encerrarOrdemServicoActionForm.setIdServicoTipo("");
				encerrarOrdemServicoActionForm.setDescricaoServicoTipo("");
				encerrarOrdemServicoActionForm.setPavimento("");
				encerrarOrdemServicoActionForm.setIdTipoRetornoReferida("");
				sessao.removeAttribute("colecaoServicoTipo");

			}
		}
	}
//	Pesquisa Unidade Repavimentadora(unidadeORganizacional)
	private UnidadeOrganizacional pesquisaUnidadeOrganizacional(EncerrarOrdemServicoActionForm form,
			Fachada fachada, HttpSession sessao){
		
		UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
		

		String idUnidade = form.getIdUnidadeRepavimentadora();
		if ( idUnidade != null && !idUnidade.equals("-1") ) {
		
			
			FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.ID, idUnidade));
			filtro.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			//Unidade Tipo R = REPAVIMENTADORA.
			filtro.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO, "R" ) );
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacional.UNIDADE_TIPO);

			Collection colecaoUnidadeOrgazanicional = fachada.pesquisar(filtro, UnidadeOrganizacional.class.getName());
			
			//Sugestao de unidade para o usuario
			if (colecaoUnidadeOrgazanicional != null && !colecaoUnidadeOrgazanicional.isEmpty()) {
				
				UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrgazanicional);
				form.setIdUnidadeRepavimentadora(unidade.getId().toString());
			}	

			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			//Unidade Tipo R = REPAVIMENTADORA.
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO, "R" ) );
			filtroUnidadeOrganizacional.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacional.UNIDADE_TIPO);

			Collection colecaoUnidade = fachada.pesquisar(filtroUnidadeOrganizacional, 
					UnidadeOrganizacional.class.getName());

			if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {
				sessao.setAttribute("colecaoUnidadeOrgazanicional", colecaoUnidade);					
			}
			
		}else {
			FiltroUnidadeOrganizacional filtro = new FiltroUnidadeOrganizacional();
			filtro.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
			//Unidade Tipo R = REPAVIMENTADORA.
			filtro.adicionarParametro(new ParametroSimples(
					FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO, "R" ) );
			filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroUnidadeOrganizacional.UNIDADE_TIPO);

			Collection colecaoUnidadeOrgazanicional = fachada.pesquisar(filtro, UnidadeOrganizacional.class.getName());

			if (colecaoUnidadeOrgazanicional != null && !colecaoUnidadeOrgazanicional.isEmpty()) {
				
				sessao.setAttribute("colecaoUnidadeOrgazanicional", colecaoUnidadeOrgazanicional);
				
			} else {
				throw new ActionServletException("erro.nao_existe_unidade_repavimentadora");
			}
		}
		return unidadeOrganizacional;
	}
}