package gcom.gui.relatorio.faturamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirGerarRelatorioImpostosPorClienteResponsavelAction extends GcomAction {
		
	public ActionForward execute(ActionMapping actionMapping,
				ActionForm actionForm, HttpServletRequest httpServletRequest,
				HttpServletResponse httpServletResponse) {

				ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioImpostosPorClienteResponsavel");
		
				RelatorioImpostosPorClienteResponsavelActionForm form = (RelatorioImpostosPorClienteResponsavelActionForm) actionForm;
				
				httpServletRequest.setAttribute("nomeCampo", "anoMesReferencia");
				
				String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");
				
				String limparCliente = httpServletRequest.getParameter("limparCliente");
				
				if (objetoConsulta!= null && !objetoConsulta.equals("")){
				
		    		Cliente cliente = null;
		    		
		    		if(form.getClienteID() != null){
		    			
		    			FiltroCliente filtroCliente = new FiltroCliente();
		    			filtroCliente.adicionarParametro(
		    				new ParametroSimples(
		    					FiltroCliente.ID,
		    					form.getClienteID()));
		    			
		    			filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
		    			filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo.esferaPoder");
		    			
		    			Collection colecaoCliente = 
		    				this.getFachada().pesquisar(
		    					filtroCliente, Cliente.class.getName());

		    			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
		    				cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
		    			}		    			
		    		}
		    		
		    		if (cliente == null) {
		    		
		    			form.setClienteID("");
		    			form.setNomeCliente("Cliente inexistente");
		    			
		    			httpServletRequest.setAttribute("corCliente", "exception");
		    			httpServletRequest.setAttribute("nomeCampo", "clienteID");
		    		
		    		} else if(!cliente.getClienteTipo().getEsferaPoder().getId().equals(EsferaPoder.FEDERAL.intValue())) {
		    			throw new ActionServletException("atencao.cliente_esfera_poder_nao_federal","exibirGerarRelatorioImpostosPorClienteResponsavelAction.do?limparCliente=1",new Exception(),"");
		    		}else {
		    			
		    			form.setClienteID(cliente.getId().toString());
		    			form.setNomeCliente(cliente.getNome());	    			

		    			httpServletRequest.setAttribute("corCliente", "nomeCliente");
		    			httpServletRequest.setAttribute("nomeCampo", "clienteID");
		    		}
				}else if(limparCliente != null && limparCliente.equals("1")){
					form.setClienteID("");
					httpServletRequest.setAttribute("nomeCampo", "");
				}
				
				
				
		
				return retorno;
	}

}
