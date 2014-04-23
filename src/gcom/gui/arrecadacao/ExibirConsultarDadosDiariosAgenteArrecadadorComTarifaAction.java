package gcom.gui.arrecadacao;

import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacaoAuxiliar;
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
public class ExibirConsultarDadosDiariosAgenteArrecadadorComTarifaAction extends GcomAction {
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
				.findForward("exibirConsultarDadosDiariosAgenteComTarifa");
		
		String referencia = httpServletRequest.getParameter("referencia");
		
		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando implementar a parte de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro = (FiltroConsultarDadosDiariosArrecadacaoAuxiliar)
			sessao.getAttribute("filtroConsultarDadosDiariosArrecadacaoAuxiliar");
		Integer periodoArrecadacaoInicial = (Integer) 
			sessao.getAttribute("periodoArrecadacaoInicial");
		Integer periodoArrecadacaoFinal = (Integer) 
			sessao.getAttribute("periodoArrecadacaoFinal");
						
		httpServletRequest.setAttribute("referencia", referencia);
		
		if (filtro != null){
			
			filtro = filtro.clone();
			
			filtro.setAgrupamento(GROUP_BY_AUX.ARRECADADOR);
			filtro.setAnoMesArrecadacao(referencia);
			
			Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> 
			 mapDadosDiariosAnoMes = fachada.filtrarDadosDiariosArrecadacaoAuxiliar(
				periodoArrecadacaoInicial,
				periodoArrecadacaoFinal,
				filtro);
			
        	BigDecimal valorTotal = new BigDecimal(0.0);
        	
        	Collection<FiltrarDadosDiariosArrecadacaoHelper> colecaoDadosDiarios = 
        		mapDadosDiariosAnoMes.get(new Integer(referencia));
        	
        	for (FiltrarDadosDiariosArrecadacaoHelper helper : colecaoDadosDiarios){
        		valorTotal = valorTotal.add(helper.getValorArrecadacaoLiquida());
        	}
        	
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
