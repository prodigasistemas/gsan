package gcom.gui.relatorio.cadastro;

import gcom.api.GsanApi;
import gcom.gui.GcomAction;
import gcom.relatorio.cadastro.dto.ContratoAdesaoimovelDTO;
import gcom.seguranca.SegurancaParametro;
import gcom.util.Util;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioContratoAdesaoImovelAction extends GcomAction {



	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		GerarRelatorioContratoAdesaoImovelActionForm form = (GerarRelatorioContratoAdesaoImovelActionForm) actionForm;

		String data = Util.formatarDataComTracoAAAAMMDD(new Date());
		String nomeRelatorio = "contrato_adesao_"+ form.getIdImovel() + data + ".pdf";
		
		String url = getFachada().getSegurancaParametro(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_CONTRATO_ADESAO.toString());

		try {
		
			GsanApi api = new GsanApi(url);
			api.invoke(getTextoContrato());
			api.download(url, nomeRelatorio, response);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private ContratoAdesaoimovelDTO getTextoContrato() {
		return new ContratoAdesaoimovelDTO("Contrato de adesão");
	}
	
}
