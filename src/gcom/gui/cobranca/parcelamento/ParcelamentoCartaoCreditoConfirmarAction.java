package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.DebitoCreditoParcelamentoHelper;
import gcom.cobranca.bean.ParcelamentoCartaoCreditoHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.debito.DebitoACobrarGeral;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ParcelamentoCartaoCreditoConfirmarAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ParcelamentoCartaoConfirmarForm form = (ParcelamentoCartaoConfirmarForm) actionForm;
		
        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        Fachada fachada = Fachada.getInstancia();

        HttpSession sessao = httpServletRequest.getSession(false);
     
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        //COLEÇÃO COM AS TRANSAÇÕES INFORMADAS PARA CADA CARTÃO (DÉBITO OU CRÉDITO)
        Collection colecaoTransacao = (Collection) sessao.getAttribute("colecaoTransacao");
        
        if (form.getModalidadeCartao().equals(ConstantesSistema.MODALIDADE_CARTAO_CREDITO.toString())){
        	
        	/*
             * [FS0007] – Somatório Inválido
             */
            fachada.verificarSomatorio(colecaoTransacao, form.getValorTotal());
            
        	String idParcelamentoSelecionado = (String) sessao.getAttribute("parcelamento");
        	
        	//CONFIRMANDO PARCELAMENTO POR CARTÃO DE CRÉDITO
        	fachada.confirmarCartaoCredito(Integer.valueOf(idParcelamentoSelecionado), 
        	colecaoTransacao, usuarioLogado);
        
        	montarPaginaSucesso(httpServletRequest,"Parcelamento confirmado com sucesso.",
    		"Realizar outra confirmação de parcelamento",
    		"exibirParcelamentoCartaoCreditoConfirmarAction.do?menu=sim");
        }
        else{
        	
        	/*
             * [FS00010] – Somatório Inválido
             */
            String valorTotal = httpServletRequest.getParameter("valorTotal");
        	fachada.verificarSomatorio(colecaoTransacao, new BigDecimal(valorTotal));
        	
        	ParcelamentoCartaoCreditoHelper parcelamentoCartaoCreditoHelper = (ParcelamentoCartaoCreditoHelper)
        	Util.retonarObjetoDeColecao(colecaoTransacao);
        	
        	//CONTA
        	String[] contasSelecionadas = form.getConta();
        	Collection<ContaValoresHelper> colecaoConta = this.getColacaoConta(contasSelecionadas, sessao);
        	
        	//GUIA DE PAGAMENTO
        	String[] guiasSelecionadas = form.getGuiaPagamento();
        	Collection<GuiaPagamentoGeral> colecaoGuiaPagamento = this.getColacaoGuiaPagamento(guiasSelecionadas);
        	
        	//DEBITO A COBRAR
        	String[] debitosSelecionados = form.getDebito();
        	Collection<DebitoACobrarGeral> colecaoDebitoACobrar = this.getColacaoDebitoACobrar(debitosSelecionados);
        	
        	//PARCELAMENTO
        	String[] parcelamentoSelecionados = form.getParcelamentoDebito();
        	Collection<Parcelamento> colecaoParcelamento = this.getColacaoParcelamento(parcelamentoSelecionados);
        	
        	//ANTECIPAÇÃO DE PARCELAS DE PARCELAMENTO
            Map<String, String[]> requestAntecipacaoParcelasMap = httpServletRequest.getParameterMap();
            
            Object[] parcelasAntecipacaoParcelamento = this.obterAntecipacaoParcelasParcelamentosSelecionados(
            sessao, requestAntecipacaoParcelasMap, fachada);
            
            Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoDebitosDeParcelamento = null;
    		Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoCreditosDeParcelamento = null;
    		
            if (parcelasAntecipacaoParcelamento != null){
            	
            	colecaoAntecipacaoDebitosDeParcelamento = (Collection<DebitoCreditoParcelamentoHelper>) parcelasAntecipacaoParcelamento[0];
            	colecaoAntecipacaoCreditosDeParcelamento = (Collection<DebitoCreditoParcelamentoHelper>) parcelasAntecipacaoParcelamento[2];
            }
        	
        	//CONFIRMANDO PAGAMENTO POR CARTÃO DE DÉBITO
        	fachada.confirmarCartaoDebito(parcelamentoCartaoCreditoHelper, colecaoConta, colecaoGuiaPagamento,
        	colecaoDebitoACobrar, colecaoParcelamento, colecaoAntecipacaoDebitosDeParcelamento,
        	colecaoAntecipacaoCreditosDeParcelamento, usuarioLogado);
        	
        	montarPaginaSucesso(httpServletRequest,"Pagamento efetuado com sucesso",
            "Realizar outro pagamento por cartão de débito",
            "exibirParcelamentoCartaoCreditoConfirmarAction.do?menu=sim");
        }
		
		

		return retorno;
	}
	
	private Collection<ContaValoresHelper> getColacaoConta(String[] contasSelecionadas, HttpSession sessao){
		
		Collection<ContaValoresHelper> colecaoContaCompleta = (Collection) sessao.getAttribute("colecaoConta");
		Collection<ContaValoresHelper> colecaoContaSelecionadas = null;
		
		if (contasSelecionadas != null && !contasSelecionadas.equals("")){
			
			colecaoContaSelecionadas = new ArrayList();
			String[] arrayContas = contasSelecionadas;
			
			for (int i = 0; i < arrayContas.length; i++) {
				
				Integer idContaSelecionada = Integer.valueOf(arrayContas[i]);
				
				Iterator iteratorContaCompleta = colecaoContaCompleta.iterator();
				
				while (iteratorContaCompleta.hasNext()){
					
					ContaValoresHelper contaValoresHelper = (ContaValoresHelper) 
					iteratorContaCompleta.next();
					
					if (contaValoresHelper.getConta().getId().equals(idContaSelecionada)){
						
						colecaoContaSelecionadas.add(contaValoresHelper);
						break;
					}
				}
			}
		}
		
		return colecaoContaSelecionadas;
	}
	
	
	private Collection<GuiaPagamentoGeral> getColacaoGuiaPagamento(String[] guiasSelecionadas){
		
		Collection<GuiaPagamentoGeral> retorno = null;
		
		if (guiasSelecionadas != null && !guiasSelecionadas.equals("")){
			
			retorno = new ArrayList();
			String[] arrayGuias = guiasSelecionadas;
			
			for (int i = 0; i < arrayGuias.length; i++) {
				
				GuiaPagamentoGeral guiaPagamentoGeral = new GuiaPagamentoGeral();
				guiaPagamentoGeral.setId(Integer.valueOf(arrayGuias[i]));
				
				retorno.add(guiaPagamentoGeral);
			}
		}
		
		return retorno;
	}
	
	
	private Collection<DebitoACobrarGeral> getColacaoDebitoACobrar(String[] debitosSelecionados){
		
		Collection<DebitoACobrarGeral> retorno = null;
		
		if (debitosSelecionados != null && !debitosSelecionados.equals("")){
			
			retorno = new ArrayList();
			String[] arrayDebitos = debitosSelecionados;
			
			for (int i = 0; i < arrayDebitos.length; i++) {
				
				DebitoACobrarGeral debitoACobrarGeral = new DebitoACobrarGeral();
				debitoACobrarGeral.setId(Integer.valueOf(arrayDebitos[i]));
				
				retorno.add(debitoACobrarGeral);
			}
		}
		
		return retorno;
	}
	
	
	private Collection<Parcelamento> getColacaoParcelamento(String[] parcelamentosSelecionados){
		
		Collection<Parcelamento> retorno = null;
		
		if (parcelamentosSelecionados != null && !parcelamentosSelecionados.equals("")){
			
			retorno = new ArrayList();
			String[] arrayParcelamentos = parcelamentosSelecionados;
			
			for (int i = 0; i < arrayParcelamentos.length; i++) {
				
				Parcelamento parcelamento = new Parcelamento();
				parcelamento.setId(Integer.valueOf(arrayParcelamentos[i]));
				
				retorno.add(parcelamento);
			}
		}
		
		return retorno;
	}
	
	/**
	 * [UC0630] Solicitar Emissão do Extrato de Débitos 
	 *
	 * @author Raphael Rossiter
	 * @date 26/04/2010
	 *
	 * @param sessao
	 * @param requestMap
	 * @param fachada
	 * @return Object[]
	 */
	private Object[] obterAntecipacaoParcelasParcelamentosSelecionados(HttpSession sessao, 
			Map<String, String[]> requestMap, Fachada fachada){
		
		Object[] retorno = null;
		
		//PARCELAMENTOS DISPONIBILIZADOS PARA O USUÁRIO SELECIONAR
		Collection colecaoDebitoCreditoParcelamentoSessao = (Collection) 
		sessao.getAttribute("colecaoDebitoCreditoParcelamento");
		
		if (colecaoDebitoCreditoParcelamentoSessao != null && 
			!colecaoDebitoCreditoParcelamentoSessao.isEmpty()){
			
			Iterator itColecaoDebitoCreditoParcelamentoSessao = colecaoDebitoCreditoParcelamentoSessao.iterator();
			DebitoCreditoParcelamentoHelper debitoCreditoParcelamentoHelper = null;
			
			BigDecimal valorTotalDebito = BigDecimal.ZERO;
			BigDecimal valorTotalCredito = BigDecimal.ZERO;
			
			Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoDebitos = new ArrayList();
			Collection<DebitoCreditoParcelamentoHelper> colecaoAntecipacaoCreditos = new ArrayList();
			
			while (itColecaoDebitoCreditoParcelamentoSessao.hasNext()){
				
				debitoCreditoParcelamentoHelper = (DebitoCreditoParcelamentoHelper) 
				itColecaoDebitoCreditoParcelamentoSessao.next();
			
				if (requestMap.get("parc" + debitoCreditoParcelamentoHelper.getParcelamento().getId()) != null) {
					
					//QUANTIDADE DE PARCELAS QUE SERÃO ANTECIPADAS
					String qtdAntecipacaoParcelas = (requestMap.get("parc" + 
					debitoCreditoParcelamentoHelper.getParcelamento().getId()))[0];
					
					if(qtdAntecipacaoParcelas != null && !qtdAntecipacaoParcelas.equals("")){
						
						//INICIALIZANDO O OBJETO DE RETORNO
						if (retorno == null){
							retorno = new Object[4];
						}
						
						//QUANTIDADE DE PARCELAS QUE SERÃO ANTECIPADAS
						debitoCreditoParcelamentoHelper.setQuantidadeAntecipacaoParcelas(
						Integer.valueOf(qtdAntecipacaoParcelas));
						
						/*
						 * SELECIONANDO OS DÉBITOS RELACIONADOS AO PARCELAMENTO E CALCULANDO O VALOR QUE SERÁ
						 * COLOCADO NO EXTRATO DE ACORDO COM A QUANTIDADE DE PARCELAS QUE FOI INFORMADA PARA
						 * ANTECIPAÇÃO.
						 */
						if (debitoCreditoParcelamentoHelper.getColecaoDebitoACobrarParcelamento() != null &&
					       !debitoCreditoParcelamentoHelper.getColecaoDebitoACobrarParcelamento().isEmpty()){
						
							Collection colecaoDebito = debitoCreditoParcelamentoHelper
						   .getColecaoDebitoACobrarParcelamento();
						
						   Iterator iterDebito = colecaoDebito.iterator();
						
						   while (iterDebito.hasNext()) {
							
							   DebitoACobrar debitoACobrar = (DebitoACobrar) iterDebito.next();
							
							   /*
							    * [UC0630] Solicitar Emissão do Extrato de Débitos
							    * [FS0003] – Quantidade de Parcelas Informadas Inválida
							    */
							   fachada.verificarQuantidadeParcelasInformada(debitoACobrar, 
							   Short.valueOf(qtdAntecipacaoParcelas));
							   
							   //CALCULANDO O VALOR QUE SERÁ ANTECIPADO
							   valorTotalDebito = valorTotalDebito.add(debitoACobrar
							   .getValorAntecipacaoParcela(Integer.valueOf(qtdAntecipacaoParcelas)));
						   }
						   
						   //DÉBITOS A COBRAR QUE FARÃO PARTE DO EXTRATO DE DÉBITO
						   colecaoAntecipacaoDebitos.add(debitoCreditoParcelamentoHelper);
						}
						
						
						/*
						 * SELECIONANDO OS CRÉDITOS RELACIONADOS AO PARCELAMENTO E CALCULANDO O VALOR QUE SERÁ
						 * COLOCADO NO EXTRATO DE ACORDO COM A QUANTIDADE DE PARCELAS QUE FOI INFORMADA PARA
						 * ANTECIPAÇÃO.
						 */
						if(debitoCreditoParcelamentoHelper.getColecaoCreditoARealizarParcelamento() != null &&
						   !debitoCreditoParcelamentoHelper.getColecaoCreditoARealizarParcelamento().isEmpty()){
							
							Collection colecaoCredito = debitoCreditoParcelamentoHelper
							.getColecaoCreditoARealizarParcelamento();
							
							Iterator iterCredito = colecaoCredito.iterator();
							
							while (iterCredito.hasNext()) {
								
								CreditoARealizar creditoARealizar = (CreditoARealizar) iterCredito.next();
								
								//CALCULANDO O VALOR QUE SERÁ ANTECIPADO
								valorTotalCredito = valorTotalCredito.add(creditoARealizar
								.getValorAntecipacaoParcela(Integer.valueOf(qtdAntecipacaoParcelas)));
							}
							
							//CRÉDITOS A REALIZAR QUE FARÃO PARTE DO EXTRATO DE DÉBITO
							colecaoAntecipacaoCreditos.add(debitoCreditoParcelamentoHelper);
						}
					}
					else{
						
						//QUANTIDADE DE PARCELAS QUE SERÃO ANTECIPADAS
						debitoCreditoParcelamentoHelper.setQuantidadeAntecipacaoParcelas(null);
					}
				}
			}
			
			if (retorno != null){
				
				retorno[0] = colecaoAntecipacaoDebitos;
				retorno[1] = valorTotalDebito;
				retorno[2] = colecaoAntecipacaoCreditos;
				retorno[3] = valorTotalCredito;
			}
		}
		
		return retorno;
	}
}
