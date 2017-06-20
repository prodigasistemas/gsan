package gcom.gui.cobranca;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.parcelamento.FiltroParcelamento;
import gcom.cobranca.parcelamento.FiltroParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.FiltroParcelamentoPagamentoCartaoCredito;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.cobranca.repositorios.dto.CancelarParcelamentoDTO;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;
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

public class ExibirConsultarParcelamentoDebitoAction extends GcomAction {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse httpServletResponse) {
		ActionForward retorno = actionMapping.findForward("consultarParcelamentoDebito");
		HttpSession sessao = request.getSession(false);

		ParcelamentoDebitoActionForm form = (ParcelamentoDebitoActionForm) actionForm;

		Collection<Integer> idsContaEP = new ArrayList<Integer>();

		String codigoImovel = request.getParameter("codigoImovel");
		String codigoParcelamento = request.getParameter("codigoParcelamento");
		sessao.setAttribute("idParcelamento", codigoParcelamento);
		String acao = request.getParameter("acao");
		CancelarParcelamentoDTO cancelarParcelamentoDTO = null;

		if (acao != null && acao.equals("cancelar")) {
			cancelarParcelamentoDTO = getFachada().pesquisarParcelamentoParaCancelar(Integer.parseInt(codigoParcelamento));
			Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			getFachada().cancelarParcelamento(cancelarParcelamentoDTO, usuarioLogado);

			return retorno;
		}

