package gcom.gui.atendimentopublico.registroatendimento;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0403] - Filtrar Especificação da Situação do Imóvel
 * 
 * @author Rafael Pinto
 * @date 08/11/2006
 */
public class ExibirFiltrarEspecificacaoSituacaoImovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarEspecificacaoSituacaoImovel");

		FiltrarEspecificacaoSituacaoImovelActionForm form = 
			(FiltrarEspecificacaoSituacaoImovelActionForm) actionForm;
		
		if (form.getTipoPesquisa() == null || form.getTipoPesquisa().equals("")) {
			form.setTipoPesquisa(""+ConstantesSistema.TIPO_PESQUISA_INICIAL);
		}
		
		this.getSessao(httpServletRequest).setAttribute("indicadorAtualizar", "1");

		return retorno;
	}
}
