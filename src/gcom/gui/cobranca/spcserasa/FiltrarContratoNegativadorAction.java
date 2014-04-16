package gcom.gui.cobranca.spcserasa;

import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorContrato;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza o filtro do Contrato Negativador de acordo com os parâmetros informados
 * 
 * @author Yara Taciane de Souza
 * @created 19/12/2007
 */
public class FiltrarContratoNegativadorAction extends GcomAction {
	/**
	 * Este caso de uso permite o filtro de um Contrato de Nagativador
	 * 
	 * [UC0661] Filtrar Contrato Negativador
	 * 
	 * 
	 * @author Yara Taciane de Souza
	 * @date 11/05/2006
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


        ActionForward retorno = actionMapping.findForward("retornarFiltroContratoNegativador");
        
        FiltrarContratoNegativadorActionForm filtrarContratoNegativadorActionForm = (FiltrarContratoNegativadorActionForm) actionForm;
       
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Fachada fachada = Fachada.getInstancia();
        
        String idNegativador = null;
        if(!"-1".equals(filtrarContratoNegativadorActionForm.getIdNegativador())){
        	idNegativador = filtrarContratoNegativadorActionForm.getIdNegativador(); 
        }
        String numeroContrato = null;
        if(!"".equals(filtrarContratoNegativadorActionForm.getNumeroContrato())){
        	numeroContrato = filtrarContratoNegativadorActionForm.getNumeroContrato();
        }
                      
	

		// 1 check   --- null uncheck 
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");

		FiltroNegativadorContrato filtroNegativadorContrato = new FiltroNegativadorContrato();
		
		if (idNegativador != null 
				&& !idNegativador.equalsIgnoreCase("")){

			filtroNegativadorContrato.adicionarParametro(new ParametroSimples(FiltroNegativadorContrato.NEGATIVADOR_ID, idNegativador));

		}

		if (numeroContrato != null && !numeroContrato.equalsIgnoreCase("")){

//			filtroNegativadorContrato.adicionarCaminhoParaCarregamentoEntidade("numeroContrato");
			filtroNegativadorContrato.adicionarParametro(new ParametroSimples(FiltroNegativadorContrato.NUMERO_CONTRATO, numeroContrato));

		}
		
		
		

//		//[FS0003] Verificar preenchimento dos campos
//		if (!peloMenosUmParametroInformado){
//			throw new ActionServletException(
//					"atencao.filtro.nenhum_parametro_informado");
//		}

		sessao.setAttribute("filtroNegativadorContrato",filtroNegativadorContrato);
		sessao.setAttribute("indicadorAtualizar",indicadorAtualizar );
	
       return retorno;
    }
   
    

}
 
