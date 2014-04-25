package gcom.gui.micromedicao;

import gcom.cobranca.CobrancaAcao;
import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.CobrancaGrupo;
import gcom.cobranca.FiltroCobrancaAcao;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.cobranca.RotaAcaoCriterio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de Adicionar Critério de Cobrança da Rota
 * 
 * @author Vivianne Sousa
 * @created 25/04/06
 */
public class AdicionarCriterioCobrancaRotaAction extends GcomAction {
	
	/**
	 * Este caso de uso permite a inclusão de um Critério de Cobrança da Rota
	 * 
	 * [UC0038] Inserir Rota 
	 * @author Vivianne Sousa
	 * @date 25/04/2006
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


        ActionForward retorno = actionMapping.findForward("inserirCriterioCobrancaRota");
        
                
   	 	AdicionarCriterioCobrancaRotaActionForm adicionarCriterioCobrancaRotaActionForm = 
			(AdicionarCriterioCobrancaRotaActionForm) actionForm;
        Fachada fachada = Fachada.getInstancia();
        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
    
        
        String idCobrancaAcao =  adicionarCriterioCobrancaRotaActionForm.getCobrancaAcao();
        String idCobrancaCriterio = adicionarCriterioCobrancaRotaActionForm.getIdCobrancaCriterio();
        
        Collection collectionRotaAcaoCriterio = null;
       
        if (sessao.getAttribute("collectionRotaAcaoCriterio") != null) {
        	collectionRotaAcaoCriterio = (Collection) sessao
                    .getAttribute("collectionRotaAcaoCriterio");
        } else {
        	collectionRotaAcaoCriterio = new ArrayList();
        }

        validacaoFinal(collectionRotaAcaoCriterio,idCobrancaAcao,idCobrancaCriterio,fachada);
        
        //instância uma rotaAcaoCriterioNova/
        RotaAcaoCriterio rotaAcaoCriterioNova = new RotaAcaoCriterio();

        // testa se a ação de cobrança ja foi selecionada
        if (idCobrancaAcao != null && !idCobrancaAcao.equalsIgnoreCase("")) {
       	
        	FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
        	filtroCobrancaAcao.adicionarParametro(new ParametroSimples
        			(FiltroCobrancaAcao.ID,idCobrancaAcao));
        	
        	Collection<CobrancaAcao> collectionCobrancaAcao = fachada.pesquisar(
        			filtroCobrancaAcao, CobrancaAcao.class.getName());
        	
        	CobrancaAcao cobrancaAcao = (CobrancaAcao)Util.retonarObjetoDeColecao(collectionCobrancaAcao);
        	//cobrancaAcao.setId(new Integer(idCobrancaAcao));
        	rotaAcaoCriterioNova.setCobrancaAcao(cobrancaAcao);
        }
        
        // testa se  o critério de cobrança ja foi pesquisado 
        if (idCobrancaCriterio != null && !idCobrancaCriterio.equalsIgnoreCase("")) {
        	
        	FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
        	filtroCobrancaCriterio.adicionarParametro(new ParametroSimples
        			(FiltroCobrancaCriterio.ID,idCobrancaCriterio));
        	
        	Collection<CobrancaCriterio> collectionCobrancaCriterio = fachada.pesquisar(
        			filtroCobrancaCriterio, CobrancaCriterio.class.getName());
        	
        	CobrancaCriterio cobrancaCriterio = (CobrancaCriterio)Util.retonarObjetoDeColecao(collectionCobrancaCriterio);
        	rotaAcaoCriterioNova.setCobrancaCriterio(cobrancaCriterio);
        }
        
        collectionRotaAcaoCriterio.add(rotaAcaoCriterioNova);
      
        //código p verificar se já foram adicionadas todos os critérios de cobrança da rota
        //se ainda falta adicionar HABILITA o botão adicionar
        //senã DESABILITA o botão adicionar
        FiltroCobrancaAcao filtroCobrancaAcao = new FiltroCobrancaAcao();
        Collection<CobrancaGrupo> collectionCobrancaAcao = fachada.pesquisar(filtroCobrancaAcao, CobrancaAcao.class.getName());
        if (collectionRotaAcaoCriterio.size() != collectionCobrancaAcao.size()){
			httpServletRequest.setAttribute("adicionar","habilitado");
		}else{
			httpServletRequest.setAttribute("adicionar","desabilitado");
		}
        
        //manda para a sessão a coleção de RotaAcaoCriterio
        sessao.setAttribute("collectionRotaAcaoCriterio", collectionRotaAcaoCriterio);
        httpServletRequest.setAttribute("reloadPage", "OK");
		//Definindo o caso de uso que receberá o retorno
    	if (sessao.getAttribute("UseCase").equals("INSERIRROTA")){
    		httpServletRequest.setAttribute("reloadPageURL", "INSERIRROTA");
    	}
    	else if (sessao.getAttribute("UseCase").equals("ATUALIZARROTA")) {
    		httpServletRequest.setAttribute("reloadPageURL", "ATUALIZARROTA");
    		
    	} 
    	
    	sessao.removeAttribute("adicionarCriterioCobrancaRotaActionForm");
    	sessao.removeAttribute("idCobrancaAcao");        
        
        return retorno;
    }

        

    
    private void validacaoFinal (Collection collectionRotaAcaoCriterio,
							String idCobrancaAcao,String idCobrancaCriterio,
							Fachada fachada){
    	
    	// ação de cobrança é obrigatório.
		if ((idCobrancaAcao == null)
				|| (idCobrancaAcao.equals(""
						+ ConstantesSistema.NUMERO_NAO_INFORMADO))) {
			throw new ActionServletException(
					"atencao.acao_cobranca_nao_informado");
		}
		
		// critério de cobrança é obrigatório.
		if (idCobrancaCriterio == null || idCobrancaCriterio.equalsIgnoreCase("")) {
			throw new ActionServletException(
					"atencao.criterio_cobranca_nao_informado");
		}
		verificaExistenciaCodCobrancaCriterio( idCobrancaCriterio, fachada);
		
         
         // testa se a ação de cobrança ja foi selecionada
         if (idCobrancaAcao != null && !idCobrancaAcao.equalsIgnoreCase("")) {
        	 //[FS0009]verifica ação de cobrança ja existente
     		if (collectionRotaAcaoCriterio != null && !collectionRotaAcaoCriterio.isEmpty()){
    			
    			RotaAcaoCriterio rotaAcaoCriterio = null;
    			Iterator iterator = collectionRotaAcaoCriterio.iterator();

    			while (iterator.hasNext()) {
    				rotaAcaoCriterio = (RotaAcaoCriterio) iterator.next();
    				
    				if (rotaAcaoCriterio.getCobrancaAcao().getId().equals(new Integer(idCobrancaAcao))){
    					//Esta Ação de Cobrança ja foi informada
    					throw new ActionServletException(
    					"atencao.acao_cobranca.ja_informada");
    				}

    			}
    			
    		}
         }
         	
    	
    }
    	
    	

    private void verificaExistenciaCodCobrancaCriterio(String idDigitadoEnterCobrancaCriterio, 
    					Fachada fachada) {

    	//Verifica se o código da CobrancaCriterio foi digitado
		if (idDigitadoEnterCobrancaCriterio != null
				&& !idDigitadoEnterCobrancaCriterio.trim().equals("")
				&& Integer.parseInt(idDigitadoEnterCobrancaCriterio) > 0) {
		
			//Recupera a CobrancaCriterio informada pelo usuário
			FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();
			
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.ID, new Integer(
						idDigitadoEnterCobrancaCriterio)));
				
				Collection<CobrancaCriterio> cobrancaCriterios = fachada.pesquisar(
						filtroCobrancaCriterio, CobrancaCriterio.class.getName());
			
			
			if (cobrancaCriterios == null || cobrancaCriterios.isEmpty()) {
				//Critério de Cobrança inexistente.
				throw new ActionServletException(
				"atencao.pesquisa_inexistente", null, "Critério de Cobrança");
			
			}
		}

    }
    
}
 
