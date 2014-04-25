package gcom.gui.batch;

import gcom.cobranca.CobrancaAcaoAtividadeComando;
import gcom.cobranca.CobrancaAcaoAtividadeCronograma;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir processo cobrança
 * 
 * @author Rodrigo Silveira
 * @created 17/08/2006
 */
public class ExibirInserirProcessoCobrancaComandadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("inserirProcessoCobrancaComandado");

		Fachada fachada = Fachada.getInstancia();

		Collection<CobrancaAcaoAtividadeCronograma> colecaoAcaoAtividadeCronograma = fachada
				.pesquisarCobrancaAcaoAtividadeCronogramaComandadosNaoRealizados();

/*		if (colecaoAcaoAtividadeCronograma == null
				|| colecaoAcaoAtividadeCronograma.isEmpty()) {
			throw new ActionServletException(
					"atencao.cobranca_acao_atividade_cronograma.comandada.inexistente");

		}
*/
		Collection<CobrancaAcaoAtividadeComando> colecaoAcaoAtividadeComando = fachada
				.pesquisarCobrancaAcaoAtividadeCronogramaEventuaisComandadosNaoRealizados();

/*		if (colecaoAcaoAtividadeComando == null
				|| colecaoAcaoAtividadeComando.isEmpty()) {
			throw new ActionServletException(
					"atencao.cobranca_acao_atividade_cronograma.eventual.inexistente");

		}
*/
		httpServletRequest
				.setAttribute("anoMesFaturamento", Util
						.formatarAnoMesParaMesAno(fachada
								.pesquisarParametrosDoSistema()
								.getAnoMesFaturamento()));

		httpServletRequest.setAttribute("colecaoAcaoAtividadeCronograma",
				colecaoAcaoAtividadeCronograma);

		httpServletRequest.setAttribute("colecaoAcaoAtividadeComando",
				colecaoAcaoAtividadeComando);

		return retorno;
	}

}
