package gcom.gui.atendimentopublico.registroatendimento;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipo;
import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoGrupo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
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
 * Action que define o pré-processamento da página de pesquisa de cliente
 * 
 * @author Thiago Tenório
 * @created 25 de Abril de 2005
 */
public class ExibirPesquisarTipoSolicitacaoEspecificacoesAction extends GcomAction {
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("pesquisarTipoSolicitacaoEspecificacoes");
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Parte que passa as coleções necessárias no jsp
		FiltroSolicitacaoTipoGrupo filtroSolicitacaoTipoGrupo = new FiltroSolicitacaoTipoGrupo();
		filtroSolicitacaoTipoGrupo.setCampoOrderBy(FiltroSolicitacaoTipo.ID);
		Collection colecaoSolicitacaoTipoGrupo = fachada.pesquisar(
				filtroSolicitacaoTipoGrupo, SolicitacaoTipoGrupo.class.getName());

		if (colecaoSolicitacaoTipoGrupo != null && !colecaoSolicitacaoTipoGrupo.isEmpty()) {
			sessao.setAttribute("colecaoSolicitacaoTipoGrupo", colecaoSolicitacaoTipoGrupo);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Grupo do Tipo da Solicitação");
		}
		
		if(httpServletRequest.getParameter("tipo") != null){
			sessao.setAttribute("tipo", httpServletRequest.getParameter("tipo"));
		}
		


		return retorno;
	}
}
