package gcom.gui.arrecadacao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.DebitoACobrarValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.bean.ObterDebitoImovelOuClienteHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1214] Informar Acerto Documentos Não Aceitos
 *
 * @author Mariana Victor
 * @date 23/08/2011
 */
public class ExibirInformarAcertoDocumentosNaoAceitosDebitosAction extends GcomAction {

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
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
        //seta o mapeamento de retorno para a página da primeira aba
        ActionForward retorno = actionMapping.findForward("informarAcertoDocumentosNaoAceitosDebitos");
        
        InformarAcertoDocumentosNaoAceitosActionForm form = (InformarAcertoDocumentosNaoAceitosActionForm) actionForm;

        //cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
    	String limparDadosAnteriores = httpServletRequest.getParameter("limparDadosAnteriores");
    	if(limparDadosAnteriores !=null && !limparDadosAnteriores.equals("")){
    		removerAtributosSessao(sessao, form);
    	}

        if (httpServletRequest.getParameter("retornoPopup") == null
        		|| !httpServletRequest.getParameter("retornoPopup").trim().equalsIgnoreCase("SIM")) {
        	
			// [FS0006] - Verificar existência da matrícula do imóvel
			String codigoImovelDigitadoEnter = form.getIdImovel();
			
	
			if (codigoImovelDigitadoEnter != null
					&& !codigoImovelDigitadoEnter.trim().equalsIgnoreCase("")) {
	
				Imovel imovelEncontrado = fachada
						.pesquisarImovelDigitado(new Integer(
								codigoImovelDigitadoEnter));
				
				if (imovelEncontrado != null) {
					
					form.setIdImovel(imovelEncontrado.getId().toString());
					form.setDescricaoImovel(imovelEncontrado.getInscricaoFormatada());
					
					httpServletRequest
							.setAttribute("idImovelNaoEncontrado", "true");
					
					this.carregarDebitosImovel(sessao, imovelEncontrado.getId().toString(), form);
	
			        form.setTotalDebitoSelecionado(Util.formatarMoedaReal(BigDecimal.ZERO));
	
				} else {
					// Caso a matrícula do imóvel (IMOV_ID) informada não exista na tabela IMOVEL
	
					this.removerAtributosSessao(sessao, form);
					
			        form.setTotalDebitoSelecionado(Util.formatarMoedaReal(BigDecimal.ZERO));
			        
					form.setIdImovel("");
					form.setDescricaoImovel("Matrícula inexistente");
					
					httpServletRequest
							.setAttribute("idImovelNaoEncontrado", "exception");
					
				}
			}
        } else if (sessao.getAttribute("valorAdicionado") != null
        		&& !sessao.getAttribute("valorAdicionado").toString().trim().equals("")) {

    		String totalDebitosSessao = "0,00";
    		if(form.getTotalDebitos() != null
    				&& !form.getTotalDebitos().trim().equals("")) {
    			totalDebitosSessao = form.getTotalDebitos();
    		}
    		
    		BigDecimal totalDebitos = (BigDecimal) sessao.getAttribute("valorAdicionado");
    		
    		totalDebitos = totalDebitos.add(Util.formatarMoedaRealparaBigDecimal(totalDebitosSessao));
    		
    		form.setTotalDebitos(Util.formatarMoedaReal(totalDebitos));
    		
        }
        
