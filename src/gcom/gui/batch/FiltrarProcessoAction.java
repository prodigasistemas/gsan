package gcom.gui.batch;

import gcom.batch.FiltroProcessoIniciado;
import gcom.batch.ProcessoIniciado;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Intervalo;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que filtra um ProcessoIniciado no sistema
 * 
 * @author Rodrigo Silveira
 * @created 24/07/2006
 */
public class FiltrarProcessoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("resultadoFiltrarProcesso");

		FiltrarProcessoActionForm filtrarProcessoActionForm = (FiltrarProcessoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		String dataAgendamentoInicial = filtrarProcessoActionForm.getDataAgendamentoInicial();
		String horaAgendamentoInicial = filtrarProcessoActionForm.getHoraAgendamentoInicial();
		String dataAgendamentoFinal = filtrarProcessoActionForm.getDataAgendamentoFinal();
		String horaAgendamentoFinal = filtrarProcessoActionForm.getHoraAgendamentoFinal();
		String dataPeriodoInicioInicial = filtrarProcessoActionForm.getDataPeriodoInicioInicial();
		String horaPeriodoInicioInicial = filtrarProcessoActionForm.getHoraPeriodoInicioInicial();
		String dataPeriodoInicioFinal = filtrarProcessoActionForm.getDataPeriodoInicioFinal();
		String horaPeriodoInicioFinal = filtrarProcessoActionForm.getHoraPeriodoInicioFinal();
		String dataConclusaoInicial = filtrarProcessoActionForm.getDataConclusaoInicial();
		String horaConclusaoInicial = filtrarProcessoActionForm.getHoraConclusaoInicial();
		String dataConclusaoFinal = filtrarProcessoActionForm.getDataConclusaoFinal();
		String horaConclusaoFinal = filtrarProcessoActionForm.getHoraConclusaoFinal();
		String dataComandoInicial = filtrarProcessoActionForm.getDataComandoInicial();
		String horaComandoInicial = filtrarProcessoActionForm.getHoraComandoInicial();
		String dataComandoFinal = filtrarProcessoActionForm.getDataComandoFinal();
		String horaComandoFinal = filtrarProcessoActionForm.getHoraComandoFinal();

		//CRC-1466
		String usuarioId = filtrarProcessoActionForm.getUsuarioId();
		

		// Se o usuário não informar a hora inicial, ela ficará com o valor
		// "00:00:00"
		if (checarCampoVazioNulo(horaAgendamentoInicial)) {
			horaAgendamentoInicial = "00:00:00";
		}

		if (checarCampoVazioNulo(horaPeriodoInicioInicial)) {
			horaPeriodoInicioInicial = "00:00:00";
		}

		if (checarCampoVazioNulo(horaConclusaoInicial)) {
			horaConclusaoInicial = "00:00:00";
		}

		if (checarCampoVazioNulo(horaComandoInicial)) {
			horaComandoInicial = "00:00:00";
		}

		// Se o usuário não informar a hora final, ela ficará com o valor
		// "23:59:59"
		if (checarCampoVazioNulo(horaAgendamentoFinal)) {
			horaAgendamentoFinal = "23:59:59";
		}

		if (checarCampoVazioNulo(horaPeriodoInicioFinal)) {
			horaPeriodoInicioFinal = "23:59:59";
		}

		if (checarCampoVazioNulo(horaConclusaoFinal)) {
			horaConclusaoFinal = "23:59:59";
		}

		if (checarCampoVazioNulo(horaComandoFinal)) {
			horaComandoFinal = "23:59:59";
		}

		FiltroProcessoIniciado filtroProcessoIniciado = 
			new FiltroProcessoIniciado(FiltroProcessoIniciado.DATA_HORA_AGENDAMENTO);
		
		//CRC-1466
		if(usuarioId != null && !usuarioId.trim().equals("")){
			filtroProcessoIniciado.adicionarParametro(new ParametroSimples(FiltroProcessoIniciado.USUARIO_ID, usuarioId));
		}

		if (!checarCampoVazioNulo(filtrarProcessoActionForm.getIdProcesso())) {
			
			int idProcesso = 
				Integer.parseInt(filtrarProcessoActionForm.getIdProcesso());

			if (idProcesso != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				filtroProcessoIniciado.adicionarParametro(
					new ParametroSimples(
						FiltroProcessoIniciado.ID_PROCESSO, idProcesso));
			}
		}
		
		if (!checarCampoVazioNulo(filtrarProcessoActionForm.getIdSituacaoProcesso())) {
			
			int idSituacaoProcesso = 
				Integer.parseInt(filtrarProcessoActionForm.getIdSituacaoProcesso());

			if (idSituacaoProcesso != ConstantesSistema.NUMERO_NAO_INFORMADO) {
				filtroProcessoIniciado.adicionarParametro(
					new ParametroSimples(FiltroProcessoIniciado.PROCESSO_SITUACAO_ID,idSituacaoProcesso));
			}
		}

		// Trecho que verifica se o usuário apenas preencheu a data inicial para
		// completar a data final com o mesmo dado informado
		if (!checarCampoVazioNulo(dataAgendamentoInicial)) {
			
			if (checarCampoVazioNulo(dataAgendamentoFinal)) {
				dataAgendamentoFinal = dataAgendamentoInicial;
			}

			filtroProcessoIniciado.adicionarParametro(
				new Intervalo(FiltroProcessoIniciado.DATA_HORA_AGENDAMENTO,
					converterDataHora(dataAgendamentoInicial,horaAgendamentoInicial), 
					converterDataHora(dataAgendamentoFinal, horaAgendamentoFinal)));
		}

		if (!checarCampoVazioNulo(dataPeriodoInicioInicial)) {
			
			if (checarCampoVazioNulo(dataPeriodoInicioFinal)) {
				dataPeriodoInicioFinal = dataPeriodoInicioInicial;
			}

			filtroProcessoIniciado.adicionarParametro(
				new Intervalo(FiltroProcessoIniciado.DATA_HORA_INICIO,
					converterDataHora(dataPeriodoInicioInicial,horaPeriodoInicioInicial), 
					converterDataHora(dataPeriodoInicioFinal, horaPeriodoInicioFinal)));
		}

		if (!checarCampoVazioNulo(dataConclusaoInicial)) {
			
			if (checarCampoVazioNulo(dataConclusaoFinal)) {
				dataConclusaoFinal = dataConclusaoInicial;
			}
			
			filtroProcessoIniciado.adicionarParametro(
				new Intervalo(FiltroProcessoIniciado.DATA_HORA_TERMINO,
					converterDataHora(dataConclusaoInicial,horaConclusaoInicial), 
					converterDataHora(dataConclusaoFinal, horaConclusaoFinal)));
		}

		if (!checarCampoVazioNulo(dataComandoInicial)) {

			if (checarCampoVazioNulo(dataConclusaoFinal)) {
				dataComandoFinal = dataComandoInicial;
			}

			filtroProcessoIniciado.adicionarParametro(
				new Intervalo(FiltroProcessoIniciado.DATA_HORA_COMANDO,
					converterDataHora(dataComandoInicial, horaComandoInicial),
					converterDataHora(dataComandoFinal, horaComandoFinal)));

		}

		filtroProcessoIniciado.adicionarCaminhoParaCarregamentoEntidade("processo");
		filtroProcessoIniciado.adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtroProcessoIniciado.adicionarCaminhoParaCarregamentoEntidade("processoSituacao");

		Map resultado = 
			controlarPaginacao(httpServletRequest, 
				retorno,
				filtroProcessoIniciado, 
				ProcessoIniciado.class.getName());
		
		Collection<ProcessoIniciado> colecaoProcessosIniciados = 
			(Collection) resultado.get("colecaoRetorno");
		
		/*
		 *Caso a pesquisa não retorne resultado é lançada mensagem informando que 
		 *a pesquisa não retorna nenhum resultado 
		 */
		if ( colecaoProcessosIniciados != null && colecaoProcessosIniciados.isEmpty() ) {
			
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		
		}else{
		
			retorno = (ActionForward) resultado.get("destinoActionForward");

			httpServletRequest.setAttribute("colecaoProcessosIniciados",colecaoProcessosIniciados);

			httpServletRequest.setAttribute("mesAnoReferencia", 
					Util.formatarAnoMesParaMesAno(fachada.pesquisarParametrosDoSistema().getAnoMesFaturamento()));
			httpServletRequest.setAttribute("dataCorrente", new Date());

			return retorno;
		}
	}

	/**
	 * Função que verifica se o campo é vazio ou se está nulo
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/07/2006
	 * 
	 * @return
	 */
	private boolean checarCampoVazioNulo(String campo) {
		boolean retorno = false;
		if (campo == null || 
			campo.trim().equals("") || 
			campo.trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			retorno = true;

		}
		return retorno;

	}

	/**
	 * Converte a data e a hora informada pelo usuário para um Date
	 * 
	 * @author Rodrigo Silveira
	 * @date 26/07/2006
	 * 
	 * @param data
	 * @param hora
	 * @return
	 */
	private Date converterDataHora(String data, String hora) {
		
		SimpleDateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy k:mm:ss");
		try {
			return formatoDataHora.parse(data + " " + hora);
		} catch (ParseException e) {
			throw new ActionServletException("erro.sistema");

		}

	}
}
