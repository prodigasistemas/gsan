package gcom.gui.micromedicao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action form de manter rota
 * 
 * @author Vivianne Sousa
 * @created03/04/2006
 */
public class RemoverRotaAction extends GcomAction {
	/**
	 * @author Vivianne Sousa
	 * @date 03/04/2006
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

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //mensagem de erro quando o usuário tenta excluir sem ter selecionado
        // nenhum registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException("atencao.registros.nao_selecionados");
        }
		
        this.getFachada().removerRota(ids,this.getUsuarioLogado(httpServletRequest));

        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            
        	montarPaginaSucesso(httpServletRequest,
        		ids.length + " Rota(s) removida(s) com sucesso.",
        		"Realizar outra Manutenção de Rota",
                "exibirFiltrarRotaAction.do?desfazer=S");
        }
        
       return retorno;
    }
    


}
 
