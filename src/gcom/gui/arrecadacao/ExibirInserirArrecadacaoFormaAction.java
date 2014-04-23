package gcom.gui.arrecadacao;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;


/**
 * @author Vinícius de Melo Medeiros
 * @created 08/04/2008
 */
public class ExibirInserirArrecadacaoFormaAction extends GcomAction {
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

		// Mudar isso quando houver um esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("inserirArrecadacaoForma");

		InserirArrecadacaoFormaActionForm inserirArrecadacaoFormaActionForm = (InserirArrecadacaoFormaActionForm) actionForm;

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirArrecadacaoFormaActionForm.setDescricao("");
			inserirArrecadacaoFormaActionForm.setCodigoArrecadacaoForma("");

			if (inserirArrecadacaoFormaActionForm.getDescricao() == null
					|| inserirArrecadacaoFormaActionForm.getDescricao().equals("")&& 
					inserirArrecadacaoFormaActionForm.getCodigoArrecadacaoForma() == null
					|| inserirArrecadacaoFormaActionForm.getCodigoArrecadacaoForma().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();

				filtroArrecadacaoForma.setCampoOrderBy(FiltroArrecadacaoForma.DESCRICAO);

				colecaoPesquisa = fachada.pesquisar(filtroArrecadacaoForma,
						ArrecadacaoForma.class.getName());

				// Caso não haja nenhum registro na tabela
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Forma de Arrecadação");
				} else {
					sessao.setAttribute("colecaoArrecadacaoForma", colecaoPesquisa);
				}

				// Coleção de Forma de Arrecadacao
				FiltroArrecadacaoForma filtroArrecadacaoForma1 = new FiltroArrecadacaoForma();
				filtroArrecadacaoForma1.setCampoOrderBy(FiltroArrecadacaoForma.CODIGO_ARRECADACAO_FORMA);

				Collection colecaoArrecadacaoForma1 = fachada.pesquisar(filtroArrecadacaoForma1,
						ArrecadacaoForma.class.getName());
				sessao.setAttribute("colecaoArrecadacaoForma1", colecaoArrecadacaoForma1);

			}

		}
		return retorno;
	}
}
