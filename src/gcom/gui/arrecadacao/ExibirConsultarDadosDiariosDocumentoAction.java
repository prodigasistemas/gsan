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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao.GROUP_BY;
import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Fernanda Paiva
 * @created 24 de Maio de 2006
**/
public class ExibirConsultarDadosDiariosDocumentoAction extends GcomAction {
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
				.findForward("exibirConsultarDadosDiariosDocumento");
		
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroConsultarDadosDiariosArrecadacao filtro = (FiltroConsultarDadosDiariosArrecadacao)
			sessao.getAttribute("filtroConsultarDadosDiariosArrecadacao");
		Integer periodoArrecadacaoInicial = (Integer) 
			sessao.getAttribute("periodoArrecadacaoInicial");
		Integer periodoArrecadacaoFinal = (Integer) 
			sessao.getAttribute("periodoArrecadacaoFinal");
		
		FiltrarDadosDiariosArrecadacaoActionForm filtroDadosDiarios = (FiltrarDadosDiariosArrecadacaoActionForm)
			sessao.getAttribute("FiltrarDadosDiariosArrecadacaoActionForm");
		String nomeArrecadador = filtroDadosDiarios.getNomeArrecadador();
		
		Collection<BigDecimal> colecaoValorTotal = new ArrayList<BigDecimal>();
		BigDecimal valorTotalPeriodo = new BigDecimal(0.0);
		
