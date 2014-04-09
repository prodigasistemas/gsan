package gcom.gui.relatorio.micromedicao;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ImovelMicromedicao;
import gcom.faturamento.FaturamentoGrupo;
import gcom.micromedicao.medicao.FiltroMedicaoHistoricoSql;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.micromedicao.FiltrarAnaliseExcecoesLeiturasHelper;
import gcom.relatorio.micromedicao.RelatorioAvisoAnormalidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0805] Gerar Aviso de Anormalidade
 * 
 * @author Rafael Corrêa
 * @date 03/06/2008
 */

public class GerarRelatorioAvisoAnormalidadeAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		HttpSession sessao = httpServletRequest.getSession(false);
		FiltroMedicaoHistoricoSql filtroMedicaoHistoricoSql = new FiltroMedicaoHistoricoSql();
		FiltrarAnaliseExcecoesLeiturasHelper filtrarAnaliseExcecoesLeiturasHelper = (FiltrarAnaliseExcecoesLeiturasHelper) sessao.getAttribute("filtrarAnaliseExcecoesLeiturasHelper");
		FaturamentoGrupo faturamentoGrupo = (FaturamentoGrupo)sessao.getAttribute("faturamentoGrupo");
		Collection colecaoImoveisGerarAviso = (Collection) sessao.getAttribute("colecaoImoveisGerarAviso");
		String mesAnoPesquisa = (String) sessao.getAttribute("mesAnoPesquisa");
		
		Collection colecaoIdsImovel = (Collection) sessao.getAttribute("colecaoIdsImovelTotal");
		
		if (colecaoIdsImovel != null && !colecaoIdsImovel.isEmpty()) {

			int index = (Integer) sessao.getAttribute("index");

			Imovel imovel = ((ImovelMicromedicao) ((List) colecaoIdsImovel)
					.get(index)).getImovel();

			// Verifica se o imóvel o imóvel atual foi selecionado, em caso
			// afirmativo adiciona-o a coleção
			if (httpServletRequest.getParameter("gerarAviso") != null
					&& !httpServletRequest.getParameter("gerarAviso").trim()
							.equals("")) {

				if (colecaoImoveisGerarAviso == null) {
					colecaoImoveisGerarAviso = new ArrayList();
				}

				if (!colecaoImoveisGerarAviso.contains(imovel.getId())) {
					colecaoImoveisGerarAviso.add(imovel.getId());
				}
			} else {
				if (colecaoImoveisGerarAviso != null
						&& colecaoImoveisGerarAviso.contains(imovel.getId())) {
					colecaoImoveisGerarAviso.remove(imovel.getId());
				}
			}

		}
		
		if (sessao.getAttribute("filtroMedicaoHistoricoSql") != null) {
			filtroMedicaoHistoricoSql = (FiltroMedicaoHistoricoSql) sessao.getAttribute("filtroMedicaoHistoricoSql");
		}
		
		// Parte que vai mandar o relatório para a tela
		// cria uma instância da classe do relatório
		RelatorioAvisoAnormalidade relatorioAvisoAnormalidade = new RelatorioAvisoAnormalidade((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
//		relatorioFaturamentoLigacoesMedicaoIndividualizada.addParametro("colecaoFaturamentoLigacoesMedicaoIndividualizadaHelper",colecaoFaturamentoLigacoesMedicaoIndividualizadaHelper);

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioAvisoAnormalidade.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		
		relatorioAvisoAnormalidade.addParametro("anoMesFaturamentoGrupo", faturamentoGrupo.getAnoMesReferencia());
		relatorioAvisoAnormalidade.addParametro("anoMesPesquisa", Util.formatarMesAnoComBarraParaAnoMes(mesAnoPesquisa));
		relatorioAvisoAnormalidade.addParametro("idGrupoFaturamento", "" + faturamentoGrupo.getId());
		relatorioAvisoAnormalidade.addParametro("colecaoImoveisGerarAviso", colecaoImoveisGerarAviso);
		
		relatorioAvisoAnormalidade.addParametro("faturamentoGrupo", faturamentoGrupo);
		relatorioAvisoAnormalidade.addParametro("filtroMedicaoHistoricoSql", filtroMedicaoHistoricoSql);
		relatorioAvisoAnormalidade.addParametro("filtrarAnaliseExcecoesLeiturasHelper", filtrarAnaliseExcecoesLeiturasHelper);
		try {
			retorno = processarExibicaoRelatorio(relatorioAvisoAnormalidade,
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
