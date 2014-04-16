package gcom.gui.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rômulo Aurélio
 * @created 29/09/2008
 */
public class ExibirInserirUnidadeNegocioAction extends GcomAction{

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
       
       // HttpSession sessao = httpServletRequest.getSession(false);
        
        ActionForward retorno = actionMapping
                .findForward("inserirUnidadeNegocio");
        
        InserirUnidadeNegocioActionForm form = (InserirUnidadeNegocioActionForm) actionForm;
        
        Fachada fachada = Fachada.getInstancia();
        
        String idCliente = form.getIdCliente();
        
        String idGerenciaRegional = form.getIdGerenciaRegional();
        
        if(httpServletRequest.getParameter("menu") != null){
            
            form.setIndicadorUso("" + ConstantesSistema.INDICADOR_USO_ATIVO);
            
        }
        
        if (idCliente != null && !idCliente.trim().equals("")) {
            // Pesquisa o cliente na base
            FiltroCliente filtroCliente = new FiltroCliente();
            
            filtroCliente.adicionarParametro(new ParametroSimples(
                    FiltroCliente.ID, idCliente));
            
            Collection colecaoCliente = fachada.pesquisar(filtroCliente,
                    Cliente.class.getName());
            
            if (colecaoCliente != null && !colecaoCliente.isEmpty()) {
                Cliente cliente = (Cliente) colecaoCliente.iterator().next();
                
                form.setIdCliente(cliente.getId().toString());
                
                form.setNomeCliente(cliente.getNome());
            }else{
                form.setIdCliente("");
                form.setNomeCliente("Cliente inexistente");
            
                httpServletRequest.setAttribute("corCliente", "exception");
                httpServletRequest.setAttribute("nomeCampo", "idCliente");
            }
        }
        
        if (idGerenciaRegional != null && !idGerenciaRegional.trim().equals("")) {
            // Pesquisa o cliente na base
            FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
            
            filtroGerenciaRegional.adicionarParametro(new ParametroSimples(
                    FiltroGerenciaRegional.ID, idGerenciaRegional));
            
            Collection colecaoGerenciaRegional = fachada.pesquisar(filtroGerenciaRegional,
                    GerenciaRegional.class.getName());
            
            if (colecaoGerenciaRegional != null && !colecaoGerenciaRegional.isEmpty()) {
               
                GerenciaRegional gerenciaRegional = (GerenciaRegional) colecaoGerenciaRegional.iterator().next();
                
                form.setIdGerenciaRegional(gerenciaRegional.getId().toString());
                
                form.setNomeGerenciaRegional(gerenciaRegional.getNome());
            }else{
                form.setIdGerenciaRegional("");
                form.setNomeGerenciaRegional("Gerência Regional inexistente");
            
                httpServletRequest.setAttribute("corGerenciaRegional", "exception");
                httpServletRequest.setAttribute("nomeCampo", "idGerenciaRegional");
            }
        
        }
        return retorno;
    }
}
