package gcom.gui.cobranca;

import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.DebitoCreditoParcelamentoHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.IndicadoresParcelamentoHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirDebitoCreditoDadosSelecaoExtratoAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		ActionForward retorno = mapping.findForward("debitoCreditoDadosSelecaoExtrato");

		DebitoCreditoDadosSelecaoExtratoActionForm form = (DebitoCreditoDadosSelecaoExtratoActionForm) actionForm;

		HttpSession sessao = request.getSession(false);

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Fachada fachada = Fachada.getInstancia();

		boolean temPermissaoIncluirAcrescimoImpontualidadeNoExtratoDeDebitosComDesconto = fachada.verificarPermissaoIncluirAcrescimoImpontualidadeNoExtratoDeDebitosComDesconto(usuarioLogado);
		request.setAttribute("temPermissaoIncluirAcrescExtratoComDesconto", temPermissaoIncluirAcrescimoImpontualidadeNoExtratoDeDebitosComDesconto);

		boolean temPermissaoRetirarTaxaCobrancaDoExtratoDeDebitos = false;
		Short indicadorCobrarTaxaExtrato = fachada.pesquisarParametrosDoSistema().getIndicadorCobrarTaxaExtrato();
		if (indicadorCobrarTaxaExtrato.equals(ConstantesSistema.SIM)) {
			temPermissaoRetirarTaxaCobrancaDoExtratoDeDebitos = fachada.verificarPermissaoRetirarTaxaCobrancaDoExtratoDeDebitos(usuarioLogado);
			request.setAttribute("temPermissaoRetirarTaxaCobrancaDoExtratoDeDebitos", temPermissaoRetirarTaxaCobrancaDoExtratoDeDebitos);
		}

		if (request.getParameter("menu") == null) {
			request.setAttribute("habilitaIncluirDebito", 1);
		} else {
			if (temPermissaoRetirarTaxaCobrancaDoExtratoDeDebitos) {
				form.setIndicadorTaxaCobranca(ConstantesSistema.SIM.toString());
			} else {
				form.setIndicadorTaxaCobranca(ConstantesSistema.NAO.toString());
			}

			form.setIdsConta(null);
			form.setIdsDebito(null);
			form.setIdsCredito(null);
			form.setIdsGuia(null);
			form.setIdsParcelamento(null);
		}
		
		String idImovel = form.getIdImovel();
		if (request.getParameter("reloadPage") == null) {

			sessao.removeAttribute("colecaoConta");
			sessao.removeAttribute("colecaoContaNormais");
			sessao.removeAttribute("colecaoContaPreteritos");
			sessao.removeAttribute("colecaoDebitoACobrar");
			sessao.removeAttribute("colecaoCreditoARealizar");
			sessao.removeAttribute("colecaoGuiaPagamento");
			sessao.removeAttribute("colecaoDebitoCreditoParcelamento");
			sessao.removeAttribute("idImovel");
			sessao.removeAttribute("colecaoPagamentosImovelContaInconformes");
			sessao.removeAttribute("colecaoPagamentosInconformesAtuais");
			sessao.removeAttribute("colecaoPagamentosInconformesPreteritos");
			
			form.setTotalDebitoSelecionado("0");
			form.setTotalDebitoAtualizadoSelecionado("0");
			form.setIndicadorIncluirAcrescimosImpontualidade(CobrancaDocumento.INCLUIR_ACRESCIMOS);
			request.removeAttribute("habilitaIncluirDebito");

			if (idImovel != null && !idImovel.equals("")) {

				String inscricaoImovel = fachada.pesquisarInscricaoImovel(new Integer(idImovel));

				if (inscricaoImovel == null) {
					form.setInscricaoImovel("IMOVEL INEXISTENTE");
					request.setAttribute("corImovelDestino", "exception");
					form.setDescricaoLigacaoAguaSituacaoImovel("");
					form.setDescricaoLigacaoEsgotoSituacaoImovel("");
					form.setNomeClienteUsuarioImovel("");
					form.setCpfCnpj("");
					
				} else {
					// INSCRICAO IMOVEL
					form.setInscricaoImovel(inscricaoImovel);
					sessao.setAttribute("idImovel", idImovel);

					// CLIENTE USUARIO DO IMOVEL
					Cliente cliente = fachada.pesquisarClienteUsuarioImovel(new Integer(idImovel));
					form.setNomeClienteUsuarioImovel(cliente.getNome());
					// sessao.setAttribute("nomeCliente",cliente.getNome());

					// CPF ou CNPJ do Cliente
					if (cliente.getCpf() != null) {
						form.setCpfCnpj(cliente.getCpfFormatado());
					} else if (cliente.getCnpj() != null) {
						form.setCpfCnpj(cliente.getCnpjFormatado());
					}

					// LIGACAO AGUA SITUACAO
					LigacaoAguaSituacao ligacaoAguaSituacao = fachada.pesquisarLigacaoAguaSituacao(new Integer(idImovel));
					form.setDescricaoLigacaoAguaSituacaoImovel(ligacaoAguaSituacao.getDescricao());
					form.setIdLigacaoAguaSituacaoImovel(ligacaoAguaSituacao.getId().toString());

					// LIGACAO ESGOTO SITUACAO
					LigacaoEsgotoSituacao ligacaoEsgotoSituacao = fachada.pesquisarLigacaoEsgotoSituacao(new Integer(idImovel));
					form.setDescricaoLigacaoEsgotoSituacaoImovel(ligacaoEsgotoSituacao.getDescricao());
					form.setIdLigacaoEsgotoSituacaoImovel(ligacaoEsgotoSituacao.getId().toString());
					
					
					// CONTAS INCONFORMES
					FiltroPagamentoSituacao filtroPagamentoSituacao = new FiltroPagamentoSituacao();
					filtroPagamentoSituacao.adicionarParametro(new ParametroSimples(FiltroPagamentoSituacao.DESCRICAO_ABREVIADA, "NCONF"));
					
					
					PagamentoSituacao pagamentoSituacao = (PagamentoSituacao) Util.retonarObjetoDeColecao(fachada.pesquisar(filtroPagamentoSituacao, PagamentoSituacao.class.getName()));
					
					Object[] colecaoContasInconformes = fachada.pesquisarPagamentoInconformeImovel(idImovel.trim());
					Collection<Pagamento> colecaoPagamentosInconformesAtuais = (Collection<Pagamento>) colecaoContasInconformes[0];
					Collection<Pagamento> colecaoPagamentosInconformesPreteritas = (Collection<Pagamento>) colecaoContasInconformes[1];
					Collection<Pagamento> colecaoPagamentosImovelContaInconformes = new ArrayList<Pagamento>();
					colecaoPagamentosImovelContaInconformes.addAll(colecaoPagamentosInconformesAtuais);
					colecaoPagamentosImovelContaInconformes.addAll(colecaoPagamentosInconformesPreteritas);
					
					sessao.setAttribute("colecaoPagamentosImovelContaInconformes", colecaoPagamentosImovelContaInconformes);
					sessao.setAttribute("colecaoPagamentosInconformesAtuais", colecaoPagamentosInconformesAtuais);
					sessao.setAttribute("colecaoPagamentosInconformesPreteritos", colecaoPagamentosInconformesPreteritas);
					

					ObterDebitoImovelOuClienteHelper helper = fachada.apresentarDebitoCreditoImovelExtratoDebito(new Integer(idImovel), false);

					Collection<ContaValoresHelper> colecaoConta = new ArrayList<ContaValoresHelper>();
					colecaoConta.addAll(helper.getColecaoContasValoresImovel());
					colecaoConta.addAll(helper.getColecaoContasValoresPreteritos());

					sessao.setAttribute("colecaoConta", colecaoConta);
					sessao.setAttribute("colecaoContaNormais", helper.getColecaoContasValoresImovel());
					sessao.setAttribute("colecaoContaPreteritos", helper.getColecaoContasValoresPreteritos());
					sessao.setAttribute("colecaoDebitoACobrar", helper.getColecaoDebitoACobrar());
					sessao.setAttribute("colecaoGuiaPagamento", helper.getColecaoGuiasPagamentoValoresSemFichaCompensacao());
					sessao.setAttribute("colecaoDebitoCreditoParcelamento", helper.getColecaoDebitoCreditoParcelamentoHelper());

					request.setAttribute("habilitaIncluirDebito", 1);
				}

			}

		} else {

			String[] idsContas = request.getParameterValues("conta");
			form.setIdsConta(Arrays.toString(idsContas).replace("[", "").replace("]", ""));

			String[] idsDebitos = request.getParameterValues("debito");
			if (sessao.getAttribute("idDebitoACobrarInserido") != null) {
				if (idsDebitos == null) {
					form.setIdsDebito((String) sessao.getAttribute("idDebitoACobrarInserido"));
				} else {
					String debitos = Arrays.toString(idsDebitos).replace("[", "").replace("]", "");
					debitos = debitos + "," + (String) sessao.getAttribute("idDebitoACobrarInserido");
					form.setIdsDebito(debitos);
				}
			} else {
				form.setIdsDebito(Arrays.toString(idsDebitos).replace("[", "").replace("]", ""));
			}

			String[] idsCreditos = request.getParameterValues("credito");
			form.setIdsCredito(Arrays.toString(idsCreditos).replace("[", "").replace("]", ""));

			String[] idsguias = request.getParameterValues("guiaPagamento");
			form.setIdsGuia(Arrays.toString(idsguias).replace("[", "").replace("]", ""));

			String[] idsParcelamentos = request.getParameterValues("parcelamento");
			form.setIdsParcelamento(Arrays.toString(idsParcelamentos).replace("[", "").replace("]", ""));

		}

		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		if (sistemaParametro.getResolucaoDiretoria() != null) {

			if (idImovel != null && !idImovel.equals("")) {
				if (form.getIdLigacaoAguaSituacaoImovel().equals(LigacaoAguaSituacao.SUPRIMIDO.toString())
						|| form.getIdLigacaoAguaSituacaoImovel().equals(LigacaoAguaSituacao.SUPR_PARC.toString())
						|| form.getIdLigacaoAguaSituacaoImovel().equals(LigacaoAguaSituacao.SUPR_PARC_PEDIDO.toString())) {

					boolean temPermissaoResolucaoDiretoria = fachada.verificarPermissaoEspecial(PermissaoEspecial.USAR_PLANO_PAI_PARA_ORGAO_PUBLICO,
							usuarioLogado);

					if (!temPermissaoResolucaoDiretoria) {
						form.setIndicadorRestabelecimento("2");
					}

					request.setAttribute("temPermissaoResolucaoDiretoria", temPermissaoResolucaoDiretoria);
				} else {
					form.setIndicadorRestabelecimento("2");
				}

				request.setAttribute("botaoCalcularDesconto", true);
				form.setIndicadorIncluirAcrescimosImpontualidade(CobrancaDocumento.INCLUIR_ACRESCIMOS);
			}

			if (form.getIndicadorRestabelecimento() == null) {
				form.setIndicadorRestabelecimento("2");
			}
		}

		if (request.getParameter("calcularDesconto") != null) {
			calcularDescontos(form, request, sessao, fachada, sistemaParametro, usuarioLogado);
		}

		request.setAttribute("nomeCampo", "idImovel");

		return retorno;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object[] obterContasSelecionadas(String idsContas, HttpSession sessao) {

		Object[] retorno = null;
		Collection<ContaValoresHelper> colecaoContas = null;
		BigDecimal valorTotalConta = BigDecimal.ZERO;
		BigDecimal valorTotalAcrescimoImpontualidade = BigDecimal.ZERO;
		BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
		BigDecimal valorJurosMora = new BigDecimal("0.00");
		BigDecimal valorMulta = new BigDecimal("0.00");

		if (idsContas != null && !idsContas.equals("")) {
			retorno = new Object[6];
			colecaoContas = new ArrayList();

			Collection colecaoContasSessao = (Collection) sessao.getAttribute("colecaoConta");
			Iterator itColecaoContasSessao = colecaoContasSessao.iterator();
			ContaValoresHelper contaValoresHelper = null;

			String[] idsContasArray = idsContas.split(",");

			while (itColecaoContasSessao.hasNext()) {

				contaValoresHelper = (ContaValoresHelper) itColecaoContasSessao.next();

				for (int x = 0; x < idsContasArray.length; x++) {

					if (contaValoresHelper.getConta().getId().equals(new Integer(idsContasArray[x]))) {
						colecaoContas.add(contaValoresHelper);
						valorTotalConta = valorTotalConta.add(contaValoresHelper.getValorTotalConta());
						valorTotalAcrescimoImpontualidade = valorTotalAcrescimoImpontualidade.add(contaValoresHelper.getValorTotalContaValoresParcelamento());

						if (contaValoresHelper.getConta().getValorImposto() != null) {
							valorTotalConta = valorTotalConta.subtract(contaValoresHelper.getConta().getValorImposto());
						}

						if (contaValoresHelper.getValorAtualizacaoMonetaria() != null && !contaValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(contaValoresHelper.getValorAtualizacaoMonetaria().setScale(
									Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorJurosMora() != null && !contaValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(contaValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,
									Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorMulta() != null && !contaValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(contaValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,
									Parcelamento.TIPO_ARREDONDAMENTO));
						}

					}
				}
			}
			retorno[0] = colecaoContas;
			retorno[1] = valorTotalConta;
			retorno[2] = valorTotalAcrescimoImpontualidade;
			retorno[3] = valorAtualizacaoMonetaria;
			retorno[4] = valorJurosMora;
			retorno[5] = valorMulta;
		}

		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object[] obterDebitosSelecionados(String idsDebitos, HttpSession sessao) {

		Object[] retorno = null;
		Collection<DebitoACobrar> colecaoDebitos = null;
		BigDecimal valorTotalDebitoACobrar = BigDecimal.ZERO;

		if (idsDebitos != null && !idsDebitos.equals("")) {
			retorno = new Object[2];
			colecaoDebitos = new ArrayList();

			Collection colecaoDebitosSessao = (Collection) sessao.getAttribute("colecaoDebitoACobrar");
			Iterator itColecaoDebitosSessao = colecaoDebitosSessao.iterator();
			DebitoACobrar debitoACobrar = null;

			String[] idsDebitosArray = idsDebitos.split(",");

			while (itColecaoDebitosSessao.hasNext()) {

				debitoACobrar = (DebitoACobrar) itColecaoDebitosSessao.next();

				for (int x = 0; x < idsDebitosArray.length; x++) {

					if (debitoACobrar.getId().equals(new Integer(idsDebitosArray[x]))) {
						colecaoDebitos.add(debitoACobrar);
						valorTotalDebitoACobrar = valorTotalDebitoACobrar.add(debitoACobrar.getValorTotalComBonus());

					}
				}
			}
			retorno[0] = colecaoDebitos;
			retorno[1] = valorTotalDebitoACobrar;
		}

		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object[] obterCreditosSelecionadas(String idsCreditos, HttpSession sessao) {

		Object[] retorno = null;
		Collection<CreditoARealizar> colecaoCreditos = null;
		BigDecimal valorTotalCreditoARealizar = BigDecimal.ZERO;

		if (idsCreditos != null && !idsCreditos.equals("")) {
			retorno = new Object[2];
			colecaoCreditos = new ArrayList();

			Collection colecaoCreditosSessao = (Collection) sessao.getAttribute("colecaoCreditoARealizar");
			Iterator itColecaoCreditosSessao = colecaoCreditosSessao.iterator();
			CreditoARealizar creditoARealizar = null;

			String[] idsCreditosArray = idsCreditos.split(",");

			while (itColecaoCreditosSessao.hasNext()) {

				creditoARealizar = (CreditoARealizar) itColecaoCreditosSessao.next();

				for (int x = 0; x < idsCreditosArray.length; x++) {

					if (creditoARealizar.getId().equals(new Integer(idsCreditosArray[x]))) {
						colecaoCreditos.add(creditoARealizar);
						valorTotalCreditoARealizar = valorTotalCreditoARealizar.add(creditoARealizar.getValorTotalComBonus());

					}
				}
			}
			retorno[0] = colecaoCreditos;
			retorno[1] = valorTotalCreditoARealizar;
		}

		return retorno;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object[] obterGuiasSelecionadas(String idsGuias, HttpSession sessao) {

		Object[] retorno = null;
		Collection<GuiaPagamentoValoresHelper> colecaoGuias = null;
		BigDecimal valorTotalGuiaPagamento = BigDecimal.ZERO;
		BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
		BigDecimal valorJurosMora = new BigDecimal("0.00");
		BigDecimal valorMulta = new BigDecimal("0.00");

		if (idsGuias != null && !idsGuias.equals("")) {
			retorno = new Object[5];
			colecaoGuias = new ArrayList();

			Collection colecaoGuiasSessao = (Collection) sessao.getAttribute("colecaoGuiaPagamento");
			Iterator itColecaoGuiasSessao = colecaoGuiasSessao.iterator();
			GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = null;

			String[] idsGuiasArray = idsGuias.split(",");

			while (itColecaoGuiasSessao.hasNext()) {

				guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) itColecaoGuiasSessao.next();

				for (int x = 0; x < idsGuiasArray.length; x++) {

					if (guiaPagamentoValoresHelper.getGuiaPagamento().getId().equals(new Integer(idsGuiasArray[x]))) {
						colecaoGuias.add(guiaPagamentoValoresHelper);
						valorTotalGuiaPagamento = valorTotalGuiaPagamento.add(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());

						if (guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria() != null
								&& !guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().setScale(
									Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (guiaPagamentoValoresHelper.getValorJurosMora() != null && !guiaPagamentoValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(guiaPagamentoValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,
									Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (guiaPagamentoValoresHelper.getValorMulta() != null && !guiaPagamentoValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(guiaPagamentoValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,
									Parcelamento.TIPO_ARREDONDAMENTO));
						}

					}
				}
			}
			retorno[0] = colecaoGuias;
			retorno[1] = valorTotalGuiaPagamento;
			retorno[2] = valorAtualizacaoMonetaria;
			retorno[3] = valorJurosMora;
			retorno[4] = valorMulta;
		}

		return retorno;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object[] obterParcelamentosSelecionados(String idsParcelamentos, HttpSession sessao) {

		Object[] retorno = null;
		Collection<DebitoACobrar> colecaoDebitoFinal = null;
		Collection<CreditoARealizar> colecaoCreditoFinal = null;
		BigDecimal valorTotalDebito = BigDecimal.ZERO;
		BigDecimal valorTotalCredito = BigDecimal.ZERO;

		if (idsParcelamentos != null && !idsParcelamentos.equals("")) {
			retorno = new Object[4];
			colecaoDebitoFinal = new ArrayList();
			colecaoCreditoFinal = new ArrayList();

			Collection colecaoDebitoCreditoParcelamentoSessao = (Collection) sessao.getAttribute("colecaoDebitoCreditoParcelamento");
			Iterator itColecaoDebitoCreditoParcelamentoSessao = colecaoDebitoCreditoParcelamentoSessao.iterator();
			DebitoCreditoParcelamentoHelper debitoCreditoParcelamentoHelper = null;

			String[] idsParcelamentoArray = idsParcelamentos.split(",");

			while (itColecaoDebitoCreditoParcelamentoSessao.hasNext()) {

				debitoCreditoParcelamentoHelper = (DebitoCreditoParcelamentoHelper) itColecaoDebitoCreditoParcelamentoSessao.next();

				for (int x = 0; x < idsParcelamentoArray.length; x++) {

					if (debitoCreditoParcelamentoHelper.getParcelamento().getId().equals(new Integer(idsParcelamentoArray[x]))) {
						Collection<DebitoACobrar> colecaoDebito = null;
						Collection<CreditoARealizar> colecaoCredito = null;

						if (debitoCreditoParcelamentoHelper.getColecaoCreditoARealizarParcelamento() != null
								&& !debitoCreditoParcelamentoHelper.getColecaoCreditoARealizarParcelamento().isEmpty()) {
							colecaoCredito = debitoCreditoParcelamentoHelper.getColecaoCreditoARealizarParcelamento();

							Iterator iterCredito = colecaoCredito.iterator();
							while (iterCredito.hasNext()) {
								CreditoARealizar creditoARealizar = (CreditoARealizar) iterCredito.next();
								colecaoCreditoFinal.add(creditoARealizar);
								valorTotalCredito = valorTotalCredito.add(creditoARealizar.getValorTotalComBonus());
							}

						}

						if (debitoCreditoParcelamentoHelper.getColecaoDebitoACobrarParcelamento() != null
								&& !debitoCreditoParcelamentoHelper.getColecaoDebitoACobrarParcelamento().isEmpty()) {
							colecaoDebito = debitoCreditoParcelamentoHelper.getColecaoDebitoACobrarParcelamento();

							Iterator iterDebito = colecaoDebito.iterator();
							while (iterDebito.hasNext()) {
								DebitoACobrar debitoACobrar = (DebitoACobrar) iterDebito.next();
								colecaoDebitoFinal.add(debitoACobrar);
								valorTotalDebito = valorTotalDebito.add(debitoACobrar.getValorTotalComBonus());
							}

						}

					}
				}
			}
			retorno[0] = colecaoDebitoFinal;
			retorno[1] = valorTotalDebito;
			retorno[2] = colecaoCreditoFinal;
			retorno[3] = valorTotalCredito;
		}

		return retorno;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void calcularDescontos(DebitoCreditoDadosSelecaoExtratoActionForm form, HttpServletRequest httpServletRequest, HttpSession sessao, Fachada fachada,
			SistemaParametro sistemaParametro, Usuario usuarioLogado) {

		String indicadorIncluirAcrescimosImpontualidade = form.getIndicadorIncluirAcrescimosImpontualidade();

		Collection<ContaValoresHelper> colecaoContas = null;
		Collection<GuiaPagamentoValoresHelper> colecaoGuiasPagamento = null;
		Collection<DebitoACobrar> colecaoDebitosACobrar = null;
		Collection<CreditoARealizar> colecaoCreditoARealizar = null;
		Collection<DebitoACobrar> colecaoDebitosACobrarParcelamento = null;
		Collection<CreditoARealizar> colecaoCreditoARealizarParcelamento = null;
		BigDecimal valorAcrescimosImpontualidade = new BigDecimal("0.00");
		BigDecimal valorDebitoTotalAtualizado = new BigDecimal("0.00");

		String idsContas = httpServletRequest.getParameter("conta");
		String idsDebitos = httpServletRequest.getParameter("debito");
		String idsCreditos = httpServletRequest.getParameter("credito");
		String idsGuias = httpServletRequest.getParameter("guiaPagamento");
		String idsParcelamentos = httpServletRequest.getParameter("parcelamento");

		Object[] contas = this.obterContasSelecionadas(idsContas, sessao);
		Object[] debitos = this.obterDebitosSelecionados(idsDebitos, sessao);
		Object[] creditos = this.obterCreditosSelecionadas(idsCreditos, sessao);
		Object[] guias = this.obterGuiasSelecionadas(idsGuias, sessao);
		Object[] parcelamentos = this.obterParcelamentosSelecionados(idsParcelamentos, sessao);

		// ANTECIPA��O DE PARCELAS DE PARCELAMENTO
		Map<String, String[]> requestAntecipacaoParcelasMap = httpServletRequest.getParameterMap();

		Object[] parcelasAntecipacaoParcelamento = this.obterAntecipacaoParcelasParcelamentosSelecionados(sessao, requestAntecipacaoParcelasMap, fachada);

		/*
		 * TOTALIZANDO O VALOR DE CADA TIPO DE DOCUMENTO SELECIONADO, EM
		 * SEPARADO
		 */
		BigDecimal valorTotalConta = BigDecimal.ZERO;
		BigDecimal valorTotalDebitoACobrar = BigDecimal.ZERO;
		BigDecimal valorTotalCreditoARealizar = BigDecimal.ZERO;
		BigDecimal valorTotalGuiaPagamento = BigDecimal.ZERO;
		BigDecimal valorTotalDebitoParcelamento = BigDecimal.ZERO;
		BigDecimal valorTotalCreditoParcelamento = BigDecimal.ZERO;
		BigDecimal valorTotalAntecipacaoDebitoParcelamento = BigDecimal.ZERO;
		BigDecimal valorTotalAntecipacaoCreditoParcelamento = BigDecimal.ZERO;
		BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
		BigDecimal valorJurosMora = new BigDecimal("0.00");
		BigDecimal valorMulta = new BigDecimal("0.00");

		// CONTAS
		if (contas != null) {
			colecaoContas = (Collection) contas[0];
			valorTotalConta = (BigDecimal) contas[1];

			if (contas[2] != null && !indicadorIncluirAcrescimosImpontualidade.equals(CobrancaDocumento.NAO_INCLUIR_ACRESCIMOS)) {
				valorAcrescimosImpontualidade = (BigDecimal) contas[2];

			}

			valorAtualizacaoMonetaria = (BigDecimal) contas[3];
			valorJurosMora = (BigDecimal) contas[4];
			valorMulta = (BigDecimal) contas[5];

		}

		// D�BITOS A COBRAR
		if (debitos != null) {
			colecaoDebitosACobrar = (Collection) debitos[0];
			valorTotalDebitoACobrar = (BigDecimal) debitos[1];
		}

		// CR�DITOS A REALIZAR
		if (creditos != null) {
			colecaoCreditoARealizar = (Collection) creditos[0];
			valorTotalCreditoARealizar = (BigDecimal) creditos[1];
		}

		// GUIAS DE PAGAMENTO
		if (guias != null) {
			colecaoGuiasPagamento = (Collection) guias[0];
			valorTotalGuiaPagamento = (BigDecimal) guias[1];

			valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add((BigDecimal) guias[2]);
			valorJurosMora = valorJurosMora.add((BigDecimal) guias[3]);
			valorMulta = valorMulta.add((BigDecimal) guias[4]);
		}

		// PARCELAMENTOS
		if (parcelamentos != null) {
			colecaoDebitosACobrarParcelamento = (Collection) parcelamentos[0];
			valorTotalDebitoParcelamento = (BigDecimal) parcelamentos[1];

			if (colecaoDebitosACobrarParcelamento != null) {
				if (colecaoDebitosACobrar == null) {
					colecaoDebitosACobrar = new ArrayList();
				}
				colecaoDebitosACobrar.addAll(colecaoDebitosACobrarParcelamento);
			}

			colecaoCreditoARealizarParcelamento = (Collection) parcelamentos[2];
			valorTotalCreditoParcelamento = (BigDecimal) parcelamentos[3];

			if (colecaoCreditoARealizarParcelamento != null) {
				if (colecaoCreditoARealizar == null) {
					colecaoCreditoARealizar = new ArrayList();
				}
				colecaoCreditoARealizar.addAll(colecaoCreditoARealizarParcelamento);
			}
		}

		// ANTECIPA��O PARCELAS DE PARCELAMENTO
		if (parcelasAntecipacaoParcelamento != null) {

			valorTotalAntecipacaoDebitoParcelamento = (BigDecimal) parcelasAntecipacaoParcelamento[1];
			valorTotalAntecipacaoCreditoParcelamento = (BigDecimal) parcelasAntecipacaoParcelamento[3];
		}

		// TOTALIZANDO O VALOR DO D�BITO TOTAL
		valorDebitoTotalAtualizado = valorTotalConta.add(valorTotalDebitoACobrar);
		valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.add(valorTotalGuiaPagamento);
		valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.add(valorTotalDebitoParcelamento);
		valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.add(valorAcrescimosImpontualidade);
		valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.add(valorTotalAntecipacaoDebitoParcelamento);
		valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.subtract(valorTotalCreditoARealizar);
		valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.subtract(valorTotalCreditoParcelamento);
		valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.subtract(valorTotalAntecipacaoCreditoParcelamento);

		// TOTALIZANDO O VALOR DOS CR�DITOS A REALIZAR
		BigDecimal valorCreditoDocumento = valorTotalCreditoARealizar.add(valorTotalCreditoParcelamento);
		valorCreditoDocumento = valorCreditoDocumento.add(valorTotalAntecipacaoCreditoParcelamento);

		ImovelPerfil imovelPerfil = fachada.obterImovelPerfil(new Integer(form.getIdImovel()));
		Short numeroReparcelamentoConsecutivos = fachada.consultarNumeroReparcelamentoConsecutivosImovel(new Integer(form.getIdImovel()));
		if (numeroReparcelamentoConsecutivos == null) {
			numeroReparcelamentoConsecutivos = new Short("0");
		}
		IndicadoresParcelamentoHelper indicadoresParcelamentoHelper = new IndicadoresParcelamentoHelper();

		indicadoresParcelamentoHelper.setIndicadorDebitosACobrar(new Integer("1"));
		indicadoresParcelamentoHelper.setIndicadorCreditoARealizar(new Integer("1"));
		indicadoresParcelamentoHelper.setIndicadorGuiasPagamento(new Integer("1"));
		indicadoresParcelamentoHelper.setIndicadorAcrescimosImpotualidade(new Integer("1"));
		indicadoresParcelamentoHelper.setIndicadorContasRevisao(new Integer("1"));
		indicadoresParcelamentoHelper.setIndicadorDividaAtiva(new Integer("3"));

		String indicadorRestabelecimento = form.getIndicadorRestabelecimento();
		if (indicadorRestabelecimento == null) {
			indicadorRestabelecimento = "2";
		}

		// CARREGANDO O HELPER COM AS INFORMA��ES DO PARCELAMENTO
		ObterOpcoesDeParcelamentoHelper helper = new ObterOpcoesDeParcelamentoHelper(sistemaParametro.getResolucaoDiretoria().getId(), new Integer(
				form.getIdImovel()), new BigDecimal("0.00"), new Integer(form.getIdLigacaoAguaSituacaoImovel()), new Integer(
				form.getIdLigacaoEsgotoSituacaoImovel()), imovelPerfil.getId(), "01/0001", new Integer(indicadorRestabelecimento), colecaoContas,
				valorDebitoTotalAtualizado, valorMulta, valorJurosMora, valorAtualizacaoMonetaria, new Integer(numeroReparcelamentoConsecutivos.toString()),
				colecaoGuiasPagamento, usuarioLogado, valorTotalDebitoParcelamento, Util.formatarMesAnoComBarraParaAnoMes("01/0001"),
				Util.formatarMesAnoComBarraParaAnoMes("12/9999"), indicadoresParcelamentoHelper, valorCreditoDocumento);

		NegociacaoOpcoesParcelamentoHelper negociacaoOpcoesParcelamentoHelper = fachada.calcularValorDosDescontosPagamentoAVista(helper);

		BigDecimal valorTotalDescontoPagamentoAVista = negociacaoOpcoesParcelamentoHelper.getValorTotalDescontoPagamentoAVista();
		BigDecimal valorPagamentoAVista = valorDebitoTotalAtualizado.subtract(valorTotalDescontoPagamentoAVista);

		form.setValorDescontoPagamentoAVista(Util.formatarMoedaReal(valorTotalDescontoPagamentoAVista));
		form.setValorPagamentoAVista(Util.formatarMoedaReal(valorPagamentoAVista));

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object[] obterAntecipacaoParcelasParcelamentosSelecionados(HttpSession sessao, Map<String, String[]> requestMap, Fachada fachada) {

		Object[] retorno = null;

		// PARCELAMENTOS DISPONIBILIZADOS PARA O USU�RIO SELECIONAR
		Collection colecaoDebitoCreditoParcelamentoSessao = (Collection) sessao.getAttribute("colecaoDebitoCreditoParcelamento");

		if (colecaoDebitoCreditoParcelamentoSessao != null && !colecaoDebitoCreditoParcelamentoSessao.isEmpty()) {

			Iterator itColecaoDebitoCreditoParcelamentoSessao = colecaoDebitoCreditoParcelamentoSessao.iterator();
			DebitoCreditoParcelamentoHelper debitoCreditoParcelamentoHelper = null;

			BigDecimal valorTotalDebito = BigDecimal.ZERO;
			BigDecimal valorTotalCredito = BigDecimal.ZERO;

			Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoDebitos = new ArrayList();
			Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoCreditos = new ArrayList();

			while (itColecaoDebitoCreditoParcelamentoSessao.hasNext()) {

				debitoCreditoParcelamentoHelper = (DebitoCreditoParcelamentoHelper) itColecaoDebitoCreditoParcelamentoSessao.next();

				if (requestMap.get("parc" + debitoCreditoParcelamentoHelper.getParcelamento().getId()) != null) {

					// QUANTIDADE DE PARCELAS QUE SER�O ANTECIPADAS
					String qtdAntecipacaoParcelas = (requestMap.get("parc" + debitoCreditoParcelamentoHelper.getParcelamento().getId()))[0];

					if (qtdAntecipacaoParcelas != null && !qtdAntecipacaoParcelas.equals("")) {

						// INICIALIZANDO O OBJETO DE RETORNO
						if (retorno == null) {
							retorno = new Object[4];
						}

						// QUANTIDADE DE PARCELAS QUE SER�O ANTECIPADAS
						debitoCreditoParcelamentoHelper.setQuantidadeAntecipacaoParcelas(Integer.valueOf(qtdAntecipacaoParcelas));

						/*
						 * SELECIONANDO OS D�BITOS RELACIONADOS AO PARCELAMENTO
						 * E CALCULANDO O VALOR QUE SER� COLOCADO NO EXTRATO DE
						 * ACORDO COM A QUANTIDADE DE PARCELAS QUE FOI INFORMADA
						 * PARA ANTECIPA��O.
						 */
						if (debitoCreditoParcelamentoHelper.getColecaoDebitoACobrarParcelamento() != null
								&& !debitoCreditoParcelamentoHelper.getColecaoDebitoACobrarParcelamento().isEmpty()) {

							Collection colecaoDebito = debitoCreditoParcelamentoHelper.getColecaoDebitoACobrarParcelamento();

							Iterator iterDebito = colecaoDebito.iterator();

							while (iterDebito.hasNext()) {

								DebitoACobrar debitoACobrar = (DebitoACobrar) iterDebito.next();

								/*
								 * [UC0630] Solicitar Emiss�o do Extrato de
								 * D�bitos [FS0003] � Quantidade de Parcelas
								 * Informadas Inv�lida
								 */
								fachada.verificarQuantidadeParcelasInformada(debitoACobrar, Short.valueOf(qtdAntecipacaoParcelas));

								// CALCULANDO O VALOR QUE SER� ANTECIPADO
								valorTotalDebito = valorTotalDebito.add(debitoACobrar.getValorAntecipacaoParcela(Integer.valueOf(qtdAntecipacaoParcelas)));
							}

							// D�BITOS A COBRAR QUE FAR�O PARTE DO EXTRATO DE
							// D�BITO
							colecaoAntecipacaoDebitos.add(debitoCreditoParcelamentoHelper);
						}

						/*
						 * SELECIONANDO OS CR�DITOS RELACIONADOS AO PARCELAMENTO
						 * E CALCULANDO O VALOR QUE SER� COLOCADO NO EXTRATO DE
						 * ACORDO COM A QUANTIDADE DE PARCELAS QUE FOI INFORMADA
						 * PARA ANTECIPA��O.
						 */
						if (debitoCreditoParcelamentoHelper.getColecaoCreditoARealizarParcelamento() != null
								&& !debitoCreditoParcelamentoHelper.getColecaoCreditoARealizarParcelamento().isEmpty()) {

							Collection colecaoCredito = debitoCreditoParcelamentoHelper.getColecaoCreditoARealizarParcelamento();

							Iterator iterCredito = colecaoCredito.iterator();

							while (iterCredito.hasNext()) {

								CreditoARealizar creditoARealizar = (CreditoARealizar) iterCredito.next();

								// CALCULANDO O VALOR QUE SER� ANTECIPADO
								valorTotalCredito = valorTotalCredito.add(creditoARealizar.getValorAntecipacaoParcela(Integer.valueOf(qtdAntecipacaoParcelas)));
							}

							// CR�DITOS A REALIZAR QUE FAR�O PARTE DO EXTRATO DE
							// D�BITO
							colecaoAntecipacaoCreditos.add(debitoCreditoParcelamentoHelper);
						}
					}
				}
			}

			if (retorno != null) {

				retorno[0] = colecaoAntecipacaoDebitos;
				retorno[1] = valorTotalDebito;
				retorno[2] = colecaoAntecipacaoCreditos;
				retorno[3] = valorTotalCredito;
			}
		}

		return retorno;
	}
}
