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
 * Action que define o pré-processamento do Popup de 
 * Adicionar Percentual por Faixa de Valor 
 * 
 * @author Vivianne Sousa
 * @created 26/10/2006
 */

public class ExibirAdicionarPercentualFaixaValorPopupAction  extends GcomAction {
	
	/**
	 * Este caso de uso permite a inclusão de um novo perfil de parcelamento
	 * 
	 * [UC0220] Inserir Perfil de Parcelamento
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 26/10/2006
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

        ActionForward retorno = actionMapping.findForward("adicionarPercentualFaixaValorPopup");
        //InserirPrestacoesParcelamentoPerfilActionForm inserirPrestacoesParcelamentoPerfilActionForm = (InserirPrestacoesParcelamentoPerfilActionForm) actionForm;
        HttpSession sessao = httpServletRequest.getSession(false);
        //Fachada fachada = Fachada.getInstancia();
        

        if(httpServletRequest.getParameter("primeiraVez")!= null &&
        		httpServletRequest.getParameter("primeiraVez").equals("S")){
        	
        	atualizaColecoesNaSessao( sessao,httpServletRequest);
        }
        
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
