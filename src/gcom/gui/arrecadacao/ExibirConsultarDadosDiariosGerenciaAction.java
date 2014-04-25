package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao.GROUP_BY;
import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
 * @author Fernanda Paiva, Francisco Nascimento
 * @created 20 de Maio de 2006, 14 de novembro de 2008
**/
public class ExibirConsultarDadosDiariosGerenciaAction extends GcomAction {
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
				.findForward("exibirConsultarDadosDiariosGerencia");
		
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
			
			filtro.setAgrupamento(GROUP_BY.GERENCIA_REGIONAL);
			Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> 
			 mapDadosDiariosAnoMes = fachada.filtrarDadosDiariosArrecadacao(
				periodoArrecadacaoInicial,
				periodoArrecadacaoFinal,
				filtro);			
			
            for(Integer itemAnoMes : mapDadosDiariosAnoMes.keySet()){
            
            	BigDecimal valorTotal = new BigDecimal(0.0);
            	
        		BigDecimal totalDebitos = new BigDecimal(0.0);
        		BigDecimal totalDescontos = new BigDecimal(0.0);
        		BigDecimal totalArrecadacao = new BigDecimal(0.0);
        		BigDecimal totalDevolucoes = new BigDecimal(0.0);
        		BigDecimal totalArrecadacaoLiquida = new BigDecimal(0.0);        		
            	
            	for (FiltrarDadosDiariosArrecadacaoHelper helper : mapDadosDiariosAnoMes.get(itemAnoMes)){
            		valorTotal = valorTotal.add(helper.getValorArrecadacaoLiquida());
            	
            		totalDebitos = totalDebitos.add(helper.getValorDebitos());
            		totalDescontos = totalDescontos.add(helper.getValorDescontos());
            		totalArrecadacao = totalArrecadacao.add(helper.getValorArrecadacao());
            		totalDevolucoes = totalDevolucoes.add(helper.getValorDevolucoes());
            		totalArrecadacaoLiquida = totalArrecadacaoLiquida.add(helper.getValorArrecadacaoLiquida());            		
            		
            	}            	
        		colecaoValorTotal.add(valorTotal);
        		valorTotalPeriodo = valorTotalPeriodo.add(valorTotal);
        		
        		GerenciaRegional gerenciaTodos = new GerenciaRegional();
        		gerenciaTodos.setId(-1);
        		gerenciaTodos.setNomeAbreviado("");
        		gerenciaTodos.setNome("TODAS");
        		
        		FiltrarDadosDiariosArrecadacaoHelper helperTodos = new FiltrarDadosDiariosArrecadacaoHelper();
        		helperTodos.setItemAgrupado(gerenciaTodos);
        		helperTodos.setValorDebitos(totalDebitos);
        		helperTodos.setValorDescontos(totalDescontos);
        		helperTodos.setValorArrecadacao(totalArrecadacao);
        		helperTodos.setValorDevolucoes(totalDevolucoes);
        		helperTodos.setValorArrecadacaoLiquida(totalArrecadacaoLiquida);
        		helperTodos.setPercentual(new BigDecimal(100.00));
        		
        		mapDadosDiariosAnoMes.get(itemAnoMes).add(helperTodos);
	
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
            
            sessao.setAttribute("mapDadosDiariosAnoMes", mapDadosDiariosAnoMes);
            
            sessao.setAttribute("colecaoValorTotal", colecaoValorTotal);
            
            sessao.setAttribute("valorTotalPeriodo", valorTotalPeriodo);
            
            sessao.setAttribute("arrecadador", nomeArrecadador);
            
            SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
    		
    		if (sistemaParametro.getCdDadosDiarios() != null &&
    				sistemaParametro.getCdDadosDiarios() == 1){
    			
    			sessao.setAttribute("exibirFaturamentoCobrado", true);
    		}
			
		}
		
		
		
		
		
//		if(sessao.getAttribute("dadosArrecadacaoUnidadeNegocio") == null){
//		
//	        Collection colecaoArrecadacaoDadosDiariosGerencia = null;
//			
//			colecaoArrecadacaoDadosDiariosGerencia = (Collection) sessao
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
//	        		colecaoArrecadacaoDadosDiariosGerencia,	null, null, null, null,
//	        		null, null, null, null, null, null, null, false, false, false, false, false);
//	        
//			for (Iterator iter = itensAgrupadosAnoMes.iterator(); iter
//					.hasNext();) {
//				ArrecadacaoDadosDiariosItemAcumuladorHelper itemAnoMes = 
//					(ArrecadacaoDadosDiariosItemAcumuladorHelper) iter.next();
//
//				ArrecadacaoDadosDiariosAcumuladorHelper acumuladorHelperGerencia = new  ArrecadacaoDadosDiariosAcumuladorHelper(
//		        		ArrecadacaoDadosDiariosItemAcumuladorHelper.GROUP_BY_GERENCIA);
//		        
//				Collection<ArrecadacaoDadosDiariosItemAcumuladorHelper> itensAgrupadosPorGerenciaEmAnoMes = 
//					acumuladorHelperGerencia.aplicarFiltroEAcumularValores(        		
//		        		colecaoArrecadacaoDadosDiariosGerencia,	itemAnoMes.getAnoMesReferencia() + "", null, null, null,
//		        		null, null, null, null, null, null, null, false, true, false, false,false);
//
//				Collections.sort((List) itensAgrupadosPorGerenciaEmAnoMes,
//						new Comparator() {
//							public int compare(Object a, Object b) {
//								String codigo1 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) a)
//										.getGerenciaRegional().getNomeAbreviado();
//								String codigo2 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) b)
//										.getGerenciaRegional().getNomeAbreviado();
//								if (codigo1 == null || codigo1.equals("")) {
//									return -1;
//								} else {
//									return codigo1.compareTo(codigo2);
//								}
//							}
//						});
//								
//				mapAnoMes.put(itemAnoMes, itensAgrupadosPorGerenciaEmAnoMes);
//				
//				System.out.println("ValorTotal.agrupadoporgerencia["+itemAnoMes.getAnoMesReferencia()+"]="
//						+acumuladorHelperGerencia.getValorLiquidoTotal());	
//			}
//			
//			System.out.println("ValorTotal.agrupadoporanomes="+acumuladorHelper.getValorLiquidoTotal());	 
//			
//			sessao.setAttribute("dadosArrecadacaoUnidadeNegocio",mapAnoMes);//mapPrincipal);
//			sessao.setAttribute("valordadosArrecadacaoUnidadeNegocio",acumuladorHelper.getValorLiquidoTotal());
//		}
		
		return retorno;
	}
}
