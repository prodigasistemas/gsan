package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
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
 * @author Administrador
 * @date 07/03/2006
 * 
 */
public class ExibirManterTipoServicoAction extends GcomAction {

	/**
	 * <Breve descrição sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descrição sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descrição sobre o fluxo secundário>
	 * 
	 * <Identificador e nome do fluxo secundário>
	 * 
	 * @author Administrador
	 * @date 07/03/2006
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
		

		ActionForward retorno = actionMapping.findForward("manterTipoServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoServicoTipo = null;

		// Parte da verificação do filtro
		FiltroTipoServico filtroTipoServico = null;

		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("filtroTipoServico") != null) {
			filtroTipoServico = (FiltroTipoServico) sessao
					.getAttribute("filtroTipoServico");
			filtroTipoServico
			.adicionarCaminhoParaCarregamentoEntidade("programaCalibragem");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterTipoServico"))) {
			
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroTipoServico, ServicoTipo.class.getName());
			colecaoServicoTipo = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			

			// [FS0002] Nenhum registro encontrado				
			if (colecaoServicoTipo == null || colecaoServicoTipo.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)httpServletRequest.getParameter("indicadorAtualizar");
			
			if (colecaoServicoTipo.size()== 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("exibirAtualizarTipoServico");
				ServicoTipo servicoTipo = (ServicoTipo) colecaoServicoTipo.iterator().next();
				sessao.setAttribute("servicoTipo", servicoTipo);
				sessao.setAttribute("pesquisa", "S");
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarTipoPerfilServicoAction.do");
				//chama ExibirAtualizarTipoPerfilServicoAction
			}else{
				sessao.setAttribute("colecaoServicoTipo", colecaoServicoTipo);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterServicoTipoPerfilAction.do");
				//chama ExibirManterTipoPerfilServicoAction
			}

		}
		sessao.removeAttribute("tipoPesquisaRetorno");

		return retorno;
	}

}
