package gcom.gui.relatorio.faturamento;

import gcom.gui.faturamento.consumotarifa.FiltrarConsumoTarifaActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.faturamento.RelatorioManterConsumoTarifa;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de bairro manter
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioConsumoTarifaManterAction extends ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descrição do método>>
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		FiltrarConsumoTarifaActionForm filtrarConsumoTarifaActionForm = (FiltrarConsumoTarifaActionForm) actionForm;

		String descricao = null;
		if (filtrarConsumoTarifaActionForm.getDescTarifa() != null && !filtrarConsumoTarifaActionForm.getDescTarifa().trim().equals("")) {
			descricao = filtrarConsumoTarifaActionForm.getDescTarifa().toUpperCase();
		}
		String dataVigenciaInicial = filtrarConsumoTarifaActionForm.getDataVigencia();
		String dataVigenciaFinal = filtrarConsumoTarifaActionForm.getDataVigenciaFim();

		Date dataVigenciaInicialFormatada = null;
		if (dataVigenciaInicial != null && !dataVigenciaInicial.trim().equals("")) {
			dataVigenciaInicialFormatada = Util.converteStringParaDate(dataVigenciaInicial);
		}
		
		Date dataVigenciaFinalFormatada = null;
		if (dataVigenciaFinal != null && !dataVigenciaFinal.trim().equals("")) {
			dataVigenciaFinalFormatada = Util.converteStringParaDate(dataVigenciaFinal);
		}
		
		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioManterConsumoTarifa relatorio = new RelatorioManterConsumoTarifa((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorio.addParametro("descricao", descricao);
		relatorio.addParametro("dataVigenciaInicial", dataVigenciaInicialFormatada);
		relatorio.addParametro("dataVigenciaFinal", dataVigenciaFinalFormatada);
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
