package gcom.gui.arrecadacao;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
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

/**
 * Descrição da classe
 * 
 * @author Marcio Roberto
 * @date 13/03/2007
 */
public class ExibirFiltrarContratoArrecadadorAction extends GcomAction {

	/**
	 * [UC0509] Filtrar Contrato Arrecadador
	 * 
	 * @author Marcio Roberto
	 * @date 13/03/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("contratoArrecadadorFiltrar");

		FiltrarContratoArrecadadorActionForm filtrarContratoArrecadadorActionForm = (FiltrarContratoArrecadadorActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		String idCliente = filtrarContratoArrecadadorActionForm.getIdCliente();

		// Verificar se o número do cliente não está cadastrado
		if (idCliente != null && !idCliente.trim().equals("")) {
			// Filtro para descobrir id do Cliente
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,idCliente));
			Collection colecaoCliente = fachada.pesquisar(filtroCliente,Cliente.class.getName());
			
			if (colecaoCliente == null || colecaoCliente.isEmpty()) {
				filtrarContratoArrecadadorActionForm.setIdCliente("");
				httpServletRequest.setAttribute("existeCliente", "exception");
				throw new ActionServletException("atencao.cliente.inexistente");
			} else {
				Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
				filtrarContratoArrecadadorActionForm.setIdCliente(cliente.getId().toString());
				filtrarContratoArrecadadorActionForm.setNomeCliente(cliente.getNome());
				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}
		}
        
          // código para checar ou naum o Atualizar
        String primeiraVez = httpServletRequest.getParameter("menu");
        if (primeiraVez != null && !primeiraVez.equals("")) {
            sessao.setAttribute("indicadorAtualizar","1");
        }
             
        
		return retorno;
	}
}
