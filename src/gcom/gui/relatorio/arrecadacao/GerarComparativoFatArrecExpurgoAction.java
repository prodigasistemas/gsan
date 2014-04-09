package gcom.gui.relatorio.arrecadacao;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.arrecadacao.RelatorioComparativoFatArrecExpurgado;
import gcom.tarefa.TarefaRelatorio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00744] Gerar Comparativo do Faturamento, Arrecadação e Expurgo
 * 
 * @author Sávio Luiz
 *
 * @date 09/12/2008
 */

public class GerarComparativoFatArrecExpurgoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		GerarComparativoFatArrecExpurgoActionForm form = 
			(GerarComparativoFatArrecExpurgoActionForm) actionForm;
			
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioComparativoFatArrecExpurgado relatorio = 
			new RelatorioComparativoFatArrecExpurgado(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("gerenciaRegional", form.getIdGerenciaRegional());
		relatorio.addParametro("unidadeNegocio", form.getIdUnidadeNegocio());
		relatorio.addParametro("mesAnoreferencia", form.getMesAnoReferencia());
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}
	
}
