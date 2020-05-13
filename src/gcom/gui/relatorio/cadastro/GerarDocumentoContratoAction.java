package gcom.gui.relatorio.cadastro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.api.GsanApi;
import gcom.cadastro.imovel.ContratoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.relatorio.cadastro.dto.ContratoDTO;
import gcom.seguranca.SegurancaParametro;

public class GerarDocumentoContratoAction extends GcomAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		int idContrato = new Integer((String) request.getParameter("idContrato"));
		int tipo = new Integer((String) request.getParameter("tipoContrato"));

		try {
			ContratoDTO dto = null;
			if (tipo == ContratoTipo.ADESAO) {
				dto = getFachada().obterContratoAdesao(idContrato);
			} else if (tipo == ContratoTipo.INSTALACAO_RESERVACAO) {
				dto = getFachada().obterContratoInstalacaoReservacao(idContrato);
			}

			if (dto != null) {
				String url = getFachada().getSegurancaParametro(SegurancaParametro.NOME_PARAMETRO_SEGURANCA.URL_CONTRATO.toString());
				GsanApi api = new GsanApi(url);
				api.invoke(dto);
				api.download(dto.getNomeArquivo(), response);
			} else {
				throw new ActionServletException("atencao.gerar_contrato_sem_dados");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ActionServletException("erro.gerar_contrato");
		}

		return null;
	}
}