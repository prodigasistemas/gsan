package gcom.gui.operacional;

import java.util.ArrayList;
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
import gcom.operacional.Bacia;
import gcom.operacional.FiltroBacia;
import gcom.operacional.FiltroSistemaEsgoto;
import gcom.operacional.SistemaEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;



/**
 * @author Arthur Carvalho
 * @created 21 de maio de 2008
 */
public class ExibirInserirBaciaAction extends GcomAction {
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

		ActionForward retorno = actionMapping.findForward("inserirBacia");

		InserirBaciaActionForm inserirBaciaActionForm = (InserirBaciaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		
		Collection colecaoSistemaEsgoto = new ArrayList();
		
		if (sessao.getAttribute("colecaoSistemaEsgoto") == null) {
			FiltroSistemaEsgoto filtroSistemaEsgoto= new FiltroSistemaEsgoto(FiltroSistemaEsgoto.DESCRICAO);
			filtroSistemaEsgoto.adicionarParametro(new ParametroSimples
					(FiltroSistemaEsgoto.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));

			
			colecaoSistemaEsgoto = fachada.pesquisar(
					filtroSistemaEsgoto, SistemaEsgoto.class.getName());
			}
		sessao.setAttribute("colecaoSistemaEsgoto", colecaoSistemaEsgoto);
		
		
		if (httpServletRequest.getParameter("menu") != null && httpServletRequest.getParameter("menu").equals("sim")) {
			inserirBaciaActionForm.setIndicadorUso("2");
		}

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirBaciaActionForm.setDescricao("");

			if (inserirBaciaActionForm.getDescricao() == null
					|| inserirBaciaActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroBacia filtroBacia= new FiltroBacia();

				filtroBacia.setCampoOrderBy(FiltroBacia.ID);

				colecaoPesquisa = fachada.pesquisar(filtroBacia,
						Bacia.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Bacia");
				} else {
					sessao.setAttribute("colecaoBacia", colecaoPesquisa);
				}

				// Coleção de Bacia
				filtroBacia = new FiltroBacia();
				filtroBacia.setCampoOrderBy(FiltroBacia.ID);

				Collection colecaoBacia = fachada.pesquisar(filtroBacia,
						Bacia.class.getName());
				sessao.setAttribute("colecaoBacia", colecaoBacia);

			}

		}
		return retorno;
	}
}
