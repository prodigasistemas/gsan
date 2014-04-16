package gcom.gui.arrecadacao;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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
 * [UC0536]FILTRAR FERIADO
 * 
 * @author Kássia Albuquerque
 * @date 19/01/2006
 */



public class ExibirFiltrarArrecadadorAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("filtrarArrecadador");

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarArrecadadorActionForm filtrarArrecadadorActionForm = (FiltrarArrecadadorActionForm) actionForm;
		
		//	Código para checar ou não o ATUALIZAR
		
		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			sessao.setAttribute("indicadorAtualizar", "1");
			filtrarArrecadadorActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
			
		}		

		String idCliente = filtrarArrecadadorActionForm.getIdCliente();
		String idImovel =  filtrarArrecadadorActionForm.getIdImovel();
		
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
				filtrarArrecadadorActionForm.setIdCliente("");
				filtrarArrecadadorActionForm.setNomeCliente( "CLIENTE INEXISTENTE" );
				httpServletRequest.setAttribute("existeCliente","exception");
				httpServletRequest.setAttribute("nomeCampo","idCliente");
			}else{
				Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
				filtrarArrecadadorActionForm.setIdCliente(cliente.getId().toString());
				filtrarArrecadadorActionForm.setNomeCliente(cliente.getNome());
				httpServletRequest.setAttribute("nomeCampo","idCliente");
			}
		}

		// Verifica se o id do imovel não está cadastrado
		if (idImovel != null && !idImovel.trim().equals("")) {

			// Filtro para descobrir id do Imovel
			FiltroImovel filtroImovel = new FiltroImovel();

			filtroImovel.adicionarParametro(
				new ParametroSimples(
						FiltroImovel.ID, 
						idImovel));

			Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
	
			if (colecaoImovel == null || colecaoImovel.isEmpty()) {
				filtrarArrecadadorActionForm.setIdImovel(""); 
				filtrarArrecadadorActionForm.setInscricaoImovel( "IMOVEL INEXISTENTE" );
				httpServletRequest.setAttribute("existeImovel","exception");
				httpServletRequest.setAttribute("nomeCampo","idImovel");
			}else{
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
				filtrarArrecadadorActionForm.setIdImovel(imovel.getId().toString());
				String inscricaoImovel = fachada.pesquisarInscricaoImovel(imovel.getId()); 
				filtrarArrecadadorActionForm.setInscricaoImovel(inscricaoImovel);
				httpServletRequest.setAttribute("nomeCampo","idImovel");
			}
		}
		
		return retorno;
	}
}
