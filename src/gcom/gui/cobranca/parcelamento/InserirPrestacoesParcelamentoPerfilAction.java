/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.cobranca.parcelamento;

import gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacao;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadePrestacaoHelper;
import gcom.cobranca.parcelamento.ParcelamentoQuantidadeReparcelamentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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
 * Action responsável por adicionar na collectionParcelamentoQuantidadePrestacao
 * um ou mais objeto(s) do tipo ParcelamentoQuantidadePrestacao
 * 
 * @author Vivianne Sousa
 * @created 05/05/2006
 */
public class InserirPrestacoesParcelamentoPerfilAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um novo perfil de parcelamento
	 * 
	 * [UC0220] Inserir Perfil de Parcelamento
	 * 
	 * 
	 * @author Vivianne Sousa
	 * @date 05/05/2006
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

        ActionForward retorno = actionMapping
                .findForward("inserirPrestacoesParcelamentoPerfilAction");
    	HttpSession sessao = httpServletRequest.getSession(false);
    	
       
        atualizaColecaoNaSessao (sessao, httpServletRequest);
        Fachada fachada = Fachada.getInstancia();
        sessao.removeAttribute("collectionParcelamentoFaixaValor");
        
	    if (sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper") != null
				&& !sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper").equals(
						"")) {

			Collection collectionParcelamentoQuantidadePrestacaoHelper = (Collection) sessao
					.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper");
			// cria as variáveis para recuperar os parâmetros do request e jogar
			// no objeto  ParcelamentoDescontoInatividade
			String txJuros = null;
			String percMinEntrada = null;
			String percTarMinImovel = null;
			String percVlReparcelado = null;
			
			String fatCalculo = null;
			String indMediaConta = null;
			String indValorUltConta = null;
			
			Iterator iteratorParcelamentoQuantidadePrestacao = collectionParcelamentoQuantidadePrestacaoHelper
					.iterator();
			
			while (iteratorParcelamentoQuantidadePrestacao.hasNext()) {
				ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoHelper = 
						(ParcelamentoQuantidadePrestacaoHelper) iteratorParcelamentoQuantidadePrestacao
						.next();
				
				ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao = 
					parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao();
				
				long valorTempo = parcelamentoQuantidadePrestacao.getUltimaAlteracao().getTime();
				txJuros = (String) httpServletRequest.getParameter("txJuros" + valorTempo);
				percMinEntrada = httpServletRequest.getParameter("percMinEntrada" + valorTempo);
				percTarMinImovel = httpServletRequest.getParameter("percTarMinImovel" + valorTempo);
				percVlReparcelado = (String)httpServletRequest.getParameter("percVlReparcelado" + valorTempo);
				
				fatCalculo = (String) httpServletRequest.getParameter("fatQtdPrest" + valorTempo);
				indMediaConta = (String) httpServletRequest.getParameter("indMedVlCnta" + valorTempo);
				indValorUltConta = (String) httpServletRequest.getParameter("indValorUltConta" + valorTempo);
				
				// insere essas variáveis no objeto ParcelamentoDescontoInatividade
				BigDecimal taxaJuros  = null;
				if (txJuros != null 
						&& !txJuros.equals("")) {
					taxaJuros = Util.formatarMoedaRealparaBigDecimal(txJuros);
				}
				
				Integer fatorCalculo  = null;
				if (fatCalculo != null && !fatCalculo.equals("")) {
					fatorCalculo = new Integer(fatCalculo);
				}
				
				Short indicadorMediaConta  = ConstantesSistema.NAO;
				if (indMediaConta != null && !indMediaConta.equals("")) {
					indicadorMediaConta = new Short(indMediaConta);
				}
				Short indicadorValorUltConta  = ConstantesSistema.NAO;
				if (indValorUltConta!= null && !indValorUltConta.equals("")) {
					indicadorValorUltConta = new Short(indValorUltConta);
				}
				
			    BigDecimal percentualMinimoEntrada = null;
			    BigDecimal percentualTarifaMinimaImovel = null;
			    BigDecimal percentualValorReparcelado = null;
			    
			    if ((percMinEntrada == null || percMinEntrada.equals("") || percMinEntrada.equals("0,00"))
	    		&& (percTarMinImovel == null || percTarMinImovel.equals("") || percTarMinImovel.equals("0,00"))){
			    	 percentualMinimoEntrada = new BigDecimal(0);
					 percentualTarifaMinimaImovel = new BigDecimal(0);
			    }else{
			    	
			    	if (percMinEntrada != null && !percMinEntrada.equals("")){
			    		percentualMinimoEntrada = Util.formatarMoedaRealparaBigDecimal(percMinEntrada);
			    	}else if (percTarMinImovel != null && !percTarMinImovel.equals("")){
			    		percentualTarifaMinimaImovel = Util.formatarMoedaRealparaBigDecimal(percTarMinImovel);
			    	}
			    }
			    
			    if (percVlReparcelado == null || percVlReparcelado.equals("") || percVlReparcelado.equals("0,00")){
				    percentualValorReparcelado = new BigDecimal(0);
			   }else{
					percentualValorReparcelado =Util.formatarMoedaRealparaBigDecimal(percVlReparcelado);
			   }
			 
			    
				parcelamentoQuantidadePrestacao.setTaxaJuros(taxaJuros);
				parcelamentoQuantidadePrestacao.setPercentualMinimoEntrada(percentualMinimoEntrada);
				parcelamentoQuantidadePrestacao.setPercentualTarifaMinimaImovel(percentualTarifaMinimaImovel);
				parcelamentoQuantidadePrestacao.setPercentualValorReparcelado(percentualValorReparcelado);
				parcelamentoQuantidadePrestacaoHelper.setParcelamentoQuantidadePrestacao(parcelamentoQuantidadePrestacao);
				
				parcelamentoQuantidadePrestacao.setFatorQuantidadePrestacoes(fatorCalculo);
				parcelamentoQuantidadePrestacao.setIndicadorMediaValorContas(indicadorMediaConta);
				parcelamentoQuantidadePrestacao.setIndicadorValorUltimaContaEmAtraso(indicadorValorUltConta);
				//sessao.setAttribute("collectionParcelamentoQuantidadePrestacao",collectionParcelamentoQuantidadePrestacao);
			}
			

			validacaoFinal( collectionParcelamentoQuantidadePrestacaoHelper,fachada);			
			
	        Collection collectionParcelamentoQuantidadeReparcelamentoHelper = (Collection) sessao
	                    .getAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper");
	        
	        Collection collectionParcelamentoQuantidadeReparcelamentoHelper1 = new ArrayList();
	                
	        Iterator iterator = collectionParcelamentoQuantidadeReparcelamentoHelper.iterator();

			while (iterator.hasNext()) {
				ParcelamentoQuantidadeReparcelamentoHelper parcelamentoQuantidadeReparcelamentoHelper = 
					(ParcelamentoQuantidadeReparcelamentoHelper) iterator.next();

				if (parcelamentoQuantidadeReparcelamentoHelper.getQuantidadeMaximaReparcelamento().toString()
						.equals((String)sessao.getAttribute("qtdeMaxReparcelamento"))) {
					parcelamentoQuantidadeReparcelamentoHelper.
						setCollectionParcelamentoQuantidadePrestacaoHelper(collectionParcelamentoQuantidadePrestacaoHelper);
					parcelamentoQuantidadeReparcelamentoHelper.
						setInformacaoParcelamentoQtdeReparcelamento("INFORMADAS");
										
				}
				collectionParcelamentoQuantidadeReparcelamentoHelper1.add(parcelamentoQuantidadeReparcelamentoHelper);
			}
			
			sessao.setAttribute("collectionParcelamentoQuantidadeReparcelamentoHelper",
					collectionParcelamentoQuantidadeReparcelamentoHelper1);
			
			if (sessao.getAttribute("UseCase")!= null &&
	    			sessao.getAttribute("UseCase").equals("INSERIRPERFIL")){
				httpServletRequest.setAttribute("reloadPage","INSERIRPERFIL");
	    	}else{
	    		httpServletRequest.setAttribute("reloadPage","ATUALIZARPERFIL");
	    	}
						
			
        }	        	

        return retorno;
    }

    private void atualizaColecaoNaSessao(HttpSession sessao,
			HttpServletRequest httpServletRequest){

    	//collectionParcelamentoQuantidadePrestacao
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
			
			String fatCalculo = null;
			String indMediaConta = null;
			String indValorUltConta = null;
			
			Iterator iterator = collectionParcelamentoQuantidadePrestacaoHelper
					.iterator();
			
			while (iterator.hasNext()) {
				ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoHelper = 
						(ParcelamentoQuantidadePrestacaoHelper) iterator.next();
				
				ParcelamentoQuantidadePrestacao parcelamentoQuantidadePrestacao =
					parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao();
				
				long valorTempo = parcelamentoQuantidadePrestacao.getUltimaAlteracao()
						.getTime();
				
				txJuros = (String) httpServletRequest.getParameter("txJuros" + valorTempo);
				percMinEntrada = (String) httpServletRequest.getParameter("percMinEntrada" + valorTempo);
				percTarMinImovel = (String)httpServletRequest.getParameter("percTarMinImovel" + valorTempo);
				
				fatCalculo = (String) httpServletRequest.getParameter("fatQtdPrest" + valorTempo);
				indMediaConta = (String) httpServletRequest.getParameter("indMedVlCnta" + valorTempo);
				indValorUltConta = (String) httpServletRequest.getParameter("indValorUltConta" + valorTempo);
				
				// insere essas variáveis no objeto ParcelamentoQuantidadePrestacao
				BigDecimal taxaJuros  = null;
				if (txJuros != null 
						&& !txJuros.equals("")) {
					taxaJuros = Util.formatarMoedaRealparaBigDecimal(txJuros);
				}
				
				Integer fatorCalculo  = null;
				if (fatCalculo != null && !fatCalculo.equals("")) {
					fatorCalculo = new Integer(fatCalculo);
				}
				
				Short indicadorMediaConta  = ConstantesSistema.NAO;
				if (indMediaConta != null && !indMediaConta.equals("")) {
					indicadorMediaConta = new Short(indMediaConta);
				}
				
				Short indicadorValorUltConta  = ConstantesSistema.NAO;
				if (indValorUltConta!= null && !indValorUltConta.equals("")) {
					indicadorValorUltConta = new Short(indValorUltConta);
				}
				
				/*BigDecimal percentualMinEntrada  = null;
				if (percMinEntrada != null 
						&& !percMinEntrada.equals("")) {
					percentualMinEntrada = Util.formatarMoedaRealparaBigDecimal(percMinEntrada);
				}*/
				
				BigDecimal percentualMinimoEntrada = null;
			    BigDecimal percentualTarifaMinimaImovel = null;
			    
			    
			    if ((percMinEntrada == null || percMinEntrada.equals("") || percMinEntrada.equals(0))
			    		&& (percTarMinImovel == null || percTarMinImovel.equals("") || percTarMinImovel.equals(0))){
			    	 percentualMinimoEntrada = new BigDecimal(0);
					 percentualTarifaMinimaImovel = new BigDecimal(0);
			    }else{
			    	
			    	if (percMinEntrada != null && !percMinEntrada.equals("")){
			    		percentualMinimoEntrada = Util.formatarMoedaRealparaBigDecimal(percMinEntrada);
			    	}else if (percTarMinImovel != null && !percTarMinImovel.equals("")){
			    		percentualTarifaMinimaImovel = Util.formatarMoedaRealparaBigDecimal(percTarMinImovel);
			    	}
			    }
								
				parcelamentoQuantidadePrestacao.setTaxaJuros(taxaJuros);
				parcelamentoQuantidadePrestacao.setPercentualMinimoEntrada(percentualMinimoEntrada);
				parcelamentoQuantidadePrestacao.setPercentualTarifaMinimaImovel(percentualTarifaMinimaImovel);
				
				parcelamentoQuantidadePrestacao.setFatorQuantidadePrestacoes(fatorCalculo);
				parcelamentoQuantidadePrestacao.setIndicadorMediaValorContas(indicadorMediaConta);
				parcelamentoQuantidadePrestacao.setIndicadorValorUltimaContaEmAtraso(indicadorValorUltConta);
				
				parcelamentoQuantidadePrestacaoHelper.setParcelamentoQuantidadePrestacao(parcelamentoQuantidadePrestacao);
			}
        }	
    
	}
    
    
    private void validacaoFinal(Collection collectionParcelamentoQuantidadePrestacaoHelper,
    		Fachada fachada){
    	
    	// filtro para descobrir o percentual mínimo de entrada permitido para financiamento
		/*FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		Collection colecaoSistemaParametros;

			colecaoSistemaParametros = fachada.pesquisar(
					filtroSistemaParametro, SistemaParametro.class.getName());
		SistemaParametro sistemaParametro = (SistemaParametro)colecaoSistemaParametros
				.iterator().next();
		BigDecimal percentualFinanciamentoEntradaMinima = sistemaParametro.getPercentualFinanciamentoEntradaMinima();
		*/

    	Iterator iteratorParcelamentoQuantidadePrestacaoHelper = collectionParcelamentoQuantidadePrestacaoHelper.iterator();

		 while (iteratorParcelamentoQuantidadePrestacaoHelper.hasNext()) {
			
			 ParcelamentoQuantidadePrestacaoHelper  parcelamentoQuantidadePrestacaoHelper = 
				 			(ParcelamentoQuantidadePrestacaoHelper) iteratorParcelamentoQuantidadePrestacaoHelper.next();
			 
			 ParcelamentoQuantidadePrestacao  parcelamentoQuantidadePrestacao = 
				 parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao();
			 
			if(parcelamentoQuantidadePrestacao.getQuantidadeMaximaPrestacao() == null){
				throw new ActionServletException(
						// Informe Quantidade Máxima de Prestações
						"atencao.required", null, " Quantidade Máxima de Prestações");	
			}
			
			if(parcelamentoQuantidadePrestacao.getTaxaJuros() == null){
				throw new ActionServletException(
						// Informe Taxa de Juros a.m
						"atencao.required", null, " Taxa de Juros a.m");	
			}
			
//			if(parcelamentoQuantidadePrestacao.getFatorQuantidadePrestacoes() == null){
//				throw new ActionServletException(
//						// Informe Fator Cálculo Qtd. Prestações Parc.
//						"atencao.required", null, " Fator Cálculo Qtd. Prestações Parc.");	
//			}
			/*
			if(parcelamentoQuantidadePrestacao.getPercentualMinimoEntrada() == null){
				throw new ActionServletException(
						// Informe Percentual Mínimo de Entrada
						"atencao.required", null, " Percentual Mínimo de Entrada");	
			}else{
				//[FS0005]Verificar percentual mínimo de entrada
				BigDecimal percentualMinimoEntrada = parcelamentoQuantidadePrestacao.getPercentualMinimoEntrada();
				
				if (percentualFinanciamentoEntradaMinima.compareTo(percentualMinimoEntrada) > 0  ){
					//Percentual Mínimo de Entrada inferior a Percentual Mínimo de Entrada de 
					//{0} permitido para Financiamento.
					throw new ActionServletException(
					"atencao.percentual_min_entrada_inferior_percentual_min_financiamento",
					null,"" + percentualFinanciamentoEntradaMinima);
				}
			
			}*/
    }
}
}
