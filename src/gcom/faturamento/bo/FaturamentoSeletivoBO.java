package gcom.faturamento.bo;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.SessionContext;

import org.apache.struts.action.ActionForm;
import org.jboss.logging.Logger;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.faturamento.FaturamentoSeletivoAction;
import gcom.gui.faturamento.FaturamentoSeletivoActionForm;
import gcom.gui.faturamento.ImovelFaturamentoSeletivo;

public class FaturamentoSeletivoBO {

	FaturamentoSeletivoActionForm form;
	SessionContext sessionContext;
	
	private Logger logger = Logger.getLogger(FaturamentoSeletivoAction.class);
	
	public FaturamentoSeletivoBO(ActionForm actionForm) {
		this.form = (FaturamentoSeletivoActionForm) actionForm;
	}
	
	public void faturar() {
		
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
	
	public void setSessionContext(SessionContext sessionContext) {
		this.sessionContext = sessionContext;
	}
}
