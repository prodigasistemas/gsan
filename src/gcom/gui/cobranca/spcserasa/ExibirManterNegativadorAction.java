package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.Negativador;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativador;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir manter Negativador
 * @author Thiago Vieira
 * @created 22/12/2007
 */

public class ExibirManterNegativadorAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterNegativador");
		HttpSession sessao = httpServletRequest.getSession(false);
        Collection collectionNegativador = null;

        FiltroNegativador filtroNegativador = null;
        
		// Verifica se o filtro foi informado pela página de filtragem do Negativador
		if (sessao.getAttribute("filtroNegativador") != null) {
			filtroNegativador = (FiltroNegativador) sessao.getAttribute("filtroNegativador");
		}
		
		String checkAtualizar = "";
		
		if (sessao.getAttribute("checkAtualizar") != null) {
			checkAtualizar = (String) sessao.getAttribute("checkAtualizar");
		}

		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterNegativador"))) {
				
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("cliente");
			filtroNegativador.adicionarCaminhoParaCarregamentoEntidade("imovel");
			
			filtroNegativador.setCampoOrderBy(FiltroNegativador.ID);

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtroNegativador, Negativador.class.getName());
			
			
			collectionNegativador = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			// [FS0004] Nenhum registro encontrado				
			if (collectionNegativador == null || collectionNegativador.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado");
			}
			
			
			if (collectionNegativador.size()== 1 && checkAtualizar != null && !checkAtualizar.equals("")){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarNegativador");
				
				Negativador negativador = (Negativador)collectionNegativador.iterator().next();
				sessao.setAttribute("idRegistroAtualizacao", new Integer (negativador.getId()).toString());
			}else{
				sessao.setAttribute("collectionNegativador", collectionNegativador);
			}

		}
		
		return retorno;
        
    }

}
