package gcom.gui.cobranca;

import gcom.cobranca.CobrancaCriterio;
import gcom.cobranca.FiltroCobrancaCriterio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Tiago Moreno
 * @create 16/02/2006
 * 
 */
public class PesquisarCriterioCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("pesquisarCriterioCobrancaResultado");

		// Obtém a instância da fachada
		//Fachada fachada = Fachada.getInstancia();

		// cria sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarCriterioCobrancaActionForm pesquisarCriterioCobrancaActionForm = (PesquisarCriterioCobrancaActionForm) actionForm;

		String descricaoCriterio = pesquisarCriterioCobrancaActionForm
				.getDescricaoCriterio();
		String dataInicio = pesquisarCriterioCobrancaActionForm.getDataInicio();
		String dataFim = pesquisarCriterioCobrancaActionForm.getDataFim();
		String numeroAnos = pesquisarCriterioCobrancaActionForm.getNumeroAnos();
		String opcaoContaRevisao = pesquisarCriterioCobrancaActionForm
				.getOpcaoContaRevisao();
		String opcaoImovelDebito = pesquisarCriterioCobrancaActionForm
				.getOpcaoImovelDebito();
		String opcaoImovelSitCobranca = pesquisarCriterioCobrancaActionForm
				.getOpcaoImovelSitCobranca();
		String opcaoImovelSitEspecial = pesquisarCriterioCobrancaActionForm
				.getOpcaoImovelSitEspecial();
		String opcaoInqDebitoConta = pesquisarCriterioCobrancaActionForm
				.getOpcaoInqDebitoConta();
		String opcaoInqDebitoContaAntiga = pesquisarCriterioCobrancaActionForm
				.getOpcaoInqDebitoContaAntiga();

		FiltroCobrancaCriterio filtroCobrancaCriterio = new FiltroCobrancaCriterio();

		if ((descricaoCriterio == null || descricaoCriterio
				.equalsIgnoreCase(""))
				&& (dataInicio == null || dataInicio.equalsIgnoreCase(""))
				&& (numeroAnos == null || numeroAnos.equalsIgnoreCase(""))
				&& (opcaoContaRevisao == null || opcaoContaRevisao
						.equalsIgnoreCase(""))
				&& (opcaoImovelSitCobranca == null || opcaoImovelSitCobranca
						.equalsIgnoreCase(""))
				&& (opcaoInqDebitoContaAntiga == null || opcaoInqDebitoContaAntiga
						.equalsIgnoreCase(""))
				&& (opcaoInqDebitoConta == null || opcaoInqDebitoConta
						.equalsIgnoreCase(""))
				&& (opcaoImovelDebito == null || opcaoImovelDebito
						.equalsIgnoreCase(""))
				&& (opcaoImovelSitEspecial == null || opcaoImovelSitEspecial
						.equalsIgnoreCase(""))) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		if ((dataInicio.trim().length() == 10)
				&& (dataFim.trim().length() == 10) && dataFim != null && !dataFim.equalsIgnoreCase("")) {

			Calendar calendarInicio = new GregorianCalendar();
			Calendar calendarFim = new GregorianCalendar();

			calendarInicio.set(Calendar.DAY_OF_MONTH, new Integer(dataInicio
					.substring(0, 2)).intValue());
			calendarInicio.set(Calendar.MONTH, new Integer(dataInicio
					.substring(3, 5)).intValue());
			calendarInicio.set(Calendar.YEAR, new Integer(dataInicio.substring(
					6, 10)).intValue());

			calendarFim.set(Calendar.DAY_OF_MONTH, new Integer(dataFim
					.substring(0, 2)).intValue());
			calendarFim.set(Calendar.MONTH,
					new Integer(dataFim.substring(3, 5)).intValue());
			calendarFim.set(Calendar.YEAR,
					new Integer(dataFim.substring(6, 10)).intValue());
			// joga exessão
			if (calendarFim.compareTo(calendarInicio) < 0) {
				throw new ActionServletException(
						"atencao.data_fim_menor_inicio");
			}
		}

		if (descricaoCriterio != null
				&& !descricaoCriterio.equalsIgnoreCase("")) {
			filtroCobrancaCriterio.adicionarParametro(new ComparacaoTexto(
					FiltroCobrancaCriterio.DESCRICAO_COBRANCA_CRITERIO,
					descricaoCriterio));
		}

		if (dataInicio != null && !dataInicio.equalsIgnoreCase("")) {
			if (dataFim == null || dataFim.equalsIgnoreCase("")) {
				filtroCobrancaCriterio.adicionarParametro(new Intervalo(FiltroCobrancaCriterio.DATA_INICIO_VIGENCIA, Util
						.converteStringParaDate(dataInicio), Util.converteStringParaDate(ConstantesSistema.DATA_LIMITE)));
			}else{
				filtroCobrancaCriterio.adicionarParametro(new Intervalo(
						FiltroCobrancaCriterio.DATA_INICIO_VIGENCIA, Util
								.converteStringParaDate(dataInicio), Util
								.converteStringParaDate(dataFim)));
			}
		}
		if (numeroAnos != null && !numeroAnos.equalsIgnoreCase("")) {

			Integer numeroAnosFormatado = new Integer(numeroAnos);
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.NUMERO_ANOS_CONTA_ANTIGA,
					numeroAnosFormatado));
		}
		if (opcaoContaRevisao != null
				&& !opcaoContaRevisao.equalsIgnoreCase("")
				&& !opcaoContaRevisao.equalsIgnoreCase("3")) {
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.INDICADOR_CONTA_REVISAO,
					opcaoContaRevisao));
		}
		if (opcaoImovelSitCobranca != null
				&& !opcaoImovelSitCobranca.equalsIgnoreCase("")
				&& !opcaoImovelSitCobranca.equalsIgnoreCase("3")) {
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.INDICADOR_IMOVEL_SITUACAO_COBRANCA,
					opcaoImovelSitCobranca));
		}
		if (opcaoInqDebitoContaAntiga != null
				&& !opcaoInqDebitoContaAntiga.equalsIgnoreCase("")
				&& !opcaoInqDebitoContaAntiga.equalsIgnoreCase("3")) {
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.INDICADOR_DEBITO_CONTA_ANTIGA,
					opcaoInqDebitoContaAntiga));
		}
		if (opcaoInqDebitoConta != null
				&& !opcaoInqDebitoConta.equalsIgnoreCase("")
				&& !opcaoInqDebitoConta.equalsIgnoreCase("3")) {
			filtroCobrancaCriterio
					.adicionarParametro(new ParametroSimples(
							FiltroCobrancaCriterio.INDICADOR_INQUILINO_DEBITO_CONTA_MES,
							opcaoInqDebitoConta));
		}
		if (opcaoImovelDebito != null
				&& !opcaoImovelDebito.equalsIgnoreCase("")
				&& !opcaoImovelDebito.equalsIgnoreCase("3")) {
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.INDICADOR_DEBITO_CONTA_MES,
					opcaoImovelDebito));
		}
		if (opcaoImovelSitEspecial != null
				&& !opcaoImovelSitEspecial.equalsIgnoreCase("")
				&& !opcaoImovelSitEspecial.equalsIgnoreCase("3")) {
			filtroCobrancaCriterio.adicionarParametro(new ParametroSimples(
					FiltroCobrancaCriterio.INDICADOR_IMOVEL_PARALISACAO,
					opcaoImovelSitEspecial));
		}

		Map resultado = controlarPaginacao(httpServletRequest, retorno,
				filtroCobrancaCriterio, CobrancaCriterio.class.getName());

		Collection colecaoCriterioCobranca = (Collection) resultado
				.get("colecaoRetorno");

		retorno = (ActionForward) resultado.get("destinoActionForward");

		if (colecaoCriterioCobranca == null
				|| colecaoCriterioCobranca.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		} else {
			sessao.setAttribute("colecaoCriterioCobranca",
					colecaoCriterioCobranca);
		}

		return retorno;
	}
}
