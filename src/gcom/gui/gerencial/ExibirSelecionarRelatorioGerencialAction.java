package gcom.gui.gerencial;

import gcom.gui.GcomAction;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirSelecionarRelatorioGerencialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		Map<String, String> relatoriosCadastro = new HashMap<String, String>();
		relatoriosCadastro.put("Resumo Ligações Economia",
				"ResumoLigacaoEconomia");
		relatoriosCadastro.put("Resumo Indicador Ligacao Economia",
		"ResumoIndicadorLigacaoEconomia");
		Map<String, String> relatoriosMicromedicao = new HashMap<String, String>();
		relatoriosMicromedicao.put("Resumo Anormalidades Consumo",
				"ResumoAnormalidadesConsumo");
		relatoriosMicromedicao.put("Resumo Consumo Agua",
				"ResumoConsumoAgua");

		relatoriosMicromedicao.put("Resumo Leitura Anormalidade",
		"ResumoLeituraAnormalidade");
		relatoriosMicromedicao.put("Resumo Hidrometro",
		"ResumoHidrometro");
		relatoriosMicromedicao.put("Resumo Instalacao Hidrometro",
		"ResumoInstalacaoHidrometro");
		relatoriosMicromedicao.put("Resumo Indicador Desempenho Micromedicao",
		"ResumoIndicadorDesempenhoMicromedicao");

		
		Map<String, String> relatoriosFaturamento = new HashMap<String, String>();
		relatoriosFaturamento.put("Resumo Situação Especial Faturamento",
				"ResumoSituacaoEspecialFaturamento");
		relatoriosFaturamento.put("Resumo Pendência", "ResumoPendencia");
		Map<String, String> relatoriosCobranca = new HashMap<String, String>();
		relatoriosCobranca.put("Resumo Situação Especial Cobrança",
				"ResumoSituacaoEspecialCobranca");
		Map<String, String> relatoriosArrecadacao = new HashMap<String, String>();
		Map<String, String> relatoriosAtendimentoPublico = new HashMap<String, String>();
		relatoriosCobranca.put("Resumo Parcelamento",
		"ResumoParcelamento");
		relatoriosFaturamento.put("Resumo Faturamento Outros",
		"ResumoFaturamentoOutros");
		relatoriosFaturamento.put("Resumo Faturamento Credito",
		"ResumoFaturamentoCredito");
		relatoriosFaturamento.put("Resumo Faturamento Agua Esgoto",
		"ResumoFaturamentoAguaEsgoto");
		relatoriosArrecadacao.put("Resumo Arrecadacao Outros",
		"ResumoArrecadacaoOutros");
		relatoriosArrecadacao.put("Resumo Arrecadacao Credito",
		"ResumoArrecadacaoCredito");
		relatoriosArrecadacao.put("Resumo Arrecadacao Agua Esgoto",
		"ResumoArrecadacaoAguaEsgoto");
		relatoriosAtendimentoPublico.put("Resumo Registro Atendimento",
		"ResumoRegistroAtendimento");
		
		httpServletRequest.setAttribute("relatoriosCadastro", relatoriosCadastro);
		httpServletRequest.setAttribute("relatoriosMicromedicao", relatoriosMicromedicao);
		httpServletRequest.setAttribute("relatoriosFaturamento", relatoriosFaturamento);
		httpServletRequest.setAttribute("relatoriosCobranca", relatoriosCobranca);
		httpServletRequest.setAttribute("relatoriosArrecadacao", relatoriosArrecadacao);
		httpServletRequest.setAttribute("relatoriosAtendimentoPublico", relatoriosAtendimentoPublico);

		
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("selecionarRelatorioGerencial");

		return retorno;

	}

}
