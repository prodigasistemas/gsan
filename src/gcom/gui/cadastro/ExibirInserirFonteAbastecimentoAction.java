package gcom.gui.cadastro;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

/**
 * @author Arthur Carvalho
 * @created 14 de agosto de 2008
 */
public class ExibirInserirFonteAbastecimentoAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("inserirFonteAbastecimento");

		InserirFonteAbastecimentoActionForm inserirFonteAbastecimentoActionForm = (InserirFonteAbastecimentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			inserirFonteAbastecimentoActionForm.setIndicadorCalcularVolumeFixo(new Short("2"));
			inserirFonteAbastecimentoActionForm.setIndicadorPermitePoco(new Short("2"));
		}
		
		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirFonteAbastecimentoActionForm.setDescricao("");

			if (inserirFonteAbastecimentoActionForm.getDescricao() == null
					|| inserirFonteAbastecimentoActionForm
							.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();

				filtroFonteAbastecimento
						.setCampoOrderBy(FiltroFonteAbastecimento.ID);

				colecaoPesquisa = fachada.pesquisar(
						filtroFonteAbastecimento,
						FonteAbastecimento.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Fonte de Abastecimento");
				} else {
					sessao.setAttribute("colecaoFonteAbastecimento",
							colecaoPesquisa);
				}

				// Coleção da Fonte de Abastecimento
				filtroFonteAbastecimento = new FiltroFonteAbastecimento();
				
				filtroFonteAbastecimento
						.setCampoOrderBy(FiltroFonteAbastecimento.ID);

				Collection colecaoFonteAbastecimento = fachada
						.pesquisar(filtroFonteAbastecimento,
								FonteAbastecimento.class.getName());
				sessao.setAttribute("colecaoFonteAbastecimento",
						colecaoFonteAbastecimento);

			}

		}
		return retorno;
	}
}
