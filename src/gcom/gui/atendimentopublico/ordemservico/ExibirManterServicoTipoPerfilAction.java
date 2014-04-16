package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoPerfilTipo;
import gcom.atendimentopublico.ordemservico.ServicoPerfilTipo;
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
 * [UC0387] MANTER TIPO PERFIL SERVICO]
 * 
 * @author Kássia Albuquerque
 * @date 26/10/2006
 */

public class ExibirManterServicoTipoPerfilAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping
		.findForward("manterTipoPerfilServico");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoServicoTipoPerfil = null;

		// Parte da verificação do filtro
        FiltroServicoPerfilTipo filtroServicoPerfilTipo = null;

		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("filtroServicoPerfilTipo") != null) {
			filtroServicoPerfilTipo = (FiltroServicoPerfilTipo) sessao
					.getAttribute("filtroServicoPerfilTipo");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterTipoPerfilServico"))) {
			
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroServicoPerfilTipo, ServicoPerfilTipo.class.getName());
			colecaoServicoTipoPerfil = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			

			// [FS0002] Nenhum registro encontrado				
			if (colecaoServicoTipoPerfil == null || colecaoServicoTipoPerfil.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (colecaoServicoTipoPerfil.size()== 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("exibirAtualizarTipoPerfilServico");
				ServicoPerfilTipo servicoPerfilTipo = (ServicoPerfilTipo) colecaoServicoTipoPerfil.iterator().next();
				sessao.setAttribute("idRegistroAtualizar", servicoPerfilTipo);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarTipoPerfilServicoAction.do");
				//chama ExibirAtualizarTipoPerfilServicoAction
			}else{
				sessao.setAttribute("colecaoServicoTipoPerfil", colecaoServicoTipoPerfil);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterServicoTipoPerfilAction.do");
				//chama ExibirManterTipoPerfilServicoAction
			}

		}
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		return retorno;
		
		
	} 
	
}
