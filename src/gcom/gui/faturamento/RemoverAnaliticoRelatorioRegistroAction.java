package gcom.gui.faturamento;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.gui.GcomAction;

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
 * Action que remove a localidade ou o setor ou a quadra
 * do filtrar realtorio analitico faturamento
 * 
 * @author Flávio Cordeiro
 * @created 23 de Maio de 2007
 */
public class RemoverAnaliticoRelatorioRegistroAction extends GcomAction {

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

        ActionForward retorno = actionMapping
                .findForward("exibirPagina");

        //obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        
        if(httpServletRequest.getParameter("habilitaBotao") != null){
        	if(httpServletRequest.getParameter("habilitaBotao").equalsIgnoreCase("S")){
        		sessao.removeAttribute("bloqueiaSetor");
    			sessao.removeAttribute("bloqueiaQuadra");
    			sessao.removeAttribute("bloqueiaLocalidade");
        	}else if(httpServletRequest.getParameter("habilitaBotao").equalsIgnoreCase("N")){
        		sessao.setAttribute("bloqueiaSetor", "s");
    			sessao.setAttribute("bloqueiaQuadra", "s");
    			sessao.setAttribute("bloqueiaLocalidade", "s");
    			sessao.setAttribute("colecaoLocalidades",new ArrayList());
    			sessao.setAttribute("colecaoSetor",new ArrayList());
    			sessao.setAttribute("colecaoQuadras",new ArrayList());
        	}
        }

        if(httpServletRequest.getParameter("idRemocaoLocalidade") != null){
        	String idLocalidade = httpServletRequest.getParameter("idRemocaoLocalidade");
        	Collection colecaoLocalidades = (Collection) sessao.getAttribute("colecaoLocalidades");
        	Iterator iterator = colecaoLocalidades.iterator();
        	while(iterator.hasNext()){
        		Localidade localidade = (Localidade) iterator.next();
        		String id = localidade.getId().toString();
        		if(idLocalidade.equalsIgnoreCase(id)){
        			iterator.remove();
        			break;
        		}
        	}
        	sessao.setAttribute("colecaoLocalidades",colecaoLocalidades);
        	if(colecaoLocalidades.size() > 1){
    			sessao.setAttribute("bloqueiaSetor", "s");
    			sessao.setAttribute("bloqueiaQuadra", "s");
    		}else if(colecaoLocalidades.size() == 1){
    			sessao.removeAttribute("bloqueiaSetor");
    			sessao.removeAttribute("bloqueiaQuadra");
    		}
        }else
        if (httpServletRequest.getParameter("idRemocaoSetor") != null){
        	String idSetor = httpServletRequest.getParameter("idRemocaoSetor");
        	Collection colecaoSetor = (Collection) sessao.getAttribute("colecaoSetor");
        	Iterator iterator = colecaoSetor.iterator();
        	while(iterator.hasNext()){
        		SetorComercial setorComercial = (SetorComercial) iterator.next();
        		String codigo = setorComercial.getCodigo() + "";
        		if(idSetor.equalsIgnoreCase(codigo)){
        			iterator.remove();
        			break;
        		}
        	}
        	sessao.setAttribute("colecaoSetor",colecaoSetor);
        	if(colecaoSetor.size() > 1){
    			sessao.setAttribute("bloqueiaQuadra", "s");
    			sessao.setAttribute("bloqueiaLocalidade", "s");
    		}else if(colecaoSetor.size() == 1){
    			sessao.removeAttribute("bloqueiaQuadra");
    			sessao.setAttribute("bloqueiaLocalidade", "s");
    		}else if(colecaoSetor.isEmpty()){
    			sessao.removeAttribute("bloqueiaLocalidade");
    		}
        }else
        if (httpServletRequest.getParameter("idRemocaoQuadra") != null){
        	String idQuadra = httpServletRequest.getParameter("idRemocaoQuadra");
        	Collection colecaoQuadras = (Collection) sessao.getAttribute("colecaoQuadras");
        	Iterator iterator = colecaoQuadras.iterator();
        	while(iterator.hasNext()){
        		Quadra quadra = (Quadra) iterator.next();
        		String id = quadra.getNumeroQuadra() + "";
        		if(idQuadra.equalsIgnoreCase(id)){
        			iterator.remove();
        			break;
        		}
        	}
        	if(colecaoQuadras.isEmpty()){
        		sessao.removeAttribute("bloqueiaSetor");
        	}else{
        		sessao.setAttribute("bloqueiaSetor", "s");
        	}
        	sessao.setAttribute("colecaoQuadras",colecaoQuadras);
        }

        return retorno;
    }

}
