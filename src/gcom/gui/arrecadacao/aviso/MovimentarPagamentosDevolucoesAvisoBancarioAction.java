package gcom.gui.arrecadacao.aviso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import gcom.arrecadacao.aviso.bean.MovimentarPagamentosDevolucoesHelper;
import gcom.arrecadacao.aviso.bean.PagamentosDevolucoesHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0611] Movimentar Pagamentos/ Devoluções entre Avisos Bancários
 * 
 * @author Ana Maria 
 * 
 * @date 11/06/2007
 */

public class MovimentarPagamentosDevolucoesAvisoBancarioAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        Fachada fachada = Fachada.getInstancia();
        
        MovimentarPagamentosDevolucoesAvisoBancarioActionForm form = (MovimentarPagamentosDevolucoesAvisoBancarioActionForm) actionForm;
        
        Integer idAvisoBancarioO = (Integer) sessao.getAttribute("avisoBancarioO");
        Integer idAvisoBancarioD = (Integer) sessao.getAttribute("avisoBancarioD");
        
        if(sessao.getAttribute("pagamentoHelper") != null){
        	
        	PagamentosDevolucoesHelper helper = (PagamentosDevolucoesHelper)sessao.getAttribute("pagamentoHelper");
        	if (helper.getColecaoMovimentarPagamentos() != null){
        		Collection<Integer> idsPagamentos = new ArrayList(); 
        		Collection pagamentos = (Collection)helper.getColecaoMovimentarPagamentos();
        		Iterator colecaoPagamentosIterator = pagamentos.iterator();
        		while (colecaoPagamentosIterator.hasNext()) {
        			MovimentarPagamentosDevolucoesHelper pagamento = (MovimentarPagamentosDevolucoesHelper) colecaoPagamentosIterator.next();
        			idsPagamentos.add(pagamento.getId());
			
        		}        		
        		 fachada.atualizarAvisoBancarioPagamentos(idsPagamentos, form.getArrecadacaoInformadoDepoisOrigem(),form.getArrecadacaoCalculadoDepoisOrigem(), 
                		 form.getArrecadacaoInformadoDepoisDestino(), form.getArrecadacaoCalculadoDepoisDestino(), idAvisoBancarioO, idAvisoBancarioD);
        	}            
        	
        }
        
        if(sessao.getAttribute("devolucaoHelper") != null){
        	
        	PagamentosDevolucoesHelper helper = (PagamentosDevolucoesHelper)sessao.getAttribute("devolucaoHelper");
        	if (helper.getColecaoMovimentarDevolucoes() != null){
        		Collection<Integer> idsDevolucoes = new ArrayList(); 
        		Collection devolucoes = (Collection)helper.getColecaoMovimentarDevolucoes();
        		Iterator colecaoDevolucoesIterator = devolucoes.iterator();
        		while (colecaoDevolucoesIterator.hasNext()) {
        			MovimentarPagamentosDevolucoesHelper devolucao = (MovimentarPagamentosDevolucoesHelper) colecaoDevolucoesIterator.next();
        			idsDevolucoes.add(devolucao.getId());
			
        		}        		
        	     fachada.atualizarAvisoBancarioDevolucoes(idsDevolucoes, form.getDevolucaoInformadoDepoisOrigem(), form.getDevolucaoCalculadoDepoisOrigem(), 
                  		 form.getDevolucaoInformadoDepoisDestino(), form.getDevolucaoCalculadoDepoisDestino(), idAvisoBancarioO, idAvisoBancarioD);
        	}             
            	
        }
        
		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Pagamentos/Devoluções foram movimentados com sucesso do Aviso Bancário "+sessao.getAttribute("descricaoABOrigem")
				+" para o Aviso Bancário "+sessao.getAttribute("descricaoABDestino"),
				"Realizar outra Movimentação", "exibirSelecionarPagamentosAvisoBancarioAction.do?menu=sim");
        
		return retorno;
    }

}
