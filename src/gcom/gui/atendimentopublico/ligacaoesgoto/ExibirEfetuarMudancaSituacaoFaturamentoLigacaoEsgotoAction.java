package gcom.gui.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgoto;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgoto;
import gcom.atendimentopublico.ordemservico.FiltroServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.ordemservico.ServicoNaoCobrancaMotivo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.LigacaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirEfetuarMudancaSituacaoFaturamentoLigacaoEsgotoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		HttpSession sessao = request.getSession(false);
		ActionForward retorno = actionMapping.findForward("efetuarMudancaSituacaoFaturamentoLigacaoEsgoto");
		EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm form = (EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		form.setMostrarVolume(true);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		String idOrdemServico = null;
		if (form.getIdOrdemServico() != null) {
			idOrdemServico = form.getIdOrdemServico();
		} else {
			idOrdemServico = (String) request.getAttribute("veioEncerrarOS");
			form.setDataMudanca((String) request.getAttribute("dataEncerramento"));

			sessao.setAttribute("caminhoRetornoIntegracaoComercial", request.getAttribute("caminhoRetornoIntegracaoComercial"));
		}

		OrdemServico ordemServico = null;

		if (request.getAttribute("semMenu") != null) {
			sessao.setAttribute("semMenu", "SIM");
		} else {
			sessao.removeAttribute("semMenu");
		}

		form.setVolumeMinimoFixado(null);

		Boolean veioEncerrarOS = null;
		if (request.getAttribute("veioEncerrarOS") != null) {
			veioEncerrarOS = Boolean.TRUE;
		} else {
			if (form.getVeioEncerrarOS() != null && !form.getVeioEncerrarOS().equals("")) {
				if (form.getVeioEncerrarOS().toLowerCase().equals("true")) {
					veioEncerrarOS = veioEncerrarOS = Boolean.TRUE;
				} else {
					veioEncerrarOS = veioEncerrarOS = Boolean.FALSE;
				}
			} else {
				veioEncerrarOS = Boolean.FALSE;
			}
		}

		form.setVeioEncerrarOS("" + veioEncerrarOS);

		// Filtro para o campo Tpo Debito
		Collection colecaoNaoCobranca = (Collection) sessao.getAttribute("colecaoNaoCobranca");
		if (colecaoNaoCobranca == null) {
			FiltroServicoNaoCobrancaMotivo filtroServicoNaoCobrancaMotivo = new FiltroServicoNaoCobrancaMotivo();

			filtroServicoNaoCobrancaMotivo.setCampoOrderBy(FiltroDebitoTipo.DESCRICAO);

			colecaoNaoCobranca = fachada.pesquisar(filtroServicoNaoCobrancaMotivo, ServicoNaoCobrancaMotivo.class.getName());

			if (colecaoNaoCobranca != null && !colecaoNaoCobranca.isEmpty()) {
				sessao.setAttribute("colecaoNaoCobranca", colecaoNaoCobranca);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Motivo da Não Cobrança");
			}
		}

		if (idOrdemServico != null && !idOrdemServico.trim().equals("")) {
			ordemServico = fachada.recuperaOSPorId(new Integer(idOrdemServico));

			if (ordemServico != null) {
				String tipoResultado = fachada.validarMudancaSituacaoFaturamentoLigacaoesgotoExibir(ordemServico, veioEncerrarOS);
				if (tipoResultado != null) {
					if (tipoResultado.trim().equalsIgnoreCase("TAMPONADO")) {
						form.setNovaSitLigacaoEsgoto("TAMPONADO");
						form.setMostrarVolume(false);
					} else if (tipoResultado.trim().equalsIgnoreCase("LIGADO FORA DE USO")) {
						form.setMostrarVolume(false);
						form.setNovaSitLigacaoEsgoto("LIGADO FORA DE USO");
					} else if (tipoResultado.trim().equalsIgnoreCase("POTENCIAL")) {
						form.setMostrarVolume(false);
						form.setNovaSitLigacaoEsgoto("POTENCIAL");
					} else if (tipoResultado.trim().equalsIgnoreCase("FACTIVEL")) {
						form.setMostrarVolume(false);
						form.setNovaSitLigacaoEsgoto("FACTIVEL");
					} else {
						form.setMostrarVolume(true);
						form.setNovaSitLigacaoEsgoto("LIGADO");
					}
				}
				form.setIdOrdemServico(idOrdemServico);
				form.setNomeOrdemServico(ordemServico.getServicoTipo().getDescricao());

				/*-------------- Início dados do Imóvel---------------*/
				Imovel imovel = ordemServico.getRegistroAtendimento().getImovel();
				sessao.setAttribute("imovel", ordemServico.getRegistroAtendimento().getImovel());

				FiltroLigacaoEsgoto filtroLigacaoEsgoto = new FiltroLigacaoEsgoto();
				filtroLigacaoEsgoto.adicionarParametro(new ParametroSimples(FiltroLigacaoEsgoto.ID, imovel.getId()));

				Collection colecaoLigacaoEsgoto = fachada.pesquisar(filtroLigacaoEsgoto, LigacaoEsgoto.class.getName());
				if (!colecaoLigacaoEsgoto.isEmpty()) {
					LigacaoEsgoto ligacaoEsgoto = (LigacaoEsgoto) Util.retonarObjetoDeColecao(colecaoLigacaoEsgoto);

					sessao.setAttribute("ligacaoEsgoto", ligacaoEsgoto);
				}

				sessao.setAttribute("ordemServico", ordemServico);

				BigDecimal valorDebito = new BigDecimal(0.00);
				if (ordemServico.getServicoTipo() != null && ordemServico.getServicoTipo().getDebitoTipo() != null) {
					form.setIdTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getId().toString());
					form.setDescricaoTipoDebito(ordemServico.getServicoTipo().getDebitoTipo().getDescricao());

					this.permitirAlteracaoValor(ordemServico.getServicoTipo(), form);

					valorDebito = this.calcularValores(request, ordemServico, form);

					if (ordemServico.getServicoNaoCobrancaMotivo() != null) {
						form.setMotivoNaoCobranca(ordemServico.getServicoNaoCobrancaMotivo().getId().toString());
					}

					if (ordemServico.getPercentualCobranca() != null) {
						form.setPercentualCobranca(ordemServico.getPercentualCobranca().toString());
					}
				}

				if (imovel != null) {

					// Matricula Imóvel
					String matriculaImovel = ordemServico.getRegistroAtendimento().getImovel().getId().toString();
					form.setMatriculaImovel(matriculaImovel);

					// Inscrição Imóvel
					String inscricaoImovel = fachada.pesquisarInscricaoImovel(ordemServico.getRegistroAtendimento().getImovel().getId());
					form.setInscricaoImovel(inscricaoImovel);

					// Situação da Ligação de Agua
					String situacaoLigacaoAgua = imovel.getLigacaoAguaSituacao().getDescricao();
					form.setSituacaoLigacaoAgua(situacaoLigacaoAgua);

					// Situação da Ligação de Esgoto
					String situacaoLigacaoEsgoto = imovel.getLigacaoEsgotoSituacao().getDescricao();
					form.setSituacaoLigacaoEsgoto(situacaoLigacaoEsgoto);

					// Filtro para carregaar o Cliente
					FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();

					filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, matriculaImovel));

					filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));

					filtroClienteImovel.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));

					filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente");
					filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

					Collection colecaoClienteImovel = fachada.pesquisar(filtroClienteImovel, ClienteImovel.class.getName());

					if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {

						ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovel.iterator().next();
						Cliente cliente = clienteImovel.getCliente();

						String documento = "";

						if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
							documento = cliente.getCpfFormatado();
						} else {
							documento = cliente.getCnpjFormatado();
						}
						// Cliente Nome/CPF-CNPJ
						form.setClienteUsuario(cliente.getNome());
						form.setCpfCnpjCliente(documento);

					} else {
						throw new ActionServletException("atencao.naocadastrado", null, "Cliente");
					}
				}
				/*-------------- Fim dados do Imóvel---------------*/

				/*-------------- Dados da Ligação ----------------------------*/

				String dataEncerramentoOdServico = Util.formatarData(ordemServico.getDataEncerramento());
				if (dataEncerramentoOdServico != null && !dataEncerramentoOdServico.equals("")) {
					form.setDataMudanca(dataEncerramentoOdServico);
				}

				if (imovel.getQuantidadeEconomias() != null) {
					form.setQtdeEconomia(imovel.getQuantidadeEconomias().toString());
				} else {
					form.setQtdeEconomia("1");
				}

				boolean temPermissaoMotivoNaoCobranca = fachada.verificarPermissaoInformarMotivoNaoCobranca(usuarioLogado);

				if (temPermissaoMotivoNaoCobranca) {
					request.setAttribute("permissaoMotivoNaoCobranca", temPermissaoMotivoNaoCobranca);
				} else {
					form.setPercentualCobranca("100");
					form.setQuantidadeParcelas("1");
					form.setValorParcelas(Util.formataBigDecimal(valorDebito, 2, true));
				}

			} else {
				form.setNomeOrdemServico("Ordem de Serviço inexistente");
				form.setIdOrdemServico("");
				request.setAttribute("OrdemServioInexistente", true);
			}
			/*-------------------- Fim Dados da Ligação ----------------------------*/
		} else {
			request.setAttribute("nomeCampo", "idOrdemServico");
			form.setIdOrdemServico("");
			form.setMatriculaImovel("");
			form.setInscricaoImovel("");
			form.setClienteUsuario("");
			form.setCpfCnpjCliente("");
			form.setSituacaoLigacaoAgua("");
			form.setSituacaoLigacaoEsgoto("");
			form.setNomeOrdemServico("");
			form.setIdTipoDebito("");
			form.setDescricaoTipoDebito("");
			form.setQuantidadeParcelas("");
			form.setValorParcelas("");
			form.setPercentualCobranca("-1");
			form.setMotivoNaoCobranca("-1");
		}
		return retorno;
	}

	/*
	 * [FS0013 - Alteração de Valor]
	 * 
	 * autor: Raphael Rossiter data: 19/04/2007
	 */
	private void permitirAlteracaoValor(ServicoTipo servicoTipo, EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm form) {

		if (servicoTipo.getIndicadorPermiteAlterarValor() == ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()) {

			form.setAlteracaoValor("OK");
		} else {
			form.setAlteracaoValor("");
		}

	}

	/*
	 * Calcular valor da prestação com juros
	 * 
	 * return: Retorna o valor total do débito
	 * 
	 * autor: Raphael Rossiter data: 04/05/2007
	 */
	private BigDecimal calcularValores(HttpServletRequest httpServletRequest, OrdemServico ordemServico, EfetuarMudancaSituacaoFaturamentoLigacaoEsgotoActionForm form) {

		String calculaValores = httpServletRequest.getParameter("calculaValores");

		BigDecimal valorDebito = new BigDecimal(0);
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		Integer qtdeParcelas = null;

		if (calculaValores != null && calculaValores.equals("S")) {

			// [UC0186] - Calcular Prestação
			BigDecimal taxaJurosFinanciamento = null;
			qtdeParcelas = new Integer(form.getQuantidadeParcelas());

			if (ordemServico.getServicoTipo().getIndicadorCobrarJuros() == ConstantesSistema.SIM.shortValue() && qtdeParcelas.intValue() > 1) {

				taxaJurosFinanciamento = sistemaParametro.getPercentualTaxaJurosFinanciamento();
			} else {
				taxaJurosFinanciamento = new BigDecimal(0);
				qtdeParcelas = 1;
			}

			BigDecimal valorPrestacao = null;
			if (taxaJurosFinanciamento != null) {

				valorDebito = new BigDecimal(form.getValorDebito().replace(",", "."));

				String percentualCobranca = form.getPercentualCobranca();

				if (percentualCobranca.equals("70")) {
					valorDebito = valorDebito.multiply(new BigDecimal(0.7));
				} else if (percentualCobranca.equals("50")) {
					valorDebito = valorDebito.multiply(new BigDecimal(0.5));
				}

				valorPrestacao = this.getFachada().calcularPrestacao(taxaJurosFinanciamento, qtdeParcelas, valorDebito, new BigDecimal("0.00"));

				valorPrestacao.setScale(2, BigDecimal.ROUND_HALF_UP);
			}

			if (valorPrestacao != null) {
				String valorPrestacaoComVirgula = Util.formataBigDecimal(valorPrestacao, 2, true);
				form.setValorParcelas(valorPrestacaoComVirgula);
			} else {
				form.setValorParcelas("0,00");
			}

		} else {

			valorDebito = Fachada.getInstancia().obterValorDebito(ordemServico.getServicoTipo().getId(), ordemServico.getRegistroAtendimento().getImovel().getId(),
					new Short(LigacaoTipo.LIGACAO_AGUA + ""));

			form.setValorDebito(Util.formataBigDecimal(valorDebito, 2, true));
		}

		return valorDebito;
	}
}
