package gcom.gui.cadastro.atualizacaocadastral;

import gcom.atualizacaocadastral.ImovelControleAtualizacaoCadastral;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarLoteAtualizacaoCadastralAction extends GcomAction {

    private GerarLoteAtualizacaoCadastralForm form;
    
    @SuppressWarnings("unchecked")
    public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
        this.form = (GerarLoteAtualizacaoCadastralForm) actionForm;

        try {
            HttpSession sessao = request.getSession(false);
            
            List<ImovelControleAtualizacaoCadastral> imoveis = (List<ImovelControleAtualizacaoCadastral>) sessao.getAttribute("colecaoImoveisLoteAtualizacaoCadastral");
            
            Fachada.getInstancia().gerarLote(imoveis, form.getLote());
        } catch (Exception e) {
            throw new ActionServletException(e.getMessage());
        }

        montarPaginaSucesso(request, 
                "Lote gerado com sucesso.", 
                "Gerar lote atualização cadastral",
                "exibirFiltrarGerarLoteAtualizacaoCadastralAction.do?menu=sim");
        
        return mapping.findForward("telaSucesso");
    }

}
