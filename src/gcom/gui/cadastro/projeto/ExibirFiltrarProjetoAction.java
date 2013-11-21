package gcom.gui.cadastro.projeto;

import java.util.Collection;
import java.util.List;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarProjetoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		//Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("exibirFiltrarProjeto");
		
		//Pega Parametros
		String pesquisaCliente = httpServletRequest.getParameter("pesquisarCliente");
		String indicadorAtualizar = httpServletRequest.getParameter("atualizar");
		
		//Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		FiltrarProjetoActionForm form = (FiltrarProjetoActionForm) actionForm;
		
		//Indicador Atualizar
		if(indicadorAtualizar!=null && indicadorAtualizar.equals("sim")){
			httpServletRequest.setAttribute("atualizar","1");
		}
		
		
		//PESQUISAR CLIENTE
		if (pesquisaCliente != null && pesquisaCliente.equalsIgnoreCase("sim")) {

			this.pesquisarCliente(form.getIdOrgaoFinanciador(), form, fachada,
					httpServletRequest);
		}
		
		String menu = httpServletRequest.getParameter("menu");
		
		if(menu!=null && menu.equals("sim")){
			httpServletRequest.setAttribute("atualizar","1");
			httpServletRequest.setAttribute("selecionarRadio","sim");
		}
		
		return retorno;
	}
	

	/**
	 * Pesquisar Clientes
	 * 
	 * @param filtroCliente
	 * @param idCliente
	 * @param clientes
	 * @param filtrarImovelFiltrarActionForm
	 * @param fachada
	 * @param httpServletRequest
	 */
	public void pesquisarCliente(String idCliente,
			FiltrarProjetoActionForm form, Fachada fachada,
			HttpServletRequest httpServletRequest) {
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
				idCliente));
		filtroCliente.adicionarParametro(new ParametroSimples(
				FiltroCliente.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
				Cliente.class.getName());

		if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
			// O municipio foi encontrado
			form.setIdOrgaoFinanciador(""
					+ ((Cliente) ((List) clienteEncontrado).get(0)).getId());
			form.setNomeOrgaoFinanciador(((Cliente) ((List) clienteEncontrado).get(0))
					.getNome());
			httpServletRequest.setAttribute("existeCliente", "true");

		} else {
			form.setIdOrgaoFinanciador("");
			form.setNomeOrgaoFinanciador("Cliente inexistente");
			httpServletRequest.setAttribute("existeCliente","exception");


		}
	}
}
