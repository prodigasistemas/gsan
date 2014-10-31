package gcom.gui.cobranca;

import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
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
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/**
 * Permite efetuar o parcelamento dos débitos de um imóvel
 * 
 * UC0214] - Efetuar Parcelamento de Débitos
 * 
 * Pré-processamento da segunda página
 * 
 * @author Roberta Costa
 * @date 11/02/2006
 */
public class ExibirEfetuarParcelamentoDebitosProcesso2Action extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("processo2");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		DynaActionForm efetuarParcelamentoDebitosActionForm = (DynaActionForm) actionForm;
		
		// Pega dados do formulário
		String codigoImovel = (String)(efetuarParcelamentoDebitosActionForm
			.get("matriculaImovel"));
		String dataParcelamento = (String)(efetuarParcelamentoDebitosActionForm
			.get("dataParcelamento"));
		String resolucaoDiretoria = (String)(efetuarParcelamentoDebitosActionForm
			.get("resolucaoDiretoria"));
		String fimIntervaloParcelamento = (String)efetuarParcelamentoDebitosActionForm
			.get("fimIntervaloParcelamento");
		String inicioIntervaloParcelamento = (String)efetuarParcelamentoDebitosActionForm
			.get("inicioIntervaloParcelamento");
		String indicadorContasRevisao = (String) efetuarParcelamentoDebitosActionForm
			.get("indicadorContasRevisao");
		String indicadorGuiasPagamento = (String) efetuarParcelamentoDebitosActionForm
			.get("indicadorGuiasPagamento");
		String indicadorAcrescimosImpotualidade = (String) efetuarParcelamentoDebitosActionForm
			.get("indicadorAcrescimosImpotualidade");
		String indicadorDebitosACobrar = (String) efetuarParcelamentoDebitosActionForm
			.get("indicadorDebitosACobrar");
		String indicadorCreditoARealizar = (String) efetuarParcelamentoDebitosActionForm
			.get("indicadorCreditoARealizar");
		String indicadorDividaAtiva = (String) efetuarParcelamentoDebitosActionForm
			.get("indicadorDividaAtiva");
		
		Boolean indicadorContas = true;
		//se o intervalo de parcelamento estiver igual a null
		//não se deve levar em consideração no parcelamento a coleão de contas 
		if ((inicioIntervaloParcelamento == null || inicioIntervaloParcelamento.equals(""))
				&& (fimIntervaloParcelamento == null || fimIntervaloParcelamento.equals(""))){
			indicadorContas = false;
		}
		
		// Atualiza os valores da primeira aba na seção caso seja confirmado a alteração
		// de alguma informação do primeiro formulário
		if( httpServletRequest.getParameter("confirmado") != null
	        		&& httpServletRequest.getParameter("confirmado").equals("ok") ){
	        sessao.setAttribute("codigoImovelEscolhida", codigoImovel);
			sessao.setAttribute("dataParcelamentoEscolhida", dataParcelamento);
			sessao.setAttribute("resolucaoDiretoriaEscolhida", resolucaoDiretoria);
			sessao.setAttribute("fimIntervaloParcelamentoEscolhida", fimIntervaloParcelamento);
			sessao.setAttribute("inicioIntervaloParcelamentoEscolhida", inicioIntervaloParcelamento); 
			sessao.setAttribute("indicadorContasRevisaoEscolhida", indicadorContasRevisao);
			sessao.setAttribute("indicadorGuiasPagamentoEscolhida", indicadorGuiasPagamento);
			sessao.setAttribute("indicadorAcrescimosImpotualidadeEscolhida", indicadorAcrescimosImpotualidade);
			sessao.setAttribute("indicadorDebitosACobrarEscolhida", indicadorDebitosACobrar); 
			sessao.setAttribute("indicadorCreditoARealizarEscolhida", indicadorCreditoARealizar);
			sessao.setAttribute("indicadorDividaAtivaEscolhida", indicadorDividaAtiva);

	        // Limpa a sessão
	        sessao.removeAttribute("calcula");
	        sessao.removeAttribute("colecaoContaValores");
	        sessao.removeAttribute("colecaoGuiaPagamentoValores");
	        sessao.removeAttribute("opcoesParcelamento");
	        sessao.removeAttribute("colecaoOpcoesParcelamento");
	        sessao.removeAttribute("valorDebitoTotalAtualizado");
	        sessao.removeAttribute("valorTotalContaValores");
	        sessao.removeAttribute("valorAcrescimosImpontualidade");
	        sessao.removeAttribute("idsContaEP");
	        sessao.removeAttribute("colecaoCreditoARealizar");
	        
	        sessao.removeAttribute("colecaoContasEmAntiguidade");
        }
		
		// Verifica de onde está vindo a chamada  para fazer o cálculo das contas EP e NB
		// Se é do botão do Calcular ou do Avançar ou Aba
		String calcula = null;
		String verificaCalcula = null;
		if (httpServletRequest.getParameter("calcula") != null
				&& !httpServletRequest.getParameter("calcula").equals("")) {
			calcula = httpServletRequest.getParameter("calcula");
			verificaCalcula = "request";
		}else if (sessao.getAttribute("calcula")!= null 
				&& !sessao.getAttribute("calcula").equals("")){
			if(httpServletRequest.getParameter("limpaCombo")== null 
					|| httpServletRequest.getParameter("limpaCombo").equals("")){
				 calcula = (String) sessao.getAttribute("calcula");
				 verificaCalcula = "session";
			}
		}

		if( calcula == null){
			if (codigoImovel != null && !codigoImovel.trim().equals("")) {
				String confirmado = httpServletRequest
						.getParameter("confirmado");
	
				if (confirmado != null
						&& confirmado.trim().equalsIgnoreCase("ok")) {
			        // Limpa o formulário
			        efetuarParcelamentoDebitosActionForm.reset(actionMapping,
							httpServletRequest);
			        
			        // Limpa os botões de rádio da EP e NB da lista de contas
			        if( sessao.getAttribute("colecaoContaValores") != null
			        		&& !sessao.getAttribute("colecaoContaValores").equals("")){
				        Collection colecaoContas = (Collection)sessao.getAttribute("colecaoContaValores");
						Iterator colecaoContasIterator = colecaoContas.iterator();
						colecaoContasIterator = colecaoContas.iterator();
						while(colecaoContasIterator.hasNext()){
							ContaValoresHelper contaValoresHelper = (ContaValoresHelper)colecaoContasIterator.next();
							contaValoresHelper.setIndicadorContasDebito(null);
						}
			        }
			        
					// Limpa a opção de parcelamento
			        if( sessao.getAttribute("colecaoContaValoresNegociacao") != null
			        		&& !sessao.getAttribute("colecaoContaValoresNegociacao").equals("")){
				        Collection colecaoContaValoresNegociacao = (Collection)
				        	sessao.getAttribute("colecaoContaValoresNegociacao");
						Iterator contaValores = colecaoContaValoresNegociacao.iterator();
						while(contaValores.hasNext()) {
							ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
							if( contaValoresHelper.getIndicadorContasDebito() != null
									&& contaValoresHelper.getIndicadorContasDebito().equals(1) ){
								contaValoresHelper.setIndicadorContasDebito(null);
							}
						}
			        }	
	
			        // Limpando a sessão
					sessao.removeAttribute("colecaoGuiaPagamentoValores");
			        sessao.removeAttribute("colecaoDebitoACobrar");
			        sessao.removeAttribute("colecaoCreditoARealizar");
			        sessao.removeAttribute("calcula");
			        sessao.removeAttribute("colecaoContaValores");
			        sessao.removeAttribute("colecaoOpcoesParcelamento");
			        
			        sessao.removeAttribute("colecaoContasEmAntiguidade");
				}
				
				//Caso o periodo inicial do intervalo do parcelamento não seja informado
				if (inicioIntervaloParcelamento == null || inicioIntervaloParcelamento.equals("")){
					inicioIntervaloParcelamento = "01/0001";
				}
				
				// Caso o periodo final do intervalo do parcelamento não seja informado
				if( fimIntervaloParcelamento == null || fimIntervaloParcelamento.equals("")){
					fimIntervaloParcelamento = "12/9999";
				}
				
				// Obter todo o débito do imóvel para exibição
				ObterDebitoImovelOuClienteHelper colecaoDebitoCliente = fachada
						.obterDebitoImovelOuCliente(
								1, // Indicador de débito do imóvel
								codigoImovel, // Matrícula do imóvel
								null, // Código do cliente
								null, // Tipo de relação cliente imóvel
								Util.formatarMesAnoParaAnoMesSemBarra(
										inicioIntervaloParcelamento), // Referência inicial do débito
								Util.formatarMesAnoParaAnoMesSemBarra(
									fimIntervaloParcelamento), // Fim do débito
								Util.converteStringParaDate(
									"01/01/0001"), // Inicio vencimento
								Util.converteStringParaDate(
									"31/12/9999"), // Fim vencimento
								1, // Indicador de pagamento
								new Integer(indicadorContasRevisao), //  conta em revisão
								new Integer(indicadorDebitosACobrar), // Débito a cobrar
								new Integer(indicadorCreditoARealizar), // crédito a realizar
								1, // Indicador de notas promissórias
								new Integer(indicadorGuiasPagamento), //guias pagamento
								new Integer(indicadorAcrescimosImpotualidade), // acréscimos impontualidade
								indicadorContas, new Integer(indicadorDividaAtiva)); 
				// Para o cálculo do Débito Total Atualizado
				BigDecimal valorTotalContas = 
					new BigDecimal("0.00");
				BigDecimal valorTotalAcrescimoImpontualidade = 
					new BigDecimal("0.00");
				BigDecimal valorTotalRestanteServicosACobrar = 
					new BigDecimal("0.00");
				BigDecimal valorTotalRestanteServicosACobrarCurtoPrazo = 
					new BigDecimal("0.00");
				BigDecimal valorTotalRestanteServicosACobrarLongoPrazo = 
					new BigDecimal("0.00");
				BigDecimal valorTotalRestanteParcelamentosACobrar = 
					new BigDecimal("0.00");
				BigDecimal valorTotalRestanteParcelamentosACobrarCurtoPrazo = 
					new BigDecimal("0.00");
				BigDecimal valorTotalRestanteParcelamentosACobrarLongoPrazo = 
					new BigDecimal("0.00");
				BigDecimal valorTotalGuiasPagamento = 
					new BigDecimal("0.00");
				BigDecimal valorTotalAcrescimoImpontualidadeContas = 
					new BigDecimal("0.00");
				BigDecimal valorTotalAcrescimoImpontualidadeGuias = 
					new BigDecimal("0.00");
				BigDecimal valorCreditoARealizar = 
					new BigDecimal("0.00");
				BigDecimal valorRestanteACobrar = 
					new BigDecimal("0.00");
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
					
					while (contaValores.hasNext()) {
						
						ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
						
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
					
					efetuarParcelamentoDebitosActionForm.set("valorTotalContaValores", Util
					.formatarMoedaReal(valorTotalContas));
	
					sessao.setAttribute("valorTotalContaValores", valorTotalContas);
					
					// Pega os dados do Débito do Cliente
					/*if( sessao.getAttribute("colecaoContaValores") == null ){
						sessao.setAttribute("colecaoContaValores",
								colecaoContaValores);
					}*/
					
					sessao.setAttribute("colecaoContaValores", colecaoContaValores);
				} 
				else {
					
					efetuarParcelamentoDebitosActionForm.set("valorTotalContaValores", "0,00");
					
					sessao.setAttribute("valorTotalContaValores", valorTotalContas);
				}
	
	
				// Guias de Pagamento
				if( indicadorGuiasPagamento.equals("1") ){
					Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = colecaoDebitoCliente
							.getColecaoGuiasPagamentoValores();
					if (colecaoGuiaPagamentoValores != null
						 && !colecaoGuiaPagamentoValores.isEmpty() ){
						Iterator guiaPagamentoValores = colecaoGuiaPagamentoValores
								.iterator();
						while (guiaPagamentoValores.hasNext()) {
							GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) guiaPagamentoValores
									.next();
							valorTotalGuiasPagamento.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorTotalGuiasPagamento = valorTotalGuiasPagamento
									.add(guiaPagamentoValoresHelper
											.getGuiaPagamento()
											.getValorDebito());
	
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
							valorTotalAcrescimoImpontualidadeGuias = valorTotalAcrescimoImpontualidadeGuias
									.add(guiaPagamentoValoresHelper
											.getValorAcrescimosImpontualidade());
						}
						efetuarParcelamentoDebitosActionForm
								.set("valorGuiasPagamento",Util
										.formatarMoedaReal(valorTotalGuiasPagamento));
	
						// Pega as Guias de Pagamento em Débito
						sessao.setAttribute("colecaoGuiaPagamentoValores",
								colecaoGuiaPagamentoValores);
					} else {
						efetuarParcelamentoDebitosActionForm.set(
								"valorGuiasPagamento", "0,00");
					}
				}else{
					efetuarParcelamentoDebitosActionForm.set(
							"valorGuiasPagamento", "0,00");
				}
							
				// Acrescimos por Impontualidade
				BigDecimal retornoSoma = new BigDecimal("0.00");
				if( indicadorAcrescimosImpotualidade.equals("1") ){
					retornoSoma.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeContas);
					retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeGuias);
	
					efetuarParcelamentoDebitosActionForm.set("valorAcrescimosImpontualidade", Util.formatarMoedaReal(retornoSoma));
					sessao.setAttribute("valorAcrescimosImpontualidade", retornoSoma);
					
				}else{
					efetuarParcelamentoDebitosActionForm.set(
						"valorAcrescimosImpontualidade", "0,00");
					sessao.setAttribute("valorAcrescimosImpontualidade", 
							new BigDecimal("0.00"));
				}
	
				efetuarParcelamentoDebitosActionForm.set("valorAtualizacaoMonetaria", Util.formatarMoedaReal(valorAtualizacaoMonetaria));
				efetuarParcelamentoDebitosActionForm.set("valorJurosMora", Util.formatarMoedaReal(valorJurosMora));
				efetuarParcelamentoDebitosActionForm.set("valorMulta", Util.formatarMoedaReal(valorMulta));
				
				// Para o cálculo do Débito Total Atualizado
				valorTotalAcrescimoImpontualidade = retornoSoma;
	
				// Debitos A Cobrar
				if( indicadorDebitosACobrar.equals("1") ){
					//[FS0022]-Verificar existência de juros sobre imóvel
					Collection colecaoDebitoACobrar = colecaoDebitoCliente.getColecaoDebitoACobrar();

					if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
						Iterator debitoACobrarValores = colecaoDebitoACobrar.iterator();
		
						final int indiceCurtoPrazo = 0;
						final int indiceLongoPrazo = 1;
		
						while (debitoACobrarValores.hasNext()) {
							DebitoACobrar debitoACobrar = (DebitoACobrar) debitoACobrarValores
									.next();
							
							//[FS0022]-Verificar existência de juros sobre imóvel
							if(debitoACobrar.getDebitoTipo().getId() != null && 
									!debitoACobrar.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){
								
								// Debitos A Cobrar - Serviço
								if (debitoACobrar.getFinanciamentoTipo().getId()
										.equals(FinanciamentoTipo.SERVICO_NORMAL)) {
									// [SB0001] Obter Valores de Curto e Longo Prazo
									valorRestanteACobrar = debitoACobrar.getValorTotalComBonus();
			
									BigDecimal[] valoresDeCurtoELongoPrazo = fachada
											.obterValorACobrarDeCurtoELongoPrazo(
													debitoACobrar
															.getNumeroPrestacaoDebito(),
													debitoACobrar
															.getNumeroPrestacaoCobradasMaisBonus(),
													valorRestanteACobrar);
									valorTotalRestanteServicosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
									valorTotalRestanteServicosACobrarCurtoPrazo = valorTotalRestanteServicosACobrarCurtoPrazo
											.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
									
									valorTotalRestanteServicosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
									valorTotalRestanteServicosACobrarLongoPrazo = valorTotalRestanteServicosACobrarLongoPrazo
											.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
								}
			
								// Debitos A Cobrar - Parcelamento
								if (debitoACobrar.getFinanciamentoTipo().getId().equals(
										FinanciamentoTipo.PARCELAMENTO_AGUA)
										|| debitoACobrar.getFinanciamentoTipo()
												.getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
										|| debitoACobrar.getFinanciamentoTipo()
												.getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
									// [SB0001] Obter Valores de Curto e Longo Prazo
									valorRestanteACobrar = debitoACobrar.getValorTotalComBonus();
			
									BigDecimal[] valoresDeCurtoELongoPrazo = fachada
											.obterValorACobrarDeCurtoELongoPrazo(
													debitoACobrar
															.getNumeroPrestacaoDebito(),
													debitoACobrar
															.getNumeroPrestacaoCobradasMaisBonus(),
													valorRestanteACobrar);
									valorTotalRestanteParcelamentosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
									valorTotalRestanteParcelamentosACobrarCurtoPrazo = 
										valorTotalRestanteParcelamentosACobrarCurtoPrazo
											.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
									valorTotalRestanteParcelamentosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
									valorTotalRestanteParcelamentosACobrarLongoPrazo = 
										valorTotalRestanteParcelamentosACobrarLongoPrazo
											.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
								}
							}
						}
						sessao.setAttribute("colecaoDebitoACobrar",
								colecaoDebitoACobrar);
		
						// Serviços
						valorTotalRestanteServicosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalRestanteServicosACobrar = valorTotalRestanteServicosACobrarCurtoPrazo
							.add(valorTotalRestanteServicosACobrarLongoPrazo);
						efetuarParcelamentoDebitosActionForm
							.set("valorDebitoACobrarServico", Util
									.formatarMoedaReal(valorTotalRestanteServicosACobrar));
						// Parcelamentos
						valorTotalRestanteParcelamentosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalRestanteParcelamentosACobrar = valorTotalRestanteParcelamentosACobrarCurtoPrazo
							.add(valorTotalRestanteParcelamentosACobrarLongoPrazo);
						efetuarParcelamentoDebitosActionForm
							.set("valorDebitoACobrarParcelamento", Util
								.formatarMoedaReal(valorTotalRestanteParcelamentosACobrar));
					}else{
						efetuarParcelamentoDebitosActionForm.set(
								"valorDebitoACobrarServico", "0,00");
						efetuarParcelamentoDebitosActionForm.set(
								"valorDebitoACobrarParcelamento", "0,00");
						
						// Alterado por Rafael Corrêa
						// Data: 26/08/2009
						efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoLongoPrazo", "0,00");
						efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoCurtoPrazo", "0,00");
						efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoLongoPrazo", "0,00");
						efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoCurtoPrazo", "0,00");
					}
				}else{
					efetuarParcelamentoDebitosActionForm.set(
						"valorDebitoACobrarServico", "0,00");
					efetuarParcelamentoDebitosActionForm.set(
						"valorDebitoACobrarParcelamento", "0,00");
					
					// Alterado por Rafael Corrêa
					// Data: 26/08/2009
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoLongoPrazo", "0,00");
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarServicoCurtoPrazo", "0,00");
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoLongoPrazo", "0,00");
					efetuarParcelamentoDebitosActionForm.set("valorDebitoACobrarParcelamentoCurtoPrazo", "0,00");
				}
				
				// Crédito A Realizar
				if( indicadorCreditoARealizar.equals("1") ){
					Collection<CreditoARealizar> colecaoCreditoARealizar = colecaoDebitoCliente
							.getColecaoCreditoARealizar();
					if (colecaoCreditoARealizar != null
							&& !colecaoCreditoARealizar.isEmpty() ) {
						Iterator creditoARealizarValores = colecaoCreditoARealizar
								.iterator();
						while (creditoARealizarValores.hasNext()) {
							CreditoARealizar creditoARealizar = (CreditoARealizar) creditoARealizarValores
									.next();
							valorCreditoARealizar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
							valorCreditoARealizar = valorCreditoARealizar
									.add(creditoARealizar.getValorTotalComBonus());
						}
						sessao.setAttribute("colecaoCreditoARealizar",colecaoCreditoARealizar);
						efetuarParcelamentoDebitosActionForm.set("valorCreditoARealizar", Util.formatarMoedaReal(valorCreditoARealizar));
					}else{
						efetuarParcelamentoDebitosActionForm.set("valorCreditoARealizar", "0,00");
					}
				}else{
					efetuarParcelamentoDebitosActionForm.set("valorCreditoARealizar", "0,00");
				}	
				
				// Débito Total Atualizado
				BigDecimal debitoTotalAtualizado = new BigDecimal("0.00");
				
				debitoTotalAtualizado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				
				debitoTotalAtualizado = debitoTotalAtualizado
				.add(valorTotalContas);
				
				debitoTotalAtualizado = debitoTotalAtualizado
				.add(valorTotalGuiasPagamento);
				
				debitoTotalAtualizado = debitoTotalAtualizado
				.add(valorTotalAcrescimoImpontualidade);
				
				debitoTotalAtualizado = debitoTotalAtualizado
				.add(valorTotalRestanteServicosACobrar);
				
				debitoTotalAtualizado = debitoTotalAtualizado
				.add(valorTotalRestanteParcelamentosACobrar);
				
				debitoTotalAtualizado = debitoTotalAtualizado
				.subtract(valorCreditoARealizar);
	
				if( debitoTotalAtualizado.compareTo(new BigDecimal("0.00")) == -1
					|| debitoTotalAtualizado.equals(new BigDecimal("0.00")) ){
					
					throw new ActionServletException(
					"atencao.nao.existe.debito.a.parcelar");
				}
	
				efetuarParcelamentoDebitosActionForm.set("valorDebitoTotalAtualizado", Util
				.formatarMoedaReal(debitoTotalAtualizado));
				
				sessao.setAttribute("valorDebitoTotalAtualizado", 
				debitoTotalAtualizado);
	
				// Caso o valor Total do Débito seja negativo não há débitos para parcelar
				if( debitoTotalAtualizado.compareTo(new BigDecimal("0.00")) == -1 ){
					throw new ActionServletException(
							"atencao.imovel.sem.debitos", null, codigoImovel);
				}
	
				// Limpa os botões de rádio da EP e NB da lista de contas
				if(httpServletRequest.getParameter("limpaCombo")!= null && !httpServletRequest.getParameter("limpaCombo").equals("")){
					Collection colecaoContas = (Collection)sessao.getAttribute("colecaoContaValores");
					
					Iterator colecaoContasIterator = colecaoContas.iterator();
					colecaoContasIterator = colecaoContas.iterator();
					while(colecaoContasIterator.hasNext()){
						ContaValoresHelper contaValoresHelper = (ContaValoresHelper)colecaoContasIterator.next();
						contaValoresHelper.setIndicadorContasDebito(null);
					}
				}
			}
		}
		else if( calcula != null && calcula.equals("1") ){
			
			// Pega variáveis da sessão
			BigDecimal valorTotalContaValores = (BigDecimal) sessao
			.getAttribute("valorTotalContaValores");
			
			BigDecimal valorAcrescimosImpontualidade = (BigDecimal) sessao
			.getAttribute("valorAcrescimosImpontualidade");
			
			BigDecimal valorDebitoTotalAtualizado = (BigDecimal) sessao
			.getAttribute("valorDebitoTotalAtualizado");

			// Atribui 1 a calcula na sessão
			sessao.setAttribute("calcula", "1");

			Collection<ContaValoresHelper> colecaoContaValores = (Collection<ContaValoresHelper>) sessao
					.getAttribute("colecaoContaValores");

			BigDecimal valorContaNB = new BigDecimal("0.00");
			BigDecimal valorAcrescimosNB = new BigDecimal("0.00");

			if (colecaoContaValores != null && !colecaoContaValores.isEmpty()) {
				Iterator contaValores = colecaoContaValores.iterator();
				while (contaValores.hasNext()) {
					ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores
							.next();
					if (verificaCalcula != null
							&& verificaCalcula.equals("request")) {
						if (httpServletRequest.getParameter("indicadorContasDebito"
								+contaValoresHelper.getConta().getId().toString()) != null) {
							String indice = httpServletRequest
								.getParameter("indicadorContasDebito"
									+ contaValoresHelper.getConta().getId().toString());
							
							contaValoresHelper
									.setIndicadorContasDebito(new Integer(indice));
							
							// Caso as contas sejam não baixadas(NB)
							if (indice.equals("2")) {
								// Verifica se existe conta em revisão
								if( contaValoresHelper.getConta().getDataRevisao() != null ){
									throw new ActionServletException(
											"atencao.conta.em.revisao");
								}
								valorContaNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorContaNB = valorContaNB
										.add(contaValoresHelper.getConta()
												.getValorTotal());
								if( indicadorAcrescimosImpotualidade.equals("1") ){
									valorAcrescimosNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
									valorAcrescimosNB = valorAcrescimosNB
											.add(contaValoresHelper
													.getValorTotalContaValoresParcelamento());
								}	
							}
						}
					} else {
						if (contaValoresHelper.getIndicadorContasDebito() != null) {
							if (contaValoresHelper.getIndicadorContasDebito()
									.equals(2)) {
								valorContaNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorContaNB = valorContaNB
									.add(contaValoresHelper.getConta()
											.getValorTotal());
								valorAcrescimosNB.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorAcrescimosNB = valorAcrescimosNB
									.add(contaValoresHelper
											.getValorTotalContaValoresParcelamento());
							}
						}
					}
				}
				valorTotalContaValores.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorTotalContaValores = valorTotalContaValores
						.subtract(valorContaNB);
				if( indicadorAcrescimosImpotualidade.equals("1") ){
					valorAcrescimosImpontualidade.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					valorAcrescimosImpontualidade = valorAcrescimosImpontualidade
							.subtract(valorAcrescimosNB);
				}	

				// Calcula sempre em cima do valor do debito
				valorDebitoTotalAtualizado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorDebitoTotalAtualizado = valorDebitoTotalAtualizado
						.subtract(valorContaNB);
				valorDebitoTotalAtualizado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorDebitoTotalAtualizado = valorDebitoTotalAtualizado
						.subtract(valorAcrescimosNB);
				
				efetuarParcelamentoDebitosActionForm
					.set("valorTotalContaValores",
						Util.formatarMoedaReal(valorTotalContaValores));
				
				efetuarParcelamentoDebitosActionForm
					.set("valorAcrescimosImpontualidade",
						Util.formatarMoedaReal(valorAcrescimosImpontualidade));
				
				if( valorDebitoTotalAtualizado.compareTo(new BigDecimal("0.00")) == -1
						|| valorDebitoTotalAtualizado.equals(new BigDecimal("0.00")) ){
					throw new ActionServletException(
							"atencao.nao.existe.debito.a.parcelar");
				}
				efetuarParcelamentoDebitosActionForm
					.set("valorDebitoTotalAtualizado",
						Util.formatarMoedaReal(valorDebitoTotalAtualizado));
			}
		}
		
		return retorno;
	}
}
