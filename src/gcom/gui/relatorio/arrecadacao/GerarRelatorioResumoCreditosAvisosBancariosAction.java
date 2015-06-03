package gcom.gui.relatorio.arrecadacao;

import gcom.gui.GcomAction;
import gcom.gui.arrecadacao.FiltrarDadosDiariosArrecadacaoActionForm;
import gcom.relatorio.arrecadacao.resumocreditosavisosbancarios.ResumoCreditosAvisosBancariosBO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.util.Util;

public class GerarRelatorioResumoCreditosAvisosBancariosAction extends GcomAction {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward retorno = mapping.findForward("exibirConsultarDadosDiariosArrecadador");
		
		FiltrarDadosDiariosArrecadacaoActionForm form = (FiltrarDadosDiariosArrecadacaoActionForm) actionForm;
		
		ResumoCreditosAvisosBancariosBO bo = new ResumoCreditosAvisosBancariosBO();
		bo.gerarRelatorioPDF(Integer.valueOf(Util.formatarMesAnoComBarraParaAnoMes(form.getPeriodoArrecadacaoInicio())));
		
		return retorno;
	}
}
