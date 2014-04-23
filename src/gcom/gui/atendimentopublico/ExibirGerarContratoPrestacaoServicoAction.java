package gcom.gui.atendimentopublico;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite gerar o relatório do contrato de prestação de serviço
 * 
 * [UC0XXX] Gerar Contrato de Prestação de Serviço
 * 
 * @author Rafael Corrêa
 * @since 03/05/2007
 */
public class ExibirGerarContratoPrestacaoServicoAction extends GcomAction {

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

		GerarContratoPrestacaoServicoActionForm gerarContratoPrestacaoServicoActionForm = (GerarContratoPrestacaoServicoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarContratoPrestacaoServico");
		
//		cria uma instância da sessão
		HttpSession sessao = this.getSessao(httpServletRequest);//getSessao(httpServletRequest);

		String idImovel = gerarContratoPrestacaoServicoActionForm.getIdImovel();

		/*String idCliente = gerarContratoPrestacaoServicoActionForm
				.getIdCliente();*/

		if (idImovel != null && !idImovel.trim().equals("")) {

			String inscricaoImovel = fachada
					.pesquisarInscricaoImovel(new Integer(idImovel));

			if (inscricaoImovel != null && !inscricaoImovel.trim().equals("")) {
				gerarContratoPrestacaoServicoActionForm
						.setInscricao(inscricaoImovel);
				
				//	pesquisar cliente do imovel
				
				Collection clientesImovel = fachada.pesquisarClientesImovel(new Integer(idImovel.trim()));
				
				sessao.setAttribute("imovelClientes",clientesImovel);
				
			} else {
				gerarContratoPrestacaoServicoActionForm.setIdImovel("");
				gerarContratoPrestacaoServicoActionForm
						.setInscricao("MATRÍCULA INEXISTENTE");
				httpServletRequest.setAttribute("matriculaInexistente", true);
			}

		}

		/*if (idCliente != null && !idCliente.trim().equals("")) {

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, idCliente));

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

				Cliente cliente = (Cliente) Util
						.retonarObjetoDeColecao(colecaoCliente);

				gerarContratoPrestacaoServicoActionForm.setIdCliente(idCliente);
				gerarContratoPrestacaoServicoActionForm.setNomeCliente(cliente
						.getNome());
			} else {
				gerarContratoPrestacaoServicoActionForm.setIdImovel("");
				gerarContratoPrestacaoServicoActionForm
						.setInscricao("CLIENTE INEXISTENTE");
				httpServletRequest.setAttribute("clienteInexistente", true);
			}

		}*/
		

		return retorno;

	}

}
