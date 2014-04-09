package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.FiltroAtividade;
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
 * @author Vinícius de Melo Medeiros
 * @created 29/04/2008
 */
public class ExibirInserirAtividadeAction extends GcomAction {
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

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("inserirAtividade");

		InserirAtividadeActionForm inserirAtividadeActionForm = (InserirAtividadeActionForm) actionForm;

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirAtividadeActionForm.setDescricao("");
			inserirAtividadeActionForm.setDescricaoAbreviada("");
			inserirAtividadeActionForm.setIndicadorAtividadeUnica("");
			
			
			if (inserirAtividadeActionForm.getDescricao() == null
					|| inserirAtividadeActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;
					FiltroAtividade filtroAtividade = new FiltroAtividade();

				filtroAtividade.setCampoOrderBy(FiltroAtividade.DESCRICAO);
				
				colecaoPesquisa = fachada.pesquisar(filtroAtividade,
						Atividade.class.getName());

			    // Verifica se há algum registro na tabela
				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Atividade");
				} else {
					sessao.setAttribute("colecaoAtividade", colecaoPesquisa);
				}

				// Coleção de Atividade
				FiltroAtividade filtroAtividade2 = new FiltroAtividade();
				filtroAtividade2.setCampoOrderBy(FiltroAtividade.ID);

				Collection colecaoAtividade2 = fachada.pesquisar(filtroAtividade2,
						Atividade.class.getName());
				
				sessao.setAttribute("colecaoAtividade2", colecaoAtividade2);

			}

		}
		
		return retorno;
	}
}
