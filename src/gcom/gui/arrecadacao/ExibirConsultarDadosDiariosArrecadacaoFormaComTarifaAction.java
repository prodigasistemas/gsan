package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacaoAuxiliar;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao.GROUP_BY;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacaoAuxiliar.GROUP_BY_AUX;
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
public class ExibirConsultarDadosDiariosArrecadacaoFormaComTarifaAction extends GcomAction {
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
				.findForward("exibirConsultarDadosDiariosArrecadacaoFormaComTarifa");
		
		String referencia = httpServletRequest.getParameter("referencia");
		
		String idArrecadador = httpServletRequest.getParameter("idArrecadadorPopup");
		httpServletRequest.setAttribute("idArrecadadorPopup", idArrecadador);
		
		httpServletRequest.setAttribute("nomeAgente", httpServletRequest.getParameter("nomeAgente"));
		
		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro = (FiltroConsultarDadosDiariosArrecadacaoAuxiliar)
			sessao.getAttribute("filtroConsultarDadosDiariosArrecadacaoAuxiliar");
		Integer periodoArrecadacaoInicial = (Integer) 
			sessao.getAttribute("periodoArrecadacaoInicial");
		Integer periodoArrecadacaoFinal = (Integer) 
			sessao.getAttribute("periodoArrecadacaoFinal");

		String valorTotalArrecadador = httpServletRequest.getParameter("valorTotalArrecadador");
		httpServletRequest.setAttribute("valorTotalArrecadador", new BigDecimal(valorTotalArrecadador));
						
		httpServletRequest.setAttribute("referencia", referencia);
		
		if (filtro != null){
			
			filtro = filtro.clone();
			
			filtro.setAgrupamento(GROUP_BY_AUX.FORMA_ARRECADACAO);
			filtro.setAnoMesArrecadacao(referencia);
			filtro.setIdArrecadador(idArrecadador);
			
			Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> 
			 mapDadosDiariosAnoMes = fachada.filtrarDadosDiariosArrecadacaoAuxiliar(
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
	    	
	        httpServletRequest.setAttribute("colecaoDadosDiarios", colecaoDadosDiarios);	        
	        httpServletRequest.setAttribute("valorTotal", valorTotal);	     
	        

	        Date dataMesInformado = fachada.pesquisarDataProcessamentoMes(new Integer(referencia));
    		Date dataAtual = fachada.pesquisarDataProcessamentoMes(this.getSistemaParametro().getAnoMesArrecadacao());

    		
    		if(dataMesInformado!=null){ 			
    			httpServletRequest
					.setAttribute("dadosMesInformado", 
						Util.formatarDataComHora(dataMesInformado));
    		}
    		if(dataAtual!=null){   			
    			httpServletRequest
					.setAttribute("dadosAtual", 
						Util.formatarDataComHora(dataAtual));
    		}	
			
		}

		return retorno;
	}
}
