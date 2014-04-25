package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteImovelEconomia;
import gcom.cadastro.cliente.ClienteImovelFimRelacaoMotivo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

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
public class RemoverClienteImovelTarifaSocialAction extends GcomAction {

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
				.findForward("exibirDadosCliente");
		
		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);
		
		AtualizarDadosTarifaSocialClienteActionForm atualizarDadosTarifaSocialClienteActionForm = (AtualizarDadosTarifaSocialClienteActionForm) actionForm;

		String motivoFimRelacao = atualizarDadosTarifaSocialClienteActionForm.getClienteImovelFimRelacaoMotivo();
		
		String dataFimRelacao = atualizarDadosTarifaSocialClienteActionForm.getDataFimRelacao();
		Date dataFimRelacaoFormatada = null;
		Date dataAtual = new Date();
		
		if (dataFimRelacao != null && !dataFimRelacao.trim().equals("")) {
			dataFimRelacaoFormatada = Util.converteStringParaDate(dataFimRelacao);
			
			if (dataFimRelacaoFormatada.compareTo(dataAtual) > 0) {
				throw new ActionServletException("atencao.data_fim_relacao_cliente_imovel");
			}
			
		} else {
			dataFimRelacaoFormatada = dataAtual;
		}
		
		TarifaSocialHelper tarifaSocialHelperAtualizar = (TarifaSocialHelper) sessao.getAttribute("tarifaSocialHelperAtualizar");
		
		if (sessao.getAttribute("colecaoClienteImovelRemover") != null) {

			Collection colecaoClienteImovelRemover = (Collection) sessao
					.getAttribute("colecaoClienteImovelRemover");

			Collection colecaoClienteImovel = (Collection) sessao
					.getAttribute("colecaoClienteImovel");

			Iterator colecaoClienteImovelRemoverIterator = colecaoClienteImovelRemover
					.iterator();

			Collection colecaoClienteRemoverTarifaSocial = null;

			if (tarifaSocialHelperAtualizar.getColecaoClientesRemovidos() != null) {
				colecaoClienteRemoverTarifaSocial = tarifaSocialHelperAtualizar
						.getColecaoClientesRemovidos();
			} else {
				colecaoClienteRemoverTarifaSocial = new ArrayList();
			}

			while (colecaoClienteImovelRemoverIterator.hasNext()) {

				ClienteImovel clienteImovel = (ClienteImovel) colecaoClienteImovelRemoverIterator
						.next();
				Date dataInicioRelacao = clienteImovel.getDataInicioRelacao();

				if (dataInicioRelacao.compareTo(dataFimRelacaoFormatada) > 0) {
					throw new ActionServletException(
							"atencao.data_fim_relacao_cliente_imovel_menor_inicial");
				}

				colecaoClienteImovel.remove(clienteImovel);

				ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
				clienteImovelFimRelacaoMotivo.setId(new Integer(
						motivoFimRelacao));
				clienteImovel.setDataFimRelacao(dataFimRelacaoFormatada);
				clienteImovel
						.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacaoMotivo);

				colecaoClienteRemoverTarifaSocial.add(clienteImovel);

			}

			tarifaSocialHelperAtualizar
					.setColecaoClientesRemovidos(colecaoClienteImovelRemover);

		} else if (sessao.getAttribute("colecaoClienteImovelEconomiaRemover") != null) {
			
			Collection colecaoClienteImovelEconomiaRemover = (Collection) sessao
					.getAttribute("colecaoClienteImovelEconomiaRemover");

			Collection colecaoClienteImovelEconomia = (Collection) sessao
					.getAttribute("colecaoClienteImovelEconomia");

			Iterator colecaoClienteImovelEconomiaRemoverIterator = colecaoClienteImovelEconomiaRemover
					.iterator();

			Collection colecaoClienteEconomiaRemoverTarifaSocial = null;

			if (tarifaSocialHelperAtualizar.getColecaoClientesEconomiaRemovidos() != null) {
				colecaoClienteEconomiaRemoverTarifaSocial = tarifaSocialHelperAtualizar
						.getColecaoClientesEconomiaRemovidos();
			} else {
				colecaoClienteEconomiaRemoverTarifaSocial = new ArrayList();
			}

			while (colecaoClienteImovelEconomiaRemoverIterator.hasNext()) {

				ClienteImovelEconomia clienteImovelEconomia = (ClienteImovelEconomia) colecaoClienteImovelEconomiaRemoverIterator
						.next();
				Date dataInicioRelacao = clienteImovelEconomia.getDataInicioRelacao();

				if (dataInicioRelacao.compareTo(dataFimRelacaoFormatada) > 0) {
					throw new ActionServletException(
							"atencao.data_fim_relacao_cliente_imovel_menor_inicial");
				}

				colecaoClienteImovelEconomia.remove(clienteImovelEconomia);

				ClienteImovelFimRelacaoMotivo clienteImovelFimRelacaoMotivo = new ClienteImovelFimRelacaoMotivo();
				clienteImovelFimRelacaoMotivo.setId(new Integer(
						motivoFimRelacao));
				clienteImovelEconomia.setDataFimRelacao(dataFimRelacaoFormatada);
				clienteImovelEconomia
						.setClienteImovelFimRelacaoMotivo(clienteImovelFimRelacaoMotivo);

				colecaoClienteEconomiaRemoverTarifaSocial.add(clienteImovelEconomia);

			}

			tarifaSocialHelperAtualizar
					.setColecaoClientesEconomiaRemovidos(colecaoClienteEconomiaRemoverTarifaSocial);
			
		}
		
		sessao.setAttribute("tarifaSocialHelperAtualizar", tarifaSocialHelperAtualizar);
		
		sessao.setAttribute("telaLimpa", true);

		return retorno;

	}

}
