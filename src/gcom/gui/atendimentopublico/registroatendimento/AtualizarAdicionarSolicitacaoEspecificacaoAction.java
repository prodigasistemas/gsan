package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 13/11/2006
 */
public class AtualizarAdicionarSolicitacaoEspecificacaoAction extends
		GcomAction {
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
				.findForward("atualizarAdicionarSolicitacaoEspecificacao");

		Collection colecaoSolicitacaoTipoEspecificacao = null;
		if (sessao.getAttribute("colecaoSolicitacaoTipoEspecificacao") == null) {
			colecaoSolicitacaoTipoEspecificacao = new ArrayList();
		} else {
			colecaoSolicitacaoTipoEspecificacao = (Collection) sessao
					.getAttribute("colecaoSolicitacaoTipoEspecificacao");
		}

		// String idSolicitacaoTipo = sessao.getAttribute("idTipoSolicitacao",
		// idSolicitacaoTipo);

		AtualizarAdicionarSolicitacaoEspecificacaoActionForm atualizarAdicionarSolicitacaoEspecificacaoActionForm = (AtualizarAdicionarSolicitacaoEspecificacaoActionForm) actionForm;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		if (sessao.getAttribute("atualizar") != null) {

			// Obs: Se remover tudo da coleção, qdo a coleçao tiver mais de um
			// elemento vai dar pau. Entao, terei que Instaciar um objeto pra
			// receber os dados da colecao que esta na sessao, para depois
			// comparar com a que esta sendo alterada, pra depois fazer
			// alteração.

			Integer posicaoComponente = (Integer) sessao
					.getAttribute("posicaoComponente");

			int index = 0;

			Iterator colecaoSolicitacaoTipoEspecificacaoIterator = colecaoSolicitacaoTipoEspecificacao
					.iterator();

			while (colecaoSolicitacaoTipoEspecificacaoIterator.hasNext()) {

				index++;

				SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = (SolicitacaoTipoEspecificacao) colecaoSolicitacaoTipoEspecificacaoIterator
						.next();

				if (index == posicaoComponente) {

					solicitacaoTipoEspecificacao
							.setIndicadorSolicitante(new Short("1"));

					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getPrazoPrevisaoAtendimento() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getPrazoPrevisaoAtendimento().equals("")) {
						// Prazo de previsão de atendimento
						solicitacaoTipoEspecificacao.setDiasPrazo(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getPrazoPrevisaoAtendimento()));
					}
					// Descrição da especificação
					solicitacaoTipoEspecificacao
							.setDescricao(atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getDescricaoSolicitacao());

					// Pavimento de calçada obrigatório
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPavimentoCalcada() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorPavimentoCalcada().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorPavimentoCalcada(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorPavimentoCalcada()));
					}
					// Pavimento de rua obrigatório
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPavimentoRua() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorPavimentoRua().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorPavimentoRua(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorPavimentoRua()));
					}

					// refere-se a ligação de agua
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorLigacaoAgua() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorLigacaoAgua().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorLigacaoAgua(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorLigacaoAgua()));
					}
					// Cobrança de material
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCobrancaMaterial() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorCobrancaMaterial().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorCobrancaMaterial(new Integer(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorCobrancaMaterial()));
					}
					// Matricula do imóvel obrigatório
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorMatriculaImovel() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorMatriculaImovel().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorMatricula(new Integer(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorMatriculaImovel()));
					}
					// indicador Urgência
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorUrgencia() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorUrgencia().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorUrgencia(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorUrgencia()));
					}
					// Parecer de encerramento obrigatório
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorParecerEncerramento() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorParecerEncerramento().equals(
											"")) {
						solicitacaoTipoEspecificacao
								.setIndicadorParecerEncerramento(new Integer(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorParecerEncerramento()));
					}
					// Gera débito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGerarDebito() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorGerarDebito().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorGeracaoDebito(new Integer(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorGerarDebito()));
					}
					// Gera Crédito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGerarCredito() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorGerarCredito().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorGeracaoCredito(new Integer(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorGerarCredito()));
					}
					// hora e data correntes
					solicitacaoTipoEspecificacao.setUltimaAlteracao(new Date());

					// Unidade inicial tramitação
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdUnidadeTramitacao() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdUnidadeTramitacao().equals("")) {
						// Verifica se o código foi modificado
						if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getDescricaoUnidadeTramitacao() == null
								|| atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getDescricaoUnidadeTramitacao().trim()
										.equals("")) {

							FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

							filtroUnidadeOrganizacional
									.adicionarParametro(new ParametroSimples(
											FiltroUnidadeOrganizacional.ID,
											atualizarAdicionarSolicitacaoEspecificacaoActionForm
													.getIdUnidadeTramitacao()));

							filtroUnidadeOrganizacional
									.adicionarParametro(new ParametroSimples(
											FiltroUnidadeOrganizacional.INDICADOR_USO,
											ConstantesSistema.INDICADOR_USO_ATIVO));

							Collection unidadeOrganizacionalEncontrado = fachada
									.pesquisar(filtroUnidadeOrganizacional,
											UnidadeOrganizacional.class
													.getName());

							if (unidadeOrganizacionalEncontrado != null
									&& !unidadeOrganizacionalEncontrado
											.isEmpty()) {
								UnidadeOrganizacional uinidadeOrganizacional = (UnidadeOrganizacional) ((List) unidadeOrganizacionalEncontrado)
										.get(0);
								solicitacaoTipoEspecificacao
										.setUnidadeOrganizacional(uinidadeOrganizacional);

							} else {
								throw new ActionServletException(
										"atencao.pesquisa_inexistente", null,
										"Unidade Organizacional");
							}

						} else {
							UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
							unidadeOrganizacional.setId(new Integer(
									atualizarAdicionarSolicitacaoEspecificacaoActionForm
											.getIdUnidadeTramitacao()));
							solicitacaoTipoEspecificacao
									.setUnidadeOrganizacional(unidadeOrganizacional);
						}
					} else {
						solicitacaoTipoEspecificacao
								.setUnidadeOrganizacional(null);
					}

					// id do tipo da solicitação gerada
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdServicoOS() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdServicoOS().equals("")) {

						// Verifica se o código foi modificado
						if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getDescricaoServicoOS() == null
								|| atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getDescricaoServicoOS().trim().equals(
												"")) {

							FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

							filtroServicoTipo
									.adicionarParametro(new ParametroSimples(
											FiltroServicoTipo.ID,
											atualizarAdicionarSolicitacaoEspecificacaoActionForm
													.getIdServicoOS()));

							Collection servicoTipoEncontrado = fachada
									.pesquisar(filtroServicoTipo,
											ServicoTipo.class.getName());

							if (servicoTipoEncontrado != null
									&& !servicoTipoEncontrado.isEmpty()) {
								// [SF0003] - Validar Tipo Serviço
								fachada
										.verificarServicoTipoReferencia(new Integer(
												atualizarAdicionarSolicitacaoEspecificacaoActionForm
														.getIdServicoOS()));

								ServicoTipo servicoTipo = (ServicoTipo) ((List) servicoTipoEncontrado)
										.get(0);
								solicitacaoTipoEspecificacao
										.setServicoTipo(servicoTipo);
							} else {
								throw new ActionServletException(
										"atencao.pesquisa_inexistente", null,
										"Serviço Tipo");
							}
						} else {
							ServicoTipo servicoTipo = new ServicoTipo();
							servicoTipo.setId(new Integer(
									atualizarAdicionarSolicitacaoEspecificacaoActionForm
											.getIdServicoOS()));

							servicoTipo
									.setDescricao(atualizarAdicionarSolicitacaoEspecificacaoActionForm
											.getDescricaoServicoOS());

							solicitacaoTipoEspecificacao
									.setServicoTipo(servicoTipo);
						}

					}

					// Gera ordem de serviço
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGeraOrdemServico() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorGeraOrdemServico().equals("")) {

						solicitacaoTipoEspecificacao
								.setIndicadorGeracaoOrdemServico(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorGeraOrdemServico()));
					}
					// Cliente Obrigatório
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCliente() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorCliente().equals("")) {

						solicitacaoTipoEspecificacao
								.setIndicadorCliente(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorCliente()));
					}

					// Indicador verfificcação de débito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorVerificarDebito() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorVerificarDebito().equals("")) {

						solicitacaoTipoEspecificacao
								.setIndicadorVerificarDebito(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorVerificarDebito()));
					}

					// Indicador encerramento automático
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorEncerramentoAutomatico() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorEncerramentoAutomatico()
									.equals("")) {

						solicitacaoTipoEspecificacao
								.setIndicadorEncerramentoAutomatico(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorEncerramentoAutomatico()));
					}
					
					//Indicador loja virtual
					if(Util.verificarNaoVazio(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorLojaVirtual())){
						solicitacaoTipoEspecificacao.setIndicadorLojaVirtual(new Short(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorLojaVirtual()));
					}

					// Situação imovel
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdSituacaoImovel() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdSituacaoImovel().equals("")) {
						EspecificacaoImovelSituacao especificacaoImovelSituacao = new EspecificacaoImovelSituacao();
						especificacaoImovelSituacao.setId(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIdSituacaoImovel()));
						solicitacaoTipoEspecificacao
								.setEspecificacaoImovelSituacao(especificacaoImovelSituacao);
					}
					Collection colecaoEspecificacaoServicoTipo = (Collection) sessao
							.getAttribute("colecaoEspecificacaoServicoTipo");
					// recupera a coleção de especificacao servico tipo
					if (colecaoEspecificacaoServicoTipo != null
							&& !colecaoEspecificacaoServicoTipo.isEmpty()) {

						Collection colecao = new ArrayList();
						colecao.addAll(colecaoEspecificacaoServicoTipo);
						// [SF0004] - Validar Valor Ordem de Serviço 2ª parte
						fachada.verificarOrdemExecucaoForaOrdem(colecao);
						solicitacaoTipoEspecificacao
								.setEspecificacaoServicoTipos(new HashSet(
										colecaoEspecificacaoServicoTipo));
						sessao
								.removeAttribute("colecaoEspecificacaoServicoTipo");
					}

					// Colocado por Raphael Rossiter em 25/02/2008
					// Tipo de Débito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdDebitoTipo() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdDebitoTipo().equals("")) {

						FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

						filtroDebitoTipo
								.adicionarParametro(new ParametroSimples(
										FiltroDebitoTipo.ID,
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIdDebitoTipo()));

						Collection debitoTipoEncontrado = fachada.pesquisar(
								filtroDebitoTipo, DebitoTipo.class.getName());

						if (debitoTipoEncontrado != null
								&& !debitoTipoEncontrado.isEmpty()) {

							DebitoTipo debitoTipo = (DebitoTipo) Util
									.retonarObjetoDeColecao(debitoTipoEncontrado);

							solicitacaoTipoEspecificacao
									.setDebitoTipo(debitoTipo);
						} else {

							// [FS0007] - Validar Tipo de débito
							throw new ActionServletException(
									"atencao.pesquisa_inexistente", null,
									"Tipo de Débito");
						}

					} else {
						solicitacaoTipoEspecificacao.setDebitoTipo(null);
					}

					// Valor do Débito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getValorDebito() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getValorDebito().equals("")) {

						solicitacaoTipoEspecificacao
								.setValorDebito(Util
										.formatarMoedaRealparaBigDecimal(atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getValorDebito()));
					} else {
						solicitacaoTipoEspecificacao.setValorDebito(null);
					}

					// Alterar Valor do débito
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPermiteAlterarValor() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorPermiteAlterarValor().equals(
											"")) {
						solicitacaoTipoEspecificacao
								.setIndicadorPermiteAlterarValor(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorPermiteAlterarValor()));
					}

					// Cobrar Juros
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCobrarJuros() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorCobrarJuros().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorCobrarJuros(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorCobrarJuros()));
					}

					// indicador de uso ativo
					solicitacaoTipoEspecificacao.setIndicadorUso(new Short(
							ConstantesSistema.INDICADOR_USO_ATIVO));

					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdEspecificacao() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdEspecificacao().equals("")) {
						SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoNovoRA = new SolicitacaoTipoEspecificacao();
						solicitacaoTipoEspecificacaoNovoRA
								.setId(Integer
										.parseInt(atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIdEspecificacao()));

						SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
						solicitacaoTipo
								.setId(Integer
										.parseInt(atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIdTipoSolicitacao()));
						solicitacaoTipoEspecificacaoNovoRA
								.setSolicitacaoTipo(solicitacaoTipo);

						solicitacaoTipoEspecificacao
								.setSolicitacaoTipoEspecificacaoNovoRA(solicitacaoTipoEspecificacaoNovoRA);
					}

					// Informar conta no Registro de Atendimento
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorInformarContaRA() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorInformarContaRA().equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorInformarContaRA(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorInformarContaRA()));
					}

					// Informar conta no Registro de Atendimento
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorInformarPagamentoDP() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorInformarPagamentoDP().equals(
											"")) {
						solicitacaoTipoEspecificacao
								.setIndicadorInformarPagamentoDuplicidade(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorInformarPagamentoDP()));
					}

					// Informar alteração no vencimento alternativo
					if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorAlterarVencimentoAlternativo() != null
							&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIndicadorAlterarVencimentoAlternativo()
									.equals("")) {
						solicitacaoTipoEspecificacao
								.setIndicadorAlterarVencimentoAlternativo(new Short(
										atualizarAdicionarSolicitacaoEspecificacaoActionForm
												.getIndicadorAlterarVencimentoAlternativo()));
					}
				}
			}

		} else {

			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();

			solicitacaoTipoEspecificacao
					.setIndicadorSolicitante(new Short("1"));

			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getPrazoPrevisaoAtendimento() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getPrazoPrevisaoAtendimento().equals("")) {
				// Prazo de previsão de atendimento
				solicitacaoTipoEspecificacao.setDiasPrazo(new Integer(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getPrazoPrevisaoAtendimento()));
			}
			// Descrição da especificação
			solicitacaoTipoEspecificacao
					.setDescricao(atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getDescricaoSolicitacao());

			// Pavimento de calçada obrigatório
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorPavimentoCalcada() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPavimentoCalcada().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorPavimentoCalcada(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorPavimentoCalcada()));
			}
			// Pavimento de rua obrigatório
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorPavimentoRua() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPavimentoRua().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorPavimentoRua(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorPavimentoRua()));
			}

			// refere-se a ligação de agua
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorLigacaoAgua() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorLigacaoAgua().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorLigacaoAgua(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorLigacaoAgua()));
			}
			// Cobrança de material
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorCobrancaMaterial() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCobrancaMaterial().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorCobrancaMaterial(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorCobrancaMaterial()));
			}
			// Matricula do imóvel obrigatório
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorMatriculaImovel() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorMatriculaImovel().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorMatricula(new Integer(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorMatriculaImovel()));
			}
			// Indicador Urgência
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorUrgencia() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorUrgencia().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorUrgencia(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorUrgencia()));
			}
			// Parecer de encerramento obrigatório
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorParecerEncerramento() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorParecerEncerramento().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorParecerEncerramento(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorParecerEncerramento()));
			}
			// Gera débito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorGerarDebito() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGerarDebito().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorGeracaoDebito(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorGerarDebito()));
			}
			// Gera Crédito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorGerarCredito() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGerarCredito().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorGeracaoCredito(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorGerarCredito()));
			}

			// Encerramento Automático
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorEncerramentoAutomatico() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorEncerramentoAutomatico().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorEncerramentoAutomatico(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorEncerramentoAutomatico()));
			}
			
			//Indicador loja virtual
			if(Util.verificarNaoVazio(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorLojaVirtual())){
				solicitacaoTipoEspecificacao.setIndicadorLojaVirtual(new Short(atualizarAdicionarSolicitacaoEspecificacaoActionForm.getIndicadorLojaVirtual()));
			}

			// hora e data correntes
			solicitacaoTipoEspecificacao.setUltimaAlteracao(new Date());

			// Unidade inicial tramitação
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdUnidadeTramitacao() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdUnidadeTramitacao().equals("")) {
				// Verifica se o código foi modificado
				if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
						.getDescricaoUnidadeTramitacao() == null
						|| atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getDescricaoUnidadeTramitacao().trim().equals(
										"")) {

					FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

					filtroUnidadeOrganizacional
							.adicionarParametro(new ParametroSimples(
									FiltroUnidadeOrganizacional.ID,
									atualizarAdicionarSolicitacaoEspecificacaoActionForm
											.getIdUnidadeTramitacao()));

					filtroUnidadeOrganizacional
							.adicionarParametro(new ParametroSimples(
									FiltroUnidadeOrganizacional.INDICADOR_USO,
									ConstantesSistema.INDICADOR_USO_ATIVO));

					Collection unidadeOrganizacionalEncontrado = fachada
							.pesquisar(filtroUnidadeOrganizacional,
									UnidadeOrganizacional.class.getName());

					if (unidadeOrganizacionalEncontrado != null
							&& !unidadeOrganizacionalEncontrado.isEmpty()) {
						UnidadeOrganizacional uinidadeOrganizacional = (UnidadeOrganizacional) ((List) unidadeOrganizacionalEncontrado)
								.get(0);
						solicitacaoTipoEspecificacao
								.setUnidadeOrganizacional(uinidadeOrganizacional);

					} else {
						throw new ActionServletException(
								"atencao.pesquisa_inexistente", null,
								"Unidade Organizacional");
					}

				} else {
					UnidadeOrganizacional unidadeOrganizacional = new UnidadeOrganizacional();
					unidadeOrganizacional.setId(new Integer(
							atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdUnidadeTramitacao()));
					solicitacaoTipoEspecificacao
							.setUnidadeOrganizacional(unidadeOrganizacional);
				}
			}

			// id do tipo da solicitação gerada
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdServicoOS() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdServicoOS().equals("")) {

				// Verifica se o código foi modificado
				if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
						.getDescricaoServicoOS() == null
						|| atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getDescricaoServicoOS().trim().equals("")) {

					FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

					filtroServicoTipo.adicionarParametro(new ParametroSimples(
							FiltroServicoTipo.ID,
							atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdServicoOS()));

					Collection servicoTipoEncontrado = fachada.pesquisar(
							filtroServicoTipo, ServicoTipo.class.getName());

					if (servicoTipoEncontrado != null
							&& !servicoTipoEncontrado.isEmpty()) {
						// [SF0003] - Validar Tipo Serviço
						fachada.verificarServicoTipoReferencia(new Integer(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIdServicoOS()));

						ServicoTipo servicoTipo = (ServicoTipo) ((List) servicoTipoEncontrado)
								.get(0);
						solicitacaoTipoEspecificacao
								.setServicoTipo(servicoTipo);
					} else {
						throw new ActionServletException(
								"atencao.pesquisa_inexistente", null,
								"Serviço Tipo");
					}
				} else {
					ServicoTipo servicoTipo = new ServicoTipo();
					servicoTipo.setId(new Integer(
							atualizarAdicionarSolicitacaoEspecificacaoActionForm
									.getIdServicoOS()));
					solicitacaoTipoEspecificacao.setServicoTipo(servicoTipo);
				}

			}

			// Gera ordem de serviço
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorGeraOrdemServico() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorGeraOrdemServico().equals("")) {

				solicitacaoTipoEspecificacao
						.setIndicadorGeracaoOrdemServico(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorGeraOrdemServico()));
			}
			// Cliente Obrigatório
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorCliente() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCliente().equals("")) {

				solicitacaoTipoEspecificacao.setIndicadorCliente(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorCliente()));
			}

			// Indicador verfificcação de débito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorVerificarDebito() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorVerificarDebito().equals("")) {

				solicitacaoTipoEspecificacao
						.setIndicadorVerificarDebito(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorVerificarDebito()));
			}

			// Situação imovel
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdSituacaoImovel() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdSituacaoImovel().equals("")) {
				EspecificacaoImovelSituacao especificacaoImovelSituacao = new EspecificacaoImovelSituacao();
				especificacaoImovelSituacao.setId(new Integer(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIdSituacaoImovel()));
				solicitacaoTipoEspecificacao
						.setEspecificacaoImovelSituacao(especificacaoImovelSituacao);
			}
			Collection colecaoEspecificacaoServicoTipo = (Collection) sessao
					.getAttribute("colecaoEspecificacaoServicoTipo");
			// recupera a coleção de especificacao servico tipo
			if (colecaoEspecificacaoServicoTipo != null
					&& !colecaoEspecificacaoServicoTipo.isEmpty()) {

				Collection colecao = new ArrayList();
				colecao.addAll(colecaoEspecificacaoServicoTipo);

				// [SF0004] - Validar Valor Ordem de Serviço 2ª parte
				fachada.verificarOrdemExecucaoForaOrdem(colecao);
				solicitacaoTipoEspecificacao
						.setEspecificacaoServicoTipos(new HashSet(
								colecaoEspecificacaoServicoTipo));
				sessao.removeAttribute("colecaoEspecificacaoServicoTipo");
			}

			// Colocado por Raphael Rossiter em 25/02/2008
			// Tipo de Débito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdDebitoTipo() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdDebitoTipo().equals("")) {

				FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();

				filtroDebitoTipo.adicionarParametro(new ParametroSimples(
						FiltroDebitoTipo.ID,
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIdDebitoTipo()));

				Collection debitoTipoEncontrado = fachada.pesquisar(
						filtroDebitoTipo, DebitoTipo.class.getName());

				if (debitoTipoEncontrado != null
						&& !debitoTipoEncontrado.isEmpty()) {

					DebitoTipo debitoTipo = (DebitoTipo) Util
							.retonarObjetoDeColecao(debitoTipoEncontrado);

					solicitacaoTipoEspecificacao.setDebitoTipo(debitoTipo);
				} else {

					// [FS0007] - Validar Tipo de débito
					throw new ActionServletException(
							"atencao.pesquisa_inexistente", null,
							"Tipo de Débito");
				}

			}

			// Valor do Débito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getValorDebito() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getValorDebito().equals("")) {

				solicitacaoTipoEspecificacao
						.setValorDebito(Util
								.formatarMoedaRealparaBigDecimal(atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getValorDebito()));
			}

			// Alterar Valor do débito
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorPermiteAlterarValor() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorPermiteAlterarValor().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorPermiteAlterarValor(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorPermiteAlterarValor()));
			}

			// Cobrar Juros
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorCobrarJuros() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorCobrarJuros().equals("")) {
				solicitacaoTipoEspecificacao.setIndicadorCobrarJuros(new Short(
						atualizarAdicionarSolicitacaoEspecificacaoActionForm
								.getIndicadorCobrarJuros()));
			}

			// indicador de uso ativo
			solicitacaoTipoEspecificacao.setIndicadorUso(new Short(
					ConstantesSistema.INDICADOR_USO_ATIVO));

			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIdEspecificacao() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIdEspecificacao().equals("")) {
				SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacaoNovoRA = new SolicitacaoTipoEspecificacao();
				solicitacaoTipoEspecificacaoNovoRA
						.setId(Integer
								.parseInt(atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIdEspecificacao()));

				SolicitacaoTipo solicitacaoTipo = new SolicitacaoTipo();
				solicitacaoTipo
						.setId(Integer
								.parseInt(atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIdTipoSolicitacao()));
				solicitacaoTipoEspecificacaoNovoRA
						.setSolicitacaoTipo(solicitacaoTipo);

				solicitacaoTipoEspecificacao
						.setSolicitacaoTipoEspecificacaoNovoRA(solicitacaoTipoEspecificacaoNovoRA);
			}

			// Informar conta no Registro de Atendimento
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorInformarContaRA() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorInformarContaRA().equals("")) {
				solicitacaoTipoEspecificacao
						.setIndicadorInformarContaRA(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorInformarContaRA()));
			}

			// Informar Pagamento em duplicidade
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorInformarPagamentoDP() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorInformarPagamentoDP().equals("")) {

				solicitacaoTipoEspecificacao
						.setIndicadorInformarPagamentoDuplicidade(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorInformarPagamentoDP()));
			}

			// Informar Alteração no Vencimento Alternativo
			if (atualizarAdicionarSolicitacaoEspecificacaoActionForm
					.getIndicadorAlterarVencimentoAlternativo() != null
					&& !atualizarAdicionarSolicitacaoEspecificacaoActionForm
							.getIndicadorAlterarVencimentoAlternativo().equals("")) {

				solicitacaoTipoEspecificacao
						.setIndicadorAlterarVencimentoAlternativo(new Short(
								atualizarAdicionarSolicitacaoEspecificacaoActionForm
										.getIndicadorAlterarVencimentoAlternativo()));
			}
			if (!colecaoSolicitacaoTipoEspecificacao
					.contains(solicitacaoTipoEspecificacao)) {
				colecaoSolicitacaoTipoEspecificacao
						.add(solicitacaoTipoEspecificacao);
			}

		}

		sessao.setAttribute("colecaoSolicitacaoTipoEspecificacao",
				colecaoSolicitacaoTipoEspecificacao);

		// manda um parametro para fechar o popup
		httpServletRequest.setAttribute("fecharPopup", 1);

		return retorno;
	}
}
