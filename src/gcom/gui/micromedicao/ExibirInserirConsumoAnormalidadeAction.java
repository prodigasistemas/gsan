package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.consumo.ConsumoAnormalidade;
import gcom.micromedicao.consumo.FiltroConsumoAnormalidade;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author Vinícius de Melo Medeiros
 * @created 29/04/2008
 */
public class ExibirInserirConsumoAnormalidadeAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("inserirConsumoAnormalidade");

		InserirConsumoAnormalidadeActionForm inserirConsumoAnormalidadeActionForm = (InserirConsumoAnormalidadeActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		inserirConsumoAnormalidadeActionForm.setMensagemConta("".toUpperCase());
		inserirConsumoAnormalidadeActionForm.setIndicadorRevisaoComPermissaoEspecial("" + ConstantesSistema.NAO );
		
		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirConsumoAnormalidadeActionForm.setDescricao("");
			inserirConsumoAnormalidadeActionForm.setDescricaoAbreviada("");
			inserirConsumoAnormalidadeActionForm.setMensagemConta("");
			inserirConsumoAnormalidadeActionForm.setIndicadorRevisaoComPermissaoEspecial("" + ConstantesSistema.NAO );
			
			if (inserirConsumoAnormalidadeActionForm.getDescricao() == null
					|| inserirConsumoAnormalidadeActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;
				FiltroConsumoAnormalidade filtroConsumoAnormalidade = new FiltroConsumoAnormalidade();

				filtroConsumoAnormalidade.setCampoOrderBy(FiltroConsumoAnormalidade.DESCRICAO);
				
				colecaoPesquisa = fachada.pesquisar(filtroConsumoAnormalidade,
						ConsumoAnormalidade.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Anormalidade de Consumo");
				} else {
					sessao.setAttribute("colecaoConsumoAnormalidade", colecaoPesquisa);
				}

				// Coleção de Anormalidade de Consumo
				FiltroConsumoAnormalidade filtroConsumoAnormalidade2 = new FiltroConsumoAnormalidade();
				filtroConsumoAnormalidade2.setCampoOrderBy(FiltroConsumoAnormalidade.ID);

				Collection colecaoConsumoAnormalidade2 = fachada.pesquisar(filtroConsumoAnormalidade2,
						ConsumoAnormalidade.class.getName());
				sessao.setAttribute("colecaoConsumoAnormalidade2", colecaoConsumoAnormalidade2);

			}

		}
		return retorno;
	}
}
