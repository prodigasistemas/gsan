package gcom.gui.faturamento;

import gcom.arrecadacao.ContratoDemanda;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.faturamento.FiltroContratoDemanda;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarContratoDemandaAction extends GcomAction {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterContratoDemanda");

		Fachada fachada = Fachada.getInstancia();

		FiltrarContratoDemandaActionForm filtrarContratoDemandaActionForm = (FiltrarContratoDemandaActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		// Limpa todo o formulário para evitar "sujeiras" na tela
		String numeroContrato = filtrarContratoDemandaActionForm
				.getNumeroContrato();
		String idImovel = filtrarContratoDemandaActionForm.getIdImovel();
		String dataInicioContrato = filtrarContratoDemandaActionForm
				.getDataInicioContrato();
		String dataFimContrato = filtrarContratoDemandaActionForm
				.getDataFimContrato();

		// Cria o filtro
		FiltroContratoDemanda filtroContratoDemanda = new FiltroContratoDemanda();

		boolean peloMenosUmParametroInformado = false;

		// Neste ponto o filtro é criado com os parâmetros informados na página
		// de filtrar contrato de demanda para ser executada a pesquisa no
		// ExibirManterContratoDemandaAction
		if (numeroContrato != null
				&& !numeroContrato.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroContratoDemanda.adicionarParametro(new ParametroSimples(
					FiltroContratoDemanda.MUMEROCONTRATO, numeroContrato));
			
			// [FS0003] - Verificar existência do contrato de demanda
			Collection colecaoContratoDemanda = fachada.pesquisar(
					filtroContratoDemanda, ContratoDemanda.class.getName());
			
			if (colecaoContratoDemanda == null || colecaoContratoDemanda.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Contrato de Demanda");
			}
		}

		// Verifica se o imóvel existe e em caso afirmativo
		// seta-a no filtro
		if (idImovel != null && !idImovel.trim().equals("")) {

			Imovel imovel = fachada.pesquisarImovel(new Integer(idImovel));

			if (imovel == null) {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Imóvel");
			} else {
				peloMenosUmParametroInformado = true;
				filtroContratoDemanda.adicionarParametro(new ParametroSimples(
						FiltroContratoDemanda.IMOVEL, idImovel));
			}

		}

		if (dataInicioContrato != null
				&& !dataInicioContrato.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			Date dataInicioFormatada = Util.converteStringParaDate(dataInicioContrato);
			
			filtroContratoDemanda.adicionarParametro(new MaiorQue(
					FiltroContratoDemanda.DATACONTRATOINICIO,
					dataInicioFormatada));
		}

		if (dataFimContrato != null
				&& !dataFimContrato.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			
			Date dataFimFormatada = Util.converteStringParaDate(dataFimContrato);
			
			filtroContratoDemanda.adicionarParametro(new MenorQue(
					FiltroContratoDemanda.DATACONTRATOINICIO, dataFimFormatada));
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pela sessão uma variável para o
		// ExibirManterEquipeAction e nele verificar se irá para o
		// atualizar ou para o manter, caso o checkbox esteja desmarcado remove
		// da sessão
		String indicadorAtualizar = httpServletRequest
				.getParameter("atualizar");

		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {
			sessao.setAttribute("atualizar", indicadorAtualizar);
		} else {
			sessao.removeAttribute("atualizar");
		}

		// Manda o filtro pela sessao para o
		// ExibirManterContratoDemandaAction
		sessao.setAttribute("filtroContratoDemanda", filtroContratoDemanda);

		return retorno;
	}
}
