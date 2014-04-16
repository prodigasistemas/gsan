package gcom.gui.cobranca;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processa a remoção do critério cobrança
 * 
 * @author Sávio Luiz
 * @created03/04/2006
 */
public class RemoverCriterioCobrancaAction extends GcomAction {
	/**
	 * @author Vivianne Sousa
	 * @date 03/04/2006
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

		HttpSession sessao = httpServletRequest.getSession(false);

		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		String qtIdsRemovidos = ""+ids.length;
		
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}

		fachada.removerCobrancaCriterio(ids,this.getUsuarioLogado(httpServletRequest));

		sessao.removeAttribute("indicadorAtualizar");
		sessao.removeAttribute("voltar");

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest,
					qtIdsRemovidos+" Critério(s) de Cobrança removido(s) com sucesso.",
					"Realizar outra Manutenção de Critério de Cobrança",
					"exibirFiltrarCriterioCobrancaAction.do?menu=sim");
		}

		return retorno;
	}

}
