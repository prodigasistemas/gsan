package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.ServicoTipoPrioridade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 11/08/2006
 */
public class InserirPrioridadeTipoServicoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um tipo de serviço de referência.
	 * 
	 * [UC0436] Inserir Tipo de Serviço de Referência
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 11/08/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirPrioridadeTipoServicoActionForm inserirPrioridadeTipoServicoActionForm = (InserirPrioridadeTipoServicoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		// Setando no Objeto ServicoTipoPrioridade os dados do form

		String descricao = inserirPrioridadeTipoServicoActionForm
				.getDescricao();

		String descricaoAbreviada = inserirPrioridadeTipoServicoActionForm
				.getAbreviatura();

		String qtdHorasInicio = inserirPrioridadeTipoServicoActionForm
				.getQtdHorasInicio();

		String qtdHorasFim = inserirPrioridadeTipoServicoActionForm
				.getQtdHorasFim();

		ServicoTipoPrioridade servicoTipoPrioridade = new ServicoTipoPrioridade();

		servicoTipoPrioridade.setDescricao(descricao);

		servicoTipoPrioridade.setDescricaoAbreviada(descricaoAbreviada);
		
		if (qtdHorasInicio != null && !qtdHorasInicio.equals("")) {
			servicoTipoPrioridade.setPrazoExecucaoInicio(new Short(qtdHorasInicio));
		}
		
		if (qtdHorasFim != null && !qtdHorasFim.equals("")) {
			servicoTipoPrioridade.setPrazoExecucaoFim(new Short(qtdHorasFim));
		}
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		Integer idPrioridadeTipoServico = fachada
				.inserirPrioridadeTipoServico(servicoTipoPrioridade, usuarioLogado);

		montarPaginaSucesso(httpServletRequest,
				"Prioridade do Tipo de Serviço de código "
						+ idPrioridadeTipoServico + " inserido com sucesso.",
				"Inserir outra Prioridade do Tipo de Serviço",
				"exibirInserirPrioridadeTipoServicoAction.do?menu=sim");

		return retorno;

	}
}
