package gcom.gui.relatorio.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.gui.arrecadacao.FiltrarArrecadacaoFormaActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.arrecadacao.RelatorioManterArrecadacaoForma;
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
 * @author Rafael Corrêa
 * @version 1.0
 */

public class GerarRelatorioArrecadacaoFormaManterAction extends
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

		FiltrarArrecadacaoFormaActionForm filtrarArrecadacaoFormaActionForm = (FiltrarArrecadacaoFormaActionForm) actionForm;

		FiltroArrecadacaoForma filtroArrecadacaoForma = (FiltroArrecadacaoForma) sessao
				.getAttribute("filtroArrecadacaoForma");

		// Inicio da parte que vai mandar os parametros para o relatório

		ArrecadacaoForma arrecadacaoFormaParametros = new ArrecadacaoForma();

		String idArrecadacaoForma = null;

		String idArrecadacaoFormaPesquisar = (String) filtrarArrecadacaoFormaActionForm
				.getIdArrecadacaoForma();

		if (idArrecadacaoFormaPesquisar != null && !idArrecadacaoFormaPesquisar.equals("")) {
			idArrecadacaoForma = idArrecadacaoFormaPesquisar;
		}
		
		String codigoArrecadacaoForma = null;

		String codigoArrecadacaoFormaPesquisar = filtrarArrecadacaoFormaActionForm.getCodigoArrecadacaoForma();

		if (codigoArrecadacaoFormaPesquisar != null && !codigoArrecadacaoFormaPesquisar.equals("")) {
			codigoArrecadacaoForma = codigoArrecadacaoFormaPesquisar;
		}

		// seta os parametros que serão mostrados no relatório

		arrecadacaoFormaParametros.setId(idArrecadacaoForma == null ? null : new Integer(
				idArrecadacaoForma));
		arrecadacaoFormaParametros.setCodigoArrecadacaoForma(codigoArrecadacaoForma);
		arrecadacaoFormaParametros.setDescricao(""
				+ filtrarArrecadacaoFormaActionForm.getDescricao());
		
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterArrecadacaoForma relatorioManterArrecadacaoForma = new RelatorioManterArrecadacaoForma(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterArrecadacaoForma.addParametro("filtroArrecadacaoForma",
				filtroArrecadacaoForma);
		relatorioManterArrecadacaoForma.addParametro("arrecadacaoFormaParametros",
				arrecadacaoFormaParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterArrecadacaoForma.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterArrecadacaoForma,
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
