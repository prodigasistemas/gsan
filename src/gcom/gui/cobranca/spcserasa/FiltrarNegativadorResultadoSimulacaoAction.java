package gcom.gui.cobranca.spcserasa;

import java.util.Collection;
import java.util.Iterator;

import gcom.cobranca.NegativadorResultadoSimulacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorResultadoSimulacao;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Realiza o filtro do Negativador Exclusao Motivo de acordo com os parâmetros informados
 * 
 * @author Yara Taciane de Souza
 * @created 03/01/2008
 */
public class FiltrarNegativadorResultadoSimulacaoAction extends GcomAction {
	/**
	 * Este caso de uso permite o filtro de um Negativador Exclusao Motivo
	 * 
	 * [UC0670] Filtrar Motivo Exclusao Negativador
	 * 
	 * 
	 * @author Yara Taciane de Souza
	 * @date 03/01/2007
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


        ActionForward retorno = actionMapping.findForward("gerarRelatorioNegativadorResultadoSimulacao");
        
        FiltrarNegativadorResultadoSimulacaoActionForm form = (FiltrarNegativadorResultadoSimulacaoActionForm) actionForm;
       
        HttpSession sessao = httpServletRequest.getSession(false);
        
    	Fachada fachada = Fachada.getInstancia();        
      
        Boolean peloMenosUmParametroInformado = false;
        
   //-------------------------------------------------------------------------
        String id = null;
		if (form.getIdComando() != null
				&& !form.getIdComando().equals("-1")) {			
			id=form.getIdComando();
		} else {
			throw new ActionServletException("atencao.required", null,
					"Comando");
		}
     
     

		//[FS0003] Verificar preenchimento dos campos
		if (!peloMenosUmParametroInformado){
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		
        //Exibe na Tela o nome do Cliente Negativador
		FiltroNegativadorResultadoSimulacao filtro = new FiltroNegativadorResultadoSimulacao();
		filtro.adicionarParametro(new ParametroSimples(FiltroNegativadorResultadoSimulacao.ID,id));        
		filtro.adicionarCaminhoParaCarregamentoEntidade("negativacaoComando");
        Collection coll = fachada.pesquisar(filtro, NegativadorResultadoSimulacao.class.getName());
        
        NegativadorResultadoSimulacao negativadorResultadoSimulacao = null;
        Iterator it = coll.iterator();
        while(it.hasNext()){
        	  negativadorResultadoSimulacao = (NegativadorResultadoSimulacao)it.next();        	 
        }
        
        if(negativadorResultadoSimulacao != null && negativadorResultadoSimulacao.getNegativacaoComando() != null){
        	if (negativadorResultadoSimulacao.getNegativacaoComando().getIndicadorSimulacao() == 2){
        		throw new ActionServletException(
				"atencao.comando_nao_corresponde_simulacao");
        	}
        	
        	if (negativadorResultadoSimulacao.getNegativacaoComando().getDataHoraRealizacao() == null){
        		throw new ActionServletException(
        				"atencao.simulacao_nao_realizada", "exibirFiltrarNegativadorResultadoSimulacaoAction.do?menu=sim", new Exception());
        	}
        	
        }
        
        
    	
		sessao.setAttribute("filtroNegativadorResultadoSimulacao",filtro);
	
	
       return retorno;
    }
   
    

}
 
