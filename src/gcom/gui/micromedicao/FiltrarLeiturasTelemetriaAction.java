package gcom.gui.micromedicao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.bean.FiltrarLeiturasTelemetriaHelper;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1070] Filtrar Leituras Telemetria
 * 
 * @author Hugo Amorim
 * @date 27/09/2010
 * 
 */
public class FiltrarLeiturasTelemetriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirManterLeiturasTemeletriaAction");

		FiltrarLeiturasTelemetriaForm form = (FiltrarLeiturasTelemetriaForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		this.executarValidacoes(form);
		
		FiltrarLeiturasTelemetriaHelper helper =
			this.criarHelper(form);
		
		Integer qtdTotalRegistros
			= this.getFachada().countFiltrarLeiturasTelemetria(helper);
		
		if(qtdTotalRegistros==0){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		boolean existeLeiturasNaoProcessadas = this.getFachada().verificarLeiturasTelemetriaNaoProcessadas(helper);
		
		sessao.setAttribute("filtro", helper);
		sessao.setAttribute("existeLeiturasNaoProcessadas", existeLeiturasNaoProcessadas);
		sessao.setAttribute("qtdTotalRegistros", qtdTotalRegistros);

		return retorno;
	}

	private FiltrarLeiturasTelemetriaHelper criarHelper(
			FiltrarLeiturasTelemetriaForm form) {
		
		FiltrarLeiturasTelemetriaHelper retorno = 
			new FiltrarLeiturasTelemetriaHelper();
		
		retorno.setSituacaoLeitura(form.getSituacaoLeitura());
		
		if (Util.verificarNaoVazio(form.getPeriodoEnvioInicial())){
			
			Date periodoInicial = 
				Util.converteStringParaDate(form.getPeriodoEnvioInicial());
			
			retorno.setPeriodoEnvioInicial(periodoInicial);
		}
		
		if (Util.verificarNaoVazio(form.getPeriodoEnvioFinal())){
			
			Date periodoFinal = 
				Util.converteStringParaDate(form.getPeriodoEnvioFinal());
			
			retorno.setPeriodoEnvioFinal(periodoFinal);
		}
		
		if (Util.verificarNaoVazio(form.getPeriodoLeituraInicial())){
			
			Date periodoInicial = 
				Util.converteStringParaDate(form.getPeriodoLeituraInicial());
			
			retorno.setPeriodoLeituraInicial(periodoInicial);
		}
		
		if (Util.verificarNaoVazio(form.getPeriodoLeituraFinal())){
			
			Date periodoFinal = 
				Util.converteStringParaDate(form.getPeriodoLeituraFinal());
			
			retorno.setPeriodoLeituraFinal(periodoFinal);
		}
		
		
		return retorno;
	}

	private void executarValidacoes(FiltrarLeiturasTelemetriaForm form) {
		
		//Valida Datas de Envio no filtro.
		if (Util.verificarNaoVazio(form.getPeriodoEnvioInicial())
				&& Util.verificarNaoVazio(form.getPeriodoEnvioFinal())) {

			Date periodoInicial = Util.converteStringParaDate(form
					.getPeriodoEnvioInicial());
			Date periodoFinal = Util.converteStringParaDate(form
					.getPeriodoEnvioFinal());

			if (periodoFinal.compareTo(periodoInicial) < 0) {
				throw new ActionServletException(
						"atencao.data_final_periodo.anterior.data_inicial_periodo");
			}
		}
		//Valida Datas de Leituras no filtro.
		if (Util.verificarNaoVazio(form.getPeriodoLeituraInicial())
				&& Util.verificarNaoVazio(form.getPeriodoLeituraFinal())) {

			Date periodoInicial = Util.converteStringParaDate(form
					.getPeriodoLeituraInicial());
			Date periodoFinal = Util.converteStringParaDate(form
					.getPeriodoLeituraFinal());

			if (periodoFinal.compareTo(periodoInicial) < 0) {
				throw new ActionServletException(
						"atencao.data_final_periodo.anterior.data_inicial_periodo");
			}
		}
	}
}
