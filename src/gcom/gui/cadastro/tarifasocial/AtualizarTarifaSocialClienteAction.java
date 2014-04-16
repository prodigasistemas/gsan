package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

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
 * @author Rafael Corrêa
 */
public class AtualizarTarifaSocialClienteAction extends GcomAction {
	
	/**
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
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("voltar");
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String concluir = httpServletRequest.getParameter("concluir");
		
		if (concluir != null && !concluir.equals("")) {
			retorno = actionMapping
			.findForward("concluir");
		}
		
		boolean existeUsuario = false;
		boolean existeProprietario = false;
		
		Cliente clienteUsuario = null;
		Cliente clienteEconomiaUsuario = null;
		
		if (sessao.getAttribute("colecaoClienteImovel") != null) {
		
		Collection colecaoClienteImovel = (Collection) sessao.getAttribute("colecaoClienteImovel");
		
		if (colecaoClienteImovel != null && !colecaoClienteImovel.isEmpty()) {
			Iterator colecaoClienteImovelIterator = colecaoClienteImovel
					.iterator();
			while (colecaoClienteImovelIterator.hasNext()) {
				ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovelIterator
						.next();

				if (clienteImovel.getClienteRelacaoTipo() != null) {
					if (clienteImovel.getClienteRelacaoTipo().getId()
							.toString().equals(
									ClienteRelacaoTipo.USUARIO.toString())) {
						existeUsuario = true;
						clienteUsuario = clienteImovel.getCliente();
					} else if (clienteImovel.getClienteRelacaoTipo().getId()
							.toString().equals(
									ClienteRelacaoTipo.PROPRIETARIO.toString())) {
						existeProprietario = true;
					}
				}
			}
		}
		
		} else if (sessao.getAttribute("colecaoClienteImovelEconomia") != null) {
			
			Collection colecaoClienteImovelEconomia = (Collection) sessao.getAttribute("colecaoClienteImovelEconomia");

			if (colecaoClienteImovelEconomia != null && !colecaoClienteImovelEconomia.isEmpty()) {
				Iterator colecaoClienteImovelEconomiaIterator = colecaoClienteImovelEconomia
						.iterator();
				while (colecaoClienteImovelEconomiaIterator.hasNext()) {
					ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) colecaoClienteImovelEconomiaIterator
							.next();
					
					if (clienteImovelEconomia.getClienteRelacaoTipo() != null) {
						if (clienteImovelEconomia.getClienteRelacaoTipo().getId()
								.toString().equals(
										ClienteRelacaoTipo.USUARIO.toString())) {
							existeUsuario = true;
							clienteEconomiaUsuario = clienteImovelEconomia.getCliente();
						} else if (clienteImovelEconomia.getClienteRelacaoTipo().getId()
								.toString().equals(
										ClienteRelacaoTipo.PROPRIETARIO.toString())) {
							existeProprietario = true;
						}
					}
				}
			}
			
		}
		
		if (!existeUsuario) {
			throw new ActionServletException("atencao.informe.cliente_usuario");
		}
		
		if (!existeProprietario) {
			throw new ActionServletException("atencao.informe.cliente_proprietario");
		}
		
		TarifaSocialHelper tarifaSocialHelperAtualizar = (TarifaSocialHelper) sessao.getAttribute("tarifaSocialHelperAtualizar");
		
		if (clienteUsuario != null) {
			ClienteImovel clienteImovelTarifaSocial = tarifaSocialHelperAtualizar.getClienteImovel();
			clienteImovelTarifaSocial.setCliente(clienteUsuario);
			tarifaSocialHelperAtualizar.setClienteImovel(clienteImovelTarifaSocial);
		} else if (clienteEconomiaUsuario != null) {
			ClienteImovelEconomia clienteImovelEconomiaTarifaSocial = tarifaSocialHelperAtualizar.getClienteImovelEconomia();
			clienteImovelEconomiaTarifaSocial.setCliente(clienteEconomiaUsuario);
			tarifaSocialHelperAtualizar.setClienteImovelEconomia(clienteImovelEconomiaTarifaSocial);
		}
		
		sessao.setAttribute("tarifaSocialHelperAtualizar", tarifaSocialHelperAtualizar);
		
		httpServletRequest.setAttribute("voltar", true);

		return retorno;

	}
	
}	
