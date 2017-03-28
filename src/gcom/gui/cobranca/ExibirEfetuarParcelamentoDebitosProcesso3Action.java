package gcom.gui.cobranca;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.IndicadoresParcelamentoHelper;
import gcom.cobranca.bean.NegociacaoOpcoesParcelamentoHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.bean.ObterOpcoesDeParcelamentoHelper;
import gcom.cobranca.bean.OpcoesParcelamentoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.jboss.logging.Logger;

public class ExibirEfetuarParcelamentoDebitosProcesso3Action extends GcomAction {

	private static Logger logger = Logger.getLogger(ExibirEfetuarParcelamentoDebitosProcesso3Action.class);
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("processo3");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		
		DynaActionForm form = (DynaActionForm) actionForm;

		// Faz os cálculos quando a entrada for modificada
		String calculaOpcaoParcelamento = null;
		if( httpServletRequest.getParameter("calculaOpcaoParcelamento") != null ){
			calculaOpcaoParcelamento = httpServletRequest.getParameter("calculaOpcaoParcelamento");
		}else if(sessao.getAttribute("calculaOpcaoParcelamento") != null ){
			calculaOpcaoParcelamento = (String) sessao.getAttribute("calculaOpcaoParcelamento");
		}
		
		// Pega variáveis do formulário
		String codigoImovel = (String) form.get("matriculaImovel");
		String codigoImovelAntes = (String) form.get("codigoImovelAntes");
		Integer situacaoAguaId = new Integer( (String) form.get("situacaoAguaId"));
		Integer situacaoEsgotoId = new Integer( (String) form.get("situacaoEsgotoId"));
		Integer perfilImovel = new Integer( (String) form.get("perfilImovel"));
		Integer numeroReparcelamentoConsecutivos = new Integer( (String) form.get("numeroReparcelamentoConsecutivos"));
		String dataParcelamento = (String)(form.get("dataParcelamento"));
		String resolucaoDiretoria = (String) form.get("resolucaoDiretoria");
		String inicioIntervaloParcelamento = (String) form.get("inicioIntervaloParcelamento");
		String fimIntervaloParcelamento = (String)form.get("fimIntervaloParcelamento");
		String indicadorContasRevisao = (String) form.get("indicadorContasRevisao");
		String indicadorGuiasPagamento = (String) form.get("indicadorGuiasPagamento");
		String indicadorAcrescimosImpotualidade = (String) form.get("indicadorAcrescimosImpotualidade");
		String indicadorDebitosACobrar = (String) form.get("indicadorDebitosACobrar");
		String indicadorCreditoARealizar = (String) form.get("indicadorCreditoARealizar");
		String valorDebitoACobrarParcelamentoImovel =( (String)form.get("valorDebitoACobrarParcelamentoImovel"));
		String indicadorDividaAtiva = (String) form.get("indicadorDividaAtiva");
		
		logger.info("Parcelamento do imóvel " + codigoImovel);
		
		BigDecimal valorDebitoACobrarParcelamentoImovelBigDecimal = new BigDecimal("0.00");
		
		String bloqueiaIntervaloParcelamento = (String) sessao.getAttribute("bloqueiaIntervaloParcelamento");
		
		Integer fimIntervaloParcelamentoFormatado = null;
		
		Integer inicioIntervaloParcelamentoFormatado = null;
		
		if (bloqueiaIntervaloParcelamento != null 
				&& bloqueiaIntervaloParcelamento.equalsIgnoreCase("nao")){	
			fimIntervaloParcelamentoFormatado = Util.formatarMesAnoComBarraParaAnoMes(fimIntervaloParcelamento);
			inicioIntervaloParcelamentoFormatado = Util.formatarMesAnoComBarraParaAnoMes(inicioIntervaloParcelamento);
		}
		
		if (valorDebitoACobrarParcelamentoImovel != null){
			valorDebitoACobrarParcelamentoImovelBigDecimal = Util.formatarMoedaRealparaBigDecimal(valorDebitoACobrarParcelamentoImovel);
		}
			
		IndicadoresParcelamentoHelper indicadoresParcelamentoHelper = new IndicadoresParcelamentoHelper();
		indicadoresParcelamentoHelper.setIndicadorContasRevisao(new Integer(indicadorContasRevisao));
		indicadoresParcelamentoHelper.setIndicadorDebitosACobrar(new Integer(indicadorDebitosACobrar));
		indicadoresParcelamentoHelper.setIndicadorCreditoARealizar(new Integer(indicadorCreditoARealizar));
		indicadoresParcelamentoHelper.setIndicadorGuiasPagamento(new Integer(indicadorGuiasPagamento));
		indicadoresParcelamentoHelper.setIndicadorAcrescimosImpotualidade(new Integer(indicadorAcrescimosImpotualidade));
		indicadoresParcelamentoHelper.setIndicadorDividaAtiva(new Integer(indicadorDividaAtiva));
		
		Boolean indicadorContas = true;
		//se o intervalo de parcelamento estiver igual a null
		//não se deve levar em consideração no parcelamento a coleão de contas 
		if ((inicioIntervaloParcelamento == null || inicioIntervaloParcelamento.equals(""))
				&& (fimIntervaloParcelamento == null || fimIntervaloParcelamento.equals(""))){
			indicadorContas = false;
		}
		
		BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
		
		// Valor Entrada Informado
		BigDecimal valorEntradaInformado = null;
		BigDecimal valorEntradaInformadoAntes = new BigDecimal("0.00");
		if( form.get("valorEntradaInformado") != null 
				&& form.get("valorEntradaInformado").equals("")) {
			valorEntradaInformado = BigDecimal.ZERO;
			form.set("valorEntradaInformado",Util.formatarMoedaReal(valorEntradaInformado));
		}
		
		if( form.get("valorEntradaInformado") != null 
				&& !form.get("valorEntradaInformado").equals("")
					&& !form.get("valorEntradaInformado").equals("0.00")){
			
			if (form.get("valorEntradaInformado").equals("0") ){
				valorEntradaInformado = BigDecimal.ZERO;
				form.set("valorEntradaInformado",Util.formatarMoedaReal(valorEntradaInformado));
			}else{
				valorEntradaInformado = Util.formatarMoedaRealparaBigDecimal((String) form.get("valorEntradaInformado"));
			}
			
		}
		if( form.get("valorEntradaInformadoAntes") != null 
				&& !form.get("valorEntradaInformadoAntes").equals("")
					&& !form.get("valorEntradaInformadoAntes").equals("0.00")){
			valorEntradaInformadoAntes = Util.formatarMoedaRealparaBigDecimal((String) form.get("valorEntradaInformadoAntes"));
		}
		
