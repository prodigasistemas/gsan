package gcom.gui.cobranca.cobrancaporresultado;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1169] Movimentar Ordens de Serviço de Cobrança por Resultado
 * 
 * Action responsável por cancelar o processo de 
 * movimentar ordens de serviço de cobrança por resultado.
 *
 * @author Mariana Victor
 * @date 10/05/2011
 */
public class CancelarMovimentarOrdemServicoAction extends GcomAction {

    /**
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno para a tela principal
        ActionForward retorno = actionMapping.findForward("telaPrincipal");

        //Obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Limpa a sessão
    	sessao.removeAttribute("colecaoQuantidadeContas");
    	sessao.removeAttribute("colecaoFaixa");
    	sessao.removeAttribute("colecaoQtdeContas");
    	sessao.removeAttribute("colecaoQtdeClientes");
    	sessao.removeAttribute("colecaoValorTotalDivida");
    	sessao.removeAttribute("motivoInformado");
    	sessao.removeAttribute("habilitaNumerosOS");

        //Retorna o mapeamento contido na variável retorno
        return retorno;
    }

}
