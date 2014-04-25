package gcom.gui.cobranca;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário as ações de cobrança
 * cadastradas e disponíveis para atualização ou remoção
 * 
 * @author Raphael Rossiter
 * @date 22/10/2007
 */
public class ExibirManterAcaoCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("manterAcaoCobranca");

		HttpSession sessao = httpServletRequest.getSession(false);

		if (sessao.getAttribute("filtroCobrancaCriterio") != null) {

			FiltroCobrancaAcao filtroCobrancaAcao = (FiltroCobrancaAcao) sessao
					.getAttribute("filtroCobrancaCriterio");

			filtroCobrancaAcao
					.adicionarCaminhoParaCarregamentoEntidade("cobrancaCriterio");
			filtroCobrancaAcao
					.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");

			// Componente de Paginação
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroCobrancaAcao, CobrancaAcao.class.getName());

			Collection colecaoCobrancaAcao = (Collection) resultado
					.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			// [FS0001] Verificar existência de dados
			if (colecaoCobrancaAcao == null || colecaoCobrancaAcao.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}

			String identificadorAtualizar = (String) sessao
					.getAttribute("indicadorAtualizar");

			if (colecaoCobrancaAcao.size() == 1
					&& identificadorAtualizar != null) {
				// TELA ATUALIZAR
				retorno = actionMapping.findForward("atualizarAcaoCobranca");
				CobrancaAcao cobrancaAcao = (CobrancaAcao) colecaoCobrancaAcao
						.iterator().next();
				sessao.setAttribute("cobrancaAcao", cobrancaAcao);
			} else {
				sessao.setAttribute("colecaoCobrancaAcao", colecaoCobrancaAcao);
			}
		}

		return retorno;
	}

}
