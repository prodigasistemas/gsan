package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipoOperacao;
import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoOperacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroOperacao;
import gcom.seguranca.acesso.Operacao;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descri��o da classe
 * 
 * @author Administrador
 * @date 07/03/2006
 * 
 */
public class ExibirManterTipoServicoAction extends GcomAction {

	/**
	 * <Breve descri��o sobre o caso de uso>
	 * 
	 * <Identificador e nome do caso de uso>
	 * 
	 * <Breve descri��o sobre o subfluxo>
	 * 
	 * <Identificador e nome do subfluxo>
	 * 
	 * <Breve descri��o sobre o fluxo secund�rio>
	 * 
	 * <Identificador e nome do fluxo secund�rio>
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

		// Parte da verifica��o do filtro
		FiltroTipoServico filtroTipoServico = null;

		// Verifica se o filtro foi informado pela p�gina de filtragem 
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
				// caso o resultado do filtro s� retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema n�o exibe a tela de manter, exibe a de atualizar 
				
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
