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
 * Action que define o pré-processamento do popup de consultar de Percentual por Faixa de Valor
 * [UC0220] Inserir Perfil de Parcelamento
 * 
 * @author Vivianne Sousa
 * @date 27/10/2006
 */
public class ExibirConsultarPercentualFaixaValorPopupAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarPercentualFaixaValor");
		
        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
		
        String qtdeMaximaPrestacao = (String)httpServletRequest.getParameter("qtdeMaximaPrestacao");
        
        atualizaColecoesNaSessao(sessao,httpServletRequest);
        
        Collection collectionParcelamentoQuantidadePrestacaoHelper = 
        	(Collection)sessao.getAttribute("collectionParcelamentoQuantidadePrestacaoHelper");

        
        Iterator iterator = collectionParcelamentoQuantidadePrestacaoHelper.iterator();
			
			while (iterator.hasNext()) {
				ParcelamentoQuantidadePrestacaoHelper parcelamentoQuantidadePrestacaoHelper = 
					(ParcelamentoQuantidadePrestacaoHelper) iterator.next();
			
				if (parcelamentoQuantidadePrestacaoHelper.getParcelamentoQuantidadePrestacao().
						getQuantidadeMaximaPrestacao().toString().equals(qtdeMaximaPrestacao)) {
					
					Collection collectionParcelamentoFaixaValor = null;
					collectionParcelamentoFaixaValor = parcelamentoQuantidadePrestacaoHelper.getCollectionParcelamentoFaixaValor();
					 						
					httpServletRequest.setAttribute("collectionParcelamentoFaixaValorConsulta",collectionParcelamentoFaixaValor);
					sessao.setAttribute("collectionParcelamentoFaixaValorConsulta",collectionParcelamentoFaixaValor);
				}				
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