		if (filtro != null){
			
			filtro = filtro.clone();

			Map<Integer, FiltrarDadosDiariosArrecadacaoHelper> mapDadosProcessamento = 
				new TreeMap<Integer, FiltrarDadosDiariosArrecadacaoHelper>();
			
			filtro.setAgrupamento(GROUP_BY.TIPO_DOCUMENTO_AGREGADOR);
			Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> 
			 mapDadosDiariosAnoMes = fachada.filtrarDadosDiariosArrecadacao(
				periodoArrecadacaoInicial,
				periodoArrecadacaoFinal,
				filtro);
			
			Map<Integer, Boolean> flagDocumentoAgregador = new HashMap<Integer, Boolean>();
			
	        for(Integer itemAnoMes : mapDadosDiariosAnoMes.keySet()){
	        
	        	BigDecimal valorTotal = new BigDecimal(0.0);
	        	
	        	DocumentoTipo documentoTipoTodos = new DocumentoTipo();
	        	documentoTipoTodos.setId(-1);
	        	documentoTipoTodos.setDescricaoDocumentoTipo("TODOS");
	        	
	        	FiltrarDadosDiariosArrecadacaoHelper helperTodos = new FiltrarDadosDiariosArrecadacaoHelper();
	        	helperTodos.setItemAgrupado(documentoTipoTodos);
	        	int qtdDocumentos = 0;
	        	int qtdPagamentos = 0;
	        	BigDecimal valorDebitos = new BigDecimal(0.0);
	        	BigDecimal valorDescontos = new BigDecimal(0.0);
	        	BigDecimal valorArrecadacao = new BigDecimal(0.0);
	        	BigDecimal valorDevolucoes = new BigDecimal(0.0);
	        	BigDecimal valorArrecadacaoLiquida = new BigDecimal(0.0);
	        	
	        	for (FiltrarDadosDiariosArrecadacaoHelper helper : mapDadosDiariosAnoMes.get(itemAnoMes)){
	        		valorTotal = valorTotal.add(helper.getValorArrecadacaoLiquida());

	        		DocumentoTipo documentoTipo = (DocumentoTipo) helper.getItemAgrupado();
	        		
	        		boolean ehDocumentoTipoAgregador = false;
	        		
	        		// verificar se o tipo de documento é agregador
	        		if (documentoTipo.getIndicadorAgregador() != null && 
	        			documentoTipo.getIndicadorAgregador().equals(new Short("1"))) {
	        			ehDocumentoTipoAgregador = true;
	        		}

	        		flagDocumentoAgregador.put(documentoTipo.getId(), ehDocumentoTipoAgregador);
	        		
	        		qtdDocumentos += helper.getQuantidadeDocumentos();
	        		qtdPagamentos += helper.getQuantidadePagamentos();
	        		valorDebitos = valorDebitos.add(helper.getValorDebitos());
	        		valorDescontos = valorDescontos.add(helper.getValorDescontos());
	        		valorArrecadacao = valorArrecadacao.add(helper.getValorArrecadacao());
	        		valorDevolucoes = valorDevolucoes.add(helper.getValorDevolucoes());
	        		valorArrecadacaoLiquida = valorArrecadacaoLiquida.add(helper.getValorArrecadacaoLiquida());
	        		
	        	}
	        	
	        	helperTodos.setQuantidadeDocumentos(qtdDocumentos);
	        	helperTodos.setQuantidadePagamentos(qtdPagamentos);
	        	helperTodos.setValorDebitos(valorDebitos);
	        	helperTodos.setValorDescontos(valorDescontos);
	        	helperTodos.setValorArrecadacao(valorArrecadacao);
	        	helperTodos.setValorDevolucoes(valorDevolucoes);
	        	helperTodos.setValorArrecadacaoLiquida(valorArrecadacaoLiquida);
	        	helperTodos.setPercentual(new BigDecimal(100.00));
	        	
	        	mapDadosDiariosAnoMes.get(itemAnoMes).add(helperTodos);	        	
	        	flagDocumentoAgregador.put(documentoTipoTodos.getId(), true);
	        	
	    		colecaoValorTotal.add(valorTotal);
	    		valorTotalPeriodo = valorTotalPeriodo.add(valorTotal);
	    		
	    		Date dataMesInformado = fachada.pesquisarDataProcessamentoMes(itemAnoMes);
        		Date dataAtual = fachada.pesquisarDataProcessamentoMes(this.getSistemaParametro().getAnoMesArrecadacao());
        		
        		
        		FiltrarDadosDiariosArrecadacaoHelper helperDadasProcessamento =
        			new FiltrarDadosDiariosArrecadacaoHelper();
        		
        		if(dataMesInformado!=null){
        			helperDadasProcessamento
        				.setUltimoProcessamentoMesInformado(Util.formatarDataComHora(dataMesInformado));
        		}
        		if(dataAtual!=null){
        			helperDadasProcessamento
        				.setUltimoProcessamentoAtualSistema(Util.formatarDataComHora(dataAtual));
        		}
        		
        		if ( itemAnoMes >= this.getSistemaParametro().getAnoMesArrecadacao() ){
        			
//    				httpServletRequest.setAttribute("tipoProcessamento","provisorio");
        			helperDadasProcessamento.setTipoProcessamento("provisorio");
    				
    			}else{
    				
//    				httpServletRequest.setAttribute("tipoProcessamento","definitivo");
    				helperDadasProcessamento.setTipoProcessamento("definitivo");
    				
    			}
        		
        		Integer anoMesAnterior = Util.subtrairMesDoAnoMes(itemAnoMes, 1);
        		
        		BigDecimal faturamentoCobradoEmConta = fachada.pesquisarFaturamentoCobradoEmConta(anoMesAnterior);
        		
        		helperDadasProcessamento.setFaturamentoCobradoEmConta(
        				Util.formatarMoedaReal(faturamentoCobradoEmConta));
        		
        		mapDadosProcessamento.put(itemAnoMes, helperDadasProcessamento);
			}
	        
	        sessao.setAttribute("mapDadosProcessamento", mapDadosProcessamento);
	        
	        sessao.setAttribute("mapDocumentoAgregador", flagDocumentoAgregador);
	        
	        sessao.setAttribute("mapDadosDiariosAnoMes", mapDadosDiariosAnoMes);
	        
	        sessao.setAttribute("colecaoValorTotal", colecaoValorTotal);
	        sessao.setAttribute("colecaoValorTotal", colecaoValorTotal);
			
	        sessao.setAttribute("valorTotalPeriodo", valorTotalPeriodo);
	        
	        sessao.setAttribute("arrecadador", nomeArrecadador);
	        
	        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
	        
	        if (sistemaParametro.getCdDadosDiarios() != null &&
					sistemaParametro.getCdDadosDiarios() == 1){
				
				sessao.setAttribute("exibirFaturamentoCobrado", true);
			}
			
		}
		
//		BigDecimal valor = new BigDecimal("0.00");
//
//		
//		if(sessao.getAttribute("dadosArrecadacaoDocumento") == null){
//		
//	        Collection colecaoArrecadacaoDadosDiariosDocumento = null;
//			
//			colecaoArrecadacaoDadosDiariosDocumento = (Collection) sessao
//					.getAttribute("colecaoArrecadacaoDadosDiarios");
//		
//			Comparator comparadorAnoMes = new Comparator(){
//				public int compare(Object a, Object b) {
//					String codigo1 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) a)
//						.getAnoMesReferencia() + "";
//					String codigo2 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) b)
//						.getAnoMesReferencia() + "";
//					if (codigo1 == null || codigo1.equals("")) {
//						return -1;
//					} else {
//						return codigo1.compareTo(codigo2);
//					}
//				}								
//			};
//			
//			Map<ArrecadacaoDadosDiariosItemAcumuladorHelper,
//				Collection<ArrecadacaoDadosDiariosItemAcumuladorHelper>> mapAnoMes = new TreeMap(comparadorAnoMes);
//			
//			ArrecadacaoDadosDiariosAcumuladorHelper acumuladorHelper = new  ArrecadacaoDadosDiariosAcumuladorHelper(
//	        		ArrecadacaoDadosDiariosItemAcumuladorHelper.GROUP_BY_ANO_MES);
//	        
//			Collection<ArrecadacaoDadosDiariosItemAcumuladorHelper> itensAgrupadosAnoMes = 
//				acumuladorHelper.aplicarFiltroEAcumularValores(        		
//	        		colecaoArrecadacaoDadosDiariosDocumento, null, null, null, null,
//	        		null, null, null, null, null, null, null, false, false, false,false, false);
//	        
//			for (Iterator iter = itensAgrupadosAnoMes.iterator(); iter
//					.hasNext();) {
//				ArrecadacaoDadosDiariosItemAcumuladorHelper itemAnoMes = 
//					(ArrecadacaoDadosDiariosItemAcumuladorHelper) iter.next();
//	
//				ArrecadacaoDadosDiariosAcumuladorHelper acumuladorHelperDocumento = new  ArrecadacaoDadosDiariosAcumuladorHelper(
//		        		ArrecadacaoDadosDiariosItemAcumuladorHelper.GROUP_BY_DOCUMENTO_AGREGADOR);
//		        
//				Collection<ArrecadacaoDadosDiariosItemAcumuladorHelper> itensAgrupadosPorDocumentoEmAnoMes = 
//					acumuladorHelperDocumento.aplicarFiltroEAcumularValores(        		
//		        		colecaoArrecadacaoDadosDiariosDocumento, itemAnoMes.getAnoMesReferencia() + "", null, null, null,
//		        		null, null, null, null, null, null, null, false, false, false, false, true);
//	
//				Collections.sort((List) itensAgrupadosPorDocumentoEmAnoMes,
//						new Comparator() {
//							public int compare(Object a, Object b) {
//								String codigo1 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) a)
//										.getDocumentoTipoAgregador().getDescricaoDocumentoTipo();
//								String codigo2 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) b)
//										.getDocumentoTipoAgregador().getDescricaoDocumentoTipo();
//								if (codigo1 == null || codigo1.equals("")) {
//									return -1;
//								} else {
//									return codigo1.compareTo(codigo2);
//								}
//							}
//						});
//								
//				mapAnoMes.put(itemAnoMes, itensAgrupadosPorDocumentoEmAnoMes);
//				
//				System.out.println("ValorTotal.agrupadoporDocumento["+itemAnoMes.getAnoMesReferencia()+"]="
//						+acumuladorHelperDocumento.getValorLiquidoTotal());	
//			}
//			
//			valor = acumuladorHelper.getValorLiquidoTotal();
//			
//			sessao.setAttribute("dadosArrecadacaoDocumento",mapAnoMes);
//			sessao.setAttribute("valordadosArrecadacaoDocumento",valor);
//	
//		}
		
		return retorno;
	}
}