package gcom.gui.faturamento;

import gcom.faturamento.FaturamentoAtivCronRota;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarComandoAtividadeFaturamentoDataVencimentoAction extends
		GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("");

		// Carrega o objeto sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		// Instância do formulário que está sendo utilizado
		//InserirComandoAtividadeFaturamentoActionForm inserirComandoAtividadeFaturamentoActionForm = (InserirComandoAtividadeFaturamentoActionForm) actionForm;

		// Faturamento Atividade Cronograma selecionado
		//FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) sessao
		//		.getAttribute("faturamentoAtividadeCronograma");

		// FaturamentoAtividade = FATURAR GRUPO
		if (sessao.getAttribute("exibirCampoVencimentoGrupo") != null) {

			// Para auxiliar na formatação de uma data
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

			// Data vencimento do grupo
			// Data corrente para comparação
			// ==========================================
			String dataCorrenteString = null;
			Date dataCorrenteDate = null;

			dataCorrenteString = (String) sessao.getAttribute("dataCorrente");

			try {
				dataCorrenteDate = formatoData.parse(dataCorrenteString);
			} catch (ParseException ex) {
				dataCorrenteDate = null;
			}

			// Coleção final
			Collection colecaoFaturamentoAtividadeCronogramaRotaUniao = (Collection) sessao
					.getAttribute("colecaoFaturamentoAtividadeCronogramaRotaUniao");

			Iterator colecaoFaturamentoAtividadeCronogramaRotaUniaoIterator = colecaoFaturamentoAtividadeCronogramaRotaUniao
					.iterator();

			FaturamentoAtivCronRota faturamentoAtivCronRota = null;
			String descricaoParametro = null;

			Date dataVencimentoRota = null;
			String dataVencimentoRotaJSP = null;

			String mesDataVencimentoGrupo = null;
			String anoDataVencimentoGrupo = null;

			String mesDataVencimentoRota = null;
			String anoDataVencimentoRota = null;

			while (colecaoFaturamentoAtividadeCronogramaRotaUniaoIterator
					.hasNext()) {
				faturamentoAtivCronRota = (FaturamentoAtivCronRota) colecaoFaturamentoAtividadeCronogramaRotaUniaoIterator
						.next();

				// Formatação do nome do parâmetro
				descricaoParametro = "d"
						+ faturamentoAtivCronRota.getRota().getId().toString();

				// Data informada pelo usuário
				dataVencimentoRotaJSP = (String) httpServletRequest
						.getParameter(descricaoParametro);

				if (dataVencimentoRotaJSP != null
						&& !dataVencimentoRotaJSP.equalsIgnoreCase("")) {

					try {
						dataVencimentoRota = formatoData
								.parse(dataVencimentoRotaJSP);

						mesDataVencimentoGrupo = dataCorrenteString.substring(
								3, 5);
						anoDataVencimentoGrupo = dataCorrenteString.substring(
								6, 10);

						mesDataVencimentoRota = dataVencimentoRotaJSP
								.substring(3, 5);
						anoDataVencimentoRota = dataVencimentoRotaJSP
								.substring(6, 10);

						if (dataCorrenteDate.after(dataVencimentoRota)) {
							throw new ActionServletException(
									"atencao.faturamento_data_rota_grupo_menor",
									null, dataCorrenteString);
						} else if ((!mesDataVencimentoGrupo
								.equalsIgnoreCase(mesDataVencimentoRota))
								|| (!anoDataVencimentoGrupo
										.equalsIgnoreCase(anoDataVencimentoRota))) {
							throw new ActionServletException(
									"atencao.faturamento_data_rota_mes_ano_diferente");
						}
					} catch (ParseException ex) {
						dataVencimentoRota = null;
					}

					faturamentoAtivCronRota
							.setDataContaVencimento(dataVencimentoRota);
				}
			}
		}

		return retorno;
	}
}
