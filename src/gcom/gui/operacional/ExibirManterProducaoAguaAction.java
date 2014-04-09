package gcom.gui.operacional;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroProducaoAgua;
import gcom.operacional.ProducaoAgua;

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
public class ExibirManterProducaoAguaAction extends GcomAction {
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
		ActionForward retorno = actionMapping.findForward("exibirManterProducaoAgua");

		Collection colecaoProducaoAgua = new ArrayList();

		// Mudar isso quando for implementado o esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroProducaoAgua filtroProducaoAgua = (FiltroProducaoAgua) sessao.getAttribute("filtroProducaoAgua");
		filtroProducaoAgua.adicionarCaminhoParaCarregamentoEntidade(FiltroProducaoAgua.LOCALIDADE);
		
		filtroProducaoAgua.setCampoOrderBy(FiltroProducaoAgua.ID);

		if (filtroProducaoAgua != null && !filtroProducaoAgua.equals("")) {
			
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroProducaoAgua, ProducaoAgua.class.getName());
			colecaoProducaoAgua = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
		
		}

		if (colecaoProducaoAgua != null && !colecaoProducaoAgua.isEmpty()) {
		
			if (colecaoProducaoAgua.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null 
							|| httpServletRequest.getParameter("page.offset").equals("1"))) {
				
				// Caso o indicador ATUALIZAR esteja marcado, altera o caminho do retorno
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
				
					retorno = actionMapping.findForward("exibirAtualizarProducaoAgua");
					ProducaoAgua producaoAgua = (ProducaoAgua) colecaoProducaoAgua.iterator().next();
					sessao.setAttribute("producaoAgua", producaoAgua);
				
				} else {
				
					httpServletRequest.setAttribute("colecaoProducaoAgua",colecaoProducaoAgua);
				
				}
			} else {
				
				httpServletRequest.setAttribute("colecaoProducaoAgua",colecaoProducaoAgua);
			}
		} else {
			
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
	
		}

		return retorno;
	}
}
