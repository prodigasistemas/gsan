package gcom.gui.relatorio.arrecadacao.pagamento;

import gcom.arrecadacao.pagamento.FiltroPagamentoSituacao;
import gcom.arrecadacao.pagamento.PagamentoSituacao;
import gcom.gui.arrecadacao.FiltrarPagamentoSituacaoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.pagamento.RelatorioManterSituacaoPagamento;
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
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Arthur Carvalho
 * @version 1.0
 */

public class GerarRelatorioSituacaoPagamentoManterAction extends
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

		FiltrarPagamentoSituacaoActionForm filtrarPagamentoSituacaoActionForm = (FiltrarPagamentoSituacaoActionForm) actionForm;

		FiltroPagamentoSituacao filtroPagamentoSituacao = (FiltroPagamentoSituacao) sessao
				.getAttribute("filtroPagamentoSituacao");

		// Inicio da parte que vai mandar os parametros para o relatório

		PagamentoSituacao pagamentoSituacaoParametros = new PagamentoSituacao();

		String idSituacaoPagamento = null;

		Short indicadorDeUso = null;

		if (filtrarPagamentoSituacaoActionForm.getIndicadorUso() != null
				&& !filtrarPagamentoSituacaoActionForm.getIndicadorUso().equals("")) {

			indicadorDeUso = new Short(""
					+ filtrarPagamentoSituacaoActionForm.getIndicadorUso());
		}

		// seta os parametros que serão mostrados no relatório

		pagamentoSituacaoParametros.setId(idSituacaoPagamento == null ? null : new Integer(
				idSituacaoPagamento));
		pagamentoSituacaoParametros.setDescricao(""
				+ filtrarPagamentoSituacaoActionForm.getDescricao());
		pagamentoSituacaoParametros.setDescricaoAbreviada(""
				+ filtrarPagamentoSituacaoActionForm.getDescricaoAbreviada());
		pagamentoSituacaoParametros.setIndicadorUso(indicadorDeUso);

		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterSituacaoPagamento relatorioManterSituacaoPagamento= new RelatorioManterSituacaoPagamento(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterSituacaoPagamento.addParametro("filtroPagamentoSituacao",
				filtroPagamentoSituacao);
		relatorioManterSituacaoPagamento.addParametro("pagamentoSituacaoParametros",
				pagamentoSituacaoParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterSituacaoPagamento.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterSituacaoPagamento,
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
