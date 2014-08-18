package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jboss.logging.Logger;

public class FaturamentoSeletivoAction extends GcomAction {

	private Logger logger = Logger.getLogger(FaturamentoSeletivoAction.class);
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        Fachada fachada = Fachada.getInstancia();

        //Usuario usuarioLogado = this.getUsuarioLogado(httpServletRequest);

        FaturamentoSeletivoActionForm form = (FaturamentoSeletivoActionForm) actionForm;
       // obterImoveisParaFaturar(form);
        fachada.faturarImoveisSeletivo(obterImoveisParaFaturar(form));

 		montarPaginaSucesso(httpServletRequest, "Imóveis selecionados faturados com sucesso.", "Faturar outro imóvel", "filtrarFaturamentoSeletivo.do?menu=sim");
     		
        return retorno;
    }

	private Collection<ImovelFaturamentoSeletivo> obterImoveisParaFaturar(FaturamentoSeletivoActionForm form) {
		
		Collection<ImovelFaturamentoSeletivo> imoveisParaFaturar = new ArrayList<ImovelFaturamentoSeletivo>();
		
//		if (form.getColecaoImoveisFaturamentoSeletivo().size() == form.getIdImoveisSelecionados().length) {
//			imoveisParaFaturar = form.getColecaoImoveisFaturamentoSeletivo();
//		} else {
			
			for (ImovelFaturamentoSeletivo imovelFaturamentoSeletivo : form.getColecaoImoveisFaturamentoSeletivo()) {
				for (String imovelSelecionado : form.getIdImoveisSelecionados()) {
					
					if(imovelFaturamentoSeletivo.getDadoMovimentacao().getMatriculaImovel().toString().equals(imovelSelecionado)) {
						imoveisParaFaturar.add(imovelFaturamentoSeletivo);
						
						logger.info("Imóvel: " + imovelFaturamentoSeletivo.getIdImovel() + " [" + imovelFaturamentoSeletivo.getLeitura() + "]"+ " [" + imovelFaturamentoSeletivo.getAnormalidade() + "]"+ " [" + imovelFaturamentoSeletivo.getDataLeitura() + "]");
					}
				}
			}
//		}
		return imoveisParaFaturar;
	}
		
}
