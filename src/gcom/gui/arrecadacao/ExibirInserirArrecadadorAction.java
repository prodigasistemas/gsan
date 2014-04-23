package gcom.gui.arrecadacao;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Marcio Roberto
 * @date 30/01/2007
 */
public class ExibirInserirArrecadadorAction extends GcomAction {

	/**
	 * [UC0506] Inserir Arrecadador
	 * 
	 * @author Marcio Roberto
	 * @date 30/01/2007
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

		ActionForward retorno = actionMapping.findForward("arrecadadorInserir");
		
		InserirArrecadadorActionForm arrecadadorActionForm = (InserirArrecadadorActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		String idCliente = arrecadadorActionForm.getIdCliente();
		String idImovel = arrecadadorActionForm.getIdImovel();
		
		// Verificar se o número do cliente não está cadastrado
		if (idCliente != null && !idCliente.trim().equals("")) {

			// Filtro para descobrir id do Cliente
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(
				new ParametroSimples(
						FiltroCliente.ID, 
						idCliente));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
	
			if (colecaoCliente == null || colecaoCliente.isEmpty()) {
				arrecadadorActionForm.setIdCliente("");
				arrecadadorActionForm.setNomeCliente("CLIENTE INEXISTENTE");
				httpServletRequest.setAttribute("existeCliente","exception");	
				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}else{
				Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
				arrecadadorActionForm.setIdCliente(cliente.getId().toString());
				arrecadadorActionForm.setNomeCliente(cliente.getNome());
				httpServletRequest.setAttribute("nomeCampo","idCliente");
			}
		}

		// Verifica se o id do imovel não está cadastrado
		if (idImovel != null && !idImovel.trim().equals("")) {

			// Filtro para descobrir id do Cliente
			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel.adicionarParametro(
				new ParametroSimples(
						FiltroImovel.ID, 
						idImovel));

			Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
	
			if (colecaoImovel == null || colecaoImovel.isEmpty()) {
				arrecadadorActionForm.setIdImovel(""); 
				arrecadadorActionForm.setInscricaoImovel("IMOVEL INEXISTENTE");
				httpServletRequest.setAttribute("existeImovel","exception");
				httpServletRequest.setAttribute("nomeCampo", "idImovel");
			}else{
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
				arrecadadorActionForm.setIdImovel(imovel.getId().toString());
				String inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel.getId()); 
				arrecadadorActionForm.setInscricaoImovel(inscricaoImovel);
				httpServletRequest.setAttribute("nomeCampo","idImovel");
			}
		}
		return retorno;
	}

}
