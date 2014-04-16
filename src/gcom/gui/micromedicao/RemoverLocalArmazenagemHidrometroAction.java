package gcom.gui.micromedicao;




import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author Arthur Carvalho
 * @date 06/08/2008
 */

public class RemoverLocalArmazenagemHidrometroAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        Fachada fachada = Fachada.getInstancia();
        
        if (ids == null || ids.length == 0) {
            throw new ActionServletException("atencao.registros.nao_selecionados");
        }

        fachada.remover(ids, HidrometroLocalArmazenagem.class.getName(),
				null, null);
        
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest,
            		ids.length + " Local(is) de Armazenagem do Hidrômetro(s) removido(s) com sucesso.",
                    "Realizar outra Manutenção de Local de Armazenagem do Hidrômetro",
                    "exibirFiltrarLocalArmazenagemHidrometroAction.do?menu=sim");
        }

        return retorno;

	}
}
