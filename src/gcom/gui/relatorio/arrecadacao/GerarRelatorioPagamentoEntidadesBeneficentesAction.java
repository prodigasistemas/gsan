package gcom.gui.relatorio.arrecadacao;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioPagamentoEntidadesBeneficentesAnalitico;
import gcom.relatorio.arrecadacao.RelatorioPagamentoEntidadesBeneficentesSintetico;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Geração do relatório [UC0978] Gerar Relatório de Pagamento para Entidades
 * Beneficentes
 * 
 * @author Daniel Alves
 */

public class GerarRelatorioPagamentoEntidadesBeneficentesAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);

		GerarRelatorioPagamentoEntidadesBeneficentesActionForm form = (GerarRelatorioPagamentoEntidadesBeneficentesActionForm) actionForm;

		String idEntidadeBeneficente = form.getIdEntidadeBeneficente();

		String mesAnoInicial = form.getMesAnoInicial();

		String mesAnoFinal = form.getMesAnoFinal();

		String tipo = form.getTipo();

		String idGerenciaRegional = form.getIdGerenciaRegional();

		String idUnidadeNegocio = form.getIdUnidadeNegocio();

		String idLocalidade = form.getIdLocalidade();

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		

		int opcaoTotalizacao = form.getOpcaoTotalizacao();

		if (tipoRelatorio == null || tipoRelatorio.equalsIgnoreCase("")) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			throw new ActionServletException("atencao.required", null,
					"Opção de Totalização ");
		}
		

		// verifica a data de consulta de pagamento
		if ((mesAnoInicial == null || mesAnoInicial.equals(""))
				&& (mesAnoFinal == null || mesAnoFinal.equals(""))) {
			throw new ActionServletException("atencao.required", null,
					"Mês/Ano de Pagamento");
		}

		// Verifica se a Data Final é maior que a Inicial
		if (mesAnoFinal != null && !mesAnoFinal.equals("")
				&& mesAnoInicial != null && !mesAnoInicial.equals("")) {

			Integer dtInicial = new Integer(Util
					.formatarMesAnoParaAnoMesSemBarra(mesAnoInicial));
			Integer dtFinal = new Integer(Util
					.formatarMesAnoParaAnoMesSemBarra(mesAnoFinal));

			if (dtFinal.compareTo(dtInicial) < 0) {

				throw new ActionServletException(
						"atencao.data.intervalo.invalido", null,
						"Data Invalida");

			}

		}

		// Parte que vai mandar o relatório para a tela

		if (tipo.equals("analitico")) {
			// cria uma instância da classe do relatório
			RelatorioPagamentoEntidadesBeneficentesAnalitico relatorioPagamentoEntidadesBeneficentes = new RelatorioPagamentoEntidadesBeneficentesAnalitico(
					(Usuario) (httpServletRequest.getSession(false))
							.getAttribute("usuarioLogado"));

			relatorioPagamentoEntidadesBeneficentes.addParametro("mesAnoInicial", mesAnoInicial);
			relatorioPagamentoEntidadesBeneficentes.addParametro("mesAnoFinal",
					mesAnoFinal);
			relatorioPagamentoEntidadesBeneficentes.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));

			relatorioPagamentoEntidadesBeneficentes.addParametro("opcaoTotalizacao", opcaoTotalizacao);

			relatorioPagamentoEntidadesBeneficentes.addParametro("idEntidadeBeneficente", idEntidadeBeneficente);
			relatorioPagamentoEntidadesBeneficentes.addParametro("idGerenciaRegional", idGerenciaRegional);
			relatorioPagamentoEntidadesBeneficentes.addParametro("idUnidadeNegocio", idUnidadeNegocio);
			relatorioPagamentoEntidadesBeneficentes.addParametro("idLocalidade", idLocalidade);

			try {
				retorno = processarExibicaoRelatorio(
						relatorioPagamentoEntidadesBeneficentes, tipoRelatorio,
						httpServletRequest, httpServletResponse, actionMapping);
			} catch (RelatorioVazioException ex) {
				// manda o erro para a página no request atual
				reportarErros(httpServletRequest, "atencao.relatorio.vazio");

				// seta o mapeamento de retorno para a tela de atenção de popup
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}

		}else if (tipo.equals("sintetico")) {
			// cria uma instância da classe do relatório
			RelatorioPagamentoEntidadesBeneficentesSintetico relatorioPagamentoEntidadesBeneficentes = new RelatorioPagamentoEntidadesBeneficentesSintetico(
					(Usuario) (httpServletRequest.getSession(false))
							.getAttribute("usuarioLogado"));

			relatorioPagamentoEntidadesBeneficentes.addParametro("mesAnoInicial", mesAnoInicial);
			relatorioPagamentoEntidadesBeneficentes.addParametro("mesAnoFinal",mesAnoFinal);
			relatorioPagamentoEntidadesBeneficentes.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));

			relatorioPagamentoEntidadesBeneficentes.addParametro("opcaoTotalizacao", opcaoTotalizacao);

			relatorioPagamentoEntidadesBeneficentes.addParametro("idEntidadeBeneficente", idEntidadeBeneficente);
			relatorioPagamentoEntidadesBeneficentes.addParametro("idGerenciaRegional", idGerenciaRegional);
			relatorioPagamentoEntidadesBeneficentes.addParametro("idUnidadeNegocio", idUnidadeNegocio);
			relatorioPagamentoEntidadesBeneficentes.addParametro("idLocalidade", idLocalidade);

			try {
				retorno = processarExibicaoRelatorio(
						relatorioPagamentoEntidadesBeneficentes, tipoRelatorio,
						httpServletRequest, httpServletResponse, actionMapping);
			} catch (RelatorioVazioException ex) {
				// manda o erro para a página no request atual
				reportarErros(httpServletRequest, "atencao.relatorio.vazio");

				// seta o mapeamento de retorno para a tela de atenção de popup
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}

		}
		
		
		return retorno;
	}

}
