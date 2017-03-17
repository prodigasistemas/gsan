package gcom.gui.cobranca;

import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.cobranca.parcelamento.ParcelamentoDescontoAntiguidade;
import gcom.cobranca.parcelamento.ParcelamentoPerfil;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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

		DynaActionForm form = (DynaActionForm) actionForm;
		
		// Pega dados do formulário
		String codigoImovel = (String)(form.get("matriculaImovel"));
		String dataParcelamento = (String)(form.get("dataParcelamento"));
		String resolucaoDiretoria = (String)(form.get("resolucaoDiretoria"));
		String fimIntervaloParcelamento = (String)form.get("fimIntervaloParcelamento");
		String inicioIntervaloParcelamento = (String)form.get("inicioIntervaloParcelamento");
		String indicadorContasRevisao = (String) form.get("indicadorContasRevisao");
		String indicadorGuiasPagamento = (String) form.get("indicadorGuiasPagamento");
		String indicadorAcrescimosImpotualidade = (String) form.get("indicadorAcrescimosImpotualidade");
		String indicadorDebitosACobrar = (String) form.get("indicadorDebitosACobrar");
		String indicadorCreditoARealizar = (String) form.get("indicadorCreditoARealizar");
		String indicadorDividaAtiva = (String) form.get("indicadorDividaAtiva");
		
		Boolean indicadorContas = true;

		if (semIntervaloParcelamento(fimIntervaloParcelamento, inicioIntervaloParcelamento)){
			indicadorContas = false;
		}
		
		// Atualiza os valores da primeira aba na seção caso seja confirmado a alteração de alguma informação do primeiro formulário
		if( httpServletRequest.getParameter("confirmado") != null && httpServletRequest.getParameter("confirmado").equals("ok") ){
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

	        limparSessao(sessao);
        }
		
		// Verifica de onde está vindo a chamada  para fazer o cálculo das contas EP e NB
		// Se é do botão do Calcular ou do Avançar ou Aba
		String calcula = null;
		String verificaCalcula = null;
		if (httpServletRequest.getParameter("calcula") != null && !httpServletRequest.getParameter("calcula").equals("")) {
			calcula = httpServletRequest.getParameter("calcula");
			verificaCalcula = "request";
		}else if (sessao.getAttribute("calcula")!= null && !sessao.getAttribute("calcula").equals("")){
			if(httpServletRequest.getParameter("limpaCombo")== null || httpServletRequest.getParameter("limpaCombo").equals("")){
				 calcula = (String) sessao.getAttribute("calcula");
				 verificaCalcula = "session";
			}
		}

		if(calcula == null){
			if (codigoImovel != null && !codigoImovel.trim().equals("")) {
				String confirmado = httpServletRequest.getParameter("confirmado");
	
				if (confirmado != null && confirmado.trim().equalsIgnoreCase("ok")) {

					form.reset(actionMapping,httpServletRequest);
			        
			        limparBotoesEPeNB(sessao);
			        limparOpcaoParcelamento(sessao);	
					limparSessao2(sessao);
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
								codigoImovel, 
								null, // Código do cliente
								null, // Tipo de relação cliente imóvel
								Util.formatarMesAnoParaAnoMesSemBarra(inicioIntervaloParcelamento), // Referência inicial do débito
								Util.formatarMesAnoParaAnoMesSemBarra(fimIntervaloParcelamento), // Fim do débito
								Util.converteStringParaDate("01/01/0001"), // Inicio vencimento
								Util.converteStringParaDate("31/12/9999"), // Fim vencimento
								1, // Indicador de pagamento
								new Integer(indicadorContasRevisao), 
								new Integer(indicadorDebitosACobrar), 
								new Integer(indicadorCreditoARealizar), 
								1, // Indicador de notas promissórias
								new Integer(indicadorGuiasPagamento), 
								new Integer(indicadorAcrescimosImpotualidade), 
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
				BigDecimal valorTotalAcrescimoImpontualidadeContas =new BigDecimal("0.00");
				BigDecimal valorTotalAcrescimoImpontualidadeGuias = new BigDecimal("0.00");
				BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
				BigDecimal valorRestanteACobrar = new BigDecimal("0.00");
				BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
				BigDecimal valorJurosMora = new BigDecimal("0.00");
				BigDecimal valorMulta = new BigDecimal("0.00");
				
				BigDecimal valorCreditosAnterioresCurtoPrazo = new BigDecimal("0.00");
				BigDecimal valorCreditosAnterioresLongoPrazo = new BigDecimal("0.00");
				BigDecimal valorTotalCreditosAnteriores = new BigDecimal("0.00");
				
				Collection<ContaValoresHelper> colecaoContaValores = new ArrayList<ContaValoresHelper>();
				
				ParcelamentoPerfil parcelamentoPerfil = (ParcelamentoPerfil)sessao.getAttribute("parcelamentoPerfil");

				fachada.verificarUnicaFatura(colecaoContaValores,parcelamentoPerfil);
				
				if (clientePossuiDebitos(colecaoDebitoCliente)) {
					colecaoContaValores.addAll(colecaoDebitoCliente.getColecaoContasValores());
					colecaoContaValores.addAll(colecaoDebitoCliente.getColecaoContasValoresPreteritos());
					
					int quantidadeMinimaMesesAntiguidade = 0;
					int maiorQuantidadeMinimaMesesAntiguidade = 0;
					Iterator contaValores = colecaoContaValores.iterator();
					ContaValoresHelper contaRemovida = null;
					
					while (contaValores.hasNext()) {
						
						ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
						
						if(verificaReferenciaIgualReferencialFaturamento(contaValoresHelper.getConta().getAnoMesReferenciaConta())) {
							contaRemovida = contaValoresHelper;
							continue;
						}
						
						Collection<ParcelamentoDescontoAntiguidade> parcelamentosDescontoAntiguidade = 
						fachada.obterParcelamentoDescontoAntiguidadeParaConta(parcelamentoPerfil, contaValoresHelper.getConta());

						ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidadeMaior = new ParcelamentoDescontoAntiguidade();

						if (parcelamentosDescontoAntiguidade != null && !parcelamentosDescontoAntiguidade.isEmpty()) {
							
							Iterator parcelamentoDescontoAntiguidadeValores = parcelamentosDescontoAntiguidade.iterator();

							quantidadeMinimaMesesAntiguidade = 0;
							maiorQuantidadeMinimaMesesAntiguidade = 0;

							// 2.4 Determina o percentual de desconto por antiguidade do débito
							while (parcelamentoDescontoAntiguidadeValores.hasNext()) {
								ParcelamentoDescontoAntiguidade parcelamentoDescontoAntiguidade = 
										(ParcelamentoDescontoAntiguidade) parcelamentoDescontoAntiguidadeValores.next();
								
								quantidadeMinimaMesesAntiguidade = parcelamentoDescontoAntiguidade.getQuantidadeMinimaMesesDebito();
								
								if (quantidadeMinimaMesesAntiguidade > maiorQuantidadeMinimaMesesAntiguidade) {
									maiorQuantidadeMinimaMesesAntiguidade = quantidadeMinimaMesesAntiguidade;
									parcelamentoDescontoAntiguidadeMaior = parcelamentoDescontoAntiguidade;
								}
							}
							
							// As contas onde o perfil de parcelamento para desconto de antiguidade estiver com o motivo de revisão informado NÃO entrarão no parcelamento.
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
								contaValoresHelper.setRevisao(1);
							}
						} else{
							
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
					}
					
					form.set("valorTotalContaValores", Util.formatarMoedaReal(valorTotalContas));
	
					sessao.setAttribute("valorTotalContaValores", valorTotalContas);
					
					if (contaRemovida != null) {
			            colecaoContaValores.remove(contaRemovida);
			        } 
					sessao.setAttribute("colecaoContaValores", colecaoContaValores);
				} else {
					form.set("valorTotalContaValores", "0,00");
					sessao.setAttribute("valorTotalContaValores", valorTotalContas);
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

						sessao.setAttribute("colecaoGuiaPagamentoValores", colecaoGuiaPagamentoValores);
					} else {
						form.set("valorGuiasPagamento", "0,00");
					}
				}else{
					form.set("valorGuiasPagamento", "0,00");
				}
							
				// Acrescimos por Impontualidade
				BigDecimal retornoSoma = new BigDecimal("0.00");
				if( indicadorAcrescimosImpotualidade.equals("1") ){
					retornoSoma.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeContas);
					retornoSoma = retornoSoma.add(valorTotalAcrescimoImpontualidadeGuias);
	
					form.set("valorAcrescimosImpontualidade", Util.formatarMoedaReal(retornoSoma));
					sessao.setAttribute("valorAcrescimosImpontualidade", retornoSoma);
					
				}else{
					form.set("valorAcrescimosImpontualidade", "0,00");
					sessao.setAttribute("valorAcrescimosImpontualidade",new BigDecimal("0.00"));
				}
	
				form.set("valorAtualizacaoMonetaria", Util.formatarMoedaReal(valorAtualizacaoMonetaria));
				form.set("valorJurosMora", Util.formatarMoedaReal(valorJurosMora));
				form.set("valorMulta", Util.formatarMoedaReal(valorMulta));
				
				// Para o cálculo do Débito Total Atualizado
				valorTotalAcrescimoImpontualidade = retornoSoma;
	
				final int indiceCurtoPrazo = 0;
				final int indiceLongoPrazo = 1;
				
				// Debitos A Cobrar
				if( indicadorDebitosACobrar.equals("1") ){
					//[FS0022]-Verificar existência de juros sobre imóvel
					Collection colecaoDebitoACobrar = colecaoDebitoCliente.getColecaoDebitoACobrar();

					if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()) {
						Iterator debitoACobrarValores = colecaoDebitoACobrar.iterator();
						Collection<DebitoACobrar> debitosRemovidos = new ArrayList<DebitoACobrar>();
		
						while (debitoACobrarValores.hasNext()) {
							DebitoACobrar debitoACobrar = (DebitoACobrar) debitoACobrarValores
									.next();
							
							if(verificaReferenciaIgualReferencialFaturamento(Util.recuperaAnoMesDaData(debitoACobrar.getGeracaoDebito()))) {
								debitosRemovidos.add(debitoACobrar);
								continue;
							}
							
							//[FS0022]-Verificar existência de juros sobre imóvel
							if(debitoACobrar.getDebitoTipo().getId() != null && 
									!debitoACobrar.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){
								
								// Debitos A Cobrar - Serviço
								if (debitoACobrar.getFinanciamentoTipo().getId()
										.equals(FinanciamentoTipo.SERVICO_NORMAL)) {
									// [SB0001] Obter Valores de Curto e Longo Prazo
									valorRestanteACobrar = debitoACobrar.getValorTotalComBonus();
			
									BigDecimal[] valoresDeCurtoELongoPrazo = fachada
											.obterValorCurtoELongoPrazo(
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
											.obterValorCurtoELongoPrazo(
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
						
						if(!debitosRemovidos.isEmpty())
							colecaoDebitoACobrar.removeAll(debitosRemovidos);
						sessao.setAttribute("colecaoDebitoACobrar", colecaoDebitoACobrar);
		
						// Serviços
						valorTotalRestanteServicosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalRestanteServicosACobrar = valorTotalRestanteServicosACobrarCurtoPrazo
							.add(valorTotalRestanteServicosACobrarLongoPrazo);
						form
							.set("valorDebitoACobrarServico", Util
									.formatarMoedaReal(valorTotalRestanteServicosACobrar));
						// Parcelamentos
						valorTotalRestanteParcelamentosACobrar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalRestanteParcelamentosACobrar = valorTotalRestanteParcelamentosACobrarCurtoPrazo
							.add(valorTotalRestanteParcelamentosACobrarLongoPrazo);
						form
							.set("valorDebitoACobrarParcelamento", Util
								.formatarMoedaReal(valorTotalRestanteParcelamentosACobrar));
					}else{
						form.set(
								"valorDebitoACobrarServico", "0,00");
						form.set(
								"valorDebitoACobrarParcelamento", "0,00");
						
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
				if (indicadorCreditoARealizar.equals("1")) {
					Collection<CreditoARealizar> colecaoCreditoARealizar = colecaoDebitoCliente.getColecaoCreditoARealizar();
					if (colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()) {
						Iterator creditoARealizarValores = colecaoCreditoARealizar.iterator();
						Collection<CreditoARealizar> creditosRemovidos = new ArrayList<CreditoARealizar>();

						while (creditoARealizarValores.hasNext()) {
							CreditoARealizar creditoARealizar = (CreditoARealizar) creditoARealizarValores.next();

							if (verificaReferenciaIgualReferencialFaturamento(Util.recuperaAnoMesDaData(creditoARealizar.getGeracaoCredito()))) {
								creditosRemovidos.add(creditoARealizar);
								continue;
							}

							if (isCreditoDeParcelamento(creditoARealizar)) {
								BigDecimal valorCreditoAnterior = creditoARealizar.getValorNaoConcedido();

								BigDecimal[] valores = fachada.obterValorCurtoELongoPrazo(creditoARealizar.getNumeroPrestacaoCredito(), 
										creditoARealizar.getNumeroPrestacaoRealizada(), valorCreditoAnterior);
								
								valorCreditosAnterioresCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorCreditosAnterioresCurtoPrazo = valorCreditosAnterioresCurtoPrazo.add(valores[indiceCurtoPrazo]);
								
								valorCreditosAnterioresLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorCreditosAnterioresLongoPrazo = valorCreditosAnterioresLongoPrazo.add(valores[indiceLongoPrazo]);
								
								valorTotalCreditosAnteriores = valorTotalCreditosAnteriores.add(valorCreditosAnterioresCurtoPrazo).add(valorCreditosAnterioresLongoPrazo);
							} else {
								valorCreditoARealizar.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
								valorCreditoARealizar = valorCreditoARealizar.add(creditoARealizar.getValorTotalComBonus());
							}
						}

						if (!creditosRemovidos.isEmpty())
							colecaoCreditoARealizar.removeAll(creditosRemovidos);
						sessao.setAttribute("colecaoCreditoARealizar", colecaoCreditoARealizar);
						
						form.set("valorCreditoARealizar", Util.formatarMoedaReal(valorCreditoARealizar));
						form.set("valorCreditosAnterioresCurtoPrazo", Util.formatarMoedaReal(valorCreditosAnterioresCurtoPrazo));
						form.set("valorCreditosAnterioresLongoPrazo", Util.formatarMoedaReal(valorCreditosAnterioresLongoPrazo));
						form.set("valorTotalCreditosAnteriores", Util.formatarMoedaReal(valorTotalCreditosAnteriores));
					} else {
						form.set("valorCreditoARealizar", "0,00");
						form.set("valorCreditosAnterioresCurtoPrazo", "0,00");
						form.set("valorCreditosAnterioresLongoPrazo", "0,00");
						form.set("valorTotalCreditosAnteriores", "0,00");
					}
				}else{
					form.set("valorCreditoARealizar", "0,00");
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
	
				form.set("valorDebitoTotalAtualizado", Util
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
				ContaValoresHelper contaRemovida = null;
				while (contaValores.hasNext()) {
					ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores
							.next();
					
					if(verificaReferenciaIgualReferencialFaturamento(contaValoresHelper.getConta().getAnoMesReferenciaConta())) {
						contaRemovida = contaValoresHelper;
						continue;
					}
					
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
				valorTotalContaValores = valorTotalContaValores.subtract(valorContaNB);
				if( indicadorAcrescimosImpotualidade.equals("1") ){
					valorAcrescimosImpontualidade.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
					valorAcrescimosImpontualidade = valorAcrescimosImpontualidade.subtract(valorAcrescimosNB);
				}	

				// Calcula sempre em cima do valor do debito
				valorDebitoTotalAtualizado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorDebitoTotalAtualizado = valorDebitoTotalAtualizado
						.subtract(valorContaNB);
				valorDebitoTotalAtualizado.setScale(Parcelamento.CASAS_DECIMAIS, Parcelamento.TIPO_ARREDONDAMENTO);
				valorDebitoTotalAtualizado = valorDebitoTotalAtualizado
						.subtract(valorAcrescimosNB);
				
				form
					.set("valorTotalContaValores",
						Util.formatarMoedaReal(valorTotalContaValores));
				
				form
					.set("valorAcrescimosImpontualidade",
						Util.formatarMoedaReal(valorAcrescimosImpontualidade));
				
				if( valorDebitoTotalAtualizado.compareTo(new BigDecimal("0.00")) == -1
						|| valorDebitoTotalAtualizado.equals(new BigDecimal("0.00")) ){
					throw new ActionServletException(
							"atencao.nao.existe.debito.a.parcelar");
				}
				form
					.set("valorDebitoTotalAtualizado",
						Util.formatarMoedaReal(valorDebitoTotalAtualizado));
			}
		}
		
		return retorno;
	}

	private boolean clientePossuiDebitos(ObterDebitoImovelOuClienteHelper colecaoDebitoCliente) {
		return (colecaoDebitoCliente.getColecaoContasValores() != null && !colecaoDebitoCliente.getColecaoContasValores().isEmpty())
				|| (colecaoDebitoCliente.getColecaoContasValoresPreteritos() != null && !colecaoDebitoCliente.getColecaoContasValoresPreteritos().isEmpty());
	}

	private void limparSessao2(HttpSession sessao) {
		sessao.removeAttribute("colecaoGuiaPagamentoValores");
		sessao.removeAttribute("colecaoDebitoACobrar");
		sessao.removeAttribute("colecaoCreditoARealizar");
		sessao.removeAttribute("calcula");
		sessao.removeAttribute("colecaoContaValores");
		sessao.removeAttribute("colecaoOpcoesParcelamento");
		
		sessao.removeAttribute("colecaoContasEmAntiguidade");
	}

	private void limparOpcaoParcelamento(HttpSession sessao) {
		if( sessao.getAttribute("colecaoContaValoresNegociacao") != null && !sessao.getAttribute("colecaoContaValoresNegociacao").equals("")){
		    Collection colecaoContaValoresNegociacao = (Collection) sessao.getAttribute("colecaoContaValoresNegociacao");
			Iterator contaValores = colecaoContaValoresNegociacao.iterator();
			while(contaValores.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) contaValores.next();
				if( contaValoresHelper.getIndicadorContasDebito() != null && contaValoresHelper.getIndicadorContasDebito().equals(1) ){
					contaValoresHelper.setIndicadorContasDebito(null);
				}
			}
		}
	}

	private void limparBotoesEPeNB(HttpSession sessao) {
		if( sessao.getAttribute("colecaoContaValores") != null && !sessao.getAttribute("colecaoContaValores").equals("")){
		    
			Collection colecaoContas = (Collection)sessao.getAttribute("colecaoContaValores");
			Iterator colecaoContasIterator = colecaoContas.iterator();
			colecaoContasIterator = colecaoContas.iterator();
			
			while(colecaoContasIterator.hasNext()){
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper)colecaoContasIterator.next();
				contaValoresHelper.setIndicadorContasDebito(null);
			}
		}
	}

	private void limparSessao(HttpSession sessao) {
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

	private boolean semIntervaloParcelamento(String fimIntervaloParcelamento, String inicioIntervaloParcelamento) {
		return (inicioIntervaloParcelamento == null || inicioIntervaloParcelamento.equals(""))
				&& (fimIntervaloParcelamento == null || fimIntervaloParcelamento.equals(""));
	}
	
	private boolean isCreditoDeParcelamento(CreditoARealizar creditoARealizar) {
		return creditoARealizar.getCreditoOrigem().getId().intValue() == CreditoOrigem.DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO
			&& creditoARealizar.getParcelamento() != null;
	}
}
