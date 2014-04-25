package gcom.gui.arrecadacao;



import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;




/**
 * 
 * @author Arthur Carvalho
 * @date 08/05/2008
 */
public class ExibirAtualizarPagamentoSituacaoAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
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
				.findForward("pagamentoSituacaoAtualizar");

		AtualizarPagamentoSituacaoActionForm atualizarPagamentoSituacaoActionForm = (AtualizarPagamentoSituacaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		String id = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null){
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
		}
		else{
			id = ((PagamentoSituacao) sessao.getAttribute("pagamentoSituacao")).getId().toString();
		}
		

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		PagamentoSituacao pagamentoSituacao= new PagamentoSituacao();
						
		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			FiltroPagamentoSituacao filtroPagamentoSituacao = new FiltroPagamentoSituacao();
			filtroPagamentoSituacao.adicionarParametro(new ParametroSimples(
					FiltroPagamentoSituacao.CODIGO, id));
			Collection colecaoPagamentoSituacao = fachada.pesquisar(
					filtroPagamentoSituacao, PagamentoSituacao.class.getName());
			if (colecaoPagamentoSituacao != null
					&& !colecaoPagamentoSituacao.isEmpty()) {
				pagamentoSituacao= (PagamentoSituacao) Util
						.retonarObjetoDeColecao(colecaoPagamentoSituacao);
			}

			if (id == null || id.trim().equals("")) {

				atualizarPagamentoSituacaoActionForm.setId(pagamentoSituacao
						.getId().toString());

				atualizarPagamentoSituacaoActionForm
						.setDescricao(pagamentoSituacao.getDescricao());

				atualizarPagamentoSituacaoActionForm
						.setDescricaoAbreviada(pagamentoSituacao
								.getDescricaoAbreviada());

			}
			
			atualizarPagamentoSituacaoActionForm.setId(pagamentoSituacao.getId().toString());
			
			atualizarPagamentoSituacaoActionForm.setDescricao(pagamentoSituacao.getDescricao());
			
			atualizarPagamentoSituacaoActionForm.setDescricaoAbreviada(pagamentoSituacao.getDescricaoAbreviada());
			
			atualizarPagamentoSituacaoActionForm.setIndicadorUso(pagamentoSituacao.getIndicadorUso().toString());

			
			sessao.setAttribute("atualizarPagamentoSituacao", pagamentoSituacao);

			if (sessao.getAttribute("colecaoPagamentoSituacao") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarPagamentoSituacaoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarPagamentoSituacaoAction.do");
			}

		}
		

		return retorno;
	}
}
