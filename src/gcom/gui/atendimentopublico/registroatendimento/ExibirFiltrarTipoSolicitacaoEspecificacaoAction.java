package gcom.gui.atendimentopublico.registroatendimento;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.atendimentopublico.registroatendimento.FiltroSolicitacaoTipoGrupo;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoGrupo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 06/11/2006
 */
public class ExibirFiltrarTipoSolicitacaoEspecificacaoAction extends GcomAction {

	/**
	 * [UC0400] Filtrar Tipo de Solicitação com Especificações
	 * 
	 * Este caso de uso cria um filtro que será usado na pesquisa de Tipo de
	 * Solicitação com Especificações
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

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarTipoSolicitacaoEspecificacao");
		FiltrarTipoSolicitacaoEspecificacaoActionForm filtrarTipoSolicitacaoEspecificacaoActionForm = (FiltrarTipoSolicitacaoEspecificacaoActionForm) actionForm;
		// limpa os campos da sessão
		if (httpServletRequest.getParameter("limpaSessao") != null
				&& !httpServletRequest.getParameter("limpaSessao").equals("")) {
			filtrarTipoSolicitacaoEspecificacaoActionForm.setDescricao("");
			filtrarTipoSolicitacaoEspecificacaoActionForm
					.setIndicadorFaltaAgua("");
			filtrarTipoSolicitacaoEspecificacaoActionForm
					.setIndicadorTarifaSocial("");
			filtrarTipoSolicitacaoEspecificacaoActionForm
					.setIdgrupoTipoSolicitacao("");
			//sessao.removeAttribute("colecaoSolicitacaoTipoEspecificacao");
		}

		Fachada fachada = Fachada.getInstancia();
		if (sessao.getAttribute("") == null
				|| sessao.getAttribute("").equals("")) {

			FiltroSolicitacaoTipoGrupo filtroSolicitacaoTipoGrupo = new FiltroSolicitacaoTipoGrupo();
			filtroSolicitacaoTipoGrupo.adicionarParametro(new ParametroSimples(
					FiltroSolicitacaoTipoGrupo.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			Collection colecaoSolicitacaoTipoGrupo = fachada.pesquisar(
					filtroSolicitacaoTipoGrupo, SolicitacaoTipoGrupo.class
							.getName());
			sessao.setAttribute("colecaoSolicitacaoTipoGrupo",
					colecaoSolicitacaoTipoGrupo);

		}
		filtrarTipoSolicitacaoEspecificacaoActionForm.setAtualizar("1");

		filtrarTipoSolicitacaoEspecificacaoActionForm
				.setIndicadorFaltaAgua("3");
		
		filtrarTipoSolicitacaoEspecificacaoActionForm.setIndicadorTarifaSocial("3");

		filtrarTipoSolicitacaoEspecificacaoActionForm.setIndicadorUso("3");

		// remove o parametro de retorno
		sessao.removeAttribute("retornarTela");

		return retorno;
	}
}
