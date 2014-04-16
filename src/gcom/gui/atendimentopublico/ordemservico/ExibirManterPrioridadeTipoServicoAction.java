package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipoPrioridade;
import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 04/04/2006
 */
public class ExibirManterPrioridadeTipoServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterPrioridadeTipoServico");

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroServicoTipoPrioridade filtroServicoTipoPrioridade =
			(FiltroServicoTipoPrioridade)sessao.getAttribute("filtroPrioridadeTipoServico");

		// Aciona o controle de paginação para que sejam pesquisados apenas
		// os registros que aparecem na página
		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroServicoTipoPrioridade, ServicoTipoPrioridade.class.getName());
		Collection<ServicoTipoPrioridade> colecaoServicoTipoPrioridade = (Collection) resultado
				.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");

		if (colecaoServicoTipoPrioridade == null || colecaoServicoTipoPrioridade.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Prioridade Tipo Serviço");

		}else{
			
			if (colecaoServicoTipoPrioridade.size() == 1
					&& (httpServletRequest.getParameter("page.offset") == null || httpServletRequest
							.getParameter("page.offset").equals("1"))) {
				
				if (httpServletRequest.getParameter("indicadorAtualizar") != null) {
					retorno = actionMapping
							.findForward("exibirAtualizarPrioridadeTipoServico");
					
					ServicoTipoPrioridade servicoTipoPrioridade = (ServicoTipoPrioridade) colecaoServicoTipoPrioridade
							.iterator().next();
					
					sessao.setAttribute("objetoServicoTipoPrioridade", servicoTipoPrioridade);

				} else {
					
					httpServletRequest.setAttribute("colecaoServicoTipoPrioridade",
							colecaoServicoTipoPrioridade);
				}
			} else {
				
				httpServletRequest.setAttribute("colecaoServicoTipoPrioridade",
						colecaoServicoTipoPrioridade);
			}
		}

		sessao.setAttribute("colecaoServicoTipoPrioridade", colecaoServicoTipoPrioridade);

		return retorno;
	}
}
