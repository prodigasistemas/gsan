package gcom.gui.arrecadacao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import gcom.arrecadacao.bean.InformarAcertoDocumentosNaoAceitosHelper;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.bean.ContaValoresHelper;
import gcom.cobranca.bean.DebitoACobrarValoresHelper;
import gcom.cobranca.bean.GuiaPagamentoValoresHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável por informar acerto documentos não aceitos
 * 
 * [UC1214] Informar Acerto Documentos Não Aceitos
 *
 * @author 	Mariana Victor
 * @created	18/08/2011
 */
public class InformarAcertoDocumentosNaoAceitosAction extends GcomAction {

   
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * <Breve descrição sobre o subfluxo>
     *
     * <Identificador e nome do subfluxo>	
     *
     * <Breve descrição sobre o fluxo secundário>
     *
     * <Identificador e nome do fluxo secundário> 
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Cria a variável de retorno 
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        InformarAcertoDocumentosNaoAceitosActionForm form = (InformarAcertoDocumentosNaoAceitosActionForm) actionForm;
        
        //Cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        // [FS0007] ? Verificar seleção dos débitos
        if ((form.getContasSelecao() == null || form.getContasSelecao().length == 0)
        		&& (form.getDebitosACobrarSelecao() == null || form.getDebitosACobrarSelecao().length == 0)
        		&& (form.getGuiasSelecao() == null || form.getGuiasSelecao().length == 0)) {

        	// Caso nenhum débito selecionado, exibir a mensagem 
			throw new ActionServletException("atencao.nenhum_debito.selecionado.pagamento");
			
        }
        
        
        // [FS0008] ? Verificar valor do débito
        if (Util.formatarMoedaRealparaBigDecimal(form.getTotalDebitoSelecionado())
        		.compareTo(Util.formatarMoedaRealparaBigDecimal(form.getTotalPagamento())) > 0) {
        	
        	// Caso o total dos débitos seja maior que o total do pagamento
			throw new ActionServletException("atencao.valor_total_debitos_informado.maior.valor_total_pagamento",
					new String[]{form.getTotalDebitoSelecionado(), form.getTotalPagamento()});
        	
        }
        
        InformarAcertoDocumentosNaoAceitosHelper helper = this.montarHelper(sessao, form);
        
        fachada.efetuarAcertosPagamentos(helper);
        
        montarPaginaSucesso(httpServletRequest, "O Pagamento foi associado aos débitos com sucesso.",
        		"Informar outro Acerto de Documentos não Aceitos",
                "exibirInformarAcertoDocumentosNaoAceitosAction.do?menu=sim");

	    //Retorna o mapeamento contido na variável retorno
	    return retorno;
	}
    
    public InformarAcertoDocumentosNaoAceitosHelper montarHelper(HttpSession sessao,
    		InformarAcertoDocumentosNaoAceitosActionForm form){
    	
    	InformarAcertoDocumentosNaoAceitosHelper helper = new InformarAcertoDocumentosNaoAceitosHelper();
    	
    	Collection<ContaValoresHelper> colecaoContaValoresSelecionados =
    		new ArrayList<ContaValoresHelper>();
		Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValoresSelecionados =  
			new ArrayList<GuiaPagamentoValoresHelper>();
		Collection<DebitoACobrarValoresHelper>  colecaoDebitoACobrarSelecionados =  
			new ArrayList<DebitoACobrarValoresHelper>();
				
		Collection<ContaValoresHelper> colecaoContaValores = 
			(Collection<ContaValoresHelper>)sessao.getAttribute("colecaoContaValores"); 
		Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores = 
			(Collection<GuiaPagamentoValoresHelper>)sessao.getAttribute("colecaoGuiaPagamentoValores"); 
		Collection<DebitoACobrarValoresHelper>  colecaoDebitoACobrar = 
			(Collection<DebitoACobrarValoresHelper>)sessao.getAttribute("colecaoDebitoACobrar"); 
		
		if (colecaoContaValores != null && !colecaoContaValores.isEmpty() 
				&& form.getContasSelecao() != null && form.getContasSelecao().length > 0) {
			Iterator iterator = colecaoContaValores.iterator();
			
			while(iterator.hasNext()) {
				ContaValoresHelper contaValoresHelper = (ContaValoresHelper) iterator.next();
				
				for (String idConta : form.getContasSelecao()) {
					
					if(contaValoresHelper.getConta().getId()
							.compareTo(Integer.parseInt(idConta)) == 0){
						colecaoContaValoresSelecionados.add(contaValoresHelper);
					}
				}
			}
			
		}
		
		if (colecaoGuiaPagamentoValores != null && !colecaoGuiaPagamentoValores.isEmpty()
				&& form.getGuiasSelecao() != null && form.getGuiasSelecao().length > 0) {
			Iterator iterator = colecaoGuiaPagamentoValores.iterator();
			
			while(iterator.hasNext()) {
				GuiaPagamentoValoresHelper guiaPagamentoValoresHelper = (GuiaPagamentoValoresHelper) iterator.next();
				
				for (String idGuia : form.getGuiasSelecao()) {
					
					if(guiaPagamentoValoresHelper.getGuiaPagamento().getId()
							.compareTo(Integer.parseInt(idGuia)) == 0){
						colecaoGuiaPagamentoValoresSelecionados.add(guiaPagamentoValoresHelper);
					}
				}
			}
			
		}
		
		if (colecaoDebitoACobrar != null && !colecaoDebitoACobrar.isEmpty()
				&& form.getDebitosACobrarSelecao() != null && form.getDebitosACobrarSelecao().length > 0) {
			Iterator iterator = colecaoDebitoACobrar.iterator();
			
			while(iterator.hasNext()) {
				DebitoACobrarValoresHelper debitoACobrarValoresHelper = (DebitoACobrarValoresHelper) iterator.next();
				
				for (String idDebitoACobrar : form.getDebitosACobrarSelecao()) {
					
					if(debitoACobrarValoresHelper.getDebitoACobrar().getId()
							.compareTo(Integer.parseInt(idDebitoACobrar)) == 0){
						colecaoDebitoACobrarSelecionados.add(debitoACobrarValoresHelper);
					}
				}
			}
			
		}

		Imovel imovel = new Imovel();
		imovel.setId(new Integer(form.getIdImovel()));
		
    	helper.setValorPagamento(Util.formatarMoedaRealparaBigDecimal(form.getTotalPagamento()));
    	helper.setValorTotalDebitos(Util.formatarMoedaRealparaBigDecimal(form.getTotalDebitoSelecionado()));
    	helper.setColecaoContaValores(colecaoContaValoresSelecionados);
    	helper.setColecaoGuiaPagamentoValores(colecaoGuiaPagamentoValoresSelecionados);
    	helper.setColecaoDebitoACobrar(colecaoDebitoACobrarSelecionados);
    	helper.setIdPagamento(new Integer(form.getIdPagamentoSelecionado()));
    	helper.setImovel(imovel);
    	
    	return helper;
    	
    }

}
