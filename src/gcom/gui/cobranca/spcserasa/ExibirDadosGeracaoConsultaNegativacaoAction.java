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
package gcom.gui.cobranca.spcserasa;


import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.DadosConsultaNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

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
 * @date 26/10/2006
 * 
 */          
public class ExibirDadosGeracaoConsultaNegativacaoAction extends GcomAction {
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
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirDadosGeracaoConsultaNegativacao");
		
		//obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		DadosConsultaNegativacaoHelper dadosConsultaNegativacaoHelper = 
			(DadosConsultaNegativacaoHelper) sessao.getAttribute("dadosConsultaNegativacaoHelper");
		
		
		// [UC0676] Consultar Resumo da Negativação
        //-------------------------------------------------------------------------------------------
		// Alterado por :  Yara Taciane  - data : 28/07/2008 
		// Analista :  Fátima Sampaio
        //-------------------------------------------------------------------------------------------	
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema(); 	
		dadosConsultaNegativacaoHelper.setNumeroExecucaoResumoNegativacao(sistemaParametro.getNumeroExecucaoResumoNegativacao());
		//-------------------------------------------------------------------------------------------


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                         R E F E R E N T E    A    N E G A T I V A C O E S    I N C L U I D A S
//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\		
		// Pesquisa os dados do resumo da Negociacao agrupando por idNegociador
		Collection<NegativacaoHelper>colecaoResumoNegativacaoNeg = fachada.consultarResumoNegativacao(dadosConsultaNegativacaoHelper,1);
		sessao.setAttribute("colecaoResumoNegativacaoNeg", colecaoResumoNegativacaoNeg);
		
		Iterator itcolecaoResumoNegativacaoNeg = colecaoResumoNegativacaoNeg.iterator();
		Integer totalQuantidadeInclusoesNeg = new Integer(0);
		BigDecimal valorTotalInclusoesNeg   = new BigDecimal(0);
		while(itcolecaoResumoNegativacaoNeg.hasNext()){
			NegativacaoHelper retumoTotaisNeg = (NegativacaoHelper)itcolecaoResumoNegativacaoNeg.next();
			totalQuantidadeInclusoesNeg += retumoTotaisNeg.getSomatorioQuantidadeInclusoes();
			valorTotalInclusoesNeg = valorTotalInclusoesNeg.add(retumoTotaisNeg.getSomatorioValorDebito());
		}
		sessao.setAttribute("totalQuantidadeInclusoesNeg",totalQuantidadeInclusoesNeg);   
		sessao.setAttribute("valorTotalInclusoesNeg",valorTotalInclusoesNeg);     

		
		
		// Pesquisa os dados do resumo da Negociacao agrupando por cobrancaDebitoSituacao 
		Collection<NegativacaoHelper>colecaoResumoNegativacao = fachada.consultarResumoNegativacao(dadosConsultaNegativacaoHelper,2);
		
		Iterator itcolecaoResumoNegativacao = colecaoResumoNegativacao.iterator();
		Integer totalQuantidadeInclusoes = new Integer(0);
		BigDecimal valorTotalInclusoes   = new BigDecimal(0);
		while(itcolecaoResumoNegativacao.hasNext()){
			NegativacaoHelper retumoTotais = (NegativacaoHelper)itcolecaoResumoNegativacao.next();
			totalQuantidadeInclusoes = retumoTotais.getSomatorioQuantidadeInclusoes();
			valorTotalInclusoes = retumoTotais.getSomatorioValorDebito();
			
			// Calculo dos Percentuais
			BigDecimal percentQTD = new BigDecimal((totalQuantidadeInclusoes.doubleValue() *100) / totalQuantidadeInclusoesNeg.doubleValue()); 
			retumoTotais.setPercentualQtd(percentQTD);
			BigDecimal percentValores = new BigDecimal((valorTotalInclusoes.doubleValue() *100) / valorTotalInclusoesNeg.doubleValue());
			retumoTotais.setPercentualValor(percentValores); 
		}
		sessao.setAttribute("colecaoResumoNegativacao", colecaoResumoNegativacao);


		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//      R E F E R E N T E    A    N E G A T I V A C O E S    I N C L U I D A S     E     C O N F I R M A D A S  
//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\		
		// Pesquisa os dados do resumo da Negociacao agrupando por idNegociador - Situação da negativacao.
		Collection<NegativacaoHelper>colecaoResumoNegativacaoConfirmadas = fachada.consultarResumoNegativacao(dadosConsultaNegativacaoHelper,3);
		sessao.setAttribute("colecaoResumoNegativacaoConfirmadas", colecaoResumoNegativacaoConfirmadas);
		
