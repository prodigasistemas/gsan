package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.ImovelSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 13/06/2006
 */
public class RemoverImovelSituacaoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		String[] idsRegistrosRemocao = manutencaoRegistroActionForm
				.getIdRegistrosRemocao();

		fachada.remover(idsRegistrosRemocao, ImovelSituacao.class.getName(),
				null, null);

		montarPaginaSucesso(httpServletRequest, idsRegistrosRemocao.length
				+ " Situação(ões) de Imóvel(is) excluída(s) com sucesso.",
				"Consultar outra Situação de Imóvel",
				"exibirFiltrarImovelSituacaoAction.do?menu=sim");

		return retorno;

	}
}
