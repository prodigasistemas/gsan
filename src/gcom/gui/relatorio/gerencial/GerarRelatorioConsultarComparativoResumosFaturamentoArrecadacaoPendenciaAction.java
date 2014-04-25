package gcom.gui.relatorio.gerencial;

import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.gerencial.micromedicao.RelatorioResumoAnormalidadeLeitura;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.SistemaException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe 
 *
 * @author Pedro Alexandre
 * @date 07/06/2006
 */
public class GerarRelatorioConsultarComparativoResumosFaturamentoArrecadacaoPendenciaAction extends
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

		//ResumoAnormalidadeActionForm resumoAnormalidadeActionForm = (ResumoAnormalidadeActionForm) actionForm;

		Collection colecaoResumosAnormalidadeLeitura = (Collection) sessao
				.getAttribute("colecaoResumoAnormalidadeRelatorio");
		InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = 
				(InformarDadosGeracaoRelatorioConsultaHelper)sessao.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");

		// Inicio da parte que vai mandar os parametros para o relatório

		// Fim da parte que vai mandar os parametros para o relatório

		byte[] relatorioRetorno = null;

		OutputStream out = null;

		try {

			RelatorioResumoAnormalidadeLeitura relatorioResumoAnormalidadeLeitura = new RelatorioResumoAnormalidadeLeitura(
					(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			relatorioResumoAnormalidadeLeitura.addParametro(
					"opcaoTotalizacao",
					informarDadosGeracaoRelatorioConsultaHelper.getOpcaoTotalizacao());
			
			relatorioResumoAnormalidadeLeitura.addParametro(
						"colecaoResumosAnormalidadeLeitura",
						colecaoResumosAnormalidadeLeitura);

			// chama o metódo de gerar relatório passando o código da analise
			// como parâmetro
			relatorioRetorno = (byte[]) relatorioResumoAnormalidadeLeitura
					.executar();

			if (retorno == null) {
				// prepara a resposta para o popup
				httpServletResponse.setContentType("application/pdf");
				out = httpServletResponse.getOutputStream();
				out.write(relatorioRetorno);
				out.flush();
				out.close();
			}
		} catch (IOException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");

			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");

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
