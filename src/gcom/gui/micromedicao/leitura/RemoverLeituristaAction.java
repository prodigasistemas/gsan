package gcom.gui.micromedicao.leitura;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.micromedicao.Leiturista;
import gcom.micromedicao.leitura.FiltroLeiturista;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Arthur Carvalho
 * @date 29/12/2009
 */

public class RemoverLeituristaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}

		if (ids != null && ids.length != 0) {
			// remove todas as unidade executoras informadas
			for (int i = 0; i < ids.length; i++) {
				// atribui a variável "id" o código da unidade executora para
				// remoção
				int id = Integer.parseInt(ids[i]);

				FiltroLeiturista filtroLeiturista = new FiltroLeiturista();

				filtroLeiturista.adicionarParametro(new ParametroSimples(
						FiltroLeiturista.ID, id));

				Collection colecaoLeiturista = fachada.pesquisar(
						filtroLeiturista, Leiturista.class.getName());

				if (colecaoLeiturista != null
						&& !colecaoLeiturista.isEmpty()) {

					Leiturista leiturista = (Leiturista) colecaoLeiturista
							.iterator().next();

					fachada.remover(leiturista);

				}

			}

		}

		fachada.remover(ids, Leiturista.class.getName(), null, null);

		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest, ids.length
					+ " Leiturista(s) removida(s) com sucesso.",
					"Realizar outra Manutenção de Leiturista",
					"exibirFiltrarLeituristaAction.do?menu=sim");
		}

		return retorno;

	}
}
