package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
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
 * @author Vinicius Medeiros
 * @date 10/04/2008
 */
public class ExibirAtualizarArrecadacaoFormaAction extends GcomAction {

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

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarArrecadacaoForma");

		AtualizarArrecadacaoFormaActionForm atualizarArrecadacaoFormaActionForm = (AtualizarArrecadacaoFormaActionForm) actionForm;

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String idArrecadacaoForma = httpServletRequest
			.getParameter("idRegistroAtualizacao");

		// Verifica se veio do Manter ou do Filtrar
		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (idArrecadacaoForma == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				idArrecadacaoForma = (String) sessao
						.getAttribute("idRegistroAtualizacao");
			} else {
				idArrecadacaoForma = (String) httpServletRequest.getAttribute(
						"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();

		
		if (idArrecadacaoForma != null && !idArrecadacaoForma.trim().equals("")
				&& Integer.parseInt(idArrecadacaoForma) > 0) {

			FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
			filtroArrecadacaoForma.adicionarParametro(new ParametroSimples(
					FiltroArrecadacaoForma.CODIGO, idArrecadacaoForma));
			Collection colecaoArrecadacaoForma = fachada.pesquisar(
					filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
			if (colecaoArrecadacaoForma != null
					&& !colecaoArrecadacaoForma.isEmpty()) {
				arrecadacaoForma = (ArrecadacaoForma) Util
						.retonarObjetoDeColecao(colecaoArrecadacaoForma);
			}

			if (idArrecadacaoForma == null
					|| idArrecadacaoForma.trim().equals("")) {

				atualizarArrecadacaoFormaActionForm
						.setIdArrecadacaoForma(arrecadacaoForma.getId()
								.toString());
				atualizarArrecadacaoFormaActionForm
						.setDescricao(arrecadacaoForma.getDescricao());
				atualizarArrecadacaoFormaActionForm
						.setCodigoArrecadacaoForma(arrecadacaoForma
								.getCodigoArrecadacaoForma());

			}

			atualizarArrecadacaoFormaActionForm
					.setIdArrecadacaoForma(idArrecadacaoForma);

			atualizarArrecadacaoFormaActionForm.setDescricao(arrecadacaoForma
					.getDescricao());

			atualizarArrecadacaoFormaActionForm
					.setCodigoArrecadacaoForma(arrecadacaoForma
							.getCodigoArrecadacaoForma());

			sessao.setAttribute("arrecadacaoFormaAtualizar", arrecadacaoForma);

			if (sessao.getAttribute("colecaoArrecadacaoForma") != null) {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarArrecadacaoFormaAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarArrecadacaoFormaAction.do");
			}

		}

		return retorno;
	}
}
