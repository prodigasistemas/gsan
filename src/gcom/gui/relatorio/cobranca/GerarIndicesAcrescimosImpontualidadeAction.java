package gcom.gui.relatorio.cobranca;

import gcom.cobranca.FiltroIndicesAcrescimosImpontualidade;
import gcom.cobranca.IndicesAcrescimosImpontualidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioGerarIndicesAcrescimosImpontualidade;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarIndicesAcrescimosImpontualidadeAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarDadosParaLeitura");

		IndiceAcrescimosImpontualidadeRelatorioActionForm indiceAcrescimosImpontualidadeRelatorioActionForm = (IndiceAcrescimosImpontualidadeRelatorioActionForm) actionForm;

		Collection colecaoIndicesAcrescimosImpontualidade = this
				.gerarColecaoDadosParaLeituraHelper(indiceAcrescimosImpontualidadeRelatorioActionForm);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioGerarIndicesAcrescimosImpontualidade relatorio = new RelatorioGerarIndicesAcrescimosImpontualidade(
				this.getUsuarioLogado(httpServletRequest));

		relatorio.addParametro("todosAcrescimos",
				indiceAcrescimosImpontualidadeRelatorioActionForm
						.getTodosAcrecimos());

		relatorio.addParametro("mesAnoReferenciaInicial",
				indiceAcrescimosImpontualidadeRelatorioActionForm
						.getMesAnoReferenciaInicial());

		relatorio.addParametro("mesAnoReferenciaFinal",
				indiceAcrescimosImpontualidadeRelatorioActionForm
						.getMesAnoReferenciaFinal());

		relatorio.addParametro("colecaoIndicesAcrescimosImpontualidade",
				colecaoIndicesAcrescimosImpontualidade);

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);

		return retorno;
	}

	private Collection gerarColecaoDadosParaLeituraHelper(
			IndiceAcrescimosImpontualidadeRelatorioActionForm indiceAcrescimosImpontualidadeRelatorioActionForm) {

		Collection retorno = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		FiltroIndicesAcrescimosImpontualidade filtroIndicesAcrescimosImpontualidade = new FiltroIndicesAcrescimosImpontualidade();
		filtroIndicesAcrescimosImpontualidade
				.setCampoOrderBy(FiltroIndicesAcrescimosImpontualidade.ANO_MES_REFERENCIA);

		if (indiceAcrescimosImpontualidadeRelatorioActionForm
				.getTodosAcrecimos() != null
				&& indiceAcrescimosImpontualidadeRelatorioActionForm
						.getTodosAcrecimos().equals("2")) {
			Integer anoMesReferenciaInicial = Util
					.formatarMesAnoComBarraParaAnoMes(indiceAcrescimosImpontualidadeRelatorioActionForm
							.getMesAnoReferenciaInicial());
			Integer anoMesReferenciaFinal = Util
					.formatarMesAnoComBarraParaAnoMes(indiceAcrescimosImpontualidadeRelatorioActionForm
							.getMesAnoReferenciaFinal());

			filtroIndicesAcrescimosImpontualidade
					.adicionarParametro(new MaiorQue(
							FiltroIndicesAcrescimosImpontualidade.ANO_MES_REFERENCIA,
							anoMesReferenciaInicial));
			filtroIndicesAcrescimosImpontualidade
					.adicionarParametro(new MenorQue(
							FiltroIndicesAcrescimosImpontualidade.ANO_MES_REFERENCIA,
							anoMesReferenciaFinal));

		}

		retorno = fachada.pesquisar(filtroIndicesAcrescimosImpontualidade,
				IndicesAcrescimosImpontualidade.class.getName());
		if (Util.isVazioOrNulo(retorno)) {
			throw new ActionServletException("atencao.naocadastrado", null,
					"Índice Acréscimos Impontualidade");
		}

		return retorno;
	}
}
