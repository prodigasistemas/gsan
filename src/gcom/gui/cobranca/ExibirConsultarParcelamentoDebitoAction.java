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
import gcom.cobranca.parcelamento.FiltroParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.FiltroParcelamentoPagamentoCartaoCredito;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoMotivoDesfazer;
import gcom.cobranca.parcelamento.ParcelamentoPagamentoCartaoCredito;
import gcom.cobranca.parcelamento.ParcelamentoSituacao;
import gcom.cobranca.parcelamento.ParcelamentoTipo;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.FiltroConta;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoACobrar;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
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

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("consultarParcelamentoDebito");
		HttpSession sessao = request.getSession(false);

		ParcelamentoDebitoActionForm form = (ParcelamentoDebitoActionForm) actionForm;

		Collection<Integer> idsContaEP = new ArrayList<Integer>();

		String codigoImovel = request.getParameter("codigoImovel");
		String codigoParcelamento = request.getParameter("codigoParcelamento");
		sessao.setAttribute("idParcelamento", codigoParcelamento);
		String acao = request.getParameter("acao");
		boolean exibirBotaoCancelamentoParcelamento = false;

		if (acao != null && acao.equals("cancelar")) {
			cancelarParcelamento(sessao, Integer.parseInt(codigoParcelamento));
		}

		if (codigoImovel != null && !codigoImovel.trim().equals("")) {
			Collection<ClienteImovel> clienteImovel = pesquisarImovel(codigoImovel);
			verificarImovel(request, form, clienteImovel);
			setarDadosClienteImovel(request, form, codigoImovel, clienteImovel);
			
			SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();

			Collection<Parcelamento> colecaoParcelamento = pesquisarParcelamento(codigoParcelamento);
			
			if (colecaoParcelamento != null && !colecaoParcelamento.isEmpty()) {
				request.setAttribute("colecaoParcelamento", colecaoParcelamento);

				Iterator<Parcelamento> iterator = colecaoParcelamento.iterator();
				while (iterator.hasNext()) {
					Parcelamento parcelamento = (Parcelamento) iterator.next();
					setarDadosParcelamento(form, parcelamento);

					short numeroPrestacaoCobradas = pesquisarNumeroPrestacoesCobradas(codigoParcelamento);

					boolean itensHistoricoParcelamento = getFachada().verificarItensParcelamentoNoHistorico(new Integer(codigoImovel), new Integer(codigoParcelamento));
					Integer anoMesEfetivacaoParcelamento = Util.getAnoMesComoInteger(parcelamento.getParcelamento());

					if (anoMesEfetivacaoParcelamento.compareTo(sistemaParametro.getAnoMesArrecadacao()) >= 0
							&& parcelamento.getParcelamentoSituacao().getId().intValue() == ParcelamentoSituacao.NORMAL.intValue() 
							&& numeroPrestacaoCobradas == 0 
							&& !itensHistoricoParcelamento) {

						pesquisarMotivosDesfazerParcelamento(request);
						verificarContas(sessao, idsContaEP, codigoImovel, codigoParcelamento);
					}
					
					exibirBotaoCancelamentoParcelamento = isParcelamentoRealizadoNoGsan(parcelamento.getParcelamento()) && isParcelamentoNormal(parcelamento);
				}
			}
		}

		verificarGuiaEntrada(sessao, codigoParcelamento);
		verificarPagamentoCartaoDeCredito(sessao, request, codigoParcelamento);
		verificarPermissaoCancelarParcelamento(request, sessao, exibirBotaoCancelamentoParcelamento);

		return retorno;
	}

	private void setarDadosParcelamento(ParcelamentoDebitoActionForm form, Parcelamento parcelamento) {
		if (parcelamento.getCliente() != null && parcelamento.getCliente().getNome() != null) {
			form.setNomeClienteResponsavel(parcelamento.getCliente().getNome());
		}

		if (parcelamento.getParcelamentoSituacao().getId().intValue() == ParcelamentoSituacao.DESFEITO.intValue()) {
			form.setDataParcelamentoDesfeito(Util.formatarDataComHora(parcelamento.getUltimaAlteracao()));
		} else {
			form.setDataParcelamentoDesfeito("");
		}
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
	private void verificarGuiaEntrada(HttpSession sessao, String codigoParcelamento) {
		Filtro filtro = new FiltroGuiaPagamento();
		filtro.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.PARCELAMENTO_ID, new Integer(codigoParcelamento)));

		Collection<GuiaPagamento> colecao = getFachada().pesquisar(filtro, GuiaPagamento.class.getName());

		if (colecao != null && !colecao.isEmpty()) {
			sessao.setAttribute("btImprimirGuiaPagamentoEntrada", 1);
		} else {
			sessao.removeAttribute("btImprimirGuiaPagamentoEntrada");
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

	private void setarDadosClienteImovel(HttpServletRequest request, ParcelamentoDebitoActionForm form, String codigoImovel, Collection<ClienteImovel> clienteImovel) {
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
			request.setAttribute("enderecoFormatado", pesquisarEnderecoFormatado(codigoImovel));
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

	private void verificarImovel(HttpServletRequest request, ParcelamentoDebitoActionForm form, Collection<ClienteImovel> imovelPesquisado) {
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

	private void verificarPermissaoCancelarParcelamento(HttpServletRequest request, HttpSession sessao, boolean isParcalamentoRealizadoNoGsan) {
		boolean possuiPermissaoCancelarParcelamento = this.getFachada().verificarPermissaoEspecial(PermissaoEspecial.CANCELAR_PARCELAMENTO, (Usuario) sessao.getAttribute("usuarioLogado"));
		boolean exibirBotao = possuiPermissaoCancelarParcelamento && isParcalamentoRealizadoNoGsan;
		request.setAttribute("possuiPermissaoCancelarParcelamento", exibirBotao);
	}
	
	public boolean isParcelamentoRealizadoNoGsan(Date dataParcelamento) {
		String data = getFachada().getCobrancaParametro(CobrancaParametro.NOME_PARAMETRO_COBRANCA.DATA_INICIO_PARCELAMENTO_GSAN.toString());
		Date dataInicioParcelamentoGsan = Util.converteStringParaDate(data);
		
		return dataParcelamento.compareTo(dataInicioParcelamentoGsan) >= 0;
	}
	
	private boolean isParcelamentoNormal(Parcelamento parcelamento) {
		return parcelamento.getParcelamentoSituacao().getId().intValue() == ParcelamentoSituacao.NORMAL.intValue();
	}

	private void cancelarParcelamento(HttpSession sessao, Integer codigoParcelamento) {
		CancelarParcelamentoHelper helper = getFachada().pesquisarParcelamentoParaCancelar(codigoParcelamento);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		getFachada().cancelarParcelamento(helper, usuarioLogado);
	}
}
