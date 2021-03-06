package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaMotivoRetificacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class RetirarDebitoCobradoConjuntoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("exibirRetirarDebitoCobradoConjuntoConta");
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Inst�ncia do formul�rio que est� sendo utilizado
        RetirarDebitoCobradoActionForm retirarDebitoCobradoActionForm = (RetirarDebitoCobradoActionForm) actionForm;

        Fachada fachada = Fachada.getInstancia();     
        
        //MotivoReatificacaoConta selecinado pelo usu�rio
        ContaMotivoRetificacao contaMotivoRetificacao = new ContaMotivoRetificacao();
        contaMotivoRetificacao.setId(new Integer(retirarDebitoCobradoActionForm.getIdMotivoRetificacao()));
        
        Collection debitosTipoRetirar = new ArrayList();
        if(sessao.getAttribute("debitosTipoRetirar") != null){
        	debitosTipoRetirar = (Collection)sessao.getAttribute("debitosTipoRetirar"); 
        }else{
            throw new ActionServletException("atencao.campo.informado", null, "Tipo de D�bito");
        }
        
        
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
    		
            //Retificar uma ou v�rias contas
        	Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);
        	
            //Cancelando uma ou v�rias contas
        	/*Integer codigoCliente = null;
    		if(sessao.getAttribute("codigoCliente") != null){
    			codigoCliente = new Integer((String)sessao.getAttribute("codigoCliente"));
    		}
        	if(codigoCliente != null){
        	  Short relacaoTipo = null;
        	  if(sessao.getAttribute("relacaoTipo") != null){
       		    relacaoTipo = ((Integer)sessao.getAttribute("relacaoTipo")).shortValue();
        	  }
        	  fachada.retificarConjuntoContaCliente(codigoCliente, relacaoTipo, anoMes, contaMotivoRetificacao, 
        			  debitosTipoRetirar, usuarioLogado, dataVencimentoContaInicio, dataVencimentoContaFim,
        			  anoMesFim);
                fachada.retificarConjuntoConta(colecaoImovel, anoMes, contaMotivoRetificacao, debitosTipoRetirar, 
              		  usuarioLogado, dataVencimentoContaInicio, dataVencimentoContaFim, anoMesFim, indicadorContaPaga);	        		
        	}
        	else*/ if (idGrupoFaturamento != null){
        		
        		fachada.retificarConjuntoConta(idGrupoFaturamento, anoMes, contaMotivoRetificacao, debitosTipoRetirar, 
              		  usuarioLogado, dataVencimentoContaInicio, dataVencimentoContaFim, anoMesFim);
        	}
        	else{
        		
              fachada.retificarConjuntoConta(colecaoImovel, anoMes, contaMotivoRetificacao, debitosTipoRetirar, 
            		  usuarioLogado, dataVencimentoContaInicio, dataVencimentoContaFim, anoMesFim, indicadorContaPaga);	
        	}
        	
        	httpServletRequest.setAttribute("mensagemSucesso","Retirar debitos cobrados de conjunto de contas encaminhado para processamento.");
        	        	
        }

        //Realizar um reload na tela de manter conta
    	httpServletRequest.setAttribute("fecharPopup", "OK");
        
        //sessao.setAttribute("cancelar", "1");
        sessao.removeAttribute("anoMes");
        sessao.removeAttribute("anoMesFim");
        sessao.removeAttribute("dataVencimentoContaInicial");
        sessao.removeAttribute("dataVencimentoContaFinal");
        sessao.removeAttribute("indicadorContaPaga");
                
        return retorno;
    }

}
