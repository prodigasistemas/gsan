package gcom.gui.faturamento.conta;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoRealizado;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class RemoverSelecaoCreditoRealizadoAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
    	        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirRetificarConta");

        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        String creditoRealizadoUltimaAlteracao = httpServletRequest.getParameter("creditoRealizadoUltimaAlteracao");
        
        if (creditoRealizadoUltimaAlteracao != null && !creditoRealizadoUltimaAlteracao.equalsIgnoreCase("") &&
        	sessao.getAttribute("colecaoCreditoRealizado") != null){
        	
        	long creditoRealizadoUltimaAlteracaoLong = Long.parseLong(creditoRealizadoUltimaAlteracao);
        	
        	Collection colecaoCreditoRealizado = (Collection) sessao.getAttribute("colecaoCreditoRealizado");
        	
        	Iterator colecaoCreditoRealizadoIt = colecaoCreditoRealizado.iterator();
        	CreditoRealizado creditoRealizadoColecao;
        	
        	while (colecaoCreditoRealizadoIt.hasNext()){
        		creditoRealizadoColecao = (CreditoRealizado) colecaoCreditoRealizadoIt.next();
        		
        		if (GcomAction.obterTimestampIdObjeto(creditoRealizadoColecao) == creditoRealizadoUltimaAlteracaoLong){
        			colecaoCreditoRealizado.remove(creditoRealizadoColecao);
        			break;
        		}
        	}
        }
        
        
        //Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
        
        /*
		 * Colocado por Raphael Rossiter em 17/04/2007
		 * Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa
		 */
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		httpServletRequest.setAttribute("empresaNome", sistemaParametro.getNomeAbreviadoEmpresa().trim());
        
        
        return retorno;
    }

}

