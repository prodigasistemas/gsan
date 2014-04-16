package gcom.gui.cobranca.parcelamento;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter Perfil de Parcelamento remove um ou mais objeto do tipo
 * PerfilParcelamento
 * 
 * @author Vivianne Sousa
 * @created 11/05/2006
 */
public class RemoverPerfilParcelamentoAction extends GcomAction {
	/**
	 * @author Vivianne Sousa
	 * @date 11/05/2006
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

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}
		
		fachada.removerPerfilParcelamento(ids,this.getUsuarioLogado(httpServletRequest));

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest,
					 ids.length +
					" Perfil(is) de Parcelamento removido(s) com sucesso.",
					"Realizar outra manutenção de Perfil de Parcelamento",
					"exibirFiltrarPerfilParcelamentoAction.do?desfazer=S");
		}

		return retorno;
	}

}
