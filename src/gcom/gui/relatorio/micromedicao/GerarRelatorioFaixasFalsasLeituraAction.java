package gcom.gui.relatorio.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.micromedicao.RelatorioFaixasFalsasLeitura;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

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
public class GerarRelatorioFaixasFalsasLeituraAction extends
		ExibidorProcessamentoTarefaRelatorio {

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
		
		// Retorna uma instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		GerarRelatorioFaixasFalsasLeituraActionForm gerarRelatorioFaixasFalsasLeituraActionForm = (GerarRelatorioFaixasFalsasLeituraActionForm) actionForm;
		
		String anoMesReferenciaString = gerarRelatorioFaixasFalsasLeituraActionForm.getMesAnoReferencia();
		
		Integer anoMesReferencia = Util.formatarMesAnoComBarraParaAnoMes(anoMesReferenciaString);
		
		Integer qtdeRegistros = fachada.pesquisarImovelFaixaFalsaCount(anoMesReferencia);
		
		if (qtdeRegistros.intValue() == 0) {
			throw new ActionServletException(
					"atencao.nenhuma_faixa_falsa_encontrada", null,
					anoMesReferenciaString);
		}
		
		// Fim da parte que vai mandar os parametros para o relatório
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioFaixasFalsasLeitura relatorioFaixasFalsasLeitura = new RelatorioFaixasFalsasLeitura((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		relatorioFaixasFalsasLeitura.addParametro(
				"anoMesReferencia", anoMesReferencia);
		relatorioFaixasFalsasLeitura.addParametro(
				"qtdeRegistros", qtdeRegistros);
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioFaixasFalsasLeitura.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorioFaixasFalsasLeitura, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
