package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.bean.ComandoNegativacaoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action de exibir manter Negativador
 * @author Thiago Vieira
 * @created 28/12/2007
 */

public class ExibirManterComandoNegativacaoAction extends GcomAction {
	
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Inicializando Variaveis
        ActionForward retorno = actionMapping.findForward("manterComandoNegativacao");
		HttpSession sessao = httpServletRequest.getSession(false);
        Collection collectionComandoNegativacao = null;

        String checkAtualizar = "";
        ComandoNegativacaoHelper comandoNegativacaoHelper = new ComandoNegativacaoHelper();
        
		if (sessao.getAttribute("checkAtualizar") != null) {
			checkAtualizar = (String) sessao.getAttribute("checkAtualizar");
		}

		if (sessao.getAttribute("comandoNegativacaoHelper") != null) {
			comandoNegativacaoHelper = (ComandoNegativacaoHelper) sessao.getAttribute("comandoNegativacaoHelper");
		}
		
		if ((retorno != null)&&(retorno.getName().equalsIgnoreCase("manterComandoNegativacao"))) {

			collectionComandoNegativacao = Fachada.getInstancia().pesquisarComandoNegativacaoHelper(comandoNegativacaoHelper);
			// [FS0004] Nenhum registro encontrado				
			if (collectionComandoNegativacao == null || collectionComandoNegativacao.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.nenhumresultado");
			}
			
			if (collectionComandoNegativacao.size()== 1 && checkAtualizar != null && !checkAtualizar.equals("")){
				// caso o resultado do filtro só retorne um registro 
				// e o check box Atualizar estiver selecionado
				//o sistema não exibe a tela de manter, exibe a de atualizar 
				retorno = actionMapping.findForward("atualizarComandoNegativador");
				
				ComandoNegativacaoHelper helper = (ComandoNegativacaoHelper)collectionComandoNegativacao.iterator().next();
				httpServletRequest.setAttribute("idComandoNegativacao", new Integer (helper.getIdComandoNegativacao()).toString());
				sessao.setAttribute("atualizar","atualizar");
			}else{
				sessao.setAttribute("collectionComandoNegativacao", collectionComandoNegativacao);
			}

		}
		
		return retorno;
        
    }

}
