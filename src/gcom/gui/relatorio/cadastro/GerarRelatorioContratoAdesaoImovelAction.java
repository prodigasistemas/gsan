package gcom.gui.relatorio.cadastro;

import gcom.api.relatorio.ReportItemDTO;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.relatorio.arrecadacao.dto.ResumoCreditosAvisosBancariosDTO;
import gcom.relatorio.cadastro.dto.ContratoAdesaoimovelDTO;
import gcom.util.IoUtil;
import gcom.util.Util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioContratoAdesaoImovelAction extends GcomAction {



	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		GerarRelatorioContratoAdesaoImovelActionForm form = (GerarRelatorioContratoAdesaoImovelActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		String data = Util.formatarDataComTracoAAAAMMDD(new Date());
		String nomeRelatorio = "contrato_adesao_"+ form.getIdImovel() + data + ".pdf";
		
		//url
		// gsan-api 
		// invoke
		return null;
	}

	private String getTextoContrato() {
		return new StringBuilder("Contrato de adesão").toString();
	}
	
}
