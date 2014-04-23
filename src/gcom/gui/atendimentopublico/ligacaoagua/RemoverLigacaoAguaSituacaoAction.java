package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @author Vinícius Medeiros
 * @date 29/04/2008
 */

public class RemoverLigacaoAguaSituacaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

        String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

        // Seta o caminho de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        // Obtém a instância da fachada
        Fachada fachada = Fachada.getInstancia();
       
        // Mensagem de erro quando o usuário tenta excluir sem ter selecionado
        // nenhum registro
        if (ids == null || ids.length == 0) {
            throw new ActionServletException("atencao.registros.nao_selecionados");
        }

        fachada.remover(ids, LigacaoAguaSituacao.class.getName(),null, null);
        
        if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
        	
            montarPaginaSucesso(httpServletRequest,
            		ids.length + " Situação(ões) de Ligação(ões) de Água removida(s) com sucesso.",
                    "Realizar outra Manutenção de Situação de Ligação de Água",
                    "exibirFiltrarLigacaoAguaSituacaoAction.do?menu=sim");
        
        }

        return retorno;

	}
}
