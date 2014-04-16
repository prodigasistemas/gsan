package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author rodrigo
 */
public class ConcluirAlteracaoDadosTarifaSocialAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
    	
        ActionForward retorno = actionMapping.findForward("exibirAtualizarDadosTarifaSocial");
        
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		TarifaSocialHelper tarifaSocialHelperAtualizar = (TarifaSocialHelper) sessao.getAttribute("tarifaSocialHelperAtualizar");
		
		Collection colecaoTarifaSocialHelperAtualizar = null;
		
		if (sessao.getAttribute("colecaoTarifaSocialHelperAtualizar") != null) {
			colecaoTarifaSocialHelperAtualizar = (Collection) sessao.getAttribute("colecaoTarifaSocialHelperAtualizar"); 
		} else {
			colecaoTarifaSocialHelperAtualizar = new ArrayList();
		}
		
		colecaoTarifaSocialHelperAtualizar.add(tarifaSocialHelperAtualizar);
		
		sessao.setAttribute("colecaoTarifaSocialHelperAtualizar", colecaoTarifaSocialHelperAtualizar);
		
		ArrayList colecaoTarifaSocialHelper = (ArrayList) sessao.getAttribute("colecaoTarifaSocialHelper");
		
		Iterator colecaoTarifaSocialHelperIterator = colecaoTarifaSocialHelper.iterator();
		
		if (sessao.getAttribute("colecaoClienteImovel") != null) {
		
		Collection colecaoClienteImovel = (Collection) sessao.getAttribute("colecaoClienteImovel");
		
		tarifaSocialHelperAtualizar.setColecaoClientesImoveis(colecaoClienteImovel);
		 
		} else if (sessao.getAttribute("colecaoClienteImovelEconomia") != null) {
			
			Collection colecaoClienteImovelEconomia = (Collection) sessao.getAttribute("colecaoClienteImovelEconomia");
			
			tarifaSocialHelperAtualizar.setColecaoClientesImoveisEconomias(colecaoClienteImovelEconomia);
			
		}
		
		int i = 0;
		
		while (colecaoTarifaSocialHelperIterator.hasNext()) {

			TarifaSocialHelper tarifaSocialHelper = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator
					.next();

			// Uma Economia
			if (tarifaSocialHelperAtualizar.getClienteImovel() != null) {
				colecaoTarifaSocialHelper.set(i, tarifaSocialHelperAtualizar);
				sessao.setAttribute("clienteImovel", tarifaSocialHelperAtualizar.getClienteImovel()); 
				break;
			} 
			
			// Múltiplas Economias
			else if (tarifaSocialHelperAtualizar.getClienteImovelEconomia() != null) {
				if (tarifaSocialHelper.getClienteImovelEconomia()
						.getImovelEconomia().getId().equals(
								tarifaSocialHelperAtualizar
										.getClienteImovelEconomia()
										.getImovelEconomia().getId())) {
					if (tarifaSocialHelperAtualizar.getClienteImovelEconomia().getCliente() == null) {
						Cliente cliente = tarifaSocialHelper.getClienteImovelEconomia().getCliente();
						ClienteImovelEconomia clienteImovelEconomia = tarifaSocialHelperAtualizar.getClienteImovelEconomia();
						clienteImovelEconomia.setCliente(cliente);
						tarifaSocialHelperAtualizar.setClienteImovelEconomia(clienteImovelEconomia);
					}
					colecaoTarifaSocialHelper.set(i, tarifaSocialHelperAtualizar);
					break;
				}
					
			}
			
			i++;

		}
		
		sessao.setAttribute("colecaoTarifaSocialHelper", colecaoTarifaSocialHelper);
		sessao.setAttribute("atualizar", true);
		
		
		httpServletRequest.setAttribute("fechar", true);

        return retorno;
    }
}
