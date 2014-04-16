package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao.GROUP_BY;
import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.cobranca.DocumentoTipo;
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
public class ExibirConsultarDadosDiariosDocumentoAgregadorAction extends GcomAction {
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
				.findForward("exibirConsultarDadosDiariosDocumentoAgregador");
		
		String referencia = httpServletRequest.getParameter("referencia");
		String idTipoDocumentoAgregador = httpServletRequest.getParameter("idDocumentoTipoAgregador");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroConsultarDadosDiariosArrecadacao filtro = (FiltroConsultarDadosDiariosArrecadacao)
			sessao.getAttribute("filtroConsultarDadosDiariosArrecadacao");
		Integer periodoArrecadacaoInicial = (Integer) 
			sessao.getAttribute("periodoArrecadacaoInicial");
		Integer periodoArrecadacaoFinal = (Integer) 
			sessao.getAttribute("periodoArrecadacaoFinal");
		
		sessao.setAttribute("referencia",referencia);
		sessao.setAttribute("descricaoDocumentoTipoAgregador", 
				httpServletRequest.getParameter("descricaoDocumentoTipoAgregador"));
		sessao.setAttribute("idTipoDocumentoAgregador",idTipoDocumentoAgregador);
				
		if (filtro != null){
			
			filtro = filtro.clone();
			
			filtro.setAgrupamento(GROUP_BY.TIPO_DOCUMENTO);
			
			if (idTipoDocumentoAgregador != null && !idTipoDocumentoAgregador.equals("") && 
					!idTipoDocumentoAgregador.equals("-1")){
				String idsDocumentoTipoAgregador[] = {idTipoDocumentoAgregador};
				filtro.setIdsDocumentoTipoAgregador(idsDocumentoTipoAgregador);
			}
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
	    	DocumentoTipo documentoTipo = new DocumentoTipo(); 
	    	documentoTipo.setId(-1);
	    	documentoTipo.setDescricaoDocumentoTipo("TODOS");
    		
    		FiltrarDadosDiariosArrecadacaoHelper helperTodos = new FiltrarDadosDiariosArrecadacaoHelper();
    		helperTodos.setItemAgrupado(documentoTipo);
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
		
//		Collection colecaoArrecadacaoDadosDiariosDocumentoTipo = new ArrayList();
//			
//		BigDecimal valorTotal = new BigDecimal("0.00");
//		
//        Collection colecaoArrecadacaoDadosDiarios = (Collection) sessao
//				.getAttribute("colecaoArrecadacaoDadosDiarios");
//        
//        httpServletRequest.setAttribute("descricaoDocumentoTipoAgregador", 
//        		httpServletRequest.getParameter("descricaoDocumentoTipoAgregador"));
//        httpServletRequest.setAttribute("idTipoDocumentoAgregador",idTipoDocumentoAgregador);
//        
//        ArrecadacaoDadosDiariosAcumuladorHelper acumuladorHelper = new  ArrecadacaoDadosDiariosAcumuladorHelper(
//        		ArrecadacaoDadosDiariosItemAcumuladorHelper.GROUP_BY_DOCUMENTO_TIPO);
//        
//        colecaoArrecadacaoDadosDiariosDocumentoTipo = acumuladorHelper.aplicarFiltroEAcumularValores(        		
//        		colecaoArrecadacaoDadosDiarios, 
//        		referencia, null, null, null, null, null, null, null, null, null, idTipoDocumentoAgregador, true, false, false, false, false);
//        
//        valorTotal = acumuladorHelper.getValorLiquidoTotal();
//        
//		Collections.sort((List) colecaoArrecadacaoDadosDiariosDocumentoTipo,
//				new Comparator() {
//					public int compare(Object a, Object b) {
//						String codigo1 = null;
//						if(((ArrecadacaoDadosDiariosItemAcumuladorHelper) a)
//								.getDocumentoTipo() != null && 
//							((ArrecadacaoDadosDiariosItemAcumuladorHelper) a)
//								.getDocumentoTipo().getId() != null){
//							codigo1 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) a)
//								.getDocumentoTipo().getId() + "";
//						}
//						
//						
//						String codigo2 = null;
//						if(((ArrecadacaoDadosDiariosItemAcumuladorHelper) b)
//								.getDocumentoTipo() != null && 
//							((ArrecadacaoDadosDiariosItemAcumuladorHelper) b)
//								.getDocumentoTipo().getId() != null){
//							codigo2 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) b)
//								.getDocumentoTipo().getId() + "";
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
//		sessao.setAttribute("colecaoDadosDiarios",colecaoArrecadacaoDadosDiariosDocumentoTipo);
//		
//		sessao.setAttribute("referencia",referencia);
//		sessao.setAttribute("valorTotal",valorTotal);
		return retorno;
	}
}
