package gcom.gui.gerencial.faturamento;

import gcom.fachada.Fachada;
import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gerencial.bean.ResumoFaturamentoSimulacaoDetalheHelper;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirResumoAnaliseFaturamentoDetalheAction extends GcomAction {
	
	 public ActionForward execute(ActionMapping actionMapping,
	            ActionForm actionForm, HttpServletRequest httpServletRequest,
	            HttpServletResponse httpServletResponse) {

	        //Seta o retorno
	        ActionForward retorno = actionMapping
	                .findForward("exibirResumoAnaliseFaturamentoDetalheAction");

	        //Obtém a instância da fachada
	        Fachada fachada = Fachada.getInstancia();

	        //Obtém a sessão
	        HttpSession sessao = httpServletRequest.getSession(false);
	        
	        String tipo = "";
	        
	        if(httpServletRequest.getParameter("tipo")!=null){
	        	tipo = httpServletRequest.getParameter("tipo");
	        	
	        	if(tipo.equalsIgnoreCase("DEBITO")){
	        		sessao.setAttribute("tipo", "DÉBITO");
	        	}else if(tipo.equalsIgnoreCase("CREDITO")){
	        		sessao.setAttribute("tipo", "CRÉDITO");
	        	}else{
	        		sessao.removeAttribute("tipo");
	        	}
	        }
	         
	        
	        InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = 
	        	(InformarDadosGeracaoRelatorioConsultaHelper)sessao.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");
	        
	        informarDadosGeracaoRelatorioConsultaHelper.setTipoDetalhe(tipo);
	        
	        Collection<Object[]> retornoConsulta = 
	        	fachada.consultarResumoAnaliseFaturamentoDetalhe(informarDadosGeracaoRelatorioConsultaHelper);
	        
	        Collection<ResumoFaturamentoSimulacaoDetalheHelper> colecaoDetalhes 
	        	= new ArrayList<ResumoFaturamentoSimulacaoDetalheHelper>(); 
	        
	        for (Object[] dados : retornoConsulta) {
	        	ResumoFaturamentoSimulacaoDetalheHelper  helper 
	        	 	= new ResumoFaturamentoSimulacaoDetalheHelper(
	        	 			(String)dados[0],
	        	 			Util.formatarMoedaReal((BigDecimal) dados[1]),
	        	 			"");
	        	
	        	colecaoDetalhes.add(helper);
			}
	        
	        sessao.setAttribute("colecaoDetalhes", colecaoDetalhes);
	        
	        return retorno;
	 }
}
