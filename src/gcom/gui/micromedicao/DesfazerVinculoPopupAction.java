package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Atualizar Tipo de Rateio
 * 
 * @author Rafael Santos, Magno Gouveia
 * @since 12/01/2006, 20/08/2011
 */
public class DesfazerVinculoPopupAction extends GcomAction {
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

        ActionForward retorno = actionMapping.findForward("telaSucessoPopup");

        Fachada fachada = Fachada.getInstancia();
        
        HttpSession sessao = httpServletRequest.getSession(false);
        
        DesfazerVinculoPopupActionForm form = (DesfazerVinculoPopupActionForm) actionForm;
        
        String[] ids = form.getIdImovel();
        
        Imovel imovelCondominio = null;
        if(sessao.getAttribute("imovelCondominio") != null){
        	imovelCondominio = (Imovel) sessao.getAttribute("imovelCondominio") ;
        }
        
        boolean desvincular = false;
        if(ids.length == ((Collection) sessao.getAttribute("colecaoImoveisVinculados")).size()){
        	desvincular = true;
        } else {
        	/*
			 * SB0003 - FS 4. Caso a opção de desvincular não seja TODOS, o
			 * sistema verifica se o imóvel com indicador de área comum foi
			 * selecionado, [FS0015 - Pesquisar Imóvel Área Comum]
			 */
        	Integer idImovelAreaComum = fachada.pesquisarImovelAreaComum(imovelCondominio.getId());
        	if (idImovelAreaComum != null) {
        		for (String idImovelParaRemover : ids) {
        			
					if (Integer.parseInt(idImovelParaRemover.trim()) == idImovelAreaComum.intValue()) {
						throw new ActionServletException("atencao.imovel_selecionado_corresponde_area_comum", idImovelParaRemover);
					}
				}
        	}
        }
        
        /** alterado por pedro alexandre dia 20/11/2006 
         * Recupera o usuário logado para passar no metódo de desfazer vínculo
         * para verificar se o usuário tem abrangência para desfazer o vínculo do imóvel
         * informado.
         */
        Usuario usuarioLogado = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);
        fachada.desfazerVinculo(imovelCondominio, ids, desvincular, usuarioLogado);
        //fachada.desfazerVinculo(imovel,ids,desvincular);
        
        //remove da sessao
        if(sessao.getAttribute("colecaoImoveisVinculados") != null){
        	sessao.removeAttribute("colecaoImoveisVinculados");
        }
        if(sessao.getAttribute("imovelCondominio") != null){
        	sessao.removeAttribute("imovelCondominio") ;
        }
        
        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucessoPopup")) {
            montarPaginaSucesso(httpServletRequest,
            		ids.length + " imóvel(is) desvinculado(s) do imóvel condomínio " + imovelCondominio.getId()+" com sucesso." ,
                    "",
            		"");
        }        
        
       return retorno;
    }
}
 
