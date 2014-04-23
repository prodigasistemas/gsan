package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroMaterial;
import gcom.atendimentopublico.ordemservico.Material;
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
 * [UC0383] MANTER MATERIAL
 * 
 * @author Kássia Albuquerque
 * @date 16/11/2006
 */

public class ExibirManterMaterialAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		ActionForward retorno = actionMapping.findForward("manterMaterial");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection colecaoMaterial = null;

		// Parte da verificação do filtro
		FiltroMaterial filtroMaterial = new FiltroMaterial();

		// Verifica se o filtro foi informado pela página de filtragem 
		if (sessao.getAttribute("filtroMaterial") != null) {
			filtroMaterial = (FiltroMaterial) sessao
					.getAttribute("filtroMaterial");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterMaterial"))) {
			
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroMaterial, Material.class.getName());
			colecaoMaterial = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			
			// [UC0382] FILTRAR MATERIAL
			// [FS0001] Nenhum registro encontrado	
			//			
			//	0 Caso  nenhum registro seja encontrado o sistema exibe a mensagem
			//	a baixo. " A pesquisa não retornou  nenhum resultado."
			
			if (colecaoMaterial == null || colecaoMaterial.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
			
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (colecaoMaterial.size()== 1 && identificadorAtualizar != null && !identificadorAtualizar.equals("")){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("exibirAtualizarMaterial");
				Material material = (Material) colecaoMaterial.iterator().next();
				sessao.setAttribute("materialAtualizar", material);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarMaterialAction.do");
				//chama ExibirAtualizarMaterialAction
			}else{
				sessao.setAttribute("colecaoMaterial", colecaoMaterial);
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirManterMaterialAction.do");
				//chama ExibirManterMaterialAction
			}

		}
		sessao.removeAttribute("tipoPesquisaRetorno");
		
		return retorno;
		
		
	} 
	
}
