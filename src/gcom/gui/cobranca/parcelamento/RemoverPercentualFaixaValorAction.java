package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.parcelamento.ParcelamentoFaixaValor;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacao;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacaoHelper;
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

/**
 * Action de remover um objeto do tipo PercentualFaixaValor
 * da collectionPercentualFaixaValor
 * 
 * @author Vivianne Sousa
 * @created 10/05/2006
 */
public class RemoverPercentualFaixaValorAction extends GcomAction {
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
    	
    	ActionForward retorno = actionMapping.findForward("inserirPerfilRemoverPercentualFaixaValor");	

    	atualizaColecoesNaSessao(sessao,httpServletRequest);
    	
    	String valorMaximo = httpServletRequest.getParameter("valorMaximo");

    	
    	//se tela de inserir antes do adicionar  Informações por Quantidade Máxima de Prestações
        if (valorMaximo != null && !valorMaximo.equalsIgnoreCase("") && sessao.getAttribute("collectionParcelamentoFaixaValor") != null){
        	        	
        	Collection collectionParcelamentoFaixaValor = (Collection) sessao.getAttribute("collectionParcelamentoFaixaValor");
        	
    		ParcelamentoFaixaValor parcelamentoFaixaValor = null;
    		ParcelamentoFaixaValor parcelamentoFaixaValorExcluir = null;
			Iterator iterator = collectionParcelamentoFaixaValor.iterator();
						
			while (iterator.hasNext()) {
				parcelamentoFaixaValor = (ParcelamentoFaixaValor) iterator.next();
		
				//procura na coleção o parcelamentoFaixaValor que tem a valorMaximo selecionado
				if (parcelamentoFaixaValor.getValorFaixa().toString().equals(valorMaximo)){
					parcelamentoFaixaValorExcluir =  parcelamentoFaixaValor;
				}
			}
			
			collectionParcelamentoFaixaValor.remove(parcelamentoFaixaValorExcluir);
       	 	sessao.setAttribute("collectionParcelamentoFaixaValor", 
       	 			collectionParcelamentoFaixaValor);
        	
        }
        
