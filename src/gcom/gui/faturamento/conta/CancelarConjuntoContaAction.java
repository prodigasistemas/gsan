package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMotivoCancelamento;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class CancelarConjuntoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("exibirCancelarConjuntoConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Instância do formulário que está sendo utilizado
        CancelarContaActionForm cancelarContaActionForm = (CancelarContaActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();     
        
        //MotivoCancelamentoConta selecinado pelo usuário
        ContaMotivoCancelamento contaMotivoCancelamento = new ContaMotivoCancelamento();
        contaMotivoCancelamento.setId(new Integer(cancelarContaActionForm.getMotivoCancelamentoContaID()));
        
        
        if (sessao.getAttribute("colecaoImovel") != null){
        
        	Collection<Conta> colecaoImovel = (Collection) sessao.getAttribute("colecaoImovel");      	
        	
        	Integer anoMes = null;
        	if(sessao.getAttribute("anoMes") != null){
        	  anoMes = (Integer)sessao.getAttribute("anoMes");	
        	}
        	
        	Integer anoMesFim = null;
        	if(sessao.getAttribute("anoMesFim") != null){
        	  anoMesFim = (Integer)sessao.getAttribute("anoMesFim");	
        	}
        	
        	Date dataVencimentoContaInicio = null;
    		Date dataVencimentoContaFim = null;
    		String indicadorContaPaga = null;    		
    		Integer idGrupoFaturamento = null;
    		
    		if (sessao.getAttribute("dataVencimentoContaInicial") != null){
    			
    			dataVencimentoContaInicio = (Date) sessao.getAttribute("dataVencimentoContaInicial"); 
    		}
    		
    		if (sessao.getAttribute("dataVencimentoContaFinal") != null){
    			
    			dataVencimentoContaFim = (Date) sessao.getAttribute("dataVencimentoContaFinal");
    		}
    		
    		if (sessao.getAttribute("indicadorContaPaga") != null){
    			
    			indicadorContaPaga = (String) sessao.getAttribute("indicadorContaPaga");
    		}
    		
    		if (sessao.getAttribute("idGrupoFaturamento") != null){
    			
    			idGrupoFaturamento = (Integer) sessao.getAttribute("idGrupoFaturamento");
    		}
        	
    		// Usuario logado no sistema
    		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
    		
    		/*Integer codigoCliente = null;
    		 if(sessao.getAttribute("codigoCliente") != null){
    			codigoCliente = new Integer((String)sessao.getAttribute("codigoCliente"));
    		}
    		
    		//Cancelando uma ou várias contas
    		 if(codigoCliente != null){
               Short relacaoTipo = null;
        	  if(sessao.getAttribute("relacaoTipo") != null){
       		    relacaoTipo = ((Integer)sessao.getAttribute("relacaoTipo")).shortValue();
        	  }
        	  fachada.cancelarConjuntoContaCliente(codigoCliente, relacaoTipo, contaMotivoCancelamento, anoMes,
        			  dataVencimentoContaInicio, dataVencimentoContaFim, anoMesFim, usuarioLogado);
        		fachada.cancelarConjuntoConta(colecaoImovel, contaMotivoCancelamento, anoMes, dataVencimentoContaInicio,
                		dataVencimentoContaFim, anoMesFim, usuarioLogado, indicadorContaPaga);	        		
        	}
        	else*/ if (idGrupoFaturamento != null){
        		
        		fachada.cancelarConjuntoConta(idGrupoFaturamento, contaMotivoCancelamento, anoMes, dataVencimentoContaInicio,
          		 dataVencimentoContaFim, anoMesFim, usuarioLogado);
        	}
        	else{
        		
        		fachada.cancelarConjuntoConta(colecaoImovel, contaMotivoCancelamento, anoMes, dataVencimentoContaInicio,
        		dataVencimentoContaFim, anoMesFim, usuarioLogado, indicadorContaPaga);	
        	}
        	//Realizar um reload na tela de manter conta
        	httpServletRequest.setAttribute("reloadPage", "OK");
        	
        }
        
        sessao.setAttribute("cancelar", "1");
        sessao.removeAttribute("anoMes");
        sessao.removeAttribute("anoMesFim");
        sessao.removeAttribute("dataVencimentoContaInicial");
        sessao.removeAttribute("dataVencimentoContaFinal");
        sessao.removeAttribute("indicadorContaPaga");
                
        return retorno;
    }

}
