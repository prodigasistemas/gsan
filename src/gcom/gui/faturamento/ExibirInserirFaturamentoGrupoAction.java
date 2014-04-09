package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
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
 * @author Vinícius de Melo Medeiros
 * @created 31 de março de 2008
 */
public class ExibirInserirFaturamentoGrupoAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("inserirFaturamentoGrupo");

		InserirFaturamentoGrupoActionForm inserirFaturamentoGrupoActionForm = (InserirFaturamentoGrupoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirFaturamentoGrupoActionForm.setDescricao("");
			inserirFaturamentoGrupoActionForm.setDescricaoAbreviada("");
			inserirFaturamentoGrupoActionForm.setAnoMesReferencia("");
			inserirFaturamentoGrupoActionForm.setDiaVencimento("");
			inserirFaturamentoGrupoActionForm.setIndicadorVencimentoMesFatura("");

			
			if (inserirFaturamentoGrupoActionForm.getDescricao() == null
					|| inserirFaturamentoGrupoActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;
				FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();

				filtroFaturamentoGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.DESCRICAO);
				
				colecaoPesquisa = fachada.pesquisar(filtroFaturamentoGrupo,
						FaturamentoGrupo.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Grupo de Faturamento");
				} else {
					sessao.setAttribute("colecaoFaturamentoGrupo", colecaoPesquisa);
				}

				// Coleção de Grupo de Faturamento
				FiltroFaturamentoGrupo filtroFatGrupo = new FiltroFaturamentoGrupo();
				filtroFatGrupo.setCampoOrderBy(FiltroFaturamentoGrupo.ID);

				Collection colecaoFatGrupo = fachada.pesquisar(filtroFatGrupo,
						FaturamentoGrupo.class.getName());
				sessao.setAttribute("colecaoFatGrupo", colecaoFatGrupo);

			}

		}
		return retorno;
	}
}
