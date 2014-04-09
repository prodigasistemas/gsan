package gcom.gui.micromedicao.hidrometro;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroDiametro;


/**
 * @author Vinícius de Melo Medeiros
 * @created 16/05/2008
 */
public class ExibirInserirHidrometroDiametroAction extends GcomAction {
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

		// Mudar quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("inserirHidrometroDiametro");

		InserirHidrometroDiametroActionForm inserirHidrometroDiametroActionForm = (InserirHidrometroDiametroActionForm) actionForm;

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirHidrometroDiametroActionForm.setDescricao("");
			inserirHidrometroDiametroActionForm.setDescricaoAbreviada("");
			inserirHidrometroDiametroActionForm.setNumeroOrdem("");
			

			if (inserirHidrometroDiametroActionForm.getDescricao() == null
					|| inserirHidrometroDiametroActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;
				FiltroHidrometroDiametro filtroHidrometroDiametro = new FiltroHidrometroDiametro();

				filtroHidrometroDiametro.setCampoOrderBy(FiltroHidrometroDiametro.ID,
														 FiltroHidrometroDiametro.DESCRICAO);
				
				colecaoPesquisa = fachada.pesquisar(filtroHidrometroDiametro,
						HidrometroDiametro.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Diâmetro do Hidrômetro");
				} else {
					sessao.setAttribute("colecaoHidrometroDiametro", colecaoPesquisa);
				}

				// Coleção de Diametro do Hidrometro
				FiltroHidrometroDiametro filtroHidrometroDiametro2 = new FiltroHidrometroDiametro();
				filtroHidrometroDiametro2.setCampoOrderBy(FiltroHidrometroDiametro.ID,
														  FiltroHidrometroDiametro.DESCRICAO);

				Collection colecaoHidrometroDiametro2 = fachada.pesquisar(filtroHidrometroDiametro2,
						HidrometroDiametro.class.getName());
				sessao.setAttribute("colecaoHidrometroDiametro2", colecaoHidrometroDiametro2);

			}

		}
		return retorno;
	}
}
