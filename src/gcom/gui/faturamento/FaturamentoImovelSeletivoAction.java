package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.bo.FaturamentoSeletivoBO;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jboss.logging.Logger;

public class FaturamentoImovelSeletivoAction extends GcomAction {

	private Logger logger = Logger.getLogger(FaturamentoImovelSeletivoAction.class);
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        Fachada fachada = Fachada.getInstancia();

        FaturamentoImovelSeletivoActionForm form = (FaturamentoImovelSeletivoActionForm) actionForm;
        ImovelFaturamentoSeletivo imovelSeletivo = new ImovelFaturamentoSeletivo();
        imovelSeletivo.setIdImovel(Integer.parseInt(form.getMatriculaImovel()));
        
        fachada.faturarImovelSeletivo(imovelSeletivo);

 		montarPaginaSucesso(httpServletRequest, "Imóvel selecionado faturado com sucesso.", "Faturar outro imóvel", "filtrarFaturamentoImovelSeletivo.do?menu=sim");
     		
        return retorno;
    }
		
}
