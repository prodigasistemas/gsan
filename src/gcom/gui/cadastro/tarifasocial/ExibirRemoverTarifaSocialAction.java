package gcom.gui.cadastro.tarifasocial;

import gcom.cadastro.tarifasocial.FiltroTarifaSocialExclusaoMotivo;
import gcom.cadastro.tarifasocial.TarifaSocialDadoEconomia;
import gcom.cadastro.tarifasocial.TarifaSocialExclusaoMotivo;
import gcom.cadastro.tarifasocial.bean.TarifaSocialHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
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
public class ExibirRemoverTarifaSocialAction extends GcomAction {

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

		Fachada fachada = Fachada.getInstancia();

		// Pega uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroTarifaSocialExclusaoMotivo filtroTarifaSocialExclusaoMotivo = new FiltroTarifaSocialExclusaoMotivo();
		filtroTarifaSocialExclusaoMotivo
				.adicionarParametro(new ParametroSimples(
						FiltroTarifaSocialExclusaoMotivo.INDICADOR_USO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroTarifaSocialExclusaoMotivo.setCampoOrderBy(FiltroTarifaSocialExclusaoMotivo.DESCRICAO);

		Collection colecaoTarifaSocialExclusaoMotivo = fachada.pesquisar(
				filtroTarifaSocialExclusaoMotivo,
				TarifaSocialExclusaoMotivo.class.getName());

		sessao.setAttribute("colecaoTarifaSocialExclusaoMotivo",
				colecaoTarifaSocialExclusaoMotivo);

		String idTarifaSocialDadoEconomia = httpServletRequest
				.getParameter("idTarifaSocial");

		if (sessao.getAttribute("colecaoTarifasSociaisRecadastradas") != null) {

			Collection colecaoTarifasSociaisRecadastradas = (Collection) sessao
					.getAttribute("colecaoTarifasSociaisRecadastradas");

			if (!colecaoTarifasSociaisRecadastradas.isEmpty()) {

				Iterator colecaoTarifasSociaisRecadastradasIterator = colecaoTarifasSociaisRecadastradas
						.iterator();

				while (colecaoTarifasSociaisRecadastradasIterator.hasNext()) {
					TarifaSocialDadoEconomia tarifaSocialDadoEconomia = (TarifaSocialDadoEconomia) colecaoTarifasSociaisRecadastradasIterator
							.next();

					if (tarifaSocialDadoEconomia.getId().equals(
							new Integer(idTarifaSocialDadoEconomia))) {
						colecaoTarifasSociaisRecadastradas
								.remove(tarifaSocialDadoEconomia);
						sessao.setAttribute(
								"colecaoTarifasSociaisRecadastradas",
								colecaoTarifasSociaisRecadastradas);

						ArrayList colecaoTarifaSocialHelper = (ArrayList) sessao
								.getAttribute("colecaoTarifaSocialHelper");

						if (colecaoTarifaSocialHelper != null
								&& !colecaoTarifaSocialHelper.isEmpty()) {
							Iterator colecaoTarifaSocialHelperIterator = colecaoTarifaSocialHelper
									.iterator();

							int i = 0;

							while (colecaoTarifaSocialHelperIterator.hasNext()) {
								TarifaSocialHelper tarifaSocialHelper = (TarifaSocialHelper) colecaoTarifaSocialHelperIterator
										.next();

								if (tarifaSocialHelper
										.getTarifaSocialDadoEconomia().getId()
										.toString().equals(
												idTarifaSocialDadoEconomia)) {
									tarifaSocialDadoEconomia = tarifaSocialHelper
											.getTarifaSocialDadoEconomia();
									TarifaSocialExclusaoMotivo tarifaSocialExclusaoMotivo = new TarifaSocialExclusaoMotivo();
									tarifaSocialDadoEconomia
											.setTarifaSocialExclusaoMotivo(tarifaSocialExclusaoMotivo);
									tarifaSocialHelper
											.setTarifaSocialDadoEconomia(tarifaSocialDadoEconomia);
									colecaoTarifaSocialHelper.set(i,
											tarifaSocialHelper);
									sessao.setAttribute("atualizar", true);
									break;
								}

								i++;
							}
						}

						httpServletRequest.setAttribute("fecharPopup", true);
						sessao.setAttribute("atualizar", true);
						break;
					}
				}

			}

		} 
		
		sessao.setAttribute("idTarifaSocial", idTarifaSocialDadoEconomia);

		return retorno;

	}

}
