package gcom.gui.arrecadacao.pagamento;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action responsável pelo cancelamento de inserir pagamentos
 * Este action é responsável também por remover da sessão todos os 
 * objetos utilizados no processode inserir pagamentos
 *
 * [UC0971] Inserir Pagamentos para Faturas Especiais
 * 
 * @author 	Vivianne Sousa
 * @created	21/12/2009
 */
public class CancelarInserirPagamentosFaturasEspeciaisAction extends GcomAction {
   
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno para a tela principal
        ActionForward retorno = actionMapping.findForward("telaPrincipal");

        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Limpa a sessão
        sessao.removeAttribute("colecaoFormaArrecadacao");
        sessao.removeAttribute("colecaoInserirPagamentoViaCanetaHelper");

        //Retorna o mapeamento contido na variável retorno
        return retorno;
    }
}
