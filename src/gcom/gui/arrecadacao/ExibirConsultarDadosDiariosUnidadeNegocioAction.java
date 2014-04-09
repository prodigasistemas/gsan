package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao.GROUP_BY;
import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

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
 * @author Rafael Santos
 * @created 03/01/2007
**/
public class ExibirConsultarDadosDiariosUnidadeNegocioAction extends GcomAction {
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
				.findForward("exibirConsultarDadosDiariosUnidadeNegocio");
		
		String referencia = httpServletRequest.getParameter("referencia");
		
		String idGerencia = httpServletRequest.getParameter("idGerencia");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroConsultarDadosDiariosArrecadacao filtro = (FiltroConsultarDadosDiariosArrecadacao)
			sessao.getAttribute("filtroConsultarDadosDiariosArrecadacao");
		Integer periodoArrecadacaoInicial = (Integer) 
			sessao.getAttribute("periodoArrecadacaoInicial");
		Integer periodoArrecadacaoFinal = (Integer) 
			sessao.getAttribute("periodoArrecadacaoFinal");
		
		BigDecimal valorTotalGerencia = new BigDecimal(httpServletRequest.getParameter("valorTotalGerencia"));
		sessao.setAttribute("valorTotalGerencia", valorTotalGerencia);		
		sessao.setAttribute("idGerencia", idGerencia);
		
