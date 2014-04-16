package gcom.gui.cobranca;

import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir manter o critério cobrança
 * @author Sávio Luiz
 * @created 08/05/2006
 */

public class ExibirManterCriterioCobrancaAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterCriterioCobranca");
        
               
		HttpSession sessao = httpServletRequest.getSession(false);

        Collection collectionCriterioCobranca = null;

		// Parte da verificação do filtro
		FiltroCobrancaCriterio filtroCobrancaCriterio = null;

		// Verifica se o filtro foi informado pela página de filtragem de cobranca critério
		if (sessao.getAttribute("filtroCobrancaCriterio") != null) {
			filtroCobrancaCriterio = (FiltroCobrancaCriterio) sessao
					.getAttribute("filtroCobrancaCriterio");
		}
        
		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterCriterioCobranca"))) {
		
					
			filtroCobrancaCriterio.setCampoOrderBy(FiltroCobrancaCriterio.DESCRICAO_COBRANCA_CRITERIO);

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroCobrancaCriterio, CobrancaCriterio.class.getName());
			collectionCriterioCobranca = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");
			

			// [FS0003] Nenhum registro encontrado				
			if (collectionCriterioCobranca == null || collectionCriterioCobranca.isEmpty()) {
				// Nenhum criterio de cobrança cadastrada
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			String identificadorAtualizar = (String)sessao.getAttribute("indicadorAtualizar");
			
			if (collectionCriterioCobranca.size()== 1 && identificadorAtualizar != null){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarCriterioCobranca");
				CobrancaCriterio cobrancaCriterio = (CobrancaCriterio)Util.retonarObjetoDeColecao(collectionCriterioCobranca);
				sessao.setAttribute("idRegistroAtualizacao", new Integer (cobrancaCriterio.getId()).toString());
			}else{
				sessao.setAttribute("collectionCriterioCobranca", collectionCriterioCobranca);
			}
			
		

		}

		sessao.removeAttribute("desabilita");
		sessao.removeAttribute("colecaoCobrancaCriterioLinha");
        return retorno;
        
    }

}
