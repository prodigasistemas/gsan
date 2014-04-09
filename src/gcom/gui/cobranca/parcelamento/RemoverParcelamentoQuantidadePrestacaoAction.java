package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacao;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacaoHelper;
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

/**
 * Action de remover um objeto do tipo ParcelamentoQuantidadePrestacao 
 * da collectionParcelamentoQuantidadePrestacao
 * 
 * @author Vivianne Sousa
 * @created 10/05/2006
 */
public class RemoverParcelamentoQuantidadePrestacaoAction extends GcomAction {
	/**
	 * @author Vivianne Sousa
	 * @date 10/05/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
    	//ActionForward retorno = actionMapping.findForward("atualizarPerfilRemoverParcelamentoQuantidadePrestacao");
    	//if (sessao.getAttribute("UseCase")!= null &&
    	//		sessao.getAttribute("UseCase").equals("INSERIRPERFIL")){
    	ActionForward retorno = actionMapping.findForward("inserirPerfilRemoverParcelamentoQuantidadePrestacao");	
    	//}
    		

    	atualizaColecaoNaSessao(sessao,httpServletRequest);
    	
    	String quantidadeMaximaPrestacao = httpServletRequest.getParameter("qtdeMaxPrestacao");

        if (quantidadeMaximaPrestacao != null && !quantidadeMaximaPrestacao.equalsIgnoreCase("") &&
        	sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper") != null){
        	        	
        	Collection collectionParcelamentoQuantidadePrestacaoHelper = (Collection) sessao
            					.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper");
        	
        	//collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas
        	Collection collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas = null;
    		if (sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas") != null
    				&& !sessao
    						.getAttribute("collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas")
    						.equals("")) {
    			collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas = (Collection) sessao
    					.getAttribute("collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas");
    		} else {
    			collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas = new ArrayList();
    		}
        	
        	ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoHelper = null;
        	ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoHelperExcluir = null;
			Iterator iterator = collectionParcelamentoQuantidadePrestacaoHelper.iterator();
						
			while (iterator.hasNext()) {
				parcelamentoQuantidadePrestacaoHelper = (ParcelamentoQuantidadePrestacaoHelper) iterator.next();
		
				//procura na coleção o parcelamentoQuantidadePrestacao que tem a quantidadeMaximaPrestacao selecionada
				if (parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().getQuantidadeMaximaPrestacao().toString().equals(quantidadeMaximaPrestacao)){
					parcelamentoQuantidadePrestacaoHelperExcluir =  parcelamentoQuantidadePrestacaoHelper;
					collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas.add(parcelamentoQuantidadePrestacaoHelper);
				}
			}
			
			collectionParcelamentoQuantidadePrestacaoHelper.remove(parcelamentoQuantidadePrestacaoHelperExcluir);
       	 	sessao.setAttribute("collectionParcelamentoQuantidadePrestacaoHelper", 
       	 			collectionParcelamentoQuantidadePrestacaoHelper);
       	 	sessao.setAttribute("collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas", 
       			collectionParcelamentoQuantidadePrestacaoHelperLinhaRemovidas);
        	
        }
        
       return retorno;
    }
    
    private void atualizaColecaoNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest){

    	//collectionParcelamentoQuantidadePrestacaoHelper
    	if (sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper") != null
				&& !sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper").equals(
						"")) {

			Collection collectionParcelamentoQuantidadePrestacaoHelper = (Collection) sessao
					.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto  ParcelamentoQuantidadePrestacao
			String txJuros = null;
			String percMinEntrada = null;

			Iterator iterator = collectionParcelamentoQuantidadePrestacaoHelper
					.iterator();
			
			while (iterator.hasNext()) {
				ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoHelper = 
						(ParcelamentoQuantidadePrestacaoHelper) iterator.next();
				
				ParcelamentoQuantidadePrestacao  parcelamentoQuantidadePrestacao = 
					parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao();
					
				long valorTempo = parcelamentoQuantidadePrestacao.getUltimaAlteracao()
						.getTime();
				
				txJuros = (String) httpServletRequest.getParameter("txJuros" + valorTempo);
				percMinEntrada = (String) httpServletRequest.getParameter("percMinEntrada" + valorTempo);
				
				// insere essas variáveis no objeto ParcelamentoQuantidadePrestacao
				BigDecimal taxaJuros  = null;
				if (txJuros != null 
						&& !txJuros.equals("")) {
					taxaJuros = Util.formatarMoedaRealparaBigDecimal(txJuros);
				}
				
				BigDecimal percentualMinEntrada  = null;
				if (percMinEntrada != null 
						&& !percMinEntrada.equals("")) {
					percentualMinEntrada = Util.formatarMoedaRealparaBigDecimal(percMinEntrada);
				}
								
				parcelamentoQuantidadePrestacao.setTaxaJuros(taxaJuros);
				parcelamentoQuantidadePrestacao.setPercentualMinimoEntrada(percentualMinEntrada);
				
				parcelamentoQuantidadePrestacaoHelper.setParcelamentoQuantidadePrestacao(parcelamentoQuantidadePrestacao);
			}
        }	

		
	}

}
 
