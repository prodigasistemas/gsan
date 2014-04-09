package gcom.gui.relatorio.arrecadacao;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.FiltroDevolucao;
import gcom.arrecadacao.FiltroDevolucaoHistorico;
import gcom.gui.arrecadacao.FiltrarDevolucaoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioDevolucao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de bairro manter
 * 
 * @author Sávio Luiz
 * @created 11 de Julho de 2005
 */
public class GerarRelatorioDevolucaoAction extends
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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarDevolucaoActionForm filtrarDevolucaoActionForm = (FiltrarDevolucaoActionForm) actionForm;

		FiltroDevolucao filtroDevolucao = (FiltroDevolucao) sessao
				.getAttribute("filtroDevolucao");

		FiltroDevolucaoHistorico filtroDevolucaoHistorico = (FiltroDevolucaoHistorico) sessao
				.getAttribute("filtroDevolucaoHistorico");
		
		// Collection colecaoDevolucoes = (Collection) sessao
		// .getAttribute("colecaoLocalidadeDevolucoes");

		// Inicio da parte que vai mandar os parametros para o relatório

		Devolucao devolucaoParametrosInicial = new Devolucao();
		Devolucao devolucaoParametrosFinal = new Devolucao();

		// seta os parametros que serão mostrados no relatório
		if (filtrarDevolucaoActionForm.getPeriodoArrecadacaoInicio() != null
				&& !filtrarDevolucaoActionForm.getPeriodoArrecadacaoInicio()
						.equals("")) {
			devolucaoParametrosInicial
					.setAnoMesReferenciaDevolucao(new Integer(
							Util
									.formatarMesAnoParaAnoMesSemBarra(filtrarDevolucaoActionForm
											.getPeriodoArrecadacaoInicio())));
			devolucaoParametrosFinal
					.setAnoMesReferenciaDevolucao(new Integer(
							Util
									.formatarMesAnoParaAnoMesSemBarra(filtrarDevolucaoActionForm
											.getPeriodoArrecadacaoFim())));
		}
		if (filtrarDevolucaoActionForm.getDataDevolucaoInicio() != null
				&& !filtrarDevolucaoActionForm.getDataDevolucaoInicio().equals(
						"")) {
			devolucaoParametrosInicial.setDataDevolucao(Util
					.converteStringParaDate(filtrarDevolucaoActionForm
							.getDataDevolucaoInicio()));
			devolucaoParametrosFinal.setDataDevolucao(Util
					.converteStringParaDate(filtrarDevolucaoActionForm
							.getDataDevolucaoFim()));
		}

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioDevolucao relatorioDevolucao = new RelatorioDevolucao((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		// relatorioDevolucao.addParametro("colecaoDevolucoes",
		// colecaoDevolucoes);
		relatorioDevolucao.addParametro("filtroDevolucao", filtroDevolucao);
		relatorioDevolucao.addParametro("filtroDevolucaoHistorico", filtroDevolucaoHistorico);
		relatorioDevolucao.addParametro("devolucaoParametrosInicial",
				devolucaoParametrosInicial);
		relatorioDevolucao.addParametro("devolucaoParametrosFinal",
				devolucaoParametrosFinal);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioDevolucao.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioDevolucao,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

		} catch (RelatorioVazioException ex1) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		// devolve o mapeamento contido na variável retorno
		return retorno;
	}

}
