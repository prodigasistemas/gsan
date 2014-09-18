package gcom.gui.atendimentopublico.ordemservico;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ControladorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0539] Manter Prioridade do Tipo de Serviço 
 * Action form de manter Prioridade Tipo de Servico 
 * remove um ou mais objeto do tipo ServicoTipoPrioridade
 * 
 * @author Vivianne Sousa
 * @created 12/02/2007
 */
public class RemoverPrioridadeTipoServicoAction extends GcomAction {
	/**
	 * @author Vivianne Sousa
	 * @date 12/02/2007
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

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);
		Usuario usuarioLogado = (Usuario)sessao.getAttribute(Usuario.USUARIO_LOGADO);

		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}
		
		try {
			fachada.removerPrioridadeTipoServico(ids,usuarioLogado);
		} catch (ControladorException e) {
			
			e.printStackTrace();
		}

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest,
					 ids.length +
					" Prioridade(s) de Serviço(s) removido(s) com sucesso.",
					"Realizar outra manutenção de Prioridade de Serviço",
					"exibirFiltrarPrioridadeTipoServicoAction.do?desfazer=S");
		}

		return retorno;
	}

}