		Iterator itcolecaoResumoNegativacaoConfirmadas = colecaoResumoNegativacaoConfirmadas.iterator();
		Integer totalQuantidadeInclusoesConfirmadas = new Integer(0);
		BigDecimal valorTotalInclusoesConfirmadas   = new BigDecimal(0);
		while(itcolecaoResumoNegativacaoConfirmadas.hasNext()){
			NegativacaoHelper retumoTotaisConfirmadas = (NegativacaoHelper)itcolecaoResumoNegativacaoConfirmadas.next();
			totalQuantidadeInclusoesConfirmadas += retumoTotaisConfirmadas.getSomatorioQuantidadeInclusoes();
			valorTotalInclusoesConfirmadas = valorTotalInclusoesConfirmadas.add(retumoTotaisConfirmadas.getSomatorioValorDebito());
		}
		sessao.setAttribute("totalQuantidadeInclusoesConfirmadas",totalQuantidadeInclusoesConfirmadas);   
		sessao.setAttribute("valorTotalInclusoesConfirmadas",valorTotalInclusoesConfirmadas); 
		
		// Pesquisa os dados do resumo da Negociacao agrupando por cobrancaDebitoSituacao 
		Collection<NegativacaoHelper>colecaoResumoNegativacaoIncluidasConfirmadas = fachada.consultarResumoNegativacao(dadosConsultaNegativacaoHelper,5);
		
		Iterator itcolecaoResumoNegativacaoIncluidasConfirmadas = colecaoResumoNegativacaoIncluidasConfirmadas.iterator();
		Integer totalQuantidadeInclusoesIncluidasConfirmadas = new Integer(0);
		BigDecimal valorTotalInclusoesIncluidasConfirmadas   = new BigDecimal(0);
		while(itcolecaoResumoNegativacaoIncluidasConfirmadas.hasNext()){
			NegativacaoHelper retumoTotaisIncluidasConfirmadas = (NegativacaoHelper)itcolecaoResumoNegativacaoIncluidasConfirmadas.next();
			totalQuantidadeInclusoesIncluidasConfirmadas = retumoTotaisIncluidasConfirmadas.getSomatorioQuantidadeInclusoes();
			valorTotalInclusoesIncluidasConfirmadas = retumoTotaisIncluidasConfirmadas.getSomatorioValorDebito();

			// Calculo dos Percentuais
			BigDecimal percentQTD = new BigDecimal((totalQuantidadeInclusoesIncluidasConfirmadas.doubleValue() *100) / totalQuantidadeInclusoesConfirmadas.doubleValue()); 
			retumoTotaisIncluidasConfirmadas.setPercentualQtd(percentQTD);
			BigDecimal percentValores = new BigDecimal((valorTotalInclusoesIncluidasConfirmadas.doubleValue() *100) / valorTotalInclusoesConfirmadas.doubleValue());
			retumoTotaisIncluidasConfirmadas.setPercentualValor(percentValores); 
		}
		sessao.setAttribute("colecaoResumoNegativacaoIncluidasConfirmadas", colecaoResumoNegativacaoIncluidasConfirmadas);
		
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//      R E F E R E N T E    A    N E G A T I V A C O E S    I N C L U I D A S     E    N A O    C O N F I R M A D A S  
//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\		
		// Pesquisa os dados do resumo da Negociacao agrupando por idNegociador - Situação da negativacao.
		Collection<NegativacaoHelper>colecaoResumoNegativacaoNaoConfirmadas = fachada.consultarResumoNegativacao(dadosConsultaNegativacaoHelper,4);
		sessao.setAttribute("colecaoResumoNegativacaoNaoConfirmadas", colecaoResumoNegativacaoNaoConfirmadas);
		
