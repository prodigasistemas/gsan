package gcom.gui.cobranca;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaParametro;
import gcom.cobranca.bean.CancelarParcelamentoHelper;
import gcom.cobranca.parcelamento.FiltroParcelamento;
import gcom.cobranca.parcelamento.FiltroParcelamentoMotivoCancelamento;
import gcom.cobranca.parcelamento.FiltroParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.FiltroParcelamentoPagamentoCartaoCredito;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoMotivoCancelamento;
import gcom.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarParcelamentoDebitoAction extends GcomAction {

	private ParcelamentoDebitoActionForm form;
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("consultarParcelamentoDebito");
		HttpSession sessao = request.getSession(false);

		form = (ParcelamentoDebitoActionForm) actionForm;

		Collection<Integer> idsContaEP = new ArrayList<Integer>();

		String codigoImovel = request.getParameter("codigoImovel");
		String idParcelamento = request.getParameter("codigoParcelamento");
		sessao.setAttribute("idParcelamento", idParcelamento);

		SistemaParametro parametros = getFachada().pesquisarParametrosDoSistema();
		
		Parcelamento parcelamento = null;
		if (codigoImovel != null && !codigoImovel.trim().equals("")) {
			Collection<ClienteImovel> clienteImovel = pesquisarImovel(codigoImovel);
			verificarImovel(request, clienteImovel);
			setarDadosClienteImovel(request, clienteImovel);

			Collection<Parcelamento> colecaoParcelamento = pesquisarParcelamento(idParcelamento);

			if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()) {
				request.setAttribute("colecaoParcelamento", colecaoParcelamento);

				Iterator<Parcelamento> iterator = colecaoParcelamento.iterator();
				while (iterator.hasNext()) {
					parcelamento = (Parcelamento) iterator.next();
					setarDadosParcelamento(parcelamento);

					short numeroPrestacaoCobradas = pesquisarNumeroPrestacoesCobradas(idParcelamento);

					boolean itensHistoricoParcelamento = getFachada().verificarItensParcelamentoNoHistorico(new Integer(codigoImovel), new Integer(idParcelamento));
					Integer anoMesParcelamento = Util.getAnoMesComoInteger(parcelamento.getParcelamento());

					if (anoMesParcelamento.compareTo(parametros.getAnoMesArrecadacao()) >= 0
							&& verificarSituacao(parcelamento, ParcelamentoSituacao.NORMAL) 
							&& numeroPrestacaoCobradas == 0 
							&& !itensHistoricoParcelamento) {

						pesquisarMotivosDesfazerParcelamento(request);
						verificarContas(sessao, idsContaEP, codigoImovel, idParcelamento);
					}
				}
			}
		}

		boolean geraBoletoBB = verificarGuiaEntrada(sessao, idParcelamento);
		verificarPagamentoCartaoDeCredito(sessao, request, idParcelamento);

		if (parcelamento != null) {
			verificarPermissaoCancelarParcelamento(request, sessao, parcelamento);
			pesquisarMotivosCancelamento(request);
			request.setAttribute("isParcelamentoCancelado", verificarSituacao(parcelamento, ParcelamentoSituacao.CANCELADO));

			if (geraBoletoBB) {
				request.setAttribute("linkBoletoBB", obterLinkBoletoBB(parcelamento));
			}
		}
		
		request.setAttribute("geracaoBoletoBB", parametros.getIndicadorGeracaoBoletoBB());

		return retorno;
	}

	private String obterLinkBoletoBB(Parcelamento parcelamento) {
		return getFachada().montarLinkBB(parcelamento.getImovel().getId(), parcelamento.getId(), parcelamento.getCliente(), parcelamento.getValorEntrada(), false);
	}

	private void setarDadosParcelamento(Parcelamento parcelamento) {
		if (parcelamento.getCliente() != null && parcelamento.getCliente().getNome() != null) {
			form.setNomeClienteResponsavel(parcelamento.getCliente().getNome());
		}

		if (verificarSituacao(parcelamento, ParcelamentoSituacao.DESFEITO)) {
			form.setDataParcelamentoDesfeito(Util.formatarDataComHora(parcelamento.getUltimaAlteracao()));
		} else {
			form.setDataParcelamentoDesfeito("");
		}
		
		form.setSituacao(parcelamento.getParcelamentoSituacao().getDescricao());
	}
	
	@SuppressWarnings("unchecked")
	private void verificarPagamentoCartaoDeCredito(HttpSession sessao, HttpServletRequest request, String codigoParcelamento) {
		Filtro filtro = new FiltroParcelamentoPagamentoCartaoCredito();
		filtro.adicionarParametro(new ParametroSimples(FiltroParcelamentoPagamentoCartaoCredito.ID_PARCELAMENTO, codigoParcelamento));
		filtro.adicionarCaminhoParaCarregamentoEntidade("usuarioConfirmacao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtro.adicionarCaminhoParaCarregamentoEntidade("arrecadador");

		Collection<ParcelamentoPagamentoCartaoCredito> colecao = getFachada().pesquisar(filtro, ParcelamentoPagamentoCartaoCredito.class.getName());
		ParcelamentoPagamentoCartaoCredito parcelamento = (ParcelamentoPagamentoCartaoCredito) Util.retonarObjetoDeColecao(colecao);

		if (parcelamento != null) {
			if (parcelamento.getNumeroCartaoCredito() != null && !parcelamento.getNumeroCartaoCredito().equals("")) {
				sessao.setAttribute("parcelamentoCartaCredito", codigoParcelamento);
				sessao.setAttribute("buttonCartaoCredito", "true");
			}
		}

		boolean habilitarBotaoDesfazer = getFachada().parcelamentoPagamentoCartaoCreditoJaConfirmado(Integer.valueOf(codigoParcelamento));
		if (!habilitarBotaoDesfazer) {
			request.setAttribute("habilitarBotaoDesfazer", "SIM");
		}
	}

	@SuppressWarnings("unchecked")
	private boolean verificarGuiaEntrada(HttpSession sessao, String idParcelamento) {
		Filtro filtro = new FiltroGuiaPagamento();
		filtro.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.PARCELAMENTO_ID, idParcelamento));
		Collection<GuiaPagamento> colecao = getFachada().pesquisar(filtro, GuiaPagamento.class.getName());
		if (colecao != null && !colecao.isEmpty()) {
			sessao.setAttribute("btImprimirGuiaPagamentoEntrada", 1);
			return true;
		} else {
			sessao.removeAttribute("btImprimirGuiaPagamentoEntrada");
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private void verificarContas(HttpSession sessao, Collection<Integer> idsContaEP, String codigoImovel, String codigoParcelamento) {
		Filtro filtro = new FiltroConta();
		filtro.adicionarParametro(new ParametroSimples(FiltroConta.IMOVEL_ID, codigoImovel));
		filtro.adicionarParametro(new ParametroSimples(FiltroConta.PARCELAMENTO_ID, codigoParcelamento));

		Collection<Conta> colecao = getFachada().pesquisar(filtro, Conta.class.getName());
		if (colecao != null && !colecao.isEmpty()) {
			Iterator<Conta> iterator = colecao.iterator();
			while (iterator.hasNext()) {
				Conta conta = (Conta) iterator.next();

				if ((conta.getDebitoCreditoSituacaoAtual().getId().intValue() == DebitoCreditoSituacao.NORMAL.intValue())
						|| (conta.getDebitoCreditoSituacaoAtual().getId().intValue() == DebitoCreditoSituacao.RETIFICADA.intValue())
						|| (conta.getDebitoCreditoSituacaoAtual().getId().intValue() == DebitoCreditoSituacao.INCLUIDA.intValue())) {

					idsContaEP.add(conta.getId());
				}
			}

			sessao.setAttribute("idsContaEP", idsContaEP);
		}
	}

	@SuppressWarnings("unchecked")
	private void pesquisarMotivosDesfazerParcelamento(HttpServletRequest request) {
		Filtro filtro = new FiltroParcelamentoMotivoDesfazer();
		Collection<ParcelamentoMotivoDesfazer> collectionParcelamentoMotivoDesfazer = getFachada().pesquisar(filtro, ParcelamentoMotivoDesfazer.class.getName());
		request.setAttribute("collectionParcelamentoMotivoDesfazer", collectionParcelamentoMotivoDesfazer);
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarMotivosCancelamento(HttpServletRequest request) {
		Filtro filtro = new FiltroParcelamentoMotivoCancelamento();
		filtro.adicionarParametro(new ParametroSimples(FiltroParcelamentoMotivoCancelamento.INDICADOR_USO, ConstantesSistema.SIM));
		Collection<ParcelamentoMotivoCancelamento> motivos = getFachada().pesquisar(filtro, ParcelamentoMotivoCancelamento.class.getName());
		request.setAttribute("colecaoMotivoCancelamento", motivos);
	}

	private void setarDadosClienteImovel(HttpServletRequest request, Collection<ClienteImovel> clienteImovel) {
		if (clienteImovel != null && !clienteImovel.isEmpty()) {
			ClienteImovel dados = (ClienteImovel) ((List<ClienteImovel>) clienteImovel).get(0);

			Imovel imovel = dados.getImovel();
			Cliente cliente = dados.getCliente();

			form.setCodigoImovel(imovel.getId().toString());
			form.setInscricao(imovel.getInscricaoFormatada());
			form.setSituacaoAgua(imovel.getLigacaoAguaSituacao() != null ? imovel.getLigacaoAguaSituacao().getDescricao() : null);
			form.setSituacaoEsgoto(imovel.getLigacaoEsgotoSituacao() != null ? imovel.getLigacaoEsgotoSituacao().getDescricao() : null);
			form.setNomeCliente(cliente.getNome());
			form.setImovelPerfil(imovel.getImovelPerfil() != null ? imovel.getImovelPerfil().getDescricao() : null);

			if (cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() == 1) {
				form.setCpfCnpj(cliente.getCpfFormatado());
			} else {
				form.setCpfCnpj(cliente.getCnpjFormatado());
			}

			form.setParcelamento(imovel.getNumeroParcelamento() != null ? imovel.getNumeroParcelamento().toString() : null);
			form.setReparcelamento(imovel.getNumeroReparcelamento() != null ? imovel.getNumeroReparcelamento().toString() : null);
			form.setReparcelamentoConsecutivo(imovel.getNumeroReparcelamentoConsecutivos() != null ? imovel.getNumeroReparcelamentoConsecutivos().toString() : null);

			request.setAttribute("imovelPesquisado", imovel);
			request.setAttribute("enderecoFormatado", pesquisarEnderecoFormatado(form.getCodigoImovel()));
		}
	}

	@SuppressWarnings("unchecked")
	private short pesquisarNumeroPrestacoesCobradas(String codigoParcelamento) {
		Filtro filtro = new FiltroDebitoACobrar();
		filtro.adicionarParametro(new ParametroSimples(FiltroDebitoACobrar.PARCELAMENTO_ID, codigoParcelamento));

		Collection<DebitoACobrar> colecaoDebitoACobrar = getFachada().pesquisar(filtro, DebitoACobrar.class.getName());

		short numeroPrestacaoCobradas = 0;
		if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
			numeroPrestacaoCobradas = colecaoDebitoACobrar.iterator().next().getNumeroPrestacaoCobradas();
		}

		return numeroPrestacaoCobradas;
	}

	@SuppressWarnings("unchecked")
	private Collection<Parcelamento> pesquisarParcelamento(String codigoParcelamento) {
		Filtro filtro = new FiltroParcelamento();
		filtro.adicionarParametro(new ParametroSimples(FiltroParcelamento.ID, codigoParcelamento));
		filtro.adicionarCaminhoParaCarregamentoEntidade("parcelamentoSituacao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtro.adicionarCaminhoParaCarregamentoEntidade("usuarioDesfez");
		filtro.adicionarCaminhoParaCarregamentoEntidade("funcionario");
		filtro.adicionarCaminhoParaCarregamentoEntidade("cobrancaForma");
		filtro.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtro.adicionarCaminhoParaCarregamentoEntidade("usuarioCancelamento");
		filtro.adicionarCaminhoParaCarregamentoEntidade("motivoCancelamento");

		return getFachada().pesquisar(filtro, Parcelamento.class.getName());
	}

	private String pesquisarEnderecoFormatado(String codigoImovel) {
		String enderecoFormatado = "";
		try {
			enderecoFormatado = getFachada().pesquisarEnderecoFormatado(new Integer(codigoImovel));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		return enderecoFormatado;
	}

	private void verificarImovel(HttpServletRequest request, Collection<ClienteImovel> imovelPesquisado) {
		if (imovelPesquisado != null && imovelPesquisado.isEmpty()) {
			request.setAttribute("enderecoFormatado", "Matrícula Inexistente".toUpperCase());
			form.setInscricao("");
			form.setNomeCliente("");
			form.setCpfCnpj("");
			form.setSituacaoAgua("");
			form.setSituacaoEsgoto("");
			form.setImovelPerfil("");
		}
	}

	@SuppressWarnings("unchecked")
	private Collection<ClienteImovel> pesquisarImovel(String codigoImovel) {
		Filtro filtro = new FiltroClienteImovel();
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

		return getFachada().pesquisar(filtro, ClienteImovel.class.getName());
	}

	private void verificarPermissaoCancelarParcelamento(HttpServletRequest request, HttpSession sessao, Parcelamento parcelamento) {
		boolean possuiPermissaoCancelarParcelamento = getFachada().verificarPermissaoEspecial(PermissaoEspecial.CANCELAR_PARCELAMENTO, (Usuario) sessao.getAttribute("usuarioLogado"));

		CancelarParcelamentoHelper cancelarParcelamentoHelper = getFachada().pesquisarParcelamentoParaCancelar(parcelamento.getId());

		boolean houvePagamentoParcelamento = cancelarParcelamentoHelper != null;
		boolean isParcelamentoRealizadoNoGsan = isParcelamentoRealizadoNoGsan(parcelamento.getParcelamento());

		boolean isParcelamentoConcluido = false;
		if (cancelarParcelamentoHelper != null) {
			isParcelamentoConcluido = cancelarParcelamentoHelper.getNumeroPrestacoes() == cancelarParcelamentoHelper.getNumeroPrestacoesCobradas();
		}

		boolean permiteCancelar = possuiPermissaoCancelarParcelamento 
								&& isParcelamentoRealizadoNoGsan 
								&& verificarSituacao(parcelamento, ParcelamentoSituacao.NORMAL)
								&& !isParcelamentoConcluido 
								&& houvePagamentoParcelamento;

		request.setAttribute("permiteCancelar", permiteCancelar);
	}

	public boolean isParcelamentoRealizadoNoGsan(Date dataParcelamento) {
		String data = getFachada().getCobrancaParametro(CobrancaParametro.NOME_PARAMETRO_COBRANCA.DATA_INICIO_PARCELAMENTO_GSAN.toString());
		Date dataInicioParcelamentoGsan = Util.converteStringParaDate(data);

		return dataParcelamento.compareTo(dataInicioParcelamentoGsan) >= 0;
	}

	private boolean verificarSituacao(Parcelamento parcelamento, Integer situacao) {
		return parcelamento.getParcelamentoSituacao().getId().intValue() == situacao.intValue();
	}
}
