package gcom.gui.cobranca;

import gcom.arrecadacao.pagamento.FiltroGuiaPagamento;
import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ConcluirParcelamentoDebitosHelper;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.OpcoesParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

public class ConcluirEfetuarParcelamentoDebitosAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = request.getSession(false);

		DynaActionForm form = (DynaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		// ABA 1 - 6.1 Caso o usuario confirme o parecelamento
		String codigoImovel = (String) form.get("matriculaImovel");
		Integer situacaoAguaId = new Integer((String) (form.get("situacaoAguaId")));
		Date dataParcelamento = Util.converteStringParaDate((String) form.get("dataParcelamento"));
		String resolucaoDiretoria = (String) form.get("resolucaoDiretoria");
		String fimIntervaloParcelamento = (String) form.get("fimIntervaloParcelamento");
		String indicadorRestabelecimento = (String) form.get("indicadorRestabelecimento");
		String indicadorContasRevisao = (String) form.get("indicadorContasRevisao");
		String indicadorGuiasPagamento = (String) form.get("indicadorGuiasPagamento");
		String indicadorAcrescimosImpotualidade = (String) form.get("indicadorAcrescimosImpotualidade");
		String indicadorDebitosACobrar = (String) form.get("indicadorDebitosACobrar");
		String indicadorCreditoARealizar = (String) form.get("indicadorCreditoARealizar");
		String indicadorConfirmacaoParcelamento = (String) form.get("indicadorConfirmacaoParcelamento");
		String cpfClienteParcelamentoDigitado = (String) form.get("cpfClienteParcelamentoDigitado");
		String indicadorDividaAtiva = (String) form.get("indicadorDividaAtiva");
		ParcelamentoPerfil parcelamentoPerfil = (ParcelamentoPerfil) sessao.getAttribute("parcelamentoPerfil");

		// Caso o fim do parcelamento não seja informado
		if (fimIntervaloParcelamento == null || fimIntervaloParcelamento.equals("")) {
			fimIntervaloParcelamento = "12/9999";
		}

		// [FS0009] - Verificar preenchimento dos campos
		if (codigoImovel == null || codigoImovel.trim().equals("")) {
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Matricula do Imóvel");
		}
		if (dataParcelamento == null || dataParcelamento.equals("")) {
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "Data do Parcelamento");
		}
		if (resolucaoDiretoria == null || resolucaoDiretoria.trim().equals("")) {
			throw new ActionServletException("atencao.campo_texto.obrigatorio", null, "RD do Parcelamento");
		}
		if (situacaoAguaId.equals(LigacaoAguaSituacao.SUPRIMIDO) || situacaoAguaId.equals(LigacaoAguaSituacao.SUPR_PARC) || situacaoAguaId.equals(LigacaoAguaSituacao.SUPR_PARC_PEDIDO)) {
			if (indicadorRestabelecimento == null || indicadorRestabelecimento.trim().equals("")) {
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Com Restabelecimento?");
			}
		}
		if (indicadorContasRevisao == null || indicadorContasRevisao.trim().equals("")) {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Considerar Contas em Revisão?");
		}
		if (indicadorGuiasPagamento == null || indicadorGuiasPagamento.trim().equals("")) {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Considerar Guias de Pagamento?");
		}
		if (indicadorAcrescimosImpotualidade == null || indicadorAcrescimosImpotualidade.trim().equals("")) {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Considerar Acréscimos por Impontualidade?");
		}
		if (indicadorDebitosACobrar == null || indicadorDebitosACobrar.trim().equals("")) {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Considerar Débitos a Cobrar?");
		}

		if (indicadorCreditoARealizar == null || indicadorCreditoARealizar.trim().equals("")) {
			throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "'Considerar Créditos a Realizar?'");
		}

		// ABA 3 - Verifica se foi escolhido alguma opção de parcelamento
		Collection<OpcoesParcelamentoHelper> colecaoOpcoesParcelamento = (Collection<OpcoesParcelamentoHelper>) sessao.getAttribute("colecaoOpcoesParcelamento");

		Short numeroPrestacoes = new Short("0");
		BigDecimal valorPrestacao = new BigDecimal("0.00");
		BigDecimal valorEntradaMinima = new BigDecimal("0.00");
		BigDecimal taxaJuros = new BigDecimal("0.00");
		
		if (colecaoOpcoesParcelamento != null && !colecaoOpcoesParcelamento.isEmpty()) {
			Iterator<OpcoesParcelamentoHelper> opcoesParcelamentoValores = colecaoOpcoesParcelamento.iterator();
			
			boolean opcaoMarcada = false;
			
			while (opcoesParcelamentoValores.hasNext()) {
				
				OpcoesParcelamentoHelper opcoesParcelamentoHelper = (OpcoesParcelamentoHelper) opcoesParcelamentoValores.next();
				
				if (((String) form.get("indicadorQuantidadeParcelas")).equals(opcoesParcelamentoHelper.getQuantidadePrestacao().toString())) {
					opcaoMarcada = true;
					numeroPrestacoes = opcoesParcelamentoHelper.getQuantidadePrestacao();
					valorPrestacao = opcoesParcelamentoHelper.getValorPrestacao();
					valorEntradaMinima = opcoesParcelamentoHelper.getValorEntradaMinima();
					taxaJuros = opcoesParcelamentoHelper.getTaxaJuros();
				}
			}
			
			if (!opcaoMarcada) {
				throw new ActionServletException("atencao.pelo.menos.uma.opcao.parcelamento.marcada");
			}
		} else {
			throw new ActionServletException("atencao.parametros.obrigatorios.nao.selecionados");
		}

		BigDecimal valorEntradaInformado = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorEntradaInformado")));

		BigDecimal valorDebitoTotalAtualizado = BigDecimal.ZERO;
		if (form.get("valorDebitoTotalAtualizado") != null && !form.get("valorDebitoTotalAtualizado").equals("")) {
			valorDebitoTotalAtualizado = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorDebitoTotalAtualizado")));
		}

		verificarValorEntradaMinimaPermitida(numeroPrestacoes, valorEntradaInformado, valorDebitoTotalAtualizado, parcelamentoPerfil);

		BigDecimal valorTotalContaValores = BigDecimal.ZERO;
		if (form.get("valorTotalContaValores") != null && !form.get("valorTotalContaValores").equals("")) {
			valorTotalContaValores = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorTotalContaValores")));
		}
		BigDecimal valorGuiasPagamento = BigDecimal.ZERO;
		if (form.get("valorGuiasPagamento") != null && !form.get("valorGuiasPagamento").equals("")) {
			valorGuiasPagamento = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorGuiasPagamento")));
		}

		BigDecimal valorDebitoACobrarServico = BigDecimal.ZERO;
		if (form.get("valorDebitoACobrarServico") != null && !form.get("valorDebitoACobrarServico").equals("")) {
			valorDebitoACobrarServico = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorDebitoACobrarServico")));
		}

		BigDecimal valorDebitoACobrarParcelamento = BigDecimal.ZERO;
		if (form.get("valorDebitoACobrarParcelamento") != null && !form.get("valorDebitoACobrarParcelamento").equals("")) {
			valorDebitoACobrarParcelamento = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorDebitoACobrarParcelamento")));
		}

		BigDecimal valorCreditoARealizar = BigDecimal.ZERO;
		if (form.get("valorCreditoARealizar") != null && !form.get("valorCreditoARealizar").equals("")) {
			valorCreditoARealizar = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorCreditoARealizar")));
		}
		
		BigDecimal valorCreditosAnterioresCurtoPrazo = BigDecimal.ZERO;
		if (form.get("valorCreditosAnterioresCurtoPrazo") != null && !form.get("valorCreditosAnterioresCurtoPrazo").equals("")) {
			valorCreditosAnterioresCurtoPrazo = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorCreditosAnterioresCurtoPrazo")));
		}
		
		BigDecimal valorCreditosAnterioresLongoPrazo = BigDecimal.ZERO;
		if (form.get("valorCreditosAnterioresLongoPrazo") != null && !form.get("valorCreditosAnterioresLongoPrazo").equals("")) {
			valorCreditosAnterioresLongoPrazo = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorCreditosAnterioresLongoPrazo")));
		}
		
		BigDecimal valorTotalCreditosAnteriores = BigDecimal.ZERO;
		if (form.get("valorTotalCreditosAnteriores") != null && !form.get("valorTotalCreditosAnteriores").equals("")) {
			valorTotalCreditosAnteriores = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorTotalCreditosAnteriores")));
		}

		BigDecimal valorAtualizacaoMonetaria = BigDecimal.ZERO;
		if (form.get("valorAtualizacaoMonetaria") != null && !form.get("valorAtualizacaoMonetaria").equals("")) {
			valorAtualizacaoMonetaria = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorAtualizacaoMonetaria")));
		}

		BigDecimal valorJurosMora = BigDecimal.ZERO;
		if (form.get("valorJurosMora") != null && !form.get("valorJurosMora").equals("")) {
			valorJurosMora = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorJurosMora")));
		}

		BigDecimal valorMulta = BigDecimal.ZERO;
		if (form.get("valorMulta") != null && !form.get("valorMulta").equals("")) {
			valorMulta = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorMulta")));
		}

		BigDecimal descontoFaixaReferenciaConta = BigDecimal.ZERO;
		if (form.get("descontoFaixaReferenciaConta") != null && !form.get("descontoFaixaReferenciaConta").equals("")) {
			descontoFaixaReferenciaConta = Util.formatarMoedaRealparaBigDecimal((String) (form.get("descontoFaixaReferenciaConta")));
		}
		
		BigDecimal descontoAcrescimosImpontualidade = BigDecimal.ZERO;
		if (form.get("descontoAcrescimosImpontualidade") != null && !form.get("descontoAcrescimosImpontualidade").equals("")) {
			descontoAcrescimosImpontualidade = Util.formatarMoedaRealparaBigDecimal((String) (form.get("descontoAcrescimosImpontualidade")));
		}

		BigDecimal descontoSancoesRDEspecial = BigDecimal.ZERO;
		if (form.get("descontoSancoesRDEspecial") != null && !form.get("descontoSancoesRDEspecial").equals("")) {
			descontoSancoesRDEspecial = Util.formatarMoedaRealparaBigDecimal((String) (form.get("descontoSancoesRDEspecial")));
		}

		BigDecimal descontoTarifaSocialRDEspecial = BigDecimal.ZERO;
		if (form.get("descontoTarifaSocialRDEspecial") != null && !form.get("descontoTarifaSocialRDEspecial").equals("")) {
			descontoTarifaSocialRDEspecial = Util.formatarMoedaRealparaBigDecimal((String) (form.get("descontoTarifaSocialRDEspecial")));
		}

		BigDecimal descontoAntiguidadeDebito = BigDecimal.ZERO;
		if (form.get("descontoAntiguidadeDebito") != null && !form.get("descontoAntiguidadeDebito").equals("")) {
			descontoAntiguidadeDebito = Util.formatarMoedaRealparaBigDecimal((String) (form.get("descontoAntiguidadeDebito")));
		}

		BigDecimal descontoInatividadeLigacaoAgua = BigDecimal.ZERO;
		if (form.get("descontoInatividadeLigacaoAgua") != null && !form.get("descontoInatividadeLigacaoAgua").equals("")) {
			descontoInatividadeLigacaoAgua = Util.formatarMoedaRealparaBigDecimal((String) (form.get("descontoInatividadeLigacaoAgua")));
		}

		BigDecimal percentualDescontoAcrescimosImpontualidade = BigDecimal.ZERO;
		if (form.get("percentualDescontoAcrescimosImpontualidade") != null && !form.get("percentualDescontoAcrescimosImpontualidade").equals("")) {
			percentualDescontoAcrescimosImpontualidade = Util.formatarMoedaRealparaBigDecimal((String) (form.get("percentualDescontoAcrescimosImpontualidade")));
		}

		BigDecimal percentualDescontoAntiguidadeDebito = BigDecimal.ZERO;
		if (form.get("percentualDescontoAntiguidadeDebito") != null && !form.get("percentualDescontoAntiguidadeDebito").equals("")) {
			percentualDescontoAntiguidadeDebito = Util.formatarMoedaRealparaBigDecimal((String) (form.get("percentualDescontoAntiguidadeDebito")));
		}

		BigDecimal percentualDescontoInatividadeLigacaoAgua = BigDecimal.ZERO;
		if (form.get("percentualDescontoInatividadeLigacaoAgua") != null
				&& !form.get("percentualDescontoInatividadeLigacaoAgua").equals("")) {
			percentualDescontoInatividadeLigacaoAgua = Util.formatarMoedaRealparaBigDecimal((String) (form.get("percentualDescontoInatividadeLigacaoAgua")));
		}

		BigDecimal valorAcrescimosImpontualidade = BigDecimal.ZERO;
		if (form.get("valorAcrescimosImpontualidade") != null && !form.get("valorAcrescimosImpontualidade").equals("")) {
			valorAcrescimosImpontualidade = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorAcrescimosImpontualidade")));
		}

		Integer parcelamentoPerfilId = new Integer((String) (form.get("parcelamentoPerfilId")));

		BigDecimal valorDebitoACobrarServicoLongoPrazo = new BigDecimal("0.00");
		if (form.get("valorDebitoACobrarServicoLongoPrazo") != null && !form.get("valorDebitoACobrarServicoLongoPrazo").equals("")) {
			valorDebitoACobrarServicoLongoPrazo = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorDebitoACobrarServicoLongoPrazo")));
		}

		BigDecimal valorDebitoACobrarServicoCurtoPrazo = new BigDecimal("0.00");
		if (form.get("valorDebitoACobrarServicoCurtoPrazo") != null && !form.get("valorDebitoACobrarServicoCurtoPrazo").equals("")) {
			valorDebitoACobrarServicoCurtoPrazo = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorDebitoACobrarServicoCurtoPrazo")));
		}

		BigDecimal valorDebitoACobrarParcelamentoLongoPrazo = new BigDecimal("0.00");
		if (form.get("valorDebitoACobrarParcelamentoLongoPrazo") != null
				&& !form.get("valorDebitoACobrarParcelamentoLongoPrazo").equals("")) {
			valorDebitoACobrarParcelamentoLongoPrazo = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorDebitoACobrarParcelamentoLongoPrazo")));
		}

		BigDecimal valorDebitoACobrarParcelamentoCurtoPrazo = new BigDecimal("0.00");
		if (form.get("valorDebitoACobrarParcelamentoCurtoPrazo") != null
				&& !form.get("valorDebitoACobrarParcelamentoCurtoPrazo").equals("")) {
			valorDebitoACobrarParcelamentoCurtoPrazo = Util.formatarMoedaRealparaBigDecimal((String) (form.get("valorDebitoACobrarParcelamentoCurtoPrazo")));
		}

		BigDecimal valorASerParcelado = new BigDecimal("0.00");
		BigDecimal valorASerNegociado = new BigDecimal("0.00");

		if (colecaoOpcoesParcelamento != null && !colecaoOpcoesParcelamento.isEmpty()) {
			Iterator opcoesParcelamentoValores = colecaoOpcoesParcelamento.iterator();
			while (opcoesParcelamentoValores.hasNext()) {
				OpcoesParcelamentoHelper opcoesParcelamento = (OpcoesParcelamentoHelper) opcoesParcelamentoValores.next();
				if (((String) form.get("indicadorQuantidadeParcelas")).equals(opcoesParcelamento.getQuantidadePrestacao().toString())) {
					valorASerParcelado = opcoesParcelamento.getValorPrestacao().multiply(new BigDecimal(opcoesParcelamento.getQuantidadePrestacao()));
				}
			}
		}

		BigDecimal valorDesconto = new BigDecimal("0.00");
		valorDesconto = valorDesconto.add(descontoFaixaReferenciaConta);
		valorDesconto = valorDesconto.add(descontoAcrescimosImpontualidade);
		valorDesconto = valorDesconto.add(descontoAntiguidadeDebito);
		valorDesconto = valorDesconto.add(descontoInatividadeLigacaoAgua);

		form.set("valorDesconto", Util.formatarMoedaReal(valorDesconto));

		valorASerNegociado = valorDebitoTotalAtualizado.subtract(valorDesconto);

		FiltroImovel filtroImovel = new FiltroImovel();
		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, codigoImovel));
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade(FiltroImovel.CONSUMO_TARIFA);

		Collection<Imovel> imovelPesquisado = fachada.pesquisar(filtroImovel, Imovel.class.getName());

		Imovel imovel = null;
		if (imovelPesquisado != null && !imovelPesquisado.isEmpty()) {
			imovel = imovelPesquisado.iterator().next();
		}

		Collection<ContaValoresHelper> colecaoContaValores = new ArrayList<ContaValoresHelper>();
		if (sessao.getAttribute("colecaoContaValores") != null) {
			colecaoContaValores = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContaValores");
		} else {
			colecaoContaValores = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContaValoresImovel");
		}

		Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = new ArrayList<GuiaPagamentoValoresHelper>();
		if (sessao.getAttribute("colecaoGuiaPagamentoValores") != null || indicadorGuiasPagamento.equals("2")) {
			colecaoGuiaPagamentoValores = (Collection<GuiaPagamentoValoresHelper>) sessao.getAttribute("colecaoGuiaPagamentoValores");
		} else {
			colecaoGuiaPagamentoValores = (Collection<GuiaPagamentoValoresHelper>) sessao.getAttribute("colecaoGuiaPagamentoValoresImovel");
		}

		Collection<DebitoACobrar> colecaoDebitoACobrar = new ArrayList<DebitoACobrar>();
		if (sessao.getAttribute("colecaoDebitoACobrar") != null || indicadorDebitosACobrar.equals("2")) {
			colecaoDebitoACobrar = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrar");
		} else {
			colecaoDebitoACobrar = (Collection<DebitoACobrar>) sessao.getAttribute("colecaoDebitoACobrarImovel");
		}

		Collection<CreditoARealizar> colecaoCreditoARealizar = new ArrayList<CreditoARealizar>();
		if (sessao.getAttribute("colecaoCreditoARealizar") != null || indicadorCreditoARealizar.equals("2")) {
			colecaoCreditoARealizar = (Collection<CreditoARealizar>) sessao.getAttribute("colecaoCreditoARealizar");
		} else {
			colecaoCreditoARealizar = (Collection<CreditoARealizar>) sessao.getAttribute("colecaoCreditoARealizarImovel");
		}

		Cliente cliente = new Cliente();
		Integer idCliente = 0;
		if (form.get("idClienteParcelamento") != null && !form.get("idClienteParcelamento").equals("")) {
			idCliente = new Integer((String) form.get("idClienteParcelamento"));
		} else {
			idCliente = (Integer) sessao.getAttribute("idClienteImovel");
		}
		cliente.setId(idCliente);

		// O sistema verifica se o parcelamento é para ser incluído obrigatoriamente já confirmado
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		if (sistemaParametro.getIndicadorParcelamentoConfirmado() == ConstantesSistema.SIM.shortValue()) {

			indicadorConfirmacaoParcelamento = ConstantesSistema.SIM.toString();
		}

		// CARREGANDO INFORMAÇÕES PARA CONCLUSÃO DO PARCELAMENTO
		NegociacaoOpcoesParcelamentoHelper opcoesParcelamento = (NegociacaoOpcoesParcelamentoHelper) sessao.getAttribute("opcoesParcelamento");

		Collection colecaoContasEmAntiguidade = null;
		if (opcoesParcelamento != null) {
			colecaoContasEmAntiguidade = opcoesParcelamento.getColecaoContasEmAntiguidade();
		}

		ConcluirParcelamentoDebitosHelper helper = new ConcluirParcelamentoDebitosHelper(
				colecaoContaValores,
				colecaoGuiaPagamentoValores,
				colecaoDebitoACobrar,
				colecaoCreditoARealizar,
				indicadorRestabelecimento, 
				indicadorContasRevisao, 
				indicadorGuiasPagamento, 
				indicadorAcrescimosImpotualidade, 
				indicadorDebitosACobrar,
				indicadorCreditoARealizar, 
				indicadorDividaAtiva,
				imovel, 
				valorEntradaInformado,
				valorASerNegociado, 
				valorASerParcelado, 
				dataParcelamento, 
				valorTotalContaValores, 
				valorGuiasPagamento,
				valorDebitoACobrarServico, 
				valorDebitoACobrarParcelamento,
				valorCreditoARealizar,
				valorAtualizacaoMonetaria,
				valorJurosMora, 
				valorMulta,
				valorDebitoTotalAtualizado,
				descontoFaixaReferenciaConta,
				descontoAcrescimosImpontualidade, 
				descontoAntiguidadeDebito,
				descontoInatividadeLigacaoAgua,
				percentualDescontoAcrescimosImpontualidade,
				percentualDescontoAntiguidadeDebito,
				percentualDescontoInatividadeLigacaoAgua, 
				parcelamentoPerfilId, 
				valorAcrescimosImpontualidade, 
				valorDebitoACobrarServicoLongoPrazo,
				valorDebitoACobrarServicoCurtoPrazo,
				valorDebitoACobrarParcelamentoLongoPrazo,
				valorDebitoACobrarParcelamentoCurtoPrazo, 
				numeroPrestacoes, 
				valorPrestacao,
				valorEntradaMinima,
				taxaJuros, 
				indicadorConfirmacaoParcelamento,
				cliente, 
				usuarioLogado, 
				cpfClienteParcelamentoDigitado, 
				descontoSancoesRDEspecial,
				descontoTarifaSocialRDEspecial, 
				colecaoContasEmAntiguidade, 
				valorCreditosAnterioresCurtoPrazo,
				valorCreditosAnterioresLongoPrazo, 
				valorTotalCreditosAnteriores);

		Integer idParcelamento = fachada.concluirParcelamentoDebitos(helper, usuarioLogado);
		
		System.out.println(" ====== ConcluirEfetuarParcelamentoDebitosAction ====== " 
				+ "\n Matricula: " + imovel.getId() 
				+ "\n Acrescimos de Impontualidade: " + valorAcrescimosImpontualidade
				+ "\n Descontos de acrescimos de impontualidade " + descontoAcrescimosImpontualidade
				+ "\n Descontos por faixa de referencia da conta " + descontoFaixaReferenciaConta);

		sessao.setAttribute("idParcelamento", idParcelamento.toString());

		Collection idsContaEP = (Collection) sessao.getAttribute("idsContaEP");
		
		if (idsContaEP != null && !idsContaEP.isEmpty()) {
			montarPaginaSucesso(request, 
					"Parcelamento de Débitos do Imóvel " + codigoImovel + " efetuado com sucesso.", 
					"Efetuar outro Parcelamento de Débitos",
					"exibirEfetuarParcelamentoDebitosAction.do?menu=sim", 
					"gerarRelatorioParcelamentoAction.do", 
					"Imprimir Termo",
					"Imprimir Contas EP",
					"gerarRelatorio2ViaContaAction.do?cobrarTaxaEmissaoConta=N");
		} else {
			FiltroGuiaPagamento filtroGuiaPagamento = new FiltroGuiaPagamento();
			filtroGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroGuiaPagamento.PARCELAMENTO_ID, new Integer(idParcelamento)));

			Collection<GuiaPagamento> collectionGuiaPagamento = fachada.pesquisar(filtroGuiaPagamento, GuiaPagamento.class.getName());
			sessao.setAttribute("usuarioLogado", usuarioLogado);
			String idGuiaPagamento = "";
			
			if (collectionGuiaPagamento != null && !collectionGuiaPagamento.isEmpty()) {
				GuiaPagamento guiaPagamento = (GuiaPagamento) Util.retonarObjetoDeColecao(collectionGuiaPagamento);
				idGuiaPagamento = "" + guiaPagamento.getId();

				if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
					montarPaginaSucesso(request,
							"Parcelamento de Débitos do Imóvel " + codigoImovel + " efetuado com sucesso.", 
							"Efetuar outro Parcelamento de Débitos",
							"exibirEfetuarParcelamentoDebitosAction.do?menu=sim", 
							"gerarRelatorioParcelamentoAction.do", 
							"Imprimir Termo", 
							"Imprimir Guia Pagto Entrada",
							"gerarRelatorioEmitirGuiaPagamentoActionInserir.do?idGuiaPagamento=" + idGuiaPagamento);
				}

			} else if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
				montarPaginaSucesso(request, 
						"Parcelamento de Débitos do Imóvel " + codigoImovel + " efetuado com sucesso.", 
						"Efetuar outro Parcelamento de Débitos",
						"exibirEfetuarParcelamentoDebitosAction.do?menu=sim", 
						"gerarRelatorioParcelamentoAction.do", 
						"Imprimir Termo");
			}
		}

		return retorno;
	}

	public void verificarValorEntradaMinimaPermitida(Short numeroPrestacoesSelecionada, BigDecimal valorEntrada, BigDecimal valorDebitoTotalAtualizado, ParcelamentoPerfil parcelamentoPerfil) {
		// caso o indicador de entrada mínima seja SIM
		if (parcelamentoPerfil.getIndicadorEntradaMinima() != null && parcelamentoPerfil.getIndicadorEntradaMinima().equals(ConstantesSistema.SIM)) {

			BigDecimal valorPrestacao = Util.dividirArredondando(valorDebitoTotalAtualizado, new BigDecimal(numeroPrestacoesSelecionada));

			valorPrestacao = valorPrestacao.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);

			// o sistema verificará se a entrada informada está menor que o valor da prestação calculada da opção de qtde de parcelas selecionada
			if (valorPrestacao.compareTo(valorEntrada) == 1) {
				throw new ActionServletException("atencao.valor.entrada.menor.possivel");
			}
		}
	}
}
