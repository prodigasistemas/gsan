package gcom.gui.seguranca.acesso;

import gcom.gui.GcomAction;
import gcom.seguranca.acesso.FiltroGrupo;
import gcom.seguranca.acesso.Grupo;

import java.util.Collection;
import java.util.Map;

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
 * @date 26/06/2006
 */
public class ExibirManterGrupoAction extends GcomAction {
    
    /**
     * <Breve descrição sobre o caso de uso>
     *
     * [UC0279] - Manter Grupo
     *
     * @author Pedro Alexandre
     * @date 26/06/2006
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

        ActionForward retorno = actionMapping.findForward("exibirManterGrupo");

        //Fachada fachada = Fachada.getInstancia();

        Collection colecaoGrupo = null;
        
        HttpSession sessao = httpServletRequest.getSession(false);

        FiltroGrupo filtroGrupo = new FiltroGrupo();
        filtroGrupo.setCampoOrderBy(FiltroGrupo.DESCRICAO);

		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroGrupo, Grupo.class.getName());
		colecaoGrupo = (Collection) resultado.get("colecaoRetorno");
		retorno = (ActionForward) resultado.get("destinoActionForward");
		
		
		
		
        //Collection coll = fachada.pesquisar(new FiltroGrupo(), Grupo.class.getSimpleName());

        sessao.setAttribute("grupos",colecaoGrupo);

        return retorno;
    }
}
