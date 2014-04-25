package gcom.gui.arrecadacao;

import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author Arthur Carvalho
 * @created 08 de maio de 2008
 */
public class ExibirInserirPagamentoSituacaoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		ActionForward retorno = actionMapping.findForward("inserirPagamentoSituacao");

		InserirPagamentoSituacaoActionForm inserirPagamentoSituacaoActionForm = (InserirPagamentoSituacaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirPagamentoSituacaoActionForm.setDescricao("");

			if (inserirPagamentoSituacaoActionForm.getDescricao() == null
					|| inserirPagamentoSituacaoActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroPagamentoSituacao filtroPagamentoSituacao = new FiltroPagamentoSituacao();

				filtroPagamentoSituacao.setCampoOrderBy(FiltroPagamentoSituacao.DESCRICAO);

				colecaoPesquisa = fachada.pesquisar(filtroPagamentoSituacao,
						PagamentoSituacao.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Situação de Pagamento");
				} else {
					sessao.setAttribute("colecaoPagamentoSituacao", colecaoPesquisa);
				}

				// Coleção de Pagamento Situação
				filtroPagamentoSituacao = new FiltroPagamentoSituacao();
				filtroPagamentoSituacao.setCampoOrderBy(FiltroPagamentoSituacao.CODIGO);

				Collection colecaoPagamentoSituacao = fachada.pesquisar(filtroPagamentoSituacao,
						PagamentoSituacao.class.getName());
				sessao.setAttribute("colecaoPagamentoSituacao", colecaoPagamentoSituacao);

			}

		}
		return retorno;
	}
}
