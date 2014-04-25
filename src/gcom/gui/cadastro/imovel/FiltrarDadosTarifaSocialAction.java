package gcom.gui.cadastro.imovel;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarDadosTarifaSocialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("filtrarDadosTarifaSocial");

		// Instância do formulário que está sendo utilizado
		//ImovelOutrosCriteriosActionForm imovelOutrosCriteriosActionForm = (ImovelOutrosCriteriosActionForm) actionForm;

		//FiltroImovel filtroImovel = new FiltroImovel();

		//String dataInicio = imovelOutrosCriteriosActionForm
		//		.getDataInicioImplantacao();
		//String dataFim = imovelOutrosCriteriosActionForm
		//		.getDataFimImplantacao();

		/*if ((dataInicio != null && dataFim != null)
				&& (Util.validarDiaMesAno(dataInicio) && Util
						.validarDiaMesAno(dataFim))) {

			Date dataInicioImplantacao = Util
					.converteStringParaDate(dataInicio);
			Date dataFimImplantacao = Util.converteStringParaDate(dataFim);

			Date atual = new Date(System.currentTimeMillis());
			if (dataLancamento.getTime() > atual.getTime())
				throw new ActionServletException(
						"erro.data.lancamento.posterior", null, atual
								.toString());
		} else {
			throw new ActionServletException("erro.data.lancamento.invalida",
					null, null);
		}*/

		return retorno;
	}
}
