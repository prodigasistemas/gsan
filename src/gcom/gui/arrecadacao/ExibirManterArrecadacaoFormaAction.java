package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
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
public class ExibirManterArrecadacaoFormaAction extends GcomAction {
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

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterArrecadacaoForma");

		Collection colecaoArrecadacaoForma = new ArrayList();

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroArrecadacaoForma filtroArrecadacaoForma = (FiltroArrecadacaoForma) sessao
			.getAttribute("filtroArrecadacaoForma");
		
		if(filtroArrecadacaoForma != null && !filtroArrecadacaoForma.equals("")){
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
			colecaoArrecadacaoForma = (Collection) resultado
				.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		}		
		
		if (colecaoArrecadacaoForma != null
				&& !colecaoArrecadacaoForma.isEmpty()) {
			if (colecaoArrecadacaoForma.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarArrecadacaoForma");
					ArrecadacaoForma arrecadacaoForma = (ArrecadacaoForma) colecaoArrecadacaoForma
							.iterator().next();
					sessao.setAttribute("arrecadacaoForma",
							arrecadacaoForma);
				} else {
					httpServletRequest.setAttribute(
							"colecaoArrecadacaoForma",
							colecaoArrecadacaoForma);
				}
			} else {
				httpServletRequest.setAttribute("colecaoArrecadacaoForma",
						colecaoArrecadacaoForma);
			}
		} else {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		return retorno;
}}
