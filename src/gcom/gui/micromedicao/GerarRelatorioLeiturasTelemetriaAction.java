package gcom.gui.micromedicao;

import java.util.Date;

import gcom.micromedicao.bean.FiltrarLeiturasTelemetriaHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.micromedicao.RelatorioLeiturasTelemetria;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioLeiturasTelemetriaAction extends
		ExibidorProcessamentoTarefaRelatorio {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		FiltrarLeiturasTelemetriaForm form =
			(FiltrarLeiturasTelemetriaForm) actionForm;
		
		FiltrarLeiturasTelemetriaHelper helper =
			this.criarHelper(form);
		
		RelatorioLeiturasTelemetria relatorioLeiturasTelemetria = 
			new RelatorioLeiturasTelemetria(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		//Adiciona filtro escolhido pelo usuario ao relatorio
		relatorioLeiturasTelemetria.addParametro("filtroHelper", helper);
		
		// Chama o metódo de gerar relatório passando o código da analise
		//como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		relatorioLeiturasTelemetria.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		
		retorno = 
			processarExibicaoRelatorio(relatorioLeiturasTelemetria,
				tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);
		
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
}
