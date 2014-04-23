package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.bean.ComandoNegativacaoHelper;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Thiago Vieira
 */
public class FiltrarComandoNegativacaoTipoMatriculaAction extends GcomAction {

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

//    	 localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("exibirResultadoConsultaMatricula");
		HttpSession sessao = httpServletRequest.getSession(false);

        FiltrarComandoNegativacaoTipoMatriculaActionForm form = (FiltrarComandoNegativacaoTipoMatriculaActionForm) actionForm; 
        
        Integer idNegativador = null; 
        String identificacaoCI = null;
    	Short tipoPesquisaIdentificacaoCI = null;
    	Integer idUsuarioResponsavel = null;
        
    	if (!form.getIdNegativador().equals("") && form.getIdNegativador() != null){
    		idNegativador = new Integer(form.getIdNegativador());
        }
    	
    	if (!form.getIdentificacaoCI().equals("") && form.getIdentificacaoCI() != null){
    		identificacaoCI = form.getIdentificacaoCI();
        }
    	
    	if (!form.getTipoPesquisaIdentificacaoCI().equals("") && form.getTipoPesquisaIdentificacaoCI() != null){
    		tipoPesquisaIdentificacaoCI = new Short(form.getTipoPesquisaIdentificacaoCI());
        }
    	
    	if (!form.getIdUsuarioResponsavel().equals("") && form.getIdUsuarioResponsavel() != null){
    		idUsuarioResponsavel = new Integer(form.getIdUsuarioResponsavel());
        }
    	
    	ComandoNegativacaoHelper comandoNegativacaoHelper = new ComandoNegativacaoHelper();
    	comandoNegativacaoHelper.setIdNegativador(idNegativador);    	
    	comandoNegativacaoHelper.setIdentificacaoCI(identificacaoCI);
        comandoNegativacaoHelper.setTipoPesquisaIdentificacaoCI(tipoPesquisaIdentificacaoCI);
    	comandoNegativacaoHelper.setIdUsuarioResponsavel(idUsuarioResponsavel);
        
//		Collection collNegativacaoComando = Fachada.getInstancia().pesquisarComandoNegativacaoTipoMatricula(comandoNegativacaoHelper);
    	
//		if (collNegativacaoComando == null || collNegativacaoComando.isEmpty()) {
//			throw new ActionServletException(
//					"atencao.pesquisa.nenhumresultado");
//		} else {
			sessao.setAttribute("comandoNegativacaoHelper", comandoNegativacaoHelper);
//		}
		
		return retorno;
        
    }
}
