package gcom.gui.cadastro.localidade;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirGerenciaRegionalAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("exibirInserirGerenciaRegional");

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        InserirGerenciaRegionalActionForm inserirGerenciaRegionalActionForm = (InserirGerenciaRegionalActionForm) actionForm;

        String limparForm = (String) httpServletRequest
                .getParameter("limparCampos");
        String removerEndereco = (String) httpServletRequest
                .getParameter("removerEndereco");

        String objetoConsulta = (String) httpServletRequest
        .getParameter("objetoConsulta");

        if (objetoConsulta != null
        		&& !objetoConsulta.trim().equalsIgnoreCase("")) {
        	
            
            switch (Integer.parseInt(objetoConsulta)) {
            
            // Gerente Regional

            case 1:
            	
            	this.pesquisarCliente(inserirGerenciaRegionalActionForm);

            default:

                break;
            }
        }
        
        
        if ((limparForm == null || limparForm.trim().equalsIgnoreCase("")) ||
        		(httpServletRequest.getParameter("desfazer") != null
                        && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S"))) {
        	//-------------- bt DESFAZER ---------------
         	
            //Limpando o formulario
        	inserirGerenciaRegionalActionForm.setEmail("");
        	inserirGerenciaRegionalActionForm.setFax("");
        	inserirGerenciaRegionalActionForm.setNome("");
        	inserirGerenciaRegionalActionForm.setNomeAbreviado("");
        	inserirGerenciaRegionalActionForm.setRamal("");
        	inserirGerenciaRegionalActionForm.setTelefone("");


            //Limpa o endereço da sessão
            if (sessao.getAttribute("colecaoEnderecos") != null) {
                sessao.removeAttribute("colecaoEnderecos");
            }
            sessao.removeAttribute("tipoPesquisaRetorno");
        }

        //Remove o endereco informado.
        if (removerEndereco != null
                && !removerEndereco.trim().equalsIgnoreCase("")) {

            if (sessao.getAttribute("colecaoEnderecos") != null) {

                Collection enderecos = (Collection) sessao
                        .getAttribute("colecaoEnderecos");
                if (!enderecos.isEmpty()) {
                    enderecos.remove(enderecos.iterator().next());
                }

            }

        }

        //devolve o mapeamento de retorno
        return retorno;
    }

	/**
	 * Pesquisa Cliente 
	 *
	 * @author Rafael Pinto
	 * @date 15/08/2006
	 */
	private void pesquisarCliente(InserirGerenciaRegionalActionForm form) {
		
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(
			new ParametroSimples(FiltroCliente.ID, 
			new Integer(form.getIdCliente())));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoCliente = 
			this.getFachada().pesquisar(filtroCliente,Cliente.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Cliente cliente = 
				(Cliente) Util.retonarObjetoDeColecao(colecaoCliente);

			form.setIdCliente(cliente.getId().toString());
			form.setNomeCliente(cliente.getNome());
			

		} else {
			form.setIdCliente("");
			form.setNomeCliente("Cliente inexistente");
		}
	}
}
