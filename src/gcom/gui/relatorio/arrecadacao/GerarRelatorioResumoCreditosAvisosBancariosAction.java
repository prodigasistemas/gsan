package gcom.gui.relatorio.arrecadacao;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.relatorio.arrecadacao.resumocreditosavisosbancarios.ResumoCreditosAvisosBancariosBO;
import gcom.seguranca.SegurancaParametro;
import gcom.util.Util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioResumoCreditosAvisosBancariosAction extends GcomAction {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		
		GerarRelatorioResumoCreditosAvisosBancariosActionForm form = (GerarRelatorioResumoCreditosAvisosBancariosActionForm) actionForm;
		
		ResumoCreditosAvisosBancariosBO bo = new ResumoCreditosAvisosBancariosBO();
		bo.gerarRelatorioPDF(Util.converteStringParaDate(form.getDataConsulta()));
		
		String url = Fachada.getInstancia().getSegurancaParametro(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_GSAN_RELATORIOS.toString());
		
		try {
			response.sendRedirect(url + "novo_relatorio.pdf");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main (String [] args) {
		 String data = "01/06/2015";
		 
		 Util.converteStringParaDate(data);
	}
}
