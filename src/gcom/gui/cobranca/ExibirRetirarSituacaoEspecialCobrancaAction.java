package gcom.gui.cobranca;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir bairro
 * 
 * @author Sávio Luiz
 * @created 28 de Junho de 2004
 */
public class ExibirRetirarSituacaoEspecialCobrancaAction extends GcomAction {
    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param actionForm
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     * @param httpServletResponse
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("inserirBairro");
        
        SituacaoEspecialCobrancaActionForm situacaoEspecialCobrancaActionForm = (SituacaoEspecialCobrancaActionForm) actionForm;
        
        String qtdImoveisComSituacao = "";
        
        String qtdImoveisSemSituacao = "";
        
        if(qtdImoveisComSituacao == null || qtdImoveisComSituacao.trim().equalsIgnoreCase("")){
        	qtdImoveisComSituacao = "0";
        }
        
        if(qtdImoveisSemSituacao == null || qtdImoveisSemSituacao.trim().equalsIgnoreCase("")){
        	qtdImoveisSemSituacao = "0";
        }
        
        int qtdImoveisComSituacaoShort = Integer.parseInt(qtdImoveisComSituacao);
        int qtdImoveisSemSituacaoShort = Integer.parseInt(qtdImoveisSemSituacao);
        
        int qtdTotalImoveisAtualizacao =  qtdImoveisComSituacaoShort + qtdImoveisSemSituacaoShort;
        
        situacaoEspecialCobrancaActionForm.setQuantidadeImoveisAtualizados("" + qtdTotalImoveisAtualizacao);

        return retorno;
    }
}
