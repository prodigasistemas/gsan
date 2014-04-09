package gcom.gui.cobranca.spcserasa;

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
 * @author Marcio Roberto
 * @date 25/02/2008
 */
public class CancelarConsultarResumoNegativacaoAction extends GcomAction {

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

        ActionForward retorno = actionMapping.findForward("telaPrincipal");

        //obtém a instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //limpa a sessão
		sessao.removeAttribute("periodoEnvioNegativacaoInicio");
		sessao.removeAttribute("periodoEnvioNegativacaoFim");
		sessao.removeAttribute("tipoCommando");
		sessao.removeAttribute("idEloPolo");
		sessao.removeAttribute("descricaoEloPolo");
		sessao.removeAttribute("idLocalidade");
		sessao.removeAttribute("descricaoLocalidade");
		sessao.removeAttribute("idSetorComercial");
		sessao.removeAttribute("descricaoSetorComercial");
		sessao.removeAttribute("idQuadra");
		sessao.removeAttribute("descricaoQuadra");
		sessao.removeAttribute("colecaoCobrancaGrupoResultado");
		sessao.removeAttribute("colecaoGerenciaRegionalResultado");
		sessao.removeAttribute("colecaoUnidadeNegocioResultado");
		sessao.removeAttribute("colecaoImovelPerfilResultado");
		sessao.removeAttribute("colecaoCategoriaResultado");
		sessao.removeAttribute("colecaoEsferaPoderResultado");
		sessao.removeAttribute("nomeNegativador");
		
        return retorno;
    }
}