        //retorna o mapeamento contido na variável retorno
        return retorno;
    }
    
    /**
     * [SB0001] ? Apresenta Débitos do Imóvel.
     * */
    public void carregarDebitosImovel(HttpSession sessao, String idImovel, InformarAcertoDocumentosNaoAceitosActionForm form) {
    	
    	Fachada fachada = Fachada.getInstancia();
    	
    	ArrayList<ObterDebitoImovelOuClienteHelper> colecaoClientesDebitosImoveis = new ArrayList<ObterDebitoImovelOuClienteHelper>();

		String dataVencimentoInicial = "01/01/0001";
		String dataVencimentoFinal = "31/12/9999";
		String referenciaInicial = "01/0001";
		String referenciaFinal = "12/9999";
		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		String mesInicial = referenciaInicial.substring(0, 2);
		String anoInicial = referenciaInicial.substring(3, referenciaInicial.length());
		String anoMesInicial = anoInicial + mesInicial;
		String mesFinal = referenciaFinal.substring(0, 2);
		String anoFinal = referenciaFinal.substring(3, referenciaFinal.length());
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

    	// seta valores constantes para chamar o metodo que consulta debitos do imovel
		Integer indicadorDebito = new Integer(1);
		Integer indicadorPagamento = new Integer(1);
		Integer indicadorContaRevisao = new Integer(1);
		Integer indicadorDebitoACobrar = new Integer(1);
		Integer indicadorCredito = new Integer(2);
		Integer indicadorNotas = new Integer(2);
		Integer indicadorGuias = new Integer(1);
		Integer indicadorAtualizar = new Integer(1);
		
		ObterDebitoImovelOuClienteHelper colecao = fachada.obterDebitoImovelOuCliente(
				indicadorDebito.intValue(), // 1.1.	Indicador de débito do imóvel ou cliente (1-imóvel);
				idImovel, // 1.2. Matrícula do Imóvel;
				null, // 1.3. Código do cliente com o valor nulo;
				null, // 1.1. Tipo de relação do cliente com o imóvel com o valor nulo;
				anoMesInicial, // 1.2. Período de referência do débito (referência inicial = 000101 e referência final = 999912);
				anoMesFinal, 
				dataVencimentoDebitoI, // 1.3.	Período de vencimento do débito 
				dataVencimentoDebitoF, 
				indicadorPagamento.intValue(), // 1.4. Indicador de pagamento com o valor 2; 
				indicadorContaRevisao.intValue(), // 1.5. Indicador de conta em revisão com o valor 1; 
				indicadorDebitoACobrar.intValue(), // 1.6. Indicador de débito a cobrar com o valor 1; 
				indicadorCredito.intValue(), // 1.7. Indicador de crédito a realizar com o valor 2;
				indicadorNotas.intValue(), // 1.8. Indicador de notas promissórias com o valor 2; 
				indicadorGuias.intValue(), // 1.9. Indicador de guias de pagamento com o valor 1;
				indicadorAtualizar.intValue(), // 1.10.	Indicador de atualizar débito com o valor 1;
				null);

		colecaoClientesDebitosImoveis.add(colecao);

		/////////////////////////////////////////CARREGA VALORES DEFAULT PARA TODAS VARIAVEIS ////////////////////////////////////////////
		//Criando uma lista para adicao de todas colecoesContaValores para todos Clientes (Caso selecione Cliente Superior)
		Collection<ContaValoresHelper> colecaoContaValoresTotal = new ArrayList<ContaValoresHelper>();
		//Criando uma lista para adicao de todas colecaoDebitoACobrar para todos Clientes (Caso selecione Cliente Superior)
		Collection<DebitoACobrarValoresHelper> colecaoDebitoACobrarTotal = new ArrayList<DebitoACobrarValoresHelper>();
		//Criando uma lista para adicao de todas colecaoGuiaPagamentoValores para todos Clientes (Caso selecione Cliente Superior)
		Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresTotal = new ArrayList<GuiaPagamentoValoresHelper>();
		
		ContaValoresHelper dadosConta = null;
		BigDecimal valorConta = new BigDecimal("0.00");
		BigDecimal valorAtualizacaoMonetaria = new BigDecimal("0.00");
		BigDecimal valorJurosMora = new BigDecimal("0.00");
		BigDecimal valorMulta = new BigDecimal("0.00");
		BigDecimal valorDebitoACobrar = new BigDecimal("0.00");
		BigDecimal valorDebitoACobrarSemJurosParcelamento = new BigDecimal("0.00");
		DebitoACobrar dadosDebito = null;
		BigDecimal valorRestanteACobrar = new BigDecimal("0.00");
		BigDecimal valorTotalRestanteParcelamentosACobrarCurtoPrazo = new BigDecimal("0.00");
		BigDecimal valorTotalRestanteParcelamentosACobrarLongoPrazo = new BigDecimal("0.00");
		BigDecimal valorTotalDebito = new BigDecimal("0.00");
		int indiceCurtoPrazo = 0;
		int indiceLongoPrazo = 1;
		BigDecimal valorGuiaPagamento = new BigDecimal("0.00");
		GuiaPagamentoValoresHelper dadosGuiaPagamentoValoresHelper = null;
		/////////////////////////////////////////FIM - CARREGA VALORES DEFAULT PARA TODAS VARIAVEIS ////////////////////////////////////////////
		
		for (ObterDebitoImovelOuClienteHelper colecaoDebitoImovel : colecaoClientesDebitosImoveis) {
		
			//////////////////////ITERACAO NA COLECAO DE CONTAVALORES////////////////////////////////////
			Collection<ContaValoresHelper> colecaoContaValores = new ArrayList<ContaValoresHelper>();
			if(colecaoDebitoImovel.getColecaoContasValores() != null){
				colecaoContaValores.addAll(colecaoDebitoImovel.getColecaoContasValores());
			}
			
			if (!colecaoContaValores.isEmpty()) {
				java.util.Iterator<ContaValoresHelper> colecaoContaValoresIterator = colecaoContaValores.iterator();
				// percorre a colecao de conta somando o valor para obter um valor total
				while (colecaoContaValoresIterator.hasNext()) {
					
					dadosConta = (ContaValoresHelper) colecaoContaValoresIterator.next();

					Integer idLocalidade = fachada.pesquisarLocalidadeConta(
							dadosConta.getConta().getId());
					Localidade localidade = new Localidade();
					localidade.setId(idLocalidade);
					Conta conta = dadosConta.getConta();
					conta.setLocalidade(localidade);
					
					valorConta = valorConta.add(dadosConta.getConta().getValorTotal());
					
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
					
					// atribui o valor 2 (um) ao indicador de débito pago para cada débito
					dadosConta.setIndicadorDebitoPago(ConstantesSistema.NAO);
					colecaoContaValoresTotal.add(dadosConta);
				}
				
			}
			valorTotalDebito = valorTotalDebito.add(valorConta);
			//////////////////////FIM ITERACAO NA COLECAO DE CONTAVALORES////////////////////////////////////	
			
			
			//////////////////////ITERACAO NA COLECAO DE DEBITOACOBRAR////////////////////////////////////	
			Collection<DebitoACobrar> colecaoDebitoACobrar = new ArrayList<DebitoACobrar>();
			if(colecaoDebitoImovel.getColecaoDebitoACobrar() != null){
				colecaoDebitoACobrar.addAll(colecaoDebitoImovel.getColecaoDebitoACobrar());
			}
			
			if (!colecaoDebitoACobrar.isEmpty()) {
				java.util.Iterator<DebitoACobrar> colecaoDebitoACobrarIterator = colecaoDebitoACobrar.iterator();
				// percorre a colecao de debito a cobrar somando o valor para obter um valor total
				while (colecaoDebitoACobrarIterator.hasNext()) {
					
					dadosDebito = (DebitoACobrar) colecaoDebitoACobrarIterator.next();

					Integer idLocalidade = fachada.pesquisarLocalidadeDebitoACobrar(
							dadosDebito.getId());
					Localidade localidade = new Localidade();
					localidade.setId(idLocalidade);
					dadosDebito.setLocalidade(localidade);
					
					valorDebitoACobrar = valorDebitoACobrar.add(dadosDebito.getValorTotalComBonus());
					
					if (dadosDebito.getDebitoTipo() != null &&
							!dadosDebito.getDebitoTipo().getId().equals(DebitoTipo.JUROS_SOBRE_PARCELAMENTO)){
						valorDebitoACobrarSemJurosParcelamento = valorDebitoACobrarSemJurosParcelamento.add(dadosDebito.getValorTotalComBonus());
					}
					
					//Debitos A Cobrar - Parcelamento
					if (dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_AGUA)
							|| dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_ESGOTO)
							|| dadosDebito.getFinanciamentoTipo().getId().equals(FinanciamentoTipo.PARCELAMENTO_SERVICO)) {
						
						// [SB0001] Obter Valores de Curto e Longo Prazo
						valorRestanteACobrar = dadosDebito.getValorTotalComBonus();
						
						BigDecimal[] valoresDeCurtoELongoPrazo = fachada.obterValorCurtoELongoPrazo(
								dadosDebito.getNumeroPrestacaoDebito(),	
								dadosDebito.getNumeroPrestacaoCobradasMaisBonus(),
								valorRestanteACobrar);
						valorTotalRestanteParcelamentosACobrarCurtoPrazo.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalRestanteParcelamentosACobrarCurtoPrazo = valorTotalRestanteParcelamentosACobrarCurtoPrazo.add(valoresDeCurtoELongoPrazo[indiceCurtoPrazo]);
						valorTotalRestanteParcelamentosACobrarLongoPrazo.setScale(Parcelamento.CASAS_DECIMAIS,Parcelamento.TIPO_ARREDONDAMENTO);
						valorTotalRestanteParcelamentosACobrarLongoPrazo = valorTotalRestanteParcelamentosACobrarLongoPrazo.add(valoresDeCurtoELongoPrazo[indiceLongoPrazo]);
					}
					

					// atribui o valor 2 (um) ao indicador de débito pago para cada débito
					DebitoACobrarValoresHelper helper = new DebitoACobrarValoresHelper();
					helper.setDebitoACobrar(dadosDebito);
					helper.setIndicadorDebitoPago(ConstantesSistema.NAO);
					colecaoDebitoACobrarTotal.add(helper);
					
				}
			}
			valorTotalDebito = valorTotalDebito.add(valorDebitoACobrar);
			//////////////////////FIM ITERACAO NA COLECAO DE DEBITOACOBRAR////////////////////////////////////	
			
			//////////////////////ITERACAO NA COLECAO DE GUIASPAGAMENTOVALORES////////////////////////////////////	
			Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = new ArrayList<GuiaPagamentoValoresHelper>();
			if(colecaoDebitoImovel.getColecaoGuiasPagamentoValores() != null){
				colecaoGuiaPagamentoValores.addAll(colecaoDebitoImovel.getColecaoGuiasPagamentoValores());
			}
			
			if (!colecaoGuiaPagamentoValores.isEmpty()) {
				java.util.Iterator<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresHelperIterator = colecaoGuiaPagamentoValores.iterator();
				// percorre a colecao de guia de pagamento somando o valor para obter um valor total
				while (colecaoGuiaPagamentoValoresHelperIterator.hasNext()) {
					
					dadosGuiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiaPagamentoValoresHelperIterator.next();
					
					Integer idLocalidade = fachada.pesquisarLocalidadeGuiaPagamento(
							dadosGuiaPagamentoValoresHelper.getGuiaPagamento().getId());
					Localidade localidade = new Localidade();
					localidade.setId(idLocalidade);
					GuiaPagamento guiaPagamento = dadosGuiaPagamentoValoresHelper.getGuiaPagamento();
					guiaPagamento.setLocalidade(localidade);
					
					valorGuiaPagamento = valorGuiaPagamento.add(dadosGuiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
					
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

					// atribui o valor 2 (um) ao indicador de débito pago para cada débito
					dadosGuiaPagamentoValoresHelper.setIndicadorDebitoPago(ConstantesSistema.NAO);
					colecaoGuiaPagamentoValoresTotal.add(dadosGuiaPagamentoValoresHelper);
				}
			}
			valorTotalDebito = valorTotalDebito.add(valorGuiaPagamento);
			//////////////////////FIM ITERACAO NA COLECAO DE GUIASPAGAMENTOVALORES////////////////////////////////////	
			
		}
		
		
		if (colecaoContaValoresTotal.isEmpty() && colecaoDebitoACobrarTotal.isEmpty()
				&& colecaoGuiaPagamentoValoresTotal.isEmpty()) {
			
			this.removerAtributosSessao(sessao, form);	
			
		} else {
			// Manda a colecao pelo request
			sessao.setAttribute("colecaoContaValores",colecaoContaValoresTotal);

			// Manda a colecao e os valores total de conta pelo request
			sessao.setAttribute("colecaoDebitoACobrar",colecaoDebitoACobrarTotal);
			sessao.setAttribute("valorConta", Util.formatarMoedaReal(valorConta));

			// Manda a colecao e o valor total de DebitoACobrar pelo request
			sessao.setAttribute("colecaoDebitoACobrar",colecaoDebitoACobrarTotal);
			sessao.setAttribute("valorDebitoACobrar", Util.formatarMoedaReal(valorDebitoACobrar));

			// Manda a colecao e o valor total de GuiaPagamento pelo request
			sessao.setAttribute("colecaoGuiaPagamentoValores",colecaoGuiaPagamentoValoresTotal);
			sessao.setAttribute("valorGuiaPagamento", Util.formatarMoedaReal(valorGuiaPagamento));

			// 6. O sistema exibe ao final da tela o valor total dos débitos 
			form.setTotalDebitos(Util.formatarMoedaReal(valorTotalDebito));
			
		}
    }
    
    public void removerAtributosSessao(HttpSession sessao, InformarAcertoDocumentosNaoAceitosActionForm form) {

		sessao.removeAttribute("colecaoContaValores");
		sessao.removeAttribute("valorConta");
		sessao.removeAttribute("colecaoDebitoACobrar");
		sessao.removeAttribute("valorDebitoACobrar");							
		sessao.removeAttribute("colecaoCreditoARealizar");
		sessao.removeAttribute("valorCreditoARealizar");
		sessao.removeAttribute("valorCreditoARealizarSemDescontosParcelamento");							
		sessao.removeAttribute("colecaoGuiaPagamentoValores");
		sessao.removeAttribute("valorGuiaPagamento");						
		sessao.removeAttribute("valorTotalDebito");
		
		form.setTotalDebitos("0,00");
		
    }
    
}
