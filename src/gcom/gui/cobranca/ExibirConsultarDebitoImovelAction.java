package gcom.gui.cobranca;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.IndicadoresParcelamentoHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Paiva
 * @created 18 de Janeiro de 2006
 */
public class ExibirConsultarDebitoImovelAction extends GcomAction {
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
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirDebitoImovel");

		// Mudar isso quando tiver esquema de segurança
		 HttpSession sessao = httpServletRequest.getSession(false);

		ConsultarDebitoImovelActionForm consultarDebitoImovelActionForm = (ConsultarDebitoImovelActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		String codigoImovel = (String) consultarDebitoImovelActionForm
				.getCodigoImovel();
		Short tipoRelacao = null;

		if (consultarDebitoImovelActionForm.getTipoRelacao() != null) {
			tipoRelacao = new Short(consultarDebitoImovelActionForm
					.getTipoRelacao());
		} else {
			tipoRelacao = null;
		}

		String referenciaInicial;
		String referenciaFinal;
		String dataVencimentoInicial;
		String dataVencimentoFinal;

		// seta os dados das datas digitadas ou informa datas padrão
		// no caso de não ter sido digitado nenhum dado para esses campos
		if (consultarDebitoImovelActionForm.getReferenciaInicial() != null
				&& !consultarDebitoImovelActionForm.getReferenciaInicial()
						.equals("")) {
			referenciaInicial = consultarDebitoImovelActionForm
					.getReferenciaInicial();
		} else {
			referenciaInicial = "01/0001";
		}

		if (consultarDebitoImovelActionForm.getReferenciaFinal() != null
				&& !consultarDebitoImovelActionForm.getReferenciaFinal()
						.equals("")) {
			referenciaFinal = consultarDebitoImovelActionForm
					.getReferenciaFinal();
		} else {
			referenciaFinal = "12/9999";
		}

		if (consultarDebitoImovelActionForm.getDataVencimentoInicial() != null
				&& !consultarDebitoImovelActionForm.getDataVencimentoInicial()
						.equals("")) {
			dataVencimentoInicial = consultarDebitoImovelActionForm
					.getDataVencimentoInicial();
		} else {
			dataVencimentoInicial = "01/01/0001";
		}

		if (consultarDebitoImovelActionForm.getDataVencimentoFinal() != null
				&& !consultarDebitoImovelActionForm.getDataVencimentoFinal()
						.equals("")) {
			dataVencimentoFinal = consultarDebitoImovelActionForm
					.getDataVencimentoFinal();
		} else {
			dataVencimentoFinal = "31/12/9999";
		}

		// Para auxiliar na formatação de uma data
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		String mesInicial = referenciaInicial.substring(0, 2);
		String anoInicial = referenciaInicial.substring(3, referenciaInicial
				.length());
		String anoMesInicial = anoInicial + mesInicial;
		String mesFinal = referenciaFinal.substring(0, 2);
		String anoFinal = referenciaFinal
				.substring(3, referenciaFinal.length());
		String anoMesFinal = anoFinal + mesFinal;

		Date dataVencimentoDebitoI;
		Date dataVencimentoDebitoF;

		try {
			dataVencimentoDebitoI = formatoData.parse(dataVencimentoInicial);
		} catch (ParseException ex) {
			dataVencimentoDebitoI = null;
		}
		try {
			dataVencimentoDebitoF = formatoData.parse(dataVencimentoFinal);
		} catch (ParseException ex) {
			dataVencimentoDebitoF = null;
		}

		// seta valores constantes para chamar o metodo que consulta debitos do
		// imovel
		Integer tipoImovel = new Integer(1);
		Integer indicadorPagamento = new Integer(1);
		Integer indicadorConta = new Integer(1);
		Integer indicadorDebito = new Integer(1);
		Integer indicadorCredito = new Integer(1);
		Integer indicadorNotas = new Integer(1);
		Integer indicadorGuias = new Integer(1);
		Integer indicadorAtualizar = new Integer(1);

		// Obtendo dados do imovel
		if (codigoImovel != null && !codigoImovel.trim().equals("")
				&& Integer.parseInt(codigoImovel) > 0) {

			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, codigoImovel));

			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");

			// pesquisa a coleção do imovel
			Collection<Imovel> colecaoImovel = fachada.pesquisar(filtroImovel,
					Imovel.class.getName());

