package gcom.gui.relatorio.seguranca;

import gcom.gui.seguranca.acesso.FiltrarAlteracaoTipoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.seguranca.RelatorioManterAlteracaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.transacao.AlteracaoTipo;
import gcom.seguranca.transacao.FiltroAlteracaoTipo;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Vinicius Medeiros
 * @version 1.0
 */

public class GerarRelatorioAlteracaoTipoManterAction extends
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

		FiltrarAlteracaoTipoActionForm filtrarAlteracaoTipoActionForm = (FiltrarAlteracaoTipoActionForm) actionForm;

		FiltroAlteracaoTipo filtroAlteracaoTipo = (FiltroAlteracaoTipo) sessao
				.getAttribute("filtroAlteracaoTipo");

		// Inicio da parte que vai mandar os parametros para o relatório

		AlteracaoTipo alteracaoTipoParametros = new AlteracaoTipo();

		String id = null;

		String idAlteracaoTipoPesquisar = (String) filtrarAlteracaoTipoActionForm
				.getId();

		if (idAlteracaoTipoPesquisar != null && !idAlteracaoTipoPesquisar.equals("")) {
			id = idAlteracaoTipoPesquisar;
		}
		
		// seta os parametros que serão mostrados no relatório

		alteracaoTipoParametros.setId(id == null ? null : new Integer(
				id));
		alteracaoTipoParametros.setDescricao(""
				+ filtrarAlteracaoTipoActionForm.getDescricao());
		alteracaoTipoParametros.setDescricaoAbreviada(""
				+ filtrarAlteracaoTipoActionForm.getDescricaoAbreviada());
		
		// Fim da parte que vai mandar os parametros para o relatório

		// cria uma instância da classe do relatório
		RelatorioManterAlteracaoTipo relatorioManterAlteracaoTipo = new RelatorioManterAlteracaoTipo(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorioManterAlteracaoTipo.addParametro("filtroAlteracaoTipo",
				filtroAlteracaoTipo);
		relatorioManterAlteracaoTipo.addParametro("alteracaoTipoParametros",
				alteracaoTipoParametros);

		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterAlteracaoTipo.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterAlteracaoTipo,
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
