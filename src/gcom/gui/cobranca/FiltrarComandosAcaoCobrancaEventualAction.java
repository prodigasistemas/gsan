package gcom.gui.cobranca;

import java.util.Calendar;
import java.util.GregorianCalendar;

import gcom.cobranca.FiltroCobrancaAcaoAtividadeComando;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Permite consultar comandos de ação de cobrança [UC0326] Filtrar Comandos de
 * Ação de Cobrança - Eventual
 */
public class FiltrarComandosAcaoCobrancaEventualAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("retornarComandosAcaoCobrancaEventual");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarComandosAcaoCobrancaEventualActionForm form = (FiltrarComandosAcaoCobrancaEventualActionForm) actionForm;

		if (sessao.getAttribute("filtroCobrancaAcaoAtividadeComando") == null) {

			if (!form.getIndicadorCriterio().equals("Comando")) {
				form.setCriterioCobranca("");
			}

			if (httpServletRequest.getParameter("grupoCobranca") == null) {
				form.setGrupoCobranca(null);
			}
			if (httpServletRequest.getParameter("gerenciaRegional") == null) {
				form.setGerenciaRegional(null);
			}
			if (httpServletRequest.getParameter("unidadeNegocio") == null) {
				form.setUnidadeNegocio(null);
			}
			if (httpServletRequest.getParameter("clienteRelacaoTipo") == null) {
				form.setClienteRelacaoTipo(null);
			}

			// [FS0014 - Validar período de emissão];
			String dataInicial = form.getDataEmissaoInicio();
			String dataFinal = form.getDataEmissaoFim();

			if ((dataInicial.trim().length() == 10) && (dataFinal.trim().length() == 10)) {

				Calendar calendarInicio = new GregorianCalendar();
				Calendar calendarFim = new GregorianCalendar();

				calendarInicio.setTime(Util.converteStringParaDate(dataInicial));
				calendarFim.setTime(Util.converteStringParaDate(dataFinal));

				if (calendarFim.compareTo(calendarInicio) < 0) {
					throw new ActionServletException("atencao.data_fim_menor_inicio");
				}
			}

			FiltroCobrancaAcaoAtividadeComando filtro = fachada.construirFiltroCobrancaAcaoAtividadeEventual(
					form.getGrupoCobranca(), form.getAcaoCobranca(), form.getAtividadeCobranca(),
					form.getPeriodoReferenciaContasInicial(), form.getPeriodoReferenciaContasFinal(),
					form.getPeriodoComandoInicial(), form.getPeriodoComandoFinal(),
					form.getPeriodoRealizacaoComandoInicial(), form.getPeriodoRealizacaoComandoFinal(),
					form.getPeriodoVencimentoContasInicial(), form.getPeriodoVencimentoContasFinal(),
					form.getIntervaloValorDocumentosInicial(), form.getIntervaloValorDocumentosFinal(),
					form.getIntervaloQuantidadeDocumentosInicial(), form.getIntervaloQuantidadeDocumentosFinal(),
					form.getIntervaloQuantidadeItensDocumentosInicial(), form.getIntervaloQuantidadeItensDocumentosFinal(),
					form.getSituacaoComando(), form.getIndicadorCriterio(), form.getGerenciaRegional(),
					form.getLocalidadeOrigemID(), form.getLocalidadeDestinoID(), form.getSetorComercialOrigemCD(),
					form.getSetorComercialDestinoCD(), form.getRotaInicial(), form.getRotaFinal(), form.getIdCliente(),
					form.getClienteRelacaoTipo(), form.getCriterioCobranca(), form.getUnidadeNegocio(),
					form.getIdCobrancaAcaoAtividadeComando(), form.getDataEmissaoInicio(), form.getDataEmissaoFim(),
					form.getConsumoMedioInicial(), form.getConsumoMedioFinal(), form.getTipoConsumo(),
					form.getPeriodoInicialFiscalizacao(), form.getPeriodoFinalFiscalizacao(), form.getSituacaoFiscalizacao(),
					form.getNumeroQuadraInicial(), form.getNumeroQuadraFinal());

			sessao.setAttribute("filtroCobrancaAcaoAtividadeComando", filtro);

			sessao.setAttribute("filtrarComandosAcaoCobrancaEventualActionForm", form);
		}

		return retorno;
	}

}