			if (colecaoImovel != null && !colecaoImovel.isEmpty()) {

				Imovel dadosImovel = (Imovel) ((List) colecaoImovel).get(0);

				// Seta os dados do imovel encontrado no formulario
				if (dadosImovel.getId() != null) {
					consultarDebitoImovelActionForm.setCodigoImovel(""
							+ dadosImovel.getId());
				}
				if (dadosImovel.getInscricaoFormatada() != null) {
					consultarDebitoImovelActionForm.setInscricao(""
							+ dadosImovel.getInscricaoFormatada());
				}
				if (dadosImovel.getLigacaoAguaSituacao() != null) {
					consultarDebitoImovelActionForm.setLigacaoAgua(""
							+ dadosImovel.getLigacaoAguaSituacao()
									.getDescricao());
				}
				if (dadosImovel.getLigacaoEsgotoSituacao() != null) {
					consultarDebitoImovelActionForm.setLigacaoEsgoto(""
							+ dadosImovel.getLigacaoEsgotoSituacao()
									.getDescricao());
				}
				
				if (dadosImovel.getLigacaoAguaSituacao() != null) {
					consultarDebitoImovelActionForm.setLigacaoAguaId(""
							+ dadosImovel.getLigacaoAguaSituacao().getId());
				}
				if (dadosImovel.getLigacaoEsgotoSituacao() != null) {
					consultarDebitoImovelActionForm.setLigacaoEsgotoId(""
							+ dadosImovel.getLigacaoEsgotoSituacao().getId());
				}

				// pesquisa dados da relacao do cliente com o imovel para
				// carregar na tela
				FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel(
						FiltroClienteImovel.CLIENTE_NOME);

				filtroClienteImovel.adicionarParametro(new ParametroSimples(
						FiltroClienteImovel.IMOVEL_ID, codigoImovel));

				filtroClienteImovel.adicionarParametro(new ParametroNulo(
						FiltroClienteImovel.DATA_FIM_RELACAO));

				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("cliente");

				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");

				filtroClienteImovel
						.adicionarCaminhoParaCarregamentoEntidade("clienteRelacaoTipo");

				// Faz a busca dos clientesimoveis
				Collection clienteImoveis = fachada.pesquisar(
						filtroClienteImovel, ClienteImovel.class.getName());

				if (clienteImoveis != null && !clienteImoveis.isEmpty()) {
					// Manda a colecao pelo request
					httpServletRequest.setAttribute("colecaoClienteImovel",
							clienteImoveis);
				}
			} else {

				throw new ActionServletException("atencao.imovel.inexistente");
			}

			String enderecoFormatado = fachada
						.pesquisarEndereco(new Integer(codigoImovel));