      //se vier do consultarrrrrrrrrrrrr   ???????????
      // tem q passar tb o identificador da coleção de Informações por Quantidade Máxima de Prestações   
        
//        String qtdeMaximaPrestacao = httpServletRequest.getParameter("qtdeMaximaPrestacao");
        
        
        
//        if (valorMaximo != null && !valorMaximo.equalsIgnoreCase("") && sessao.getAttribute("collectionParcelamentoFaixaValor") != null){
//        	
//        	Collection collectionParcelamentoFaixaValor = (Collection) sessao.getAttribute("collectionParcelamentoFaixaValor");
//        	
//        	//collectionParcelamentoFaixaValorLinhaRemovidas
//        	Collection collectionParcelamentoFaixaValorLinhaRemovidas = null;
//    		if (sessao.getAttribute("collectionParcelamentoFaixaValorLinhaRemovidas") != null
//    				&& !sessao.getAttribute("collectionParcelamentoFaixaValorLinhaRemovidas")
//    						.equals("")) {
//    			collectionParcelamentoFaixaValorLinhaRemovidas = (Collection) sessao
//    					.getAttribute("collectionParcelamentoFaixaValorLinhaRemovidas");
//    		} else {
//    			collectionParcelamentoFaixaValorLinhaRemovidas = new ArrayList();
//    		}
//        	
//    		ParcelamentoFaixaValor parcelamentoFaixaValor = null;
//    		ParcelamentoFaixaValor parcelamentoFaixaValorExcluir = null;
//			Iterator iterator = collectionParcelamentoFaixaValor.iterator();
//						
//			while (iterator.hasNext()) {
//				parcelamentoFaixaValor = (ParcelamentoFaixaValor) iterator.next();
//		
//				//procura na coleção o parcelamentoFaixaValor que tem a valorMaximo selecionado
//				if (parcelamentoFaixaValor.getValorFaixa().toString().equals(valorMaximo)){
//					parcelamentoFaixaValorExcluir =  parcelamentoFaixaValor;
//					collectionParcelamentoFaixaValorLinhaRemovidas.add(parcelamentoFaixaValor);
//				}
//			}
//			
//			collectionParcelamentoFaixaValor.remove(parcelamentoFaixaValorExcluir);
//       	 	sessao.setAttribute("collectionParcelamentoFaixaValor", 
//       	 			collectionParcelamentoFaixaValor);
//       	 	sessao.setAttribute("collectionParcelamentoFaixaValorLinhaRemovidas", 
//       			collectionParcelamentoFaixaValorLinhaRemovidas);
//        	
//        }
        
        
       return retorno;
    }
    
    private void atualizaColecoesNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest){
    	     	
		// collectionParcelamentoQuantidadePrestacaoHelper
		if (sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper") != null
		&& !sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper").equals(
			"")) {
		
			Collection collectionParcelamentoQuantidadePrestacaoHelper = (Collection) sessao
			.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto  ParcelamentoQuantidadePrestacao
			String txJuros = null;
			String percMinEntrada = null;
			String percTarMinImovel = null;
			String percVlReparcelado = null;
		
			Iterator iteratorParcelamentoQuantidadePrestacaoHelper = collectionParcelamentoQuantidadePrestacaoHelper
			.iterator();
		
			while (iteratorParcelamentoQuantidadePrestacaoHelper.hasNext()) {
				ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoHelper = 
					(ParcelamentoQuantidadePrestacaoHelper) iteratorParcelamentoQuantidadePrestacaoHelper.next();
				
				ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao = 
					parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao();
				
				long valorTempo = parcelamentoQuantidadePrestacao.getUltimaAlteracao()
					.getTime();
				
				txJuros = (String) httpServletRequest.getParameter("txJuros" + valorTempo);
				percMinEntrada = (String) httpServletRequest.getParameter("percMinEntrada" + valorTempo);
				percTarMinImovel = (String)httpServletRequest.getParameter("percTarMinImovel" + valorTempo);
				percVlReparcelado = (String)httpServletRequest.getParameter("percVlReparcelado" + valorTempo);
					
				// insere essas variáveis no objeto ParcelamentoQuantidadePrestacao
				BigDecimal taxaJuros  = null;
				if (txJuros != null 
					&& !txJuros.equals("")) {
					taxaJuros = Util.formatarMoedaRealparaBigDecimal(txJuros);
				}
			
				BigDecimal percentualMinimoEntrada = null;
				if (percMinEntrada != null 
					&& !percMinEntrada.equals("")) {
					percentualMinimoEntrada = Util.formatarMoedaRealparaBigDecimal(percMinEntrada);
				}
					
				BigDecimal percentualTarifaMinimaImovel = null;
				if (percTarMinImovel != null 
					&& !percTarMinImovel.equals("")) {
					percentualTarifaMinimaImovel = Util.formatarMoedaRealparaBigDecimal(percTarMinImovel);
				}
				
				BigDecimal percentualValorReparcelado = null;
				if (percVlReparcelado != null 
					&& !percVlReparcelado.equals("")) {
					percentualValorReparcelado = Util.formatarMoedaRealparaBigDecimal(percVlReparcelado);
				}
							
				parcelamentoQuantidadePrestacao.setTaxaJuros(taxaJuros);
				parcelamentoQuantidadePrestacao.setPercentualMinimoEntrada(percentualMinimoEntrada);
				parcelamentoQuantidadePrestacao.setPercentualTarifaMinimaImovel(percentualTarifaMinimaImovel);
				parcelamentoQuantidadePrestacao.setPercentualValorReparcelado(percentualValorReparcelado);
				
				parcelamentoQuantidadePrestacaoHelper.setParcelamentoQuantidadePrestacao(parcelamentoQuantidadePrestacao);
				
			}
		}
		
		if(sessao.getAttribute("collectionParcelamentoFaixaValor") != null
				&& !sessao.getAttribute("collectionParcelamentoFaixaValor").equals(
				"")){
			
			Collection collectionParcelamentoFaixaValor = (Collection) sessao
			.getAttribute("collectionParcelamentoFaixaValor");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto  ParcelamentoFaixaValor
			String perc = null;

			Iterator iteratorParcelamentoFaixaValor = collectionParcelamentoFaixaValor
			.iterator();
		
			while (iteratorParcelamentoFaixaValor.hasNext()) {
				ParcelamentoFaixaValor parcelamentoFaixaValor = (ParcelamentoFaixaValor) iteratorParcelamentoFaixaValor
					.next();
				long valorTempo = parcelamentoFaixaValor.getUltimaAlteracao()
					.getTime();
				
				perc = (String) httpServletRequest.getParameter("perc" + valorTempo);
				
				// insere essas variáveis no objeto ParcelamentoFaixaValor
				BigDecimal percentual  = null;
				if (perc != null 
					&& !perc.equals("")) {
					percentual = Util.formatarMoedaRealparaBigDecimal(perc);
				}
			
				parcelamentoFaixaValor.setPercentualFaixa(percentual);
			
			}
		}
    }
    

}
 
