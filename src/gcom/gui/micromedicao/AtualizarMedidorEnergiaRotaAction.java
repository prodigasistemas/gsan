package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Hugo Leonardo
 * @date 15/03/2010
 * 
 */
public class AtualizarMedidorEnergiaRotaAction extends GcomAction {
	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
		
		// Recupera o ponto de coleta da sessão
		Collection colecao = (Collection) sessao.getAttribute("colecaoColetaMedidorEnergia");
    	
    	ColetaMedidorEnergiaHelper helper = null;
    	String numeroMedidor = null;
    	String matricula = null;
		
		if(colecao != null && !colecao.isEmpty()){
        	
        	Iterator iteratorColecaoMedidor = colecao.iterator();
        	
        	int valor = 0;
        	// Atualiza os valores do helper
    		while (iteratorColecaoMedidor.hasNext()) {
    			helper = (ColetaMedidorEnergiaHelper) iteratorColecaoMedidor.next();
    			// teste para ver se existe na página alguma categoria 
    			valor++;
    			if (requestMap.get("medidor"+ helper.getImovel()) != null) {

    				numeroMedidor = (requestMap.get("medidor" + helper.getImovel()))[0];
    				if(numeroMedidor != null && !numeroMedidor.trim().equals("")){
    					helper.setMedidorEnergia(numeroMedidor);	
    				}
    			}
        	}
    		
    		iteratorColecaoMedidor = colecao.iterator();
    		//Atualiza os novos valores na base
    		while (iteratorColecaoMedidor.hasNext()) {
    			helper = (ColetaMedidorEnergiaHelper) iteratorColecaoMedidor.next();
    			
    			matricula = helper.getImovel();
    			numeroMedidor = helper.getMedidorEnergia();
    			
    			// Atualiza os dados
    			fachada.atualizarNumeroMedidorEnergiaImovel(matricula, numeroMedidor);
    		}
    		
        } else {
			// Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		// Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			
			sessao.removeAttribute("colecaoColetaMedidorEnergia");
			
			montarPaginaSucesso(
					httpServletRequest,"Medidor de energia atualizado com sucesso.",
					"Realizar outra Atualização de Medidor de energia.",
					"exibirInformarMedidorEnergiaRotaAction.do?menu=sim");
		}

		// devolve o mapeamento de retorno
		return retorno;
	}

}
