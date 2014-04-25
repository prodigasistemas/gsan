package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
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
 * @author Vinícius Medeiros
 * 
 */
public class ExibirManterMotivoCorteAction extends GcomAction {
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
				.findForward("exibirManterMotivoCorte");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoMotivoCorte = new ArrayList();
		
		FiltroMotivoCorte filtroMotivoCorte = (FiltroMotivoCorte) sessao
		.getAttribute("filtroMotivoCorte");
		
		if(filtroMotivoCorte != null && !filtroMotivoCorte.equals("")){
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroMotivoCorte, MotivoCorte.class.getName());
			colecaoMotivoCorte = (Collection) resultado
				.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}		
		
		if (colecaoMotivoCorte != null
				&& !colecaoMotivoCorte.isEmpty()) {
			if (colecaoMotivoCorte.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarMotivoCorte");
					MotivoCorte motivoCorte = (MotivoCorte) colecaoMotivoCorte
							.iterator().next();
					sessao.setAttribute("motivoCorte",
							motivoCorte);
				} else {
					httpServletRequest.setAttribute(
							"colecaoMotivoCorte",
							colecaoMotivoCorte);
				}
			} else {
				httpServletRequest.setAttribute("colecaoMotivoCorte",
						colecaoMotivoCorte);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
}}