		if (codigoImovel != null && !codigoImovel.trim().equals("")) {
			FiltroClienteImovel filtro = new FiltroClienteImovel();
			filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, codigoImovel));
			filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO_ID, ClienteRelacaoTipo.USUARIO));
			filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.setorComercial.municipio.unidadeFederacao");
			filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroBairro.bairro");
			filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.logradouroCep.cep");
			filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.quadra");
			filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.imovelPerfil");
			filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoAguaSituacao");
			filtro.adicionarCaminhoParaCarregamentoEntidade("imovel.ligacaoEsgotoSituacao");
			filtro.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtro.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
			filtro.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

			Collection<ClienteImovel> imovelPesquisado = getFachada().pesquisar(filtro, ClienteImovel.class.getName());

			if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
				request.setAttribute("enderecoFormatado", "Matrícula Inexistente".toUpperCase());
				form.setInscricao("");
				form.setNomeCliente("");
				form.setCpfCnpj("");
				form.setSituacaoAgua("");
				form.setSituacaoEsgoto("");
				form.setImovelPerfil("");
			}
			if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {

				ClienteImovel dadosImovel = (ClienteImovel) ((List) imovelPesquisado).get(0);

				if (dadosImovel.getImovel().getId() != null) {
					form.setCodigoImovel("" + dadosImovel.getImovel().getId());
				}
				if (dadosImovel.getImovel().getInscricaoFormatada() != null) {
					form.setInscricao("" + dadosImovel.getImovel().getInscricaoFormatada());
				}
				if (dadosImovel.getImovel().getLigacaoAguaSituacao() != null) {
					form.setSituacaoAgua("" + dadosImovel.getImovel().getLigacaoAguaSituacao().getDescricao());
				}
				if (dadosImovel.getImovel().getLigacaoEsgotoSituacao() != null) {
					form.setSituacaoEsgoto("" + dadosImovel.getImovel().getLigacaoEsgotoSituacao().getDescricao());
				}
				if (dadosImovel.getCliente().getNome() != null) {
					form.setNomeCliente("" + dadosImovel.getCliente().getNome());
				}
				if (dadosImovel.getImovel().getImovelPerfil() != null) {
					form.setImovelPerfil("" + dadosImovel.getImovel().getImovelPerfil().getDescricao());
				}
				if (dadosImovel.getCliente().getClienteTipo().getIndicadorPessoaFisicaJuridica() == 1) {
					if (dadosImovel.getCliente().getCpfFormatado() != null) {
						form.setCpfCnpj("" + dadosImovel.getCliente().getCpfFormatado());
					}
				} else {
					if (dadosImovel.getCliente().getCnpjFormatado() != null) {
						form.setCpfCnpj("" + dadosImovel.getCliente().getCnpjFormatado());
					}
				}
				if (dadosImovel.getImovel().getNumeroParcelamento() != null) {
					form.setParcelamento("" + dadosImovel.getImovel().getNumeroParcelamento());
				}
				if (dadosImovel.getImovel().getNumeroReparcelamento() != null) {
					form.setReparcelamento("" + dadosImovel.getImovel().getNumeroReparcelamento());
				}
				if (dadosImovel.getImovel().getNumeroReparcelamentoConsecutivos() != null) {
					form.setReparcelamentoConsecutivo("" + dadosImovel.getImovel().getNumeroReparcelamentoConsecutivos());
				}

				request.setAttribute("imovelPesquisado", imovelPesquisado);
				String enderecoFormatado = "";
				try {
					enderecoFormatado = getFachada().pesquisarEnderecoFormatado(new Integer(codigoImovel));
				} catch (NumberFormatException e) {

					e.printStackTrace();
				} catch (ControladorException e) {

					e.printStackTrace();
				}

				request.setAttribute("enderecoFormatado", enderecoFormatado);
			}
			
			FiltroParcelamento filtroParcelamento = new FiltroParcelamento();
			filtroParcelamento.adicionarParametro(new ParametroSimples(FiltroParcelamento.ID, codigoParcelamento));
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("parcelamentoSituacao");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("usuario");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("usuarioDesfez");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("funcionario");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("cobrancaForma");
			filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("cliente");

			Collection<Parcelamento> colecaoParcelamento = getFachada().pesquisar(filtroParcelamento, Parcelamento.class.getName());

			SistemaParametro sistemaParametro = null;

			if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()) {
				request.setAttribute("colecaoParcelamento", colecaoParcelamento);

				Iterator iteratorParcelamento = colecaoParcelamento.iterator();
				while (iteratorParcelamento.hasNext()) {

					Parcelamento parcelamento = (Parcelamento) iteratorParcelamento.next();

					if (parcelamento.getCliente() != null && parcelamento.getCliente().getNome() != null) {
						form.setNomeClienteResponsavel(parcelamento.getCliente().getNome());
					}

					if (parcelamento.getParcelamentoSituacao().getId().intValue() == ParcelamentoSituacao.DESFEITO.intValue()) {
						form.setDataParcelamentoDesfeito(Util.formatarDataComHora(parcelamento.getUltimaAlteracao()));
					} else {
						form.setDataParcelamentoDesfeito("");
					}

					sistemaParametro = getFachada().pesquisarParametrosDoSistema();

					FiltroDebitoACobrar filtroDebitoACobrar = new FiltroDebitoACobrar();
					filtroDebitoACobrar.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.PARCELAMENTO_ID, codigoParcelamento));

					Collection<DebitoACobrar> colecaoDebitoACobrar = getFachada().pesquisar(filtroDebitoACobrar, DebitoACobrar.class.getName());
					short numeroPrestacaoCobradas = 0;

					if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
						numeroPrestacaoCobradas = colecaoDebitoACobrar.iterator().next().getNumeroPrestacaoCobradas();
					}

					boolean itensHistoricoParcelamento = getFachada().verificarItensParcelamentoNoHistorico(new Integer(codigoImovel), new Integer(codigoParcelamento));
					Integer anoMesEfetivacaoParcelamento = Util.getAnoMesComoInteger(parcelamento.getParcelamento());

					if ((anoMesEfetivacaoParcelamento.compareTo(new Integer(sistemaParametro.getAnoMesArrecadacao())) >= 0)
							&& parcelamento.getParcelamentoSituacao().getId().intValue() == ParcelamentoSituacao.NORMAL.intValue() && numeroPrestacaoCobradas == 0 && !itensHistoricoParcelamento) {

						FiltroParcelamentoMotivoDesfazer filtroParcelamentoMotivoDesfazer = new FiltroParcelamentoMotivoDesfazer();
						Collection<ParcelamentoMotivoDesfazer> collectionParcelamentoMotivoDesfazer = getFachada().pesquisar(filtroParcelamentoMotivoDesfazer, ParcelamentoMotivoDesfazer.class.getName());

						request.setAttribute("collectionParcelamentoMotivoDesfazer", collectionParcelamentoMotivoDesfazer);

						// Verifica se a entrada do parcelamento tenha sido através de contas marcadas como EP
						FiltroConta filtroConta = new FiltroConta();
						filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.IMOVEL_ID, codigoImovel));
						filtroConta.adicionarParametro(new ParametroSimples(FiltroConta.PARCELAMENTO_ID, codigoParcelamento));

						Collection colecaoConta2 = getFachada().pesquisar(filtroConta, Conta.class.getName());

						if (colecaoConta2 != null && !colecaoConta2.isEmpty()) {
							Iterator iteratorConta = colecaoConta2.iterator();

							while (iteratorConta.hasNext()) {
								Conta conta = (Conta) iteratorConta.next();

								if ((conta.getDebitoCreditoSituacaoAtual().getId().intValue() == DebitoCreditoSituacao.NORMAL.intValue())
										|| (conta.getDebitoCreditoSituacaoAtual().getId().intValue() == DebitoCreditoSituacao.RETIFICADA.intValue())
										|| (conta.getDebitoCreditoSituacaoAtual().getId().intValue() == DebitoCreditoSituacao.INCLUIDA.intValue())) {

									idsContaEP.add(conta.getId());

								}
							}
						}
					}
				}
			}
		}

		FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
		filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.PARCELAMENTO_ID, new Integer(codigoParcelamento)));

		Collection collectionGuiaPagamento = getFachada().pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());

		if (collectionGuiaPagamento != null && !collectionGuiaPagamento.isEmpty()) {
			sessao.setAttribute("btImprimirGuiaPagamentoEntrada", 1);
		} else {
			sessao.removeAttribute("btImprimirGuiaPagamentoEntrada");
		}
		sessao.setAttribute("idsContaEP", idsContaEP);

		FiltroParcelamentoPagamentoCartaoCredito filtroParcelamento = new FiltroParcelamentoPagamentoCartaoCredito();
		filtroParcelamento.adicionarParametro(new ParametroSimples(FiltroParcelamentoPagamentoCartaoCredito.ID_PARCELAMENTO, codigoParcelamento));
		filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("usuarioConfirmacao");
		filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroParcelamento.adicionarCaminhoParaCarregamentoEntidade("arrecadador");

		Collection<ParcelamentoPagamentoCartaoCredito> colecaoParcelamento = getFachada().pesquisar(filtroParcelamento, ParcelamentoPagamentoCartaoCredito.class.getName());

		ParcelamentoPagamentoCartaoCredito parcelamento = (ParcelamentoPagamentoCartaoCredito) Util.retonarObjetoDeColecao(colecaoParcelamento);

		if (parcelamento != null) {
			if (parcelamento.getNumeroCartaoCredito() != null && !parcelamento.getNumeroCartaoCredito().equals("")) {
				sessao.setAttribute("parcelamentoCartaCredito", codigoParcelamento);
				sessao.setAttribute("buttonCartaoCredito", "true");
			}
		}

		/*
		 * Caso o parcelamento tenha dados de cartão de crédito não confirmados
		 * pela operadora (PACC_ICONFIRMADOOPERADORA da tabela
		 * PARCELAMENTO_PAGAMENTO_CARTAO_CREDITO com PARC_ID = PARC_ID do
		 * parcelamento selecionado com valor igual 2 (Não))
		 */
		if (codigoParcelamento != null && !codigoParcelamento.equals("")) {

			boolean habilitarBotaoDesfazer = getFachada().parcelamentoPagamentoCartaoCreditoJaConfirmado(Integer.valueOf(codigoParcelamento));

			if (!habilitarBotaoDesfazer) {
				request.setAttribute("habilitarBotaoDesfazer", "SIM");
			}
		}

		return retorno;
	}
}
