package gcom.gui.relatorio.cadastro;

import gcom.api.GsanApi;
import gcom.gui.GcomAction;
import gcom.relatorio.cadastro.dto.ContratoAdesaoimovelDTO;
import gcom.seguranca.SegurancaParametro;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioContratoAdesaoImovelAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {

		HttpSession sessao = request.getSession(false);
		Integer idImovel = new Integer((String) sessao.getAttribute("idImovelPrincipalAba"));

		String url = getFachada().getSegurancaParametro(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_CONTRATO_ADESAO.toString());

		try {
			ContratoAdesaoimovelDTO dto = getFachada().obterContratoAdesao(idImovel);
			
			GsanApi api = new GsanApi(url);
			api.invoke(dto);
			api.download(dto.getNomeRelatorio(), response);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
