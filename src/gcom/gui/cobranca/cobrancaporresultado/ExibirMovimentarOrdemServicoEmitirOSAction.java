package gcom.gui.cobranca.cobrancaporresultado;

import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.cadastro.empresa.FiltroEmpresaCobrancaFaixa;
import gcom.cadastro.empresa.FiltroEmpresaContratoCobranca;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.FiltroComandoEmpresaCobrancaConta;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.util.filtro.ParametroSimplesIn;

import java.math.BigDecimal;
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
 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
 */
public class ExibirMovimentarOrdemServicoEmitirOSAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno para a tela de Emitir OS
		ActionForward retorno = actionMapping.findForward("movimentarOrdemServicoEmitirOS");

		MovimentarOrdemServicoActionForm form = (MovimentarOrdemServicoActionForm) actionForm;

		pesquisarCamposEnter(httpServletRequest, form, this.getFachada(), this.getSessao(httpServletRequest));

		if (httpServletRequest.getParameter("confirmado") != null && httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {

			httpServletRequest.setAttribute("confirmacao", "true");

			retorno = actionMapping.findForward("emitirOSAction");

			return retorno;

		}

		if (httpServletRequest.getParameter("limparTotalizacao") != null && httpServletRequest.getParameter("limparTotalizacao").equalsIgnoreCase("SIM")) {

			form.setQtdContas("");
			form.setQtdClientes("");
			form.setValorTotalDivida("");
			form.setQtdeTotalClientes("");

			form.setTotalSelecionado(null);
			form.setColecaoInformada(null);
			this.getSessao(httpServletRequest).removeAttribute("colecaoQuantidadeContas");
			this.getSessao(httpServletRequest).removeAttribute("colecaoFaixa");
			this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeContas");
			this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeClientes");
			this.getSessao(httpServletRequest).removeAttribute("colecaoValorTotalDivida");
		}

		// 1ª div
		if (httpServletRequest.getParameter("pesquisarQtdContas") != null && form.getIdEmpresa() != null && !form.getIdEmpresa().equals("")) {

			MovimentarOrdemServicoEmitirOSHelper movimentarOrdemServicoEmitirOSHelper = this.retornaQtdContas(form);

			Integer idEmpresaContratoCobranca = new Integer(form.getIdEmpresa());

			if (movimentarOrdemServicoEmitirOSHelper != null) {

				this.getSessao(httpServletRequest).setAttribute("movimentarOrdemServicoEmitirOSHelper", movimentarOrdemServicoEmitirOSHelper);

				boolean agruparPorImovel = true;

				FiltroEmpresaContratoCobranca filtroEmpresaContratoCobranca = new FiltroEmpresaContratoCobranca();
				filtroEmpresaContratoCobranca.adicionarParametro(new ParametroSimples(FiltroEmpresaContratoCobranca.EMPRESA_ID, idEmpresaContratoCobranca));

				Collection colecaoEmpresaContratoCobranca = Fachada.getInstancia().pesquisar(filtroEmpresaContratoCobranca, EmpresaContratoCobranca.class.getName());

				if (colecaoEmpresaContratoCobranca != null && !colecaoEmpresaContratoCobranca.isEmpty()) {
					EmpresaContratoCobranca empresaContratoCobranca = (EmpresaContratoCobranca) Util.retonarObjetoDeColecao(colecaoEmpresaContratoCobranca);

					idEmpresaContratoCobranca = empresaContratoCobranca.getId();

					if (empresaContratoCobranca.getPercentualContratoCobranca() != null && empresaContratoCobranca.getPercentualContratoCobranca().compareTo(BigDecimal.ZERO) != 0) {
						agruparPorImovel = false;
					}
				}

				if (agruparPorImovel) {
					Collection<Object[]> colecaoDados = this.getFachada().pesquisarQuantidadeContasComandoAgrupandoPorImovel(movimentarOrdemServicoEmitirOSHelper);

					if (colecaoDados != null && !colecaoDados.isEmpty()) {
						Collection<String> colecaoFaixa = new ArrayList();
						Collection<Integer> colecaoQtdeContas = new ArrayList();
						Collection<Integer> colecaoQtdeClientes = new ArrayList();
						Collection<BigDecimal> colecaoValorTotalDivida = new ArrayList();

						Integer qtdeTotalClientes = 0;

						FiltroEmpresaCobrancaFaixa filtroEmpresaCobrancaFaixa = new FiltroEmpresaCobrancaFaixa();
						filtroEmpresaCobrancaFaixa.adicionarParametro(new ParametroSimples(FiltroEmpresaCobrancaFaixa.EMPRESA_CONTRATO_COBRANCA_ID, idEmpresaContratoCobranca));
						filtroEmpresaCobrancaFaixa.setCampoOrderBy(FiltroEmpresaCobrancaFaixa.NUMERO_MAXIMO_CONTAS_FAIXA);

						List<EmpresaCobrancaFaixa> colecaoEmpresaCobrancaFaixa = (List<EmpresaCobrancaFaixa>) Fachada.getInstancia().pesquisar(filtroEmpresaCobrancaFaixa,
								EmpresaCobrancaFaixa.class.getName());

						if (colecaoEmpresaCobrancaFaixa != null && !colecaoEmpresaCobrancaFaixa.isEmpty()) {

							EmpresaCobrancaFaixa empresaCobrancaFaixa = (EmpresaCobrancaFaixa) colecaoEmpresaCobrancaFaixa.get(0);
							Integer numeroMinimoContas = null;
							Integer numeroMaximoContas = empresaCobrancaFaixa.getNumeroMinimoContasFaixa() - 1;

							Integer qtdeContas = 0;
							Integer qtdeClientes = 0;
							BigDecimal valorTotalDivida = new BigDecimal(0.0);
							Iterator iteratorColecaoDados = colecaoDados.iterator();

							for (int i = 0; i < colecaoEmpresaCobrancaFaixa.size(); i++) {

								empresaCobrancaFaixa = (EmpresaCobrancaFaixa) colecaoEmpresaCobrancaFaixa.get(i);

								numeroMinimoContas = empresaCobrancaFaixa.getNumeroMinimoContasFaixa();

								numeroMaximoContas = null;

								if (i < (colecaoEmpresaCobrancaFaixa.size() - 1)) {
									numeroMaximoContas = ((EmpresaCobrancaFaixa) colecaoEmpresaCobrancaFaixa.get(i + 1)).getNumeroMinimoContasFaixa() - 1;
								}

								qtdeContas = 0;

								qtdeClientes = 0;

								valorTotalDivida = new BigDecimal(0.0);

								iteratorColecaoDados = colecaoDados.iterator();

								while (iteratorColecaoDados.hasNext()) {
									Object[] dados = (Object[]) iteratorColecaoDados.next();

									if (dados[0] != null) {
										Integer qnt = (Integer) dados[0];

										if (qnt >= numeroMinimoContas && (numeroMaximoContas == null || qnt <= numeroMaximoContas)) {

											qtdeContas += qnt;

											if (dados[1] != null) {
												qtdeClientes += (Integer) dados[1];
												qtdeTotalClientes += (Integer) dados[1];
											}

											if (dados[2] != null) {
												valorTotalDivida = valorTotalDivida.add((BigDecimal) dados[2]);
											}
										}
									}

								}

								if (i < (colecaoEmpresaCobrancaFaixa.size() - 1)) {
									colecaoFaixa.add(numeroMinimoContas + " a " + numeroMaximoContas);
								} else {
									colecaoFaixa.add("Mais de " + numeroMinimoContas);
								}
								colecaoQtdeContas.add(qtdeContas);
								colecaoQtdeClientes.add(qtdeClientes);
								colecaoValorTotalDivida.add(valorTotalDivida);

							}

							if (!colecaoQtdeContas.isEmpty() && !colecaoQtdeClientes.isEmpty() && !colecaoValorTotalDivida.isEmpty()) {

								form.setColecaoInformada("sim");
								form.setQtdeTotalClientes(qtdeTotalClientes.toString());
								this.getSessao(httpServletRequest).setAttribute("colecaoQuantidadeContas", true);
								this.getSessao(httpServletRequest).setAttribute("tamanho", colecaoFaixa.size());
								this.getSessao(httpServletRequest).setAttribute("colecaoFaixa", colecaoFaixa);
								this.getSessao(httpServletRequest).setAttribute("colecaoQtdeContas", colecaoQtdeContas);
								this.getSessao(httpServletRequest).setAttribute("colecaoQtdeClientes", colecaoQtdeClientes);
								this.getSessao(httpServletRequest).setAttribute("colecaoValorTotalDivida", colecaoValorTotalDivida);

							} else {

								form.setQtdContas("0");
								form.setQtdClientes("0");
								form.setValorTotalDivida(Util.formatarMoedaReal(BigDecimal.ZERO));
								form.setQtdeTotalClientes("0");

								form.setColecaoInformada(null);
								this.getSessao(httpServletRequest).removeAttribute("colecaoQuantidadeContas");
								this.getSessao(httpServletRequest).removeAttribute("colecaoFaixa");
								this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeContas");
								this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeClientes");
								this.getSessao(httpServletRequest).removeAttribute("colecaoValorTotalDivida");

							}

						} else {
							form.setQtdContas("0");
							form.setQtdClientes("0");
							form.setValorTotalDivida(Util.formatarMoedaReal(BigDecimal.ZERO));
							form.setQtdeTotalClientes("0");

							form.setColecaoInformada(null);
							this.getSessao(httpServletRequest).removeAttribute("colecaoQuantidadeContas");
							this.getSessao(httpServletRequest).removeAttribute("colecaoFaixa");
							this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeContas");
							this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeClientes");
							this.getSessao(httpServletRequest).removeAttribute("colecaoValorTotalDivida");

						}

					} else {
						form.setQtdContas("0");
						form.setQtdClientes("0");
						form.setValorTotalDivida(Util.formatarMoedaReal(BigDecimal.ZERO));
						form.setQtdeTotalClientes("0");

						form.setColecaoInformada(null);
						this.getSessao(httpServletRequest).removeAttribute("colecaoQuantidadeContas");
						this.getSessao(httpServletRequest).removeAttribute("colecaoFaixa");
						this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeContas");
						this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeClientes");
						this.getSessao(httpServletRequest).removeAttribute("colecaoValorTotalDivida");

					}

				} else {
					Object[] dados = this.getFachada().pesquisarQuantidadeContasComando(movimentarOrdemServicoEmitirOSHelper);

					form.setColecaoInformada(null);
					this.getSessao(httpServletRequest).removeAttribute("colecaoQuantidadeContas");
					this.getSessao(httpServletRequest).removeAttribute("colecaoFaixa");
					this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeContas");
					this.getSessao(httpServletRequest).removeAttribute("colecaoQtdeClientes");
					this.getSessao(httpServletRequest).removeAttribute("colecaoValorTotalDivida");

					if (dados != null) {
						Integer qtdeContas = null;

						Integer qtdeClientes = null;

						BigDecimal valorTotalDivida = new BigDecimal(0.0);

						if (dados[0] != null) {
							qtdeContas = (Integer) dados[0];
						}

						if (dados[1] != null) {
							qtdeClientes = (Integer) dados[1];
						}

						if (dados[2] != null) {
							valorTotalDivida = (BigDecimal) dados[2];
						}

						form.setQtdContas(qtdeContas.toString());
						form.setQtdClientes(qtdeClientes.toString());
						form.setValorTotalDivida(Util.formatarMoedaReal(valorTotalDivida));
						form.setQtdeTotalClientes(qtdeClientes.toString());
					}
				}
				form.setTotalSelecionado("sim");
				this.getSessao(httpServletRequest).setAttribute("habilitaCamposCiclo", true);
			} else {
				this.getSessao(httpServletRequest).removeAttribute("habilitaCamposCiclo");
				this.getSessao(httpServletRequest).removeAttribute("movimentarOrdemServicoEmitirOSHelper");
				throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
			}

		}

		// 2ª div
		if (httpServletRequest.getParameter("pesquisarOSEmpresaCobranca") != null && httpServletRequest.getParameter("pesquisarOSEmpresaCobranca").equalsIgnoreCase("SIM")) {
			Integer idTipoServico = null;

			if (form.getIdTipoServico() != null && !form.getIdTipoServico().equals("")) {
				idTipoServico = new Integer(form.getIdTipoServico());
			}

			Collection<Object[]> colecaoDados = Fachada.getInstancia().pesquisarDadosOSGeradasPelaEmpresa(new Integer(form.getIdComandoContaCobranca()), idTipoServico);

			if (colecaoDados != null && !colecaoDados.isEmpty()) {
				Collection<OrdemServicoGeradaEmpresaCobrancaHelper> colecaoOSEmpresaCobranca = new ArrayList();

				Iterator iterator = colecaoDados.iterator();

				while (iterator.hasNext()) {
					Object[] dados = (Object[]) iterator.next();

					OrdemServicoGeradaEmpresaCobrancaHelper helper = new OrdemServicoGeradaEmpresaCobrancaHelper();

					if (dados[0] != null) {
						helper.setNumeroOS(((Integer) dados[0]).toString());
					}
					if (dados[1] != null) {
						helper.setTipoServico((String) dados[1]);
					}
					if (dados[2] != null) {
						helper.setMatriculaImovel(((Integer) dados[2]).toString());
					}
					if (dados[3] != null) {
						helper.setCliente((String) dados[3]);
					}

					colecaoOSEmpresaCobranca.add(helper);
				}

				this.getSessao(httpServletRequest).setAttribute("colecaoOSEmpresaCobranca", colecaoOSEmpresaCobranca);

			} else {
				this.getSessao(httpServletRequest).removeAttribute("colecaoOSEmpresaCobranca");
			}
		}

		// 3ª div
		if (httpServletRequest.getParameter("pesquisarOSRA") != null && httpServletRequest.getParameter("pesquisarOSRA").equalsIgnoreCase("SIM")) {
			Integer idTipoServico = null;

			if (form.getIdTipoServicoRA() != null && !form.getIdTipoServicoRA().equals("")) {
				idTipoServico = new Integer(form.getIdTipoServicoRA());
			}

			Collection<Object[]> colecaoDados = Fachada.getInstancia().pesquisarDadosOSRegistroAtendimento(new Integer(form.getIdComandoContaCobranca()), idTipoServico);

			if (colecaoDados != null && !colecaoDados.isEmpty()) {
				Collection<OrdemServicoGeradaEmpresaCobrancaHelper> colecaoOSRegistroAtendimento = new ArrayList();

				Iterator iterator = colecaoDados.iterator();

				while (iterator.hasNext()) {
					Object[] dados = (Object[]) iterator.next();

					OrdemServicoGeradaEmpresaCobrancaHelper helper = new OrdemServicoGeradaEmpresaCobrancaHelper();

					if (dados[0] != null) {
						helper.setNumeroOS(((Integer) dados[0]).toString());
					}
					if (dados[1] != null) {
						helper.setTipoServico((String) dados[1]);
					}
					if (dados[2] != null) {
						helper.setMatriculaImovel(((Integer) dados[2]).toString());
					}
					if (dados[3] != null) {
						helper.setCliente((String) dados[3]);
					}

					colecaoOSRegistroAtendimento.add(helper);
				}

				this.getSessao(httpServletRequest).setAttribute("colecaoOSRegistroAtendimento", colecaoOSRegistroAtendimento);

			} else {
				this.getSessao(httpServletRequest).removeAttribute("colecaoOSRegistroAtendimento");
			}
		}

		if (this.getSessao(httpServletRequest).getAttribute("statusWizard") != null) {
			// Monta o Status do Wizard
			StatusWizard statusWizard = (StatusWizard) this.getSessao(httpServletRequest).getAttribute("statusWizard");

			statusWizard.setNomeBotaoConcluir("Emitir OS");

			// manda o statusWizard para a sessão
			this.getSessao(httpServletRequest).setAttribute("statusWizard", statusWizard);
		}

		// Retorna o mapemaneto na variável "retorno"
		return retorno;
	}

	private void pesquisarCamposEnter(HttpServletRequest httpServletRequest, MovimentarOrdemServicoActionForm form, Fachada fachada, HttpSession sessao) {

		String idLocalidadeOrigem = form.getIdLocalidadeOrigem();

		// Pesquisa a localidade inicial
		if (idLocalidadeOrigem != null
				&& !idLocalidadeOrigem.trim().equals("")
				&& httpServletRequest.getParameter("tipoPesquisa") != null
				&& (httpServletRequest.getParameter("tipoPesquisa").equals("localidadeOrigem") || httpServletRequest.getParameter("tipoPesquisa").equals("setorComercialOrigem") || httpServletRequest
						.getParameter("tipoPesquisa").equals("quadraInicial"))) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeOrigem));

			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

				form.setIdLocalidadeOrigem(localidade.getId().toString());
				form.setNomeLocalidadeOrigem(localidade.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialOrigem");

				if (httpServletRequest.getParameter("tipoPesquisa").equals("localidadeOrigem")) {

					form.setIdLocalidadeDestino(localidade.getId().toString());

					form.setNomeLocalidadeDestino(localidade.getDescricao());
					httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialDestino");

				}

				String codigoSetorComercialOrigem = form.getCodigoSetorComercialOrigem();

				// Pesquisa o setor comercial inicial
				if (codigoSetorComercialOrigem != null && !codigoSetorComercialOrigem.trim().equals("")) {

					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidade.getId()));
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialOrigem));

					Collection<SetorComercial> colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

					if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
						SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);

						form.setIdSetorComercialOrigem("" + setorComercial.getId());
						form.setCodigoSetorComercialOrigem("" + setorComercial.getCodigo());
						form.setDescricaoSetorComercialOrigem(setorComercial.getDescricao());
						httpServletRequest.setAttribute("nomeCampo", "idLocalidadeDestino");

						if (httpServletRequest.getParameter("tipoPesquisa").equals("setorComercialOrigem")) {

							form.setIdSetorComercialDestino("" + setorComercial.getId());
							form.setCodigoSetorComercialDestino("" + setorComercial.getCodigo());
							form.setDescricaoSetorComercialDestino(setorComercial.getDescricao());
							httpServletRequest.setAttribute("nomeCampo", "idLocalidadeDestino");

						}

						String codigoQuadraInicial = form.getCodigoQuadraInicial();

						// Pesquisa a quadra inicial
						if (codigoQuadraInicial != null && !codigoQuadraInicial.trim().equals("")) {

							FiltroQuadra filtroQuadra = new FiltroQuadra();
							filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercial.getId()));
							filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, codigoQuadraInicial));

							Collection<Quadra> colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());
							if (colecaoQuadra != null && !colecaoQuadra.isEmpty()) {
								Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);

								form.setCodigoQuadraInicial("" + quadra.getNumeroQuadra());
								form.setDescricaoQuadraInicial(quadra.getDescricao());
								httpServletRequest.setAttribute("nomeCampo", "codigoQuadraInicial");

								if (httpServletRequest.getParameter("tipoPesquisa").equals("quadraInicial")) {
									form.setCodigoQuadraFinal("" + quadra.getNumeroQuadra());
									form.setDescricaoQuadraFinal(quadra.getDescricao());
									httpServletRequest.setAttribute("nomeCampo", "codigoQuadraInicial");
								}

								sessao.removeAttribute("quadraInicialInexistente");
							} else {
								form.setCodigoQuadraInicial("");
								form.setDescricaoQuadraInicial("QUADRA INEXISTENTE");

								sessao.setAttribute("quadraInicialInexistente", true);
								httpServletRequest.setAttribute("nomeCampo", "codigoQuadraInicial");
							}

						}

						sessao.removeAttribute("setorComercialOrigemInexistente");
					} else {
						form.setIdSetorComercialOrigem("");
						form.setCodigoSetorComercialOrigem("");
						form.setDescricaoSetorComercialOrigem("SETOR COMERCIAL INEXISTENTE");

						form.setIdSetorComercialDestino("");
						form.setCodigoSetorComercialDestino("");
						form.setDescricaoSetorComercialDestino("");

						sessao.setAttribute("setorComercialOrigemInexistente", true);
						httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialOrigem");
					}

				}

				sessao.removeAttribute("localidadeOrigemInexistente");
			} else {
				form.setIdLocalidadeOrigem("");
				form.setNomeLocalidadeOrigem("LOCALIDADE INEXISTENTE");

				httpServletRequest.setAttribute("localidadeOrigemInexistente", true);

				form.setIdLocalidadeDestino("");
				form.setNomeLocalidadeDestino("");

				sessao.setAttribute("localidadeOrigemInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idLocalidadeOrigem");
			}

		}

		String idLocalidadeDestino = form.getIdLocalidadeDestino();

		// Pesquisa a localidade final
		if (idLocalidadeDestino != null
				&& !idLocalidadeDestino.trim().equals("")
				&& httpServletRequest.getParameter("tipoPesquisa") != null
				&& (httpServletRequest.getParameter("tipoPesquisa").equals("localidadeDestino") || httpServletRequest.getParameter("tipoPesquisa").equals("setorComercialDestino") || httpServletRequest
						.getParameter("tipoPesquisa").equals("quadraFinal"))) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeDestino));

			Collection<Localidade> colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {
				Localidade localidade = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidade);

				form.setIdLocalidadeDestino(localidade.getId().toString());
				form.setNomeLocalidadeDestino(localidade.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialDestino");

				String codigoSetorComercialDestino = form.getCodigoSetorComercialDestino();

				// Pesquisa o setor comercial inicial
				if (codigoSetorComercialDestino != null && !codigoSetorComercialDestino.trim().equals("")) {

					FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, localidade.getId()));
					filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialDestino));

					Collection<SetorComercial> colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

					if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
						SetorComercial setorComercial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);

						form.setIdSetorComercialDestino("" + setorComercial.getId());
						form.setCodigoSetorComercialDestino("" + setorComercial.getCodigo());
						form.setDescricaoSetorComercialDestino(setorComercial.getDescricao());
						httpServletRequest.setAttribute("nomeCampo", "referenciaInicial");

						String codigoQuadraFinal = form.getCodigoQuadraFinal();

						// Pesquisa a quadra final
						if (codigoQuadraFinal != null && !codigoQuadraFinal.trim().equals("")) {

							if (form.getCodigoQuadraInicial() != null && !form.getCodigoQuadraInicial().trim().equals("")) {
								Integer codQuadraFinal = new Integer(codigoQuadraFinal);
								Integer codQuadraInicial = new Integer(form.getCodigoQuadraInicial());

								if (codQuadraFinal.compareTo(codQuadraInicial) < 0) {
									throw new ActionServletException("atencao.quadraInicial.maior.que.quadraFinal");
								}
							}

							FiltroQuadra filtroQuadra = new FiltroQuadra();
							filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, setorComercial.getId()));
							filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, codigoQuadraFinal));

							Collection<Quadra> colecaoQuadra = fachada.pesquisar(filtroQuadra, Quadra.class.getName());

							if (colecaoQuadra != null && !colecaoQuadra.isEmpty()) {
								Quadra quadra = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadra);

								form.setCodigoQuadraFinal("" + quadra.getNumeroQuadra());
								form.setDescricaoQuadraFinal(quadra.getDescricao());
								httpServletRequest.setAttribute("nomeCampo", "codigoQuadraFinal");

								sessao.removeAttribute("quadraFinalInexistente");
							} else {
								form.setCodigoQuadraFinal("");
								form.setDescricaoQuadraFinal("QUADRA INEXISTENTE");

								sessao.setAttribute("quadraFinalInexistente", true);
								httpServletRequest.setAttribute("nomeCampo", "codigoQuadraFinal");
							}

						}

						sessao.removeAttribute("setorComercialDestinoInexistente");
					} else {
						form.setIdSetorComercialDestino("");
						form.setCodigoSetorComercialDestino("");
						form.setDescricaoSetorComercialDestino("SETOR COMERCIAL INEXISTENTE");

						sessao.setAttribute("setorComercialDestinoInexistente", true);
						httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialDestino");
					}

				}

				sessao.removeAttribute("localidadeDestinoInexistente");
			} else {
				form.setIdLocalidadeDestino("");
				form.setNomeLocalidadeDestino("LOCALIDADE INEXISTENTE");

				sessao.setAttribute("localidadeDestinoInexistente", true);
				httpServletRequest.setAttribute("nomeCampo", "idLocalidadeDestino");
			}

		}

	}

	private MovimentarOrdemServicoEmitirOSHelper retornaQtdContas(MovimentarOrdemServicoActionForm form) {

		ComandoEmpresaCobrancaConta comando = new ComandoEmpresaCobrancaConta();

		FiltroComandoEmpresaCobrancaConta filtroComandoEmpresaCobrancaConta = new FiltroComandoEmpresaCobrancaConta();
		filtroComandoEmpresaCobrancaConta.adicionarParametro(new ParametroSimples(FiltroComandoEmpresaCobrancaConta.ID, form.getIdComandoContaCobranca()));
		filtroComandoEmpresaCobrancaConta.adicionarCaminhoParaCarregamentoEntidade(FiltroComandoEmpresaCobrancaConta.EMPRESA);

		Collection<ComandoEmpresaCobrancaConta> colecaoComando = this.getFachada().pesquisar(filtroComandoEmpresaCobrancaConta, ComandoEmpresaCobrancaConta.class.getName());

		if (colecaoComando != null || !colecaoComando.isEmpty()) {
			comando = (ComandoEmpresaCobrancaConta) Util.retonarObjetoDeColecao(colecaoComando);
		}

		comando.setId(new Integer(form.getIdComandoContaCobranca()));

		boolean algumParametroInformado = false;

		String numeroOSInicial = null;
		String numeroOSFinal = null;
		// Intervalo de OS
		if (form.getNumeroOSInicial() != null && !form.getNumeroOSInicial().equals("") && form.getNumeroOSFinal() != null && !form.getNumeroOSFinal().equals("")) {

			algumParametroInformado = true;

			numeroOSInicial = form.getNumeroOSInicial();
			numeroOSFinal = form.getNumeroOSFinal();

		}

		// Localidade Inicial
		if (form.getIdLocalidadeOrigem() != null && !form.getIdLocalidadeOrigem().equals("")) {

			algumParametroInformado = true;

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeOrigem()));

			Collection colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

				Localidade localidadeInicial = new Localidade();

				localidadeInicial.setId(new Integer(form.getIdLocalidadeOrigem()));

				comando.setLocalidadeInicial(localidadeInicial);
			} else {

				throw new ActionServletException("atencao.pesquisa.localidade_inicial_inexistente");

			}
		}

		// Localidade Final
		if (form.getIdLocalidadeDestino() != null && !form.getIdLocalidadeDestino().equals("")) {

			algumParametroInformado = true;

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getIdLocalidadeDestino()));

			Collection colecaoLocalidade = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade != null && !colecaoLocalidade.isEmpty()) {

				Localidade localidadeFinal = new Localidade();

				localidadeFinal.setId(new Integer(form.getIdLocalidadeDestino()));

				comando.setLocalidadeFinal(localidadeFinal);
			} else {

				throw new ActionServletException("atencao.pesquisa.localidade_final_inexistente");

			}

		}

		// Setor Comercial Inicial
		if (form.getCodigoSetorComercialOrigem() != null && !form.getCodigoSetorComercialOrigem().equals("")) {

			algumParametroInformado = true;

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidadeOrigem()));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercialOrigem()));

			Collection colecaoSetorComercial = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

				SetorComercial setorComercialInicial = (SetorComercial) colecaoSetorComercial.iterator().next();

				setorComercialInicial.setCodigo(new Integer(form.getCodigoSetorComercialOrigem()));

				comando.setCodigoSetorComercialInicial(setorComercialInicial.getCodigo());
			} else {

				throw new ActionServletException("atencao.pesquisa.setor_inicial_inexistente");

			}

		}

		// Setor Comercial Final
		if (form.getCodigoSetorComercialDestino() != null && !form.getCodigoSetorComercialDestino().equals("")) {

			algumParametroInformado = true;

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, form.getIdLocalidadeDestino()));

			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, form.getCodigoSetorComercialDestino()));

			Collection colecaoSetorComercial = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {

				SetorComercial setorComercialFinal = (SetorComercial) colecaoSetorComercial.iterator().next();

				setorComercialFinal.setCodigo(new Integer(form.getCodigoSetorComercialOrigem()));

				comando.setCodigoSetorComercialFinal(setorComercialFinal.getCodigo());
			} else {

				throw new ActionServletException("atencao.pesquisa.setor_final_inexistente");

			}

		}

		// Quadra Inicial
		if (form.getCodigoQuadraInicial() != null && !form.getCodigoQuadraInicial().equals("")) {

			algumParametroInformado = true;

			FiltroQuadra filtroQuadra = new FiltroQuadra();

			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, form.getIdSetorComercialOrigem()));

			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, form.getCodigoQuadraInicial()));

			Collection colecaoQuadra = this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());

			if (colecaoQuadra != null && !colecaoQuadra.isEmpty()) {

				Quadra quadraInicial = (Quadra) colecaoQuadra.iterator().next();

				quadraInicial.setNumeroQuadra(new Integer(form.getCodigoQuadraInicial()));

				comando.setNumeroQuadraInicial(quadraInicial.getNumeroQuadra());
			} else {

				throw new ActionServletException("atencao.pesquisa.quadra_inicial_inexistente");

			}

		}

		// Quadra Final
		if (form.getCodigoQuadraInicial() != null && !form.getCodigoQuadraInicial().equals("")) {

			algumParametroInformado = true;

			FiltroQuadra filtroQuadra = new FiltroQuadra();

			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_SETORCOMERCIAL, form.getIdSetorComercialDestino()));

			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, form.getCodigoQuadraFinal()));

			Collection colecaoQuadra = this.getFachada().pesquisar(filtroQuadra, Quadra.class.getName());

			if (colecaoQuadra != null && !colecaoQuadra.isEmpty()) {

				Quadra quadra = (Quadra) colecaoQuadra.iterator().next();

				quadra.setNumeroQuadra(new Integer(form.getCodigoQuadraFinal()));

				comando.setNumeroQuadraFinal(quadra.getNumeroQuadra());
			} else {

				throw new ActionServletException("atencao.pesquisa.quadra_final_inexistente");

			}

		}

		if (form.getIdsCategoria() != null) {

			String[] idsCategoria = form.getIdsCategoria();

			for (int i = 0; i < idsCategoria.length; i++) {

				if (idsCategoria[i].equals(Categoria.COMERCIAL.toString())) {
					comando.setIndicadorComercial(ConstantesSistema.SIM.intValue());
				} else if (idsCategoria[i].equals(Categoria.INDUSTRIAL.toString())) {
					comando.setIndicadorIndustrial(ConstantesSistema.SIM.intValue());
				} else if (idsCategoria[i].equals(Categoria.RESIDENCIAL.toString())) {
					comando.setIndicadorResidencial(ConstantesSistema.SIM.intValue());
				} else if (idsCategoria[i].equals(Categoria.PUBLICO.toString())) {
					comando.setIndicadorPublico(ConstantesSistema.SIM.intValue());
				}

			}
		}

		Collection colecaoUnidadeNegocio = null;
		// Unidade Negocio
		if (form.getIdsUnidadeNegocio() != null && form.getIdsUnidadeNegocio().length > 0) {

			String[] idsUnidadeNegocio = form.getIdsUnidadeNegocio();
			Collection<String> colecaoIdsUnidadeNegocio = new ArrayList();
			boolean unidadeInformada = true;

			for (int i = 0; i < idsUnidadeNegocio.length; i++) {
				if (idsUnidadeNegocio[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					unidadeInformada = false;
					break;
				}
				colecaoIdsUnidadeNegocio.add(idsUnidadeNegocio[i]);
			}

			if (unidadeInformada) {
				algumParametroInformado = true;

				FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();

				filtroUnidadeNegocio.adicionarParametro(new ParametroSimplesIn(FiltroUnidadeNegocio.ID, colecaoIdsUnidadeNegocio));

				colecaoUnidadeNegocio = this.getFachada().pesquisar(filtroUnidadeNegocio, UnidadeNegocio.class.getName());

				if (colecaoUnidadeNegocio != null && !colecaoUnidadeNegocio.isEmpty()) {

					if (colecaoUnidadeNegocio.size() == 1) {
						UnidadeNegocio unidadeNegocio = (UnidadeNegocio) colecaoUnidadeNegocio.iterator().next();

						comando.setUnidadeNegocio(unidadeNegocio);
					}

				} else {
					throw new ActionServletException("atencao.unidade_negocio.inexistente");
				}
			}
		}

		Collection colecaoGerenciaRegional = null;
		// Gerência Regional
		if (form.getIdsGerenciaRegional() != null && form.getIdsGerenciaRegional().length > 0) {

			String[] idsGerenciaRegional = form.getIdsGerenciaRegional();
			Collection<String> colecaoIdsGerenciaRegional = new ArrayList();
			boolean gerenciaRegionalInformada = true;

			for (int i = 0; i < idsGerenciaRegional.length; i++) {
				if (idsGerenciaRegional[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					gerenciaRegionalInformada = false;
					break;
				}
				colecaoIdsGerenciaRegional.add(idsGerenciaRegional[i]);
			}

			if (gerenciaRegionalInformada) {
				algumParametroInformado = true;

				FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();

				filtroGerenciaRegional.adicionarParametro(new ParametroSimplesIn(FiltroGerenciaRegional.ID, colecaoIdsGerenciaRegional));

				colecaoGerenciaRegional = this.getFachada().pesquisar(filtroGerenciaRegional, GerenciaRegional.class.getName());

				if (colecaoGerenciaRegional != null && !colecaoGerenciaRegional.isEmpty()) {

					if (colecaoGerenciaRegional.size() == 1) {
						GerenciaRegional gerenciaRegional = (GerenciaRegional) colecaoGerenciaRegional.iterator().next();

						comando.setGerenciaRegional(gerenciaRegional);
					}

				} else {
					throw new ActionServletException("atencao.unidade_negocio.inexistente");
				}
			}
		}

		Collection colecaoImovelPerfil = null;
		// Imovel Perfil
		if (form.getIdsImovelPerfil() != null && form.getIdsImovelPerfil().length > 0) {

			String[] idsImovelPerfil = form.getIdsImovelPerfil();
			Collection<String> colecaoIdsImovelPerfil = new ArrayList();
			boolean imovelPerfilInformada = true;

			for (int i = 0; i < idsImovelPerfil.length; i++) {
				if (idsImovelPerfil[i].equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					imovelPerfilInformada = false;
					break;
				}
				colecaoIdsImovelPerfil.add(idsImovelPerfil[i]);
			}

			if (imovelPerfilInformada) {
				algumParametroInformado = true;

				FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

				filtroImovelPerfil.adicionarParametro(new ParametroSimplesIn(FiltroImovelPerfil.ID, colecaoIdsImovelPerfil));

				colecaoImovelPerfil = this.getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

				if (colecaoImovelPerfil != null && !colecaoImovelPerfil.isEmpty()) {

					if (colecaoImovelPerfil.size() == 1) {
						ImovelPerfil imovelPerfil = (ImovelPerfil) colecaoImovelPerfil.iterator().next();

						comando.setImovelPerfil(imovelPerfil);
					}

				} else {
					throw new ActionServletException("atencao.unidade_negocio.inexistente");
				}
			}
		}

		// Valor Conta inicial
		if (form.getValorMinimo() != null && !form.getValorMinimo().equals("")) {

			algumParametroInformado = true;

			comando.setValorMinimoConta(Util.formatarMoedaRealparaBigDecimal(form.getValorMinimo()));
		}

		// Valor Conta Final
		if (form.getValorMaximo() != null && !form.getValorMaximo().equals("")) {

			algumParametroInformado = true;

			comando.setValorMaximoConta(Util.formatarMoedaRealparaBigDecimal(form.getValorMaximo()));
		}

		if (algumParametroInformado) {
			MovimentarOrdemServicoEmitirOSHelper movimentarOrdemServicoEmitirOSHelper = new MovimentarOrdemServicoEmitirOSHelper();
			movimentarOrdemServicoEmitirOSHelper.setComandoEmpresaCobrancaConta(comando);
			movimentarOrdemServicoEmitirOSHelper.setColecaoUnidadeNegocio(colecaoUnidadeNegocio);
			movimentarOrdemServicoEmitirOSHelper.setColecaoGerenciaRegional(colecaoGerenciaRegional);
			movimentarOrdemServicoEmitirOSHelper.setColecaoImovelPerfil(colecaoImovelPerfil);
			movimentarOrdemServicoEmitirOSHelper.setNumeroOSInicial(numeroOSInicial);
			movimentarOrdemServicoEmitirOSHelper.setNumeroOSFinal(numeroOSFinal);

			return movimentarOrdemServicoEmitirOSHelper;
		} else {
			return null;
		}

	}

}
