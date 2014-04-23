package gcom.gui.micromedicao;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0174] Pesquisar Local de Armazenagem do Hidrometro
 * 
 * @date 10/09/2008
 * @author Arthur Carvalho
 */
public class ExibirPesquisarLocalArmazenagemHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirPesquisarLocalArmazenagemHidrometro");

		
		PesquisarLocalArmazenagemHidrometroActionForm pesquisarLocalArmazenagemHidrometroActionForm = (PesquisarLocalArmazenagemHidrometroActionForm) actionForm;
		
		if (httpServletRequest.getParameter("limparForm") != null && !httpServletRequest.getParameter("limparForm").equals("")) {
			pesquisarLocalArmazenagemHidrometroActionForm.setDescricao("");
			pesquisarLocalArmazenagemHidrometroActionForm.setDescricaoAbreviada("");
			pesquisarLocalArmazenagemHidrometroActionForm.setIndicadorOficina("");
			pesquisarLocalArmazenagemHidrometroActionForm.setCodigo("");
		}
		
		pesquisarLocalArmazenagemHidrometroActionForm.setTipoPesquisa(ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());

		return retorno;

	}

}
