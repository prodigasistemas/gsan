package gcom.gui.relatorio.faturamento;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioAcrescimoPorImpontualidade;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Title: GCOM
 * Description: Sistema de Gestão Comercial
 * Copyright: Copyright (c) 2004
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class GerarRelatorioAcrescimoPorImpontualidadeAction extends
		ExibidorProcessamentoTarefaRelatorio {


	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a variável de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		//recupera o código da conta do request
		String multa = (String) sessao.getAttribute("multa");

		String juros = (String) sessao.getAttribute("juros");

		String atualizacao = (String) sessao.getAttribute("atualizacao");
		
		// Inicio da parte que vai mandar os parametros para o relatório

		String valorMulta = "";
		if (multa != null && !multa.equals("")) {

			valorMulta = multa;
		}

		String jurosDeMora = "";
		if (juros != null && !juros.equals("")) {

			jurosDeMora = juros;
		}

		String atualizacaoMonetaria = "";
		if (atualizacao != null
				&& !atualizacao.equals("")) {

			atualizacaoMonetaria = atualizacao;
		}

		//pega os parametros que serão mostrados no relatório

		// cria uma instância da classe do relatório
		RelatorioAcrescimoPorImpontualidade relatorioAcrescimoPorImpontualidade = new RelatorioAcrescimoPorImpontualidade(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioAcrescimoPorImpontualidade.addParametro("valorMulta",
				valorMulta);
		relatorioAcrescimoPorImpontualidade.addParametro("jurosDeMora",
				jurosDeMora);
		relatorioAcrescimoPorImpontualidade.addParametro("atualizacaoMonetaria",
				atualizacaoMonetaria);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioAcrescimoPorImpontualidade.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioAcrescimoPorImpontualidade,
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
