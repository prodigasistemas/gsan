package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarPrioridadeTipoServicoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarPrioridadeTipoServicoActionForm atualizarPrioridadeTipoServicoActionForm = (AtualizarPrioridadeTipoServicoActionForm) actionForm;

		ServicoTipoPrioridade servicoTipoPrioridade = new ServicoTipoPrioridade();

		servicoTipoPrioridade = (ServicoTipoPrioridade) sessao
				.getAttribute("servicoTipoPrioridadeAtualizar");

		servicoTipoPrioridade.setId(new Integer(atualizarPrioridadeTipoServicoActionForm
				.getCodigo()));
		servicoTipoPrioridade
				.setDescricao(atualizarPrioridadeTipoServicoActionForm
						.getDescricao());
		servicoTipoPrioridade
				.setDescricaoAbreviada(atualizarPrioridadeTipoServicoActionForm
						.getAbreviatura());
		servicoTipoPrioridade.setPrazoExecucaoInicio(new Short(atualizarPrioridadeTipoServicoActionForm
				.getQtdHorasInicio()));
		servicoTipoPrioridade.setPrazoExecucaoFim(new Short(atualizarPrioridadeTipoServicoActionForm
				.getQtdHorasFim()));

		Collection<ServicoTipoPrioridade> colecaoServicoTipoPrioridade = null;

		if (sessao.getAttribute("colecaoServicoTipoPrioridade") != null) {
			colecaoServicoTipoPrioridade = (Collection) sessao
					.getAttribute("colecaoServicoTipoPrioridade");
		}

		fachada.atualizarPrioridadeTipoServico(servicoTipoPrioridade,
				colecaoServicoTipoPrioridade);

		montarPaginaSucesso(httpServletRequest, "Prioridade do Tipo de Serviço "
				+ servicoTipoPrioridade.getId()
				+ " atualizado com sucesso.",
				"Realizar outra Manutenção de Prioridade do Tipo de Serviço",
				"exibirFiltrarPrioridadeTipoServicoAction.do");

		return retorno;

	}

}
