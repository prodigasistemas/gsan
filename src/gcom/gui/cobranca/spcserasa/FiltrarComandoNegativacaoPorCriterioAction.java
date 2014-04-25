package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.bean.ComandoNegativacaoHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Date;

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
public class FiltrarComandoNegativacaoPorCriterioAction extends GcomAction {

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
		ActionForward retorno = actionMapping.findForward("exibirManterComandoNegativacao");
		HttpSession sessao = httpServletRequest.getSession(false);

        FiltrarComandoNegativacaoPorCriterioActionForm form = (FiltrarComandoNegativacaoPorCriterioActionForm) actionForm; 

        String tituloComando = "";
    	Short tipoPesquisaTituloComando = null;
    	Short indicadorComandoSimulado = null;
    	Date geracaoComandoInicio = null;
    	Date geracaoComandoFim = null;
    	Integer idUsuarioResponsavel = null;
    	String checkAtualizar = "";
    	Integer idNegativador = null;
    	
        if (!form.getTituloComando().equals("") && form.getTituloComando() != null){
        	tituloComando = form.getTituloComando();
        }
        if (!form.getTipoBuscaTituloComando().equals("") && form.getTipoBuscaTituloComando() != null){
        	tipoPesquisaTituloComando = Short.parseShort(form.getTipoBuscaTituloComando());
        }
        if (!form.getComandoSimulado().equals("") && form.getComandoSimulado() != null){
        	indicadorComandoSimulado = Short.parseShort(form.getComandoSimulado());
        }
      
        Date dataGeracaoComandoInicial = null;
        Date dataGeracaoComandoFinal = null;      
        if((!form.getDataGeracaoComandoInicial().equals("") && form.getDataGeracaoComandoInicial() != null) &&  (!form.getDataGeracaoComandoFinal().equals("") && form.getDataGeracaoComandoFinal() != null)){
        	dataGeracaoComandoInicial = Util.converteStringParaDate(form.getDataGeracaoComandoInicial()); 
        	dataGeracaoComandoFinal =  Util.converteStringParaDate(form.getDataGeracaoComandoFinal()); 
        	
        	String dataInicio = Util.formatarData(dataGeracaoComandoInicial);
        	String dataFim = Util.formatarData(dataGeracaoComandoFinal);
        	
			//Se data inicio maior que data fim
    		if(Util.compararData(dataGeracaoComandoInicial, dataGeracaoComandoFinal) == 1){    			 
    			throw new ActionServletException(
    					"atencao.data_inicial_maior_data_final", dataInicio,dataFim);
    		}
    		
    		geracaoComandoInicio = Util.converteStringParaDate(form.getDataGeracaoComandoInicial());
    		geracaoComandoFim = Util.converteStringParaDate(form.getDataGeracaoComandoFinal());
        } 
        
            
        
        if (!form.getUsuarioResponsavelId().equals("") && form.getUsuarioResponsavelId() != null){
        	idUsuarioResponsavel = new Integer(form.getUsuarioResponsavelId());
        }
        if (!form.getCheckAtualizar().equals("") && form.getCheckAtualizar() != null){
        	checkAtualizar = form.getCheckAtualizar();
        }
        if (!form.getIdNegativador().equals("") && form.getIdNegativador() != null){
        	idNegativador = new Integer(form.getIdNegativador());
        }

        ComandoNegativacaoHelper comandoNegativacaoHelper = new ComandoNegativacaoHelper();
        comandoNegativacaoHelper.setTituloComando(tituloComando);
        comandoNegativacaoHelper.setTipoPesquisaTituloComando(tipoPesquisaTituloComando);
        comandoNegativacaoHelper.setIndicadorComandoSimulado(indicadorComandoSimulado);
        comandoNegativacaoHelper.setGeracaoComandoInicio(geracaoComandoInicio);
        comandoNegativacaoHelper.setGeracaoComandoFim(geracaoComandoFim);
    	comandoNegativacaoHelper.setIdUsuarioResponsavel(idUsuarioResponsavel);
    	comandoNegativacaoHelper.setIdNegativador(idNegativador);
    	
    	sessao.setAttribute("checkAtualizar",checkAtualizar);
    	sessao.setAttribute("comandoNegativacaoHelper",comandoNegativacaoHelper);
    	
		return retorno;
        
    }
}
