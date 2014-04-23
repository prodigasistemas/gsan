package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
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
public class ExibirConsultarDadosDiariosArrecadacaoFormaAction extends GcomAction {
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
				.findForward("exibirConsultarDadosDiariosArrecadacaoForma");

		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String referencia = httpServletRequest.getParameter("referencia");
		
		String idArrecadador = httpServletRequest.getParameter("idArrecadadorPopup");
		sessao.setAttribute("idArrecadadorPopup", idArrecadador);
		
		sessao.setAttribute("nomeAgente", httpServletRequest.getParameter("nomeAgente"));
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroConsultarDadosDiariosArrecadacao filtro = (FiltroConsultarDadosDiariosArrecadacao)
			sessao.getAttribute("filtroConsultarDadosDiariosArrecadacao");
		Integer periodoArrecadacaoInicial = (Integer) 
			sessao.getAttribute("periodoArrecadacaoInicial");
		Integer periodoArrecadacaoFinal = (Integer) 
			sessao.getAttribute("periodoArrecadacaoFinal");

		String valorTotalArrecadador = httpServletRequest.getParameter("valorTotalArrecadador");
		sessao.setAttribute("valorTotalArrecadador", new BigDecimal(valorTotalArrecadador));
						
		sessao.setAttribute("referencia", referencia);
		
		if (filtro != null){
			
			filtro = filtro.clone();
			
			filtro.setAgrupamento(GROUP_BY.FORMA_ARRECADACAO);
			filtro.setAnoMesArrecadacao(referencia);
			filtro.setIdArrecadador(idArrecadador);
			
			Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> 
			 mapDadosDiariosAnoMes = fachada.filtrarDadosDiariosArrecadacao(
				periodoArrecadacaoInicial,
				periodoArrecadacaoFinal,
				filtro);
			
	    	BigDecimal valorTotal = new BigDecimal(0.0);

	    	// variaveis para gerar a linha de TODAS para as arrecadacao formas
	    	int qtdDocumentos = 0;
	    	int qtdPagamentos = 0;
       		BigDecimal totalDebitos = new BigDecimal(0.0);
    		BigDecimal totalDescontos = new BigDecimal(0.0);
    		BigDecimal totalArrecadacao = new BigDecimal(0.0);
    		BigDecimal totalDevolucoes = new BigDecimal(0.0);
    		BigDecimal totalArrecadacaoLiquida = new BigDecimal(0.0);   	    	
	    	
	    	Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = 
	    		mapDadosDiariosAnoMes.get(new Integer(referencia));
	    	
	    	for (FiltrarDadosDiariosArrecadacaoHelper helper : colecaoDadosDiarios){
	    		valorTotal = valorTotal.add(helper.getValorArrecadacaoLiquida());

	    		qtdDocumentos += helper.getQuantidadeDocumentos();
	    		qtdPagamentos += helper.getQuantidadePagamentos();
	    		totalDebitos = totalDebitos.add(helper.getValorDebitos());
        		totalDescontos = totalDescontos.add(helper.getValorDescontos());
        		totalArrecadacao = totalArrecadacao.add(helper.getValorArrecadacao());
        		totalDevolucoes = totalDevolucoes.add(helper.getValorDevolucoes());
        		totalArrecadacaoLiquida = totalArrecadacaoLiquida.add(helper.getValorArrecadacaoLiquida());            		
	    		
	    	}
	    	
	    	// criando helper para ser incluido como um ultimo item da lista 
	    	ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma(); 
	    	arrecadacaoForma.setId(-1);
	    	arrecadacaoForma.setDescricao("TODAS");
    		
    		FiltrarDadosDiariosArrecadacaoHelper helperTodos = new FiltrarDadosDiariosArrecadacaoHelper();
    		helperTodos.setItemAgrupado(arrecadacaoForma);
    		helperTodos.setQuantidadeDocumentos(qtdDocumentos);
    		helperTodos.setQuantidadePagamentos(qtdPagamentos);
    		helperTodos.setValorDebitos(totalDebitos);
    		helperTodos.setValorDescontos(totalDescontos);
    		helperTodos.setValorArrecadacao(totalArrecadacao);
    		helperTodos.setValorDevolucoes(totalDevolucoes);
    		helperTodos.setValorArrecadacaoLiquida(totalArrecadacaoLiquida);
    		helperTodos.setPercentual(new BigDecimal(100.00));
    		
    		colecaoDadosDiarios.add(helperTodos);
	    	
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
    			httpServletRequest
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
//        Collection colecaoArrecadacaoDadosDiarios = (Collection) sessao
//				.getAttribute("colecaoArrecadacaoDadosDiarios");
//
//        ArrecadacaoDadosDiariosAcumuladorHelper acumuladorHelper = new  ArrecadacaoDadosDiariosAcumuladorHelper(
//        		ArrecadacaoDadosDiariosItemAcumuladorHelper.GROUP_BY_ARRECADACAO_FORMA);
//        
//        colecaoArrecadacaoDadosDiariosArrecadador = acumuladorHelper.aplicarFiltroEAcumularValores(        		
//        		colecaoArrecadacaoDadosDiarios, 
//        		referencia, null, null, null, null, idArrecadador, null, null, null, null, null, true, false, false, false, false);
//        
//        valorTotal = acumuladorHelper.getValorLiquidoTotal();       
//        	 
//		Collections.sort((List) colecaoArrecadacaoDadosDiariosArrecadador,
//				new Comparator() {
//					public int compare(Object a, Object b) {
//						Integer codigo1 = null;
//						if(((ArrecadacaoDadosDiariosItemAcumuladorHelper) a)
//								.getArrecadacaoForma() != null){
//							codigo1 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) a)
//								.getArrecadacaoForma().getId();
//						}
//						
//						
//						Integer codigo2 = null;
//						if(((ArrecadacaoDadosDiariosItemAcumuladorHelper) b)
//								.getArrecadacaoForma() != null){
//							codigo2 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) b)
//							.getArrecadacaoForma().getId();
//						}
//						
//						if (codigo1 == null) {
//							return -1;
//						} else if (codigo2 == null){
//							return 1;
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
//		httpServletRequest.setAttribute("idArrecadadorPopup", idArrecadador);
//		sessao.setAttribute("referencia",referencia);
//		sessao.setAttribute("valorTotal",valorTotal);

		return retorno;
	}
}
