package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovelSimplificado;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.tarifasocial.FiltroTarifaSocialDadoEconomia;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author thiago toscano
 */
public class ExibirAtualizarDadosTarifaSocialAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	HttpSession sessao = httpServletRequest.getSession(false);
        ActionForward retorno = actionMapping.findForward("exibirAtualizarDadosTarifaSocial");
        ExibirAtualizarDadosTarifaSocialActionForm form = (ExibirAtualizarDadosTarifaSocialActionForm) actionForm;
        
        Collection tarifasocialDadoEconomeia = (Collection) sessao.getAttribute("tarifasocialDadoEconomeia");
        // caso na ja exista uma tarifa social dado economia na sessao consulta 
        if (tarifasocialDadoEconomeia == null) {
        	pesquisaTarifasocialDadoEconomeia(form,sessao);
        }
        
        
        // caso ja exista entao apresenta
        return retorno;
    }        
        
    /**
     * Metodo que pesquisa a Tarfia Social Dados Economia e o cliente de um imovel e os colocam na sessao
     *  
     * @author thiago toscano
     * @date 15/12/2005
     * @param form
     * @param sessao
     */
    public void pesquisaTarifasocialDadoEconomeia(ExibirAtualizarDadosTarifaSocialActionForm form,HttpSession sessao) { 
        
        String idRegistroAtualizacao = form.getIdRegistroAtualizacao();

        FiltroTarifaSocialDadoEconomia filtroTarifaSocialDadoEconomia = new FiltroTarifaSocialDadoEconomia();
        filtroTarifaSocialDadoEconomia.adicionarParametro(new ParametroSimples(FiltroTarifaSocialDadoEconomia.IMOVEL_ID, idRegistroAtualizacao));
        filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.TARIFA_SOCIAL_CARTAO_TIPO);
        filtroTarifaSocialDadoEconomia.adicionarCaminhoParaCarregamentoEntidade(FiltroTarifaSocialDadoEconomia.RENDA_TIPO);
        Collection tarifasocialDadoEconomeia  = 
        	this.getFachada().pesquisarTarifaSocialDadoEconomia(filtroTarifaSocialDadoEconomia);
        
        sessao.setAttribute("collTarifaSocialDadoEconomia", tarifasocialDadoEconomeia);

        FiltroClienteImovel filtroClienteImovel = new FiltroClienteImovel();
        filtroClienteImovel.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idRegistroAtualizacao));
        
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.unidadeFederacao");
        filtroClienteImovel.adicionarCaminhoParaCarregamentoEntidade("cliente.orgaoExpedidorRg");

       Collection collClienteImovel = 
    	   this.getFachada().pesquisarClienteImovel(filtroClienteImovel);

        if (collClienteImovel != null && !collClienteImovel.isEmpty()) {
        	
        	ClienteImovelSimplificado clienteImovel = (ClienteImovelSimplificado) collClienteImovel.iterator().next();
        	
        	FiltroCliente filtroCliente = new FiltroCliente();
        	filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, clienteImovel.getCliente().getId()));
            filtroCliente.adicionarCaminhoParaCarregamentoEntidade("orgaoExpedidorRg");
            filtroCliente.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
            Collection collCliente = this.getFachada().pesquisarCliente(filtroCliente);
            
            if (collCliente != null && !collCliente.isEmpty()) {
            	Cliente cliente = ((Cliente) collCliente.iterator().next());
            	sessao.setAttribute("cliente", cliente);
            }	
        }
    }
}
