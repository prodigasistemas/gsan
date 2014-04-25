package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.Tramite;
import gcom.atendimentopublico.registroatendimento.bean.RAFiltroHelper;
import gcom.gui.GcomAction;

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
 * [UC0503]Tramitar Conjunto de Registro de Atendimento
 * 
 * @author Ana Maria
 * @date 15/01/2007
 */
public class LimparRegistroAtendimentoTramitacaoAction extends GcomAction {
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

		// Seta a ação de retorno
		ActionForward retorno = actionMapping
				.findForward("tramitacaoRegistroAtendimento");

		ConjuntoTramitacaoRaActionForm form = (ConjuntoTramitacaoRaActionForm) actionForm;

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		String[] ids = (String[]) form.getIdRegistrosTramitacao();

		Collection tramites = null;
		// Recupera a coleção de tramite da sessão, caso exista, ou cria uma
		// nova
		if (sessao.getAttribute("tramites") != null
				&& !sessao.getAttribute("tramites").equals("")) {
			tramites = (Collection) sessao.getAttribute("tramites");
		} else {
			tramites = new ArrayList();
		}

		boolean achou = false;

		if (ids != null && ids.length != 0) {

			for (int i = 0; i < ids.length; i++) {

				achou = false;
				Tramite tramiteColecao = null;
				String[] idsTramitacao = ids[i].split(";");
				// Verifica a existência da coleção na sessão.
				if (tramites != null && !tramites.isEmpty()) {
					Iterator iteratorTramite = tramites.iterator();
					while (iteratorTramite.hasNext()) {
						tramiteColecao = (Tramite) iteratorTramite.next();
						// Caso exita na colecão da sessão o registro de
						// atendimento selecionado atualiza o tramite existente.
						if (tramiteColecao.getRegistroAtendimento().getId()
								.equals(Integer.parseInt(idsTramitacao[0]))) {
							// Unidade Destino
							tramiteColecao
									.setUnidadeOrganizacionalDestino(null);
							tramiteColecao.setParecerTramite(null);
							achou = true;
							break;
						}
					}
				}
				if (achou) {
					tramites.remove(tramiteColecao);
					// Unidade Destino
					atualizarUnidadeDestinoColecao(Integer
							.parseInt(idsTramitacao[0]), sessao);
				}
			}
		}

		sessao.setAttribute("tramites", tramites);

		httpServletRequest.setAttribute("fecharPopup", "OK");

		sessao.removeAttribute("ConjuntoTramitacaoRaActionForm");

		return retorno;
	}

	/**
	 * Atualiza a unidade destino no filtro da pesquisa
	 * 
	 * @param idRA
	 * @param sessao
	 * @param unidadeDestino
	 */
	private void atualizarUnidadeDestinoColecao(Integer idRA, HttpSession sessao) {
		Collection colecaoRAHelper = (Collection) sessao
				.getAttribute("colecaoRAHelper");
		RAFiltroHelper helper = null;
		Iterator iteratorColecaoRaHelper = colecaoRAHelper.iterator();
		while (iteratorColecaoRaHelper.hasNext()) {
			helper = (RAFiltroHelper) iteratorColecaoRaHelper.next();
			if (helper.getRegistroAtendimento().getId().equals(idRA)) {
				helper.setUnidadeDestino(null);
				break;
			}
		}
	}
}
