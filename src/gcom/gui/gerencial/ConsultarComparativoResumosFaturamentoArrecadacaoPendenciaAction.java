package gcom.gui.gerencial;

import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe 
 *
 * @author Pedro Alexandre
 * @date 07/06/2006
 */
public class ConsultarComparativoResumosFaturamentoArrecadacaoPendenciaAction extends GcomAction {

    /**
     * <Breve descrição sobre o caso de uso>
     *
     * <Identificador e nome do caso de uso>
     *
     * @author Pedro Alexandre
     * @date 07/06/2006
     *
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

        //Seta o retorno
        ActionForward retorno = null;

        //Obtém a sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = 
        	(InformarDadosGeracaoRelatorioConsultaHelper)sessao.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");

        
        if(informarDadosGeracaoRelatorioConsultaHelper.isGerarRelatorio()){
        	//relatorio
        	retorno = actionMapping.findForward("consultarComparativoResumoFaturamentoArrecadacaoPendenciaRelatorio");
        }else{
        	//jsp
        	retorno = actionMapping.findForward("consultarComparativoResumoFaturamentoArrecadacaoPendenciaJsp");
        }

        sessao.setAttribute("informarDadosGeracaoRelatorioConsultaHelper", informarDadosGeracaoRelatorioConsultaHelper);

        return retorno;
    }

}
