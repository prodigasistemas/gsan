package gcom.gui.cobranca;

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

import gcom.cobranca.CobrancaDocumento;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

public class EmissaoExtratoConsultarDebitoImovelAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("emissaoExtratoConsultarDebitoImovel");
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		ConsultarDebitoImovelActionForm consultarDebitoImovelActionForm = (ConsultarDebitoImovelActionForm) actionForm;
		
		Collection<ContaValoresHelper> colecaoContaValores =  null;
		Collection<DebitoACobrar> colecaoDebitoACobrar = null;
		Collection<CreditoARealizar> colecaoCreditoARealizar = null;
		Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = null;
		
		String[] idsContas = consultarDebitoImovelActionForm.getContasSelecionadas();
		String[] idsDebitos = consultarDebitoImovelActionForm.getDebitosSelecionados();
		String[] idsCreditos = consultarDebitoImovelActionForm.getCreditosSelecionados();
		String[] idsGuias = consultarDebitoImovelActionForm.getGuiasSelecionadas();
		
		Object[] contas = this.obterContasSelecionadas(idsContas, sessao);
		Object[] debitos = this.obterDebitosSelecionados(idsDebitos, sessao);
        Object[] creditos = this.obterCreditosSelecionadas(idsCreditos, sessao);
        Object[] guiasPagamento = this.obterGuiasSelecionadas(idsGuias, sessao);
		
		//CONTAS
		if(contas != null){
        	colecaoContaValores = (Collection) contas[0];
        }
		
		//DÉBITOS A COBRAR
        if(debitos != null){
        	colecaoDebitoACobrar = (Collection) debitos[0];
        }
        
        
        //CRÉDITOS A REALIZAR
        if(creditos != null){
        	colecaoCreditoARealizar = (Collection) creditos[0];
        }
        
        //GUIAS DE PAGAMENTO
        if(guiasPagamento != null){
        	colecaoGuiaPagamentoValores = (Collection) guiasPagamento[0];
        }
		
		ContaValoresHelper dadosConta = null;
		
		BigDecimal valorConta = new BigDecimal("0.00");
		BigDecimal valorAcrescimo = new BigDecimal("0.00");
		BigDecimal valorAgua = new BigDecimal("0.00");
		BigDecimal valorEsgoto = new BigDecimal("0.00");
		BigDecimal valorDebito = new BigDecimal("0.00");
		BigDecimal valorCredito = new BigDecimal("0.00");
		BigDecimal valorImposto = new BigDecimal("0.00");
			
		if(colecaoContaValores != null && !colecaoContaValores.isEmpty()){
			java.util.Iterator <ContaValoresHelper> colecaoContaValoresIterator = colecaoContaValores.iterator();
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
			}
		}
		
		BigDecimal valorDebitoACobrar = new BigDecimal("0.00");
		DebitoACobrar dadosDebito = null;
		
		if(colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()){
			java.util.Iterator <DebitoACobrar> colecaoDebitoACobrarIterator = colecaoDebitoACobrar.iterator();
			// percorre a colecao de debito a cobrar somando o valor para obter um valor total
			while (colecaoDebitoACobrarIterator.hasNext()) {
				
				dadosDebito = (DebitoACobrar) colecaoDebitoACobrarIterator.next();
				valorDebitoACobrar = valorDebitoACobrar.add(dadosDebito.getValorTotalComBonus());
			}
		}

		BigDecimal valorCreditoARealizar = new BigDecimal("0.00");
		CreditoARealizar dadosCredito = null;
		
		if(colecaoCreditoARealizar != null && !colecaoCreditoARealizar.isEmpty()){
			java.util.Iterator <CreditoARealizar> colecaoCreditoARealizarIterator = colecaoCreditoARealizar.iterator();
			// percorre a colecao de credito a realizar somando o valor para obter um valor total
			while (colecaoCreditoARealizarIterator.hasNext()) {
				
				dadosCredito = (CreditoARealizar) colecaoCreditoARealizarIterator.next();
				valorCreditoARealizar = valorCreditoARealizar.add(dadosCredito.getValorTotalComBonus());
			}
		}
		
		BigDecimal valorGuiaPagamento = new BigDecimal("0.00");
		GuiaPagamentoValoresHelper dadosGuiaPagamentoValoresHelper = null;
		
		if(colecaoGuiaPagamentoValores != null && !colecaoGuiaPagamentoValores.isEmpty()){
			java.util.Iterator <GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresHelperIterator = colecaoGuiaPagamentoValores.iterator();
			// percorre a colecao de guia de pagamento somando o valor para obter um valor total			
			while (colecaoGuiaPagamentoValoresHelperIterator.hasNext()) {
				
				dadosGuiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) colecaoGuiaPagamentoValoresHelperIterator.next();
				valorGuiaPagamento = valorGuiaPagamento.add(dadosGuiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
			}
		}
		
		// Soma o valor total dos debitos e subtrai dos creditos
		BigDecimal valorTotalSemAcrescimo = valorConta.add(valorDebitoACobrar);
		valorTotalSemAcrescimo = valorTotalSemAcrescimo.add(valorGuiaPagamento);
		valorTotalSemAcrescimo = valorTotalSemAcrescimo.subtract(valorCreditoARealizar);
		
		BigDecimal valorTotalComAcrescimo = valorTotalSemAcrescimo.add(valorAcrescimo);
		
		if ((colecaoContaValores == null) &&
				(colecaoDebitoACobrar == null || colecaoDebitoACobrar.isEmpty()) &&
				(colecaoCreditoARealizar == null || colecaoCreditoARealizar.isEmpty()) &&
				(colecaoGuiaPagamentoValores == null)) {
			
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		} else {
			//seta na sessão para emitir o extrato de débito
			sessao.setAttribute("colecaoContaValores",colecaoContaValores);
			sessao.setAttribute("colecaoDebitoACobrar",	colecaoDebitoACobrar);
			sessao.setAttribute("colecaoCreditoARealizar",colecaoCreditoARealizar);
			sessao.setAttribute("colecaoGuiaPagamentoValores", colecaoGuiaPagamentoValores);

			sessao.setAttribute("valorPagamentoAVista", valorTotalSemAcrescimo);
			sessao.setAttribute("valorCreditoARealizar", valorCreditoARealizar);
			sessao.setAttribute("valorAcrescimo", valorAcrescimo);
			
		}
		
		return retorno;		
	}
	
	private Object[] obterContasSelecionadas(String[] idsContas, HttpSession sessao) {

		Object[] retorno = null;
		Collection<ContaValoresHelper> colecaoContas = null;
		BigDecimal valorTotalConta = BigDecimal.ZERO;
		BigDecimal valorTotalAcrescimoImpontualidade = BigDecimal.ZERO;

		if (idsContas != null && !idsContas.equals("")) {
			retorno = new Object[3];
			colecaoContas = new ArrayList();

			Collection colecaoContasSessao = (Collection) sessao.getAttribute("colecaoContas");
			Iterator itColecaoContasSessao = colecaoContasSessao.iterator();
			ContaValoresHelper contaValoresHelper = null;

			while (itColecaoContasSessao.hasNext()) {

				contaValoresHelper = (ContaValoresHelper) itColecaoContasSessao.next();

				for (int x = 0; x < idsContas.length; x++) {

					if (contaValoresHelper.getConta().getId().equals(new Integer(idsContas[x]))) {
						colecaoContas.add(contaValoresHelper);
						valorTotalConta = valorTotalConta.add(contaValoresHelper.getValorTotalConta());
						valorTotalAcrescimoImpontualidade = valorTotalAcrescimoImpontualidade
								.add(contaValoresHelper.getValorTotalContaValoresParcelamento());
					}
				}
			}
			retorno[0] = colecaoContas;
			retorno[1] = valorTotalConta;
			retorno[2] = valorTotalAcrescimoImpontualidade;

		}
		return retorno;
	}

	private Object[] obterDebitosSelecionados(String[] idsDebitos, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<DebitoACobrar> colecaoDebitos = null;
		BigDecimal valorTotalDebitoACobrar = BigDecimal.ZERO;
		
		if (idsDebitos != null && !idsDebitos.equals("")){
			retorno = new Object[2];
			colecaoDebitos = new ArrayList();
			
			Collection colecaoDebitosSessao = (Collection) sessao.getAttribute("colecaoDebitoACobrar");
			Iterator itColecaoDebitosSessao = colecaoDebitosSessao.iterator();
			DebitoACobrar debitoACobrar = null;
			
			while (itColecaoDebitosSessao.hasNext()){
				
				debitoACobrar = (DebitoACobrar) itColecaoDebitosSessao.next();
				
				for(int x=0; x<idsDebitos.length; x++){
					
					if (debitoACobrar.getId().equals(new Integer(idsDebitos[x]))){
						colecaoDebitos.add(debitoACobrar);
						valorTotalDebitoACobrar = valorTotalDebitoACobrar.add(debitoACobrar.getValorTotal());
						
					}
				}
			}
			retorno[0] = colecaoDebitos;
			retorno[1] = valorTotalDebitoACobrar;
		}

		return retorno;
	}
	
	private Object[] obterCreditosSelecionadas(String[] idsCreditos, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<CreditoARealizar> colecaoCreditos = null;
		BigDecimal valorTotalCreditoARealizar = BigDecimal.ZERO;
		
		if (idsCreditos != null && !idsCreditos.equals("")){
			retorno = new Object[2];
			colecaoCreditos = new ArrayList();
			
			Collection colecaoCreditosSessao = (Collection) sessao.getAttribute("colecaoCreditoARealizar");
			Iterator itColecaoCreditosSessao = colecaoCreditosSessao.iterator();
			CreditoARealizar creditoARealizar = null;
			
			while (itColecaoCreditosSessao.hasNext()){
				
				creditoARealizar = (CreditoARealizar) itColecaoCreditosSessao.next();
				
				for(int x=0; x<idsCreditos.length; x++){
					
					if (creditoARealizar.getId().equals(new Integer(idsCreditos[x]))){
						colecaoCreditos.add(creditoARealizar);
						valorTotalCreditoARealizar = valorTotalCreditoARealizar.add(creditoARealizar.getValorTotal());
						
					}
				}
			}
			retorno[0] = colecaoCreditos;
			retorno[1] = valorTotalCreditoARealizar;
		}
		
		return retorno;
	}
	
	private Object[] obterGuiasSelecionadas(String[] idsGuias, HttpSession sessao){
		
		Object[] retorno = null;
		Collection<GuiaPagamentoValoresHelper> colecaoGuias = null;
		BigDecimal valorTotalGuiaPagamento = BigDecimal.ZERO;
		
		if (idsGuias != null && !idsGuias.equals("")){
			retorno = new Object[2];
			colecaoGuias = new ArrayList();
			
			Collection colecaoGuiasSessao = (Collection) sessao.getAttribute("colecaoGuiaPagamentoValores");
			Iterator itColecaoGuiasSessao = colecaoGuiasSessao.iterator();
			GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = null;
			
			while (itColecaoGuiasSessao.hasNext()){
				
				guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) itColecaoGuiasSessao.next();
				
				for(int x=0; x<idsGuias.length; x++){
					
					if (guiaPagamentoValoresHelper.getGuiaPagamento().getId().equals(new Integer(idsGuias[x]))){
						colecaoGuias.add(guiaPagamentoValoresHelper);
						valorTotalGuiaPagamento = valorTotalGuiaPagamento.add(
								guiaPagamentoValoresHelper.getGuiaPagamento().getValorDebito());
					}
				}
			}
			retorno[0] = colecaoGuias;
			retorno[1] = valorTotalGuiaPagamento;
		}
		
		return retorno;
	}
}
