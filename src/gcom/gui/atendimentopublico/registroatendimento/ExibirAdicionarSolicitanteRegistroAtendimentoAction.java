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

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ObterDadosRegistroAtendimentoHelper;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.collection.PersistentSet;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os
 * parâmetros para realização da inserção de um Solicitante em uma R.A já
 * existente (Aba nº 02 - Dados gerais)
 * 
 * @author Raphael Rossiter
 * @date 18/08/2006
 */
public class ExibirAdicionarSolicitanteRegistroAtendimentoAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		AdicionarSolicitanteRegistroAtendimentoActionForm form = (AdicionarSolicitanteRegistroAtendimentoActionForm) actionForm;

		httpServletRequest.setAttribute("nomeCampo", "idCliente");
		
		
		if (httpServletRequest.getParameter("redirecionarPagina") != null
				&& !httpServletRequest.getParameter("redirecionarPagina")
						.equals("")) {
			String redirecionar = httpServletRequest
					.getParameter("redirecionarPagina");
			httpServletRequest.setAttribute("mostrarPerimetro", "sim");
			retorno = actionMapping.findForward(redirecionar);
		} else {
			retorno = actionMapping
					.findForward("adicionarSolicitanteRegistroAtendimento");
			// verifica se voltou da pesquisa
			if (httpServletRequest.getParameter("tipoConsulta") != null
					&& !httpServletRequest.getParameter("tipoConsulta").equals(
							"")) {
				if (httpServletRequest.getParameter("tipoConsulta").equals(
						"cliente")) {

					form.setIdCliente(httpServletRequest
							.getParameter("idCampoEnviarDados"));
					form.setNomeCliente("");

				}
				if (httpServletRequest.getParameter("tipoConsulta").equals(
						"unidadeOrganizacional")) {

					form.setIdUnidadeSolicitanteInformar(httpServletRequest
							.getParameter("idCampoEnviarDados"));
					form.setDescricaoUnidadeSolicitanteInformar("");

				}
				if (httpServletRequest.getParameter("tipoConsulta").equals(
						"funcionario")) {

					form.setIdFuncionarioResponsavel(httpServletRequest
							.getParameter("idCampoEnviarDados"));
					form.setDescricaoFuncionarioResponsavel("");

				}

			}

			HttpSession sessao = httpServletRequest.getSession(false);

			String idRASolicitante = null;
			if (httpServletRequest.getParameter("idRASolicitante") != null) {
				
				idRASolicitante = httpServletRequest.getParameter("idRASolicitante");
				sessao.setAttribute("idRASolicitante", idRASolicitante);
				
				sessao.removeAttribute("protocoloAtendimento");
			}
			else if (sessao.getAttribute("idRASolicitante") == null &&
					sessao.getAttribute("protocoloAtendimento") == null){
				
				//OBTENDO PROTOCOLO DE ATENDIMENTO
				String protocoloAtendimento = this.getFachada().obterProtocoloAtendimento();
				sessao.setAttribute("protocoloAtendimento", protocoloAtendimento);
				
				sessao.removeAttribute("idRASolicitante");
			}

			String objetoColecao = null;
			if (httpServletRequest.getParameter("objetoColecao") != null) {
				objetoColecao = httpServletRequest
						.getParameter("objetoColecao");
				sessao.setAttribute("objetoColecao", objetoColecao);
			}

			Fachada fachada = Fachada.getInstancia();

			Integer idRegistroAtendimento = Util
					.converterStringParaInteger(httpServletRequest
							.getParameter("idRegistroAtendimento"));

			if (idRegistroAtendimento != null) {
				
				/*
				 * Colocado para limpar o endereço e os telefones
				 * O método limparCliente remove da sessão o objeto "enderecoPertenceClientePopup" 
				 */
				sessao.setAttribute("enderecoPertenceClientePopup", "SIM");

				this.limparCliente(sessao);
				this.limparUnidadeSolicitante(sessao);

				form.setIdCliente("");
				form.setNomeCliente("");
				form.setIdUnidadeSolicitanteInformar("");
				form.setDescricaoUnidadeSolicitanteInformar("");
				form.setIdFuncionarioResponsavel("");
				form.setDescricaoFuncionarioResponsavel("");
				form.setNomeSolicitanteInformar("");
				
				form.setProtocoloAtendimento("");

				httpServletRequest.setAttribute("nomeCampo", "idCliente");

				// [UC0452] Obter Dados do Registro de Atendimento
				ObterDadosRegistroAtendimentoHelper dadosRegistroAtendimento = fachada
						.obterDadosRegistroAtendimento(idRegistroAtendimento);

				// Carregando o formulário com as informações da RA
				form.setIdRA(dadosRegistroAtendimento.getRegistroAtendimento()
						.getId().toString());

				form.setIdSolicitacaoTipo(dadosRegistroAtendimento
						.getRegistroAtendimento()
						.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo()
						.getId().toString());

				form.setDescricaoSolicitacaoTipo(dadosRegistroAtendimento
						.getRegistroAtendimento()
						.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo()
						.getDescricao());

				form.setIdEspecificacao(dadosRegistroAtendimento
						.getRegistroAtendimento()
						.getSolicitacaoTipoEspecificacao().getId().toString());

				form.setDescricaoEspecificacao(dadosRegistroAtendimento
						.getRegistroAtendimento()
						.getSolicitacaoTipoEspecificacao().getDescricao());

				if (dadosRegistroAtendimento.getRegistroAtendimento()
						.getSolicitacaoTipoEspecificacao()
						.getIndicadorCliente() != null) {
					form
							.setIndicadorClienteEspecificacao(dadosRegistroAtendimento
									.getRegistroAtendimento()
									.getSolicitacaoTipoEspecificacao()
									.getIndicadorCliente().toString());
				}

				form.setDataAtendimento(Util
						.formatarData(dadosRegistroAtendimento
								.getRegistroAtendimento()
								.getRegistroAtendimento()));

				form.setHoraAtendimento(Util
						.formatarHoraSemSegundos(dadosRegistroAtendimento
								.getRegistroAtendimento()
								.getRegistroAtendimento()));

				form.setIdMeioSolicitacao(dadosRegistroAtendimento
						.getRegistroAtendimento().getMeioSolicitacao().getId()
						.toString());

				form.setDescricaoMeioSolicitacao(dadosRegistroAtendimento
						.getRegistroAtendimento().getMeioSolicitacao()
						.getDescricao());

				if (dadosRegistroAtendimento.getUnidadeAtendimento() != null) {
					form.setIdUnidadeAtendimento(dadosRegistroAtendimento
							.getUnidadeAtendimento().getId().toString());
					form
							.setDescricaoUnidadeAtendimento(dadosRegistroAtendimento
									.getUnidadeAtendimento().getDescricao());
				}

				form.setDataPrevista(Util.formatarData(dadosRegistroAtendimento
						.getRegistroAtendimento().getDataPrevistaAtual()));

				if (dadosRegistroAtendimento.getSolicitante().getCliente() != null) {
					form.setIdClienteSolcitante(dadosRegistroAtendimento
							.getSolicitante().getCliente().getId().toString());
					form.setNomeClienteSolicitante(dadosRegistroAtendimento
							.getSolicitante().getCliente().getNome());
				} else if (dadosRegistroAtendimento.getSolicitante()
						.getUnidadeOrganizacional() != null) {
					form.setIdUnidadeSolicitante(dadosRegistroAtendimento
							.getSolicitante().getUnidadeOrganizacional()
							.getId().toString());
					form
							.setDescricaoUnidadeSolicitante(dadosRegistroAtendimento
									.getSolicitante()
									.getUnidadeOrganizacional().getDescricao());
				} else {
					form.setNomeSolicitante(dadosRegistroAtendimento
							.getSolicitante().getSolicitante());
				}

				if (dadosRegistroAtendimento.getEnderecoOcorrencia() != null) {
					sessao.setAttribute("enderecoOcorrenciaRA",
							dadosRegistroAtendimento.getEnderecoOcorrencia());
				}

				if (dadosRegistroAtendimento.getRegistroAtendimento()
						.getPontoReferencia() != null) {
					form.setPontoReferencia(dadosRegistroAtendimento
							.getRegistroAtendimento().getPontoReferencia());
				}

				if (dadosRegistroAtendimento.getRegistroAtendimento()
						.getLogradouroBairro() != null
						&& dadosRegistroAtendimento.getRegistroAtendimento()
								.getLogradouroBairro().getBairro() != null) {
					form.setIdBairro(dadosRegistroAtendimento
							.getRegistroAtendimento().getLogradouroBairro()
							.getBairro().getId().toString());
					form.setDescricaoBairro(dadosRegistroAtendimento
							.getRegistroAtendimento().getLogradouroBairro()
							.getBairro().getNome());
				}

				if (dadosRegistroAtendimento.getRegistroAtendimento()
						.getBairroArea() != null) {
					form.setIdBairroArea(dadosRegistroAtendimento
							.getRegistroAtendimento().getBairroArea().getId()
							.toString());
					form
							.setDescricaoBairroArea(dadosRegistroAtendimento
									.getRegistroAtendimento().getBairroArea()
									.getNome());
				}

				if (dadosRegistroAtendimento.getRegistroAtendimento()
						.getLocalidade() != null) {
					form.setIdLocalidade(dadosRegistroAtendimento
							.getRegistroAtendimento().getLocalidade().getId()
							.toString());
				}

				if (dadosRegistroAtendimento.getRegistroAtendimento()
						.getSetorComercial() != null) {
					form.setCodigoSetorComercial(String
							.valueOf(dadosRegistroAtendimento
									.getRegistroAtendimento()
									.getSetorComercial().getCodigo()));
				}

				if (dadosRegistroAtendimento.getRegistroAtendimento()
						.getQuadra() != null) {
					form.setNumeroQuadra(String
							.valueOf(dadosRegistroAtendimento
									.getRegistroAtendimento().getQuadra()
									.getNumeroQuadra()));
				}

				if (dadosRegistroAtendimento.getUnidadeAtual() != null) {
					form.setIdUnidadeAtual(dadosRegistroAtendimento
							.getUnidadeAtual().getId().toString());
					form.setDescricaoUnidadeAtual(dadosRegistroAtendimento
							.getUnidadeAtual().getDescricao());
				}
				
				//[SB0011] 1.5 Verifica Indicador de Envio de email para ServicoTipo 
		    	if(dadosRegistroAtendimento.getRegistroAtendimento().getSolicitacaoTipoEspecificacao() != null){
		    		int idEspecificacao = dadosRegistroAtendimento.getRegistroAtendimento().getSolicitacaoTipoEspecificacao().getId().intValue();
		    		
		    		FiltroSolicitacaoTipoEspecificacao filtroSolicitacaoTipoEspecificacao = new FiltroSolicitacaoTipoEspecificacao();
		    		filtroSolicitacaoTipoEspecificacao.adicionarCaminhoParaCarregamentoEntidade(FiltroSolicitacaoTipoEspecificacao.SERVICO_TIPO);
		    		filtroSolicitacaoTipoEspecificacao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID, idEspecificacao));
		    		
		    		List<SolicitacaoTipoEspecificacao> listaSolicitacaoTipoEspecificacao = new ArrayList<SolicitacaoTipoEspecificacao>(fachada.pesquisar(filtroSolicitacaoTipoEspecificacao, SolicitacaoTipoEspecificacao.class.getName()));
		    		if(listaSolicitacaoTipoEspecificacao != null && !listaSolicitacaoTipoEspecificacao.isEmpty()){
		    			SolicitacaoTipoEspecificacao especificacao = listaSolicitacaoTipoEspecificacao.get(0);
		    			
		    			if(especificacao.getServicoTipo() != null && especificacao.getServicoTipo().getIndicadorEnvioPesquisaSatisfacao().intValue() == 1){
		    				sessao.setAttribute("habilitarCampoSatisfacaoEmail", true);
		    				if(idRASolicitante != null){
		    					RegistroAtendimentoSolicitante raSolicitanteEmail = fachada.pesquisarDadosEnvioEmailPesquisaPortal(dadosRegistroAtendimento.getSolicitante().getID().intValue());
		    					form.setEnviarEmailSatisfacao(raSolicitanteEmail.getIndicadorEnvioEmailPesquisa().intValue()+"");
		    					form.setEnderecoEmail(raSolicitanteEmail.getEnderecoEmail());
		    					
		    					if(raSolicitanteEmail.getIndicadorEnvioEmailPesquisa().intValue() == 2){
		    						form.setEnderecoEmail("");
		    					}
		    				}else{
		    					form.setEnviarEmailSatisfacao("");
		    					form.setEnderecoEmail("");
		    				}
		    			}else{
		    				sessao.setAttribute("habilitarCampoSatisfacaoEmail", false);
		    			}
		    		}
		    	}
			}

			// caso seja para atualizar o RA solicitante
			objetoColecao = (String) sessao.getAttribute("objetoColecao");
			if (httpServletRequest.getParameter("primeiraVez") != null) {
				if ((objetoColecao != null && !objetoColecao.equals("SIM"))
						&& httpServletRequest
								.getParameter("limparClienteSolicitante") == null
						&& httpServletRequest
								.getParameter("informadoNomeSolicitante") == null) {
					Collection colecaoRASolicitante = (Collection) sessao
							.getAttribute("colecaoRASolicitante");
					Iterator iteRASolicitante = colecaoRASolicitante.iterator();
					while (iteRASolicitante.hasNext()) {
						RegistroAtendimentoSolicitante registroAtendimentoSolicitante = (RegistroAtendimentoSolicitante) iteRASolicitante
								.next();
						if (registroAtendimentoSolicitante.getUltimaAlteracao()
								.getTime() == Long.parseLong(objetoColecao)) {
							// se tem cliente
							if (registroAtendimentoSolicitante.getCliente() != null
									&& registroAtendimentoSolicitante
											.getCliente().getId() != null) {
								form
										.setIdCliente(registroAtendimentoSolicitante
												.getCliente().getId()
												.toString());
								// senão
							} else {
								// seta a unidade solicitante
								if (registroAtendimentoSolicitante
										.getUnidadeOrganizacional() != null
										&& !registroAtendimentoSolicitante
												.getUnidadeOrganizacional()
												.equals("")) {
									form.setIdUnidadeSolicitanteInformar(""
											+ registroAtendimentoSolicitante
													.getUnidadeOrganizacional()
													.getId());
								}
								// seta o funcionário
								if (registroAtendimentoSolicitante
										.getFuncionario() != null
										&& !registroAtendimentoSolicitante
												.getFuncionario().equals("")) {
									form.setIdFuncionarioResponsavel(""
											+ registroAtendimentoSolicitante
													.getFuncionario().getId());
								}
								if ((registroAtendimentoSolicitante
										.getFuncionario() == null || registroAtendimentoSolicitante
										.getFuncionario().equals(""))
										&& (registroAtendimentoSolicitante
												.getUnidadeOrganizacional() == null || registroAtendimentoSolicitante
												.getUnidadeOrganizacional()
												.equals(""))) {
									form
											.setNomeSolicitanteInformar(registroAtendimentoSolicitante
													.getSolicitante());

									this.limparCliente(sessao);
									this.limparUnidadeSolicitante(sessao);

									sessao
											.setAttribute(
													"desabilitarDadosSolicitanteCliente",
													"OK");
									sessao
											.setAttribute(
													"desabilitarDadosSolicitanteUnidade",
													"OK");
									sessao
											.setAttribute(
													"desabilitarDadosSolicitanteFuncionario",
													"OK");
									sessao
											.setAttribute(
													"habilitarAlteracaoEnderecoSolicitante",
													"SIM");

									httpServletRequest.setAttribute(
											"nomeCampo",
											"botaoAdicionarSolicitante");
								} else {
									this.limparNomeSolicitante(sessao);

									httpServletRequest.setAttribute(
											"nomeCampo", "idCliente");

								}
								
								if(registroAtendimentoSolicitante.getIndicadorEnvioEmailPesquisa() != null){
									form.setEnviarEmailSatisfacao(registroAtendimentoSolicitante.getIndicadorEnvioEmailPesquisa().intValue()+"");
									form.setEnderecoEmail(registroAtendimentoSolicitante.getEnderecoEmail());
									
									if(registroAtendimentoSolicitante.getIndicadorEnvioEmailPesquisa().intValue() == 2){
										form.setEnderecoEmail("");
									}
								}

								// cria uma colecao de ciente endereço e seta o
								// logradouro cep, o logradouro bairro e o
								// complemento
								Collection colecaoEnderecosSolicitante = new ArrayList();
								ClienteEndereco clienteEndereco = new ClienteEndereco();
								// pesquisa os parametros do logradouro cep para
								// o
								// endereço
								if (registroAtendimentoSolicitante
										.getLogradouroCep() != null
										&& registroAtendimentoSolicitante
												.getLogradouroCep().getId() != null) {
									LogradouroCep logradouroCep = fachada
											.pesquisarLogradouroCepEndereco(registroAtendimentoSolicitante
													.getLogradouroCep().getId());

									clienteEndereco
											.setLogradouroCep(logradouroCep);
								}

								// pesquisa os parametros do logradouro bairro
								// para
								// o
								// endereço
								if (registroAtendimentoSolicitante
										.getLogradouroBairro() != null
										&& registroAtendimentoSolicitante
												.getLogradouroBairro().getId() != null) {
									LogradouroBairro logradouroBairro = fachada
											.pesquisarLogradouroBairroEndereco(registroAtendimentoSolicitante
													.getLogradouroBairro()
													.getId());

									clienteEndereco
											.setLogradouroBairro(logradouroBairro);
								}
								clienteEndereco
										.setComplemento(registroAtendimentoSolicitante
												.getComplementoEndereco());
								clienteEndereco.setPerimetroInicial(registroAtendimentoSolicitante.getPerimetroInicial());
								clienteEndereco.setPerimetroFinal(registroAtendimentoSolicitante.getPerimetroFinal());

								clienteEndereco
										.setUltimaAlteracao(registroAtendimentoSolicitante
												.getUltimaAlteracao());

								colecaoEnderecosSolicitante
										.add(clienteEndereco);
								sessao.setAttribute(
										"colecaoEnderecosSolicitante",
										colecaoEnderecosSolicitante);

								Collection colecaoFones = null;

								if (registroAtendimentoSolicitante
										.getSolicitanteFones() instanceof PersistentSet) {
									colecaoFones = fachada
											.pesquisarParmsFoneRegistroAtendimentoSolicitante(registroAtendimentoSolicitante
													.getID());

								} else {

									colecaoFones = registroAtendimentoSolicitante
											.getSolicitanteFones();

								}
								if (colecaoFones != null
										&& !colecaoFones.isEmpty()) {
									registroAtendimentoSolicitante
											.setSolicitanteFones(new HashSet(
													colecaoFones));
									sessao.setAttribute(
											"colecaoFonesSolicitante",
											colecaoFones);
								}
							}
							
							//PROTOCOLO DE ATENDIMENTO
							if (registroAtendimentoSolicitante.getNumeroProtocoloAtendimento() != null &&
								!registroAtendimentoSolicitante.getNumeroProtocoloAtendimento().equals("")){
								
								form.setProtocoloAtendimento(registroAtendimentoSolicitante.getNumeroProtocoloAtendimento());
							}
						}

					}

				}
			}

			/*
			 * Pesquisas realizadas a partir do ENTER
			 * ===========================================================================================================
			 */
			objetoColecao = (String) sessao.getAttribute("objetoColecao");

			idRASolicitante = (String) sessao.getAttribute("idRASolicitante");

			if ((form.getIdCliente() != null && !form.getIdCliente()
					.equalsIgnoreCase(""))
					&& (form.getNomeCliente() == null || form.getNomeCliente()
							.equals(""))) {

				/*
				 * Obtendo o id do imóvel informado na aba de inserção nº 02
				 * (Caso não tenho sido informado será passado como parâmetro
				 * NULL).
				 */
				String idImovel = null;
				// caso seja do processo de atualizar registro atendimento
				if (objetoColecao != null && !objetoColecao.equals("")) {
					AtualizarRegistroAtendimentoActionForm atualizarRegistroAtendimentoActionForm = (AtualizarRegistroAtendimentoActionForm) sessao
							.getAttribute("AtualizarRegistroAtendimentoActionForm");
					idImovel = atualizarRegistroAtendimentoActionForm
							.getIdImovel();
				} else {
					// caso seja do processo de inserir registro atendimento
					InserirRegistroAtendimentoActionForm inserirRegistroAtendimentoActionForm = (InserirRegistroAtendimentoActionForm) sessao
							.getAttribute("InserirRegistroAtendimentoActionForm");
					idImovel = inserirRegistroAtendimentoActionForm
							.getIdImovel();
				}

				// [FS0027] - Verificar informação do imóvel
				Cliente cliente = fachada.verificarInformacaoImovel(Util
						.converterStringParaInteger(form.getIdCliente()), Util
						.converterStringParaInteger(idImovel), false);

				if (cliente == null) {

					form.setIdCliente("");
					form.setNomeCliente("Cliente inexistente");

					httpServletRequest.setAttribute("corCliente", "exception");
					httpServletRequest.setAttribute("nomeCampo", "idCliente");

				} else {

					// caso seja do processo de atualizar registro atendimento
					if (objetoColecao == null || objetoColecao.equals("")) {
						// [FS0012] - Verificar existência do cliente
						// solicitante
						fachada.verificarExistenciaClienteSolicitante(Util
								.converterStringParaInteger(form.getIdRA()),
								cliente.getId());
					} else {
						if (idRASolicitante != null
								&& !idRASolicitante.equals("")) {
							// [FS0012] - Verificar existência do cliente
							// solicitante
							fachada
									.verificarExistenciaClienteSolicitanteAtualizar(
											Util
													.converterStringParaInteger(form
															.getIdRA()),
											cliente.getId(),
											Util
													.converterStringParaInteger(idRASolicitante));
						}

					}

					form.setIdCliente(cliente.getId().toString());
					form.setNomeCliente(cliente.getNome());

					Collection colecaoEnderecos = fachada
							.pesquisarEnderecosClienteAbreviado(cliente.getId());

					if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {

						/*
						 * Iterator endCorrespondencia =
						 * colecaoEnderecos.iterator(); ClienteEndereco endereco =
						 * null;
						 * 
						 * while (endCorrespondencia.hasNext()) {
						 * 
						 * endereco = (ClienteEndereco)
						 * endCorrespondencia.next();
						 * 
						 * if (endereco.getIndicadorEnderecoCorrespondencia() !=
						 * null && endereco
						 * .getIndicadorEnderecoCorrespondencia() .equals(
						 * ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA)) {
						 * 
						 * form.setClienteEnderecoSelected(endereco.getId()
						 * .toString()); break; } }
						 */
						sessao.setAttribute("colecaoEnderecosSolicitante",
								colecaoEnderecos);
					}

					Collection colecaoFones = fachada
							.pesquisarClienteFone(cliente.getId());

					sessao
							.setAttribute("colecaoFonesSolicitante",
									colecaoFones);
					sessao.setAttribute("enderecoPertenceClientePopup", "OK");

					/*
					 * if (colecaoFones != null && !colecaoFones.isEmpty()) {
					 * 
					 * Iterator fonePrincipal = colecaoFones.iterator();
					 * ClienteFone fone = null;
					 * 
					 * while (fonePrincipal.hasNext()) {
					 * 
					 * fone = (ClienteFone) fonePrincipal.next();
					 * 
					 * if (fone.getIndicadorTelefonePadrao() != null && fone
					 * .getIndicadorTelefonePadrao() .equals(
					 * ConstantesSistema.INDICADOR_TELEFONE_PRINCIPAL)) {
					 * 
					 * form
					 * .setClienteFoneSelected(""+fone.getUltimaAlteracao().getTime());
					 * break; } }
					 * 
					 * sessao .setAttribute("colecaoFonesSolicitante",
					 * colecaoFones); }
					 */
					httpServletRequest.setAttribute("nomeCampo",
							"botaoAdicionarSolicitante");

					this.limparUnidadeSolicitante(sessao);
					this.limparNomeSolicitante(sessao);

					sessao.setAttribute("desabilitarDadosSolicitanteUnidade",
							"OK");
					sessao.setAttribute(
							"desabilitarDadosSolicitanteFuncionario", "OK");
					sessao
							.setAttribute("desabilitarDadosSolicitanteNome",
									"OK");
					sessao.setAttribute(
							"habilitarAlteracaoEnderecoSolicitante", "NAO");

				}
			}

			if ((form.getIdUnidadeSolicitanteInformar() != null && !form
					.getIdUnidadeSolicitanteInformar().equals(""))
					&& (form.getDescricaoUnidadeSolicitanteInformar() == null || form
							.getDescricaoUnidadeSolicitanteInformar()
							.equals(""))) {

				FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

				filtroUnidadeOrganizacional
						.adicionarParametro(new ParametroSimples(
								FiltroUnidadeOrganizacional.ID, form
										.getIdUnidadeSolicitanteInformar()));

				filtroUnidadeOrganizacional
						.adicionarParametro(new ParametroSimples(
								FiltroUnidadeOrganizacional.INDICADOR_USO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				Collection colecaoUnidade = fachada.pesquisar(
						filtroUnidadeOrganizacional,
						UnidadeOrganizacional.class.getName());

				if (colecaoUnidade == null || colecaoUnidade.isEmpty()) {

					form.setIdUnidadeSolicitanteInformar("");
					form
							.setDescricaoUnidadeSolicitanteInformar("Unidade Solicitante inexistente");

					httpServletRequest.setAttribute("corUnidadeSolicitante", "exception");
					httpServletRequest.setAttribute("nomeCampo",
							"idUnidadeSolicitanteInformar");

				} else {
					UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util
							.retonarObjetoDeColecao(colecaoUnidade);

					form.setIdUnidadeSolicitanteInformar(unidade.getId()
							.toString());
					form.setDescricaoUnidadeSolicitanteInformar(unidade
							.getDescricao());

					// caso seja do processo de atualizar registro atendimento
					if (objetoColecao == null || objetoColecao.equals("")) {

						// [FS0026] - Verificar existência da unidade
						// solicitante
						fachada.verificarExistenciaUnidadeSolicitante(Util
								.converterStringParaInteger(form.getIdRA()),
								Util.converterStringParaInteger(form
										.getIdUnidadeSolicitanteInformar()));
					} else {
						if (idRASolicitante != null
								&& !idRASolicitante.equals("")) {
							// [FS0026] - Verificar existência da unidade
							// solicitante
							fachada
									.verificarExistenciaUnidadeSolicitanteAtualizar(
											Util
													.converterStringParaInteger(form
															.getIdRA()),
											Util
													.converterStringParaInteger(form
															.getIdUnidadeSolicitanteInformar()),
											Util
													.converterStringParaInteger(idRASolicitante));
						}

					}

					httpServletRequest.setAttribute("nomeCampo", "idFuncionarioResponsavel");

					// this.limparCliente(sessao);
					// this.limparNomeSolicitante(sessao);

					sessao.setAttribute("desabilitarDadosSolicitanteCliente",
							"OK");
					sessao
							.setAttribute("desabilitarDadosSolicitanteNome",
									"OK");
					sessao.setAttribute(
							"habilitarAlteracaoEnderecoSolicitante", "SIM");

				}
			}

			if ((form.getIdFuncionarioResponsavel() != null && !form
					.getIdFuncionarioResponsavel().equalsIgnoreCase(""))
					&& (form.getDescricaoFuncionarioResponsavel() == null || form
							.getDescricaoFuncionarioResponsavel().equals(""))) {

				FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

				filtroFuncionario.adicionarParametro(new ParametroSimples(
						FiltroFuncionario.ID, form
								.getIdFuncionarioResponsavel()));

				if (form.getIdUnidadeSolicitanteInformar() != null
						&& !form.getIdUnidadeSolicitanteInformar().equals("")) {

					filtroFuncionario.adicionarParametro(new ParametroSimples(
							FiltroFuncionario.UNIDADE_ORGANIZACIONAL_ID, form
									.getIdUnidadeSolicitanteInformar()));
				}

				Collection colecaoFuncionario = fachada.pesquisar(
						filtroFuncionario, Funcionario.class.getName());

				if (colecaoFuncionario == null || colecaoFuncionario.isEmpty()) {

					form.setIdFuncionarioResponsavel("");
					form
							.setDescricaoFuncionarioResponsavel("Funcionário inexistente");

					httpServletRequest.setAttribute("corFuncionario",
							"exception");
					httpServletRequest.setAttribute("nomeCampo",
							"idUnidadeSolicitanteInformar");

				} else {
					Funcionario funcionario = (Funcionario) Util
							.retonarObjetoDeColecao(colecaoFuncionario);

					form.setIdFuncionarioResponsavel(funcionario.getId()
							.toString());
					form.setDescricaoFuncionarioResponsavel(funcionario
							.getNome());

					httpServletRequest.setAttribute("nomeCampo",
							"botaoAdicionarSolicitante");
				}
			}

			/*
			 * Fim das pesquisas realizadas pelo ENTER
			 * ===========================================================================================================
			 * ===========================================================================================================
			 */

			String informadoNomeSolicitante = httpServletRequest
					.getParameter("informadoNomeSolicitante");

			if (informadoNomeSolicitante != null
					&& !informadoNomeSolicitante.equalsIgnoreCase("")) {

				this.limparCliente(sessao);
				this.limparUnidadeSolicitante(sessao);

				sessao.setAttribute("desabilitarDadosSolicitanteCliente", "OK");
				sessao.setAttribute("desabilitarDadosSolicitanteUnidade", "OK");
				sessao.setAttribute("desabilitarDadosSolicitanteFuncionario",
						"OK");
				sessao.setAttribute("habilitarAlteracaoEnderecoSolicitante",
						"SIM");

				httpServletRequest.setAttribute("nomeCampo", "nomeSolicitanteInformar");
			}

			String limparCliente = httpServletRequest
					.getParameter("limparClienteSolicitante");

			if (limparCliente != null && !limparCliente.equalsIgnoreCase("")) {

				this.limparCliente(sessao);

				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}

			String limparUnidadeSolicitante = httpServletRequest
					.getParameter("limparUnidadeSolicitante");

			if (limparUnidadeSolicitante != null
					&& !limparUnidadeSolicitante.equalsIgnoreCase("")) {

				this.limparUnidadeSolicitante(sessao);

				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}

			String limparNomeSolicitante = httpServletRequest
					.getParameter("limparNomeSolicitante");

			if (limparNomeSolicitante != null
					&& !limparNomeSolicitante.equalsIgnoreCase("")) {

				this.limparNomeSolicitante(sessao);

				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}

			/*
			 * Removendo endereço
			 */
			String removerEndereco = httpServletRequest
					.getParameter("removerEndereco");

			if (removerEndereco != null
					&& !removerEndereco.trim().equalsIgnoreCase("")) {

				if (sessao.getAttribute("colecaoEnderecosSolicitante") != null) {

					Collection enderecos = (Collection) sessao
							.getAttribute("colecaoEnderecosSolicitante");

					if (!enderecos.isEmpty()) {
						enderecos.remove(enderecos.iterator().next());
					}
				}
			}
			
			
			//Remover Telefone
	    	String removerFone = httpServletRequest
	    	.getParameter("removerFone");
	    	
	    	if (removerFone != null && !removerFone.equalsIgnoreCase("")){
	    		
	    		long objetoRemocao = (Long.valueOf(httpServletRequest.getParameter("removerFone"))).longValue();
	    		Collection colecaoFones = (Collection) sessao.getAttribute("colecaoFonesSolicitante");
	    		Iterator iteratorColecaoFones = colecaoFones.iterator();
	    		IClienteFone clienteFone = null;
	    		
	    		while (iteratorColecaoFones.hasNext()){
	    			clienteFone = (IClienteFone) iteratorColecaoFones.next();
	    			
	    			if (obterTimestampIdObjeto(clienteFone) == objetoRemocao){
	    				colecaoFones.remove(clienteFone);
	    				break;
	    			}
	    		}
	    	}
	    	
		}

		return retorno;
	}

	private void limparCliente(HttpSession sessao) {

		if (sessao.getAttribute("enderecoPertenceClientePopup") != null) {
			sessao.removeAttribute("colecaoEnderecosSolicitante");
			sessao.removeAttribute("colecaoFonesSolicitante");
		}
		sessao.removeAttribute("enderecoPertenceClientePopup");
		sessao.removeAttribute("desabilitarDadosSolicitanteUnidade");
		sessao.removeAttribute("desabilitarDadosSolicitanteFuncionario");
		sessao.removeAttribute("desabilitarDadosSolicitanteNome");
		sessao.removeAttribute("habilitarAlteracaoEnderecoSolicitante");

	}

	private void limparUnidadeSolicitante(HttpSession sessao) {

		sessao.removeAttribute("desabilitarDadosSolicitanteCliente");
		sessao.removeAttribute("desabilitarDadosSolicitanteNome");
		sessao.removeAttribute("habilitarAlteracaoEnderecoSolicitante");

	}

	private void limparNomeSolicitante(HttpSession sessao) {

		sessao.removeAttribute("desabilitarDadosSolicitanteCliente");
		sessao.removeAttribute("desabilitarDadosSolicitanteUnidade");
		sessao.removeAttribute("desabilitarDadosSolicitanteFuncionario");
		sessao.removeAttribute("habilitarAlteracaoEnderecoSolicitante");

	}

}