		// O indicador só será usado caso a situação de Água do Imóvel seja
		// SUPRIMIDO, SUPRIMIDO PARCIAL, SUPRIMIDO PARCIAL A PEDIDO
		Integer indicadorRestabelecimento = new Integer("0");
		if (form.get("indicadorRestabelecimento") != null
				&& !form.get("indicadorRestabelecimento").equals("")) {
			indicadorRestabelecimento = new Integer(String.valueOf(form.get("indicadorRestabelecimento")));
		}

		
		//Caso o periodo inicial do intervalo do parcelamento não seja informado
		if (inicioIntervaloParcelamento == null || inicioIntervaloParcelamento.equals("")){
			inicioIntervaloParcelamento = "01/0001";
		}
		
		// Caso o periodo final do intervalo do parcelamento não seja informado
		if( fimIntervaloParcelamento == null || fimIntervaloParcelamento.equals("")){
			fimIntervaloParcelamento = "12/9999";
		}
				
		// Verifica se a chamada é pela aba 2(colecaoContaValores) ou pela aba 1(colecaoContaValoresImovel)
		Collection<ContaValoresHelper> colecaoContaValoresNegociacao = null;
		Collection<GuiaPagamento> colecaoGuiaPagamento = null;
		BigDecimal valorDebitoTotalAtualizado = new BigDecimal("0.00");
		BigDecimal valorAcrescimosImpontualidade = new BigDecimal("0.00");
		
