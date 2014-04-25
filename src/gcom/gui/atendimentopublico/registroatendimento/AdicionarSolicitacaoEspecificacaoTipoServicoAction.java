package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipo;
import gcom.atendimentopublico.ordemservico.EspecificacaoServicoTipoPK;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela exibição
 * 
 * @author Sávio Luiz
 * @created 28 de Julho de 2006
 */
public class AdicionarSolicitacaoEspecificacaoTipoServicoAction extends
		GcomAction {
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

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAdicionarSolicitacaoEspecificacao");

		if (sessao.getAttribute("retornarTelaPopup") != null) {
			String retonarTelaPopup = (String) sessao
					.getAttribute("retornarTelaPopup");
			if (retonarTelaPopup.equalsIgnoreCase("atualizar")) {
				retorno = actionMapping
						.findForward("exibirAtualizarAdicionarSolicitacaoEspecificacao");
			}
		}

		AdicionarSolicitacaoEspecificacaoActionForm adicionarSolicitacaoEspecificacaoActionForm = (AdicionarSolicitacaoEspecificacaoActionForm) actionForm;

		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoEspecificacaoServicoTipo = null;
		if (sessao.getAttribute("colecaoEspecificacaoServicoTipo") == null) {
			colecaoEspecificacaoServicoTipo = new ArrayList();
		} else {
			colecaoEspecificacaoServicoTipo = (Collection) sessao
					.getAttribute("colecaoEspecificacaoServicoTipo");
		}

		// seta os campos do form no objeto SolicitacaoTipoEspecificacao
		EspecificacaoServicoTipo especificacaoServicoTipo = new EspecificacaoServicoTipo();

		EspecificacaoServicoTipoPK especificacaoServicoTipoPK = new EspecificacaoServicoTipoPK();

		// id do tipo de servico
		if (adicionarSolicitacaoEspecificacaoActionForm.getIdTipoServico() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getIdTipoServico().equals("")) {
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();

			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.ID,
					adicionarSolicitacaoEspecificacaoActionForm
							.getIdTipoServico()));

			Collection servicoTipoEncontrado = fachada.pesquisar(
					filtroServicoTipo, ServicoTipo.class.getName());

			if (servicoTipoEncontrado != null
					&& !servicoTipoEncontrado.isEmpty()) {
				ServicoTipo servicoTipo = (ServicoTipo) ((List) servicoTipoEncontrado)
						.get(0);
				especificacaoServicoTipoPK
						.setIdServicoTipo(servicoTipo.getId());
				especificacaoServicoTipo.setServicoTipo(servicoTipo);
			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null, "Serviço Tipo");
			}

		} else {
			throw new ActionServletException("atencao.required", null,
					"Tipo do Serviço");
		}

		// id do tipo de servico
		if (adicionarSolicitacaoEspecificacaoActionForm.getOrdemExecucao() != null
				&& !adicionarSolicitacaoEspecificacaoActionForm
						.getOrdemExecucao().equals("")) {
			Short ordemExecucao = new Short(
					adicionarSolicitacaoEspecificacaoActionForm
							.getOrdemExecucao());
			// [SF0004] - Validar Valor Ordem de Serviço 1ª parte
			fachada.verificarExistenciaOrdemExecucao(
					colecaoEspecificacaoServicoTipo, ordemExecucao);

			especificacaoServicoTipo.setOrdemExecucao(ordemExecucao);
		}
		// seta o PK na especificação do tipo de serviço
		especificacaoServicoTipo.setComp_id(especificacaoServicoTipoPK);

		especificacaoServicoTipo.setUltimaAlteracao(new Date());

		colecaoEspecificacaoServicoTipo.add(especificacaoServicoTipo);

		sessao.setAttribute("colecaoEspecificacaoServicoTipo",
				colecaoEspecificacaoServicoTipo);

		return retorno;
	}
}
