package gcom.gui.atendimentopublico;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;


/**
 * @author Vinícius de Melo Medeiros
 * @created 31 de março de 2008
 */
public class ExibirInserirMotivoCorteAction extends GcomAction {
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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("inserirMotivoCorte");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		InserirMotivoCorteActionForm inserirMotivoCorteActionForm = (InserirMotivoCorteActionForm) actionForm;
		
		if ((httpServletRequest.getParameter("desfazer") != null && httpServletRequest
				.getParameter("desfazer").equalsIgnoreCase("S"))) {

			inserirMotivoCorteActionForm.setDescricao("");

			if (inserirMotivoCorteActionForm.getDescricao() == null
					|| inserirMotivoCorteActionForm.getDescricao().equals("")) {

				Collection colecaoPesquisa = null;

				FiltroMotivoCorte filtroMotivoCorte = new FiltroMotivoCorte();

				filtroMotivoCorte.setCampoOrderBy(FiltroMotivoCorte.DESCRICAO);

				colecaoPesquisa = fachada.pesquisar(filtroMotivoCorte,
						MotivoCorte.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					throw new ActionServletException(
							"atencao.pesquisa.nenhum_registro_tabela", null,
							"Motivo de Corte");
				} else {
					sessao.setAttribute("colecaoMotivoCorte", colecaoPesquisa);
				}

				// Coleção de Motivo Corte
				FiltroMotivoCorte filtroMotCorte = new FiltroMotivoCorte();
				filtroMotCorte.setCampoOrderBy(FiltroMotivoCorte.ID);

				Collection colecaoMotCorte = fachada.pesquisar(filtroMotCorte,
						MotivoCorte.class.getName());
				sessao.setAttribute("colecaoMotCorte", colecaoMotCorte);

			}

		}
		
		return retorno;
	
	}
}
