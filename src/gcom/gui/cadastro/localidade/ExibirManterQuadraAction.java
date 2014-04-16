package gcom.gui.cadastro.localidade;

import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.Quadra;
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
 * @author COMPESA
 * @date 08/07/2006
 */
public class ExibirManterQuadraAction extends GcomAction {
	
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
     * @author COMPESA
     * @date 08/07/2006
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

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterQuadra");
		HttpSession sessao = httpServletRequest.getSession(false);

        Collection colecaoQuadra = null;

		// Parte da verificação do filtro
        FiltroQuadra filtroQuadra = null;

		// Verifica se o filtro foi informado pela página de filtragem de Quadra
		if (sessao.getAttribute("filtroQuadra") != null) {
			filtroQuadra = (FiltroQuadra) sessao
					.getAttribute("filtroQuadra");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterQuadra"))) {
	

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroQuadra, Quadra.class.getName());
			colecaoQuadra = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			

			// [FS0004] Nenhum registro encontrado				
			if (colecaoQuadra == null || colecaoQuadra.isEmpty()) {
				// Nenhuma Quadra cadastrada
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (colecaoQuadra.size()== 1 && identificadorAtualizar != null){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarQuadra");
				Quadra quadra = (Quadra)colecaoQuadra.iterator().next();
				sessao.setAttribute("idRegistroAtualizar", new Integer (quadra.getId()).toString());
			}else{
				sessao.setAttribute("colecaoQuadra", colecaoQuadra);
			}

		}
		
        return retorno;
        
    }

}
