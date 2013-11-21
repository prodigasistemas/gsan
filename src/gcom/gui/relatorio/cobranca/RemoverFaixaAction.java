package gcom.gui.relatorio.cobranca;

import java.util.Iterator;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverFaixaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
		RelatorioDocumentosAReceberForm form = (RelatorioDocumentosAReceberForm) actionForm;
		
		String descricao = httpServletRequest.getParameter("descricao");
		String valorInicial = httpServletRequest.getParameter("inicial");
		String valorFinal = httpServletRequest.getParameter("final");
		
		FaixaHelper faixaRemover = 
			new FaixaHelper(
					descricao,
					new Integer(valorInicial),
					new Integer(valorFinal));
		
		for (Iterator iterator = form.getColecaoFaixas().iterator(); iterator.hasNext();) {
			FaixaHelper faixa = (FaixaHelper) iterator.next();
			
			if(faixaRemover.equals(faixa)){
				iterator.remove();
				break;
			}			
		}
		
		if(form.getColecaoFaixas().size()>0){
			form.setIcInformouFaixa("SIM");
		}else{
			form.setIcInformouFaixa("NAO");
		}
		
		return actionMapping.findForward("exibirGerarRelatorioDocumentosAReceberAction");
	}
}
