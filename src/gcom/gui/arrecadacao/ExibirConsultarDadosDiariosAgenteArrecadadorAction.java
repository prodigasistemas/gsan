package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao.GROUP_BY;
import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

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
 * @created 26 de Maio de 2006
**/
public class ExibirConsultarDadosDiariosAgenteArrecadadorAction extends GcomAction {
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
				.findForward("exibirConsultarDadosDiariosAgente");
		
		String referencia = httpServletRequest.getParameter("referencia");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroConsultarDadosDiariosArrecadacao filtro = (FiltroConsultarDadosDiariosArrecadacao)
			sessao.getAttribute("filtroConsultarDadosDiariosArrecadacao");
		Integer periodoArrecadacaoInicial = (Integer) 
			sessao.getAttribute("periodoArrecadacaoInicial");
		Integer periodoArrecadacaoFinal = (Integer) 
			sessao.getAttribute("periodoArrecadacaoFinal");
						
		sessao.setAttribute("referencia", referencia);

		Integer anoMesAnterior = Util.subtrairMesDoAnoMes(new Integer(referencia).intValue(), 1);
		BigDecimal faturamentoCobradoEmConta = fachada.pesquisarFaturamentoCobradoEmConta(anoMesAnterior);
		sessao.setAttribute("faturamentoCobradoEmConta", Util.formatarMoedaReal(faturamentoCobradoEmConta));
		
		if (filtro != null){
			
			filtro = filtro.clone();
			
			filtro.setAgrupamento(GROUP_BY.ARRECADADOR);
			filtro.setAnoMesArrecadacao(referencia);
			
			Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> 
			 mapDadosDiariosAnoMes = fachada.filtrarDadosDiariosArrecadacao(
				periodoArrecadacaoInicial,
				periodoArrecadacaoFinal,
				filtro);
			
        	BigDecimal valorTotal = new BigDecimal(0.0);
        	
        	Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = 
        		mapDadosDiariosAnoMes.get(new Integer(referencia));
        	
        	for (FiltrarDadosDiariosArrecadacaoHelper helper : colecaoDadosDiarios){
        		valorTotal = valorTotal.add(helper.getValorArrecadacaoLiquida());
        	}
        	
        	sessao.setAttribute("colecaoDadosDiarios", colecaoDadosDiarios);	        
        	sessao.setAttribute("valorTotal", valorTotal);	 
	        
	        Date dataMesInformado = fachada.pesquisarDataProcessamentoMes(new Integer(referencia));
    		Date dataAtual = fachada.pesquisarDataProcessamentoMes(this.getSistemaParametro().getAnoMesArrecadacao());

    		
    		if(dataMesInformado!=null){ 			
    			sessao
					.setAttribute("dadosMesInformado", 
						Util.formatarDataComHora(dataMesInformado));
    		} else {
    			sessao.removeAttribute("dadosMesInformado");
    		}
    		if(dataAtual!=null){   			
    			sessao
					.setAttribute("dadosAtual", 
						Util.formatarDataComHora(dataAtual));
    		} else {
    			sessao.removeAttribute("dadosAtual");
    		}
		} else {
			sessao.removeAttribute("colecaoDadosDiarios");
			sessao.removeAttribute("valorTotal");
			sessao.removeAttribute("dadosMesInformado");
			sessao.removeAttribute("dadosAtual");
		}
		
//		Collection colecaoArrecadacaoDadosDiariosArrecadador = new ArrayList();
//		
//		BigDecimal valorTotal = new BigDecimal("0.00");
//		
//		String descricao = "ARRECADADOR";
//		
//        Collection colecaoArrecadacaoDadosDiarios = (Collection) sessao
//				.getAttribute("colecaoArrecadacaoDadosDiarios");
//
//        ArrecadacaoDadosDiariosAcumuladorHelper acumuladorHelper = new  ArrecadacaoDadosDiariosAcumuladorHelper(
//        		ArrecadacaoDadosDiariosItemAcumuladorHelper.GROUP_BY_ARRECADADOR);
//        
//        colecaoArrecadacaoDadosDiariosArrecadador = acumuladorHelper.aplicarFiltroEAcumularValores(        		
//        		colecaoArrecadacaoDadosDiarios, 
//        		referencia, null, null, null, null, null, null, null, null, null, null, true,false, false, false, false);
//        
//        valorTotal = acumuladorHelper.getValorLiquidoTotal();
//                	
//		Collections.sort((List) colecaoArrecadacaoDadosDiariosArrecadador,
//				new Comparator() {
//					public int compare(Object a, Object b) {
//						String codigo1 = null;
//						if(((ArrecadacaoDadosDiariosItemAcumuladorHelper) a)
//								.getArrecadador() != null && 
//							((ArrecadacaoDadosDiariosItemAcumuladorHelper) a)
//								.getArrecadador().getCliente() != null){
//							codigo1 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) a)
//								.getArrecadador().getCliente().getNome();
//						}
//						
//						
//						String codigo2 = null;
//						if(((ArrecadacaoDadosDiariosItemAcumuladorHelper) b)
//								.getArrecadador() != null && 
//							((ArrecadacaoDadosDiariosItemAcumuladorHelper) b)
//								.getArrecadador().getCliente() != null){
//							codigo2 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) b)
//								.getArrecadador().getCliente().getNome();
//						}
//						
//						if (codigo1 == null || codigo1.equals("")) {
//							return -1;
//						} else {
//							return codigo1.compareTo(codigo2);
//						}
//					}
//				});
//        
//        
//		sessao.setAttribute("colecaoDadosDiarios",colecaoArrecadacaoDadosDiariosArrecadador);
//		
//		
//		
//		sessao.setAttribute("referencia",referencia);
//		sessao.setAttribute("valorTotal",valorTotal);
		return retorno;
	}
}