		if (idGerencia != null && !idGerencia.equals("") && !idGerencia.equals("-1")){
			
			// pesquisar na base a gerencia Regional
			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional ();
			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(FiltroGerenciaRegional.ID,
					idGerencia));
			
			Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional,
					GerenciaRegional.class.getName());
			
			GerenciaRegional gerenciaRegional = (GerenciaRegional) 
				Util.retonarObjetoDeColecao(colecaoGerenciaRegional); 
			sessao.setAttribute("nomeGerencia", gerenciaRegional.getNomeAbreviado() + " - " + gerenciaRegional.getNome());
			
		} else if (idGerencia.equals("-1")){
			sessao.setAttribute("nomeGerencia", "TODAS");
		} else {
			sessao.removeAttribute("nomeGerencia");
		}
		
		sessao.setAttribute("referencia", referencia);

		Integer anoMesAnterior = Util.subtrairMesDoAnoMes(new Integer(referencia).intValue(), 1);
		Integer idGerenciaRegional = null;
		if ( idGerencia != null && !idGerencia.equals("-1") ) {
			idGerenciaRegional = Util.converterStringParaInteger(idGerencia);
		}
		BigDecimal faturamentoCobradoEmConta = fachada.pesquisarFaturamentoCobradoEmContaComQuebra(anoMesAnterior, idGerenciaRegional, null);
		sessao.setAttribute("faturamentoCobradoEmConta", Util.formatarMoedaReal(faturamentoCobradoEmConta));
		
		if (filtro != null){
			
			filtro = filtro.clone();
			
			filtro.setAgrupamento(GROUP_BY.UNIDADE_NEGOCIO);
			filtro.setIdGerenciaRegional(idGerencia);
			filtro.setAnoMesArrecadacao(referencia);
			
			Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> 
			 mapDadosDiariosAnoMes = fachada.filtrarDadosDiariosArrecadacao(
				periodoArrecadacaoInicial,
				periodoArrecadacaoFinal,
				filtro);
			
        	BigDecimal valorTotal = new BigDecimal(0.0);
        	
       		BigDecimal totalDebitos = new BigDecimal(0.0);
    		BigDecimal totalDescontos = new BigDecimal(0.0);
    		BigDecimal totalArrecadacao = new BigDecimal(0.0);
    		BigDecimal totalDevolucoes = new BigDecimal(0.0);
    		BigDecimal totalArrecadacaoLiquida = new BigDecimal(0.0);   
        	
        	Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = 
        		mapDadosDiariosAnoMes.get(new Integer(referencia));
        	
        	for (FiltrarDadosDiariosArrecadacaoHelper helper : colecaoDadosDiarios){
        		valorTotal = valorTotal.add(helper.getValorArrecadacaoLiquida());
        		
        		totalDebitos = totalDebitos.add(helper.getValorDebitos());
        		totalDescontos = totalDescontos.add(helper.getValorDescontos());
        		totalArrecadacao = totalArrecadacao.add(helper.getValorArrecadacao());
        		totalDevolucoes = totalDevolucoes.add(helper.getValorDevolucoes());
        		totalArrecadacaoLiquida = totalArrecadacaoLiquida.add(helper.getValorArrecadacaoLiquida());            		
        		
        	}

    		UnidadeNegocio unidadeNegocioTodos = new UnidadeNegocio();
    		unidadeNegocioTodos.setId(-1);
    		unidadeNegocioTodos.setNomeAbreviado("");
    		unidadeNegocioTodos.setNome("TODAS");
    		
    		FiltrarDadosDiariosArrecadacaoHelper helperTodos = new FiltrarDadosDiariosArrecadacaoHelper();
    		helperTodos.setItemAgrupado(unidadeNegocioTodos);
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
		
//		Collection colecaoArrecadacaoDadosDiariosUnidadeNegocio = new ArrayList();
//		
//		sessao.removeAttribute("colecaoDadosDiarios");
//		sessao.removeAttribute("valorTotal");
//		sessao.removeAttribute("valorTotalUnidadeNegocio");
//		
//        Collection colecaoArrecadacaoDadosDiarios = (Collection) sessao
//		.getAttribute("colecaoArrecadacaoDadosDiarios");
//
//        BigDecimal valorTotal = new BigDecimal("0.00");
//        
//        Collection colecaoIdUnidadeNegocio = new ArrayList();
//        
//        ArrecadacaoDadosDiariosAcumuladorHelper acumuladorHelper = new  ArrecadacaoDadosDiariosAcumuladorHelper(
//        		ArrecadacaoDadosDiariosItemAcumuladorHelper.GROUP_BY_UNIDADE_NEGOCIO);
//        
//        colecaoArrecadacaoDadosDiariosUnidadeNegocio = acumuladorHelper.aplicarFiltroEAcumularValores(        		
//        		colecaoArrecadacaoDadosDiarios, 
//        		referencia, null, null, idGerencia, null, null, null,
//        		null, null, null, null, false, false, false, false, false);
//        
//        Collections.sort((List) colecaoArrecadacaoDadosDiariosUnidadeNegocio,
//				new Comparator() {
//					public int compare(Object a, Object b) {
//						String codigo1 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) a)
//								.getUnidadeNegocio().getNomeAbreviado();
//						String codigo2 = ((ArrecadacaoDadosDiariosItemAcumuladorHelper) b)
//								.getUnidadeNegocio().getNomeAbreviado();
//						if (codigo1 == null || codigo1.equals("")) {
//							return -1;
//						} else {
//							return codigo1.compareTo(codigo2);
//						}
//					}
//				});
//        
//        valorTotal = acumuladorHelper.getValorLiquidoTotal(); 
//        	
//        if(idGerencia != null){
//			FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
//
//			filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
//					FiltroArrecadacaoDadosDiarios.ID,
//					idGerencia));
//			
//			Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional,
//					GerenciaRegional.class.getName());
//			
//			if (colecaoGerenciaRegional != null
//					&& !colecaoGerenciaRegional.isEmpty()){
//				
//				GerenciaRegional dadosGerencia = (GerenciaRegional) ((List) colecaoGerenciaRegional).get(0);
//				
//				if (dadosGerencia.getNome() != null
//						&& !dadosGerencia.getNome().equals("")) {
//					httpServletRequest.setAttribute("nomeGerencia",dadosGerencia.getNome());
//				}
//			}
//		}
//		
//		if(colecaoArrecadacaoDadosDiariosUnidadeNegocio != null){
//			sessao.setAttribute("colecaoDadosDiarios",colecaoArrecadacaoDadosDiariosUnidadeNegocio);
//			sessao.setAttribute("valorTotalGerencia",valorTotal);
//			sessao.setAttribute("valorTotalUnidadeNegocio",valorTotal);
//		}
//		
//		
//		sessao.setAttribute("idGerencia", idGerencia);
//		sessao.setAttribute("referencia", referencia);
		return retorno;
	}
}
