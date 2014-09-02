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

public class FaturamentoSeletivoAction extends GcomAction {

	private Logger logger = Logger.getLogger(FaturamentoSeletivoAction.class);
	
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");

        FaturamentoSeletivoActionForm form = (FaturamentoSeletivoActionForm) actionForm;
        
        FaturamentoSeletivoBO faturamentoSeletivo = new FaturamentoSeletivoBO(form);
        faturamentoSeletivo.faturar();

        //this.faturar(form);

 		montarPaginaSucesso(httpServletRequest, "Imóveis selecionados faturados com sucesso.", "Faturar outro imóvel", "filtrarFaturamentoSeletivo.do?menu=sim");
     		
        return retorno;
    }

	private Collection<ImovelFaturamentoSeletivo> obterImoveisParaFaturar(FaturamentoSeletivoActionForm form) {
		
		Collection<ImovelFaturamentoSeletivo> imoveisParaFaturar = new ArrayList<ImovelFaturamentoSeletivo>();
		
		if (form.getColecaoImoveisFaturamentoSeletivo().size() == form.getIdImoveisSelecionados().length) {
			imoveisParaFaturar = form.getColecaoImoveisFaturamentoSeletivo();
		} else {
			
			for (ImovelFaturamentoSeletivo imovelFaturamentoSeletivo : form.getColecaoImoveisFaturamentoSeletivo()) {
				for (String imovelSelecionado : form.getIdImoveisSelecionados()) {
					
					if(imovelFaturamentoSeletivo.getDadoMovimentacao().getMatriculaImovel().toString().equals(imovelSelecionado)) {
						imoveisParaFaturar.add(imovelFaturamentoSeletivo);
						
						logger.info("Imóvel: " + imovelFaturamentoSeletivo.getIdImovel() + " [" + imovelFaturamentoSeletivo.getLeitura() + "]"+ " [" + imovelFaturamentoSeletivo.getAnormalidade() + "]"+ " [" + imovelFaturamentoSeletivo.getDataLeitura() + "]");
					}
				}
			}
		}
		return imoveisParaFaturar;
	}
	
	private void faturar(FaturamentoSeletivoActionForm form) {
		
		Collection<ImovelFaturamentoSeletivo> colecaoImoveis = obterImoveisParaFaturar(form);

		Fachada fachada = Fachada.getInstancia();
		
		for (ImovelFaturamentoSeletivo imovel : colecaoImoveis) {
			try {

				fachada.incluirMedicaoHistoricoFaturamentoSeletivo(imovel);
				fachada.faturarImovelSeletivo(imovel);
			
			} catch (Exception e) {
				throw new ActionServletException("Erro ao faturar seletivamente imóvel " + imovel.getIdImovel());
			}
		}
	}
		
}
