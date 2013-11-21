package gcom.gui.micromedicao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroMovimentoRoteiroEmpresa;
import gcom.micromedicao.MovimentoRoteiroEmpresa;
import gcom.util.Util;
import gcom.util.filtro.ParametroNaoNulo;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MonitorarLeituraMobileAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("monitorarLeitura");

		HttpSession sessao = httpServletRequest.getSession(false);

		MonitorarLeituraMobileActionForm form = (MonitorarLeituraMobileActionForm) actionForm;

		String idRota = httpServletRequest.getParameter("idRota");

		if (idRota == null || idRota.equals("") || idRota.equals("undefined")) {
			idRota = (String) sessao.getAttribute("idRota");
			sessao.removeAttribute("idRota");
		}

		if (idRota != null && Util.validarStringNumerica(idRota)
				&& form.getMesAno() != null
				&& Util.validarMesAno(form.getMesAno())) {

			sessao.setAttribute("idRota", idRota);

			FiltroMovimentoRoteiroEmpresa filtro = new FiltroMovimentoRoteiroEmpresa(
					FiltroMovimentoRoteiroEmpresa.TEMPO_LEITURA);
			filtro
					.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoRoteiroEmpresa.IMOVEL);
			filtro
					.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoRoteiroEmpresa.LOCALIDADE);
			filtro
					.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoRoteiroEmpresa.ROTA);
			filtro
					.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoRoteiroEmpresa.LEITURISTA);
			filtro
					.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoRoteiroEmpresa.FUNCIONARIO);
			filtro
					.adicionarCaminhoParaCarregamentoEntidade(FiltroMovimentoRoteiroEmpresa.CLIENTE);

			filtro
					.adicionarParametro(new ParametroSimples(
							FiltroMovimentoRoteiroEmpresa.ANO_MES_MOVIMENTO,
							Util.formatarMesAnoComBarraParaAnoMes(form
									.getMesAno())));
			filtro.adicionarParametro(new ParametroSimples(
					FiltroMovimentoRoteiroEmpresa.ROTA_ID, idRota));
			filtro.adicionarParametro(new ParametroNaoNulo(
					FiltroMovimentoRoteiroEmpresa.TEMPO_LEITURA));

			// Collection colecao =
			// f.pesquisar(filtro,MovimentoRoteiroEmpresa.class.getName());

			Collection colecao;

			Map resultado = controlarPaginacao(httpServletRequest, retorno,
					filtro, MovimentoRoteiroEmpresa.class.getName());
			colecao = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			if (colecao == null || colecao.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.nenhumresultado", null,
						"Leituras Realizadas");
			} else {

				MovimentoRoteiroEmpresa m = (MovimentoRoteiroEmpresa) colecao
						.iterator().next();
				if (m.getRota().getLeiturista().getCliente() != null) {
					form.setLeiturista(m.getRota().getLeiturista().getCliente()
							.getNome());
				} else {
					form.setLeiturista(m.getRota().getLeiturista()
							.getFuncionario().getNome());
				}

				sessao.setAttribute("colecao", colecao);

			}

		}

		return retorno;
	}

}
