package gcom.gui.atendimentopublico;

import java.util.Collection;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class ExibirGerarContratoPrestacaoServicoJuridicoAction extends GcomAction {

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

		GerarContratoPrestacaoServicoJuridicoActionForm gerarContratoPrestacaoServicoActionForm = (GerarContratoPrestacaoServicoJuridicoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarContratoPrestacaoServicoJuridico");

		String idImovel = gerarContratoPrestacaoServicoActionForm.getIdImovel();
		String idCliente = gerarContratoPrestacaoServicoActionForm.getIdCliente();
		
		if (idImovel != null && !idImovel.trim().equals("")) {
			
			String inscricaoImovel = fachada.pesquisarInscricaoImovel(new Integer(idImovel));
			
			if (inscricaoImovel != null && !inscricaoImovel.trim().equals("")) {
				gerarContratoPrestacaoServicoActionForm.setInscricao(inscricaoImovel);
			} else {
				gerarContratoPrestacaoServicoActionForm.setIdImovel("");
				gerarContratoPrestacaoServicoActionForm.setInscricao("MATRÍCULA INEXISTENTE");
				httpServletRequest.setAttribute("matriculaInexistente", true);
			}
			
		}
		if (idCliente != null && !idCliente.trim().equals("")) {
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,new Integer(idCliente)));
			Collection clientes = fachada.pesquisarCliente(filtroCliente);
			if(!clientes.isEmpty()){
				Cliente cliente = (Cliente)clientes.iterator().next();
			
				if (cliente != null) {
					gerarContratoPrestacaoServicoActionForm.setNomeCliente(cliente.getNome());
				} 
			}else {
				gerarContratoPrestacaoServicoActionForm.setIdCliente("");
				gerarContratoPrestacaoServicoActionForm.setNomeCliente("CLIENTE INEXISTENTE");
				httpServletRequest.setAttribute("clienteInexistente", true);
			}
			
		}

		return retorno;

	}

}
