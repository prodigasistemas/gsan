package gcom.gui.relatorio.cobranca.spcserasa;

import gcom.cobranca.NegativadorResultadoSimulacao;
import gcom.gui.cobranca.spcserasa.FiltrarNegativadorResultadoSimulacaoActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.spcserasa.RelatorioNegativadorResultadoSimulacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.FiltroNegativadorResultadoSimulacao;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * action responsável pela exibição do relatório de Resultado da Simulação do Negativador.
 * 
 * @author Yara Taciane
 * @created 16 de maio de 2008
 */
public class GerarRelatorioNegativadorResultadoSimulacaoAction extends
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

		FiltrarNegativadorResultadoSimulacaoActionForm form = (FiltrarNegativadorResultadoSimulacaoActionForm) actionForm;

		FiltroNegativadorResultadoSimulacao filtro = (FiltroNegativadorResultadoSimulacao) sessao.getAttribute("filtroNegativadorResultadoSimulacao");

		// Inicio da parte que vai mandar os parametros para o relatório

		NegativadorResultadoSimulacao parametros = new NegativadorResultadoSimulacao();
		String idComando = (String) form.getIdComando();
		
		// seta os parametros que serão mostrados no relatório
		parametros.setId(new Integer(idComando));			
		
		// Fim da parte que vai mandar os parametros para o relatório

			// cria uma instância da classe do relatório
		RelatorioNegativadorResultadoSimulacao relatorio = new RelatorioNegativadorResultadoSimulacao(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));

		relatorio.addParametro("filtroNegativadorResultadoSimulacao", filtro);
		relatorio.addParametro("parametros",parametros);

			// chama o metódo de gerar relatório passando o código da analise
			// como parâmetro
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}

			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			try {
				retorno = processarExibicaoRelatorio(relatorio,
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