		Iterator itcolecaoResumoNegativacaoNaoConfirmadas = colecaoResumoNegativacaoNaoConfirmadas.iterator();
		Integer totalQuantidadeInclusoesNaoConfirmadas = new Integer(0);
		BigDecimal valorTotalInclusoesNaoConfirmadas   = new BigDecimal(0);
		while(itcolecaoResumoNegativacaoNaoConfirmadas.hasNext()){
			NegativacaoHelper retumoTotaisNaoConfirmadas = (NegativacaoHelper)itcolecaoResumoNegativacaoNaoConfirmadas.next();
			totalQuantidadeInclusoesNaoConfirmadas += retumoTotaisNaoConfirmadas.getSomatorioQuantidadeInclusoes();
			valorTotalInclusoesNaoConfirmadas = valorTotalInclusoesNaoConfirmadas.add(retumoTotaisNaoConfirmadas.getSomatorioValorDebito());
		}
		sessao.setAttribute("totalQuantidadeInclusoesNaoConfirmadas",totalQuantidadeInclusoesNaoConfirmadas);   
		sessao.setAttribute("valorTotalInclusoesNaoConfirmadas",valorTotalInclusoesNaoConfirmadas);     

		
		// Pesquisa os dados do resumo da Negociacao agrupando por cobrancaDebitoSituacao 
		Collection<NegativacaoHelper>colecaoResumoNegativacaoIncluidasNaoConfirmadas = fachada.consultarResumoNegativacao(dadosConsultaNegativacaoHelper,6);
		Iterator itcolecaoResumoNegativacaoIncluidasNaoConfirmadas = colecaoResumoNegativacaoIncluidasNaoConfirmadas.iterator();
		Integer totalQuantidadeInclusoesIncluidasNaoConfirmadas = new Integer(0);
		BigDecimal valorTotalInclusoesIncluidasNaoConfirmadas   = new BigDecimal(0);
		while(itcolecaoResumoNegativacaoIncluidasNaoConfirmadas.hasNext()){
			NegativacaoHelper retumoTotaisIncluidasNaoConfirmadas = (NegativacaoHelper)itcolecaoResumoNegativacaoIncluidasNaoConfirmadas.next();
			totalQuantidadeInclusoesIncluidasNaoConfirmadas = retumoTotaisIncluidasNaoConfirmadas.getSomatorioQuantidadeInclusoes();
			valorTotalInclusoesIncluidasNaoConfirmadas = retumoTotaisIncluidasNaoConfirmadas.getSomatorioValorDebito();

			// Calculo dos Percentuais              
			BigDecimal percentQTD = new BigDecimal((totalQuantidadeInclusoesIncluidasNaoConfirmadas.doubleValue() *100) / totalQuantidadeInclusoesNaoConfirmadas.doubleValue());
			retumoTotaisIncluidasNaoConfirmadas.setPercentualQtd(percentQTD);
			BigDecimal percentValores = new BigDecimal((valorTotalInclusoesIncluidasNaoConfirmadas.doubleValue() *100) / valorTotalInclusoesNaoConfirmadas.doubleValue());
			retumoTotaisIncluidasNaoConfirmadas.setPercentualValor(percentValores); 
		}
		sessao.setAttribute("colecaoResumoNegativacaoIncluidasNaoConfirmadas", colecaoResumoNegativacaoIncluidasNaoConfirmadas);
		
 		return retorno;
	}
}