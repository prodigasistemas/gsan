package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.FiltroNegativacaoComando;
import gcom.cobranca.NegativacaoComando;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action que define o pré-processamento da página de filtrar Negativador Exclusão Motivo
 * 
 * @author Yara Taciane de Souza
 * @created 03/01/2008
 */
public class ExibirFiltrarNegativadorResultadoSimulacaoAction extends GcomAction {
	/**
	 * Este caso de uso permite o filtro de um Negativador Exclusao Motivo 
	 * 
	 * [UC0670] Filtrar Motivo da Exlusao do Negativador
	 * 
	 * 
	 * @author Yara Taciane de Souza
	 * @date 15/05/2008
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


        ActionForward retorno = actionMapping.findForward("filtrarNegativadorResultadoSimulacao");
        
        FiltrarNegativadorResultadoSimulacaoActionForm form = (FiltrarNegativadorResultadoSimulacaoActionForm) actionForm;
        HttpSession sessao = httpServletRequest.getSession(false);

    	Fachada fachada = Fachada.getInstancia();        
         
        if (httpServletRequest.getParameter("desfazer") != null
                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {
        	
        	//-------------- bt DESFAZER ---------------        	
        	form.setIdComando("" + ConstantesSistema.NUMERO_NAO_INFORMADO);   
      
            sessao.removeAttribute("caminhoRetornoTelaPesquisa");
        }        
     
        String idDigitado = (String) form.getIdComando();
        if(!"".equals(idDigitado) && idDigitado!= null){
        	
        	 //Exibe na Tela o nome do Cliente Negativador
        	FiltroNegativacaoComando filtro = new FiltroNegativacaoComando();
    		filtro.adicionarParametro(new ParametroSimples(FiltroNegativacaoComando.ID,idDigitado));    		
            Collection coll = fachada.pesquisar(filtro, NegativacaoComando.class.getName());
            
           NegativacaoComando negativacaoComando = null;
            Iterator it = coll.iterator();
            while(it.hasNext()){
            	negativacaoComando = (NegativacaoComando)it.next();        	 
            }
            
            if(negativacaoComando != null){
            	if (negativacaoComando.getIndicadorSimulacao() == 2){
            		throw new ActionServletException(
    				"atencao.comando_nao_corresponde_simulacao");
            	}
            	
            	if (negativacaoComando.getDataHoraRealizacao() == null){
            		throw new ActionServletException(
    				"atencao.simulacao_nao_realizada", "exibirFiltrarNegativadorResultadoSimulacaoAction.do?menu=sim", new Exception());
            	}
            	
            }
          	
        }
        
         
         
        sessao.removeAttribute("caminhoRetornoTelaPesquisa");
	
		
        return retorno;
    }
    
   

}
 
