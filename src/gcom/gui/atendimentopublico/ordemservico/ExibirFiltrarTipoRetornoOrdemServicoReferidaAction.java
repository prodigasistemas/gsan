package gcom.gui.atendimentopublico.ordemservico;
import gcom.atendimentopublico.ordemservico.FiltroOSReferidaRetornoTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
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
public class ExibirFiltrarTipoRetornoOrdemServicoReferidaAction extends GcomAction {
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
		
		FiltrarTipoRetornoOrdemServicoReferidaActionForm filtrarTipoRetornoOrdemServicoReferidaActionForm = (FiltrarTipoRetornoOrdemServicoReferidaActionForm) actionForm;
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		if (httpServletRequest.getParameter("menu") != null) {

			filtrarTipoRetornoOrdemServicoReferidaActionForm.setAtualizar("1");
			filtrarTipoRetornoOrdemServicoReferidaActionForm.setDeferimento("");
			filtrarTipoRetornoOrdemServicoReferidaActionForm.setTrocaServico("");
			filtrarTipoRetornoOrdemServicoReferidaActionForm.setSituacao("");
			filtrarTipoRetornoOrdemServicoReferidaActionForm.setIndicadorUso("");
			sessao.setAttribute("atualizar", "1");

		} else {

			if (httpServletRequest.getParameter("paginacao") == null && sessao.getAttribute("filtrar") == null) {

				String atualizar = httpServletRequest.getParameter("atualizar");

				if (atualizar != null && !atualizar.equals("")) {
					sessao.setAttribute("atualizar", atualizar);
				} else {
					sessao.removeAttribute("atualizar");
				}

			}
		}
			
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("filtrarTipoRetornoOrdemServicoReferida");
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		sessao.removeAttribute("filtrar");

		// Parte que passa as coleções necessárias no jsp
		FiltroServicoTipoReferencia filtroServicoTipoReferencia = new FiltroServicoTipoReferencia();
		filtroServicoTipoReferencia.setCampoOrderBy(FiltroOSReferidaRetornoTipo.ID);
		Collection colecaoServicoTipoReferencia = fachada.pesquisar(
				filtroServicoTipoReferencia, ServicoTipoReferencia.class.getName());

		if (colecaoServicoTipoReferencia != null && !colecaoServicoTipoReferencia.isEmpty()) {
			sessao.setAttribute("colecaoServicoTipoReferencia", colecaoServicoTipoReferencia);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Referência do Tipo de Serviço");
		}
		FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = new FiltroAtendimentoMotivoEncerramento();
		filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.ID);
		Collection colecaoAtendimentoMotivoEncerramento = fachada.pesquisar(
				filtroAtendimentoMotivoEncerramento, AtendimentoMotivoEncerramento.class.getName());

		if (colecaoAtendimentoMotivoEncerramento != null && !colecaoAtendimentoMotivoEncerramento.isEmpty()) {
			sessao.setAttribute("colecaoAtendimentoMotivoEncerramento", colecaoAtendimentoMotivoEncerramento);
		} else {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Motivo de Encerramento do Atendimento");
		}
		
		
		return retorno;



	}

}
