package gcom.gui.faturamento.autoinfracao;

import gcom.faturamento.autoinfracao.FiltroAutosInfracao;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.autoinfracao.RelatorioManterAutoInfracao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioManterAutoInfracaoAction extends
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
		
//		 cria a variável de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarAutoInfracaoActionForm form = (FiltrarAutoInfracaoActionForm) actionForm;

		FiltroAutosInfracao filtroAutosInfracao= (FiltroAutosInfracao) sessao
				.getAttribute("filtroAutosInfracao");

		// Inicio da parte que vai mandar os parametros para o relatório

		RelatorioManterAutoInfracao relatorioManterAutoInfracao = new RelatorioManterAutoInfracao(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioManterAutoInfracao.addParametro("filtroAutosInfracao",
				filtroAutosInfracao);
		
		relatorioManterAutoInfracao.addParametro("idImovel",
				form.getIdImovel());
		
		relatorioManterAutoInfracao.addParametro("descricaoImovel",
				form.getDescricaoImovel());
		
		relatorioManterAutoInfracao.addParametro("dataEmissaoInicial",
				form.getDataEmissaoInicial());
		
		relatorioManterAutoInfracao.addParametro("dataEmissaoFinal",
				form.getDataEmissaoFinal());

		relatorioManterAutoInfracao.addParametro("dataInicioRecursoInicial",
				form.getDataInicioRecursoInicial());
		
		relatorioManterAutoInfracao.addParametro("dataInicioRecursoFinal",
				form.getDataInicioRecursoFinal());
		
		relatorioManterAutoInfracao.addParametro("dataTerminoRecursoInicial",
				form.getDataTerminoRecursoInicial());
		
		relatorioManterAutoInfracao.addParametro("dataTerminoRecursoFinal",
				form.getDataTerminoRecursoFinal());
		
		relatorioManterAutoInfracao.addParametro("idAutoInfracaoSituacao",
				form.getIdAutoInfracaoSituacao());
		
		relatorioManterAutoInfracao.addParametro("descricaoAutoInfracaoSituacao",
				form.getDescricaoAutoInfracaoSituacao());
		
		relatorioManterAutoInfracao.addParametro("idFiscalizacaoSituacao",
				form.getIdFiscalizacaoSituacao());
		
		relatorioManterAutoInfracao.addParametro("descricaoFiscalizacaoSituacao",
				form.getDescricaoFiscalizacaoSituacao());
		
		relatorioManterAutoInfracao.addParametro("idFuncionario",
				form.getIdFuncionario());
		
		relatorioManterAutoInfracao.addParametro("descricaoFuncionario",
				form.getDescricaoFuncionario());
		
		
		
		// chama o metódo de gerar relatório passando o código da analise
		// como parâmetro
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioManterAutoInfracao.addParametro("tipoFormatoRelatorio", Integer
				.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioManterAutoInfracao,
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