		if (sessao.getAttribute("colecaoContaValores") != null) {
			colecaoContaValoresNegociacao = (Collection<ContaValoresHelper>) sessao.getAttribute("colecaoContaValores");
			colecaoGuiaPagamento = (Collection<GuiaPagamento>) sessao.getAttribute("colecaoGuiaPagamentoValores");
			valorDebitoTotalAtualizado = BigDecimal.ZERO;
			
			if (!((String) form.get("valorDebitoTotalAtualizado")).equals("")){
				
				valorDebitoTotalAtualizado = Util.formatarMoedaRealparaBigDecimal((String) form.get("valorDebitoTotalAtualizado"));
				
			}
			
			valorAcrescimosImpontualidade = Util.formatarMoedaRealparaBigDecimal((String) form.get("valorAcrescimosImpontualidade"));
			valorCreditoARealizar = Util.formatarMoedaRealparaBigDecimal((String)  form.get("valorCreditoARealizar"));

		} 
		else {
			// Pesquisa os débitos do imóvel
			Object[] debitosImovel = fachada.pesquisarDebitosImovel(codigoImovel, codigoImovelAntes,
					dataParcelamento, resolucaoDiretoria, fimIntervaloParcelamento, 
					inicioIntervaloParcelamento , indicadorContasRevisao, 
					indicadorGuiasPagamento, indicadorAcrescimosImpotualidade,
					indicadorDebitosACobrar, indicadorCreditoARealizar,indicadorContas, indicadorDividaAtiva);
			
			// Valores dos indíces definidos no controlador
			colecaoContaValoresNegociacao = (Collection) debitosImovel[0];
			colecaoGuiaPagamento = (Collection) debitosImovel[2];
			//valorDebitoTotalAtualizado = (BigDecimal) debitosImovel[14];

			//Obter todo o débito do imóvel para exibição na ABA 4(Conclusão)
			//ou para inserir a partir da aba 3 
			ObterDebitoImovelOuClienteHelper colecaoDebitoCliente = fachada.obterDebitoImovelOuCliente(
							1, // Indicador de débito do imóvel
							codigoImovel, // Matrícula do imóvel
							null, // Código do cliente
							null, // Tipo de relação cliente imóvel
							Util.formatarMesAnoParaAnoMesSemBarra(inicioIntervaloParcelamento), // Referência inicial do débito
							Util.formatarMesAnoParaAnoMesSemBarra(fimIntervaloParcelamento), // Fim do débito
							Util.converteStringParaDate("01/01/0001"), // Inicio vencimento
							Util.converteStringParaDate("31/12/9999"), // Fim vencimento
							1, // Indicador de pagamento
							new Integer(indicadorContasRevisao), //  conta em revisão
							new Integer(indicadorDebitosACobrar), // Débito a cobrar
							new Integer(indicadorCreditoARealizar), // crédito a realizar
							1, // Indicador de notas promissórias
							new Integer(indicadorGuiasPagamento), //guias pagamento
							new Integer(indicadorAcrescimosImpotualidade), // acréscimos impontualidade
							indicadorContas,
							new Integer(indicadorDividaAtiva));

			// Para o cálculo do Débito Total Atualizado
			BigDecimal valorTotalContas = new BigDecimal("0.00");
			BigDecimal valorTotalAcrescimoImpontualidade = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteServicosACobrar = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteServicosACobrarCurtoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteServicosACobrarLongoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteParcelamentosACobrar = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteParcelamentosACobrarCurtoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalRestanteParcelamentosACobrarLongoPrazo = new BigDecimal("0.00");
			BigDecimal valorTotalGuiasPagamento = new BigDecimal("0.00");
			BigDecimal valorTotalAcrescimoImpontualidadeContas = new BigDecimal("0.00");
			BigDecimal valorTotalAcrescimoImpontualidadeGuias = new BigDecimal("0.00");
			BigDecimal valorRestanteACobrar = new BigDecimal("0.00");
			BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
			BigDecimal valorJurosMora = new BigDecimal("0.00");
			BigDecimal valorMulta = new BigDecimal("0.00");
			
			// Dados do Débito do Imóvel - Contas
			Collection<ContaValoresHelper> colecaoContaValores = colecaoDebitoCliente.getColecaoContasValores();
			
			ParcelamentoPerfil parcelamentoPerfil = (ParcelamentoPerfil)sessao.getAttribute("parcelamentoPerfil");
			//[SB0011] Verificar Única Fatura
			fachada.verificarUnicaFatura(colecaoContaValores,parcelamentoPerfil);

			if (colecaoContaValores != null && !colecaoContaValores.isEmpty()) {
				
				int quantidadeMinimaMesesAntiguidade = 0;
				int maiorQuantidadeMinimaMesesAntiguidade = 0;
				Iterator contaValores = colecaoContaValores.iterator();
				ContaValoresHelper contaRemovida = null;
				
				while (contaValores.hasNext()) {
					ContaValoresHelper contaValoresHelper = (ContaValoresHelper)contaValores.next();
					
					if(verificaReferenciaIgualReferencialFaturamento(contaValoresHelper.getConta().getAnoMesReferenciaConta())) {
						contaRemovida = contaValoresHelper;
						continue;
					}
					
					//Colocado por Raphael Rossiter em 04/12/2008
					//=============================================================================================
					Collection<ParcelamentoDescontoAntiguidade> colecaoParcelamentoDescontoAntiguidade = 
					fachada.obterParcelamentoDescontoAntiguidadeParaConta(parcelamentoPerfil,
					contaValoresHelper.getConta());

					ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidadeMaior = new ParcelamentoDescontoAntiguidade();

					// Caso nenhuma ocorrência tenha sido selecionada passar para a próxima conta
					if (colecaoParcelamentoDescontoAntiguidade != null && 
						!colecaoParcelamentoDescontoAntiguidade.isEmpty()) {
						
						Iterator parcelamentoDescontoAntiguidadeValores = colecaoParcelamentoDescontoAntiguidade
						.iterator();

						quantidadeMinimaMesesAntiguidade = 0;
						maiorQuantidadeMinimaMesesAntiguidade = 0;

						// 2.4 Determina o percentual de desconto por antiguidade do débito
						while (parcelamentoDescontoAntiguidadeValores.hasNext()) {
							ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidade = (ParcelamentoDescontoAntiguidade) parcelamentoDescontoAntiguidadeValores
									.next();
							quantidadeMinimaMesesAntiguidade = parcelamentoDescontoAntiguidade
									.getQuantidadeMinimaMesesDebito();
							if (quantidadeMinimaMesesAntiguidade > maiorQuantidadeMinimaMesesAntiguidade) {
								maiorQuantidadeMinimaMesesAntiguidade = quantidadeMinimaMesesAntiguidade;
								parcelamentoDescontoAntiguidadeMaior = parcelamentoDescontoAntiguidade;
							}
						}
						
						/*
						 * Colocado por Raphael Rossiter em 03/12/2008
						 * As contas onde o perfil de parcelamento para desconto de antiguidade estiver com
						 * o motivo de revisão informado NÃO entrarão no parcelamento.
						 */
						valorTotalContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalContas = valorTotalContas.add(contaValoresHelper.getValorTotalConta());
						
						if (contaValoresHelper.getValorAtualizacaoMonetaria() != null && !contaValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(contaValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorJurosMora() != null	&& !contaValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(contaValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorMulta() != null && !contaValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(contaValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}

						// Para cálculo do Acrescimo de Impontualidade
						valorTotalAcrescimoImpontualidadeContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalAcrescimoImpontualidadeContas = valorTotalAcrescimoImpontualidadeContas.add(contaValoresHelper.getValorTotalContaValoresParcelamento());
						
						if (parcelamentoDescontoAntiguidadeMaior.getContaMotivoRevisao() != null){
							
							//CONTA ENTRARÁ EM REVISÃO
							contaValoresHelper.setRevisao(1);
							
						}
					}
					else{
						
						valorTotalContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalContas = valorTotalContas.add(contaValoresHelper.getValorTotalConta());

						if (contaValoresHelper.getValorAtualizacaoMonetaria() != null && !contaValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(contaValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorJurosMora() != null	&& !contaValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(contaValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (contaValoresHelper.getValorMulta() != null && !contaValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(contaValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}

						// Para cálculo do Acrescimo de Impontualidade
						valorTotalAcrescimoImpontualidadeContas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalAcrescimoImpontualidadeContas = valorTotalAcrescimoImpontualidadeContas.add(contaValoresHelper.getValorTotalContaValoresParcelamento());
					}
					//=============================================================================================
				}
				
				form.set("valorTotalContaValores", Util.formatarMoedaReal(valorTotalContas));

				sessao.setAttribute("valorTotalContaValores",valorTotalContas);
				
				// Pega os dados do Débito do Cliente
				if( sessao.getAttribute("colecaoContaValores") == null ){
					sessao.setAttribute("colecaoContaValores", contaRemovida != null ? colecaoContaValores.remove(contaRemovida) : colecaoContaValores);
				}	
			} 
			else {
				
				form.set("valorTotalContaValores", "0,00");
				
				sessao.setAttribute("valorTotalContaValores",valorTotalContas);
			}


			// Guias de Pagamento
			if( indicadorGuiasPagamento.equals("1") ){
				Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = colecaoDebitoCliente.getColecaoGuiasPagamentoValores();
				if (colecaoGuiaPagamentoValores != null && !colecaoGuiaPagamentoValores.isEmpty() ){
					Iterator guiaPagamentoValores = colecaoGuiaPagamentoValores.iterator();
					Collection<GuiaPagamentoValoresHelper> guiasRemovidas = new ArrayList<GuiaPagamentoValoresHelper>();
					
					while (guiaPagamentoValores.hasNext()) {
						GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) guiaPagamentoValores.next();
						
						if(verificaReferenciaIgualReferencialFaturamento(Util.recuperaAnoMesDaData(guiaPagamentoValoresHelper.getGuiaPagamento().getDataEmissao()))) {
							guiasRemovidas.add(guiaPagamentoValoresHelper);
							continue;
						}
						
						valorTotalGuiasPagamento.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalGuiasPagamento = valorTotalGuiasPagamento.add(guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
						
						if (guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria() != null && !guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().equals("")) {
							valorAtualizacaoMonetaria.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorAtualizacaoMonetaria = valorAtualizacaoMonetaria.add(guiaPagamentoValoresHelper.getValorAtualizacaoMonetaria().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (guiaPagamentoValoresHelper.getValorJurosMora() != null && !guiaPagamentoValoresHelper.getValorJurosMora().equals("")) {
							valorJurosMora.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorJurosMora = valorJurosMora.add(guiaPagamentoValoresHelper.getValorJurosMora().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}
						if (guiaPagamentoValoresHelper.getValorMulta() != null	&& !guiaPagamentoValoresHelper.getValorMulta().equals("")) {
							valorMulta.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
							valorMulta = valorMulta.add(guiaPagamentoValoresHelper.getValorMulta().setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO));
						}

						// Para cálculo do Acrescimo de Impontualidade
						valorTotalAcrescimoImpontualidadeGuias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalAcrescimoImpontualidadeGuias = valorTotalAcrescimoImpontualidadeGuias.add(guiaPagamentoValoresHelper.getValorAcrescimosImpontualidade());
					}
					form.set("valorGuiasPagamento",Util.formatarMoedaReal(valorTotalGuiasPagamento));
					
					if(!guiasRemovidas.isEmpty())
						colecaoGuiaPagamentoValores.removeAll(guiasRemovidas);

					// Pega as Guias de Pagamento em Débito
					sessao.setAttribute("colecaoGuiaPagamentoValores", colecaoGuiaPagamentoValores);
				} else {
					form.set("valorGuiasPagamento", "0,00");
				}
			}else{
				form.set("valorGuiasPagamento", "0,00");
			}
			
			// Acrescimos por Impotualidade
			BigDecimal retornoSoma = new BigDecimal("0.00");
			if( indicadorAcrescimosImpotualidade.equals("1") ){
				retornoSoma.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeContas);
				retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeGuias);

				form.set("valorAcrescimosImpontualidade", Util.formatarMoedaReal(retornoSoma));
				sessao.setAttribute("valorAcrescimosImpontualidade", retornoSoma);
			}else{
				form.set("valorAcrescimosImpontualidade", "0,00");
				sessao.setAttribute("valorAcrescimosImpontualidade", new BigDecimal("0.00"));
			}

			form.set("valorAtualizacaoMonetaria", Util.formatarMoedaReal(valorAtualizacaoMonetaria));
			form.set("valorJurosMora", Util.formatarMoedaReal(valorJurosMora));
			form.set("valorMulta", Util.formatarMoedaReal(valorMulta));
			
			// Para o cálculo do Débito Total Atualizado
			valorTotalAcrescimoImpontualidade = retornoSoma;
			valorAcrescimosImpontualidade = valorTotalAcrescimoImpontualidade;

			// Debitos A Cobrar
			if( indicadorDebitosACobrar.equals("1") ){
				//[FS0022]-Verificar existência de juros sobre imóvel
				Collection colecaoDebitoACobrar = colecaoDebitoCliente.getColecaoDebitoACobrar();
				Collection<DebitoACobrar> debitosRemovidos = new ArrayList<DebitoACobrar>();
				
				if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
					Iterator debitoACobrarValores = colecaoDebitoACobrar.iterator();
	
					final int indiceCurtoPrazo = 0;
					final int indiceLongoPrazo = 1;
	
					while (debitoACobrarValores.hasNext()) {
						DebitoACobrar debitoACobrar = (DebitoACobrar) debitoACobrarValores.next();
						
						if(verificaReferenciaIgualReferencialFaturamento(Util.recuperaAnoMesDaData(debitoACobrar.getGeracaoDebito()))) {
							debitosRemovidos.add(debitoACobrar);
							continue;
						}
						
						//[FS0022]-Verificar existência de juros sobre imóvel
						if(debitoACobrar.getDebitoTipo().getId() != null && 
								!debitoACobrar.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){
					
							//Debitos A Cobrar - Serviço
							if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.SERVICO_NORMAL)) {
								// [SB0001] Obter Valores de Curto e Longo Prazo
								valorRestanteACobrar = debitoACobrar.getValorTotalComBonus();
		
								BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(
								debitoACobrar.getNumeroPrestacaoDebito(),debitoACobrar.getNumeroPrestacaoCobradasMaisBonus(),valorRestanteACobrar);
								valorTotalRestanteServicosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalRestanteServicosACobrarCurtoPrazo = valorTotalRestanteServicosACobrarCurtoPrazo.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
								
								valorTotalRestanteServicosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalRestanteServicosACobrarLongoPrazo = valorTotalRestanteServicosACobrarLongoPrazo.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
							}
		
							// Debitos A Cobrar - Parcelamento
							if (debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_AGUA)
								|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
								|| debitoACobrar.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
								// [SB0001] Obter Valores de Curto e Longo Prazo
								valorRestanteACobrar = debitoACobrar.getValorTotalComBonus();
		
								BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorACobrarDeCurtoELongoPrazo(
								debitoACobrar.getNumeroPrestacaoDebito(),debitoACobrar.getNumeroPrestacaoCobradasMaisBonus(),valorRestanteACobrar);
								
								valorTotalRestanteParcelamentosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalRestanteParcelamentosACobrarCurtoPrazo = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
								
								valorTotalRestanteParcelamentosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalRestanteParcelamentosACobrarLongoPrazo = valorTotalRestanteParcelamentosACobrarLongoPrazo.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
							}
		
							
						}
						
						
					}
	
					if(!debitosRemovidos.isEmpty())
						colecaoDebitoACobrar.removeAll(debitosRemovidos);
					
					sessao.setAttribute("colecaoDebitoACobrar",	colecaoDebitoACobrar);
	
					valorTotalRestanteServicosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					valorTotalRestanteServicosACobrar = valorTotalRestanteServicosACobrarCurtoPrazo.add(valorTotalRestanteServicosACobrarLongoPrazo);
					form.set("valorDebitoACobrarServico", Util.formatarMoedaReal(valorTotalRestanteServicosACobrar));
					valorTotalRestanteParcelamentosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					valorTotalRestanteParcelamentosACobrar = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(valorTotalRestanteParcelamentosACobrarLongoPrazo);
					form.set("valorDebitoACobrarParcelamento", Util.formatarMoedaReal(valorTotalRestanteParcelamentosACobrar));
				}else{
					form.set("valorDebitoACobrarServico", "0,00");
					form.set("valorDebitoACobrarParcelamento", "0,00");
					form.set("valorDebitoACobrarServicoLongoPrazo", "0,00");
					form.set("valorDebitoACobrarServicoCurtoPrazo", "0,00");
					form.set("valorDebitoACobrarParcelamentoLongoPrazo", "0,00");
					form.set("valorDebitoACobrarParcelamentoCurtoPrazo", "0,00");
				}
			}else{
				form.set("valorDebitoACobrarServico", "0,00");
				form.set("valorDebitoACobrarParcelamento", "0,00");
				form.set("valorDebitoACobrarServicoLongoPrazo", "0,00");
				form.set("valorDebitoACobrarServicoCurtoPrazo", "0,00");
				form.set("valorDebitoACobrarParcelamentoLongoPrazo", "0,00");
				form.set("valorDebitoACobrarParcelamentoCurtoPrazo", "0,00");
			}
			
			// Crédito A Realizar
			if( indicadorCreditoARealizar.equals("1") ){
				Collection<CreditoARealizar> colecaoCreditoARealizar = colecaoDebitoCliente.getColecaoCreditoARealizar();
				if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty() ) {
					Iterator creditoARealizarValores = colecaoCreditoARealizar.iterator();
					Collection<CreditoARealizar> creditosRemovidos = new ArrayList<CreditoARealizar>();
					
					while (creditoARealizarValores.hasNext()) {
						CreditoARealizar creditoARealizar = (CreditoARealizar) creditoARealizarValores.next();
						
						if(verificaReferenciaIgualReferencialFaturamento(Util.recuperaAnoMesDaData(creditoARealizar.getGeracaoCredito()))) {
							creditosRemovidos.add(creditoARealizar);
							continue;
						}
						valorCreditoARealizar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorCreditoARealizar = valorCreditoARealizar.add(creditoARealizar.getValorTotalComBonus());
					}
					
					if(!creditosRemovidos.isEmpty())
						colecaoCreditoARealizar.removeAll(creditosRemovidos);
					sessao.setAttribute("colecaoCreditoARealizar",colecaoCreditoARealizar);
					form.set("valorCreditoARealizar",Util.formatarMoedaReal(valorCreditoARealizar));
				}else{
					form.set("valorCreditoARealizar", "0,00");
				}
			}else{
				form.set("valorCreditoARealizar", "0,00");
			}	
			
			// Débito Total Atualizado
			valorDebitoTotalAtualizado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
			valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.add(valorTotalContas);
			valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.add(valorTotalGuiasPagamento);
			valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.add(valorTotalAcrescimoImpontualidade);
			valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.add(valorTotalRestanteServicosACobrar);
			valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.add(valorTotalRestanteParcelamentosACobrar);
			valorDebitoTotalAtualizado = valorDebitoTotalAtualizado.subtract(valorCreditoARealizar);
			
		}
		
		// Variáveis
		BigDecimal valorTotalMultas = new BigDecimal("0.00");
		BigDecimal valorTotalJurosMora = new BigDecimal("0.00");
		BigDecimal valorTotalAtualizacoesMonetarias = new BigDecimal("0.00");
		BigDecimal descontoFaixaReferenciaConta = new BigDecimal("0.00");
		BigDecimal descontoAcrescimosImpontualidade = new BigDecimal("0.00");
		BigDecimal descontoAntiguidadeDebito = new BigDecimal("0.00");
		BigDecimal descontoInatividadeLigacaoAgua = new BigDecimal("0.00");
		BigDecimal percentualDescontoAcrescimosImpontualidade = new BigDecimal("0.00");
		BigDecimal percentualDescontoAntiguidadeDebito = new BigDecimal("0.00");
		BigDecimal percentualDescontoInatividadeLigacaoAgua = new BigDecimal("0.00");
		//BigDecimal valorDescontoPagamentoAVista =  new BigDecimal("0.00");
		BigDecimal valorPagamentoAVista = new BigDecimal("0.00");
		ParcelamentoPerfil parcelamentoPerfil = null;
		BigDecimal valorMinimoPrestacao = new BigDecimal("0.00");
		BigDecimal valorTotalImpostosConta = new BigDecimal("0.00");
		BigDecimal descontoSancoesRDEspecial = new BigDecimal("0.00");
		BigDecimal descontoTarifaSocialRDEspecial = new BigDecimal("0.00");
		BigDecimal descontoTotalPagamentoAVista = new BigDecimal("0.00");
		
		// Quando não é pelo botão Calcular
		if ( calculaOpcaoParcelamento == null ){
			// Verifica se alguma EP foi marcada
			boolean marcadaEP = false;
			//boolean marcadaNB = false;
			if (colecaoContaValoresNegociacao != null && !colecaoContaValoresNegociacao.isEmpty()) {
				Iterator contaValoresNegociacao = colecaoContaValoresNegociacao.iterator();
				while (contaValoresNegociacao.hasNext()) {
					ContaValoresHelper contaValoresHelperNegociacao = (ContaValoresHelper) contaValoresNegociacao.next();
					if( contaValoresHelperNegociacao.getIndicadorContasDebito() != null ){
						if( contaValoresHelperNegociacao.getIndicadorContasDebito().equals(new Integer("1")) ){
							marcadaEP = true;
							sessao.removeAttribute("calculaNegociacao");
						}else if( contaValoresHelperNegociacao.getIndicadorContasDebito().equals(new Integer("2")) ){
							//marcadaNB = true;
							sessao.removeAttribute("calculaNegociacao");
						}
					}
				}
			}	
			
			BigDecimal valorEntradaDebitos = new BigDecimal("0.00"); // Valor de Entrada da Aba 2
			if( form.get("valorEntradaInformado") != null
					&& !form.get("valorEntradaInformado").equals("") && !marcadaEP ){
				valorEntradaDebitos = Util.formatarMoedaRealparaBigDecimal((String) form.get("valorEntradaInformado"));
			}else{
				valorEntradaDebitos = (BigDecimal) sessao.getAttribute("valorEntradaMinima");	
			}
			
			// Caso não esteja marcada EP e não tenha sido calculado 
			if( !marcadaEP && sessao.getAttribute("calculaNegociacao") == null ){
				valorEntradaInformado = null; //new BigDecimal("0.00"); 
			// Caso tenha EP marcada e seja valor diferente do anterior	
			}else if( !( valorEntradaDebitos.compareTo(valorEntradaInformadoAntes) == 0) ){
				valorEntradaInformado = (BigDecimal) sessao.getAttribute("valorEntradaMinima");	
			}
			
			if (colecaoContaValoresNegociacao != null && !colecaoContaValoresNegociacao.isEmpty()) {
				Iterator contaValoresNegociacao = colecaoContaValoresNegociacao.iterator();
				while (contaValoresNegociacao.hasNext()) {
					ContaValoresHelper contaValoresHelperNegociacao = (ContaValoresHelper) contaValoresNegociacao.next();

					// Caso tenha vindo da aba 2
					if (sessao.getAttribute("colecaoContaValores") != null) {
						// Caso não tenha marcado a conta
						if (contaValoresHelperNegociacao.getIndicadorContasDebito() == null ){
							if (contaValoresHelperNegociacao.getValorMulta() != null) {
								valorTotalMultas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalMultas = valorTotalMultas.add(contaValoresHelperNegociacao.getValorMulta());
							}
							if (contaValoresHelperNegociacao.getValorJurosMora() != null) {
								valorTotalJurosMora.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalJurosMora = valorTotalJurosMora.add(contaValoresHelperNegociacao.getValorJurosMora());
							}
							if (contaValoresHelperNegociacao.getValorAtualizacaoMonetaria() != null) {
								valorTotalAtualizacoesMonetarias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalAtualizacoesMonetarias = valorTotalAtualizacoesMonetarias
										.add(contaValoresHelperNegociacao.getValorAtualizacaoMonetaria());
							}
						} 
					} else {
						// Caso tenha vindo direto da aba 1
						if (contaValoresHelperNegociacao.getValorMulta() != null) {
							valorTotalMultas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalMultas = valorTotalMultas.add(contaValoresHelperNegociacao.getValorMulta());
						}
						if (contaValoresHelperNegociacao.getValorJurosMora() != null) {
							valorTotalJurosMora.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalJurosMora = valorTotalJurosMora.add(contaValoresHelperNegociacao.getValorJurosMora());
						}
						if (contaValoresHelperNegociacao.getValorAtualizacaoMonetaria() != null) {
							valorTotalAtualizacoesMonetarias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalAtualizacoesMonetarias = valorTotalAtualizacoesMonetarias
									.add(contaValoresHelperNegociacao.getValorAtualizacaoMonetaria());
						}
					}
				}
			} 
			
			// Caso o início do intervalo seja vazio preencher com o padrão da aba 1
			if( inicioIntervaloParcelamento == null || inicioIntervaloParcelamento.equals("") ){
				inicioIntervaloParcelamento = "01/0001";
			}

			
			//[SB0002] - Obter Opções de Parcelamento
			
			//CARREGANDO O HELPER COM AS INFORMAÇÕES DO PARCELAMENTO
			ObterOpcoesDeParcelamentoHelper helper = new ObterOpcoesDeParcelamentoHelper(
			new Integer(resolucaoDiretoria), new Integer(codigoImovel), valorEntradaInformado, situacaoAguaId, 
			situacaoEsgotoId, perfilImovel, inicioIntervaloParcelamento, indicadorRestabelecimento, 
			colecaoContaValoresNegociacao, valorDebitoTotalAtualizado, valorTotalMultas, valorTotalJurosMora, 
			valorTotalAtualizacoesMonetarias, numeroReparcelamentoConsecutivos, colecaoGuiaPagamento, usuario, 
			valorDebitoACobrarParcelamentoImovelBigDecimal, inicioIntervaloParcelamentoFormatado,
			fimIntervaloParcelamentoFormatado, indicadoresParcelamentoHelper,valorCreditoARealizar);
			
			Collection<OpcoesParcelamentoHelper> colecaoOpcoesParcelamento =  null;
			NegociacaoOpcoesParcelamentoHelper opcoesParcelamento =  null;
			
			opcoesParcelamento = fachada.obterOpcoesDeParcelamento(helper);
			colecaoOpcoesParcelamento = opcoesParcelamento.getOpcoesParcelamento();
			
			// Verifica se alguma opção de parcelamento foi marcada
			if( colecaoOpcoesParcelamento != null && ! colecaoOpcoesParcelamento.isEmpty() ){
				Iterator opcoesParcelamentoValores = colecaoOpcoesParcelamento.iterator();
				while(opcoesParcelamentoValores.hasNext()) {
					OpcoesParcelamentoHelper opcoesParcelamentoHelper = (OpcoesParcelamentoHelper) opcoesParcelamentoValores.next();
					if(form.get("indicadorQuantidadeParcelas") != null ){
						if( ((String)form.get("indicadorQuantidadeParcelas")).equals(opcoesParcelamentoHelper.getQuantidadePrestacao().toString()) ){
							opcoesParcelamentoHelper.setIndicadorQuantidadeParcelas(new Short(opcoesParcelamentoHelper.getQuantidadePrestacao().toString()));
						}
					}	
				}
			}
			
			// Colocando na sessão
			sessao.setAttribute("opcoesParcelamento",opcoesParcelamento);
			sessao.setAttribute("colecaoOpcoesParcelamento",colecaoOpcoesParcelamento);
			
			descontoFaixaReferenciaConta = opcoesParcelamento.getValorDescontoFaixaReferenciaConta();
			descontoAcrescimosImpontualidade = opcoesParcelamento.getValorDescontoAcrecismosImpotualidade().setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
			descontoSancoesRDEspecial = opcoesParcelamento.getValorDescontoSancoesRDEspecial();
			descontoTarifaSocialRDEspecial = opcoesParcelamento.getValorDescontoTarifaSocialRDEspecial();
			descontoAntiguidadeDebito = opcoesParcelamento.getValorDescontoAntiguidade();
			descontoInatividadeLigacaoAgua = opcoesParcelamento.getValorDescontoInatividade();
			descontoTotalPagamentoAVista  = opcoesParcelamento.getValorTotalDescontoPagamentoAVista();
			percentualDescontoAcrescimosImpontualidade = opcoesParcelamento.getPercentualDescontoAcrescimosImpontualidade();
			percentualDescontoAntiguidadeDebito = opcoesParcelamento.getPercentualDescontoAntiguidadeDebito();
			percentualDescontoInatividadeLigacaoAgua = opcoesParcelamento.getPercentualDescontoInatividadeLigacaoAgua();
			parcelamentoPerfil = opcoesParcelamento.getParcelamentoPerfil();
			BigDecimal valorEntradaMinima = opcoesParcelamento.getValorEntradaMinima();
			valorMinimoPrestacao = opcoesParcelamento.getValorMinimoPrestacao();
			
			
			// Inicia o valor da Entrada com a Miníma caso não tenha marcardo nenhuma conta
			if( marcadaEP ){
				form.set("valorEntradaInformado", Util.formatarMoedaReal(valorEntradaInformado));
				form.set("valorEntradaInformadoAntes", Util.formatarMoedaReal(valorEntradaInformado));
			}else{
				if( (valorEntradaInformado != null && ! (valorEntradaMinima.compareTo(valorEntradaInformado) == 0))
						&& sessao.getAttribute("calculaNegociacao") != null ){
					valorEntradaMinima = valorEntradaInformado;
				}
				form.set("valorEntradaInformado", Util.formatarMoedaReal(valorEntradaMinima));
				form.set("valorEntradaInformadoAntes", Util.formatarMoedaReal(valorEntradaMinima));
			}
		
		// Caso tenha acionado o botão calcular da aba 3
		}else{
			// Ver uma maneira de guardar o valor da entrada quando acionar o botão calcular
			sessao.setAttribute("calculaNegociacao", "1");
			
			// Verifica se a entrada informada é menor que a mínima 
			if (colecaoContaValoresNegociacao != null && !colecaoContaValoresNegociacao.isEmpty()) {
				Iterator contaValoresNegociacao = colecaoContaValoresNegociacao.iterator();
				while (contaValoresNegociacao.hasNext()) {
					ContaValoresHelper contaValoresHelperNegociacao = (ContaValoresHelper) contaValoresNegociacao.next();
					if (sessao.getAttribute("colecaoContaValores") != null) {
						// Caso não tenha marcado a conta
						if (contaValoresHelperNegociacao.getIndicadorContasDebito() == null) {
							if (contaValoresHelperNegociacao.getValorMulta() != null) {
								valorTotalMultas.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalMultas = valorTotalMultas.add(contaValoresHelperNegociacao.getValorMulta());
							}
							if (contaValoresHelperNegociacao
									.getValorJurosMora() != null) {
								valorTotalJurosMora.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalJurosMora = valorTotalJurosMora.add(contaValoresHelperNegociacao.getValorJurosMora());
							}
							if (contaValoresHelperNegociacao
									.getValorAtualizacaoMonetaria() != null) {
								valorTotalAtualizacoesMonetarias.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorTotalAtualizacoesMonetarias = valorTotalAtualizacoesMonetarias
										.add(contaValoresHelperNegociacao.getValorAtualizacaoMonetaria());
							}
						}
					}
				}
			}

			// Limpando as opções da sessão
			sessao.removeAttribute("opcoesParcelamento");
			sessao.removeAttribute("colecaoOpcoesParcelamento");

			//[SB0002] - Obter Opções de Parcelamento de acordo com a entrada informada
			
			//CARREGANDO O HELPER COM AS INFORMAÇÕES DO PARCELAMENTO
			ObterOpcoesDeParcelamentoHelper helper = new ObterOpcoesDeParcelamentoHelper(
			new Integer(resolucaoDiretoria), new Integer(codigoImovel), valorEntradaInformado, situacaoAguaId, 
			situacaoEsgotoId, perfilImovel, inicioIntervaloParcelamento, indicadorRestabelecimento, 
			colecaoContaValoresNegociacao, valorDebitoTotalAtualizado, valorTotalMultas, valorTotalJurosMora, 
			valorTotalAtualizacoesMonetarias, numeroReparcelamentoConsecutivos, colecaoGuiaPagamento, usuario, 
			valorDebitoACobrarParcelamentoImovelBigDecimal, inicioIntervaloParcelamentoFormatado,
			fimIntervaloParcelamentoFormatado, indicadoresParcelamentoHelper,valorCreditoARealizar);
			
			NegociacaoOpcoesParcelamentoHelper opcoesParcelamento = fachada.obterOpcoesDeParcelamento(helper);
			
			descontoFaixaReferenciaConta = opcoesParcelamento.getValorDescontoFaixaReferenciaConta();
			descontoAcrescimosImpontualidade = opcoesParcelamento.getValorDescontoAcrecismosImpotualidade();
			descontoSancoesRDEspecial = opcoesParcelamento.getValorDescontoSancoesRDEspecial();
			descontoTarifaSocialRDEspecial = opcoesParcelamento.getValorDescontoTarifaSocialRDEspecial();
			descontoAntiguidadeDebito = opcoesParcelamento.getValorDescontoAntiguidade();
			descontoInatividadeLigacaoAgua = opcoesParcelamento.getValorDescontoInatividade();
			descontoTotalPagamentoAVista = opcoesParcelamento.getValorTotalDescontoPagamentoAVista();
			percentualDescontoAcrescimosImpontualidade = opcoesParcelamento.getPercentualDescontoAcrescimosImpontualidade();
			percentualDescontoAntiguidadeDebito = opcoesParcelamento.getPercentualDescontoAntiguidadeDebito();
			percentualDescontoInatividadeLigacaoAgua = opcoesParcelamento.getPercentualDescontoInatividadeLigacaoAgua();
			parcelamentoPerfil = opcoesParcelamento.getParcelamentoPerfil();
			BigDecimal valorEntradaMinima = opcoesParcelamento.getValorEntradaMinima();
			valorMinimoPrestacao = opcoesParcelamento.getValorMinimoPrestacao();
			
			Collection<OpcoesParcelamentoHelper> colecaoOpcoesParcelamento = opcoesParcelamento.getOpcoesParcelamento();
			
			if (valorEntradaInformado != null && valorEntradaInformado.compareTo(
					valorEntradaMinima.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO)) == -1) {
				
				// -----------------------------------------------------------
				// Verificar permissão especial
				boolean temPermissaoValMinimoEntrada = fachada.verificarPermissaoValMinimoEntrada(usuario);
				// -----------------------------------------------------------
				if(!temPermissaoValMinimoEntrada){
					throw new ActionServletException(
							"atencao.valor.entrada.deve.ser.maior.igual.minima", null, Util.formatarMoedaReal(valorEntradaMinima));
				}else{
					valorEntradaMinima = valorEntradaInformado;
				}
				
			}else{
				valorEntradaMinima = valorEntradaInformado;
			}
			
			sessao.setAttribute("opcoesParcelamento",opcoesParcelamento);
			sessao.setAttribute("colecaoOpcoesParcelamento",colecaoOpcoesParcelamento);
			
			// Limpa os EP da Coleção de Contas
			if(colecaoContaValoresNegociacao !=null && !colecaoContaValoresNegociacao.isEmpty()){
				Iterator contaValores = colecaoContaValoresNegociacao.iterator();
				while(contaValores.hasNext()) {
					ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
					if (contaValoresHelper.getIndicadorContasDebito() != null && !contaValoresHelper.getIndicadorContasDebito().equals(new Integer("2"))){
						contaValoresHelper.setIndicadorContasDebito(null);
					}
				}
			}
			
			// Limpando a opção de parcelamento
			if( colecaoOpcoesParcelamento != null && !colecaoOpcoesParcelamento.equals("") ){
				Iterator opcoesParcelamentoValores = colecaoOpcoesParcelamento.iterator();
				while(opcoesParcelamentoValores.hasNext()) {
					OpcoesParcelamentoHelper opcoesParcelamentoHelper = (OpcoesParcelamentoHelper) opcoesParcelamentoValores.next();
					opcoesParcelamentoHelper.setIndicadorQuantidadeParcelas(null);
					form.set("indicadorQuantidadeParcelas", null);
				}
			}
			
			// Atribui ao hidden o valor da entrada digitado
			form.set("valorEntradaInformadoAntes", Util.formatarMoedaReal(valorEntradaInformado));
		}
		
		// Verifica se o valor do débito menos o valor dos descontos é menor que o valor minimo da parcela
		BigDecimal valorTotalDescontos = new BigDecimal("0.00");
		BigDecimal resultadoDiferenca = new BigDecimal("0.00");
		valorTotalDescontos.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		valorTotalDescontos = descontoAcrescimosImpontualidade.add(descontoAntiguidadeDebito);
		valorTotalDescontos = valorTotalDescontos.add(descontoFaixaReferenciaConta);
		valorTotalDescontos = valorTotalDescontos.add(descontoInatividadeLigacaoAgua);
		valorTotalDescontos = valorTotalDescontos.add(descontoSancoesRDEspecial);
		valorTotalDescontos = valorTotalDescontos.add(descontoTarifaSocialRDEspecial);
		
		resultadoDiferenca.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		resultadoDiferenca = valorDebitoTotalAtualizado.subtract(valorTotalDescontos);

		
		if (!fachada.verificarQtdeReparcelamentoPerfil(parcelamentoPerfil.getId(),new Short(numeroReparcelamentoConsecutivos.shortValue()))){
			throw new ActionServletException("atencao.nao.existe.condicao.por.quantidade.reparcelamentos.perfil");
		}
		
		// Coloca os valores no formulário
		form.set("descontoFaixaReferenciaConta", Util.formatarMoedaReal(descontoFaixaReferenciaConta));
		form.set("descontoAcrescimosImpontualidade", Util.formatarMoedaReal(descontoAcrescimosImpontualidade));
		form.set("descontoAntiguidadeDebito",Util.formatarMoedaReal(descontoAntiguidadeDebito));
		form.set("descontoInatividadeLigacaoAgua", Util.formatarMoedaReal(descontoInatividadeLigacaoAgua));
		form.set("percentualDescontoAcrescimosImpontualidade", Util.formatarMoedaReal(percentualDescontoAcrescimosImpontualidade));
		form.set("valorTotalDescontos", Util.formatarMoedaReal(valorTotalDescontos));
		form.set("descontoSancoesRDEspecial",Util.formatarMoedaReal(descontoSancoesRDEspecial));
		form.set("descontoTarifaSocialRDEspecial",Util.formatarMoedaReal(descontoTarifaSocialRDEspecial));
		
		
		valorTotalImpostosConta = obterValorImpostosDasContasDoParcelamento(colecaoContaValoresNegociacao);
		
		valorPagamentoAVista.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
		valorPagamentoAVista = valorDebitoTotalAtualizado.subtract(descontoTotalPagamentoAVista);

		valorPagamentoAVista = valorPagamentoAVista.subtract(valorTotalImpostosConta);
        
		// Colocando os valores no formulário
		form.set("valorDebitoTotalAtualizado",Util.formatarMoedaReal(valorDebitoTotalAtualizado));
		form.set("valorDescontoPagamentoAVista",Util.formatarMoedaReal(descontoTotalPagamentoAVista));
		form.set("valorPagamentoAVista",Util.formatarMoedaReal(valorPagamentoAVista));
		form.set("valorTotalImpostos",Util.formatarMoedaReal(valorTotalImpostosConta));
		
		if (percentualDescontoAntiguidadeDebito != null) {
			form.set("percentualDescontoAntiguidadeDebito", Util.formatarMoedaReal(percentualDescontoAntiguidadeDebito));
		} else {
			form.set("percentualDescontoAntiguidadeDebito", "0.00");
		}

		if (percentualDescontoInatividadeLigacaoAgua != null) {
			form.set("percentualDescontoInatividadeLigacaoAgua", Util
					.formatarMoedaReal(percentualDescontoInatividadeLigacaoAgua));
		} else {
			form.set("percentualDescontoInatividadeLigacaoAgua", "0.00");
		}

		if (parcelamentoPerfil != null) {
			form.set("parcelamentoPerfilId",parcelamentoPerfil.getId().toString());
		} else {
			form.set("parcelamentoPerfilId", "0");
		}
		
		// O valor do débito é menor que o valor da parcela mínima permitida.
		// Utilizar a opção Pagamento à Vista. 
		if (valorDebitoTotalAtualizado.compareTo(valorMinimoPrestacao) == -1){
			httpServletRequest.setAttribute("vlDebitoMenor","1");
		}
		form.set("valorMinimoPrestacao", Util.formatarMoedaReal(valorMinimoPrestacao));
		
		String habilitaPagamentoAVista = "1";
		if (colecaoContaValoresNegociacao == null || colecaoContaValoresNegociacao.isEmpty()) {
			habilitaPagamentoAVista = "2";
		}
		sessao.setAttribute("habilitaPagamentoAVista", habilitaPagamentoAVista);
		
		sessao.setAttribute("colecaoContaValoresNegociacao",colecaoContaValoresNegociacao);
		sessao.setAttribute("colecaoGuiaPagamentoNegociacao", colecaoGuiaPagamento);
		sessao.setAttribute("valorAcrescimosImpontualidadeNegociacao", valorAcrescimosImpontualidade);
		
		
		return retorno;
	}
	

	private BigDecimal obterValorImpostosDasContasDoParcelamento(Collection colecaoContas){
		
		BigDecimal valorTotalImpostos = BigDecimal.ZERO;
		
		if (colecaoContas != null && !colecaoContas.isEmpty()) {
			Iterator contas = colecaoContas.iterator();

			while (contas.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contas.next();
				
				if (contaValoresHelper.getConta().getValorImposto() != null) {
					valorTotalImpostos = valorTotalImpostos.add(contaValoresHelper.getConta().getValorImposto());
				}
			}
		}
		return valorTotalImpostos;
	}
}