			httpServletRequest.setAttribute("enderecoFormatado",
					enderecoFormatado);
		}

		// Obtendo os débitos do imovel
		ObterDebitoImovelOuClienteHelper colecaoDebitoImovel = fachada.obterDebitoImovelOuCliente(tipoImovel.intValue(),
						codigoImovel, null, tipoRelacao, anoMesInicial,
						anoMesFinal, dataVencimentoDebitoI,
						dataVencimentoDebitoF, indicadorPagamento.intValue(),
						indicadorConta.intValue(), indicadorDebito.intValue(),
						indicadorCredito.intValue(), indicadorNotas.intValue(),
						indicadorGuias.intValue(), indicadorAtualizar.intValue(), null);

		Collection<ContaValoresHelper> colecaoContaValores = colecaoDebitoImovel.getColecaoContasValores();
		Collection<ContaValoresHelper> colecaoContaValoresPreteritos = colecaoDebitoImovel.getColecaoContasValoresPreteritos();
		Collection<ContaValoresHelper> colecaoContas = new ArrayList<ContaValoresHelper>();
		colecaoContas.addAll(colecaoContaValores);
		colecaoContas.addAll(colecaoContaValoresPreteritos);

		ContaValoresHelper dadosConta = null;

		BigDecimal valorConta = new BigDecimal("0.00");
		BigDecimal valorAcrescimo = new BigDecimal("0.00");
		BigDecimal valorAgua = new BigDecimal("0.00");
		BigDecimal valorEsgoto = new BigDecimal("0.00");
		BigDecimal valorDebito = new BigDecimal("0.00");
		BigDecimal valorCredito = new BigDecimal("0.00");
		BigDecimal valorImposto = new BigDecimal("0.00");
		BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
		BigDecimal valorJurosMora = new BigDecimal("0.00");
		BigDecimal valorMulta = new BigDecimal("0.00");

		if (colecaoContas != null && !colecaoContas.isEmpty()) {
			java.util.Iterator<ContaValoresHelper> colecaoContaValoresIterator = colecaoContas.iterator();
			
			// percorre a colecao de conta somando o valor para obter um valor total
			while (colecaoContaValoresIterator.hasNext()) {

				dadosConta = (ContaValoresHelper) colecaoContaValoresIterator.next();
				valorConta = valorConta.add(dadosConta.getConta().getValorTotal());
				valorAcrescimo = valorAcrescimo.add(dadosConta.getValorTotalContaValores());
				valorAgua = valorAgua.add(dadosConta.getConta().getValorAgua());
				valorEsgoto = valorEsgoto.add(dadosConta.getConta().getValorEsgoto());
				valorDebito = valorDebito.add(dadosConta.getConta().getDebitos());
				valorCredito = valorCredito.add(dadosConta.getConta().getValorCreditos());
				valorImposto = valorImposto.add(dadosConta.getConta().getValorImposto());
				
				if (dadosConta.getValorAtualizacaoMonetaria() != null && !dadosConta.getValorAtualizacaoMonetaria().equals("")) {
					valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(dadosConta.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
				}
				if (dadosConta.getValorJurosMora() != null	&& !dadosConta.getValorJurosMora().equals("")) {
					valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorJurosMora = valorJurosMora.add(dadosConta.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
				}
				if (dadosConta.getValorMulta() != null && !dadosConta.getValorMulta().equals("")) {
					valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorMulta = valorMulta.add(dadosConta.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
				}
			}
		}

		Collection<DebitoACobrar> colecaoDebitoACobrar = colecaoDebitoImovel.getColecaoDebitoACobrar();

		BigDecimal valorDebitoACobrar = new BigDecimal("0.00");
		DebitoACobrar dadosDebito = null;
		BigDecimal valorRestanteACobrar = new BigDecimal("0.00");
		BigDecimal valorTotalRestanteParcelamentosACobrarCurtoPrazo = new BigDecimal("0.00");
		BigDecimal valorTotalRestanteParcelamentosACobrarLongoPrazo = new BigDecimal("0.00");
		BigDecimal valorTotalRestanteParcelamentosACobrar = new BigDecimal("0.00");
		int indiceCurtoPrazo = 0;
		int indiceLongoPrazo = 1;
		
		if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
			java.util.Iterator<DebitoACobrar> colecaoDebitoACobrarIterator = colecaoDebitoACobrar.iterator();
			// percorre a colecao de debito a cobrar somando o valor para obter um valor total
			while (colecaoDebitoACobrarIterator.hasNext()) {

				dadosDebito = (DebitoACobrar) colecaoDebitoACobrarIterator.next();
				valorDebitoACobrar = valorDebitoACobrar.add(dadosDebito.getValorTotalComBonus());
				
				//Debitos A Cobrar - Parcelamento
				if (dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_AGUA)
						|| dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
						|| dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
					// [SB0001] Obter Valores de Curto e Longo Prazo
					valorRestanteACobrar = dadosDebito.getValorTotalComBonus();

					BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(
							dadosDebito.getNumeroPrestacaoDebito(),	
							dadosDebito.getNumeroPrestacaoCobradasMaisBonus(),
							valorRestanteACobrar);
					valorTotalRestanteParcelamentosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorTotalRestanteParcelamentosACobrarCurtoPrazo = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
					valorTotalRestanteParcelamentosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorTotalRestanteParcelamentosACobrarLongoPrazo = valorTotalRestanteParcelamentosACobrarLongoPrazo.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
				}
			}
			valorTotalRestanteParcelamentosACobrar = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(valorTotalRestanteParcelamentosACobrarLongoPrazo);
		}
		

		Collection<CreditoARealizar> colecaoCreditoARealizar = colecaoDebitoImovel.getColecaoCreditoARealizar();

		BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
		CreditoARealizar dadosCredito = null;

		if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()) {
			java.util.Iterator<CreditoARealizar> colecaoCreditoARealizarIterator = colecaoCreditoARealizar.iterator();
			// percorre a colecao de credito a realizar somando o valor para obter um valor total
			while (colecaoCreditoARealizarIterator.hasNext()) {

				dadosCredito = (CreditoARealizar) colecaoCreditoARealizarIterator.next();
				valorCreditoARealizar = valorCreditoARealizar.add(dadosCredito.getValorTotalComBonus());
			}
		}

		Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = colecaoDebitoImovel
				.getColecaoGuiasPagamentoValores();

		BigDecimal valorGuiaPagamento = new BigDecimal("0.00");
		GuiaPagamentoValoresHelper dadosGuiaPagamentoValoresHelper = null;

		if (colecaoGuiaPagamentoValores != null
				&& !colecaoGuiaPagamentoValores.isEmpty()) {
			java.util.Iterator<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresHelperIterator = colecaoGuiaPagamentoValores
					.iterator();
			// percorre a colecao de guia de pagamento somando o valor para
			// obter um valor total
			while (colecaoGuiaPagamentoValoresHelperIterator.hasNext()) {

				dadosGuiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiaPagamentoValoresHelperIterator.next();
				valorGuiaPagamento = valorGuiaPagamento
						.add(dadosGuiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
				
				if (dadosGuiaPagamentoValoresHelper.getValorAtualizacaoMonetaria() != null && !dadosGuiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
					valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(dadosGuiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
				}
				if (dadosGuiaPagamentoValoresHelper.getValorJurosMora() != null && !dadosGuiaPagamentoValoresHelper.getValorJurosMora().equals("")) {
					valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorJurosMora = valorJurosMora.add(dadosGuiaPagamentoValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
				}
				if (dadosGuiaPagamentoValoresHelper.getValorMulta() != null	&& !dadosGuiaPagamentoValoresHelper.getValorMulta().equals("")) {
					valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
					valorMulta = valorMulta.add(dadosGuiaPagamentoValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
				}
				
			}
		}

		if ((colecaoContaValores == null || colecaoContaValoresPreteritos == null) && (colecaoDebitoACobrar == null || colecaoDebitoACobrar.isEmpty())
			&& (colecaoCreditoARealizar == null || colecaoCreditoARealizar.isEmpty())
			&& (colecaoGuiaPagamentoValores == null)) {

			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		} else {
			// Manda a colecao pelo request
			httpServletRequest.setAttribute("colecaoContaValores", colecaoContaValores);
			httpServletRequest.setAttribute("colecaoContaValoresPreteritos", colecaoContaValoresPreteritos);
			httpServletRequest.setAttribute("colecaoContas", colecaoContas);

			// Manda a colecao e os valores total de conta pelo request
			httpServletRequest.setAttribute("colecaoDebitoACobrar",	colecaoDebitoACobrar);
			httpServletRequest.setAttribute("valorConta", Util.formatarMoedaReal(valorConta));
			httpServletRequest.setAttribute("valorAcrescimo", Util.formatarMoedaReal(valorAcrescimo));
			httpServletRequest.setAttribute("valorAgua", Util.formatarMoedaReal(valorAgua));
			httpServletRequest.setAttribute("valorEsgoto", Util.formatarMoedaReal(valorEsgoto));
			httpServletRequest.setAttribute("valorDebito", Util.formatarMoedaReal(valorDebito));
			httpServletRequest.setAttribute("valorCredito", Util.formatarMoedaReal(valorCredito));
			httpServletRequest.setAttribute("valorContaAcrescimo", Util.formatarMoedaReal(valorConta.add(valorAcrescimo)));
			httpServletRequest.setAttribute("valorImposto", Util.formatarMoedaReal(valorImposto));

			// Manda a colecao e o valor total de DebitoACobrar pelo request
			httpServletRequest.setAttribute("colecaoDebitoACobrar",colecaoDebitoACobrar);
			httpServletRequest.setAttribute("valorDebitoACobrar", Util.formatarMoedaReal(valorDebitoACobrar));

			// Manda a colecao e o valor total de CreditoARealizar pelo request
			httpServletRequest.setAttribute("colecaoCreditoARealizar",colecaoCreditoARealizar);
			httpServletRequest.setAttribute("valorCreditoARealizar", Util.formatarMoedaReal(valorCreditoARealizar));

			// Manda a colecao e o valor total de GuiaPagamento pelo request
			httpServletRequest.setAttribute("colecaoGuiaPagamentoValores", colecaoGuiaPagamentoValores);
			httpServletRequest.setAttribute("valorGuiaPagamento", Util.formatarMoedaReal(valorGuiaPagamento));

			// Soma o valor total dos debitos e subtrai dos creditos
			BigDecimal valorTotalSemAcrescimo = valorConta.add(valorDebitoACobrar);
			valorTotalSemAcrescimo = valorTotalSemAcrescimo.add(valorGuiaPagamento);
			valorTotalSemAcrescimo = valorTotalSemAcrescimo.subtract(valorCreditoARealizar);

			BigDecimal valorTotalComAcrescimo = valorTotalSemAcrescimo.add(valorAcrescimo);

			httpServletRequest.setAttribute("valorTotalSemAcrescimo", Util.formatarMoedaReal(valorTotalSemAcrescimo));
			httpServletRequest.setAttribute("valorTotalComAcrescimo", Util.formatarMoedaReal(valorTotalComAcrescimo));
			
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			Short indicadorContasRevisao = 2; 
			if(sistemaParametro.getResolucaoDiretoria() != null){
				Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
				ImovelPerfil imovelPerfil = fachada.obterImovelPerfil(new Integer(codigoImovel)); 
				Short numeroReparcelamentoConsecutivos = fachada.consultarNumeroReparcelamentoConsecutivosImovel(new Integer(codigoImovel));
				indicadorContasRevisao = 1;
				if(numeroReparcelamentoConsecutivos == null){
					numeroReparcelamentoConsecutivos = new Short("0");
				}
				
				IndicadoresParcelamentoHelper indicadoresParcelamentoHelper = 
					new IndicadoresParcelamentoHelper();
				
				indicadoresParcelamentoHelper.setIndicadorDebitosACobrar(new Integer("1"));
				indicadoresParcelamentoHelper.setIndicadorCreditoARealizar(new Integer("1"));
				indicadoresParcelamentoHelper.setIndicadorGuiasPagamento(new Integer("1"));
				indicadoresParcelamentoHelper.setIndicadorAcrescimosImpotualidade(new Integer("1"));
				indicadoresParcelamentoHelper.setIndicadorContasRevisao(new Integer("1"));
				indicadoresParcelamentoHelper.setIndicadorDividaAtiva(new Integer("3"));
				
				//CARREGANDO O HELPER COM AS INFORMAÇÕES DO PARCELAMENTO
				ObterOpcoesDeParcelamentoHelper helper = new ObterOpcoesDeParcelamentoHelper(
						sistemaParametro.getResolucaoDiretoria().getId(), 
						new Integer(codigoImovel), 
						new BigDecimal("0.00"), 
						new Integer(consultarDebitoImovelActionForm.getLigacaoAguaId()), 
						new Integer(consultarDebitoImovelActionForm.getLigacaoEsgotoId()), 
						imovelPerfil.getId(), 
						"01/0001", 
						new Integer("2"),//indicador de restabelecimento 
						colecaoContas, 
						valorTotalComAcrescimo, 
						valorMulta, 
						valorJurosMora, 
						valorAtualizacaoMonetaria, 
						new Integer(numeroReparcelamentoConsecutivos.toString()), 
						colecaoGuiaPagamentoValores, 
						usuarioLogado, 
						valorTotalRestanteParcelamentosACobrar, 
						Util.formatarMesAnoComBarraParaAnoMes("01/0001"),
						Util.formatarMesAnoComBarraParaAnoMes("12/9999"), 
						indicadoresParcelamentoHelper,
						valorCreditoARealizar);
				
				NegociacaoOpcoesParcelamentoHelper negociacaoOpcoesParcelamentoHelper = 
					fachada.calcularValorDosDescontosPagamentoAVista(helper);
				
				BigDecimal valorTotalDescontoPagamentoAVista = negociacaoOpcoesParcelamentoHelper.getValorTotalDescontoPagamentoAVista();
				BigDecimal valorPagamentoAVista = valorTotalComAcrescimo.subtract(valorTotalDescontoPagamentoAVista);
//				
//				consultarDebitoImovelActionForm.setValorDescontoPagamentoAVista(Util.formatarMoedaReal(valorTotalDescontoPagamentoAVista));
//				consultarDebitoImovelActionForm.setValorPagamentoAVista(Util.formatarMoedaReal(valorPagamentoAVista));
//				
				httpServletRequest.setAttribute("valorTotalDescontoPagamentoAVista", Util.formatarMoedaReal(valorTotalDescontoPagamentoAVista));
				httpServletRequest.setAttribute("valorPagamentoAVistaConsultarDebitos", Util.formatarMoedaReal(valorPagamentoAVista));
				
				//seta na sessão para emitir o extrato de débito
				sessao.setAttribute("colecaoContaValores",colecaoContaValores);
				//seta sessão para consulta de imovel copia pois os mesmos estavam sendo removidos da sessão mas prudente duplicar
				sessao.setAttribute("colecaoContasImovel",colecaoContaValores);
				sessao.setAttribute("colecaoDebitoACobrarConsultar",	colecaoDebitoACobrar);
				sessao.setAttribute("colecaoCreditoARealizarConsultar",colecaoCreditoARealizar);
				sessao.setAttribute("colecaoGuiaPagamentoValoresConsultar", colecaoGuiaPagamentoValores);
				sessao.setAttribute("idImovelConsultar", codigoImovel);
				
				sessao.setAttribute("colecaoContaValoresPreteritos",colecaoContaValoresPreteritos);
				sessao.setAttribute("colecaoContas",colecaoContas);
				
				sessao.setAttribute("colecaoDebitoACobrar",	colecaoDebitoACobrar);
				sessao.setAttribute("colecaoCreditoARealizar",colecaoCreditoARealizar);
				sessao.setAttribute("colecaoGuiaPagamentoValores", colecaoGuiaPagamentoValores);

				sessao.setAttribute("valorAcrescimo", valorAcrescimo);
				sessao.setAttribute("valorTotalDescontoPagamentoAVista",valorTotalDescontoPagamentoAVista);
				sessao.setAttribute("valorPagamentoAVistaConsultarDebitos", valorPagamentoAVista);
				sessao.setAttribute("valorCreditoARealizar", valorCreditoARealizar);
				sessao.setAttribute("idImovel", codigoImovel);
			}else{
				//seta na sessão para emitir o extrato de débito
				sessao.setAttribute("colecaoContaValores",colecaoContaValores);
				
				//seta sessão para consulta de imovel copia pois os mesmos estavam sendo removidos da sessão mas prudente duplicar
				sessao.setAttribute("colecaoContasImovel",colecaoContaValores);
				sessao.setAttribute("colecaoDebitoACobrarConsultar",	colecaoDebitoACobrar);
				sessao.setAttribute("colecaoCreditoARealizarConsultar",colecaoCreditoARealizar);
				sessao.setAttribute("colecaoGuiaPagamentoValoresConsultar", colecaoGuiaPagamentoValores);
				sessao.setAttribute("idImovelConsultar", codigoImovel);
				
				sessao.setAttribute("colecaoContaValoresPreteritos",colecaoContaValoresPreteritos);
				sessao.setAttribute("colecaoContas",colecaoContas);
				
				sessao.setAttribute("colecaoDebitoACobrar",	colecaoDebitoACobrar);
				sessao.setAttribute("colecaoCreditoARealizar",colecaoCreditoARealizar);
				sessao.setAttribute("colecaoGuiaPagamentoValores", colecaoGuiaPagamentoValores);

				sessao.setAttribute("valorPagamentoAVistaConsultarDebitos", valorTotalSemAcrescimo);
				sessao.setAttribute("valorCreditoARealizar", valorCreditoARealizar);
				sessao.setAttribute("idImovel", codigoImovel);
				
			}
			sessao.setAttribute("indicadorContasRevisao",indicadorContasRevisao);
		}
		
		Short indicadorEmissaoExtratoNaConsulta = fachada.pesquisarParametrosDoSistema().getIndicadorEmissaoExtratoNaConsulta();
		consultarDebitoImovelActionForm.setIndicadorEmissaoExtratoNaConsulta(indicadorEmissaoExtratoNaConsulta.toString());
		String[] contasSelecionadas = {};
		consultarDebitoImovelActionForm.setContasSelecionadas(contasSelecionadas);
		
		if (httpServletRequest.getAttribute("caminhoRetornoTelaConsultaDebito") != null) {

			httpServletRequest.setAttribute("caminhoRetornoTelaConsultaDebito",
							httpServletRequest.getAttribute("caminhoRetornoTelaConsultaDebito"));
		}

		return retorno;
	}
}
