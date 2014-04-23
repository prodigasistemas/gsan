package gcom.gui.relatorio.faturamento;

import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioFaturamentoLigacoesMedicaoIndividualizada;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0532] Gerar Relatório de Faturamento das Ligações com Medição Individualizada
 * 
 * @author Vivianne Sousa
 * @date 09/01/2007
 */

public class GerarRelatorioFaturamentoLigacoesMedicaoIndividualizadaAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		HttpSession sessao = httpServletRequest.getSession(false);
		FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql = new FiltroMedicaoHistoricoSql();
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)sessao.getAttribute("faturamentoGrupo");
		String mesAnoPesquisa = (String) sessao.getAttribute("mesAnoPesquisa");
		
		if (sessao.getAttribute("filtroMedicaoHistoricoSql") != null) {
			filtroMedicaoHistoricoSql = (FiltroMedicaoHistoricoSql) sessao.getAttribute("filtroMedicaoHistoricoSql");
		}
		
		
//		Collection colecaoFaturamentoLigacoesMedicaoIndividualizadaHelper = null; 
//			
//		if (colecaoImoveisGerarRelatorio != null
//				&& !colecaoImoveisGerarRelatorio.isEmpty()) {
//			fachada
//					.pesquisarFaturamentoLigacoesMedicaoIndividualizadaRelatorio(
//							colecaoImoveisGerarRelatorio, ""
//									+ faturamentoGrupo.getAnoMesReferencia());
//		} else {
//			fachada
//					.pesquisarFaturamentoLigacoesMedicaoIndividualizadaRelatorio(
//							filtroMedicaoHistoricoSql, ""
//									+ faturamentoGrupo.getAnoMesReferencia());
//		}
		
		// Parte que vai mandar o relatório para a tela
		// cria uma instância da classe do relatório
		RelatorioFaturamentoLigacoesMedicaoIndividualizada relatorioFaturamentoLigacoesMedicaoIndividualizada = new RelatorioFaturamentoLigacoesMedicaoIndividualizada((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
//		relatorioFaturamentoLigacoesMedicaoIndividualizada.addParametro("colecaoFaturamentoLigacoesMedicaoIndividualizadaHelper",colecaoFaturamentoLigacoesMedicaoIndividualizadaHelper);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioFaturamentoLigacoesMedicaoIndividualizada.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		relatorioFaturamentoLigacoesMedicaoIndividualizada.addParametro("anoMesFaturamentoGrupo" ,Util.formatarAnoMesParaMesAno(faturamentoGrupo.getAnoMesReferencia()));
		relatorioFaturamentoLigacoesMedicaoIndividualizada.addParametro("idGrupoFaturamento","" + faturamentoGrupo.getId());
		
		relatorioFaturamentoLigacoesMedicaoIndividualizada.addParametro("faturamentoGrupo",faturamentoGrupo);
		relatorioFaturamentoLigacoesMedicaoIndividualizada.addParametro("filtroMedicaoHistoricoSql",filtroMedicaoHistoricoSql);
		relatorioFaturamentoLigacoesMedicaoIndividualizada.addParametro("mesAnoPesquisa",mesAnoPesquisa);
		try {
			retorno = processarExibicaoRelatorio(relatorioFaturamentoLigacoesMedicaoIndividualizada,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);
		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");
		}

		return retorno;
	}

}
