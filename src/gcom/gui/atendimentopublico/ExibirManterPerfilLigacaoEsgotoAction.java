package gcom.gui.atendimentopublico;


import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoPerfil;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Arthur Carvalho
 * 
 */
public class ExibirManterPerfilLigacaoEsgotoAction extends GcomAction {
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

		ActionForward retorno = actionMapping
				.findForward("exibirManterPerfilLigacaoEsgoto");


		Collection colecaoLigacaoEsgotoPerfil = new ArrayList();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroLigacaoEsgotoPerfil filtroLigacaoEsgotoPerfil = (FiltroLigacaoEsgotoPerfil) sessao
				.getAttribute("filtroLigacaoEsgotoPerfil");

		filtroLigacaoEsgotoPerfil.setCampoOrderBy(FiltroLigacaoEsgotoPerfil.ID);	

		
		if (filtroLigacaoEsgotoPerfil != null
				&& !filtroLigacaoEsgotoPerfil.equals("")) {
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroLigacaoEsgotoPerfil, LigacaoEsgotoPerfil.class.getName());
			colecaoLigacaoEsgotoPerfil = (Collection) resultado
					.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}

		if (colecaoLigacaoEsgotoPerfil != null
				&& !colecaoLigacaoEsgotoPerfil.isEmpty()) {
			if (colecaoLigacaoEsgotoPerfil.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarPerfilLigacaoEsgoto");
					LigacaoEsgotoPerfil ligacaoEsgotoPerfil = (LigacaoEsgotoPerfil) colecaoLigacaoEsgotoPerfil
							.iterator().next();
					sessao.setAttribute("ligacaoEsgotoPerfil", ligacaoEsgotoPerfil);
				} else {
					httpServletRequest.setAttribute("colecaoLigacaoEsgotoPerfil",
							colecaoLigacaoEsgotoPerfil);
				}
			} else {
				httpServletRequest.setAttribute("colecaoLigacaoEsgotoPerfil",
						colecaoLigacaoEsgotoPerfil);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
	}
}
