package gcom.gui.relatorio.cobranca.spcserasa;

import gcom.cobranca.bean.NegativadorMovimentoRetornoResumoHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cobranca.spcserasa.RelatorioNegativadorMovimentoRetornoResumo;
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
 * action responsável pela exibição do relatório de Tipo do Registro do Negativador manter
 * 
 * @author Yara Taciane
 * @created 26 de Fevereiro de 2008
 */
public class GerarRelatorioNegativadorMovimentoRetornoResumoAction extends
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

		// Inicio da parte que vai mandar os parametros para o relatório
		NegativadorMovimentoRetornoResumoHelper parametros = new NegativadorMovimentoRetornoResumoHelper();
		
			// cria uma instância da classe do relatório
		  RelatorioNegativadorMovimentoRetornoResumo relatorio = new RelatorioNegativadorMovimentoRetornoResumo(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			
	         String   nomeNegativador = (String)sessao.getAttribute("nomeNegativador");	       
	         String   dataProcessamento = (String)sessao.getAttribute("dataProcessamento");
	         String   horaProcessamento = (String)sessao.getAttribute("horaProcessamento");
	         String   numeroSequencialArquivo = (String)sessao.getAttribute("numeroSequencialArquivo");
	         String   numeroRegistros = (String)sessao.getAttribute("numeroRegistros");	         
	         
	         parametros.setNomeNegativador(nomeNegativador);
	         parametros.setDataProcessamento(dataProcessamento);
	         parametros.setHoraProcessamento(horaProcessamento);
	         parametros.setNumeroSequencialArquivo(numeroSequencialArquivo);
	         parametros.setNumeroRegistros(numeroRegistros);
			
			// chama o metódo de gerar relatório passando o código da analise
			// como parâmetro
			String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
			if (tipoRelatorio == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
			
			   relatorio.addParametro("parametros",parametros);
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
