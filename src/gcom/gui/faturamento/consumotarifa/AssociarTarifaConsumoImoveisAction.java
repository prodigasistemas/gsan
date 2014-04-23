package gcom.gui.faturamento.consumotarifa;

import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.faturamento.consumotarifa.FiltroConsumoTarifa;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
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
 * @author Rômulo Aurélio
 * @date 07/12/2006
 */
public class AssociarTarifaConsumoImoveisAction extends GcomAction {

	/**
	 * Este caso de uso permite associar a tarifa de consumo para um ou mais
	 * imóveis.
	 * 
	 * [UC0378] Associar Tarifa de Consumo a Imóveis
	 * 
	 * @author Rômulo Aurélio
	 * @date 07/12/2006
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

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		AssociarTarifaConsumoImoveisActionForm associarTarifaConsumoImoveisActionForm = (AssociarTarifaConsumoImoveisActionForm) actionForm;

		Collection colecaoImoveis = null;

		String matricula = associarTarifaConsumoImoveisActionForm.getIdImovel();

		String tarifaAtual = associarTarifaConsumoImoveisActionForm
				.getTarifaAtual();

		if (sessao.getAttribute("colecaoImoveis") != null) {

			colecaoImoveis = (Collection) sessao.getAttribute("colecaoImoveis");

			// atualizacao de imoveis.

			fachada.atualizarImoveisTarifaConsumo(matricula, tarifaAtual,
					colecaoImoveis);

		} else {

			Imovel imovel = new Imovel();

			imovel.setId(new Integer(matricula));

			ConsumoTarifa consumoTarifa = new ConsumoTarifa();

			consumoTarifa.setId(new Integer(tarifaAtual));

			imovel.setConsumoTarifa(consumoTarifa);

			colecaoImoveis = new ArrayList();

			colecaoImoveis.add(imovel);

			// atualiza um unico imovel

			fachada.atualizarImoveisTarifaConsumo(matricula, tarifaAtual,
					colecaoImoveis);

		}

		FiltroConsumoTarifa filtroConsumoTarifa = new FiltroConsumoTarifa();

		filtroConsumoTarifa.adicionarParametro(new ParametroSimples(
				FiltroConsumoTarifa.ID, tarifaAtual));

		Collection colecaoConsumoTarifa = fachada.pesquisar(
				filtroConsumoTarifa, ConsumoTarifa.class.getName());

		ConsumoTarifa consumoTarifaAux = (ConsumoTarifa) colecaoConsumoTarifa
				.iterator().next();

		montarPaginaSucesso(httpServletRequest, "Tarifa de Consumo "
				+ consumoTarifaAux.getDescricao() + " associada com sucesso.",
				"Associar Tarifa de Consumo a outro Imóvel",
				"exibirAssociarTarifaConsumoImoveisAction.do?menu=sim");

		return retorno;
	}
}
