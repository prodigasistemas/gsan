package gcom.gui.cadastro.cliente;

import java.util.Collection;

import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.cliente.FiltroEsferaPoder;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Action que define o pré-processamento da página de pesquisa de responsavel
 * superior
 * 
 * @author Sávio Luiz
 *  
 */
public class ExibirPesquisarResponsavelSuperiorAction extends GcomAction {
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
    	
    	// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		DynaValidatorForm pesquisarActionForm = (DynaValidatorForm) actionForm;
		
        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("pesquisarResponsavelSuperior");
        
        Fachada fachada = Fachada.getInstancia();
        
        sessao.removeAttribute("nome");
		sessao.removeAttribute("cnpj");
		String tipoPesquisa = (String) pesquisarActionForm.get("tipoPesquisa");
		if (tipoPesquisa == null || tipoPesquisa.trim().equals("")) {
			pesquisarActionForm.set("tipoPesquisa",
				ConstantesSistema.TIPO_PESQUISA_INICIAL.toString());
		}
		
		if (httpServletRequest.getParameter("pesquisaSuperior") != null && !httpServletRequest.getParameter("pesquisaSuperior").trim().equals("")) {
			sessao.setAttribute("pesquisaSuperior", true);
		}
		
		FiltroEsferaPoder filtroEsferaPoder = new FiltroEsferaPoder();
		
		Collection colecaoEsferaPoder =  fachada.pesquisar(filtroEsferaPoder, EsferaPoder.class.getName());
		httpServletRequest.setAttribute("colecaoEsferaPoder", colecaoEsferaPoder);
		
		pesquisarActionForm.set("nome",
			"aaaa");

        return retorno;
    }
}
