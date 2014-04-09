package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Rafael Corrêa
 * @since 16/01/2007
 */
public class RemoverTarifaSocialImovelAnteriorAction extends GcomAction {

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
		ActionForward retorno = actionMapping.findForward("remover");

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		RemoverTarifaSocialImovelAnteriorActionForm removerTarifaSocialImovelAnteriorActionForm = (RemoverTarifaSocialImovelAnteriorActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		String idMotivoExclusao = removerTarifaSocialImovelAnteriorActionForm
				.getMotivoExclusao();
		
		Cliente cliente = (Cliente) sessao
		.getAttribute("clienteTarifaSocialImovelAnterior");

		String manter = httpServletRequest.getParameter("manter");
		
		if (manter == null || manter.trim().equals("")) {
			
			TarifaSocialHelper tarifaSocialHelperAtualizar = (TarifaSocialHelper) sessao
					.getAttribute("tarifaSocialHelperAtualizar");
			TarifaSocialHelper tarifaSocialHelper = (TarifaSocialHelper) sessao
					.getAttribute("tarifaSocialHelper");

			ClienteRelacaoTipo clienteRelacaoTipo = new ClienteRelacaoTipo();
			clienteRelacaoTipo.setId(ClienteRelacaoTipo.USUARIO.intValue());
			clienteRelacaoTipo.setDescricao("USUARIO");

			Date dataInicioRelacao = (Date) sessao
					.getAttribute("dataInicioRelacao");

			if (sessao.getAttribute("colecaoClienteImovel") != null) {

				ClienteImovel clienteImovel = new ClienteImovel();

				clienteImovel.setCliente(cliente);

				clienteImovel.setClienteRelacaoTipo(clienteRelacaoTipo);

				Imovel imovel = tarifaSocialHelper.getClienteImovel()
						.getImovel();

				clienteImovel.setDataInicioRelacao(dataInicioRelacao);
				clienteImovel.setImovel(imovel);

				Collection colecaoClienteImovel = (Collection) sessao
						.getAttribute("colecaoClienteImovel");

				tarifaSocialHelperAtualizar.setClienteImovel(clienteImovel);
				sessao.setAttribute("tarifaSocialHelperAtualizar",
						tarifaSocialHelperAtualizar);

				colecaoClienteImovel.add(clienteImovel);
				sessao.setAttribute("colecaoClienteImovel",
						colecaoClienteImovel);

				Collection colecaoClientesInseridos = null;

				if (tarifaSocialHelperAtualizar.getColecaoClientesInseridos() != null) {
					colecaoClientesInseridos = tarifaSocialHelperAtualizar
							.getColecaoClientesInseridos();
				} else {
					colecaoClientesInseridos = new ArrayList();
				}

				colecaoClientesInseridos.add(clienteImovel);
				tarifaSocialHelperAtualizar
						.setColecaoClientesInseridos(colecaoClientesInseridos);
				sessao.setAttribute("tarifaSocialHelperAtualizar",
						tarifaSocialHelperAtualizar);

			} else {

				ClienteImovelEconomia clienteImovelEconomia = new ClienteImovelEconomia();

				clienteImovelEconomia.setCliente(cliente);

				clienteImovelEconomia.setClienteRelacaoTipo(clienteRelacaoTipo);

				ImovelEconomia imovelEconomia = tarifaSocialHelper
						.getClienteImovelEconomia().getImovelEconomia();

				clienteImovelEconomia.setDataInicioRelacao(dataInicioRelacao);
				clienteImovelEconomia.setImovelEconomia(imovelEconomia);

				Collection colecaoClienteImovelEconomia = (Collection) sessao
						.getAttribute("colecaoClienteImovelEconomia");

				tarifaSocialHelperAtualizar
						.setClienteImovelEconomia(clienteImovelEconomia);
				sessao.setAttribute("tarifaSocialHelperAtualizar",
						tarifaSocialHelperAtualizar);

				colecaoClienteImovelEconomia.add(clienteImovelEconomia);
				sessao.setAttribute("colecaoClienteImovelEconomia",
						colecaoClienteImovelEconomia);

				Collection colecaoClientesInseridos = null;

				if (tarifaSocialHelperAtualizar
						.getColecaoClientesEconomiaInseridos() != null) {
					colecaoClientesInseridos = tarifaSocialHelperAtualizar
							.getColecaoClientesEconomiaInseridos();
				} else {
					colecaoClientesInseridos = new ArrayList();
				}

				colecaoClientesInseridos.add(clienteImovelEconomia);
				tarifaSocialHelperAtualizar
						.setColecaoClientesEconomiaInseridos(colecaoClientesInseridos);

				sessao.setAttribute("tarifaSocialHelperAtualizar",
						tarifaSocialHelperAtualizar);
			}
		
		} else {
			
			retorno = actionMapping.findForward("exibirRemover");
			httpServletRequest.setAttribute("fecharPopup", true);
			sessao.setAttribute("atualizar", true);
			
			TarifaSocialDadoEconomia tarifaSocialDadoEconomiaRecadastrar = (TarifaSocialDadoEconomia) sessao
					.getAttribute("tarifaSocialRecadastrar");
			
			Collection colecaoTarifasSociaisRecadastradas = null;

			if (sessao
					.getAttribute("colecaoTarifasSociaisRecadastradas") != null) {
				colecaoTarifasSociaisRecadastradas = (Collection) sessao
						.getAttribute("colecaoTarifasSociaisRecadastradas");
			} else {
				colecaoTarifasSociaisRecadastradas = new ArrayList();
			}
			
			colecaoTarifasSociaisRecadastradas
				.add(tarifaSocialDadoEconomiaRecadastrar);

			sessao.setAttribute("colecaoTarifasSociaisRecadastradas",
				colecaoTarifasSociaisRecadastradas);
			
			ArrayList colecaoTarifaSocialHelper = (ArrayList) sessao
				.getAttribute("colecaoTarifaSocialHelper");
			
			int i = 0;
			
			Iterator colecaoTarifaSocialHelperIterator = colecaoTarifaSocialHelper.iterator();
			
			while (colecaoTarifaSocialHelperIterator.hasNext()) {

				TarifaSocialHelper tarifaSocialHelper = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator
						.next();

				if (tarifaSocialHelper.getTarifaSocialDadoEconomia().getId()
						.equals(tarifaSocialDadoEconomiaRecadastrar.getId())) {
					
					tarifaSocialHelper
							.setTarifaSocialDadoEconomia(tarifaSocialDadoEconomiaRecadastrar);
					colecaoTarifaSocialHelper.set(i, tarifaSocialHelper);

					colecaoTarifasSociaisRecadastradas
							.add(tarifaSocialDadoEconomiaRecadastrar);
					
					sessao.setAttribute("colecaoTarifaSocialHelper", colecaoTarifaSocialHelper);

					break;

				}

				i++;

			}
			
		}

		ClienteImovel clienteImovel = new ClienteImovel();

		clienteImovel.setCliente(cliente);

		TarifaSocialHelper tarifaSocialHelperImovelAnterior = new TarifaSocialHelper();

		TarifaSocialDadoEconomia tarifaSocialDadoEconomiaSessao = (TarifaSocialDadoEconomia) sessao.getAttribute("tarifaSocialDadoEconomiaImovelAnterior");
		
		TarifaSocialDadoEconomia tarifaSocialDadoEconomia = fachada.pesquisarTarifaSocial(tarifaSocialDadoEconomiaSessao.getId());
		
		tarifaSocialDadoEconomia.setTarifaSocialRevisaoMotivo(null);
		tarifaSocialDadoEconomia.setDataRevisao(null);

		TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo = new TarifaSocialExclusaoMotivo();

		tarifaSocialExclusaoMotivo.setId(new Integer(idMotivoExclusao));

		tarifaSocialDadoEconomia
				.setTarifaSocialExclusaoMotivo(tarifaSocialExclusaoMotivo);

		tarifaSocialHelperImovelAnterior
				.setTarifaSocialDadoEconomia(tarifaSocialDadoEconomia);

		tarifaSocialHelperImovelAnterior.setClienteImovel(clienteImovel);

		Collection colecaoImoveisExcluidosTarifaSocial = null;

		if (sessao.getAttribute("colecaoImoveisExcluidosTarifaSocial") != null) {
			colecaoImoveisExcluidosTarifaSocial = (Collection) sessao
					.getAttribute("colecaoImoveisExcluidosTarifaSocial");
		} else {
			colecaoImoveisExcluidosTarifaSocial = new ArrayList();
		}

		colecaoImoveisExcluidosTarifaSocial
				.add(tarifaSocialHelperImovelAnterior);

		sessao.setAttribute("colecaoImoveisExcluidosTarifaSocial",
				colecaoImoveisExcluidosTarifaSocial);
		
		sessao.setAttribute("telaLimpa", true);

		return retorno;

	}

}
