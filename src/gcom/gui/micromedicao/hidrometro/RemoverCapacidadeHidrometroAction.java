package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC0525]	Remover Anormalidade de Leitura 
 * 
 * @author Thiago Tenorio
 * @date 13/03/2007
 */



public class RemoverCapacidadeHidrometroAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        Fachada fachada = Fachada.getInstancia();
        
        // mensagem de erro quando o usuário tenta excluir sem ter selecionado
        // nenhum registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException("atencao.registros.nao_selecionados");
        }

		fachada.remover(ids, HidrometroCapacidade.class.getName(),
				null, null);
        
        // [FS0004] Verificar Sucesso de Transação
        //Monta a página de sucesso
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
            montarPaginaSucesso(httpServletRequest,
            		ids.length + " Capacidade de Hidrômetros(s) removido(s) com sucesso.",
                    "Realizar outra Manutenção de Capacidade de Hidrômetros",
                    "exibirFiltrarCapacidadeHidrometroAction.do?menu=sim");
        }

        return retorno;

	}
}
